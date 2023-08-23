package com.android.egg.landroid;

import androidx.compose.animation.core.InfiniteAnimationPolicyKt$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.ui.platform.InfiniteAnimationPolicy;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
@DebugMetadata(c = "com.android.egg.landroid.MainActivityKt$Spaaaace$1$1", f = "MainActivity.kt", l = {403}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class MainActivityKt$Spaaaace$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ VisibleUniverse $u;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivityKt$Spaaaace$1$1(VisibleUniverse visibleUniverse, Continuation continuation) {
        super(2, continuation);
        this.$u = visibleUniverse;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MainActivityKt$Spaaaace$1$1(this.$u, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Function1 function1;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0 && i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        do {
            final VisibleUniverse visibleUniverse = this.$u;
            function1 = new Function1() { // from class: com.android.egg.landroid.MainActivityKt$Spaaaace$1$1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke(((Number) obj2).longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(long j) {
                    VisibleUniverse.this.simulateAndDrawFrame(j);
                }
            };
            this.label = 1;
            InfiniteAnimationPolicyKt$$ExternalSyntheticThrowCCEIfNotNull0.m(getContext().get(InfiniteAnimationPolicy.Key));
        } while (MonotonicFrameClockKt.withFrameNanos(function1, this) != coroutineSingletons);
        return coroutineSingletons;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((MainActivityKt$Spaaaace$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
