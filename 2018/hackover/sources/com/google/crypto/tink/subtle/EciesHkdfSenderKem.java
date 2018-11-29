package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.EllipticCurves.PointFormatType;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public final class EciesHkdfSenderKem {
    private ECPublicKey recipientPublicKey;

    public static final class KemKey {
        private final ImmutableByteArray kemBytes;
        private final ImmutableByteArray symmetricKey;

        public KemKey(byte[] bArr, byte[] bArr2) {
            this.kemBytes = ImmutableByteArray.of(bArr);
            this.symmetricKey = ImmutableByteArray.of(bArr2);
        }

        public byte[] getKemBytes() {
            if (this.kemBytes == null) {
                return null;
            }
            return this.kemBytes.getBytes();
        }

        public byte[] getSymmetricKey() {
            if (this.symmetricKey == null) {
                return null;
            }
            return this.symmetricKey.getBytes();
        }
    }

    public EciesHkdfSenderKem(ECPublicKey eCPublicKey) {
        this.recipientPublicKey = eCPublicKey;
    }

    public KemKey generateKey(String str, byte[] bArr, byte[] bArr2, int i, PointFormatType pointFormatType) throws GeneralSecurityException {
        KeyPair generateKeyPair = EllipticCurves.generateKeyPair(this.recipientPublicKey.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) generateKeyPair.getPublic();
        byte[] computeSharedSecret = EllipticCurves.computeSharedSecret((ECPrivateKey) generateKeyPair.getPrivate(), this.recipientPublicKey);
        byte[] pointEncode = EllipticCurves.pointEncode(eCPublicKey.getParams().getCurve(), pointFormatType, eCPublicKey.getW());
        return new KemKey(pointEncode, Hkdf.computeEciesHkdfSymmetricKey(pointEncode, computeSharedSecret, str, bArr, bArr2, i));
    }
}
