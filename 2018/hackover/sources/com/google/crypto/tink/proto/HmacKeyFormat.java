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

public final class HmacKeyFormat extends GeneratedMessageLite<HmacKeyFormat, Builder> implements HmacKeyFormatOrBuilder {
    private static final HmacKeyFormat DEFAULT_INSTANCE = new HmacKeyFormat();
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    public static final int PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser<HmacKeyFormat> PARSER;
    private int keySize_;
    private HmacParams params_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<HmacKeyFormat, Builder> implements HmacKeyFormatOrBuilder {
        private Builder() {
            super(HmacKeyFormat.DEFAULT_INSTANCE);
        }

        public boolean hasParams() {
            return ((HmacKeyFormat) this.instance).hasParams();
        }

        public HmacParams getParams() {
            return ((HmacKeyFormat) this.instance).getParams();
        }

        public Builder setParams(HmacParams hmacParams) {
            copyOnWrite();
            ((HmacKeyFormat) this.instance).setParams(hmacParams);
            return this;
        }

        public Builder setParams(com.google.crypto.tink.proto.HmacParams.Builder builder) {
            copyOnWrite();
            ((HmacKeyFormat) this.instance).setParams(builder);
            return this;
        }

        public Builder mergeParams(HmacParams hmacParams) {
            copyOnWrite();
            ((HmacKeyFormat) this.instance).mergeParams(hmacParams);
            return this;
        }

        public Builder clearParams() {
            copyOnWrite();
            ((HmacKeyFormat) this.instance).clearParams();
            return this;
        }

        public int getKeySize() {
            return ((HmacKeyFormat) this.instance).getKeySize();
        }

        public Builder setKeySize(int i) {
            copyOnWrite();
            ((HmacKeyFormat) this.instance).setKeySize(i);
            return this;
        }

        public Builder clearKeySize() {
            copyOnWrite();
            ((HmacKeyFormat) this.instance).clearKeySize();
            return this;
        }
    }

    private HmacKeyFormat() {
    }

    public boolean hasParams() {
        return this.params_ != null;
    }

    public HmacParams getParams() {
        return this.params_ == null ? HmacParams.getDefaultInstance() : this.params_;
    }

    private void setParams(HmacParams hmacParams) {
        if (hmacParams != null) {
            this.params_ = hmacParams;
            return;
        }
        throw new NullPointerException();
    }

    private void setParams(com.google.crypto.tink.proto.HmacParams.Builder builder) {
        this.params_ = (HmacParams) builder.build();
    }

    private void mergeParams(HmacParams hmacParams) {
        if (this.params_ == null || this.params_ == HmacParams.getDefaultInstance()) {
            this.params_ = hmacParams;
        } else {
            this.params_ = (HmacParams) ((com.google.crypto.tink.proto.HmacParams.Builder) HmacParams.newBuilder(this.params_).mergeFrom(hmacParams)).buildPartial();
        }
    }

    private void clearParams() {
        this.params_ = null;
    }

    public int getKeySize() {
        return this.keySize_;
    }

    private void setKeySize(int i) {
        this.keySize_ = i;
    }

    private void clearKeySize() {
        this.keySize_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.params_ != null) {
            codedOutputStream.writeMessage(1, getParams());
        }
        if (this.keySize_ != 0) {
            codedOutputStream.writeUInt32(2, this.keySize_);
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
        if (this.keySize_ != 0) {
            i += CodedOutputStream.computeUInt32Size(2, this.keySize_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static HmacKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HmacKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static HmacKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HmacKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static HmacKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HmacKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static HmacKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HmacKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static HmacKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (HmacKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HmacKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HmacKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HmacKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HmacKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HmacKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HmacKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HmacKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HmacKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static HmacKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HmacKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HmacKeyFormat hmacKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(hmacKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new HmacKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                HmacKeyFormat hmacKeyFormat = (HmacKeyFormat) obj2;
                this.params_ = (HmacParams) visitor.visitMessage(this.params_, hmacKeyFormat.params_);
                boolean z2 = this.keySize_ != 0;
                int i = this.keySize_;
                if (hmacKeyFormat.keySize_ != 0) {
                    z = true;
                }
                this.keySize_ = visitor.visitInt(z2, i, z, hmacKeyFormat.keySize_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                com.google.crypto.tink.proto.HmacParams.Builder builder = this.params_ != null ? (com.google.crypto.tink.proto.HmacParams.Builder) this.params_.toBuilder() : null;
                                this.params_ = (HmacParams) codedInputStream.readMessage(HmacParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.params_);
                                    this.params_ = (HmacParams) builder.buildPartial();
                                }
                            } else if (readTag == 16) {
                                this.keySize_ = codedInputStream.readUInt32();
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
                    synchronized (HmacKeyFormat.class) {
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

    public static HmacKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HmacKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
