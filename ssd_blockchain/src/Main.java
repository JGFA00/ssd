import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {

        Block block1 = getBlockData();

        System.out.println("Block1: " + block1);

    }

    private static Block getBlockData() {
        Transaction transaction1 = new Transaction(Transaction.TransactionType.START_AUCTION, "Pedro comecou auction");
        Transaction transaction2 = new Transaction(Transaction.TransactionType.BID, "Joao deu bid $20");
        Transaction transaction3 = new Transaction(Transaction.TransactionType.BID, "Julio deu bid $30");
        Transaction transaction4 = new Transaction(Transaction.TransactionType.END_AUCTION, "Item vendido a Julio");
        //System.out.println(transaction.toString());

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);

        return new Block("0",transactions);
    }
}