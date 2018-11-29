package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Arrays;

class StreamingAeadDecryptingChannel implements ReadableByteChannel {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private byte[] aad;
    private ReadableByteChannel ciphertextChannel;
    private ByteBuffer ciphertextSegment = ByteBuffer.allocate(this.ciphertextSegmentSize + 1);
    private final int ciphertextSegmentSize;
    private final StreamSegmentDecrypter decrypter;
    private boolean definedState;
    private boolean endOfCiphertext;
    private boolean endOfPlaintext;
    private final int firstCiphertextSegmentSize;
    private ByteBuffer header;
    private boolean headerRead;
    private ByteBuffer plaintextSegment;
    private int segmentNr;

    public StreamingAeadDecryptingChannel(NonceBasedStreamingAead nonceBasedStreamingAead, ReadableByteChannel readableByteChannel, byte[] bArr) throws GeneralSecurityException, IOException {
        this.decrypter = nonceBasedStreamingAead.newStreamSegmentDecrypter();
        this.ciphertextChannel = readableByteChannel;
        this.header = ByteBuffer.allocate(nonceBasedStreamingAead.getHeaderLength());
        this.aad = Arrays.copyOf(bArr, bArr.length);
        this.ciphertextSegmentSize = nonceBasedStreamingAead.getCiphertextSegmentSize();
        this.ciphertextSegment.limit(0);
        this.firstCiphertextSegmentSize = this.ciphertextSegmentSize - nonceBasedStreamingAead.getCiphertextOffset();
        this.plaintextSegment = ByteBuffer.allocate(nonceBasedStreamingAead.getPlaintextSegmentSize() + 16);
        this.plaintextSegment.limit(0);
        this.headerRead = false;
        this.endOfCiphertext = false;
        this.endOfPlaintext = false;
        this.segmentNr = 0;
        this.definedState = true;
    }

    private void readSomeCiphertext(ByteBuffer byteBuffer) throws IOException {
        int read;
        do {
            read = this.ciphertextChannel.read(byteBuffer);
            if (read <= 0) {
                break;
            }
        } while (byteBuffer.remaining() > 0);
        if (read == -1) {
            this.endOfCiphertext = true;
        }
    }

    private boolean tryReadHeader() throws IOException {
        if (this.endOfCiphertext) {
            throw new IOException("Ciphertext is too short");
        }
        readSomeCiphertext(this.header);
        if (this.header.remaining() > 0) {
            return false;
        }
        this.header.flip();
        try {
            this.decrypter.init(this.header, this.aad);
            this.headerRead = true;
            return true;
        } catch (Throwable e) {
            setUndefinedState();
            throw new IOException(e);
        }
    }

    private void setUndefinedState() {
        this.definedState = false;
        this.plaintextSegment.limit(0);
    }

    private boolean tryLoadSegment() throws IOException {
        if (!this.endOfCiphertext) {
            readSomeCiphertext(this.ciphertextSegment);
        }
        byte b = (byte) 0;
        if (this.ciphertextSegment.remaining() > 0 && !this.endOfCiphertext) {
            return false;
        }
        if (!this.endOfCiphertext) {
            b = this.ciphertextSegment.get(this.ciphertextSegment.position() - 1);
            this.ciphertextSegment.position(this.ciphertextSegment.position() - 1);
        }
        this.ciphertextSegment.flip();
        this.plaintextSegment.clear();
        try {
            this.decrypter.decryptSegment(this.ciphertextSegment, this.segmentNr, this.endOfCiphertext, this.plaintextSegment);
            this.segmentNr++;
            this.plaintextSegment.flip();
            this.ciphertextSegment.clear();
            if (!this.endOfCiphertext) {
                this.ciphertextSegment.clear();
                this.ciphertextSegment.limit(this.ciphertextSegmentSize + 1);
                this.ciphertextSegment.put(b);
            }
            return true;
        } catch (Throwable e) {
            setUndefinedState();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(e.getMessage());
            stringBuilder.append("\n");
            stringBuilder.append(toString());
            stringBuilder.append("\nsegmentNr:");
            stringBuilder.append(this.segmentNr);
            stringBuilder.append(" endOfCiphertext:");
            stringBuilder.append(this.endOfCiphertext);
            throw new IOException(stringBuilder.toString(), e);
        }
    }

