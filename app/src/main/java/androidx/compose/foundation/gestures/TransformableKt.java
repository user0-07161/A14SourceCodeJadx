package androidx.compose.foundation.gestures;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TransformableKt {
    /* JADX WARN: Code restructure failed: missing block: B:198:0x01ca, code lost:
        if (androidx.compose.ui.geometry.Offset.m43equalsimpl0(r2, r5) == false) goto L81;
     */
    /* JADX WARN: Removed duplicated region for block: B:127:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x00ef A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:233:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:139:0x00ce -> B:141:0x00d2). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object access$detectZoom(androidx.compose.ui.input.pointer.AwaitPointerEventScope r25, androidx.compose.runtime.State r26, kotlinx.coroutines.channels.Channel r27, kotlin.coroutines.Continuation r28) {
        /*
            Method dump skipped, instructions count: 570
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformableKt.access$detectZoom(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.runtime.State, kotlinx.coroutines.channels.Channel, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static Modifier transformable$default(Modifier modifier, final TransformableState state) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(state, "state");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.foundation.gestures.TransformableKt$transformable$2

            /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt$transformable$2$1", f = "Transformable.kt", l = {72, 75}, m = "invokeSuspend")
            /* renamed from: androidx.compose.foundation.gestures.TransformableKt$transformable$2$1  reason: invalid class name */
            /* loaded from: classes.dex */
            final class AnonymousClass1 extends SuspendLambda implements Function2 {
                final /* synthetic */ Channel $channel;
                final /* synthetic */ TransformableState $state;
                private /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                int label;

                /* JADX INFO: Access modifiers changed from: package-private */
                /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
                @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt$transformable$2$1$1", f = "Transformable.kt", l = {80}, m = "invokeSuspend")
                /* renamed from: androidx.compose.foundation.gestures.TransformableKt$transformable$2$1$1  reason: invalid class name and collision with other inner class name */
                /* loaded from: classes.dex */
                public final class C00001 extends SuspendLambda implements Function2 {
                    final /* synthetic */ Channel $channel;
                    final /* synthetic */ Ref$ObjectRef $event;
                    private /* synthetic */ Object L$0;
                    Object L$1;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C00001(Ref$ObjectRef ref$ObjectRef, Channel channel, Continuation continuation) {
                        super(2, continuation);
                        this.$event = ref$ObjectRef;
                        this.$channel = channel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation create(Object obj, Continuation continuation) {
                        C00001 c00001 = new C00001(this.$event, this.$channel, continuation);
                        c00001.L$0 = obj;
                        return c00001;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return ((C00001) create((DefaultTransformableState$transformScope$1) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
                    /* JADX WARN: Removed duplicated region for block: B:21:0x006c  */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0066 -> B:20:0x0069). Please submit an issue!!! */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                        /*
                            r7 = this;
                            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r1 = r7.label
                            r2 = 1
                            if (r1 == 0) goto L1d
                            if (r1 != r2) goto L15
                            java.lang.Object r1 = r7.L$1
                            kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref$ObjectRef) r1
                            java.lang.Object r3 = r7.L$0
                            androidx.compose.foundation.gestures.DefaultTransformableState$transformScope$1 r3 = (androidx.compose.foundation.gestures.DefaultTransformableState$transformScope$1) r3
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L69
                        L15:
                            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                            r7.<init>(r8)
                            throw r7
                        L1d:
                            kotlin.ResultKt.throwOnFailure(r8)
                            java.lang.Object r8 = r7.L$0
                            androidx.compose.foundation.gestures.DefaultTransformableState$transformScope$1 r8 = (androidx.compose.foundation.gestures.DefaultTransformableState$transformScope$1) r8
                            r3 = r8
                        L25:
                            kotlin.jvm.internal.Ref$ObjectRef r8 = r7.$event
                            java.lang.Object r8 = r8.element
                            boolean r1 = r8 instanceof androidx.compose.foundation.gestures.TransformEvent.TransformStopped
                            if (r1 != 0) goto L6c
                            boolean r1 = r8 instanceof androidx.compose.foundation.gestures.TransformEvent.TransformDelta
                            if (r1 == 0) goto L34
                            androidx.compose.foundation.gestures.TransformEvent$TransformDelta r8 = (androidx.compose.foundation.gestures.TransformEvent.TransformDelta) r8
                            goto L35
                        L34:
                            r8 = 0
                        L35:
                            if (r8 == 0) goto L58
                            float r1 = r8.getZoomChange()
                            long r4 = r8.m7getPanChangeF1C5BW0()
                            float r8 = r8.getRotationChange()
                            androidx.compose.foundation.gestures.DefaultTransformableState r6 = r3.this$0
                            kotlin.jvm.functions.Function3 r6 = r6.getOnTransformation()
                            java.lang.Float r1 = java.lang.Float.valueOf(r1)
                            androidx.compose.ui.geometry.Offset r4 = androidx.compose.ui.geometry.Offset.m42boximpl(r4)
                            java.lang.Float r8 = java.lang.Float.valueOf(r8)
                            r6.invoke(r1, r4, r8)
                        L58:
                            kotlin.jvm.internal.Ref$ObjectRef r1 = r7.$event
                            kotlinx.coroutines.channels.Channel r8 = r7.$channel
                            r7.L$0 = r3
                            r7.L$1 = r1
                            r7.label = r2
                            java.lang.Object r8 = r8.receive(r7)
                            if (r8 != r0) goto L69
                            return r0
                        L69:
                            r1.element = r8
                            goto L25
                        L6c:
                            kotlin.Unit r7 = kotlin.Unit.INSTANCE
                            return r7
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformableKt$transformable$2.AnonymousClass1.C00001.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(Channel channel, TransformableState transformableState, Continuation continuation) {
                    super(2, continuation);
                    this.$channel = channel;
                    this.$state = transformableState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$channel, this.$state, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
                /* JADX WARN: Removed duplicated region for block: B:29:0x0096 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:32:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0091 -> B:14:0x003d). Please submit an issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r12) {
                    /*
                        r11 = this;
                        kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r1 = r11.label
                        kotlin.Unit r2 = kotlin.Unit.INSTANCE
                        r3 = 2
                        r4 = 1
                        if (r1 == 0) goto L33
                        if (r1 == r4) goto L1e
                        if (r1 != r3) goto L16
                        java.lang.Object r1 = r11.L$0
                        kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                        kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.util.concurrent.CancellationException -> L3b
                        goto L3b
                    L16:
                        java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                        java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                        r11.<init>(r12)
                        throw r11
                    L1e:
                        java.lang.Object r1 = r11.L$2
                        kotlin.jvm.internal.Ref$ObjectRef r1 = (kotlin.jvm.internal.Ref$ObjectRef) r1
                        java.lang.Object r5 = r11.L$1
                        kotlin.jvm.internal.Ref$ObjectRef r5 = (kotlin.jvm.internal.Ref$ObjectRef) r5
                        java.lang.Object r6 = r11.L$0
                        kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
                        kotlin.ResultKt.throwOnFailure(r12)
                        r7 = r6
                        r6 = r5
                        r5 = r2
                        r2 = r1
                        r1 = r0
                        goto L60
                    L33:
                        kotlin.ResultKt.throwOnFailure(r12)
                        java.lang.Object r12 = r11.L$0
                        r1 = r12
                        kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                    L3b:
                        r12 = r0
                        r6 = r1
                    L3d:
                        boolean r1 = kotlinx.coroutines.CoroutineScopeKt.isActive(r6)
                        if (r1 == 0) goto L96
                        kotlin.jvm.internal.Ref$ObjectRef r1 = new kotlin.jvm.internal.Ref$ObjectRef
                        r1.<init>()
                        kotlinx.coroutines.channels.Channel r5 = r11.$channel
                        r11.L$0 = r6
                        r11.L$1 = r1
                        r11.L$2 = r1
                        r11.label = r4
                        java.lang.Object r5 = r5.receive(r11)
                        if (r5 != r0) goto L59
                        return r0
                    L59:
                        r7 = r6
                        r6 = r1
                        r1 = r0
                        r0 = r12
                        r12 = r5
                        r5 = r2
                        r2 = r6
                    L60:
                        r2.element = r12
                        java.lang.Object r12 = r6.element
                        boolean r12 = r12 instanceof androidx.compose.foundation.gestures.TransformEvent.TransformStarted
                        if (r12 == 0) goto L91
                        androidx.compose.foundation.gestures.TransformableState r12 = r11.$state     // Catch: java.util.concurrent.CancellationException -> L91
                        androidx.compose.foundation.MutatePriority r2 = androidx.compose.foundation.MutatePriority.UserInput     // Catch: java.util.concurrent.CancellationException -> L91
                        androidx.compose.foundation.gestures.TransformableKt$transformable$2$1$1 r8 = new androidx.compose.foundation.gestures.TransformableKt$transformable$2$1$1     // Catch: java.util.concurrent.CancellationException -> L91
                        kotlinx.coroutines.channels.Channel r9 = r11.$channel     // Catch: java.util.concurrent.CancellationException -> L91
                        r10 = 0
                        r8.<init>(r6, r9, r10)     // Catch: java.util.concurrent.CancellationException -> L91
                        r11.L$0 = r7     // Catch: java.util.concurrent.CancellationException -> L91
                        r11.L$1 = r10     // Catch: java.util.concurrent.CancellationException -> L91
                        r11.L$2 = r10     // Catch: java.util.concurrent.CancellationException -> L91
                        r11.label = r3     // Catch: java.util.concurrent.CancellationException -> L91
                        androidx.compose.foundation.gestures.DefaultTransformableState r12 = (androidx.compose.foundation.gestures.DefaultTransformableState) r12     // Catch: java.util.concurrent.CancellationException -> L91
                        r12.getClass()     // Catch: java.util.concurrent.CancellationException -> L91
                        androidx.compose.foundation.gestures.DefaultTransformableState$transform$2 r6 = new androidx.compose.foundation.gestures.DefaultTransformableState$transform$2     // Catch: java.util.concurrent.CancellationException -> L91
                        r6.<init>(r12, r2, r8, r10)     // Catch: java.util.concurrent.CancellationException -> L91
                        java.lang.Object r12 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r6, r11)     // Catch: java.util.concurrent.CancellationException -> L91
                        if (r12 != r0) goto L8d
                        goto L8e
                    L8d:
                        r12 = r5
                    L8e:
                        if (r12 != r1) goto L91
                        return r1
                    L91:
                        r12 = r0
                        r0 = r1
                        r2 = r5
                        r6 = r7
                        goto L3d
                    L96:
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformableKt$transformable$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Object obj4;
                Modifier composed = (Modifier) obj;
                ((Number) obj3).intValue();
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceableGroup(1509335853);
                int i = ComposerKt.$r8$clinit;
                MutableState rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Boolean.valueOf(r2), composerImpl);
                composerImpl.startReplaceableGroup(-492369756);
                Object nextSlot = composerImpl.nextSlot();
                if (nextSlot == Composer.Companion.getEmpty()) {
                    nextSlot = ChannelKt.Channel$default(Integer.MAX_VALUE, null, 6);
                    composerImpl.updateValue(nextSlot);
                }
                composerImpl.endReplaceableGroup();
                Channel channel = (Channel) nextSlot;
                composerImpl.startReplaceableGroup(-2015617726);
                if (r3) {
                    TransformableState transformableState = state;
                    EffectsKt.LaunchedEffect(transformableState, new AnonymousClass1(channel, transformableState, null), composerImpl);
                }
                composerImpl.endReplaceableGroup();
                composerImpl.startReplaceableGroup(-492369756);
                Object nextSlot2 = composerImpl.nextSlot();
                if (nextSlot2 == Composer.Companion.getEmpty()) {
                    nextSlot2 = new TransformableKt$transformable$2$block$1$1(rememberUpdatedState, channel, null);
                    composerImpl.updateValue(nextSlot2);
                }
                composerImpl.endReplaceableGroup();
                Function2 function2 = (Function2) nextSlot2;
                if (r3) {
                    obj4 = SuspendingPointerInputFilterKt.pointerInput(Modifier.Companion, Unit.INSTANCE, function2);
                } else {
                    obj4 = Modifier.Companion;
                }
                composerImpl.endReplaceableGroup();
                return obj4;
            }
        });
    }
}
