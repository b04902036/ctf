package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import java.io.IOException;

public final class SharedPrefKeysetReader implements KeysetReader {
    private final String keysetName;
    private final SharedPreferences sharedPreferences;

    public SharedPrefKeysetReader(Context context, String str, String str2) throws IOException {
        if (str != null) {
            this.keysetName = str;
            context = context.getApplicationContext();
            if (str2 == null) {
                this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                return;
            } else {
                this.sharedPreferences = context.getSharedPreferences(str2, 0);
                return;
            }
        }
        throw new IllegalArgumentException("keysetName cannot be null");
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024 A:{Splitter: B:1:0x0002, ExcHandler: java.lang.ClassCastException (r2_4 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:8:0x0024, code:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:10:0x0036, code:
            throw new java.io.IOException(java.lang.String.format("can't read keyset; the pref value %s is not a valid hex string", new java.lang.Object[]{r6.keysetName}), r2);
     */
    private byte[] readPref() throws java.io.IOException {
        /*
        r6 = this;
        r0 = 0;
        r1 = 1;
        r2 = r6.sharedPreferences;	 Catch:{ ClassCastException -> 0x0024, ClassCastException -> 0x0024 }
        r3 = r6.keysetName;	 Catch:{ ClassCastException -> 0x0024, ClassCastException -> 0x0024 }
        r4 = 0;
        r2 = r2.getString(r3, r4);	 Catch:{ ClassCastException -> 0x0024, ClassCastException -> 0x0024 }
        if (r2 == 0) goto L_0x0012;
    L_0x000d:
        r2 = com.google.crypto.tink.subtle.Hex.decode(r2);	 Catch:{ ClassCastException -> 0x0024, ClassCastException -> 0x0024 }
        return r2;
    L_0x0012:
        r2 = new java.io.IOException;	 Catch:{ ClassCastException -> 0x0024, ClassCastException -> 0x0024 }
        r3 = "can't read keyset; the pref value %s does not exist";
        r4 = new java.lang.Object[r1];	 Catch:{ ClassCastException -> 0x0024, ClassCastException -> 0x0024 }
        r5 = r6.keysetName;	 Catch:{ ClassCastException -> 0x0024, ClassCastException -> 0x0024 }
        r4[r0] = r5;	 Catch:{ ClassCastException -> 0x0024, ClassCastException -> 0x0024 }
        r3 = java.lang.String.format(r3, r4);	 Catch:{ ClassCastException -> 0x0024, ClassCastException -> 0x0024 }
        r2.<init>(r3);	 Catch:{ ClassCastException -> 0x0024, ClassCastException -> 0x0024 }
        throw r2;	 Catch:{ ClassCastException -> 0x0024, ClassCastException -> 0x0024 }
    L_0x0024:
        r2 = move-exception;
        r3 = new java.io.IOException;
        r1 = new java.lang.Object[r1];
        r6 = r6.keysetName;
        r1[r0] = r6;
        r6 = "can't read keyset; the pref value %s is not a valid hex string";
        r6 = java.lang.String.format(r6, r1);
        r3.<init>(r6, r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.integration.android.SharedPrefKeysetReader.readPref():byte[]");
    }

    public Keyset read() throws IOException {
        return Keyset.parseFrom(readPref());
    }

    public EncryptedKeyset readEncrypted() throws IOException {
        return EncryptedKeyset.parseFrom(readPref());
    }
}
