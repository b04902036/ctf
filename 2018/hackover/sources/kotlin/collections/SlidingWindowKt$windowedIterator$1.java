package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.SequenceBuilder;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "Lkotlin/coroutines/experimental/SequenceBuilder;", "", "invoke", "(Lkotlin/coroutines/experimental/SequenceBuilder;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 10})
/* compiled from: SlidingWindow.kt */
final class SlidingWindowKt$windowedIterator$1 extends CoroutineImpl implements Function2<SequenceBuilder<? super List<? extends T>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Iterator $iterator;
    final /* synthetic */ boolean $partialWindows;
    final /* synthetic */ boolean $reuseBuffer;
    final /* synthetic */ int $size;
    final /* synthetic */ int $step;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    private SequenceBuilder p$;

    SlidingWindowKt$windowedIterator$1(int i, int i2, Iterator it, boolean z, boolean z2, Continuation continuation) {
        this.$step = i;
        this.$size = i2;
        this.$iterator = it;
        this.$reuseBuffer = z;
        this.$partialWindows = z2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull SequenceBuilder<? super List<? extends T>> sequenceBuilder, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(sequenceBuilder, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        Continuation slidingWindowKt$windowedIterator$1 = new SlidingWindowKt$windowedIterator$1(this.$step, this.$size, this.$iterator, this.$reuseBuffer, this.$partialWindows, continuation);
        slidingWindowKt$windowedIterator$1.p$ = sequenceBuilder;
        return slidingWindowKt$windowedIterator$1;
    }

    @Nullable
    public final Object invoke(@NotNull SequenceBuilder<? super List<? extends T>> sequenceBuilder, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(sequenceBuilder, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        return ((SlidingWindowKt$windowedIterator$1) create((SequenceBuilder) sequenceBuilder, (Continuation) continuation)).doResume(Unit.INSTANCE, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:66:0x0141  */
    /* JADX WARNING: Missing block: B:25:0x0084, code:
            if (r0.hasNext() == false) goto L_0x00c0;
     */
    /* JADX WARNING: Missing block: B:26:0x0086, code:
            r5 = r0.next();
     */
    /* JADX WARNING: Missing block: B:27:0x008a, code:
            if (r3 <= 0) goto L_0x008f;
     */
    /* JADX WARNING: Missing block: B:28:0x008c, code:
            r3 = r3 - 1;
     */
    /* JADX WARNING: Missing block: B:29:0x008f, code:
            r2.add(r5);
     */
    /* JADX WARNING: Missing block: B:30:0x0098, code:
            if (r2.size() != r9.$size) goto L_0x0080;
     */
    /* JADX WARNING: Missing block: B:31:0x009a, code:
            r9.L$0 = r4;
            r9.I$0 = r10;
            r9.L$1 = r2;
            r9.I$1 = r3;
            r9.L$2 = r5;
            r9.L$3 = r0;
            r9.label = 1;
     */
    /* JADX WARNING: Missing block: B:32:0x00ac, code:
            if (r4.yield(r2, r9) != r11) goto L_0x00af;
     */
    /* JADX WARNING: Missing block: B:33:0x00ae, code:
            return r11;
     */
    /* JADX WARNING: Missing block: B:35:0x00b1, code:
            if (r9.$reuseBuffer == false) goto L_0x00b7;
     */
    /* JADX WARNING: Missing block: B:36:0x00b3, code:
            r2.clear();
     */
    /* JADX WARNING: Missing block: B:37:0x00b7, code:
            r2 = new java.util.ArrayList(r9.$size);
     */
    /* JADX WARNING: Missing block: B:38:0x00be, code:
            r3 = r10;
     */
    /* JADX WARNING: Missing block: B:40:0x00c8, code:
            if ((r2.isEmpty() ^ 1) == 0) goto L_0x0181;
     */
    /* JADX WARNING: Missing block: B:42:0x00cc, code:
            if (r9.$partialWindows != false) goto L_0x00d6;
     */
    /* JADX WARNING: Missing block: B:44:0x00d4, code:
            if (r2.size() != r9.$size) goto L_0x0181;
     */
    /* JADX WARNING: Missing block: B:45:0x00d6, code:
            r9.I$0 = r10;
            r9.L$0 = r2;
            r9.I$1 = r3;
            r9.label = 2;
     */
    /* JADX WARNING: Missing block: B:46:0x00e3, code:
            if (r4.yield(r2, r9) != r11) goto L_0x0181;
     */
    /* JADX WARNING: Missing block: B:47:0x00e5, code:
            return r11;
     */
    /* JADX WARNING: Missing block: B:50:0x00f7, code:
            if (r0.hasNext() == false) goto L_0x0132;
     */
    /* JADX WARNING: Missing block: B:51:0x00f9, code:
            r11 = r0.next();
            r2.add(r11);
     */
    /* JADX WARNING: Missing block: B:52:0x0104, code:
            if (r2.isFull() == false) goto L_0x00f3;
     */
    /* JADX WARNING: Missing block: B:54:0x0108, code:
            if (r9.$reuseBuffer == false) goto L_0x010e;
     */
    /* JADX WARNING: Missing block: B:55:0x010a, code:
            r5 = r2;
     */
    /* JADX WARNING: Missing block: B:56:0x010e, code:
            r5 = new java.util.ArrayList(r2);
     */
    /* JADX WARNING: Missing block: B:57:0x0118, code:
            r9.L$0 = r4;
            r9.I$0 = r3;
            r9.L$1 = r2;
            r9.L$2 = r11;
            r9.L$3 = r0;
            r9.label = 3;
     */
    /* JADX WARNING: Missing block: B:58:0x0129, code:
            if (r4.yield(r5, r9) != r10) goto L_0x012c;
     */
    /* JADX WARNING: Missing block: B:59:0x012b, code:
            return r10;
     */
    /* JADX WARNING: Missing block: B:60:0x012c, code:
            r2.removeFirst(r9.$step);
     */
    /* JADX WARNING: Missing block: B:62:0x0134, code:
            if (r9.$partialWindows == false) goto L_0x0181;
     */
    /* JADX WARNING: Missing block: B:63:0x0136, code:
            r0 = r2;
            r2 = r3;
            r3 = r4;
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object doResume(@org.jetbrains.annotations.Nullable java.lang.Object r10, @org.jetbrains.annotations.Nullable java.lang.Throwable r11) {
        /*
        r9 = this;
        r10 = kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
        r0 = r9.label;
        r1 = 1;
        switch(r0) {
            case 0: goto L_0x0066;
            case 1: goto L_0x004e;
            case 2: goto L_0x0041;
            case 3: goto L_0x002c;
            case 4: goto L_0x001d;
            case 5: goto L_0x0012;
            default: goto L_0x000a;
        };
    L_0x000a:
        r9 = new java.lang.IllegalStateException;
        r10 = "call to 'resume' before 'invoke' with coroutine";
        r9.<init>(r10);
        throw r9;
    L_0x0012:
        r10 = r9.L$0;
        r10 = (kotlin.collections.RingBuffer) r10;
        r9 = r9.I$0;
        if (r11 != 0) goto L_0x001c;
    L_0x001a:
        goto L_0x0181;
    L_0x001c:
        throw r11;
    L_0x001d:
        r0 = r9.L$1;
        r0 = (kotlin.collections.RingBuffer) r0;
        r2 = r9.I$0;
        r3 = r9.L$0;
        r3 = (kotlin.coroutines.experimental.SequenceBuilder) r3;
        if (r11 != 0) goto L_0x002b;
    L_0x0029:
        goto L_0x0163;
    L_0x002b:
        throw r11;
    L_0x002c:
        r0 = r9.L$3;
        r0 = (java.util.Iterator) r0;
        r2 = r9.L$2;
        r2 = r9.L$1;
        r2 = (kotlin.collections.RingBuffer) r2;
        r3 = r9.I$0;
        r4 = r9.L$0;
        r4 = (kotlin.coroutines.experimental.SequenceBuilder) r4;
        if (r11 != 0) goto L_0x0040;
    L_0x003e:
        goto L_0x012c;
    L_0x0040:
        throw r11;
    L_0x0041:
        r10 = r9.I$1;
        r10 = r9.L$0;
        r10 = (java.util.ArrayList) r10;
        r9 = r9.I$0;
        if (r11 != 0) goto L_0x004d;
    L_0x004b:
        goto L_0x0181;
    L_0x004d:
        throw r11;
    L_0x004e:
        r0 = r9.L$3;
        r0 = (java.util.Iterator) r0;
        r2 = r9.L$2;
        r2 = r9.I$1;
        r2 = r9.L$1;
        r2 = (java.util.ArrayList) r2;
        r3 = r9.I$0;
        r4 = r9.L$0;
        r4 = (kotlin.coroutines.experimental.SequenceBuilder) r4;
        if (r11 != 0) goto L_0x0065;
    L_0x0062:
        r11 = r10;
        r10 = r3;
        goto L_0x00af;
    L_0x0065:
        throw r11;
    L_0x0066:
        if (r11 != 0) goto L_0x0184;
    L_0x0068:
        r11 = r9.p$;
        r0 = r9.$step;
        r2 = r9.$size;
        r0 = r0 - r2;
        if (r0 < 0) goto L_0x00e6;
    L_0x0071:
        r2 = new java.util.ArrayList;
        r3 = r9.$size;
        r2.<init>(r3);
        r3 = 0;
        r4 = r9.$iterator;
        r8 = r11;
        r11 = r10;
        r10 = r0;
        r0 = r4;
        r4 = r8;
    L_0x0080:
        r5 = r0.hasNext();
        if (r5 == 0) goto L_0x00c0;
    L_0x0086:
        r5 = r0.next();
        if (r3 <= 0) goto L_0x008f;
    L_0x008c:
        r3 = r3 + -1;
        goto L_0x0080;
    L_0x008f:
        r2.add(r5);
        r6 = r2.size();
        r7 = r9.$size;
        if (r6 != r7) goto L_0x0080;
    L_0x009a:
        r9.L$0 = r4;
        r9.I$0 = r10;
        r9.L$1 = r2;
        r9.I$1 = r3;
        r9.L$2 = r5;
        r9.L$3 = r0;
        r9.label = r1;
        r3 = r4.yield(r2, r9);
        if (r3 != r11) goto L_0x00af;
    L_0x00ae:
        return r11;
    L_0x00af:
        r3 = r9.$reuseBuffer;
        if (r3 == 0) goto L_0x00b7;
    L_0x00b3:
        r2.clear();
        goto L_0x00be;
    L_0x00b7:
        r2 = new java.util.ArrayList;
        r3 = r9.$size;
        r2.<init>(r3);
    L_0x00be:
        r3 = r10;
        goto L_0x0080;
    L_0x00c0:
        r0 = r2;
        r0 = (java.util.Collection) r0;
        r0 = r0.isEmpty();
        r0 = r0 ^ r1;
        if (r0 == 0) goto L_0x0181;
    L_0x00ca:
        r0 = r9.$partialWindows;
        if (r0 != 0) goto L_0x00d6;
    L_0x00ce:
        r0 = r2.size();
        r1 = r9.$size;
        if (r0 != r1) goto L_0x0181;
    L_0x00d6:
        r9.I$0 = r10;
        r9.L$0 = r2;
        r9.I$1 = r3;
        r10 = 2;
        r9.label = r10;
        r9 = r4.yield(r2, r9);
        if (r9 != r11) goto L_0x0181;
    L_0x00e5:
        return r11;
    L_0x00e6:
        r2 = new kotlin.collections.RingBuffer;
        r3 = r9.$size;
        r2.<init>(r3);
        r3 = r9.$iterator;
        r4 = r11;
        r8 = r3;
        r3 = r0;
        r0 = r8;
    L_0x00f3:
        r11 = r0.hasNext();
        if (r11 == 0) goto L_0x0132;
    L_0x00f9:
        r11 = r0.next();
        r2.add(r11);
        r5 = r2.isFull();
        if (r5 == 0) goto L_0x00f3;
    L_0x0106:
        r5 = r9.$reuseBuffer;
        if (r5 == 0) goto L_0x010e;
    L_0x010a:
        r5 = r2;
        r5 = (java.util.List) r5;
        goto L_0x0118;
    L_0x010e:
        r5 = new java.util.ArrayList;
        r6 = r2;
        r6 = (java.util.Collection) r6;
        r5.<init>(r6);
        r5 = (java.util.List) r5;
    L_0x0118:
        r9.L$0 = r4;
        r9.I$0 = r3;
        r9.L$1 = r2;
        r9.L$2 = r11;
        r9.L$3 = r0;
        r11 = 3;
        r9.label = r11;
        r11 = r4.yield(r5, r9);
        if (r11 != r10) goto L_0x012c;
    L_0x012b:
        return r10;
    L_0x012c:
        r11 = r9.$step;
        r2.removeFirst(r11);
        goto L_0x00f3;
    L_0x0132:
        r11 = r9.$partialWindows;
        if (r11 == 0) goto L_0x0181;
    L_0x0136:
        r0 = r2;
        r2 = r3;
        r3 = r4;
    L_0x0139:
        r11 = r0.access$getSize$p();
        r4 = r9.$step;
        if (r11 <= r4) goto L_0x0169;
    L_0x0141:
        r11 = r9.$reuseBuffer;
        if (r11 == 0) goto L_0x0149;
    L_0x0145:
        r11 = r0;
        r11 = (java.util.List) r11;
        goto L_0x0153;
    L_0x0149:
        r11 = new java.util.ArrayList;
        r4 = r0;
        r4 = (java.util.Collection) r4;
        r11.<init>(r4);
        r11 = (java.util.List) r11;
    L_0x0153:
        r9.L$0 = r3;
        r9.I$0 = r2;
        r9.L$1 = r0;
        r4 = 4;
        r9.label = r4;
        r11 = r3.yield(r11, r9);
        if (r11 != r10) goto L_0x0163;
    L_0x0162:
        return r10;
    L_0x0163:
        r11 = r9.$step;
        r0.removeFirst(r11);
        goto L_0x0139;
    L_0x0169:
        r11 = r0;
        r11 = (java.util.Collection) r11;
        r11 = r11.isEmpty();
        r11 = r11 ^ r1;
        if (r11 == 0) goto L_0x0181;
    L_0x0173:
        r9.I$0 = r2;
        r9.L$0 = r0;
        r11 = 5;
        r9.label = r11;
        r9 = r3.yield(r0, r9);
        if (r9 != r10) goto L_0x0181;
    L_0x0180:
        return r10;
    L_0x0181:
        r9 = kotlin.Unit.INSTANCE;
        return r9;
    L_0x0184:
        throw r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.SlidingWindowKt$windowedIterator$1.doResume(java.lang.Object, java.lang.Throwable):java.lang.Object");
    }
}
