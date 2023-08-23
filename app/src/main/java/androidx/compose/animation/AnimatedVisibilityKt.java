package androidx.compose.animation;

import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AnimatedVisibilityKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void AnimatedEnterExitImpl(final Transition transition, final Function1 function1, final Modifier modifier, final EnterTransition enterTransition, final ExitTransition exitTransition, final Function3 function3, Composer composer, final int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(808253933);
        if ((i & 14) == 0) {
            if (composerImpl.changed(transition)) {
                i8 = 4;
            } else {
                i8 = 2;
            }
            i2 = i8 | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            if (composerImpl.changed(function1)) {
                i7 = 32;
            } else {
                i7 = 16;
            }
            i2 |= i7;
        }
        if ((i & 896) == 0) {
            if (composerImpl.changed(modifier)) {
                i6 = 256;
            } else {
                i6 = 128;
            }
            i2 |= i6;
        }
        if ((i & 7168) == 0) {
            if (composerImpl.changed(enterTransition)) {
                i5 = 2048;
            } else {
                i5 = 1024;
            }
            i2 |= i5;
        }
        if ((i & 57344) == 0) {
            if (composerImpl.changed(exitTransition)) {
                i4 = 16384;
            } else {
                i4 = 8192;
            }
            i2 |= i4;
        }
        if ((458752 & i) == 0) {
            if (composerImpl.changed(function3)) {
                i3 = 131072;
            } else {
                i3 = 65536;
            }
            i2 |= i3;
        }
        if ((374491 & i2) == 74898 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            int i9 = ComposerKt.$r8$clinit;
            int i10 = i2 & 14;
            composerImpl.startReplaceableGroup(1157296644);
            boolean changed = composerImpl.changed(transition);
            Object nextSlot = composerImpl.nextSlot();
            if (changed || nextSlot == Composer.Companion.getEmpty()) {
                nextSlot = SnapshotStateKt.mutableStateOf$default(function1.invoke(transition.getCurrentState()));
                composerImpl.updateValue(nextSlot);
            }
            composerImpl.endReplaceableGroup();
            MutableState mutableState = (MutableState) nextSlot;
            if (((Boolean) function1.invoke(transition.getTargetState())).booleanValue() || ((Boolean) ((SnapshotMutableStateImpl) mutableState).getValue()).booleanValue() || transition.isSeeking()) {
                int i11 = i10 | 48;
                composerImpl.startReplaceableGroup(1215497572);
                int i12 = i11 & 14;
                composerImpl.startReplaceableGroup(1157296644);
                boolean changed2 = composerImpl.changed(transition);
                Object nextSlot2 = composerImpl.nextSlot();
                if (changed2 || nextSlot2 == Composer.Companion.getEmpty()) {
                    nextSlot2 = transition.getCurrentState();
                    composerImpl.updateValue(nextSlot2);
                }
                composerImpl.endReplaceableGroup();
                if (transition.isSeeking()) {
                    nextSlot2 = transition.getCurrentState();
                }
                composerImpl.startReplaceableGroup(-1220581778);
                EnterExitState targetEnterExit = targetEnterExit(transition, function1, nextSlot2, composerImpl);
                composerImpl.endReplaceableGroup();
                Object targetState = transition.getTargetState();
                composerImpl.startReplaceableGroup(-1220581778);
                EnterExitState targetEnterExit2 = targetEnterExit(transition, function1, targetState, composerImpl);
                composerImpl.endReplaceableGroup();
                Transition createChildTransitionInternal = TransitionKt.createChildTransitionInternal(transition, targetEnterExit, targetEnterExit2, composerImpl, ((i11 << 6) & 7168) | i12);
                composerImpl.endReplaceableGroup();
                composerImpl.startReplaceableGroup(511388516);
                boolean changed3 = composerImpl.changed(createChildTransitionInternal) | composerImpl.changed(mutableState);
                Object nextSlot3 = composerImpl.nextSlot();
                if (changed3 || nextSlot3 == Composer.Companion.getEmpty()) {
                    nextSlot3 = new AnimatedVisibilityKt$AnimatedEnterExitImpl$1$1(createChildTransitionInternal, mutableState, null);
                    composerImpl.updateValue(nextSlot3);
                }
                composerImpl.endReplaceableGroup();
                EffectsKt.LaunchedEffect(createChildTransitionInternal, (Function2) nextSlot3, composerImpl);
                int i13 = i2 >> 3;
                int i14 = (i13 & 57344) | (i13 & 112) | (i13 & 896) | (i13 & 7168);
                composerImpl.startReplaceableGroup(-1967270694);
                Object currentState = createChildTransitionInternal.getCurrentState();
                EnterExitState enterExitState = EnterExitState.Visible;
                if (currentState == enterExitState || createChildTransitionInternal.getTargetState() == enterExitState) {
                    int i15 = i14 & 14;
                    composerImpl.startReplaceableGroup(1157296644);
                    boolean changed4 = composerImpl.changed(createChildTransitionInternal);
                    Object nextSlot4 = composerImpl.nextSlot();
                    if (changed4 || nextSlot4 == Composer.Companion.getEmpty()) {
                        nextSlot4 = new AnimatedVisibilityScopeImpl(createChildTransitionInternal);
                        composerImpl.updateValue(nextSlot4);
                    }
                    composerImpl.endReplaceableGroup();
                    AnimatedVisibilityScopeImpl animatedVisibilityScopeImpl = (AnimatedVisibilityScopeImpl) nextSlot4;
                    int i16 = i14 >> 3;
                    Modifier then = modifier.then(EnterExitTransitionKt.createModifier(createChildTransitionInternal, enterTransition, exitTransition, composerImpl, i15 | 3072 | (i16 & 112) | (i16 & 896)));
                    composerImpl.startReplaceableGroup(-492369756);
                    Object nextSlot5 = composerImpl.nextSlot();
                    if (nextSlot5 == Composer.Companion.getEmpty()) {
                        nextSlot5 = new AnimatedEnterExitMeasurePolicy(animatedVisibilityScopeImpl);
                        composerImpl.updateValue(nextSlot5);
                    }
                    composerImpl.endReplaceableGroup();
                    MeasurePolicy measurePolicy = (MeasurePolicy) nextSlot5;
                    composerImpl.startReplaceableGroup(-1323940314);
                    Density density = (Density) composerImpl.consume(CompositionLocalsKt.getLocalDensity());
                    LayoutDirection layoutDirection = (LayoutDirection) composerImpl.consume(CompositionLocalsKt.getLocalLayoutDirection());
                    ViewConfiguration viewConfiguration = (ViewConfiguration) composerImpl.consume(CompositionLocalsKt.getLocalViewConfiguration());
                    ComposeUiNode.Companion.getClass();
                    Function0 constructor = ComposeUiNode.Companion.getConstructor();
                    ComposableLambdaImpl materializerOf = LayoutKt.materializerOf(then);
                    if (composerImpl.getApplier() instanceof Applier) {
                        composerImpl.startReusableNode();
                        if (composerImpl.getInserting()) {
                            composerImpl.createNode(constructor);
                        } else {
                            composerImpl.useNode();
                        }
                        composerImpl.disableReusing();
                        Updater.m23setimpl(composerImpl, measurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.m23setimpl(composerImpl, density, ComposeUiNode.Companion.getSetDensity());
                        Updater.m23setimpl(composerImpl, layoutDirection, ComposeUiNode.Companion.getSetLayoutDirection());
                        Updater.m23setimpl(composerImpl, viewConfiguration, ComposeUiNode.Companion.getSetViewConfiguration());
                        composerImpl.enableReusing();
                        materializerOf.invoke((Object) SkippableUpdater.m21boximpl(composerImpl), (Object) composerImpl, (Object) 0);
                        composerImpl.startReplaceableGroup(2058660585);
                        function3.invoke(animatedVisibilityScopeImpl, composerImpl, Integer.valueOf(((i14 >> 9) & 112) | 8));
                        composerImpl.endReplaceableGroup();
                        composerImpl.endNode();
                        composerImpl.endReplaceableGroup();
                    } else {
                        ComposablesKt.invalidApplier();
                        throw null;
                    }
                }
                composerImpl.endReplaceableGroup();
            }
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    ((Number) obj2).intValue();
                    AnimatedVisibilityKt.AnimatedEnterExitImpl(Transition.this, function1, modifier, enterTransition, exitTransition, function3, (Composer) obj, i | 1);
                    return Unit.INSTANCE;
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void AnimatedVisibility(final androidx.compose.foundation.layout.ColumnScope r19, final boolean r20, androidx.compose.ui.Modifier r21, androidx.compose.animation.EnterTransition r22, androidx.compose.animation.ExitTransition r23, java.lang.String r24, final kotlin.jvm.functions.Function3 r25, androidx.compose.runtime.Composer r26, final int r27, final int r28) {
        /*
            Method dump skipped, instructions count: 526
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.AnimatedVisibility(androidx.compose.foundation.layout.ColumnScope, boolean, androidx.compose.ui.Modifier, androidx.compose.animation.EnterTransition, androidx.compose.animation.ExitTransition, java.lang.String, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x008a, code lost:
        if (((java.lang.Boolean) ((androidx.compose.runtime.SnapshotMutableStateImpl) r0).getValue()).booleanValue() != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x008d, code lost:
        r1 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0037, code lost:
        if (((java.lang.Boolean) r6.invoke(r5.getCurrentState())).booleanValue() != false) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final androidx.compose.animation.EnterExitState targetEnterExit(androidx.compose.animation.core.Transition r5, kotlin.jvm.functions.Function1 r6, java.lang.Object r7, androidx.compose.runtime.Composer r8) {
        /*
            androidx.compose.runtime.ComposerImpl r8 = (androidx.compose.runtime.ComposerImpl) r8
            r0 = 361571134(0x158d233e, float:5.700505E-26)
            r8.startReplaceableGroup(r0)
            int r0 = androidx.compose.runtime.ComposerKt.$r8$clinit
            r0 = -721837504(0xffffffffd4f9a240, float:-8.5773517E12)
            r8.startMovableGroup(r0, r5)
            boolean r0 = r5.isSeeking()
            androidx.compose.animation.EnterExitState r1 = androidx.compose.animation.EnterExitState.PostExit
            androidx.compose.animation.EnterExitState r2 = androidx.compose.animation.EnterExitState.PreEnter
            androidx.compose.animation.EnterExitState r3 = androidx.compose.animation.EnterExitState.Visible
            if (r0 == 0) goto L3a
            java.lang.Object r7 = r6.invoke(r7)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L29
            goto L7c
        L29:
            java.lang.Object r5 = r5.getCurrentState()
            java.lang.Object r5 = r6.invoke(r5)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L8d
            goto L8e
        L3a:
            r0 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
            r8.startReplaceableGroup(r0)
            java.lang.Object r0 = r8.nextSlot()
            androidx.compose.runtime.Composer$Companion$Empty$1 r4 = androidx.compose.runtime.Composer.Companion.getEmpty()
            if (r0 != r4) goto L53
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            androidx.compose.runtime.ParcelableSnapshotMutableState r0 = androidx.compose.runtime.SnapshotStateKt.mutableStateOf$default(r0)
            r8.updateValue(r0)
        L53:
            r8.endReplaceableGroup()
            androidx.compose.runtime.MutableState r0 = (androidx.compose.runtime.MutableState) r0
            java.lang.Object r5 = r5.getCurrentState()
            java.lang.Object r5 = r6.invoke(r5)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L70
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            r4 = r0
            androidx.compose.runtime.SnapshotMutableStateImpl r4 = (androidx.compose.runtime.SnapshotMutableStateImpl) r4
            r4.setValue(r5)
        L70:
            java.lang.Object r5 = r6.invoke(r7)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L7e
        L7c:
            r1 = r3
            goto L8e
        L7e:
            androidx.compose.runtime.SnapshotMutableStateImpl r0 = (androidx.compose.runtime.SnapshotMutableStateImpl) r0
            java.lang.Object r5 = r0.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L8d
            goto L8e
        L8d:
            r1 = r2
        L8e:
            r8.endMovableGroup()
            r8.endReplaceableGroup()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.AnimatedVisibilityKt.targetEnterExit(androidx.compose.animation.core.Transition, kotlin.jvm.functions.Function1, java.lang.Object, androidx.compose.runtime.Composer):androidx.compose.animation.EnterExitState");
    }
}
