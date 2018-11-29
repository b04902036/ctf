package com.google.crypto.tink.proto;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum KeyStatusType implements EnumLite {
    UNKNOWN_STATUS(0),
    ENABLED(1),
    DISABLED(2),
    DESTROYED(3),
    UNRECOGNIZED(-1);
    
    public static final int DESTROYED_VALUE = 3;
    public static final int DISABLED_VALUE = 2;
    public static final int ENABLED_VALUE = 1;
    public static final int UNKNOWN_STATUS_VALUE = 0;
    private static final EnumLiteMap<KeyStatusType> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<KeyStatusType>() {
            public KeyStatusType findValueByNumber(int i) {
                return KeyStatusType.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static KeyStatusType valueOf(int i) {
        return forNumber(i);
    }

    public static KeyStatusType forNumber(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_STATUS;
            case 1:
                return ENABLED;
            case 2:
                return DISABLED;
            case 3:
                return DESTROYED;
            default:
                return null;
        }
    }

    public static EnumLiteMap<KeyStatusType> internalGetValueMap() {
        return internalValueMap;
    }

    private KeyStatusType(int i) {
        this.value = i;
    }
}
