// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: auction.proto

// Protobuf Java Version: 3.25.1
package com.ssd.grpc;

public interface NodeOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Node)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>string ip_address = 2;</code>
   * @return The ipAddress.
   */
  java.lang.String getIpAddress();
  /**
   * <code>string ip_address = 2;</code>
   * @return The bytes for ipAddress.
   */
  com.google.protobuf.ByteString
      getIpAddressBytes();

  /**
   * <code>int32 port = 3;</code>
   * @return The port.
   */
  int getPort();
}
