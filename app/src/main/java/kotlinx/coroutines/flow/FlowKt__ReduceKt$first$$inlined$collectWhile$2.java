package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FlowKt__ReduceKt$first$$inlined$collectWhile$2 implements FlowCollector {
    final /* synthetic */ Function2 $predicate$inlined;
    final /* synthetic */ Ref$ObjectRef $result$inlined;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2", f = "Reduce.kt", l = {142}, m = "emit")
    /* renamed from: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__ReduceKt$first$$inlined$collectWhile$2.this.emit(null, this);
        }
    }

    public FlowKt__ReduceKt$first$$inlined$collectWhile$2(Function2 function2, Ref$ObjectRef ref$ObjectRef) {
        this.$predicate$inlined = function2;
        this.$result$inlined = ref$ObjectRef;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0059  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2$1 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2$1 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r5 = r0.L$1
            java.lang.Object r4 = r0.L$0
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2 r4 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L47
        L2d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L35:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            kotlin.jvm.functions.Function2 r6 = r4.$predicate$inlined
            java.lang.Object r6 = r6.invoke(r5, r0)
            if (r6 != r1) goto L47
            return r1
        L47:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L54
            kotlin.jvm.internal.Ref$ObjectRef r6 = r4.$result$inlined
            r6.element = r5
            r3 = 0
        L54:
            if (r3 == 0) goto L59
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        L59:
            kotlinx.coroutines.flow.internal.AbortFlowException r5 = new kotlinx.coroutines.flow.internal.AbortFlowException
            r5.<init>(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
