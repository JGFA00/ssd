package com.ssd.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class Transaction {

    public enum TransactionType {
        START_AUCTION,
        BID,
        END_AUCTION
    }

    public TransactionType type;
    public String hash;
    public long timestamp;
    public String data;
    public byte[] signature;
    public String senderPublicKey;
    public String recieverPublicKey;


    public Transaction(TransactionType type, String data, String senderPublicKey, String recieverPublicKey) {
        this.type = type;
        this.data = data;
        this.senderPublicKey = senderPublicKey;
        this.recieverPublicKey = recieverPublicKey;
        validateTransaction();
    }

    // Criar Transação recebida de outro no
    public Transaction(String hash, String senderPK, String receiverPK, byte[] signature, long timestamp, TransactionType type, String data) {
        this.hash = hash;
        this.senderPublicKey = senderPK;
        this.recieverPublicKey = receiverPK;
        this.signature = signature;
        this.type = type;
        this.timestamp = timestamp;
        this.data = data;
    }

    public void validateTransaction() {
        this.timestamp = System.currentTimeMillis();
        this.hash = hashTransaction();
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
    
    public String hashTransaction() {
        return Hashing.applySHA256(this.senderPublicKey + this.recieverPublicKey + this.timestamp + this.type + this.data);
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

    @Override
    public String toString() {
        return "[" + type +
                "] -> '" + data + '\'';
    }
}