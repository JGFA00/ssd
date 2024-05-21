package com.ssd.server;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import com.ssd.blockchain.Blockchain;
import com.ssd.grpc.Ack;
import com.ssd.grpc.AuctionGrpc;
import com.ssd.grpc.Block;
import com.ssd.grpc.Id;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.PingResponse;
import com.ssd.grpc.TransactionKad;
import com.ssd.grpc.NodeInfo;
import com.ssd.kademlia.RoutingTable;


import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class AuctionServer {
    
    private final int port;
    private final Server server;
    /*
    private final NodeInfo nodeinfo;
    private Blockchain blockchain;
    private LinkedList<Transaction> tlist;
    private RoutingTable routingTable;
     */

    public AuctionServer(NodeInfo nodeinfo, Blockchain blockchain, LinkedList<TransactionKad> tlist, RoutingTable routingTable) {
        this(Grpc.newServerBuilderForPort(nodeinfo.getPort(), InsecureServerCredentials.create()),nodeinfo, blockchain, tlist, routingTable);
    }

    public AuctionServer(ServerBuilder<?> serverBuilder,NodeInfo nodeinfo, Blockchain blockchain, LinkedList<TransactionKad> tlist, RoutingTable routingTable) {
        this.port = nodeinfo.getPort();
        server = serverBuilder.addService(new AuctionService(nodeinfo, blockchain, tlist, routingTable)).build();
        /*
        this.nodeinfo = nodeinfo;
        this.blockchain = blockchain;
        this.tlist = tlist;
        this.routingTable = routingTable;
         */
        
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

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
          server.awaitTermination();
        }
    }
  

    private static class AuctionService extends AuctionGrpc.AuctionImplBase {
        private final NodeInfo nodeinfo;
        private Blockchain blockchain;
        private LinkedList<TransactionKad> tlist;
        private RoutingTable routingTable;

        public AuctionService(NodeInfo nodeinfo, Blockchain blockchain, LinkedList<TransactionKad> tlist, RoutingTable routingTable){
            this.nodeinfo = nodeinfo;
            this.blockchain = blockchain;
            this.tlist = tlist;
            this.routingTable = routingTable;
        }
        
        @Override 
        public void ping (NodeID nodeid, StreamObserver<PingResponse> responseObserver){ 
            //o que fazer com um ping? para já retorna só resposta ao cliente que enviou o ping 
            PingResponse response = PingResponse.newBuilder().setResponse("active").build(); 
            responseObserver.onNext(response); 
            System.out.println("ping responded"); 
            responseObserver.onCompleted(); 
        } 
 
        //este NodeID é o id do nó que o cliente quer encontrar
        @Override 
        public void findNode(NodeID nodeid, StreamObserver<NodeInfo> responseObserver){
            /*
            //Return the 3 closest nodes
            List<NodeInfo> closestNodes = routingTable.findClosestNodes(nodeid.getId(), 3);
            //este response observer.onnext(node) retorna o node para o canal com o cliente que invocou o findnode
            for(NodeInfo node : closestNodes){ 
                responseObserver.onNext(node);           
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            */
            //on completed dá a call por terminada e termina o canal
            responseObserver.onCompleted();
        }

        @Override
        public void propagateBlock(Block block, StreamObserver<Ack> responseObserver) {
            Ack ack = Ack.newBuilder().setAcknowledge("received").build();
            responseObserver.onNext(ack);
            System.out.println(block.getAllFields());
            responseObserver.onCompleted();
            
        }

        //da perspetiva do cliente, envia um pedido get blockchain e manda o seu id, recebe uma stream de blocos (blockchain)
        //da perspetiva do servidor recebe um pedido get blockchain do no nodeid e envia uma stream de blocos (blockchain)
        @Override
        public void getBlockchain(NodeID nodeid, StreamObserver<Block> responseObserver) {
            /*
            for (Block block : blockchain){
                responseObserver.onNext(block);
                
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
             */
            //on completed dá a call por terminada e termina o canal
            responseObserver.onCompleted();
        }

        //eventualmente vai haver aqui um método submitTransaction (server), que quando acumular transações suficientes gera um bloco, 
        //e passa a uma chamada propagate block da perspetiva do cliente (cria um auctionclient e envia para todos os nós presentes 
        //na routing table um propagate block)
        @Override
        public void submitTransaction(TransactionKad t, StreamObserver<Ack> responseObserver){
            //eventualmente um verify transaction que verifica a chave publica do user, que a auction está a decorrer etc etc
            Ack ack = Ack.newBuilder().setAcknowledge("Transaction received").build();
            responseObserver.onNext(ack);
            responseObserver.onCompleted();
            tlist.add(t);
            

        }

        //ao receber um pedido de listAuctions, percorrer a blockchain e retornar as auctions ativas
        @Override
        public void listAuctions(Id id, StreamObserver<TransactionKad> responseObserver){
            
        }

    }


}
