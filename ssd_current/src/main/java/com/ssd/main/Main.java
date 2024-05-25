package com.ssd.main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.ssd.blockchain.Block;
import com.ssd.blockchain.Blockchain;
import com.ssd.blockchain.Transaction;
import com.ssd.client.AuctionClient;
import com.ssd.grpc.NodeInfoGRPC;
import com.ssd.grpc.TransactionApp;
import com.ssd.grpc.NodeID;
import com.ssd.kademlia.RoutingTable;
import com.ssd.server.AuctionServer;
import com.ssd.util.AuctionUtil;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //aqui pode estar um generatenodeid() que retorna um id de 160 bytes
        NodeInfoGRPC nodeinfo = AuctionUtil.createNodeInfo("1", "172", 5000);
        NodeID nodeid = AuctionUtil.createNodeId("1");
        Blockchain blockchain = new Blockchain();
        RoutingTable routingTable = new RoutingTable(nodeid.getId());
        LinkedList<Transaction> tlist = new LinkedList<>();
        AuctionServer server = new AuctionServer(nodeinfo, blockchain, tlist, routingTable);
        server.start();
        server.blockUntilShutdown();
        //NodeInfo bootstrap = AuctionUtil.createNodeInfo("2", "173", 9000);
        //AuctionClient bootstrapClient = new AuctionClient(bootstrap);
        //routingTable.nodeLookup(bootstrap);
        //bootstrapClient.nodeLookup(nodeid.getId());
        
        /*
        ArrayList<Transaction> tempMiningList = new ArrayList<>();
        //mining functionality
        while(true){
            if(tlist.size() >= 3){
                for(int i=0; i<3; i++ ){
                    tempMiningList.add(tlist.getFirst());
                }
                Block tempBlock = new Block(blockchain.getPrevHash(), tempMiningList);
                tempBlock.mineBlock();
            }
            
        }
         */


    }
}
