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

public final class AesCtrHmacStreamingParams extends GeneratedMessageLite<AesCtrHmacStreamingParams, Builder> implements AesCtrHmacStreamingParamsOrBuilder {
    public static final int CIPHERTEXT_SEGMENT_SIZE_FIELD_NUMBER = 1;
    private static final AesCtrHmacStreamingParams DEFAULT_INSTANCE = new AesCtrHmacStreamingParams();
    public static final int DERIVED_KEY_SIZE_FIELD_NUMBER = 2;
    public static final int HKDF_HASH_TYPE_FIELD_NUMBER = 3;
    public static final int HMAC_PARAMS_FIELD_NUMBER = 4;
    private static volatile Parser<AesCtrHmacStreamingParams> PARSER;
    private int ciphertextSegmentSize_;
    private int derivedKeySize_;
    private int hkdfHashType_;
    private HmacParams hmacParams_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesCtrHmacStreamingParams, Builder> implements AesCtrHmacStreamingParamsOrBuilder {
        private Builder() {
            super(AesCtrHmacStreamingParams.DEFAULT_INSTANCE);
        }

        public int getCiphertextSegmentSize() {
            return ((AesCtrHmacStreamingParams) this.instance).getCiphertextSegmentSize();
        }

        public Builder setCiphertextSegmentSize(int i) {
            copyOnWrite();
            ((AesCtrHmacStreamingParams) this.instance).setCiphertextSegmentSize(i);
            return this;
        }

        public Builder clearCiphertextSegmentSize() {
            copyOnWrite();
            ((AesCtrHmacStreamingParams) this.instance).clearCiphertextSegmentSize();
            return this;
        }

        public int getDerivedKeySize() {
            return ((AesCtrHmacStreamingParams) this.instance).getDerivedKeySize();
        }

        public Builder setDerivedKeySize(int i) {
            copyOnWrite();
            ((AesCtrHmacStreamingParams) this.instance).setDerivedKeySize(i);
            return this;
        }

        public Builder clearDerivedKeySize() {
            copyOnWrite();
            ((AesCtrHmacStreamingParams) this.instance).clearDerivedKeySize();
            return this;
        }

        public int getHkdfHashTypeValue() {
            return ((AesCtrHmacStreamingParams) this.instance).getHkdfHashTypeValue();
        }

        public Builder setHkdfHashTypeValue(int i) {
            copyOnWrite();
            ((AesCtrHmacStreamingParams) this.instance).setHkdfHashTypeValue(i);
            return this;
        }

        public HashType getHkdfHashType() {
            return ((AesCtrHmacStreamingParams) this.instance).getHkdfHashType();
        }

        public Builder setHkdfHashType(HashType hashType) {
            copyOnWrite();
            ((AesCtrHmacStreamingParams) this.instance).setHkdfHashType(hashType);
            return this;
        }

        public Builder clearHkdfHashType() {
            copyOnWrite();
            ((AesCtrHmacStreamingParams) this.instance).clearHkdfHashType();
            return this;
        }

        public boolean hasHmacParams() {
            return ((AesCtrHmacStreamingParams) this.instance).hasHmacParams();
        }

        public HmacParams getHmacParams() {
            return ((AesCtrHmacStreamingParams) this.instance).getHmacParams();
        }

        public Builder setHmacParams(HmacParams hmacParams) {
            copyOnWrite();
            ((AesCtrHmacStreamingParams) this.instance).setHmacParams(hmacParams);
            return this;
        }

        public Builder setHmacParams(com.google.crypto.tink.proto.HmacParams.Builder builder) {
            copyOnWrite();
            ((AesCtrHmacStreamingParams) this.instance).setHmacParams(builder);
            return this;
        }

        public Builder mergeHmacParams(HmacParams hmacParams) {
            copyOnWrite();
            ((AesCtrHmacStreamingParams) this.instance).mergeHmacParams(hmacParams);
            return this;
        }

        public Builder clearHmacParams() {
            copyOnWrite();
            ((AesCtrHmacStreamingParams) this.instance).clearHmacParams();
            return this;
        }
    }

    private AesCtrHmacStreamingParams() {
    }

    public int getCiphertextSegmentSize() {
        return this.ciphertextSegmentSize_;
    }

    private void setCiphertextSegmentSize(int i) {
        this.ciphertextSegmentSize_ = i;
    }

    private void clearCiphertextSegmentSize() {
        this.ciphertextSegmentSize_ = 0;
    }

    public int getDerivedKeySize() {
        return this.derivedKeySize_;
    }

    private void setDerivedKeySize(int i) {
        this.derivedKeySize_ = i;
    }

    private void clearDerivedKeySize() {
        this.derivedKeySize_ = 0;
    }

    public int getHkdfHashTypeValue() {
        return this.hkdfHashType_;
    }

    public HashType getHkdfHashType() {
        HashType forNumber = HashType.forNumber(this.hkdfHashType_);
        return forNumber == null ? HashType.UNRECOGNIZED : forNumber;
    }

    private void setHkdfHashTypeValue(int i) {
        this.hkdfHashType_ = i;
    }

