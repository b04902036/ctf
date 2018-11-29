package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class Bytes {
    /* JADX WARNING: Missing block: B:13:0x001d, code:
            return false;
     */
    public static final boolean equal(byte[] r5, byte[] r6) {
        /*
        r0 = 0;
        if (r5 == 0) goto L_0x001d;
    L_0x0003:
        if (r6 != 0) goto L_0x0006;
    L_0x0005:
        goto L_0x001d;
    L_0x0006:
        r1 = r5.length;
        r2 = r6.length;
        if (r1 == r2) goto L_0x000b;
    L_0x000a:
        return r0;
    L_0x000b:
        r1 = r0;
        r2 = r1;
    L_0x000d:
        r3 = r5.length;
        if (r1 >= r3) goto L_0x0019;
    L_0x0010:
        r3 = r5[r1];
        r4 = r6[r1];
        r3 = r3 ^ r4;
        r2 = r2 | r3;
        r1 = r1 + 1;
        goto L_0x000d;
    L_0x0019:
        if (r2 != 0) goto L_0x001c;
    L_0x001b:
        r0 = 1;
    L_0x001c:
        return r0;
    L_0x001d:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.Bytes.equal(byte[], byte[]):boolean");
    }

    public static byte[] concat(byte[]... bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i = 0;
        int i2 = i;
        while (i < length) {
            byte[] bArr2 = bArr[i];
            if (i2 <= Integer.MAX_VALUE - bArr2.length) {
                i2 += bArr2.length;
                i++;
            } else {
                throw new GeneralSecurityException("exceeded size limit");
            }
        }
        Object obj = new byte[i2];
        i = bArr.length;
        i2 = 0;
        int i3 = i2;
        while (i2 < i) {
            Object obj2 = bArr[i2];
            System.arraycopy(obj2, 0, obj, i3, obj2.length);
            i3 += obj2.length;
            i2++;
        }
        return obj;
    }

    public static final byte[] xor(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (i3 < 0 || bArr.length - i3 < i || bArr2.length - i3 < i2) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        byte[] bArr3 = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            bArr3[i4] = (byte) (bArr[i4 + i] ^ bArr2[i4 + i2]);
        }
        return bArr3;
    }

    public static final void xor(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i) {
        if (i < 0 || byteBuffer2.remaining() < i || byteBuffer3.remaining() < i || byteBuffer.remaining() < i) {
            throw new IllegalArgumentException("That combination of buffers, offsets and length to xor result in out-of-bond accesses.");
        }
        for (int i2 = 0; i2 < i; i2++) {
            byteBuffer.put((byte) (byteBuffer2.get() ^ byteBuffer3.get()));
        }
    }

    public static final byte[] xor(byte[] bArr, byte[] bArr2) {
        if (bArr.length == bArr2.length) {
            return xor(bArr, 0, bArr2, 0, bArr.length);
        }
        throw new IllegalArgumentException("The lengths of x and y should match.");
    }

    public static final byte[] xorEnd(byte[] bArr, byte[] bArr2) {
        if (bArr.length >= bArr2.length) {
            int length = bArr.length - bArr2.length;
            bArr = Arrays.copyOf(bArr, bArr.length);
            for (int i = 0; i < bArr2.length; i++) {
                int i2 = length + i;
                bArr[i2] = (byte) (bArr[i2] ^ bArr2[i]);
            }
            return bArr;
        }
        throw new IllegalArgumentException("xorEnd requires a.length >= b.length");
    }

    public static byte[] intToByteArray(int i, int i2) {
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) ((i2 >> (i3 * 8)) & 255);
        }
        return bArr;
    }

    public static int byteArrayToInt(byte[] bArr) {
        return byteArrayToInt(bArr, bArr.length);
    }

    public static int byteArrayToInt(byte[] bArr, int i) {
        return byteArrayToInt(bArr, 0, i);
    }

    public static int byteArrayToInt(byte[] bArr, int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            i4 += (bArr[i3 + i] & 255) << (i3 * 8);
            i3++;
        }
        return i4;
    }
}
