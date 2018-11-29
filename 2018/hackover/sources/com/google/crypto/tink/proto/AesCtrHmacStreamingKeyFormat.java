package com.google.crypto.tink.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class AesCtrHmacStreamingKeyFormat extends GeneratedMessageLite<AesCtrHmacStreamingKeyFormat, Builder> implements AesCtrHmacStreamingKeyFormatOrBuilder {
    private static final AesCtrHmacStreamingKeyFormat DEFAULT_INSTANCE = new AesCtrHmacStreamingKeyFormat();
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser<AesCtrHmacStreamingKeyFormat> PARSER;
    private int keySize_;
    private AesCtrHmacStreamingParams params_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesCtrHmacStreamingKeyFormat, Builder> implements AesCtrHmacStreamingKeyFormatOrBuilder {
        private Builder() {
            super(AesCtrHmacStreamingKeyFormat.DEFAULT_INSTANCE);
        }

        public boolean hasParams() {
            return ((AesCtrHmacStreamingKeyFormat) this.instance).hasParams();
        }

        public AesCtrHmacStreamingParams getParams() {
            return ((AesCtrHmacStreamingKeyFormat) this.instance).getParams();
        }

        public Builder setParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams) {
            copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat) this.instance).setParams(aesCtrHmacStreamingParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesCtrHmacStreamingParams.Builder builder) {
            copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams) {
            copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat) this.instance).mergeParams(aesCtrHmacStreamingParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat) this.instance).clearParams();
            return this;
        }

        public int getKeySize() {
            return ((AesCtrHmacStreamingKeyFormat) this.instance).getKeySize();
        }

        public Builder setKeySize(int i) {
            copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat) this.instance).setKeySize(i);
            return this;
        }

        public Builder clearKeySize() {
            copyOnWrite();
            ((AesCtrHmacStreamingKeyFormat) this.instance).clearKeySize();
            return this;
        }
    }

    private AesCtrHmacStreamingKeyFormat() {
    }

    public boolean hasParams() {
        return this.params_ != null;
    }

    public AesCtrHmacStreamingParams getParams() {
        return this.params_ == null ? AesCtrHmacStreamingParams.getDefaultInstance() : this.params_;
    }

    private void setParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams) {
        if (aesCtrHmacStreamingParams != null) {
            this.params_ = aesCtrHmacStreamingParams;
            return;
        }
        throw new NullPointerException();
    }

    private void setParams(com.google.crypto.tink.proto.AesCtrHmacStreamingParams.Builder builder) {
        this.params_ = (AesCtrHmacStreamingParams) builder.build();
    }

    private void mergeParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams) {
        if (this.params_ == null || this.params_ == AesCtrHmacStreamingParams.getDefaultInstance()) {
            this.params_ = aesCtrHmacStreamingParams;
        } else {
            this.params_ = (AesCtrHmacStreamingParams) ((com.google.crypto.tink.proto.AesCtrHmacStreamingParams.Builder) AesCtrHmacStreamingParams.newBuilder(this.params_).mergeFrom(aesCtrHmacStreamingParams)).buildPartial();
        }
    }

    private void clearParams() {
        this.params_ = null;
    }

    public int getKeySize() {
        return this.keySize_;
    }

    private void setKeySize(int i) {
        this.keySize_ = i;
    }

    private void clearKeySize() {
        this.keySize_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.params_ != null) {
            codedOutputStream.writeMessage(1, getParams());
        }
        if (this.keySize_ != 0) {
            codedOutputStream.writeUInt32(2, this.keySize_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.params_ != null) {
            i = 0 + CodedOutputStream.computeMessageSize(1, getParams());
        }
        if (this.keySize_ != 0) {
            i += CodedOutputStream.computeUInt32Size(2, this.keySize_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (AesCtrHmacStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesCtrHmacStreamingKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrHmacStreamingKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacStreamingKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesCtrHmacStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesCtrHmacStreamingKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesCtrHmacStreamingKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesCtrHmacStreamingKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat = (AesCtrHmacStreamingKeyFormat) obj2;
                this.params_ = (AesCtrHmacStreamingParams) visitor.visitMessage(this.params_, aesCtrHmacStreamingKeyFormat.params_);
                boolean z2 = this.keySize_ != 0;
                int i = this.keySize_;
                if (aesCtrHmacStreamingKeyFormat.keySize_ != 0) {
                    z = true;
                }
                this.keySize_ = visitor.visitInt(z2, i, z, aesCtrHmacStreamingKeyFormat.keySize_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                com.google.crypto.tink.proto.AesCtrHmacStreamingParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.AesCtrHmacStreamingParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (AesCtrHmacStreamingParams) codedInputStream.readMessage(AesCtrHmacStreamingParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (AesCtrHmacStreamingParams) builder.buildPartial();
                                }
                            } else if (readTag == 16) {
                                this.keySize_ = codedInputStream.readUInt32();
                            } else if (codedInputStream.skipField(readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (AesCtrHmacStreamingKeyFormat.class) {
                        if (PARSER == null) {
                            PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static AesCtrHmacStreamingKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesCtrHmacStreamingKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
