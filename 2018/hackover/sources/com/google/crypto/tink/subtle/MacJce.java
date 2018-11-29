package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Mac;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public final class MacJce implements Mac {
    static final int MIN_KEY_SIZE_IN_BYTES = 16;
    static final int MIN_TAG_SIZE_IN_BYTES = 10;
    private final String algorithm;
    private final int digestSize;
    private final Key key;
    private javax.crypto.Mac mac;

    public MacJce(String str, Key key, int i) throws GeneralSecurityException {
        if (i >= 10) {
            Object obj = -1;
            int hashCode = str.hashCode();
            if (hashCode != -1823053428) {
                if (hashCode != 392315118) {
                    if (hashCode == 392317873 && str.equals("HMACSHA512")) {
                        obj = 2;
                    }
                } else if (str.equals("HMACSHA256")) {
                    obj = 1;
                }
            } else if (str.equals("HMACSHA1")) {
                obj = null;
            }
            switch (obj) {
                case null:
                    if (i > 20) {
                        throw new InvalidAlgorithmParameterException("tag size too big");
                    }
                    break;
                case 1:
                    if (i > 32) {
                        throw new InvalidAlgorithmParameterException("tag size too big");
                    }
                    break;
                case 2:
                    if (i > 64) {
                        throw new InvalidAlgorithmParameterException("tag size too big");
                    }
                    break;
                default:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("unknown Hmac algorithm: ");
                    stringBuilder.append(str);
                    throw new NoSuchAlgorithmException(stringBuilder.toString());
            }
            this.algorithm = str;
            this.digestSize = i;
            this.key = key;
            this.mac = (javax.crypto.Mac) EngineFactory.MAC.getInstance(str);
            this.mac.init(key);
            return;
        }
        throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
    }

    public byte[] computeMac(byte[] bArr) throws GeneralSecurityException {
        javax.crypto.Mac mac;
        try {
            mac = (javax.crypto.Mac) this.mac.clone();
        } catch (CloneNotSupportedException unused) {
            mac = (javax.crypto.Mac) EngineFactory.MAC.getInstance(this.algorithm);
            mac.init(this.key);
        }
        mac.update(bArr);
        Object obj = new byte[this.digestSize];
        System.arraycopy(mac.doFinal(), 0, obj, 0, this.digestSize);
        return obj;
    }

    public void verifyMac(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!Bytes.equal(computeMac(bArr2), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
