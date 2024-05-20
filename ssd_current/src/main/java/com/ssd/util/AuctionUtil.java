package com.ssd.util;
import java.util.List;
import com.ssd.grpc.Block;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.NodeInfo;
import com.ssd.grpc.TransactionKad;
import com.ssd.grpc.TransactionsList;

public class AuctionUtil {

    public static NodeID createNodeId(String nodeid){
        NodeID nid = NodeID.newBuilder().setId(nodeid).build();
        return nid;
    }

    public static NodeInfo createNodeInfo(String id, String ip, int port){
        NodeInfo node = NodeInfo.newBuilder().setId(id).setIp(ip).setPort(port).build();
        return node;
    }

    public static TransactionKad createTransaction(String transaction, String nome){
        TransactionKad t = TransactionKad.newBuilder().setTransaction(transaction).setNome(nome).build();
        return t;
    }

    public static TransactionsList createTransactionsList(List<TransactionKad> tl){
        TransactionsList tlist = TransactionsList.newBuilder().addAllTransactionList(tl).build();
        return tlist;
    }

    public static TransactionsList createEmptyTransactionsList(){
        TransactionsList tlist = TransactionsList.newBuilder().build();
        return tlist;
    }

    public static Block createBlock(String prevHash, int timestamp, int nonce, String blockHash, String merkleRoot, TransactionsList transactions){
        Block block = Block.newBuilder().setPrevHash(prevHash)
        .setTimestamp(timestamp)
        .setNonce(nonce)
        .setBlockHash(blockHash)
        .setMerkleRoot(merkleRoot)
        .setTransactionsList(transactions)
        .build();

        return block;
    }

}
