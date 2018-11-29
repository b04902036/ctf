package com.google.crypto.tink.subtle;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECField;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.KeyAgreement;

public final class EllipticCurves {

    public enum CurveType {
        NIST_P256,
        NIST_P384,
        NIST_P521
    }

    public enum EcdsaEncoding {
        IEEE_P1363,
        DER
    }

    public enum PointFormatType {
        UNCOMPRESSED,
        COMPRESSED,
        DO_NOT_USE_CRUNCHY_UNCOMPRESSED
    }

    public static ECParameterSpec getNistP256Params() {
        return getNistCurveSpec("115792089210356248762697446949407573530086143415290314195533631308867097853951", "115792089210356248762697446949407573529996955224135760342422259061068512044369", "5ac635d8aa3a93e7b3ebbd55769886bc651d06b0cc53b0f63bce3c3e27d2604b", "6b17d1f2e12c4247f8bce6e563a440f277037d812deb33a0f4a13945d898c296", "4fe342e2fe1a7f9b8ee7eb4a7c0f9e162bce33576b315ececbb6406837bf51f5");
    }

    public static ECParameterSpec getNistP384Params() {
        return getNistCurveSpec("39402006196394479212279040100143613805079739270465446667948293404245721771496870329047266088258938001861606973112319", "39402006196394479212279040100143613805079739270465446667946905279627659399113263569398956308152294913554433653942643", "b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef", "aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", "3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f");
    }

    public static ECParameterSpec getNistP521Params() {
        return getNistCurveSpec("6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151", "6864797660130609714981900799081393217269435300143305409394463459185543183397655394245057746333217197532963996371363321113864768612440380340372808892707005449", "051953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00", "c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", "11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650");
    }

    static void checkPointOnCurve(ECPoint eCPoint, EllipticCurve ellipticCurve) throws GeneralSecurityException {
        BigInteger modulus = getModulus(ellipticCurve);
        BigInteger affineX = eCPoint.getAffineX();
        BigInteger affineY = eCPoint.getAffineY();
        if (affineX == null || affineY == null) {
            throw new GeneralSecurityException("point is at infinity");
        } else if (affineX.signum() == -1 || affineX.compareTo(modulus) != -1) {
            throw new GeneralSecurityException("x is out of range");
        } else if (affineY.signum() == -1 || affineY.compareTo(modulus) != -1) {
            throw new GeneralSecurityException("y is out of range");
        } else if (!affineY.multiply(affineY).mod(modulus).equals(affineX.multiply(affineX).add(ellipticCurve.getA()).multiply(affineX).add(ellipticCurve.getB()).mod(modulus))) {
            throw new GeneralSecurityException("Point is not on curve");
        }
    }

