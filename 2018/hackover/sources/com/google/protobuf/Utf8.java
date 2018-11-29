package com.google.protobuf;

import java.nio.ByteBuffer;

final class Utf8 {
    private static final long ASCII_MASK_LONG = -9187201950435737472L;
    public static final int COMPLETE = 0;
    public static final int MALFORMED = -1;
    static final int MAX_BYTES_PER_CHAR = 3;
    private static final int UNSAFE_COUNT_ASCII_THRESHOLD = 16;
    private static final Processor processor = (UnsafeProcessor.isAvailable() ? new UnsafeProcessor() : new SafeProcessor());

    static abstract class Processor {
        abstract int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2);

        abstract void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer);

        abstract int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3);

        abstract int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3);

        Processor() {
        }

        final boolean isValidUtf8(byte[] bArr, int i, int i2) {
            return partialIsValidUtf8(0, bArr, i, i2) == 0;
        }

        final boolean isValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            return partialIsValidUtf8(0, byteBuffer, i, i2) == 0;
        }

        final int partialIsValidUtf8(int i, ByteBuffer byteBuffer, int i2, int i3) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                return partialIsValidUtf8(i, byteBuffer.array(), i2 + arrayOffset, arrayOffset + i3);
            } else if (byteBuffer.isDirect()) {
                return partialIsValidUtf8Direct(i, byteBuffer, i2, i3);
            } else {
                return partialIsValidUtf8Default(i, byteBuffer, i2, i3);
            }
        }

        /* JADX WARNING: Missing block: B:8:0x0017, code:
            if (r7.get(r8) > (byte) -65) goto L_0x0019;
     */
        /* JADX WARNING: Missing block: B:27:0x0048, code:
            if (r7.get(r8) > (byte) -65) goto L_0x004a;
     */
        /* JADX WARNING: Missing block: B:47:0x0086, code:
            if (r7.get(r6) > (byte) -65) goto L_0x0088;
     */
        final int partialIsValidUtf8Default(int r6, java.nio.ByteBuffer r7, int r8, int r9) {
            /*
            r5 = this;
            if (r6 == 0) goto L_0x0089;
        L_0x0002:
            if (r8 < r9) goto L_0x0005;
        L_0x0004:
            return r6;
        L_0x0005:
            r5 = (byte) r6;
            r0 = -32;
            r1 = -1;
            r2 = -65;
            if (r5 >= r0) goto L_0x001a;
        L_0x000d:
            r6 = -62;
            if (r5 < r6) goto L_0x0019;
        L_0x0011:
            r5 = r8 + 1;
            r6 = r7.get(r8);
            if (r6 <= r2) goto L_0x008a;
        L_0x0019:
            return r1;
        L_0x001a:
            r3 = -16;
            if (r5 >= r3) goto L_0x004b;
        L_0x001e:
            r6 = r6 >> 8;
            r6 = ~r6;
            r6 = (byte) r6;
            if (r6 != 0) goto L_0x0034;
        L_0x0024:
            r6 = r8 + 1;
            r8 = r7.get(r8);
            if (r6 < r9) goto L_0x0031;
        L_0x002c:
            r5 = com.google.protobuf.Utf8.incompleteStateFor(r5, r8);
            return r5;
        L_0x0031:
            r4 = r8;
            r8 = r6;
            r6 = r4;
        L_0x0034:
            if (r6 > r2) goto L_0x004a;
        L_0x0036:
            r3 = -96;
            if (r5 != r0) goto L_0x003c;
        L_0x003a:
            if (r6 < r3) goto L_0x004a;
        L_0x003c:
            r0 = -19;
            if (r5 != r0) goto L_0x0042;
        L_0x0040:
            if (r6 >= r3) goto L_0x004a;
        L_0x0042:
            r5 = r8 + 1;
            r6 = r7.get(r8);
            if (r6 <= r2) goto L_0x008a;
        L_0x004a:
            return r1;
        L_0x004b:
            r0 = r6 >> 8;
            r0 = ~r0;
            r0 = (byte) r0;
            r3 = 0;
            if (r0 != 0) goto L_0x005f;
        L_0x0052:
            r6 = r8 + 1;
            r0 = r7.get(r8);
            if (r6 < r9) goto L_0x0063;
        L_0x005a:
            r5 = com.google.protobuf.Utf8.incompleteStateFor(r5, r0);
            return r5;
        L_0x005f:
            r6 = r6 >> 16;
            r3 = (byte) r6;
            r6 = r8;
        L_0x0063:
            if (r3 != 0) goto L_0x0073;
        L_0x0065:
            r8 = r6 + 1;
            r3 = r7.get(r6);
            if (r8 < r9) goto L_0x0072;
        L_0x006d:
            r5 = com.google.protobuf.Utf8.incompleteStateFor(r5, r0, r3);
            return r5;
        L_0x0072:
            r6 = r8;
        L_0x0073:
            if (r0 > r2) goto L_0x0088;
        L_0x0075:
            r5 = r5 << 28;
            r0 = r0 + 112;
            r5 = r5 + r0;
            r5 = r5 >> 30;
            if (r5 != 0) goto L_0x0088;
        L_0x007e:
            if (r3 > r2) goto L_0x0088;
        L_0x0080:
            r8 = r6 + 1;
            r5 = r7.get(r6);
            if (r5 <= r2) goto L_0x0089;
        L_0x0088:
            return r1;
        L_0x0089:
            r5 = r8;
        L_0x008a:
            r5 = partialIsValidUtf8(r7, r5, r9);
            return r5;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.Processor.partialIsValidUtf8Default(int, java.nio.ByteBuffer, int, int):int");
        }

        private static int partialIsValidUtf8(ByteBuffer byteBuffer, int i, int i2) {
            i += Utf8.estimateConsecutiveAscii(byteBuffer, i, i2);
            while (i < i2) {
                int i3 = i + 1;
                byte b = byteBuffer.get(i);
                if (b < (byte) 0) {
                    byte b2;
                    if (b < (byte) -32) {
                        if (i3 >= i2) {
                            return b;
                        }
                        if (b < (byte) -62 || byteBuffer.get(i3) > (byte) -65) {
                            return -1;
                        }
                        i3++;
                    } else if (b < (byte) -16) {
                        if (i3 >= i2 - 1) {
                            return Utf8.incompleteStateFor(byteBuffer, b, i3, i2 - i3);
                        }
                        int i4 = i3 + 1;
                        b2 = byteBuffer.get(i3);
                        if (b2 > (byte) -65 || ((b == (byte) -32 && b2 < (byte) -96) || ((b == (byte) -19 && b2 >= (byte) -96) || byteBuffer.get(i4) > (byte) -65))) {
                            return -1;
                        }
                        i = i4 + 1;
                    } else if (i3 >= i2 - 2) {
                        return Utf8.incompleteStateFor(byteBuffer, b, i3, i2 - i3);
                    } else {
                        int i5 = i3 + 1;
                        b2 = byteBuffer.get(i3);
                        if (b2 <= (byte) -65 && (((b << 28) + (b2 + 112)) >> 30) == 0) {
                            i = i5 + 1;
                            if (byteBuffer.get(i5) <= (byte) -65) {
                                i3 = i + 1;
                                if (byteBuffer.get(i) > (byte) -65) {
                                }
                            }
                        }
                        return -1;
                    }
                }
                i = i3;
            }
            return 0;
        }

        final void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
            if (byteBuffer.hasArray()) {
                int arrayOffset = byteBuffer.arrayOffset();
                byteBuffer.position(Utf8.encode(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
            } else if (byteBuffer.isDirect()) {
                encodeUtf8Direct(charSequence, byteBuffer);
            } else {
                encodeUtf8Default(charSequence, byteBuffer);
            }
        }

        final void encodeUtf8Default(CharSequence charSequence, ByteBuffer byteBuffer) {
            char charAt;
            int length = charSequence.length();
            int position = byteBuffer.position();
            int i = 0;
            while (i < length) {
                try {
                    charAt = charSequence.charAt(i);
                    if (charAt >= 128) {
                        break;
                    }
                    byteBuffer.put(position + i, (byte) charAt);
                    i++;
                } catch (IndexOutOfBoundsException unused) {
                    length = byteBuffer.position() + Math.max(i, (position - byteBuffer.position()) + 1);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed writing ");
                    stringBuilder.append(charSequence.charAt(i));
                    stringBuilder.append(" at index ");
                    stringBuilder.append(length);
                    throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
                }
            }
            if (i == length) {
                byteBuffer.position(position + i);
                return;
            }
            position += i;
            while (i < length) {
                charAt = charSequence.charAt(i);
                int i2;
                if (charAt < 128) {
                    byteBuffer.put(position, (byte) charAt);
                } else if (charAt < 2048) {
                    i2 = position + 1;
                    try {
                        byteBuffer.put(position, (byte) ((charAt >>> 6) | 192));
                        byteBuffer.put(i2, (byte) ((charAt & 63) | 128));
                        position = i2;
                    } catch (IndexOutOfBoundsException unused2) {
                        position = i2;
                    }
                } else if (charAt < 55296 || 57343 < charAt) {
                    i2 = position + 1;
                    byteBuffer.put(position, (byte) ((charAt >>> 12) | 224));
                    position = i2 + 1;
                    byteBuffer.put(i2, (byte) (((charAt >>> 6) & 63) | 128));
                    byteBuffer.put(position, (byte) ((charAt & 63) | 128));
                } else {
                    i2 = i + 1;
                    if (i2 != length) {
                        try {
                            char charAt2 = charSequence.charAt(i2);
                            if (Character.isSurrogatePair(charAt, charAt2)) {
                                i = Character.toCodePoint(charAt, charAt2);
                                int i3 = position + 1;
                                try {
                                    byteBuffer.put(position, (byte) ((i >>> 18) | 240));
                                    position = i3 + 1;
                                    byteBuffer.put(i3, (byte) (((i >>> 12) & 63) | 128));
                                    i3 = position + 1;
                                    byteBuffer.put(position, (byte) (((i >>> 6) & 63) | 128));
                                    byteBuffer.put(i3, (byte) ((i & 63) | 128));
                                    position = i3;
                                    i = i2;
                                } catch (IndexOutOfBoundsException unused3) {
                                    position = i3;
                                }
                            } else {
                                i = i2;
                            }
                        } catch (IndexOutOfBoundsException unused4) {
                            i = i2;
                        }
                    }
                    throw new UnpairedSurrogateException(i, length);
                }
                i++;
                position++;
            }
            byteBuffer.position(position);
        }
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i, int i2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unpaired surrogate at index ");
            stringBuilder.append(i);
            stringBuilder.append(" of ");
            stringBuilder.append(i2);
            super(stringBuilder.toString());
        }
    }

    static final class SafeProcessor extends Processor {
        SafeProcessor() {
        }

        /* JADX WARNING: Missing block: B:8:0x0015, code:
            if (r7[r8] > (byte) -65) goto L_0x0017;
     */
        /* JADX WARNING: Missing block: B:27:0x0042, code:
            if (r7[r8] > (byte) -65) goto L_0x0044;
     */
        /* JADX WARNING: Missing block: B:47:0x007a, code:
            if (r7[r6] > (byte) -65) goto L_0x007c;
     */
        int partialIsValidUtf8(int r6, byte[] r7, int r8, int r9) {
            /*
            r5 = this;
            if (r6 == 0) goto L_0x007d;
        L_0x0002:
            if (r8 < r9) goto L_0x0005;
        L_0x0004:
            return r6;
        L_0x0005:
            r5 = (byte) r6;
            r0 = -32;
            r1 = -1;
            r2 = -65;
            if (r5 >= r0) goto L_0x0018;
        L_0x000d:
            r6 = -62;
            if (r5 < r6) goto L_0x0017;
        L_0x0011:
            r5 = r8 + 1;
            r6 = r7[r8];
            if (r6 <= r2) goto L_0x007e;
        L_0x0017:
            return r1;
        L_0x0018:
            r3 = -16;
            if (r5 >= r3) goto L_0x0045;
        L_0x001c:
            r6 = r6 >> 8;
            r6 = ~r6;
            r6 = (byte) r6;
            if (r6 != 0) goto L_0x0030;
        L_0x0022:
            r6 = r8 + 1;
            r8 = r7[r8];
            if (r6 < r9) goto L_0x002d;
        L_0x0028:
            r5 = com.google.protobuf.Utf8.incompleteStateFor(r5, r8);
            return r5;
        L_0x002d:
            r4 = r8;
            r8 = r6;
            r6 = r4;
        L_0x0030:
            if (r6 > r2) goto L_0x0044;
        L_0x0032:
            r3 = -96;
            if (r5 != r0) goto L_0x0038;
        L_0x0036:
            if (r6 < r3) goto L_0x0044;
        L_0x0038:
            r0 = -19;
            if (r5 != r0) goto L_0x003e;
        L_0x003c:
            if (r6 >= r3) goto L_0x0044;
        L_0x003e:
            r5 = r8 + 1;
            r6 = r7[r8];
            if (r6 <= r2) goto L_0x007e;
        L_0x0044:
            return r1;
        L_0x0045:
            r0 = r6 >> 8;
            r0 = ~r0;
            r0 = (byte) r0;
            r3 = 0;
            if (r0 != 0) goto L_0x0057;
        L_0x004c:
            r6 = r8 + 1;
            r0 = r7[r8];
            if (r6 < r9) goto L_0x005b;
        L_0x0052:
            r5 = com.google.protobuf.Utf8.incompleteStateFor(r5, r0);
            return r5;
        L_0x0057:
            r6 = r6 >> 16;
            r3 = (byte) r6;
            r6 = r8;
        L_0x005b:
            if (r3 != 0) goto L_0x0069;
        L_0x005d:
            r8 = r6 + 1;
            r3 = r7[r6];
            if (r8 < r9) goto L_0x0068;
        L_0x0063:
            r5 = com.google.protobuf.Utf8.incompleteStateFor(r5, r0, r3);
            return r5;
        L_0x0068:
            r6 = r8;
        L_0x0069:
            if (r0 > r2) goto L_0x007c;
        L_0x006b:
            r5 = r5 << 28;
            r0 = r0 + 112;
            r5 = r5 + r0;
            r5 = r5 >> 30;
            if (r5 != 0) goto L_0x007c;
        L_0x0074:
            if (r3 > r2) goto L_0x007c;
        L_0x0076:
            r8 = r6 + 1;
            r5 = r7[r6];
            if (r5 <= r2) goto L_0x007d;
        L_0x007c:
            return r1;
        L_0x007d:
            r5 = r8;
        L_0x007e:
            r5 = partialIsValidUtf8(r7, r5, r9);
            return r5;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.SafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        int partialIsValidUtf8Direct(int i, ByteBuffer byteBuffer, int i2, int i3) {
            return partialIsValidUtf8Default(i, byteBuffer, i2, i3);
        }

        int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2) {
            int i3;
            int length = charSequence.length();
            i2 += i;
            int i4 = 0;
            while (i4 < length) {
                i3 = i4 + i;
                if (i3 >= i2) {
                    break;
                }
                char charAt = charSequence.charAt(i4);
                if (charAt >= 128) {
                    break;
                }
                bArr[i3] = (byte) charAt;
                i4++;
            }
            if (i4 == length) {
                return i + length;
            }
            i += i4;
            while (i4 < length) {
                int i5;
                char charAt2 = charSequence.charAt(i4);
                if (charAt2 < 128 && i < i2) {
                    i5 = i + 1;
                    bArr[i] = (byte) charAt2;
                } else if (charAt2 < 2048 && i <= i2 - 2) {
                    i5 = i + 1;
                    bArr[i] = (byte) ((charAt2 >>> 6) | 960);
                    i = i5 + 1;
                    bArr[i5] = (byte) ((charAt2 & 63) | 128);
                    i4++;
                } else if ((charAt2 < 55296 || 57343 < charAt2) && i <= i2 - 3) {
                    i5 = i + 1;
                    bArr[i] = (byte) ((charAt2 >>> 12) | 480);
                    i = i5 + 1;
                    bArr[i5] = (byte) (((charAt2 >>> 6) & 63) | 128);
                    i5 = i + 1;
                    bArr[i] = (byte) ((charAt2 & 63) | 128);
                } else if (i <= i2 - 4) {
                    i5 = i4 + 1;
                    if (i5 != charSequence.length()) {
                        char charAt3 = charSequence.charAt(i5);
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            i4 = Character.toCodePoint(charAt2, charAt3);
                            i3 = i + 1;
                            bArr[i] = (byte) ((i4 >>> 18) | 240);
                            i = i3 + 1;
                            bArr[i3] = (byte) (((i4 >>> 12) & 63) | 128);
                            i3 = i + 1;
                            bArr[i] = (byte) (((i4 >>> 6) & 63) | 128);
                            i = i3 + 1;
                            bArr[i3] = (byte) ((i4 & 63) | 128);
                            i4 = i5;
                            i4++;
                        } else {
                            i4 = i5;
                        }
                    }
                    throw new UnpairedSurrogateException(i4 - 1, length);
                } else {
                    if (55296 <= charAt2 && charAt2 <= 57343) {
                        int i6 = i4 + 1;
                        if (i6 == charSequence.length() || !Character.isSurrogatePair(charAt2, charSequence.charAt(i6))) {
                            throw new UnpairedSurrogateException(i4, length);
                        }
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed writing ");
                    stringBuilder.append(charAt2);
                    stringBuilder.append(" at index ");
                    stringBuilder.append(i);
                    throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
                }
                i = i5;
                i4++;
            }
            return i;
        }

        void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            encodeUtf8Default(charSequence, byteBuffer);
        }

        private static int partialIsValidUtf8(byte[] bArr, int i, int i2) {
            while (i < i2 && bArr[i] >= (byte) 0) {
                i++;
            }
            if (i >= i2) {
                return 0;
            }
            return partialIsValidUtf8NonAscii(bArr, i, i2);
        }

        private static int partialIsValidUtf8NonAscii(byte[] bArr, int i, int i2) {
            while (i < i2) {
                int i3 = i + 1;
                byte b = bArr[i];
                if (b < (byte) 0) {
                    byte b2;
                    if (b < (byte) -32) {
                        if (i3 >= i2) {
                            return b;
                        }
                        if (b >= (byte) -62) {
                            i = i3 + 1;
                            if (bArr[i3] > (byte) -65) {
                            }
                        }
                        return -1;
                    } else if (b < (byte) -16) {
                        if (i3 >= i2 - 1) {
                            return Utf8.incompleteStateFor(bArr, i3, i2);
                        }
                        int i4 = i3 + 1;
                        b2 = bArr[i3];
                        if (b2 <= (byte) -65 && ((b != (byte) -32 || b2 >= (byte) -96) && (b != (byte) -19 || b2 < (byte) -96))) {
                            i = i4 + 1;
                            if (bArr[i4] > (byte) -65) {
                            }
                        }
                        return -1;
                    } else if (i3 >= i2 - 2) {
                        return Utf8.incompleteStateFor(bArr, i3, i2);
                    } else {
                        int i5 = i3 + 1;
                        b2 = bArr[i3];
                        if (b2 <= (byte) -65 && (((b << 28) + (b2 + 112)) >> 30) == 0) {
                            i = i5 + 1;
                            if (bArr[i5] <= (byte) -65) {
                                i3 = i + 1;
                                if (bArr[i] > (byte) -65) {
                                }
                            }
                        }
                        return -1;
                    }
                }
                i = i3;
            }
            return 0;
        }
    }

    static final class UnsafeProcessor extends Processor {
        UnsafeProcessor() {
        }

        static boolean isAvailable() {
            return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        /* JADX WARNING: Missing block: B:12:0x002f, code:
            if (com.google.protobuf.UnsafeUtil.getByte(r12, r1) > (byte) -65) goto L_0x0031;
     */
        /* JADX WARNING: Missing block: B:31:0x0060, code:
            if (com.google.protobuf.UnsafeUtil.getByte(r12, r1) > (byte) -65) goto L_0x0062;
     */
        /* JADX WARNING: Missing block: B:52:0x00a2, code:
            if (com.google.protobuf.UnsafeUtil.getByte(r12, r1) > (byte) -65) goto L_0x00a4;
     */
        int partialIsValidUtf8(int r11, byte[] r12, int r13, int r14) {
            /*
            r10 = this;
            r10 = r13 | r14;
            r0 = r12.length;
            r0 = r0 - r14;
            r10 = r10 | r0;
            r0 = 0;
            if (r10 < 0) goto L_0x00ad;
        L_0x0008:
            r1 = com.google.protobuf.UnsafeUtil.getArrayBaseOffset();
            r3 = (long) r13;
            r1 = r1 + r3;
            r3 = com.google.protobuf.UnsafeUtil.getArrayBaseOffset();
            r13 = (long) r14;
            r3 = r3 + r13;
            if (r11 == 0) goto L_0x00a5;
        L_0x0016:
            r10 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
            if (r10 < 0) goto L_0x001b;
        L_0x001a:
            return r11;
        L_0x001b:
            r10 = (byte) r11;
            r13 = -32;
            r14 = -1;
            r5 = -65;
            r6 = 1;
            if (r10 >= r13) goto L_0x0032;
        L_0x0025:
            r11 = -62;
            if (r10 < r11) goto L_0x0031;
        L_0x0029:
            r10 = r1 + r6;
            r13 = com.google.protobuf.UnsafeUtil.getByte(r12, r1);
            if (r13 <= r5) goto L_0x00a6;
        L_0x0031:
            return r14;
        L_0x0032:
            r8 = -16;
            if (r10 >= r8) goto L_0x0063;
        L_0x0036:
            r11 = r11 >> 8;
            r11 = ~r11;
            r11 = (byte) r11;
            if (r11 != 0) goto L_0x004c;
        L_0x003c:
            r8 = r1 + r6;
            r11 = com.google.protobuf.UnsafeUtil.getByte(r12, r1);
            r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1));
            if (r0 < 0) goto L_0x004b;
        L_0x0046:
            r10 = com.google.protobuf.Utf8.incompleteStateFor(r10, r11);
            return r10;
        L_0x004b:
            r1 = r8;
        L_0x004c:
            if (r11 > r5) goto L_0x0062;
        L_0x004e:
            r0 = -96;
            if (r10 != r13) goto L_0x0054;
        L_0x0052:
            if (r11 < r0) goto L_0x0062;
        L_0x0054:
            r13 = -19;
            if (r10 != r13) goto L_0x005a;
        L_0x0058:
            if (r11 >= r0) goto L_0x0062;
        L_0x005a:
            r10 = r1 + r6;
            r13 = com.google.protobuf.UnsafeUtil.getByte(r12, r1);
            if (r13 <= r5) goto L_0x00a6;
        L_0x0062:
            return r14;
        L_0x0063:
            r13 = r11 >> 8;
            r13 = ~r13;
            r13 = (byte) r13;
            if (r13 != 0) goto L_0x007a;
        L_0x0069:
            r8 = r1 + r6;
            r13 = com.google.protobuf.UnsafeUtil.getByte(r12, r1);
            r11 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1));
            if (r11 < 0) goto L_0x0078;
        L_0x0073:
            r10 = com.google.protobuf.Utf8.incompleteStateFor(r10, r13);
            return r10;
        L_0x0078:
            r1 = r8;
            goto L_0x007d;
        L_0x007a:
            r11 = r11 >> 16;
            r0 = (byte) r11;
        L_0x007d:
            if (r0 != 0) goto L_0x008f;
        L_0x007f:
            r8 = r1 + r6;
            r0 = com.google.protobuf.UnsafeUtil.getByte(r12, r1);
            r11 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1));
            if (r11 < 0) goto L_0x008e;
        L_0x0089:
            r10 = com.google.protobuf.Utf8.incompleteStateFor(r10, r13, r0);
            return r10;
        L_0x008e:
            r1 = r8;
        L_0x008f:
            if (r13 > r5) goto L_0x00a4;
        L_0x0091:
            r10 = r10 << 28;
            r13 = r13 + 112;
            r10 = r10 + r13;
            r10 = r10 >> 30;
            if (r10 != 0) goto L_0x00a4;
        L_0x009a:
            if (r0 > r5) goto L_0x00a4;
        L_0x009c:
            r10 = r1 + r6;
            r13 = com.google.protobuf.UnsafeUtil.getByte(r12, r1);
            if (r13 <= r5) goto L_0x00a6;
        L_0x00a4:
            return r14;
        L_0x00a5:
            r10 = r1;
        L_0x00a6:
            r3 = r3 - r10;
            r13 = (int) r3;
            r10 = partialIsValidUtf8(r12, r10, r13);
            return r10;
        L_0x00ad:
            r10 = new java.lang.ArrayIndexOutOfBoundsException;
            r11 = 3;
            r11 = new java.lang.Object[r11];
            r12 = r12.length;
            r12 = java.lang.Integer.valueOf(r12);
            r11[r0] = r12;
            r12 = 1;
            r13 = java.lang.Integer.valueOf(r13);
            r11[r12] = r13;
            r12 = 2;
            r13 = java.lang.Integer.valueOf(r14);
            r11[r12] = r13;
            r12 = "Array length=%d, index=%d, limit=%d";
            r11 = java.lang.String.format(r12, r11);
            r10.<init>(r11);
            throw r10;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }

        /* JADX WARNING: Missing block: B:12:0x002f, code:
            if (com.google.protobuf.UnsafeUtil.getByte(r1) > (byte) -65) goto L_0x0031;
     */
        /* JADX WARNING: Missing block: B:31:0x0060, code:
            if (com.google.protobuf.UnsafeUtil.getByte(r1) > (byte) -65) goto L_0x0062;
     */
        /* JADX WARNING: Missing block: B:52:0x00a2, code:
            if (com.google.protobuf.UnsafeUtil.getByte(r1) > (byte) -65) goto L_0x00a4;
     */
        int partialIsValidUtf8Direct(int r10, java.nio.ByteBuffer r11, int r12, int r13) {
            /*
            r9 = this;
            r9 = r12 | r13;
            r0 = r11.limit();
            r0 = r0 - r13;
            r9 = r9 | r0;
            r0 = 0;
            if (r9 < 0) goto L_0x00ad;
        L_0x000b:
            r1 = com.google.protobuf.UnsafeUtil.addressOffset(r11);
            r3 = (long) r12;
            r1 = r1 + r3;
            r13 = r13 - r12;
            r11 = (long) r13;
            r11 = r11 + r1;
            if (r10 == 0) goto L_0x00a5;
        L_0x0016:
            r9 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1));
            if (r9 < 0) goto L_0x001b;
        L_0x001a:
            return r10;
        L_0x001b:
            r9 = (byte) r10;
            r13 = -32;
            r3 = -1;
            r4 = -65;
            r5 = 1;
            if (r9 >= r13) goto L_0x0032;
        L_0x0025:
            r10 = -62;
            if (r9 < r10) goto L_0x0031;
        L_0x0029:
            r9 = r1 + r5;
            r13 = com.google.protobuf.UnsafeUtil.getByte(r1);
            if (r13 <= r4) goto L_0x00a6;
        L_0x0031:
            return r3;
        L_0x0032:
            r7 = -16;
            if (r9 >= r7) goto L_0x0063;
        L_0x0036:
            r10 = r10 >> 8;
            r10 = ~r10;
            r10 = (byte) r10;
            if (r10 != 0) goto L_0x004c;
        L_0x003c:
            r7 = r1 + r5;
            r10 = com.google.protobuf.UnsafeUtil.getByte(r1);
            r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1));
            if (r0 < 0) goto L_0x004b;
        L_0x0046:
            r9 = com.google.protobuf.Utf8.incompleteStateFor(r9, r10);
            return r9;
        L_0x004b:
            r1 = r7;
        L_0x004c:
            if (r10 > r4) goto L_0x0062;
        L_0x004e:
            r0 = -96;
            if (r9 != r13) goto L_0x0054;
        L_0x0052:
            if (r10 < r0) goto L_0x0062;
        L_0x0054:
            r13 = -19;
            if (r9 != r13) goto L_0x005a;
        L_0x0058:
            if (r10 >= r0) goto L_0x0062;
        L_0x005a:
            r9 = r1 + r5;
            r13 = com.google.protobuf.UnsafeUtil.getByte(r1);
            if (r13 <= r4) goto L_0x00a6;
        L_0x0062:
            return r3;
        L_0x0063:
            r13 = r10 >> 8;
            r13 = ~r13;
            r13 = (byte) r13;
            if (r13 != 0) goto L_0x007a;
        L_0x0069:
            r7 = r1 + r5;
            r13 = com.google.protobuf.UnsafeUtil.getByte(r1);
            r10 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1));
            if (r10 < 0) goto L_0x0078;
        L_0x0073:
            r9 = com.google.protobuf.Utf8.incompleteStateFor(r9, r13);
            return r9;
        L_0x0078:
            r1 = r7;
            goto L_0x007d;
        L_0x007a:
            r10 = r10 >> 16;
            r0 = (byte) r10;
        L_0x007d:
            if (r0 != 0) goto L_0x008f;
        L_0x007f:
            r7 = r1 + r5;
            r0 = com.google.protobuf.UnsafeUtil.getByte(r1);
            r10 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1));
            if (r10 < 0) goto L_0x008e;
        L_0x0089:
            r9 = com.google.protobuf.Utf8.incompleteStateFor(r9, r13, r0);
            return r9;
        L_0x008e:
            r1 = r7;
        L_0x008f:
            if (r13 > r4) goto L_0x00a4;
        L_0x0091:
            r9 = r9 << 28;
            r13 = r13 + 112;
            r9 = r9 + r13;
            r9 = r9 >> 30;
            if (r9 != 0) goto L_0x00a4;
        L_0x009a:
            if (r0 > r4) goto L_0x00a4;
        L_0x009c:
            r9 = r1 + r5;
            r13 = com.google.protobuf.UnsafeUtil.getByte(r1);
            if (r13 <= r4) goto L_0x00a6;
        L_0x00a4:
            return r3;
        L_0x00a5:
            r9 = r1;
        L_0x00a6:
            r11 = r11 - r9;
            r11 = (int) r11;
            r9 = partialIsValidUtf8(r9, r11);
            return r9;
        L_0x00ad:
            r9 = new java.lang.ArrayIndexOutOfBoundsException;
            r10 = 3;
            r10 = new java.lang.Object[r10];
            r11 = r11.limit();
            r11 = java.lang.Integer.valueOf(r11);
            r10[r0] = r11;
            r11 = 1;
            r12 = java.lang.Integer.valueOf(r12);
            r10[r11] = r12;
            r11 = 2;
            r12 = java.lang.Integer.valueOf(r13);
            r10[r11] = r12;
            r11 = "buffer limit=%d, index=%d, limit=%d";
            r10 = java.lang.String.format(r11, r10);
            r9.<init>(r10);
            throw r9;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8Direct(int, java.nio.ByteBuffer, int, int):int");
        }

        int encodeUtf8(CharSequence charSequence, byte[] bArr, int i, int i2) {
            long arrayBaseOffset = UnsafeUtil.getArrayBaseOffset() + ((long) i);
            long j = ((long) i2) + arrayBaseOffset;
            int length = charSequence.length();
            if (length > i2 || bArr.length - i2 < i) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Failed writing ");
                stringBuilder.append(charSequence.charAt(length - 1));
                stringBuilder.append(" at index ");
                stringBuilder.append(i + i2);
                throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
            }
            char charAt;
            i = 0;
            while (i < length) {
                charAt = charSequence.charAt(i);
                if (charAt >= 128) {
                    break;
                }
                long j2 = 1 + arrayBaseOffset;
                UnsafeUtil.putByte(bArr, arrayBaseOffset, (byte) charAt);
                i++;
                arrayBaseOffset = j2;
            }
            if (i == length) {
                return (int) (arrayBaseOffset - UnsafeUtil.getArrayBaseOffset());
            }
            while (i < length) {
                long j3;
                charAt = charSequence.charAt(i);
                if (charAt < 128 && arrayBaseOffset < j) {
                    j3 = arrayBaseOffset + 1;
                    UnsafeUtil.putByte(bArr, arrayBaseOffset, (byte) charAt);
                } else if (charAt < 2048 && arrayBaseOffset <= j - 2) {
                    j3 = arrayBaseOffset + 1;
                    UnsafeUtil.putByte(bArr, arrayBaseOffset, (byte) ((charAt >>> 6) | 960));
                    arrayBaseOffset = j3 + 1;
                    UnsafeUtil.putByte(bArr, j3, (byte) ((charAt & 63) | 128));
                    i++;
                } else if ((charAt < 55296 || 57343 < charAt) && arrayBaseOffset <= j - 3) {
                    j3 = arrayBaseOffset + 1;
                    UnsafeUtil.putByte(bArr, arrayBaseOffset, (byte) ((charAt >>> 12) | 480));
                    arrayBaseOffset = j3 + 1;
                    UnsafeUtil.putByte(bArr, j3, (byte) (((charAt >>> 6) & 63) | 128));
                    j3 = arrayBaseOffset + 1;
                    UnsafeUtil.putByte(bArr, arrayBaseOffset, (byte) ((charAt & 63) | 128));
                } else if (arrayBaseOffset <= j - 4) {
                    int i3 = i + 1;
                    if (i3 != length) {
                        char charAt2 = charSequence.charAt(i3);
                        if (Character.isSurrogatePair(charAt, charAt2)) {
                            i = Character.toCodePoint(charAt, charAt2);
                            long j4 = arrayBaseOffset + 1;
                            UnsafeUtil.putByte(bArr, arrayBaseOffset, (byte) ((i >>> 18) | 240));
                            arrayBaseOffset = j4 + 1;
                            UnsafeUtil.putByte(bArr, j4, (byte) (((i >>> 12) & 63) | 128));
                            j4 = arrayBaseOffset + 1;
                            UnsafeUtil.putByte(bArr, arrayBaseOffset, (byte) (((i >>> 6) & 63) | 128));
                            arrayBaseOffset = j4 + 1;
                            UnsafeUtil.putByte(bArr, j4, (byte) ((i & 63) | 128));
                            i = i3;
                            i++;
                        } else {
                            i = i3;
                        }
                    }
                    throw new UnpairedSurrogateException(i - 1, length);
                } else {
                    if (55296 <= charAt && charAt <= 57343) {
                        int i4 = i + 1;
                        if (i4 == length || !Character.isSurrogatePair(charAt, charSequence.charAt(i4))) {
                            throw new UnpairedSurrogateException(i, length);
                        }
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Failed writing ");
                    stringBuilder2.append(charAt);
                    stringBuilder2.append(" at index ");
                    stringBuilder2.append(arrayBaseOffset);
                    throw new ArrayIndexOutOfBoundsException(stringBuilder2.toString());
                }
                arrayBaseOffset = j3;
                i++;
            }
            return (int) (arrayBaseOffset - UnsafeUtil.getArrayBaseOffset());
        }

        void encodeUtf8Direct(CharSequence charSequence, ByteBuffer byteBuffer) {
            CharSequence charSequence2 = charSequence;
            ByteBuffer byteBuffer2 = byteBuffer;
            long addressOffset = UnsafeUtil.addressOffset(byteBuffer);
            long position = ((long) byteBuffer.position()) + addressOffset;
            long limit = ((long) byteBuffer.limit()) + addressOffset;
            int length = charSequence.length();
            if (((long) length) <= limit - position) {
                char c;
                long j;
                char charAt;
                int i = 0;
                while (true) {
                    c = 128;
                    j = 1;
                    if (i >= length) {
                        break;
                    }
                    charAt = charSequence2.charAt(i);
                    if (charAt >= 128) {
                        break;
                    }
                    long j2 = position + 1;
                    UnsafeUtil.putByte(position, (byte) charAt);
                    i++;
                    position = j2;
                }
                if (i == length) {
                    byteBuffer2.position((int) (position - addressOffset));
                    return;
                }
                while (i < length) {
                    long j3;
                    char c2;
                    charAt = charSequence2.charAt(i);
                    if (charAt >= c || position >= limit) {
                        if (charAt < 2048 && position <= limit - 2) {
                            j3 = position + j;
                            UnsafeUtil.putByte(position, (byte) ((charAt >>> 6) | 960));
                            position = j3 + j;
                            UnsafeUtil.putByte(j3, (byte) ((charAt & 63) | 128));
                            j3 = position;
                            position = j;
                        } else if ((charAt < 55296 || 57343 < charAt) && position <= limit - 3) {
                            j3 = position + j;
                            UnsafeUtil.putByte(position, (byte) ((charAt >>> 12) | 480));
                            position = j3 + j;
                            UnsafeUtil.putByte(j3, (byte) (((charAt >>> 6) & 63) | 128));
                            long j4 = position + 1;
                            UnsafeUtil.putByte(position, (byte) ((charAt & 63) | 128));
                            j3 = j4;
                            position = 1;
                        } else if (position <= limit - 4) {
                            int i2 = i + 1;
                            if (i2 != length) {
                                char charAt2 = charSequence2.charAt(i2);
                                if (Character.isSurrogatePair(charAt, charAt2)) {
                                    i = Character.toCodePoint(charAt, charAt2);
                                    long j5 = position + 1;
                                    UnsafeUtil.putByte(position, (byte) ((i >>> 18) | 240));
                                    position = j5 + 1;
                                    c2 = 128;
                                    UnsafeUtil.putByte(j5, (byte) (((i >>> 12) & 63) | 128));
                                    j3 = position + 1;
                                    UnsafeUtil.putByte(position, (byte) (((i >>> 6) & 63) | 128));
                                    position = 1;
                                    long j6 = j3 + 1;
                                    UnsafeUtil.putByte(j3, (byte) ((i & 63) | 128));
                                    i = i2;
                                    j3 = j6;
                                } else {
                                    i = i2;
                                }
                            }
                            throw new UnpairedSurrogateException(i - 1, length);
                        } else {
                            if (55296 <= charAt && charAt <= 57343) {
                                int i3 = i + 1;
                                if (i3 == length || !Character.isSurrogatePair(charAt, charSequence2.charAt(i3))) {
                                    throw new UnpairedSurrogateException(i, length);
                                }
                            }
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Failed writing ");
                            stringBuilder.append(charAt);
                            stringBuilder.append(" at index ");
                            stringBuilder.append(position);
                            throw new ArrayIndexOutOfBoundsException(stringBuilder.toString());
                        }
                        c2 = 128;
                    } else {
                        j3 = position + j;
                        UnsafeUtil.putByte(position, (byte) charAt);
                        position = j;
                        c2 = c;
                    }
                    i++;
                    c = c2;
                    j = position;
                    position = j3;
                }
                byteBuffer2.position((int) (position - addressOffset));
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Failed writing ");
            stringBuilder2.append(charSequence2.charAt(length - 1));
            stringBuilder2.append(" at index ");
            stringBuilder2.append(byteBuffer.limit());
            throw new ArrayIndexOutOfBoundsException(stringBuilder2.toString());
        }

        private static int unsafeEstimateConsecutiveAscii(byte[] bArr, long j, int i) {
            if (i < 16) {
                return 0;
            }
            int i2 = ((int) j) & 7;
            long j2 = j;
            int i3 = i2;
            while (i3 > 0) {
                long j3 = 1 + j2;
                if (UnsafeUtil.getByte(bArr, j2) < (byte) 0) {
                    return i2 - i3;
                }
                i3--;
                j2 = j3;
            }
            i3 = i - i2;
            while (i3 >= 8 && (UnsafeUtil.getLong(bArr, j2) & Utf8.ASCII_MASK_LONG) == 0) {
                j2 += 8;
                i3 -= 8;
            }
            return i - i3;
        }

        private static int unsafeEstimateConsecutiveAscii(long j, int i) {
            if (i < 16) {
                return 0;
            }
            int i2 = ((int) j) & 7;
            long j2 = j;
            int i3 = i2;
            while (i3 > 0) {
                long j3 = 1 + j2;
                if (UnsafeUtil.getByte(j2) < (byte) 0) {
                    return i2 - i3;
                }
                i3--;
                j2 = j3;
            }
            i3 = i - i2;
            while (i3 >= 8 && (UnsafeUtil.getLong(j2) & Utf8.ASCII_MASK_LONG) == 0) {
                j2 += 8;
                i3 -= 8;
            }
            return i - i3;
        }

        /* JADX WARNING: Missing block: B:19:0x0039, code:
            return -1;
     */
        /* JADX WARNING: Missing block: B:36:0x0063, code:
            return -1;
     */
        private static int partialIsValidUtf8(byte[] r8, long r9, int r11) {
            /*
            r0 = unsafeEstimateConsecutiveAscii(r8, r9, r11);
            r11 = r11 - r0;
            r0 = (long) r0;
            r9 = r9 + r0;
        L_0x0007:
            r0 = 0;
            r1 = r0;
        L_0x0009:
            r2 = 1;
            if (r11 <= 0) goto L_0x001a;
        L_0x000d:
            r4 = r9 + r2;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r8, r9);
            if (r1 < 0) goto L_0x0019;
        L_0x0015:
            r11 = r11 + -1;
            r9 = r4;
            goto L_0x0009;
        L_0x0019:
            r9 = r4;
        L_0x001a:
            if (r11 != 0) goto L_0x001d;
        L_0x001c:
            return r0;
        L_0x001d:
            r11 = r11 + -1;
            r0 = -32;
            r4 = -65;
            r5 = -1;
            if (r1 >= r0) goto L_0x003a;
        L_0x0026:
            if (r11 != 0) goto L_0x0029;
        L_0x0028:
            return r1;
        L_0x0029:
            r11 = r11 + -1;
            r0 = -62;
            if (r1 < r0) goto L_0x0039;
        L_0x002f:
            r2 = r2 + r9;
            r9 = com.google.protobuf.UnsafeUtil.getByte(r8, r9);
            if (r9 <= r4) goto L_0x0037;
        L_0x0036:
            goto L_0x0039;
        L_0x0037:
            r9 = r2;
            goto L_0x0007;
        L_0x0039:
            return r5;
        L_0x003a:
            r6 = -16;
            if (r1 >= r6) goto L_0x0064;
        L_0x003e:
            r6 = 2;
            if (r11 >= r6) goto L_0x0046;
        L_0x0041:
            r8 = unsafeIncompleteStateFor(r8, r1, r9, r11);
            return r8;
        L_0x0046:
            r11 = r11 + -2;
            r6 = r9 + r2;
            r9 = com.google.protobuf.UnsafeUtil.getByte(r8, r9);
            if (r9 > r4) goto L_0x0063;
        L_0x0050:
            r10 = -96;
            if (r1 != r0) goto L_0x0056;
        L_0x0054:
            if (r9 < r10) goto L_0x0063;
        L_0x0056:
            r0 = -19;
            if (r1 != r0) goto L_0x005c;
        L_0x005a:
            if (r9 >= r10) goto L_0x0063;
        L_0x005c:
            r2 = r2 + r6;
            r9 = com.google.protobuf.UnsafeUtil.getByte(r8, r6);
            if (r9 <= r4) goto L_0x0037;
        L_0x0063:
            return r5;
        L_0x0064:
            r0 = 3;
            if (r11 >= r0) goto L_0x006c;
        L_0x0067:
            r8 = unsafeIncompleteStateFor(r8, r1, r9, r11);
            return r8;
        L_0x006c:
            r11 = r11 + -3;
            r6 = r9 + r2;
            r9 = com.google.protobuf.UnsafeUtil.getByte(r8, r9);
            if (r9 > r4) goto L_0x008e;
        L_0x0076:
            r10 = r1 << 28;
            r9 = r9 + 112;
            r10 = r10 + r9;
            r9 = r10 >> 30;
            if (r9 != 0) goto L_0x008e;
        L_0x007f:
            r9 = r6 + r2;
            r0 = com.google.protobuf.UnsafeUtil.getByte(r8, r6);
            if (r0 > r4) goto L_0x008e;
        L_0x0087:
            r2 = r2 + r9;
            r9 = com.google.protobuf.UnsafeUtil.getByte(r8, r9);
            if (r9 <= r4) goto L_0x0037;
        L_0x008e:
            return r5;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(byte[], long, int):int");
        }

        /* JADX WARNING: Missing block: B:19:0x0039, code:
            return -1;
     */
        /* JADX WARNING: Missing block: B:36:0x0063, code:
            return -1;
     */
        private static int partialIsValidUtf8(long r8, int r10) {
            /*
            r0 = unsafeEstimateConsecutiveAscii(r8, r10);
            r1 = (long) r0;
            r8 = r8 + r1;
            r10 = r10 - r0;
        L_0x0007:
            r0 = 0;
            r1 = r0;
        L_0x0009:
            r2 = 1;
            if (r10 <= 0) goto L_0x001a;
        L_0x000d:
            r4 = r8 + r2;
            r1 = com.google.protobuf.UnsafeUtil.getByte(r8);
            if (r1 < 0) goto L_0x0019;
        L_0x0015:
            r10 = r10 + -1;
            r8 = r4;
            goto L_0x0009;
        L_0x0019:
            r8 = r4;
        L_0x001a:
            if (r10 != 0) goto L_0x001d;
        L_0x001c:
            return r0;
        L_0x001d:
            r10 = r10 + -1;
            r0 = -32;
            r4 = -65;
            r5 = -1;
            if (r1 >= r0) goto L_0x003a;
        L_0x0026:
            if (r10 != 0) goto L_0x0029;
        L_0x0028:
            return r1;
        L_0x0029:
            r10 = r10 + -1;
            r0 = -62;
            if (r1 < r0) goto L_0x0039;
        L_0x002f:
            r2 = r2 + r8;
            r8 = com.google.protobuf.UnsafeUtil.getByte(r8);
            if (r8 <= r4) goto L_0x0037;
        L_0x0036:
            goto L_0x0039;
        L_0x0037:
            r8 = r2;
            goto L_0x0007;
        L_0x0039:
            return r5;
        L_0x003a:
            r6 = -16;
            if (r1 >= r6) goto L_0x0064;
        L_0x003e:
            r6 = 2;
            if (r10 >= r6) goto L_0x0046;
        L_0x0041:
            r8 = unsafeIncompleteStateFor(r8, r1, r10);
            return r8;
        L_0x0046:
            r10 = r10 + -2;
            r6 = r8 + r2;
            r8 = com.google.protobuf.UnsafeUtil.getByte(r8);
            if (r8 > r4) goto L_0x0063;
        L_0x0050:
            r9 = -96;
            if (r1 != r0) goto L_0x0056;
        L_0x0054:
            if (r8 < r9) goto L_0x0063;
        L_0x0056:
            r0 = -19;
            if (r1 != r0) goto L_0x005c;
        L_0x005a:
            if (r8 >= r9) goto L_0x0063;
        L_0x005c:
            r2 = r2 + r6;
            r8 = com.google.protobuf.UnsafeUtil.getByte(r6);
            if (r8 <= r4) goto L_0x0037;
        L_0x0063:
            return r5;
        L_0x0064:
            r0 = 3;
            if (r10 >= r0) goto L_0x006c;
        L_0x0067:
            r8 = unsafeIncompleteStateFor(r8, r1, r10);
            return r8;
        L_0x006c:
            r10 = r10 + -3;
            r6 = r8 + r2;
            r8 = com.google.protobuf.UnsafeUtil.getByte(r8);
            if (r8 > r4) goto L_0x008e;
        L_0x0076:
            r9 = r1 << 28;
            r8 = r8 + 112;
            r9 = r9 + r8;
            r8 = r9 >> 30;
            if (r8 != 0) goto L_0x008e;
        L_0x007f:
            r8 = r6 + r2;
            r0 = com.google.protobuf.UnsafeUtil.getByte(r6);
            if (r0 > r4) goto L_0x008e;
        L_0x0087:
            r2 = r2 + r8;
            r8 = com.google.protobuf.UnsafeUtil.getByte(r8);
            if (r8 <= r4) goto L_0x0037;
        L_0x008e:
            return r5;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.UnsafeProcessor.partialIsValidUtf8(long, int):int");
        }

        private static int unsafeIncompleteStateFor(byte[] bArr, int i, long j, int i2) {
            switch (i2) {
                case 0:
                    return Utf8.incompleteStateFor(i);
                case 1:
                    return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(bArr, j));
                case 2:
                    return Utf8.incompleteStateFor(i, (int) UnsafeUtil.getByte(bArr, j), (int) UnsafeUtil.getByte(bArr, j + 1));
                default:
                    throw new AssertionError();
            }
        }

        private static int unsafeIncompleteStateFor(long j, int i, int i2) {
            switch (i2) {
                case 0:
                    return Utf8.incompleteStateFor(i);
                case 1:
                    return Utf8.incompleteStateFor(i, UnsafeUtil.getByte(j));
                case 2:
                    return Utf8.incompleteStateFor(i, (int) UnsafeUtil.getByte(j), (int) UnsafeUtil.getByte(j + 1));
                default:
                    throw new AssertionError();
            }
        }
    }

    private static int incompleteStateFor(int i) {
        return i > -12 ? -1 : i;
    }

    private static int incompleteStateFor(int i, int i2) {
        return (i > -12 || i2 > -65) ? -1 : i ^ (i2 << 8);
    }

    private static int incompleteStateFor(int i, int i2, int i3) {
        return (i > -12 || i2 > -65 || i3 > -65) ? -1 : (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return processor.isValidUtf8(bArr, 0, bArr.length);
    }

    public static boolean isValidUtf8(byte[] bArr, int i, int i2) {
        return processor.isValidUtf8(bArr, i, i2);
    }

    public static int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3) {
        return processor.partialIsValidUtf8(i, bArr, i2, i3);
    }

    private static int incompleteStateFor(byte[] bArr, int i, int i2) {
        int i3 = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return incompleteStateFor(i3);
            case 1:
                return incompleteStateFor(i3, bArr[i]);
            case 2:
                return incompleteStateFor(i3, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }

    private static int incompleteStateFor(ByteBuffer byteBuffer, int i, int i2, int i3) {
        switch (i3) {
            case 0:
                return incompleteStateFor(i);
            case 1:
                return incompleteStateFor(i, byteBuffer.get(i2));
            case 2:
                return incompleteStateFor(i, byteBuffer.get(i2), byteBuffer.get(i2 + 1));
            default:
                throw new AssertionError();
        }
    }

    static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt >= 2048) {
                i2 += encodedLengthGeneral(charSequence, i);
                break;
            }
            i2 += (127 - charAt) >>> 31;
            i++;
        }
        if (i2 >= length) {
            return i2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UTF-8 length does not fit in int: ");
        stringBuilder.append(((long) i2) + 4294967296L);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) >= 65536) {
                        i++;
                    } else {
                        throw new UnpairedSurrogateException(i, length);
                    }
                }
            }
            i++;
        }
        return i2;
    }

    static int encode(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return processor.encodeUtf8(charSequence, bArr, i, i2);
    }

    static boolean isValidUtf8(ByteBuffer byteBuffer) {
        return processor.isValidUtf8(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    static int partialIsValidUtf8(int i, ByteBuffer byteBuffer, int i2, int i3) {
        return processor.partialIsValidUtf8(i, byteBuffer, i2, i3);
    }

    static void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        processor.encodeUtf8(charSequence, byteBuffer);
    }

    private static int estimateConsecutiveAscii(ByteBuffer byteBuffer, int i, int i2) {
        i2 -= 7;
        int i3 = i;
        while (i3 < i2 && (byteBuffer.getLong(i3) & ASCII_MASK_LONG) == 0) {
            i3 += 8;
        }
        return i3 - i;
    }

    private Utf8() {
    }
}
