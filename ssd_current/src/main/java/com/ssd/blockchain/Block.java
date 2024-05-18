package com.ssd.blockchain;
import java.util.List;

public class Block {
    private String blockHash;
    private String prevHash;
    private long timestamp;
    private int nonce=0;
    private String merkleRoot;
    private List<Transaction> transactions;
    private String publicKey;

    public Block(String prev_hash, List<Transaction> transactions) {
        this.prevHash = prev_hash;
        this.transactions = transactions;
        this.merkleRoot = calculateMerkleRoot(transactions);
        this.blockHash = "";
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getBlockHash(){
        return blockHash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> alt_transactions) {
        this.transactions = alt_transactions;
        this.merkleRoot = calculateMerkleRoot(alt_transactions);
    }

    public int getNumberOfTransactions(){
        return transactions.size();
    }

    private String calculateMerkleRoot(List<Transaction> transactions ) {
        MerkleTree merkleTree = new MerkleTree(transactions);
        return merkleTree.getMerkleRoot();
    }

    public void mineBlock() {
        this.timestamp = System.currentTimeMillis();
        String currHash;
        // Iterate until a valid hash is found
        do {
            nonce++;
            currHash = calculateHash();
        } while(!isValidHash(currHash));
        // Valid hash found, block mined
        this.blockHash = currHash;
    }

    private String calculateHash() {
        // Include nonce in the hash calculation
        String data = prevHash + merkleRoot + timestamp + nonce;
        return Hashing.applySHA256(data);
    }

    private boolean isValidHash(String hash) {
        // Check if the hash has the required number of leading zeros
        String prefix = "0".repeat(Config.DIFFICULTY);
        return hash.startsWith(prefix);
    }

    public boolean verifyBlock(){
        return calculateHash().equals(blockHash);
    }

    @Override
    public String toString() {
        return "Block[" +
                "prev_hash=" + prevHash +
                ", timestamp=" + timestamp +
                ", nonce=" + nonce +
                ", merkle_root=" + merkleRoot +
                ", hash=" + blockHash +
                ']';
    }
}