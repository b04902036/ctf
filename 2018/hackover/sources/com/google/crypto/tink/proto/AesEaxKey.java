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

public final class AesEaxKey extends GeneratedMessageLite<AesEaxKey, Builder> implements AesEaxKeyOrBuilder {
    private static final AesEaxKey DEFAULT_INSTANCE = new AesEaxKey();
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser<AesEaxKey> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_ = ByteString.EMPTY;
    private AesEaxParams params_;
    private int version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesEaxKey, Builder> implements AesEaxKeyOrBuilder {
        private Builder() {
            super(AesEaxKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((AesEaxKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((AesEaxKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((AesEaxKey) this.instance).clearVersion();
            return this;
        }

        public boolean hasParams() {
            return ((AesEaxKey) this.instance).hasParams();
        }

        public AesEaxParams getParams() {
            return ((AesEaxKey) this.instance).getParams();
        }

        public Builder setParams(AesEaxParams aesEaxParams) {
            copyOnWrite();
            ((AesEaxKey) this.instance).setParams(aesEaxParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.AesEaxParams.Builder builder) {
            copyOnWrite();
            ((AesEaxKey) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(AesEaxParams aesEaxParams) {
            copyOnWrite();
            ((AesEaxKey) this.instance).mergeParams(aesEaxParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((AesEaxKey) this.instance).clearParams();
            return this;
        }

        public ByteString getKeyValue() {
            return ((AesEaxKey) this.instance).getKeyValue();
        }

        public Builder setKeyValue(ByteString byteString) {
            copyOnWrite();
            ((AesEaxKey) this.instance).setKeyValue(byteString);
            return this;
        }

        public Builder clearKeyValue() {
            copyOnWrite();
            ((AesEaxKey) this.instance).clearKeyValue();
            return this;
        }
    }

    private AesEaxKey() {
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

    public static AesEaxKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesEaxKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesEaxKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesEaxKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesEaxKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesEaxKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesEaxKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesEaxKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesEaxKey parseFrom(InputStream inputStream) throws IOException {
        return (AesEaxKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesEaxKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesEaxKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesEaxKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesEaxKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesEaxKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesEaxKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesEaxKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesEaxKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesEaxKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesEaxKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesEaxKey aesEaxKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesEaxKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesEaxKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesEaxKey aesEaxKey = (AesEaxKey) obj2;
                this.version_ = visitor.visitInt(this.version_ != 0, this.version_, aesEaxKey.version_ != 0, aesEaxKey.version_);
                this.params_ = (AesEaxParams) visitor.visitMessage(this.params_, aesEaxKey.params_);
                boolean z2 = this.keyValue_ != ByteString.EMPTY;
                ByteString byteString = this.keyValue_;
                if (aesEaxKey.keyValue_ != ByteString.EMPTY) {
                    z = true;
                }
                this.keyValue_ = visitor.visitByteString(z2, byteString, z, aesEaxKey.keyValue_);
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
                                com.google.crypto.tink.proto.AesEaxParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.AesEaxParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (AesEaxParams) codedInputStream.readMessage(AesEaxParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (AesEaxParams) builder.buildPartial();
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
                    synchronized (AesEaxKey.class) {
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

    public static AesEaxKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesEaxKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
