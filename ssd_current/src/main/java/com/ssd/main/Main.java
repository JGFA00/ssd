package com.ssd.main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ssd.blockchain.Block;
import com.ssd.blockchain.Blockchain;
import com.ssd.blockchain.Transaction;
import com.ssd.client.AuctionClient;
import com.ssd.grpc.NodeInfoGRPC;
import com.ssd.grpc.TransactionApp;
import com.ssd.grpc.BlockGRPC;
import com.ssd.grpc.NodeID;
import com.ssd.kademlia.NodeInfo;
import com.ssd.kademlia.RoutingTable;
import com.ssd.server.AuctionServer;
import com.ssd.util.AuctionUtil;

public class Main {

    public void sendBlockAllClients(List<NodeInfo> clients){

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length < 3) {
            System.err.println("Usage: java Main <node_id> <ip_address> <port>");
            System.exit(1);
        }

        String nodeIdString = args[0];
        String ipAddress = args[1];
        int port = Integer.parseInt(args[2]);

        //aqui pode estar um generatenodeid() que retorna um id de 160 bytes
        
        Blockchain blockchain = new Blockchain();
        NodeInfoGRPC nodeinfo =AuctionUtil.createNodeInfo(nodeIdString, ipAddress,port);
        NodeInfo convertedNodeInfo=AuctionUtil.convertNodeInfoGRPCtoNodeInfo(nodeinfo);
        RoutingTable routingTable = new RoutingTable(nodeIdString,convertedNodeInfo);
        LinkedList<Transaction> tlist = new LinkedList<>();
        AuctionServer server = new AuctionServer(nodeinfo, blockchain, tlist, routingTable);
        server.start();
        NodeInfoGRPC test = AuctionUtil.createNodeInfo("1b2d3c4e5f6a7b8c9d0e1f2a3b4c5d6e7f8a9b0c", "localhost",5001);
        AuctionClient client = new AuctionClient(test);
        client.ping(nodeinfo);
        client.getBlockchain(nodeinfo);
        
        
        ArrayList<Transaction> tempMiningList = new ArrayList<>();
        //mining functionality
        Thread t = new Thread(new Runnable() {
            public void run() {
                while(true){
                    if(tlist.size() >= 3){
                        System.out.println("Mining process will start, minimum size of transactions achieved \n\n");
                        tempMiningList.clear();
                        //size achieved, prep for mining
                        int size = blockchain.getBlockchain().size();
                        for(int i=0; i<3; i++ ){
                            tempMiningList.add(tlist.getFirst());
                        }
                        Block tempBlock = new Block(blockchain.getLastHash(), tempMiningList, AuctionUtil.convertNodeInfoGRPCtoNodeInfo(nodeinfo));
                        tempBlock.mineBlock();

                        if (size == blockchain.getBlockchain().size()) {
                            for(int i=0; i<3; i++ ){
                                tlist.removeFirst();
                            }
                            blockchain.addBlock(tempBlock);
                            BlockGRPC bgrpc= AuctionUtil.convertBlocktoBlockGRPC(tempBlock); 
                            List<NodeInfo> clients = routingTable.getAllRoutes();
                            for (NodeInfo c : clients){
                                NodeInfoGRPC cl = AuctionUtil.convertNodeInfotoNodeInfoGRPC(c);
                                AuctionClient target = new AuctionClient(cl);
                                target.propagateBlock(bgrpc);
                            }
                        }
                    }
                    try{
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}

                }
            }
        });

        t.start();
        server.blockUntilShutdown();
        

    } 
}
