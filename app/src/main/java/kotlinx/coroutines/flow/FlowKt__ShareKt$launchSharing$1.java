package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1", f = "Share.kt", l = {214, 218, 219, 225}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class FlowKt__ShareKt$launchSharing$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object $initialValue;
    final /* synthetic */ MutableSharedFlow $shared;
    final /* synthetic */ SharingStarted $started;
    final /* synthetic */ Flow $upstream;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1", f = "Share.kt", l = {}, m = "invokeSuspend")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends SuspendLambda implements Function2 {
        /* synthetic */ int I$0;
        int label;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.I$0 = ((Number) obj).intValue();
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            boolean z;
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.I$0 > 0) {
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2", f = "Share.kt", l = {227}, m = "invokeSuspend")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ Object $initialValue;
        final /* synthetic */ MutableSharedFlow $shared;
        final /* synthetic */ Flow $upstream;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Flow flow, MutableSharedFlow mutableSharedFlow, Object obj, Continuation continuation) {
            super(2, continuation);
            this.$upstream = flow;
            this.$shared = mutableSharedFlow;
            this.$initialValue = obj;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$upstream, this.$shared, this.$initialValue, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass2) create((SharingCommand) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
                int ordinal = ((SharingCommand) this.L$0).ordinal();
                if (ordinal != 0) {
                    if (ordinal == 2) {
                        Object obj2 = this.$initialValue;
                        if (obj2 == SharedFlowKt.NO_VALUE) {
                            this.$shared.resetReplayCache();
                        } else {
                            this.$shared.tryEmit(obj2);
                        }
                    }
                } else {
                    Flow flow = this.$upstream;
                    MutableSharedFlow mutableSharedFlow = this.$shared;
                    this.label = 1;
                    if (flow.collect(mutableSharedFlow, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ShareKt$launchSharing$1(SharingStarted sharingStarted, Flow flow, MutableSharedFlow mutableSharedFlow, Object obj, Continuation continuation) {
        super(2, continuation);
        this.$started = sharingStarted;
        this.$upstream = flow;
        this.$shared = mutableSharedFlow;
        this.$initialValue = obj;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FlowKt__ShareKt$launchSharing$1(this.$started, this.$upstream, this.$shared, this.$initialValue, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__ShareKt$launchSharing$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006b A[RETURN] */
    /* JADX WARN: Type inference failed for: r13v3 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r13.label
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L26
            if (r1 == r6) goto L21
            if (r1 == r5) goto L1d
            if (r1 == r4) goto L21
            if (r1 != r3) goto L15
            goto L21
        L15:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L1d:
            kotlin.ResultKt.throwOnFailure(r14)
            goto L5f
        L21:
            kotlin.ResultKt.throwOnFailure(r14)
            goto Lb5
        L26:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlinx.coroutines.flow.SharingStarted r14 = r13.$started
            kotlinx.coroutines.flow.SharingStarted$Companion r1 = kotlinx.coroutines.flow.SharingStarted.Companion
            kotlinx.coroutines.flow.SharingStarted r1 = kotlinx.coroutines.flow.SharingStarted.Companion.getEagerly()
            if (r14 != r1) goto L40
            kotlinx.coroutines.flow.Flow r14 = r13.$upstream
            kotlinx.coroutines.flow.MutableSharedFlow r1 = r13.$shared
            r13.label = r6
            java.lang.Object r13 = r14.collect(r1, r13)
            if (r13 != r0) goto Lb5
            return r0
        L40:
            kotlinx.coroutines.flow.SharingStarted r14 = r13.$started
            kotlinx.coroutines.flow.SharingStarted r1 = kotlinx.coroutines.flow.SharingStarted.Companion.getLazily()
            r6 = 0
            if (r14 != r1) goto L6c
            kotlinx.coroutines.flow.MutableSharedFlow r14 = r13.$shared
            kotlinx.coroutines.flow.internal.AbstractSharedFlow r14 = (kotlinx.coroutines.flow.internal.AbstractSharedFlow) r14
            kotlinx.coroutines.flow.StateFlow r14 = r14.getSubscriptionCount()
            kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1 r1 = new kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$1
            r1.<init>(r6)
            r13.label = r5
            java.lang.Object r14 = kotlinx.coroutines.flow.FlowKt.first(r14, r1, r13)
            if (r14 != r0) goto L5f
            return r0
        L5f:
            kotlinx.coroutines.flow.Flow r14 = r13.$upstream
            kotlinx.coroutines.flow.MutableSharedFlow r1 = r13.$shared
            r13.label = r4
            java.lang.Object r13 = r14.collect(r1, r13)
            if (r13 != r0) goto Lb5
            return r0
        L6c:
            kotlinx.coroutines.flow.SharingStarted r14 = r13.$started
            kotlinx.coroutines.flow.MutableSharedFlow r1 = r13.$shared
            kotlinx.coroutines.flow.internal.AbstractSharedFlow r1 = (kotlinx.coroutines.flow.internal.AbstractSharedFlow) r1
            kotlinx.coroutines.flow.StateFlow r1 = r1.getSubscriptionCount()
            kotlinx.coroutines.flow.Flow r14 = r14.command(r1)
            kotlinx.coroutines.flow.Flow r9 = kotlinx.coroutines.flow.FlowKt__DistinctKt.distinctUntilChanged(r14)
            kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2 r14 = new kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1$2
            kotlinx.coroutines.flow.Flow r1 = r13.$upstream
            kotlinx.coroutines.flow.MutableSharedFlow r4 = r13.$shared
            java.lang.Object r5 = r13.$initialValue
            r14.<init>(r1, r4, r5, r6)
            r13.label = r3
            int r1 = kotlinx.coroutines.flow.FlowKt__MergeKt.$r8$clinit
            kotlinx.coroutines.flow.FlowKt__MergeKt$mapLatest$1 r8 = new kotlinx.coroutines.flow.FlowKt__MergeKt$mapLatest$1
            r8.<init>(r14, r6)
            kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest r14 = new kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest
            kotlin.coroutines.EmptyCoroutineContext r1 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
            r11 = -2
            kotlinx.coroutines.channels.BufferOverflow r3 = kotlinx.coroutines.channels.BufferOverflow.SUSPEND
            r7 = r14
            r10 = r1
            r12 = r3
            r7.<init>(r8, r9, r10, r11, r12)
            r4 = 0
            kotlinx.coroutines.flow.Flow r14 = r14.fuse(r1, r4, r3)
            kotlinx.coroutines.flow.internal.NopCollector r1 = kotlinx.coroutines.flow.internal.NopCollector.INSTANCE
            java.lang.Object r13 = r14.collect(r1, r13)
            if (r13 != r0) goto Lad
            goto Lae
        Lad:
            r13 = r2
        Lae:
            if (r13 != r0) goto Lb1
            goto Lb2
        Lb1:
            r13 = r2
        Lb2:
            if (r13 != r0) goto Lb5
            return r0
        Lb5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
