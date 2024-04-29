package com.ssd.util;

import com.ssd.grpc.Block;
import com.ssd.grpc.TransactionsList;
public class AuctionUtil {


    public TransactionsList createTransactionsList(){
        TransactionsList tlist = TransactionsList.newBuilder().build();
        return tlist;
    }

    public Block createBlock(String prevHash, int timestamp, int nonce, String blockHash, String merkleRoot, TransactionsList transactions){
        Block block = Block.newBuilder().setPrevHash(prevHash)
        .setTimestamp(0)
        .setNonce(nonce)
        .setBlockHash(blockHash)
        .setMerkleRoot(merkleRoot)
        .setTransactionsList(transactions)
        .build();

        return block;
    }
}
