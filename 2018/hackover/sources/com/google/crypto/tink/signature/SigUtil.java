package com.google.crypto.tink.signature;

import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.subtle.EllipticCurves.CurveType;
import com.google.crypto.tink.subtle.EllipticCurves.EcdsaEncoding;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;

final class SigUtil {
    static final String INVALID_PARAMS = "Invalid ECDSA parameters";

    SigUtil() {
    }

    public static void validateEcdsaParams(EcdsaParams ecdsaParams) throws GeneralSecurityException {
        EcdsaSignatureEncoding encoding = ecdsaParams.getEncoding();
        HashType hashType = ecdsaParams.getHashType();
        EllipticCurveType curve = ecdsaParams.getCurve();
        switch (encoding) {
            case DER:
            case IEEE_P1363:
                switch (curve) {
                    case NIST_P256:
                        if (hashType != HashType.SHA256) {
                            throw new GeneralSecurityException(INVALID_PARAMS);
                        }
                        return;
                    case NIST_P384:
                    case NIST_P521:
                        if (hashType != HashType.SHA512) {
                            throw new GeneralSecurityException(INVALID_PARAMS);
                        }
                        return;
                    default:
                        throw new GeneralSecurityException(INVALID_PARAMS);
                }
            default:
                throw new GeneralSecurityException("unsupported signature encoding");
        }
    }

    public static Enums.HashType toHashType(HashType hashType) throws GeneralSecurityException {
        switch (hashType) {
            case SHA1:
                return Enums.HashType.SHA1;
            case SHA256:
                return Enums.HashType.SHA256;
            case SHA512:
                return Enums.HashType.SHA512;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("unknown hash type: ");
                stringBuilder.append(hashType);
                throw new GeneralSecurityException(stringBuilder.toString());
        }
    }

    public static CurveType toCurveType(EllipticCurveType ellipticCurveType) throws GeneralSecurityException {
        switch (ellipticCurveType) {
            case NIST_P256:
                return CurveType.NIST_P256;
            case NIST_P384:
                return CurveType.NIST_P384;
            case NIST_P521:
                return CurveType.NIST_P521;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("unknown curve type: ");
                stringBuilder.append(ellipticCurveType);
                throw new GeneralSecurityException(stringBuilder.toString());
        }
    }

    public static EcdsaEncoding toEcdsaEncoding(EcdsaSignatureEncoding ecdsaSignatureEncoding) throws GeneralSecurityException {
        switch (ecdsaSignatureEncoding) {
            case DER:
                return EcdsaEncoding.DER;
            case IEEE_P1363:
                return EcdsaEncoding.IEEE_P1363;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("unknown ECDSA encoding: ");
                stringBuilder.append(ecdsaSignatureEncoding);
                throw new GeneralSecurityException(stringBuilder.toString());
        }
    }
}
