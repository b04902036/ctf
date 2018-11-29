package com.google.crypto.tink.proto;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class KeyData extends GeneratedMessageLite<KeyData, Builder> implements KeyDataOrBuilder {
    private static final KeyData DEFAULT_INSTANCE = new KeyData();
    public static final int KEY_MATERIAL_TYPE_FIELD_NUMBER = 3;
    private static volatile Parser<KeyData> PARSER = null;
    public static final int TYPE_URL_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    private int keyMaterialType_;
    private String typeUrl_ = "";
    private ByteString value_ = ByteString.EMPTY;

    public enum KeyMaterialType implements EnumLite {
        UNKNOWN_KEYMATERIAL(0),
        SYMMETRIC(1),
        ASYMMETRIC_PRIVATE(2),
        ASYMMETRIC_PUBLIC(3),
        REMOTE(4),
        UNRECOGNIZED(-1);
        
        public static final int ASYMMETRIC_PRIVATE_VALUE = 2;
        public static final int ASYMMETRIC_PUBLIC_VALUE = 3;
        public static final int REMOTE_VALUE = 4;
        public static final int SYMMETRIC_VALUE = 1;
        public static final int UNKNOWN_KEYMATERIAL_VALUE = 0;
        private static final EnumLiteMap<KeyMaterialType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<KeyMaterialType>() {
                public KeyMaterialType findValueByNumber(int i) {
                    return KeyMaterialType.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static KeyMaterialType valueOf(int i) {
            return forNumber(i);
        }

        public static KeyMaterialType forNumber(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN_KEYMATERIAL;
                case 1:
                    return SYMMETRIC;
                case 2:
                    return ASYMMETRIC_PRIVATE;
                case 3:
                    return ASYMMETRIC_PUBLIC;
                case 4:
                    return REMOTE;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<KeyMaterialType> internalGetValueMap() {
            return internalValueMap;
        }

        private KeyMaterialType(int i) {
            this.value = i;
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<KeyData, Builder> implements KeyDataOrBuilder {
        private Builder() {
            super(KeyData.DEFAULT_INSTANCE);
        }

        public String getTypeUrl() {
            return ((KeyData) this.instance).getTypeUrl();
        }

        public ByteString getTypeUrlBytes() {
            return ((KeyData) this.instance).getTypeUrlBytes();
        }

        public Builder setTypeUrl(String str) {
            copyOnWrite();
            ((KeyData) this.instance).setTypeUrl(str);
            return this;
        }

        public Builder clearTypeUrl() {
            copyOnWrite();
            ((KeyData) this.instance).clearTypeUrl();
            return this;
        }

        public Builder setTypeUrlBytes(ByteString byteString) {
            copyOnWrite();
            ((KeyData) this.instance).setTypeUrlBytes(byteString);
            return this;
        }

        public ByteString getValue() {
            return ((KeyData) this.instance).getValue();
        }

        public Builder setValue(ByteString byteString) {
            copyOnWrite();
            ((KeyData) this.instance).setValue(byteString);
            return this;
        }

        public Builder clearValue() {
            copyOnWrite();
            ((KeyData) this.instance).clearValue();
            return this;
        }

        public int getKeyMaterialTypeValue() {
            return ((KeyData) this.instance).getKeyMaterialTypeValue();
        }

        public Builder setKeyMaterialTypeValue(int i) {
            copyOnWrite();
            ((KeyData) this.instance).setKeyMaterialTypeValue(i);
            return this;
        }

        public KeyMaterialType getKeyMaterialType() {
            return ((KeyData) this.instance).getKeyMaterialType();
        }

        public Builder setKeyMaterialType(KeyMaterialType keyMaterialType) {
            copyOnWrite();
            ((KeyData) this.instance).setKeyMaterialType(keyMaterialType);
            return this;
        }

        public Builder clearKeyMaterialType() {
            copyOnWrite();
            ((KeyData) this.instance).clearKeyMaterialType();
            return this;
        }
    }

    private KeyData() {
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

    public ByteString getValue() {
        return this.value_;
    }

    private void setValue(ByteString byteString) {
        if (byteString != null) {
            this.value_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    private void clearValue() {
        this.value_ = getDefaultInstance().getValue();
    }

    public int getKeyMaterialTypeValue() {
        return this.keyMaterialType_;
    }

    public KeyMaterialType getKeyMaterialType() {
        KeyMaterialType forNumber = KeyMaterialType.forNumber(this.keyMaterialType_);
        return forNumber == null ? KeyMaterialType.UNRECOGNIZED : forNumber;
    }

    private void setKeyMaterialTypeValue(int i) {
        this.keyMaterialType_ = i;
    }

    private void setKeyMaterialType(KeyMaterialType keyMaterialType) {
        if (keyMaterialType != null) {
            this.keyMaterialType_ = keyMaterialType.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    private void clearKeyMaterialType() {
        this.keyMaterialType_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.typeUrl_.isEmpty()) {
            codedOutputStream.writeString(1, getTypeUrl());
        }
        if (!this.value_.isEmpty()) {
            codedOutputStream.writeBytes(2, this.value_);
        }
        if (this.keyMaterialType_ != KeyMaterialType.UNKNOWN_KEYMATERIAL.getNumber()) {
            codedOutputStream.writeEnum(3, this.keyMaterialType_);
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
        if (!this.value_.isEmpty()) {
            i += CodedOutputStream.computeBytesSize(2, this.value_);
        }
        if (this.keyMaterialType_ != KeyMaterialType.UNKNOWN_KEYMATERIAL.getNumber()) {
            i += CodedOutputStream.computeEnumSize(3, this.keyMaterialType_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static KeyData parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (KeyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static KeyData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KeyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static KeyData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (KeyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static KeyData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KeyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static KeyData parseFrom(InputStream inputStream) throws IOException {
        return (KeyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KeyData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KeyData parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (KeyData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KeyData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeyData) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KeyData parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (KeyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static KeyData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeyData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KeyData keyData) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(keyData);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new KeyData();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                KeyData keyData = (KeyData) obj2;
                this.typeUrl_ = visitor.visitString(this.typeUrl_.isEmpty() ^ true, this.typeUrl_, keyData.typeUrl_.isEmpty() ^ true, keyData.typeUrl_);
                this.value_ = visitor.visitByteString(this.value_ != ByteString.EMPTY, this.value_, keyData.value_ != ByteString.EMPTY, keyData.value_);
                boolean z2 = this.keyMaterialType_ != 0;
                int i = this.keyMaterialType_;
                if (keyData.keyMaterialType_ != 0) {
                    z = true;
                }
                this.keyMaterialType_ = visitor.visitInt(z2, i, z, keyData.keyMaterialType_);
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
                            } else if (readTag == 18) {
                                this.value_ = codedInputStream.readBytes();
                            } else if (readTag == 24) {
                                this.keyMaterialType_ = codedInputStream.readEnum();
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
                    synchronized (KeyData.class) {
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

    public static KeyData getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KeyData> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
