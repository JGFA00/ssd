import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {

        BlockData data_block1 = getBlockData();

        System.out.println("Data Block1: " + data_block1.toString());

        BlockHeader head_block1 = new BlockHeader(0, 0, data_block1);
        System.out.println("Block Header: " + head_block1.toString());



    }

    private static BlockData getBlockData() {
        Transaction transaction1 = new Transaction(Transaction.TransactionType.START_AUCTION, "Pedro comecou auction");
        Transaction transaction2 = new Transaction(Transaction.TransactionType.BID, "Joao deu bid $20");
        Transaction transaction3 = new Transaction(Transaction.TransactionType.BID, "Julio deu bid $30");
        //System.out.println(transaction.toString());

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);

        return new BlockData(transactions);
    }
}