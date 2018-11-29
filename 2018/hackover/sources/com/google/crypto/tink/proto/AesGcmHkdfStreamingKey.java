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

public final class AesGcmHkdfStreamingKey extends GeneratedMessageLite<AesGcmHkdfStreamingKey, Builder> implements AesGcmHkdfStreamingKeyOrBuilder {
    private static final AesGcmHkdfStreamingKey DEFAULT_INSTANCE = new AesGcmHkdfStreamingKey();
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser<AesGcmHkdfStreamingKey> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_ = ByteString.EMPTY;
    private AesGcmHkdfStreamingParams params_;
    private int version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesGcmHkdfStreamingKey, Builder> implements AesGcmHkdfStreamingKeyOrBuilder {
        private Builder() {
            super(AesGcmHkdfStreamingKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((AesGcmHkdfStreamingKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((AesGcmHkdfStreamingKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((AesGcmHkdfStreamingKey) this.instance).clearVersion();
            return this;
        }

        public boolean hasParams() {
            return ((AesGcmHkdfStreamingKey) this.instance).hasParams();
        }

        public AesGcmHkdfStreamingParams getParams() {
            return ((AesGcmHkdfStreamingKey) this.instance).getParams();
        }

        public Builder setParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams) {
            copyOnWrite();
            ((AesGcmHkdfStreamingKey) this.instance).setParams(aesGcmHkdfStreamingParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.Builder builder) {
            copyOnWrite();
            ((AesGcmHkdfStreamingKey) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams) {
            copyOnWrite();
            ((AesGcmHkdfStreamingKey) this.instance).mergeParams(aesGcmHkdfStreamingParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((AesGcmHkdfStreamingKey) this.instance).clearParams();
            return this;
        }

        public ByteString getKeyValue() {
            return ((AesGcmHkdfStreamingKey) this.instance).getKeyValue();
        }

        public Builder setKeyValue(ByteString byteString) {
            copyOnWrite();
            ((AesGcmHkdfStreamingKey) this.instance).setKeyValue(byteString);
            return this;
        }

        public Builder clearKeyValue() {
            copyOnWrite();
            ((AesGcmHkdfStreamingKey) this.instance).clearKeyValue();
            return this;
        }
    }

    private AesGcmHkdfStreamingKey() {
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

    public AesGcmHkdfStreamingParams getParams() {
        return this.params_ == null ? AesGcmHkdfStreamingParams.getDefaultInstance() : this.params_;
    }

    private void setParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams) {
        if (aesGcmHkdfStreamingParams != null) {
            this.params_ = aesGcmHkdfStreamingParams;
            return;
        }
        throw new NullPointerException();
    }

    private void setParams(com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.Builder builder) {
        this.params_ = (AesGcmHkdfStreamingParams) builder.build();
    }

    private void mergeParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams) {
        if (this.params_ == null || this.params_ == AesGcmHkdfStreamingParams.getDefaultInstance()) {
            this.params_ = aesGcmHkdfStreamingParams;
        } else {
            this.params_ = (AesGcmHkdfStreamingParams) ((com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.Builder) AesGcmHkdfStreamingParams.newBuilder(this.params_).mergeFrom(aesGcmHkdfStreamingParams)).buildPartial();
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

    public static AesGcmHkdfStreamingKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesGcmHkdfStreamingKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesGcmHkdfStreamingKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingKey parseFrom(InputStream inputStream) throws IOException {
        return (AesGcmHkdfStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesGcmHkdfStreamingKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmHkdfStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesGcmHkdfStreamingKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesGcmHkdfStreamingKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmHkdfStreamingKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesGcmHkdfStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesGcmHkdfStreamingKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmHkdfStreamingKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesGcmHkdfStreamingKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesGcmHkdfStreamingKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesGcmHkdfStreamingKey aesGcmHkdfStreamingKey = (AesGcmHkdfStreamingKey) obj2;
                this.version_ = visitor.visitInt(this.version_ != 0, this.version_, aesGcmHkdfStreamingKey.version_ != 0, aesGcmHkdfStreamingKey.version_);
                this.params_ = (AesGcmHkdfStreamingParams) visitor.visitMessage(this.params_, aesGcmHkdfStreamingKey.params_);
                boolean z2 = this.keyValue_ != ByteString.EMPTY;
                ByteString byteString = this.keyValue_;
                if (aesGcmHkdfStreamingKey.keyValue_ != ByteString.EMPTY) {
                    z = true;
                }
                this.keyValue_ = visitor.visitByteString(z2, byteString, z, aesGcmHkdfStreamingKey.keyValue_);
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
                                com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (AesGcmHkdfStreamingParams) codedInputStream.readMessage(AesGcmHkdfStreamingParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (AesGcmHkdfStreamingParams) builder.buildPartial();
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
                    synchronized (AesGcmHkdfStreamingKey.class) {
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

    public static AesGcmHkdfStreamingKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesGcmHkdfStreamingKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
