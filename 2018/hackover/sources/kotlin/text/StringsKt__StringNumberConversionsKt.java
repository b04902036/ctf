package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\u001a\u0013\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0003\u001a\u001b\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0006\u001a\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005*\u00020\u0002H\u0007¢\u0006\u0002\u0010\b\u001a\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\t\u001a\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u0002H\u0007¢\u0006\u0002\u0010\f\u001a\u001b\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\r\u001a\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0010\u001a\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"toByteOrNull", "", "", "(Ljava/lang/String;)Ljava/lang/Byte;", "radix", "", "(Ljava/lang/String;I)Ljava/lang/Byte;", "toIntOrNull", "(Ljava/lang/String;)Ljava/lang/Integer;", "(Ljava/lang/String;I)Ljava/lang/Integer;", "toLongOrNull", "", "(Ljava/lang/String;)Ljava/lang/Long;", "(Ljava/lang/String;I)Ljava/lang/Long;", "toShortOrNull", "", "(Ljava/lang/String;)Ljava/lang/Short;", "(Ljava/lang/String;I)Ljava/lang/Short;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 10}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: StringNumberConversions.kt */
class StringsKt__StringNumberConversionsKt extends StringsKt__StringNumberConversionsJVMKt {
    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return toByteOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Byte toByteOrNull(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Integer toIntOrNull = toIntOrNull(str, i);
        if (toIntOrNull == null) {
            return null;
        }
        int intValue = toIntOrNull.intValue();
        if (intValue < -128 || intValue > 127) {
            return null;
        }
        return Byte.valueOf((byte) intValue);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return toShortOrNull(str, 10);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Short toShortOrNull(@NotNull String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        Integer toIntOrNull = toIntOrNull(str, i);
        if (toIntOrNull == null) {
            return null;
        }
        int intValue = toIntOrNull.intValue();
        if (intValue < -32768 || intValue > 32767) {
            return null;
        }
        return Short.valueOf((short) intValue);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Integer toIntOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return toIntOrNull(str, 10);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037 A:{LOOP_START, LOOP:0: B:18:0x0037->B:28:0x004e, PHI: r2 r3 } */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0053  */
    @kotlin.SinceKotlin(version = "1.1")
    @org.jetbrains.annotations.Nullable
    public static final java.lang.Integer toIntOrNull(@org.jetbrains.annotations.NotNull java.lang.String r9, int r10) {
        /*
        r0 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0);
        kotlin.text.CharsKt__CharJVMKt.checkRadix(r10);
        r0 = r9.length();
        r1 = 0;
        if (r0 != 0) goto L_0x0010;
    L_0x000f:
        return r1;
    L_0x0010:
        r2 = 0;
        r3 = r9.charAt(r2);
        r4 = 48;
        r5 = -2147483647; // 0xffffffff80000001 float:-1.4E-45 double:NaN;
        r6 = 1;
        if (r3 >= r4) goto L_0x0030;
    L_0x001d:
        if (r0 != r6) goto L_0x0020;
    L_0x001f:
        return r1;
    L_0x0020:
        r4 = 45;
        if (r3 != r4) goto L_0x0028;
    L_0x0024:
        r5 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r3 = r6;
        goto L_0x0031;
    L_0x0028:
        r4 = 43;
        if (r3 != r4) goto L_0x002f;
    L_0x002c:
        r4 = r2;
        r3 = r6;
        goto L_0x0032;
    L_0x002f:
        return r1;
    L_0x0030:
        r3 = r2;
    L_0x0031:
        r4 = r3;
    L_0x0032:
        r7 = r5 / r10;
        r0 = r0 - r6;
        if (r3 > r0) goto L_0x0051;
    L_0x0037:
        r6 = r9.charAt(r3);
        r6 = kotlin.text.CharsKt__CharJVMKt.digitOf(r6, r10);
        if (r6 >= 0) goto L_0x0042;
    L_0x0041:
        return r1;
    L_0x0042:
        if (r2 >= r7) goto L_0x0045;
    L_0x0044:
        return r1;
    L_0x0045:
        r2 = r2 * r10;
        r8 = r5 + r6;
        if (r2 >= r8) goto L_0x004b;
    L_0x004a:
        return r1;
    L_0x004b:
        r2 = r2 - r6;
        if (r3 == r0) goto L_0x0051;
    L_0x004e:
        r3 = r3 + 1;
        goto L_0x0037;
    L_0x0051:
        if (r4 == 0) goto L_0x0058;
    L_0x0053:
        r9 = java.lang.Integer.valueOf(r2);
        goto L_0x005d;
    L_0x0058:
        r9 = -r2;
        r9 = java.lang.Integer.valueOf(r9);
    L_0x005d:
        return r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringNumberConversionsKt.toIntOrNull(java.lang.String, int):java.lang.Integer");
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Long toLongOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "$receiver");
        return toLongOrNull(str, 10);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x003f A:{LOOP_START, LOOP:0: B:17:0x003f->B:29:0x0061, PHI: r3 r4 r14 } */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0067  */
    @kotlin.SinceKotlin(version = "1.1")
    @org.jetbrains.annotations.Nullable
    public static final java.lang.Long toLongOrNull(@org.jetbrains.annotations.NotNull java.lang.String r19, int r20) {
        /*
        r0 = r19;
        r1 = r20;
        r2 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r2);
        kotlin.text.CharsKt__CharJVMKt.checkRadix(r20);
        r2 = r19.length();
        r3 = 0;
        if (r2 != 0) goto L_0x0014;
    L_0x0013:
        return r3;
    L_0x0014:
        r4 = 0;
        r5 = r0.charAt(r4);
        r6 = 48;
        r7 = -9223372036854775807; // 0x8000000000000001 float:1.4E-45 double:-4.9E-324;
        r9 = 1;
        if (r5 >= r6) goto L_0x0036;
    L_0x0023:
        if (r2 != r9) goto L_0x0026;
    L_0x0025:
        return r3;
    L_0x0026:
        r6 = 45;
        if (r5 != r6) goto L_0x002e;
    L_0x002a:
        r7 = -9223372036854775808;
        r4 = r9;
        goto L_0x0036;
    L_0x002e:
        r6 = 43;
        if (r5 != r6) goto L_0x0035;
    L_0x0032:
        r5 = r4;
        r4 = r9;
        goto L_0x0037;
    L_0x0035:
        return r3;
    L_0x0036:
        r5 = r4;
    L_0x0037:
        r10 = (long) r1;
        r12 = r7 / r10;
        r14 = 0;
        r2 = r2 - r9;
        if (r4 > r2) goto L_0x0065;
    L_0x003f:
        r6 = r0.charAt(r4);
        r6 = kotlin.text.CharsKt__CharJVMKt.digitOf(r6, r1);
        if (r6 >= 0) goto L_0x004a;
    L_0x0049:
        return r3;
    L_0x004a:
        r9 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1));
        if (r9 >= 0) goto L_0x004f;
    L_0x004e:
        return r3;
    L_0x004f:
        r14 = r14 * r10;
        r16 = r4;
        r3 = (long) r6;
        r17 = r7 + r3;
        r6 = (r14 > r17 ? 1 : (r14 == r17 ? 0 : -1));
        if (r6 >= 0) goto L_0x005b;
    L_0x0059:
        r6 = 0;
        return r6;
    L_0x005b:
        r6 = 0;
        r14 = r14 - r3;
        r4 = r16;
        if (r4 == r2) goto L_0x0065;
    L_0x0061:
        r4 = r4 + 1;
        r3 = r6;
        goto L_0x003f;
    L_0x0065:
        if (r5 == 0) goto L_0x006c;
    L_0x0067:
        r0 = java.lang.Long.valueOf(r14);
        goto L_0x0071;
    L_0x006c:
        r0 = -r14;
        r0 = java.lang.Long.valueOf(r0);
    L_0x0071:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringNumberConversionsKt.toLongOrNull(java.lang.String, int):java.lang.Long");
    }
}
