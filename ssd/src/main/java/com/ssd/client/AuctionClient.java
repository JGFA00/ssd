package com.ssd.client;
import com.ssd.grpc.AuctionGrpc;
import com.ssd.grpc.AuctionGrpc.AuctionBlockingStub;
import com.ssd.grpc.AuctionGrpc.AuctionStub;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.PingResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class AuctionClient {

    final AuctionBlockingStub blockingStub;
    final AuctionStub asyncStub;
    final ManagedChannel channel;

    public AuctionClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        blockingStub = AuctionGrpc.newBlockingStub(channel);
        asyncStub = AuctionGrpc.newStub(channel);
    } 

    //este node id é o id do próprio nó que está a enviar o ping
    public void ping(int nodeid) {
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
    
    //O cliente não precisa do main (só a app, que só vai ter cliente, é que precisa) é mais para teste. o Nó vai estar a correr 
    //servidor e a chamar métodos aqui do cliente para interagir com outros nós
    public static void main(String[] args){
        AuctionClient client = new AuctionClient("localhost", 5000);
        client.findNode(3);
        client.ping(3);
        
    } 
}
