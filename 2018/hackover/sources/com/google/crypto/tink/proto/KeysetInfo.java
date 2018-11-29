package com.google.crypto.tink.proto;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.Internal.ProtobufList;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class KeysetInfo extends GeneratedMessageLite<KeysetInfo, Builder> implements KeysetInfoOrBuilder {
    private static final KeysetInfo DEFAULT_INSTANCE = new KeysetInfo();
    public static final int KEY_INFO_FIELD_NUMBER = 2;
    private static volatile Parser<KeysetInfo> PARSER = null;
    public static final int PRIMARY_KEY_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private ProtobufList<KeyInfo> keyInfo_ = GeneratedMessageLite.emptyProtobufList();
    private int primaryKeyId_;

    public interface KeyInfoOrBuilder extends MessageLiteOrBuilder {
        int getKeyId();

        OutputPrefixType getOutputPrefixType();

        int getOutputPrefixTypeValue();

        KeyStatusType getStatus();

        int getStatusValue();

        String getTypeUrl();

        ByteString getTypeUrlBytes();
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<KeysetInfo, Builder> implements KeysetInfoOrBuilder {
        private Builder() {
            super(KeysetInfo.DEFAULT_INSTANCE);
        }

        public int getPrimaryKeyId() {
            return ((KeysetInfo) this.instance).getPrimaryKeyId();
        }

        public Builder setPrimaryKeyId(int i) {
            copyOnWrite();
            ((KeysetInfo) this.instance).setPrimaryKeyId(i);
            return this;
        }

        public Builder clearPrimaryKeyId() {
            copyOnWrite();
            ((KeysetInfo) this.instance).clearPrimaryKeyId();
            return this;
        }

        public List<KeyInfo> getKeyInfoList() {
            return Collections.unmodifiableList(((KeysetInfo) this.instance).getKeyInfoList());
        }

        public int getKeyInfoCount() {
            return ((KeysetInfo) this.instance).getKeyInfoCount();
        }

        public KeyInfo getKeyInfo(int i) {
            return ((KeysetInfo) this.instance).getKeyInfo(i);
        }

        public Builder setKeyInfo(int i, KeyInfo keyInfo) {
            copyOnWrite();
            ((KeysetInfo) this.instance).setKeyInfo(i, keyInfo);
            return this;
        }

        public Builder setKeyInfo(int i, Builder builder) {
            copyOnWrite();
            ((KeysetInfo) this.instance).setKeyInfo(i, builder);
            return this;
        }

        public Builder addKeyInfo(KeyInfo keyInfo) {
            copyOnWrite();
            ((KeysetInfo) this.instance).addKeyInfo(keyInfo);
            return this;
        }

        public Builder addKeyInfo(int i, KeyInfo keyInfo) {
            copyOnWrite();
            ((KeysetInfo) this.instance).addKeyInfo(i, keyInfo);
            return this;
        }

        public Builder addKeyInfo(Builder builder) {
            copyOnWrite();
            ((KeysetInfo) this.instance).addKeyInfo(builder);
            return this;
        }

        public Builder addKeyInfo(int i, Builder builder) {
            copyOnWrite();
            ((KeysetInfo) this.instance).addKeyInfo(i, builder);
            return this;
        }

        public Builder addAllKeyInfo(Iterable<? extends KeyInfo> iterable) {
            copyOnWrite();
            ((KeysetInfo) this.instance).addAllKeyInfo(iterable);
            return this;
        }

        public Builder clearKeyInfo() {
            copyOnWrite();
            ((KeysetInfo) this.instance).clearKeyInfo();
            return this;
        }

        public Builder removeKeyInfo(int i) {
            copyOnWrite();
            ((KeysetInfo) this.instance).removeKeyInfo(i);
            return this;
        }
    }

    public static final class KeyInfo extends GeneratedMessageLite<KeyInfo, Builder> implements KeyInfoOrBuilder {
        private static final KeyInfo DEFAULT_INSTANCE = new KeyInfo();
        public static final int KEY_ID_FIELD_NUMBER = 3;
        public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 4;
        private static volatile Parser<KeyInfo> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        public static final int TYPE_URL_FIELD_NUMBER = 1;
        private int keyId_;
        private int outputPrefixType_;
        private int status_;
        private String typeUrl_ = "";

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<KeyInfo, Builder> implements KeyInfoOrBuilder {
            private Builder() {
                super(KeyInfo.DEFAULT_INSTANCE);
            }

            public String getTypeUrl() {
                return ((KeyInfo) this.instance).getTypeUrl();
            }

            public ByteString getTypeUrlBytes() {
                return ((KeyInfo) this.instance).getTypeUrlBytes();
            }

            public Builder setTypeUrl(String str) {
                copyOnWrite();
                ((KeyInfo) this.instance).setTypeUrl(str);
                return this;
            }

            public Builder clearTypeUrl() {
                copyOnWrite();
                ((KeyInfo) this.instance).clearTypeUrl();
                return this;
            }

            public Builder setTypeUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((KeyInfo) this.instance).setTypeUrlBytes(byteString);
                return this;
            }

            public int getStatusValue() {
                return ((KeyInfo) this.instance).getStatusValue();
            }

            public Builder setStatusValue(int i) {
                copyOnWrite();
                ((KeyInfo) this.instance).setStatusValue(i);
                return this;
            }

            public KeyStatusType getStatus() {
                return ((KeyInfo) this.instance).getStatus();
            }

            public Builder setStatus(KeyStatusType keyStatusType) {
                copyOnWrite();
                ((KeyInfo) this.instance).setStatus(keyStatusType);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((KeyInfo) this.instance).clearStatus();
                return this;
            }

            public int getKeyId() {
                return ((KeyInfo) this.instance).getKeyId();
            }

            public Builder setKeyId(int i) {
                copyOnWrite();
                ((KeyInfo) this.instance).setKeyId(i);
                return this;
            }

            public Builder clearKeyId() {
                copyOnWrite();
                ((KeyInfo) this.instance).clearKeyId();
                return this;
            }

            public int getOutputPrefixTypeValue() {
                return ((KeyInfo) this.instance).getOutputPrefixTypeValue();
            }

            public Builder setOutputPrefixTypeValue(int i) {
                copyOnWrite();
                ((KeyInfo) this.instance).setOutputPrefixTypeValue(i);
                return this;
            }

            public OutputPrefixType getOutputPrefixType() {
                return ((KeyInfo) this.instance).getOutputPrefixType();
            }

            public Builder setOutputPrefixType(OutputPrefixType outputPrefixType) {
                copyOnWrite();
                ((KeyInfo) this.instance).setOutputPrefixType(outputPrefixType);
                return this;
            }

            public Builder clearOutputPrefixType() {
                copyOnWrite();
                ((KeyInfo) this.instance).clearOutputPrefixType();
                return this;
            }
        }

        private KeyInfo() {
        }

        public String getTypeUrl() {
            return this.typeUrl_;
        }

        public ByteString getTypeUrlBytes() {
            return ByteString.copyFromUtf8(this.typeUrl_);
        }

        private void setTypeUrl(String str) {
            if (str != null) {
                this.typeUrl_ = str;
                return;
            }
            throw new NullPointerException();
        }

        private void clearTypeUrl() {
            this.typeUrl_ = getDefaultInstance().getTypeUrl();
        }

        private void setTypeUrlBytes(ByteString byteString) {
            if (byteString != null) {
                AbstractMessageLite.checkByteStringIsUtf8(byteString);
                this.typeUrl_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public int getStatusValue() {
            return this.status_;
        }

        public KeyStatusType getStatus() {
            KeyStatusType forNumber = KeyStatusType.forNumber(this.status_);
            return forNumber == null ? KeyStatusType.UNRECOGNIZED : forNumber;
        }

        private void setStatusValue(int i) {
            this.status_ = i;
        }

        private void setStatus(KeyStatusType keyStatusType) {
            if (keyStatusType != null) {
                this.status_ = keyStatusType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        private void clearStatus() {
            this.status_ = 0;
        }

        public int getKeyId() {
            return this.keyId_;
        }

        private void setKeyId(int i) {
            this.keyId_ = i;
        }

        private void clearKeyId() {
            this.keyId_ = 0;
        }

        public int getOutputPrefixTypeValue() {
            return this.outputPrefixType_;
        }

        public OutputPrefixType getOutputPrefixType() {
            OutputPrefixType forNumber = OutputPrefixType.forNumber(this.outputPrefixType_);
            return forNumber == null ? OutputPrefixType.UNRECOGNIZED : forNumber;
        }

        private void setOutputPrefixTypeValue(int i) {
            this.outputPrefixType_ = i;
        }

        private void setOutputPrefixType(OutputPrefixType outputPrefixType) {
            if (outputPrefixType != null) {
                this.outputPrefixType_ = outputPrefixType.getNumber();
                return;
            }
            throw new NullPointerException();
        }

        private void clearOutputPrefixType() {
            this.outputPrefixType_ = 0;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.typeUrl_.isEmpty()) {
                codedOutputStream.writeString(1, getTypeUrl());
            }
            if (this.status_ != KeyStatusType.UNKNOWN_STATUS.getNumber()) {
                codedOutputStream.writeEnum(2, this.status_);
            }
            if (this.keyId_ != 0) {
                codedOutputStream.writeUInt32(3, this.keyId_);
            }
            if (this.outputPrefixType_ != OutputPrefixType.UNKNOWN_PREFIX.getNumber()) {
                codedOutputStream.writeEnum(4, this.outputPrefixType_);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            i = 0;
            if (!this.typeUrl_.isEmpty()) {
                i = 0 + CodedOutputStream.computeStringSize(1, getTypeUrl());
            }
            if (this.status_ != KeyStatusType.UNKNOWN_STATUS.getNumber()) {
                i += CodedOutputStream.computeEnumSize(2, this.status_);
            }
            if (this.keyId_ != 0) {
                i += CodedOutputStream.computeUInt32Size(3, this.keyId_);
            }
            if (this.outputPrefixType_ != OutputPrefixType.UNKNOWN_PREFIX.getNumber()) {
                i += CodedOutputStream.computeEnumSize(4, this.outputPrefixType_);
            }
            this.memoizedSerializedSize = i;
            return i;
        }

        public static KeyInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (KeyInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static KeyInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (KeyInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static KeyInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (KeyInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static KeyInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (KeyInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static KeyInfo parseFrom(InputStream inputStream) throws IOException {
            return (KeyInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static KeyInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static KeyInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (KeyInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static KeyInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static KeyInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (KeyInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static KeyInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(KeyInfo keyInfo) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(keyInfo);
        }

        protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new KeyInfo();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    KeyInfo keyInfo = (KeyInfo) obj2;
                    this.typeUrl_ = visitor.visitString(this.typeUrl_.isEmpty() ^ true, this.typeUrl_, keyInfo.typeUrl_.isEmpty() ^ true, keyInfo.typeUrl_);
                    this.status_ = visitor.visitInt(this.status_ != 0, this.status_, keyInfo.status_ != 0, keyInfo.status_);
                    this.keyId_ = visitor.visitInt(this.keyId_ != 0, this.keyId_, keyInfo.keyId_ != 0, keyInfo.keyId_);
                    boolean z2 = this.outputPrefixType_ != 0;
                    int i = this.outputPrefixType_;
                    if (keyInfo.outputPrefixType_ != 0) {
                        z = true;
                    }
                    this.outputPrefixType_ = visitor.visitInt(z2, i, z, keyInfo.outputPrefixType_);
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
                                    this.typeUrl_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 16) {
                                    this.status_ = codedInputStream.readEnum();
                                } else if (readTag == 24) {
                                    this.keyId_ = codedInputStream.readUInt32();
                                } else if (readTag == 32) {
                                    this.outputPrefixType_ = codedInputStream.readEnum();
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
                        synchronized (KeyInfo.class) {
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

        public static KeyInfo getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<KeyInfo> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    private KeysetInfo() {
    }

    public int getPrimaryKeyId() {
        return this.primaryKeyId_;
    }

    private void setPrimaryKeyId(int i) {
        this.primaryKeyId_ = i;
    }

    private void clearPrimaryKeyId() {
        this.primaryKeyId_ = 0;
    }

    public List<KeyInfo> getKeyInfoList() {
        return this.keyInfo_;
    }

    public List<? extends KeyInfoOrBuilder> getKeyInfoOrBuilderList() {
        return this.keyInfo_;
    }

    public int getKeyInfoCount() {
        return this.keyInfo_.size();
    }

    public KeyInfo getKeyInfo(int i) {
        return (KeyInfo) this.keyInfo_.get(i);
    }

    public KeyInfoOrBuilder getKeyInfoOrBuilder(int i) {
        return (KeyInfoOrBuilder) this.keyInfo_.get(i);
    }

    private void ensureKeyInfoIsMutable() {
        if (!this.keyInfo_.isModifiable()) {
            this.keyInfo_ = GeneratedMessageLite.mutableCopy(this.keyInfo_);
        }
    }

    private void setKeyInfo(int i, KeyInfo keyInfo) {
        if (keyInfo != null) {
            ensureKeyInfoIsMutable();
            this.keyInfo_.set(i, keyInfo);
            return;
        }
        throw new NullPointerException();
    }

    private void setKeyInfo(int i, Builder builder) {
        ensureKeyInfoIsMutable();
        this.keyInfo_.set(i, builder.build());
    }

    private void addKeyInfo(KeyInfo keyInfo) {
        if (keyInfo != null) {
            ensureKeyInfoIsMutable();
            this.keyInfo_.add(keyInfo);
            return;
        }
        throw new NullPointerException();
    }

    private void addKeyInfo(int i, KeyInfo keyInfo) {
        if (keyInfo != null) {
            ensureKeyInfoIsMutable();
            this.keyInfo_.add(i, keyInfo);
            return;
        }
        throw new NullPointerException();
    }

    private void addKeyInfo(Builder builder) {
        ensureKeyInfoIsMutable();
        this.keyInfo_.add(builder.build());
    }

    private void addKeyInfo(int i, Builder builder) {
        ensureKeyInfoIsMutable();
        this.keyInfo_.add(i, builder.build());
    }

    private void addAllKeyInfo(Iterable<? extends KeyInfo> iterable) {
        ensureKeyInfoIsMutable();
        AbstractMessageLite.addAll(iterable, this.keyInfo_);
    }

    private void clearKeyInfo() {
        this.keyInfo_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void removeKeyInfo(int i) {
        ensureKeyInfoIsMutable();
        this.keyInfo_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.primaryKeyId_ != 0) {
            codedOutputStream.writeUInt32(1, this.primaryKeyId_);
        }
        for (int i = 0; i < this.keyInfo_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.keyInfo_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        i = this.primaryKeyId_ != 0 ? CodedOutputStream.computeUInt32Size(1, this.primaryKeyId_) + 0 : 0;
        while (i2 < this.keyInfo_.size()) {
            i += CodedOutputStream.computeMessageSize(2, (MessageLite) this.keyInfo_.get(i2));
            i2++;
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static KeysetInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (KeysetInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static KeysetInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KeysetInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static KeysetInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (KeysetInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static KeysetInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KeysetInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static KeysetInfo parseFrom(InputStream inputStream) throws IOException {
        return (KeysetInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KeysetInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeysetInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KeysetInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (KeysetInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KeysetInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeysetInfo) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KeysetInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (KeysetInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static KeysetInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeysetInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KeysetInfo keysetInfo) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(keysetInfo);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new KeysetInfo();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.keyInfo_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                KeysetInfo keysetInfo = (KeysetInfo) obj2;
                boolean z2 = this.primaryKeyId_ != 0;
                int i = this.primaryKeyId_;
                if (keysetInfo.primaryKeyId_ != 0) {
                    z = true;
                }
                this.primaryKeyId_ = visitor.visitInt(z2, i, z, keysetInfo.primaryKeyId_);
                this.keyInfo_ = visitor.visitList(this.keyInfo_, keysetInfo.keyInfo_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= keysetInfo.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.primaryKeyId_ = codedInputStream.readUInt32();
                            } else if (readTag == 18) {
                                if (!this.keyInfo_.isModifiable()) {
                                    this.keyInfo_ = GeneratedMessageLite.mutableCopy(this.keyInfo_);
                                }
                                this.keyInfo_.add(codedInputStream.readMessage(KeyInfo.parser(), extensionRegistryLite));
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
                    synchronized (KeysetInfo.class) {
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

    public static KeysetInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KeysetInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
