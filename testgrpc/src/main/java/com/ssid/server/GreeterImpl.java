package com.ssid.server;
import com.ssid.grpc.HelloRequest;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import com.ssid.grpc.GreeterGrpc;
import com.ssid.grpc.HelloReply;

public class GreeterImpl extends GreeterGrpc.GreeterImplBase{

    private List<String> list = new ArrayList<>(); 

    @Override
    public void sayHello (HelloRequest hello, StreamObserver<HelloReply> responseObserver){
        String hellomessage = hello.getName();
        String replymessage = "Hello, " + hellomessage + ".";
        HelloReply reply = HelloReply.newBuilder().setMessage(replymessage).build();
        System.out.println(hellomessage);
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        list.add(hellomessage);
        if(list.size()==3){
            System.out.println(list);
        }
 
    }

}