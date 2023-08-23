package androidx.compose.ui.platform;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.Path;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RenderNodeApi29 implements DeviceRenderNode {
    private final RenderNode renderNode;

    public RenderNodeApi29(AndroidComposeView ownerView) {
        Intrinsics.checkNotNullParameter(ownerView, "ownerView");
        this.renderNode = new RenderNode("Compose");
    }

    public final void discardDisplayList() {
        this.renderNode.discardDisplayList();
    }

    public final void drawInto(Canvas canvas) {
        canvas.drawRenderNode(this.renderNode);
    }

    public final float getAlpha() {
        return this.renderNode.getAlpha();
    }

    public final int getBottom() {
        return this.renderNode.getBottom();
    }

    public final boolean getClipToBounds() {
        return this.renderNode.getClipToBounds();
    }

    public final boolean getClipToOutline() {
        return this.renderNode.getClipToOutline();
    }

    public final float getElevation() {
        return this.renderNode.getElevation();
    }

    public final boolean getHasDisplayList() {
        return this.renderNode.hasDisplayList();
    }

    public final int getHeight() {
        return this.renderNode.getHeight();
    }

    public final int getLeft() {
        return this.renderNode.getLeft();
    }

    public final void getMatrix(Matrix matrix) {
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        this.renderNode.getMatrix(matrix);
    }

    public final int getRight() {
        return this.renderNode.getRight();
    }

    public final int getTop() {
        return this.renderNode.getTop();
    }

    public final int getWidth() {
        return this.renderNode.getWidth();
    }

    public final void offsetLeftAndRight(int i) {
        this.renderNode.offsetLeftAndRight(i);
    }

    public final void offsetTopAndBottom(int i) {
        this.renderNode.offsetTopAndBottom(i);
    }

    public final void record(CanvasHolder canvasHolder, Path path, Function1 function1) {
        Intrinsics.checkNotNullParameter(canvasHolder, "canvasHolder");
        RenderNode renderNode = this.renderNode;
        RecordingCanvas beginRecording = renderNode.beginRecording();
        Intrinsics.checkNotNullExpressionValue(beginRecording, "renderNode.beginRecording()");
        Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
        canvasHolder.getAndroidCanvas().setInternalCanvas(beginRecording);
        AndroidCanvas androidCanvas = canvasHolder.getAndroidCanvas();
        if (path != null) {
            androidCanvas.save();
            androidCanvas.mo67clipPathmtrdDE(path, 1);
        }
        function1.invoke(androidCanvas);
        if (path != null) {
            androidCanvas.restore();
        }
        canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
        renderNode.endRecording();
    }

    public final void setAlpha(float f) {
        this.renderNode.setAlpha(f);
    }

    public final void setAmbientShadowColor(int i) {
        this.renderNode.setAmbientShadowColor(i);
    }

    public final void setCameraDistance(float f) {
        this.renderNode.setCameraDistance(f);
    }

    public final void setClipToBounds(boolean z) {
        this.renderNode.setClipToBounds(z);
    }

    public final void setClipToOutline(boolean z) {
        this.renderNode.setClipToOutline(z);
    }

    /* renamed from: setCompositingStrategy-aDBOjCE  reason: not valid java name */
    public final void m288setCompositingStrategyaDBOjCE(int i) {
        boolean z;
        boolean z2;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        RenderNode renderNode = this.renderNode;
        if (z) {
            renderNode.setUseCompositingLayer(true, null);
            renderNode.setHasOverlappingRendering(true);
            return;
        }
        if (i == 2) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            renderNode.setUseCompositingLayer(false, null);
            renderNode.setHasOverlappingRendering(false);
            return;
        }
        renderNode.setUseCompositingLayer(false, null);
        renderNode.setHasOverlappingRendering(true);
    }

    public final void setElevation(float f) {
        this.renderNode.setElevation(f);
    }

    public final boolean setHasOverlappingRendering() {
        return this.renderNode.setHasOverlappingRendering(true);
    }

    public final void setOutline(Outline outline) {
        this.renderNode.setOutline(outline);
    }

    public final void setPivotX(float f) {
        this.renderNode.setPivotX(f);
    }

    public final void setPivotY(float f) {
        this.renderNode.setPivotY(f);
    }

    public final boolean setPosition(int i, int i2, int i3, int i4) {
        return this.renderNode.setPosition(i, i2, i3, i4);
    }

    public final void setRenderEffect() {
        RenderNode renderNode = this.renderNode;
        Intrinsics.checkNotNullParameter(renderNode, "renderNode");
        renderNode.setRenderEffect(null);
    }

    public final void setRotationX(float f) {
        this.renderNode.setRotationX(f);
    }

    public final void setRotationY(float f) {
        this.renderNode.setRotationY(f);
    }

    public final void setRotationZ(float f) {
        this.renderNode.setRotationZ(f);
    }

    public final void setScaleX(float f) {
        this.renderNode.setScaleX(f);
    }

    public final void setScaleY(float f) {
        this.renderNode.setScaleY(f);
    }

    public final void setSpotShadowColor(int i) {
        this.renderNode.setSpotShadowColor(i);
    }

    public final void setTranslationX(float f) {
        this.renderNode.setTranslationX(f);
    }

    public final void setTranslationY(float f) {
        this.renderNode.setTranslationY(f);
    }
}
