package com.google.crypto.tink.proto;

import com.google.protobuf.AbstractMessageLite;
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

public final class KmsAeadKeyFormat extends GeneratedMessageLite<KmsAeadKeyFormat, Builder> implements KmsAeadKeyFormatOrBuilder {
    private static final KmsAeadKeyFormat DEFAULT_INSTANCE = new KmsAeadKeyFormat();
    public static final int KEY_URI_FIELD_NUMBER = 1;
    private static volatile Parser<KmsAeadKeyFormat> PARSER;
    private String keyUri_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<KmsAeadKeyFormat, Builder> implements KmsAeadKeyFormatOrBuilder {
        private Builder() {
            super(KmsAeadKeyFormat.DEFAULT_INSTANCE);
        }

        public String getKeyUri() {
            return ((KmsAeadKeyFormat) this.instance).getKeyUri();
        }

        public ByteString getKeyUriBytes() {
            return ((KmsAeadKeyFormat) this.instance).getKeyUriBytes();
        }

        public Builder setKeyUri(String str) {
            copyOnWrite();
            ((KmsAeadKeyFormat) this.instance).setKeyUri(str);
            return this;
        }

        public Builder clearKeyUri() {
            copyOnWrite();
            ((KmsAeadKeyFormat) this.instance).clearKeyUri();
            return this;
        }

        public Builder setKeyUriBytes(ByteString byteString) {
            copyOnWrite();
            ((KmsAeadKeyFormat) this.instance).setKeyUriBytes(byteString);
            return this;
        }
    }

    private KmsAeadKeyFormat() {
    }

    public String getKeyUri() {
        return this.keyUri_;
    }

    public ByteString getKeyUriBytes() {
        return ByteString.copyFromUtf8(this.keyUri_);
    }

    private void setKeyUri(String str) {
        if (str != null) {
            this.keyUri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    private void clearKeyUri() {
        this.keyUri_ = getDefaultInstance().getKeyUri();
    }

    private void setKeyUriBytes(ByteString byteString) {
        if (byteString != null) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.keyUri_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.keyUri_.isEmpty()) {
            codedOutputStream.writeString(1, getKeyUri());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (!this.keyUri_.isEmpty()) {
            i = 0 + CodedOutputStream.computeStringSize(1, getKeyUri());
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static KmsAeadKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (KmsAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static KmsAeadKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KmsAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static KmsAeadKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (KmsAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static KmsAeadKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KmsAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static KmsAeadKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (KmsAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KmsAeadKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KmsAeadKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (KmsAeadKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KmsAeadKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsAeadKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KmsAeadKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (KmsAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static KmsAeadKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KmsAeadKeyFormat kmsAeadKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(kmsAeadKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new KmsAeadKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                KmsAeadKeyFormat kmsAeadKeyFormat = (KmsAeadKeyFormat) obj2;
                this.keyUri_ = ((Visitor) obj).visitString(this.keyUri_.isEmpty() ^ true, this.keyUri_, true ^ kmsAeadKeyFormat.keyUri_.isEmpty(), kmsAeadKeyFormat.keyUri_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                int i = 0;
                while (i == 0) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.keyUri_ = codedInputStream.readStringRequireUtf8();
                            } else if (codedInputStream.skipField(readTag)) {
                            }
                        }
                        i = 1;
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
                    synchronized (KmsAeadKeyFormat.class) {
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

    public static KmsAeadKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KmsAeadKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
