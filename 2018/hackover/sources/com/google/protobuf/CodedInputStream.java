package com.google.protobuf;

import com.google.protobuf.MessageLite.Builder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CodedInputStream {
    private static final int BUFFER_SIZE = 4096;
    private static final int DEFAULT_RECURSION_LIMIT = 100;
    private static final int DEFAULT_SIZE_LIMIT = 67108864;
    private final byte[] buffer;
    private final boolean bufferIsImmutable;
    private int bufferPos;
    private int bufferSize;
    private int bufferSizeAfterLimit;
    private int currentLimit = Integer.MAX_VALUE;
    private boolean enableAliasing = false;
    private final InputStream input;
    private int lastTag;
    private int recursionDepth;
    private int recursionLimit = 100;
    private RefillCallback refillCallback = null;
    private int sizeLimit = DEFAULT_SIZE_LIMIT;
    private int totalBytesRetired;

    private interface RefillCallback {
        void onRefill();
    }

    private class SkippedDataSink implements RefillCallback {
        private ByteArrayOutputStream byteArrayStream;
        private int lastPos = CodedInputStream.this.bufferPos;

        private SkippedDataSink() {
        }

        public void onRefill() {
            if (this.byteArrayStream == null) {
                this.byteArrayStream = new ByteArrayOutputStream();
            }
            this.byteArrayStream.write(CodedInputStream.this.buffer, this.lastPos, CodedInputStream.this.bufferPos - this.lastPos);
            this.lastPos = 0;
        }

        ByteBuffer getSkippedData() {
            if (this.byteArrayStream == null) {
                return ByteBuffer.wrap(CodedInputStream.this.buffer, this.lastPos, CodedInputStream.this.bufferPos - this.lastPos);
            }
            this.byteArrayStream.write(CodedInputStream.this.buffer, this.lastPos, CodedInputStream.this.bufferPos);
            return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
        }
    }

    public static int decodeZigZag32(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long decodeZigZag64(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static CodedInputStream newInstance(InputStream inputStream) {
        return new CodedInputStream(inputStream, 4096);
    }

    static CodedInputStream newInstance(InputStream inputStream, int i) {
        return new CodedInputStream(inputStream, i);
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i, int i2) {
        return newInstance(bArr, i, i2, false);
    }

    static CodedInputStream newInstance(byte[] bArr, int i, int i2, boolean z) {
        CodedInputStream codedInputStream = new CodedInputStream(bArr, i, i2, z);
        try {
            codedInputStream.pushLimit(i2);
            return codedInputStream;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return newInstance(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
        }
        byteBuffer = byteBuffer.duplicate();
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return newInstance(bArr);
    }

    public int readTag() throws IOException {
        if (isAtEnd()) {
            this.lastTag = 0;
            return 0;
        }
        this.lastTag = readRawVarint32();
        if (WireFormat.getTagFieldNumber(this.lastTag) != 0) {
            return this.lastTag;
        }
        throw InvalidProtocolBufferException.invalidTag();
    }

    public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
        if (this.lastTag != i) {
            throw InvalidProtocolBufferException.invalidEndTag();
        }
    }

    public int getLastTag() {
        return this.lastTag;
    }

    public boolean skipField(int i) throws IOException {
        switch (WireFormat.getTagWireType(i)) {
            case 0:
                skipRawVarint();
                return true;
            case 1:
                skipRawBytes(8);
                return true;
            case 2:
                skipRawBytes(readRawVarint32());
                return true;
            case 3:
                skipMessage();
                checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                skipRawBytes(4);
                return true;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
        long readInt64;
        switch (WireFormat.getTagWireType(i)) {
            case 0:
                readInt64 = readInt64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeUInt64NoTag(readInt64);
                return true;
            case 1:
                readInt64 = readRawLittleEndian64();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed64NoTag(readInt64);
                return true;
            case 2:
                ByteString readBytes = readBytes();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeBytesNoTag(readBytes);
                return true;
            case 3:
                codedOutputStream.writeRawVarint32(i);
                skipMessage(codedOutputStream);
                i = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                checkLastTagWas(i);
                codedOutputStream.writeRawVarint32(i);
                return true;
            case 4:
                return false;
            case 5:
                int readRawLittleEndian32 = readRawLittleEndian32();
                codedOutputStream.writeRawVarint32(i);
                codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                return true;
            default:
                throw InvalidProtocolBufferException.invalidWireType();
        }
    }

    public void skipMessage() throws IOException {
        int readTag;
        do {
            readTag = readTag();
            if (readTag == 0) {
                return;
            }
        } while (skipField(readTag));
    }

    public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
        int readTag;
        do {
            readTag = readTag();
            if (readTag == 0) {
                return;
            }
        } while (skipField(readTag, codedOutputStream));
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readRawLittleEndian64());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readRawLittleEndian32());
    }

    public long readUInt64() throws IOException {
        return readRawVarint64();
    }

    public long readInt64() throws IOException {
        return readRawVarint64();
    }

    public int readInt32() throws IOException {
        return readRawVarint32();
    }

    public long readFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public int readFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public boolean readBool() throws IOException {
        return readRawVarint64() != 0;
    }

    public String readString() throws IOException {
        int readRawVarint32 = readRawVarint32();
        String str;
        if (readRawVarint32 <= this.bufferSize - this.bufferPos && readRawVarint32 > 0) {
            str = new String(this.buffer, this.bufferPos, readRawVarint32, Internal.UTF_8);
            this.bufferPos += readRawVarint32;
            return str;
        } else if (readRawVarint32 == 0) {
            return "";
        } else {
            if (readRawVarint32 > this.bufferSize) {
                return new String(readRawBytesSlowPath(readRawVarint32), Internal.UTF_8);
            }
            refillBuffer(readRawVarint32);
            str = new String(this.buffer, this.bufferPos, readRawVarint32, Internal.UTF_8);
            this.bufferPos += readRawVarint32;
            return str;
        }
    }

    public String readStringRequireUtf8() throws IOException {
        byte[] bArr;
        int readRawVarint32 = readRawVarint32();
        int i = this.bufferPos;
        int i2 = 0;
        if (readRawVarint32 <= this.bufferSize - i && readRawVarint32 > 0) {
            bArr = this.buffer;
            this.bufferPos = i + readRawVarint32;
            i2 = i;
        } else if (readRawVarint32 == 0) {
            return "";
        } else {
            if (readRawVarint32 <= this.bufferSize) {
                refillBuffer(readRawVarint32);
                bArr = this.buffer;
                this.bufferPos = readRawVarint32 + 0;
            } else {
                bArr = readRawBytesSlowPath(readRawVarint32);
            }
        }
        if (Utf8.isValidUtf8(bArr, i2, i2 + readRawVarint32)) {
            return new String(bArr, i2, readRawVarint32, Internal.UTF_8);
        }
        throw InvalidProtocolBufferException.invalidUtf8();
    }

    public void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        if (this.recursionDepth < this.recursionLimit) {
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
            return;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        if (this.recursionDepth < this.recursionLimit) {
            this.recursionDepth++;
            MessageLite messageLite = (MessageLite) parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
            return messageLite;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    @Deprecated
    public void readUnknownGroup(int i, Builder builder) throws IOException {
        readGroup(i, builder, null);
    }

    public void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth < this.recursionLimit) {
            readRawVarint32 = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(readRawVarint32);
            return;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (this.recursionDepth < this.recursionLimit) {
            readRawVarint32 = pushLimit(readRawVarint32);
            this.recursionDepth++;
            MessageLite messageLite = (MessageLite) parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(readRawVarint32);
            return messageLite;
        }
        throw InvalidProtocolBufferException.recursionLimitExceeded();
    }

    public ByteString readBytes() throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 <= this.bufferSize - this.bufferPos && readRawVarint32 > 0) {
            ByteString wrap;
            if (this.bufferIsImmutable && this.enableAliasing) {
                wrap = ByteString.wrap(this.buffer, this.bufferPos, readRawVarint32);
            } else {
                wrap = ByteString.copyFrom(this.buffer, this.bufferPos, readRawVarint32);
            }
            this.bufferPos += readRawVarint32;
            return wrap;
        } else if (readRawVarint32 == 0) {
            return ByteString.EMPTY;
        } else {
            return ByteString.wrap(readRawBytesSlowPath(readRawVarint32));
        }
    }

    public byte[] readByteArray() throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 > this.bufferSize - this.bufferPos || readRawVarint32 <= 0) {
            return readRawBytesSlowPath(readRawVarint32);
        }
        byte[] copyOfRange = Arrays.copyOfRange(this.buffer, this.bufferPos, this.bufferPos + readRawVarint32);
        this.bufferPos += readRawVarint32;
        return copyOfRange;
    }

    public ByteBuffer readByteBuffer() throws IOException {
        int readRawVarint32 = readRawVarint32();
        if (readRawVarint32 <= this.bufferSize - this.bufferPos && readRawVarint32 > 0) {
            ByteBuffer slice;
            if (this.input == null && !this.bufferIsImmutable && this.enableAliasing) {
                slice = ByteBuffer.wrap(this.buffer, this.bufferPos, readRawVarint32).slice();
            } else {
                slice = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, this.bufferPos, this.bufferPos + readRawVarint32));
            }
            this.bufferPos += readRawVarint32;
            return slice;
        } else if (readRawVarint32 == 0) {
            return Internal.EMPTY_BYTE_BUFFER;
        } else {
            return ByteBuffer.wrap(readRawBytesSlowPath(readRawVarint32));
        }
    }

    public int readUInt32() throws IOException {
        return readRawVarint32();
    }

    public int readEnum() throws IOException {
        return readRawVarint32();
    }

    public int readSFixed32() throws IOException {
        return readRawLittleEndian32();
    }

    public long readSFixed64() throws IOException {
        return readRawLittleEndian64();
    }

    public int readSInt32() throws IOException {
        return decodeZigZag32(readRawVarint32());
    }

    public long readSInt64() throws IOException {
        return decodeZigZag64(readRawVarint64());
    }

    /* JADX WARNING: Missing block: B:29:0x006a, code:
            if (r1[r2] < (byte) 0) goto L_0x006c;
     */
    public int readRawVarint32() throws java.io.IOException {
        /*
        r5 = this;
        r0 = r5.bufferPos;
        r1 = r5.bufferSize;
        if (r1 != r0) goto L_0x0007;
    L_0x0006:
        goto L_0x006c;
    L_0x0007:
        r1 = r5.buffer;
        r2 = r0 + 1;
        r0 = r1[r0];
        if (r0 < 0) goto L_0x0012;
    L_0x000f:
        r5.bufferPos = r2;
        return r0;
    L_0x0012:
        r3 = r5.bufferSize;
        r3 = r3 - r2;
        r4 = 9;
        if (r3 >= r4) goto L_0x001a;
    L_0x0019:
        goto L_0x006c;
    L_0x001a:
        r3 = r2 + 1;
        r2 = r1[r2];
        r2 = r2 << 7;
        r0 = r0 ^ r2;
        if (r0 >= 0) goto L_0x0026;
    L_0x0023:
        r0 = r0 ^ -128;
        goto L_0x0072;
    L_0x0026:
        r2 = r3 + 1;
        r3 = r1[r3];
        r3 = r3 << 14;
        r0 = r0 ^ r3;
        if (r0 < 0) goto L_0x0033;
    L_0x002f:
        r0 = r0 ^ 16256;
    L_0x0031:
        r3 = r2;
        goto L_0x0072;
    L_0x0033:
        r3 = r2 + 1;
        r2 = r1[r2];
        r2 = r2 << 21;
        r0 = r0 ^ r2;
        if (r0 >= 0) goto L_0x0041;
    L_0x003c:
        r1 = -2080896; // 0xffffffffffe03f80 float:NaN double:NaN;
        r0 = r0 ^ r1;
        goto L_0x0072;
    L_0x0041:
        r2 = r3 + 1;
        r3 = r1[r3];
        r4 = r3 << 28;
        r0 = r0 ^ r4;
        r4 = 266354560; // 0xfe03f80 float:2.2112565E-29 double:1.315966377E-315;
        r0 = r0 ^ r4;
        if (r3 >= 0) goto L_0x0031;
    L_0x004e:
        r3 = r2 + 1;
        r2 = r1[r2];
        if (r2 >= 0) goto L_0x0072;
    L_0x0054:
        r2 = r3 + 1;
        r3 = r1[r3];
        if (r3 >= 0) goto L_0x0031;
    L_0x005a:
        r3 = r2 + 1;
        r2 = r1[r2];
        if (r2 >= 0) goto L_0x0072;
    L_0x0060:
        r2 = r3 + 1;
        r3 = r1[r3];
        if (r3 >= 0) goto L_0x0031;
    L_0x0066:
        r3 = r2 + 1;
        r1 = r1[r2];
        if (r1 >= 0) goto L_0x0072;
    L_0x006c:
        r0 = r5.readRawVarint64SlowPath();
        r5 = (int) r0;
        return r5;
    L_0x0072:
        r5.bufferPos = r3;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.readRawVarint32():int");
    }

    private void skipRawVarint() throws IOException {
        if (this.bufferSize - this.bufferPos >= 10) {
            byte[] bArr = this.buffer;
            int i = this.bufferPos;
            int i2 = 0;
            while (i2 < 10) {
                int i3 = i + 1;
                if (bArr[i] >= (byte) 0) {
                    this.bufferPos = i3;
                    return;
                } else {
                    i2++;
                    i = i3;
                }
            }
        }
        skipRawVarintSlowPath();
    }

    private void skipRawVarintSlowPath() throws IOException {
        int i = 0;
        while (i < 10) {
            if (readRawByte() < (byte) 0) {
                i++;
            } else {
                return;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    static int readRawVarint32(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return readRawVarint32(read, inputStream);
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }

    public static int readRawVarint32(int i, InputStream inputStream) throws IOException {
        if ((i & 128) == 0) {
            return i;
        }
        int read;
        i &= 127;
        int i2 = 7;
        while (i2 < 32) {
            read = inputStream.read();
            if (read != -1) {
                i |= (read & 127) << i2;
                if ((read & 128) == 0) {
                    return i;
                }
                i2 += 7;
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }
        while (i2 < 64) {
            read = inputStream.read();
            if (read == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if ((read & 128) == 0) {
                return i;
            } else {
                i2 += 7;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public long readRawVarint64() throws IOException {
        int i = this.bufferPos;
        if (this.bufferSize != i) {
            byte[] bArr = this.buffer;
            int i2 = i + 1;
            byte b = bArr[i];
            if (b >= (byte) 0) {
                this.bufferPos = i2;
                return (long) b;
            } else if (this.bufferSize - i2 >= 9) {
                long j;
                long j2;
                int i3 = i2 + 1;
                i = b ^ (bArr[i2] << 7);
                if (i < 0) {
                    j = (long) (i ^ -128);
                } else {
                    i2 = i3 + 1;
                    i ^= bArr[i3] << 14;
                    if (i >= 0) {
                        long j3 = (long) (i ^ 16256);
                        i = i2;
                        j2 = j3;
                    } else {
                        i3 = i2 + 1;
                        i ^= bArr[i2] << 21;
                        if (i < 0) {
                            j = (long) (i ^ -2080896);
                        } else {
                            long j4 = (long) i;
                            i = i3 + 1;
                            long j5 = (((long) bArr[i3]) << 28) ^ j4;
                            if (j5 >= 0) {
                                j2 = j5 ^ 266354560;
                            } else {
                                int i4 = i + 1;
                                j5 ^= ((long) bArr[i]) << 35;
                                if (j5 < 0) {
                                    j = -34093383808L ^ j5;
                                } else {
                                    i = i4 + 1;
                                    j5 ^= ((long) bArr[i4]) << 42;
                                    if (j5 >= 0) {
                                        j2 = j5 ^ 4363953127296L;
                                    } else {
                                        i4 = i + 1;
                                        j5 ^= ((long) bArr[i]) << 49;
                                        if (j5 < 0) {
                                            j = -558586000294016L ^ j5;
                                        } else {
                                            i = i4 + 1;
                                            j5 = (j5 ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                            if (j5 < 0) {
                                                i4 = i + 1;
                                                if (((long) bArr[i]) >= 0) {
                                                    j2 = j5;
                                                    i = i4;
                                                }
                                            } else {
                                                j2 = j5;
                                            }
                                        }
                                    }
                                }
                                j2 = j;
                                i = i4;
                            }
                        }
                    }
                    this.bufferPos = i;
                    return j2;
                }
                j2 = j;
                i = i3;
                this.bufferPos = i;
                return j2;
            }
        }
        return readRawVarint64SlowPath();
    }

    long readRawVarint64SlowPath() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte readRawByte = readRawByte();
            j |= ((long) (readRawByte & 127)) << i;
            if ((readRawByte & 128) == 0) {
                return j;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    public int readRawLittleEndian32() throws IOException {
        int i = this.bufferPos;
        if (this.bufferSize - i < 4) {
            refillBuffer(4);
            i = this.bufferPos;
        }
        byte[] bArr = this.buffer;
        this.bufferPos = i + 4;
        return (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
    }

    public long readRawLittleEndian64() throws IOException {
        int i = this.bufferPos;
        if (this.bufferSize - i < 8) {
            refillBuffer(8);
            i = this.bufferPos;
        }
        byte[] bArr = this.buffer;
        this.bufferPos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48));
    }

    private CodedInputStream(byte[] bArr, int i, int i2, boolean z) {
        this.buffer = bArr;
        this.bufferSize = i2 + i;
        this.bufferPos = i;
        this.totalBytesRetired = -i;
        this.input = null;
        this.bufferIsImmutable = z;
    }

    private CodedInputStream(InputStream inputStream, int i) {
        this.buffer = new byte[i];
        this.bufferPos = 0;
        this.totalBytesRetired = 0;
        this.input = inputStream;
        this.bufferIsImmutable = false;
    }

    public void enableAliasing(boolean z) {
        this.enableAliasing = z;
    }

    public int setRecursionLimit(int i) {
        if (i >= 0) {
            int i2 = this.recursionLimit;
            this.recursionLimit = i;
            return i2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Recursion limit cannot be negative: ");
        stringBuilder.append(i);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public int setSizeLimit(int i) {
        if (i >= 0) {
            int i2 = this.sizeLimit;
            this.sizeLimit = i;
            return i2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Size limit cannot be negative: ");
        stringBuilder.append(i);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public void resetSizeCounter() {
        this.totalBytesRetired = -this.bufferPos;
    }

    public int pushLimit(int i) throws InvalidProtocolBufferException {
        if (i >= 0) {
            i += this.totalBytesRetired + this.bufferPos;
            int i2 = this.currentLimit;
            if (i <= i2) {
                this.currentLimit = i;
                recomputeBufferSizeAfterLimit();
                return i2;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        throw InvalidProtocolBufferException.negativeSize();
    }

    private void recomputeBufferSizeAfterLimit() {
        this.bufferSize += this.bufferSizeAfterLimit;
        int i = this.totalBytesRetired + this.bufferSize;
        if (i > this.currentLimit) {
            this.bufferSizeAfterLimit = i - this.currentLimit;
            this.bufferSize -= this.bufferSizeAfterLimit;
            return;
        }
        this.bufferSizeAfterLimit = 0;
    }

    public void popLimit(int i) {
        this.currentLimit = i;
        recomputeBufferSizeAfterLimit();
    }

    public int getBytesUntilLimit() {
        if (this.currentLimit == Integer.MAX_VALUE) {
            return -1;
        }
        return this.currentLimit - (this.totalBytesRetired + this.bufferPos);
    }

    public boolean isAtEnd() throws IOException {
        return this.bufferPos == this.bufferSize && !tryRefillBuffer(1);
    }

    public int getTotalBytesRead() {
        return this.totalBytesRetired + this.bufferPos;
    }

    private void refillBuffer(int i) throws IOException {
        if (!tryRefillBuffer(i)) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    private boolean tryRefillBuffer(int i) throws IOException {
        if (this.bufferPos + i <= this.bufferSize) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("refillBuffer() called when ");
            stringBuilder.append(i);
            stringBuilder.append(" bytes were already available in buffer");
            throw new IllegalStateException(stringBuilder.toString());
        } else if ((this.totalBytesRetired + this.bufferPos) + i > this.currentLimit) {
            return false;
        } else {
            if (this.refillCallback != null) {
                this.refillCallback.onRefill();
            }
            if (this.input != null) {
                int i2 = this.bufferPos;
                if (i2 > 0) {
                    if (this.bufferSize > i2) {
                        System.arraycopy(this.buffer, i2, this.buffer, 0, this.bufferSize - i2);
                    }
                    this.totalBytesRetired += i2;
                    this.bufferSize -= i2;
                    this.bufferPos = 0;
                }
                i2 = this.input.read(this.buffer, this.bufferSize, this.buffer.length - this.bufferSize);
                if (i2 == 0 || i2 < -1 || i2 > this.buffer.length) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("InputStream#read(byte[]) returned invalid result: ");
                    stringBuilder2.append(i2);
                    stringBuilder2.append("\nThe InputStream implementation is buggy.");
                    throw new IllegalStateException(stringBuilder2.toString());
                } else if (i2 > 0) {
                    this.bufferSize += i2;
                    if ((this.totalBytesRetired + i) - this.sizeLimit <= 0) {
                        recomputeBufferSizeAfterLimit();
                        return this.bufferSize >= i ? true : tryRefillBuffer(i);
                    }
                    throw InvalidProtocolBufferException.sizeLimitExceeded();
                }
            }
            return false;
        }
    }

    public byte readRawByte() throws IOException {
        if (this.bufferPos == this.bufferSize) {
            refillBuffer(1);
        }
        byte[] bArr = this.buffer;
        int i = this.bufferPos;
        this.bufferPos = i + 1;
        return bArr[i];
    }

    public byte[] readRawBytes(int i) throws IOException {
        int i2 = this.bufferPos;
        if (i > this.bufferSize - i2 || i <= 0) {
            return readRawBytesSlowPath(i);
        }
        i += i2;
        this.bufferPos = i;
        return Arrays.copyOfRange(this.buffer, i2, i);
    }

    private byte[] readRawBytesSlowPath(int i) throws IOException {
        if (i > 0) {
            int i2 = (this.totalBytesRetired + this.bufferPos) + i;
            if (i2 > this.sizeLimit) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            } else if (i2 > this.currentLimit) {
                skipRawBytes((this.currentLimit - this.totalBytesRetired) - this.bufferPos);
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (this.input != null) {
                i2 = this.bufferPos;
                int i3 = this.bufferSize - this.bufferPos;
                this.totalBytesRetired += this.bufferSize;
                this.bufferPos = 0;
                this.bufferSize = 0;
                int i4 = i - i3;
                if (i4 < 4096 || i4 <= this.input.available()) {
                    Object obj = new byte[i];
                    System.arraycopy(this.buffer, i2, obj, 0, i3);
                    while (i3 < obj.length) {
                        i2 = this.input.read(obj, i3, i - i3);
                        if (i2 != -1) {
                            this.totalBytesRetired += i2;
                            i3 += i2;
                        } else {
                            throw InvalidProtocolBufferException.truncatedMessage();
                        }
                    }
                    return obj;
                }
                List<byte[]> arrayList = new ArrayList();
                while (i4 > 0) {
                    Object obj2 = new byte[Math.min(i4, 4096)];
                    int i5 = 0;
                    while (i5 < obj2.length) {
                        int read = this.input.read(obj2, i5, obj2.length - i5);
                        if (read != -1) {
                            this.totalBytesRetired += read;
                            i5 += read;
                        } else {
                            throw InvalidProtocolBufferException.truncatedMessage();
                        }
                    }
                    i4 -= obj2.length;
                    arrayList.add(obj2);
                }
                Object obj3 = new byte[i];
                System.arraycopy(this.buffer, i2, obj3, 0, i3);
                for (byte[] bArr : arrayList) {
                    System.arraycopy(bArr, 0, obj3, i3, bArr.length);
                    i3 += bArr.length;
                }
                return obj3;
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        } else if (i == 0) {
            return Internal.EMPTY_BYTE_ARRAY;
        } else {
            throw InvalidProtocolBufferException.negativeSize();
        }
    }

    public void skipRawBytes(int i) throws IOException {
        if (i > this.bufferSize - this.bufferPos || i < 0) {
            skipRawBytesSlowPath(i);
        } else {
            this.bufferPos += i;
        }
    }

    private void skipRawBytesSlowPath(int i) throws IOException {
        if (i < 0) {
            throw InvalidProtocolBufferException.negativeSize();
        } else if ((this.totalBytesRetired + this.bufferPos) + i <= this.currentLimit) {
            int i2 = this.bufferSize - this.bufferPos;
            this.bufferPos = this.bufferSize;
            refillBuffer(1);
            while (true) {
                int i3 = i - i2;
                if (i3 > this.bufferSize) {
                    i2 += this.bufferSize;
                    this.bufferPos = this.bufferSize;
                    refillBuffer(1);
                } else {
                    this.bufferPos = i3;
                    return;
                }
            }
        } else {
            skipRawBytes((this.currentLimit - this.totalBytesRetired) - this.bufferPos);
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }
}
