package kotlin.coroutines.experimental;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.experimental.CoroutineContext.DefaultImpls;
import kotlin.coroutines.experimental.CoroutineContext.Element;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0000H\u0002J\u0013\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J5\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u0002H\u00122\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00120\u0015H\u0016¢\u0006\u0002\u0010\u0016J(\u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\b\b\u0000\u0010\u0018*\u00020\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001aH\u0002¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0014\u0010\u001e\u001a\u00020\u00012\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001aH\u0016J\b\u0010\u001f\u001a\u00020\u001dH\u0002J\b\u0010 \u001a\u00020!H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\""}, d2 = {"Lkotlin/coroutines/experimental/CombinedContext;", "Lkotlin/coroutines/experimental/CoroutineContext;", "left", "element", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlin/coroutines/experimental/CoroutineContext$Element;)V", "getElement", "()Lkotlin/coroutines/experimental/CoroutineContext$Element;", "getLeft", "()Lkotlin/coroutines/experimental/CoroutineContext;", "contains", "", "containsAll", "context", "equals", "other", "", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "get", "E", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "(Lkotlin/coroutines/experimental/CoroutineContext$Key;)Lkotlin/coroutines/experimental/CoroutineContext$Element;", "hashCode", "", "minusKey", "size", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 10})
/* compiled from: CoroutineContextImpl.kt */
public final class CombinedContext implements CoroutineContext {
    @NotNull
    private final Element element;
    @NotNull
    private final CoroutineContext left;

    public CombinedContext(@NotNull CoroutineContext coroutineContext, @NotNull Element element) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "left");
        Intrinsics.checkParameterIsNotNull(element, "element");
        this.left = coroutineContext;
        this.element = element;
    }

    @NotNull
    public final Element getElement() {
        return this.element;
    }

    @NotNull
    public final CoroutineContext getLeft() {
        return this.left;
    }

    @NotNull
    public CoroutineContext plus(@NotNull CoroutineContext coroutineContext) {
        Intrinsics.checkParameterIsNotNull(coroutineContext, "context");
        return DefaultImpls.plus(this, coroutineContext);
    }

    @Nullable
    public <E extends Element> E get(@NotNull Key<E> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        while (true) {
            this = this;
            E e = this.element.get(key);
            if (e != null) {
                return e;
            }
            this = this.left;
            if (!(this instanceof CombinedContext)) {
                return this.get(key);
            }
        }
    }

    public <R> R fold(R r, @NotNull Function2<? super R, ? super Element, ? extends R> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "operation");
        return function2.invoke(this.left.fold(r, function2), this.element);
    }

    @NotNull
    public CoroutineContext minusKey(@NotNull Key<?> key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (this.element.get(key) != null) {
            return this.left;
        }
        CoroutineContext coroutineContext;
        CoroutineContext minusKey = this.left.minusKey(key);
        if (minusKey == this.left) {
            coroutineContext = this;
        } else if (minusKey == EmptyCoroutineContext.INSTANCE) {
            coroutineContext = this.element;
        } else {
            coroutineContext = new CombinedContext(minusKey, this.element);
        }
        return coroutineContext;
    }

    private final int size() {
        return this.left instanceof CombinedContext ? ((CombinedContext) this.left).size() + 1 : 2;
    }

    private final boolean contains(Element element) {
        return Intrinsics.areEqual(get(element.getKey()), (Object) element);
    }

    private final boolean containsAll(CombinedContext combinedContext) {
        while (contains(combinedContext.element)) {
            CoroutineContext coroutineContext = combinedContext.left;
            if (coroutineContext instanceof CombinedContext) {
                combinedContext = (CombinedContext) coroutineContext;
            } else if (coroutineContext != null) {
                return contains((Element) coroutineContext);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.CoroutineContext.Element");
            }
        }
        return false;
    }

    /* JADX WARNING: Missing block: B:7:0x0019, code:
            if (r3.containsAll(r2) != false) goto L_0x001e;
     */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
        r2 = this;
        r0 = r2;
        r0 = (kotlin.coroutines.experimental.CombinedContext) r0;
        if (r0 == r3) goto L_0x001e;
    L_0x0005:
        r0 = r3 instanceof kotlin.coroutines.experimental.CombinedContext;
        if (r0 == 0) goto L_0x001c;
    L_0x0009:
        r3 = (kotlin.coroutines.experimental.CombinedContext) r3;
        r0 = r3.size();
        r1 = r2.size();
        if (r0 != r1) goto L_0x001c;
    L_0x0015:
        r2 = r3.containsAll(r2);
        if (r2 == 0) goto L_0x001c;
    L_0x001b:
        goto L_0x001e;
    L_0x001c:
        r2 = 0;
        goto L_0x001f;
    L_0x001e:
        r2 = 1;
    L_0x001f:
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.coroutines.experimental.CombinedContext.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return this.left.hashCode() + this.element.hashCode();
    }

    @NotNull
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        stringBuilder.append((String) fold("", CombinedContext$toString$1.INSTANCE));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
