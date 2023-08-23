package kotlinx.coroutines;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LazyStandaloneCoroutine extends StandaloneCoroutine {
    private final Continuation continuation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyStandaloneCoroutine(CoroutineContext parentContext, Function2 block) {
        super(parentContext, false);
        Intrinsics.checkNotNullParameter(parentContext, "parentContext");
        Intrinsics.checkNotNullParameter(block, "block");
        this.continuation = IntrinsicsKt.createCoroutineUnintercepted(this, this, block);
    }

    @Override // kotlinx.coroutines.JobSupport
    protected final void onStart$1() {
        Continuation continuation = this.continuation;
        Intrinsics.checkNotNullParameter(continuation, "<this>");
        try {
            DispatchedContinuationKt.resumeCancellableWith(IntrinsicsKt.intercepted(continuation), Unit.INSTANCE, null);
        } catch (Throwable th) {
            resumeWith(ResultKt.createFailure(th));
            throw th;
        }
    }
}
