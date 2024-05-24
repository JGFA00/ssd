package com.ssd.util;
import java.util.ArrayList;
import java.util.List;
import com.ssd.grpc.BlockGRPC;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.NodeInfoGRPC;
import com.ssd.grpc.TransactionApp;
import com.ssd.grpc.TransactionsList;
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

    public static TransactionApp createTransactionAPP(String transaction, String nome){
        TransactionApp t = TransactionApp.newBuilder().setTransaction(transaction).setNome(nome).build();
        return t;
    }

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
        Block b = new Block(block.getPrevHash(), block.getTimestamp(), block.getNonce(), block.getBlockHash(), block.getMerkleRoot(), convertBlockGrpctoBlock(block));
        return b;
    }

    public static BlockGRPC convertBlocktoBlockGRPC(Block block){
        BlockGRPC b = BlockGRPC.newBuilder().build();
        return b;
    }

    //o que é esta list?
    public static List<Transaction> convertTransactionGrpctoTransaction(TransactionsList tlist){
        List<Transaction> list = new ArrayList<>(); 
        for(TransactionApp t: tlist.getTransactionListList()){
            list.add(convertTransactionApptoTransaction(t));
        }

        return list;
    }

    public static Transaction convertTransactionApptoTransaction(TransactionApp transaction){
        Transaction t = new Transaction(null, null);
        return t;
    }
    
    public static NodeInfo

}
