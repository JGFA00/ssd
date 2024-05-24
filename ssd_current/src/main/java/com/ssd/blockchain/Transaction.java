package com.ssd.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class Transaction {

    public enum TransactionType {
        BID, START_AUCTION, END_AUCTION
    }

    public TransactionType type;
    public int amount;
    public String item;
    public String userId;
    public String auctionId;
    public String hash;
    public byte[] signature;


    // Cria Transação tchillad
    public Transaction(TransactionType type, int amount, String item, String userId, String auctionId) {
        this.type = type;
        this.amount = amount;
        this.item = item;
        this.userId = userId;
        this.auctionId = auctionId;
        this.hash = hashTransaction();
    }

    // Criar Transação recebida de outro no
    public Transaction(String hash, byte[] signature, TransactionType type, int amount, String item, String userId, String auctionId) {
        this.signature = signature;
        this.type = type;
        this.hash = hash;
        this.amount = amount;
        this.item = item;
        this.userId = userId;
        this.auctionId = auctionId;
    }

    public String buildHashString() {
        StringBuilder builder = new StringBuilder();
        builder.append(type);
        if (amount != 0) {
            builder.append(amount);
        }
        if (item != null) {
            builder.append(item);
        }
        if (userId != null) {
            builder.append(userId);
        }
        if (auctionId != null) {
            builder.append(auctionId);
        }
        return builder.toString();
    }
    
    private String hashTransaction() {
        return Hashing.applySHA256(buildHashString());
    }

    public boolean isSigned() {
        return this.signature != null;
    }

    public boolean signTransaction(KeyPairs keypairs) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, InvalidKeySpecException, UnsupportedEncodingException {
        // Check if Transaction was modified
        if (!this.hash.equals(this.hashTransaction())) {
            return false;
        }

        this.signature = keypairs.sign(hash)[1];
        return true;
    }


    // Tenho de por um toString todo grifado para cada transacao
    @Override
    public String toString() {
        return "[" + type +
                "] -> '" + userId + '\'';
    }
}