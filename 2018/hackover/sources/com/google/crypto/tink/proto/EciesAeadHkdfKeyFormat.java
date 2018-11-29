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

public final class EciesAeadHkdfKeyFormat extends GeneratedMessageLite<EciesAeadHkdfKeyFormat, Builder> implements EciesAeadHkdfKeyFormatOrBuilder {
    private static final EciesAeadHkdfKeyFormat DEFAULT_INSTANCE = new EciesAeadHkdfKeyFormat();
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser<EciesAeadHkdfKeyFormat> PARSER;
    private EciesAeadHkdfParams params_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EciesAeadHkdfKeyFormat, Builder> implements EciesAeadHkdfKeyFormatOrBuilder {
        private Builder() {
            super(EciesAeadHkdfKeyFormat.DEFAULT_INSTANCE);
        }

        public boolean hasParams() {
            return ((EciesAeadHkdfKeyFormat) this.instance).hasParams();
        }

        public EciesAeadHkdfParams getParams() {
            return ((EciesAeadHkdfKeyFormat) this.instance).getParams();
        }

        public Builder setParams(EciesAeadHkdfParams eciesAeadHkdfParams) {
            copyOnWrite();
            ((EciesAeadHkdfKeyFormat) this.instance).setParams(eciesAeadHkdfParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.EciesAeadHkdfParams.Builder builder) {
            copyOnWrite();
            ((EciesAeadHkdfKeyFormat) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(EciesAeadHkdfParams eciesAeadHkdfParams) {
            copyOnWrite();
            ((EciesAeadHkdfKeyFormat) this.instance).mergeParams(eciesAeadHkdfParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((EciesAeadHkdfKeyFormat) this.instance).clearParams();
            return this;
        }
    }

    private EciesAeadHkdfKeyFormat() {
    }

    public boolean hasParams() {
        return this.params_ != null;
    }

    public EciesAeadHkdfParams getParams() {
        return this.params_ == null ? EciesAeadHkdfParams.getDefaultInstance() : this.params_;
    }

    private void setParams(EciesAeadHkdfParams eciesAeadHkdfParams) {
        if (eciesAeadHkdfParams != null) {
            this.params_ = eciesAeadHkdfParams;
            return;
        }
        throw new NullPointerException();
    }

    private void setParams(com.google.crypto.tink.proto.EciesAeadHkdfParams.Builder builder) {
        this.params_ = (EciesAeadHkdfParams) builder.build();
    }

    private void mergeParams(EciesAeadHkdfParams eciesAeadHkdfParams) {
        if (this.params_ == null || this.params_ == EciesAeadHkdfParams.getDefaultInstance()) {
            this.params_ = eciesAeadHkdfParams;
        } else {
            this.params_ = (EciesAeadHkdfParams) ((com.google.crypto.tink.proto.EciesAeadHkdfParams.Builder) EciesAeadHkdfParams.newBuilder(this.params_).mergeFrom(eciesAeadHkdfParams)).buildPartial();
        }
    }

    private void clearParams() {
        this.params_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.params_ != null) {
            codedOutputStream.writeMessage(1, getParams());
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
        this.memoizedSerializedSize = i;
        return i;
    }

    public static EciesAeadHkdfKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (EciesAeadHkdfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadHkdfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesAeadHkdfKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EciesAeadHkdfKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesAeadHkdfKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadHkdfKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EciesAeadHkdfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EciesAeadHkdfKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadHkdfKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EciesAeadHkdfKeyFormat eciesAeadHkdfKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(eciesAeadHkdfKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EciesAeadHkdfKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.params_ = (EciesAeadHkdfParams) ((Visitor) obj).visitMessage(this.params_, ((EciesAeadHkdfKeyFormat) obj2).params_);
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
                            if (readTag == 10) {
                                com.google.crypto.tink.proto.EciesAeadHkdfParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.EciesAeadHkdfParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (EciesAeadHkdfParams) codedInputStream.readMessage(EciesAeadHkdfParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (EciesAeadHkdfParams) builder.buildPartial();
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
                    synchronized (EciesAeadHkdfKeyFormat.class) {
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

    public static EciesAeadHkdfKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EciesAeadHkdfKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
