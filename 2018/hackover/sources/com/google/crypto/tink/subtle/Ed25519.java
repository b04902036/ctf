package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;

final class Ed25519 {
    private static final CachedXYT CACHED_NEUTRAL = new CachedXYT(new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    static final byte[] GROUP_ORDER = new byte[]{(byte) -19, (byte) -45, (byte) -11, (byte) 92, (byte) 26, (byte) 99, (byte) 18, (byte) 88, (byte) -42, (byte) -100, (byte) -9, (byte) -94, (byte) -34, (byte) -7, (byte) -34, (byte) 20, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 16};
    private static final PartialXYZT NEUTRAL = new PartialXYZT(new XYZ(new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}), new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    public static final int PUBLIC_KEY_LEN = 32;
    public static final int SECRET_KEY_LEN = 32;
    public static final int SIGNATURE_LEN = 64;

    static class CachedXYT {
        final long[] t2d;
        final long[] yMinusX;
        final long[] yPlusX;

        CachedXYT() {
            this(new long[10], new long[10], new long[10]);
        }

        CachedXYT(long[] jArr, long[] jArr2, long[] jArr3) {
            this.yPlusX = jArr;
            this.yMinusX = jArr2;
            this.t2d = jArr3;
        }

        CachedXYT(CachedXYT cachedXYT) {
            this.yPlusX = Arrays.copyOf(cachedXYT.yPlusX, 10);
            this.yMinusX = Arrays.copyOf(cachedXYT.yMinusX, 10);
            this.t2d = Arrays.copyOf(cachedXYT.t2d, 10);
        }

        void multByZ(long[] jArr, long[] jArr2) {
            System.arraycopy(jArr2, 0, jArr, 0, 10);
        }

        void copyConditional(CachedXYT cachedXYT, int i) {
            Curve25519.copyConditional(this.yPlusX, cachedXYT.yPlusX, i);
            Curve25519.copyConditional(this.yMinusX, cachedXYT.yMinusX, i);
            Curve25519.copyConditional(this.t2d, cachedXYT.t2d, i);
        }
    }

    private static class PartialXYZT {
        final long[] t;
        final XYZ xyz;

        PartialXYZT() {
            this(new XYZ(), new long[10]);
        }

        PartialXYZT(XYZ xyz, long[] jArr) {
            this.xyz = xyz;
            this.t = jArr;
        }

        PartialXYZT(PartialXYZT partialXYZT) {
            this.xyz = new XYZ(partialXYZT.xyz);
            this.t = Arrays.copyOf(partialXYZT.t, 10);
        }
    }

    private static class XYZ {
        final long[] x;
        final long[] y;
        final long[] z;

        XYZ() {
            this(new long[10], new long[10], new long[10]);
        }

        XYZ(long[] jArr, long[] jArr2, long[] jArr3) {
            this.x = jArr;
            this.y = jArr2;
            this.z = jArr3;
        }

        XYZ(XYZ xyz) {
            this.x = Arrays.copyOf(xyz.x, 10);
            this.y = Arrays.copyOf(xyz.y, 10);
            this.z = Arrays.copyOf(xyz.z, 10);
        }

        XYZ(PartialXYZT partialXYZT) {
            this();
            fromPartialXYZT(this, partialXYZT);
        }

        static XYZ fromPartialXYZT(XYZ xyz, PartialXYZT partialXYZT) {
            Field25519.mult(xyz.x, partialXYZT.xyz.x, partialXYZT.t);
            Field25519.mult(xyz.y, partialXYZT.xyz.y, partialXYZT.xyz.z);
            Field25519.mult(xyz.z, partialXYZT.xyz.z, partialXYZT.t);
            return xyz;
        }

        byte[] toBytes() {
            long[] jArr = new long[10];
            long[] jArr2 = new long[10];
            long[] jArr3 = new long[10];
            Field25519.inverse(jArr, this.z);
            Field25519.mult(jArr2, this.x, jArr);
            Field25519.mult(jArr3, this.y, jArr);
            byte[] contract = Field25519.contract(jArr3);
            contract[31] = (byte) (contract[31] ^ (Ed25519.getLsb(jArr2) << 7));
            return contract;
        }

        boolean isOnCurve() {
            long[] jArr = new long[10];
            Field25519.square(jArr, this.x);
            long[] jArr2 = new long[10];
            Field25519.square(jArr2, this.y);
            long[] jArr3 = new long[10];
            Field25519.square(jArr3, this.z);
            long[] jArr4 = new long[10];
            Field25519.square(jArr4, jArr3);
            long[] jArr5 = new long[10];
            Field25519.sub(jArr5, jArr2, jArr);
            Field25519.mult(jArr5, jArr5, jArr3);
            long[] jArr6 = new long[10];
            Field25519.mult(jArr6, jArr, jArr2);
            Field25519.mult(jArr6, jArr6, Ed25519Constants.D);
            Field25519.sum(jArr6, jArr4);
            return Bytes.equal(Field25519.contract(jArr5), Field25519.contract(jArr6));
        }
    }

    private static class XYZT {
        final long[] t;
        final XYZ xyz;

        XYZT() {
            this(new XYZ(), new long[10]);
        }

        XYZT(XYZ xyz, long[] jArr) {
            this.xyz = xyz;
            this.t = jArr;
        }

        XYZT(PartialXYZT partialXYZT) {
            this();
            fromPartialXYZT(this, partialXYZT);
        }

        private static XYZT fromPartialXYZT(XYZT xyzt, PartialXYZT partialXYZT) {
            Field25519.mult(xyzt.xyz.x, partialXYZT.xyz.x, partialXYZT.t);
            Field25519.mult(xyzt.xyz.y, partialXYZT.xyz.y, partialXYZT.xyz.z);
            Field25519.mult(xyzt.xyz.z, partialXYZT.xyz.z, partialXYZT.t);
            Field25519.mult(xyzt.t, partialXYZT.xyz.x, partialXYZT.xyz.y);
            return xyzt;
        }

        private static XYZT fromBytesNegateVarTime(byte[] bArr) throws GeneralSecurityException {
            long[] jArr = new long[10];
            long[] expand = Field25519.expand(bArr);
            long[] jArr2 = new long[10];
            jArr2[0] = 1;
            long[] jArr3 = new long[10];
            long[] jArr4 = new long[10];
            long[] jArr5 = new long[10];
            long[] jArr6 = new long[10];
            long[] jArr7 = new long[10];
            Field25519.square(jArr4, expand);
            Field25519.mult(jArr5, jArr4, Ed25519Constants.D);
            Field25519.sub(jArr4, jArr4, jArr2);
            Field25519.sum(jArr5, jArr5, jArr2);
            long[] jArr8 = new long[10];
            Field25519.square(jArr8, jArr5);
            Field25519.mult(jArr8, jArr8, jArr5);
            Field25519.square(jArr, jArr8);
            Field25519.mult(jArr, jArr, jArr5);
            Field25519.mult(jArr, jArr, jArr4);
            Ed25519.pow2252m3(jArr, jArr);
            Field25519.mult(jArr, jArr, jArr8);
            Field25519.mult(jArr, jArr, jArr4);
            Field25519.square(jArr6, jArr);
            Field25519.mult(jArr6, jArr6, jArr5);
            Field25519.sub(jArr7, jArr6, jArr4);
            if (Ed25519.isNonZeroVarTime(jArr7)) {
                Field25519.sum(jArr7, jArr6, jArr4);
                if (Ed25519.isNonZeroVarTime(jArr7)) {
                    throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. No square root exists for modulo 2^255-19");
                }
                Field25519.mult(jArr, jArr, Ed25519Constants.SQRTM1);
            }
            if (Ed25519.isNonZeroVarTime(jArr) || ((bArr[31] & 255) >> 7) == 0) {
                if (Ed25519.getLsb(jArr) == ((bArr[31] & 255) >> 7)) {
                    Ed25519.neg(jArr, jArr);
                }
                Field25519.mult(jArr3, jArr, expand);
                return new XYZT(new XYZ(jArr, expand, jArr2), jArr3);
            }
            throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. Computed x is zero and encoded x's least significant bit is not zero");
        }
    }

    private static class CachedXYZT extends CachedXYT {
        private final long[] z;

        CachedXYZT() {
            this(new long[10], new long[10], new long[10], new long[10]);
        }

        CachedXYZT(XYZT xyzt) {
            this();
            Field25519.sum(this.yPlusX, xyzt.xyz.y, xyzt.xyz.x);
            Field25519.sub(this.yMinusX, xyzt.xyz.y, xyzt.xyz.x);
            System.arraycopy(xyzt.xyz.z, 0, this.z, 0, 10);
            Field25519.mult(this.t2d, xyzt.t, Ed25519Constants.D2);
        }

        CachedXYZT(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4) {
            super(jArr, jArr2, jArr4);
            this.z = jArr3;
        }

        public void multByZ(long[] jArr, long[] jArr2) {
            Field25519.mult(jArr, jArr2, this.z);
        }
    }

    private static int eq(int i, int i2) {
        i = (~(i ^ i2)) & 255;
        i &= i << 4;
        i &= i << 2;
        return ((i & (i << 1)) >> 7) & 1;
    }

    Ed25519() {
    }

    private static void add(PartialXYZT partialXYZT, XYZT xyzt, CachedXYT cachedXYT) {
        long[] jArr = new long[10];
        Field25519.sum(partialXYZT.xyz.x, xyzt.xyz.y, xyzt.xyz.x);
        Field25519.sub(partialXYZT.xyz.y, xyzt.xyz.y, xyzt.xyz.x);
        Field25519.mult(partialXYZT.xyz.y, partialXYZT.xyz.y, cachedXYT.yMinusX);
        Field25519.mult(partialXYZT.xyz.z, partialXYZT.xyz.x, cachedXYT.yPlusX);
        Field25519.mult(partialXYZT.t, xyzt.t, cachedXYT.t2d);
        cachedXYT.multByZ(partialXYZT.xyz.x, xyzt.xyz.z);
        Field25519.sum(jArr, partialXYZT.xyz.x, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.x, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.y, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.z, jArr, partialXYZT.t);
        Field25519.sub(partialXYZT.t, jArr, partialXYZT.t);
    }

    private static void sub(PartialXYZT partialXYZT, XYZT xyzt, CachedXYT cachedXYT) {
        long[] jArr = new long[10];
        Field25519.sum(partialXYZT.xyz.x, xyzt.xyz.y, xyzt.xyz.x);
        Field25519.sub(partialXYZT.xyz.y, xyzt.xyz.y, xyzt.xyz.x);
        Field25519.mult(partialXYZT.xyz.y, partialXYZT.xyz.y, cachedXYT.yPlusX);
        Field25519.mult(partialXYZT.xyz.z, partialXYZT.xyz.x, cachedXYT.yMinusX);
        Field25519.mult(partialXYZT.t, xyzt.t, cachedXYT.t2d);
        cachedXYT.multByZ(partialXYZT.xyz.x, xyzt.xyz.z);
        Field25519.sum(jArr, partialXYZT.xyz.x, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.x, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.y, partialXYZT.xyz.z, partialXYZT.xyz.y);
        Field25519.sub(partialXYZT.xyz.z, jArr, partialXYZT.t);
        Field25519.sum(partialXYZT.t, jArr, partialXYZT.t);
    }

    private static void doubleXYZ(PartialXYZT partialXYZT, XYZ xyz) {
        long[] jArr = new long[10];
        Field25519.square(partialXYZT.xyz.x, xyz.x);
        Field25519.square(partialXYZT.xyz.z, xyz.y);
        Field25519.square(partialXYZT.t, xyz.z);
        Field25519.sum(partialXYZT.t, partialXYZT.t, partialXYZT.t);
        Field25519.sum(partialXYZT.xyz.y, xyz.x, xyz.y);
        Field25519.square(jArr, partialXYZT.xyz.y);
        Field25519.sum(partialXYZT.xyz.y, partialXYZT.xyz.z, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.z, partialXYZT.xyz.z, partialXYZT.xyz.x);
        Field25519.sub(partialXYZT.xyz.x, jArr, partialXYZT.xyz.y);
        Field25519.sub(partialXYZT.t, partialXYZT.t, partialXYZT.xyz.z);
    }

    private static void doubleXYZT(PartialXYZT partialXYZT, XYZT xyzt) {
        doubleXYZ(partialXYZT, xyzt.xyz);
    }

    private static void select(CachedXYT cachedXYT, int i, byte b) {
        int i2 = (b & 255) >> 7;
        int i3 = b - (((-i2) & b) << 1);
        cachedXYT.copyConditional(Ed25519Constants.B_TABLE[i][0], eq(i3, 1));
        cachedXYT.copyConditional(Ed25519Constants.B_TABLE[i][1], eq(i3, 2));
        cachedXYT.copyConditional(Ed25519Constants.B_TABLE[i][2], eq(i3, 3));
        cachedXYT.copyConditional(Ed25519Constants.B_TABLE[i][3], eq(i3, 4));
        cachedXYT.copyConditional(Ed25519Constants.B_TABLE[i][4], eq(i3, 5));
        cachedXYT.copyConditional(Ed25519Constants.B_TABLE[i][5], eq(i3, 6));
        cachedXYT.copyConditional(Ed25519Constants.B_TABLE[i][6], eq(i3, 7));
        cachedXYT.copyConditional(Ed25519Constants.B_TABLE[i][7], eq(i3, 8));
        long[] copyOf = Arrays.copyOf(cachedXYT.yMinusX, 10);
        long[] copyOf2 = Arrays.copyOf(cachedXYT.yPlusX, 10);
        long[] copyOf3 = Arrays.copyOf(cachedXYT.t2d, 10);
        neg(copyOf3, copyOf3);
        cachedXYT.copyConditional(new CachedXYT(copyOf, copyOf2, copyOf3), i2);
    }

    private static XYZ scalarMultWithBase(byte[] bArr) {
        int i;
        CachedXYT cachedXYT;
        byte[] bArr2 = new byte[64];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            i = 1;
            if (i3 >= 32) {
                break;
            }
            int i4 = i3 * 2;
            bArr2[i4 + 0] = (byte) (((bArr[i3] & 255) >> 0) & 15);
            bArr2[i4 + 1] = (byte) (((bArr[i3] & 255) >> 4) & 15);
            i3++;
        }
        int i5 = 0;
        i3 = i5;
        while (i5 < bArr2.length - 1) {
            bArr2[i5] = (byte) (bArr2[i5] + i3);
            i3 = (bArr2[i5] + 8) >> 4;
            bArr2[i5] = (byte) (bArr2[i5] - (i3 << 4));
            i5++;
        }
        i5 = bArr2.length - 1;
        bArr2[i5] = (byte) (bArr2[i5] + i3);
        PartialXYZT partialXYZT = new PartialXYZT(NEUTRAL);
        XYZT xyzt = new XYZT();
        while (i < bArr2.length) {
            cachedXYT = new CachedXYT(CACHED_NEUTRAL);
            select(cachedXYT, i / 2, bArr2[i]);
            add(partialXYZT, XYZT.fromPartialXYZT(xyzt, partialXYZT), cachedXYT);
            i += 2;
        }
        XYZ xyz = new XYZ();
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        while (i2 < bArr2.length) {
            cachedXYT = new CachedXYT(CACHED_NEUTRAL);
            select(cachedXYT, i2 / 2, bArr2[i2]);
            add(partialXYZT, XYZT.fromPartialXYZT(xyzt, partialXYZT), cachedXYT);
            i2 += 2;
        }
        XYZ xyz2 = new XYZ(partialXYZT);
        if (xyz2.isOnCurve()) {
            return xyz2;
        }
        throw new IllegalStateException("arithmetic error in scalar multiplication");
    }

