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

public final class EciesAeadHkdfParams extends GeneratedMessageLite<EciesAeadHkdfParams, Builder> implements EciesAeadHkdfParamsOrBuilder {
    private static final EciesAeadHkdfParams DEFAULT_INSTANCE = new EciesAeadHkdfParams();
    public static final int DEM_PARAMS_FIELD_NUMBER = 2;
    public static final int EC_POINT_FORMAT_FIELD_NUMBER = 3;
    public static final int KEM_PARAMS_FIELD_NUMBER = 1;
    private static volatile Parser<EciesAeadHkdfParams> PARSER;
    private EciesAeadDemParams demParams_;
    private int ecPointFormat_;
    private EciesHkdfKemParams kemParams_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EciesAeadHkdfParams, Builder> implements EciesAeadHkdfParamsOrBuilder {
        private Builder() {
            super(EciesAeadHkdfParams.DEFAULT_INSTANCE);
        }

        public boolean hasKemParams() {
            return ((EciesAeadHkdfParams) this.instance).hasKemParams();
        }

        public EciesHkdfKemParams getKemParams() {
            return ((EciesAeadHkdfParams) this.instance).getKemParams();
        }

        public Builder setKemParams(EciesHkdfKemParams eciesHkdfKemParams) {
            copyOnWrite();
            ((EciesAeadHkdfParams) this.instance).setKemParams(eciesHkdfKemParams);
            return this;
        }

        public Builder setKemParams(com.google.crypto.tink.proto.EciesHkdfKemParams.Builder builder) {
            copyOnWrite();
            ((EciesAeadHkdfParams) this.instance).setKemParams(builder);
            return this;
        }

        public Builder mergeKemParams(EciesHkdfKemParams eciesHkdfKemParams) {
            copyOnWrite();
            ((EciesAeadHkdfParams) this.instance).mergeKemParams(eciesHkdfKemParams);
            return this;
        }

        public Builder clearKemParams() {
            copyOnWrite();
            ((EciesAeadHkdfParams) this.instance).clearKemParams();
            return this;
        }

        public boolean hasDemParams() {
            return ((EciesAeadHkdfParams) this.instance).hasDemParams();
        }

        public EciesAeadDemParams getDemParams() {
            return ((EciesAeadHkdfParams) this.instance).getDemParams();
        }

        public Builder setDemParams(EciesAeadDemParams eciesAeadDemParams) {
            copyOnWrite();
            ((EciesAeadHkdfParams) this.instance).setDemParams(eciesAeadDemParams);
            return this;
        }

        public Builder setDemParams(com.google.crypto.tink.proto.EciesAeadDemParams.Builder builder) {
            copyOnWrite();
            ((EciesAeadHkdfParams) this.instance).setDemParams(builder);
            return this;
        }

        public Builder mergeDemParams(EciesAeadDemParams eciesAeadDemParams) {
            copyOnWrite();
            ((EciesAeadHkdfParams) this.instance).mergeDemParams(eciesAeadDemParams);
            return this;
        }

        public Builder clearDemParams() {
            copyOnWrite();
            ((EciesAeadHkdfParams) this.instance).clearDemParams();
            return this;
        }

        public int getEcPointFormatValue() {
            return ((EciesAeadHkdfParams) this.instance).getEcPointFormatValue();
        }

        public Builder setEcPointFormatValue(int i) {
            copyOnWrite();
            ((EciesAeadHkdfParams) this.instance).setEcPointFormatValue(i);
            return this;
        }

        public EcPointFormat getEcPointFormat() {
            return ((EciesAeadHkdfParams) this.instance).getEcPointFormat();
        }

        public Builder setEcPointFormat(EcPointFormat ecPointFormat) {
            copyOnWrite();
            ((EciesAeadHkdfParams) this.instance).setEcPointFormat(ecPointFormat);
            return this;
        }

        public Builder clearEcPointFormat() {
            copyOnWrite();
            ((EciesAeadHkdfParams) this.instance).clearEcPointFormat();
            return this;
        }
    }

    private EciesAeadHkdfParams() {
    }

    public boolean hasKemParams() {
        return this.kemParams_ != null;
    }

    public EciesHkdfKemParams getKemParams() {
        return this.kemParams_ == null ? EciesHkdfKemParams.getDefaultInstance() : this.kemParams_;
    }

    private void setKemParams(EciesHkdfKemParams eciesHkdfKemParams) {
        if (eciesHkdfKemParams != null) {
            this.kemParams_ = eciesHkdfKemParams;
            return;
        }
        throw new NullPointerException();
    }

    private void setKemParams(com.google.crypto.tink.proto.EciesHkdfKemParams.Builder builder) {
        this.kemParams_ = (EciesHkdfKemParams) builder.build();
    }

    private void mergeKemParams(EciesHkdfKemParams eciesHkdfKemParams) {
        if (this.kemParams_ == null || this.kemParams_ == EciesHkdfKemParams.getDefaultInstance()) {
            this.kemParams_ = eciesHkdfKemParams;
        } else {
            this.kemParams_ = (EciesHkdfKemParams) ((com.google.crypto.tink.proto.EciesHkdfKemParams.Builder) EciesHkdfKemParams.newBuilder(this.kemParams_).mergeFrom(eciesHkdfKemParams)).buildPartial();
        }
    }

    private void clearKemParams() {
        this.kemParams_ = null;
    }

