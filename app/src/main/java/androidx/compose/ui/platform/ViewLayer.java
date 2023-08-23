package androidx.compose.ui.platform;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.View;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ViewLayer extends View implements OwnedLayer {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static boolean hasRetrievedMethod;
    private static Field recreateDisplayList;
    private static boolean shouldUseDispatchDraw;
    private static Method updateDisplayListIfDirtyMethod;
    private final CanvasHolder canvasHolder;
    private Rect clipBoundsCache;
    private boolean clipToBounds;
    private final DrawChildContainer container;
    private Function1 drawBlock;
    private boolean drawnWithZ;
    private Function0 invalidateParentLayer;
    private boolean isInvalidated;
    private boolean mHasOverlappingRendering;
    private long mTransformOrigin;
    private final LayerMatrixCache matrixCache;
    private final OutlineResolver outlineResolver;
    private final AndroidComposeView ownerView;
    private static final Function2 getMatrix = new Function2() { // from class: androidx.compose.ui.platform.ViewLayer$Companion$getMatrix$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            View view = (View) obj;
            Matrix matrix = (Matrix) obj2;
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(matrix, "matrix");
            matrix.set(view.getMatrix());
            return Unit.INSTANCE;
        }
    };
    private static final ViewLayer$Companion$OutlineProvider$1 OutlineProvider = new ViewLayer$Companion$OutlineProvider$1();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class Companion {
        public static void updateDisplayList(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            try {
                if (!ViewLayer.hasRetrievedMethod) {
                    ViewLayer.hasRetrievedMethod = true;
                    ViewLayer.updateDisplayListIfDirtyMethod = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass()).invoke(View.class, "updateDisplayListIfDirty", new Class[0]);
                    ViewLayer.recreateDisplayList = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(View.class, "mRecreateDisplayList");
                    Method method = ViewLayer.updateDisplayListIfDirtyMethod;
                    if (method != null) {
                        method.setAccessible(true);
                    }
                    Field field = ViewLayer.recreateDisplayList;
                    if (field != null) {
                        field.setAccessible(true);
                    }
                }
                Field field2 = ViewLayer.recreateDisplayList;
                if (field2 != null) {
                    field2.setBoolean(view, true);
                }
                Method method2 = ViewLayer.updateDisplayListIfDirtyMethod;
                if (method2 != null) {
                    method2.invoke(view, new Object[0]);
                }
            } catch (Throwable unused) {
                ViewLayer.shouldUseDispatchDraw = true;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewLayer(AndroidComposeView ownerView, DrawChildContainer drawChildContainer, Function1 drawBlock, Function0 invalidateParentLayer) {
        super(ownerView.getContext());
        long j;
        Intrinsics.checkNotNullParameter(ownerView, "ownerView");
        Intrinsics.checkNotNullParameter(drawBlock, "drawBlock");
        Intrinsics.checkNotNullParameter(invalidateParentLayer, "invalidateParentLayer");
        this.ownerView = ownerView;
        this.container = drawChildContainer;
        this.drawBlock = drawBlock;
        this.invalidateParentLayer = invalidateParentLayer;
        this.outlineResolver = new OutlineResolver(ownerView.getDensity());
        this.canvasHolder = new CanvasHolder();
        this.matrixCache = new LayerMatrixCache(getMatrix);
        j = TransformOrigin.Center;
        this.mTransformOrigin = j;
        this.mHasOverlappingRendering = true;
        setWillNotDraw(false);
        drawChildContainer.addView(this);
        View.generateViewId();
    }

    public static final /* synthetic */ boolean access$getHasRetrievedMethod$cp() {
        return hasRetrievedMethod;
    }

    public static final /* synthetic */ boolean access$getShouldUseDispatchDraw$cp() {
        return shouldUseDispatchDraw;
    }

    private final Path getManualClipPath() {
        if (getClipToOutline() && !this.outlineResolver.getOutlineClipSupported()) {
            return this.outlineResolver.getClipPath();
        }
        return null;
    }

    private final void resetClipBounds() {
        Rect rect;
        if (this.clipToBounds) {
            Rect rect2 = this.clipBoundsCache;
            if (rect2 == null) {
                this.clipBoundsCache = new Rect(0, 0, getWidth(), getHeight());
            } else {
                Intrinsics.checkNotNull(rect2);
                rect2.set(0, 0, getWidth(), getHeight());
            }
            rect = this.clipBoundsCache;
        } else {
            rect = null;
        }
        setClipBounds(rect);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void destroy() {
        if (this.isInvalidated) {
            this.isInvalidated = false;
            this.ownerView.notifyLayerIsDirty$ui_release(this, false);
        }
        this.ownerView.requestClearInvalidObservations();
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        this.ownerView.recycle$ui_release(this);
        this.container.removeViewInLayout(this);
    }

    @Override // android.view.View
    protected final void dispatchDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        boolean z = false;
        if (this.isInvalidated) {
            this.isInvalidated = false;
            this.ownerView.notifyLayerIsDirty$ui_release(this, false);
        }
        CanvasHolder canvasHolder = this.canvasHolder;
        Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
        canvasHolder.getAndroidCanvas().setInternalCanvas(canvas);
        AndroidCanvas androidCanvas = canvasHolder.getAndroidCanvas();
        if (getManualClipPath() != null || !canvas.isHardwareAccelerated()) {
            androidCanvas.save();
            this.outlineResolver.clipToOutline(androidCanvas);
            z = true;
        }
        Function1 function1 = this.drawBlock;
        if (function1 != null) {
            function1.invoke(androidCanvas);
        }
        if (z) {
            androidCanvas.restore();
        }
        canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void drawLayer(androidx.compose.ui.graphics.Canvas canvas) {
        boolean z;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (getElevation() > 0.0f) {
            z = true;
        } else {
            z = false;
        }
        this.drawnWithZ = z;
        if (z) {
            canvas.enableZ();
        }
        this.container.drawChild$ui_release(canvas, this, getDrawingTime());
        if (this.drawnWithZ) {
            canvas.disableZ();
        }
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return this.mHasOverlappingRendering;
    }

    @Override // android.view.View, androidx.compose.ui.node.OwnedLayer
    public final void invalidate() {
        boolean z = this.isInvalidated;
        if (!z) {
            if (true != z) {
                this.isInvalidated = true;
                this.ownerView.notifyLayerIsDirty$ui_release(this, true);
            }
            super.invalidate();
            this.ownerView.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: isInLayer-k-4lQ0M */
    public final boolean mo269isInLayerk4lQ0M(long j) {
        float m45getXimpl = Offset.m45getXimpl(j);
        float m46getYimpl = Offset.m46getYimpl(j);
        if (this.clipToBounds) {
            if (0.0f <= m45getXimpl && m45getXimpl < getWidth() && 0.0f <= m46getYimpl && m46getYimpl < getHeight()) {
                return true;
            }
            return false;
        } else if (!getClipToOutline()) {
            return true;
        } else {
            return this.outlineResolver.m286isInOutlinek4lQ0M(j);
        }
    }

    public final boolean isInvalidated() {
        return this.isInvalidated;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void mapBounds(MutableRect mutableRect, boolean z) {
        if (z) {
            float[] m284calculateInverseMatrixbWbORWo = this.matrixCache.m284calculateInverseMatrixbWbORWo(this);
            if (m284calculateInverseMatrixbWbORWo != null) {
                androidx.compose.ui.graphics.Matrix.m106mapimpl(m284calculateInverseMatrixbWbORWo, mutableRect);
                return;
            } else {
                mutableRect.set();
                return;
            }
        }
        androidx.compose.ui.graphics.Matrix.m106mapimpl(this.matrixCache.m285calculateMatrixGrdbGEg(this), mutableRect);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: mapOffset-8S9VItk */
    public final long mo270mapOffset8S9VItk(long j, boolean z) {
        long j2;
        if (z) {
            float[] m284calculateInverseMatrixbWbORWo = this.matrixCache.m284calculateInverseMatrixbWbORWo(this);
            if (m284calculateInverseMatrixbWbORWo != null) {
                return androidx.compose.ui.graphics.Matrix.m105mapMKHz9U(m284calculateInverseMatrixbWbORWo, j);
            }
            Offset.Companion companion = Offset.Companion;
            j2 = Offset.Infinite;
            return j2;
        }
        return androidx.compose.ui.graphics.Matrix.m105mapMKHz9U(this.matrixCache.m285calculateMatrixGrdbGEg(this), j);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: move--gyyYBs */
    public final void mo271movegyyYBs(long j) {
        IntOffset.Companion companion = IntOffset.Companion;
        int i = (int) (j >> 32);
        if (i != getLeft()) {
            offsetLeftAndRight(i - getLeft());
            this.matrixCache.invalidate();
        }
        int m402getYimpl = IntOffset.m402getYimpl(j);
        if (m402getYimpl != getTop()) {
            offsetTopAndBottom(m402getYimpl - getTop());
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: resize-ozmzZPI */
    public final void mo272resizeozmzZPI(long j) {
        ViewLayer$Companion$OutlineProvider$1 viewLayer$Companion$OutlineProvider$1;
        int i = (int) (j >> 32);
        int m405getHeightimpl = IntSize.m405getHeightimpl(j);
        if (i != getWidth() || m405getHeightimpl != getHeight()) {
            long j2 = this.mTransformOrigin;
            int i2 = TransformOrigin.$r8$clinit;
            float f = i;
            setPivotX(Float.intBitsToFloat((int) (j2 >> 32)) * f);
            float f2 = m405getHeightimpl;
            setPivotY(TransformOrigin.m126getPivotFractionYimpl(this.mTransformOrigin) * f2);
            this.outlineResolver.m287updateuvyYCjk(SizeKt.Size(f, f2));
            if (this.outlineResolver.getOutline() != null) {
                viewLayer$Companion$OutlineProvider$1 = OutlineProvider;
            } else {
                viewLayer$Companion$OutlineProvider$1 = null;
            }
            setOutlineProvider(viewLayer$Companion$OutlineProvider$1);
            layout(getLeft(), getTop(), getLeft() + i, getTop() + m405getHeightimpl);
            resetClipBounds();
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void reuseLayer(Function0 invalidateParentLayer, Function1 drawBlock) {
        long j;
        Intrinsics.checkNotNullParameter(drawBlock, "drawBlock");
        Intrinsics.checkNotNullParameter(invalidateParentLayer, "invalidateParentLayer");
        this.container.addView(this);
        this.clipToBounds = false;
        this.drawnWithZ = false;
        int i = TransformOrigin.$r8$clinit;
        j = TransformOrigin.Center;
        this.mTransformOrigin = j;
        this.drawBlock = drawBlock;
        this.invalidateParentLayer = invalidateParentLayer;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public final void updateDisplayList() {
        boolean z = this.isInvalidated;
        if (z && !shouldUseDispatchDraw) {
            if (z) {
                this.isInvalidated = false;
                this.ownerView.notifyLayerIsDirty$ui_release(this, false);
            }
            Companion.updateDisplayList(this);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* renamed from: updateLayerProperties-dDxr-wY */
    public final void mo273updateLayerPropertiesdDxrwY(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, long j, Shape shape, boolean z, long j2, long j3, int i, LayoutDirection layoutDirection, Density density) {
        Function0 function0;
        Intrinsics.checkNotNullParameter(shape, "shape");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(density, "density");
        this.mTransformOrigin = j;
        setScaleX(f);
        setScaleY(f2);
        setAlpha(f3);
        setTranslationX(f4);
        setTranslationY(f5);
        setElevation(f6);
        setRotation(f9);
        setRotationX(f7);
        setRotationY(f8);
        long j4 = this.mTransformOrigin;
        int i2 = TransformOrigin.$r8$clinit;
        setPivotX(Float.intBitsToFloat((int) (j4 >> 32)) * getWidth());
        setPivotY(TransformOrigin.m126getPivotFractionYimpl(this.mTransformOrigin) * getHeight());
        setCameraDistance(getResources().getDisplayMetrics().densityDpi * f10);
        boolean z2 = true;
        this.clipToBounds = z && shape == RectangleShapeKt.getRectangleShape();
        resetClipBounds();
        boolean z3 = getManualClipPath() != null;
        setClipToOutline(z && shape != RectangleShapeKt.getRectangleShape());
        boolean update = this.outlineResolver.update(shape, getAlpha(), getClipToOutline(), getElevation(), layoutDirection, density);
        setOutlineProvider(this.outlineResolver.getOutline() != null ? OutlineProvider : null);
        boolean z4 = getManualClipPath() != null;
        if (z3 != z4 || (z4 && update)) {
            invalidate();
        }
        if (!this.drawnWithZ && getElevation() > 0.0f && (function0 = this.invalidateParentLayer) != null) {
            function0.invoke();
        }
        this.matrixCache.invalidate();
        setOutlineAmbientShadowColor(ColorKt.m100toArgb8_81llA(j2));
        setOutlineSpotShadowColor(ColorKt.m100toArgb8_81llA(j3));
        setRenderEffect(null);
        if (i == 1) {
            setLayerType(2, null);
        } else {
            if (i == 2) {
                setLayerType(0, null);
                z2 = false;
            } else {
                setLayerType(0, null);
            }
        }
        this.mHasOverlappingRendering = z2;
    }

    @Override // android.view.View
    public final void forceLayout() {
    }

    @Override // android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }
}
