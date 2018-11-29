package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.subtle.EllipticCurves.EcdsaEncoding;
import com.google.crypto.tink.subtle.Enums.HashType;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;

public final class EcdsaVerifyJce implements PublicKeyVerify {
    private final EcdsaEncoding encoding;
    private final ECPublicKey publicKey;
    private final String signatureAlgorithm;

    public EcdsaVerifyJce(ECPublicKey eCPublicKey, HashType hashType, EcdsaEncoding ecdsaEncoding) throws GeneralSecurityException {
        EllipticCurves.checkPublicKey(eCPublicKey);
        this.signatureAlgorithm = SubtleUtil.toEcdsaAlgo(hashType);
        this.publicKey = eCPublicKey;
        this.encoding = ecdsaEncoding;
    }

    public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (this.encoding == EcdsaEncoding.IEEE_P1363) {
            if (bArr.length == EllipticCurves.fieldSizeInBytes(this.publicKey.getParams().getCurve()) * 2) {
                bArr = EllipticCurves.ecdsaIeee2Der(bArr);
            } else {
                throw new GeneralSecurityException("Invalid signature");
            }
        }
        if (EllipticCurves.isValidDerEncoding(bArr)) {
            boolean verify;
            Signature signature = (Signature) EngineFactory.SIGNATURE.getInstance(this.signatureAlgorithm);
            signature.initVerify(this.publicKey);
            signature.update(bArr2);
            try {
                verify = signature.verify(bArr);
            } catch (RuntimeException unused) {
                verify = false;
            }
            if (!verify) {
                throw new GeneralSecurityException("Invalid signature");
            }
            return;
        }
        throw new GeneralSecurityException("Invalid signature");
    }
}
