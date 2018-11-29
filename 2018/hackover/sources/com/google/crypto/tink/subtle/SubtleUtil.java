package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.Enums.HashType;
import java.security.GeneralSecurityException;

public class SubtleUtil {
    public static String toEcdsaAlgo(HashType hashType) throws GeneralSecurityException {
        Validators.validateSignatureHash(hashType);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(hashType);
        stringBuilder.append("withECDSA");
        return stringBuilder.toString();
    }

    public static String toRsaSsaPkcs1Algo(HashType hashType) throws GeneralSecurityException {
        Validators.validateSignatureHash(hashType);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(hashType);
        stringBuilder.append("withRSA");
        return stringBuilder.toString();
    }

    public static boolean isAndroid() {
        try {
            Class.forName("android.app.Application", false, null);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
