package com.ssid.server;
import com.ssid.grpc.HelloRequest;
import com.ssid.grpc.Node;
import com.ssid.grpc.NodeID;

import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import com.ssid.grpc.GreeterGrpc;
import com.ssid.grpc.HelloReply;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase{

    private List<Node> nodes;

    public GreeterImpl(){
        nodes = new ArrayList<>(); 
        Node n1 = Node.newBuilder().setId(1).setIpAddress("192.168.1").setPort(1).build();
        Node n2 = Node.newBuilder().setId(2).setIpAddress("192.168.2").setPort(2).build();
        Node n3 = Node.newBuilder().setId(3).setIpAddress("192.168.3").setPort(3).build();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
    }

    @Override
    public void sayHello (HelloRequest hello, StreamObserver<HelloReply> responseObserver){
        String hellomessage = hello.getName();
        String replymessage = "Hello, " + hellomessage + ".";
        HelloReply reply = HelloReply.newBuilder().setMessage(replymessage).build();
        System.out.println(hellomessage);
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
 
    }

    @Override
    public void findNode (NodeID nodeid, StreamObserver<Node> responseObserver){
        for (Node node : nodes){
            responseObserver.onNext(node);
        }
        responseObserver.onCompleted();

    }

}