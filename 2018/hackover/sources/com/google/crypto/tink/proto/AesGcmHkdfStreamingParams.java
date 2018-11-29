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

public final class AesGcmHkdfStreamingParams extends GeneratedMessageLite<AesGcmHkdfStreamingParams, Builder> implements AesGcmHkdfStreamingParamsOrBuilder {
    public static final int CIPHERTEXT_SEGMENT_SIZE_FIELD_NUMBER = 1;
    private static final AesGcmHkdfStreamingParams DEFAULT_INSTANCE = new AesGcmHkdfStreamingParams();
    public static final int DERIVED_KEY_SIZE_FIELD_NUMBER = 2;
    public static final int HKDF_HASH_TYPE_FIELD_NUMBER = 3;
    private static volatile Parser<AesGcmHkdfStreamingParams> PARSER;
    private int ciphertextSegmentSize_;
    private int derivedKeySize_;
    private int hkdfHashType_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesGcmHkdfStreamingParams, Builder> implements AesGcmHkdfStreamingParamsOrBuilder {
        private Builder() {
            super(AesGcmHkdfStreamingParams.DEFAULT_INSTANCE);
        }

        public int getCiphertextSegmentSize() {
            return ((AesGcmHkdfStreamingParams) this.instance).getCiphertextSegmentSize();
        }

        public Builder setCiphertextSegmentSize(int i) {
            copyOnWrite();
            ((AesGcmHkdfStreamingParams) this.instance).setCiphertextSegmentSize(i);
            return this;
        }

        public Builder clearCiphertextSegmentSize() {
            copyOnWrite();
            ((AesGcmHkdfStreamingParams) this.instance).clearCiphertextSegmentSize();
            return this;
        }

        public int getDerivedKeySize() {
            return ((AesGcmHkdfStreamingParams) this.instance).getDerivedKeySize();
        }

        public Builder setDerivedKeySize(int i) {
            copyOnWrite();
            ((AesGcmHkdfStreamingParams) this.instance).setDerivedKeySize(i);
            return this;
        }

        public Builder clearDerivedKeySize() {
            copyOnWrite();
            ((AesGcmHkdfStreamingParams) this.instance).clearDerivedKeySize();
            return this;
        }

        public int getHkdfHashTypeValue() {
            return ((AesGcmHkdfStreamingParams) this.instance).getHkdfHashTypeValue();
        }

        public Builder setHkdfHashTypeValue(int i) {
            copyOnWrite();
            ((AesGcmHkdfStreamingParams) this.instance).setHkdfHashTypeValue(i);
            return this;
        }

        public HashType getHkdfHashType() {
            return ((AesGcmHkdfStreamingParams) this.instance).getHkdfHashType();
        }

        public Builder setHkdfHashType(HashType hashType) {
            copyOnWrite();
            ((AesGcmHkdfStreamingParams) this.instance).setHkdfHashType(hashType);
            return this;
        }

        public Builder clearHkdfHashType() {
            copyOnWrite();
            ((AesGcmHkdfStreamingParams) this.instance).clearHkdfHashType();
            return this;
        }
    }

    private AesGcmHkdfStreamingParams() {
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
        this.memoizedSerializedSize = i;
        return i;
    }

    public static AesGcmHkdfStreamingParams parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesGcmHkdfStreamingParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingParams parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesGcmHkdfStreamingParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesGcmHkdfStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingParams parseFrom(InputStream inputStream) throws IOException {
        return (AesGcmHkdfStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesGcmHkdfStreamingParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmHkdfStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingParams parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesGcmHkdfStreamingParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesGcmHkdfStreamingParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmHkdfStreamingParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesGcmHkdfStreamingParams parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesGcmHkdfStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesGcmHkdfStreamingParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmHkdfStreamingParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesGcmHkdfStreamingParams);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesGcmHkdfStreamingParams();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesGcmHkdfStreamingParams aesGcmHkdfStreamingParams = (AesGcmHkdfStreamingParams) obj2;
                this.ciphertextSegmentSize_ = visitor.visitInt(this.ciphertextSegmentSize_ != 0, this.ciphertextSegmentSize_, aesGcmHkdfStreamingParams.ciphertextSegmentSize_ != 0, aesGcmHkdfStreamingParams.ciphertextSegmentSize_);
                this.derivedKeySize_ = visitor.visitInt(this.derivedKeySize_ != 0, this.derivedKeySize_, aesGcmHkdfStreamingParams.derivedKeySize_ != 0, aesGcmHkdfStreamingParams.derivedKeySize_);
                boolean z2 = this.hkdfHashType_ != 0;
                int i = this.hkdfHashType_;
                if (aesGcmHkdfStreamingParams.hkdfHashType_ != 0) {
                    z = true;
                }
                this.hkdfHashType_ = visitor.visitInt(z2, i, z, aesGcmHkdfStreamingParams.hkdfHashType_);
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
                    synchronized (AesGcmHkdfStreamingParams.class) {
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

    public static AesGcmHkdfStreamingParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesGcmHkdfStreamingParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
