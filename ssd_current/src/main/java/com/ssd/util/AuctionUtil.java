package com.ssd.util;
import java.math.BigInteger;
import java.util.ArrayList;
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

    public static NodeID createNodeId(String nodeid){
        NodeID nid = NodeID.newBuilder().setId(nodeid).build();
        return nid;
    }

    public static NodeInfoGRPC createNodeInfo(String id, String ip, int port){
        NodeInfoGRPC node = NodeInfoGRPC.newBuilder().setId(id).setIp(ip).setPort(port).build();
        return node;
    }

    /*
    public static TransactionApp createTransactionAPP(String transaction, String nome){
        TransactionApp t = TransactionApp.newBuilder().set
        return t;
    }
    */
    public static TransactionsList createTransactionsList(List<TransactionApp> tl){
        TransactionsList tlist = TransactionsList.newBuilder().addAllTransactionList(tl).build();
        return tlist;
    }

    public static TransactionsList createEmptyTransactionsList(){
        TransactionsList tlist = TransactionsList.newBuilder().build();
        return tlist;
    }

    public static BlockGRPC createBlockGRPC(String prevHash, int timestamp, int nonce, String blockHash, String merkleRoot, TransactionsList transactions){
        BlockGRPC block = BlockGRPC.newBuilder().setPrevHash(prevHash)
        .setTimestamp(timestamp)
        .setNonce(nonce)
        .setBlockHash(blockHash)
        .setMerkleRoot(merkleRoot)
        .setTransactionsList(transactions)
        .build();

        return block;
    }
    
    public static Block convertBlockGrpctoBlock(BlockGRPC block){
        Block b = new Block(block.getPrevHash(), block.getTimestamp(), block.getNonce(), block.getBlockHash(), block.getMerkleRoot(), convertTransactionlistGrpctoTransaction(block.getTransactionsList()));
        return b;
    }

    public static BlockGRPC convertBlocktoBlockGRPC(Block block){
        BlockGRPC b = BlockGRPC.newBuilder().setPrevHash(block.getPrevHash())
        .setTimestamp(block.timestamp).setNonce(block.nonce).setBlockHash(block.getBlockHash())
        .setMerkleRoot(block.merkleRoot).setTransactionsList()
        .build();
        return b;
    }

    //o que é esta list?
    public static List<Transaction> convertTransactionlistGrpctoTransaction(TransactionsList tlist){
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
            //atenção que pode dar erro por causa do auctionid
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
        NodeInfoGRPC ngrpc = NodeInfoGRPC.newBuilder().setId(ninfo.getIpAddress()).build();
        return ngrpc;
        
    }

}
