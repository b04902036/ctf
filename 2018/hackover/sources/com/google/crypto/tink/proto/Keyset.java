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

public final class Keyset extends GeneratedMessageLite<Keyset, Builder> implements KeysetOrBuilder {
    private static final Keyset DEFAULT_INSTANCE = new Keyset();
    public static final int KEY_FIELD_NUMBER = 2;
    private static volatile Parser<Keyset> PARSER = null;
    public static final int PRIMARY_KEY_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private ProtobufList<Key> key_ = GeneratedMessageLite.emptyProtobufList();
    private int primaryKeyId_;

    public interface KeyOrBuilder extends MessageLiteOrBuilder {
        KeyData getKeyData();

        int getKeyId();

        OutputPrefixType getOutputPrefixType();

        int getOutputPrefixTypeValue();

        KeyStatusType getStatus();

        int getStatusValue();

        boolean hasKeyData();
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Keyset, Builder> implements KeysetOrBuilder {
        private Builder() {
            super(Keyset.DEFAULT_INSTANCE);
        }

        public int getPrimaryKeyId() {
            return ((Keyset) this.instance).getPrimaryKeyId();
        }

        public Builder setPrimaryKeyId(int i) {
            copyOnWrite();
            ((Keyset) this.instance).setPrimaryKeyId(i);
            return this;
        }

        public Builder clearPrimaryKeyId() {
            copyOnWrite();
            ((Keyset) this.instance).clearPrimaryKeyId();
            return this;
        }

        public List<Key> getKeyList() {
            return Collections.unmodifiableList(((Keyset) this.instance).getKeyList());
        }

        public int getKeyCount() {
            return ((Keyset) this.instance).getKeyCount();
        }

        public Key getKey(int i) {
            return ((Keyset) this.instance).getKey(i);
        }

        public Builder setKey(int i, Key key) {
            copyOnWrite();
            ((Keyset) this.instance).setKey(i, key);
            return this;
        }

        public Builder setKey(int i, Builder builder) {
            copyOnWrite();
            ((Keyset) this.instance).setKey(i, builder);
            return this;
        }

        public Builder addKey(Key key) {
            copyOnWrite();
            ((Keyset) this.instance).addKey(key);
            return this;
        }

        public Builder addKey(int i, Key key) {
            copyOnWrite();
            ((Keyset) this.instance).addKey(i, key);
            return this;
        }

        public Builder addKey(Builder builder) {
            copyOnWrite();
            ((Keyset) this.instance).addKey(builder);
            return this;
        }

        public Builder addKey(int i, Builder builder) {
            copyOnWrite();
            ((Keyset) this.instance).addKey(i, builder);
            return this;
        }

        public Builder addAllKey(Iterable<? extends Key> iterable) {
            copyOnWrite();
            ((Keyset) this.instance).addAllKey(iterable);
            return this;
        }

        public Builder clearKey() {
            copyOnWrite();
            ((Keyset) this.instance).clearKey();
            return this;
        }

        public Builder removeKey(int i) {
            copyOnWrite();
            ((Keyset) this.instance).removeKey(i);
            return this;
        }
    }

