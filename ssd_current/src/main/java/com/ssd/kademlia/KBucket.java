package com.ssd.kademlia;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Set;

import com.ssd.grpc.NodeInfo;

import java.util.Iterator;

public class KBucket {
    public final Set<NodeInfo> nodes;
    public final int capacity;

    public KBucket(int capacity) {
        this.nodes = new LinkedHashSet<>(capacity);
        this.capacity = capacity;
    }

    public synchronized void addNode(NodeInfo node) {
        /*
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
            if (!ping(oldestNode)) {
                it.remove();
                nodes.add(node);
            }
        }
         */
    }

    public synchronized void removeNode(NodeInfo node) {
        nodes.remove(node);
    }

    public NodeInfo findNode(BigInteger nodeId) {
        for (NodeInfo node : nodes) {
            if (node.getId().equals(nodeId)) {
                return node;
            }
        }
        return null;
    }

    public Set<NodeInfo> getNodes() {
        return new LinkedHashSet<>(nodes);
    }

    public boolean isNodePresent(NodeInfo node) {
        return nodes.contains(node);
    }

    public synchronized void refresh() {
        /*
        //Ping all Nodes and update
        Iterator<NodeInfo> it = nodes.iterator();
        while (it.hasNext()) {
            NodeInfo node = it.next();
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
