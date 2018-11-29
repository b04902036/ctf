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

public final class KmsEnvelopeAeadKey extends GeneratedMessageLite<KmsEnvelopeAeadKey, Builder> implements KmsEnvelopeAeadKeyOrBuilder {
    private static final KmsEnvelopeAeadKey DEFAULT_INSTANCE = new KmsEnvelopeAeadKey();
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser<KmsEnvelopeAeadKey> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private KmsEnvelopeAeadKeyFormat params_;
    private int version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<KmsEnvelopeAeadKey, Builder> implements KmsEnvelopeAeadKeyOrBuilder {
        private Builder() {
            super(KmsEnvelopeAeadKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((KmsEnvelopeAeadKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((KmsEnvelopeAeadKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((KmsEnvelopeAeadKey) this.instance).clearVersion();
            return this;
        }

        public boolean hasParams() {
            return ((KmsEnvelopeAeadKey) this.instance).hasParams();
        }

        public KmsEnvelopeAeadKeyFormat getParams() {
            return ((KmsEnvelopeAeadKey) this.instance).getParams();
        }

        public Builder setParams(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat) {
            copyOnWrite();
            ((KmsEnvelopeAeadKey) this.instance).setParams(kmsEnvelopeAeadKeyFormat);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat.Builder builder) {
            copyOnWrite();
            ((KmsEnvelopeAeadKey) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat) {
            copyOnWrite();
            ((KmsEnvelopeAeadKey) this.instance).mergeParams(kmsEnvelopeAeadKeyFormat);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((KmsEnvelopeAeadKey) this.instance).clearParams();
            return this;
        }
    }

    private KmsEnvelopeAeadKey() {
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

    public KmsEnvelopeAeadKeyFormat getParams() {
        return this.params_ == null ? KmsEnvelopeAeadKeyFormat.getDefaultInstance() : this.params_;
    }

    private void setParams(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat) {
        if (kmsEnvelopeAeadKeyFormat != null) {
            this.params_ = kmsEnvelopeAeadKeyFormat;
            return;
        }
        throw new NullPointerException();
    }

    private void setParams(com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat.Builder builder) {
        this.params_ = (KmsEnvelopeAeadKeyFormat) builder.build();
    }

    private void mergeParams(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat) {
        if (this.params_ == null || this.params_ == KmsEnvelopeAeadKeyFormat.getDefaultInstance()) {
            this.params_ = kmsEnvelopeAeadKeyFormat;
        } else {
            this.params_ = (KmsEnvelopeAeadKeyFormat) ((com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat.Builder) KmsEnvelopeAeadKeyFormat.newBuilder(this.params_).mergeFrom(kmsEnvelopeAeadKeyFormat)).buildPartial();
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

    public static KmsEnvelopeAeadKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static KmsEnvelopeAeadKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static KmsEnvelopeAeadKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static KmsEnvelopeAeadKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static KmsEnvelopeAeadKey parseFrom(InputStream inputStream) throws IOException {
        return (KmsEnvelopeAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KmsEnvelopeAeadKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsEnvelopeAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KmsEnvelopeAeadKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (KmsEnvelopeAeadKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KmsEnvelopeAeadKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsEnvelopeAeadKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KmsEnvelopeAeadKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (KmsEnvelopeAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static KmsEnvelopeAeadKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsEnvelopeAeadKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KmsEnvelopeAeadKey kmsEnvelopeAeadKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(kmsEnvelopeAeadKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new KmsEnvelopeAeadKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                KmsEnvelopeAeadKey kmsEnvelopeAeadKey = (KmsEnvelopeAeadKey) obj2;
                boolean z2 = this.version_ != 0;
                int i = this.version_;
                if (kmsEnvelopeAeadKey.version_ != 0) {
                    z = true;
                }
                this.version_ = visitor.visitInt(z2, i, z, kmsEnvelopeAeadKey.version_);
                this.params_ = (KmsEnvelopeAeadKeyFormat) visitor.visitMessage(this.params_, kmsEnvelopeAeadKey.params_);
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
                                com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.KmsEnvelopeAeadKeyFormat.Builder) this.params_.toBuilder() : null;
                                this.params_ = (KmsEnvelopeAeadKeyFormat) codedInputStream.readMessage(KmsEnvelopeAeadKeyFormat.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (KmsEnvelopeAeadKeyFormat) builder.buildPartial();
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
                    synchronized (KmsEnvelopeAeadKey.class) {
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

    public static KmsEnvelopeAeadKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KmsEnvelopeAeadKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
