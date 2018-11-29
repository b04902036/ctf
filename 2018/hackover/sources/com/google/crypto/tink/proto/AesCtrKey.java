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

public final class AesCtrKey extends GeneratedMessageLite<AesCtrKey, Builder> implements AesCtrKeyOrBuilder {
    private static final AesCtrKey DEFAULT_INSTANCE = new AesCtrKey();
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser<AesCtrKey> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_ = ByteString.EMPTY;
    private AesCtrParams params_;
    private int version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesCtrKey, Builder> implements AesCtrKeyOrBuilder {
        private Builder() {
            super(AesCtrKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((AesCtrKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((AesCtrKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((AesCtrKey) this.instance).clearVersion();
            return this;
        }

        public boolean hasParams() {
            return ((AesCtrKey) this.instance).hasParams();
        }

        public AesCtrParams getParams() {
            return ((AesCtrKey) this.instance).getParams();
        }

        public Builder setParams(AesCtrParams aesCtrParams) {
            copyOnWrite();
            ((AesCtrKey) this.instance).setParams(aesCtrParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesCtrParams.Builder builder) {
            copyOnWrite();
            ((AesCtrKey) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(AesCtrParams aesCtrParams) {
            copyOnWrite();
            ((AesCtrKey) this.instance).mergeParams(aesCtrParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((AesCtrKey) this.instance).clearParams();
            return this;
        }

        public ByteString getKeyValue() {
            return ((AesCtrKey) this.instance).getKeyValue();
        }

        public Builder setKeyValue(ByteString byteString) {
            copyOnWrite();
            ((AesCtrKey) this.instance).setKeyValue(byteString);
            return this;
        }

        public Builder clearKeyValue() {
            copyOnWrite();
            ((AesCtrKey) this.instance).clearKeyValue();
            return this;
        }
    }

    private AesCtrKey() {
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

    public static AesCtrKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesCtrKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesCtrKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesCtrKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesCtrKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesCtrKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesCtrKey parseFrom(InputStream inputStream) throws IOException {
        return (AesCtrKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesCtrKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesCtrKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesCtrKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesCtrKey aesCtrKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesCtrKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesCtrKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesCtrKey aesCtrKey = (AesCtrKey) obj2;
                this.version_ = visitor.visitInt(this.version_ != 0, this.version_, aesCtrKey.version_ != 0, aesCtrKey.version_);
                this.params_ = (AesCtrParams) visitor.visitMessage(this.params_, aesCtrKey.params_);
                boolean z2 = this.keyValue_ != ByteString.EMPTY;
                ByteString byteString = this.keyValue_;
                if (aesCtrKey.keyValue_ != ByteString.EMPTY) {
                    z = true;
                }
                this.keyValue_ = visitor.visitByteString(z2, byteString, z, aesCtrKey.keyValue_);
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
                                com.google.crypto.tink.proto.AesCtrParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.AesCtrParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (AesCtrParams) codedInputStream.readMessage(AesCtrParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (AesCtrParams) builder.buildPartial();
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
                    synchronized (AesCtrKey.class) {
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

    public static AesCtrKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesCtrKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
