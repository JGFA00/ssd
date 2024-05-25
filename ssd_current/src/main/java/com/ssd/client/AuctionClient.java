package com.ssd.client;
import com.ssd.grpc.Ack;
import com.ssd.grpc.AuctionGrpc;
import com.ssd.grpc.BlockGRPC;
import com.ssd.grpc.AuctionGrpc.AuctionBlockingStub;
import com.ssd.grpc.AuctionGrpc.AuctionStub;
import com.ssd.util.AuctionUtil;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.NodeInfoGRPC;
import com.ssd.grpc.PingResponse;
import com.ssd.grpc.TransactionsList;

import java.util.ArrayList;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class AuctionClient {
    NodeInfoGRPC targetnodeinfo;
    NodeInfoGRPC selfnodeinfo;
    final AuctionBlockingStub blockingStub;
    final AuctionStub asyncStub;
    final ManagedChannel channel;

    public AuctionClient(NodeInfoGRPC targetnodeInfo, NodeInfoGRPC selfnodeinfo) {
        channel = ManagedChannelBuilder.forAddress(targetnodeInfo.getIp(), targetnodeInfo.getPort()).usePlaintext().build();
        blockingStub = AuctionGrpc.newBlockingStub(channel);
        asyncStub = AuctionGrpc.newStub(channel);
        this.targetnodeinfo = targetnodeInfo;
        this.selfnodeinfo = selfnodeinfo;
    } 

    //public AuctionClient(NodeInfo nodeInfo, RoutingTable routingTable)
    //este node id é o id do próprio nó que está a enviar o ping
    public Boolean ping() {
        PingResponse response;
        response = blockingStub.ping(targetnodeinfo);
        System.out.println(response.getResponse());
        return true;
    }

    //este node id é o id do nó que queremos encontrar
    public List<NodeInfoGRPC> findNode(String nodeid) {
        NodeID id = NodeID.newBuilder().setId(nodeid).build();
        List<NodeInfoGRPC> Nodes = new ArrayList<>();
        //aqui estamos a invocar o findNode do servidor, passando um id para o canal criado e a receber a resposta
        blockingStub.findNode(selfnodeinfo).forEachRemaining(Node -> {
            Nodes.add(Node);
        });
        return Nodes;
    }

    public void propagateBlock(BlockGRPC block){
        Ack ack;
        ack = blockingStub.propagateBlock(block);
        System.out.println(ack.getAcknowledge());
    }

    public void getBlockchain(String nodeid) {
        NodeID id = NodeID.newBuilder().setId(nodeid).build();
        //aqui estamos a invocar o findNode do servidor, passando um id para o canal criado e a receber a resposta
        blockingStub.getBlockchain(id).forEachRemaining(Block -> {
            System.out.println(Block.getAllFields());
        });
    }
    
}
