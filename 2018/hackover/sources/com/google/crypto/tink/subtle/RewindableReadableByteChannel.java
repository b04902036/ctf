package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import javax.annotation.concurrent.GuardedBy;

public final class RewindableReadableByteChannel implements ReadableByteChannel {
    @GuardedBy("this")
    final ReadableByteChannel baseChannel;
    @GuardedBy("this")
    ByteBuffer buffer = null;
    @GuardedBy("this")
    boolean canRewind = true;
    @GuardedBy("this")
    boolean directRead = false;

    public RewindableReadableByteChannel(ReadableByteChannel readableByteChannel) {
        this.baseChannel = readableByteChannel;
    }

    @GuardedBy("this")
    public synchronized void disableRewinding() {
        this.canRewind = false;
    }

    @GuardedBy("this")
    public synchronized void rewind() throws IOException {
        if (!this.canRewind) {
            throw new IOException("Cannot rewind anymore.");
        } else if (this.buffer != null) {
            this.buffer.position(0);
        }
    }

    /* JADX WARNING: Missing block: B:26:0x0044, code:
            return r0;
     */
    /* JADX WARNING: Missing block: B:36:0x0066, code:
            return r0;
     */
    @javax.annotation.concurrent.GuardedBy("this")
    public synchronized int read(java.nio.ByteBuffer r6) throws java.io.IOException {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = r5.directRead;	 Catch:{ all -> 0x00bb }
        if (r0 == 0) goto L_0x000d;
    L_0x0005:
        r0 = r5.baseChannel;	 Catch:{ all -> 0x00bb }
        r6 = r0.read(r6);	 Catch:{ all -> 0x00bb }
        monitor-exit(r5);
        return r6;
    L_0x000d:
        r0 = r6.remaining();	 Catch:{ all -> 0x00bb }
        if (r0 != 0) goto L_0x0016;
    L_0x0013:
        r6 = 0;
        monitor-exit(r5);
        return r6;
    L_0x0016:
        r1 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r2 = 1;
        if (r1 != 0) goto L_0x0045;
    L_0x001b:
        r1 = r5.canRewind;	 Catch:{ all -> 0x00bb }
        if (r1 != 0) goto L_0x0029;
    L_0x001f:
        r5.directRead = r2;	 Catch:{ all -> 0x00bb }
        r0 = r5.baseChannel;	 Catch:{ all -> 0x00bb }
        r6 = r0.read(r6);	 Catch:{ all -> 0x00bb }
        monitor-exit(r5);
        return r6;
    L_0x0029:
        r0 = java.nio.ByteBuffer.allocate(r0);	 Catch:{ all -> 0x00bb }
        r5.buffer = r0;	 Catch:{ all -> 0x00bb }
        r0 = r5.baseChannel;	 Catch:{ all -> 0x00bb }
        r1 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r0 = r0.read(r1);	 Catch:{ all -> 0x00bb }
        if (r0 <= 0) goto L_0x0043;
    L_0x0039:
        r1 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r1.flip();	 Catch:{ all -> 0x00bb }
        r1 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r6.put(r1);	 Catch:{ all -> 0x00bb }
    L_0x0043:
        monitor-exit(r5);
        return r0;
    L_0x0045:
        r1 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r1 = r1.remaining();	 Catch:{ all -> 0x00bb }
        if (r1 < r0) goto L_0x0067;
    L_0x004d:
        r1 = new byte[r0];	 Catch:{ all -> 0x00bb }
        r3 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r3.get(r1);	 Catch:{ all -> 0x00bb }
        r6.put(r1);	 Catch:{ all -> 0x00bb }
        r6 = r5.canRewind;	 Catch:{ all -> 0x00bb }
        if (r6 != 0) goto L_0x0065;
    L_0x005b:
        r6 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r6 = r6.remaining();	 Catch:{ all -> 0x00bb }
        if (r6 != 0) goto L_0x0065;
    L_0x0063:
        r5.directRead = r2;	 Catch:{ all -> 0x00bb }
    L_0x0065:
        monitor-exit(r5);
        return r0;
    L_0x0067:
        r1 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r1 = r1.remaining();	 Catch:{ all -> 0x00bb }
        r0 = r0 - r1;
        r3 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r6.put(r3);	 Catch:{ all -> 0x00bb }
        r3 = java.nio.ByteBuffer.allocate(r0);	 Catch:{ all -> 0x00bb }
        r4 = r5.baseChannel;	 Catch:{ all -> 0x00bb }
        r4 = r4.read(r3);	 Catch:{ all -> 0x00bb }
        if (r4 <= 0) goto L_0x0085;
    L_0x007f:
        r3.flip();	 Catch:{ all -> 0x00bb }
        r6.put(r3);	 Catch:{ all -> 0x00bb }
    L_0x0085:
        r6 = r5.canRewind;	 Catch:{ all -> 0x00bb }
        if (r6 == 0) goto L_0x00b3;
    L_0x0089:
        r6 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r6 = r6.limit();	 Catch:{ all -> 0x00bb }
        r6 = r6 + r0;
        r6 = java.nio.ByteBuffer.allocate(r6);	 Catch:{ all -> 0x00bb }
        r0 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r0.flip();	 Catch:{ all -> 0x00bb }
        r0 = r5.buffer;	 Catch:{ all -> 0x00bb }
        r6.put(r0);	 Catch:{ all -> 0x00bb }
        if (r4 <= 0) goto L_0x00a6;
    L_0x00a0:
        r3.flip();	 Catch:{ all -> 0x00bb }
        r6.put(r3);	 Catch:{ all -> 0x00bb }
    L_0x00a6:
        r6.flip();	 Catch:{ all -> 0x00bb }
        r0 = r6.limit();	 Catch:{ all -> 0x00bb }
        r6.position(r0);	 Catch:{ all -> 0x00bb }
        r5.buffer = r6;	 Catch:{ all -> 0x00bb }
        goto L_0x00b8;
    L_0x00b3:
        r6 = 0;
        r5.buffer = r6;	 Catch:{ all -> 0x00bb }
        r5.directRead = r2;	 Catch:{ all -> 0x00bb }
    L_0x00b8:
        r1 = r1 + r4;
        monitor-exit(r5);
        return r1;
    L_0x00bb:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.RewindableReadableByteChannel.read(java.nio.ByteBuffer):int");
    }

    @GuardedBy("this")
    public synchronized void close() throws IOException {
        this.canRewind = false;
        this.directRead = true;
        this.baseChannel.close();
    }

    @GuardedBy("this")
    public synchronized boolean isOpen() {
        return this.baseChannel.isOpen();
    }
}
