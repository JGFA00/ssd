import java.util.List;

public class Block {
    private String prev_hash;
    private long timestamp;
    //private int nonce;
    private String merkleRoot;
    //private String block_hash;
    private List<Transaction> transactions;

    public Block(String prev_hash, List<Transaction> transactions) {
        this.prev_hash = prev_hash;
        this.transactions = transactions;
        this.merkleRoot = calculateMerkleRoot(transactions);
        this.timestamp = System.currentTimeMillis();
        //this.block_hash = calculateHash();
    }

    public String getPrev_hash() {
        return prev_hash;
    }

    public String getTransactions() {
        return transactions.toString();
    }

    @Override
    public String toString() {
        return "Block[" +
                "prev_hash=" + prev_hash +
                ", timestamp=" + timestamp +
                ", merkle_root=" + merkleRoot +
                ']';
    }

    private String calculateMerkleRoot(List<Transaction> transactions ) {
        MerkleTree merkleTree = new MerkleTree(transactions);
        System.out.println(merkleTree);
        return merkleTree.getMerkleRoot();
    }

}