    static byte[] scalarMultWithBaseToBytes(byte[] bArr) {
        return scalarMultWithBase(bArr).toBytes();
    }

    private static byte[] slide(byte[] bArr) {
        int i;
        byte[] bArr2 = new byte[256];
        for (i = 0; i < 256; i++) {
            bArr2[i] = (byte) (1 & ((bArr[i >> 3] & 255) >> (i & 7)));
        }
        for (int i2 = 0; i2 < 256; i2++) {
            if (bArr2[i2] != (byte) 0) {
                for (i = 1; i <= 6; i++) {
                    int i3 = i2 + i;
                    if (i3 >= 256) {
                        break;
                    }
                    if (bArr2[i3] != (byte) 0) {
                        if (bArr2[i2] + (bArr2[i3] << i) > 15) {
                            if (bArr2[i2] - (bArr2[i3] << i) < -15) {
                                break;
                            }
                            bArr2[i2] = (byte) (bArr2[i2] - (bArr2[i3] << i));
                            while (i3 < 256) {
                                if (bArr2[i3] == (byte) 0) {
                                    bArr2[i3] = (byte) 1;
                                    break;
                                }
                                bArr2[i3] = (byte) 0;
                                i3++;
                            }
                        } else {
                            bArr2[i2] = (byte) (bArr2[i2] + (bArr2[i3] << i));
                            bArr2[i3] = (byte) 0;
                        }
                    }
                }
            }
        }
        return bArr2;
    }

