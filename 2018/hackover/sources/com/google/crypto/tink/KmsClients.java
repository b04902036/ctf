package com.google.crypto.tink;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.CopyOnWriteArrayList;

public final class KmsClients {
    private static List<KmsClient> autoClients;
    private static final CopyOnWriteArrayList<KmsClient> clients = new CopyOnWriteArrayList();

    public static void add(KmsClient kmsClient) {
        clients.add(kmsClient);
    }

    public static KmsClient get(String str) throws GeneralSecurityException {
        Iterator it = clients.iterator();
        while (it.hasNext()) {
            KmsClient kmsClient = (KmsClient) it.next();
            if (kmsClient.doesSupport(str)) {
                return kmsClient;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("No KMS client does support: ");
        stringBuilder.append(str);
        throw new GeneralSecurityException(stringBuilder.toString());
    }

    public static synchronized KmsClient getAutoLoaded(String str) throws GeneralSecurityException {
        synchronized (KmsClients.class) {
            if (autoClients == null) {
                autoClients = loadAutoKmsClients();
            }
            for (KmsClient kmsClient : autoClients) {
                if (kmsClient.doesSupport(str)) {
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("No KMS client does support: ");
            stringBuilder.append(str);
            throw new GeneralSecurityException(stringBuilder.toString());
        }
        return kmsClient;
    }

    private static List<KmsClient> loadAutoKmsClients() {
        List arrayList = new ArrayList();
        Iterator it = ServiceLoader.load(KmsClient.class).iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
