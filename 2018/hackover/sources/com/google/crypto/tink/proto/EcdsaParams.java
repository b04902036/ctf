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

public final class EcdsaParams extends GeneratedMessageLite<EcdsaParams, Builder> implements EcdsaParamsOrBuilder {
    public static final int CURVE_FIELD_NUMBER = 2;
    private static final EcdsaParams DEFAULT_INSTANCE = new EcdsaParams();
    public static final int ENCODING_FIELD_NUMBER = 3;
    public static final int HASH_TYPE_FIELD_NUMBER = 1;
    private static volatile Parser<EcdsaParams> PARSER;
    private int curve_;
    private int encoding_;
    private int hashType_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EcdsaParams, Builder> implements EcdsaParamsOrBuilder {
        private Builder() {
            super(EcdsaParams.DEFAULT_INSTANCE);
        }

        public int getHashTypeValue() {
            return ((EcdsaParams) this.instance).getHashTypeValue();
        }

        public Builder setHashTypeValue(int i) {
            copyOnWrite();
            ((EcdsaParams) this.instance).setHashTypeValue(i);
            return this;
        }

        public HashType getHashType() {
            return ((EcdsaParams) this.instance).getHashType();
        }

        public Builder setHashType(HashType hashType) {
            copyOnWrite();
            ((EcdsaParams) this.instance).setHashType(hashType);
            return this;
        }

        public Builder clearHashType() {
            copyOnWrite();
            ((EcdsaParams) this.instance).clearHashType();
            return this;
        }

        public int getCurveValue() {
            return ((EcdsaParams) this.instance).getCurveValue();
        }

        public Builder setCurveValue(int i) {
            copyOnWrite();
            ((EcdsaParams) this.instance).setCurveValue(i);
            return this;
        }

        public EllipticCurveType getCurve() {
            return ((EcdsaParams) this.instance).getCurve();
        }

        public Builder setCurve(EllipticCurveType ellipticCurveType) {
            copyOnWrite();
            ((EcdsaParams) this.instance).setCurve(ellipticCurveType);
            return this;
        }

        public Builder clearCurve() {
            copyOnWrite();
            ((EcdsaParams) this.instance).clearCurve();
            return this;
        }

        public int getEncodingValue() {
            return ((EcdsaParams) this.instance).getEncodingValue();
        }

        public Builder setEncodingValue(int i) {
            copyOnWrite();
            ((EcdsaParams) this.instance).setEncodingValue(i);
            return this;
        }

        public EcdsaSignatureEncoding getEncoding() {
            return ((EcdsaParams) this.instance).getEncoding();
        }

        public Builder setEncoding(EcdsaSignatureEncoding ecdsaSignatureEncoding) {
            copyOnWrite();
            ((EcdsaParams) this.instance).setEncoding(ecdsaSignatureEncoding);
            return this;
        }

        public Builder clearEncoding() {
            copyOnWrite();
            ((EcdsaParams) this.instance).clearEncoding();
            return this;
        }
    }

    private EcdsaParams() {
    }

    public int getHashTypeValue() {
        return this.hashType_;
    }

    public HashType getHashType() {
        HashType forNumber = HashType.forNumber(this.hashType_);
        return forNumber == null ? HashType.UNRECOGNIZED : forNumber;
    }

    private void setHashTypeValue(int i) {
        this.hashType_ = i;
    }

    private void setHashType(HashType hashType) {
        if (hashType != null) {
            this.hashType_ = hashType.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    private void clearHashType() {
        this.hashType_ = 0;
    }

    public int getCurveValue() {
        return this.curve_;
    }

    public EllipticCurveType getCurve() {
        EllipticCurveType forNumber = EllipticCurveType.forNumber(this.curve_);
        return forNumber == null ? EllipticCurveType.UNRECOGNIZED : forNumber;
    }

    private void setCurveValue(int i) {
        this.curve_ = i;
    }

    private void setCurve(EllipticCurveType ellipticCurveType) {
        if (ellipticCurveType != null) {
            this.curve_ = ellipticCurveType.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    private void clearCurve() {
        this.curve_ = 0;
    }

    public int getEncodingValue() {
        return this.encoding_;
    }

    public EcdsaSignatureEncoding getEncoding() {
        EcdsaSignatureEncoding forNumber = EcdsaSignatureEncoding.forNumber(this.encoding_);
        return forNumber == null ? EcdsaSignatureEncoding.UNRECOGNIZED : forNumber;
    }

    private void setEncodingValue(int i) {
        this.encoding_ = i;
    }

    private void setEncoding(EcdsaSignatureEncoding ecdsaSignatureEncoding) {
        if (ecdsaSignatureEncoding != null) {
            this.encoding_ = ecdsaSignatureEncoding.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    private void clearEncoding() {
        this.encoding_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.hashType_ != HashType.UNKNOWN_HASH.getNumber()) {
            codedOutputStream.writeEnum(1, this.hashType_);
        }
        if (this.curve_ != EllipticCurveType.UNKNOWN_CURVE.getNumber()) {
            codedOutputStream.writeEnum(2, this.curve_);
        }
        if (this.encoding_ != EcdsaSignatureEncoding.UNKNOWN_ENCODING.getNumber()) {
            codedOutputStream.writeEnum(3, this.encoding_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.hashType_ != HashType.UNKNOWN_HASH.getNumber()) {
            i = 0 + CodedOutputStream.computeEnumSize(1, this.hashType_);
        }
        if (this.curve_ != EllipticCurveType.UNKNOWN_CURVE.getNumber()) {
            i += CodedOutputStream.computeEnumSize(2, this.curve_);
        }
        if (this.encoding_ != EcdsaSignatureEncoding.UNKNOWN_ENCODING.getNumber()) {
            i += CodedOutputStream.computeEnumSize(3, this.encoding_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static EcdsaParams parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EcdsaParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EcdsaParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EcdsaParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EcdsaParams parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EcdsaParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EcdsaParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EcdsaParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EcdsaParams parseFrom(InputStream inputStream) throws IOException {
        return (EcdsaParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EcdsaParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EcdsaParams parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EcdsaParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EcdsaParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EcdsaParams parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EcdsaParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EcdsaParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EcdsaParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EcdsaParams ecdsaParams) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(ecdsaParams);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EcdsaParams();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                EcdsaParams ecdsaParams = (EcdsaParams) obj2;
                this.hashType_ = visitor.visitInt(this.hashType_ != 0, this.hashType_, ecdsaParams.hashType_ != 0, ecdsaParams.hashType_);
                this.curve_ = visitor.visitInt(this.curve_ != 0, this.curve_, ecdsaParams.curve_ != 0, ecdsaParams.curve_);
                boolean z2 = this.encoding_ != 0;
                int i = this.encoding_;
                if (ecdsaParams.encoding_ != 0) {
                    z = true;
                }
                this.encoding_ = visitor.visitInt(z2, i, z, ecdsaParams.encoding_);
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
                                this.hashType_ = codedInputStream.readEnum();
                            } else if (readTag == 16) {
                                this.curve_ = codedInputStream.readEnum();
                            } else if (readTag == 24) {
                                this.encoding_ = codedInputStream.readEnum();
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
                    synchronized (EcdsaParams.class) {
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

    public static EcdsaParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EcdsaParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
