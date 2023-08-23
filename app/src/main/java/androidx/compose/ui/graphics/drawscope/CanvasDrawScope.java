package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class CanvasDrawScope implements DrawScope {
    private AndroidPaint fillPaint;
    private AndroidPaint strokePaint;
    private final DrawParams drawParams = new DrawParams();
    private final CanvasDrawScope$drawContext$1 drawContext = new CanvasDrawScope$drawContext$1(this);

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class DrawParams {
        private Canvas canvas;
        private Density density;
        private LayoutDirection layoutDirection;
        private long size;

        public DrawParams() {
            long j;
            Density access$getDefaultDensity$p = CanvasDrawScopeKt.access$getDefaultDensity$p();
            LayoutDirection layoutDirection = LayoutDirection.Ltr;
            EmptyCanvas emptyCanvas = new EmptyCanvas();
            j = Size.Zero;
            this.density = access$getDefaultDensity$p;
            this.layoutDirection = layoutDirection;
            this.canvas = emptyCanvas;
            this.size = j;
        }

        public final Density component1() {
            return this.density;
        }

        public final LayoutDirection component2() {
            return this.layoutDirection;
        }

        public final Canvas component3() {
            return this.canvas;
        }

        /* renamed from: component4-NH-jbRc  reason: not valid java name */
        public final long m156component4NHjbRc() {
            return this.size;
        }

        public final boolean equals(Object obj) {
            boolean z;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DrawParams)) {
                return false;
            }
            DrawParams drawParams = (DrawParams) obj;
            if (!Intrinsics.areEqual(this.density, drawParams.density) || this.layoutDirection != drawParams.layoutDirection || !Intrinsics.areEqual(this.canvas, drawParams.canvas)) {
                return false;
            }
            long j = this.size;
            long j2 = drawParams.size;
            Size.Companion companion = Size.Companion;
            if (j == j2) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
            return false;
        }

        public final Canvas getCanvas() {
            return this.canvas;
        }

        public final Density getDensity() {
            return this.density;
        }

        public final LayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        /* renamed from: getSize-NH-jbRc  reason: not valid java name */
        public final long m157getSizeNHjbRc() {
            return this.size;
        }

        public final int hashCode() {
            int hashCode = this.layoutDirection.hashCode();
            int hashCode2 = this.canvas.hashCode();
            long j = this.size;
            Size.Companion companion = Size.Companion;
            return Long.hashCode(j) + ((hashCode2 + ((hashCode + (this.density.hashCode() * 31)) * 31)) * 31);
        }

        public final void setCanvas(Canvas canvas) {
            Intrinsics.checkNotNullParameter(canvas, "<set-?>");
            this.canvas = canvas;
        }

        public final void setDensity(Density density) {
            Intrinsics.checkNotNullParameter(density, "<set-?>");
            this.density = density;
        }

        public final void setLayoutDirection(LayoutDirection layoutDirection) {
            Intrinsics.checkNotNullParameter(layoutDirection, "<set-?>");
            this.layoutDirection = layoutDirection;
        }

        /* renamed from: setSize-uvyYCjk  reason: not valid java name */
        public final void m158setSizeuvyYCjk(long j) {
            this.size = j;
        }

        public final String toString() {
            return "DrawParams(density=" + this.density + ", layoutDirection=" + this.layoutDirection + ", canvas=" + this.canvas + ", size=" + ((Object) Size.m65toStringimpl(this.size)) + ')';
        }
    }

    /* renamed from: configurePaint-2qPWKa0$default  reason: not valid java name */
    static AndroidPaint m131configurePaint2qPWKa0$default(CanvasDrawScope canvasDrawScope, long j, DrawStyle drawStyle, float f, int i) {
        boolean z;
        AndroidPaint selectPaint = canvasDrawScope.selectPaint(drawStyle);
        long m136modulate5vOe2sY = m136modulate5vOe2sY(j, f);
        if (!Color.m93equalsimpl0(selectPaint.m77getColor0d7_KjU(), m136modulate5vOe2sY)) {
            selectPaint.m82setColor8_81llA(m136modulate5vOe2sY);
        }
        if (selectPaint.getShader() != null) {
            selectPaint.setShader(null);
        }
        if (!Intrinsics.areEqual(null, null)) {
            selectPaint.setColorFilter();
        }
        boolean z2 = false;
        if (selectPaint.m76getBlendMode0nO6VwU() == i) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            selectPaint.m81setBlendModes9anfk8(i);
        }
        if (selectPaint.m78getFilterQualityfv9h1I() == 1) {
            z2 = true;
        }
        if (!z2) {
            selectPaint.m83setFilterQualityvDHp3xo(1);
        }
        return selectPaint;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: configurePaint-swdJneE  reason: not valid java name */
    public final AndroidPaint m132configurePaintswdJneE(Brush brush, DrawStyle drawStyle, float f, int i, int i2) {
        boolean z;
        boolean z2;
        AndroidPaint selectPaint = selectPaint(drawStyle);
        boolean z3 = true;
        if (brush != null) {
            brush.mo89applyToPq9zytI(f, mo167getSizeNHjbRc(), selectPaint);
        } else {
            if (selectPaint.getAlpha() == f) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                selectPaint.setAlpha(f);
            }
        }
        if (!Intrinsics.areEqual(null, null)) {
            selectPaint.setColorFilter();
        }
        if (selectPaint.m76getBlendMode0nO6VwU() == i) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            selectPaint.m81setBlendModes9anfk8(i);
        }
        if (selectPaint.m78getFilterQualityfv9h1I() != i2) {
            z3 = false;
        }
        if (!z3) {
            selectPaint.m83setFilterQualityvDHp3xo(i2);
        }
        return selectPaint;
    }

    /* renamed from: configureStrokePaint-Q_0CZUI$default  reason: not valid java name */
    static AndroidPaint m134configureStrokePaintQ_0CZUI$default(CanvasDrawScope canvasDrawScope, long j, float f, int i, PathEffect pathEffect, float f2, int i2) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        AndroidPaint androidPaint = canvasDrawScope.strokePaint;
        if (androidPaint == null) {
            androidPaint = AndroidPaint_androidKt.Paint();
            androidPaint.m86setStylek9PVt8s(1);
            canvasDrawScope.strokePaint = androidPaint;
        }
        long m136modulate5vOe2sY = m136modulate5vOe2sY(j, f2);
        if (!Color.m93equalsimpl0(androidPaint.m77getColor0d7_KjU(), m136modulate5vOe2sY)) {
            androidPaint.m82setColor8_81llA(m136modulate5vOe2sY);
        }
        if (androidPaint.getShader() != null) {
            androidPaint.setShader(null);
        }
        if (!Intrinsics.areEqual(null, null)) {
            androidPaint.setColorFilter();
        }
        boolean z6 = false;
        if (androidPaint.m76getBlendMode0nO6VwU() == i2) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            androidPaint.m81setBlendModes9anfk8(i2);
        }
        if (androidPaint.getStrokeWidth() == f) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            androidPaint.setStrokeWidth(f);
        }
        if (androidPaint.getStrokeMiterLimit() == 4.0f) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z3) {
            androidPaint.setStrokeMiterLimit(4.0f);
        }
        if (androidPaint.m79getStrokeCapKaPHkGw() == i) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (!z4) {
            androidPaint.m84setStrokeCapBeK7IIE(i);
        }
        if (androidPaint.m80getStrokeJoinLxFBmk8() == 0) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (!z5) {
            androidPaint.m85setStrokeJoinWw9F2mQ(0);
        }
        if (!Intrinsics.areEqual(androidPaint.getPathEffect(), pathEffect)) {
            androidPaint.setPathEffect(pathEffect);
        }
        if (androidPaint.m78getFilterQualityfv9h1I() == 1) {
            z6 = true;
        }
        if (!z6) {
            androidPaint.m83setFilterQualityvDHp3xo(1);
        }
        return androidPaint;
    }

    /* renamed from: configureStrokePaint-ho4zsrM$default  reason: not valid java name */
    static AndroidPaint m135configureStrokePaintho4zsrM$default(CanvasDrawScope canvasDrawScope, Brush brush, float f, int i, PathEffect pathEffect, float f2, int i2) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        AndroidPaint androidPaint = canvasDrawScope.strokePaint;
        if (androidPaint == null) {
            androidPaint = AndroidPaint_androidKt.Paint();
            androidPaint.m86setStylek9PVt8s(1);
            canvasDrawScope.strokePaint = androidPaint;
        }
        boolean z7 = false;
        if (brush != null) {
            brush.mo89applyToPq9zytI(f2, canvasDrawScope.mo167getSizeNHjbRc(), androidPaint);
        } else {
            if (androidPaint.getAlpha() == f2) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                androidPaint.setAlpha(f2);
            }
        }
        if (!Intrinsics.areEqual(null, null)) {
            androidPaint.setColorFilter();
        }
        if (androidPaint.m76getBlendMode0nO6VwU() == i2) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            androidPaint.m81setBlendModes9anfk8(i2);
        }
        if (androidPaint.getStrokeWidth() == f) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z3) {
            androidPaint.setStrokeWidth(f);
        }
        if (androidPaint.getStrokeMiterLimit() == 4.0f) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (!z4) {
            androidPaint.setStrokeMiterLimit(4.0f);
        }
        if (androidPaint.m79getStrokeCapKaPHkGw() == i) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (!z5) {
            androidPaint.m84setStrokeCapBeK7IIE(i);
        }
        if (androidPaint.m80getStrokeJoinLxFBmk8() == 0) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (!z6) {
            androidPaint.m85setStrokeJoinWw9F2mQ(0);
        }
        if (!Intrinsics.areEqual(androidPaint.getPathEffect(), pathEffect)) {
            androidPaint.setPathEffect(pathEffect);
        }
        if (androidPaint.m78getFilterQualityfv9h1I() == 1) {
            z7 = true;
        }
        if (!z7) {
            androidPaint.m83setFilterQualityvDHp3xo(1);
        }
        return androidPaint;
    }

    /* renamed from: modulate-5vOe2sY  reason: not valid java name */
    private static long m136modulate5vOe2sY(long j, float f) {
        boolean z;
        long Color;
        if (f == 1.0f) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            Color = ColorKt.Color(Color.m97getRedimpl(j), Color.m96getGreenimpl(j), Color.m95getBlueimpl(j), Color.m94getAlphaimpl(j) * f, ColorSpaces.getColorSpacesArray$ui_graphics_release()[(int) (j & 63)]);
            return Color;
        }
        return j;
    }

    private final AndroidPaint selectPaint(DrawStyle drawStyle) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = false;
        if (Intrinsics.areEqual(drawStyle, Fill.INSTANCE)) {
            AndroidPaint androidPaint = this.fillPaint;
            if (androidPaint == null) {
                AndroidPaint Paint = AndroidPaint_androidKt.Paint();
                Paint.m86setStylek9PVt8s(0);
                this.fillPaint = Paint;
                return Paint;
            }
            return androidPaint;
        } else if (drawStyle instanceof Stroke) {
            AndroidPaint androidPaint2 = this.strokePaint;
            if (androidPaint2 == null) {
                androidPaint2 = AndroidPaint_androidKt.Paint();
                androidPaint2.m86setStylek9PVt8s(1);
                this.strokePaint = androidPaint2;
            }
            Stroke stroke = (Stroke) drawStyle;
            if (androidPaint2.getStrokeWidth() == stroke.getWidth()) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                androidPaint2.setStrokeWidth(stroke.getWidth());
            }
            if (androidPaint2.m79getStrokeCapKaPHkGw() == stroke.m168getCapKaPHkGw()) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                androidPaint2.m84setStrokeCapBeK7IIE(stroke.m168getCapKaPHkGw());
            }
            if (androidPaint2.getStrokeMiterLimit() == stroke.getMiter()) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!z3) {
                androidPaint2.setStrokeMiterLimit(stroke.getMiter());
            }
            if (androidPaint2.m80getStrokeJoinLxFBmk8() == stroke.m169getJoinLxFBmk8()) {
                z4 = true;
            }
            if (!z4) {
                androidPaint2.m85setStrokeJoinWw9F2mQ(stroke.m169getJoinLxFBmk8());
            }
            if (!Intrinsics.areEqual(androidPaint2.getPathEffect(), stroke.getPathEffect())) {
                androidPaint2.setPathEffect(stroke.getPathEffect());
            }
            return androidPaint2;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawArc-illE91I  reason: not valid java name */
    public final void mo137drawArcillE91I(Brush brush, float f, float f2, boolean z, long j, long j2, float f3, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().drawArc(Offset.m45getXimpl(j), Offset.m46getYimpl(j), Size.m64getWidthimpl(j2) + Offset.m45getXimpl(j), Size.m63getHeightimpl(j2) + Offset.m46getYimpl(j), f, f2, z, m132configurePaintswdJneE(brush, style, f3, i, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawArc-yD3GUKo  reason: not valid java name */
    public final void mo138drawArcyD3GUKo(long j, float f, float f2, boolean z, long j2, long j3, float f3, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().drawArc(Offset.m45getXimpl(j2), Offset.m46getYimpl(j2), Size.m64getWidthimpl(j3) + Offset.m45getXimpl(j2), Size.m63getHeightimpl(j3) + Offset.m46getYimpl(j2), f, f2, z, m131configurePaint2qPWKa0$default(this, j, style, f3, i));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-V9BoPsw  reason: not valid java name */
    public final void mo139drawCircleV9BoPsw(Brush brush, float f, long j, float f2, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().mo70drawCircle9KIMszo(f, j, m132configurePaintswdJneE(brush, style, f2, i, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-VaOC9Bg  reason: not valid java name */
    public final void mo140drawCircleVaOC9Bg(long j, float f, long j2, float f2, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().mo70drawCircle9KIMszo(f, j2, m131configurePaint2qPWKa0$default(this, j, style, f2, i));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawImage-9jGpkUE  reason: not valid java name */
    public final /* synthetic */ void mo141drawImage9jGpkUE(ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(null, "image");
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().mo72drawImageRectHPBpro0(m132configurePaintswdJneE(null, style, f, i, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawImage-AZ2fEMs  reason: not valid java name */
    public final void mo142drawImageAZ2fEMs(ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle style, ColorFilter colorFilter, int i, int i2) {
        Intrinsics.checkNotNullParameter(null, "image");
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().mo72drawImageRectHPBpro0(m132configurePaintswdJneE(null, style, f, i, i2));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawImage-gbVJVH8  reason: not valid java name */
    public final void mo143drawImagegbVJVH8(ImageBitmap imageBitmap, long j, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(null, "image");
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().mo71drawImaged4ec7I(m132configurePaintswdJneE(null, style, f, i, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawLine-1RTmtNc  reason: not valid java name */
    public final void mo144drawLine1RTmtNc(Brush brush, long j, long j2, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        this.drawParams.getCanvas().mo73drawLineWko1d7g(j, j2, m135configureStrokePaintho4zsrM$default(this, brush, f, i, pathEffect, f2, i2));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawLine-NGM6Ib0  reason: not valid java name */
    public final void mo145drawLineNGM6Ib0(long j, long j2, long j3, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2) {
        this.drawParams.getCanvas().mo73drawLineWko1d7g(j2, j3, m134configureStrokePaintQ_0CZUI$default(this, j, f, i, pathEffect, f2, i2));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawOval-AsUm42w  reason: not valid java name */
    public final void mo146drawOvalAsUm42w(Brush brush, long j, long j2, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().drawOval(Offset.m45getXimpl(j), Offset.m46getYimpl(j), Offset.m45getXimpl(j) + Size.m64getWidthimpl(j2), Size.m63getHeightimpl(j2) + Offset.m46getYimpl(j), m132configurePaintswdJneE(brush, style, f, i, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawOval-n-J9OG0  reason: not valid java name */
    public final void mo147drawOvalnJ9OG0(long j, long j2, long j3, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().drawOval(Offset.m45getXimpl(j2), Offset.m46getYimpl(j2), Size.m64getWidthimpl(j3) + Offset.m45getXimpl(j2), Size.m63getHeightimpl(j3) + Offset.m46getYimpl(j2), m131configurePaint2qPWKa0$default(this, j, style, f, i));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPath-GBMwjPU  reason: not valid java name */
    public final void mo148drawPathGBMwjPU(Path path, Brush brush, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().drawPath(path, m132configurePaintswdJneE(brush, style, f, i, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPath-LG529CI  reason: not valid java name */
    public final void mo149drawPathLG529CI(Path path, long j, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().drawPath(path, m131configurePaint2qPWKa0$default(this, j, style, f, i));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPoints-F8ZwMP8  reason: not valid java name */
    public final void mo150drawPointsF8ZwMP8(List points, int i, long j, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3) {
        Intrinsics.checkNotNullParameter(points, "points");
        this.drawParams.getCanvas().mo74drawPointsO7TthRY(i, m134configureStrokePaintQ_0CZUI$default(this, j, f, i2, pathEffect, f2, i3), points);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPoints-Gsft0Ws  reason: not valid java name */
    public final void mo151drawPointsGsft0Ws(List points, int i, Brush brush, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3) {
        Intrinsics.checkNotNullParameter(points, "points");
        Intrinsics.checkNotNullParameter(brush, "brush");
        this.drawParams.getCanvas().mo74drawPointsO7TthRY(i, m135configureStrokePaintho4zsrM$default(this, brush, f, i2, pathEffect, f2, i3), points);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-AsUm42w  reason: not valid java name */
    public final void mo152drawRectAsUm42w(Brush brush, long j, long j2, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().drawRect(Offset.m45getXimpl(j), Offset.m46getYimpl(j), Offset.m45getXimpl(j) + Size.m64getWidthimpl(j2), Size.m63getHeightimpl(j2) + Offset.m46getYimpl(j), m132configurePaintswdJneE(brush, style, f, i, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-n-J9OG0  reason: not valid java name */
    public final void mo153drawRectnJ9OG0(long j, long j2, long j3, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().drawRect(Offset.m45getXimpl(j2), Offset.m46getYimpl(j2), Size.m64getWidthimpl(j3) + Offset.m45getXimpl(j2), Size.m63getHeightimpl(j3) + Offset.m46getYimpl(j2), m131configurePaint2qPWKa0$default(this, j, style, f, i));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-ZuiqVtQ  reason: not valid java name */
    public final void mo154drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().drawRoundRect(Offset.m45getXimpl(j), Offset.m46getYimpl(j), Offset.m45getXimpl(j) + Size.m64getWidthimpl(j2), Size.m63getHeightimpl(j2) + Offset.m46getYimpl(j), CornerRadius.m39getXimpl(j3), CornerRadius.m40getYimpl(j3), m132configurePaintswdJneE(brush, style, f, i, 1));
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-u-Aw5IA  reason: not valid java name */
    public final void mo155drawRoundRectuAw5IA(long j, long j2, long j3, long j4, DrawStyle style, float f, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.drawParams.getCanvas().drawRoundRect(Offset.m45getXimpl(j2), Offset.m46getYimpl(j2), Offset.m45getXimpl(j2) + Size.m64getWidthimpl(j3), Offset.m46getYimpl(j2) + Size.m63getHeightimpl(j3), CornerRadius.m39getXimpl(j4), CornerRadius.m40getYimpl(j4), m131configurePaint2qPWKa0$default(this, j, style, f, i));
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.drawParams.getDensity().getDensity();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public final DrawContext getDrawContext() {
        return this.drawContext;
    }

    public final DrawParams getDrawParams() {
        return this.drawParams;
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getFontScale() {
        return this.drawParams.getDensity().getFontScale();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public final LayoutDirection getLayoutDirection() {
        return this.drawParams.getLayoutDirection();
    }
}
