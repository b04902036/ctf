package com.google.crypto.tink.daead;

import com.google.crypto.tink.DeterministicAead;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.logging.Logger;

public final class DeterministicAeadFactory {
    private static final Logger logger = Logger.getLogger(DeterministicAeadFactory.class.getName());

    public static DeterministicAead getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    public static DeterministicAead getPrimitive(KeysetHandle keysetHandle, KeyManager<DeterministicAead> keyManager) throws GeneralSecurityException {
        final PrimitiveSet primitives = Registry.getPrimitives(keysetHandle, keyManager);
        validate(primitives);
        return new DeterministicAead() {
            public byte[] encryptDeterministically(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
                return Bytes.concat(primitives.getPrimary().getIdentifier(), ((DeterministicAead) primitives.getPrimary().getPrimitive()).encryptDeterministically(bArr, bArr2));
            }

            /* JADX WARNING: Can't wrap try/catch for R(6:14|15|16|23|17|12) */
            /* JADX WARNING: Missing block: B:13:0x005b, code:
            if (r6.hasNext() != false) goto L_0x005d;
     */
            /* JADX WARNING: Missing block: B:17:0x006d, code:
            return ((com.google.crypto.tink.DeterministicAead) ((com.google.crypto.tink.PrimitiveSet.Entry) r6.next()).getPrimitive()).decryptDeterministically(r7, r8);
     */
            /* JADX WARNING: Missing block: B:19:0x0075, code:
            throw new java.security.GeneralSecurityException("decryption failed");
     */
            public byte[] decryptDeterministically(byte[] r7, byte[] r8) throws java.security.GeneralSecurityException {
                /*
                r6 = this;
                r0 = r7.length;
                r1 = 5;
                if (r0 <= r1) goto L_0x004d;
            L_0x0004:
                r0 = 0;
                r0 = java.util.Arrays.copyOfRange(r7, r0, r1);
                r2 = r7.length;
                r1 = java.util.Arrays.copyOfRange(r7, r1, r2);
                r2 = r0;
                r0 = r2.getPrimitive(r0);
                r0 = r0.iterator();
            L_0x0018:
                r2 = r0.hasNext();
                if (r2 == 0) goto L_0x004d;
            L_0x001e:
                r2 = r0.next();
                r2 = (com.google.crypto.tink.PrimitiveSet.Entry) r2;
                r2 = r2.getPrimitive();	 Catch:{ GeneralSecurityException -> 0x002f }
                r2 = (com.google.crypto.tink.DeterministicAead) r2;	 Catch:{ GeneralSecurityException -> 0x002f }
                r2 = r2.decryptDeterministically(r1, r8);	 Catch:{ GeneralSecurityException -> 0x002f }
                return r2;
            L_0x002f:
                r2 = move-exception;
                r3 = com.google.crypto.tink.daead.DeterministicAeadFactory.logger;
                r4 = new java.lang.StringBuilder;
                r4.<init>();
                r5 = "ciphertext prefix matches a key, but cannot decrypt: ";
                r4.append(r5);
                r2 = r2.toString();
                r4.append(r2);
                r2 = r4.toString();
                r3.info(r2);
                goto L_0x0018;
            L_0x004d:
                r6 = r0;
                r6 = r6.getRawPrimitives();
                r6 = r6.iterator();
            L_0x0057:
                r0 = r6.hasNext();
                if (r0 == 0) goto L_0x006e;
            L_0x005d:
                r0 = r6.next();
                r0 = (com.google.crypto.tink.PrimitiveSet.Entry) r0;
                r0 = r0.getPrimitive();	 Catch:{ GeneralSecurityException -> 0x0057 }
                r0 = (com.google.crypto.tink.DeterministicAead) r0;	 Catch:{ GeneralSecurityException -> 0x0057 }
                r0 = r0.decryptDeterministically(r7, r8);	 Catch:{ GeneralSecurityException -> 0x0057 }
                return r0;
            L_0x006e:
                r6 = new java.security.GeneralSecurityException;
                r7 = "decryption failed";
                r6.<init>(r7);
                throw r6;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.daead.DeterministicAeadFactory.1.decryptDeterministically(byte[], byte[]):byte[]");
            }
        };
    }

    private static void validate(PrimitiveSet<DeterministicAead> primitiveSet) throws GeneralSecurityException {
        for (Collection<Entry> it : primitiveSet.getAll()) {
            for (Entry primitive : it) {
                if (!(primitive.getPrimitive() instanceof DeterministicAead)) {
                    throw new GeneralSecurityException("invalid Deterministic AEAD key material");
                }
            }
        }
    }
}
