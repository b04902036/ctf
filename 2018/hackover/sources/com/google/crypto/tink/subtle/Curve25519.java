package com.google.crypto.tink.subtle;

import com.google.crypto.tink.annotations.Alpha;
import java.security.InvalidKeyException;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;

@Alpha
final class Curve25519 {
    static final byte[][] BANNED_PUBLIC_KEYS = new byte[][]{new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, new byte[]{(byte) 1, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, new byte[]{(byte) -32, (byte) -21, (byte) 122, (byte) 124, (byte) 59, (byte) 65, (byte) -72, (byte) -82, (byte) 22, (byte) 86, (byte) -29, (byte) -6, (byte) -15, (byte) -97, (byte) -60, (byte) 106, (byte) -38, (byte) 9, (byte) -115, (byte) -21, (byte) -100, (byte) 50, (byte) -79, (byte) -3, (byte) -122, (byte) 98, (byte) 5, (byte) 22, (byte) 95, (byte) 73, (byte) -72, (byte) 0}, new byte[]{(byte) 95, (byte) -100, (byte) -107, (byte) -68, (byte) -93, (byte) 80, (byte) -116, (byte) 36, (byte) -79, (byte) -48, (byte) -79, (byte) 85, (byte) -100, (byte) -125, (byte) -17, (byte) 91, (byte) 4, (byte) 68, (byte) 92, (byte) -60, (byte) 88, (byte) 28, (byte) -114, (byte) -122, (byte) -40, (byte) 34, (byte) 78, (byte) -35, (byte) -48, (byte) -97, (byte) 17, (byte) 87}, new byte[]{(byte) -20, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, ByteCompanionObject.MAX_VALUE}, new byte[]{(byte) -19, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, ByteCompanionObject.MAX_VALUE}, new byte[]{(byte) -18, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, ByteCompanionObject.MAX_VALUE}};

    Curve25519() {
    }

    private static void monty(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5, long[] jArr6, long[] jArr7, long[] jArr8, long[] jArr9) {
        long[] jArr10 = jArr5;
        long[] jArr11 = jArr6;
        long[] jArr12 = jArr7;
        long[] jArr13 = jArr8;
        long[] copyOf = Arrays.copyOf(jArr10, 10);
        long[] jArr14 = new long[19];
        long[] jArr15 = new long[19];
        long[] jArr16 = new long[19];
        Object obj = new long[19];
        Object obj2 = new long[19];
        long[] jArr17 = new long[19];
        Object obj3 = new long[19];
        Field25519.sum(jArr5, jArr6);
        Field25519.sub(jArr11, copyOf);
        Object copyOf2 = Arrays.copyOf(jArr12, 10);
        Field25519.sum(jArr7, jArr8);
        Field25519.sub(jArr13, copyOf2);
        Field25519.product(obj, jArr12, jArr11);
        Field25519.product(obj2, jArr10, jArr13);
        Field25519.reduceSizeByModularReduction(obj);
        Field25519.reduceCoefficients(obj);
        Field25519.reduceSizeByModularReduction(obj2);
        Field25519.reduceCoefficients(obj2);
        System.arraycopy(obj, 0, copyOf2, 0, 10);
        Field25519.sum(obj, obj2);
        Field25519.sub(obj2, copyOf2);
        Field25519.square(obj3, obj);
        Field25519.square(jArr17, obj2);
        Field25519.product(obj2, jArr17, jArr9);
        Field25519.reduceSizeByModularReduction(obj2);
        Field25519.reduceCoefficients(obj2);
        jArr13 = jArr3;
        System.arraycopy(obj3, 0, jArr3, 0, 10);
        System.arraycopy(obj2, 0, jArr4, 0, 10);
        Field25519.square(jArr15, jArr10);
        Field25519.square(jArr16, jArr11);
        jArr10 = jArr;
        Field25519.product(jArr, jArr15, jArr16);
        Field25519.reduceSizeByModularReduction(jArr);
        Field25519.reduceCoefficients(jArr);
        Field25519.sub(jArr16, jArr15);
        Arrays.fill(jArr14, 10, jArr14.length - 1, 0);
        Field25519.scalarProduct(jArr14, jArr16, 121665);
        Field25519.reduceCoefficients(jArr14);
        Field25519.sum(jArr14, jArr15);
        jArr10 = jArr2;
        Field25519.product(jArr2, jArr16, jArr14);
        Field25519.reduceSizeByModularReduction(jArr2);
        Field25519.reduceCoefficients(jArr2);
    }

    static void swapConditional(long[] jArr, long[] jArr2, int i) {
        i = -i;
        for (int i2 = 0; i2 < 10; i2++) {
            int i3 = (((int) jArr[i2]) ^ ((int) jArr2[i2])) & i;
            jArr[i2] = (long) (((int) jArr[i2]) ^ i3);
            jArr2[i2] = (long) (i3 ^ ((int) jArr2[i2]));
        }
    }

    static void copyConditional(long[] jArr, long[] jArr2, int i) {
        i = -i;
        for (int i2 = 0; i2 < 10; i2++) {
            jArr[i2] = (long) (((((int) jArr[i2]) ^ ((int) jArr2[i2])) & i) ^ ((int) jArr[i2]));
        }
    }

    static void curveMult(long[] jArr, byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        long[] jArr2 = jArr;
        validatePubKeyAndClearMsb(bArr2);
        Object expand = Field25519.expand(bArr2);
        long[] jArr3 = new long[19];
        long[] jArr4 = new long[19];
        int i = 0;
        jArr4[0] = 1;
        long[] jArr5 = new long[19];
        jArr5[0] = 1;
        long[] jArr6 = new long[19];
        long[] jArr7 = new long[19];
        long[] jArr8 = new long[19];
        jArr8[0] = 1;
        long[] jArr9 = new long[19];
        long[] jArr10 = new long[19];
        jArr10[0] = 1;
        long[] jArr11 = new long[19];
        int i2 = 10;
        System.arraycopy(expand, 0, jArr3, 0, 10);
        jArr11 = jArr9;
        int i3 = 0;
        while (i3 < 32) {
            long[] jArr12;
            long[] jArr13;
            long[] jArr14;
            int i4 = bArr[(32 - i3) - 1] & 255;
            long[] jArr15 = jArr11;
            jArr11 = jArr8;
            jArr8 = jArr5;
            jArr5 = jArr4;
            jArr4 = jArr10;
            int i5 = i;
            long[] jArr16 = jArr7;
            jArr7 = jArr3;
            jArr3 = jArr16;
            while (i5 < 8) {
                i = (i4 >> (7 - i5)) & 1;
                swapConditional(jArr8, jArr7, i);
                swapConditional(jArr6, jArr5, i);
                long[] jArr17 = jArr11;
                long[] jArr18 = jArr3;
                long[] jArr19 = jArr4;
                long[] jArr20 = jArr15;
                int i6 = i4;
                long[] jArr21 = jArr5;
                jArr12 = jArr6;
                jArr13 = jArr7;
                jArr14 = jArr8;
                monty(jArr15, jArr4, jArr18, jArr17, jArr8, jArr6, jArr7, jArr21, expand);
                jArr7 = jArr18;
                swapConditional(jArr20, jArr7, i);
                jArr8 = jArr17;
                swapConditional(jArr19, jArr8, i);
                i5++;
                jArr5 = jArr8;
                jArr6 = jArr19;
                jArr8 = jArr20;
                i4 = i6;
                jArr11 = jArr21;
                jArr4 = jArr12;
                jArr3 = jArr13;
                jArr15 = jArr14;
            }
            jArr12 = jArr6;
            jArr13 = jArr7;
            jArr14 = jArr8;
            jArr8 = jArr11;
            jArr7 = jArr3;
            i3++;
            jArr10 = jArr4;
            jArr11 = jArr15;
            jArr4 = jArr5;
            jArr3 = jArr13;
            jArr5 = jArr14;
            i = 0;
            i2 = 10;
        }
        jArr11 = new long[i2];
        Field25519.inverse(jArr11, jArr6);
        Field25519.mult(jArr2, jArr5, jArr11);
        if (!isCollinear(expand, jArr2, jArr3, jArr4)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Arithmetic error in curve multiplication with the public key: ");
            stringBuilder.append(Hex.encode(bArr2));
            throw new IllegalStateException(stringBuilder.toString());
        }
    }

    private static void validatePubKeyAndClearMsb(byte[] bArr) throws InvalidKeyException {
        if (bArr.length == 32) {
            bArr[31] = (byte) (bArr[31] & 127);
            for (int i = 0; i < BANNED_PUBLIC_KEYS.length; i++) {
                if (Bytes.equal(BANNED_PUBLIC_KEYS[i], bArr)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Banned public key: ");
                    stringBuilder.append(Hex.encode(BANNED_PUBLIC_KEYS[i]));
                    throw new InvalidKeyException(stringBuilder.toString());
                }
            }
            return;
        }
        throw new InvalidKeyException("Public key length is not 32-byte");
    }

    private static boolean isCollinear(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4) {
        long[] jArr5 = new long[10];
        long[] jArr6 = new long[10];
        long[] jArr7 = new long[11];
        long[] jArr8 = new long[11];
        long[] jArr9 = new long[11];
        Field25519.mult(jArr5, jArr, jArr2);
        Field25519.sum(jArr6, jArr, jArr2);
        jArr = new long[10];
        jArr[0] = 486662;
        Field25519.sum(jArr8, jArr6, jArr);
        Field25519.mult(jArr8, jArr8, jArr4);
        Field25519.sum(jArr8, jArr3);
        Field25519.mult(jArr8, jArr8, jArr5);
        Field25519.mult(jArr8, jArr8, jArr3);
        Field25519.scalarProduct(jArr7, jArr8, 4);
        Field25519.reduceCoefficients(jArr7);
        Field25519.mult(jArr8, jArr5, jArr4);
        Field25519.sub(jArr8, jArr8, jArr4);
        Field25519.mult(jArr9, jArr6, jArr3);
        Field25519.sum(jArr8, jArr8, jArr9);
        Field25519.square(jArr8, jArr8);
        return Bytes.equal(Field25519.contract(jArr7), Field25519.contract(jArr8));
    }
}
