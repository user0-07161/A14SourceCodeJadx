package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Canvas;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class CanvasDrawScopeKt$asDrawTransform$1 {
    final /* synthetic */ DrawContext $this_asDrawTransform;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CanvasDrawScopeKt$asDrawTransform$1(DrawContext drawContext) {
        this.$this_asDrawTransform = drawContext;
    }

    /* renamed from: rotate-Uv8p0NA  reason: not valid java name */
    public final void m161rotateUv8p0NA(long j, float f) {
        Canvas canvas = ((CanvasDrawScope$drawContext$1) this.$this_asDrawTransform).getCanvas();
        canvas.translate(Offset.m45getXimpl(j), Offset.m46getYimpl(j));
        canvas.rotate(f);
        canvas.translate(-Offset.m45getXimpl(j), -Offset.m46getYimpl(j));
    }

    /* renamed from: scale-0AR0LA0  reason: not valid java name */
    public final void m162scale0AR0LA0(float f, long j, float f2) {
        Canvas canvas = ((CanvasDrawScope$drawContext$1) this.$this_asDrawTransform).getCanvas();
        canvas.translate(Offset.m45getXimpl(j), Offset.m46getYimpl(j));
        canvas.scale(f, f2);
        canvas.translate(-Offset.m45getXimpl(j), -Offset.m46getYimpl(j));
    }

    public final void translate(float f, float f2) {
        ((CanvasDrawScope$drawContext$1) this.$this_asDrawTransform).getCanvas().translate(f, f2);
    }
}
