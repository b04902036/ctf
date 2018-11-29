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

public final class AesSivKeyFormat extends GeneratedMessageLite<AesSivKeyFormat, Builder> implements AesSivKeyFormatOrBuilder {
    private static final AesSivKeyFormat DEFAULT_INSTANCE = new AesSivKeyFormat();
    public static final int KEY_SIZE_FIELD_NUMBER = 1;
    private static volatile Parser<AesSivKeyFormat> PARSER;
    private int keySize_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesSivKeyFormat, Builder> implements AesSivKeyFormatOrBuilder {
        private Builder() {
            super(AesSivKeyFormat.DEFAULT_INSTANCE);
        }

        public int getKeySize() {
            return ((AesSivKeyFormat) this.instance).getKeySize();
        }

        public Builder setKeySize(int i) {
            copyOnWrite();
            ((AesSivKeyFormat) this.instance).setKeySize(i);
            return this;
        }

        public Builder clearKeySize() {
            copyOnWrite();
            ((AesSivKeyFormat) this.instance).clearKeySize();
            return this;
        }
    }

    private AesSivKeyFormat() {
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
            codedOutputStream.writeUInt32(1, this.keySize_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.keySize_ != 0) {
            i = 0 + CodedOutputStream.computeUInt32Size(1, this.keySize_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static AesSivKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesSivKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesSivKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesSivKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesSivKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesSivKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesSivKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesSivKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesSivKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (AesSivKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesSivKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesSivKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesSivKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesSivKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesSivKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesSivKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesSivKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesSivKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesSivKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesSivKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesSivKeyFormat aesSivKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesSivKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesSivKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesSivKeyFormat aesSivKeyFormat = (AesSivKeyFormat) obj2;
                boolean z2 = this.keySize_ != 0;
                int i = this.keySize_;
                if (aesSivKeyFormat.keySize_ != 0) {
                    z = true;
                }
                this.keySize_ = visitor.visitInt(z2, i, z, aesSivKeyFormat.keySize_);
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
                    synchronized (AesSivKeyFormat.class) {
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

    public static AesSivKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesSivKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
