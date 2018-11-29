package com.google.crypto.tink.signature;

import com.google.crypto.tink.Catalogue;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.PublicKeySign;
import java.security.GeneralSecurityException;

class PublicKeySignCatalogue implements Catalogue<PublicKeySign> {
    public KeyManager<PublicKeySign> getKeyManager(String str, String str2, int i) throws GeneralSecurityException {
        String toLowerCase = str2.toLowerCase();
        int i2 = (toLowerCase.hashCode() == -1213945325 && toLowerCase.equals("publickeysign")) ? 0 : -1;
        if (i2 == 0) {
            KeyManager<PublicKeySign> publicKeySignKeyManager = publicKeySignKeyManager(str);
            if (publicKeySignKeyManager.getVersion() >= i) {
                return publicKeySignKeyManager;
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
    private com.google.crypto.tink.KeyManager<com.google.crypto.tink.PublicKeySign> publicKeySignKeyManager(java.lang.String r4) throws java.security.GeneralSecurityException {
        /*
        r3 = this;
        r3 = r4.hashCode();
        r0 = -1470419991; // 0xffffffffa85b2be9 float:-1.2166464E-14 double:NaN;
        r1 = 0;
        r2 = 1;
        if (r3 == r0) goto L_0x001b;
    L_0x000b:
        r0 = -359160126; // 0xffffffffea97a6c2 float:-9.1667645E25 double:NaN;
        if (r3 == r0) goto L_0x0011;
    L_0x0010:
        goto L_0x0025;
    L_0x0011:
        r3 = "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey";
        r3 = r4.equals(r3);
        if (r3 == 0) goto L_0x0025;
    L_0x0019:
        r3 = r1;
        goto L_0x0026;
    L_0x001b:
        r3 = "type.googleapis.com/google.crypto.tink.Ed25519PrivateKey";
        r3 = r4.equals(r3);
        if (r3 == 0) goto L_0x0025;
    L_0x0023:
        r3 = r2;
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
        r4 = "No support for primitive 'PublicKeySign' with key type '%s'.";
        r4 = java.lang.String.format(r4, r0);
        r3.<init>(r4);
        throw r3;
    L_0x0039:
        r3 = new com.google.crypto.tink.signature.Ed25519PrivateKeyManager;
        r3.<init>();
        return r3;
    L_0x003f:
        r3 = new com.google.crypto.tink.signature.EcdsaSignKeyManager;
        r3.<init>();
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.signature.PublicKeySignCatalogue.publicKeySignKeyManager(java.lang.String):com.google.crypto.tink.KeyManager<com.google.crypto.tink.PublicKeySign>");
    }
}
