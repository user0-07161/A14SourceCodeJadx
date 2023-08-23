package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface DrawScope extends Density {
    /* renamed from: drawCircle-VaOC9Bg$default  reason: not valid java name */
    static void m163drawCircleVaOC9Bg$default(DrawScope drawScope, long j, float f, long j2, Stroke stroke, int i) {
        float f2;
        long j3;
        float f3;
        Fill fill;
        int i2;
        if ((i & 2) != 0) {
            long mo167getSizeNHjbRc = drawScope.mo167getSizeNHjbRc();
            f2 = Math.min(Math.abs(Size.m64getWidthimpl(mo167getSizeNHjbRc)), Math.abs(Size.m63getHeightimpl(mo167getSizeNHjbRc))) / 2.0f;
        } else {
            f2 = f;
        }
        if ((i & 4) != 0) {
            j3 = drawScope.mo166getCenterF1C5BW0();
        } else {
            j3 = j2;
        }
        if ((i & 8) != 0) {
            f3 = 1.0f;
        } else {
            f3 = 0.0f;
        }
        float f4 = f3;
        if ((i & 16) != 0) {
            fill = Fill.INSTANCE;
        } else {
            fill = stroke;
        }
        if ((i & 64) != 0) {
            i2 = 3;
        } else {
            i2 = 0;
        }
        drawScope.mo140drawCircleVaOC9Bg(j, f2, j3, f4, fill, null, i2);
    }

    /* renamed from: drawPath-LG529CI$default  reason: not valid java name */
    static /* synthetic */ void m165drawPathLG529CI$default(DrawScope drawScope, Path path, long j, Stroke stroke, int i) {
        float f;
        int i2;
        if ((i & 4) != 0) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        float f2 = f;
        Fill fill = stroke;
        if ((i & 8) != 0) {
            fill = Fill.INSTANCE;
        }
        DrawStyle drawStyle = fill;
        if ((i & 32) != 0) {
            i2 = 3;
        } else {
            i2 = 0;
        }
        drawScope.mo149drawPathLG529CI(path, j, f2, drawStyle, null, i2);
    }

    /* renamed from: drawArc-illE91I */
    void mo137drawArcillE91I(Brush brush, float f, float f2, boolean z, long j, long j2, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawArc-yD3GUKo */
    void mo138drawArcyD3GUKo(long j, float f, float f2, boolean z, long j2, long j3, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawCircle-V9BoPsw */
    void mo139drawCircleV9BoPsw(Brush brush, float f, long j, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawCircle-VaOC9Bg */
    void mo140drawCircleVaOC9Bg(long j, float f, long j2, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawImage-9jGpkUE */
    /* synthetic */ void mo141drawImage9jGpkUE(ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawImage-AZ2fEMs */
    void mo142drawImageAZ2fEMs(ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2);

    /* renamed from: drawImage-gbVJVH8 */
    void mo143drawImagegbVJVH8(ImageBitmap imageBitmap, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawLine-1RTmtNc */
    void mo144drawLine1RTmtNc(Brush brush, long j, long j2, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2);

    /* renamed from: drawLine-NGM6Ib0 */
    void mo145drawLineNGM6Ib0(long j, long j2, long j3, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2);

    /* renamed from: drawOval-AsUm42w */
    void mo146drawOvalAsUm42w(Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawOval-n-J9OG0 */
    void mo147drawOvalnJ9OG0(long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawPath-GBMwjPU */
    void mo148drawPathGBMwjPU(Path path, Brush brush, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawPath-LG529CI */
    void mo149drawPathLG529CI(Path path, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawPoints-F8ZwMP8 */
    void mo150drawPointsF8ZwMP8(List list, int i, long j, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3);

    /* renamed from: drawPoints-Gsft0Ws */
    void mo151drawPointsGsft0Ws(List list, int i, Brush brush, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3);

    /* renamed from: drawRect-AsUm42w */
    void mo152drawRectAsUm42w(Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawRect-n-J9OG0 */
    void mo153drawRectnJ9OG0(long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawRoundRect-ZuiqVtQ */
    void mo154drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i);

    /* renamed from: drawRoundRect-u-Aw5IA */
    void mo155drawRoundRectuAw5IA(long j, long j2, long j3, long j4, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i);

    /* renamed from: getCenter-F1C5BW0  reason: not valid java name */
    default long mo166getCenterF1C5BW0() {
        long m159getSizeNHjbRc = ((CanvasDrawScope$drawContext$1) getDrawContext()).m159getSizeNHjbRc();
        return OffsetKt.Offset(Size.m64getWidthimpl(m159getSizeNHjbRc) / 2.0f, Size.m63getHeightimpl(m159getSizeNHjbRc) / 2.0f);
    }

    DrawContext getDrawContext();

    LayoutDirection getLayoutDirection();

    /* renamed from: getSize-NH-jbRc  reason: not valid java name */
    default long mo167getSizeNHjbRc() {
        return ((CanvasDrawScope$drawContext$1) getDrawContext()).m159getSizeNHjbRc();
    }
}
