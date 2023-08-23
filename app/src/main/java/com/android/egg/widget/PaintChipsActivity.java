package com.android.egg.widget;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RemoteViews;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PaintChipsActivity extends Activity {
    public static final int $stable = 8;
    private FrameLayout layout;

    private final void rebuildGrid() {
        FrameLayout frameLayout = this.layout;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
            RemoteViews buildFullWidget = PaintChipsWidgetKt.buildFullWidget(this, ClickBehavior.SHARE);
            FrameLayout frameLayout2 = this.layout;
            if (frameLayout2 != null) {
                View apply = buildFullWidget.apply(this, frameLayout2);
                FrameLayout frameLayout3 = this.layout;
                if (frameLayout3 != null) {
                    frameLayout3.addView(apply, new FrameLayout.LayoutParams(-1, -1));
                    return;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("layout");
                    throw null;
                }
            }
            Intrinsics.throwUninitializedPropertyAccessException("layout");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("layout");
        throw null;
    }

    public final int dp2px(float f) {
        return (int) (f * getResources().getDisplayMetrics().density);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        rebuildGrid();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setNavigationBarColor(0);
        getWindow().setStatusBarColor(0);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        FrameLayout frameLayout = new FrameLayout(this);
        this.layout = frameLayout;
        frameLayout.setPadding(dp2px(8.0f), dp2px(8.0f), dp2px(8.0f), dp2px(8.0f));
        rebuildGrid();
        FrameLayout frameLayout2 = this.layout;
        if (frameLayout2 != null) {
            setContentView(frameLayout2);
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("layout");
            throw null;
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        rebuildGrid();
    }
}
