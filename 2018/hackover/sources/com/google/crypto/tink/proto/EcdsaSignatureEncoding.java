package com.google.crypto.tink.proto;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum EcdsaSignatureEncoding implements EnumLite {
    UNKNOWN_ENCODING(0),
    IEEE_P1363(1),
    DER(2),
    UNRECOGNIZED(-1);
    
    public static final int DER_VALUE = 2;
    public static final int IEEE_P1363_VALUE = 1;
    public static final int UNKNOWN_ENCODING_VALUE = 0;
    private static final EnumLiteMap<EcdsaSignatureEncoding> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<EcdsaSignatureEncoding>() {
            public EcdsaSignatureEncoding findValueByNumber(int i) {
                return EcdsaSignatureEncoding.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static EcdsaSignatureEncoding valueOf(int i) {
        return forNumber(i);
    }

    public static EcdsaSignatureEncoding forNumber(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_ENCODING;
            case 1:
                return IEEE_P1363;
            case 2:
                return DER;
            default:
                return null;
        }
    }

    public static EnumLiteMap<EcdsaSignatureEncoding> internalGetValueMap() {
        return internalValueMap;
    }

    private EcdsaSignatureEncoding(int i) {
        this.value = i;
    }
}
