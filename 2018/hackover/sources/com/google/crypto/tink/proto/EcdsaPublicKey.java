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

public final class EcdsaPublicKey extends GeneratedMessageLite<EcdsaPublicKey, Builder> implements EcdsaPublicKeyOrBuilder {
    private static final EcdsaPublicKey DEFAULT_INSTANCE = new EcdsaPublicKey();
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser<EcdsaPublicKey> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    public static final int X_FIELD_NUMBER = 3;
    public static final int Y_FIELD_NUMBER = 4;
    private EcdsaParams params_;
    private int version_;
    private ByteString x_ = ByteString.EMPTY;
    private ByteString y_ = ByteString.EMPTY;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EcdsaPublicKey, Builder> implements EcdsaPublicKeyOrBuilder {
        private Builder() {
            super(EcdsaPublicKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((EcdsaPublicKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((EcdsaPublicKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((EcdsaPublicKey) this.instance).clearVersion();
            return this;
        }

        public boolean hasParams() {
            return ((EcdsaPublicKey) this.instance).hasParams();
        }

        public EcdsaParams getParams() {
            return ((EcdsaPublicKey) this.instance).getParams();
        }

        public Builder setParams(EcdsaParams ecdsaParams) {
            copyOnWrite();
            ((EcdsaPublicKey) this.instance).setParams(ecdsaParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.EcdsaParams.Builder builder) {
            copyOnWrite();
            ((EcdsaPublicKey) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(EcdsaParams ecdsaParams) {
            copyOnWrite();
            ((EcdsaPublicKey) this.instance).mergeParams(ecdsaParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((EcdsaPublicKey) this.instance).clearParams();
            return this;
        }

        public ByteString getX() {
            return ((EcdsaPublicKey) this.instance).getX();
        }

        public Builder setX(ByteString byteString) {
            copyOnWrite();
            ((EcdsaPublicKey) this.instance).setX(byteString);
            return this;
        }

        public Builder clearX() {
            copyOnWrite();
            ((EcdsaPublicKey) this.instance).clearX();
            return this;
        }

        public ByteString getY() {
            return ((EcdsaPublicKey) this.instance).getY();
        }

        public Builder setY(ByteString byteString) {
            copyOnWrite();
            ((EcdsaPublicKey) this.instance).setY(byteString);
            return this;
        }

        public Builder clearY() {
            copyOnWrite();
            ((EcdsaPublicKey) this.instance).clearY();
            return this;
        }
    }

    private EcdsaPublicKey() {
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

    public EcdsaParams getParams() {
        return this.params_ == null ? EcdsaParams.getDefaultInstance() : this.params_;
    }

    private void setParams(EcdsaParams ecdsaParams) {
        if (ecdsaParams != null) {
            this.params_ = ecdsaParams;
            return;
        }
        throw new NullPointerException();
    }

    private void setParams(com.google.crypto.tink.proto.EcdsaParams.Builder builder) {
        this.params_ = (EcdsaParams) builder.build();
    }

    private void mergeParams(EcdsaParams ecdsaParams) {
        if (this.params_ == null || this.params_ == EcdsaParams.getDefaultInstance()) {
            this.params_ = ecdsaParams;
        } else {
            this.params_ = (EcdsaParams) ((com.google.crypto.tink.proto.EcdsaParams.Builder) EcdsaParams.newBuilder(this.params_).mergeFrom(ecdsaParams)).buildPartial();
        }
    }

    private void clearParams() {
        this.params_ = null;
    }

    public ByteString getX() {
        return this.x_;
    }

    private void setX(ByteString byteString) {
        if (byteString != null) {
            this.x_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    private void clearX() {
        this.x_ = getDefaultInstance().getX();
    }

    public ByteString getY() {
        return this.y_;
    }

    private void setY(ByteString byteString) {
        if (byteString != null) {
            this.y_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    private void clearY() {
        this.y_ = getDefaultInstance().getY();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.version_ != 0) {
            codedOutputStream.writeUInt32(1, this.version_);
        }
        if (this.params_ != null) {
            codedOutputStream.writeMessage(2, getParams());
        }
        if (!this.x_.isEmpty()) {
            codedOutputStream.writeBytes(3, this.x_);
        }
        if (!this.y_.isEmpty()) {
            codedOutputStream.writeBytes(4, this.y_);
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
        if (!this.x_.isEmpty()) {
            i += CodedOutputStream.computeBytesSize(3, this.x_);
        }
        if (!this.y_.isEmpty()) {
            i += CodedOutputStream.computeBytesSize(4, this.y_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static EcdsaPublicKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EcdsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EcdsaPublicKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EcdsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EcdsaPublicKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EcdsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EcdsaPublicKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EcdsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EcdsaPublicKey parseFrom(InputStream inputStream) throws IOException {
        return (EcdsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EcdsaPublicKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EcdsaPublicKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EcdsaPublicKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EcdsaPublicKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaPublicKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EcdsaPublicKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EcdsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EcdsaPublicKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaPublicKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EcdsaPublicKey ecdsaPublicKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(ecdsaPublicKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EcdsaPublicKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                EcdsaPublicKey ecdsaPublicKey = (EcdsaPublicKey) obj2;
                this.version_ = visitor.visitInt(this.version_ != 0, this.version_, ecdsaPublicKey.version_ != 0, ecdsaPublicKey.version_);
                this.params_ = (EcdsaParams) visitor.visitMessage(this.params_, ecdsaPublicKey.params_);
                this.x_ = visitor.visitByteString(this.x_ != ByteString.EMPTY, this.x_, ecdsaPublicKey.x_ != ByteString.EMPTY, ecdsaPublicKey.x_);
                boolean z2 = this.y_ != ByteString.EMPTY;
                ByteString byteString = this.y_;
                if (ecdsaPublicKey.y_ != ByteString.EMPTY) {
                    z = true;
                }
                this.y_ = visitor.visitByteString(z2, byteString, z, ecdsaPublicKey.y_);
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
                                com.google.crypto.tink.proto.EcdsaParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.EcdsaParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (EcdsaParams) codedInputStream.readMessage(EcdsaParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (EcdsaParams) builder.buildPartial();
                                }
                            } else if (readTag == 26) {
                                this.x_ = codedInputStream.readBytes();
                            } else if (readTag == 34) {
                                this.y_ = codedInputStream.readBytes();
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
                    synchronized (EcdsaPublicKey.class) {
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

    public static EcdsaPublicKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EcdsaPublicKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
