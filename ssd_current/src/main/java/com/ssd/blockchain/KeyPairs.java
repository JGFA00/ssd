package com.ssd.blockchain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;


public class KeyPairs {
    private String publicKey;
    private String privateKey;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public KeyPairs() {
        generateKeyPairs();
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    private void generateKeyPairs() {
        try {
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA", "BC");
            SecureRandom random = new FixedRand();
            keyGenerator.initialize(1024,random);
            KeyPair pair = keyGenerator.generateKeyPair();
            PublicKey pub = pair.getPublic();
            PrivateKey priv = pair.getPrivate();
            this.publicKey = new String(Base64.encode(pub.getEncoded()));
            this.privateKey = new String(Base64.encode(priv.getEncoded()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class FixedRand extends SecureRandom {

        MessageDigest sha;
        byte[] state;

        FixedRand() {
            try {
                this.sha = MessageDigest.getInstance(Config.HASH_ALG);
                this.state = sha.digest();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Couldn't find algorithm!");
            }
        }
    }
}
