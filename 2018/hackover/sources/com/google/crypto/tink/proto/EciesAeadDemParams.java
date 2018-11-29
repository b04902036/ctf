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

public final class EciesAeadDemParams extends GeneratedMessageLite<EciesAeadDemParams, Builder> implements EciesAeadDemParamsOrBuilder {
    public static final int AEAD_DEM_FIELD_NUMBER = 2;
    private static final EciesAeadDemParams DEFAULT_INSTANCE = new EciesAeadDemParams();
    private static volatile Parser<EciesAeadDemParams> PARSER;
    private KeyTemplate aeadDem_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EciesAeadDemParams, Builder> implements EciesAeadDemParamsOrBuilder {
        private Builder() {
            super(EciesAeadDemParams.DEFAULT_INSTANCE);
        }

        public boolean hasAeadDem() {
            return ((EciesAeadDemParams) this.instance).hasAeadDem();
        }

        public KeyTemplate getAeadDem() {
            return ((EciesAeadDemParams) this.instance).getAeadDem();
        }

        public Builder setAeadDem(KeyTemplate keyTemplate) {
            copyOnWrite();
            ((EciesAeadDemParams) this.instance).setAeadDem(keyTemplate);
            return this;
        }

        public Builder setAeadDem(com.google.crypto.tink.proto.KeyTemplate.Builder builder) {
            copyOnWrite();
            ((EciesAeadDemParams) this.instance).setAeadDem(builder);
            return this;
        }

        public Builder mergeAeadDem(KeyTemplate keyTemplate) {
            copyOnWrite();
            ((EciesAeadDemParams) this.instance).mergeAeadDem(keyTemplate);
            return this;
        }

        public Builder clearAeadDem() {
            copyOnWrite();
            ((EciesAeadDemParams) this.instance).clearAeadDem();
            return this;
        }
    }

    private EciesAeadDemParams() {
    }

    public boolean hasAeadDem() {
        return this.aeadDem_ != null;
    }

    public KeyTemplate getAeadDem() {
        return this.aeadDem_ == null ? KeyTemplate.getDefaultInstance() : this.aeadDem_;
    }

    private void setAeadDem(KeyTemplate keyTemplate) {
        if (keyTemplate != null) {
            this.aeadDem_ = keyTemplate;
            return;
        }
        throw new NullPointerException();
    }

    private void setAeadDem(com.google.crypto.tink.proto.KeyTemplate.Builder builder) {
        this.aeadDem_ = (KeyTemplate) builder.build();
    }

    private void mergeAeadDem(KeyTemplate keyTemplate) {
        if (this.aeadDem_ == null || this.aeadDem_ == KeyTemplate.getDefaultInstance()) {
            this.aeadDem_ = keyTemplate;
        } else {
            this.aeadDem_ = (KeyTemplate) ((com.google.crypto.tink.proto.KeyTemplate.Builder) KeyTemplate.newBuilder(this.aeadDem_).mergeFrom(keyTemplate)).buildPartial();
        }
    }

    private void clearAeadDem() {
        this.aeadDem_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.aeadDem_ != null) {
            codedOutputStream.writeMessage(2, getAeadDem());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.aeadDem_ != null) {
            i = 0 + CodedOutputStream.computeMessageSize(2, getAeadDem());
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static EciesAeadDemParams parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EciesAeadDemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EciesAeadDemParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EciesAeadDemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EciesAeadDemParams parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EciesAeadDemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EciesAeadDemParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EciesAeadDemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EciesAeadDemParams parseFrom(InputStream inputStream) throws IOException {
        return (EciesAeadDemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesAeadDemParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadDemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesAeadDemParams parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EciesAeadDemParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesAeadDemParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadDemParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesAeadDemParams parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EciesAeadDemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EciesAeadDemParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadDemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EciesAeadDemParams eciesAeadDemParams) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(eciesAeadDemParams);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EciesAeadDemParams();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.aeadDem_ = (KeyTemplate) ((Visitor) obj).visitMessage(this.aeadDem_, ((EciesAeadDemParams) obj2).aeadDem_);
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
                                com.google.crypto.tink.proto.KeyTemplate.Builder builder = this.aeadDem_ != null ? (com.google.crypto.tink.proto.KeyTemplate.Builder) this.aeadDem_.toBuilder() : null;
                                this.aeadDem_ = (KeyTemplate) codedInputStream.readMessage(KeyTemplate.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.aeadDem_);
                                    this.aeadDem_ = (KeyTemplate) builder.buildPartial();
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
                    synchronized (EciesAeadDemParams.class) {
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

    public static EciesAeadDemParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EciesAeadDemParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
