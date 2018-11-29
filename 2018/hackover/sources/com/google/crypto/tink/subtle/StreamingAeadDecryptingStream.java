package com.google.crypto.tink.subtle;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;

class StreamingAeadDecryptingStream extends FilterInputStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private byte[] aad;
    private ByteBuffer ciphertextSegment;
    private final int ciphertextSegmentSize;
    private final StreamSegmentDecrypter decrypter;
    private boolean definedState;
    private boolean endOfCiphertext;
    private boolean endOfPlaintext;
    private final int firstCiphertextSegmentSize;
    private int headerLength;
    private boolean headerRead = false;
    private ByteBuffer plaintextSegment;
    private int segmentNr;

    public boolean markSupported() {
        return false;
    }

    public StreamingAeadDecryptingStream(NonceBasedStreamingAead nonceBasedStreamingAead, InputStream inputStream, byte[] bArr) throws GeneralSecurityException, IOException {
        super(inputStream);
        this.decrypter = nonceBasedStreamingAead.newStreamSegmentDecrypter();
        this.headerLength = nonceBasedStreamingAead.getHeaderLength();
        this.aad = Arrays.copyOf(bArr, bArr.length);
        this.ciphertextSegmentSize = nonceBasedStreamingAead.getCiphertextSegmentSize();
        this.ciphertextSegment = ByteBuffer.allocate(this.ciphertextSegmentSize + 1);
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

    private void readHeader() throws IOException {
        byte[] bArr = new byte[this.headerLength];
        if (this.in.read(bArr) == this.headerLength) {
            try {
                this.decrypter.init(ByteBuffer.wrap(bArr), this.aad);
                this.headerRead = true;
                return;
            } catch (Throwable e) {
                throw new IOException(e);
            }
        }
        setUndefinedState();
        throw new IOException("Ciphertext is too short");
    }

    private void setUndefinedState() {
        this.definedState = false;
        this.plaintextSegment.limit(0);
    }

    private void loadSegment() throws IOException {
        while (!this.endOfCiphertext && this.ciphertextSegment.remaining() > 0) {
            int read = this.in.read(this.ciphertextSegment.array(), this.ciphertextSegment.position(), this.ciphertextSegment.remaining());
            if (read > 0) {
                this.ciphertextSegment.position(this.ciphertextSegment.position() + read);
            } else if (read == -1) {
                this.endOfCiphertext = true;
            } else if (read == 0) {
                throw new IOException("Could not read bytes from the ciphertext stream");
            }
        }
        byte b = (byte) 0;
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

    public int read() throws IOException {
        byte[] bArr = new byte[1];
        int read = read(bArr, 0, 1);
        if (read == 1) {
            return bArr[0] & 255;
        }
        if (read == -1) {
            return read;
        }
        throw new IOException("Reading failed");
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    /* JADX WARNING: Missing block: B:28:0x0054, code:
            return r0;
     */
    public synchronized int read(byte[] r7, int r8, int r9) throws java.io.IOException {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.definedState;	 Catch:{ all -> 0x005d }
        if (r0 == 0) goto L_0x0055;
    L_0x0005:
        r0 = r6.headerRead;	 Catch:{ all -> 0x005d }
        r1 = 1;
        if (r0 != 0) goto L_0x001a;
    L_0x000a:
        r6.readHeader();	 Catch:{ all -> 0x005d }
        r0 = r6.ciphertextSegment;	 Catch:{ all -> 0x005d }
        r0.clear();	 Catch:{ all -> 0x005d }
        r0 = r6.ciphertextSegment;	 Catch:{ all -> 0x005d }
        r2 = r6.firstCiphertextSegmentSize;	 Catch:{ all -> 0x005d }
        r2 = r2 + r1;
        r0.limit(r2);	 Catch:{ all -> 0x005d }
    L_0x001a:
        r0 = r6.endOfPlaintext;	 Catch:{ all -> 0x005d }
        r2 = -1;
        if (r0 == 0) goto L_0x0021;
    L_0x001f:
        monitor-exit(r6);
        return r2;
    L_0x0021:
        r0 = 0;
    L_0x0022:
        if (r0 >= r9) goto L_0x004b;
    L_0x0024:
        r3 = r6.plaintextSegment;	 Catch:{ all -> 0x005d }
        r3 = r3.remaining();	 Catch:{ all -> 0x005d }
        if (r3 != 0) goto L_0x0036;
    L_0x002c:
        r3 = r6.endOfCiphertext;	 Catch:{ all -> 0x005d }
        if (r3 == 0) goto L_0x0033;
    L_0x0030:
        r6.endOfPlaintext = r1;	 Catch:{ all -> 0x005d }
        goto L_0x004b;
    L_0x0033:
        r6.loadSegment();	 Catch:{ all -> 0x005d }
    L_0x0036:
        r3 = r6.plaintextSegment;	 Catch:{ all -> 0x005d }
        r3 = r3.remaining();	 Catch:{ all -> 0x005d }
        r4 = r9 - r0;
        r3 = java.lang.Math.min(r3, r4);	 Catch:{ all -> 0x005d }
        r4 = r6.plaintextSegment;	 Catch:{ all -> 0x005d }
        r5 = r0 + r8;
        r4.get(r7, r5, r3);	 Catch:{ all -> 0x005d }
        r0 = r0 + r3;
        goto L_0x0022;
    L_0x004b:
        if (r0 != 0) goto L_0x0053;
    L_0x004d:
        r7 = r6.endOfPlaintext;	 Catch:{ all -> 0x005d }
        if (r7 == 0) goto L_0x0053;
    L_0x0051:
        monitor-exit(r6);
        return r2;
    L_0x0053:
        monitor-exit(r6);
        return r0;
    L_0x0055:
        r7 = new java.io.IOException;	 Catch:{ all -> 0x005d }
        r8 = "This StreamingAeadDecryptingStream is in an undefined state";
        r7.<init>(r8);	 Catch:{ all -> 0x005d }
        throw r7;	 Catch:{ all -> 0x005d }
    L_0x005d:
        r7 = move-exception;
        monitor-exit(r6);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.StreamingAeadDecryptingStream.read(byte[], int, int):int");
    }

    public synchronized void close() throws IOException {
        super.close();
    }

    public synchronized int available() {
        return this.plaintextSegment.remaining();
    }

    public synchronized void mark(int i) {
    }

    public synchronized String toString() {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        stringBuilder.append("StreamingAeadDecryptingStream");
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
