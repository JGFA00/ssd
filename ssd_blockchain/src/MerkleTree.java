import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree {
    private List<Transaction> transactions;
    private List<String> merkleTree;

    public MerkleTree(List<Transaction> transactions) {
        this.transactions = transactions;
        this.merkleTree = new ArrayList<>();
        buildTree();
    }

    private void buildTree() {
        List<String> currentTreeLevel = new ArrayList<>();
        for (Transaction transaction : transactions) {
            currentTreeLevel.add(applySHA256(transaction.getData()));
        }
        while (currentTreeLevel.size() > 1) {
            List<String> nextTreeLevel = new ArrayList<>();
            for (int i = 0; i < currentTreeLevel.size() - 1; i += 2) {
                String concatenatedHash = currentTreeLevel.get(i) + currentTreeLevel.get(i + 1);
                String hash = applySHA256(concatenatedHash);
                nextTreeLevel.add(hash);
            }
            if (currentTreeLevel.size() % 2 == 1) {
                // If the number of transactions is odd, duplicate the last transaction
                String concatenatedHash = currentTreeLevel.get(currentTreeLevel.size() - 1);
                String hash = applySHA256(concatenatedHash + concatenatedHash);
                nextTreeLevel.add(hash);
            }
            merkleTree.addAll(currentTreeLevel);
            currentTreeLevel = nextTreeLevel;
        }
        merkleTree.addAll(currentTreeLevel);
    }


    public String getMerkleRoot() {
        return merkleTree.get(0);
    }

    private String applySHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}