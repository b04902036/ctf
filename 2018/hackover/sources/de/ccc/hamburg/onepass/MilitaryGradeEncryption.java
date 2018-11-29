package de.ccc.hamburg.onepass;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AeadFactory;
import com.google.crypto.tink.subtle.Base64;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0002J\u0016\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004J\u0016\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u0016"}, d2 = {"Lde/ccc/hamburg/onepass/MilitaryGradeEncryption;", "", "()V", "keyTemplate", "", "getKeyTemplate", "()Ljava/lang/String;", "militaryGradeEncryptionSecret", "getMilitaryGradeEncryptionSecret", "b64decode", "", "data", "b64encode", "createAEADHandler", "Lcom/google/crypto/tink/Aead;", "key", "decrypt", "encrypted", "encrypt", "secret", "unwrapKey", "mask", "app_release"}, k = 1, mv = {1, 1, 10})
/* compiled from: MilitaryGradeEncryption.kt */
public final class MilitaryGradeEncryption {
    @NotNull
    private final String keyTemplate = "{\"primaryKeyId\":13371337,\"key\":[{\"keyData\":{\"typeUrl\":\"type.googleapis.com/google.crypto.tink.AesGcmKey\",\"keyMaterialType\":\"SYMMETRIC\",\"value\":\"INSERTKEY\"},\"outputPrefixType\":\"TINK\",\"keyId\":13371337,\"status\":\"ENABLED\"}]}";
    @NotNull
    private final String militaryGradeEncryptionSecret = "vH32u3Kb3taX6RX2J56uXd1kKxrc4NquqMuAcxlGEacxGQ==";

    public MilitaryGradeEncryption() {
        AeadConfig.register();
    }

    @NotNull
    public final String getKeyTemplate() {
        return this.keyTemplate;
    }

    @NotNull
    public final String getMilitaryGradeEncryptionSecret() {
        return this.militaryGradeEncryptionSecret;
    }

    @NotNull
    public final byte[] b64decode(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "data");
        Object decode = Base64.decode(str, 0);
        Intrinsics.checkExpressionValueIsNotNull(decode, "Base64.decode(data, Base64.DEFAULT)");
        return decode;
    }

    @NotNull
    public final byte[] b64encode(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "data");
        Object encode = Base64.encode(bArr, 0);
        Intrinsics.checkExpressionValueIsNotNull(encode, "Base64.encode(data, Base64.DEFAULT)");
        return encode;
    }

    @NotNull
    public final String unwrapKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "mask");
        byte[] b64decode = b64decode(this.militaryGradeEncryptionSecret);
        byte[] b64decode2 = b64decode(str);
        int length = b64decode2.length;
        for (int i = 0; i < length; i++) {
            b64decode2[i] = (byte) (b64decode2[i] ^ b64decode[i % b64decode.length]);
        }
        return new String(b64encode(b64decode2), Charsets.UTF_8);
    }

    @NotNull
    public final String decrypt(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "encrypted");
        Intrinsics.checkParameterIsNotNull(str2, "key");
        str2 = unwrapKey(str2);
        byte[] b64decode = b64decode(str);
        if (b64decode(str2).length != 34) {
            return "";
        }
        Aead createAEADHandler = createAEADHandler(str2);
        Object bytes = "hackover18".getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        Object decrypt = createAEADHandler.decrypt(b64decode, bytes);
        Intrinsics.checkExpressionValueIsNotNull(decrypt, "decrypted");
        return new String(decrypt, Charsets.UTF_8);
    }

    @NotNull
    public final String encrypt(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "secret");
        Intrinsics.checkParameterIsNotNull(str2, "key");
        try {
            Aead createAEADHandler = createAEADHandler(str2);
            Object bytes = str.getBytes(Charsets.UTF_8);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            Object bytes2 = "hackover18".getBytes(Charsets.UTF_8);
            Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
            bytes = createAEADHandler.encrypt(bytes, bytes2);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "encrypted");
            return new String(b64encode(bytes), Charsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    private final Aead createAEADHandler(String str) {
        String replace$default = StringsKt__StringsJVMKt.replace$default(this.keyTemplate, "INSERTKEY", str, false, 4, null);
        Charset charset = Charsets.UTF_8;
        if (replace$default != null) {
            Object bytes = replace$default.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            Aead primitive = AeadFactory.getPrimitive(CleartextKeysetHandle.read(JsonKeysetReader.withBytes(bytes)));
            Intrinsics.checkExpressionValueIsNotNull(primitive, "AeadFactory.getPrimitive(keysetHandle)");
            return primitive;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
