package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.proto.HashType;
import java.security.NoSuchAlgorithmException;

class StreamingAeadUtil {
    StreamingAeadUtil() {
    }

    public static String toHmacAlgo(HashType hashType) throws NoSuchAlgorithmException {
        switch (hashType) {
            case SHA1:
                return "HmacSha1";
            case SHA256:
                return "HmacSha256";
            case SHA512:
                return "HmacSha512";
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("hash unsupported for HMAC: ");
                stringBuilder.append(hashType);
                throw new NoSuchAlgorithmException(stringBuilder.toString());
        }
    }
}
