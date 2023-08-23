package androidx.compose.animation.core;

import androidx.compose.animation.EnterExitState;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TransitionKt {
    public static final Transition createChildTransitionInternal(final Transition transition, EnterExitState enterExitState, EnterExitState enterExitState2, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-198307638);
        int i2 = ComposerKt.$r8$clinit;
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed = composerImpl.changed(transition);
        Object nextSlot = composerImpl.nextSlot();
        if (changed || nextSlot == Composer.Companion.getEmpty()) {
            MutableTransitionState mutableTransitionState = new MutableTransitionState(enterExitState);
            nextSlot = new Transition(mutableTransitionState, transition.getLabel() + " > EnterExitTransition");
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.endReplaceableGroup();
        final Transition transition2 = (Transition) nextSlot;
        composerImpl.startReplaceableGroup(511388516);
        boolean changed2 = composerImpl.changed(transition) | composerImpl.changed(transition2);
        Object nextSlot2 = composerImpl.nextSlot();
        if (changed2 || nextSlot2 == Composer.Companion.getEmpty()) {
            nextSlot2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$createChildTransitionInternal$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    DisposableEffectScope DisposableEffect = (DisposableEffectScope) obj;
                    Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                    Transition.this.addTransition$animation_core_release(transition2);
                    return new TransitionKt$createDeferredAnimation$1$invoke$$inlined$onDispose$1(Transition.this, transition2, 1);
                }
            };
            composerImpl.updateValue(nextSlot2);
        }
        composerImpl.endReplaceableGroup();
        EffectsKt.DisposableEffect(transition2, (Function1) nextSlot2, composerImpl);
        if (transition.isSeeking()) {
            transition2.seek(enterExitState, enterExitState2, transition.getLastSeekedTimeNanos$animation_core_release());
        } else {
            transition2.updateTarget$animation_core_release(enterExitState2, composerImpl, ((i >> 3) & 8) | ((i >> 6) & 14));
            transition2.setSeeking$animation_core_release(false);
        }
        composerImpl.endReplaceableGroup();
        return transition2;
    }

    public static final Transition.DeferredAnimation createDeferredAnimation(final Transition transition, TwoWayConverter typeConverter, String str, Composer composer) {
        Transition.DeferredAnimation.DeferredAnimationData data$animation_core_release;
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-1714122528);
        int i = ComposerKt.$r8$clinit;
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed = composerImpl.changed(transition);
        Object nextSlot = composerImpl.nextSlot();
        if (changed || nextSlot == Composer.Companion.getEmpty()) {
            nextSlot = new Transition.DeferredAnimation(transition, typeConverter, str);
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.endReplaceableGroup();
        final Transition.DeferredAnimation deferredAnimation = (Transition.DeferredAnimation) nextSlot;
        EffectsKt.DisposableEffect(deferredAnimation, new Function1() { // from class: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                DisposableEffectScope DisposableEffect = (DisposableEffectScope) obj;
                Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                return new TransitionKt$createDeferredAnimation$1$invoke$$inlined$onDispose$1(Transition.this, deferredAnimation, 0);
            }
        }, composerImpl);
        if (transition.isSeeking() && (data$animation_core_release = deferredAnimation.getData$animation_core_release()) != null) {
            Transition.TransitionAnimationState animation = data$animation_core_release.getAnimation();
            Function1 targetValueByState = data$animation_core_release.getTargetValueByState();
            Transition transition2 = deferredAnimation.this$0;
            animation.updateInitialAndTargetValue$animation_core_release(targetValueByState.invoke(((Transition.SegmentImpl) transition2.getSegment()).getInitialState()), data$animation_core_release.getTargetValueByState().invoke(((Transition.SegmentImpl) transition2.getSegment()).getTargetState()), (FiniteAnimationSpec) data$animation_core_release.getTransitionSpec().invoke(transition2.getSegment()));
        }
        composerImpl.endReplaceableGroup();
        return deferredAnimation;
    }

    public static final Transition.TransitionAnimationState createTransitionAnimation(final Transition transition, Object obj, Object obj2, FiniteAnimationSpec animationSpec, TwoWayConverter typeConverter, String label, Composer composer) {
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
        Intrinsics.checkNotNullParameter(label, "label");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-304821198);
        int i = ComposerKt.$r8$clinit;
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed = composerImpl.changed(transition);
        Object nextSlot = composerImpl.nextSlot();
        if (changed || nextSlot == Composer.Companion.getEmpty()) {
            nextSlot = new Transition.TransitionAnimationState(transition, obj, AnimationStateKt.createZeroVectorFrom(typeConverter, obj2), typeConverter, label);
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.endReplaceableGroup();
        final Transition.TransitionAnimationState transitionAnimationState = (Transition.TransitionAnimationState) nextSlot;
        if (transition.isSeeking()) {
            transitionAnimationState.updateInitialAndTargetValue$animation_core_release(obj, obj2, animationSpec);
        } else {
            transitionAnimationState.updateTargetValue$animation_core_release(obj2, animationSpec);
        }
        composerImpl.startReplaceableGroup(511388516);
        boolean changed2 = composerImpl.changed(transition) | composerImpl.changed(transitionAnimationState);
        Object nextSlot2 = composerImpl.nextSlot();
        if (changed2 || nextSlot2 == Composer.Companion.getEmpty()) {
            nextSlot2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$createTransitionAnimation$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    DisposableEffectScope DisposableEffect = (DisposableEffectScope) obj3;
                    Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                    Transition.this.addAnimation$animation_core_release(transitionAnimationState);
                    return new TransitionKt$createDeferredAnimation$1$invoke$$inlined$onDispose$1(Transition.this, transitionAnimationState, 2);
                }
            };
            composerImpl.updateValue(nextSlot2);
        }
        composerImpl.endReplaceableGroup();
        EffectsKt.DisposableEffect(transitionAnimationState, (Function1) nextSlot2, composerImpl);
        composerImpl.endReplaceableGroup();
        return transitionAnimationState;
    }

    public static final Transition updateTransition(Object obj, String str, Composer composer, int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(2029166765);
        int i2 = ComposerKt.$r8$clinit;
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot = composerImpl.nextSlot();
        if (nextSlot == Composer.Companion.getEmpty()) {
            nextSlot = new Transition(new MutableTransitionState(obj), str);
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.endReplaceableGroup();
        final Transition transition = (Transition) nextSlot;
        transition.animateTo$animation_core_release(obj, composerImpl, (i & 8) | 48 | (i & 14));
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed = composerImpl.changed(transition);
        Object nextSlot2 = composerImpl.nextSlot();
        if (changed || nextSlot2 == Composer.Companion.getEmpty()) {
            nextSlot2 = new Function1() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    DisposableEffectScope DisposableEffect = (DisposableEffectScope) obj2;
                    Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                    final Transition transition2 = Transition.this;
                    return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$1$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public final void dispose() {
                            Transition.this.onTransitionEnd$animation_core_release();
                        }
                    };
                }
            };
            composerImpl.updateValue(nextSlot2);
        }
        composerImpl.endReplaceableGroup();
        EffectsKt.DisposableEffect(transition, (Function1) nextSlot2, composerImpl);
        composerImpl.endReplaceableGroup();
        return transition;
    }
}
