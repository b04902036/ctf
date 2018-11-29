package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public final class Ed25519Sign implements PublicKeySign {
    public static final int SECRET_KEY_LEN = 32;
    private final byte[] hashedPrivateKey;
    private final byte[] publicKey;

    public static final class KeyPair {
        private final byte[] privateKey;
        private final byte[] publicKey;

        private KeyPair(byte[] bArr, byte[] bArr2) {
            this.publicKey = bArr;
            this.privateKey = bArr2;
        }

        public byte[] getPublicKey() {
            return Arrays.copyOf(this.publicKey, this.publicKey.length);
        }

        public byte[] getPrivateKey() {
            return Arrays.copyOf(this.privateKey, this.privateKey.length);
        }

        public static KeyPair newKeyPair() throws GeneralSecurityException {
            byte[] randBytes = Random.randBytes(32);
            return new KeyPair(Ed25519.scalarMultWithBaseToBytes(Ed25519.getHashedScalar(randBytes)), randBytes);
        }
    }

    public Ed25519Sign(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length == 32) {
            this.hashedPrivateKey = Ed25519.getHashedScalar(bArr);
            this.publicKey = Ed25519.scalarMultWithBaseToBytes(this.hashedPrivateKey);
            return;
        }
        throw new IllegalArgumentException(String.format("Given private key's length is not %s", new Object[]{Integer.valueOf(32)}));
    }

    public byte[] sign(byte[] bArr) throws GeneralSecurityException {
        return Ed25519.sign(bArr, this.publicKey, this.hashedPrivateKey);
    }
}
