package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlinx.atomicfu.TraceBase;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AtomicInt {
    private static final AtomicIntegerFieldUpdater FU = AtomicIntegerFieldUpdater.newUpdater(AtomicInt.class, "value");
    private final TraceBase trace = TraceBase.None.INSTANCE;
    private volatile int value = 0;

    public final boolean compareAndSet(int i, int i2) {
        boolean compareAndSet = FU.compareAndSet(this, i, i2);
        if (compareAndSet) {
            TraceBase.None none = TraceBase.None.INSTANCE;
            TraceBase traceBase = this.trace;
            if (traceBase != none) {
                traceBase.getClass();
                TraceBase.append("CAS(" + i + ", " + i2 + ")");
            }
        }
        return compareAndSet;
    }

    public final void decrementAndGet() {
        int decrementAndGet = FU.decrementAndGet(this);
        TraceBase.None none = TraceBase.None.INSTANCE;
        TraceBase traceBase = this.trace;
        if (traceBase != none) {
            traceBase.getClass();
            TraceBase.append("decAndGet():" + decrementAndGet);
        }
    }

    public final int getValue() {
        return this.value;
    }

    public final void incrementAndGet() {
        int incrementAndGet = FU.incrementAndGet(this);
        TraceBase.None none = TraceBase.None.INSTANCE;
        TraceBase traceBase = this.trace;
        if (traceBase != none) {
            traceBase.getClass();
            TraceBase.append("incAndGet():" + incrementAndGet);
        }
    }

    public final void setValue(int i) {
        this.value = i;
        TraceBase traceBase = this.trace;
        if (traceBase != TraceBase.None.INSTANCE) {
            traceBase.getClass();
            TraceBase.append("set(" + i + ")");
        }
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}
