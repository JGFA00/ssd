package com.ssd.client;

import com.ssd.grpc.Ack;
import com.ssd.grpc.AuctionGrpc;
import com.ssd.grpc.Block;
import com.ssd.grpc.AuctionGrpc.AuctionBlockingStub;
import com.ssd.grpc.AuctionGrpc.AuctionStub;
import com.ssd.util.AuctionUtil;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.PingResponse;
import com.ssd.grpc.TransactionsList;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class AuctionClient {

    final AuctionBlockingStub blockingStub;
    final AuctionStub asyncStub;
    final ManagedChannel channel;

    public AuctionClient(NodeInfo nodeInfo) {
        channel = ManagedChannelBuilder.forAddress(nodeInfo.getIpAddress(), ondeInfo.getPort).usePlaintext().build();
        blockingStub = AuctionGrpc.newBlockingStub(channel);
        asyncStub = AuctionGrpc.newStub(channel);
    } 

    //este node id é o id do próprio nó que está a enviar o ping
    public void ping() {
        NodeID id = NodeID.newBuilder().setId(nodeid).build();
        PingResponse response;
        response = blockingStub.ping(id);
        System.out.println(response.getResponse());
    }

    //aqui implementa-se o find node da perspetiva do cliente, o que queremos fazer com os nós que recebemos? adicionar à 
    //routing table etc etc
    //Terá que ser asincrono? para já fica sincrono
    //este node id é o id do nó que queremos encontrar
    public void findNode(int nodeid) {
        NodeID id = NodeID.newBuilder().setId(nodeid).build();
        //aqui estamos a invocar o findNode do servidor, passando um id para o canal criado e a receber a resposta
        blockingStub.findNode(id).forEachRemaining(Node -> {
            System.out.println(Node.getId());
        });
    }

    public void propagateBlock(Block block){
        Ack ack;
        ack = blockingStub.propagateBlock(block);
        System.out.println(ack.getAcknowledge());
    }

    public void getBlockchain(int nodeid) {
        NodeID id = NodeID.newBuilder().setId(nodeid).build();
        //aqui estamos a invocar o findNode do servidor, passando um id para o canal criado e a receber a resposta
        blockingStub.getBlockchain(id).forEachRemaining(Block -> {
            System.out.println(Block.getAllFields());
        });
    }
    
    //O cliente não precisa do main (só a app, que só vai ter cliente, é que precisa) é mais para teste. o Nó vai estar a correr 
    //servidor e a chamar métodos aqui do cliente para interagir com outros nós
    public static void main(String[] args){
        //TransactionsList tlist = AuctionUtil.createEmptyTransactionsList();
        //Block block = AuctionUtil.createBlock("dd", 0, 0, "dd", "dd", tlist);
        //AuctionClient client = new AuctionClient("localhost", 5000);
        //client.findNode(3);
        //client.propagateBlock(block);
        //client.ping(3);
        //client.getBlockchain(3);
    } 
}
