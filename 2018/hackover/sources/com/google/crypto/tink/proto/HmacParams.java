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

public final class HmacParams extends GeneratedMessageLite<HmacParams, Builder> implements HmacParamsOrBuilder {
    private static final HmacParams DEFAULT_INSTANCE = new HmacParams();
    public static final int HASH_FIELD_NUMBER = 1;
    private static volatile Parser<HmacParams> PARSER = null;
    public static final int TAG_SIZE_FIELD_NUMBER = 2;
    private int hash_;
    private int tagSize_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<HmacParams, Builder> implements HmacParamsOrBuilder {
        private Builder() {
            super(HmacParams.DEFAULT_INSTANCE);
        }

        public int getHashValue() {
            return ((HmacParams) this.instance).getHashValue();
        }

        public Builder setHashValue(int i) {
            copyOnWrite();
            ((HmacParams) this.instance).setHashValue(i);
            return this;
        }

        public HashType getHash() {
            return ((HmacParams) this.instance).getHash();
        }

        public Builder setHash(HashType hashType) {
            copyOnWrite();
            ((HmacParams) this.instance).setHash(hashType);
            return this;
        }

        public Builder clearHash() {
            copyOnWrite();
            ((HmacParams) this.instance).clearHash();
            return this;
        }

        public int getTagSize() {
            return ((HmacParams) this.instance).getTagSize();
        }

        public Builder setTagSize(int i) {
            copyOnWrite();
            ((HmacParams) this.instance).setTagSize(i);
            return this;
        }

        public Builder clearTagSize() {
            copyOnWrite();
            ((HmacParams) this.instance).clearTagSize();
            return this;
        }
    }

    private HmacParams() {
    }

    public int getHashValue() {
        return this.hash_;
    }

    public HashType getHash() {
        HashType forNumber = HashType.forNumber(this.hash_);
        return forNumber == null ? HashType.UNRECOGNIZED : forNumber;
    }

    private void setHashValue(int i) {
        this.hash_ = i;
    }

    private void setHash(HashType hashType) {
        if (hashType != null) {
            this.hash_ = hashType.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    private void clearHash() {
        this.hash_ = 0;
    }

    public int getTagSize() {
        return this.tagSize_;
    }

    private void setTagSize(int i) {
        this.tagSize_ = i;
    }

    private void clearTagSize() {
        this.tagSize_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.hash_ != HashType.UNKNOWN_HASH.getNumber()) {
            codedOutputStream.writeEnum(1, this.hash_);
        }
        if (this.tagSize_ != 0) {
            codedOutputStream.writeUInt32(2, this.tagSize_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        i = 0;
        if (this.hash_ != HashType.UNKNOWN_HASH.getNumber()) {
            i = 0 + CodedOutputStream.computeEnumSize(1, this.hash_);
        }
        if (this.tagSize_ != 0) {
            i += CodedOutputStream.computeUInt32Size(2, this.tagSize_);
        }
        this.memoizedSerializedSize = i;
        return i;
    }

    public static HmacParams parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static HmacParams parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static HmacParams parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static HmacParams parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static HmacParams parseFrom(InputStream inputStream) throws IOException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HmacParams parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HmacParams parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HmacParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HmacParams parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HmacParams) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HmacParams parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static HmacParams parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HmacParams) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HmacParams hmacParams) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(hmacParams);
    }

    protected final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new HmacParams();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                HmacParams hmacParams = (HmacParams) obj2;
                this.hash_ = visitor.visitInt(this.hash_ != 0, this.hash_, hmacParams.hash_ != 0, hmacParams.hash_);
                boolean z2 = this.tagSize_ != 0;
                int i = this.tagSize_;
                if (hmacParams.tagSize_ != 0) {
                    z = true;
                }
                this.tagSize_ = visitor.visitInt(z2, i, z, hmacParams.tagSize_);
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
                                this.hash_ = codedInputStream.readEnum();
                            } else if (readTag == 16) {
                                this.tagSize_ = codedInputStream.readUInt32();
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
                    synchronized (HmacParams.class) {
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

    public static HmacParams getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HmacParams> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
