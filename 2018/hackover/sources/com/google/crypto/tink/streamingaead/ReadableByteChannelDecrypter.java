package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.subtle.RewindableReadableByteChannel;
import java.io.IOException;
import java.nio.channels.ReadableByteChannel;
import javax.annotation.concurrent.GuardedBy;

final class ReadableByteChannelDecrypter implements ReadableByteChannel {
    byte[] associatedData;
    @GuardedBy("this")
    boolean attemptedMatching = false;
    @GuardedBy("this")
    RewindableReadableByteChannel ciphertextChannel;
    @GuardedBy("this")
    ReadableByteChannel matchingChannel = null;
    PrimitiveSet<StreamingAead> primitives;

    public ReadableByteChannelDecrypter(PrimitiveSet<StreamingAead> primitiveSet, ReadableByteChannel readableByteChannel, byte[] bArr) {
        this.primitives = primitiveSet;
        this.ciphertextChannel = new RewindableReadableByteChannel(readableByteChannel);
        this.associatedData = (byte[]) bArr.clone();
    }

    /* JADX WARNING: Missing block: B:31:0x0059, code:
            return r3;
     */
    @javax.annotation.concurrent.GuardedBy("this")
    public synchronized int read(java.nio.ByteBuffer r6) throws java.io.IOException {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r6.remaining();	 Catch:{ all -> 0x007f }
        r1 = 0;
        if (r0 != 0) goto L_0x000a;
    L_0x0008:
        monitor-exit(r5);
        return r1;
    L_0x000a:
        r0 = r5.matchingChannel;	 Catch:{ all -> 0x007f }
        if (r0 == 0) goto L_0x0016;
    L_0x000e:
        r0 = r5.matchingChannel;	 Catch:{ all -> 0x007f }
        r6 = r0.read(r6);	 Catch:{ all -> 0x007f }
        monitor-exit(r5);
        return r6;
    L_0x0016:
        r0 = r5.attemptedMatching;	 Catch:{ all -> 0x007f }
        if (r0 != 0) goto L_0x0077;
    L_0x001a:
        r0 = 1;
        r5.attemptedMatching = r0;	 Catch:{ all -> 0x007f }
        r0 = r5.primitives;	 Catch:{ GeneralSecurityException -> 0x006e }
        r0 = r0.getRawPrimitives();	 Catch:{ GeneralSecurityException -> 0x006e }
        r0 = r0.iterator();	 Catch:{ all -> 0x007f }
    L_0x0027:
        r2 = r0.hasNext();	 Catch:{ all -> 0x007f }
        if (r2 == 0) goto L_0x0066;
    L_0x002d:
        r2 = r0.next();	 Catch:{ all -> 0x007f }
        r2 = (com.google.crypto.tink.PrimitiveSet.Entry) r2;	 Catch:{ all -> 0x007f }
        r2 = r2.getPrimitive();	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
        r2 = (com.google.crypto.tink.StreamingAead) r2;	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
        r3 = r5.ciphertextChannel;	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
        r4 = r5.associatedData;	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
        r2 = r2.newDecryptingChannel(r3, r4);	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
        r3 = r2.read(r6);	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
        if (r3 <= 0) goto L_0x004f;
    L_0x0047:
        r5.matchingChannel = r2;	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
        r2 = r5.ciphertextChannel;	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
        r2.disableRewinding();	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
        goto L_0x0058;
    L_0x004f:
        if (r3 != 0) goto L_0x0058;
    L_0x0051:
        r2 = r5.ciphertextChannel;	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
        r2.rewind();	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
        r5.attemptedMatching = r1;	 Catch:{ IOException -> 0x0060, GeneralSecurityException -> 0x005a }
    L_0x0058:
        monitor-exit(r5);
        return r3;
    L_0x005a:
        r2 = r5.ciphertextChannel;	 Catch:{ all -> 0x007f }
        r2.rewind();	 Catch:{ all -> 0x007f }
        goto L_0x0027;
    L_0x0060:
        r2 = r5.ciphertextChannel;	 Catch:{ all -> 0x007f }
        r2.rewind();	 Catch:{ all -> 0x007f }
        goto L_0x0027;
    L_0x0066:
        r6 = new java.io.IOException;	 Catch:{ all -> 0x007f }
        r0 = "No matching key found for the ciphertext in the stream.";
        r6.<init>(r0);	 Catch:{ all -> 0x007f }
        throw r6;	 Catch:{ all -> 0x007f }
    L_0x006e:
        r6 = move-exception;
        r0 = new java.io.IOException;	 Catch:{ all -> 0x007f }
        r1 = "Keyset failure: ";
        r0.<init>(r1, r6);	 Catch:{ all -> 0x007f }
        throw r0;	 Catch:{ all -> 0x007f }
    L_0x0077:
        r6 = new java.io.IOException;	 Catch:{ all -> 0x007f }
        r0 = "No matching key found for the ciphertext in the stream.";
        r6.<init>(r0);	 Catch:{ all -> 0x007f }
        throw r6;	 Catch:{ all -> 0x007f }
    L_0x007f:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.streamingaead.ReadableByteChannelDecrypter.read(java.nio.ByteBuffer):int");
    }

    @GuardedBy("this")
    public synchronized void close() throws IOException {
        this.ciphertextChannel.close();
    }

    @GuardedBy("this")
    public synchronized boolean isOpen() {
        return this.ciphertextChannel.isOpen();
    }
}
