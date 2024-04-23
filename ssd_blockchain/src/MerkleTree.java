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

        public String getMerkleRoot() {
            return merkleTree.getFirst();
        }

        private void buildTree() {

            if (transactions == null || transactions.isEmpty()) {
                return;
            }

            List<String> currentTreeLevel = new ArrayList<>(transactions);

            // Return hash of the element if there is only 1 transaction
            if (currentTreeLevel.size() == 1) {
                merkleTree.add(applySHA256(currentTreeLevel.getFirst()));
                return;
            }

            // Show all transactions
            System.out.println("Transactions before: " + currentTreeLevel);

            // Duplicate last value if there is an odd number of transactions
            if (currentTreeLevel.size() % 2 != 0) {
                currentTreeLevel.add(currentTreeLevel.getLast());
            }

            // Show all transactions to see if duplicated
            System.out.println("Transactions after: " + currentTreeLevel);

            // Hash all the leafs and add to first level
            List<String> leaf_hashes = new ArrayList<>();
            for (int i = 0; i < currentTreeLevel.size(); i++) {
                leaf_hashes.add(applySHA256(currentTreeLevel.get(i)));
            }
            currentTreeLevel = leaf_hashes;
            merkleTree.addAll(currentTreeLevel);

            System.out.println("Leafs (Hashed Transactions): " + currentTreeLevel);

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