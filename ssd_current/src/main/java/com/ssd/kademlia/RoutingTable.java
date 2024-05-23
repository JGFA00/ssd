
package com.ssd.kademlia;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import com.ssd.kademlia.KBucket;
import com.ssd.grpc.NodeID;
import com.ssd.grpc.NodeInfo;
import java.util.ArrayList;
import java.util.Collections;

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
    public void addNode(NodeInfo node) {
        int bucketIndex = getBucketIndex(new BigInteger(node.getId(), 16));
        kBuckets.get(bucketIndex).addNode(node);
    }

    public void removeNode(NodeInfo node) {
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

    public List<NodeInfo> findClosestNodes(BigInteger targetId, int count) {
        List<NodeInfo> allNodes = new ArrayList<>();
        
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
    
    public void nodeLookup(NodeInfo nodeInfo){
        AuctionClient client = new AuctionClient(nodeInfo);
        Queue q = client.findNode();
        
    }

    public List<NodeInfo> getAllRoutes() {
        List<NodeInfo> Nodes = new ArrayList<>();
        for (KBucket kBucket : kBuckets) {
            Nodes.addAll(kBucket.getNodes());  // Add all nodes from each KBucket
        }
        return Nodes;
    }

    public boolean containsNode(NodeInfo Node){
        List<NodeInfo> Nodes = new ArrayList<>();
        Nodes=getAllRoutes();
        if(Nodes.contains(Node)){
            return true;
        }
        return false;    
    }
     
}
