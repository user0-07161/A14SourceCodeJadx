package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.atomicfu.TraceBase;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AtomicRef {
    private static final AtomicReferenceFieldUpdater FU = AtomicReferenceFieldUpdater.newUpdater(AtomicRef.class, Object.class, "value");
    private final TraceBase trace = TraceBase.None.INSTANCE;
    private volatile Object value;

    public AtomicRef(Object obj) {
        this.value = obj;
    }

    public final boolean compareAndSet(Object obj, Object obj2) {
        boolean compareAndSet = FU.compareAndSet(this, obj, obj2);
        if (compareAndSet) {
            TraceBase.None none = TraceBase.None.INSTANCE;
            TraceBase traceBase = this.trace;
            if (traceBase != none) {
                traceBase.getClass();
                TraceBase.append("CAS(" + obj + ", " + obj2 + ")");
            }
        }
        return compareAndSet;
    }

    public final Object getAndSet(Object obj) {
        Object andSet = FU.getAndSet(this, obj);
        TraceBase.None none = TraceBase.None.INSTANCE;
        TraceBase traceBase = this.trace;
        if (traceBase != none) {
            traceBase.getClass();
            TraceBase.append("getAndSet(" + obj + "):" + andSet);
        }
        return andSet;
    }

    public final Object getValue() {
        return this.value;
    }

    public final void lazySet(Object obj) {
        FU.lazySet(this, obj);
        TraceBase.None none = TraceBase.None.INSTANCE;
        TraceBase traceBase = this.trace;
        if (traceBase != none) {
            traceBase.getClass();
            TraceBase.append("lazySet(" + obj + ")");
        }
    }

    public final void setValue(Object obj) {
        this.value = obj;
        TraceBase traceBase = this.trace;
        if (traceBase != TraceBase.None.INSTANCE) {
            traceBase.getClass();
            TraceBase.append("set(" + obj + ")");
        }
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}
