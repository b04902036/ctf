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

public final class AesGcmHkdfStreamingKeyFormat extends GeneratedMessageLite<AesGcmHkdfStreamingKeyFormat, Builder> implements AesGcmHkdfStreamingKeyFormatOrBuilder {
    private static final AesGcmHkdfStreamingKeyFormat DEFAULT_INSTANCE = new AesGcmHkdfStreamingKeyFormat();
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser<AesGcmHkdfStreamingKeyFormat> PARSER;
    private int keySize_;
    private AesGcmHkdfStreamingParams params_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesGcmHkdfStreamingKeyFormat, Builder> implements AesGcmHkdfStreamingKeyFormatOrBuilder {
        private Builder() {
            super(AesGcmHkdfStreamingKeyFormat.DEFAULT_INSTANCE);
        }

        public boolean hasParams() {
            return ((AesGcmHkdfStreamingKeyFormat) this.instance).hasParams();
        }

        public AesGcmHkdfStreamingParams getParams() {
            return ((AesGcmHkdfStreamingKeyFormat) this.instance).getParams();
        }

        public Builder setParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams) {
            copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat) this.instance).setParams(aesGcmHkdfStreamingParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.Builder builder) {
            copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams) {
            copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat) this.instance).mergeParams(aesGcmHkdfStreamingParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat) this.instance).clearParams();
            return this;
        }

        public int getKeySize() {
            return ((AesGcmHkdfStreamingKeyFormat) this.instance).getKeySize();
        }

        public Builder setKeySize(int i) {
            copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat) this.instance).setKeySize(i);
            return this;
        }

        public Builder clearKeySize() {
            copyOnWrite();
            ((AesGcmHkdfStreamingKeyFormat) this.instance).clearKeySize();
            return this;
        }
    }

    private AesGcmHkdfStreamingKeyFormat() {
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

    public static AesGcmHkdfStreamingKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesGcmHkdfStreamingKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesGcmHkdfStreamingKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmHkdfStreamingKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesGcmHkdfStreamingKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesGcmHkdfStreamingKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesGcmHkdfStreamingKeyFormat aesGcmHkdfStreamingKeyFormat = (AesGcmHkdfStreamingKeyFormat) obj2;
                this.params_ = (AesGcmHkdfStreamingParams) visitor.visitMessage(this.params_, aesGcmHkdfStreamingKeyFormat.params_);
                boolean z2 = this.keySize_ != 0;
                int i = this.keySize_;
                if (aesGcmHkdfStreamingKeyFormat.keySize_ != 0) {
                    z = true;
                }
                this.keySize_ = visitor.visitInt(z2, i, z, aesGcmHkdfStreamingKeyFormat.keySize_);
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
                                com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.AesGcmHkdfStreamingParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (AesGcmHkdfStreamingParams) codedInputStream.readMessage(AesGcmHkdfStreamingParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (AesGcmHkdfStreamingParams) builder.buildPartial();
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
                    synchronized (AesGcmHkdfStreamingKeyFormat.class) {
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

    public static AesGcmHkdfStreamingKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesGcmHkdfStreamingKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
