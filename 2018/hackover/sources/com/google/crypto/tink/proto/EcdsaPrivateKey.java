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

public final class EcdsaPrivateKey extends GeneratedMessageLite<EcdsaPrivateKey, Builder> implements EcdsaPrivateKeyOrBuilder {
    private static final EcdsaPrivateKey DEFAULT_INSTANCE = new EcdsaPrivateKey();
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    private static volatile Parser<EcdsaPrivateKey> PARSER = null;
    public static final int PUBLIC_KEY_FIELD_NUMBER = 2;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_ = ByteString.EMPTY;
    private EcdsaPublicKey publicKey_;
    private int version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EcdsaPrivateKey, Builder> implements EcdsaPrivateKeyOrBuilder {
        private Builder() {
            super(EcdsaPrivateKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((EcdsaPrivateKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((EcdsaPrivateKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((EcdsaPrivateKey) this.instance).clearVersion();
            return this;
        }

        public boolean hasPublicKey() {
            return ((EcdsaPrivateKey) this.instance).hasPublicKey();
        }

        public EcdsaPublicKey getPublicKey() {
            return ((EcdsaPrivateKey) this.instance).getPublicKey();
        }

        public Builder setPublicKey(EcdsaPublicKey ecdsaPublicKey) {
            copyOnWrite();
            ((EcdsaPrivateKey) this.instance).setPublicKey(ecdsaPublicKey);
            return this;
        }

        public Builder setPublicKey(com.google.crypto.tink.proto.EcdsaPublicKey.Builder builder) {
            copyOnWrite();
            ((EcdsaPrivateKey) this.instance).setPublicKey(builder);
            return this;
        }

        public Builder mergePublicKey(EcdsaPublicKey ecdsaPublicKey) {
            copyOnWrite();
            ((EcdsaPrivateKey) this.instance).mergePublicKey(ecdsaPublicKey);
            return this;
        }

        public Builder clearPublicKey() {
            copyOnWrite();
            ((EcdsaPrivateKey) this.instance).clearPublicKey();
            return this;
        }

        public ByteString getKeyValue() {
            return ((EcdsaPrivateKey) this.instance).getKeyValue();
        }

        public Builder setKeyValue(ByteString byteString) {
            copyOnWrite();
            ((EcdsaPrivateKey) this.instance).setKeyValue(byteString);
            return this;
        }

        public Builder clearKeyValue() {
            copyOnWrite();
            ((EcdsaPrivateKey) this.instance).clearKeyValue();
            return this;
        }
    }

    private EcdsaPrivateKey() {
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

    public boolean hasPublicKey() {
        return this.publicKey_ != null;
    }

    public EcdsaPublicKey getPublicKey() {
        return this.publicKey_ == null ? EcdsaPublicKey.getDefaultInstance() : this.publicKey_;
    }

    private void setPublicKey(EcdsaPublicKey ecdsaPublicKey) {
        if (ecdsaPublicKey != null) {
            this.publicKey_ = ecdsaPublicKey;
            return;
        }
        throw new NullPointerException();
    }

    private void setPublicKey(com.google.crypto.tink.proto.EcdsaPublicKey.Builder builder) {
        this.publicKey_ = (EcdsaPublicKey) builder.build();
    }

    private void mergePublicKey(EcdsaPublicKey ecdsaPublicKey) {
        if (this.publicKey_ == null || this.publicKey_ == EcdsaPublicKey.getDefaultInstance()) {
            this.publicKey_ = ecdsaPublicKey;
        } else {
            this.publicKey_ = (EcdsaPublicKey) ((com.google.crypto.tink.proto.EcdsaPublicKey.Builder) EcdsaPublicKey.newBuilder(this.publicKey_).mergeFrom(ecdsaPublicKey)).buildPartial();
        }
    }

    private void clearPublicKey() {
        this.publicKey_ = null;
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
        if (this.publicKey_ != null) {
            codedOutputStream.writeMessage(2, getPublicKey());
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
        if (this.publicKey_ != null) {
            i += CodedOutputStream.computeMessageSize(2, getPublicKey());
        }
        if (!this.keyValue_.isEmpty()) {
            i += CodedOutputStream.computeBytesSize(3, this.keyValue_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static EcdsaPrivateKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EcdsaPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EcdsaPrivateKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EcdsaPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EcdsaPrivateKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EcdsaPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EcdsaPrivateKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EcdsaPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EcdsaPrivateKey parseFrom(InputStream inputStream) throws IOException {
        return (EcdsaPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EcdsaPrivateKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EcdsaPrivateKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EcdsaPrivateKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EcdsaPrivateKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaPrivateKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EcdsaPrivateKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EcdsaPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EcdsaPrivateKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EcdsaPrivateKey ecdsaPrivateKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(ecdsaPrivateKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EcdsaPrivateKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                EcdsaPrivateKey ecdsaPrivateKey = (EcdsaPrivateKey) obj2;
                this.version_ = visitor.visitInt(this.version_ != 0, this.version_, ecdsaPrivateKey.version_ != 0, ecdsaPrivateKey.version_);
                this.publicKey_ = (EcdsaPublicKey) visitor.visitMessage(this.publicKey_, ecdsaPrivateKey.publicKey_);
                boolean z2 = this.keyValue_ != ByteString.EMPTY;
                ByteString byteString = this.keyValue_;
                if (ecdsaPrivateKey.keyValue_ != ByteString.EMPTY) {
                    z = true;
                }
                this.keyValue_ = visitor.visitByteString(z2, byteString, z, ecdsaPrivateKey.keyValue_);
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
                                com.google.crypto.tink.proto.EcdsaPublicKey.Builder builder = this.publicKey_ != null ? (com.google.crypto.tink.proto.EcdsaPublicKey.Builder) this.publicKey_.toBuilder() : null;
                                this.publicKey_ = (EcdsaPublicKey) codedInputStream.readMessage(EcdsaPublicKey.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.publicKey_);
                                    this.publicKey_ = (EcdsaPublicKey) builder.buildPartial();
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
                    synchronized (EcdsaPrivateKey.class) {
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

    public static EcdsaPrivateKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EcdsaPrivateKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
