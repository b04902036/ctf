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

public final class KmsEnvelopeAeadKeyFormat extends GeneratedMessageLite<KmsEnvelopeAeadKeyFormat, Builder> implements KmsEnvelopeAeadKeyFormatOrBuilder {
    private static final KmsEnvelopeAeadKeyFormat DEFAULT_INSTANCE = new KmsEnvelopeAeadKeyFormat();
    public static final int DEK_TEMPLATE_FIELD_NUMBER = 2;
    public static final int KEK_URI_FIELD_NUMBER = 1;
    private static volatile Parser<KmsEnvelopeAeadKeyFormat> PARSER;
    private KeyTemplate dekTemplate_;
    private String kekUri_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<KmsEnvelopeAeadKeyFormat, Builder> implements KmsEnvelopeAeadKeyFormatOrBuilder {
        private Builder() {
            super(KmsEnvelopeAeadKeyFormat.DEFAULT_INSTANCE);
        }

        public String getKekUri() {
            return ((KmsEnvelopeAeadKeyFormat) this.instance).getKekUri();
        }

        public ByteString getKekUriBytes() {
            return ((KmsEnvelopeAeadKeyFormat) this.instance).getKekUriBytes();
        }

        public Builder setKekUri(String str) {
            copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat) this.instance).setKekUri(str);
            return this;
        }

        public Builder clearKekUri() {
            copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat) this.instance).clearKekUri();
            return this;
        }

        public Builder setKekUriBytes(ByteString byteString) {
            copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat) this.instance).setKekUriBytes(byteString);
            return this;
        }

        public boolean hasDekTemplate() {
            return ((KmsEnvelopeAeadKeyFormat) this.instance).hasDekTemplate();
        }

        public KeyTemplate getDekTemplate() {
            return ((KmsEnvelopeAeadKeyFormat) this.instance).getDekTemplate();
        }

        public Builder setDekTemplate(KeyTemplate keyTemplate) {
            copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat) this.instance).setDekTemplate(keyTemplate);
            return this;
        }

        public Builder setDekTemplate(com.google.crypto.tink.proto.KeyTemplate.Builder builder) {
            copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat) this.instance).setDekTemplate(builder);
            return this;
        }

        public Builder mergeDekTemplate(KeyTemplate keyTemplate) {
            copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat) this.instance).mergeDekTemplate(keyTemplate);
            return this;
        }

        public Builder clearDekTemplate() {
            copyOnWrite();
            ((KmsEnvelopeAeadKeyFormat) this.instance).clearDekTemplate();
            return this;
        }
    }

    private KmsEnvelopeAeadKeyFormat() {
    }

    public String getKekUri() {
        return this.kekUri_;
    }

    public ByteString getKekUriBytes() {
        return ByteString.copyFromUtf8(this.kekUri_);
    }

    private void setKekUri(String str) {
        if (str != null) {
            this.kekUri_ = str;
            return;
        }
        throw new NullPointerException();
    }

    private void clearKekUri() {
        this.kekUri_ = getDefaultInstance().getKekUri();
    }

    private void setKekUriBytes(ByteString byteString) {
        if (byteString != null) {
            AbstractMessageLite.checkByteStringIsUtf8(byteString);
            this.kekUri_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasDekTemplate() {
        return this.dekTemplate_ != null;
    }

    public KeyTemplate getDekTemplate() {
        return this.dekTemplate_ == null ? KeyTemplate.getDefaultInstance() : this.dekTemplate_;
    }

    private void setDekTemplate(KeyTemplate keyTemplate) {
        if (keyTemplate != null) {
            this.dekTemplate_ = keyTemplate;
            return;
        }
        throw new NullPointerException();
    }

    private void setDekTemplate(com.google.crypto.tink.proto.KeyTemplate.Builder builder) {
        this.dekTemplate_ = (KeyTemplate) builder.build();
    }

    private void mergeDekTemplate(KeyTemplate keyTemplate) {
        if (this.dekTemplate_ == null || this.dekTemplate_ == KeyTemplate.getDefaultInstance()) {
            this.dekTemplate_ = keyTemplate;
        } else {
            this.dekTemplate_ = (KeyTemplate) ((com.google.crypto.tink.proto.KeyTemplate.Builder) KeyTemplate.newBuilder(this.dekTemplate_).mergeFrom(keyTemplate)).buildPartial();
        }
    }

    private void clearDekTemplate() {
        this.dekTemplate_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.kekUri_.isEmpty()) {
            codedOutputStream.writeString(1, getKekUri());
        }
        if (this.dekTemplate_ != null) {
            codedOutputStream.writeMessage(2, getDekTemplate());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (!this.kekUri_.isEmpty()) {
            i = 0 + CodedOutputStream.computeStringSize(1, getKekUri());
        }
        if (this.dekTemplate_ != null) {
            i += CodedOutputStream.computeMessageSize(2, getDekTemplate());
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (KmsEnvelopeAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(InputStream inputStream) throws IOException {
        return (KmsEnvelopeAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsEnvelopeAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KmsEnvelopeAeadKeyFormat parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (KmsEnvelopeAeadKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static KmsEnvelopeAeadKeyFormat parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsEnvelopeAeadKeyFormat) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (KmsEnvelopeAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static KmsEnvelopeAeadKeyFormat parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (KmsEnvelopeAeadKeyFormat) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(kmsEnvelopeAeadKeyFormat);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new KmsEnvelopeAeadKeyFormat();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                KmsEnvelopeAeadKeyFormat kmsEnvelopeAeadKeyFormat = (KmsEnvelopeAeadKeyFormat) obj2;
                this.kekUri_ = visitor.visitString(this.kekUri_.isEmpty() ^ true, this.kekUri_, true ^ kmsEnvelopeAeadKeyFormat.kekUri_.isEmpty(), kmsEnvelopeAeadKeyFormat.kekUri_);
                this.dekTemplate_ = (KeyTemplate) visitor.visitMessage(this.dekTemplate_, kmsEnvelopeAeadKeyFormat.dekTemplate_);
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
                                this.kekUri_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                com.google.crypto.tink.proto.KeyTemplate.Builder builder = this.dekTemplate_ != null ? (com.google.crypto.tink.proto.KeyTemplate.Builder) this.dekTemplate_.toBuilder() : null;
                                this.dekTemplate_ = (KeyTemplate) codedInputStream.readMessage(KeyTemplate.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.dekTemplate_);
                                    this.dekTemplate_ = (KeyTemplate) builder.buildPartial();
                                }
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
                    synchronized (KmsEnvelopeAeadKeyFormat.class) {
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

    public static KmsEnvelopeAeadKeyFormat getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<KmsEnvelopeAeadKeyFormat> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
