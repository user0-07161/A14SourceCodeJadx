package com.android.egg.landroid;

import androidx.compose.runtime.MutableState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
@DebugMetadata(c = "com.android.egg.landroid.MainActivityKt$Telemetry$1$1", f = "MainActivity.kt", l = {171, 173}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class MainActivityKt$Telemetry$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ MutableState $bottomVisible$delegate;
    final /* synthetic */ MutableState $topVisible$delegate;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivityKt$Telemetry$1$1(MutableState mutableState, MutableState mutableState2, Continuation continuation) {
        super(2, continuation);
        this.$bottomVisible$delegate = mutableState;
        this.$topVisible$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MainActivityKt$Telemetry$1$1(this.$bottomVisible$delegate, this.$topVisible$delegate, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    MainActivityKt.Telemetry$lambda$2(this.$topVisible$delegate, true);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        } else {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(1000L, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        MainActivityKt.Telemetry$lambda$5(this.$bottomVisible$delegate, true);
        this.label = 2;
        if (DelayKt.delay(1000L, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        MainActivityKt.Telemetry$lambda$2(this.$topVisible$delegate, true);
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((MainActivityKt$Telemetry$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