    private void setHkdfHashType(HashType hashType) {
        if (hashType != null) {
            this.hkdfHashType_ = hashType.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    private void clearHkdfHashType() {
        this.hkdfHashType_ = 0;
    }

    public boolean hasHmacParams() {
        return this.hmacParams_ != null;
    }

    public HmacParams getHmacParams() {
        return this.hmacParams_ == null ? HmacParams.getDefaultInstance() : this.hmacParams_;
    }

    private void setHmacParams(HmacParams hmacParams) {
        if (hmacParams != null) {
            this.hmacParams_ = hmacParams;
            return;
        }
        throw new NullPointerException();
    }

    private void setHmacParams(com.google.crypto.tink.proto.HmacParams.Builder builder) {
        this.hmacParams_ = (HmacParams) builder.build();
    }

    private void mergeHmacParams(HmacParams hmacParams) {
        if (this.hmacParams_ == null || this.hmacParams_ == HmacParams.getDefaultInstance()) {
            this.hmacParams_ = hmacParams;
        } else {
            this.hmacParams_ = (HmacParams) ((com.google.crypto.tink.proto.HmacParams.Builder) HmacParams.newBuilder(this.hmacParams_).mergeFrom(hmacParams)).buildPartial();
        }
    }

    private void clearHmacParams() {
        this.hmacParams_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.ciphertextSegmentSize_ != 0) {
            codedOutputStream.writeUInt32(1, this.ciphertextSegmentSize_);
        }
        if (this.derivedKeySize_ != 0) {
            codedOutputStream.writeUInt32(2, this.derivedKeySize_);
        }
        if (this.hkdfHashType_ != HashType.UNKNOWN_HASH.getNumber()) {
            codedOutputStream.writeEnum(3, this.hkdfHashType_);
        }
        if (this.hmacParams_ != null) {
            codedOutputStream.writeMessage(4, getHmacParams());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.ciphertextSegmentSize_ != 0) {
            i = 0 + CodedOutputStream.computeUInt32Size(1, this.ciphertextSegmentSize_);
        }
        if (this.derivedKeySize_ != 0) {
            i += CodedOutputStream.computeUInt32Size(2, this.derivedKeySize_);
        }
        if (this.hkdfHashType_ != HashType.UNKNOWN_HASH.getNumber()) {
            i += CodedOutputStream.computeEnumSize(3, this.hkdfHashType_);
        }
        if (this.hmacParams_ != null) {
            i += CodedOutputStream.computeMessageSize(4, getHmacParams());
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static AesCtrHmacStreamingParams parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesCtrHmacStreamingParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingParams parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesCtrHmacStreamingParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesCtrHmacStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingParams parseFrom(InputStream inputStream) throws IOException {
        return (AesCtrHmacStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrHmacStreamingParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingParams parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesCtrHmacStreamingParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesCtrHmacStreamingParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacStreamingParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesCtrHmacStreamingParams parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesCtrHmacStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesCtrHmacStreamingParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesCtrHmacStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesCtrHmacStreamingParams aesCtrHmacStreamingParams) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesCtrHmacStreamingParams);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesCtrHmacStreamingParams();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesCtrHmacStreamingParams aesCtrHmacStreamingParams = (AesCtrHmacStreamingParams) obj2;
                this.ciphertextSegmentSize_ = visitor.visitInt(this.ciphertextSegmentSize_ != 0, this.ciphertextSegmentSize_, aesCtrHmacStreamingParams.ciphertextSegmentSize_ != 0, aesCtrHmacStreamingParams.ciphertextSegmentSize_);
                this.derivedKeySize_ = visitor.visitInt(this.derivedKeySize_ != 0, this.derivedKeySize_, aesCtrHmacStreamingParams.derivedKeySize_ != 0, aesCtrHmacStreamingParams.derivedKeySize_);
                boolean z2 = this.hkdfHashType_ != 0;
                int i = this.hkdfHashType_;
                if (aesCtrHmacStreamingParams.hkdfHashType_ != 0) {
                    z = true;
                }
                this.hkdfHashType_ = visitor.visitInt(z2, i, z, aesCtrHmacStreamingParams.hkdfHashType_);
                this.hmacParams_ = (HmacParams) visitor.visitMessage(this.hmacParams_, aesCtrHmacStreamingParams.hmacParams_);
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
                                this.ciphertextSegmentSize_ = codedInputStream.readUInt32();
                            } else if (readTag == 16) {
                                this.derivedKeySize_ = codedInputStream.readUInt32();
                            } else if (readTag == 24) {
                                this.hkdfHashType_ = codedInputStream.readEnum();
                            } else if (readTag == 34) {
                                com.google.crypto.tink.proto.HmacParams.Builder builder = this.hmacParams_ != null ? (com.google.crypto.tink.proto.HmacParams.Builder) this.hmacParams_.toBuilder() : null;
                                this.hmacParams_ = (HmacParams) codedInputStream.readMessage(HmacParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.hmacParams_);
                                    this.hmacParams_ = (HmacParams) builder.buildPartial();
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
                    synchronized (AesCtrHmacStreamingParams.class) {
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

    public static AesCtrHmacStreamingParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesCtrHmacStreamingParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
