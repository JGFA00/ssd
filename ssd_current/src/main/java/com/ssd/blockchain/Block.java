package com.ssd.blockchain;
import java.util.List;
import com.ssd.kademlia.NodeInfo;

public class Block {
    public String blockHash;
    public String prevHash;
    public long timestamp;
    public int nonce=0;
    public String merkleRoot;
    public List<Transaction> transactions;
    public NodeInfo nodeinfo;
    
    public Block(String prev_hash, List<Transaction> transactions, NodeInfo nodeinfo) {
        this.prevHash = prev_hash;
        this.transactions = transactions;
        this.merkleRoot = calculateMerkleRoot(transactions);
        this.blockHash = "";
        this.nodeinfo = nodeinfo;
    }

    //constructor for block received after being converted, needs fixing
    public Block(String prev_hash, long timestamp, int nonce, String blockHash, String merkleRoot, List<Transaction> transactions, NodeInfo nodeinfo){
        this.transactions = transactions;
        this.prevHash= prev_hash;
        this.timestamp = timestamp;
        this.blockHash = blockHash;
        this.nonce = nonce;
        this.nodeinfo = nodeinfo;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getBlockHash(){
        return blockHash;
    }

    public NodeInfo getNodeInfo() {
        return this.nodeinfo;
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

    public String calculateHash() {
        // Include nonce in the hash calculation
        String nodeInfoString = nodeinfo.getId().toString() + nodeinfo.getIpAddress() + nodeinfo.getPort();
        String data = prevHash + merkleRoot + timestamp + nonce + nodeInfoString;
        return Hashing.applySHA256(data);
    }

    public boolean isValidHash(String hash) {
        
        // Check if the hash has the required number of leading zeros
        char[] prefixArray = new char[Config.DIFFICULTY];
        for (int i = 0; i < Config.DIFFICULTY; i++) {
            prefixArray[i] = '0';
        }
        String prefix = new String(prefixArray);
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
                ", nodeinfo=" + nodeinfo +
                ']';
    }
}