package androidx.compose.animation.core;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.DisposableEffectResult;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TransitionKt$createDeferredAnimation$1$invoke$$inlined$onDispose$1 implements DisposableEffectResult {
    final /* synthetic */ Object $lazyAnim$inlined;
    public final /* synthetic */ int $r8$classId;
    final /* synthetic */ Transition $this_createDeferredAnimation$inlined;

    public /* synthetic */ TransitionKt$createDeferredAnimation$1$invoke$$inlined$onDispose$1(Transition transition, Object obj, int i) {
        this.$r8$classId = i;
        this.$this_createDeferredAnimation$inlined = transition;
        this.$lazyAnim$inlined = obj;
    }

    @Override // androidx.compose.runtime.DisposableEffectResult
    public final void dispose() {
        Transition.TransitionAnimationState animation;
        int i = this.$r8$classId;
        Transition transition = this.$this_createDeferredAnimation$inlined;
        Object obj = this.$lazyAnim$inlined;
        switch (i) {
            case 0:
                Transition.DeferredAnimation deferredAnimation = (Transition.DeferredAnimation) obj;
                transition.getClass();
                Intrinsics.checkNotNullParameter(deferredAnimation, "deferredAnimation");
                Transition.DeferredAnimation.DeferredAnimationData data$animation_core_release = deferredAnimation.getData$animation_core_release();
                if (data$animation_core_release != null && (animation = data$animation_core_release.getAnimation()) != null) {
                    transition.removeAnimation$animation_core_release(animation);
                    return;
                }
                return;
            case 1:
                transition.removeTransition$animation_core_release((Transition) obj);
                return;
            default:
                transition.removeAnimation$animation_core_release((Transition.TransitionAnimationState) obj);
                return;
        }
    }
}
