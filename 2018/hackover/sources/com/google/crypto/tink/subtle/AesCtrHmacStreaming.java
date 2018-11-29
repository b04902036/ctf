package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class AesCtrHmacStreaming extends NonceBasedStreamingAead {
    private static final int HMAC_KEY_SIZE_IN_BYTES = 32;
    private static final int NONCE_PREFIX_IN_BYTES = 7;
    private static final int NONCE_SIZE_IN_BYTES = 16;
    private final int ciphertextSegmentSize;
    private final int firstSegmentOffset;
    private final String hkdfAlgo;
    private final byte[] ikm;
    private final int keySizeInBytes;
    private final int plaintextSegmentSize;
    private final String tagAlgo;
    private final int tagSizeInBytes;

    class AesCtrHmacStreamDecrypter implements StreamSegmentDecrypter {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Cipher cipher;
        private SecretKeySpec hmacKeySpec;
        private SecretKeySpec keySpec;
        private Mac mac;
        private byte[] noncePrefix;

        static {
            Class cls = AesCtrHmacStreaming.class;
        }

        AesCtrHmacStreamDecrypter() {
        }

        public synchronized void init(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException {
            if (byteBuffer.remaining() != AesCtrHmacStreaming.this.getHeaderLength()) {
                throw new InvalidAlgorithmParameterException("Invalid header length");
            } else if (byteBuffer.get() == AesCtrHmacStreaming.this.getHeaderLength()) {
                this.noncePrefix = new byte[7];
                byte[] bArr2 = new byte[AesCtrHmacStreaming.this.keySizeInBytes];
                byteBuffer.get(bArr2);
                byteBuffer.get(this.noncePrefix);
                byte[] access$400 = AesCtrHmacStreaming.this.deriveKeyMaterial(bArr2, bArr);
                this.keySpec = AesCtrHmacStreaming.this.deriveKeySpec(access$400);
                this.hmacKeySpec = AesCtrHmacStreaming.this.deriveHmacKeySpec(access$400);
                this.cipher = AesCtrHmacStreaming.cipherInstance();
                this.mac = AesCtrHmacStreaming.this.macInstance();
            } else {
                throw new GeneralSecurityException("Invalid ciphertext");
            }
        }

        public synchronized void decryptSegment(ByteBuffer byteBuffer, int i, boolean z, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            int position = byteBuffer.position();
            byte[] access$700 = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, i, z);
            int remaining = byteBuffer.remaining();
            if (remaining >= AesCtrHmacStreaming.this.tagSizeInBytes) {
                position += remaining - AesCtrHmacStreaming.this.tagSizeInBytes;
                ByteBuffer duplicate = byteBuffer.duplicate();
                duplicate.limit(position);
                ByteBuffer duplicate2 = byteBuffer.duplicate();
                duplicate2.position(position);
                this.mac.init(this.hmacKeySpec);
                this.mac.update(access$700);
                this.mac.update(duplicate);
                byte[] copyOf = Arrays.copyOf(this.mac.doFinal(), AesCtrHmacStreaming.this.tagSizeInBytes);
                byte[] bArr = new byte[AesCtrHmacStreaming.this.tagSizeInBytes];
                duplicate2.get(bArr);
                if (Bytes.equal(bArr, copyOf)) {
                    byteBuffer.limit(position);
                    this.cipher.init(1, this.keySpec, new IvParameterSpec(access$700));
                    this.cipher.doFinal(byteBuffer, byteBuffer2);
                } else {
                    throw new GeneralSecurityException("Tag mismatch");
                }
            }
            throw new GeneralSecurityException("Ciphertext too short");
        }
    }

    class AesCtrHmacStreamEncrypter implements StreamSegmentEncrypter {
        private final Cipher cipher = AesCtrHmacStreaming.cipherInstance();
        private int encryptedSegments = 0;
        private ByteBuffer header;
        private final SecretKeySpec hmacKeySpec;
        private final SecretKeySpec keySpec;
        private final Mac mac;
        private final byte[] noncePrefix;

        public AesCtrHmacStreamEncrypter(byte[] bArr) throws GeneralSecurityException {
            this.mac = AesCtrHmacStreaming.this.macInstance();
            this.encryptedSegments = 0;
            byte[] access$200 = AesCtrHmacStreaming.this.randomSalt();
            this.noncePrefix = AesCtrHmacStreaming.this.randomNonce();
            this.header = ByteBuffer.allocate(AesCtrHmacStreaming.this.getHeaderLength());
            this.header.put((byte) AesCtrHmacStreaming.this.getHeaderLength());
            this.header.put(access$200);
            this.header.put(this.noncePrefix);
            this.header.flip();
            bArr = AesCtrHmacStreaming.this.deriveKeyMaterial(access$200, bArr);
            this.keySpec = AesCtrHmacStreaming.this.deriveKeySpec(bArr);
            this.hmacKeySpec = AesCtrHmacStreaming.this.deriveHmacKeySpec(bArr);
        }

        public ByteBuffer getHeader() {
            return this.header.asReadOnlyBuffer();
        }

        public synchronized void encryptSegment(ByteBuffer byteBuffer, boolean z, ByteBuffer byteBuffer2) throws GeneralSecurityException {
            int position = byteBuffer2.position();
            byte[] access$700 = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, z);
            this.cipher.init(1, this.keySpec, new IvParameterSpec(access$700));
            this.encryptedSegments++;
            this.cipher.doFinal(byteBuffer, byteBuffer2);
            byteBuffer = byteBuffer2.duplicate();
            byteBuffer.flip();
            byteBuffer.position(position);
            this.mac.init(this.hmacKeySpec);
            this.mac.update(access$700);
            this.mac.update(byteBuffer);
            byteBuffer2.put(this.mac.doFinal(), 0, AesCtrHmacStreaming.this.tagSizeInBytes);
        }

        public synchronized void encryptSegment(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, boolean z, ByteBuffer byteBuffer3) throws GeneralSecurityException {
            int position = byteBuffer3.position();
            byte[] access$700 = AesCtrHmacStreaming.this.nonceForSegment(this.noncePrefix, this.encryptedSegments, z);
            this.cipher.init(1, this.keySpec, new IvParameterSpec(access$700));
            this.encryptedSegments++;
            this.cipher.update(byteBuffer, byteBuffer3);
            this.cipher.doFinal(byteBuffer2, byteBuffer3);
            byteBuffer = byteBuffer3.duplicate();
            byteBuffer.flip();
            byteBuffer.position(position);
            this.mac.init(this.hmacKeySpec);
            this.mac.update(access$700);
            this.mac.update(byteBuffer);
            byteBuffer3.put(this.mac.doFinal(), 0, AesCtrHmacStreaming.this.tagSizeInBytes);
        }

        public synchronized int getEncryptedSegments() {
            return this.encryptedSegments;
        }
    }

    public AesCtrHmacStreaming(byte[] bArr, String str, int i, String str2, int i2, int i3, int i4) throws InvalidAlgorithmParameterException {
        validateParameters(bArr.length, i, str2, i2, i3, i4);
        this.ikm = Arrays.copyOf(bArr, bArr.length);
        this.hkdfAlgo = str;
        this.keySizeInBytes = i;
        this.tagAlgo = str2;
        this.tagSizeInBytes = i2;
        this.ciphertextSegmentSize = i3;
        this.firstSegmentOffset = i4;
        this.plaintextSegmentSize = i3 - i2;
    }

    private static void validateParameters(int i, int i2, String str, int i3, int i4, int i5) throws InvalidAlgorithmParameterException {
        if (i < 16 || i < i2) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ikm too short, must be >= ");
            stringBuilder.append(Math.max(16, i2));
            throw new InvalidAlgorithmParameterException(stringBuilder.toString());
        }
        Validators.validateAesKeySize(i2);
        if (i3 < 10) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("tag size too small ");
            stringBuilder2.append(i3);
            throw new InvalidAlgorithmParameterException(stringBuilder2.toString());
        } else if ((str.equals("HmacSha1") && i3 > 20) || ((str.equals("HmacSha256") && i3 > 32) || (str.equals("HmacSha512") && i3 > 64))) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        } else if (((((i4 - i5) - i3) - i2) - 7) - 1 <= 0) {
            throw new InvalidAlgorithmParameterException("ciphertextSegmentSize too small");
        }
    }

    public AesCtrHmacStreamEncrypter newStreamSegmentEncrypter(byte[] bArr) throws GeneralSecurityException {
        return new AesCtrHmacStreamEncrypter(bArr);
    }

    public AesCtrHmacStreamDecrypter newStreamSegmentDecrypter() throws GeneralSecurityException {
        return new AesCtrHmacStreamDecrypter();
    }

    public int getCiphertextSegmentSize() {
        return this.ciphertextSegmentSize;
    }

    public int getPlaintextSegmentSize() {
        return this.plaintextSegmentSize;
    }

    public int getHeaderLength() {
        return (this.keySizeInBytes + 1) + 7;
    }

    public int getCiphertextOffset() {
        return getHeaderLength() + this.firstSegmentOffset;
    }

    public int getCiphertextOverhead() {
        return this.tagSizeInBytes;
    }

    public int getFirstSegmentOffset() {
        return this.firstSegmentOffset;
    }

    public long expectedCiphertextSize(long j) {
        j += (long) getCiphertextOffset();
        long j2 = (j / ((long) this.plaintextSegmentSize)) * ((long) this.ciphertextSegmentSize);
        j %= (long) this.plaintextSegmentSize;
        return j > 0 ? j2 + (j + ((long) this.tagSizeInBytes)) : j2;
    }

    private static Cipher cipherInstance() throws GeneralSecurityException {
        return (Cipher) EngineFactory.CIPHER.getInstance("AES/CTR/NoPadding");
    }

    private Mac macInstance() throws GeneralSecurityException {
        return (Mac) EngineFactory.MAC.getInstance(this.tagAlgo);
    }

    private byte[] randomSalt() {
        return Random.randBytes(this.keySizeInBytes);
    }

    private byte[] nonceForSegment(byte[] bArr, int i, boolean z) {
        ByteBuffer allocate = ByteBuffer.allocate(16);
        allocate.order(ByteOrder.BIG_ENDIAN);
        allocate.put(bArr);
        allocate.putInt(i);
        allocate.put((byte) z);
        allocate.putInt(0);
        return allocate.array();
    }

    private byte[] randomNonce() {
        return Random.randBytes(7);
    }

    private byte[] deriveKeyMaterial(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return Hkdf.computeHkdf(this.hkdfAlgo, this.ikm, bArr, bArr2, this.keySizeInBytes + 32);
    }

    private SecretKeySpec deriveKeySpec(byte[] bArr) throws GeneralSecurityException {
        return new SecretKeySpec(bArr, 0, this.keySizeInBytes, "AES");
    }

    private SecretKeySpec deriveHmacKeySpec(byte[] bArr) throws GeneralSecurityException {
        return new SecretKeySpec(bArr, this.keySizeInBytes, 32, this.tagAlgo);
    }
}
