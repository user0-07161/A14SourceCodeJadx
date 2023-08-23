package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AnimationResult {
    private final AnimationEndReason endReason;
    private final AnimationState endState;

    public AnimationResult(AnimationState endState, AnimationEndReason animationEndReason) {
        Intrinsics.checkNotNullParameter(endState, "endState");
        this.endState = endState;
        this.endReason = animationEndReason;
    }

    public final String toString() {
        return "AnimationResult(endReason=" + this.endReason + ", endState=" + this.endState + ')';
    }
}
