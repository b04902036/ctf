package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.Keyset.Builder;
import com.google.crypto.tink.proto.Keyset.Key;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.annotation.concurrent.GuardedBy;

public final class KeysetManager {
    @GuardedBy("this")
    private final Builder keysetBuilder;

    private KeysetManager(Builder builder) {
        this.keysetBuilder = builder;
    }

    public static KeysetManager withKeysetHandle(KeysetHandle keysetHandle) {
        return new KeysetManager((Builder) keysetHandle.getKeyset().toBuilder());
    }

    public static KeysetManager withEmptyKeyset() {
        return new KeysetManager(Keyset.newBuilder());
    }

    @GuardedBy("this")
    public synchronized KeysetHandle getKeysetHandle() throws GeneralSecurityException {
        return KeysetHandle.fromKeyset((Keyset) this.keysetBuilder.build());
    }

    @GuardedBy("this")
    public synchronized KeysetManager rotate(KeyTemplate keyTemplate) throws GeneralSecurityException {
        Key newKey = newKey(keyTemplate);
        this.keysetBuilder.addKey(newKey).setPrimaryKeyId(newKey.getKeyId());
        return this;
    }

    @GuardedBy("this")
    public synchronized KeysetManager add(KeyTemplate keyTemplate) throws GeneralSecurityException {
        this.keysetBuilder.addKey(newKey(keyTemplate));
        return this;
    }

    @GuardedBy("this")
    public synchronized KeysetManager setPrimary(int i) throws GeneralSecurityException {
        StringBuilder stringBuilder;
        int i2 = 0;
        while (i2 < this.keysetBuilder.getKeyCount()) {
            Key key = this.keysetBuilder.getKey(i2);
            if (key.getKeyId() != i) {
                i2++;
            } else if (key.getStatus().equals(KeyStatusType.ENABLED)) {
                this.keysetBuilder.setPrimaryKeyId(i);
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("cannot set key as primary because it's not enabled: ");
                stringBuilder.append(i);
                throw new GeneralSecurityException(stringBuilder.toString());
            }
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("key not found: ");
        stringBuilder.append(i);
        throw new GeneralSecurityException(stringBuilder.toString());
        return this;
    }

    @GuardedBy("this")
    @Deprecated
    public synchronized KeysetManager promote(int i) throws GeneralSecurityException {
        return setPrimary(i);
    }

    @GuardedBy("this")
    public synchronized KeysetManager enable(int i) throws GeneralSecurityException {
        int i2 = 0;
        while (i2 < this.keysetBuilder.getKeyCount()) {
            Key key = this.keysetBuilder.getKey(i2);
            if (key.getKeyId() != i) {
                i2++;
            } else if (key.getStatus() == KeyStatusType.ENABLED || key.getStatus() == KeyStatusType.DISABLED) {
                this.keysetBuilder.setKey(i2, (Key) ((Key.Builder) key.toBuilder()).setStatus(KeyStatusType.ENABLED).build());
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("cannot enable key with id ");
                stringBuilder.append(i);
                stringBuilder.append(" and status ");
                stringBuilder.append(key.getStatus());
                throw new GeneralSecurityException(stringBuilder.toString());
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("key not found: ");
        stringBuilder2.append(i);
        throw new GeneralSecurityException(stringBuilder2.toString());
        return this;
    }

    @GuardedBy("this")
    public synchronized KeysetManager disable(int i) throws GeneralSecurityException {
        if (i != this.keysetBuilder.getPrimaryKeyId()) {
            int i2 = 0;
            while (i2 < this.keysetBuilder.getKeyCount()) {
                Key key = this.keysetBuilder.getKey(i2);
                if (key.getKeyId() != i) {
                    i2++;
                } else if (key.getStatus() == KeyStatusType.ENABLED || key.getStatus() == KeyStatusType.DISABLED) {
                    this.keysetBuilder.setKey(i2, (Key) ((Key.Builder) key.toBuilder()).setStatus(KeyStatusType.DISABLED).build());
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("cannot disable key with id ");
                    stringBuilder.append(i);
                    stringBuilder.append(" and status ");
                    stringBuilder.append(key.getStatus());
                    throw new GeneralSecurityException(stringBuilder.toString());
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("key not found: ");
            stringBuilder2.append(i);
            throw new GeneralSecurityException(stringBuilder2.toString());
        }
        throw new GeneralSecurityException("cannot disable the primary key");
        return this;
    }

    @GuardedBy("this")
    public synchronized KeysetManager delete(int i) throws GeneralSecurityException {
        if (i != this.keysetBuilder.getPrimaryKeyId()) {
            int i2 = 0;
            while (i2 < this.keysetBuilder.getKeyCount()) {
                if (this.keysetBuilder.getKey(i2).getKeyId() == i) {
                    this.keysetBuilder.removeKey(i2);
                } else {
                    i2++;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("key not found: ");
            stringBuilder.append(i);
            throw new GeneralSecurityException(stringBuilder.toString());
        }
        throw new GeneralSecurityException("cannot delete the primary key");
        return this;
    }

    @GuardedBy("this")
    public synchronized KeysetManager destroy(int i) throws GeneralSecurityException {
        if (i != this.keysetBuilder.getPrimaryKeyId()) {
            int i2 = 0;
            while (i2 < this.keysetBuilder.getKeyCount()) {
                Key key = this.keysetBuilder.getKey(i2);
                if (key.getKeyId() != i) {
                    i2++;
                } else if (key.getStatus() == KeyStatusType.ENABLED || key.getStatus() == KeyStatusType.DISABLED || key.getStatus() == KeyStatusType.DESTROYED) {
                    this.keysetBuilder.setKey(i2, (Key) ((Key.Builder) key.toBuilder()).setStatus(KeyStatusType.DESTROYED).clearKeyData().build());
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("cannot destroy key with id ");
                    stringBuilder.append(i);
                    stringBuilder.append(" and status ");
                    stringBuilder.append(key.getStatus());
                    throw new GeneralSecurityException(stringBuilder.toString());
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("key not found: ");
            stringBuilder2.append(i);
            throw new GeneralSecurityException(stringBuilder2.toString());
        }
        throw new GeneralSecurityException("cannot destroy the primary key");
        return this;
    }

    @GuardedBy("this")
    private synchronized Key newKey(KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyData newKeyData;
        int newKeyId;
        OutputPrefixType outputPrefixType;
        newKeyData = Registry.newKeyData(keyTemplate);
        newKeyId = newKeyId();
        outputPrefixType = keyTemplate.getOutputPrefixType();
        if (outputPrefixType == OutputPrefixType.UNKNOWN_PREFIX) {
            outputPrefixType = OutputPrefixType.TINK;
        }
        return (Key) Key.newBuilder().setKeyData(newKeyData).setKeyId(newKeyId).setStatus(KeyStatusType.ENABLED).setOutputPrefixType(outputPrefixType).build();
    }

    @GuardedBy("this")
    private synchronized int newKeyId() {
        int randPositiveInt;
        randPositiveInt = randPositiveInt();
        for (Key keyId : this.keysetBuilder.getKeyList()) {
            if (keyId.getKeyId() == randPositiveInt) {
                randPositiveInt = randPositiveInt();
            }
        }
        return randPositiveInt;
    }

    private static int randPositiveInt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[4];
        int i = 0;
        while (i == 0) {
            secureRandom.nextBytes(bArr);
            i = ((((bArr[0] & 127) << 24) | ((bArr[1] & 255) << 16)) | ((bArr[2] & 255) << 8)) | (bArr[3] & 255);
        }
        return i;
    }
}
