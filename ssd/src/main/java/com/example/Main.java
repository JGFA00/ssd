package com.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private final int port;
    private EventLoopGroup workerGroup;

    public Main(int port) {
        this.port = port;
    }
    
    public void start() throws Exception {
        // Server part
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new StringDecoder(), new StringEncoder(), new SimpleChannelInboundHandler<String>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                    System.out.println("Received: " + msg);
                                    // Echo back the message
                                    ctx.writeAndFlush("Pong!\n");
                                }
                            });
                        }
                    });

            // Start the server.
            b.bind(port).sync();
            System.out.println("Server started on port " + port);

            // Start a new thread to handle console input
            new Thread(this::handleConsoleInput).start();
            
            // Keep the main thread alive
            Thread.currentThread().join();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private void handleConsoleInput() {
        System.out.println("Enter 'ping <port>' to send a ping message to the specified port:");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ping")) {
                    String[] parts = line.split(" ");
                    if (parts.length == 2) {
                        int targetPort = Integer.parseInt(parts[1]);
                        sendPingMessage(targetPort);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendPingMessage(int targetPort) {
        Bootstrap c = new Bootstrap();
        c.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder(), new StringEncoder(), new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println("Received: " + msg);
                                ctx.close(); // Close the context after receiving the message
                            }
    
                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
                                cause.printStackTrace();
                                ctx.close();
                            }
                        });
                    }
                });
        try {
            ChannelFuture f = c.connect("localhost", targetPort).sync(); // Connect to the server
            System.out.println("Sending ping to port " + targetPort);
            f.channel().writeAndFlush("Ping!\n");
    
            f.channel().closeFuture().sync(); // Wait for the close signal before shutting down
        } catch (Exception e) {
            System.err.println("Could not connect to port " + targetPort + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            int port = Integer.parseInt(args[0]);
            new Main(port).start();
        } else {
            System.out.println("Please provide a port number as an argument.");
        }
    }
}
