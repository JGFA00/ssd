package com.ssd.blockchain;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    public ArrayList<Block> blockchain;

    // criar vazio
    public Blockchain() {
        blockchain = new ArrayList<>();
    }

    public Blockchain(KeyPairs keypairs) {
        this.blockchain= new ArrayList<>();
        Block genesisBlock = createGenesisBlock(keypairs);
        blockchain.add(genesisBlock);
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }

    public String getPrevHash(){
        String prevhash = (blockchain.get(blockchain.size() - 1)).prevHash;
        return prevhash;

    }

    private Block createGenesisBlock(KeyPairs keypairs) {
        // For simplicity, the genesis block has no transactions and a previous hash of 0
        Transaction genesisTransaction = new Transaction();
        List<Transaction> genesis_transactions = new ArrayList<>();
        genesis_transactions.add(genesisTransaction);
        Block genesisBlock = new Block("0", genesis_transactions);
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Block block : blockchain) {
            stringBuilder.append(block).append("\n");
        }
        return stringBuilder.toString();
    }


}

