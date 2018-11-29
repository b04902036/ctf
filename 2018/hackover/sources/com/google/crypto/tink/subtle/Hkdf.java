package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class Hkdf {
    public static byte[] computeHkdf(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, int i) throws GeneralSecurityException {
        Mac mac = (Mac) EngineFactory.MAC.getInstance(str);
        if (i <= mac.getMacLength() * 255) {
            if (bArr2 == null || bArr2.length == 0) {
                mac.init(new SecretKeySpec(new byte[mac.getMacLength()], str));
            } else {
                mac.init(new SecretKeySpec(bArr2, str));
            }
            Object obj = new byte[i];
            mac.init(new SecretKeySpec(mac.doFinal(bArr), str));
            bArr = new byte[0];
            int i2 = 1;
            int i3 = 0;
            while (true) {
                mac.update(bArr);
                mac.update(bArr3);
                mac.update((byte) i2);
                bArr = mac.doFinal();
                if (bArr.length + i3 < i) {
                    System.arraycopy(bArr, 0, obj, i3, bArr.length);
                    i3 += bArr.length;
                    i2++;
                } else {
                    System.arraycopy(bArr, 0, obj, i3, i - i3);
                    return obj;
                }
            }
        }
        throw new GeneralSecurityException("size too large");
    }

    public static byte[] computeEciesHkdfSymmetricKey(byte[] bArr, byte[] bArr2, String str, byte[] bArr3, byte[] bArr4, int i) throws GeneralSecurityException {
        return computeHkdf(str, Bytes.concat(bArr, bArr2), bArr3, bArr4, i);
    }
}
