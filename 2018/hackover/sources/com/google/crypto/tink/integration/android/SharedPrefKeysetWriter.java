package com.google.crypto.tink.integration.android;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.google.crypto.tink.KeysetWriter;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.subtle.Hex;
import java.io.IOException;

public final class SharedPrefKeysetWriter implements KeysetWriter {
    private final Editor editor;
    private final String keysetName;

    public SharedPrefKeysetWriter(Context context, String str, String str2) {
        if (str != null) {
            this.keysetName = str;
            context = context.getApplicationContext();
            if (str2 == null) {
                this.editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
                return;
            } else {
                this.editor = context.getSharedPreferences(str2, 0).edit();
                return;
            }
        }
        throw new IllegalArgumentException("keysetName cannot be null");
    }

    public void write(Keyset keyset) throws IOException {
        this.editor.putString(this.keysetName, Hex.encode(keyset.toByteArray())).apply();
    }

    public void write(EncryptedKeyset encryptedKeyset) throws IOException {
        this.editor.putString(this.keysetName, Hex.encode(encryptedKeyset.toByteArray())).apply();
    }
}
