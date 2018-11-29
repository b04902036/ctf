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

public final class KeyTypeEntry extends GeneratedMessageLite<KeyTypeEntry, Builder> implements KeyTypeEntryOrBuilder {
    public static final int CATALOGUE_NAME_FIELD_NUMBER = 5;
    private static final KeyTypeEntry DEFAULT_INSTANCE = new KeyTypeEntry();
    public static final int KEY_MANAGER_VERSION_FIELD_NUMBER = 3;
    public static final int NEW_KEY_ALLOWED_FIELD_NUMBER = 4;
    private static volatile Parser<KeyTypeEntry> PARSER = null;
    public static final int PRIMITIVE_NAME_FIELD_NUMBER = 1;
    public static final int TYPE_URL_FIELD_NUMBER = 2;
    private String catalogueName_ = "";
    private int keyManagerVersion_;
    private boolean newKeyAllowed_;
    private String primitiveName_ = "";
    private String typeUrl_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<KeyTypeEntry, Builder> implements KeyTypeEntryOrBuilder {
        private Builder() {
            super(KeyTypeEntry.DEFAULT_INSTANCE);
        }

        public String getPrimitiveName() {
            return ((KeyTypeEntry) this.instance).getPrimitiveName();
        }

        public ByteString getPrimitiveNameBytes() {
            return ((KeyTypeEntry) this.instance).getPrimitiveNameBytes();
        }

        public Builder setPrimitiveName(String str) {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).setPrimitiveName(str);
            return this;
        }

