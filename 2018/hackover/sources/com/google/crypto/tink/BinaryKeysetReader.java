package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class BinaryKeysetReader implements KeysetReader {
    private final InputStream inputStream;

    public static KeysetReader withInputStream(InputStream inputStream) {
        return new BinaryKeysetReader(inputStream);
    }

    public static KeysetReader withBytes(byte[] bArr) {
        return new BinaryKeysetReader(new ByteArrayInputStream(bArr));
    }

    public static KeysetReader withFile(File file) throws IOException {
        return new BinaryKeysetReader(new FileInputStream(file));
    }

    private BinaryKeysetReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Keyset read() throws IOException {
        return Keyset.parseFrom(this.inputStream);
    }

    public EncryptedKeyset readEncrypted() throws IOException {
        return EncryptedKeyset.parseFrom(this.inputStream);
    }
}
