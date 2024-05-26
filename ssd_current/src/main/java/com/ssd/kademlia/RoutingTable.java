package com.ssd.kademlia;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import com.ssd.client.AuctionClient;
import com.ssd.kademlia.NodeInfo;
import com.ssd.util.AuctionUtil;
import com.ssd.grpc.NodeInfoGRPC;

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
        int bucketIndex = getBucketIndex(node.getId());
        kBuckets.get(bucketIndex).addNode(node);
    }

    public void removeNode(NodeInfo node) {
        int bucketIndex = getBucketIndex(node.getId());
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
        List<NodeInfo> allNodes = getAllRoutes();

        // Sort nodes by XOR distance from the target ID
        Collections.sort(allNodes, (node1, node2) -> {
            BigInteger id1 = node1.getId();
            BigInteger id2 = node2.getId();
            return xorDistance(id1, targetId).compareTo(xorDistance(id2, targetId));
        });

        // Return the closest 'count' nodes, or the total number of nodes if fewer than 'count'
        return allNodes.subList(0, Math.min(count, allNodes.size()));
    }

    public void nodeLookup(NodeInfo nodeInfo) {
        Set<NodeInfo> queriedNodes = new HashSet<>();
        List<NodeInfo> toQuery = new ArrayList<>();
        toQuery.add(nodeInfo);

        int iterations = 0;
        while (!toQuery.isEmpty() && iterations < 3) {  // Limit to 3 iterations
            List<NodeInfo> newNodesToQuery = new ArrayList<>();
            for (NodeInfo currentNode : toQuery) {
                if (!queriedNodes.add(currentNode)) {
                    continue;  // Skip already queried nodes
                }
                NodeInfoGRPC convertedNode = AuctionUtil.convertNodeInfotoNodeInfoGRPC(currentNode);
                AuctionClient client = new AuctionClient(convertedNode);
                List<NodeInfoGRPC> foundNodes = client.findNode();
                List<NodeInfo> convertedFoundNodes = AuctionUtil.convertNodeInfoGRPCListToNodeInfoList(foundNodes);

                // Combine found nodes with nodes from the current routing table
                Set<NodeInfo> allNodesSet = new HashSet<>(convertedFoundNodes);
                allNodesSet.addAll(getAllRoutes());

                List<NodeInfo> allNodes = new ArrayList<>(allNodesSet);

                // Sort nodes by XOR distance from the nodeInfo's ID
                Collections.sort(allNodes, (node1, node2) -> {
                    BigInteger id1 = node1.getId();
                    BigInteger id2 = node2.getId();
                    BigInteger targetId = nodeInfo.getId();
                    return xorDistance(id1, targetId).compareTo(xorDistance(id2, targetId));
                });

                // Add the closest nodes to the routing table and queue for further lookup
                int nodesToTake = Math.min(3, allNodes.size());
                List<NodeInfo> closestNodes = allNodes.subList(0, nodesToTake);
                for (NodeInfo node : closestNodes) {
                    addNode(node);
                }
                newNodesToQuery.addAll(closestNodes);
            }

            toQuery = newNodesToQuery;
            iterations++;
        }
    }

    public List<NodeInfo> getAllRoutes() {
        List<NodeInfo> nodes = new ArrayList<>();
        for (KBucket kBucket : kBuckets) {
            nodes.addAll(kBucket.getNodes());  // Add all nodes from each KBucket
        }
        return nodes;
    }

    public boolean containsNode(NodeInfo node) {
        return getAllRoutes().contains(node);
    }
}
