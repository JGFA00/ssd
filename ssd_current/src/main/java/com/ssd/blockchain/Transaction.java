package com.ssd.blockchain;
public class Transaction {

    public enum TransactionType {
        START_AUCTION,
        BID,
        END_AUCTION
    }

    private TransactionType type;
    private String data;

    public Transaction(TransactionType type, String data) {
        this.type = type;
        this.data = data;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "[" + type +
                "] -> '" + data + '\'';
    }
}