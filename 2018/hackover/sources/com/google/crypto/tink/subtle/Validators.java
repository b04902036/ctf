package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.Enums.HashType;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.regex.Pattern;

public final class Validators {
    private static final Pattern GCP_KMS_CRYPTO_KEY_PATTERN = Pattern.compile(String.format("^projects/%s/locations/%s/keyRings/%s/cryptoKeys/%s$", new Object[]{URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS}), 2);
    private static final Pattern GCP_KMS_CRYPTO_KEY_VERSION_PATTERN = Pattern.compile(String.format("^projects/%s/locations/%s/keyRings/%s/cryptoKeys/%s/cryptoKeyVersions/%s$", new Object[]{URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS, URI_UNRESERVED_CHARS}), 2);
    private static final String TYPE_URL_PREFIX = "type.googleapis.com/";
    private static final String URI_UNRESERVED_CHARS = "([0-9a-zA-Z\\-\\.\\_~])+";

    public static void validateTypeUrl(String str) throws GeneralSecurityException {
        if (!str.startsWith(TYPE_URL_PREFIX)) {
            throw new GeneralSecurityException(String.format("Error: type URL %s is invalid; it must start with %s.\n", new Object[]{str, TYPE_URL_PREFIX}));
        } else if (str.length() == TYPE_URL_PREFIX.length()) {
            throw new GeneralSecurityException(String.format("Error: type URL %s is invalid; it has no message name.\n", new Object[]{str}));
        }
    }

    public static void validateAesKeySize(int i) throws InvalidAlgorithmParameterException {
        if (i != 16 && i != 32) {
            throw new InvalidAlgorithmParameterException("invalid key size; only 128-bit and 256-bit AES keys are supported");
        }
    }

    public static void validateVersion(int i, int i2) throws GeneralSecurityException {
        if (i < 0 || i > i2) {
            throw new GeneralSecurityException(String.format("key has version %d; only keys with version in range [0..%d] are supported", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        }
    }

    public static void validateSignatureHash(HashType hashType) throws GeneralSecurityException {
        switch (hashType) {
            case SHA256:
            case SHA512:
                return;
            case SHA1:
                throw new GeneralSecurityException("SHA1 is not safe for signature");
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unsupported hash ");
                stringBuilder.append(hashType);
                throw new GeneralSecurityException(stringBuilder.toString());
        }
    }

    public static void validateNotExists(File file) throws IOException {
        if (file.exists()) {
            throw new IOException(String.format("%s exists, please choose another file\n", new Object[]{file.toString()}));
        }
    }

    public static void validateExists(File file) throws IOException {
        if (!file.exists()) {
            throw new IOException(String.format("Error: %s doesn't exist, please choose another file\n", new Object[]{file.toString()}));
        }
    }

    public static String validateKmsKeyUriAndRemovePrefix(String str, String str2) throws IllegalArgumentException {
        if (str2.toLowerCase().startsWith(str)) {
            return str2.substring(str.length());
        }
        throw new IllegalArgumentException(String.format("key URI must start with %s", new Object[]{str}));
    }

    public static void validateCryptoKeyUri(String str) throws GeneralSecurityException {
        if (!GCP_KMS_CRYPTO_KEY_PATTERN.matcher(str).matches()) {
            if (GCP_KMS_CRYPTO_KEY_VERSION_PATTERN.matcher(str).matches()) {
                throw new GeneralSecurityException("Invalid Google Cloud KMS Key URI. The URI must point to a CryptoKey, not a CryptoKeyVersion");
            }
            throw new GeneralSecurityException("Invalid Google Cloud KMS Key URI. The URI must point to a CryptoKey in the format projects/*/locations/*/keyRings/*/cryptoKeys/*. See https://cloud.google.com/kms/docs/reference/rest/v1/projects.locations.keyRings.cryptoKeys#CryptoKey");
        }
    }
}
