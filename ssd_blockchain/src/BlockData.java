import java.util.List;

public class BlockData {
    private List<Transaction> transactions;

    public BlockData(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BlockData{");
        sb.append("transactions=[");
        for (Transaction transaction : transactions) {
            sb.append(transaction.toString()).append(", ");
        }
        // Remove the trailing comma and space
        if (!transactions.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]}");
        return sb.toString();
    }

}