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

public final class AesEaxParams extends GeneratedMessageLite<AesEaxParams, Builder> implements AesEaxParamsOrBuilder {
    private static final AesEaxParams DEFAULT_INSTANCE = new AesEaxParams();
    public static final int IV_SIZE_FIELD_NUMBER = 1;
    private static volatile Parser<AesEaxParams> PARSER;
    private int ivSize_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesEaxParams, Builder> implements AesEaxParamsOrBuilder {
        private Builder() {
            super(AesEaxParams.DEFAULT_INSTANCE);
        }

        public int getIvSize() {
            return ((AesEaxParams) this.instance).getIvSize();
        }

        public Builder setIvSize(int i) {
            copyOnWrite();
            ((AesEaxParams) this.instance).setIvSize(i);
            return this;
        }

        public Builder clearIvSize() {
            copyOnWrite();
            ((AesEaxParams) this.instance).clearIvSize();
            return this;
        }
    }

    private AesEaxParams() {
    }

    public int getIvSize() {
        return this.ivSize_;
    }

    private void setIvSize(int i) {
        this.ivSize_ = i;
    }

    private void clearIvSize() {
        this.ivSize_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.ivSize_ != 0) {
            codedOutputStream.writeUInt32(1, this.ivSize_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.ivSize_ != 0) {
            i = 0 + CodedOutputStream.computeUInt32Size(1, this.ivSize_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static AesEaxParams parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesEaxParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesEaxParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesEaxParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesEaxParams parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesEaxParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesEaxParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesEaxParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesEaxParams parseFrom(InputStream inputStream) throws IOException {
        return (AesEaxParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesEaxParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesEaxParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesEaxParams parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesEaxParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesEaxParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesEaxParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesEaxParams parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesEaxParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesEaxParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesEaxParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesEaxParams aesEaxParams) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesEaxParams);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesEaxParams();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesEaxParams aesEaxParams = (AesEaxParams) obj2;
                boolean z2 = this.ivSize_ != 0;
                int i = this.ivSize_;
                if (aesEaxParams.ivSize_ != 0) {
                    z = true;
                }
                this.ivSize_ = visitor.visitInt(z2, i, z, aesEaxParams.ivSize_);
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
                                this.ivSize_ = codedInputStream.readUInt32();
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
                    synchronized (AesEaxParams.class) {
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

    public static AesEaxParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesEaxParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
