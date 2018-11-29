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

public final class Ed25519PrivateKey extends GeneratedMessageLite<Ed25519PrivateKey, Builder> implements Ed25519PrivateKeyOrBuilder {
    private static final Ed25519PrivateKey DEFAULT_INSTANCE = new Ed25519PrivateKey();
    public static final int KEY_VALUE_FIELD_NUMBER = 2;
    private static volatile Parser<Ed25519PrivateKey> PARSER = null;
    public static final int PUBLIC_KEY_FIELD_NUMBER = 3;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_ = ByteString.EMPTY;
    private Ed25519PublicKey publicKey_;
    private int version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Ed25519PrivateKey, Builder> implements Ed25519PrivateKeyOrBuilder {
        private Builder() {
            super(Ed25519PrivateKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((Ed25519PrivateKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((Ed25519PrivateKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((Ed25519PrivateKey) this.instance).clearVersion();
            return this;
        }

        public ByteString getKeyValue() {
            return ((Ed25519PrivateKey) this.instance).getKeyValue();
        }

        public Builder setKeyValue(ByteString byteString) {
            copyOnWrite();
            ((Ed25519PrivateKey) this.instance).setKeyValue(byteString);
            return this;
        }

        public Builder clearKeyValue() {
            copyOnWrite();
            ((Ed25519PrivateKey) this.instance).clearKeyValue();
            return this;
        }

        public boolean hasPublicKey() {
            return ((Ed25519PrivateKey) this.instance).hasPublicKey();
        }

        public Ed25519PublicKey getPublicKey() {
            return ((Ed25519PrivateKey) this.instance).getPublicKey();
        }

        public Builder setPublicKey(Ed25519PublicKey ed25519PublicKey) {
            copyOnWrite();
            ((Ed25519PrivateKey) this.instance).setPublicKey(ed25519PublicKey);
            return this;
        }

        public Builder setPublicKey(com.google.crypto.tink.proto.Ed25519PublicKey.Builder builder) {
            copyOnWrite();
            ((Ed25519PrivateKey) this.instance).setPublicKey(builder);
            return this;
        }

        public Builder mergePublicKey(Ed25519PublicKey ed25519PublicKey) {
            copyOnWrite();
            ((Ed25519PrivateKey) this.instance).mergePublicKey(ed25519PublicKey);
            return this;
        }

        public Builder clearPublicKey() {
            copyOnWrite();
            ((Ed25519PrivateKey) this.instance).clearPublicKey();
            return this;
        }
    }

    private Ed25519PrivateKey() {
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

    public boolean hasPublicKey() {
        return this.publicKey_ != null;
    }

    public Ed25519PublicKey getPublicKey() {
        return this.publicKey_ == null ? Ed25519PublicKey.getDefaultInstance() : this.publicKey_;
    }

    private void setPublicKey(Ed25519PublicKey ed25519PublicKey) {
        if (ed25519PublicKey != null) {
            this.publicKey_ = ed25519PublicKey;
            return;
        }
        throw new NullPointerException();
    }

    private void setPublicKey(com.google.crypto.tink.proto.Ed25519PublicKey.Builder builder) {
        this.publicKey_ = (Ed25519PublicKey) builder.build();
    }

    private void mergePublicKey(Ed25519PublicKey ed25519PublicKey) {
        if (this.publicKey_ == null || this.publicKey_ == Ed25519PublicKey.getDefaultInstance()) {
            this.publicKey_ = ed25519PublicKey;
        } else {
            this.publicKey_ = (Ed25519PublicKey) ((com.google.crypto.tink.proto.Ed25519PublicKey.Builder) Ed25519PublicKey.newBuilder(this.publicKey_).mergeFrom(ed25519PublicKey)).buildPartial();
        }
    }

    private void clearPublicKey() {
        this.publicKey_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.version_ != 0) {
            codedOutputStream.writeUInt32(1, this.version_);
        }
        if (!this.keyValue_.isEmpty()) {
            codedOutputStream.writeBytes(2, this.keyValue_);
        }
        if (this.publicKey_ != null) {
            codedOutputStream.writeMessage(3, getPublicKey());
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
        if (!this.keyValue_.isEmpty()) {
            i += CodedOutputStream.computeBytesSize(2, this.keyValue_);
        }
        if (this.publicKey_ != null) {
            i += CodedOutputStream.computeMessageSize(3, getPublicKey());
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static Ed25519PrivateKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Ed25519PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Ed25519PrivateKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Ed25519PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Ed25519PrivateKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Ed25519PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Ed25519PrivateKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Ed25519PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Ed25519PrivateKey parseFrom(InputStream inputStream) throws IOException {
        return (Ed25519PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Ed25519PrivateKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Ed25519PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Ed25519PrivateKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Ed25519PrivateKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Ed25519PrivateKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Ed25519PrivateKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Ed25519PrivateKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Ed25519PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Ed25519PrivateKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Ed25519PrivateKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Ed25519PrivateKey ed25519PrivateKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(ed25519PrivateKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new Ed25519PrivateKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                Ed25519PrivateKey ed25519PrivateKey = (Ed25519PrivateKey) obj2;
                this.version_ = visitor.visitInt(this.version_ != 0, this.version_, ed25519PrivateKey.version_ != 0, ed25519PrivateKey.version_);
                boolean z2 = this.keyValue_ != ByteString.EMPTY;
                ByteString byteString = this.keyValue_;
                if (ed25519PrivateKey.keyValue_ != ByteString.EMPTY) {
                    z = true;
                }
                this.keyValue_ = visitor.visitByteString(z2, byteString, z, ed25519PrivateKey.keyValue_);
                this.publicKey_ = (Ed25519PublicKey) visitor.visitMessage(this.publicKey_, ed25519PrivateKey.publicKey_);
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
                                this.keyValue_ = codedInputStream.readBytes();
                            } else if (readTag == 26) {
                                com.google.crypto.tink.proto.Ed25519PublicKey.Builder builder = this.publicKey_ != null ? (com.google.crypto.tink.proto.Ed25519PublicKey.Builder) this.publicKey_.toBuilder() : null;
                                this.publicKey_ = (Ed25519PublicKey) codedInputStream.readMessage(Ed25519PublicKey.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.publicKey_);
                                    this.publicKey_ = (Ed25519PublicKey) builder.buildPartial();
                                }
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
                    synchronized (Ed25519PrivateKey.class) {
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

    public static Ed25519PrivateKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Ed25519PrivateKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
