package com.ssd.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: auction.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AuctionGrpc {

  private AuctionGrpc() {}

  public static final java.lang.String SERVICE_NAME = "Auction";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.ssd.grpc.NodeInfoGRPC,
      com.ssd.grpc.PingResponse> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = com.ssd.grpc.NodeInfoGRPC.class,
      responseType = com.ssd.grpc.PingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ssd.grpc.NodeInfoGRPC,
      com.ssd.grpc.PingResponse> getPingMethod() {
    io.grpc.MethodDescriptor<com.ssd.grpc.NodeInfoGRPC, com.ssd.grpc.PingResponse> getPingMethod;
    if ((getPingMethod = AuctionGrpc.getPingMethod) == null) {
      synchronized (AuctionGrpc.class) {
        if ((getPingMethod = AuctionGrpc.getPingMethod) == null) {
          AuctionGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.ssd.grpc.NodeInfoGRPC, com.ssd.grpc.PingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.NodeInfoGRPC.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.PingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AuctionMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ssd.grpc.NodeInfoGRPC,
      com.ssd.grpc.NodeInfoGRPC> getFindNodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FindNode",
      requestType = com.ssd.grpc.NodeInfoGRPC.class,
      responseType = com.ssd.grpc.NodeInfoGRPC.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.ssd.grpc.NodeInfoGRPC,
      com.ssd.grpc.NodeInfoGRPC> getFindNodeMethod() {
    io.grpc.MethodDescriptor<com.ssd.grpc.NodeInfoGRPC, com.ssd.grpc.NodeInfoGRPC> getFindNodeMethod;
    if ((getFindNodeMethod = AuctionGrpc.getFindNodeMethod) == null) {
      synchronized (AuctionGrpc.class) {
        if ((getFindNodeMethod = AuctionGrpc.getFindNodeMethod) == null) {
          AuctionGrpc.getFindNodeMethod = getFindNodeMethod =
              io.grpc.MethodDescriptor.<com.ssd.grpc.NodeInfoGRPC, com.ssd.grpc.NodeInfoGRPC>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FindNode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.NodeInfoGRPC.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.NodeInfoGRPC.getDefaultInstance()))
              .setSchemaDescriptor(new AuctionMethodDescriptorSupplier("FindNode"))
              .build();
        }
      }
    }
    return getFindNodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ssd.grpc.BlockGRPC,
      com.ssd.grpc.Ack> getPropagateBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PropagateBlock",
      requestType = com.ssd.grpc.BlockGRPC.class,
      responseType = com.ssd.grpc.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ssd.grpc.BlockGRPC,
      com.ssd.grpc.Ack> getPropagateBlockMethod() {
    io.grpc.MethodDescriptor<com.ssd.grpc.BlockGRPC, com.ssd.grpc.Ack> getPropagateBlockMethod;
    if ((getPropagateBlockMethod = AuctionGrpc.getPropagateBlockMethod) == null) {
      synchronized (AuctionGrpc.class) {
        if ((getPropagateBlockMethod = AuctionGrpc.getPropagateBlockMethod) == null) {
          AuctionGrpc.getPropagateBlockMethod = getPropagateBlockMethod =
              io.grpc.MethodDescriptor.<com.ssd.grpc.BlockGRPC, com.ssd.grpc.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PropagateBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.BlockGRPC.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new AuctionMethodDescriptorSupplier("PropagateBlock"))
              .build();
        }
      }
    }
    return getPropagateBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ssd.grpc.NodeInfoGRPC,
      com.ssd.grpc.BlockGRPC> getGetBlockchainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBlockchain",
      requestType = com.ssd.grpc.NodeInfoGRPC.class,
      responseType = com.ssd.grpc.BlockGRPC.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.ssd.grpc.NodeInfoGRPC,
      com.ssd.grpc.BlockGRPC> getGetBlockchainMethod() {
    io.grpc.MethodDescriptor<com.ssd.grpc.NodeInfoGRPC, com.ssd.grpc.BlockGRPC> getGetBlockchainMethod;
    if ((getGetBlockchainMethod = AuctionGrpc.getGetBlockchainMethod) == null) {
      synchronized (AuctionGrpc.class) {
        if ((getGetBlockchainMethod = AuctionGrpc.getGetBlockchainMethod) == null) {
          AuctionGrpc.getGetBlockchainMethod = getGetBlockchainMethod =
              io.grpc.MethodDescriptor.<com.ssd.grpc.NodeInfoGRPC, com.ssd.grpc.BlockGRPC>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getBlockchain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.NodeInfoGRPC.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.BlockGRPC.getDefaultInstance()))
              .setSchemaDescriptor(new AuctionMethodDescriptorSupplier("getBlockchain"))
              .build();
        }
      }
    }
    return getGetBlockchainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ssd.grpc.Id,
      com.ssd.grpc.TransactionApp> getListAuctionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListAuctions",
      requestType = com.ssd.grpc.Id.class,
      responseType = com.ssd.grpc.TransactionApp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.ssd.grpc.Id,
      com.ssd.grpc.TransactionApp> getListAuctionsMethod() {
    io.grpc.MethodDescriptor<com.ssd.grpc.Id, com.ssd.grpc.TransactionApp> getListAuctionsMethod;
    if ((getListAuctionsMethod = AuctionGrpc.getListAuctionsMethod) == null) {
      synchronized (AuctionGrpc.class) {
        if ((getListAuctionsMethod = AuctionGrpc.getListAuctionsMethod) == null) {
          AuctionGrpc.getListAuctionsMethod = getListAuctionsMethod =
              io.grpc.MethodDescriptor.<com.ssd.grpc.Id, com.ssd.grpc.TransactionApp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListAuctions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.Id.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.TransactionApp.getDefaultInstance()))
              .setSchemaDescriptor(new AuctionMethodDescriptorSupplier("ListAuctions"))
              .build();
        }
      }
    }
    return getListAuctionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.ssd.grpc.TransactionApp,
      com.ssd.grpc.Ack> getSubmitTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitTransaction",
      requestType = com.ssd.grpc.TransactionApp.class,
      responseType = com.ssd.grpc.Ack.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.ssd.grpc.TransactionApp,
      com.ssd.grpc.Ack> getSubmitTransactionMethod() {
    io.grpc.MethodDescriptor<com.ssd.grpc.TransactionApp, com.ssd.grpc.Ack> getSubmitTransactionMethod;
    if ((getSubmitTransactionMethod = AuctionGrpc.getSubmitTransactionMethod) == null) {
      synchronized (AuctionGrpc.class) {
        if ((getSubmitTransactionMethod = AuctionGrpc.getSubmitTransactionMethod) == null) {
          AuctionGrpc.getSubmitTransactionMethod = getSubmitTransactionMethod =
              io.grpc.MethodDescriptor.<com.ssd.grpc.TransactionApp, com.ssd.grpc.Ack>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubmitTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.TransactionApp.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.ssd.grpc.Ack.getDefaultInstance()))
              .setSchemaDescriptor(new AuctionMethodDescriptorSupplier("SubmitTransaction"))
              .build();
        }
      }
    }
    return getSubmitTransactionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuctionStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuctionStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuctionStub>() {
        @java.lang.Override
        public AuctionStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuctionStub(channel, callOptions);
        }
      };
    return AuctionStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuctionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuctionBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuctionBlockingStub>() {
        @java.lang.Override
        public AuctionBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuctionBlockingStub(channel, callOptions);
        }
      };
    return AuctionBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuctionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuctionFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuctionFutureStub>() {
        @java.lang.Override
        public AuctionFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuctionFutureStub(channel, callOptions);
        }
      };
    return AuctionFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void ping(com.ssd.grpc.NodeInfoGRPC request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.PingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    default void findNode(com.ssd.grpc.NodeInfoGRPC request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.NodeInfoGRPC> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindNodeMethod(), responseObserver);
    }

    /**
     */
    default void propagateBlock(com.ssd.grpc.BlockGRPC request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPropagateBlockMethod(), responseObserver);
    }

    /**
     */
    default void getBlockchain(com.ssd.grpc.NodeInfoGRPC request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.BlockGRPC> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBlockchainMethod(), responseObserver);
    }

    /**
     */
    default void listAuctions(com.ssd.grpc.Id request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.TransactionApp> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListAuctionsMethod(), responseObserver);
    }

    /**
     */
    default void submitTransaction(com.ssd.grpc.TransactionApp request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.Ack> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubmitTransactionMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Auction.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class AuctionImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AuctionGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Auction.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class AuctionStub
      extends io.grpc.stub.AbstractAsyncStub<AuctionStub> {
    private AuctionStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuctionStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuctionStub(channel, callOptions);
    }

    /**
     */
    public void ping(com.ssd.grpc.NodeInfoGRPC request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.PingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void findNode(com.ssd.grpc.NodeInfoGRPC request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.NodeInfoGRPC> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getFindNodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void propagateBlock(com.ssd.grpc.BlockGRPC request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPropagateBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBlockchain(com.ssd.grpc.NodeInfoGRPC request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.BlockGRPC> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetBlockchainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listAuctions(com.ssd.grpc.Id request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.TransactionApp> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListAuctionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void submitTransaction(com.ssd.grpc.TransactionApp request,
        io.grpc.stub.StreamObserver<com.ssd.grpc.Ack> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubmitTransactionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Auction.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class AuctionBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AuctionBlockingStub> {
    private AuctionBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuctionBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuctionBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.ssd.grpc.PingResponse ping(com.ssd.grpc.NodeInfoGRPC request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.ssd.grpc.NodeInfoGRPC> findNode(
        com.ssd.grpc.NodeInfoGRPC request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getFindNodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.ssd.grpc.Ack propagateBlock(com.ssd.grpc.BlockGRPC request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPropagateBlockMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.ssd.grpc.BlockGRPC> getBlockchain(
        com.ssd.grpc.NodeInfoGRPC request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetBlockchainMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.ssd.grpc.TransactionApp> listAuctions(
        com.ssd.grpc.Id request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListAuctionsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.ssd.grpc.Ack submitTransaction(com.ssd.grpc.TransactionApp request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubmitTransactionMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Auction.
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class AuctionFutureStub
      extends io.grpc.stub.AbstractFutureStub<AuctionFutureStub> {
    private AuctionFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuctionFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuctionFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ssd.grpc.PingResponse> ping(
        com.ssd.grpc.NodeInfoGRPC request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ssd.grpc.Ack> propagateBlock(
        com.ssd.grpc.BlockGRPC request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPropagateBlockMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ssd.grpc.Ack> submitTransaction(
        com.ssd.grpc.TransactionApp request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubmitTransactionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;
  private static final int METHODID_FIND_NODE = 1;
  private static final int METHODID_PROPAGATE_BLOCK = 2;
  private static final int METHODID_GET_BLOCKCHAIN = 3;
  private static final int METHODID_LIST_AUCTIONS = 4;
  private static final int METHODID_SUBMIT_TRANSACTION = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.ping((com.ssd.grpc.NodeInfoGRPC) request,
              (io.grpc.stub.StreamObserver<com.ssd.grpc.PingResponse>) responseObserver);
          break;
        case METHODID_FIND_NODE:
          serviceImpl.findNode((com.ssd.grpc.NodeInfoGRPC) request,
              (io.grpc.stub.StreamObserver<com.ssd.grpc.NodeInfoGRPC>) responseObserver);
          break;
        case METHODID_PROPAGATE_BLOCK:
          serviceImpl.propagateBlock((com.ssd.grpc.BlockGRPC) request,
              (io.grpc.stub.StreamObserver<com.ssd.grpc.Ack>) responseObserver);
          break;
        case METHODID_GET_BLOCKCHAIN:
          serviceImpl.getBlockchain((com.ssd.grpc.NodeInfoGRPC) request,
              (io.grpc.stub.StreamObserver<com.ssd.grpc.BlockGRPC>) responseObserver);
          break;
        case METHODID_LIST_AUCTIONS:
          serviceImpl.listAuctions((com.ssd.grpc.Id) request,
              (io.grpc.stub.StreamObserver<com.ssd.grpc.TransactionApp>) responseObserver);
          break;
        case METHODID_SUBMIT_TRANSACTION:
          serviceImpl.submitTransaction((com.ssd.grpc.TransactionApp) request,
              (io.grpc.stub.StreamObserver<com.ssd.grpc.Ack>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getPingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ssd.grpc.NodeInfoGRPC,
              com.ssd.grpc.PingResponse>(
                service, METHODID_PING)))
        .addMethod(
          getFindNodeMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.ssd.grpc.NodeInfoGRPC,
              com.ssd.grpc.NodeInfoGRPC>(
                service, METHODID_FIND_NODE)))
        .addMethod(
          getPropagateBlockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ssd.grpc.BlockGRPC,
              com.ssd.grpc.Ack>(
                service, METHODID_PROPAGATE_BLOCK)))
        .addMethod(
          getGetBlockchainMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.ssd.grpc.NodeInfoGRPC,
              com.ssd.grpc.BlockGRPC>(
                service, METHODID_GET_BLOCKCHAIN)))
        .addMethod(
          getListAuctionsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.ssd.grpc.Id,
              com.ssd.grpc.TransactionApp>(
                service, METHODID_LIST_AUCTIONS)))
        .addMethod(
          getSubmitTransactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.ssd.grpc.TransactionApp,
              com.ssd.grpc.Ack>(
                service, METHODID_SUBMIT_TRANSACTION)))
        .build();
  }

  private static abstract class AuctionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AuctionBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ssd.grpc.AuctionOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Auction");
    }
  }

  private static final class AuctionFileDescriptorSupplier
      extends AuctionBaseDescriptorSupplier {
    AuctionFileDescriptorSupplier() {}
  }

  private static final class AuctionMethodDescriptorSupplier
      extends AuctionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AuctionMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AuctionGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuctionFileDescriptorSupplier())
              .addMethod(getPingMethod())
              .addMethod(getFindNodeMethod())
              .addMethod(getPropagateBlockMethod())
              .addMethod(getGetBlockchainMethod())
              .addMethod(getListAuctionsMethod())
              .addMethod(getSubmitTransactionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
