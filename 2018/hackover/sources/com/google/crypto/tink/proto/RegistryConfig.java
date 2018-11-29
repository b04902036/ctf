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
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class RegistryConfig extends GeneratedMessageLite<RegistryConfig, Builder> implements RegistryConfigOrBuilder {
    public static final int CONFIG_NAME_FIELD_NUMBER = 1;
    private static final RegistryConfig DEFAULT_INSTANCE = new RegistryConfig();
    public static final int ENTRY_FIELD_NUMBER = 2;
    private static volatile Parser<RegistryConfig> PARSER;
    private int bitField0_;
    private String configName_ = "";
    private ProtobufList<KeyTypeEntry> entry_ = GeneratedMessageLite.emptyProtobufList();

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<RegistryConfig, Builder> implements RegistryConfigOrBuilder {
        private Builder() {
            super(RegistryConfig.DEFAULT_INSTANCE);
        }

        public String getConfigName() {
            return ((RegistryConfig) this.instance).getConfigName();
        }

        public ByteString getConfigNameBytes() {
            return ((RegistryConfig) this.instance).getConfigNameBytes();
        }

        public Builder setConfigName(String str) {
            copyOnWrite();
            ((RegistryConfig) this.instance).setConfigName(str);
            return this;
        }

        public Builder clearConfigName() {
            copyOnWrite();
            ((RegistryConfig) this.instance).clearConfigName();
            return this;
        }

        public Builder setConfigNameBytes(ByteString byteString) {
            copyOnWrite();
            ((RegistryConfig) this.instance).setConfigNameBytes(byteString);
            return this;
        }

        public List<KeyTypeEntry> getEntryList() {
            return Collections.unmodifiableList(((RegistryConfig) this.instance).getEntryList());
        }

        public int getEntryCount() {
            return ((RegistryConfig) this.instance).getEntryCount();
        }

        public KeyTypeEntry getEntry(int i) {
            return ((RegistryConfig) this.instance).getEntry(i);
        }

        public Builder setEntry(int i, KeyTypeEntry keyTypeEntry) {
            copyOnWrite();
            ((RegistryConfig) this.instance).setEntry(i, keyTypeEntry);
            return this;
        }

        public Builder setEntry(int i, com.google.crypto.tink.proto.KeyTypeEntry.Builder builder) {
            copyOnWrite();
            ((RegistryConfig) this.instance).setEntry(i, builder);
            return this;
        }

        public Builder addEntry(KeyTypeEntry keyTypeEntry) {
            copyOnWrite();
            ((RegistryConfig) this.instance).addEntry(keyTypeEntry);
            return this;
        }

        public Builder addEntry(int i, KeyTypeEntry keyTypeEntry) {
            copyOnWrite();
            ((RegistryConfig) this.instance).addEntry(i, keyTypeEntry);
            return this;
        }

        public Builder addEntry(com.google.crypto.tink.proto.KeyTypeEntry.Builder builder) {
            copyOnWrite();
            ((RegistryConfig) this.instance).addEntry(builder);
            return this;
        }

        public Builder addEntry(int i, com.google.crypto.tink.proto.KeyTypeEntry.Builder builder) {
            copyOnWrite();
            ((RegistryConfig) this.instance).addEntry(i, builder);
            return this;
        }

        public Builder addAllEntry(Iterable<? extends KeyTypeEntry> iterable) {
            copyOnWrite();
            ((RegistryConfig) this.instance).addAllEntry(iterable);
            return this;
        }

        public Builder clearEntry() {
            copyOnWrite();
            ((RegistryConfig) this.instance).clearEntry();
            return this;
        }

        public Builder removeEntry(int i) {
            copyOnWrite();
            ((RegistryConfig) this.instance).removeEntry(i);
            return this;
        }
    }

    private RegistryConfig() {
    }

    public String getConfigName() {
        return this.configName_;
    }

    public ByteString getConfigNameBytes() {
        return ByteString.copyFromUtf8(this.configName_);
    }

    private void setConfigName(String str) {
        if (str != null) {
            this.configName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    private void clearConfigName() {
        this.configName_ = getDefaultInstance().getConfigName();
    }

    private void setConfigNameBytes(ByteString byteString) {
        if (byteString != null) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.configName_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public List<KeyTypeEntry> getEntryList() {
        return this.entry_;
    }

    public List<? extends KeyTypeEntryOrBuilder> getEntryOrBuilderList() {
        return this.entry_;
    }

    public int getEntryCount() {
        return this.entry_.size();
    }

    public KeyTypeEntry getEntry(int i) {
        return (KeyTypeEntry) this.entry_.get(i);
    }

    public KeyTypeEntryOrBuilder getEntryOrBuilder(int i) {
        return (KeyTypeEntryOrBuilder) this.entry_.get(i);
    }

    private void ensureEntryIsMutable() {
        if (!this.entry_.isModifiable()) {
            this.entry_ = GeneratedMessageLite.mutableCopy(this.entry_);
        }
    }

    private void setEntry(int i, KeyTypeEntry keyTypeEntry) {
        if (keyTypeEntry != null) {
            ensureEntryIsMutable();
            this.entry_.set(i, keyTypeEntry);
            return;
        }
        throw new NullPointerException();
    }

    private void setEntry(int i, com.google.crypto.tink.proto.KeyTypeEntry.Builder builder) {
        ensureEntryIsMutable();
        this.entry_.set(i, builder.build());
    }

    private void addEntry(KeyTypeEntry keyTypeEntry) {
        if (keyTypeEntry != null) {
            ensureEntryIsMutable();
            this.entry_.add(keyTypeEntry);
            return;
        }
        throw new NullPointerException();
    }

    private void addEntry(int i, KeyTypeEntry keyTypeEntry) {
        if (keyTypeEntry != null) {
            ensureEntryIsMutable();
            this.entry_.add(i, keyTypeEntry);
            return;
        }
        throw new NullPointerException();
    }

    private void addEntry(com.google.crypto.tink.proto.KeyTypeEntry.Builder builder) {
        ensureEntryIsMutable();
        this.entry_.add(builder.build());
    }

    private void addEntry(int i, com.google.crypto.tink.proto.KeyTypeEntry.Builder builder) {
        ensureEntryIsMutable();
        this.entry_.add(i, builder.build());
    }

    private void addAllEntry(Iterable<? extends KeyTypeEntry> iterable) {
        ensureEntryIsMutable();
        AbstractMessageLite.addAll(iterable, this.entry_);
    }

    private void clearEntry() {
        this.entry_ = GeneratedMessageLite.emptyProtobufList();
    }

    private void removeEntry(int i) {
        ensureEntryIsMutable();
        this.entry_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.configName_.isEmpty()) {
            codedOutputStream.writeString(1, getConfigName());
        }
        for (int i = 0; i < this.entry_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.entry_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        i = !this.configName_.isEmpty() ? CodedOutputStream.computeStringSize(1, getConfigName()) + 0 : 0;
        while (i2 < this.entry_.size()) {
            i += CodedOutputStream.computeMessageSize(2, (MessageLite) this.entry_.get(i2));
            i2++;
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static RegistryConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RegistryConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RegistryConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RegistryConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RegistryConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RegistryConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RegistryConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RegistryConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RegistryConfig parseFrom(InputStream inputStream) throws IOException {
        return (RegistryConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RegistryConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RegistryConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RegistryConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RegistryConfig) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RegistryConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RegistryConfig) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RegistryConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RegistryConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RegistryConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RegistryConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RegistryConfig registryConfig) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(registryConfig);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new RegistryConfig();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.entry_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                RegistryConfig registryConfig = (RegistryConfig) obj2;
                this.configName_ = visitor.visitString(this.configName_.isEmpty() ^ true, this.configName_, true ^ registryConfig.configName_.isEmpty(), registryConfig.configName_);
                this.entry_ = visitor.visitList(this.entry_, registryConfig.entry_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= registryConfig.bitField0_;
                }
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
                                this.configName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                if (!this.entry_.isModifiable()) {
                                    this.entry_ = GeneratedMessageLite.mutableCopy(this.entry_);
                                }
                                this.entry_.add(codedInputStream.readMessage(KeyTypeEntry.parser(), extensionRegistryLite));
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
                    synchronized (RegistryConfig.class) {
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

    public static RegistryConfig getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RegistryConfig> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
