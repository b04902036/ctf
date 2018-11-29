package kotlin.internal;

import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0001\u001a\b\u0010\b\u001a\u00020\u0005H\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "apiVersionIsAtLeast", "", "major", "", "minor", "patch", "getJavaVersion", "kotlin-stdlib"}, k = 2, mv = {1, 1, 10})
/* compiled from: PlatformImplementations.kt */
public final class PlatformImplementationsKt {
    @NotNull
    @JvmField
    public static final PlatformImplementations IMPLEMENTATIONS;

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:9:?, code:
            r1 = java.lang.Class.forName("kotlin.internal.JRE8PlatformImplementations").newInstance();
     */
    /* JADX WARNING: Missing block: B:10:0x002a, code:
            if (r1 != null) goto L_0x002c;
     */
    /* JADX WARNING: Missing block: B:11:0x002c, code:
            r1 = (kotlin.internal.PlatformImplementations) r1;
     */
    /* JADX WARNING: Missing block: B:13:0x0036, code:
            throw new kotlin.TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
     */
    /* JADX WARNING: Missing block: B:15:0x003a, code:
            if (r0 >= 65543) goto L_0x003c;
     */
    /* JADX WARNING: Missing block: B:17:?, code:
            r0 = java.lang.Class.forName("kotlin.internal.jdk7.JDK7PlatformImplementations").newInstance();
     */
    /* JADX WARNING: Missing block: B:18:0x0046, code:
            if (r0 != null) goto L_0x0048;
     */
    /* JADX WARNING: Missing block: B:19:0x0048, code:
            r1 = (kotlin.internal.PlatformImplementations) r0;
     */
    /* JADX WARNING: Missing block: B:21:0x0053, code:
            throw new kotlin.TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
     */
    /* JADX WARNING: Missing block: B:23:?, code:
            r0 = java.lang.Class.forName("kotlin.internal.JRE7PlatformImplementations").newInstance();
     */
    /* JADX WARNING: Missing block: B:24:0x005e, code:
            if (r0 != null) goto L_0x0060;
     */
    /* JADX WARNING: Missing block: B:25:0x0060, code:
            r1 = (kotlin.internal.PlatformImplementations) r0;
     */
    /* JADX WARNING: Missing block: B:27:0x006b, code:
            throw new kotlin.TypeCastException("null cannot be cast to non-null type kotlin.internal.PlatformImplementations");
     */
    /* JADX WARNING: Missing block: B:28:0x006c, code:
            r1 = new kotlin.internal.PlatformImplementations();
     */
    static {
        /*
        r0 = getJavaVersion();
        r1 = 65544; // 0x10008 float:9.1847E-41 double:3.2383E-319;
        if (r0 < r1) goto L_0x0037;
    L_0x0009:
        r1 = "kotlin.internal.jdk8.JDK8PlatformImplementations";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0020 }
        r1 = r1.newInstance();	 Catch:{ ClassNotFoundException -> 0x0020 }
        if (r1 == 0) goto L_0x0018;
    L_0x0015:
        r1 = (kotlin.internal.PlatformImplementations) r1;	 Catch:{ ClassNotFoundException -> 0x0020 }
        goto L_0x0071;
    L_0x0018:
        r1 = new kotlin.TypeCastException;	 Catch:{ ClassNotFoundException -> 0x0020 }
        r2 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";
        r1.<init>(r2);	 Catch:{ ClassNotFoundException -> 0x0020 }
        throw r1;	 Catch:{ ClassNotFoundException -> 0x0020 }
    L_0x0020:
        r1 = "kotlin.internal.JRE8PlatformImplementations";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x0037 }
        r1 = r1.newInstance();	 Catch:{ ClassNotFoundException -> 0x0037 }
        if (r1 == 0) goto L_0x002f;
    L_0x002c:
        r1 = (kotlin.internal.PlatformImplementations) r1;	 Catch:{ ClassNotFoundException -> 0x0037 }
        goto L_0x0071;
    L_0x002f:
        r1 = new kotlin.TypeCastException;	 Catch:{ ClassNotFoundException -> 0x0037 }
        r2 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";
        r1.<init>(r2);	 Catch:{ ClassNotFoundException -> 0x0037 }
        throw r1;	 Catch:{ ClassNotFoundException -> 0x0037 }
    L_0x0037:
        r1 = 65543; // 0x10007 float:9.1845E-41 double:3.23825E-319;
        if (r0 < r1) goto L_0x006c;
    L_0x003c:
        r0 = "kotlin.internal.jdk7.JDK7PlatformImplementations";
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x0054 }
        r0 = r0.newInstance();	 Catch:{ ClassNotFoundException -> 0x0054 }
        if (r0 == 0) goto L_0x004c;
    L_0x0048:
        r1 = r0;
        r1 = (kotlin.internal.PlatformImplementations) r1;	 Catch:{ ClassNotFoundException -> 0x0054 }
        goto L_0x0071;
    L_0x004c:
        r0 = new kotlin.TypeCastException;	 Catch:{ ClassNotFoundException -> 0x0054 }
        r1 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";
        r0.<init>(r1);	 Catch:{ ClassNotFoundException -> 0x0054 }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x0054 }
    L_0x0054:
        r0 = "kotlin.internal.JRE7PlatformImplementations";
        r0 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x006c }
        r0 = r0.newInstance();	 Catch:{ ClassNotFoundException -> 0x006c }
        if (r0 == 0) goto L_0x0064;
    L_0x0060:
        r1 = r0;
        r1 = (kotlin.internal.PlatformImplementations) r1;	 Catch:{ ClassNotFoundException -> 0x006c }
        goto L_0x0071;
    L_0x0064:
        r0 = new kotlin.TypeCastException;	 Catch:{ ClassNotFoundException -> 0x006c }
        r1 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";
        r0.<init>(r1);	 Catch:{ ClassNotFoundException -> 0x006c }
        throw r0;	 Catch:{ ClassNotFoundException -> 0x006c }
    L_0x006c:
        r1 = new kotlin.internal.PlatformImplementations;
        r1.<init>();
    L_0x0071:
        IMPLEMENTATIONS = r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.internal.PlatformImplementationsKt.<clinit>():void");
    }

    private static final int getJavaVersion() {
        String property = System.getProperty("java.specification.version");
        int i = 65542;
        if (property == null) {
            return 65542;
        }
        CharSequence charSequence = property;
        int indexOf$default = StringsKt__StringsKt.indexOf$default(charSequence, '.', 0, false, 6, null);
        if (indexOf$default < 0) {
            try {
                i = Integer.parseInt(property) * 65536;
            } catch (NumberFormatException unused) {
                return i;
            }
        }
        int i2 = indexOf$default + 1;
        int indexOf$default2 = StringsKt__StringsKt.indexOf$default(charSequence, '.', i2, false, 4, null);
        if (indexOf$default2 < 0) {
            indexOf$default2 = property.length();
        }
        if (property != null) {
            String substring = property.substring(0, indexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            if (property != null) {
                property = property.substring(i2, indexOf$default2);
                Intrinsics.checkExpressionValueIsNotNull(property, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                try {
                    i = (Integer.parseInt(substring) * 65536) + Integer.parseInt(property);
                } catch (NumberFormatException unused2) {
                    return i;
                }
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final boolean apiVersionIsAtLeast(int i, int i2, int i3) {
        return KotlinVersion.CURRENT.isAtLeast(i, i2, i3);
    }
}
