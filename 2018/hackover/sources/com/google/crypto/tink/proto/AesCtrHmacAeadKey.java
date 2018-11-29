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

public final class AesCtrHmacAeadKey extends GeneratedMessageLite<AesCtrHmacAeadKey, Builder> implements AesCtrHmacAeadKeyOrBuilder {
    public static final int AES_CTR_KEY_FIELD_NUMBER = 2;
    private static final AesCtrHmacAeadKey DEFAULT_INSTANCE = new AesCtrHmacAeadKey();
    public static final int HMAC_KEY_FIELD_NUMBER = 3;
    private static volatile Parser<AesCtrHmacAeadKey> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private AesCtrKey aesCtrKey_;
    private HmacKey hmacKey_;
    private int version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesCtrHmacAeadKey, Builder> implements AesCtrHmacAeadKeyOrBuilder {
        private Builder() {
            super(AesCtrHmacAeadKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((AesCtrHmacAeadKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((AesCtrHmacAeadKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((AesCtrHmacAeadKey) this.instance).clearVersion();
            return this;
        }

        public boolean hasAesCtrKey() {
            return ((AesCtrHmacAeadKey) this.instance).hasAesCtrKey();
        }

        public AesCtrKey getAesCtrKey() {
            return ((AesCtrHmacAeadKey) this.instance).getAesCtrKey();
        }

        public Builder setAesCtrKey(AesCtrKey aesCtrKey) {
            copyOnWrite();
            ((AesCtrHmacAeadKey) this.instance).setAesCtrKey(aesCtrKey);
            return this;
        }

        public Builder setAesCtrKey(com.google.crypto.tink.proto.AesCtrKey.Builder builder) {
            copyOnWrite();
            ((AesCtrHmacAeadKey) this.instance).setAesCtrKey(builder);
            return this;
        }

        public Builder mergeAesCtrKey(AesCtrKey aesCtrKey) {
            copyOnWrite();
            ((AesCtrHmacAeadKey) this.instance).mergeAesCtrKey(aesCtrKey);
            return this;
        }

        public Builder clearAesCtrKey() {
            copyOnWrite();
            ((AesCtrHmacAeadKey) this.instance).clearAesCtrKey();
            return this;
        }

        public boolean hasHmacKey() {
            return ((AesCtrHmacAeadKey) this.instance).hasHmacKey();
        }

        public HmacKey getHmacKey() {
            return ((AesCtrHmacAeadKey) this.instance).getHmacKey();
        }

        public Builder setHmacKey(HmacKey hmacKey) {
            copyOnWrite();
            ((AesCtrHmacAeadKey) this.instance).setHmacKey(hmacKey);
            return this;
        }

        public Builder setHmacKey(com.google.crypto.tink.proto.HmacKey.Builder builder) {
            copyOnWrite();
            ((AesCtrHmacAeadKey) this.instance).setHmacKey(builder);
            return this;
        }

        public Builder mergeHmacKey(HmacKey hmacKey) {
            copyOnWrite();
            ((AesCtrHmacAeadKey) this.instance).mergeHmacKey(hmacKey);
            return this;
        }

        public Builder clearHmacKey() {
            copyOnWrite();
            ((AesCtrHmacAeadKey) this.instance).clearHmacKey();
            return this;
        }
    }

    private AesCtrHmacAeadKey() {
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

    public boolean hasAesCtrKey() {
        return this.aesCtrKey_ != null;
    }

    public AesCtrKey getAesCtrKey() {
        return this.aesCtrKey_ == null ? AesCtrKey.getDefaultInstance() : this.aesCtrKey_;
    }

    private void setAesCtrKey(AesCtrKey aesCtrKey) {
        if (aesCtrKey != null) {
            this.aesCtrKey_ = aesCtrKey;
            return;
        }
        throw new NullPointerException();
    }

    private void setAesCtrKey(com.google.crypto.tink.proto.AesCtrKey.Builder builder) {
        this.aesCtrKey_ = (AesCtrKey) builder.build();
    }

    private void mergeAesCtrKey(AesCtrKey aesCtrKey) {
        if (this.aesCtrKey_ == null || this.aesCtrKey_ == AesCtrKey.getDefaultInstance()) {
            this.aesCtrKey_ = aesCtrKey;
        } else {
            this.aesCtrKey_ = (AesCtrKey) ((com.google.crypto.tink.proto.AesCtrKey.Builder) AesCtrKey.newBuilder(this.aesCtrKey_).mergeFrom(aesCtrKey)).buildPartial();
        }
    }

    private void clearAesCtrKey() {
        this.aesCtrKey_ = null;
    }

    public boolean hasHmacKey() {
        return this.hmacKey_ != null;
    }

    public HmacKey getHmacKey() {
        return this.hmacKey_ == null ? HmacKey.getDefaultInstance() : this.hmacKey_;
    }

    private void setHmacKey(HmacKey hmacKey) {
        if (hmacKey != null) {
            this.hmacKey_ = hmacKey;
            return;
        }
        throw new NullPointerException();
    }

    private void setHmacKey(com.google.crypto.tink.proto.HmacKey.Builder builder) {
        this.hmacKey_ = (HmacKey) builder.build();
    }

    private void mergeHmacKey(HmacKey hmacKey) {
        if (this.hmacKey_ == null || this.hmacKey_ == HmacKey.getDefaultInstance()) {
            this.hmacKey_ = hmacKey;
        } else {
            this.hmacKey_ = (HmacKey) ((com.google.crypto.tink.proto.HmacKey.Builder) HmacKey.newBuilder(this.hmacKey_).mergeFrom(hmacKey)).buildPartial();
        }
    }

    private void clearHmacKey() {
        this.hmacKey_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.version_ != 0) {
            codedOutputStream.writeUInt32(1, this.version_);
        }
        if (this.aesCtrKey_ != null) {
            codedOutputStream.writeMessage(2, getAesCtrKey());
        }
        if (this.hmacKey_ != null) {
            codedOutputStream.writeMessage(3, getHmacKey());
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
        if (this.aesCtrKey_ != null) {
            i += CodedOutputStream.computeMessageSize(2, getAesCtrKey());
        }
        if (this.hmacKey_ != null) {
            i += CodedOutputStream.computeMessageSize(3, getHmacKey());
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static AesCtrHmacAeadKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesCtrHmacAeadKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesCtrHmacAeadKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesCtrHmacAeadKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesCtrHmacAeadKey parseFrom(InputStream inputStream) throws IOException {
        return (AesCtrHmacAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrHmacAeadKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrHmacAeadKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesCtrHmacAeadKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrHmacAeadKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacAeadKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrHmacAeadKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesCtrHmacAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesCtrHmacAeadKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesCtrHmacAeadKey aesCtrHmacAeadKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesCtrHmacAeadKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesCtrHmacAeadKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesCtrHmacAeadKey aesCtrHmacAeadKey = (AesCtrHmacAeadKey) obj2;
                boolean z2 = this.version_ != 0;
                int i = this.version_;
                if (aesCtrHmacAeadKey.version_ != 0) {
                    z = true;
                }
                this.version_ = visitor.visitInt(z2, i, z, aesCtrHmacAeadKey.version_);
                this.aesCtrKey_ = (AesCtrKey) visitor.visitMessage(this.aesCtrKey_, aesCtrHmacAeadKey.aesCtrKey_);
                this.hmacKey_ = (HmacKey) visitor.visitMessage(this.hmacKey_, aesCtrHmacAeadKey.hmacKey_);
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
                                com.google.crypto.tink.proto.AesCtrKey.Builder builder = this.aesCtrKey_ != null ? (com.google.crypto.tink.proto.AesCtrKey.Builder) this.aesCtrKey_.toBuilder() : null;
                                this.aesCtrKey_ = (AesCtrKey) codedInputStream.readMessage(AesCtrKey.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.aesCtrKey_);
                                    this.aesCtrKey_ = (AesCtrKey) builder.buildPartial();
                                }
                            } else if (readTag == 26) {
                                com.google.crypto.tink.proto.HmacKey.Builder builder2 = this.hmacKey_ != null ? (com.google.crypto.tink.proto.HmacKey.Builder) this.hmacKey_.toBuilder() : null;
                                this.hmacKey_ = (HmacKey) codedInputStream.readMessage(HmacKey.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.hmacKey_);
                                    this.hmacKey_ = (HmacKey) builder2.buildPartial();
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
                    synchronized (AesCtrHmacAeadKey.class) {
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

    public static AesCtrHmacAeadKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesCtrHmacAeadKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
