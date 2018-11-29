package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.util.Arrays;

class Poly1305 {
    public static final int MAC_KEY_SIZE_IN_BYTES = 32;
    public static final int MAC_TAG_SIZE_IN_BYTES = 16;

    private Poly1305() {
    }

    private static long load32(byte[] bArr, int i) {
        return ((long) (((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)))) & 4294967295L;
    }

    private static long load26(byte[] bArr, int i, int i2) {
        return (load32(bArr, i) >> i2) & 67108863;
    }

    private static void toByteArray(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) ((int) (255 & j));
            i2++;
            j >>= 8;
        }
    }

    private static void copyBlockSize(byte[] bArr, byte[] bArr2, int i) {
        int min = Math.min(16, bArr2.length - i);
        System.arraycopy(bArr2, i, bArr, 0, min);
        bArr[min] = (byte) 1;
        if (min != 16) {
            Arrays.fill(bArr, min + 1, bArr.length, (byte) 0);
        }
    }

    static byte[] computeMac(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (bArr3.length == 32) {
            int i = 0;
            long load26 = load26(bArr3, 0, 0) & 67108863;
            int i2 = 2;
            long load262 = load26(bArr3, 3, 2) & 67108611;
            long load263 = load26(bArr3, 6, 4) & 67092735;
            long load264 = load26(bArr3, 9, 6) & 66076671;
            long load265 = load26(bArr3, 12, 8) & 1048575;
            long j = load262 * 5;
            long j2 = load263 * 5;
            long j3 = load264 * 5;
            long j4 = load265 * 5;
            byte[] bArr5 = new byte[17];
            long j5 = 0;
            int i3 = 0;
            long j6 = 0;
            long j7 = j6;
            long j8 = j7;
            long j9 = j8;
            while (i3 < bArr4.length) {
                copyBlockSize(bArr5, bArr4, i3);
                j5 += load26(bArr5, i, i);
                j6 += load26(bArr5, 3, i2);
                j7 += load26(bArr5, 6, 4);
                j8 += load26(bArr5, 9, 6);
                j9 += load26(bArr5, 12, 8) | ((long) (bArr5[16] << 24));
                long j10 = ((((j5 * load26) + (j6 * j4)) + (j7 * j3)) + (j8 * j2)) + (j9 * j);
                long j11 = ((((j5 * load263) + (j6 * load262)) + (j7 * load26)) + (j8 * j4)) + (j9 * j3);
                long j12 = (((((j5 * load262) + (j6 * load26)) + (j7 * j4)) + (j8 * j3)) + (j9 * j2)) + (j10 >> 26);
                j11 += j12 >> 26;
                long j13 = (((((j5 * load264) + (j6 * load263)) + (j7 * load262)) + (j8 * load26)) + (j9 * j4)) + (j11 >> 26);
                j5 = (((((j5 * load265) + (j6 * load264)) + (j7 * load263)) + (j8 * load262)) + (j9 * load26)) + (j13 >> 26);
                j10 = (j10 & 67108863) + ((j5 >> 26) * 5);
                j6 = (j12 & 67108863) + (j10 >> 26);
                i3 += 16;
                j7 = j11 & 67108863;
                j8 = j13 & 67108863;
                i2 = 2;
                j9 = j5 & 67108863;
                j5 = j10 & 67108863;
                i = 0;
            }
            j7 += j6 >> 26;
            long j14 = j7 & 67108863;
            j8 += j7 >> 26;
            long j15 = j8 & 67108863;
            j9 += j8 >> 26;
            long j16 = j9 & 67108863;
            j5 += (j9 >> 26) * 5;
            load263 = j5 & 67108863;
            long j17 = (j6 & 67108863) + (j5 >> 26);
            long j18 = load263 + 5;
            load264 = j18 & 67108863;
            long j19 = (j18 >> 26) + j17;
            load265 = j19 >> 26;
            j19 &= 67108863;
            load265 = j14 + load265;
            j18 = load265 >> 26;
            load265 &= 67108863;
            j18 = j15 + j18;
            long j20 = j18 & 67108863;
            j = (j16 + (j18 >> 26)) - 67108864;
            long j21 = j >> 63;
            load263 &= j21;
            j17 &= j21;
            j14 &= j21;
            j15 &= j21;
            j16 &= j21;
            long j22 = ~j21;
            j19 = (j19 & j22) | j17;
            j17 = (load265 & j22) | j14;
            j14 = (j20 & j22) | j15;
            j15 = (j & j22) | j16;
            j16 = j19 << 26;
            j19 = ((j19 >> 6) | (j17 << 20)) & 4294967295L;
            j17 = ((j17 >> 12) | (j14 << 14)) & 4294967295L;
            j14 = ((j14 >> 18) | (j15 << 8)) & 4294967295L;
            j16 = ((j16 | (load263 | (load264 & j22))) & 4294967295L) + load32(bArr3, 16);
            j15 = j16 & 4294967295L;
            j19 = (j19 + load32(bArr3, 20)) + (j16 >> 32);
            j16 = j19 & 4294967295L;
            j17 = (j17 + load32(bArr3, 24)) + (j19 >> 32);
            j19 = j17 & 4294967295L;
            j17 = ((j14 + load32(bArr3, 28)) + (j17 >> 32)) & 4294967295L;
            bArr3 = new byte[16];
            toByteArray(bArr3, j15, 0);
            toByteArray(bArr3, j16, 4);
            toByteArray(bArr3, j19, 8);
            toByteArray(bArr3, j17, 12);
            return bArr3;
        }
        throw new IllegalArgumentException("The key length in bytes must be 32.");
    }

    static void verifyMac(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (!Bytes.equal(computeMac(bArr, bArr2), bArr3)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
