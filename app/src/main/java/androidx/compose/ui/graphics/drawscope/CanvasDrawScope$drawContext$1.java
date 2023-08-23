package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.graphics.Canvas;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class CanvasDrawScope$drawContext$1 implements DrawContext {
    final /* synthetic */ CanvasDrawScope this$0;
    private final CanvasDrawScopeKt$asDrawTransform$1 transform;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CanvasDrawScope$drawContext$1(CanvasDrawScope canvasDrawScope) {
        this.this$0 = canvasDrawScope;
        int i = CanvasDrawScopeKt.$r8$clinit;
        this.transform = new CanvasDrawScopeKt$asDrawTransform$1(this);
    }

    public final Canvas getCanvas() {
        return this.this$0.getDrawParams().getCanvas();
    }

    /* renamed from: getSize-NH-jbRc  reason: not valid java name */
    public final long m159getSizeNHjbRc() {
        return this.this$0.getDrawParams().m157getSizeNHjbRc();
    }

    public final CanvasDrawScopeKt$asDrawTransform$1 getTransform() {
        return this.transform;
    }

    /* renamed from: setSize-uvyYCjk  reason: not valid java name */
    public final void m160setSizeuvyYCjk(long j) {
        this.this$0.getDrawParams().m158setSizeuvyYCjk(j);
    }
}
