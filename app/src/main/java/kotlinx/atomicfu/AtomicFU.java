package kotlinx.atomicfu;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AtomicFU {
    public static final AtomicRef atomic(Object obj) {
        return new AtomicRef(obj);
    }

    public static final AtomicBoolean atomic(boolean z) {
        return new AtomicBoolean(z);
    }
}