    private static XYZ doubleScalarMultVarTime(byte[] bArr, XYZT xyzt, byte[] bArr2) {
        int i;
        CachedXYZT[] cachedXYZTArr = new CachedXYZT[8];
        cachedXYZTArr[0] = new CachedXYZT(xyzt);
        PartialXYZT partialXYZT = new PartialXYZT();
        doubleXYZT(partialXYZT, xyzt);
        xyzt = new XYZT(partialXYZT);
        for (i = 1; i < cachedXYZTArr.length; i++) {
            add(partialXYZT, xyzt, cachedXYZTArr[i - 1]);
            cachedXYZTArr[i] = new CachedXYZT(new XYZT(partialXYZT));
        }
        bArr = slide(bArr);
        byte[] slide = slide(bArr2);
        PartialXYZT partialXYZT2 = new PartialXYZT(NEUTRAL);
        XYZT xyzt2 = new XYZT();
        i = 255;
        while (i >= 0 && bArr[i] == (byte) 0 && slide[i] == (byte) 0) {
            i--;
        }
        while (i >= 0) {
            doubleXYZ(partialXYZT2, new XYZ(partialXYZT2));
            if (bArr[i] > (byte) 0) {
                add(partialXYZT2, XYZT.fromPartialXYZT(xyzt2, partialXYZT2), cachedXYZTArr[bArr[i] / 2]);
            } else if (bArr[i] < (byte) 0) {
                sub(partialXYZT2, XYZT.fromPartialXYZT(xyzt2, partialXYZT2), cachedXYZTArr[(-bArr[i]) / 2]);
            }
            if (slide[i] > (byte) 0) {
                add(partialXYZT2, XYZT.fromPartialXYZT(xyzt2, partialXYZT2), Ed25519Constants.B2[slide[i] / 2]);
            } else if (slide[i] < (byte) 0) {
                sub(partialXYZT2, XYZT.fromPartialXYZT(xyzt2, partialXYZT2), Ed25519Constants.B2[(-slide[i]) / 2]);
            }
            i--;
        }
        return new XYZ(partialXYZT2);
    }

    private static boolean isNonZeroVarTime(long[] jArr) {
        Object obj = new long[(jArr.length + 1)];
        System.arraycopy(jArr, 0, obj, 0, jArr.length);
        Field25519.reduceCoefficients(obj);
        for (byte b : Field25519.contract(obj)) {
            if (b != (byte) 0) {
                return true;
            }
        }
        return false;
    }

    private static int getLsb(long[] jArr) {
        return Field25519.contract(jArr)[0] & 1;
    }

    private static void neg(long[] jArr, long[] jArr2) {
        for (int i = 0; i < jArr2.length; i++) {
            jArr[i] = -jArr2[i];
        }
    }

