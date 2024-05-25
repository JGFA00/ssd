package com.ssd.kademlia;

import java.math.BigInteger;

public class NodeInfo {
    private final BigInteger id;
    private final String ipAddress;
    private final int port;
    
     public NodeInfo(BigInteger id, String ipAddress, int port) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.port = port;
     }
     public BigInteger getId() {
        return id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "NodeInfo{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                '}';
    }
}
