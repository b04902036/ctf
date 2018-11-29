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

public final class AesCtrHmacStreamingKey extends GeneratedMessageLite<AesCtrHmacStreamingKey, Builder> implements AesCtrHmacStreamingKeyOrBuilder {
    private static final AesCtrHmacStreamingKey DEFAULT_INSTANCE = new AesCtrHmacStreamingKey();
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser<AesCtrHmacStreamingKey> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_ = ByteString.EMPTY;
    private AesCtrHmacStreamingParams params_;
    private int version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesCtrHmacStreamingKey, Builder> implements AesCtrHmacStreamingKeyOrBuilder {
        private Builder() {
            super(AesCtrHmacStreamingKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((AesCtrHmacStreamingKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((AesCtrHmacStreamingKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((AesCtrHmacStreamingKey) this.instance).clearVersion();
            return this;
        }

        public boolean hasParams() {
            return ((AesCtrHmacStreamingKey) this.instance).hasParams();
        }

        public AesCtrHmacStreamingParams getParams() {
            return ((AesCtrHmacStreamingKey) this.instance).getParams();
        }

        public Builder setParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams) {
            copyOnWrite();
            ((AesCtrHmacStreamingKey) this.instance).setParams(aesCtrHmacStreamingParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesCtrHmacStreamingParams.Builder builder) {
            copyOnWrite();
            ((AesCtrHmacStreamingKey) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(AesCtrHmacStreamingParams aesCtrHmacStreamingParams) {
            copyOnWrite();
            ((AesCtrHmacStreamingKey) this.instance).mergeParams(aesCtrHmacStreamingParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((AesCtrHmacStreamingKey) this.instance).clearParams();
            return this;
        }

        public ByteString getKeyValue() {
            return ((AesCtrHmacStreamingKey) this.instance).getKeyValue();
        }

        public Builder setKeyValue(ByteString byteString) {
            copyOnWrite();
            ((AesCtrHmacStreamingKey) this.instance).setKeyValue(byteString);
            return this;
        }

        public Builder clearKeyValue() {
            copyOnWrite();
            ((AesCtrHmacStreamingKey) this.instance).clearKeyValue();
            return this;
        }
    }

    private AesCtrHmacStreamingKey() {
    }

    public int getVersion() {
        return this.version_;
    }

    private void setVersion(int i) {
        this.version_ = i;
    }

    private void clearVersion() {
        this.version_ = 0;
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

    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    private void setKeyValue(ByteString byteString) {
        if (byteString != null) {
            this.keyValue_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    private void clearKeyValue() {
        this.keyValue_ = getDefaultInstance().getKeyValue();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.version_ != 0) {
            codedOutputStream.writeUInt32(1, this.version_);
        }
        if (this.params_ != null) {
            codedOutputStream.writeMessage(2, getParams());
        }
        if (!this.keyValue_.isEmpty()) {
            codedOutputStream.writeBytes(3, this.keyValue_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.version_ != 0) {
            i = 0 + CodedOutputStream.computeUInt32Size(1, this.version_);
        }
        if (this.params_ != null) {
            i += CodedOutputStream.computeMessageSize(2, getParams());
        }
        if (!this.keyValue_.isEmpty()) {
            i += CodedOutputStream.computeBytesSize(3, this.keyValue_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static AesCtrHmacStreamingKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesCtrHmacStreamingKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesCtrHmacStreamingKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingKey parseFrom(InputStream inputStream) throws IOException {
        return (AesCtrHmacStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrHmacStreamingKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesCtrHmacStreamingKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrHmacStreamingKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacStreamingKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesCtrHmacStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesCtrHmacStreamingKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesCtrHmacStreamingKey aesCtrHmacStreamingKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesCtrHmacStreamingKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesCtrHmacStreamingKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesCtrHmacStreamingKey aesCtrHmacStreamingKey = (AesCtrHmacStreamingKey) obj2;
                this.version_ = visitor.visitInt(this.version_ != 0, this.version_, aesCtrHmacStreamingKey.version_ != 0, aesCtrHmacStreamingKey.version_);
                this.params_ = (AesCtrHmacStreamingParams) visitor.visitMessage(this.params_, aesCtrHmacStreamingKey.params_);
                boolean z2 = this.keyValue_ != ByteString.EMPTY;
                ByteString byteString = this.keyValue_;
                if (aesCtrHmacStreamingKey.keyValue_ != ByteString.EMPTY) {
                    z = true;
                }
                this.keyValue_ = visitor.visitByteString(z2, byteString, z, aesCtrHmacStreamingKey.keyValue_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.version_ = codedInputStream.readUInt32();
                            } else if (readTag == 18) {
                                com.google.crypto.tink.proto.AesCtrHmacStreamingParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.AesCtrHmacStreamingParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (AesCtrHmacStreamingParams) codedInputStream.readMessage(AesCtrHmacStreamingParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (AesCtrHmacStreamingParams) builder.buildPartial();
                                }
                            } else if (readTag == 26) {
                                this.keyValue_ = codedInputStream.readBytes();
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
                    synchronized (AesCtrHmacStreamingKey.class) {
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

    public static AesCtrHmacStreamingKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesCtrHmacStreamingKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
