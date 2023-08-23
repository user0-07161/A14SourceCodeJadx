package kotlinx.coroutines;

import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CancelHandler implements NotCompleted, Function1 {
    public abstract void invoke(Throwable th);
}
