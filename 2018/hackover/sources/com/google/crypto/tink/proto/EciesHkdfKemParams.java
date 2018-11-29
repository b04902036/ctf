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

public final class EciesHkdfKemParams extends GeneratedMessageLite<EciesHkdfKemParams, Builder> implements EciesHkdfKemParamsOrBuilder {
    public static final int CURVE_TYPE_FIELD_NUMBER = 1;
    private static final EciesHkdfKemParams DEFAULT_INSTANCE = new EciesHkdfKemParams();
    public static final int HKDF_HASH_TYPE_FIELD_NUMBER = 2;
    public static final int HKDF_SALT_FIELD_NUMBER = 11;
    private static volatile Parser<EciesHkdfKemParams> PARSER;
    private int curveType_;
    private int hkdfHashType_;
    private ByteString hkdfSalt_ = ByteString.EMPTY;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EciesHkdfKemParams, Builder> implements EciesHkdfKemParamsOrBuilder {
        private Builder() {
            super(EciesHkdfKemParams.DEFAULT_INSTANCE);
        }

        public int getCurveTypeValue() {
            return ((EciesHkdfKemParams) this.instance).getCurveTypeValue();
        }

        public Builder setCurveTypeValue(int i) {
            copyOnWrite();
            ((EciesHkdfKemParams) this.instance).setCurveTypeValue(i);
            return this;
        }

        public EllipticCurveType getCurveType() {
            return ((EciesHkdfKemParams) this.instance).getCurveType();
        }

        public Builder setCurveType(EllipticCurveType ellipticCurveType) {
            copyOnWrite();
            ((EciesHkdfKemParams) this.instance).setCurveType(ellipticCurveType);
            return this;
        }

        public Builder clearCurveType() {
            copyOnWrite();
            ((EciesHkdfKemParams) this.instance).clearCurveType();
            return this;
        }

        public int getHkdfHashTypeValue() {
            return ((EciesHkdfKemParams) this.instance).getHkdfHashTypeValue();
        }

        public Builder setHkdfHashTypeValue(int i) {
            copyOnWrite();
            ((EciesHkdfKemParams) this.instance).setHkdfHashTypeValue(i);
            return this;
        }

        public HashType getHkdfHashType() {
            return ((EciesHkdfKemParams) this.instance).getHkdfHashType();
        }

        public Builder setHkdfHashType(HashType hashType) {
            copyOnWrite();
            ((EciesHkdfKemParams) this.instance).setHkdfHashType(hashType);
            return this;
        }

        public Builder clearHkdfHashType() {
            copyOnWrite();
            ((EciesHkdfKemParams) this.instance).clearHkdfHashType();
            return this;
        }

        public ByteString getHkdfSalt() {
            return ((EciesHkdfKemParams) this.instance).getHkdfSalt();
        }

        public Builder setHkdfSalt(ByteString byteString) {
            copyOnWrite();
            ((EciesHkdfKemParams) this.instance).setHkdfSalt(byteString);
            return this;
        }

        public Builder clearHkdfSalt() {
            copyOnWrite();
            ((EciesHkdfKemParams) this.instance).clearHkdfSalt();
            return this;
        }
    }

    private EciesHkdfKemParams() {
    }

    public int getCurveTypeValue() {
        return this.curveType_;
    }

    public EllipticCurveType getCurveType() {
        EllipticCurveType forNumber = EllipticCurveType.forNumber(this.curveType_);
        return forNumber == null ? EllipticCurveType.UNRECOGNIZED : forNumber;
    }

    private void setCurveTypeValue(int i) {
        this.curveType_ = i;
    }

    private void setCurveType(EllipticCurveType ellipticCurveType) {
        if (ellipticCurveType != null) {
            this.curveType_ = ellipticCurveType.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    private void clearCurveType() {
        this.curveType_ = 0;
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

    public ByteString getHkdfSalt() {
        return this.hkdfSalt_;
    }

    private void setHkdfSalt(ByteString byteString) {
        if (byteString != null) {
            this.hkdfSalt_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    private void clearHkdfSalt() {
        this.hkdfSalt_ = getDefaultInstance().getHkdfSalt();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.curveType_ != EllipticCurveType.UNKNOWN_CURVE.getNumber()) {
            codedOutputStream.writeEnum(1, this.curveType_);
        }
        if (this.hkdfHashType_ != HashType.UNKNOWN_HASH.getNumber()) {
            codedOutputStream.writeEnum(2, this.hkdfHashType_);
        }
        if (!this.hkdfSalt_.isEmpty()) {
            codedOutputStream.writeBytes(11, this.hkdfSalt_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.curveType_ != EllipticCurveType.UNKNOWN_CURVE.getNumber()) {
            i = 0 + CodedOutputStream.computeEnumSize(1, this.curveType_);
        }
        if (this.hkdfHashType_ != HashType.UNKNOWN_HASH.getNumber()) {
            i += CodedOutputStream.computeEnumSize(2, this.hkdfHashType_);
        }
        if (!this.hkdfSalt_.isEmpty()) {
            i += CodedOutputStream.computeBytesSize(11, this.hkdfSalt_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static EciesHkdfKemParams parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EciesHkdfKemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EciesHkdfKemParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EciesHkdfKemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EciesHkdfKemParams parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EciesHkdfKemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EciesHkdfKemParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EciesHkdfKemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EciesHkdfKemParams parseFrom(InputStream inputStream) throws IOException {
        return (EciesHkdfKemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesHkdfKemParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesHkdfKemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesHkdfKemParams parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EciesHkdfKemParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesHkdfKemParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesHkdfKemParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesHkdfKemParams parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EciesHkdfKemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EciesHkdfKemParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesHkdfKemParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EciesHkdfKemParams eciesHkdfKemParams) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(eciesHkdfKemParams);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EciesHkdfKemParams();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                EciesHkdfKemParams eciesHkdfKemParams = (EciesHkdfKemParams) obj2;
                this.curveType_ = visitor.visitInt(this.curveType_ != 0, this.curveType_, eciesHkdfKemParams.curveType_ != 0, eciesHkdfKemParams.curveType_);
                this.hkdfHashType_ = visitor.visitInt(this.hkdfHashType_ != 0, this.hkdfHashType_, eciesHkdfKemParams.hkdfHashType_ != 0, eciesHkdfKemParams.hkdfHashType_);
                boolean z2 = this.hkdfSalt_ != ByteString.EMPTY;
                ByteString byteString = this.hkdfSalt_;
                if (eciesHkdfKemParams.hkdfSalt_ != ByteString.EMPTY) {
                    z = true;
                }
                this.hkdfSalt_ = visitor.visitByteString(z2, byteString, z, eciesHkdfKemParams.hkdfSalt_);
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
                                this.curveType_ = codedInputStream.readEnum();
                            } else if (readTag == 16) {
                                this.hkdfHashType_ = codedInputStream.readEnum();
                            } else if (readTag == 90) {
                                this.hkdfSalt_ = codedInputStream.readBytes();
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
                    synchronized (EciesHkdfKemParams.class) {
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

    public static EciesHkdfKemParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EciesHkdfKemParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
