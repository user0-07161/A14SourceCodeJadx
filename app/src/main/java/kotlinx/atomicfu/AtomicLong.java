package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlinx.atomicfu.TraceBase;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AtomicLong {
    private static final AtomicLongFieldUpdater FU = AtomicLongFieldUpdater.newUpdater(AtomicLong.class, "value");
    private final TraceBase trace = TraceBase.None.INSTANCE;
    private volatile long value;

    public AtomicLong(long j) {
        this.value = j;
    }

    public final long addAndGet(long j) {
        long addAndGet = FU.addAndGet(this, j);
        TraceBase.None none = TraceBase.None.INSTANCE;
        TraceBase traceBase = this.trace;
        if (traceBase != none) {
            traceBase.getClass();
            TraceBase.append("addAndGet(" + j + "):" + addAndGet);
        }
        return addAndGet;
    }

    public final boolean compareAndSet(long j, long j2) {
        boolean compareAndSet = FU.compareAndSet(this, j, j2);
        if (compareAndSet) {
            TraceBase.None none = TraceBase.None.INSTANCE;
            TraceBase traceBase = this.trace;
            if (traceBase != none) {
                traceBase.getClass();
                TraceBase.append("CAS(" + j + ", " + j2 + ")");
            }
        }
        return compareAndSet;
    }

    public final long getAndDecrement() {
        long andDecrement = FU.getAndDecrement(this);
        TraceBase.None none = TraceBase.None.INSTANCE;
        TraceBase traceBase = this.trace;
        if (traceBase != none) {
            traceBase.getClass();
            TraceBase.append("getAndDec():" + andDecrement);
        }
        return andDecrement;
    }

    public final long getValue() {
        return this.value;
    }

    public final long incrementAndGet() {
        long incrementAndGet = FU.incrementAndGet(this);
        TraceBase.None none = TraceBase.None.INSTANCE;
        TraceBase traceBase = this.trace;
        if (traceBase != none) {
            traceBase.getClass();
            TraceBase.append("incAndGet():" + incrementAndGet);
        }
        return incrementAndGet;
    }

    public final void setValue(long j) {
        this.value = j;
        TraceBase traceBase = this.trace;
        if (traceBase != TraceBase.None.INSTANCE) {
            traceBase.getClass();
            TraceBase.append("set(" + j + ")");
        }
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}
