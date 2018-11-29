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

public final class EciesAeadHkdfPrivateKey extends GeneratedMessageLite<EciesAeadHkdfPrivateKey, Builder> implements EciesAeadHkdfPrivateKeyOrBuilder {
    private static final EciesAeadHkdfPrivateKey DEFAULT_INSTANCE = new EciesAeadHkdfPrivateKey();
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    private static volatile Parser<EciesAeadHkdfPrivateKey> PARSER = null;
    public static final int PUBLIC_KEY_FIELD_NUMBER = 2;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_ = ByteString.EMPTY;
    private EciesAeadHkdfPublicKey publicKey_;
    private int version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EciesAeadHkdfPrivateKey, Builder> implements EciesAeadHkdfPrivateKeyOrBuilder {
        private Builder() {
            super(EciesAeadHkdfPrivateKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((EciesAeadHkdfPrivateKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((EciesAeadHkdfPrivateKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((EciesAeadHkdfPrivateKey) this.instance).clearVersion();
            return this;
        }

        public boolean hasPublicKey() {
            return ((EciesAeadHkdfPrivateKey) this.instance).hasPublicKey();
        }

        public EciesAeadHkdfPublicKey getPublicKey() {
            return ((EciesAeadHkdfPrivateKey) this.instance).getPublicKey();
        }

        public Builder setPublicKey(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey) {
            copyOnWrite();
            ((EciesAeadHkdfPrivateKey) this.instance).setPublicKey(eciesAeadHkdfPublicKey);
            return this;
        }

        public Builder setPublicKey(com.google.crypto.tink.proto.EciesAeadHkdfPublicKey.Builder builder) {
            copyOnWrite();
            ((EciesAeadHkdfPrivateKey) this.instance).setPublicKey(builder);
            return this;
        }

        public Builder mergePublicKey(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey) {
            copyOnWrite();
            ((EciesAeadHkdfPrivateKey) this.instance).mergePublicKey(eciesAeadHkdfPublicKey);
            return this;
        }

        public Builder clearPublicKey() {
            copyOnWrite();
            ((EciesAeadHkdfPrivateKey) this.instance).clearPublicKey();
            return this;
        }

        public ByteString getKeyValue() {
            return ((EciesAeadHkdfPrivateKey) this.instance).getKeyValue();
        }

        public Builder setKeyValue(ByteString byteString) {
            copyOnWrite();
            ((EciesAeadHkdfPrivateKey) this.instance).setKeyValue(byteString);
            return this;
        }

        public Builder clearKeyValue() {
            copyOnWrite();
            ((EciesAeadHkdfPrivateKey) this.instance).clearKeyValue();
            return this;
        }
    }

    private EciesAeadHkdfPrivateKey() {
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

    public EciesAeadHkdfPublicKey getPublicKey() {
        return this.publicKey_ == null ? EciesAeadHkdfPublicKey.getDefaultInstance() : this.publicKey_;
    }

    private void setPublicKey(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey) {
        if (eciesAeadHkdfPublicKey != null) {
            this.publicKey_ = eciesAeadHkdfPublicKey;
            return;
        }
        throw new NullPointerException();
    }

    private void setPublicKey(com.google.crypto.tink.proto.EciesAeadHkdfPublicKey.Builder builder) {
        this.publicKey_ = (EciesAeadHkdfPublicKey) builder.build();
    }

    private void mergePublicKey(EciesAeadHkdfPublicKey eciesAeadHkdfPublicKey) {
        if (this.publicKey_ == null || this.publicKey_ == EciesAeadHkdfPublicKey.getDefaultInstance()) {
            this.publicKey_ = eciesAeadHkdfPublicKey;
        } else {
            this.publicKey_ = (EciesAeadHkdfPublicKey) ((com.google.crypto.tink.proto.EciesAeadHkdfPublicKey.Builder) EciesAeadHkdfPublicKey.newBuilder(this.publicKey_).mergeFrom(eciesAeadHkdfPublicKey)).buildPartial();
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

    public static EciesAeadHkdfPrivateKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(InputStream inputStream) throws IOException {
        return (EciesAeadHkdfPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadHkdfPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesAeadHkdfPrivateKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EciesAeadHkdfPrivateKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesAeadHkdfPrivateKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadHkdfPrivateKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EciesAeadHkdfPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EciesAeadHkdfPrivateKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadHkdfPrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(eciesAeadHkdfPrivateKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EciesAeadHkdfPrivateKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                EciesAeadHkdfPrivateKey eciesAeadHkdfPrivateKey = (EciesAeadHkdfPrivateKey) obj2;
                this.version_ = visitor.visitInt(this.version_ != 0, this.version_, eciesAeadHkdfPrivateKey.version_ != 0, eciesAeadHkdfPrivateKey.version_);
                this.publicKey_ = (EciesAeadHkdfPublicKey) visitor.visitMessage(this.publicKey_, eciesAeadHkdfPrivateKey.publicKey_);
                boolean z2 = this.keyValue_ != ByteString.EMPTY;
                ByteString byteString = this.keyValue_;
                if (eciesAeadHkdfPrivateKey.keyValue_ != ByteString.EMPTY) {
                    z = true;
                }
                this.keyValue_ = visitor.visitByteString(z2, byteString, z, eciesAeadHkdfPrivateKey.keyValue_);
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
                                com.google.crypto.tink.proto.EciesAeadHkdfPublicKey.Builder builder = this.publicKey_ != null ? (com.google.crypto.tink.proto.EciesAeadHkdfPublicKey.Builder) this.publicKey_.toBuilder() : null;
                                this.publicKey_ = (EciesAeadHkdfPublicKey) codedInputStream.readMessage(EciesAeadHkdfPublicKey.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.publicKey_);
                                    this.publicKey_ = (EciesAeadHkdfPublicKey) builder.buildPartial();
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
                    synchronized (EciesAeadHkdfPrivateKey.class) {
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

    public static EciesAeadHkdfPrivateKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EciesAeadHkdfPrivateKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
