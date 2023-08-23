package com.android.egg.paint;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class CutoutAvoidingToolbar extends LinearLayout {
    public static final int $stable = 8;
    private WindowInsets _insets;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CutoutAvoidingToolbar(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void adjustLayout() {
        DisplayCutout displayCutout;
        List<Rect> boundingRects;
        WindowInsets windowInsets = this._insets;
        if (windowInsets != null && (displayCutout = windowInsets.getDisplayCutout()) != null && (boundingRects = displayCutout.getBoundingRects()) != null) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (Rect rect : boundingRects) {
                if (rect.top <= 0) {
                    if (rect.left == getLeft()) {
                        i = rect.width();
                    } else if (rect.right == getRight()) {
                        i3 = rect.width();
                    } else {
                        i2 = rect.width();
                    }
                }
            }
            View findViewWithTag = findViewWithTag("cutoutLeft");
            if (findViewWithTag != null) {
                findViewWithTag.setLayoutParams(new LinearLayout.LayoutParams(i, -1));
            }
            View findViewWithTag2 = findViewWithTag("cutoutCenter");
            if (findViewWithTag2 != null) {
                findViewWithTag2.setLayoutParams(new LinearLayout.LayoutParams(i2, -1));
            }
            View findViewWithTag3 = findViewWithTag("cutoutRight");
            if (findViewWithTag3 != null) {
                findViewWithTag3.setLayoutParams(new LinearLayout.LayoutParams(i3, -1));
            }
            requestLayout();
        }
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this._insets = windowInsets;
        adjustLayout();
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        Intrinsics.checkNotNullExpressionValue(onApplyWindowInsets, "super.onApplyWindowInsets(insets)");
        return onApplyWindowInsets;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        adjustLayout();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CutoutAvoidingToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CutoutAvoidingToolbar(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
    }
}
