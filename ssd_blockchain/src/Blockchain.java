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
