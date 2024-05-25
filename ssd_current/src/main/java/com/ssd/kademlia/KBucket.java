package com.ssd.kademlia;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

import com.ssd.client.AuctionClient;
import com.ssd.util.AuctionUtil;
import com.ssd.grpc.NodeInfoGRPC;

import java.util.Iterator;

public class KBucket {
    public final Set<NodeInfoGRPC> nodes;
    public final int capacity;

    public KBucket(int capacity) {
        this.nodes = new LinkedHashSet<>(capacity);
        this.capacity = capacity;
    }

    public synchronized void addNode(NodeInfoGRPC node) {
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
            AuctionClient client = new AuctionClient(convertNodeInfotoNodeInfoGRPC(oldestNode));
            if (!client.ping()) {
                it.remove();
                nodes.add(node);
            }
        }
         
    }

    public synchronized void removeNode(NodeInfoGRPC node) {
        nodes.remove(node);
    }

    public NodeInfoGRPC findNode() {
        for (NodeInfoGRPC node : nodes) {
            if (node.getId().equals(nodeId)) {
                return node;
            }
        }
        return null;
    }

    public Set<NodeInfoGRPC> getNodes() {
        return new LinkedHashSet<>(nodes);
    }

    public boolean isNodePresent(NodeInfoGRPC node) {
        return nodes.contains(node);
    }

    public synchronized void refresh() {
        /*
        //Ping all Nodes and update
        Iterator<NodeInfoGRPC> it = nodes.iterator();
        while (it.hasNext()) {
            NodeInfoGRPC node = it.next();
            if (!ping(node)) {
                it.remove();
            }
        }
         */
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
