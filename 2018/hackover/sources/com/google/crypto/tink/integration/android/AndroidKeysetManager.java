package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.KeysetManager;
import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.KeysetWriter;
import com.google.crypto.tink.proto.KeyTemplate;
import java.io.IOException;
import java.security.GeneralSecurityException;
import javax.annotation.concurrent.GuardedBy;

public final class AndroidKeysetManager {
    private static final String TAG = "com.google.crypto.tink.integration.android.AndroidKeysetManager";
    private final KeyTemplate keyTemplate;
    @GuardedBy("this")
    private KeysetManager keysetManager;
    private final Aead masterKey;
    private final KeysetReader reader;
    private final boolean useKeystore;
    private final KeysetWriter writer;

    public static final class Builder {
        private KeyTemplate keyTemplate = null;
        private Aead masterKey = null;
        private KeysetReader reader = null;
        private boolean useKeystore = true;
        private KeysetWriter writer = null;

        public Builder withSharedPref(Context context, String str, String str2) throws IOException {
            if (context == null) {
                throw new IllegalArgumentException("need an Android context");
            } else if (str != null) {
                this.reader = new SharedPrefKeysetReader(context, str, str2);
                this.writer = new SharedPrefKeysetWriter(context, str, str2);
                return this;
            } else {
                throw new IllegalArgumentException("need a keyset name");
            }
        }

        public Builder withMasterKeyUri(String str) throws GeneralSecurityException, IOException {
            this.masterKey = AndroidKeystoreKmsClient.getOrGenerateNewAeadKey(str);
            return this;
        }

        public Builder withKeyTemplate(KeyTemplate keyTemplate) {
            this.keyTemplate = keyTemplate;
            return this;
        }

        public Builder doNotUseKeystore() {
            this.useKeystore = false;
            return this;
        }

        public AndroidKeysetManager build() throws GeneralSecurityException, IOException {
            return new AndroidKeysetManager(this);
        }
    }

    private AndroidKeysetManager(Builder builder) throws GeneralSecurityException, IOException {
        this.reader = builder.reader;
        if (this.reader != null) {
            this.writer = builder.writer;
            if (this.writer != null) {
                this.useKeystore = builder.useKeystore;
                this.masterKey = builder.masterKey;
                if (this.useKeystore && this.masterKey == null) {
                    throw new IllegalArgumentException("need a master key URI, please set it with Builder#masterKeyUri");
                }
                this.keyTemplate = builder.keyTemplate;
                this.keysetManager = readOrGenerateNewKeyset();
                return;
            }
            throw new IllegalArgumentException("need to specify where to write the keyset to with Builder#withSharedPref");
        }
        throw new IllegalArgumentException("need to specify where to read the keyset from with Builder#withSharedPref");
    }

    @GuardedBy("this")
    public synchronized KeysetHandle getKeysetHandle() throws GeneralSecurityException {
        return this.keysetManager.getKeysetHandle();
    }

    @GuardedBy("this")
    public synchronized AndroidKeysetManager rotate(KeyTemplate keyTemplate) throws GeneralSecurityException {
        this.keysetManager = this.keysetManager.rotate(keyTemplate);
        write(this.keysetManager);
        return this;
    }

    @GuardedBy("this")
    public synchronized AndroidKeysetManager add(KeyTemplate keyTemplate) throws GeneralSecurityException {
        this.keysetManager = this.keysetManager.add(keyTemplate);
        write(this.keysetManager);
        return this;
    }

    @GuardedBy("this")
    public synchronized AndroidKeysetManager setPrimary(int i) throws GeneralSecurityException {
        this.keysetManager = this.keysetManager.setPrimary(i);
        write(this.keysetManager);
        return this;
    }

    @GuardedBy("this")
    @Deprecated
    public synchronized AndroidKeysetManager promote(int i) throws GeneralSecurityException {
        return setPrimary(i);
    }

    @GuardedBy("this")
    public synchronized AndroidKeysetManager enable(int i) throws GeneralSecurityException {
        this.keysetManager = this.keysetManager.enable(i);
        write(this.keysetManager);
        return this;
    }

