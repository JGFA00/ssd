package com.ssd.client;
import com.ssd.blockchain.Blockchain;
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
import com.ssd.kademlia.NodeInfo;

import java.util.ArrayList;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class AuctionClient {
    NodeInfoGRPC targetnodeinfo;
    final AuctionBlockingStub blockingStub;
    final AuctionStub asyncStub;
    final ManagedChannel channel;

    public AuctionClient(NodeInfoGRPC targetnodeInfo) {
        channel = ManagedChannelBuilder.forAddress(targetnodeInfo.getIp(), targetnodeInfo.getPort()).usePlaintext().build();
        blockingStub = AuctionGrpc.newBlockingStub(channel);
        asyncStub = AuctionGrpc.newStub(channel);
        this.targetnodeinfo = targetnodeInfo;
    } 

    //public AuctionClient(NodeInfo nodeInfo, RoutingTable routingTable)
    //este node id é o id do nó que queremos contactar
    public Boolean ping(NodeInfoGRPC nodeInfo) {
        System.out.println(nodeInfo.toString());
        PingResponse response;
        response = blockingStub.ping(nodeInfo);
        System.out.println(response.getResponse());
        return true;
    }

    //este node id é o id do nó que queremos encontrar
    public List<NodeInfoGRPC> findNode(NodeInfoGRPC nodeInfo) {
        List<NodeInfoGRPC> Nodes = new ArrayList<>();
        //aqui estamos a invocar o findNode do servidor, passando um id para o canal criado e a receber a resposta
        blockingStub.findNode(targetnodeinfo).forEachRemaining(Node -> {
            Nodes.add(Node);
            System.out.println(Node.getId());
        });
        return Nodes;
    }

    public void propagateBlock(BlockGRPC block){
        Ack ack;
        ack = blockingStub.propagateBlock(block);
        System.out.println(ack.getAcknowledge() + "\n");
    }

    public Blockchain getBlockchain(NodeInfoGRPC nodeinfo) {
        Blockchain bchain = new Blockchain();
        //aqui estamos a invocar o findNode do servidor, passando um id para o canal criado e a receber a resposta
        blockingStub.getBlockchain(targetnodeinfo).forEachRemaining(Block -> {
            bchain.addBlock(AuctionUtil.convertBlockGrpctoBlock(Block));
        });
        return bchain;
    }
    
}
