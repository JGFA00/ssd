import java.util.List;
import java.util.Date;


public class BlockHeader {
    private int prevHash;
    private long timestamp;
    private int nonce;
    private String merkleRoot;

    public BlockHeader(int prevHash, int nonce, BlockData blockData) {
        this.prevHash = prevHash;
        this.timestamp = System.currentTimeMillis();;
        this.nonce = nonce;
        this.merkleRoot = calculateMerkleRoot(blockData);
    }

    // Getters
    public int getPrevHash() {
        return prevHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNonce() {
        return nonce;
    }


    @Override
    public String toString() {
        return "BlockHeader{" +
                "previousHash=" + prevHash +
                ", timestamp=" + timestamp +
                ", nonce=" + nonce +
                ", merkleRoot='" + merkleRoot + '\'' +
                '}';
    }


    private String calculateMerkleRoot(BlockData blockData) {
        List<Transaction> transactions = blockData.getTransactions();
        MerkleTree merkleTree = new MerkleTree(transactions);
        return merkleTree.getMerkleRoot();
    }
}