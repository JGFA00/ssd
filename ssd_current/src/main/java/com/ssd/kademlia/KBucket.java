package com.ssd.kademlia;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;
import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;

public class KBucket {
    private final Set<NodeInfo> nodes;
    private final int capacity;

    public KBucket(int capacity) {
        this.nodes = new LinkedHashSet<>(capacity);
        this.capacity = capacity;
    }

    public synchronized void addNode(NodeInfo node) {
        if (!nodes.contains(node)) {
            if (nodes.size() >= capacity) {
                Iterator<KademliaNodeInfo> it = nodes.iterator();
                KademliaNodeInfo oldestNode = it.next();
                if (!isResponsive(oldestNode)) {
                    it.remove();
                }
            }
            nodes.add(node);
        } else {
            // Move the node to the end to mark it as most recently seen
            nodes.remove(node);
            nodes.add(node);
        }
    }

    public boolean isResponsive(NodeInfo node) {
        // Implement actual check (e.g., ping)
        return true; // Placeholder
    }

    public Set<NodeInfo> getNodes() {
        return new LinkedHashSet<>(nodes);
    }
}
