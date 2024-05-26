package com.ssd.kademlia;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

import com.ssd.client.AuctionClient;
import com.ssd.util.AuctionUtil;
import com.ssd.kademlia.NodeInfo;
import com.ssd.util.AuctionUtil;
import java.util.Iterator;
import com.ssd.grpc.NodeInfoGRPC;


public class KBucket {
    public final Set<NodeInfo> nodes;
    public final int capacity;
    public final NodeInfo nodeInfo;

    public KBucket(int capacity,NodeInfo nodeInfo) {
        this.nodes = new LinkedHashSet<>(capacity);
        this.capacity = capacity;
        this.nodeInfo = nodeInfo;
    }

    public synchronized void addNode(NodeInfo node) {
        if (nodes.contains(node)) {
            // Node is already in the KBucket, move it to the end to mark as recently seen
            nodes.remove(node);
            nodes.add(node);
        } else if (nodes.size() < capacity) {
            nodes.add(node);
        } else {
            // KBucket is full, check the oldest node's responsiveness if unresponsive remove it and add the new one
            Iterator<NodeInfo> it = nodes.iterator();
            NodeInfo oldestNode = it.next();
            NodeInfoGRPC convertedNode = AuctionUtil.convertNodeInfotoNodeInfoGRPC(oldestNode);
            AuctionClient client = new AuctionClient(convertedNode);
            NodeInfoGRPC myConvertedNodeInfo = AuctionUtil.convertNodeInfotoNodeInfoGRPC(this.nodeInfo);
            if (!client.ping(myConvertedNodeInfo)) {
                it.remove();
                nodes.add(node);
            }
        }
         
    }

    public synchronized void removeNode(NodeInfo node) {
        nodes.remove(node);
    }

    

    public Set<NodeInfo> getNodes() {
        return new LinkedHashSet<>(nodes);
    }

    public boolean isNodePresent(NodeInfo node) {
        return nodes.contains(node);
    }

    public synchronized void refresh() {
        
        //Ping all Nodes and update
        Iterator<NodeInfo> it = nodes.iterator();
        while (it.hasNext()) {            
            NodeInfo node = it.next();
            NodeInfoGRPC convertedNode = AuctionUtil.convertNodeInfotoNodeInfoGRPC(node);
            AuctionClient client = new AuctionClient(convertedNode);
            NodeInfoGRPC myConvertedNodeInfo = AuctionUtil.convertNodeInfotoNodeInfoGRPC(this.nodeInfo);
            if (!client.ping(myConvertedNodeInfo)) {
                it.remove();
            }
        }
        
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    public int size() {
        return nodes.size();
    }

    public void clear() {
        nodes.clear();
    }

}
