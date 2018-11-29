package com.google.crypto.tink.subtle;

public final class ImmutableByteArray {
    private final byte[] data;

    public static ImmutableByteArray of(byte[] bArr) {
        return bArr == null ? null : of(bArr, 0, bArr.length);
    }

    public static ImmutableByteArray of(byte[] bArr, int i, int i2) {
        return new ImmutableByteArray(bArr, i, i2);
    }

    public byte[] getBytes() {
        Object obj = new byte[this.data.length];
        System.arraycopy(this.data, 0, obj, 0, this.data.length);
        return obj;
    }

    public int getLength() {
        return this.data.length;
    }

    private ImmutableByteArray(byte[] bArr, int i, int i2) {
        this.data = new byte[i2];
        System.arraycopy(bArr, i, this.data, 0, i2);
    }
}