    @GuardedBy("this")
    public synchronized AndroidKeysetManager disable(int i) throws GeneralSecurityException {
        this.keysetManager = this.keysetManager.disable(i);
        write(this.keysetManager);
        return this;
    }

    @GuardedBy("this")
    public synchronized AndroidKeysetManager delete(int i) throws GeneralSecurityException {
        this.keysetManager = this.keysetManager.delete(i);
        write(this.keysetManager);
        return this;
    }

    @GuardedBy("this")
    public synchronized AndroidKeysetManager destroy(int i) throws GeneralSecurityException {
        this.keysetManager = this.keysetManager.destroy(i);
        write(this.keysetManager);
        return this;
    }

    private KeysetManager readOrGenerateNewKeyset() throws GeneralSecurityException, IOException {
        try {
            return read();
        } catch (IOException e) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("cannot read keyset: ");
            stringBuilder.append(e.toString());
            Log.i(str, stringBuilder.toString());
            if (this.keyTemplate != null) {
                KeysetManager rotate = KeysetManager.withEmptyKeyset().rotate(this.keyTemplate);
                write(rotate);
                return rotate;
            }
            throw new GeneralSecurityException("cannot obtain keyset handle");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x0013 A:{Splitter: B:2:0x0006, ExcHandler: com.google.protobuf.InvalidProtocolBufferException (r0_4 'e' java.lang.Exception)} */
    /* JADX WARNING: Missing block: B:5:0x0013, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:6:0x0014, code:
            r1 = TAG;
            r2 = new java.lang.StringBuilder();
            r2.append("cannot decrypt keyset: ");
            r2.append(r0.toString());
            android.util.Log.i(r1, r2.toString());
     */
    private com.google.crypto.tink.KeysetManager read() throws java.security.GeneralSecurityException, java.io.IOException {
        /*
        r4 = this;
        r0 = r4.shouldUseKeystore();
        if (r0 == 0) goto L_0x002e;
    L_0x0006:
        r0 = r4.reader;	 Catch:{ InvalidProtocolBufferException -> 0x0013, InvalidProtocolBufferException -> 0x0013 }
        r1 = r4.masterKey;	 Catch:{ InvalidProtocolBufferException -> 0x0013, InvalidProtocolBufferException -> 0x0013 }
        r0 = com.google.crypto.tink.KeysetHandle.read(r0, r1);	 Catch:{ InvalidProtocolBufferException -> 0x0013, InvalidProtocolBufferException -> 0x0013 }
        r0 = com.google.crypto.tink.KeysetManager.withKeysetHandle(r0);	 Catch:{ InvalidProtocolBufferException -> 0x0013, InvalidProtocolBufferException -> 0x0013 }
        return r0;
    L_0x0013:
        r0 = move-exception;
        r1 = TAG;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "cannot decrypt keyset: ";
        r2.append(r3);
        r0 = r0.toString();
        r2.append(r0);
        r0 = r2.toString();
        android.util.Log.i(r1, r0);
    L_0x002e:
        r0 = r4.reader;
        r0 = com.google.crypto.tink.CleartextKeysetHandle.read(r0);
        r1 = r4.shouldUseKeystore();
        if (r1 == 0) goto L_0x0041;
    L_0x003a:
        r1 = r4.writer;
        r4 = r4.masterKey;
        r0.write(r1, r4);
    L_0x0041:
        r4 = com.google.crypto.tink.KeysetManager.withKeysetHandle(r0);
        return r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.integration.android.AndroidKeysetManager.read():com.google.crypto.tink.KeysetManager");
    }

    private void write(KeysetManager keysetManager) throws GeneralSecurityException {
        try {
            if (shouldUseKeystore()) {
                keysetManager.getKeysetHandle().write(this.writer, this.masterKey);
            } else {
                CleartextKeysetHandle.write(keysetManager.getKeysetHandle(), this.writer);
            }
        } catch (Throwable e) {
            throw new GeneralSecurityException(e);
        }
    }

    private boolean shouldUseKeystore() {
        return this.useKeystore && VERSION.SDK_INT >= 23;
    }
}
