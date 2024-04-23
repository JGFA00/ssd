package com.ssid.client;
import com.ssid.grpc.GreeterGrpc;
import com.ssid.grpc.HelloReply;
import com.ssid.grpc.HelloRequest;
import com.ssid.grpc.NodeID;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

    public class GreeterClient {
        public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5003).usePlaintext().build();
        String name = "Name";
        HelloReply reply;
        GreeterGrpc.GreeterBlockingStub stub =GreeterGrpc.newBlockingStub(channel);
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        reply = stub.sayHello(request);
        System.out.println("Response for first call: " + reply.getMessage());
        NodeID id = NodeID.newBuilder().setId(1).build();
        stub.findNode(id).forEachRemaining(Node -> {
            System.out.println(Node.getId());
        });
        
    }
}
