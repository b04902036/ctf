package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.Catalogue;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.StreamingAead;
import java.security.GeneralSecurityException;

class StreamingAeadCatalogue implements Catalogue<StreamingAead> {
    public KeyManager<StreamingAead> getKeyManager(String str, String str2, int i) throws GeneralSecurityException {
        String toLowerCase = str2.toLowerCase();
        int i2 = (toLowerCase.hashCode() == 754366121 && toLowerCase.equals("streamingaead")) ? 0 : -1;
        if (i2 == 0) {
            KeyManager<StreamingAead> streamingAeadKeyManager = streamingAeadKeyManager(str);
            if (streamingAeadKeyManager.getVersion() >= i) {
                return streamingAeadKeyManager;
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
    private com.google.crypto.tink.KeyManager<com.google.crypto.tink.StreamingAead> streamingAeadKeyManager(java.lang.String r4) throws java.security.GeneralSecurityException {
        /*
        r3 = this;
        r3 = r4.hashCode();
        r0 = -2002307740; // 0xffffffff88a73564 float:-1.0063499E-33 double:NaN;
        r1 = 0;
        r2 = 1;
        if (r3 == r0) goto L_0x001b;
    L_0x000b:
        r0 = -608502222; // 0xffffffffdbbafe32 float:-1.05267673E17 double:NaN;
        if (r3 == r0) goto L_0x0011;
    L_0x0010:
        goto L_0x0025;
    L_0x0011:
        r3 = "type.googleapis.com/google.crypto.tink.AesGcmHkdfStreamingKey";
        r3 = r4.equals(r3);
        if (r3 == 0) goto L_0x0025;
    L_0x0019:
        r3 = r2;
        goto L_0x0026;
    L_0x001b:
        r3 = "type.googleapis.com/google.crypto.tink.AesCtrHmacStreamingKey";
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
        r4 = "No support for primitive 'StreamingAead' with key type '%s'.";
        r4 = java.lang.String.format(r4, r0);
        r3.<init>(r4);
        throw r3;
    L_0x0039:
        r3 = new com.google.crypto.tink.streamingaead.AesGcmHkdfStreamingKeyManager;
        r3.<init>();
        return r3;
    L_0x003f:
        r3 = new com.google.crypto.tink.streamingaead.AesCtrHmacStreamingKeyManager;
        r3.<init>();
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.streamingaead.StreamingAeadCatalogue.streamingAeadKeyManager(java.lang.String):com.google.crypto.tink.KeyManager<com.google.crypto.tink.StreamingAead>");
    }
}
