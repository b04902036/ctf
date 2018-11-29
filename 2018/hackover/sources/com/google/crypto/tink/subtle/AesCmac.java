package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.annotations.Alpha;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Alpha
public final class AesCmac implements Mac {
    static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private final SecretKey keySpec;
    private byte[] subKey1;
    private byte[] subKey2;
    private final int tagSizeInBytes;

    private static Cipher instance() throws GeneralSecurityException {
        return (Cipher) EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");
    }

    public AesCmac(byte[] bArr, int i) throws GeneralSecurityException {
        Validators.validateAesKeySize(bArr.length);
        if (i < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, min is 10 bytes");
        } else if (i <= 16) {
            this.keySpec = new SecretKeySpec(bArr, "AES");
            this.tagSizeInBytes = i;
            generateSubKeys();
        } else {
            throw new InvalidAlgorithmParameterException("tag size too large, max is 16 bytes");
        }
    }

    public byte[] computeMac(byte[] bArr) throws GeneralSecurityException {
        byte[] xor;
        Cipher instance = instance();
        instance.init(1, this.keySpec);
        int max = Math.max(1, (int) Math.ceil(((double) bArr.length) / 16.0d));
        if ((max * 16 == bArr.length ? 1 : 0) != 0) {
            xor = Bytes.xor(bArr, (max - 1) * 16, this.subKey1, 0, 16);
        } else {
            xor = Bytes.xor(AesUtil.cmacPad(Arrays.copyOfRange(bArr, (max - 1) * 16, bArr.length)), this.subKey2);
        }
        byte[] bArr2 = new byte[16];
        for (int i = 0; i < max - 1; i++) {
            bArr2 = instance.doFinal(Bytes.xor(bArr2, 0, bArr, i * 16, 16));
        }
        Object obj = new byte[this.tagSizeInBytes];
        System.arraycopy(instance.doFinal(Bytes.xor(xor, bArr2)), 0, obj, 0, this.tagSizeInBytes);
        return obj;
    }

    public void verifyMac(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!Bytes.equal(bArr, computeMac(bArr2))) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }

    private void generateSubKeys() throws GeneralSecurityException {
        Cipher instance = instance();
        instance.init(1, this.keySpec);
        this.subKey1 = AesUtil.dbl(instance.doFinal(new byte[16]));
        this.subKey2 = AesUtil.dbl(this.subKey1);
    }
}
