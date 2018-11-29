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

public final class EcdsaKeyFormat extends GeneratedMessageLite<EcdsaKeyFormat, Builder> implements EcdsaKeyFormatOrBuilder {
    private static final EcdsaKeyFormat DEFAULT_INSTANCE = new EcdsaKeyFormat();
    public static final int PARAMS_FIELD_NUMBER = 2;
    private static volatile Parser<EcdsaKeyFormat> PARSER;
    private EcdsaParams params_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EcdsaKeyFormat, Builder> implements EcdsaKeyFormatOrBuilder {
        private Builder() {
            super(EcdsaKeyFormat.DEFAULT_INSTANCE);
        }

        public boolean hasParams() {
            return ((EcdsaKeyFormat) this.instance).hasParams();
        }

        public EcdsaParams getParams() {
            return ((EcdsaKeyFormat) this.instance).getParams();
        }

        public Builder setParams(EcdsaParams ecdsaParams) {
            copyOnWrite();
            ((EcdsaKeyFormat) this.instance).setParams(ecdsaParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.EcdsaParams.Builder builder) {
            copyOnWrite();
            ((EcdsaKeyFormat) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(EcdsaParams ecdsaParams) {
            copyOnWrite();
            ((EcdsaKeyFormat) this.instance).mergeParams(ecdsaParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((EcdsaKeyFormat) this.instance).clearParams();
            return this;
        }
    }

    private EcdsaKeyFormat() {
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

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
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
        if (this.params_ != null) {
            i = 0 + CodedOutputStream.computeMessageSize(2, getParams());
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static EcdsaKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EcdsaKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EcdsaKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EcdsaKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EcdsaKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EcdsaKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EcdsaKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EcdsaKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EcdsaKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (EcdsaKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EcdsaKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EcdsaKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EcdsaKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EcdsaKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EcdsaKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EcdsaKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EcdsaKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EcdsaKeyFormat ecdsaKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(ecdsaKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EcdsaKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.params_ = (EcdsaParams) ((Visitor) obj).visitMessage(this.params_, ((EcdsaKeyFormat) obj2).params_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                Object obj3 = null;
                while (obj3 == null) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 18) {
                                com.google.crypto.tink.proto.EcdsaParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.EcdsaParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (EcdsaParams) codedInputStream.readMessage(EcdsaParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (EcdsaParams) builder.buildPartial();
                                }
                            } else if (codedInputStream.skipField(readTag)) {
                            }
                        }
                        obj3 = 1;
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
                    synchronized (EcdsaKeyFormat.class) {
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

    public static EcdsaKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EcdsaKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
