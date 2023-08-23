package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.ThreadContextElement;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ThreadState {
    public final CoroutineContext context;
    private final ThreadContextElement[] elements;
    private final Object[] values;

    public ThreadState(CoroutineContext context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.values = new Object[i];
        this.elements = new ThreadContextElement[i];
    }

    public final void restore(CoroutineContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ThreadContextElement[] threadContextElementArr = this.elements;
        int length = threadContextElementArr.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i = length - 1;
            ThreadContextElement threadContextElement = threadContextElementArr[length];
            Intrinsics.checkNotNull(threadContextElement);
            ((CoroutineId) threadContextElement).restoreThreadContext(context, this.values[length]);
            if (i >= 0) {
                length = i;
            } else {
                return;
            }
        }
    }
}
