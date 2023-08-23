package androidx.compose.ui.graphics;

import androidx.compose.ui.Modifier;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class GraphicsLayerModifierKt {
    /* renamed from: graphicsLayer-Ap8cVGQ$default  reason: not valid java name */
    public static Modifier m103graphicsLayerAp8cVGQ$default(Modifier.Companion companion, boolean z, int i) {
        float f;
        float f2;
        float f3;
        long j;
        RectangleShapeKt$RectangleShape$1 shape;
        boolean z2;
        long j2;
        float f4 = 0.0f;
        if ((i & 1) != 0) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 1.0f;
        } else {
            f2 = 0.0f;
        }
        if ((i & 4) != 0) {
            f3 = 1.0f;
        } else {
            f3 = 0.0f;
        }
        if ((i & 512) != 0) {
            f4 = 8.0f;
        }
        float f5 = f4;
        long j3 = 0;
        if ((i & 1024) != 0) {
            j = TransformOrigin.Center;
        } else {
            j = 0;
        }
        if ((i & 2048) != 0) {
            shape = RectangleShapeKt.getRectangleShape();
        } else {
            shape = null;
        }
        if ((i & 4096) != 0) {
            z2 = false;
        } else {
            z2 = z;
        }
        if ((i & 16384) != 0) {
            j2 = GraphicsLayerScopeKt.getDefaultShadowColor();
        } else {
            j2 = 0;
        }
        if ((i & 32768) != 0) {
            j3 = GraphicsLayerScopeKt.getDefaultShadowColor();
        }
        Intrinsics.checkNotNullParameter(shape, "shape");
        return new GraphicsLayerModifierNodeElement(f, f2, f3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f5, j, shape, z2, j2, j3, 0);
    }
}
