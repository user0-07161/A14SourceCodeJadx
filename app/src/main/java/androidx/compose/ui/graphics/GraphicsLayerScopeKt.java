package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.Color;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class GraphicsLayerScopeKt {
    private static final long DefaultShadowColor;

    static {
        long j;
        Color.Companion companion = Color.Companion;
        j = Color.Black;
        DefaultShadowColor = j;
    }

    public static final long getDefaultShadowColor() {
        return DefaultShadowColor;
    }
}
