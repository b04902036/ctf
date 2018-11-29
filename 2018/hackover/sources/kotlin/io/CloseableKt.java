package kotlin.io;

import java.io.Closeable;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001a;\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\bø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\b\n\u0006\b\u0011(\n0\u0001¨\u0006\f"}, d2 = {"closeFinally", "", "Ljava/io/Closeable;", "cause", "", "use", "R", "T", "block", "Lkotlin/Function1;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 10})
@JvmName(name = "CloseableKt")
/* compiled from: Closeable.kt */
public final class CloseableKt {
    /* JADX WARNING: Missing block: B:14:0x0024, code:
            kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Missing block: B:15:0x002b, code:
            if (kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(1, 1, 0) == false) goto L_0x002d;
     */
    /* JADX WARNING: Missing block: B:16:0x002d, code:
            if (r3 != null) goto L_0x002f;
     */
    /* JADX WARNING: Missing block: B:17:0x002f, code:
            if (r0 == null) goto L_0x0031;
     */
    /* JADX WARNING: Missing block: B:18:0x0031, code:
            r3.close();
     */
    /* JADX WARNING: Missing block: B:20:?, code:
            r3.close();
     */
    /* JADX WARNING: Missing block: B:21:0x0039, code:
            closeFinally(r3, r0);
     */
    /* JADX WARNING: Missing block: B:22:0x003c, code:
            kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    @kotlin.internal.InlineOnly
    private static final <T extends java.io.Closeable, R> R use(T r3, kotlin.jvm.functions.Function1<? super T, ? extends R> r4) {
        /*
        r0 = 0;
        r0 = (java.lang.Throwable) r0;
        r1 = 0;
        r2 = 1;
        r4 = r4.invoke(r3);	 Catch:{ Throwable -> 0x0022 }
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r1 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r2, r2, r1);
        if (r1 == 0) goto L_0x0016;
    L_0x0012:
        closeFinally(r3, r0);
        goto L_0x001c;
    L_0x0016:
        if (r3 != 0) goto L_0x0019;
    L_0x0018:
        goto L_0x001c;
    L_0x0019:
        r3.close();
    L_0x001c:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        return r4;
    L_0x0020:
        r4 = move-exception;
        goto L_0x0024;
    L_0x0022:
        r0 = move-exception;
        throw r0;	 Catch:{ all -> 0x0020 }
    L_0x0024:
        kotlin.jvm.internal.InlineMarker.finallyStart(r2);
        r1 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r2, r2, r1);
        if (r1 != 0) goto L_0x0039;
    L_0x002d:
        if (r3 == 0) goto L_0x003c;
    L_0x002f:
        if (r0 != 0) goto L_0x0035;
    L_0x0031:
        r3.close();
        goto L_0x003c;
    L_0x0035:
        r3.close();	 Catch:{ Throwable -> 0x003c }
        goto L_0x003c;
    L_0x0039:
        closeFinally(r3, r0);
    L_0x003c:
        kotlin.jvm.internal.InlineMarker.finallyEnd(r2);
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.CloseableKt.use(java.io.Closeable, kotlin.jvm.functions.Function1):R");
    }

    @SinceKotlin(version = "1.1")
    @PublishedApi
    public static final void closeFinally(@Nullable Closeable closeable, @Nullable Throwable th) {
        if (closeable != null) {
            if (th == null) {
                closeable.close();
                return;
            }
            try {
                closeable.close();
            } catch (Throwable th2) {
                ExceptionsKt__ExceptionsKt.addSuppressed(th, th2);
            }
        }
    }
}