    /* JADX WARNING: Missing block: B:40:0x008e, code:
            return r7;
     */
    public synchronized int read(java.nio.ByteBuffer r7) throws java.io.IOException {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.definedState;	 Catch:{ all -> 0x0097 }
        if (r0 == 0) goto L_0x008f;
    L_0x0005:
        r0 = r6.headerRead;	 Catch:{ all -> 0x0097 }
        r1 = 1;
        if (r0 != 0) goto L_0x0020;
    L_0x000a:
        r0 = r6.tryReadHeader();	 Catch:{ all -> 0x0097 }
        if (r0 != 0) goto L_0x0013;
    L_0x0010:
        r7 = 0;
        monitor-exit(r6);
        return r7;
    L_0x0013:
        r0 = r6.ciphertextSegment;	 Catch:{ all -> 0x0097 }
        r0.clear();	 Catch:{ all -> 0x0097 }
        r0 = r6.ciphertextSegment;	 Catch:{ all -> 0x0097 }
        r2 = r6.firstCiphertextSegmentSize;	 Catch:{ all -> 0x0097 }
        r2 = r2 + r1;
        r0.limit(r2);	 Catch:{ all -> 0x0097 }
    L_0x0020:
        r0 = r6.endOfPlaintext;	 Catch:{ all -> 0x0097 }
        r2 = -1;
        if (r0 == 0) goto L_0x0027;
    L_0x0025:
        monitor-exit(r6);
        return r2;
    L_0x0027:
        r0 = r7.position();	 Catch:{ all -> 0x0097 }
    L_0x002b:
        r3 = r7.remaining();	 Catch:{ all -> 0x0097 }
        if (r3 <= 0) goto L_0x0080;
    L_0x0031:
        r3 = r6.plaintextSegment;	 Catch:{ all -> 0x0097 }
        r3 = r3.remaining();	 Catch:{ all -> 0x0097 }
        if (r3 != 0) goto L_0x0047;
    L_0x0039:
        r3 = r6.endOfCiphertext;	 Catch:{ all -> 0x0097 }
        if (r3 == 0) goto L_0x0040;
    L_0x003d:
        r6.endOfPlaintext = r1;	 Catch:{ all -> 0x0097 }
        goto L_0x0080;
    L_0x0040:
        r3 = r6.tryLoadSegment();	 Catch:{ all -> 0x0097 }
        if (r3 != 0) goto L_0x0047;
    L_0x0046:
        goto L_0x0080;
    L_0x0047:
        r3 = r6.plaintextSegment;	 Catch:{ all -> 0x0097 }
        r3 = r3.remaining();	 Catch:{ all -> 0x0097 }
        r4 = r7.remaining();	 Catch:{ all -> 0x0097 }
        if (r3 > r4) goto L_0x005e;
    L_0x0053:
        r3 = r6.plaintextSegment;	 Catch:{ all -> 0x0097 }
        r3.remaining();	 Catch:{ all -> 0x0097 }
        r3 = r6.plaintextSegment;	 Catch:{ all -> 0x0097 }
        r7.put(r3);	 Catch:{ all -> 0x0097 }
        goto L_0x002b;
    L_0x005e:
        r3 = r7.remaining();	 Catch:{ all -> 0x0097 }
        r4 = r6.plaintextSegment;	 Catch:{ all -> 0x0097 }
        r4 = r4.duplicate();	 Catch:{ all -> 0x0097 }
        r5 = r4.position();	 Catch:{ all -> 0x0097 }
        r5 = r5 + r3;
        r4.limit(r5);	 Catch:{ all -> 0x0097 }
        r7.put(r4);	 Catch:{ all -> 0x0097 }
        r4 = r6.plaintextSegment;	 Catch:{ all -> 0x0097 }
        r5 = r6.plaintextSegment;	 Catch:{ all -> 0x0097 }
        r5 = r5.position();	 Catch:{ all -> 0x0097 }
        r5 = r5 + r3;
        r4.position(r5);	 Catch:{ all -> 0x0097 }
        goto L_0x002b;
    L_0x0080:
        r7 = r7.position();	 Catch:{ all -> 0x0097 }
        r7 = r7 - r0;
        if (r7 != 0) goto L_0x008d;
    L_0x0087:
        r0 = r6.endOfPlaintext;	 Catch:{ all -> 0x0097 }
        if (r0 == 0) goto L_0x008d;
    L_0x008b:
        monitor-exit(r6);
        return r2;
    L_0x008d:
        monitor-exit(r6);
        return r7;
    L_0x008f:
        r7 = new java.io.IOException;	 Catch:{ all -> 0x0097 }
        r0 = "This StreamingAeadDecryptingChannel is in an undefined state";
        r7.<init>(r0);	 Catch:{ all -> 0x0097 }
        throw r7;	 Catch:{ all -> 0x0097 }
    L_0x0097:
        r7 = move-exception;
        monitor-exit(r6);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.StreamingAeadDecryptingChannel.read(java.nio.ByteBuffer):int");
    }

    public synchronized void close() throws IOException {
        this.ciphertextChannel.close();
    }

    public synchronized boolean isOpen() {
        return this.ciphertextChannel.isOpen();
    }

    public synchronized String toString() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        stringBuilder.append("StreamingAeadDecryptingChannel");
        stringBuilder.append("\nsegmentNr:");
        stringBuilder.append(this.segmentNr);
        stringBuilder.append("\nciphertextSegmentSize:");
        stringBuilder.append(this.ciphertextSegmentSize);
        stringBuilder.append("\nheaderRead:");
        stringBuilder.append(this.headerRead);
        stringBuilder.append("\nendOfCiphertext:");
        stringBuilder.append(this.endOfCiphertext);
        stringBuilder.append("\nendOfPlaintext:");
        stringBuilder.append(this.endOfPlaintext);
        stringBuilder.append("\ndefinedState:");
        stringBuilder.append(this.definedState);
        stringBuilder.append("\nHeader");
        stringBuilder.append(" position:");
        stringBuilder.append(this.header.position());
        stringBuilder.append(" limit:");
        stringBuilder.append(this.header.position());
        stringBuilder.append("\nciphertextSgement");
        stringBuilder.append(" position:");
        stringBuilder.append(this.ciphertextSegment.position());
        stringBuilder.append(" limit:");
        stringBuilder.append(this.ciphertextSegment.limit());
        stringBuilder.append("\nplaintextSegment");
        stringBuilder.append(" position:");
        stringBuilder.append(this.plaintextSegment.position());
        stringBuilder.append(" limit:");
        stringBuilder.append(this.plaintextSegment.limit());
        return stringBuilder.toString();
    }
}
