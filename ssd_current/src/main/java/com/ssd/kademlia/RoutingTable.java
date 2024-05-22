
package com.ssd.kademlia;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import com.ssd.kademlia.KBucket;
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
        
        List<NodeInfo> closestNodes = new ArrayList<>();
        int startBucketIndex = getBucketIndex(targetId);

        // Collect nodes starting from the closest bucket to the targetId
        for (int i = startBucketIndex; closestNodes.size() < count && i < kBuckets.size(); i++) {
            closestNodes.addAll(kBuckets.get(i).getNodes());
        }
        for (int i = startBucketNextIndex - 1; closestNodes.size() < count && i >= 0; i--) {
            closestNodes.addAll(kBuckets.get(i).getNodes());
        }

        // Sort the collected nodes by their XOR distance from the targetId
        Collections.sort(closestNodes, (node1, node2) -> xorDistance(new BigInteger(node1.getId(), 16), targetId).compareTo(xorDistance(new BigInteger(node2.getId(), 16), targetId)));

        // Return only the required number of closest nodes
        return closestNodes.subList(0, Math.min(count, closestNodes.size()));
    }
    
    public void nodeLookup(NodeInfo nodeInfo){
        AuctionClient client = new AuctionClient(nodeInfo);
        Queue q = client.findNode();
        
    }
    
    /*
    public List<kBuckets> getAllRoutes(){
        
        this.kBuckets.get();
        
    } 
     */
}
