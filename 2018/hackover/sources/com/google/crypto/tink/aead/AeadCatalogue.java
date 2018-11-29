package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Catalogue;
import com.google.crypto.tink.KeyManager;
import java.security.GeneralSecurityException;

class AeadCatalogue implements Catalogue<Aead> {
    public KeyManager<Aead> getKeyManager(String str, String str2, int i) throws GeneralSecurityException {
        String toLowerCase = str2.toLowerCase();
        int i2 = (toLowerCase.hashCode() == 2989895 && toLowerCase.equals("aead")) ? 0 : -1;
        if (i2 == 0) {
            KeyManager<Aead> aeadKeyManager = aeadKeyManager(str);
            if (aeadKeyManager.getVersion() >= i) {
                return aeadKeyManager;
            }
            throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
        }
        throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
    }

    private com.google.crypto.tink.KeyManager<com.google.crypto.tink.Aead> aeadKeyManager(java.lang.String r3) throws java.security.GeneralSecurityException {
        /*
        r2 = this;
        r2 = r3.hashCode();
        r0 = 0;
        r1 = 1;
        switch(r2) {
            case 360753376: goto L_0x003c;
            case 1215885937: goto L_0x0032;
            case 1469984853: goto L_0x0028;
            case 1797113348: goto L_0x001e;
            case 1855890991: goto L_0x0014;
            case 2079211877: goto L_0x000a;
            default: goto L_0x0009;
        };
    L_0x0009:
        goto L_0x0046;
    L_0x000a:
        r2 = "type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0046;
    L_0x0012:
        r2 = 5;
        goto L_0x0047;
    L_0x0014:
        r2 = "type.googleapis.com/google.crypto.tink.AesGcmKey";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0046;
    L_0x001c:
        r2 = 2;
        goto L_0x0047;
    L_0x001e:
        r2 = "type.googleapis.com/google.crypto.tink.AesEaxKey";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0046;
    L_0x0026:
        r2 = r1;
        goto L_0x0047;
    L_0x0028:
        r2 = "type.googleapis.com/google.crypto.tink.KmsAeadKey";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0046;
    L_0x0030:
        r2 = 4;
        goto L_0x0047;
    L_0x0032:
        r2 = "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0046;
    L_0x003a:
        r2 = r0;
        goto L_0x0047;
    L_0x003c:
        r2 = "type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key";
        r2 = r3.equals(r2);
        if (r2 == 0) goto L_0x0046;
    L_0x0044:
        r2 = 3;
        goto L_0x0047;
    L_0x0046:
        r2 = -1;
    L_0x0047:
        switch(r2) {
            case 0: goto L_0x0078;
            case 1: goto L_0x0072;
            case 2: goto L_0x006c;
            case 3: goto L_0x0066;
            case 4: goto L_0x0060;
            case 5: goto L_0x005a;
            default: goto L_0x004a;
        };
    L_0x004a:
        r2 = new java.security.GeneralSecurityException;
        r1 = new java.lang.Object[r1];
        r1[r0] = r3;
        r3 = "No support for primitive 'Aead' with key type '%s'.";
        r3 = java.lang.String.format(r3, r1);
        r2.<init>(r3);
        throw r2;
    L_0x005a:
        r2 = new com.google.crypto.tink.aead.KmsEnvelopeAeadKeyManager;
        r2.<init>();
        return r2;
    L_0x0060:
        r2 = new com.google.crypto.tink.aead.KmsAeadKeyManager;
        r2.<init>();
        return r2;
    L_0x0066:
        r2 = new com.google.crypto.tink.aead.ChaCha20Poly1305KeyManager;
        r2.<init>();
        return r2;
    L_0x006c:
        r2 = new com.google.crypto.tink.aead.AesGcmKeyManager;
        r2.<init>();
        return r2;
    L_0x0072:
        r2 = new com.google.crypto.tink.aead.AesEaxKeyManager;
        r2.<init>();
        return r2;
    L_0x0078:
        r2 = new com.google.crypto.tink.aead.AesCtrHmacAeadKeyManager;
        r2.<init>();
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.aead.AeadCatalogue.aeadKeyManager(java.lang.String):com.google.crypto.tink.KeyManager<com.google.crypto.tink.Aead>");
    }
}
