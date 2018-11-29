package com.google.crypto.tink.signature;

import com.google.crypto.tink.Catalogue;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.PublicKeyVerify;
import java.security.GeneralSecurityException;

class PublicKeyVerifyCatalogue implements Catalogue<PublicKeyVerify> {
    public KeyManager<PublicKeyVerify> getKeyManager(String str, String str2, int i) throws GeneralSecurityException {
        String toLowerCase = str2.toLowerCase();
        int i2 = (toLowerCase.hashCode() == 1712166735 && toLowerCase.equals("publickeyverify")) ? 0 : -1;
        if (i2 == 0) {
            KeyManager<PublicKeyVerify> publicKeyVerifyKeyManager = publicKeyVerifyKeyManager(str);
            if (publicKeyVerifyKeyManager.getVersion() >= i) {
                return publicKeyVerifyKeyManager;
            }
            throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
        }
        throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0039  */
    private com.google.crypto.tink.KeyManager<com.google.crypto.tink.PublicKeyVerify> publicKeyVerifyKeyManager(java.lang.String r4) throws java.security.GeneralSecurityException {
        /*
        r3 = this;
        r3 = r4.hashCode();
        r0 = 248906128; // 0xed60190 float:5.2756578E-30 double:1.22975967E-315;
        r1 = 0;
        r2 = 1;
        if (r3 == r0) goto L_0x001b;
    L_0x000b:
        r0 = 1737079689; // 0x6789bb89 float:1.3008472E24 double:8.582313984E-315;
        if (r3 == r0) goto L_0x0011;
    L_0x0010:
        goto L_0x0025;
    L_0x0011:
        r3 = "type.googleapis.com/google.crypto.tink.Ed25519PublicKey";
        r3 = r4.equals(r3);
        if (r3 == 0) goto L_0x0025;
    L_0x0019:
        r3 = r2;
        goto L_0x0026;
    L_0x001b:
        r3 = "type.googleapis.com/google.crypto.tink.EcdsaPublicKey";
        r3 = r4.equals(r3);
        if (r3 == 0) goto L_0x0025;
    L_0x0023:
        r3 = r1;
        goto L_0x0026;
    L_0x0025:
        r3 = -1;
    L_0x0026:
        switch(r3) {
            case 0: goto L_0x003f;
            case 1: goto L_0x0039;
            default: goto L_0x0029;
        };
    L_0x0029:
        r3 = new java.security.GeneralSecurityException;
        r0 = new java.lang.Object[r2];
        r0[r1] = r4;
        r4 = "No support for primitive 'PublicKeyVerify' with key type '%s'.";
        r4 = java.lang.String.format(r4, r0);
        r3.<init>(r4);
        throw r3;
    L_0x0039:
        r3 = new com.google.crypto.tink.signature.Ed25519PublicKeyManager;
        r3.<init>();
        return r3;
    L_0x003f:
        r3 = new com.google.crypto.tink.signature.EcdsaVerifyKeyManager;
        r3.<init>();
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.signature.PublicKeyVerifyCatalogue.publicKeyVerifyKeyManager(java.lang.String):com.google.crypto.tink.KeyManager<com.google.crypto.tink.PublicKeyVerify>");
    }
}
