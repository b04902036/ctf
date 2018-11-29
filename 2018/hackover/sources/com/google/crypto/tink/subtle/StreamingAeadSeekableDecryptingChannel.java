package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.security.GeneralSecurityException;
import java.util.Arrays;

class StreamingAeadSeekableDecryptingChannel implements SeekableByteChannel {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private final byte[] aad;
    private final SeekableByteChannel ciphertextChannel;
    private final long ciphertextChannelSize;
    private final int ciphertextOffset;
    private final ByteBuffer ciphertextSegment = ByteBuffer.allocate(this.ciphertextSegmentSize);
    private final int ciphertextSegmentSize;
    private int currentSegmentNr;
    private final StreamSegmentDecrypter decrypter;
    private final int firstSegmentOffset;
    private final ByteBuffer header;
    private boolean headerRead;
    private boolean isCurrentSegmentDecrypted;
    private boolean isopen;
    private final int lastCiphertextSegmentSize;
    private final int numberOfSegments;
    private long plaintextPosition;
    private final ByteBuffer plaintextSegment;
    private final int plaintextSegmentSize;
    private long plaintextSize;

    public StreamingAeadSeekableDecryptingChannel(NonceBasedStreamingAead nonceBasedStreamingAead, SeekableByteChannel seekableByteChannel, byte[] bArr) throws IOException, GeneralSecurityException {
        this.decrypter = nonceBasedStreamingAead.newStreamSegmentDecrypter();
        this.ciphertextChannel = seekableByteChannel;
        this.header = ByteBuffer.allocate(nonceBasedStreamingAead.getHeaderLength());
        this.ciphertextSegmentSize = nonceBasedStreamingAead.getCiphertextSegmentSize();
        this.plaintextSegmentSize = nonceBasedStreamingAead.getPlaintextSegmentSize();
        this.plaintextSegment = ByteBuffer.allocate(this.plaintextSegmentSize + 16);
        this.plaintextPosition = 0;
        this.headerRead = false;
        this.currentSegmentNr = -1;
        this.isCurrentSegmentDecrypted = false;
        this.ciphertextChannelSize = this.ciphertextChannel.size();
        this.aad = Arrays.copyOf(bArr, bArr.length);
        this.isopen = this.ciphertextChannel.isOpen();
        int i = (int) (this.ciphertextChannelSize / ((long) this.ciphertextSegmentSize));
        int i2 = (int) (this.ciphertextChannelSize % ((long) this.ciphertextSegmentSize));
        int ciphertextOverhead = nonceBasedStreamingAead.getCiphertextOverhead();
        if (i2 > 0) {
            this.numberOfSegments = i + 1;
            if (i2 >= ciphertextOverhead) {
                this.lastCiphertextSegmentSize = i2;
            } else {
                throw new IOException("Invalid ciphertext size");
            }
        }
        this.numberOfSegments = i;
        this.lastCiphertextSegmentSize = this.ciphertextSegmentSize;
        this.ciphertextOffset = nonceBasedStreamingAead.getCiphertextOffset();
        this.firstSegmentOffset = this.ciphertextOffset - nonceBasedStreamingAead.getHeaderLength();
        if (this.firstSegmentOffset >= 0) {
            long j = (((long) this.numberOfSegments) * ((long) ciphertextOverhead)) + ((long) this.ciphertextOffset);
            if (j <= this.ciphertextChannelSize) {
                this.plaintextSize = this.ciphertextChannelSize - j;
                return;
            }
            throw new IOException("Ciphertext is too short");
        }
        throw new IOException("Invalid ciphertext offset or header length");
    }

    public synchronized String toString() {
        StringBuilder stringBuilder;
        String stringBuilder2;
        stringBuilder = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("position:");
        stringBuilder3.append(this.ciphertextChannel.position());
        stringBuilder2 = stringBuilder3.toString();
        try {
        } catch (IOException unused) {
            stringBuilder2 = "position: n/a";
        }
        return stringBuilder.toString();
        stringBuilder.append("StreamingAeadSeekableDecryptingChannel");
        stringBuilder.append("\nciphertextChannel");
        stringBuilder.append(stringBuilder2);
        stringBuilder.append("\nciphertextChannelSize:");
        stringBuilder.append(this.ciphertextChannelSize);
        stringBuilder.append("\nplaintextSize:");
        stringBuilder.append(this.plaintextSize);
        stringBuilder.append("\nciphertextSegmentSize:");
        stringBuilder.append(this.ciphertextSegmentSize);
        stringBuilder.append("\nnumberOfSegments:");
        stringBuilder.append(this.numberOfSegments);
        stringBuilder.append("\nheaderRead:");
        stringBuilder.append(this.headerRead);
        stringBuilder.append("\nplaintextPosition:");
        stringBuilder.append(this.plaintextPosition);
        stringBuilder.append("\nHeader");
        stringBuilder.append(" position:");
        stringBuilder.append(this.header.position());
        stringBuilder.append(" limit:");
        stringBuilder.append(this.header.position());
        stringBuilder.append("\ncurrentSegmentNr:");
        stringBuilder.append(this.currentSegmentNr);
        stringBuilder.append("\nciphertextSgement");
        stringBuilder.append(" position:");
        stringBuilder.append(this.ciphertextSegment.position());
        stringBuilder.append(" limit:");
        stringBuilder.append(this.ciphertextSegment.limit());
        stringBuilder.append("\nisCurrentSegmentDecrypted:");
        stringBuilder.append(this.isCurrentSegmentDecrypted);
        stringBuilder.append("\nplaintextSegment");
        stringBuilder.append(" position:");
        stringBuilder.append(this.plaintextSegment.position());
        stringBuilder.append(" limit:");
        stringBuilder.append(this.plaintextSegment.limit());
        return stringBuilder.toString();
    }

