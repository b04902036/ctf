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

public final class AesCtrHmacAeadKeyFormat extends GeneratedMessageLite<AesCtrHmacAeadKeyFormat, Builder> implements AesCtrHmacAeadKeyFormatOrBuilder {
    public static final int AES_CTR_KEY_FORMAT_FIELD_NUMBER = 1;
    private static final AesCtrHmacAeadKeyFormat DEFAULT_INSTANCE = new AesCtrHmacAeadKeyFormat();
    public static final int HMAC_KEY_FORMAT_FIELD_NUMBER = 2;
    private static volatile Parser<AesCtrHmacAeadKeyFormat> PARSER;
    private AesCtrKeyFormat aesCtrKeyFormat_;
    private HmacKeyFormat hmacKeyFormat_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesCtrHmacAeadKeyFormat, Builder> implements AesCtrHmacAeadKeyFormatOrBuilder {
        private Builder() {
            super(AesCtrHmacAeadKeyFormat.DEFAULT_INSTANCE);
        }

        public boolean hasAesCtrKeyFormat() {
            return ((AesCtrHmacAeadKeyFormat) this.instance).hasAesCtrKeyFormat();
        }

        public AesCtrKeyFormat getAesCtrKeyFormat() {
            return ((AesCtrHmacAeadKeyFormat) this.instance).getAesCtrKeyFormat();
        }

        public Builder setAesCtrKeyFormat(AesCtrKeyFormat aesCtrKeyFormat) {
            copyOnWrite();
            ((AesCtrHmacAeadKeyFormat) this.instance).setAesCtrKeyFormat(aesCtrKeyFormat);
            return this;
        }

        public Builder setAesCtrKeyFormat(com.google.crypto.tink.proto.AesCtrKeyFormat.Builder builder) {
            copyOnWrite();
            ((AesCtrHmacAeadKeyFormat) this.instance).setAesCtrKeyFormat(builder);
            return this;
        }

        public Builder mergeAesCtrKeyFormat(AesCtrKeyFormat aesCtrKeyFormat) {
            copyOnWrite();
            ((AesCtrHmacAeadKeyFormat) this.instance).mergeAesCtrKeyFormat(aesCtrKeyFormat);
            return this;
        }

        public Builder clearAesCtrKeyFormat() {
            copyOnWrite();
            ((AesCtrHmacAeadKeyFormat) this.instance).clearAesCtrKeyFormat();
            return this;
        }

        public boolean hasHmacKeyFormat() {
            return ((AesCtrHmacAeadKeyFormat) this.instance).hasHmacKeyFormat();
        }

        public HmacKeyFormat getHmacKeyFormat() {
            return ((AesCtrHmacAeadKeyFormat) this.instance).getHmacKeyFormat();
        }

        public Builder setHmacKeyFormat(HmacKeyFormat hmacKeyFormat) {
            copyOnWrite();
            ((AesCtrHmacAeadKeyFormat) this.instance).setHmacKeyFormat(hmacKeyFormat);
            return this;
        }

        public Builder setHmacKeyFormat(com.google.crypto.tink.proto.HmacKeyFormat.Builder builder) {
            copyOnWrite();
            ((AesCtrHmacAeadKeyFormat) this.instance).setHmacKeyFormat(builder);
            return this;
        }

        public Builder mergeHmacKeyFormat(HmacKeyFormat hmacKeyFormat) {
            copyOnWrite();
            ((AesCtrHmacAeadKeyFormat) this.instance).mergeHmacKeyFormat(hmacKeyFormat);
            return this;
        }

        public Builder clearHmacKeyFormat() {
            copyOnWrite();
            ((AesCtrHmacAeadKeyFormat) this.instance).clearHmacKeyFormat();
            return this;
        }
    }

    private AesCtrHmacAeadKeyFormat() {
    }

    public boolean hasAesCtrKeyFormat() {
        return this.aesCtrKeyFormat_ != null;
    }

    public AesCtrKeyFormat getAesCtrKeyFormat() {
        return this.aesCtrKeyFormat_ == null ? AesCtrKeyFormat.getDefaultInstance() : this.aesCtrKeyFormat_;
    }

    private void setAesCtrKeyFormat(AesCtrKeyFormat aesCtrKeyFormat) {
        if (aesCtrKeyFormat != null) {
            this.aesCtrKeyFormat_ = aesCtrKeyFormat;
            return;
        }
        throw new NullPointerException();
    }

    private void setAesCtrKeyFormat(com.google.crypto.tink.proto.AesCtrKeyFormat.Builder builder) {
        this.aesCtrKeyFormat_ = (AesCtrKeyFormat) builder.build();
    }

