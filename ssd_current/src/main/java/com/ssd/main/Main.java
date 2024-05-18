package com.ssd.main;
import com.ssd.app.Transactions;
import com.ssd.blockchain.Blockchain;
import com.ssd.client.AuctionClient;
import com.ssd.grpc.NodeInfo;
import com.ssd.grpc.NodeID;
import com.ssd.kademlia.RoutingTable;
import com.ssd.server.AuctionServer;
import com.ssd.util.AuctionUtil;

public class Main {
    public static void main(String[] args) {
        //aqui pode estar um generatenodeid() que retorna um id de 160 bytes
        NodeInfo nodeinfo = AuctionUtil.createNodeInfo("1", "172", 8000);
        NodeID nodeid = AuctionUtil.createNodeId("1");
        Blockchain blockchain = new Blockchain();
        RoutingTable routingTable = new RoutingTable(nodeid.getId());
        Transactions tlist = new Transactions();
        AuctionServer server = new AuctionServer(nodeinfo, blockchain, tlist, routingTable);
        server.start();
        NodeInfo bootstrap = AuctionUtil.createNodeInfo("2", "173", 9000);
        AuctionClient bootstrapClient = new AuctionClient(bootstrap);
        bootstrapClient.findNode(nodeid.getId());

    }
}