    public synchronized long position() {
        return this.plaintextPosition;
    }

    public synchronized SeekableByteChannel position(long j) {
        this.plaintextPosition = j;
        return this;
    }

    private boolean tryReadHeader() throws IOException {
        this.ciphertextChannel.position((long) (this.header.position() + this.firstSegmentOffset));
        this.ciphertextChannel.read(this.header);
        if (this.header.remaining() > 0) {
            return false;
        }
        this.header.flip();
        try {
            this.decrypter.init(this.header, this.aad);
            this.headerRead = true;
            return true;
        } catch (Throwable e) {
            throw new IOException(e);
        }
    }

    private int getSegmentNr(long j) {
        return (int) ((j + ((long) this.ciphertextOffset)) / ((long) this.plaintextSegmentSize));
    }

    private boolean tryLoadSegment(int i) throws IOException {
        if (i < 0 || i >= this.numberOfSegments) {
            throw new IOException("Invalid position");
        }
        boolean z = i == this.numberOfSegments - 1;
        if (i != this.currentSegmentNr) {
            long j = ((long) i) * ((long) this.ciphertextSegmentSize);
            int i2 = this.ciphertextSegmentSize;
            if (z) {
                i2 = this.lastCiphertextSegmentSize;
            }
            if (i == 0) {
                i2 -= this.ciphertextOffset;
                j = (long) this.ciphertextOffset;
            }
            this.ciphertextChannel.position(j);
            this.ciphertextSegment.clear();
            this.ciphertextSegment.limit(i2);
            this.currentSegmentNr = i;
            this.isCurrentSegmentDecrypted = false;
        } else if (this.isCurrentSegmentDecrypted) {
            return true;
        }
        if (this.ciphertextSegment.remaining() > 0) {
            this.ciphertextChannel.read(this.ciphertextSegment);
        }
        if (this.ciphertextSegment.remaining() > 0) {
            return false;
        }
        this.ciphertextSegment.flip();
        this.plaintextSegment.clear();
        try {
            this.decrypter.decryptSegment(this.ciphertextSegment, i, z, this.plaintextSegment);
            this.plaintextSegment.flip();
            this.isCurrentSegmentDecrypted = true;
            return true;
        } catch (Throwable e) {
            this.currentSegmentNr = -1;
            throw new IOException("Failed to decrypt", e);
        }
    }

    private boolean reachedEnd() {
        if (this.isCurrentSegmentDecrypted && this.currentSegmentNr == this.numberOfSegments - 1 && this.plaintextSegment.remaining() == 0) {
            return true;
        }
        return false;
    }

    public synchronized int read(ByteBuffer byteBuffer, long j) throws IOException {
        int read;
        long position = position();
        try {
            position(j);
            read = read(byteBuffer);
        } finally {
            position(position);
        }
        return read;
    }