    private void mergeAesCtrKeyFormat(AesCtrKeyFormat aesCtrKeyFormat) {
        if (this.aesCtrKeyFormat_ == null || this.aesCtrKeyFormat_ == AesCtrKeyFormat.getDefaultInstance()) {
            this.aesCtrKeyFormat_ = aesCtrKeyFormat;
        } else {
            this.aesCtrKeyFormat_ = (AesCtrKeyFormat) ((com.google.crypto.tink.proto.AesCtrKeyFormat.Builder) AesCtrKeyFormat.newBuilder(this.aesCtrKeyFormat_).mergeFrom(aesCtrKeyFormat)).buildPartial();
        }
    }

    private void clearAesCtrKeyFormat() {
        this.aesCtrKeyFormat_ = null;
    }

    public boolean hasHmacKeyFormat() {
        return this.hmacKeyFormat_ != null;
    }

    public HmacKeyFormat getHmacKeyFormat() {
        return this.hmacKeyFormat_ == null ? HmacKeyFormat.getDefaultInstance() : this.hmacKeyFormat_;
    }

    private void setHmacKeyFormat(HmacKeyFormat hmacKeyFormat) {
        if (hmacKeyFormat != null) {
            this.hmacKeyFormat_ = hmacKeyFormat;
            return;
        }
        throw new NullPointerException();
    }

    private void setHmacKeyFormat(com.google.crypto.tink.proto.HmacKeyFormat.Builder builder) {
        this.hmacKeyFormat_ = (HmacKeyFormat) builder.build();
    }

    private void mergeHmacKeyFormat(HmacKeyFormat hmacKeyFormat) {
        if (this.hmacKeyFormat_ == null || this.hmacKeyFormat_ == HmacKeyFormat.getDefaultInstance()) {
            this.hmacKeyFormat_ = hmacKeyFormat;
        } else {
            this.hmacKeyFormat_ = (HmacKeyFormat) ((com.google.crypto.tink.proto.HmacKeyFormat.Builder) HmacKeyFormat.newBuilder(this.hmacKeyFormat_).mergeFrom(hmacKeyFormat)).buildPartial();
        }
    }

    private void clearHmacKeyFormat() {
        this.hmacKeyFormat_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.aesCtrKeyFormat_ != null) {
            codedOutputStream.writeMessage(1, getAesCtrKeyFormat());
        }
        if (this.hmacKeyFormat_ != null) {
            codedOutputStream.writeMessage(2, getHmacKeyFormat());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.aesCtrKeyFormat_ != null) {
            i = 0 + CodedOutputStream.computeMessageSize(1, getAesCtrKeyFormat());
        }
        if (this.hmacKeyFormat_ != null) {
            i += CodedOutputStream.computeMessageSize(2, getHmacKeyFormat());
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrHmacAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (AesCtrHmacAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrHmacAeadKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesCtrHmacAeadKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrHmacAeadKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacAeadKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesCtrHmacAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesCtrHmacAeadKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesCtrHmacAeadKeyFormat aesCtrHmacAeadKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesCtrHmacAeadKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesCtrHmacAeadKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesCtrHmacAeadKeyFormat aesCtrHmacAeadKeyFormat = (AesCtrHmacAeadKeyFormat) obj2;
                this.aesCtrKeyFormat_ = (AesCtrKeyFormat) visitor.visitMessage(this.aesCtrKeyFormat_, aesCtrHmacAeadKeyFormat.aesCtrKeyFormat_);
                this.hmacKeyFormat_ = (HmacKeyFormat) visitor.visitMessage(this.hmacKeyFormat_, aesCtrHmacAeadKeyFormat.hmacKeyFormat_);
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
                                com.google.crypto.tink.proto.AesCtrKeyFormat.Builder builder = this.aesCtrKeyFormat_ != null ? (com.google.crypto.tink.proto.AesCtrKeyFormat.Builder) this.aesCtrKeyFormat_.toBuilder() : null;
                                this.aesCtrKeyFormat_ = (AesCtrKeyFormat) codedInputStream.readMessage(AesCtrKeyFormat.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.aesCtrKeyFormat_);
                                    this.aesCtrKeyFormat_ = (AesCtrKeyFormat) builder.buildPartial();
                                }
                            } else if (readTag == 18) {
                                com.google.crypto.tink.proto.HmacKeyFormat.Builder builder2 = this.hmacKeyFormat_ != null ? (com.google.crypto.tink.proto.HmacKeyFormat.Builder) this.hmacKeyFormat_.toBuilder() : null;
                                this.hmacKeyFormat_ = (HmacKeyFormat) codedInputStream.readMessage(HmacKeyFormat.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.hmacKeyFormat_);
                                    this.hmacKeyFormat_ = (HmacKeyFormat) builder2.buildPartial();
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
                    synchronized (AesCtrHmacAeadKeyFormat.class) {
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

    public static AesCtrHmacAeadKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesCtrHmacAeadKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
