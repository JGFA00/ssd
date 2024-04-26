import java.util.List;

public class Block {
    private String prevHash;
    private long timestamp;
    private int nonce;
    private String merkleRoot;
    private String blockHash;
    private List<Transaction> transactions;
    private int difficulty;

    public Block(String prev_hash, List<Transaction> transactions, int difficulty) {
        this.prevHash = prev_hash;
        this.transactions = transactions;
        this.merkleRoot = calculateMerkleRoot(transactions);
        this.timestamp = System.currentTimeMillis();
        this.difficulty = difficulty;
        mineBlock();
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getTransactions() {
        return transactions.toString();
    }

    @Override
    public String toString() {
        return "Block[" +
                "prev_hash=" + prevHash +
                ", timestamp=" + timestamp +
                ", nonce=" + timestamp +
                ", merkle_root=" + merkleRoot +
                ", hash=" + blockHash +
                ']';
    }

    private String calculateMerkleRoot(List<Transaction> transactions ) {
        MerkleTree merkleTree = new MerkleTree(transactions);
        System.out.println(merkleTree);
        return merkleTree.getMerkleRoot();
    }

    private void mineBlock() {
        // Start with nonce value of 0
        nonce = 0;
        // Iterate until a valid hash is found
        while (!isValidHash(calculateHash())) {
            nonce++;
        }
        // Valid hash found, block mined
        blockHash = calculateHash();
    }

    private String calculateHash() {
        // Include nonce in the hash calculation
        String data = prevHash + merkleRoot + timestamp + nonce;
        return Hashing.applySHA256(data);
    }

    private boolean isValidHash(String hash) {
        // Check if the hash has the required number of leading zeros
        String prefix = "0".repeat(difficulty);
        return hash.startsWith(prefix);
    }

}