        public Builder clearPrimitiveName() {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).clearPrimitiveName();
            return this;
        }

        public Builder setPrimitiveNameBytes(ByteString byteString) {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).setPrimitiveNameBytes(byteString);
            return this;
        }

        public String getTypeUrl() {
            return ((KeyTypeEntry) this.instance).getTypeUrl();
        }

        public ByteString getTypeUrlBytes() {
            return ((KeyTypeEntry) this.instance).getTypeUrlBytes();
        }

        public Builder setTypeUrl(String str) {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).setTypeUrl(str);
            return this;
        }

        public Builder clearTypeUrl() {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).clearTypeUrl();
            return this;
        }

        public Builder setTypeUrlBytes(ByteString byteString) {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).setTypeUrlBytes(byteString);
            return this;
        }

        public int getKeyManagerVersion() {
            return ((KeyTypeEntry) this.instance).getKeyManagerVersion();
        }

        public Builder setKeyManagerVersion(int i) {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).setKeyManagerVersion(i);
            return this;
        }

        public Builder clearKeyManagerVersion() {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).clearKeyManagerVersion();
            return this;
        }

        public boolean getNewKeyAllowed() {
            return ((KeyTypeEntry) this.instance).getNewKeyAllowed();
        }

        public Builder setNewKeyAllowed(boolean z) {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).setNewKeyAllowed(z);
            return this;
        }

        public Builder clearNewKeyAllowed() {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).clearNewKeyAllowed();
            return this;
        }

        public String getCatalogueName() {
            return ((KeyTypeEntry) this.instance).getCatalogueName();
        }

        public ByteString getCatalogueNameBytes() {
            return ((KeyTypeEntry) this.instance).getCatalogueNameBytes();
        }

        public Builder setCatalogueName(String str) {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).setCatalogueName(str);
            return this;
        }

        public Builder clearCatalogueName() {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).clearCatalogueName();
            return this;
        }

        public Builder setCatalogueNameBytes(ByteString byteString) {
            copyOnWrite();
            ((KeyTypeEntry) this.instance).setCatalogueNameBytes(byteString);
            return this;
        }
    }

    private KeyTypeEntry() {
    }

    public String getPrimitiveName() {
        return this.primitiveName_;
    }

    public ByteString getPrimitiveNameBytes() {
        return ByteString.copyFromUtf8(this.primitiveName_);
    }

    private void setPrimitiveName(String str) {
        if (str != null) {
            this.primitiveName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    private void clearPrimitiveName() {
        this.primitiveName_ = getDefaultInstance().getPrimitiveName();
    }

    private void setPrimitiveNameBytes(ByteString byteString) {
        if (byteString != null) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.primitiveName_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
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

    public int getKeyManagerVersion() {
        return this.keyManagerVersion_;
    }

    private void setKeyManagerVersion(int i) {
        this.keyManagerVersion_ = i;
    }

    private void clearKeyManagerVersion() {
        this.keyManagerVersion_ = 0;
    }

    public boolean getNewKeyAllowed() {
        return this.newKeyAllowed_;
    }

    private void setNewKeyAllowed(boolean z) {
        this.newKeyAllowed_ = z;
    }

    private void clearNewKeyAllowed() {
        this.newKeyAllowed_ = false;
    }

    public String getCatalogueName() {
        return this.catalogueName_;
    }

    public ByteString getCatalogueNameBytes() {
        return ByteString.copyFromUtf8(this.catalogueName_);
    }

    private void setCatalogueName(String str) {
        if (str != null) {
            this.catalogueName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    private void clearCatalogueName() {
        this.catalogueName_ = getDefaultInstance().getCatalogueName();
    }

    private void setCatalogueNameBytes(ByteString byteString) {
        if (byteString != null) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.catalogueName_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.primitiveName_.isEmpty()) {
            codedOutputStream.writeString(1, getPrimitiveName());
        }
        if (!this.typeUrl_.isEmpty()) {
            codedOutputStream.writeString(2, getTypeUrl());
        }
        if (this.keyManagerVersion_ != 0) {
            codedOutputStream.writeUInt32(3, this.keyManagerVersion_);
        }
        if (this.newKeyAllowed_) {
            codedOutputStream.writeBool(4, this.newKeyAllowed_);
        }
        if (!this.catalogueName_.isEmpty()) {
            codedOutputStream.writeString(5, getCatalogueName());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (!this.primitiveName_.isEmpty()) {
            i = 0 + CodedOutputStream.computeStringSize(1, getPrimitiveName());
        }
        if (!this.typeUrl_.isEmpty()) {
            i += CodedOutputStream.computeStringSize(2, getTypeUrl());
        }
        if (this.keyManagerVersion_ != 0) {
            i += CodedOutputStream.computeUInt32Size(3, this.keyManagerVersion_);
        }
        if (this.newKeyAllowed_) {
            i += CodedOutputStream.computeBoolSize(4, this.newKeyAllowed_);
        }
        if (!this.catalogueName_.isEmpty()) {
            i += CodedOutputStream.computeStringSize(5, getCatalogueName());
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static KeyTypeEntry parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (KeyTypeEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static KeyTypeEntry parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KeyTypeEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static KeyTypeEntry parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (KeyTypeEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static KeyTypeEntry parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KeyTypeEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static KeyTypeEntry parseFrom(InputStream inputStream) throws IOException {
        return (KeyTypeEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KeyTypeEntry parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeyTypeEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KeyTypeEntry parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (KeyTypeEntry) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KeyTypeEntry parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeyTypeEntry) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KeyTypeEntry parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (KeyTypeEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static KeyTypeEntry parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeyTypeEntry) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KeyTypeEntry keyTypeEntry) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(keyTypeEntry);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new KeyTypeEntry();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                KeyTypeEntry keyTypeEntry = (KeyTypeEntry) obj2;
                this.primitiveName_ = visitor.visitString(this.primitiveName_.isEmpty() ^ true, this.primitiveName_, keyTypeEntry.primitiveName_.isEmpty() ^ true, keyTypeEntry.primitiveName_);
                this.typeUrl_ = visitor.visitString(this.typeUrl_.isEmpty() ^ true, this.typeUrl_, keyTypeEntry.typeUrl_.isEmpty() ^ true, keyTypeEntry.typeUrl_);
                boolean z2 = this.keyManagerVersion_ != 0;
                int i = this.keyManagerVersion_;
                if (keyTypeEntry.keyManagerVersion_ != 0) {
                    z = true;
                }
                this.keyManagerVersion_ = visitor.visitInt(z2, i, z, keyTypeEntry.keyManagerVersion_);
                this.newKeyAllowed_ = visitor.visitBoolean(this.newKeyAllowed_, this.newKeyAllowed_, keyTypeEntry.newKeyAllowed_, keyTypeEntry.newKeyAllowed_);
                this.catalogueName_ = visitor.visitString(this.catalogueName_.isEmpty() ^ true, this.catalogueName_, keyTypeEntry.catalogueName_.isEmpty() ^ true, keyTypeEntry.catalogueName_);
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
                                this.primitiveName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.typeUrl_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 24) {
                                this.keyManagerVersion_ = codedInputStream.readUInt32();
                            } else if (readTag == 32) {
                                this.newKeyAllowed_ = codedInputStream.readBool();
                            } else if (readTag == 42) {
                                this.catalogueName_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (KeyTypeEntry.class) {
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

    public static KeyTypeEntry getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KeyTypeEntry> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
