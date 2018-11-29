package com.google.crypto.tink;

import com.google.crypto.tink.proto.Keyset.Key;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public final class CryptoFormat {
    public static final int LEGACY_PREFIX_SIZE = 5;
    public static final byte LEGACY_START_BYTE = (byte) 0;
    public static final int NON_RAW_PREFIX_SIZE = 5;
    public static final byte[] RAW_PREFIX = new byte[0];
    public static final int RAW_PREFIX_SIZE = 0;
    public static final int TINK_PREFIX_SIZE = 5;
    public static final byte TINK_START_BYTE = (byte) 1;

    public static byte[] getOutputPrefix(Key key) throws GeneralSecurityException {
        switch (key.getOutputPrefixType()) {
            case LEGACY:
            case CRUNCHY:
                return ByteBuffer.allocate(5).put((byte) 0).putInt(key.getKeyId()).array();
            case TINK:
                return ByteBuffer.allocate(5).put((byte) 1).putInt(key.getKeyId()).array();
            case RAW:
                return RAW_PREFIX;
            default:
                throw new GeneralSecurityException("unknown output prefix type");
        }
    }
}
