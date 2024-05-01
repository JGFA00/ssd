package com.ssd.util;
import java.util.List;
import com.ssd.grpc.Block;
import com.ssd.grpc.Node;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.Transaction;
import com.ssd.grpc.TransactionsList;

public class AuctionUtil {

    public NodeID createNodeId(int nodeid){
        NodeID nid = NodeID.newBuilder().setId(nodeid).build();
        return nid;
    }

    public Node createNode(int id, String ip, int port){
        Node node = Node.newBuilder().setId(id).setIpAddress(ip).setPort(port).build();
        return node;
    }

    public Transaction createTransaction(String transaction, String nome){
        Transaction t = Transaction.newBuilder().setTransaction(transaction).setNome(nome).build();
        return t;
    }

    public TransactionsList createTransactionsList(List<Transaction> tl){
        TransactionsList tlist = TransactionsList.newBuilder().addAllTransactionList(tl).build();
        return tlist;
    }

    public TransactionsList createEmptyTransactionsList(){
        TransactionsList tlist = TransactionsList.newBuilder().build();
        return tlist;
    }

    public Block createBlock(String prevHash, int timestamp, int nonce, String blockHash, String merkleRoot, TransactionsList transactions){
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
