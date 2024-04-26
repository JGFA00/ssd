package com.ssd.server;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.ssd.client.AuctionClient;
import com.ssd.grpc.AuctionGrpc;
import com.ssd.grpc.Node;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.PingResponse;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class AuctionServer {

    private final int port;
    private final Server server;

    public AuctionServer(int port) {
        this(Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create()),
        port);  
    }

    public AuctionServer(ServerBuilder<?> serverBuilder, int port) {
        this.port = port;
        server = serverBuilder.addService(new AuctionService()).build();
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

    public static void main(String[] args) throws IOException {
        AuctionServer server = new AuctionServer(5000);
        //a primeira coisa aqui até vai ser um nodelookup como cliente para o bootstrap, depois é que se inicializa o servidor
        //eventualmente podemos por o find node assincrono para poder começar o servidor ao mesmo tempo
        
        //AuctionClient client = new AuctionClient("localhost", 6000);
        //client.findNode(0);
        server.start();
        try {
            server.blockUntilShutdown();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static class AuctionService extends AuctionGrpc.AuctionImplBase {

        private List<Node> nodes;
        
        private AuctionService(){
            nodes = new ArrayList<>(); 
            Node n1 = Node.newBuilder().setId(1).setIpAddress("192.168.1").setPort(1).build();
            Node n2 = Node.newBuilder().setId(2).setIpAddress("192.168.2").setPort(2).build();
            Node n3 = Node.newBuilder().setId(3).setIpAddress("192.168.3").setPort(3).build();
            nodes.add(n1);
            nodes.add(n2);
            nodes.add(n3);
        }

        //eventualmente vai haver aqui um método submitTransaction (server), que quando acumular transações suficientes gera um bloco, 
        //e passa a uma chamada propagate block da perspetiva do cliente (cria um auctionclient e envia para todos os nós presentes 
        //na routing table um propagate block)
        
        @Override
        public void ping (NodeID nodeid, StreamObserver<PingResponse> responseObserver){
            //o que fazer com um ping? para já retorna só resposta ao cliente que enviou o ping
            PingResponse response = PingResponse.newBuilder().setResponse("active").build();
            responseObserver.onNext(response);
            System.out.println("ping responded");
            responseObserver.onCompleted();
        }

        @Override
        public void findNode (NodeID nodeid, StreamObserver<Node> responseObserver){
            // Implementar aqui a funcionalidade do find node da perspetiva do servidor - que nós da routing table retorna?
            for (Node node : nodes){
                //este response observer.onnext(node) retorna o node para o canal com o cliente que invocou o findnode 
                responseObserver.onNext(node);
                
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            //on completed dá a call por terminada e termina o canal
            responseObserver.onCompleted();
        }

    }



}
