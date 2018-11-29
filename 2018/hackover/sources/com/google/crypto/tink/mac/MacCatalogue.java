package com.google.crypto.tink.mac;

import com.google.crypto.tink.Catalogue;
import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.Mac;
import java.security.GeneralSecurityException;

class MacCatalogue implements Catalogue<Mac> {
    public KeyManager<Mac> getKeyManager(String str, String str2, int i) throws GeneralSecurityException {
        String toLowerCase = str2.toLowerCase();
        int i2 = (toLowerCase.hashCode() == 107855 && toLowerCase.equals("mac")) ? 0 : -1;
        if (i2 == 0) {
            KeyManager<Mac> macKeyManager = macKeyManager(str);
            if (macKeyManager.getVersion() >= i) {
                return macKeyManager;
            }
            throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
        }
        throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
    }

    private KeyManager<Mac> macKeyManager(String str) throws GeneralSecurityException {
        int i = (str.hashCode() == 836622442 && str.equals("type.googleapis.com/google.crypto.tink.HmacKey")) ? 0 : -1;
        if (i == 0) {
            return new HmacKeyManager();
        }
        throw new GeneralSecurityException(String.format("No support for primitive 'Mac' with key type '%s'.", new Object[]{str}));
    }
}
