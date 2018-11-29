package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.Catalogue;
import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.KeyManager;
import java.security.GeneralSecurityException;

class HybridEncryptCatalogue implements Catalogue<HybridEncrypt> {
    public KeyManager<HybridEncrypt> getKeyManager(String str, String str2, int i) throws GeneralSecurityException {
        String toLowerCase = str2.toLowerCase();
        int i2 = (toLowerCase.hashCode() == 1420614889 && toLowerCase.equals("hybridencrypt")) ? 0 : -1;
        if (i2 == 0) {
            KeyManager<HybridEncrypt> hybridEncryptKeyManager = hybridEncryptKeyManager(str);
            if (hybridEncryptKeyManager.getVersion() >= i) {
                return hybridEncryptKeyManager;
            }
            throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
        }
        throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
    }

    private KeyManager<HybridEncrypt> hybridEncryptKeyManager(String str) throws GeneralSecurityException {
        int i = (str.hashCode() == 396454335 && str.equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey")) ? 0 : -1;
        if (i == 0) {
            return new EciesAeadHkdfPublicKeyManager();
        }
        throw new GeneralSecurityException(String.format("No support for primitive 'HybridEncrypt' with key type '%s'.", new Object[]{str}));
    }
}
