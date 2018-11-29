package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Aead;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesEaxJce implements Aead {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int BLOCK_SIZE_IN_BYTES = 16;
    static final int TAG_SIZE_IN_BYTES = 16;
    private final byte[] b;
    private final int ivSizeInBytes;
    private final SecretKeySpec keySpec;
    private final byte[] p;

    public AesEaxJce(byte[] bArr, int i) throws GeneralSecurityException {
        if (i == 12 || i == 16) {
            this.ivSizeInBytes = i;
            Validators.validateAesKeySize(bArr.length);
            this.keySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/NOPADDING");
            instance.init(1, this.keySpec);
            this.b = multiplyByX(instance.doFinal(new byte[16]));
            this.p = multiplyByX(this.b);
            return;
        }
        throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
    }

    private static byte[] xor(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    private static byte[] multiplyByX(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i = 0;
        int i2 = 0;
        while (i2 < 15) {
            int i3 = i2 + 1;
            bArr2[i2] = (byte) (((bArr[i2] << 1) ^ ((bArr[i3] & 255) >>> 7)) & 255);
            i2 = i3;
        }
        i2 = bArr[15] << 1;
        if ((bArr[0] & 128) != 0) {
            i = 135;
        }
        bArr2[15] = (byte) (i2 ^ i);
        return bArr2;
    }

    private byte[] pad(byte[] bArr) {
        if (bArr.length == 16) {
            return xor(bArr, this.b);
        }
        byte[] copyOf = Arrays.copyOf(this.p, 16);
        for (int i = 0; i < bArr.length; i++) {
            copyOf[i] = (byte) (copyOf[i] ^ bArr[i]);
        }
        copyOf[bArr.length] = (byte) (copyOf[bArr.length] ^ 128);
        return copyOf;
    }

    private byte[] omac(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws IllegalBlockSizeException, BadPaddingException {
        byte[] bArr2 = new byte[16];
        bArr2[15] = (byte) i;
        if (i3 == 0) {
            return cipher.doFinal(xor(bArr2, this.b));
        }
        byte[] doFinal = cipher.doFinal(bArr2);
        i = 0;
        while (i3 - i > 16) {
            for (int i4 = 0; i4 < 16; i4++) {
                doFinal[i4] = (byte) (doFinal[i4] ^ bArr[(i2 + i) + i4]);
            }
            doFinal = cipher.doFinal(doFinal);
            i += 16;
        }
        return cipher.doFinal(xor(doFinal, pad(Arrays.copyOfRange(bArr, i + i2, i2 + i3))));
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = bArr;
        if (bArr3.length <= (Integer.MAX_VALUE - this.ivSizeInBytes) - 16) {
            Object obj = new byte[((this.ivSizeInBytes + bArr3.length) + 16)];
            Object randBytes = Random.randBytes(this.ivSizeInBytes);
            int i = 0;
            System.arraycopy(randBytes, 0, obj, 0, this.ivSizeInBytes);
            Cipher instance = Cipher.getInstance("AES/ECB/NOPADDING");
            instance.init(1, this.keySpec);
            byte[] omac = omac(instance, 0, randBytes, 0, randBytes.length);
            byte[] bArr4 = bArr2 == null ? new byte[0] : bArr2;
            byte[] omac2 = omac(instance, 1, bArr4, 0, bArr4.length);
            Cipher instance2 = Cipher.getInstance("AES/CTR/NOPADDING");
            instance2.init(1, this.keySpec, new IvParameterSpec(omac));
            instance2.doFinal(bArr, 0, bArr3.length, obj, this.ivSizeInBytes);
            byte[] omac3 = omac(instance, 2, obj, this.ivSizeInBytes, bArr3.length);
            int length = bArr3.length + this.ivSizeInBytes;
            while (i < 16) {
                obj[length + i] = (byte) ((omac2[i] ^ omac[i]) ^ omac3[i]);
                i++;
            }
            return obj;
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = (bArr.length - this.ivSizeInBytes) - 16;
        if (length >= 0) {
            Cipher instance = Cipher.getInstance("AES/ECB/NOPADDING");
            instance.init(1, this.keySpec);
            byte[] omac = omac(instance, 0, bArr, 0, this.ivSizeInBytes);
            int i = 0;
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            byte[] bArr3 = bArr2;
            bArr2 = omac(instance, 1, bArr3, 0, bArr3.length);
            byte[] omac2 = omac(instance, 2, bArr, this.ivSizeInBytes, length);
            int length2 = bArr.length - 16;
            int i2 = 0;
            while (i < 16) {
                i2 = (byte) (i2 | (((bArr[length2 + i] ^ bArr2[i]) ^ omac[i]) ^ omac2[i]));
                i++;
            }
            if (i2 == 0) {
                Cipher instance2 = Cipher.getInstance("AES/CTR/NOPADDING");
                instance2.init(1, this.keySpec, new IvParameterSpec(omac));
                return instance2.doFinal(bArr, this.ivSizeInBytes, length);
            }
            throw new AEADBadTagException("tag mismatch");
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}
