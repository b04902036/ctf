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

public final class AesEaxKeyFormat extends GeneratedMessageLite<AesEaxKeyFormat, Builder> implements AesEaxKeyFormatOrBuilder {
    private static final AesEaxKeyFormat DEFAULT_INSTANCE = new AesEaxKeyFormat();
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser<AesEaxKeyFormat> PARSER;
    private int keySize_;
    private AesEaxParams params_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesEaxKeyFormat, Builder> implements AesEaxKeyFormatOrBuilder {
        private Builder() {
            super(AesEaxKeyFormat.DEFAULT_INSTANCE);
        }

        public boolean hasParams() {
            return ((AesEaxKeyFormat) this.instance).hasParams();
        }

        public AesEaxParams getParams() {
            return ((AesEaxKeyFormat) this.instance).getParams();
        }

        public Builder setParams(AesEaxParams aesEaxParams) {
            copyOnWrite();
            ((AesEaxKeyFormat) this.instance).setParams(aesEaxParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesEaxParams.Builder builder) {
            copyOnWrite();
            ((AesEaxKeyFormat) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(AesEaxParams aesEaxParams) {
            copyOnWrite();
            ((AesEaxKeyFormat) this.instance).mergeParams(aesEaxParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((AesEaxKeyFormat) this.instance).clearParams();
            return this;
        }

        public int getKeySize() {
            return ((AesEaxKeyFormat) this.instance).getKeySize();
        }

        public Builder setKeySize(int i) {
            copyOnWrite();
            ((AesEaxKeyFormat) this.instance).setKeySize(i);
            return this;
        }

        public Builder clearKeySize() {
            copyOnWrite();
            ((AesEaxKeyFormat) this.instance).clearKeySize();
            return this;
        }
    }

    private AesEaxKeyFormat() {
    }

    public boolean hasParams() {
        return this.params_ != null;
    }

    public AesEaxParams getParams() {
        return this.params_ == null ? AesEaxParams.getDefaultInstance() : this.params_;
    }

    private void setParams(AesEaxParams aesEaxParams) {
        if (aesEaxParams != null) {
            this.params_ = aesEaxParams;
            return;
        }
        throw new NullPointerException();
    }

    private void setParams(com.google.crypto.tink.proto.AesEaxParams.Builder builder) {
        this.params_ = (AesEaxParams) builder.build();
    }

    private void mergeParams(AesEaxParams aesEaxParams) {
        if (this.params_ == null || this.params_ == AesEaxParams.getDefaultInstance()) {
            this.params_ = aesEaxParams;
        } else {
            this.params_ = (AesEaxParams) ((com.google.crypto.tink.proto.AesEaxParams.Builder) AesEaxParams.newBuilder(this.params_).mergeFrom(aesEaxParams)).buildPartial();
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

    public static AesEaxKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesEaxKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesEaxKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesEaxKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesEaxKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesEaxKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesEaxKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesEaxKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesEaxKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (AesEaxKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesEaxKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesEaxKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesEaxKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesEaxKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesEaxKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesEaxKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesEaxKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesEaxKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesEaxKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesEaxKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesEaxKeyFormat aesEaxKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesEaxKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesEaxKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesEaxKeyFormat aesEaxKeyFormat = (AesEaxKeyFormat) obj2;
                this.params_ = (AesEaxParams) visitor.visitMessage(this.params_, aesEaxKeyFormat.params_);
                boolean z2 = this.keySize_ != 0;
                int i = this.keySize_;
                if (aesEaxKeyFormat.keySize_ != 0) {
                    z = true;
                }
                this.keySize_ = visitor.visitInt(z2, i, z, aesEaxKeyFormat.keySize_);
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
                                com.google.crypto.tink.proto.AesEaxParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.AesEaxParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (AesEaxParams) codedInputStream.readMessage(AesEaxParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (AesEaxParams) builder.buildPartial();
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
                    synchronized (AesEaxKeyFormat.class) {
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

    public static AesEaxKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesEaxKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
