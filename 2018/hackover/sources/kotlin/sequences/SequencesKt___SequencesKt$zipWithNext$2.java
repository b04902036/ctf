package kotlin.sequences;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.SequenceBuilder;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "R", "Lkotlin/coroutines/experimental/SequenceBuilder;", "invoke", "(Lkotlin/coroutines/experimental/SequenceBuilder;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 10})
/* compiled from: _Sequences.kt */
final class SequencesKt___SequencesKt$zipWithNext$2 extends CoroutineImpl implements Function2<SequenceBuilder<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    private SequenceBuilder p$;
    final /* synthetic */ Sequence receiver$0;

    SequencesKt___SequencesKt$zipWithNext$2(Sequence sequence, Function2 function2, Continuation continuation) {
        this.receiver$0 = sequence;
        this.$transform = function2;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull SequenceBuilder<? super R> sequenceBuilder, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(sequenceBuilder, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        Continuation sequencesKt___SequencesKt$zipWithNext$2 = new SequencesKt___SequencesKt$zipWithNext$2(this.receiver$0, this.$transform, continuation);
        sequencesKt___SequencesKt$zipWithNext$2.p$ = sequenceBuilder;
        return sequencesKt___SequencesKt$zipWithNext$2;
    }

    @Nullable
    public final Object invoke(@NotNull SequenceBuilder<? super R> sequenceBuilder, @NotNull Continuation<? super Unit> continuation) {
        Intrinsics.checkParameterIsNotNull(sequenceBuilder, "$receiver");
        Intrinsics.checkParameterIsNotNull(continuation, "continuation");
        return ((SequencesKt___SequencesKt$zipWithNext$2) create((SequenceBuilder) sequenceBuilder, (Continuation) continuation)).doResume(Unit.INSTANCE, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0044  */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object doResume(@org.jetbrains.annotations.Nullable java.lang.Object r5, @org.jetbrains.annotations.Nullable java.lang.Throwable r6) {
        /*
        r4 = this;
        r5 = kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
        r0 = r4.label;
        switch(r0) {
            case 0: goto L_0x0023;
            case 1: goto L_0x0011;
            default: goto L_0x0009;
        };
    L_0x0009:
        r4 = new java.lang.IllegalStateException;
        r5 = "call to 'resume' before 'invoke' with coroutine";
        r4.<init>(r5);
        throw r4;
    L_0x0011:
        r0 = r4.L$3;
        r1 = r4.L$2;
        r1 = r4.L$1;
        r1 = (java.util.Iterator) r1;
        r2 = r4.L$0;
        r2 = (kotlin.coroutines.experimental.SequenceBuilder) r2;
        if (r6 != 0) goto L_0x0022;
    L_0x001f:
        r6 = r5;
    L_0x0020:
        r5 = r0;
        goto L_0x003e;
    L_0x0022:
        throw r6;
    L_0x0023:
        if (r6 != 0) goto L_0x0063;
    L_0x0025:
        r6 = r4.p$;
        r0 = r4.receiver$0;
        r0 = r0.iterator();
        r1 = r0.hasNext();
        if (r1 != 0) goto L_0x0036;
    L_0x0033:
        r4 = kotlin.Unit.INSTANCE;
        return r4;
    L_0x0036:
        r1 = r0.next();
        r2 = r6;
        r6 = r5;
        r5 = r1;
        r1 = r0;
    L_0x003e:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0060;
    L_0x0044:
        r0 = r1.next();
        r3 = r4.$transform;
        r3 = r3.invoke(r5, r0);
        r4.L$0 = r2;
        r4.L$1 = r1;
        r4.L$2 = r5;
        r4.L$3 = r0;
        r5 = 1;
        r4.label = r5;
        r5 = r2.yield(r3, r4);
        if (r5 != r6) goto L_0x0020;
    L_0x005f:
        return r6;
    L_0x0060:
        r4 = kotlin.Unit.INSTANCE;
        return r4;
    L_0x0063:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SequencesKt___SequencesKt$zipWithNext$2.doResume(java.lang.Object, java.lang.Throwable):java.lang.Object");
    }
}
