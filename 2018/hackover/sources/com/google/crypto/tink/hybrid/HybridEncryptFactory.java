package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.logging.Logger;

public final class HybridEncryptFactory {
    private static final Logger logger = Logger.getLogger(HybridEncryptFactory.class.getName());

    public static HybridEncrypt getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    public static HybridEncrypt getPrimitive(KeysetHandle keysetHandle, KeyManager<HybridEncrypt> keyManager) throws GeneralSecurityException {
        final PrimitiveSet primitives = Registry.getPrimitives(keysetHandle, keyManager);
        validate(primitives);
        return new HybridEncrypt() {
            public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
                return Bytes.concat(primitives.getPrimary().getIdentifier(), ((HybridEncrypt) primitives.getPrimary().getPrimitive()).encrypt(bArr, bArr2));
            }
        };
    }

    private static void validate(PrimitiveSet<HybridEncrypt> primitiveSet) throws GeneralSecurityException {
        for (Collection<Entry> it : primitiveSet.getAll()) {
            for (Entry primitive : it) {
                if (!(primitive.getPrimitive() instanceof HybridEncrypt)) {
                    throw new GeneralSecurityException("invalid HybridEncrypt key material");
                }
            }
        }
    }
}
