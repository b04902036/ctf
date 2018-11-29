package com.google.crypto.tink.subtle;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.subtle.EciesHkdfSenderKem.KemKey;
import com.google.crypto.tink.subtle.EllipticCurves.PointFormatType;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

public final class EciesAeadHkdfHybridEncrypt implements HybridEncrypt {
    private static final byte[] EMPTY_AAD = new byte[0];
    private final EciesAeadHkdfDemHelper demHelper;
    private final PointFormatType ecPointFormat;
    private final String hkdfHmacAlgo;
    private final byte[] hkdfSalt;
    private final EciesHkdfSenderKem senderKem;

    public EciesAeadHkdfHybridEncrypt(ECPublicKey eCPublicKey, byte[] bArr, String str, PointFormatType pointFormatType, EciesAeadHkdfDemHelper eciesAeadHkdfDemHelper) throws GeneralSecurityException {
        EllipticCurves.checkPublicKey(eCPublicKey);
        this.senderKem = new EciesHkdfSenderKem(eCPublicKey);
        this.hkdfSalt = bArr;
        this.hkdfHmacAlgo = str;
        this.ecPointFormat = pointFormatType;
        this.demHelper = eciesAeadHkdfDemHelper;
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        KemKey generateKey = this.senderKem.generateKey(this.hkdfHmacAlgo, this.hkdfSalt, bArr2, this.demHelper.getSymmetricKeySizeInBytes(), this.ecPointFormat);
        byte[] encrypt = this.demHelper.getAead(generateKey.getSymmetricKey()).encrypt(bArr, EMPTY_AAD);
        bArr = generateKey.getKemBytes();
        return ByteBuffer.allocate(bArr.length + encrypt.length).put(bArr).put(encrypt).array();
    }
}