    /* JADX WARNING: Missing block: B:35:0x009d, code:
            return r7;
     */
    public synchronized int read(java.nio.ByteBuffer r7) throws java.io.IOException {
        /*
        r6 = this;
        monitor-enter(r6);
        r0 = r6.isopen;	 Catch:{ all -> 0x00a4 }
        if (r0 == 0) goto L_0x009e;
    L_0x0005:
        r0 = r6.headerRead;	 Catch:{ all -> 0x00a4 }
        if (r0 != 0) goto L_0x0012;
    L_0x0009:
        r0 = r6.tryReadHeader();	 Catch:{ all -> 0x00a4 }
        if (r0 != 0) goto L_0x0012;
    L_0x000f:
        r7 = 0;
        monitor-exit(r6);
        return r7;
    L_0x0012:
        r0 = r7.position();	 Catch:{ all -> 0x00a4 }
    L_0x0016:
        r1 = r7.remaining();	 Catch:{ all -> 0x00a4 }
        if (r1 <= 0) goto L_0x008c;
    L_0x001c:
        r1 = r6.plaintextPosition;	 Catch:{ all -> 0x00a4 }
        r3 = r6.plaintextSize;	 Catch:{ all -> 0x00a4 }
        r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r1 >= 0) goto L_0x008c;
    L_0x0024:
        r1 = r6.plaintextPosition;	 Catch:{ all -> 0x00a4 }
        r1 = r6.getSegmentNr(r1);	 Catch:{ all -> 0x00a4 }
        if (r1 != 0) goto L_0x0030;
    L_0x002c:
        r2 = r6.plaintextPosition;	 Catch:{ all -> 0x00a4 }
        r2 = (int) r2;	 Catch:{ all -> 0x00a4 }
        goto L_0x003b;
    L_0x0030:
        r2 = r6.plaintextPosition;	 Catch:{ all -> 0x00a4 }
        r4 = r6.ciphertextOffset;	 Catch:{ all -> 0x00a4 }
        r4 = (long) r4;	 Catch:{ all -> 0x00a4 }
        r2 = r2 + r4;
        r4 = r6.plaintextSegmentSize;	 Catch:{ all -> 0x00a4 }
        r4 = (long) r4;	 Catch:{ all -> 0x00a4 }
        r2 = r2 % r4;
        r2 = (int) r2;	 Catch:{ all -> 0x00a4 }
    L_0x003b:
        r1 = r6.tryLoadSegment(r1);	 Catch:{ all -> 0x00a4 }
        if (r1 == 0) goto L_0x008c;
    L_0x0041:
        r1 = r6.plaintextSegment;	 Catch:{ all -> 0x00a4 }
        r1.position(r2);	 Catch:{ all -> 0x00a4 }
        r1 = r6.plaintextSegment;	 Catch:{ all -> 0x00a4 }
        r1 = r1.remaining();	 Catch:{ all -> 0x00a4 }
        r2 = r7.remaining();	 Catch:{ all -> 0x00a4 }
        if (r1 > r2) goto L_0x0064;
    L_0x0052:
        r1 = r6.plaintextPosition;	 Catch:{ all -> 0x00a4 }
        r3 = r6.plaintextSegment;	 Catch:{ all -> 0x00a4 }
        r3 = r3.remaining();	 Catch:{ all -> 0x00a4 }
        r3 = (long) r3;	 Catch:{ all -> 0x00a4 }
        r1 = r1 + r3;
        r6.plaintextPosition = r1;	 Catch:{ all -> 0x00a4 }
        r1 = r6.plaintextSegment;	 Catch:{ all -> 0x00a4 }
        r7.put(r1);	 Catch:{ all -> 0x00a4 }
        goto L_0x0016;
    L_0x0064:
        r1 = r7.remaining();	 Catch:{ all -> 0x00a4 }
        r2 = r6.plaintextSegment;	 Catch:{ all -> 0x00a4 }
        r2 = r2.duplicate();	 Catch:{ all -> 0x00a4 }
        r3 = r2.position();	 Catch:{ all -> 0x00a4 }
        r3 = r3 + r1;
        r2.limit(r3);	 Catch:{ all -> 0x00a4 }
        r7.put(r2);	 Catch:{ all -> 0x00a4 }
        r2 = r6.plaintextPosition;	 Catch:{ all -> 0x00a4 }
        r4 = (long) r1;	 Catch:{ all -> 0x00a4 }
        r2 = r2 + r4;
        r6.plaintextPosition = r2;	 Catch:{ all -> 0x00a4 }
        r2 = r6.plaintextSegment;	 Catch:{ all -> 0x00a4 }
        r3 = r6.plaintextSegment;	 Catch:{ all -> 0x00a4 }
        r3 = r3.position();	 Catch:{ all -> 0x00a4 }
        r3 = r3 + r1;
        r2.position(r3);	 Catch:{ all -> 0x00a4 }
        goto L_0x0016;
    L_0x008c:
        r7 = r7.position();	 Catch:{ all -> 0x00a4 }
        r7 = r7 - r0;
        if (r7 != 0) goto L_0x009c;
    L_0x0093:
        r0 = r6.reachedEnd();	 Catch:{ all -> 0x00a4 }
        if (r0 == 0) goto L_0x009c;
    L_0x0099:
        r7 = -1;
        monitor-exit(r6);
        return r7;
    L_0x009c:
        monitor-exit(r6);
        return r7;
    L_0x009e:
        r7 = new java.nio.channels.ClosedChannelException;	 Catch:{ all -> 0x00a4 }
        r7.<init>();	 Catch:{ all -> 0x00a4 }
        throw r7;	 Catch:{ all -> 0x00a4 }
    L_0x00a4:
        r7 = move-exception;
        monitor-exit(r6);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.StreamingAeadSeekableDecryptingChannel.read(java.nio.ByteBuffer):int");
    }

    public long size() {
        return this.plaintextSize;
    }

    public synchronized long verifiedSize() throws IOException {
        if (tryLoadSegment(this.numberOfSegments - 1)) {
        } else {
            throw new IOException("could not verify the size");
        }
        return this.plaintextSize;
    }

    public SeekableByteChannel truncate(long j) throws NonWritableChannelException {
        throw new NonWritableChannelException();
    }

    public int write(ByteBuffer byteBuffer) throws NonWritableChannelException {
        throw new NonWritableChannelException();
    }

    public synchronized void close() throws IOException {
        this.ciphertextChannel.close();
        this.isopen = false;
    }

    public synchronized boolean isOpen() {
        return this.isopen;
    }
}
