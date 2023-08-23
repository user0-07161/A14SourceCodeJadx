package androidx.compose.animation;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ExitTransitionImpl extends ExitTransition {
    private final TransitionData data;

    public ExitTransitionImpl(TransitionData transitionData) {
        this.data = transitionData;
    }

    @Override // androidx.compose.animation.ExitTransition
    public final TransitionData getData$animation_release() {
        return this.data;
    }
}
