package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.EllipticCurves.CurveType;
import com.google.crypto.tink.subtle.EllipticCurves.PointFormatType;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

class HybridUtil {
    HybridUtil() {
    }

    public static void validate(EciesAeadHkdfParams eciesAeadHkdfParams) throws GeneralSecurityException {
        EllipticCurves.getCurveSpec(toCurveType(eciesAeadHkdfParams.getKemParams().getCurveType()));
        toHmacAlgo(eciesAeadHkdfParams.getKemParams().getHkdfHashType());
        if (eciesAeadHkdfParams.getEcPointFormat() != EcPointFormat.UNKNOWN_FORMAT) {
            Registry.newKeyData(eciesAeadHkdfParams.getDemParams().getAeadDem());
            return;
        }
        throw new GeneralSecurityException("unknown EC point format");
    }

    public static String toHmacAlgo(HashType hashType) throws NoSuchAlgorithmException {
        switch (hashType) {
            case SHA1:
                return "HmacSha1";
            case SHA256:
                return "HmacSha256";
            case SHA512:
                return "HmacSha512";
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("hash unsupported for HMAC: ");
                stringBuilder.append(hashType);
                throw new NoSuchAlgorithmException(stringBuilder.toString());
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

    public static PointFormatType toPointFormatType(EcPointFormat ecPointFormat) throws GeneralSecurityException {
        switch (ecPointFormat) {
            case UNCOMPRESSED:
                return PointFormatType.UNCOMPRESSED;
            case DO_NOT_USE_CRUNCHY_UNCOMPRESSED:
                return PointFormatType.DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
            case COMPRESSED:
                return PointFormatType.COMPRESSED;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("unknown point format: ");
                stringBuilder.append(ecPointFormat);
                throw new GeneralSecurityException(stringBuilder.toString());
        }
    }
}
