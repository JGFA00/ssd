// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: auction.proto

// Protobuf Java Version: 3.25.1
package com.ssd.grpc;

public interface TransactionsListOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TransactionsList)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .Transaction transactionList = 1;</code>
   */
  java.util.List<com.ssd.grpc.Transaction> 
      getTransactionListList();
  /**
   * <code>repeated .Transaction transactionList = 1;</code>
   */
  com.ssd.grpc.Transaction getTransactionList(int index);
  /**
   * <code>repeated .Transaction transactionList = 1;</code>
   */
  int getTransactionListCount();
  /**
   * <code>repeated .Transaction transactionList = 1;</code>
   */
  java.util.List<? extends com.ssd.grpc.TransactionOrBuilder> 
      getTransactionListOrBuilderList();
  /**
   * <code>repeated .Transaction transactionList = 1;</code>
   */
  com.ssd.grpc.TransactionOrBuilder getTransactionListOrBuilder(
      int index);
}
