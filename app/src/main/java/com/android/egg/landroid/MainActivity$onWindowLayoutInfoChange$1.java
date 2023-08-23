package com.android.egg.landroid;

import android.util.Log;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.RepeatOnLifecycleKt;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowInfoTrackerImpl;
import androidx.window.layout.WindowLayoutInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.ChannelFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
@DebugMetadata(c = "com.android.egg.landroid.MainActivity$onWindowLayoutInfoChange$1", f = "MainActivity.kt", l = {298}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class MainActivity$onWindowLayoutInfoChange$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ WindowInfoTracker $windowInfoTracker;
    int label;
    final /* synthetic */ MainActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    @DebugMetadata(c = "com.android.egg.landroid.MainActivity$onWindowLayoutInfoChange$1$1", f = "MainActivity.kt", l = {299}, m = "invokeSuspend")
    /* renamed from: com.android.egg.landroid.MainActivity$onWindowLayoutInfoChange$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ WindowInfoTracker $windowInfoTracker;
        int label;
        final /* synthetic */ MainActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(WindowInfoTracker windowInfoTracker, MainActivity mainActivity, Continuation continuation) {
            super(2, continuation);
            this.$windowInfoTracker = windowInfoTracker;
            this.this$0 = mainActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$windowInfoTracker, this.this$0, continuation);
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
                Flow windowLayoutInfo = ((WindowInfoTrackerImpl) this.$windowInfoTracker).windowLayoutInfo(this.this$0);
                final MainActivity mainActivity = this.this$0;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.egg.landroid.MainActivity.onWindowLayoutInfoChange.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(WindowLayoutInfo windowLayoutInfo2, Continuation continuation) {
                        MutableState mutableState;
                        MutableState mutableState2;
                        mutableState = MainActivity.this.foldState;
                        List displayFeatures = windowLayoutInfo2.getDisplayFeatures();
                        ArrayList arrayList = new ArrayList();
                        for (Object obj2 : displayFeatures) {
                            if (obj2 instanceof FoldingFeature) {
                                arrayList.add(obj2);
                            }
                        }
                        ((SnapshotMutableStateImpl) mutableState).setValue(CollectionsKt.firstOrNull((List) arrayList));
                        mutableState2 = MainActivity.this.foldState;
                        Log.v("Landroid", "fold updated: " + mutableState2);
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (((ChannelFlow) windowLayoutInfo).collect(flowCollector, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$onWindowLayoutInfoChange$1(MainActivity mainActivity, WindowInfoTracker windowInfoTracker, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mainActivity;
        this.$windowInfoTracker = windowInfoTracker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new MainActivity$onWindowLayoutInfoChange$1(this.this$0, this.$windowInfoTracker, continuation);
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
            Lifecycle lifecycle = this.this$0.getLifecycle();
            Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$windowInfoTracker, this.this$0, null);
            this.label = 1;
            if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycle, anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return ((MainActivity$onWindowLayoutInfoChange$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
