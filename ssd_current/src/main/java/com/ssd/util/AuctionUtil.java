package com.ssd.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.ssd.grpc.BlockGRPC;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.NodeInfoGRPC;
import com.ssd.grpc.TransactionApp;
import com.ssd.grpc.TransactionsList;
import com.ssd.kademlia.NodeInfo;
import com.ssd.blockchain.Block;
import com.ssd.blockchain.Transaction;

public class AuctionUtil {
    private static final SecureRandom random = new SecureRandom();
    private static final int PORT_MIN = 5001;
    private static final int PORT_MAX = 65535; // Max port number for TCP/UDP

    public static NodeID createNodeId(String nodeid){
        NodeID nid = NodeID.newBuilder().setId(nodeid).build();
        return nid;
    }

    public static NodeInfoGRPC createNodeInfo(String id, String ip, int port){
        NodeInfoGRPC node = NodeInfoGRPC.newBuilder().setId(id).setIp(ip).setPort(port).build();
        return node;
    }

    public static TransactionsList createTransactionsList(List<TransactionApp> tl){
        TransactionsList tlist = TransactionsList.newBuilder().addAllTransactionList(tl).build();
        return tlist;
    }

    public static TransactionsList createEmptyTransactionsList(){
        TransactionsList tlist = TransactionsList.newBuilder().build();
        return tlist;
    }

    public static BlockGRPC createBlockGRPC(String prevHash, int timestamp, int nonce, String blockHash, String merkleRoot, TransactionsList transactions, NodeInfoGRPC ngrpc){
        BlockGRPC block = BlockGRPC.newBuilder().setPrevHash(prevHash)
        .setTimestamp(timestamp)
        .setNonce(nonce)
        .setBlockHash(blockHash)
        .setMerkleRoot(merkleRoot)
        .setTransactionsList(transactions)
        .setNinfo(ngrpc)
        .build();

        return block;
    }

    public static Block convertBlockGrpctoBlock(BlockGRPC block){
        Block b = new Block(block.getPrevHash(), block.getTimestamp(), block.getNonce(), block.getBlockHash(), block.getMerkleRoot(), 
        convertTransactionlistGrpctoTransactionList(block.getTransactionsList()), convertNodeInfoGRPCtoNodeInfo(block.getNinfo()));
        return b;
    }

    public static BlockGRPC convertBlocktoBlockGRPC(Block block){
        TransactionsList tlist = convertTransactionListtoTransactionsListGRPC(block.getTransactions());
        BlockGRPC b = BlockGRPC.newBuilder().setPrevHash(block.getPrevHash())
        .setTimestamp(block.timestamp).setNonce(block.nonce).setBlockHash(block.getBlockHash())
        .setMerkleRoot(block.merkleRoot).setTransactionsList(tlist).setNinfo(convertNodeInfotoNodeInfoGRPC(block.getNodeInfo()))
        .build();
        return b;
    }

    public static TransactionsList convertTransactionListtoTransactionsListGRPC(List<Transaction> list){
        List<TransactionApp> temp = new LinkedList<>();
        for (Transaction t: list){
            TransactionApp tapp = convertTransactiontoTransactionAPP(t);
            temp.add(tapp);
        }
        TransactionsList tlist = TransactionsList.newBuilder().addAllTransactionList(temp).build();
        return tlist;

    }

    public static List<Transaction> convertTransactionlistGrpctoTransactionList(TransactionsList tlist){
        List<Transaction> list = new ArrayList<>(); 
        for(TransactionApp t: tlist.getTransactionListList()){
            list.add(convertTransactionApptoTransaction(t));
        }

        return list;
    }

    public static Transaction convertTransactionApptoTransaction(TransactionApp transaction){
        Transaction t;
        switch (transaction.getType()) {
            case "bid":
                t = new Transaction(transaction.getType(), transaction.getUserId(), transaction.getAuctionId(), transaction.getAmount());
                return t;
            
            case "start_auction":
                t = new Transaction(transaction.getType(), transaction.getUserId(), transaction.getAuctionId(), transaction.getItem());
                return t;
            case "end_auction":
                t = new Transaction(transaction.getType(), transaction.getUserId(), transaction.getAuctionId());
                return t;
            default:
                Transaction def = new Transaction();
                return def;
        }
    }

    public static TransactionApp convertTransactiontoTransactionAPP(Transaction transaction){
        TransactionApp t;
        switch(transaction.getType()){
            case "bid":
                t= TransactionApp.newBuilder().setType(transaction.getType()).setUserId(transaction.getUserId()).
                setAuctionId(transaction.getAuctionId()).setAmount(transaction.getAmount()).build();
                return t;
            case "start_auction":
                t = TransactionApp.newBuilder().setType(transaction.getType()).setUserId(transaction.getUserId()).
                setAuctionId(transaction.getAuctionId()).setItem(transaction.getItem()).build();
                return t;
            case "end_auction":
                t = TransactionApp.newBuilder().setType(transaction.getType()).setUserId(transaction.getUserId()).
                setAuctionId(transaction.getAuctionId()).build();
            default:
                TransactionApp def = TransactionApp.newBuilder().build();
                return def;
        }
    }

    public static NodeInfo convertNodeInfoGRPCtoNodeInfo(NodeInfoGRPC ngrpc) {
        NodeInfo ninfo = new NodeInfo(new BigInteger(ngrpc.getId(), 16), ngrpc.getIp(), ngrpc.getPort());
        return ninfo;
    }

    public static NodeInfoGRPC convertNodeInfotoNodeInfoGRPC(NodeInfo ninfo) {
        NodeInfoGRPC ngrpc = NodeInfoGRPC.newBuilder().setId(ninfo.getId().toString(16)).setIp(ninfo.getIpAddress()).setPort(ninfo.getPort()).build();
        return ngrpc;
    }

    public static List<NodeInfo> convertNodeInfoGRPCListToNodeInfoList(List<NodeInfoGRPC> nodeInfoGRPCList) {
        List<NodeInfo> nodeInfoList = new ArrayList<>();
        for (NodeInfoGRPC nodeInfoGRPC : nodeInfoGRPCList) {
            NodeInfo nodeInfo = convertNodeInfoGRPCtoNodeInfo(nodeInfoGRPC);
            nodeInfoList.add(nodeInfo);
        }
        return nodeInfoList;
    }

    public static List<NodeInfoGRPC> convertNodeInfoListToNodeInfoGRPCList(List<NodeInfo> nodeInfoList) {
        List<NodeInfoGRPC> nodeInfoGRPCList = new ArrayList<>();
        for (NodeInfo nodeInfo : nodeInfoList) {
            NodeInfoGRPC nodeInfoGRPC = convertNodeInfotoNodeInfoGRPC(nodeInfo);
            nodeInfoGRPCList.add(nodeInfoGRPC);
        }
        return nodeInfoGRPCList;
    }

    public static String generateRandomID() {
        return new BigInteger(160, random).toString(16);
    }

    public static int generateRandomPort() {
        return PORT_MIN + random.nextInt(PORT_MAX - PORT_MIN + 1);
    }
}
