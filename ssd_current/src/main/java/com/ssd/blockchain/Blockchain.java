package com.ssd.blockchain;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> blockchain;

    public Blockchain() {
        this.blockchain= new ArrayList<>();
        Block genesisBlock = createGenesisBlock();
        blockchain.add(genesisBlock);
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }

    private Block createGenesisBlock() {
        // For simplicity, the genesis block has no transactions and a previous hash of 0
        Transaction genesisTransaction = new Transaction(Transaction.TransactionType.START_AUCTION,"genesiscenas");
        List<Transaction> genesis_transactions = new ArrayList<>();
        genesis_transactions.add(genesisTransaction);
        return new Block("0", genesis_transactions);
    }

    public void addBlock(Block block) {
        blockchain.add(block);
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
