package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.GuardedBy;

final class InputStreamDecrypter extends InputStream {
    byte[] associatedData;
    @GuardedBy("this")
    boolean attemptedMatching = false;
    @GuardedBy("this")
    InputStream ciphertextStream;
    @GuardedBy("this")
    InputStream matchingStream = null;
    PrimitiveSet<StreamingAead> primitives;

    public boolean markSupported() {
        return false;
    }

    public InputStreamDecrypter(PrimitiveSet<StreamingAead> primitiveSet, InputStream inputStream, byte[] bArr) {
        this.primitives = primitiveSet;
        if (inputStream.markSupported()) {
            this.ciphertextStream = inputStream;
        } else {
            this.ciphertextStream = new BufferedInputStream(inputStream);
        }
        this.ciphertextStream.mark(Integer.MAX_VALUE);
        this.associatedData = (byte[]) bArr.clone();
    }

    @GuardedBy("this")
    private void rewind() throws IOException {
        this.ciphertextStream.reset();
    }

    @GuardedBy("this")
    private void disableRewinding() throws IOException {
        this.ciphertextStream.mark(0);
    }

    @GuardedBy("this")
    public synchronized int available() throws IOException {
        if (this.matchingStream == null) {
            return 0;
        }
        return this.matchingStream.available();
    }

    @GuardedBy("this")
    public synchronized int read() throws IOException {
        byte[] bArr = new byte[1];
        if (read(bArr) != 1) {
            return -1;
        }
        return bArr[0];
    }

    @GuardedBy("this")
    public synchronized int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    /* JADX WARNING: Missing block: B:28:0x004f, code:
            return r3;
     */
    @javax.annotation.concurrent.GuardedBy("this")
    public synchronized int read(byte[] r6, int r7, int r8) throws java.io.IOException {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = 0;
        if (r8 != 0) goto L_0x0006;
    L_0x0004:
        monitor-exit(r5);
        return r0;
    L_0x0006:
        r1 = r5.matchingStream;	 Catch:{ all -> 0x0071 }
        if (r1 == 0) goto L_0x0012;
    L_0x000a:
        r0 = r5.matchingStream;	 Catch:{ all -> 0x0071 }
        r6 = r0.read(r6, r7, r8);	 Catch:{ all -> 0x0071 }
        monitor-exit(r5);
        return r6;
    L_0x0012:
        r1 = r5.attemptedMatching;	 Catch:{ all -> 0x0071 }
        if (r1 != 0) goto L_0x0069;
    L_0x0016:
        r1 = 1;
        r5.attemptedMatching = r1;	 Catch:{ all -> 0x0071 }
        r1 = r5.primitives;	 Catch:{ GeneralSecurityException -> 0x0060 }
        r1 = r1.getRawPrimitives();	 Catch:{ GeneralSecurityException -> 0x0060 }
        r1 = r1.iterator();	 Catch:{ all -> 0x0071 }
    L_0x0023:
        r2 = r1.hasNext();	 Catch:{ all -> 0x0071 }
        if (r2 == 0) goto L_0x0058;
    L_0x0029:
        r2 = r1.next();	 Catch:{ all -> 0x0071 }
        r2 = (com.google.crypto.tink.PrimitiveSet.Entry) r2;	 Catch:{ all -> 0x0071 }
        r2 = r2.getPrimitive();	 Catch:{ IOException -> 0x0054, GeneralSecurityException -> 0x0050 }
        r2 = (com.google.crypto.tink.StreamingAead) r2;	 Catch:{ IOException -> 0x0054, GeneralSecurityException -> 0x0050 }
        r3 = r5.ciphertextStream;	 Catch:{ IOException -> 0x0054, GeneralSecurityException -> 0x0050 }
        r4 = r5.associatedData;	 Catch:{ IOException -> 0x0054, GeneralSecurityException -> 0x0050 }
        r2 = r2.newDecryptingStream(r3, r4);	 Catch:{ IOException -> 0x0054, GeneralSecurityException -> 0x0050 }
        r3 = r2.read(r6, r7, r8);	 Catch:{ IOException -> 0x0054, GeneralSecurityException -> 0x0050 }
        if (r3 != 0) goto L_0x0049;
    L_0x0043:
        r5.rewind();	 Catch:{ IOException -> 0x0054, GeneralSecurityException -> 0x0050 }
        r5.attemptedMatching = r0;	 Catch:{ IOException -> 0x0054, GeneralSecurityException -> 0x0050 }
        goto L_0x004e;
    L_0x0049:
        r5.matchingStream = r2;	 Catch:{ IOException -> 0x0054, GeneralSecurityException -> 0x0050 }
        r5.disableRewinding();	 Catch:{ IOException -> 0x0054, GeneralSecurityException -> 0x0050 }
    L_0x004e:
        monitor-exit(r5);
        return r3;
    L_0x0050:
        r5.rewind();	 Catch:{ all -> 0x0071 }
        goto L_0x0023;
    L_0x0054:
        r5.rewind();	 Catch:{ all -> 0x0071 }
        goto L_0x0023;
    L_0x0058:
        r6 = new java.io.IOException;	 Catch:{ all -> 0x0071 }
        r7 = "No matching key found for the ciphertext in the stream.";
        r6.<init>(r7);	 Catch:{ all -> 0x0071 }
        throw r6;	 Catch:{ all -> 0x0071 }
    L_0x0060:
        r6 = move-exception;
        r7 = new java.io.IOException;	 Catch:{ all -> 0x0071 }
        r8 = "Keyset failure: ";
        r7.<init>(r8, r6);	 Catch:{ all -> 0x0071 }
        throw r7;	 Catch:{ all -> 0x0071 }
    L_0x0069:
        r6 = new java.io.IOException;	 Catch:{ all -> 0x0071 }
        r7 = "No matching key found for the ciphertext in the stream.";
        r6.<init>(r7);	 Catch:{ all -> 0x0071 }
        throw r6;	 Catch:{ all -> 0x0071 }
    L_0x0071:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.streamingaead.InputStreamDecrypter.read(byte[], int, int):int");
    }

    @GuardedBy("this")
    public synchronized void close() throws IOException {
        this.ciphertextStream.close();
    }
}