    private static void pow2252m3(long[] jArr, long[] jArr2) {
        int i;
        int i2;
        long[] jArr3 = new long[10];
        long[] jArr4 = new long[10];
        long[] jArr5 = new long[10];
        Field25519.square(jArr3, jArr2);
        Field25519.square(jArr4, jArr3);
        int i3 = 1;
        for (i = 1; i < 2; i++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr4, jArr2, jArr4);
        Field25519.mult(jArr3, jArr3, jArr4);
        Field25519.square(jArr3, jArr3);
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr4, jArr3);
        for (i = 1; i < 5; i++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr4, jArr3);
        for (i = 1; i < 10; i++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr4, jArr4, jArr3);
        Field25519.square(jArr5, jArr4);
        for (i = 1; i < 20; i++) {
            Field25519.square(jArr5, jArr5);
        }
        Field25519.mult(jArr4, jArr5, jArr4);
        Field25519.square(jArr4, jArr4);
        for (i = 1; i < 10; i++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr4, jArr3);
        for (i2 = 1; i2 < 50; i2++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr4, jArr4, jArr3);
        Field25519.square(jArr5, jArr4);
        for (i2 = 1; i2 < 100; i2++) {
            Field25519.square(jArr5, jArr5);
        }
        Field25519.mult(jArr4, jArr5, jArr4);
        Field25519.square(jArr4, jArr4);
        for (i2 = 1; i2 < 50; i2++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr3, jArr3);
        while (i3 < 2) {
            Field25519.square(jArr3, jArr3);
            i3++;
        }
        Field25519.mult(jArr, jArr3, jArr2);
    }

    private static long load3(byte[] bArr, int i) {
        return (((long) (bArr[i + 2] & 255)) << 16) | ((((long) bArr[i]) & 255) | (((long) (bArr[i + 1] & 255)) << 8));
    }

    private static long load4(byte[] bArr, int i) {
        return (((long) (bArr[i + 3] & 255)) << 24) | load3(bArr, i);
    }

    private static void reduce(byte[] bArr) {
        byte[] bArr2 = bArr;
        long load3 = (load3(bArr2, 47) >> 2) & 2097151;
        long load4 = (load4(bArr2, 49) >> 7) & 2097151;
        long load42 = (load4(bArr2, 52) >> 4) & 2097151;
        long load32 = (load3(bArr2, 55) >> 1) & 2097151;
        long load43 = (load4(bArr2, 57) >> 6) & 2097151;
        long load44 = load4(bArr2, 60) >> 3;
        long load33 = (load3(bArr2, 42) & 2097151) - (load44 * 683901);
        long load45 = ((((load4(bArr2, 36) >> 6) & 2097151) - (load44 * 997805)) + (load43 * 136657)) - (load32 * 683901);
        long load46 = ((((((load4(bArr2, 31) >> 4) & 2097151) + (load44 * 470296)) + (load43 * 654183)) - (load32 * 997805)) + (load42 * 136657)) - (load4 * 683901);
        long load47 = ((load4(bArr2, 15) >> 6) & 2097151) + (load3 * 666643);
        long load34 = (((load3(bArr2, 18) >> 3) & 2097151) + (load4 * 666643)) + (load3 * 470296);
        long load35 = (((load3(bArr2, 21) & 2097151) + (load42 * 666643)) + (load4 * 470296)) + (load3 * 654183);
        long load48 = (((((load4(bArr2, 23) >> 5) & 2097151) + (load32 * 666643)) + (load42 * 470296)) + (load4 * 654183)) - (load3 * 997805);
        long load36 = ((((((load3(bArr2, 26) >> 2) & 2097151) + (load43 * 666643)) + (load32 * 470296)) + (load42 * 654183)) - (load4 * 997805)) + (load3 * 136657);
        long load49 = (((((((load4(bArr2, 28) >> 7) & 2097151) + (load44 * 666643)) + (load43 * 470296)) + (load32 * 654183)) - (load42 * 997805)) + (load4 * 136657)) - (load3 * 683901);
        load3 = (load47 + 1048576) >> 21;
        load34 += load3;
        load47 -= load3 << 21;
        load3 = (load35 + 1048576) >> 21;
        load48 += load3;
        load35 -= load3 << 21;
        load3 = (load36 + 1048576) >> 21;
        load49 += load3;
        load36 -= load3 << 21;
        load3 = (load46 + 1048576) >> 21;
        long load37 = ((((((load3(bArr2, 34) >> 1) & 2097151) + (load44 * 654183)) - (load43 * 997805)) + (load32 * 136657)) - (load42 * 683901)) + load3;
        load46 -= load3 << 21;
        load3 = (load45 + 1048576) >> 21;
        long load38 = ((((load3(bArr2, 39) >> 3) & 2097151) + (load44 * 136657)) - (load43 * 683901)) + load3;
        load45 -= load3 << 21;
        load3 = (load33 + 1048576) >> 21;
        long load410 = ((load4(bArr2, 44) >> 5) & 2097151) + load3;
        load33 -= load3 << 21;
        load3 = (load34 + 1048576) >> 21;
        load35 += load3;
        load34 -= load3 << 21;
        load3 = (load48 + 1048576) >> 21;
        load36 += load3;
        load48 -= load3 << 21;
        load3 = (load49 + 1048576) >> 21;
        load46 += load3;
        load49 -= load3 << 21;
        load3 = (load37 + 1048576) >> 21;
        load45 += load3;
        load37 -= load3 << 21;
        load3 = (load38 + 1048576) >> 21;
        load33 += load3;
        load38 -= load3 << 21;
        load36 -= load410 * 683901;
        load35 = ((load35 - (load410 * 997805)) + (load33 * 136657)) - (load38 * 683901);
        load47 = ((((load47 + (load410 * 470296)) + (load33 * 654183)) - (load38 * 997805)) + (load45 * 136657)) - (load37 * 683901);
        long load39 = (load3(bArr2, 0) & 2097151) + (load46 * 666643);
        long load411 = (((load4(bArr2, 2) >> 5) & 2097151) + (load37 * 666643)) + (load46 * 470296);
        long load310 = ((((load3(bArr2, 5) >> 2) & 2097151) + (load45 * 666643)) + (load37 * 470296)) + (load46 * 654183);
        long load412 = (((((load4(bArr2, 7) >> 7) & 2097151) + (load38 * 666643)) + (load45 * 470296)) + (load37 * 654183)) - (load46 * 997805);
        long load413 = ((((((load4(bArr2, 10) >> 4) & 2097151) + (load33 * 666643)) + (load38 * 470296)) + (load45 * 654183)) - (load37 * 997805)) + (load46 * 136657);
        long load311 = (((((((load3(bArr2, 13) >> 1) & 2097151) + (load410 * 666643)) + (load33 * 470296)) + (load38 * 654183)) - (load45 * 997805)) + (load37 * 136657)) - (load46 * 683901);
        load46 = (load39 + 1048576) >> 21;
        load411 += load46;
        load39 -= load46 << 21;
        load46 = (load310 + 1048576) >> 21;
        load412 += load46;
        load310 -= load46 << 21;
        load46 = (load413 + 1048576) >> 21;
        load311 += load46;
        load413 -= load46 << 21;
        load46 = (load47 + 1048576) >> 21;
        load34 = ((((load34 + (load410 * 654183)) - (load33 * 997805)) + (load38 * 136657)) - (load45 * 683901)) + load46;
        load47 -= load46 << 21;
        load46 = (load35 + 1048576) >> 21;
        load48 = ((load48 + (load410 * 136657)) - (load33 * 683901)) + load46;
        load35 -= load46 << 21;
        load46 = (load36 + 1048576) >> 21;
        load49 += load46;
        load36 -= load46 << 21;
        load46 = (load411 + 1048576) >> 21;
        load310 += load46;
        load411 -= load46 << 21;
        load46 = (load412 + 1048576) >> 21;
        load413 += load46;
        load412 -= load46 << 21;
        load46 = (load311 + 1048576) >> 21;
        load47 += load46;
        load311 -= load46 << 21;
        load46 = (load34 + 1048576) >> 21;
        load35 += load46;
        load34 -= load46 << 21;
        load46 = (load48 + 1048576) >> 21;
        load36 += load46;
        load48 -= load46 << 21;
        load43 = (load49 + 1048576) >> 21;
        load46 = load43 + 0;
        load39 += load46 * 666643;
        load411 += load46 * 470296;
        load310 += load46 * 654183;
        load412 -= load46 * 997805;
        load311 -= load46 * 683901;
        load46 = load39 >> 21;
        load411 += load46;
        load39 -= load46 << 21;
        load46 = load411 >> 21;
        load310 += load46;
        load411 -= load46 << 21;
        load46 = load310 >> 21;
        load412 += load46;
        load310 -= load46 << 21;
        load46 = load412 >> 21;
        load413 = (load413 + (load46 * 136657)) + load46;
        load412 -= load46 << 21;
        load46 = load413 >> 21;
        load311 += load46;
        load413 -= load46 << 21;
        load46 = load311 >> 21;
        load47 += load46;
        load311 -= load46 << 21;
        load46 = load47 >> 21;
        load34 += load46;
        load47 -= load46 << 21;
        load46 = load34 >> 21;
        load35 += load46;
        load34 -= load46 << 21;
        load46 = load35 >> 21;
        load48 += load46;
        load35 -= load46 << 21;
        load46 = load48 >> 21;
        load36 += load46;
        load48 -= load46 << 21;
        load46 = load36 >> 21;
        load49 = (load49 - (load43 << 21)) + load46;
        load36 -= load46 << 21;
        load46 = load49 >> 21;
        load37 = load46 + 0;
        load49 -= load46 << 21;
        load39 += 666643 * load37;
        load46 = load39 >> 21;
        load411 = (load411 + (470296 * load37)) + load46;
        load39 -= load46 << 21;
        load46 = load411 >> 21;
        load310 = (load310 + (654183 * load37)) + load46;
        load411 -= load46 << 21;
        load46 = load310 >> 21;
        load412 = (load412 - (997805 * load37)) + load46;
        load310 -= load46 << 21;
        load46 = load412 >> 21;
        load413 = (load413 + (136657 * load37)) + load46;
        load412 -= load46 << 21;
        load46 = load413 >> 21;
        load311 = (load311 - (load37 * 683901)) + load46;
        load413 -= load46 << 21;
        load46 = load311 >> 21;
        load47 += load46;
        load311 -= load46 << 21;
        load46 = load47 >> 21;
        load34 += load46;
        load47 -= load46 << 21;
        load46 = load34 >> 21;
        load35 += load46;
        load34 -= load46 << 21;
        load46 = load35 >> 21;
        load48 += load46;
        long j = load411;
        long j2 = load35 - (load46 << 21);
        load35 = load48 >> 21;
        load36 += load35;
        load48 -= load35 << 21;
        load35 = load36 >> 21;
        load49 += load35;
        load36 -= load35 << 21;
        bArr2[0] = (byte) ((int) load39);
        bArr2[1] = (byte) ((int) (load39 >> 8));
        bArr2[2] = (byte) ((int) ((load39 >> 16) | (j << 5)));
        bArr2[3] = (byte) ((int) (j >> 3));
        bArr2[4] = (byte) ((int) (j >> 11));
        bArr2[5] = (byte) ((int) ((j >> 19) | (load310 << 2)));
        bArr2[6] = (byte) ((int) (load310 >> 6));
        bArr2[7] = (byte) ((int) ((load310 >> 14) | (load412 << 7)));
        bArr2[8] = (byte) ((int) (load412 >> 1));
        bArr2[9] = (byte) ((int) (load412 >> 9));
        bArr2[10] = (byte) ((int) ((load412 >> 17) | (load413 << 4)));
        bArr2[11] = (byte) ((int) (load413 >> 4));
        bArr2[12] = (byte) ((int) (load413 >> 12));
        bArr2[13] = (byte) ((int) ((load413 >> 20) | (load311 << 1)));
        bArr2[14] = (byte) ((int) (load311 >> 7));
        bArr2[15] = (byte) ((int) ((load311 >> 15) | (load47 << 6)));
        bArr2[16] = (byte) ((int) (load47 >> 2));
        bArr2[17] = (byte) ((int) (load47 >> 10));
        bArr2[18] = (byte) ((int) ((load47 >> 18) | (load34 << 3)));
        bArr2[19] = (byte) ((int) (load34 >> 5));
        bArr2[20] = (byte) ((int) (load34 >> 13));
        bArr2[21] = (byte) ((int) j2);
        bArr2[22] = (byte) ((int) (j2 >> 8));
        bArr2[23] = (byte) ((int) ((j2 >> 16) | (load48 << 5)));
        bArr2[24] = (byte) ((int) (load48 >> 3));
        bArr2[25] = (byte) ((int) (load48 >> 11));
        bArr2[26] = (byte) ((int) ((load48 >> 19) | (load36 << 2)));
        bArr2[27] = (byte) ((int) (load36 >> 6));
        bArr2[28] = (byte) ((int) ((load36 >> 14) | (load49 << 7)));
        bArr2[29] = (byte) ((int) (load49 >> 1));
        bArr2[30] = (byte) ((int) (load49 >> 9));
        bArr2[31] = (byte) ((int) (load49 >> 17));
    }

    private static void mulAdd(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = bArr2;
        byte[] bArr6 = bArr3;
        byte[] bArr7 = bArr4;
        long load3 = load3(bArr5, 0) & 2097151;
        long load4 = (load4(bArr5, 2) >> 5) & 2097151;
        long load32 = (load3(bArr5, 5) >> 2) & 2097151;
        long load42 = (load4(bArr5, 7) >> 7) & 2097151;
        long load43 = (load4(bArr5, 10) >> 4) & 2097151;
        long load33 = (load3(bArr5, 13) >> 1) & 2097151;
        long load44 = (load4(bArr5, 15) >> 6) & 2097151;
        long load34 = (load3(bArr5, 18) >> 3) & 2097151;
        long load35 = load3(bArr5, 21) & 2097151;
        long load45 = (load4(bArr5, 23) >> 5) & 2097151;
        long load36 = (load3(bArr5, 26) >> 2) & 2097151;
        long load46 = load4(bArr5, 28) >> 7;
        long load37 = load3(bArr6, 0) & 2097151;
        long load47 = (load4(bArr6, 2) >> 5) & 2097151;
        long load38 = (load3(bArr6, 5) >> 2) & 2097151;
        long load48 = (load4(bArr6, 7) >> 7) & 2097151;
        long load49 = (load4(bArr6, 10) >> 4) & 2097151;
        long load39 = (load3(bArr6, 13) >> 1) & 2097151;
        long load410 = (load4(bArr6, 15) >> 6) & 2097151;
        long load310 = (load3(bArr6, 18) >> 3) & 2097151;
        long load311 = load3(bArr6, 21) & 2097151;
        long load411 = (load4(bArr6, 23) >> 5) & 2097151;
        long load312 = (load3(bArr6, 26) >> 2) & 2097151;
        long load412 = load4(bArr6, 28) >> 7;
        long load313 = (load3(bArr7, 0) & 2097151) + (load3 * load37);
        long load413 = (((load4(bArr7, 2) >> 5) & 2097151) + (load3 * load47)) + (load4 * load37);
        long load314 = ((((load3(bArr7, 5) >> 2) & 2097151) + (load3 * load38)) + (load4 * load47)) + (load32 * load37);
        long load414 = (((((load4(bArr7, 7) >> 7) & 2097151) + (load3 * load48)) + (load4 * load38)) + (load32 * load47)) + (load42 * load37);
        long load415 = ((((((load4(bArr7, 10) >> 4) & 2097151) + (load3 * load49)) + (load4 * load48)) + (load32 * load38)) + (load42 * load47)) + (load43 * load37);
        long load315 = (((((((load3(bArr7, 13) >> 1) & 2097151) + (load3 * load39)) + (load4 * load49)) + (load32 * load48)) + (load42 * load38)) + (load43 * load47)) + (load33 * load37);
        long load416 = ((((((((load4(bArr7, 15) >> 6) & 2097151) + (load3 * load410)) + (load4 * load39)) + (load32 * load49)) + (load42 * load48)) + (load43 * load38)) + (load33 * load47)) + (load44 * load37);
        long load316 = (((((((((load3(bArr7, 18) >> 3) & 2097151) + (load3 * load310)) + (load4 * load410)) + (load32 * load39)) + (load42 * load49)) + (load43 * load48)) + (load33 * load38)) + (load44 * load47)) + (load34 * load37);
        long load317 = (((((((((load3(bArr7, 21) & 2097151) + (load3 * load311)) + (load4 * load310)) + (load32 * load410)) + (load42 * load39)) + (load43 * load49)) + (load33 * load48)) + (load44 * load38)) + (load34 * load47)) + (load35 * load37);
        long load417 = (((((((((((load4(bArr7, 23) >> 5) & 2097151) + (load3 * load411)) + (load4 * load311)) + (load32 * load310)) + (load42 * load410)) + (load43 * load39)) + (load33 * load49)) + (load44 * load48)) + (load34 * load38)) + (load35 * load47)) + (load45 * load37);
        long load318 = ((((((((((((load3(bArr7, 26) >> 2) & 2097151) + (load3 * load312)) + (load4 * load411)) + (load32 * load311)) + (load42 * load310)) + (load43 * load410)) + (load33 * load39)) + (load44 * load49)) + (load34 * load48)) + (load35 * load38)) + (load45 * load47)) + (load36 * load37);
        load4 = ((((((((((load4 * load412) + (load32 * load312)) + (load42 * load411)) + (load43 * load311)) + (load33 * load310)) + (load44 * load410)) + (load34 * load39)) + (load35 * load49)) + (load45 * load48)) + (load36 * load38)) + (load47 * load46);
        load42 = ((((((((load42 * load412) + (load43 * load312)) + (load33 * load411)) + (load44 * load311)) + (load34 * load310)) + (load35 * load410)) + (load45 * load39)) + (load36 * load49)) + (load48 * load46);
        load33 = ((((((load33 * load412) + (load44 * load312)) + (load34 * load411)) + (load35 * load311)) + (load45 * load310)) + (load36 * load410)) + (load39 * load46);
        load34 = ((((load34 * load412) + (load35 * load312)) + (load45 * load411)) + (load36 * load311)) + (load310 * load46);
        load45 = ((load45 * load412) + (load36 * load312)) + (load411 * load46);
        load46 *= load412;
        long j = (load313 + 1048576) >> 21;
        load413 += j;
        load313 -= j << 21;
        j = (load314 + 1048576) >> 21;
        load414 += j;
        load314 -= j << 21;
        j = (load415 + 1048576) >> 21;
        load315 += j;
        load415 -= j << 21;
        j = (load416 + 1048576) >> 21;
        load316 += j;
        load416 -= j << 21;
        j = (load317 + 1048576) >> 21;
        load417 += j;
        load317 -= j << 21;
        j = (load318 + 1048576) >> 21;
        long load418 = (((((((((((((load4(bArr7, 28) >> 7) + (load3 * load412)) + (load4 * load312)) + (load32 * load411)) + (load42 * load311)) + (load43 * load310)) + (load33 * load410)) + (load44 * load39)) + (load34 * load49)) + (load35 * load48)) + (load45 * load38)) + (load36 * load47)) + (load37 * load46)) + j;
        load318 -= j << 21;
        j = (load4 + 1048576) >> 21;
        load32 = ((((((((((load32 * load412) + (load42 * load312)) + (load43 * load411)) + (load33 * load311)) + (load44 * load310)) + (load34 * load410)) + (load35 * load39)) + (load45 * load49)) + (load36 * load48)) + (load38 * load46)) + j;
        load4 -= j << 21;
        j = (load42 + 1048576) >> 21;
        load43 = ((((((((load43 * load412) + (load33 * load312)) + (load44 * load411)) + (load34 * load311)) + (load35 * load310)) + (load45 * load410)) + (load36 * load39)) + (load49 * load46)) + j;
        load42 -= j << 21;
        j = (load33 + 1048576) >> 21;
        load44 = ((((((load44 * load412) + (load34 * load312)) + (load35 * load411)) + (load45 * load311)) + (load36 * load310)) + (load410 * load46)) + j;
        load33 -= j << 21;
        j = (load34 + 1048576) >> 21;
        load35 = ((((load35 * load412) + (load45 * load312)) + (load36 * load411)) + (load311 * load46)) + j;
        load34 -= j << 21;
        j = (load45 + 1048576) >> 21;
        load36 = ((load36 * load412) + (load312 * load46)) + j;
        load45 -= j << 21;
        j = (load46 + 1048576) >> 21;
        load37 = j + 0;
        load46 -= j << 21;
        j = (load413 + 1048576) >> 21;
        load314 += j;
        load413 -= j << 21;
        j = (load414 + 1048576) >> 21;
        load415 += j;
        load414 -= j << 21;
        j = (load315 + 1048576) >> 21;
        load416 += j;
        load315 -= j << 21;
        j = (load316 + 1048576) >> 21;
        load317 += j;
        load316 -= j << 21;
        j = (load417 + 1048576) >> 21;
        load318 += j;
        load417 -= j << 21;
        j = (load418 + 1048576) >> 21;
        load4 += j;
        load418 -= j << 21;
        j = (load32 + 1048576) >> 21;
        load42 += j;
        load32 -= j << 21;
        j = (load43 + 1048576) >> 21;
        load33 += j;
        load43 -= j << 21;
        j = (load44 + 1048576) >> 21;
        load34 += j;
        load44 -= j << 21;
        j = (load35 + 1048576) >> 21;
        load45 += j;
        load35 -= j << 21;
        j = (load36 + 1048576) >> 21;
        load46 += j;
        load36 -= j << 21;
        load33 -= load37 * 683901;
        load42 = ((load42 - (load37 * 997805)) + (load46 * 136657)) - (load36 * 683901);
        load4 = ((((load4 + (load37 * 470296)) + (load46 * 654183)) - (load36 * 997805)) + (load45 * 136657)) - (load35 * 683901);
        load416 += load34 * 666643;
        load316 = (load316 + (load35 * 666643)) + (load34 * 470296);
        load317 = ((load317 + (load45 * 666643)) + (load35 * 470296)) + (load34 * 654183);
        load417 = (((load417 + (load36 * 666643)) + (load45 * 470296)) + (load35 * 654183)) - (load34 * 997805);
        load318 = ((((load318 + (load46 * 666643)) + (load36 * 470296)) + (load45 * 654183)) - (load35 * 997805)) + (load34 * 136657);
        load418 = (((((load418 + (load37 * 666643)) + (load46 * 470296)) + (load36 * 654183)) - (load45 * 997805)) + (load35 * 136657)) - (load34 * 683901);
        load34 = (load416 + 1048576) >> 21;
        load316 += load34;
        load416 -= load34 << 21;
        load34 = (load317 + 1048576) >> 21;
        load417 += load34;
        load317 -= load34 << 21;
        load34 = (load318 + 1048576) >> 21;
        load418 += load34;
        load318 -= load34 << 21;
        load34 = (load4 + 1048576) >> 21;
        load32 = ((((load32 + (load37 * 654183)) - (load46 * 997805)) + (load36 * 136657)) - (load45 * 683901)) + load34;
        load4 -= load34 << 21;
        load34 = (load42 + 1048576) >> 21;
        load43 = ((load43 + (load37 * 136657)) - (load46 * 683901)) + load34;
        load42 -= load34 << 21;
        load34 = (load33 + 1048576) >> 21;
        load44 += load34;
        load33 -= load34 << 21;
        load34 = (load316 + 1048576) >> 21;
        load317 += load34;
        load316 -= load34 << 21;
        load34 = (load417 + 1048576) >> 21;
        load318 += load34;
        load417 -= load34 << 21;
        load34 = (load418 + 1048576) >> 21;
        load4 += load34;
        load418 -= load34 << 21;
        load34 = (load32 + 1048576) >> 21;
        load42 += load34;
        load32 -= load34 << 21;
        load34 = (load43 + 1048576) >> 21;
        load33 += load34;
        load43 -= load34 << 21;
        load318 -= load44 * 683901;
        load317 = ((load317 - (load44 * 997805)) + (load33 * 136657)) - (load43 * 683901);
        load416 = ((((load416 + (load44 * 470296)) + (load33 * 654183)) - (load43 * 997805)) + (load42 * 136657)) - (load32 * 683901);
        load313 += load4 * 666643;
        load413 = (load413 + (load32 * 666643)) + (load4 * 470296);
        load314 = ((load314 + (load42 * 666643)) + (load32 * 470296)) + (load4 * 654183);
        load414 = (((load414 + (load43 * 666643)) + (load42 * 470296)) + (load32 * 654183)) - (load4 * 997805);
        load415 = ((((load415 + (load33 * 666643)) + (load43 * 470296)) + (load42 * 654183)) - (load32 * 997805)) + (load4 * 136657);
        load315 = (((((load315 + (load44 * 666643)) + (load33 * 470296)) + (load43 * 654183)) - (load42 * 997805)) + (load32 * 136657)) - (load4 * 683901);
        load4 = (load313 + 1048576) >> 21;
        load413 += load4;
        load313 -= load4 << 21;
        load4 = (load314 + 1048576) >> 21;
        load414 += load4;
        load314 -= load4 << 21;
        load4 = (load415 + 1048576) >> 21;
        load315 += load4;
        load415 -= load4 << 21;
        load4 = (load416 + 1048576) >> 21;
        load316 = ((((load316 + (load44 * 654183)) - (load33 * 997805)) + (load43 * 136657)) - (load42 * 683901)) + load4;
        load416 -= load4 << 21;
        load4 = (load317 + 1048576) >> 21;
        load417 = ((load417 + (load44 * 136657)) - (load33 * 683901)) + load4;
        load317 -= load4 << 21;
        load4 = (load318 + 1048576) >> 21;
        load418 += load4;
        load318 -= load4 << 21;
        load4 = (load413 + 1048576) >> 21;
        load314 += load4;
        load413 -= load4 << 21;
        load4 = (load414 + 1048576) >> 21;
        load415 += load4;
        load414 -= load4 << 21;
        load4 = (load315 + 1048576) >> 21;
        load416 += load4;
        load315 -= load4 << 21;
        load4 = (load316 + 1048576) >> 21;
        load317 += load4;
        load316 -= load4 << 21;
        load4 = (load417 + 1048576) >> 21;
        load318 += load4;
        load417 -= load4 << 21;
        load412 = (load418 + 1048576) >> 21;
        load4 = 0 + load412;
        load313 += load4 * 666643;
        load413 += load4 * 470296;
        load314 += load4 * 654183;
        load414 -= load4 * 997805;
        load315 -= load4 * 683901;
        load4 = load313 >> 21;
        load413 += load4;
        load313 -= load4 << 21;
        load4 = load413 >> 21;
        load314 += load4;
        load413 -= load4 << 21;
        load4 = load314 >> 21;
        load414 += load4;
        load314 -= load4 << 21;
        load4 = load414 >> 21;
        load415 = (load415 + (load4 * 136657)) + load4;
        load414 -= load4 << 21;
        load4 = load415 >> 21;
        load315 += load4;
        load415 -= load4 << 21;
        load4 = load315 >> 21;
        load416 += load4;
        load315 -= load4 << 21;
        load4 = load416 >> 21;
        load316 += load4;
        load416 -= load4 << 21;
        load4 = load316 >> 21;
        load317 += load4;
        load316 -= load4 << 21;
        load4 = load317 >> 21;
        load417 += load4;
        load317 -= load4 << 21;
        load4 = load417 >> 21;
        load318 += load4;
        load417 -= load4 << 21;
        load4 = load318 >> 21;
        load418 = (load418 - (load412 << 21)) + load4;
        load318 -= load4 << 21;
        load4 = load418 >> 21;
        load32 = 0 + load4;
        load418 -= load4 << 21;
        load313 += 666643 * load32;
        long j2 = load313 >> 21;
        load413 = (load413 + (470296 * load32)) + j2;
        j2 = load313 - (j2 << 21);
        load3 = load413 >> 21;
        load314 = (load314 + (654183 * load32)) + load3;
        load413 -= load3 << 21;
        load3 = load314 >> 21;
        load414 = (load414 - (997805 * load32)) + load3;
        load314 -= load3 << 21;
        load3 = load414 >> 21;
        load415 = (load415 + (136657 * load32)) + load3;
        load414 -= load3 << 21;
        load3 = load415 >> 21;
        load315 = (load315 - (load32 * 683901)) + load3;
        load415 -= load3 << 21;
        load3 = load315 >> 21;
        load416 += load3;
        load315 -= load3 << 21;
        load3 = load416 >> 21;
        load316 += load3;
        load416 -= load3 << 21;
        load3 = load316 >> 21;
        load317 += load3;
        load316 -= load3 << 21;
        load3 = load317 >> 21;
        load417 += load3;
        load3 = load317 - (load3 << 21);
        load4 = load417 >> 21;
        load318 += load4;
        load417 -= load4 << 21;
        load4 = load318 >> 21;
        load418 += load4;
        load318 -= load4 << 21;
        bArr[0] = (byte) ((int) j2);
        bArr[1] = (byte) ((int) (j2 >> 8));
        bArr[2] = (byte) ((int) ((j2 >> 16) | (load413 << 5)));
        bArr[3] = (byte) ((int) (load413 >> 3));
        bArr[4] = (byte) ((int) (load413 >> 11));
        bArr[5] = (byte) ((int) ((load413 >> 19) | (load314 << 2)));
        bArr[6] = (byte) ((int) (load314 >> 6));
        bArr[7] = (byte) ((int) ((load314 >> 14) | (load414 << 7)));
        bArr[8] = (byte) ((int) (load414 >> 1));
        bArr[9] = (byte) ((int) (load414 >> 9));
        bArr[10] = (byte) ((int) ((load414 >> 17) | (load415 << 4)));
        bArr[11] = (byte) ((int) (load415 >> 4));
        bArr[12] = (byte) ((int) (load415 >> 12));
        bArr[13] = (byte) ((int) ((load415 >> 20) | (load315 << 1)));
        bArr[14] = (byte) ((int) (load315 >> 7));
        bArr[15] = (byte) ((int) ((load315 >> 15) | (load416 << 6)));
        bArr[16] = (byte) ((int) (load416 >> 2));
        bArr[17] = (byte) ((int) (load416 >> 10));
        bArr[18] = (byte) ((int) ((load416 >> 18) | (load316 << 3)));
        bArr[19] = (byte) ((int) (load316 >> 5));
        bArr[20] = (byte) ((int) (load316 >> 13));
        bArr[21] = (byte) ((int) load3);
        bArr[22] = (byte) ((int) (load3 >> 8));
        bArr[23] = (byte) ((int) ((load3 >> 16) | (load417 << 5)));
        bArr[24] = (byte) ((int) (load417 >> 3));
        bArr[25] = (byte) ((int) (load417 >> 11));
        bArr[26] = (byte) ((int) ((load417 >> 19) | (load318 << 2)));
        bArr[27] = (byte) ((int) (load318 >> 6));
        bArr[28] = (byte) ((int) ((load318 >> 14) | (load418 << 7)));
        bArr[29] = (byte) ((int) (load418 >> 1));
        bArr[30] = (byte) ((int) (load418 >> 9));
        bArr[31] = (byte) ((int) (load418 >> 17));
    }

    static byte[] getHashedScalar(byte[] bArr) throws GeneralSecurityException {
        MessageDigest messageDigest = (MessageDigest) EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        messageDigest.update(bArr, 0, 32);
        bArr = messageDigest.digest();
        bArr[0] = (byte) (bArr[0] & 248);
        bArr[31] = (byte) (bArr[31] & 127);
        bArr[31] = (byte) (bArr[31] | 64);
        return bArr;
    }

    static byte[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        bArr = Arrays.copyOfRange(bArr, 0, bArr.length);
        MessageDigest messageDigest = (MessageDigest) EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        messageDigest.update(bArr3, 32, 32);
        messageDigest.update(bArr);
        byte[] digest = messageDigest.digest();
        reduce(digest);
        byte[] copyOfRange = Arrays.copyOfRange(scalarMultWithBase(digest).toBytes(), 0, 32);
        messageDigest.reset();
        messageDigest.update(copyOfRange);
        messageDigest.update(bArr2);
        messageDigest.update(bArr);
        bArr = messageDigest.digest();
        reduce(bArr);
        mulAdd(new byte[32], bArr, bArr3, digest);
        return Bytes.concat(copyOfRange, bArr2);
    }

    private static boolean isSmallerThanGroupOrder(byte[] bArr) {
        int i = 31;
        while (true) {
            boolean z = false;
            if (i < 0) {
                return false;
            }
            int i2 = bArr[i] & 255;
            int i3 = GROUP_ORDER[i] & 255;
            if (i2 != i3) {
                if (i2 < i3) {
                    z = true;
                }
                return z;
            }
            i--;
        }
    }

    static boolean verify(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr2.length != 64) {
            return false;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, 32, 64);
        if (!isSmallerThanGroupOrder(copyOfRange)) {
            return false;
        }
        MessageDigest messageDigest = (MessageDigest) EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        messageDigest.update(bArr2, 0, 32);
        messageDigest.update(bArr3);
        messageDigest.update(bArr);
        bArr = messageDigest.digest();
        reduce(bArr);
        bArr = doubleScalarMultVarTime(bArr, XYZT.fromBytesNegateVarTime(bArr3), copyOfRange).toBytes();
        for (int i = 0; i < 32; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }
}
