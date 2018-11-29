package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.logging.Logger;

public final class MacFactory {
    private static final Logger logger = Logger.getLogger(MacFactory.class.getName());

    public static Mac getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    public static Mac getPrimitive(KeysetHandle keysetHandle, KeyManager<Mac> keyManager) throws GeneralSecurityException {
        final PrimitiveSet primitives = Registry.getPrimitives(keysetHandle, keyManager);
        validate(primitives);
        final byte[] bArr = new byte[]{(byte) 0};
        return new Mac() {
            public byte[] computeMac(byte[] bArr) throws GeneralSecurityException {
                if (primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                    byte[][] bArr2 = new byte[2][];
                    bArr2[0] = primitives.getPrimary().getIdentifier();
                    bArr2[1] = ((Mac) primitives.getPrimary().getPrimitive()).computeMac(Bytes.concat(bArr, bArr));
                    return Bytes.concat(bArr2);
                }
                return Bytes.concat(primitives.getPrimary().getIdentifier(), ((Mac) primitives.getPrimary().getPrimitive()).computeMac(bArr));
            }

            /* JADX WARNING: Can't wrap try/catch for R(6:17|18|19|27|20|15) */
            /* JADX WARNING: Missing block: B:16:0x007e, code:
            if (r7.hasNext() != false) goto L_0x0080;
     */
            /* JADX WARNING: Missing block: B:19:?, code:
            ((com.google.crypto.tink.Mac) ((com.google.crypto.tink.PrimitiveSet.Entry) r7.next()).getPrimitive()).verifyMac(r8, r9);
     */
            /* JADX WARNING: Missing block: B:20:0x008f, code:
            return;
     */
            /* JADX WARNING: Missing block: B:22:0x0097, code:
            throw new java.security.GeneralSecurityException("invalid MAC");
     */
            public void verifyMac(byte[] r8, byte[] r9) throws java.security.GeneralSecurityException {
                /*
                r7 = this;
                r0 = r8.length;
                r1 = 5;
                if (r0 <= r1) goto L_0x0098;
            L_0x0004:
                r0 = 0;
                r2 = java.util.Arrays.copyOfRange(r8, r0, r1);
                r3 = r8.length;
                r1 = java.util.Arrays.copyOfRange(r8, r1, r3);
                r3 = r1;
                r2 = r3.getPrimitive(r2);
                r2 = r2.iterator();
            L_0x0018:
                r3 = r2.hasNext();
                if (r3 == 0) goto L_0x0070;
            L_0x001e:
                r3 = r2.next();
                r3 = (com.google.crypto.tink.PrimitiveSet.Entry) r3;
                r4 = r3.getOutputPrefixType();	 Catch:{ GeneralSecurityException -> 0x0052 }
                r5 = com.google.crypto.tink.proto.OutputPrefixType.LEGACY;	 Catch:{ GeneralSecurityException -> 0x0052 }
                r4 = r4.equals(r5);	 Catch:{ GeneralSecurityException -> 0x0052 }
                if (r4 == 0) goto L_0x0048;
            L_0x0030:
                r3 = r3.getPrimitive();	 Catch:{ GeneralSecurityException -> 0x0052 }
                r3 = (com.google.crypto.tink.Mac) r3;	 Catch:{ GeneralSecurityException -> 0x0052 }
                r4 = 2;
                r4 = new byte[r4][];	 Catch:{ GeneralSecurityException -> 0x0052 }
                r4[r0] = r9;	 Catch:{ GeneralSecurityException -> 0x0052 }
                r5 = 1;
                r6 = r2;	 Catch:{ GeneralSecurityException -> 0x0052 }
                r4[r5] = r6;	 Catch:{ GeneralSecurityException -> 0x0052 }
                r4 = com.google.crypto.tink.subtle.Bytes.concat(r4);	 Catch:{ GeneralSecurityException -> 0x0052 }
                r3.verifyMac(r1, r4);	 Catch:{ GeneralSecurityException -> 0x0052 }
                goto L_0x0051;
            L_0x0048:
                r3 = r3.getPrimitive();	 Catch:{ GeneralSecurityException -> 0x0052 }
                r3 = (com.google.crypto.tink.Mac) r3;	 Catch:{ GeneralSecurityException -> 0x0052 }
                r3.verifyMac(r1, r9);	 Catch:{ GeneralSecurityException -> 0x0052 }
            L_0x0051:
                return;
            L_0x0052:
                r3 = move-exception;
                r4 = com.google.crypto.tink.mac.MacFactory.logger;
                r5 = new java.lang.StringBuilder;
                r5.<init>();
                r6 = "tag prefix matches a key, but cannot verify: ";
                r5.append(r6);
                r3 = r3.toString();
                r5.append(r3);
                r3 = r5.toString();
                r4.info(r3);
                goto L_0x0018;
            L_0x0070:
                r7 = r1;
                r7 = r7.getRawPrimitives();
                r7 = r7.iterator();
            L_0x007a:
                r0 = r7.hasNext();
                if (r0 == 0) goto L_0x0090;
            L_0x0080:
                r0 = r7.next();
                r0 = (com.google.crypto.tink.PrimitiveSet.Entry) r0;
                r0 = r0.getPrimitive();	 Catch:{ GeneralSecurityException -> 0x007a }
                r0 = (com.google.crypto.tink.Mac) r0;	 Catch:{ GeneralSecurityException -> 0x007a }
                r0.verifyMac(r8, r9);	 Catch:{ GeneralSecurityException -> 0x007a }
                return;
            L_0x0090:
                r7 = new java.security.GeneralSecurityException;
                r8 = "invalid MAC";
                r7.<init>(r8);
                throw r7;
            L_0x0098:
                r7 = new java.security.GeneralSecurityException;
                r8 = "tag too short";
                r7.<init>(r8);
                throw r7;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.mac.MacFactory.1.verifyMac(byte[], byte[]):void");
            }
        };
    }

    private static void validate(PrimitiveSet<Mac> primitiveSet) throws GeneralSecurityException {
        for (Collection<Entry> it : primitiveSet.getAll()) {
            for (Entry primitive : it) {
                if (!(primitive.getPrimitive() instanceof Mac)) {
                    throw new GeneralSecurityException("invalid MAC key material");
                }
            }
        }
    }
}
