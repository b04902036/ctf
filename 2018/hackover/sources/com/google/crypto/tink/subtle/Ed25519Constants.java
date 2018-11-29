package com.google.crypto.tink.subtle;

import java.lang.reflect.Array;
import java.math.BigInteger;

final class Ed25519Constants {
    static final CachedXYT[] B2 = new CachedXYT[8];
    static final CachedXYT[][] B_TABLE = ((CachedXYT[][]) Array.newInstance(CachedXYT.class, new int[]{32, 8}));
    static final long[] D = Field25519.expand(toLittleEndian(D_BI));
    static final long[] D2 = Field25519.expand(toLittleEndian(D2_BI));
    private static final BigInteger D2_BI = BigInteger.valueOf(2).multiply(D_BI).mod(P_BI);
    private static final BigInteger D_BI = BigInteger.valueOf(-121665).multiply(BigInteger.valueOf(121666).modInverse(P_BI)).mod(P_BI);
    private static final BigInteger P_BI = BigInteger.valueOf(2).pow(255).subtract(BigInteger.valueOf(19));
    static final long[] SQRTM1 = Field25519.expand(toLittleEndian(SQRTM1_BI));
    private static final BigInteger SQRTM1_BI = BigInteger.valueOf(2).modPow(P_BI.subtract(BigInteger.ONE).divide(BigInteger.valueOf(4)), P_BI);

    private static class Point {
        private BigInteger x;
        private BigInteger y;

        private Point() {
        }
    }

    Ed25519Constants() {
    }

    static {
        Point point = new Point();
        point.y = BigInteger.valueOf(4).multiply(BigInteger.valueOf(5).modInverse(P_BI)).mod(P_BI);
        point.x = recoverX(point.y);
        int i = 0;
        Point point2 = point;
        int i2 = 0;
        while (i2 < 32) {
            Point point3 = point2;
            for (int i3 = 0; i3 < 8; i3++) {
                B_TABLE[i2][i3] = getCachedXYT(point3);
                point3 = edwards(point3, point2);
            }
            Point point4 = point2;
            for (int i4 = 0; i4 < 8; i4++) {
                point4 = edwards(point4, point4);
            }
            i2++;
            point2 = point4;
        }
        Point edwards = edwards(point, point);
        while (i < 8) {
            B2[i] = getCachedXYT(point);
            point = edwards(point, edwards);
            i++;
        }
    }

    private static BigInteger recoverX(BigInteger bigInteger) {
        bigInteger = bigInteger.pow(2).subtract(BigInteger.ONE).multiply(D_BI.multiply(bigInteger.pow(2)).add(BigInteger.ONE).modInverse(P_BI));
        BigInteger modPow = bigInteger.modPow(P_BI.add(BigInteger.valueOf(3)).divide(BigInteger.valueOf(8)), P_BI);
        if (!modPow.pow(2).subtract(bigInteger).mod(P_BI).equals(BigInteger.ZERO)) {
            modPow = modPow.multiply(SQRTM1_BI).mod(P_BI);
        }
        return modPow.testBit(0) ? P_BI.subtract(modPow) : modPow;
    }

    private static Point edwards(Point point, Point point2) {
        Point point3 = new Point();
        BigInteger mod = D_BI.multiply(point.x.multiply(point2.x).multiply(point.y).multiply(point2.y)).mod(P_BI);
        point3.x = point.x.multiply(point2.y).add(point2.x.multiply(point.y)).multiply(BigInteger.ONE.add(mod).modInverse(P_BI)).mod(P_BI);
        point3.y = point.y.multiply(point2.y).add(point.x.multiply(point2.x)).multiply(BigInteger.ONE.subtract(mod).modInverse(P_BI)).mod(P_BI);
        return point3;
    }

    private static byte[] toLittleEndian(BigInteger bigInteger) {
        Object obj = new byte[32];
        Object toByteArray = bigInteger.toByteArray();
        int i = 0;
        System.arraycopy(toByteArray, 0, obj, 32 - toByteArray.length, toByteArray.length);
        while (i < obj.length / 2) {
            byte b = obj[i];
            obj[i] = obj[(obj.length - i) - 1];
            obj[(obj.length - i) - 1] = b;
            i++;
        }
        return obj;
    }

    private static CachedXYT getCachedXYT(Point point) {
        return new CachedXYT(Field25519.expand(toLittleEndian(point.y.add(point.x).mod(P_BI))), Field25519.expand(toLittleEndian(point.y.subtract(point.x).mod(P_BI))), Field25519.expand(toLittleEndian(D2_BI.multiply(point.x).multiply(point.y).mod(P_BI))));
    }
}
