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

public final class KmsAeadKey extends GeneratedMessageLite<KmsAeadKey, Builder> implements KmsAeadKeyOrBuilder {
    private static final KmsAeadKey DEFAULT_INSTANCE = new KmsAeadKey();
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser<KmsAeadKey> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private KmsAeadKeyFormat params_;
    private int version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<KmsAeadKey, Builder> implements KmsAeadKeyOrBuilder {
        private Builder() {
            super(KmsAeadKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((KmsAeadKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((KmsAeadKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((KmsAeadKey) this.instance).clearVersion();
            return this;
        }

        public boolean hasParams() {
            return ((KmsAeadKey) this.instance).hasParams();
        }

        public KmsAeadKeyFormat getParams() {
            return ((KmsAeadKey) this.instance).getParams();
        }

        public Builder setParams(KmsAeadKeyFormat kmsAeadKeyFormat) {
            copyOnWrite();
            ((KmsAeadKey) this.instance).setParams(kmsAeadKeyFormat);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.KmsAeadKeyFormat.Builder builder) {
            copyOnWrite();
            ((KmsAeadKey) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(KmsAeadKeyFormat kmsAeadKeyFormat) {
            copyOnWrite();
            ((KmsAeadKey) this.instance).mergeParams(kmsAeadKeyFormat);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((KmsAeadKey) this.instance).clearParams();
            return this;
        }
    }

    private KmsAeadKey() {
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

    public KmsAeadKeyFormat getParams() {
        return this.params_ == null ? KmsAeadKeyFormat.getDefaultInstance() : this.params_;
    }

    private void setParams(KmsAeadKeyFormat kmsAeadKeyFormat) {
        if (kmsAeadKeyFormat != null) {
            this.params_ = kmsAeadKeyFormat;
            return;
        }
        throw new NullPointerException();
    }

    private void setParams(com.google.crypto.tink.proto.KmsAeadKeyFormat.Builder builder) {
        this.params_ = (KmsAeadKeyFormat) builder.build();
    }

    private void mergeParams(KmsAeadKeyFormat kmsAeadKeyFormat) {
        if (this.params_ == null || this.params_ == KmsAeadKeyFormat.getDefaultInstance()) {
            this.params_ = kmsAeadKeyFormat;
        } else {
            this.params_ = (KmsAeadKeyFormat) ((com.google.crypto.tink.proto.KmsAeadKeyFormat.Builder) KmsAeadKeyFormat.newBuilder(this.params_).mergeFrom(kmsAeadKeyFormat)).buildPartial();
        }
    }

    private void clearParams() {
        this.params_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.version_ != 0) {
            codedOutputStream.writeUInt32(1, this.version_);
        }
        if (this.params_ != null) {
            codedOutputStream.writeMessage(2, getParams());
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
        this.memoizedSerializedSize = i;
        return i;
    }

    public static KmsAeadKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static KmsAeadKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static KmsAeadKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static KmsAeadKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static KmsAeadKey parseFrom(InputStream inputStream) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KmsAeadKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KmsAeadKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KmsAeadKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KmsAeadKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static KmsAeadKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KmsAeadKey kmsAeadKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(kmsAeadKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new KmsAeadKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                KmsAeadKey kmsAeadKey = (KmsAeadKey) obj2;
                boolean z2 = this.version_ != 0;
                int i = this.version_;
                if (kmsAeadKey.version_ != 0) {
                    z = true;
                }
                this.version_ = visitor.visitInt(z2, i, z, kmsAeadKey.version_);
                this.params_ = (KmsAeadKeyFormat) visitor.visitMessage(this.params_, kmsAeadKey.params_);
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
                                com.google.crypto.tink.proto.KmsAeadKeyFormat.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.KmsAeadKeyFormat.Builder) this.params_.toBuilder() : null;
                                this.params_ = (KmsAeadKeyFormat) codedInputStream.readMessage(KmsAeadKeyFormat.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (KmsAeadKeyFormat) builder.buildPartial();
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
                    synchronized (KmsAeadKey.class) {
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

    public static KmsAeadKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KmsAeadKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
