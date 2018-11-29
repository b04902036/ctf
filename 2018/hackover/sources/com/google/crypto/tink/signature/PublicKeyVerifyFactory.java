package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PublicKeyVerify;
import com.google.crypto.tink.Registry;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.logging.Logger;

public final class PublicKeyVerifyFactory {
    private static final Logger logger = Logger.getLogger(PublicKeyVerifyFactory.class.getName());

    public static PublicKeyVerify getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    public static PublicKeyVerify getPrimitive(KeysetHandle keysetHandle, KeyManager<PublicKeyVerify> keyManager) throws GeneralSecurityException {
        final PrimitiveSet primitives = Registry.getPrimitives(keysetHandle, keyManager);
        validate(primitives);
        return new PublicKeyVerify() {
            /* JADX WARNING: Can't wrap try/catch for R(6:17|18|19|28|20|15) */
            /* JADX WARNING: Missing block: B:16:0x0080, code:
            if (r7.hasNext() != false) goto L_0x0082;
     */
            /* JADX WARNING: Missing block: B:19:?, code:
            ((com.google.crypto.tink.PublicKeyVerify) ((com.google.crypto.tink.PrimitiveSet.Entry) r7.next()).getPrimitive()).verify(r8, r9);
     */
            /* JADX WARNING: Missing block: B:20:0x0091, code:
            return;
     */
            /* JADX WARNING: Missing block: B:22:0x0099, code:
            throw new java.security.GeneralSecurityException("invalid signature");
     */
            public void verify(byte[] r8, byte[] r9) throws java.security.GeneralSecurityException {
                /*
                r7 = this;
                r0 = r8.length;
                r1 = 5;
                if (r0 <= r1) goto L_0x009a;
            L_0x0004:
                r0 = 0;
                r2 = java.util.Arrays.copyOfRange(r8, r0, r1);
                r3 = r8.length;
                r1 = java.util.Arrays.copyOfRange(r8, r1, r3);
                r3 = r0;
                r2 = r3.getPrimitive(r2);
                r2 = r2.iterator();
            L_0x0018:
                r3 = r2.hasNext();
                if (r3 == 0) goto L_0x0072;
            L_0x001e:
                r3 = r2.next();
                r3 = (com.google.crypto.tink.PrimitiveSet.Entry) r3;
                r4 = r3.getOutputPrefixType();	 Catch:{ GeneralSecurityException -> 0x0054 }
                r5 = com.google.crypto.tink.proto.OutputPrefixType.LEGACY;	 Catch:{ GeneralSecurityException -> 0x0054 }
                r4 = r4.equals(r5);	 Catch:{ GeneralSecurityException -> 0x0054 }
                if (r4 == 0) goto L_0x004a;
            L_0x0030:
                r4 = 1;
                r5 = new byte[r4];	 Catch:{ GeneralSecurityException -> 0x0054 }
                r5[r0] = r0;	 Catch:{ GeneralSecurityException -> 0x0054 }
                r6 = 2;
                r6 = new byte[r6][];	 Catch:{ GeneralSecurityException -> 0x0054 }
                r6[r0] = r9;	 Catch:{ GeneralSecurityException -> 0x0054 }
                r6[r4] = r5;	 Catch:{ GeneralSecurityException -> 0x0054 }
                r4 = com.google.crypto.tink.subtle.Bytes.concat(r6);	 Catch:{ GeneralSecurityException -> 0x0054 }
                r3 = r3.getPrimitive();	 Catch:{ GeneralSecurityException -> 0x0054 }
                r3 = (com.google.crypto.tink.PublicKeyVerify) r3;	 Catch:{ GeneralSecurityException -> 0x0054 }
                r3.verify(r1, r4);	 Catch:{ GeneralSecurityException -> 0x0054 }
                goto L_0x0053;
            L_0x004a:
                r3 = r3.getPrimitive();	 Catch:{ GeneralSecurityException -> 0x0054 }
                r3 = (com.google.crypto.tink.PublicKeyVerify) r3;	 Catch:{ GeneralSecurityException -> 0x0054 }
                r3.verify(r1, r9);	 Catch:{ GeneralSecurityException -> 0x0054 }
            L_0x0053:
                return;
            L_0x0054:
                r3 = move-exception;
                r4 = com.google.crypto.tink.signature.PublicKeyVerifyFactory.logger;
                r5 = new java.lang.StringBuilder;
                r5.<init>();
                r6 = "signature prefix matches a key, but cannot verify: ";
                r5.append(r6);
                r3 = r3.toString();
                r5.append(r3);
                r3 = r5.toString();
                r4.info(r3);
                goto L_0x0018;
            L_0x0072:
                r7 = r0;
                r7 = r7.getRawPrimitives();
                r7 = r7.iterator();
            L_0x007c:
                r0 = r7.hasNext();
                if (r0 == 0) goto L_0x0092;
            L_0x0082:
                r0 = r7.next();
                r0 = (com.google.crypto.tink.PrimitiveSet.Entry) r0;
                r0 = r0.getPrimitive();	 Catch:{ GeneralSecurityException -> 0x007c }
                r0 = (com.google.crypto.tink.PublicKeyVerify) r0;	 Catch:{ GeneralSecurityException -> 0x007c }
                r0.verify(r8, r9);	 Catch:{ GeneralSecurityException -> 0x007c }
                return;
            L_0x0092:
                r7 = new java.security.GeneralSecurityException;
                r8 = "invalid signature";
                r7.<init>(r8);
                throw r7;
            L_0x009a:
                r7 = new java.security.GeneralSecurityException;
                r8 = "signature too short";
                r7.<init>(r8);
                throw r7;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.signature.PublicKeyVerifyFactory.1.verify(byte[], byte[]):void");
            }
        };
    }

    private static void validate(PrimitiveSet<PublicKeyVerify> primitiveSet) throws GeneralSecurityException {
        for (Collection<Entry> it : primitiveSet.getAll()) {
            for (Entry primitive : it) {
                if (!(primitive.getPrimitive() instanceof PublicKeyVerify)) {
                    throw new GeneralSecurityException("invalid PublicKeyVerify key material");
                }
            }
        }
    }
}
