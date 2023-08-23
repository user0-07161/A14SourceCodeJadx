package androidx.compose.ui.platform;

import androidx.compose.ui.unit.DpKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface ViewConfiguration {
    /* renamed from: getMinimumTouchTargetSize-MYxV2XQ */
    default long mo233getMinimumTouchTargetSizeMYxV2XQ() {
        float f = 48;
        return DpKt.m394DpSizeYgX7TsA(f, f);
    }

    float getTouchSlop();
}
