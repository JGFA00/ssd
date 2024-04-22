package com.ssid.server;
import java.io.IOException;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException{
        System.out.println("Running!");
        Server server = ServerBuilder.forPort(5003).addService(new GreeterImpl()).build();
        server.start();
        server.awaitTermination();

    }
}
