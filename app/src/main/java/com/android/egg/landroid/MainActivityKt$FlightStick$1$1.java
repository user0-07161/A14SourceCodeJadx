package com.android.egg.landroid;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilter;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
@DebugMetadata(c = "com.android.egg.landroid.MainActivityKt$FlightStick$1$1", f = "MainActivity.kt", l = {338}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class MainActivityKt$FlightStick$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $onStickChanged;
    final /* synthetic */ MutableState $origin;
    final /* synthetic */ MutableState $target;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    @DebugMetadata(c = "com.android.egg.landroid.MainActivityKt$FlightStick$1$1$1", f = "MainActivity.kt", l = {339}, m = "invokeSuspend")
    /* renamed from: com.android.egg.landroid.MainActivityKt$FlightStick$1$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Function1 $onStickChanged;
        final /* synthetic */ MutableState $origin;
        final /* synthetic */ MutableState $target;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
        @DebugMetadata(c = "com.android.egg.landroid.MainActivityKt$FlightStick$1$1$1$1", f = "MainActivity.kt", l = {341, 347}, m = "invokeSuspend")
        /* renamed from: com.android.egg.landroid.MainActivityKt$FlightStick$1$1$1$1  reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public final class C00041 extends RestrictedSuspendLambda implements Function2 {
            final /* synthetic */ Function1 $onStickChanged;
            final /* synthetic */ MutableState $origin;
            final /* synthetic */ MutableState $target;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00041(MutableState mutableState, MutableState mutableState2, Function1 function1, Continuation continuation) {
                super(continuation);
                this.$origin = mutableState;
                this.$target = mutableState2;
                this.$onStickChanged = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                C00041 c00041 = new C00041(this.$origin, this.$target, this.$onStickChanged, continuation);
                c00041.L$0 = obj;
                return c00041;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation continuation) {
                return ((C00041) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:27:0x00ce, code lost:
                r5 = true;
             */
            /* JADX WARN: Code restructure failed: missing block: B:44:0x0106, code lost:
                throw new java.lang.ArithmeticException("Count overflow has happened.");
             */
            /* JADX WARN: Code restructure failed: missing block: B:45:0x0107, code lost:
                if (r5 != 1) goto L37;
             */
            /* JADX WARN: Removed duplicated region for block: B:25:0x00c2  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x00d3  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0062 -> B:17:0x0065). Please submit an issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r11) {
                /*
                    Method dump skipped, instructions count: 311
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.egg.landroid.MainActivityKt$FlightStick$1$1.AnonymousClass1.C00041.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(MutableState mutableState, MutableState mutableState2, Function1 function1, Continuation continuation) {
            super(2, continuation);
            this.$origin = mutableState;
            this.$target = mutableState2;
            this.$onStickChanged = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$origin, this.$target, this.$onStickChanged, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
            return ((AnonymousClass1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    ResultKt.throwOnFailure(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            } else {
                ResultKt.throwOnFailure(obj);
                C00041 c00041 = new C00041(this.$origin, this.$target, this.$onStickChanged, null);
                this.label = 1;
                if (((SuspendingPointerInputFilter) ((PointerInputScope) this.L$0)).awaitPointerEventScope(c00041, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivityKt$FlightStick$1$1(MutableState mutableState, MutableState mutableState2, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$origin = mutableState;
        this.$target = mutableState2;
        this.$onStickChanged = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MainActivityKt$FlightStick$1$1 mainActivityKt$FlightStick$1$1 = new MainActivityKt$FlightStick$1$1(this.$origin, this.$target, this.$onStickChanged, continuation);
        mainActivityKt$FlightStick$1$1.L$0 = obj;
        return mainActivityKt$FlightStick$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation continuation) {
        return ((MainActivityKt$FlightStick$1$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } else {
            ResultKt.throwOnFailure(obj);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$origin, this.$target, this.$onStickChanged, null);
            this.label = 1;
            if (ForEachGestureKt.forEachGesture((PointerInputScope) this.L$0, anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        return Unit.INSTANCE;
    }
}
