package com.google.crypto.tink.proto;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum OutputPrefixType implements EnumLite {
    UNKNOWN_PREFIX(0),
    TINK(1),
    LEGACY(2),
    RAW(3),
    CRUNCHY(4),
    UNRECOGNIZED(-1);
    
    public static final int CRUNCHY_VALUE = 4;
    public static final int LEGACY_VALUE = 2;
    public static final int RAW_VALUE = 3;
    public static final int TINK_VALUE = 1;
    public static final int UNKNOWN_PREFIX_VALUE = 0;
    private static final EnumLiteMap<OutputPrefixType> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<OutputPrefixType>() {
            public OutputPrefixType findValueByNumber(int i) {
                return OutputPrefixType.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static OutputPrefixType valueOf(int i) {
        return forNumber(i);
    }

    public static OutputPrefixType forNumber(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_PREFIX;
            case 1:
                return TINK;
            case 2:
                return LEGACY;
            case 3:
                return RAW;
            case 4:
                return CRUNCHY;
            default:
                return null;
        }
    }

    public static EnumLiteMap<OutputPrefixType> internalGetValueMap() {
        return internalValueMap;
    }

    private OutputPrefixType(int i) {
        this.value = i;
    }
}
