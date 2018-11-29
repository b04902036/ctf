package com.google.crypto.tink.subtle;

public final class Hex {
    public static String encode(byte[] bArr) {
        String str = "0123456789abcdef";
        StringBuilder stringBuilder = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            stringBuilder.append(str.charAt(i / 16));
            stringBuilder.append(str.charAt(i % 16));
        }
        return stringBuilder.toString();
    }

    public static byte[] decode(String str) {
        if (str.length() % 2 == 0) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                int digit = Character.digit(str.charAt(i2), 16);
                i2 = Character.digit(str.charAt(i2 + 1), 16);
                if (digit == -1 || i2 == -1) {
                    throw new IllegalArgumentException("input is not hexadecimal");
                }
                bArr[i] = (byte) ((digit * 16) + i2);
            }
            return bArr;
        }
        throw new IllegalArgumentException("Expected a string of even length");
    }
}
