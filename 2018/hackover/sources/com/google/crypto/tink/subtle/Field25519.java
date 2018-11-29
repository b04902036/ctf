package com.google.crypto.tink.subtle;

import com.google.crypto.tink.annotations.Alpha;
import java.util.Arrays;

@Alpha
final class Field25519 {
    private static final int[] EXPAND_SHIFT = new int[]{0, 2, 3, 5, 6, 0, 1, 3, 4, 6};
    private static final int[] EXPAND_START = new int[]{0, 3, 6, 9, 12, 16, 19, 22, 25, 28};
    static final int FIELD_LEN = 32;
    static final int LIMB_CNT = 10;
    private static final int[] MASK = new int[]{67108863, 33554431};
    private static final int[] SHIFT = new int[]{26, 25};
    private static final long TWO_TO_25 = 33554432;
    private static final long TWO_TO_26 = 67108864;

    private static int eq(int i, int i2) {
        i = ~(i ^ i2);
        i &= i << 16;
        i &= i << 8;
        i &= i << 4;
        i &= i << 2;
        return (i & (i << 1)) >> 31;
    }

    private static int gte(int i, int i2) {
        return ~((i - i2) >> 31);
    }

    Field25519() {
    }

    static void sum(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 10; i++) {
            jArr[i] = jArr2[i] + jArr3[i];
        }
    }

    static void sum(long[] jArr, long[] jArr2) {
        sum(jArr, jArr, jArr2);
    }

    static void sub(long[] jArr, long[] jArr2, long[] jArr3) {
        for (int i = 0; i < 10; i++) {
            jArr[i] = jArr2[i] - jArr3[i];
        }
    }

    static void sub(long[] jArr, long[] jArr2) {
        sub(jArr, jArr2, jArr);
    }

    static void scalarProduct(long[] jArr, long[] jArr2, long j) {
        for (int i = 0; i < 10; i++) {
            jArr[i] = jArr2[i] * j;
        }
    }

    static void product(long[] jArr, long[] jArr2, long[] jArr3) {
        jArr[0] = jArr2[0] * jArr3[0];
        jArr[1] = (jArr2[0] * jArr3[1]) + (jArr2[1] * jArr3[0]);
        jArr[2] = (((jArr2[1] * 2) * jArr3[1]) + (jArr2[0] * jArr3[2])) + (jArr2[2] * jArr3[0]);
        jArr[3] = (((jArr2[1] * jArr3[2]) + (jArr2[2] * jArr3[1])) + (jArr2[0] * jArr3[3])) + (jArr2[3] * jArr3[0]);
        jArr[4] = (((jArr2[2] * jArr3[2]) + (((jArr2[1] * jArr3[3]) + (jArr2[3] * jArr3[1])) * 2)) + (jArr2[0] * jArr3[4])) + (jArr2[4] * jArr3[0]);
        jArr[5] = (((((jArr2[2] * jArr3[3]) + (jArr2[3] * jArr3[2])) + (jArr2[1] * jArr3[4])) + (jArr2[4] * jArr3[1])) + (jArr2[0] * jArr3[5])) + (jArr2[5] * jArr3[0]);
        jArr[6] = (((((((jArr2[3] * jArr3[3]) + (jArr2[1] * jArr3[5])) + (jArr2[5] * jArr3[1])) * 2) + (jArr2[2] * jArr3[4])) + (jArr2[4] * jArr3[2])) + (jArr2[0] * jArr3[6])) + (jArr2[6] * jArr3[0]);
        jArr[7] = (((((((jArr2[3] * jArr3[4]) + (jArr2[4] * jArr3[3])) + (jArr2[2] * jArr3[5])) + (jArr2[5] * jArr3[2])) + (jArr2[1] * jArr3[6])) + (jArr2[6] * jArr3[1])) + (jArr2[0] * jArr3[7])) + (jArr2[7] * jArr3[0]);
        jArr[8] = (((((jArr2[4] * jArr3[4]) + (((((jArr2[3] * jArr3[5]) + (jArr2[5] * jArr3[3])) + (jArr2[1] * jArr3[7])) + (jArr2[7] * jArr3[1])) * 2)) + (jArr2[2] * jArr3[6])) + (jArr2[6] * jArr3[2])) + (jArr2[0] * jArr3[8])) + (jArr2[8] * jArr3[0]);
        jArr[9] = (((((((((jArr2[4] * jArr3[5]) + (jArr2[5] * jArr3[4])) + (jArr2[3] * jArr3[6])) + (jArr2[6] * jArr3[3])) + (jArr2[2] * jArr3[7])) + (jArr2[7] * jArr3[2])) + (jArr2[1] * jArr3[8])) + (jArr2[8] * jArr3[1])) + (jArr2[0] * jArr3[9])) + (jArr2[9] * jArr3[0]);
        jArr[10] = (((((((((jArr2[5] * jArr3[5]) + (jArr2[3] * jArr3[7])) + (jArr2[7] * jArr3[3])) + (jArr2[1] * jArr3[9])) + (jArr2[9] * jArr3[1])) * 2) + (jArr2[4] * jArr3[6])) + (jArr2[6] * jArr3[4])) + (jArr2[2] * jArr3[8])) + (jArr2[8] * jArr3[2]);
        jArr[11] = (((((((jArr2[5] * jArr3[6]) + (jArr2[6] * jArr3[5])) + (jArr2[4] * jArr3[7])) + (jArr2[7] * jArr3[4])) + (jArr2[3] * jArr3[8])) + (jArr2[8] * jArr3[3])) + (jArr2[2] * jArr3[9])) + (jArr2[9] * jArr3[2]);
        jArr[12] = (((jArr2[6] * jArr3[6]) + (((((jArr2[5] * jArr3[7]) + (jArr2[7] * jArr3[5])) + (jArr2[3] * jArr3[9])) + (jArr2[9] * jArr3[3])) * 2)) + (jArr2[4] * jArr3[8])) + (jArr2[8] * jArr3[4]);
        jArr[13] = (((((jArr2[6] * jArr3[7]) + (jArr2[7] * jArr3[6])) + (jArr2[5] * jArr3[8])) + (jArr2[8] * jArr3[5])) + (jArr2[4] * jArr3[9])) + (jArr2[9] * jArr3[4]);
        jArr[14] = (((((jArr2[7] * jArr3[7]) + (jArr2[5] * jArr3[9])) + (jArr2[9] * jArr3[5])) * 2) + (jArr2[6] * jArr3[8])) + (jArr2[8] * jArr3[6]);
        jArr[15] = (((jArr2[7] * jArr3[8]) + (jArr2[8] * jArr3[7])) + (jArr2[6] * jArr3[9])) + (jArr2[9] * jArr3[6]);
        jArr[16] = (jArr2[8] * jArr3[8]) + (((jArr2[7] * jArr3[9]) + (jArr2[9] * jArr3[7])) * 2);
        jArr[17] = (jArr2[8] * jArr3[9]) + (jArr2[9] * jArr3[8]);
        jArr[18] = (jArr2[9] * 2) * jArr3[9];
    }

    static void reduceSizeByModularReduction(long[] jArr) {
        jArr[8] = jArr[8] + (jArr[18] << 4);
        jArr[8] = jArr[8] + (jArr[18] << 1);
        jArr[8] = jArr[8] + jArr[18];
        jArr[7] = jArr[7] + (jArr[17] << 4);
        jArr[7] = jArr[7] + (jArr[17] << 1);
        jArr[7] = jArr[7] + jArr[17];
        jArr[6] = jArr[6] + (jArr[16] << 4);
        jArr[6] = jArr[6] + (jArr[16] << 1);
        jArr[6] = jArr[6] + jArr[16];
        jArr[5] = jArr[5] + (jArr[15] << 4);
        jArr[5] = jArr[5] + (jArr[15] << 1);
        jArr[5] = jArr[5] + jArr[15];
        jArr[4] = jArr[4] + (jArr[14] << 4);
        jArr[4] = jArr[4] + (jArr[14] << 1);
        jArr[4] = jArr[4] + jArr[14];
        jArr[3] = jArr[3] + (jArr[13] << 4);
        jArr[3] = jArr[3] + (jArr[13] << 1);
        jArr[3] = jArr[3] + jArr[13];
        jArr[2] = jArr[2] + (jArr[12] << 4);
        jArr[2] = jArr[2] + (jArr[12] << 1);
        jArr[2] = jArr[2] + jArr[12];
        jArr[1] = jArr[1] + (jArr[11] << 4);
        jArr[1] = jArr[1] + (jArr[11] << 1);
        jArr[1] = jArr[1] + jArr[11];
        jArr[0] = jArr[0] + (jArr[10] << 4);
        jArr[0] = jArr[0] + (jArr[10] << 1);
        jArr[0] = jArr[0] + jArr[10];
    }

    static void reduceCoefficients(long[] jArr) {
        jArr[10] = 0;
        int i = 0;
        while (i < 10) {
            long j = jArr[i] / TWO_TO_26;
            jArr[i] = jArr[i] - (j << 26);
            int i2 = i + 1;
            jArr[i2] = jArr[i2] + j;
            long j2 = jArr[i2] / TWO_TO_25;
            jArr[i2] = jArr[i2] - (j2 << 25);
            i += 2;
            jArr[i] = jArr[i] + j2;
        }
        jArr[0] = jArr[0] + (jArr[10] << 4);
        jArr[0] = jArr[0] + (jArr[10] << 1);
        jArr[0] = jArr[0] + jArr[10];
        jArr[10] = 0;
        long j3 = jArr[0] / TWO_TO_26;
        jArr[0] = jArr[0] - (j3 << 26);
        jArr[1] = jArr[1] + j3;
    }

    static void mult(long[] jArr, long[] jArr2, long[] jArr3) {
        Object obj = new long[19];
        product(obj, jArr2, jArr3);
        reduceSizeByModularReduction(obj);
        reduceCoefficients(obj);
        System.arraycopy(obj, 0, jArr, 0, 10);
    }

    private static void squareInner(long[] jArr, long[] jArr2) {
        jArr[0] = jArr2[0] * jArr2[0];
        jArr[1] = (jArr2[0] * 2) * jArr2[1];
        jArr[2] = ((jArr2[1] * jArr2[1]) + (jArr2[0] * jArr2[2])) * 2;
        jArr[3] = ((jArr2[1] * jArr2[2]) + (jArr2[0] * jArr2[3])) * 2;
        jArr[4] = ((jArr2[2] * jArr2[2]) + ((jArr2[1] * 4) * jArr2[3])) + ((jArr2[0] * 2) * jArr2[4]);
        jArr[5] = (((jArr2[2] * jArr2[3]) + (jArr2[1] * jArr2[4])) + (jArr2[0] * jArr2[5])) * 2;
        jArr[6] = ((((jArr2[3] * jArr2[3]) + (jArr2[2] * jArr2[4])) + (jArr2[0] * jArr2[6])) + ((jArr2[1] * 2) * jArr2[5])) * 2;
        jArr[7] = ((((jArr2[3] * jArr2[4]) + (jArr2[2] * jArr2[5])) + (jArr2[1] * jArr2[6])) + (jArr2[0] * jArr2[7])) * 2;
        jArr[8] = (jArr2[4] * jArr2[4]) + ((((jArr2[2] * jArr2[6]) + (jArr2[0] * jArr2[8])) + (((jArr2[1] * jArr2[7]) + (jArr2[3] * jArr2[5])) * 2)) * 2);
        jArr[9] = (((((jArr2[4] * jArr2[5]) + (jArr2[3] * jArr2[6])) + (jArr2[2] * jArr2[7])) + (jArr2[1] * jArr2[8])) + (jArr2[0] * jArr2[9])) * 2;
        jArr[10] = ((((jArr2[5] * jArr2[5]) + (jArr2[4] * jArr2[6])) + (jArr2[2] * jArr2[8])) + (((jArr2[3] * jArr2[7]) + (jArr2[1] * jArr2[9])) * 2)) * 2;
        jArr[11] = ((((jArr2[5] * jArr2[6]) + (jArr2[4] * jArr2[7])) + (jArr2[3] * jArr2[8])) + (jArr2[2] * jArr2[9])) * 2;
        jArr[12] = (jArr2[6] * jArr2[6]) + (((jArr2[4] * jArr2[8]) + (((jArr2[5] * jArr2[7]) + (jArr2[3] * jArr2[9])) * 2)) * 2);
        jArr[13] = (((jArr2[6] * jArr2[7]) + (jArr2[5] * jArr2[8])) + (jArr2[4] * jArr2[9])) * 2;
        jArr[14] = (((jArr2[7] * jArr2[7]) + (jArr2[6] * jArr2[8])) + ((jArr2[5] * 2) * jArr2[9])) * 2;
        jArr[15] = ((jArr2[7] * jArr2[8]) + (jArr2[6] * jArr2[9])) * 2;
        jArr[16] = (jArr2[8] * jArr2[8]) + ((jArr2[7] * 4) * jArr2[9]);
        jArr[17] = (jArr2[8] * 2) * jArr2[9];
        jArr[18] = (jArr2[9] * 2) * jArr2[9];
    }

    static void square(long[] jArr, long[] jArr2) {
        Object obj = new long[19];
        squareInner(obj, jArr2);
        reduceSizeByModularReduction(obj);
        reduceCoefficients(obj);
        System.arraycopy(obj, 0, jArr, 0, 10);
    }

    static long[] expand(byte[] bArr) {
        long[] jArr = new long[10];
        for (int i = 0; i < 10; i++) {
            jArr[i] = ((((((long) (bArr[EXPAND_START[i]] & 255)) | (((long) (bArr[EXPAND_START[i] + 1] & 255)) << 8)) | (((long) (bArr[EXPAND_START[i] + 2] & 255)) << 16)) | (((long) (bArr[EXPAND_START[i] + 3] & 255)) << 24)) >> EXPAND_SHIFT[i]) & ((long) MASK[i & 1]);
        }
        return jArr;
    }

    static byte[] contract(long[] jArr) {
        int i;
        int i2;
        int i3;
        jArr = Arrays.copyOf(jArr, 10);
        int i4 = 0;
        for (i = 0; i < 2; i++) {
            int i5 = 0;
            while (i5 < 9) {
                int i6 = i5 & 1;
                i2 = -((int) ((jArr[i5] & (jArr[i5] >> 31)) >> SHIFT[i6]));
                jArr[i5] = jArr[i5] + ((long) (i2 << SHIFT[i6]));
                i5++;
                jArr[i5] = jArr[i5] - ((long) i2);
            }
            i3 = -((int) (((jArr[9] >> 31) & jArr[9]) >> 25));
            jArr[9] = jArr[9] + ((long) (i3 << 25));
            jArr[0] = jArr[0] - ((long) (i3 * 19));
        }
        i = -((int) ((jArr[0] & (jArr[0] >> 31)) >> 26));
        jArr[0] = jArr[0] + ((long) (i << 26));
        jArr[1] = jArr[1] - ((long) i);
        for (i = 0; i < 2; i++) {
            i2 = 0;
            while (i2 < 9) {
                int i7 = i2 & 1;
                int i8 = (int) (jArr[i2] >> SHIFT[i7]);
                jArr[i2] = jArr[i2] & ((long) MASK[i7]);
                i2++;
                jArr[i2] = jArr[i2] + ((long) i8);
            }
        }
        i = (int) (jArr[9] >> 25);
        jArr[9] = jArr[9] & 33554431;
        jArr[0] = jArr[0] + ((long) (i * 19));
        int gte = gte((int) jArr[0], 67108845);
        for (i = 1; i < 10; i++) {
            gte &= eq((int) jArr[i], MASK[i & 1]);
        }
        jArr[0] = jArr[0] - ((long) (gte & 67108845));
        long j = (long) (33554431 & gte);
        jArr[1] = jArr[1] - j;
        for (i = 2; i < 10; i += 2) {
            jArr[i] = jArr[i] - ((long) (67108863 & gte));
            i3 = i + 1;
            jArr[i3] = jArr[i3] - j;
        }
        for (i = 0; i < 10; i++) {
            jArr[i] = jArr[i] << EXPAND_SHIFT[i];
        }
        byte[] bArr = new byte[32];
        while (i4 < 10) {
            i3 = EXPAND_START[i4];
            bArr[i3] = (byte) ((int) (((long) bArr[i3]) | (jArr[i4] & 255)));
            i3 = EXPAND_START[i4] + 1;
            bArr[i3] = (byte) ((int) (((long) bArr[i3]) | ((jArr[i4] >> 8) & 255)));
            i3 = EXPAND_START[i4] + 2;
            bArr[i3] = (byte) ((int) (((long) bArr[i3]) | ((jArr[i4] >> 16) & 255)));
            i3 = EXPAND_START[i4] + 3;
            bArr[i3] = (byte) ((int) (((long) bArr[i3]) | ((jArr[i4] >> 24) & 255)));
            i4++;
        }
        return bArr;
    }

    static void inverse(long[] jArr, long[] jArr2) {
        int i;
        int i2;
        long[] jArr3 = new long[10];
        long[] jArr4 = new long[10];
        long[] jArr5 = new long[10];
        long[] jArr6 = new long[10];
        long[] jArr7 = new long[10];
        long[] jArr8 = new long[10];
        long[] jArr9 = new long[10];
        long[] jArr10 = new long[10];
        long[] jArr11 = new long[10];
        long[] jArr12 = new long[10];
        square(jArr3, jArr2);
        square(jArr12, jArr3);
        square(jArr11, jArr12);
        mult(jArr4, jArr11, jArr2);
        mult(jArr5, jArr4, jArr3);
        square(jArr11, jArr5);
        mult(jArr6, jArr11, jArr4);
        square(jArr11, jArr6);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        mult(jArr7, jArr11, jArr6);
        square(jArr11, jArr7);
        square(jArr12, jArr11);
        int i3 = 2;
        for (i = 2; i < 10; i += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr8, jArr12, jArr7);
        square(jArr11, jArr8);
        square(jArr12, jArr11);
        for (i = 2; i < 20; i += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr11, jArr12, jArr8);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        for (i = 2; i < 10; i += 2) {
            square(jArr12, jArr11);
            square(jArr11, jArr12);
        }
        mult(jArr9, jArr11, jArr7);
        square(jArr11, jArr9);
        square(jArr12, jArr11);
        for (i2 = 2; i2 < 50; i2 += 2) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
        }
        mult(jArr10, jArr12, jArr9);
        square(jArr12, jArr10);
        square(jArr11, jArr12);
        for (i2 = 2; i2 < 100; i2 += 2) {
            square(jArr12, jArr11);
            square(jArr11, jArr12);
        }
        mult(jArr12, jArr11, jArr10);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        while (i3 < 50) {
            square(jArr11, jArr12);
            square(jArr12, jArr11);
            i3 += 2;
        }
        mult(jArr11, jArr12, jArr9);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        square(jArr11, jArr12);
        square(jArr12, jArr11);
        mult(jArr, jArr12, jArr5);
    }
}
