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

public final class AesCtrKeyFormat extends GeneratedMessageLite<AesCtrKeyFormat, Builder> implements AesCtrKeyFormatOrBuilder {
    private static final AesCtrKeyFormat DEFAULT_INSTANCE = new AesCtrKeyFormat();
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser<AesCtrKeyFormat> PARSER;
    private int keySize_;
    private AesCtrParams params_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesCtrKeyFormat, Builder> implements AesCtrKeyFormatOrBuilder {
        private Builder() {
            super(AesCtrKeyFormat.DEFAULT_INSTANCE);
        }

        public boolean hasParams() {
            return ((AesCtrKeyFormat) this.instance).hasParams();
        }

        public AesCtrParams getParams() {
            return ((AesCtrKeyFormat) this.instance).getParams();
        }

        public Builder setParams(AesCtrParams aesCtrParams) {
            copyOnWrite();
            ((AesCtrKeyFormat) this.instance).setParams(aesCtrParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesCtrParams.Builder builder) {
            copyOnWrite();
            ((AesCtrKeyFormat) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(AesCtrParams aesCtrParams) {
            copyOnWrite();
            ((AesCtrKeyFormat) this.instance).mergeParams(aesCtrParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((AesCtrKeyFormat) this.instance).clearParams();
            return this;
        }

        public int getKeySize() {
            return ((AesCtrKeyFormat) this.instance).getKeySize();
        }

        public Builder setKeySize(int i) {
            copyOnWrite();
            ((AesCtrKeyFormat) this.instance).setKeySize(i);
            return this;
        }

        public Builder clearKeySize() {
            copyOnWrite();
            ((AesCtrKeyFormat) this.instance).clearKeySize();
            return this;
        }
    }

    private AesCtrKeyFormat() {
    }

    public boolean hasParams() {
        return this.params_ != null;
    }

    public AesCtrParams getParams() {
        return this.params_ == null ? AesCtrParams.getDefaultInstance() : this.params_;
    }

    private void setParams(AesCtrParams aesCtrParams) {
        if (aesCtrParams != null) {
            this.params_ = aesCtrParams;
            return;
        }
        throw new NullPointerException();
    }

    private void setParams(com.google.crypto.tink.proto.AesCtrParams.Builder builder) {
        this.params_ = (AesCtrParams) builder.build();
    }

    private void mergeParams(AesCtrParams aesCtrParams) {
        if (this.params_ == null || this.params_ == AesCtrParams.getDefaultInstance()) {
            this.params_ = aesCtrParams;
        } else {
            this.params_ = (AesCtrParams) ((com.google.crypto.tink.proto.AesCtrParams.Builder) AesCtrParams.newBuilder(this.params_).mergeFrom(aesCtrParams)).buildPartial();
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

    public static AesCtrKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesCtrKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesCtrKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesCtrKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesCtrKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesCtrKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesCtrKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (AesCtrKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesCtrKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesCtrKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesCtrKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesCtrKeyFormat aesCtrKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesCtrKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesCtrKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesCtrKeyFormat aesCtrKeyFormat = (AesCtrKeyFormat) obj2;
                this.params_ = (AesCtrParams) visitor.visitMessage(this.params_, aesCtrKeyFormat.params_);
                boolean z2 = this.keySize_ != 0;
                int i = this.keySize_;
                if (aesCtrKeyFormat.keySize_ != 0) {
                    z = true;
                }
                this.keySize_ = visitor.visitInt(z2, i, z, aesCtrKeyFormat.keySize_);
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
                                com.google.crypto.tink.proto.AesCtrParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.AesCtrParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (AesCtrParams) codedInputStream.readMessage(AesCtrParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (AesCtrParams) builder.buildPartial();
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
                    synchronized (AesCtrKeyFormat.class) {
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

    public static AesCtrKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesCtrKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
