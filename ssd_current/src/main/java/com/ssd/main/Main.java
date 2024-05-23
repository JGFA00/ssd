package com.ssd.main;
import java.io.IOException;
import java.util.LinkedList;
import com.ssd.blockchain.Blockchain;
import com.ssd.client.AuctionClient;
import com.ssd.grpc.NodeInfo;
import com.ssd.grpc.TransactionApp;
import com.ssd.grpc.NodeID;
import com.ssd.kademlia.RoutingTable;
import com.ssd.server.AuctionServer;
import com.ssd.util.AuctionUtil;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //aqui pode estar um generatenodeid() que retorna um id de 160 bytes
        NodeInfo nodeinfo = AuctionUtil.createNodeInfo("1", "172", 5000);
        NodeID nodeid = AuctionUtil.createNodeId("1");
        Blockchain blockchain = new Blockchain();
        RoutingTable routingTable = new RoutingTable(nodeid.getId());
        LinkedList<TransactionApp> tlist = new LinkedList<>();
        AuctionServer server = new AuctionServer(nodeinfo, blockchain, tlist, routingTable);
        server.start();
        server.blockUntilShutdown();
        //NodeInfo bootstrap = AuctionUtil.createNodeInfo("2", "173", 9000);
        //AuctionClient bootstrapClient = new AuctionClient(bootstrap);
        routingTable.nodeLookup(bootstrap);
        //bootstrapClient.nodeLookup(nodeid.getId());
        


    }
}
