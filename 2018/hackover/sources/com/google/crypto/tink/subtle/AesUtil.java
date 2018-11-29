package com.google.crypto.tink.subtle;

import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;

class AesUtil {
    public static final int BLOCK_SIZE = 16;

    AesUtil() {
    }

    static byte[] dbl(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr2.length; i++) {
            bArr2[i] = (byte) ((bArr[i] << 1) & 254);
            if (i < bArr2.length - 1) {
                bArr2[i] = (byte) (bArr2[i] | ((byte) ((bArr[i + 1] >> 7) & 1)));
            }
        }
        bArr2[15] = (byte) (((byte) ((bArr[0] >> 7) & 135)) ^ bArr2[15]);
        return bArr2;
    }

    static byte[] cmacPad(byte[] bArr) {
        if (bArr.length < 16) {
            byte[] copyOf = Arrays.copyOf(bArr, 16);
            copyOf[bArr.length] = ByteCompanionObject.MIN_VALUE;
            return copyOf;
        }
        throw new IllegalArgumentException("x must be smaller than a block.");
    }
}
