package com.google.crypto.tink.subtle;

import java.security.InvalidKeyException;

public final class ChaCha20Poly1305 extends SnufflePoly1305 {
    public ChaCha20Poly1305(byte[] bArr) throws InvalidKeyException {
        super(bArr);
    }

    Snuffle createSnuffleInstance(byte[] bArr, int i) throws InvalidKeyException {
        return new ChaCha20(bArr, i);
    }
}
