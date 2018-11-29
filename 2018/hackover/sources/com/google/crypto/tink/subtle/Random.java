package com.google.crypto.tink.subtle;

import java.security.SecureRandom;

public final class Random {
    private static final ThreadLocal<SecureRandom> localRandom = new ThreadLocal<SecureRandom>() {
        protected SecureRandom initialValue() {
            return Random.newDefaultSecureRandom();
        }
    };

    private static SecureRandom newDefaultSecureRandom() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }

    public static byte[] randBytes(int i) {
        byte[] bArr = new byte[i];
        ((SecureRandom) localRandom.get()).nextBytes(bArr);
        return bArr;
    }

    public static final int randInt(int i) {
        return ((SecureRandom) localRandom.get()).nextInt(i);
    }
}
