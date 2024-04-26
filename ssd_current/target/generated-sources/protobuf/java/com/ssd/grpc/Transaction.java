// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: auction.proto

// Protobuf Java Version: 3.25.1
package com.ssd.grpc;

/**
 * Protobuf type {@code Transaction}
 */
public final class Transaction extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:Transaction)
    TransactionOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Transaction.newBuilder() to construct.
  private Transaction(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Transaction() {
    transaction_ = "";
    nome_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Transaction();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.ssd.grpc.AuctionOuterClass.internal_static_Transaction_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.ssd.grpc.AuctionOuterClass.internal_static_Transaction_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.ssd.grpc.Transaction.class, com.ssd.grpc.Transaction.Builder.class);
  }

  public static final int TRANSACTION_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object transaction_ = "";
  /**
   * <code>string transaction = 1;</code>
   * @return The transaction.
   */
  @java.lang.Override
  public java.lang.String getTransaction() {
    java.lang.Object ref = transaction_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      transaction_ = s;
      return s;
    }
  }
  /**
   * <code>string transaction = 1;</code>
   * @return The bytes for transaction.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getTransactionBytes() {
    java.lang.Object ref = transaction_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      transaction_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NOME_FIELD_NUMBER = 2;
  @SuppressWarnings("serial")
  private volatile java.lang.Object nome_ = "";
  /**
   * <code>string nome = 2;</code>
   * @return The nome.
   */
  @java.lang.Override
  public java.lang.String getNome() {
    java.lang.Object ref = nome_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      nome_ = s;
      return s;
    }
  }
  /**
   * <code>string nome = 2;</code>
   * @return The bytes for nome.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNomeBytes() {
    java.lang.Object ref = nome_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      nome_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(transaction_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, transaction_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(nome_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, nome_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(transaction_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, transaction_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(nome_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, nome_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.ssd.grpc.Transaction)) {
      return super.equals(obj);
    }
    com.ssd.grpc.Transaction other = (com.ssd.grpc.Transaction) obj;

    if (!getTransaction()
        .equals(other.getTransaction())) return false;
    if (!getNome()
        .equals(other.getNome())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + TRANSACTION_FIELD_NUMBER;
    hash = (53 * hash) + getTransaction().hashCode();
    hash = (37 * hash) + NOME_FIELD_NUMBER;
    hash = (53 * hash) + getNome().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.ssd.grpc.Transaction parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.ssd.grpc.Transaction parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.ssd.grpc.Transaction parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.ssd.grpc.Transaction parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.ssd.grpc.Transaction parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.ssd.grpc.Transaction parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.ssd.grpc.Transaction parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.ssd.grpc.Transaction parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.ssd.grpc.Transaction parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.ssd.grpc.Transaction parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.ssd.grpc.Transaction parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.ssd.grpc.Transaction parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.ssd.grpc.Transaction prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code Transaction}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:Transaction)
      com.ssd.grpc.TransactionOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.ssd.grpc.AuctionOuterClass.internal_static_Transaction_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.ssd.grpc.AuctionOuterClass.internal_static_Transaction_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.ssd.grpc.Transaction.class, com.ssd.grpc.Transaction.Builder.class);
    }

    // Construct using com.ssd.grpc.Transaction.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      transaction_ = "";
      nome_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.ssd.grpc.AuctionOuterClass.internal_static_Transaction_descriptor;
    }

    @java.lang.Override
    public com.ssd.grpc.Transaction getDefaultInstanceForType() {
      return com.ssd.grpc.Transaction.getDefaultInstance();
    }

    @java.lang.Override
    public com.ssd.grpc.Transaction build() {
      com.ssd.grpc.Transaction result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.ssd.grpc.Transaction buildPartial() {
      com.ssd.grpc.Transaction result = new com.ssd.grpc.Transaction(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.ssd.grpc.Transaction result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.transaction_ = transaction_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.nome_ = nome_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.ssd.grpc.Transaction) {
        return mergeFrom((com.ssd.grpc.Transaction)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.ssd.grpc.Transaction other) {
      if (other == com.ssd.grpc.Transaction.getDefaultInstance()) return this;
      if (!other.getTransaction().isEmpty()) {
        transaction_ = other.transaction_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (!other.getNome().isEmpty()) {
        nome_ = other.nome_;
        bitField0_ |= 0x00000002;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              transaction_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              nome_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object transaction_ = "";
    /**
     * <code>string transaction = 1;</code>
     * @return The transaction.
     */
    public java.lang.String getTransaction() {
      java.lang.Object ref = transaction_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        transaction_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string transaction = 1;</code>
     * @return The bytes for transaction.
     */
    public com.google.protobuf.ByteString
        getTransactionBytes() {
      java.lang.Object ref = transaction_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        transaction_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string transaction = 1;</code>
     * @param value The transaction to set.
     * @return This builder for chaining.
     */
    public Builder setTransaction(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      transaction_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string transaction = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearTransaction() {
      transaction_ = getDefaultInstance().getTransaction();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string transaction = 1;</code>
     * @param value The bytes for transaction to set.
     * @return This builder for chaining.
     */
    public Builder setTransactionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      transaction_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private java.lang.Object nome_ = "";
    /**
     * <code>string nome = 2;</code>
     * @return The nome.
     */
    public java.lang.String getNome() {
      java.lang.Object ref = nome_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        nome_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string nome = 2;</code>
     * @return The bytes for nome.
     */
    public com.google.protobuf.ByteString
        getNomeBytes() {
      java.lang.Object ref = nome_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        nome_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string nome = 2;</code>
     * @param value The nome to set.
     * @return This builder for chaining.
     */
    public Builder setNome(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      nome_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>string nome = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearNome() {
      nome_ = getDefaultInstance().getNome();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     * <code>string nome = 2;</code>
     * @param value The bytes for nome to set.
     * @return This builder for chaining.
     */
    public Builder setNomeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      nome_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:Transaction)
  }

  // @@protoc_insertion_point(class_scope:Transaction)
  private static final com.ssd.grpc.Transaction DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.ssd.grpc.Transaction();
  }

  public static com.ssd.grpc.Transaction getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Transaction>
      PARSER = new com.google.protobuf.AbstractParser<Transaction>() {
    @java.lang.Override
    public Transaction parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<Transaction> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Transaction> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.ssd.grpc.Transaction getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

