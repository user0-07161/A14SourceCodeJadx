package kotlinx.coroutines;

import kotlinx.atomicfu.AtomicBoolean;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class CompletedExceptionally {
    private final AtomicBoolean _handled;
    public final Throwable cause;

    public CompletedExceptionally(Throwable th, boolean z) {
        this.cause = th;
        this._handled = new AtomicBoolean(z);
    }

    public final boolean getHandled() {
        return this._handled.getValue();
    }

    public final boolean makeHandled() {
        return this._handled.compareAndSet();
    }

    public final String toString() {
        String classSimpleName = DebugStringsKt.getClassSimpleName(this);
        return classSimpleName + "[" + this.cause + "]";
    }
}
