package com.android.egg.paint;

import android.content.Context;
import android.transition.Transition;
import android.transition.TransitionListenerAdapter;
import android.util.AttributeSet;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ToolbarView extends FrameLayout {
    public static final int $stable = 8;
    private boolean inTransition;
    private Transition.TransitionListener transitionListener;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToolbarView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.transitionListener = new TransitionListenerAdapter() { // from class: com.android.egg.paint.ToolbarView$transitionListener$1
            @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition) {
                ToolbarView.this.setInTransition(false);
            }

            @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition) {
                ToolbarView.this.setInTransition(true);
            }
        };
    }

    public final boolean getInTransition() {
        return this.inTransition;
    }

    public final Transition.TransitionListener getTransitionListener() {
        return this.transitionListener;
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        if (layoutParams != null && windowInsets != null) {
            if (windowInsets.hasStableInsets()) {
                layoutParams.topMargin = windowInsets.getStableInsetTop();
                layoutParams.bottomMargin = windowInsets.getStableInsetBottom();
            } else {
                layoutParams.topMargin = windowInsets.getSystemWindowInsetTop();
                layoutParams.bottomMargin = windowInsets.getSystemWindowInsetBottom();
            }
            setLayoutParams(layoutParams);
        }
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        Intrinsics.checkNotNullExpressionValue(onApplyWindowInsets, "super.onApplyWindowInsets(insets)");
        return onApplyWindowInsets;
    }

    public final void setInTransition(boolean z) {
        this.inTransition = z;
    }

    public final void setTransitionListener(Transition.TransitionListener transitionListener) {
        Intrinsics.checkNotNullParameter(transitionListener, "<set-?>");
        this.transitionListener = transitionListener;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToolbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.transitionListener = new TransitionListenerAdapter() { // from class: com.android.egg.paint.ToolbarView$transitionListener$1
            @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition) {
                ToolbarView.this.setInTransition(false);
            }

            @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition) {
                ToolbarView.this.setInTransition(true);
            }
        };
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToolbarView(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.transitionListener = new TransitionListenerAdapter() { // from class: com.android.egg.paint.ToolbarView$transitionListener$1
            @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
            public void onTransitionEnd(Transition transition) {
                ToolbarView.this.setInTransition(false);
            }

            @Override // android.transition.TransitionListenerAdapter, android.transition.Transition.TransitionListener
            public void onTransitionStart(Transition transition) {
                ToolbarView.this.setInTransition(true);
            }
        };
    }
}