    static void checkPublicKey(ECPublicKey eCPublicKey) throws GeneralSecurityException {
        checkPointOnCurve(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
    }

    public static void validatePublicKey(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        validatePublicKeySpec(eCPublicKey, eCPrivateKey);
        checkPointOnCurve(eCPublicKey.getW(), eCPrivateKey.getParams().getCurve());
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.IllegalArgumentException (r2_4 'e' java.lang.RuntimeException)} */
    /* JADX WARNING: Missing block: B:12:0x0045, code:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:14:0x004f, code:
            throw new java.security.GeneralSecurityException(r2.toString());
     */
    static void validatePublicKeySpec(java.security.interfaces.ECPublicKey r2, java.security.interfaces.ECPrivateKey r3) throws java.security.GeneralSecurityException {
        /*
        r2 = r2.getParams();	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        r3 = r3.getParams();	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        r0 = r2.getCurve();	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        r1 = r3.getCurve();	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        r0 = r0.equals(r1);	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        if (r0 == 0) goto L_0x003d;
    L_0x0016:
        r0 = r2.getGenerator();	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        r1 = r3.getGenerator();	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        r0 = r0.equals(r1);	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        if (r0 == 0) goto L_0x003d;
    L_0x0024:
        r0 = r2.getOrder();	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        r1 = r3.getOrder();	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        r0 = r0.equals(r1);	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        if (r0 == 0) goto L_0x003d;
    L_0x0032:
        r2 = r2.getCofactor();	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        r3 = r3.getCofactor();	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        if (r2 != r3) goto L_0x003d;
    L_0x003c:
        return;
    L_0x003d:
        r2 = new java.security.GeneralSecurityException;	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        r3 = "invalid public key spec";
        r2.<init>(r3);	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
        throw r2;	 Catch:{ IllegalArgumentException -> 0x0045, IllegalArgumentException -> 0x0045 }
    L_0x0045:
        r2 = move-exception;
        r3 = new java.security.GeneralSecurityException;
        r2 = r2.toString();
        r3.<init>(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.EllipticCurves.validatePublicKeySpec(java.security.interfaces.ECPublicKey, java.security.interfaces.ECPrivateKey):void");
    }

    public static BigInteger getModulus(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        ECField field = ellipticCurve.getField();
        if (field instanceof ECFieldFp) {
            return ((ECFieldFp) field).getP();
        }
        throw new GeneralSecurityException("Only curves over prime order fields are supported");
    }

    private static int fieldSizeInBits(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        return getModulus(ellipticCurve).subtract(BigInteger.ONE).bitLength();
    }

    public static int fieldSizeInBytes(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        return (fieldSizeInBits(ellipticCurve) + 7) / 8;
    }

    private static ECParameterSpec getNistCurveSpec(String str, String str2, String str3, String str4, String str5) {
        BigInteger bigInteger = new BigInteger(str);
        return new ECParameterSpec(new EllipticCurve(new ECFieldFp(bigInteger), bigInteger.subtract(new BigInteger("3")), new BigInteger(str3, 16)), new ECPoint(new BigInteger(str4, 16), new BigInteger(str5, 16)), new BigInteger(str2), 1);
    }

    protected static BigInteger modSqrt(BigInteger bigInteger, BigInteger bigInteger2) throws GeneralSecurityException {
        if (bigInteger2.signum() == 1) {
            bigInteger = bigInteger.mod(bigInteger2);
            BigInteger bigInteger3 = null;
            if (bigInteger.equals(BigInteger.ZERO)) {
                return BigInteger.ZERO;
            }
            int i = 0;
            if (bigInteger2.testBit(0) && bigInteger2.testBit(1)) {
                bigInteger3 = bigInteger.modPow(bigInteger2.add(BigInteger.ONE).shiftRight(2), bigInteger2);
            } else if (bigInteger2.testBit(0) && !bigInteger2.testBit(1)) {
                bigInteger3 = BigInteger.ONE;
                BigInteger shiftRight = bigInteger2.subtract(BigInteger.ONE).shiftRight(1);
                while (true) {
                    BigInteger mod = bigInteger3.multiply(bigInteger3).subtract(bigInteger).mod(bigInteger2);
                    if (mod.equals(BigInteger.ZERO)) {
                        return bigInteger3;
                    }
                    BigInteger modPow = mod.modPow(shiftRight, bigInteger2);
                    if (modPow.add(BigInteger.ONE).equals(bigInteger2)) {
                        BigInteger shiftRight2 = bigInteger2.add(BigInteger.ONE).shiftRight(1);
                        BigInteger bigInteger4 = BigInteger.ONE;
                        BigInteger bigInteger5 = bigInteger3;
                        for (int bitLength = shiftRight2.bitLength() - 2; bitLength >= 0; bitLength--) {
                            modPow = bigInteger5.multiply(bigInteger4);
                            bigInteger5 = bigInteger5.multiply(bigInteger5).add(bigInteger4.multiply(bigInteger4).mod(bigInteger2).multiply(mod)).mod(bigInteger2);
                            bigInteger4 = modPow.add(modPow).mod(bigInteger2);
                            if (shiftRight2.testBit(bitLength)) {
                                modPow = bigInteger5.multiply(bigInteger3).add(bigInteger4.multiply(mod)).mod(bigInteger2);
                                bigInteger4 = bigInteger3.multiply(bigInteger4).add(bigInteger5).mod(bigInteger2);
                                bigInteger5 = modPow;
                            }
                        }
                        bigInteger3 = bigInteger5;
                    } else if (modPow.equals(BigInteger.ONE)) {
                        bigInteger3 = bigInteger3.add(BigInteger.ONE);
                        i++;
                        if (i == 128) {
                            if (!bigInteger2.isProbablePrime(80)) {
                                throw new InvalidAlgorithmParameterException("p is not prime");
                            }
                        }
                    } else {
                        throw new InvalidAlgorithmParameterException("p is not prime");
                    }
                }
            }
            if (bigInteger3 == null || bigInteger3.multiply(bigInteger3).mod(bigInteger2).compareTo(bigInteger) == 0) {
                return bigInteger3;
            }
            throw new GeneralSecurityException("Could not find a modular square root");
        }
        throw new InvalidAlgorithmParameterException("p must be positive");
    }

    public static BigInteger getY(BigInteger bigInteger, boolean z, EllipticCurve ellipticCurve) throws GeneralSecurityException {
        BigInteger modulus = getModulus(ellipticCurve);
        bigInteger = modSqrt(bigInteger.multiply(bigInteger).add(ellipticCurve.getA()).multiply(bigInteger).add(ellipticCurve.getB()).mod(modulus), modulus);
        return z != bigInteger.testBit(0) ? modulus.subtract(bigInteger).mod(modulus) : bigInteger;
    }

    private static byte[] toMinimalSignedNumber(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length && bArr[i2] == (byte) 0) {
            i2++;
        }
        if (i2 == bArr.length) {
            i2 = bArr.length - 1;
        }
        if ((bArr[i2] & 128) == 128) {
            i = 1;
        }
        Object obj = new byte[((bArr.length - i2) + i)];
        System.arraycopy(bArr, i2, obj, i, bArr.length - i2);
        return obj;
    }

    public static byte[] ecdsaIeee2Der(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length % 2 != 0 || bArr.length == 0 || bArr.length > 132) {
            throw new GeneralSecurityException("Invalid IEEE_P1363 encoding");
        }
        byte[] bArr2;
        Object toMinimalSignedNumber = toMinimalSignedNumber(Arrays.copyOf(bArr, bArr.length / 2));
        Object toMinimalSignedNumber2 = toMinimalSignedNumber(Arrays.copyOfRange(bArr, bArr.length / 2, bArr.length));
        int length = (((toMinimalSignedNumber.length + 2) + 1) + 1) + toMinimalSignedNumber2.length;
        if (length >= 128) {
            bArr2 = new byte[(length + 3)];
            bArr2[0] = (byte) 48;
            bArr2[1] = (byte) -127;
            bArr2[2] = (byte) length;
            length = 3;
        } else {
            bArr2 = new byte[(length + 2)];
            bArr2[0] = (byte) 48;
            bArr2[1] = (byte) length;
            length = 2;
        }
        int i = length + 1;
        bArr2[length] = (byte) 2;
        length = i + 1;
        bArr2[i] = (byte) toMinimalSignedNumber.length;
        System.arraycopy(toMinimalSignedNumber, 0, bArr2, length, toMinimalSignedNumber.length);
        length += toMinimalSignedNumber.length;
        int i2 = length + 1;
        bArr2[length] = (byte) 2;
        int i3 = i2 + 1;
        bArr2[i2] = (byte) toMinimalSignedNumber2.length;
        System.arraycopy(toMinimalSignedNumber2, 0, bArr2, i3, toMinimalSignedNumber2.length);
        return bArr2;
    }

    public static byte[] ecdsaDer2Ieee(byte[] bArr, int i) throws GeneralSecurityException {
        if (isValidDerEncoding(bArr)) {
            Object obj = new byte[i];
            int i2 = 1;
            int i3 = ((bArr[1] & 255) >= 128 ? 3 : 2) + 1;
            int i4 = i3 + 1;
            byte b = bArr[i3];
            int i5 = bArr[i4] == (byte) 0 ? 1 : 0;
            System.arraycopy(bArr, i4 + i5, obj, ((i / 2) - b) + i5, b - i5);
            i4 += b + 1;
            i3 = i4 + 1;
            byte b2 = bArr[i4];
            if (bArr[i3] != (byte) 0) {
                i2 = 0;
            }
            System.arraycopy(bArr, i3 + i2, obj, (i - b2) + i2, b2 - i2);
            return obj;
        }
        throw new GeneralSecurityException("Invalid DER encoding");
    }

    public static boolean isValidDerEncoding(byte[] bArr) {
        if (bArr.length < 8 || bArr[0] != (byte) 48) {
            return false;
        }
        int i;
        int i2 = bArr[1] & 255;
        if (i2 == 129) {
            i2 = bArr[2] & 255;
            if (i2 < 128) {
                return false;
            }
            i = 2;
        } else if (i2 == 128 || i2 > 129) {
            return false;
        } else {
            i = 1;
        }
        if (i2 != (bArr.length - 1) - i) {
            return false;
        }
        i2 = i + 1;
        if (bArr[i2] != (byte) 2) {
            return false;
        }
        i2++;
        int i3 = bArr[i2] & 255;
        i2 = ((i2 + 1) + i3) + 1;
        if (i2 >= bArr.length || i3 == 0) {
            return false;
        }
        int i4 = i + 3;
        if ((bArr[i4] & 255) >= 128) {
            return false;
        }
        if ((i3 > 1 && bArr[i4] == (byte) 0 && (bArr[i + 4] & 255) < 128) || bArr[i4 + i3] != (byte) 2) {
            return false;
        }
        int i5 = bArr[i2] & 255;
        if ((i2 + 1) + i5 != bArr.length || i5 == 0) {
            return false;
        }
        i2 = (i + 5) + i3;
        if ((bArr[i2] & 255) >= 128) {
            return false;
        }
        return i5 <= 1 || bArr[i2] != (byte) 0 || (bArr[(i + 6) + i3] & 255) >= 128;
    }

    public static int encodingSizeInBytes(EllipticCurve ellipticCurve, PointFormatType pointFormatType) throws GeneralSecurityException {
        int fieldSizeInBytes = fieldSizeInBytes(ellipticCurve);
        switch (pointFormatType) {
            case UNCOMPRESSED:
                return (fieldSizeInBytes * 2) + 1;
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                return fieldSizeInBytes * 2;
            case COMPRESSED:
                return fieldSizeInBytes + 1;
            default:
                throw new GeneralSecurityException("unknown EC point format");
        }
    }

    @Deprecated
    public static ECPoint ecPointDecode(EllipticCurve ellipticCurve, PointFormatType pointFormatType, byte[] bArr) throws GeneralSecurityException {
        return pointDecode(ellipticCurve, pointFormatType, bArr);
    }

    public static ECPoint pointDecode(CurveType curveType, PointFormatType pointFormatType, byte[] bArr) throws GeneralSecurityException {
        return pointDecode(getCurveSpec(curveType).getCurve(), pointFormatType, bArr);
    }

    public static ECPoint pointDecode(EllipticCurve ellipticCurve, PointFormatType pointFormatType, byte[] bArr) throws GeneralSecurityException {
        int fieldSizeInBytes = fieldSizeInBytes(ellipticCurve);
        boolean z = false;
        ECPoint eCPoint;
        switch (pointFormatType) {
            case UNCOMPRESSED:
                if (bArr.length != (fieldSizeInBytes * 2) + 1) {
                    throw new GeneralSecurityException("invalid point size");
                } else if (bArr[0] == (byte) 4) {
                    fieldSizeInBytes++;
                    eCPoint = new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 1, fieldSizeInBytes)), new BigInteger(1, Arrays.copyOfRange(bArr, fieldSizeInBytes, bArr.length)));
                    checkPointOnCurve(eCPoint, ellipticCurve);
                    return eCPoint;
                } else {
                    throw new GeneralSecurityException("invalid point format");
                }
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                if (bArr.length == fieldSizeInBytes * 2) {
                    eCPoint = new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 0, fieldSizeInBytes)), new BigInteger(1, Arrays.copyOfRange(bArr, fieldSizeInBytes, bArr.length)));
                    checkPointOnCurve(eCPoint, ellipticCurve);
                    return eCPoint;
                }
                throw new GeneralSecurityException("invalid point size");
            case COMPRESSED:
                BigInteger modulus = getModulus(ellipticCurve);
                if (bArr.length == fieldSizeInBytes + 1) {
                    if (bArr[0] != (byte) 2) {
                        if (bArr[0] == (byte) 3) {
                            z = true;
                        } else {
                            throw new GeneralSecurityException("invalid format");
                        }
                    }
                    BigInteger bigInteger = new BigInteger(1, Arrays.copyOfRange(bArr, 1, bArr.length));
                    if (bigInteger.signum() != -1 && bigInteger.compareTo(modulus) == -1) {
                        return new ECPoint(bigInteger, getY(bigInteger, z, ellipticCurve));
                    }
                    throw new GeneralSecurityException("x is out of range");
                }
                throw new GeneralSecurityException("compressed point has wrong length");
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("invalid format:");
                stringBuilder.append(pointFormatType);
                throw new GeneralSecurityException(stringBuilder.toString());
        }
    }

    public static byte[] pointEncode(CurveType curveType, PointFormatType pointFormatType, ECPoint eCPoint) throws GeneralSecurityException {
        return pointEncode(getCurveSpec(curveType).getCurve(), pointFormatType, eCPoint);
    }

    public static byte[] pointEncode(EllipticCurve ellipticCurve, PointFormatType pointFormatType, ECPoint eCPoint) throws GeneralSecurityException {
        checkPointOnCurve(eCPoint, ellipticCurve);
        int fieldSizeInBytes = fieldSizeInBytes(ellipticCurve);
        int i;
        Object obj;
        Object toByteArray;
        Object toByteArray2;
        switch (pointFormatType) {
            case UNCOMPRESSED:
                i = (fieldSizeInBytes * 2) + 1;
                obj = new byte[i];
                toByteArray = eCPoint.getAffineX().toByteArray();
                toByteArray2 = eCPoint.getAffineY().toByteArray();
                System.arraycopy(toByteArray2, 0, obj, i - toByteArray2.length, toByteArray2.length);
                System.arraycopy(toByteArray, 0, obj, (fieldSizeInBytes + 1) - toByteArray.length, toByteArray.length);
                obj[0] = (byte) 4;
                return obj;
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                i = fieldSizeInBytes * 2;
                obj = new byte[i];
                toByteArray = eCPoint.getAffineX().toByteArray();
                if (toByteArray.length > fieldSizeInBytes) {
                    toByteArray = Arrays.copyOfRange(toByteArray, toByteArray.length - fieldSizeInBytes, toByteArray.length);
                }
                toByteArray2 = eCPoint.getAffineY().toByteArray();
                if (toByteArray2.length > fieldSizeInBytes) {
                    toByteArray2 = Arrays.copyOfRange(toByteArray2, toByteArray2.length - fieldSizeInBytes, toByteArray2.length);
                }
                System.arraycopy(toByteArray2, 0, obj, i - toByteArray2.length, toByteArray2.length);
                System.arraycopy(toByteArray, 0, obj, fieldSizeInBytes - toByteArray.length, toByteArray.length);
                return obj;
            case COMPRESSED:
                fieldSizeInBytes++;
                Object obj2 = new byte[fieldSizeInBytes];
                obj = eCPoint.getAffineX().toByteArray();
                System.arraycopy(obj, 0, obj2, fieldSizeInBytes - obj.length, obj.length);
                obj2[0] = (byte) (eCPoint.getAffineY().testBit(0) ? 3 : 2);
                return obj2;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("invalid format:");
                stringBuilder.append(pointFormatType);
                throw new GeneralSecurityException(stringBuilder.toString());
        }
    }

    public static ECParameterSpec getCurveSpec(CurveType curveType) throws NoSuchAlgorithmException {
        switch (curveType) {
            case NIST_P256:
                return getNistP256Params();
            case NIST_P384:
                return getNistP384Params();
            case NIST_P521:
                return getNistP521Params();
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("curve not implemented:");
                stringBuilder.append(curveType);
                throw new NoSuchAlgorithmException(stringBuilder.toString());
        }
    }

    public static ECPublicKey getEcPublicKey(byte[] bArr) throws GeneralSecurityException {
        return (ECPublicKey) ((KeyFactory) EngineFactory.KEY_FACTORY.getInstance("EC")).generatePublic(new X509EncodedKeySpec(bArr));
    }

    public static ECPublicKey getEcPublicKey(CurveType curveType, PointFormatType pointFormatType, byte[] bArr) throws GeneralSecurityException {
        return getEcPublicKey(getCurveSpec(curveType), pointFormatType, bArr);
    }

    public static ECPublicKey getEcPublicKey(ECParameterSpec eCParameterSpec, PointFormatType pointFormatType, byte[] bArr) throws GeneralSecurityException {
        return (ECPublicKey) ((KeyFactory) EngineFactory.KEY_FACTORY.getInstance("EC")).generatePublic(new ECPublicKeySpec(pointDecode(eCParameterSpec.getCurve(), pointFormatType, bArr), eCParameterSpec));
    }

    public static ECPublicKey getEcPublicKey(CurveType curveType, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        ECParameterSpec curveSpec = getCurveSpec(curveType);
        ECPoint eCPoint = new ECPoint(new BigInteger(1, bArr), new BigInteger(1, bArr2));
        checkPointOnCurve(eCPoint, curveSpec.getCurve());
        return (ECPublicKey) ((KeyFactory) EngineFactory.KEY_FACTORY.getInstance("EC")).generatePublic(new ECPublicKeySpec(eCPoint, curveSpec));
    }

    public static ECPrivateKey getEcPrivateKey(byte[] bArr) throws GeneralSecurityException {
        return (ECPrivateKey) ((KeyFactory) EngineFactory.KEY_FACTORY.getInstance("EC")).generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }

    public static ECPrivateKey getEcPrivateKey(CurveType curveType, byte[] bArr) throws GeneralSecurityException {
        return (ECPrivateKey) ((KeyFactory) EngineFactory.KEY_FACTORY.getInstance("EC")).generatePrivate(new ECPrivateKeySpec(new BigInteger(1, bArr), getCurveSpec(curveType)));
    }

    public static KeyPair generateKeyPair(CurveType curveType) throws GeneralSecurityException {
        return generateKeyPair(getCurveSpec(curveType));
    }

    public static KeyPair generateKeyPair(ECParameterSpec eCParameterSpec) throws GeneralSecurityException {
        KeyPairGenerator keyPairGenerator = (KeyPairGenerator) EngineFactory.KEY_PAIR_GENERATOR.getInstance("EC");
        keyPairGenerator.initialize(eCParameterSpec);
        return keyPairGenerator.generateKeyPair();
    }

    private static void validateSharedSecret(byte[] bArr, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        EllipticCurve curve = eCPrivateKey.getParams().getCurve();
        BigInteger bigInteger = new BigInteger(1, bArr);
        if (bigInteger.signum() == -1 || bigInteger.compareTo(getModulus(curve)) != -1) {
            throw new GeneralSecurityException("shared secret is out of range");
        }
        getY(bigInteger, true, curve);
    }

    public static byte[] computeSharedSecret(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey) throws GeneralSecurityException {
        validatePublicKeySpec(eCPublicKey, eCPrivateKey);
        return computeSharedSecret(eCPrivateKey, eCPublicKey.getW());
    }

    public static byte[] computeSharedSecret(ECPrivateKey eCPrivateKey, ECPoint eCPoint) throws GeneralSecurityException {
        checkPointOnCurve(eCPoint, eCPrivateKey.getParams().getCurve());
        ECParameterSpec params = eCPrivateKey.getParams();
        params.getCurve();
        Key generatePublic = KeyFactory.getInstance("EC").generatePublic(new ECPublicKeySpec(eCPoint, params));
        KeyAgreement keyAgreement = (KeyAgreement) EngineFactory.KEY_AGREEMENT.getInstance("ECDH");
        keyAgreement.init(eCPrivateKey);
        try {
            keyAgreement.doPhase(generatePublic, true);
            byte[] generateSecret = keyAgreement.generateSecret();
            validateSharedSecret(generateSecret, eCPrivateKey);
            return generateSecret;
        } catch (IllegalStateException e) {
            throw new GeneralSecurityException(e.toString());
        }
    }
}
