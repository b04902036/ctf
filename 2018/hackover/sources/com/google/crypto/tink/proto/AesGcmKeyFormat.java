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

public final class AesGcmKeyFormat extends GeneratedMessageLite<AesGcmKeyFormat, Builder> implements AesGcmKeyFormatOrBuilder {
    private static final AesGcmKeyFormat DEFAULT_INSTANCE = new AesGcmKeyFormat();
    public static final int KEY_SIZE_FIELD_NUMBER = 2;
    private static volatile Parser<AesGcmKeyFormat> PARSER;
    private int keySize_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesGcmKeyFormat, Builder> implements AesGcmKeyFormatOrBuilder {
        private Builder() {
            super(AesGcmKeyFormat.DEFAULT_INSTANCE);
        }

        public int getKeySize() {
            return ((AesGcmKeyFormat) this.instance).getKeySize();
        }

        public Builder setKeySize(int i) {
            copyOnWrite();
            ((AesGcmKeyFormat) this.instance).setKeySize(i);
            return this;
        }

        public Builder clearKeySize() {
            copyOnWrite();
            ((AesGcmKeyFormat) this.instance).clearKeySize();
            return this;
        }
    }

    private AesGcmKeyFormat() {
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
        if (this.keySize_ != 0) {
            i = 0 + CodedOutputStream.computeUInt32Size(2, this.keySize_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static AesGcmKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesGcmKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesGcmKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesGcmKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesGcmKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesGcmKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesGcmKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesGcmKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesGcmKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (AesGcmKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesGcmKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesGcmKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesGcmKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesGcmKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesGcmKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesGcmKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesGcmKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesGcmKeyFormat aesGcmKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesGcmKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesGcmKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesGcmKeyFormat aesGcmKeyFormat = (AesGcmKeyFormat) obj2;
                boolean z2 = this.keySize_ != 0;
                int i = this.keySize_;
                if (aesGcmKeyFormat.keySize_ != 0) {
                    z = true;
                }
                this.keySize_ = visitor.visitInt(z2, i, z, aesGcmKeyFormat.keySize_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 16) {
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
                    synchronized (AesGcmKeyFormat.class) {
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

    public static AesGcmKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesGcmKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
