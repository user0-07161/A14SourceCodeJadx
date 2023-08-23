package androidx.window.layout.adapter.extensions;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ExtensionsWindowLayoutInfoAdapter {
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ee A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0044 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.window.layout.WindowLayoutInfo translate$window_release(android.content.Context r10, androidx.window.extensions.layout.WindowLayoutInfo r11) {
        /*
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "info"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            androidx.window.layout.WindowMetricsCalculatorCompat r0 = androidx.window.layout.WindowMetricsCalculatorCompat.INSTANCE
            java.lang.Class<android.view.WindowManager> r0 = android.view.WindowManager.class
            java.lang.Object r10 = r10.getSystemService(r0)
            android.view.WindowManager r10 = (android.view.WindowManager) r10
            android.view.WindowMetrics r0 = r10.getCurrentWindowMetrics()
            android.view.WindowInsets r0 = r0.getWindowInsets()
            androidx.core.view.WindowInsetsCompat r0 = androidx.core.view.WindowInsetsCompat.toWindowInsetsCompat(r0)
            androidx.window.layout.WindowMetrics r1 = new androidx.window.layout.WindowMetrics
            android.view.WindowMetrics r10 = r10.getCurrentWindowMetrics()
            android.graphics.Rect r10 = r10.getBounds()
            java.lang.String r2 = "wm.currentWindowMetrics.bounds"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r2)
            r1.<init>(r10, r0)
            java.util.List r10 = r11.getDisplayFeatures()
            java.lang.String r11 = "info.displayFeatures"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r11)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.Iterator r10 = r10.iterator()
        L44:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto Lf3
            java.lang.Object r0 = r10.next()
            androidx.window.extensions.layout.DisplayFeature r0 = (androidx.window.extensions.layout.DisplayFeature) r0
            boolean r2 = r0 instanceof androidx.window.extensions.layout.FoldingFeature
            if (r2 == 0) goto Leb
            java.lang.String r2 = "feature"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            androidx.window.extensions.layout.FoldingFeature r0 = (androidx.window.extensions.layout.FoldingFeature) r0
            int r2 = r0.getType()
            r3 = 2
            r4 = 1
            if (r2 == r4) goto L6c
            if (r2 == r3) goto L67
            goto Leb
        L67:
            androidx.window.layout.HardwareFoldingFeature$Type r2 = androidx.window.layout.HardwareFoldingFeature.Type.access$getHINGE$cp()
            goto L70
        L6c:
            androidx.window.layout.HardwareFoldingFeature$Type r2 = androidx.window.layout.HardwareFoldingFeature.Type.access$getFOLD$cp()
        L70:
            int r5 = r0.getState()
            if (r5 == r4) goto L7d
            if (r5 == r3) goto L7a
            goto Leb
        L7a:
            androidx.window.layout.FoldingFeature$State r3 = androidx.window.layout.FoldingFeature.State.HALF_OPENED
            goto L7f
        L7d:
            androidx.window.layout.FoldingFeature$State r3 = androidx.window.layout.FoldingFeature.State.FLAT
        L7f:
            androidx.window.core.Bounds r5 = new androidx.window.core.Bounds
            android.graphics.Rect r6 = r0.getBounds()
            java.lang.String r7 = "oemFeature.bounds"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            r5.<init>(r6)
            android.graphics.Rect r6 = r1.getBounds()
            boolean r8 = r5.isZero()
            if (r8 == 0) goto L98
            goto Ld6
        L98:
            int r8 = r5.getWidth()
            int r9 = r6.width()
            if (r8 == r9) goto Lad
            int r8 = r5.getHeight()
            int r9 = r6.height()
            if (r8 == r9) goto Lad
            goto Ld6
        Lad:
            int r8 = r5.getWidth()
            int r9 = r6.width()
            if (r8 >= r9) goto Lc2
            int r8 = r5.getHeight()
            int r9 = r6.height()
            if (r8 >= r9) goto Lc2
            goto Ld6
        Lc2:
            int r8 = r5.getWidth()
            int r9 = r6.width()
            if (r8 != r9) goto Ld7
            int r5 = r5.getHeight()
            int r6 = r6.height()
            if (r5 != r6) goto Ld7
        Ld6:
            r4 = 0
        Ld7:
            if (r4 == 0) goto Leb
            androidx.window.layout.HardwareFoldingFeature r4 = new androidx.window.layout.HardwareFoldingFeature
            androidx.window.core.Bounds r5 = new androidx.window.core.Bounds
            android.graphics.Rect r0 = r0.getBounds()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r7)
            r5.<init>(r0)
            r4.<init>(r5, r2, r3)
            goto Lec
        Leb:
            r4 = 0
        Lec:
            if (r4 == 0) goto L44
            r11.add(r4)
            goto L44
        Lf3:
            androidx.window.layout.WindowLayoutInfo r10 = new androidx.window.layout.WindowLayoutInfo
            r10.<init>(r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.window.layout.adapter.extensions.ExtensionsWindowLayoutInfoAdapter.translate$window_release(android.content.Context, androidx.window.extensions.layout.WindowLayoutInfo):androidx.window.layout.WindowLayoutInfo");
    }
}