    public boolean hasDemParams() {
        return this.demParams_ != null;
    }

    public EciesAeadDemParams getDemParams() {
        return this.demParams_ == null ? EciesAeadDemParams.getDefaultInstance() : this.demParams_;
    }

    private void setDemParams(EciesAeadDemParams eciesAeadDemParams) {
        if (eciesAeadDemParams != null) {
            this.demParams_ = eciesAeadDemParams;
            return;
        }
        throw new NullPointerException();
    }

    private void setDemParams(com.google.crypto.tink.proto.EciesAeadDemParams.Builder builder) {
        this.demParams_ = (EciesAeadDemParams) builder.build();
    }

    private void mergeDemParams(EciesAeadDemParams eciesAeadDemParams) {
        if (this.demParams_ == null || this.demParams_ == EciesAeadDemParams.getDefaultInstance()) {
            this.demParams_ = eciesAeadDemParams;
        } else {
            this.demParams_ = (EciesAeadDemParams) ((com.google.crypto.tink.proto.EciesAeadDemParams.Builder) EciesAeadDemParams.newBuilder(this.demParams_).mergeFrom(eciesAeadDemParams)).buildPartial();
        }
    }

    private void clearDemParams() {
        this.demParams_ = null;
    }

    public int getEcPointFormatValue() {
        return this.ecPointFormat_;
    }

    public EcPointFormat getEcPointFormat() {
        EcPointFormat forNumber = EcPointFormat.forNumber(this.ecPointFormat_);
        return forNumber == null ? EcPointFormat.UNRECOGNIZED : forNumber;
    }

    private void setEcPointFormatValue(int i) {
        this.ecPointFormat_ = i;
    }

    private void setEcPointFormat(EcPointFormat ecPointFormat) {
        if (ecPointFormat != null) {
            this.ecPointFormat_ = ecPointFormat.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    private void clearEcPointFormat() {
        this.ecPointFormat_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.kemParams_ != null) {
            codedOutputStream.writeMessage(1, getKemParams());
        }
        if (this.demParams_ != null) {
            codedOutputStream.writeMessage(2, getDemParams());
        }
        if (this.ecPointFormat_ != EcPointFormat.UNKNOWN_FORMAT.getNumber()) {
            codedOutputStream.writeEnum(3, this.ecPointFormat_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.kemParams_ != null) {
            i = 0 + CodedOutputStream.computeMessageSize(1, getKemParams());
        }
        if (this.demParams_ != null) {
            i += CodedOutputStream.computeMessageSize(2, getDemParams());
        }
        if (this.ecPointFormat_ != EcPointFormat.UNKNOWN_FORMAT.getNumber()) {
            i += CodedOutputStream.computeEnumSize(3, this.ecPointFormat_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static EciesAeadHkdfParams parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EciesAeadHkdfParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EciesAeadHkdfParams parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EciesAeadHkdfParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EciesAeadHkdfParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EciesAeadHkdfParams parseFrom(InputStream inputStream) throws IOException {
        return (EciesAeadHkdfParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesAeadHkdfParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadHkdfParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesAeadHkdfParams parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EciesAeadHkdfParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EciesAeadHkdfParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadHkdfParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EciesAeadHkdfParams parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EciesAeadHkdfParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EciesAeadHkdfParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EciesAeadHkdfParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EciesAeadHkdfParams eciesAeadHkdfParams) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(eciesAeadHkdfParams);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EciesAeadHkdfParams();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                EciesAeadHkdfParams eciesAeadHkdfParams = (EciesAeadHkdfParams) obj2;
                this.kemParams_ = (EciesHkdfKemParams) visitor.visitMessage(this.kemParams_, eciesAeadHkdfParams.kemParams_);
                this.demParams_ = (EciesAeadDemParams) visitor.visitMessage(this.demParams_, eciesAeadHkdfParams.demParams_);
                boolean z2 = this.ecPointFormat_ != 0;
                int i = this.ecPointFormat_;
                if (eciesAeadHkdfParams.ecPointFormat_ != 0) {
                    z = true;
                }
                this.ecPointFormat_ = visitor.visitInt(z2, i, z, eciesAeadHkdfParams.ecPointFormat_);
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
                                com.google.crypto.tink.proto.EciesHkdfKemParams.Builder builder = this.kemParams_ != null ? (com.google.crypto.tink.proto.EciesHkdfKemParams.Builder) this.kemParams_.toBuilder() : null;
                                this.kemParams_ = (EciesHkdfKemParams) codedInputStream.readMessage(EciesHkdfKemParams.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.kemParams_);
                                    this.kemParams_ = (EciesHkdfKemParams) builder.buildPartial();
                                }
                            } else if (readTag == 18) {
                                com.google.crypto.tink.proto.EciesAeadDemParams.Builder builder2 = this.demParams_ != null ? (com.google.crypto.tink.proto.EciesAeadDemParams.Builder) this.demParams_.toBuilder() : null;
                                this.demParams_ = (EciesAeadDemParams) codedInputStream.readMessage(EciesAeadDemParams.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.demParams_);
                                    this.demParams_ = (EciesAeadDemParams) builder2.buildPartial();
                                }
                            } else if (readTag == 24) {
                                this.ecPointFormat_ = codedInputStream.readEnum();
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
                    synchronized (EciesAeadHkdfParams.class) {
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

    public static EciesAeadHkdfParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EciesAeadHkdfParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