    public static final class Key extends GeneratedMessageLite<Key, Builder> implements KeyOrBuilder {
        private static final Key DEFAULT_INSTANCE = new Key();
        public static final int KEY_DATA_FIELD_NUMBER = 1;
        public static final int KEY_ID_FIELD_NUMBER = 3;
        public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 4;
        private static volatile Parser<Key> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        private KeyData keyData_;
        private int keyId_;
        private int outputPrefixType_;
        private int status_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Key, Builder> implements KeyOrBuilder {
            private Builder() {
                super(Key.DEFAULT_INSTANCE);
            }

            public boolean hasKeyData() {
                return ((Key) this.instance).hasKeyData();
            }

            public KeyData getKeyData() {
                return ((Key) this.instance).getKeyData();
            }

            public Builder setKeyData(KeyData keyData) {
                copyOnWrite();
                ((Key) this.instance).setKeyData(keyData);
                return this;
            }

            public Builder setKeyData(com.google.crypto.tink.proto.KeyData.Builder builder) {
                copyOnWrite();
                ((Key) this.instance).setKeyData(builder);
                return this;
            }

            public Builder mergeKeyData(KeyData keyData) {
                copyOnWrite();
                ((Key) this.instance).mergeKeyData(keyData);
                return this;
            }

            public Builder clearKeyData() {
                copyOnWrite();
                ((Key) this.instance).clearKeyData();
                return this;
            }

            public int getStatusValue() {
                return ((Key) this.instance).getStatusValue();
            }

            public Builder setStatusValue(int i) {
                copyOnWrite();
                ((Key) this.instance).setStatusValue(i);
                return this;
            }

            public KeyStatusType getStatus() {
                return ((Key) this.instance).getStatus();
            }

            public Builder setStatus(KeyStatusType keyStatusType) {
                copyOnWrite();
                ((Key) this.instance).setStatus(keyStatusType);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((Key) this.instance).clearStatus();
                return this;
            }

            public int getKeyId() {
                return ((Key) this.instance).getKeyId();
            }

            public Builder setKeyId(int i) {
                copyOnWrite();
                ((Key) this.instance).setKeyId(i);
                return this;
            }

            public Builder clearKeyId() {
                copyOnWrite();
                ((Key) this.instance).clearKeyId();
                return this;
            }

            public int getOutputPrefixTypeValue() {
                return ((Key) this.instance).getOutputPrefixTypeValue();
            }

            public Builder setOutputPrefixTypeValue(int i) {
                copyOnWrite();
                ((Key) this.instance).setOutputPrefixTypeValue(i);
                return this;
            }

            public OutputPrefixType getOutputPrefixType() {
                return ((Key) this.instance).getOutputPrefixType();
            }

            public Builder setOutputPrefixType(OutputPrefixType outputPrefixType) {
                copyOnWrite();
                ((Key) this.instance).setOutputPrefixType(outputPrefixType);
                return this;
            }

            public Builder clearOutputPrefixType() {
                copyOnWrite();
                ((Key) this.instance).clearOutputPrefixType();
                return this;
            }
        }

        private Key() {
        }

        public boolean hasKeyData() {
            return this.keyData_ != null;
        }

        public KeyData getKeyData() {
            return this.keyData_ == null ? KeyData.getDefaultInstance() : this.keyData_;
        }

        private void setKeyData(KeyData keyData) {
            if (keyData != null) {
                this.keyData_ = keyData;
                return;
            }
            throw new NullPointerException();
        }

        private void setKeyData(com.google.crypto.tink.proto.KeyData.Builder builder) {
            this.keyData_ = (KeyData) builder.build();
        }

        private void mergeKeyData(KeyData keyData) {
            if (this.keyData_ == null || this.keyData_ == KeyData.getDefaultInstance()) {
                this.keyData_ = keyData;
            } else {
                this.keyData_ = (KeyData) ((com.google.crypto.tink.proto.KeyData.Builder) KeyData.newBuilder(this.keyData_).mergeFrom(keyData)).buildPartial();
            }
        }

        private void clearKeyData() {
            this.keyData_ = null;
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
            if (this.keyData_ != null) {
                codedOutputStream.writeMessage(1, getKeyData());
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
            if (this.keyData_ != null) {
                i = 0 + CodedOutputStream.computeMessageSize(1, getKeyData());
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

        public static Key parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Key parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Key parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Key parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Key parseFrom(InputStream inputStream) throws IOException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Key parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Key parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Key) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Key parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Key) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Key parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Key parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Key) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Key key) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(key);
        }

        protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new Key();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    Key key = (Key) obj2;
                    this.keyData_ = (KeyData) visitor.visitMessage(this.keyData_, key.keyData_);
                    this.status_ = visitor.visitInt(this.status_ != 0, this.status_, key.status_ != 0, key.status_);
                    this.keyId_ = visitor.visitInt(this.keyId_ != 0, this.keyId_, key.keyId_ != 0, key.keyId_);
                    boolean z2 = this.outputPrefixType_ != 0;
                    int i = this.outputPrefixType_;
                    if (key.outputPrefixType_ != 0) {
                        z = true;
                    }
                    this.outputPrefixType_ = visitor.visitInt(z2, i, z, key.outputPrefixType_);
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
                                    com.google.crypto.tink.proto.KeyData.Builder builder = this.keyData_ != null ? (com.google.crypto.tink.proto.KeyData.Builder) this.keyData_.toBuilder() : null;
                                    this.keyData_ = (KeyData) codedInputStream.readMessage(KeyData.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.keyData_);
                                        this.keyData_ = (KeyData) builder.buildPartial();
                                    }
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
                        synchronized (Key.class) {
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

        public static Key getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Key> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    private Keyset() {
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

    public List<Key> getKeyList() {
        return this.key_;
    }

    public List<? extends KeyOrBuilder> getKeyOrBuilderList() {
        return this.key_;
    }

    public int getKeyCount() {
        return this.key_.size();
    }

    public Key getKey(int i) {
        return (Key) this.key_.get(i);
    }

    public KeyOrBuilder getKeyOrBuilder(int i) {
        return (KeyOrBuilder) this.key_.get(i);
    }

    private void ensureKeyIsMutable() {
        if (!this.key_.isModifiable()) {
            this.key_ = GeneratedMessageLite.mutableCopy(this.key_);
        }
    }

    private void setKey(int i, Key key) {
        if (key != null) {
            ensureKeyIsMutable();
            this.key_.set(i, key);
            return;
        }
        throw new NullPointerException();
    }

    private void setKey(int i, Builder builder) {
        ensureKeyIsMutable();
        this.key_.set(i, builder.build());
    }

    private void addKey(Key key) {
        if (key != null) {
            ensureKeyIsMutable();
            this.key_.add(key);
            return;
        }
        throw new NullPointerException();
    }

    private void addKey(int i, Key key) {
        if (key != null) {
            ensureKeyIsMutable();
            this.key_.add(i, key);
            return;
        }
        throw new NullPointerException();
    }

    private void addKey(Builder builder) {
        ensureKeyIsMutable();
        this.key_.add(builder.build());
    }

    private void addKey(int i, Builder builder) {
        ensureKeyIsMutable();
        this.key_.add(i, builder.build());
    }

    private void addAllKey(Iterable<? extends Key> iterable) {
        ensureKeyIsMutable();
        AbstractMessageLite.addAll(iterable, this.key_);
    }

    private void clearKey() {
        this.key_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void removeKey(int i) {
        ensureKeyIsMutable();
        this.key_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.primaryKeyId_ != 0) {
            codedOutputStream.writeUInt32(1, this.primaryKeyId_);
        }
        for (int i = 0; i < this.key_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.key_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        i = this.primaryKeyId_ != 0 ? CodedOutputStream.computeUInt32Size(1, this.primaryKeyId_) + 0 : 0;
        while (i2 < this.key_.size()) {
            i += CodedOutputStream.computeMessageSize(2, (MessageLite) this.key_.get(i2));
            i2++;
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static Keyset parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Keyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Keyset parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Keyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Keyset parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Keyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Keyset parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Keyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Keyset parseFrom(InputStream inputStream) throws IOException {
        return (Keyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Keyset parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Keyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Keyset parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Keyset) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Keyset parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Keyset) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Keyset parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Keyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Keyset parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Keyset) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Keyset keyset) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(keyset);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new Keyset();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.key_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                Keyset keyset = (Keyset) obj2;
                boolean z2 = this.primaryKeyId_ != 0;
                int i = this.primaryKeyId_;
                if (keyset.primaryKeyId_ != 0) {
                    z = true;
                }
                this.primaryKeyId_ = visitor.visitInt(z2, i, z, keyset.primaryKeyId_);
                this.key_ = visitor.visitList(this.key_, keyset.key_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= keyset.bitField0_;
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
                                if (!this.key_.isModifiable()) {
                                    this.key_ = GeneratedMessageLite.mutableCopy(this.key_);
                                }
                                this.key_.add(codedInputStream.readMessage(Key.parser(), extensionRegistryLite));
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
                    synchronized (Keyset.class) {
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

    public static Keyset getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Keyset> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
