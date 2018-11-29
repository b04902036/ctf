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

public final class EncryptedKeyset extends GeneratedMessageLite<EncryptedKeyset, Builder> implements EncryptedKeysetOrBuilder {
    private static final EncryptedKeyset DEFAULT_INSTANCE = new EncryptedKeyset();
    public static final int ENCRYPTED_KEYSET_FIELD_NUMBER = 2;
    public static final int KEYSET_INFO_FIELD_NUMBER = 3;
    private static volatile Parser<EncryptedKeyset> PARSER;
    private ByteString encryptedKeyset_ = ByteString.EMPTY;
    private KeysetInfo keysetInfo_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EncryptedKeyset, Builder> implements EncryptedKeysetOrBuilder {
        private Builder() {
            super(EncryptedKeyset.DEFAULT_INSTANCE);
        }

        public ByteString getEncryptedKeyset() {
            return ((EncryptedKeyset) this.instance).getEncryptedKeyset();
        }

        public Builder setEncryptedKeyset(ByteString byteString) {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).setEncryptedKeyset(byteString);
            return this;
        }

        public Builder clearEncryptedKeyset() {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).clearEncryptedKeyset();
            return this;
        }

        public boolean hasKeysetInfo() {
            return ((EncryptedKeyset) this.instance).hasKeysetInfo();
        }

        public KeysetInfo getKeysetInfo() {
            return ((EncryptedKeyset) this.instance).getKeysetInfo();
        }

        public Builder setKeysetInfo(KeysetInfo keysetInfo) {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).setKeysetInfo(keysetInfo);
            return this;
        }

        public Builder setKeysetInfo(com.google.crypto.tink.proto.KeysetInfo.Builder builder) {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).setKeysetInfo(builder);
            return this;
        }

        public Builder mergeKeysetInfo(KeysetInfo keysetInfo) {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).mergeKeysetInfo(keysetInfo);
            return this;
        }

        public Builder clearKeysetInfo() {
            copyOnWrite();
            ((EncryptedKeyset) this.instance).clearKeysetInfo();
            return this;
        }
    }

    private EncryptedKeyset() {
    }

    public ByteString getEncryptedKeyset() {
        return this.encryptedKeyset_;
    }

    private void setEncryptedKeyset(ByteString byteString) {
        if (byteString != null) {
            this.encryptedKeyset_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    private void clearEncryptedKeyset() {
        this.encryptedKeyset_ = getDefaultInstance().getEncryptedKeyset();
    }

    public boolean hasKeysetInfo() {
        return this.keysetInfo_ != null;
    }

    public KeysetInfo getKeysetInfo() {
        return this.keysetInfo_ == null ? KeysetInfo.getDefaultInstance() : this.keysetInfo_;
    }

    private void setKeysetInfo(KeysetInfo keysetInfo) {
        if (keysetInfo != null) {
            this.keysetInfo_ = keysetInfo;
            return;
        }
        throw new NullPointerException();
    }

    private void setKeysetInfo(com.google.crypto.tink.proto.KeysetInfo.Builder builder) {
        this.keysetInfo_ = (KeysetInfo) builder.build();
    }

    private void mergeKeysetInfo(KeysetInfo keysetInfo) {
        if (this.keysetInfo_ == null || this.keysetInfo_ == KeysetInfo.getDefaultInstance()) {
            this.keysetInfo_ = keysetInfo;
        } else {
            this.keysetInfo_ = (KeysetInfo) ((com.google.crypto.tink.proto.KeysetInfo.Builder) KeysetInfo.newBuilder(this.keysetInfo_).mergeFrom(keysetInfo)).buildPartial();
        }
    }

    private void clearKeysetInfo() {
        this.keysetInfo_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.encryptedKeyset_.isEmpty()) {
            codedOutputStream.writeBytes(2, this.encryptedKeyset_);
        }
        if (this.keysetInfo_ != null) {
            codedOutputStream.writeMessage(3, getKeysetInfo());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (!this.encryptedKeyset_.isEmpty()) {
            i = 0 + CodedOutputStream.computeBytesSize(2, this.encryptedKeyset_);
        }
        if (this.keysetInfo_ != null) {
            i += CodedOutputStream.computeMessageSize(3, getKeysetInfo());
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static EncryptedKeyset parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EncryptedKeyset parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EncryptedKeyset parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EncryptedKeyset parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EncryptedKeyset parseFrom(InputStream inputStream) throws IOException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EncryptedKeyset parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EncryptedKeyset parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EncryptedKeyset) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EncryptedKeyset parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EncryptedKeyset) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EncryptedKeyset parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EncryptedKeyset parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EncryptedKeyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EncryptedKeyset encryptedKeyset) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(encryptedKeyset);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EncryptedKeyset();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                EncryptedKeyset encryptedKeyset = (EncryptedKeyset) obj2;
                boolean z2 = this.encryptedKeyset_ != ByteString.EMPTY;
                ByteString byteString = this.encryptedKeyset_;
                if (encryptedKeyset.encryptedKeyset_ != ByteString.EMPTY) {
                    z = true;
                }
                this.encryptedKeyset_ = visitor.visitByteString(z2, byteString, z, encryptedKeyset.encryptedKeyset_);
                this.keysetInfo_ = (KeysetInfo) visitor.visitMessage(this.keysetInfo_, encryptedKeyset.keysetInfo_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 18) {
                                this.encryptedKeyset_ = codedInputStream.readBytes();
                            } else if (readTag == 26) {
                                com.google.crypto.tink.proto.KeysetInfo.Builder builder = this.keysetInfo_ != null ? (com.google.crypto.tink.proto.KeysetInfo.Builder) this.keysetInfo_.toBuilder() : null;
                                this.keysetInfo_ = (KeysetInfo) codedInputStream.readMessage(KeysetInfo.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.keysetInfo_);
                                    this.keysetInfo_ = (KeysetInfo) builder.buildPartial();
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
                    synchronized (EncryptedKeyset.class) {
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

    public static EncryptedKeyset getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EncryptedKeyset> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
