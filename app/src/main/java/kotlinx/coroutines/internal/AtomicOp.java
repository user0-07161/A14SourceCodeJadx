package kotlinx.coroutines.internal;

import kotlinx.atomicfu.AtomicRef;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AtomicOp extends OpDescriptor {
    private final AtomicRef _consensus = new AtomicRef(AtomicKt.NO_DECISION);

    public abstract void complete(Object obj, Object obj2);

    @Override // kotlinx.coroutines.internal.OpDescriptor
    public final Object perform(Object obj) {
        AtomicRef atomicRef = this._consensus;
        Object value = atomicRef.getValue();
        Symbol symbol = AtomicKt.NO_DECISION;
        if (value == symbol) {
            value = prepare(obj);
            Object value2 = atomicRef.getValue();
            if (value2 != symbol) {
                value = value2;
            } else if (!atomicRef.compareAndSet(symbol, value)) {
                value = atomicRef.getValue();
            }
        }
        complete(obj, value);
        return value;
    }

    public abstract Symbol prepare(Object obj);
}
