package com.ssd.server;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.ssd.blockchain.Block;
import com.ssd.blockchain.Blockchain;
import com.ssd.blockchain.Transaction;
import com.ssd.client.AuctionClient;
import com.ssd.grpc.Ack;
import com.ssd.grpc.AuctionGrpc;
import com.ssd.grpc.BlockGRPC;
import com.ssd.grpc.Id;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.PingResponse;
import com.ssd.grpc.TransactionApp;
import com.ssd.grpc.NodeInfoGRPC;
import com.ssd.kademlia.NodeInfo;
import com.ssd.kademlia.RoutingTable;
import com.ssd.util.AuctionUtil;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class AuctionServer {
    
    private final int port;
    private final Server server;

    public AuctionServer(NodeInfoGRPC nodeinfo, Blockchain blockchain, LinkedList<Transaction> tlist, RoutingTable routingTable) {
        this(Grpc.newServerBuilderForPort(nodeinfo.getPort(), InsecureServerCredentials.create()),nodeinfo, blockchain, tlist, routingTable);
    }

    public AuctionServer(ServerBuilder<?> serverBuilder,NodeInfoGRPC nodeinfo, Blockchain blockchain, LinkedList<Transaction> tlist, RoutingTable routingTable) {
        this.port = nodeinfo.getPort();
        server = serverBuilder.addService(new AuctionService(nodeinfo, blockchain, tlist, routingTable)).build();

    }

    
    public void start() throws IOException {
        System.out.println("got here");
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                AuctionServer.this.stop();
                } catch (InterruptedException e) {
                e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
          server.awaitTermination();
        }
    }
    

    private static class AuctionService extends AuctionGrpc.AuctionImplBase {
        private final NodeInfoGRPC nodeinfo;
        private Blockchain blockchain;
        private LinkedList<Transaction> tlist;
        private RoutingTable routingTable;

        //implementação do auction service, estes métodos todos são da perspetiva do servidor e estão à escuta de mensagens de outros nos
        public AuctionService(NodeInfoGRPC nodeinfo, Blockchain blockchain, LinkedList<Transaction> tlist, RoutingTable routingTable){
            this.nodeinfo = nodeinfo;
            this.blockchain = blockchain;
            this.tlist = tlist;
            this.routingTable = routingTable;
        }
        
        //nodeid é do cliente, é possivel ter que fazer alguma coisa com o ping recebido (verificar o kbucket nós inativos)
        //verifica se tem o node na routing table, se não faz um find node
        @Override 
        public void ping(NodeInfoGRPC nodeInfo, StreamObserver<PingResponse> responseObserver){
            NodeInfo convertedNode =AuctionUtil.convertNodeInfoGRPCtoNodeInfo(nodeInfo); 
            System.out.println(convertedNode.toString());
            checkNodeInRoutinTable(convertedNode);
            //o que fazer com um ping? para já retorna só resposta ao cliente que enviou o ping
            PingResponse response = PingResponse.newBuilder().setResponse("active").build(); 
            responseObserver.onNext(response); 
            System.out.println("ping responded"); 
            responseObserver.onCompleted(); 
        } 

        //à espera de pedidos find node de outros nós
        @Override 
        public void findNode(NodeInfoGRPC nodeInfo, StreamObserver<NodeInfoGRPC> responseObserver){
            System.out.println("Find Node received printing my routing table\n");
            this.routingTable.print();
            NodeInfo convertedNode =AuctionUtil.convertNodeInfoGRPCtoNodeInfo(nodeInfo);
            checkNodeInRoutinTable(convertedNode);
            //Return the 3 closest nodes
            List<NodeInfo> closestNodes = routingTable.findClosestNodes(convertedNode.getId(), 3);
            List<NodeInfoGRPC> grpcClosestNodes = AuctionUtil.convertNodeInfoListToNodeInfoGRPCList(closestNodes);
            //este response observer.onnext(node) retorna o node para o canal com o cliente que invocou o findnode
            for(NodeInfoGRPC node : grpcClosestNodes){ 
                responseObserver.onNext(node);           
               
            }
            //on completed dá a call por terminada e termina o canal
            responseObserver.onCompleted();
        }

        //este método está a escuta de outros nós enviarem um bloco
        @Override   
        public void propagateBlock(BlockGRPC block, StreamObserver<Ack> responseObserver) {
            NodeInfo convertedNode =AuctionUtil.convertNodeInfoGRPCtoNodeInfo(block.getNinfo());
            checkNodeInRoutinTable(convertedNode);
            Ack ack = Ack.newBuilder().setAcknowledge("received").build();
            responseObserver.onNext(ack);
            responseObserver.onCompleted();
            Block b = AuctionUtil.convertBlockGrpctoBlock(block);
            if(b.verifyBlock()){
                if(verifyPrevHash(b)){
                    blockchain.addBlock(b);
                }
                //conflict in blockchain, check timestamp
                else{
                    System.out.println("Conflict in blockchain, resolving\n");
                    Block current = blockchain.getLastBlock();
                    Block secondlast = blockchain.getSecondLastBlock();
                    if(b.getPrevHash() == secondlast.getBlockHash()){
                        if(new BigInteger(b.getBlockHash(),16).compareTo(new BigInteger(current.getBlockHash(), 16)) == -1){
                            blockchain.blockchain.remove(current);
                            blockchain.addBlock(b);
                        }
                        
                    }
                }
            }
            
        }

        //da perspetiva do cliente, envia um pedido get blockchain e manda o seu id, recebe uma stream de blocos (blockchain)
        //da perspetiva do servidor recebe um pedido get blockchain do no nodeid e envia uma stream de blocos (blockchain)
        @Override
        public void getBlockchain(NodeInfoGRPC ninfo, StreamObserver<BlockGRPC> responseObserver) {
            //converter todos os blocos e enviar
            
            for (Block block : blockchain.getBlockchain()){
                BlockGRPC b = AuctionUtil.convertBlocktoBlockGRPC(block);
                responseObserver.onNext(b);
                
            }
            //on completed dá a call por terminada e termina o canal
            responseObserver.onCompleted();
        }

        //eventualmente vai haver aqui um método submitTransaction (server), que quando acumular transações suficientes gera um bloco, 
        //e passa a uma chamada propagate block da perspetiva do cliente (cria um auctionclient e envia para todos os nós presentes 
        //na routing table um propagate block)
        @Override
        public void submitTransaction(TransactionApp t, StreamObserver<Ack> responseObserver){
            //eventualmente um verify transaction que verifica a chave publica do user, que a auction está a decorrer etc etc
            Transaction trans = AuctionUtil.convertTransactionApptoTransaction(t);
            String responsemessage = "";
            Random rand = new Random();
            int upperbound = 1000;
            HashMap<Integer, Transaction> activeAuctions = blockchain.getActiveAuctions();
            switch (trans.getType()) {
                case "bid":
                    if(verifyValidAuctionId(trans.getAuctionId(), activeAuctions)){
                        responsemessage = "Bid accepted";
                        tlist.add(trans);
                    }
                    else {
                        responsemessage = "Auction not ongoing, try again";
                    }
                    break;
                case "start_auction":
                    int auctionid = rand.nextInt(upperbound);
                    while(verifyValidAuctionId(auctionid, activeAuctions)){
                        auctionid = rand.nextInt(upperbound);
                    }
                    trans.setAuctionId(auctionid);
                    tlist.add(trans);
                    responsemessage = "Auction will be started";
                    break;

                case "end_auction":
                    if(verifyCorrectUser(trans.getAuctionId(), trans.getUserId(), activeAuctions)){

                        if(verifyValidAuctionId(trans.getAuctionId(), activeAuctions)){
                            responsemessage = "Auction will be ended soon";
                            tlist.add(trans);
                        }
                        else {
                            responsemessage = "auction not ongoing";
                        }
                    }
                    else{
                        responsemessage = "You don't own the auction";
                    }
                    break;
                default:
                    responsemessage = "transaction not supported";
            }
            //Boolean verify = trans.validateTransaction(id nó);
            Ack ack = Ack.newBuilder().setAcknowledge(responsemessage).build();
            responseObserver.onNext(ack);
            responseObserver.onCompleted();

        }

        //ao receber um pedido de listAuctions, percorrer a blockchain e retornar as auctions ativas
        @Override
        public void listAuctions(Id id, StreamObserver<TransactionApp> responseObserver){
            List<TransactionApp> activeAuctions = new ArrayList<>();
            HashMap<Integer, Transaction> map = blockchain.getActiveAuctions();
            map.forEach((key, value) -> {
                activeAuctions.add(AuctionUtil.convertTransactiontoTransactionAPP(value));
            });

            for(TransactionApp t : activeAuctions){
                responseObserver.onNext(t);
            }

            responseObserver.onCompleted();

        }

        
        public void checkNodeInRoutinTable(NodeInfo nodeInfo){
            if(!this.routingTable.containsNode(nodeInfo)){
                this.routingTable.addNode(nodeInfo);
            }
        }

        public Boolean verifyValidAuctionId(int auctionid, HashMap<Integer,Transaction> activeAuctions){
            if(activeAuctions.containsKey(auctionid)){
                return true;
            }
            return false;
        }

        public Boolean verifyCorrectUser(int auctionid ,int userid, HashMap<Integer,Transaction> activeAuctions){
            if(activeAuctions.get(auctionid).userId == userid){
                return true;
            }
            return false;
        }


    }


}
