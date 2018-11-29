package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Bytes;
import java.security.GeneralSecurityException;
import java.util.Collection;

public final class PublicKeySignFactory {
    public static PublicKeySign getPrimitive(KeysetHandle keysetHandle) throws GeneralSecurityException {
        return getPrimitive(keysetHandle, null);
    }

    public static PublicKeySign getPrimitive(KeysetHandle keysetHandle, KeyManager<PublicKeySign> keyManager) throws GeneralSecurityException {
        final PrimitiveSet primitives = Registry.getPrimitives(keysetHandle, keyManager);
        validate(primitives);
        return new PublicKeySign() {
            public byte[] sign(byte[] bArr) throws GeneralSecurityException {
                if (primitives.getPrimary().getOutputPrefixType().equals(OutputPrefixType.LEGACY)) {
                    byte[] bArr2 = new byte[]{(byte) 0};
                    byte[][] bArr3 = new byte[2][];
                    bArr3[0] = primitives.getPrimary().getIdentifier();
                    bArr3[1] = ((PublicKeySign) primitives.getPrimary().getPrimitive()).sign(Bytes.concat(bArr, bArr2));
                    return Bytes.concat(bArr3);
                }
                return Bytes.concat(primitives.getPrimary().getIdentifier(), ((PublicKeySign) primitives.getPrimary().getPrimitive()).sign(bArr));
            }
        };
    }

    private static void validate(PrimitiveSet<PublicKeySign> primitiveSet) throws GeneralSecurityException {
        for (Collection<Entry> it : primitiveSet.getAll()) {
            for (Entry primitive : it) {
                if (!(primitive.getPrimitive() instanceof PublicKeySign)) {
                    throw new GeneralSecurityException("invalid PublicKeySign key material");
                }
            }
        }
    }
}
