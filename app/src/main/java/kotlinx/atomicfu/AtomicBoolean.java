package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlinx.atomicfu.TraceBase;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AtomicBoolean {
    private static final AtomicIntegerFieldUpdater FU = AtomicIntegerFieldUpdater.newUpdater(AtomicBoolean.class, "_value");
    private volatile int _value;
    private final TraceBase trace = TraceBase.None.INSTANCE;

    public AtomicBoolean(boolean z) {
        this._value = z ? 1 : 0;
    }

    public final boolean compareAndSet() {
        boolean compareAndSet = FU.compareAndSet(this, 0, 1);
        if (compareAndSet) {
            TraceBase.None none = TraceBase.None.INSTANCE;
            TraceBase traceBase = this.trace;
            if (traceBase != none) {
                traceBase.getClass();
                TraceBase.append("CAS(false, true)");
            }
        }
        return compareAndSet;
    }

    public final boolean getValue() {
        if (this._value != 0) {
            return true;
        }
        return false;
    }

    public final void setValue() {
        this._value = 1;
        TraceBase traceBase = this.trace;
        if (traceBase != TraceBase.None.INSTANCE) {
            traceBase.getClass();
            TraceBase.append("set(true)");
        }
    }

    public final String toString() {
        return String.valueOf(getValue());
    }
}
