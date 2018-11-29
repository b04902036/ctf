package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.proto.AesCtrHmacStreamingKey;
import com.google.crypto.tink.proto.AesCtrHmacStreamingKeyFormat;
import com.google.crypto.tink.proto.AesCtrHmacStreamingParams;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.subtle.AesCtrHmacStreaming;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import java.security.GeneralSecurityException;

class AesCtrHmacStreamingKeyManager implements KeyManager<StreamingAead> {
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;
    public static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.AesCtrHmacStreamingKey";
    private static final int VERSION = 0;

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacStreamingKey";
    }

    public int getVersion() {
        return 0;
    }

    AesCtrHmacStreamingKeyManager() {
    }

    public StreamingAead getPrimitive(ByteString byteString) throws GeneralSecurityException {
        try {
            return getPrimitive(AesCtrHmacStreamingKey.parseFrom(byteString));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected AesCtrHmacStreamingKey proto", e);
        }
    }

    public StreamingAead getPrimitive(MessageLite messageLite) throws GeneralSecurityException {
        if (messageLite instanceof AesCtrHmacStreamingKey) {
            AesCtrHmacStreamingKey aesCtrHmacStreamingKey = (AesCtrHmacStreamingKey) messageLite;
            validate(aesCtrHmacStreamingKey);
            return new AesCtrHmacStreaming(aesCtrHmacStreamingKey.getKeyValue().toByteArray(), StreamingAeadUtil.toHmacAlgo(aesCtrHmacStreamingKey.getParams().getHkdfHashType()), aesCtrHmacStreamingKey.getParams().getDerivedKeySize(), StreamingAeadUtil.toHmacAlgo(aesCtrHmacStreamingKey.getParams().getHmacParams().getHash()), aesCtrHmacStreamingKey.getParams().getHmacParams().getTagSize(), aesCtrHmacStreamingKey.getParams().getCiphertextSegmentSize(), 0);
        }
        throw new GeneralSecurityException("expected AesCtrHmacStreamingKey proto");
    }

    public MessageLite newKey(ByteString byteString) throws GeneralSecurityException {
        try {
            return newKey(AesCtrHmacStreamingKeyFormat.parseFrom(byteString));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacStreamingKeyFormat proto", e);
        }
    }

    public MessageLite newKey(MessageLite messageLite) throws GeneralSecurityException {
        if (messageLite instanceof AesCtrHmacStreamingKeyFormat) {
            AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat = (AesCtrHmacStreamingKeyFormat) messageLite;
            validate(aesCtrHmacStreamingKeyFormat);
            return AesCtrHmacStreamingKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(aesCtrHmacStreamingKeyFormat.getKeySize()))).setParams(aesCtrHmacStreamingKeyFormat.getParams()).setVersion(0).build();
        }
        throw new GeneralSecurityException("expected AesCtrHmacStreamingKeyFormat proto");
    }

    public KeyData newKeyData(ByteString byteString) throws GeneralSecurityException {
        return (KeyData) KeyData.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.AesCtrHmacStreamingKey").setValue(((AesCtrHmacStreamingKey) newKey(byteString)).toByteString()).setKeyMaterialType(KeyMaterialType.SYMMETRIC).build();
    }

    public boolean doesSupport(String str) {
        return str.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacStreamingKey");
    }

    private void validate(AesCtrHmacStreamingKey aesCtrHmacStreamingKey) throws GeneralSecurityException {
        Validators.validateVersion(aesCtrHmacStreamingKey.getVersion(), 0);
        if (aesCtrHmacStreamingKey.getKeyValue().size() < 16) {
            throw new GeneralSecurityException("key_value must have at least 16 bytes");
        } else if (aesCtrHmacStreamingKey.getKeyValue().size() >= aesCtrHmacStreamingKey.getParams().getDerivedKeySize()) {
            validate(aesCtrHmacStreamingKey.getParams());
        } else {
            throw new GeneralSecurityException("key_value must have at least as many bits as derived keys");
        }
    }

    private void validate(AesCtrHmacStreamingKeyFormat aesCtrHmacStreamingKeyFormat) throws GeneralSecurityException {
        if (aesCtrHmacStreamingKeyFormat.getKeySize() >= 16) {
            validate(aesCtrHmacStreamingKeyFormat.getParams());
            return;
        }
        throw new GeneralSecurityException("key_size must be at least 16 bytes");
    }

    private void validate(AesCtrHmacStreamingParams aesCtrHmacStreamingParams) throws GeneralSecurityException {
        Validators.validateAesKeySize(aesCtrHmacStreamingParams.getDerivedKeySize());
        if (aesCtrHmacStreamingParams.getHkdfHashType() == HashType.UNKNOWN_HASH) {
            throw new GeneralSecurityException("unknown HKDF hash type");
        } else if (aesCtrHmacStreamingParams.getHmacParams().getHash() != HashType.UNKNOWN_HASH) {
            validateHmacParams(aesCtrHmacStreamingParams.getHmacParams());
            if (aesCtrHmacStreamingParams.getCiphertextSegmentSize() < (aesCtrHmacStreamingParams.getDerivedKeySize() + aesCtrHmacStreamingParams.getHmacParams().getTagSize()) + 8) {
                throw new GeneralSecurityException("ciphertext_segment_size must be at least (derived_key_size + tag_size + 8)");
            }
        } else {
            throw new GeneralSecurityException("unknown HMAC hash type");
        }
    }

    private void validateHmacParams(HmacParams hmacParams) throws GeneralSecurityException {
        if (hmacParams.getTagSize() >= 10) {
            switch (hmacParams.getHash()) {
                case SHA1:
                    if (hmacParams.getTagSize() > 20) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case SHA256:
                    if (hmacParams.getTagSize() > 32) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                case SHA512:
                    if (hmacParams.getTagSize() > 64) {
                        throw new GeneralSecurityException("tag size too big");
                    }
                    return;
                default:
                    throw new GeneralSecurityException("unknown hash type");
            }
        }
        throw new GeneralSecurityException("tag size too small");
    }
}
