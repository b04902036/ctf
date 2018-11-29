package com.google.crypto.tink.aead;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.KeyTemplate;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class KmsEnvelopeAead implements Aead {
    private static final byte[] EMPTY_AAD = new byte[0];
    private static final int LENGTH_ENCRYPTED_DEK = 4;
    private final KeyTemplate dekTemplate;
    private final Aead remote;

    public KmsEnvelopeAead(KeyTemplate keyTemplate, Aead aead) {
        this.dekTemplate = keyTemplate;
        this.remote = aead;
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] toByteArray = Registry.newKey(this.dekTemplate).toByteArray();
        return buildCiphertext(this.remote.encrypt(toByteArray, EMPTY_AAD), ((Aead) Registry.getPrimitive(this.dekTemplate.getTypeUrl(), toByteArray)).encrypt(bArr, bArr2));
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0043 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.IndexOutOfBoundsException (r4_7 'e' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0043 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.IndexOutOfBoundsException (r4_7 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:9:0x0043, code:
            r4 = move-exception;
     */
    /* JADX WARNING: Missing block: B:11:0x004b, code:
            throw new java.security.GeneralSecurityException("invalid ciphertext", r4);
     */
    public byte[] decrypt(byte[] r5, byte[] r6) throws java.security.GeneralSecurityException {
        /*
        r4 = this;
        r0 = java.nio.ByteBuffer.wrap(r5);	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r1 = r0.getInt();	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        if (r1 <= 0) goto L_0x003b;
    L_0x000a:
        r5 = r5.length;	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r5 = r5 + -4;
        if (r1 > r5) goto L_0x003b;
    L_0x000f:
        r5 = new byte[r1];	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r2 = 0;
        r0.get(r5, r2, r1);	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r1 = r0.remaining();	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r1 = new byte[r1];	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r3 = r0.remaining();	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r0.get(r1, r2, r3);	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r0 = r4.remote;	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r2 = EMPTY_AAD;	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r5 = r0.decrypt(r5, r2);	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r4 = r4.dekTemplate;	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r4 = r4.getTypeUrl();	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r4 = com.google.crypto.tink.Registry.getPrimitive(r4, r5);	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r4 = (com.google.crypto.tink.Aead) r4;	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r4 = r4.decrypt(r1, r6);	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        return r4;
    L_0x003b:
        r4 = new java.security.GeneralSecurityException;	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        r5 = "invalid ciphertext";
        r4.<init>(r5);	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
        throw r4;	 Catch:{ IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043, IndexOutOfBoundsException -> 0x0043 }
    L_0x0043:
        r4 = move-exception;
        r5 = new java.security.GeneralSecurityException;
        r6 = "invalid ciphertext";
        r5.<init>(r6, r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.aead.KmsEnvelopeAead.decrypt(byte[], byte[]):byte[]");
    }

    private byte[] buildCiphertext(byte[] bArr, byte[] bArr2) {
        return ByteBuffer.allocate((bArr.length + 4) + bArr2.length).putInt(bArr.length).put(bArr).put(bArr2).array();
    }
}
