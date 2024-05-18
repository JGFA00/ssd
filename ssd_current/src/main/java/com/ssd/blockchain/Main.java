package com.ssd.blockchain;
import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {

        Blockchain chain = new Blockchain();

        System.out.println("blockchain inicial: " + chain);

        Block genesisBlock = chain.getBlockchain().get(0); // Get the genesis block
        String genesis_hash = genesisBlock.getBlockHash();

        Block block1 = getBlockData(genesis_hash);

        System.out.println("Block1: " + block1);
        System.out.println("Block1 Transactions: " + block1.getTransactions().toString());
        System.out.println(block1.verifyBlock());

        chain.addBlock(block1);

        System.out.println("blockchain final: " + chain);

    }

    private static Block getBlockData(String previous_hash) {
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

        return new Block(previous_hash,transactions);
    }
}