package kotlinx.coroutines;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CoroutineId extends AbstractCoroutineContextElement implements ThreadContextElement {
    public abstract void restoreThreadContext(CoroutineContext coroutineContext, Object obj);

    public abstract Object updateThreadContext(CoroutineContext coroutineContext);
}
