package com.google.crypto.tink.subtle;

import com.google.crypto.tink.annotations.Alpha;
import java.security.InvalidKeyException;
import java.util.Arrays;

@Alpha
public final class X25519 {
    public static byte[] generatePrivateKey() {
        byte[] randBytes = Random.randBytes(32);
        randBytes[0] = (byte) (randBytes[0] | 7);
        randBytes[31] = (byte) (randBytes[31] & 63);
        randBytes[31] = (byte) (randBytes[31] | 128);
        return randBytes;
    }

    public static byte[] computeSharedSecret(byte[] bArr, byte[] bArr2) throws InvalidKeyException {
        if (bArr.length == 32) {
            long[] jArr = new long[11];
            bArr = Arrays.copyOf(bArr, 32);
            bArr[0] = (byte) (bArr[0] & 248);
            bArr[31] = (byte) (bArr[31] & 127);
            bArr[31] = (byte) (bArr[31] | 64);
            Curve25519.curveMult(jArr, bArr, bArr2);
            return Field25519.contract(jArr);
        }
        throw new InvalidKeyException("Private key must have 32 bytes.");
    }

    public static byte[] publicFromPrivate(byte[] bArr) throws InvalidKeyException {
        if (bArr.length == 32) {
            byte[] bArr2 = new byte[32];
            bArr2[0] = (byte) 9;
            return computeSharedSecret(bArr, bArr2);
        }
        throw new InvalidKeyException("Private key must have 32 bytes.");
    }
}
