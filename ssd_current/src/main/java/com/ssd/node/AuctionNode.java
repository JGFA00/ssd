package com.ssd.node;

import javax.crypto.spec.RC2ParameterSpec;

import com.ssd.kademlia.NodeInfo;
import com.ssd.kademlia.RoutingTable;

public class AuctionNode {
    public static NodeInfo nodeInfo;
    public static RoutingTable routingTable;

    public AuctionNode(NodeInfo noodeInfo){
        this.nodeInfo= nodeInfo;
        this.routingTable = new RoutingTable(nodeInfo.getId());
    }
    

}
