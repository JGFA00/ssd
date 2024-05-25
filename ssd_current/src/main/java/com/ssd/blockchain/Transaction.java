package com.ssd.blockchain;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class Transaction {

    public String type;
    public int amount;
    public String item;
    public int userId;
    public int auctionId;
    public String signature;

    //genesis transaction
    public Transaction(){
        this.type = "Genesis";
    }
    //bid transaction
    public Transaction(String type, int userId, int auctionId, int amount) {
        this.type = type;
        this.amount = amount;
        this.userId = userId;
        this.auctionId = auctionId;        
    }

    //start_auction
    public Transaction(String type, int userId, int auctionId, String item){
        this.type = type;
        this.item = item;
        this.userId = userId;
        this.auctionId = auctionId;  
    }

    //end_auction
    public Transaction(String type, int userId, int auctionId){
        this.type = type;
        this.auctionId = auctionId;  
        this.userId = userId;
          
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getAuctionId() {
        return auctionId;
    }
    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
    public String getSignature() {
        return signature;
    }
    public void setSignature(String signature) {
        this.signature = signature;
    }

    
    /*
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

     */
}