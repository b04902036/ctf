package com.google.crypto.tink.integration.android;

import android.annotation.TargetApi;
import com.google.crypto.tink.Aead;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

@TargetApi(23)
public final class AndroidKeystoreAesGcm implements Aead {
    private static final int IV_SIZE_IN_BYTES = 12;
    private static final int TAG_SIZE_IN_BYTES = 16;
    private final SecretKey key;

    public AndroidKeystoreAesGcm(String str) throws GeneralSecurityException, IOException {
        KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
        instance.load(null);
        this.key = (SecretKey) instance.getKey(str, null);
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length <= 2147483619) {
            Object obj = new byte[((bArr.length + 12) + 16)];
            Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            instance.init(1, this.key);
            instance.updateAAD(bArr2);
            instance.doFinal(bArr, 0, bArr.length, obj, 12);
            System.arraycopy(instance.getIV(), 0, obj, 0, 12);
            return obj;
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length >= 28) {
            AlgorithmParameterSpec gCMParameterSpec = new GCMParameterSpec(128, bArr, 0, 12);
            Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            instance.init(2, this.key, gCMParameterSpec);
            instance.updateAAD(bArr2);
            return instance.doFinal(bArr, 12, bArr.length - 12);
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}
