package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.PrimitiveSet.Entry;
import com.google.crypto.tink.StreamingAead;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.security.GeneralSecurityException;
import javax.annotation.concurrent.GuardedBy;

final class SeekableByteChannelDecrypter implements SeekableByteChannel {
    byte[] associatedData;
    @GuardedBy("this")
    boolean attemptedMatching = false;
    @GuardedBy("this")
    long cachedPosition;
    @GuardedBy("this")
    SeekableByteChannel ciphertextChannel;
    @GuardedBy("this")
    SeekableByteChannel matchingChannel = null;
    PrimitiveSet<StreamingAead> primitives;
    @GuardedBy("this")
    long startingPosition;

    public SeekableByteChannelDecrypter(PrimitiveSet<StreamingAead> primitiveSet, SeekableByteChannel seekableByteChannel, byte[] bArr) throws IOException {
        this.primitives = primitiveSet;
        this.ciphertextChannel = seekableByteChannel;
        this.cachedPosition = -1;
        this.startingPosition = seekableByteChannel.position();
        this.associatedData = (byte[]) bArr.clone();
    }

    @GuardedBy("this")
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() == 0) {
            return 0;
        }
        if (this.matchingChannel != null) {
            return this.matchingChannel.read(byteBuffer);
        } else if (this.attemptedMatching) {
            throw new IOException("No matching key found for the ciphertext in the stream.");
        } else {
            this.attemptedMatching = true;
            try {
                for (Entry primitive : this.primitives.getRawPrimitives()) {
                    SeekableByteChannel newSeekableDecryptingChannel = ((StreamingAead) primitive.getPrimitive()).newSeekableDecryptingChannel(this.ciphertextChannel, this.associatedData);
                    if (this.cachedPosition >= 0) {
                        newSeekableDecryptingChannel.position(this.cachedPosition);
                    }
                    int read = newSeekableDecryptingChannel.read(byteBuffer);
                    if (read > 0) {
                        this.matchingChannel = newSeekableDecryptingChannel;
                    } else if (read == 0) {
                        this.ciphertextChannel.position(this.startingPosition);
                        this.attemptedMatching = false;
                    }
                    this.matchingChannel = newSeekableDecryptingChannel;
                    return read;
                }
                throw new IOException("No matching key found for the ciphertext in the stream.");
            } catch (Throwable e) {
                throw new IOException("Keyset failure: ", e);
            } catch (IOException unused) {
                this.ciphertextChannel.position(this.startingPosition);
            } catch (GeneralSecurityException unused2) {
                this.ciphertextChannel.position(this.startingPosition);
            }
        }
    }

    @GuardedBy("this")
    public synchronized SeekableByteChannel position(long j) throws IOException {
        if (this.matchingChannel != null) {
            this.matchingChannel.position(j);
        } else if (j >= 0) {
            this.cachedPosition = j;
        } else {
            throw new IllegalArgumentException("Position must be non-negative");
        }
        return this;
    }

    @GuardedBy("this")
    public synchronized long position() throws IOException {
        if (this.matchingChannel != null) {
            return this.matchingChannel.position();
        }
        return this.cachedPosition;
    }

    @GuardedBy("this")
    public synchronized long size() throws IOException {
        if (this.matchingChannel != null) {
        } else {
            throw new IOException("Cannot determine size before first read()-call.");
        }
        return this.matchingChannel.size();
    }

    public SeekableByteChannel truncate(long j) throws IOException {
        throw new NonWritableChannelException();
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        throw new NonWritableChannelException();
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
