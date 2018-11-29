package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesCtrJceCipher implements IndCpaCipher {
    private static final String CIPHER_ALGORITHM = "AES/CTR/NoPadding";
    private static final String KEY_ALGORITHM = "AES";
    private static final int MIN_IV_SIZE_IN_BYTES = 12;
    private final int blockSize = ((Cipher) EngineFactory.CIPHER.getInstance(CIPHER_ALGORITHM)).getBlockSize();
    private final int ivSize;
    private final SecretKeySpec keySpec;

    public AesCtrJceCipher(byte[] bArr, int i) throws GeneralSecurityException {
        Validators.validateAesKeySize(bArr.length);
        this.keySpec = new SecretKeySpec(bArr, KEY_ALGORITHM);
        if (i < 12 || i > this.blockSize) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.ivSize = i;
    }

    public byte[] encrypt(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length <= Integer.MAX_VALUE - this.ivSize) {
            Object obj = new byte[(this.ivSize + bArr.length)];
            Object randBytes = Random.randBytes(this.ivSize);
            System.arraycopy(randBytes, 0, obj, 0, this.ivSize);
            doCtr(bArr, 0, bArr.length, obj, this.ivSize, randBytes, true);
            return obj;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("plaintext length can not exceed ");
        stringBuilder.append(Integer.MAX_VALUE - this.ivSize);
        throw new GeneralSecurityException(stringBuilder.toString());
    }

    public byte[] decrypt(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length >= this.ivSize) {
            Object obj = new byte[this.ivSize];
            System.arraycopy(bArr, 0, obj, 0, this.ivSize);
            byte[] bArr2 = new byte[(bArr.length - this.ivSize)];
            doCtr(bArr, this.ivSize, bArr.length - this.ivSize, bArr2, 0, obj, false);
            return bArr2;
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    private void doCtr(byte[] bArr, int i, int i2, byte[] bArr2, int i3, byte[] bArr3, boolean z) throws GeneralSecurityException {
        Cipher cipher = (Cipher) EngineFactory.CIPHER.getInstance(CIPHER_ALGORITHM);
        Object obj = new byte[this.blockSize];
        System.arraycopy(bArr3, 0, obj, 0, this.ivSize);
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(obj);
        if (z) {
            cipher.init(1, this.keySpec, ivParameterSpec);
        } else {
            cipher.init(2, this.keySpec, ivParameterSpec);
        }
        if (cipher.doFinal(bArr, i, i2, bArr2, i3) != i2) {
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
    }
}
