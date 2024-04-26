    import java.security.MessageDigest;
    import java.security.NoSuchAlgorithmException;
    import java.util.ArrayList;
    import java.util.List;

    public class MerkleTree {
        private final List<Transaction> transactions;
        private final List<String> merkleTree;

        public MerkleTree(List<Transaction> transactions) {
            this.transactions = transactions;
            this.merkleTree = new ArrayList<>();
            buildTree();
        }

        public String getMerkleRoot() {
            return merkleTree.getLast();
        }

        private void buildTree() {

            if (transactions == null || transactions.isEmpty()) {
                return;
            }

            List<Transaction> currentTreeLevel = new ArrayList<>(transactions);

            // Return hash of the element if there is only 1 transaction
            if (currentTreeLevel.size() == 1) {
                merkleTree.add(applySHA256(currentTreeLevel.getFirst().toString()));
                return;
            }

            // Show all transactions
            // System.out.println("Transactions before: " + currentTreeLevel);

            // Duplicate last value if there is an odd number of transactions
            if (currentTreeLevel.size() % 2 != 0) {
                currentTreeLevel.add(currentTreeLevel.getLast());
            }

            // Show all transactions to see if duplicated
            System.out.println("Transactions after: " + currentTreeLevel);

            // Hash all the leafs and add to first level
            List<String> leaf_hashes = new ArrayList<>();
            for (Transaction transaction : currentTreeLevel) {
                leaf_hashes.add(applySHA256(transaction.toString()));
            }
            merkleTree.addAll(leaf_hashes);

            //System.out.println("Leafs (Hashed Transactions): " + leaf_hashes);
            //System.out.println("Tree(leafs only): " + merkleTree);

            while (leaf_hashes.size() > 1) {
                List<String> nextTreeLevel = new ArrayList<>();
                for (int i = 0; i < leaf_hashes.size() - 1; i += 2) {
                    String concatenatedHash = leaf_hashes.get(i) + leaf_hashes.get(i + 1);
                    String hash = applySHA256(concatenatedHash);
                    nextTreeLevel.add(hash);
                }
                if (leaf_hashes.size() % 2 == 1) {
                    // If the number of transactions is odd, duplicate the last transaction
                    String concatenatedHash = leaf_hashes.getLast();
                    String hash = applySHA256(concatenatedHash + concatenatedHash);
                    nextTreeLevel.add(hash);
                }
                merkleTree.addAll(leaf_hashes);
                leaf_hashes = nextTreeLevel;
            }
            merkleTree.addAll(leaf_hashes);
        }

        private String applySHA256(String data) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(data.getBytes());
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

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();

            int level = 0;
            int levelSize = 1;
            int currentIndex = merkleTree.size() - 1; // Start from the last element

            while (currentIndex >= 0) {
                stringBuilder.append("Level ").append(level).append(": ");

                for (int i = 0; i < levelSize && currentIndex >= 0; i++) {
                    stringBuilder.append(merkleTree.get(currentIndex)).append(" ");
                    currentIndex--;
                }

                stringBuilder.append("\n");
                level++;
                levelSize *= 2;
            }

            return stringBuilder.toString();
        }
    }