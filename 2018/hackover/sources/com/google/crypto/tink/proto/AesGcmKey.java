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

public final class AesGcmKey extends GeneratedMessageLite<AesGcmKey, Builder> implements AesGcmKeyOrBuilder {
    private static final AesGcmKey DEFAULT_INSTANCE = new AesGcmKey();
    public static final int KEY_VALUE_FIELD_NUMBER = 3;
    private static volatile Parser<AesGcmKey> PARSER = null;
    public static final int VERSION_FIELD_NUMBER = 1;
    private ByteString keyValue_ = ByteString.EMPTY;
    private int version_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AesGcmKey, Builder> implements AesGcmKeyOrBuilder {
        private Builder() {
            super(AesGcmKey.DEFAULT_INSTANCE);
        }

        public int getVersion() {
            return ((AesGcmKey) this.instance).getVersion();
        }

        public Builder setVersion(int i) {
            copyOnWrite();
            ((AesGcmKey) this.instance).setVersion(i);
            return this;
        }

        public Builder clearVersion() {
            copyOnWrite();
            ((AesGcmKey) this.instance).clearVersion();
            return this;
        }

        public ByteString getKeyValue() {
            return ((AesGcmKey) this.instance).getKeyValue();
        }

        public Builder setKeyValue(ByteString byteString) {
            copyOnWrite();
            ((AesGcmKey) this.instance).setKeyValue(byteString);
            return this;
        }

        public Builder clearKeyValue() {
            copyOnWrite();
            ((AesGcmKey) this.instance).clearKeyValue();
            return this;
        }
    }

    private AesGcmKey() {
    }

    public int getVersion() {
        return this.version_;
    }

    private void setVersion(int i) {
        this.version_ = i;
    }

    private void clearVersion() {
        this.version_ = 0;
    }

    public ByteString getKeyValue() {
        return this.keyValue_;
    }

    private void setKeyValue(ByteString byteString) {
        if (byteString != null) {
            this.keyValue_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    private void clearKeyValue() {
        this.keyValue_ = getDefaultInstance().getKeyValue();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.version_ != 0) {
            codedOutputStream.writeUInt32(1, this.version_);
        }
        if (!this.keyValue_.isEmpty()) {
            codedOutputStream.writeBytes(3, this.keyValue_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.version_ != 0) {
            i = 0 + CodedOutputStream.computeUInt32Size(1, this.version_);
        }
        if (!this.keyValue_.isEmpty()) {
            i += CodedOutputStream.computeBytesSize(3, this.keyValue_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static AesGcmKey parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AesGcmKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AesGcmKey parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesGcmKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AesGcmKey parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AesGcmKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AesGcmKey parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AesGcmKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AesGcmKey parseFrom(InputStream inputStream) throws IOException {
        return (AesGcmKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesGcmKey parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesGcmKey parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AesGcmKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AesGcmKey parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmKey) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AesGcmKey parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AesGcmKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AesGcmKey parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AesGcmKey) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AesGcmKey aesGcmKey) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(aesGcmKey);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AesGcmKey();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AesGcmKey aesGcmKey = (AesGcmKey) obj2;
                this.version_ = visitor.visitInt(this.version_ != 0, this.version_, aesGcmKey.version_ != 0, aesGcmKey.version_);
                boolean z2 = this.keyValue_ != ByteString.EMPTY;
                ByteString byteString = this.keyValue_;
                if (aesGcmKey.keyValue_ != ByteString.EMPTY) {
                    z = true;
                }
                this.keyValue_ = visitor.visitByteString(z2, byteString, z, aesGcmKey.keyValue_);
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
                                this.version_ = codedInputStream.readUInt32();
                            } else if (readTag == 26) {
                                this.keyValue_ = codedInputStream.readBytes();
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
                    synchronized (AesGcmKey.class) {
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

    public static AesGcmKey getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AesGcmKey> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
