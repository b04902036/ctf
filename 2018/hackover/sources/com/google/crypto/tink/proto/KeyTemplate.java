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

public final class KeyTemplate extends GeneratedMessageLite<KeyTemplate, Builder> implements KeyTemplateOrBuilder {
    private static final KeyTemplate DEFAULT_INSTANCE = new KeyTemplate();
    public static final int OUTPUT_PREFIX_TYPE_FIELD_NUMBER = 3;
    private static volatile Parser<KeyTemplate> PARSER = null;
    public static final int TYPE_URL_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 2;
    private int outputPrefixType_;
    private String typeUrl_ = "";
    private ByteString value_ = ByteString.EMPTY;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<KeyTemplate, Builder> implements KeyTemplateOrBuilder {
        private Builder() {
            super(KeyTemplate.DEFAULT_INSTANCE);
        }

        public String getTypeUrl() {
            return ((KeyTemplate) this.instance).getTypeUrl();
        }

        public ByteString getTypeUrlBytes() {
            return ((KeyTemplate) this.instance).getTypeUrlBytes();
        }

        public Builder setTypeUrl(String str) {
            copyOnWrite();
            ((KeyTemplate) this.instance).setTypeUrl(str);
            return this;
        }

        public Builder clearTypeUrl() {
            copyOnWrite();
            ((KeyTemplate) this.instance).clearTypeUrl();
            return this;
        }

        public Builder setTypeUrlBytes(ByteString byteString) {
            copyOnWrite();
            ((KeyTemplate) this.instance).setTypeUrlBytes(byteString);
            return this;
        }

        public ByteString getValue() {
            return ((KeyTemplate) this.instance).getValue();
        }

        public Builder setValue(ByteString byteString) {
            copyOnWrite();
            ((KeyTemplate) this.instance).setValue(byteString);
            return this;
        }

        public Builder clearValue() {
            copyOnWrite();
            ((KeyTemplate) this.instance).clearValue();
            return this;
        }

        public int getOutputPrefixTypeValue() {
            return ((KeyTemplate) this.instance).getOutputPrefixTypeValue();
        }

        public Builder setOutputPrefixTypeValue(int i) {
            copyOnWrite();
            ((KeyTemplate) this.instance).setOutputPrefixTypeValue(i);
            return this;
        }

        public OutputPrefixType getOutputPrefixType() {
            return ((KeyTemplate) this.instance).getOutputPrefixType();
        }

        public Builder setOutputPrefixType(OutputPrefixType outputPrefixType) {
            copyOnWrite();
            ((KeyTemplate) this.instance).setOutputPrefixType(outputPrefixType);
            return this;
        }

        public Builder clearOutputPrefixType() {
            copyOnWrite();
            ((KeyTemplate) this.instance).clearOutputPrefixType();
            return this;
        }
    }

    private KeyTemplate() {
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
        if (!this.value_.isEmpty()) {
            codedOutputStream.writeBytes(2, this.value_);
        }
        if (this.outputPrefixType_ != OutputPrefixType.UNKNOWN_PREFIX.getNumber()) {
            codedOutputStream.writeEnum(3, this.outputPrefixType_);
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
        if (this.outputPrefixType_ != OutputPrefixType.UNKNOWN_PREFIX.getNumber()) {
            i += CodedOutputStream.computeEnumSize(3, this.outputPrefixType_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static KeyTemplate parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (KeyTemplate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static KeyTemplate parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KeyTemplate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static KeyTemplate parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (KeyTemplate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static KeyTemplate parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KeyTemplate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static KeyTemplate parseFrom(InputStream inputStream) throws IOException {
        return (KeyTemplate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KeyTemplate parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeyTemplate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KeyTemplate parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (KeyTemplate) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KeyTemplate parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeyTemplate) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KeyTemplate parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (KeyTemplate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static KeyTemplate parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KeyTemplate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KeyTemplate keyTemplate) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(keyTemplate);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new KeyTemplate();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                KeyTemplate keyTemplate = (KeyTemplate) obj2;
                this.typeUrl_ = visitor.visitString(this.typeUrl_.isEmpty() ^ true, this.typeUrl_, keyTemplate.typeUrl_.isEmpty() ^ true, keyTemplate.typeUrl_);
                this.value_ = visitor.visitByteString(this.value_ != ByteString.EMPTY, this.value_, keyTemplate.value_ != ByteString.EMPTY, keyTemplate.value_);
                boolean z2 = this.outputPrefixType_ != 0;
                int i = this.outputPrefixType_;
                if (keyTemplate.outputPrefixType_ != 0) {
                    z = true;
                }
                this.outputPrefixType_ = visitor.visitInt(z2, i, z, keyTemplate.outputPrefixType_);
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
                    synchronized (KeyTemplate.class) {
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

    public static KeyTemplate getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KeyTemplate> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
