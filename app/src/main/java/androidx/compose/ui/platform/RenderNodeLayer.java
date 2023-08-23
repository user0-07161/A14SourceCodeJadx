package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.view.ViewParent;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RenderNodeLayer implements OwnedLayer {
    private static final Function2 getMatrix = new Function2() { // from class: androidx.compose.ui.platform.RenderNodeLayer$Companion$getMatrix$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            DeviceRenderNode rn = (DeviceRenderNode) obj;
            Matrix matrix = (Matrix) obj2;
            Intrinsics.checkNotNullParameter(rn, "rn");
            Intrinsics.checkNotNullParameter(matrix, "matrix");
            ((RenderNodeApi29) rn).getMatrix(matrix);
            return Unit.INSTANCE;
        }
    };
    private final CanvasHolder canvasHolder;
    private Function1 drawBlock;
    private boolean drawnWithZ;
    private Function0 invalidateParentLayer;
    private boolean isDestroyed;
    private boolean isDirty;
    private final LayerMatrixCache matrixCache;
    private final OutlineResolver outlineResolver;
    private final AndroidComposeView ownerView;
    private final RenderNodeApi29 renderNode;
    private AndroidPaint softwareLayerPaint;
    private long transformOrigin;

    public RenderNodeLayer(AndroidComposeView ownerView, Function1 drawBlock, Function0 invalidateParentLayer) {
        long j;
        Intrinsics.checkNotNullParameter(ownerView, "ownerView");
        Intrinsics.checkNotNullParameter(drawBlock, "drawBlock");
        Intrinsics.checkNotNullParameter(invalidateParentLayer, "invalidateParentLayer");
        this.ownerView = ownerView;
        this.drawBlock = drawBlock;
        this.invalidateParentLayer = invalidateParentLayer;
        this.outlineResolver = new OutlineResolver(ownerView.getDensity());
        this.matrixCache = new LayerMatrixCache(getMatrix);
        this.canvasHolder = new CanvasHolder();
        j = TransformOrigin.Center;
        this.transformOrigin = j;
        RenderNodeApi29 renderNodeApi29 = new RenderNodeApi29(ownerView);
        renderNodeApi29.setHasOverlappingRendering();
        this.renderNode = renderNodeApi29;
    }

    private final void setDirty(boolean z) {
        if (z != this.isDirty) {
            this.isDirty = z;
            this.ownerView.notifyLayerIsDirty$ui_release(this, z);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void destroy() {
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        if (renderNodeApi29.getHasDisplayList()) {
            renderNodeApi29.discardDisplayList();
        }
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        this.isDestroyed = true;
        setDirty(false);
        AndroidComposeView androidComposeView = this.ownerView;
        androidComposeView.requestClearInvalidObservations();
        androidComposeView.recycle$ui_release(this);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void drawLayer(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        int i = AndroidCanvas_androidKt.$r8$clinit;
        android.graphics.Canvas internalCanvas = ((AndroidCanvas) canvas).getInternalCanvas();
        boolean isHardwareAccelerated = internalCanvas.isHardwareAccelerated();
        boolean z = false;
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        if (isHardwareAccelerated) {
            updateDisplayList();
            if (renderNodeApi29.getElevation() > 0.0f) {
                z = true;
            }
            this.drawnWithZ = z;
            if (z) {
                canvas.enableZ();
            }
            renderNodeApi29.drawInto(internalCanvas);
            if (this.drawnWithZ) {
                canvas.disableZ();
                return;
            }
            return;
        }
        float left = renderNodeApi29.getLeft();
        float top = renderNodeApi29.getTop();
        float right = renderNodeApi29.getRight();
        float bottom = renderNodeApi29.getBottom();
        if (renderNodeApi29.getAlpha() < 1.0f) {
            AndroidPaint androidPaint = this.softwareLayerPaint;
            if (androidPaint == null) {
                androidPaint = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = androidPaint;
            }
            androidPaint.setAlpha(renderNodeApi29.getAlpha());
            internalCanvas.saveLayer(left, top, right, bottom, androidPaint.asFrameworkPaint());
        } else {
            canvas.save();
        }
        canvas.translate(left, top);
        canvas.mo69concat58bKbWc(this.matrixCache.m285calculateMatrixGrdbGEg(renderNodeApi29));
        if (renderNodeApi29.getClipToOutline() || renderNodeApi29.getClipToBounds()) {
            this.outlineResolver.clipToOutline(canvas);
        }
        Function1 function1 = this.drawBlock;
        if (function1 != null) {
            function1.invoke(canvas);
        }
        canvas.restore();
        setDirty(false);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void invalidate() {
        if (!this.isDirty && !this.isDestroyed) {
            this.ownerView.invalidate();
            setDirty(true);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: isInLayer-k-4lQ0M */
    public final boolean mo269isInLayerk4lQ0M(long j) {
        float m45getXimpl = Offset.m45getXimpl(j);
        float m46getYimpl = Offset.m46getYimpl(j);
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        if (renderNodeApi29.getClipToBounds()) {
            if (0.0f <= m45getXimpl && m45getXimpl < renderNodeApi29.getWidth() && 0.0f <= m46getYimpl && m46getYimpl < renderNodeApi29.getHeight()) {
                return true;
            }
            return false;
        } else if (!renderNodeApi29.getClipToOutline()) {
            return true;
        } else {
            return this.outlineResolver.m286isInOutlinek4lQ0M(j);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void mapBounds(MutableRect mutableRect, boolean z) {
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        LayerMatrixCache layerMatrixCache = this.matrixCache;
        if (z) {
            float[] m284calculateInverseMatrixbWbORWo = layerMatrixCache.m284calculateInverseMatrixbWbORWo(renderNodeApi29);
            if (m284calculateInverseMatrixbWbORWo == null) {
                mutableRect.set();
                return;
            } else {
                androidx.compose.ui.graphics.Matrix.m106mapimpl(m284calculateInverseMatrixbWbORWo, mutableRect);
                return;
            }
        }
        androidx.compose.ui.graphics.Matrix.m106mapimpl(layerMatrixCache.m285calculateMatrixGrdbGEg(renderNodeApi29), mutableRect);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: mapOffset-8S9VItk */
    public final long mo270mapOffset8S9VItk(long j, boolean z) {
        long j2;
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        LayerMatrixCache layerMatrixCache = this.matrixCache;
        if (z) {
            float[] m284calculateInverseMatrixbWbORWo = layerMatrixCache.m284calculateInverseMatrixbWbORWo(renderNodeApi29);
            if (m284calculateInverseMatrixbWbORWo == null) {
                j2 = Offset.Infinite;
                return j2;
            }
            return androidx.compose.ui.graphics.Matrix.m105mapMKHz9U(m284calculateInverseMatrixbWbORWo, j);
        }
        return androidx.compose.ui.graphics.Matrix.m105mapMKHz9U(layerMatrixCache.m285calculateMatrixGrdbGEg(renderNodeApi29), j);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: move--gyyYBs */
    public final void mo271movegyyYBs(long j) {
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        int left = renderNodeApi29.getLeft();
        int top = renderNodeApi29.getTop();
        int i = (int) (j >> 32);
        int m402getYimpl = IntOffset.m402getYimpl(j);
        if (left != i || top != m402getYimpl) {
            renderNodeApi29.offsetLeftAndRight(i - left);
            renderNodeApi29.offsetTopAndBottom(m402getYimpl - top);
            AndroidComposeView ownerView = this.ownerView;
            Intrinsics.checkNotNullParameter(ownerView, "ownerView");
            ViewParent parent = ownerView.getParent();
            if (parent != null) {
                parent.onDescendantInvalidated(ownerView, ownerView);
            }
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: resize-ozmzZPI */
    public final void mo272resizeozmzZPI(long j) {
        int i = (int) (j >> 32);
        int m405getHeightimpl = IntSize.m405getHeightimpl(j);
        long j2 = this.transformOrigin;
        int i2 = TransformOrigin.$r8$clinit;
        float f = i;
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        renderNodeApi29.setPivotX(Float.intBitsToFloat((int) (j2 >> 32)) * f);
        float f2 = m405getHeightimpl;
        renderNodeApi29.setPivotY(TransformOrigin.m126getPivotFractionYimpl(this.transformOrigin) * f2);
        if (renderNodeApi29.setPosition(renderNodeApi29.getLeft(), renderNodeApi29.getTop(), renderNodeApi29.getLeft() + i, renderNodeApi29.getTop() + m405getHeightimpl)) {
            long Size = SizeKt.Size(f, f2);
            OutlineResolver outlineResolver = this.outlineResolver;
            outlineResolver.m287updateuvyYCjk(Size);
            renderNodeApi29.setOutline(outlineResolver.getOutline());
            if (!this.isDirty && !this.isDestroyed) {
                this.ownerView.invalidate();
                setDirty(true);
            }
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void reuseLayer(Function0 invalidateParentLayer, Function1 drawBlock) {
        long j;
        Intrinsics.checkNotNullParameter(drawBlock, "drawBlock");
        Intrinsics.checkNotNullParameter(invalidateParentLayer, "invalidateParentLayer");
        setDirty(false);
        this.isDestroyed = false;
        this.drawnWithZ = false;
        int i = TransformOrigin.$r8$clinit;
        j = TransformOrigin.Center;
        this.transformOrigin = j;
        this.drawBlock = drawBlock;
        this.invalidateParentLayer = invalidateParentLayer;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    @Override // androidx.compose.ui.node.OwnedLayer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateDisplayList() {
        /*
            r3 = this;
            boolean r0 = r3.isDirty
            androidx.compose.ui.platform.RenderNodeApi29 r1 = r3.renderNode
            if (r0 != 0) goto Lc
            boolean r0 = r1.getHasDisplayList()
            if (r0 != 0) goto L2d
        Lc:
            r0 = 0
            r3.setDirty(r0)
            boolean r0 = r1.getClipToOutline()
            if (r0 == 0) goto L23
            androidx.compose.ui.platform.OutlineResolver r0 = r3.outlineResolver
            boolean r2 = r0.getOutlineClipSupported()
            if (r2 != 0) goto L23
            androidx.compose.ui.graphics.Path r0 = r0.getClipPath()
            goto L24
        L23:
            r0 = 0
        L24:
            kotlin.jvm.functions.Function1 r2 = r3.drawBlock
            if (r2 == 0) goto L2d
            androidx.compose.ui.graphics.CanvasHolder r3 = r3.canvasHolder
            r1.record(r3, r0, r2)
        L2d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.RenderNodeLayer.updateDisplayList():void");
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: updateLayerProperties-dDxr-wY */
    public final void mo273updateLayerPropertiesdDxrwY(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j, Shape shape, boolean z, long j2, long j3, int i, LayoutDirection layoutDirection, Density density) {
        Function0 function0;
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(density, "density");
        this.transformOrigin = j;
        RenderNodeApi29 renderNodeApi29 = this.renderNode;
        boolean clipToOutline = renderNodeApi29.getClipToOutline();
        OutlineResolver outlineResolver = this.outlineResolver;
        boolean z2 = false;
        boolean z3 = clipToOutline && !outlineResolver.getOutlineClipSupported();
        renderNodeApi29.setScaleX(f);
        renderNodeApi29.setScaleY(f2);
        renderNodeApi29.setAlpha(f3);
        renderNodeApi29.setTranslationX(f4);
        renderNodeApi29.setTranslationY(f5);
        renderNodeApi29.setElevation(f6);
        renderNodeApi29.setAmbientShadowColor(ColorKt.m100toArgb8_81llA(j2));
        renderNodeApi29.setSpotShadowColor(ColorKt.m100toArgb8_81llA(j3));
        renderNodeApi29.setRotationZ(f9);
        renderNodeApi29.setRotationX(f7);
        renderNodeApi29.setRotationY(f8);
        renderNodeApi29.setCameraDistance(f10);
        int i2 = TransformOrigin.$r8$clinit;
        renderNodeApi29.setPivotX(Float.intBitsToFloat((int) (j >> 32)) * renderNodeApi29.getWidth());
        renderNodeApi29.setPivotY(TransformOrigin.m126getPivotFractionYimpl(j) * renderNodeApi29.getHeight());
        renderNodeApi29.setClipToOutline(z && shape != RectangleShapeKt.getRectangleShape());
        renderNodeApi29.setClipToBounds(z && shape == RectangleShapeKt.getRectangleShape());
        renderNodeApi29.setRenderEffect();
        renderNodeApi29.m288setCompositingStrategyaDBOjCE(i);
        boolean update = this.outlineResolver.update(shape, renderNodeApi29.getAlpha(), renderNodeApi29.getClipToOutline(), renderNodeApi29.getElevation(), layoutDirection, density);
        renderNodeApi29.setOutline(outlineResolver.getOutline());
        if (renderNodeApi29.getClipToOutline() && !outlineResolver.getOutlineClipSupported()) {
            z2 = true;
        }
        AndroidComposeView ownerView = this.ownerView;
        if (z3 == z2 && (!z2 || !update)) {
            Intrinsics.checkNotNullParameter(ownerView, "ownerView");
            ViewParent parent = ownerView.getParent();
            if (parent != null) {
                parent.onDescendantInvalidated(ownerView, ownerView);
            }
        } else if (!this.isDirty && !this.isDestroyed) {
            ownerView.invalidate();
            setDirty(true);
        }
        if (!this.drawnWithZ && renderNodeApi29.getElevation() > 0.0f && (function0 = this.invalidateParentLayer) != null) {
            function0.invoke();
        }
        this.matrixCache.invalidate();
    }
}
