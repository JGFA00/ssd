package com.ssd.blockchain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.math.BigInteger;
import com.ssd.kademlia.NodeInfo;

public class Blockchain {
    public ArrayList<Block> blockchain;

    public Blockchain() {
        this.blockchain= new ArrayList<>();
        Block genesisBlock = createGenesisBlock();
        blockchain.add(genesisBlock);
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }

    public String getLastHash(){
        String lasthash = (blockchain.get(blockchain.size() - 1)).blockHash;
        return lasthash;
    }

    private Block createGenesisBlock() {
        // For simplicity, the genesis block has no transactions and a previous hash of 0
        Transaction genesisTransaction = new Transaction();
        List<Transaction> genesis_transactions = new ArrayList<>();
        genesis_transactions.add(genesisTransaction);
        NodeInfo genesisNodeinfo = new NodeInfo(BigInteger.valueOf(0),"0", 0);
        Block genesisBlock = new Block("0", genesis_transactions, genesisNodeinfo);
        genesisBlock.mineBlock();
        return genesisBlock;
    }

    public void addBlock(Block block) {
        blockchain.add(block);
    }

    public boolean isValid() {
        for (int i=1; i<blockchain.size(); i++) {
            Block curr_block = blockchain.get(i);
            Block prev_block = blockchain.get(i-1);

            // Mismatch between Block's prev_hash value and previous Block's Hash value
            if(!prev_block.getBlockHash().equals(curr_block.getPrevHash())) {
                return false;
            }

            // Block's Hash value is not the same as the computed Block Hash
            if(!curr_block.verifyBlock()) {
                return false;
            }
        }
        return true;
    }

    public  HashMap<Integer, Transaction> getActiveAuctions(){
        
        HashMap<Integer, Transaction> map = new HashMap<>();
        int size = blockchain.size();
        // Loop through all blocks excluding the last two
        for (int i = 0; i < size - 2; i++) {
            Block b = blockchain.get(i);
            for (Transaction t : b.getTransactions()){
                if(t.getType() == "start_auction"){
                    map.put(t.getAuctionId(),t);
                }
                if(t.getType() == "end_auction"){
                    map.remove(t.getAuctionId());
                }
        }
        return map;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Block block : blockchain) {
            stringBuilder.append(block).append("\n");
        }
        return stringBuilder.toString();
    }


}

