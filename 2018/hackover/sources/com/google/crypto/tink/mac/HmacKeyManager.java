package com.google.crypto.tink.mac;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.Mac;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HmacKey;
import com.google.crypto.tink.proto.HmacKeyFormat;
import com.google.crypto.tink.proto.HmacParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyData.KeyMaterialType;
import com.google.crypto.tink.subtle.MacJce;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;
import java.security.GeneralSecurityException;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

class HmacKeyManager implements KeyManager<Mac> {
    private static final int MIN_KEY_SIZE_IN_BYTES = 16;
    private static final int MIN_TAG_SIZE_IN_BYTES = 10;
    public static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.HmacKey";
    private static final int VERSION = 0;

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    public int getVersion() {
        return 0;
    }

    HmacKeyManager() {
    }

    public Mac getPrimitive(ByteString byteString) throws GeneralSecurityException {
        try {
            return getPrimitive(HmacKey.parseFrom(byteString));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized HmacKey proto", e);
        }
    }

    public Mac getPrimitive(MessageLite messageLite) throws GeneralSecurityException {
        if (messageLite instanceof HmacKey) {
            HmacKey hmacKey = (HmacKey) messageLite;
            validate(hmacKey);
            HashType hash = hmacKey.getParams().getHash();
            Key secretKeySpec = new SecretKeySpec(hmacKey.getKeyValue().toByteArray(), "HMAC");
            int tagSize = hmacKey.getParams().getTagSize();
            switch (hash) {
                case SHA1:
                    return new MacJce("HMACSHA1", secretKeySpec, tagSize);
                case SHA256:
                    return new MacJce("HMACSHA256", secretKeySpec, tagSize);
                case SHA512:
                    return new MacJce("HMACSHA512", secretKeySpec, tagSize);
                default:
                    throw new GeneralSecurityException("unknown hash");
            }
        }
        throw new GeneralSecurityException("expected HmacKey proto");
    }

    public MessageLite newKey(ByteString byteString) throws GeneralSecurityException {
        try {
            return newKey(HmacKeyFormat.parseFrom(byteString));
        } catch (Throwable e) {
            throw new GeneralSecurityException("expected serialized HmacKeyFormat proto", e);
        }
    }

    public MessageLite newKey(MessageLite messageLite) throws GeneralSecurityException {
        if (messageLite instanceof HmacKeyFormat) {
            HmacKeyFormat hmacKeyFormat = (HmacKeyFormat) messageLite;
            validate(hmacKeyFormat);
            return HmacKey.newBuilder().setVersion(0).setParams(hmacKeyFormat.getParams()).setKeyValue(ByteString.copyFrom(Random.randBytes(hmacKeyFormat.getKeySize()))).build();
        }
        throw new GeneralSecurityException("expected HmacKeyFormat proto");
    }

    public KeyData newKeyData(ByteString byteString) throws GeneralSecurityException {
        return (KeyData) KeyData.newBuilder().setTypeUrl("type.googleapis.com/google.crypto.tink.HmacKey").setValue(((HmacKey) newKey(byteString)).toByteString()).setKeyMaterialType(KeyMaterialType.SYMMETRIC).build();
    }

    public boolean doesSupport(String str) {
        return str.equals("type.googleapis.com/google.crypto.tink.HmacKey");
    }

    private void validate(HmacKey hmacKey) throws GeneralSecurityException {
        Validators.validateVersion(hmacKey.getVersion(), 0);
        if (hmacKey.getKeyValue().size() >= 16) {
            validate(hmacKey.getParams());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }

    private void validate(HmacKeyFormat hmacKeyFormat) throws GeneralSecurityException {
        if (hmacKeyFormat.getKeySize() >= 16) {
            validate(hmacKeyFormat.getParams());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }

    private void validate(HmacParams hmacParams) throws GeneralSecurityException {
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
