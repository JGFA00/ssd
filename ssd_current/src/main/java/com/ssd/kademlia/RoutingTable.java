
package com.ssd.kademlia;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import com.ssd.kademlia.KBucket;
import com.ssd.client.AuctionClient;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.NodeInfoGRPC;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

public class RoutingTable {
    private final BigInteger nodeId;
    private final List<KBucket> kBuckets;
    private static final int BUCKET_COUNT = 160;  

    public RoutingTable(BigInteger nodeId) {
        this.nodeId = nodeId;
        this.kBuckets = new ArrayList<>(BUCKET_COUNT);
        for (int i = 0; i < BUCKET_COUNT; i++) {
            this.kBuckets.add(new KBucket(20)); 
        }
    }

    public RoutingTable(String nodeIdHex) {
        this(new BigInteger(nodeIdHex, 16));  
    }
    public void addNode(NodeInfoGRPC node) {
        int bucketIndex = getBucketIndex(new BigInteger(node.getId(), 16));
        kBuckets.get(bucketIndex).addNode(node);
    }

    public void removeNode(NodeInfoGRPC node) {
        int bucketIndex = getBucketIndex(new BigInteger(node.getId(), 16));
        kBuckets.get(bucketIndex).removeNode(node);
    }

    private int getBucketIndex(BigInteger nodeId) {
        BigInteger distance = this.nodeId.xor(nodeId);
        return (distance.bitLength() == 0 ? 0 : distance.bitLength() - 1);
    }

    private BigInteger xorDistance(BigInteger id1, BigInteger id2) {
        return id1.xor(id2);
    }

    public List<NodeInfoGRPC> findClosestNodes(BigInteger targetId, int count) {
        List<NodeInfoGRPC> allNodes = new ArrayList<>();
        
        allNodes = getAllRoutes(); 

        // Sort nodes by XOR distance from the target ID
        Collections.sort(allNodes, (node1, node2) -> {
            BigInteger id1 = new BigInteger(node1.getId(), 16); 
            BigInteger id2 = new BigInteger(node2.getId(), 16);
            return xorDistance(id1, targetId).compareTo(xorDistance(id2, targetId));
        });

        // Return the closest 'count' nodes, or the total number of nodes if fewer than 'count'
        return allNodes.subList(0, Math.min(count, allNodes.size()));
    }
    
    public List<NodeInfoGRPC> nodeLookup(NodeInfoGRPC nodeInfo) {
        Set<NodeInfoGRPC> queriedNodes = new HashSet<>();
        List<NodeInfoGRPC> resultNodes = new ArrayList<>();
        List<NodeInfoGRPC> toQuery = new ArrayList<>();
        toQuery.add(nodeInfo);

        int iterations = 0;
        while (!toQuery.isEmpty() && iterations < 3) {  // Limit to 3 iterations
            List<NodeInfoGRPC> newNodesToQuery = new ArrayList<>();
            for (NodeInfoGRPC currentNode : toQuery) {
                if (!queriedNodes.add(currentNode)) {
                    continue;  // Skip already queried nodes
                }

                AuctionClient client = new AuctionClient(currentNode);
                List<NodeInfoGRPC> foundNodes = client.findNode();

                // Combine found nodes with nodes from the current routing table
                Set<NodeInfoGRPC> allNodesSet = new HashSet<>(foundNodes);
                allNodesSet.addAll(getAllRoutes());

                List<NodeInfoGRPC> allNodes = new ArrayList<>(allNodesSet);

                // Sort nodes by XOR distance from the NodeInfoGRPC's ID
                Collections.sort(allNodes, (node1, node2) -> {
                    BigInteger id1 = new BigInteger(node1.getId(), 16);
                    BigInteger id2 = new BigInteger(node2.getId(), 16);
                    BigInteger targetId = new BigInteger(nodeInfo.getId(), 16);
                    return xorDistance(id1, targetId).compareTo(xorDistance(id2, targetId));
                });

                // Add the closest nodes to the result and queue for further lookup
                int nodesToTake = Math.min(3, allNodes.size());
                List<NodeInfoGRPC> closestNodes = allNodes.subList(0, nodesToTake);
                resultNodes.addAll(closestNodes);
                newNodesToQuery.addAll(closestNodes);
            }

            toQuery = newNodesToQuery;
            iterations++;
        }

        // Ensure unique results
        List<NodeInfoGRPC> uniqueResultNodes = new ArrayList<>(new HashSet<>(resultNodes));

        // Sort final result by XOR distance
        Collections.sort(uniqueResultNodes, (node1, node2) -> {
            BigInteger id1 = new BigInteger(node1.getId(), 16);
            BigInteger id2 = new BigInteger(node2.getId(), 16);
            BigInteger targetId = new BigInteger(nodeInfo.getId(), 16);
            return xorDistance(id1, targetId).compareTo(xorDistance(id2, targetId));
        });

        return uniqueResultNodes;
    }

    public List<NodeInfoGRPC> getAllRoutes() {
        List<NodeInfoGRPC> Nodes = new ArrayList<>();
        for (KBucket kBucket : kBuckets) {
            Nodes.addAll(kBucket.getNodes());  // Add all nodes from each KBucket
        }
        return Nodes;
    }

    public boolean containsNode(NodeInfoGRPC Node){
        List<NodeInfoGRPC> Nodes = new ArrayList<>();
        Nodes=getAllRoutes();
        if(Nodes.contains(Node)){
            return true;
        }
        return false;    
    }
     
}
