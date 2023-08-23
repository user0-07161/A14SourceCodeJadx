package com.android.egg.landroid;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class VisibleUniverseKt$zoom$ds$1 implements ZoomedDrawScope, DrawScope {
    private final /* synthetic */ DrawScope $$delegate_0;
    private float zoom;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VisibleUniverseKt$zoom$ds$1(DrawScope drawScope, float f) {
        this.$$delegate_0 = drawScope;
        this.zoom = f;
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawArc-illE91I */
    public void mo137drawArcillE91I(Brush brush, float f, float f2, boolean z, long j, long j2, float f3, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo137drawArcillE91I(brush, f, f2, z, j, j2, f3, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawArc-yD3GUKo */
    public void mo138drawArcyD3GUKo(long j, float f, float f2, boolean z, long j2, long j3, float f3, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo138drawArcyD3GUKo(j, f, f2, z, j2, j3, f3, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-V9BoPsw */
    public void mo139drawCircleV9BoPsw(Brush brush, float f, long j, float f2, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo139drawCircleV9BoPsw(brush, f, j, f2, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-VaOC9Bg */
    public void mo140drawCircleVaOC9Bg(long j, float f, long j2, float f2, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo140drawCircleVaOC9Bg(j, f, j2, f2, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawImage-9jGpkUE */
    public /* synthetic */ void mo141drawImage9jGpkUE(ImageBitmap image, long j, long j2, long j3, long j4, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo141drawImage9jGpkUE(image, j, j2, j3, j4, f, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawImage-AZ2fEMs */
    public void mo142drawImageAZ2fEMs(ImageBitmap image, long j, long j2, long j3, long j4, float f, DrawStyle style, ColorFilter colorFilter, int i, int i2) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo142drawImageAZ2fEMs(image, j, j2, j3, j4, f, style, colorFilter, i, i2);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawImage-gbVJVH8 */
    public void mo143drawImagegbVJVH8(ImageBitmap image, long j, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo143drawImagegbVJVH8(image, j, f, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawLine-1RTmtNc */
    public void mo144drawLine1RTmtNc(Brush brush, long j, long j2, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        this.$$delegate_0.mo144drawLine1RTmtNc(brush, j, j2, f, i, pathEffect, f2, colorFilter, i2);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawLine-NGM6Ib0 */
    public void mo145drawLineNGM6Ib0(long j, long j2, long j3, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2) {
        this.$$delegate_0.mo145drawLineNGM6Ib0(j, j2, j3, f, i, pathEffect, f2, colorFilter, i2);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawOval-AsUm42w */
    public void mo146drawOvalAsUm42w(Brush brush, long j, long j2, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo146drawOvalAsUm42w(brush, j, j2, f, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawOval-n-J9OG0 */
    public void mo147drawOvalnJ9OG0(long j, long j2, long j3, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo147drawOvalnJ9OG0(j, j2, j3, f, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPath-GBMwjPU */
    public void mo148drawPathGBMwjPU(Path path, Brush brush, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo148drawPathGBMwjPU(path, brush, f, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPath-LG529CI */
    public void mo149drawPathLG529CI(Path path, long j, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo149drawPathLG529CI(path, j, f, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPoints-F8ZwMP8 */
    public void mo150drawPointsF8ZwMP8(List points, int i, long j, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3) {
        Intrinsics.checkNotNullParameter(points, "points");
        this.$$delegate_0.mo150drawPointsF8ZwMP8(points, i, j, f, i2, pathEffect, f2, colorFilter, i3);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPoints-Gsft0Ws */
    public void mo151drawPointsGsft0Ws(List points, int i, Brush brush, float f, int i2, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i3) {
        Intrinsics.checkNotNullParameter(points, "points");
        Intrinsics.checkNotNullParameter(brush, "brush");
        this.$$delegate_0.mo151drawPointsGsft0Ws(points, i, brush, f, i2, pathEffect, f2, colorFilter, i3);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-AsUm42w */
    public void mo152drawRectAsUm42w(Brush brush, long j, long j2, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo152drawRectAsUm42w(brush, j, j2, f, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-n-J9OG0 */
    public void mo153drawRectnJ9OG0(long j, long j2, long j3, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo153drawRectnJ9OG0(j, j2, j3, f, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-ZuiqVtQ */
    public void mo154drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle style, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo154drawRoundRectZuiqVtQ(brush, j, j2, j3, f, style, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-u-Aw5IA */
    public void mo155drawRoundRectuAw5IA(long j, long j2, long j3, long j4, DrawStyle style, float f, ColorFilter colorFilter, int i) {
        Intrinsics.checkNotNullParameter(style, "style");
        this.$$delegate_0.mo155drawRoundRectuAw5IA(j, j2, j3, j4, style, f, colorFilter, i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: getCenter-F1C5BW0 */
    public long mo166getCenterF1C5BW0() {
        return this.$$delegate_0.mo166getCenterF1C5BW0();
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.$$delegate_0.getDensity();
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    public DrawContext getDrawContext() {
        return this.$$delegate_0.getDrawContext();
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    public float getFontScale() {
        return this.$$delegate_0.getFontScale();
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    public LayoutDirection getLayoutDirection() {
        return this.$$delegate_0.getLayoutDirection();
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: getSize-NH-jbRc */
    public long mo167getSizeNHjbRc() {
        return this.$$delegate_0.mo167getSizeNHjbRc();
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope
    public float getZoom() {
        return this.zoom;
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: roundToPx--R2X_6o */
    public int mo235roundToPxR2X_6o(long j) {
        return this.$$delegate_0.mo235roundToPxR2X_6o(j);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: roundToPx-0680j_4 */
    public int mo202roundToPx0680j_4(float f) {
        return this.$$delegate_0.mo202roundToPx0680j_4(f);
    }

    public void setZoom(float f) {
        this.zoom = f;
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: toDp-GaN1DYA */
    public float mo236toDpGaN1DYA(long j) {
        return this.$$delegate_0.mo236toDpGaN1DYA(j);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public float mo203toDpu2uoSUM(float f) {
        return this.$$delegate_0.mo203toDpu2uoSUM(f);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: toDpSize-k-rfVVM */
    public long mo237toDpSizekrfVVM(long j) {
        return this.$$delegate_0.mo237toDpSizekrfVVM(j);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: toPx--R2X_6o */
    public float mo205toPxR2X_6o(long j) {
        return this.$$delegate_0.mo205toPxR2X_6o(j);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: toPx-0680j_4 */
    public float mo206toPx0680j_4(float f) {
        return this.$$delegate_0.mo206toPx0680j_4(f);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope
    public Rect toRect(DpRect dpRect) {
        Intrinsics.checkNotNullParameter(dpRect, "<this>");
        throw null;
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: toSize-XkaWNTQ */
    public long mo207toSizeXkaWNTQ(long j) {
        return this.$$delegate_0.mo207toSizeXkaWNTQ(j);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: toSp-0xMU5do */
    public long mo238toSp0xMU5do(float f) {
        return this.$$delegate_0.mo238toSp0xMU5do(f);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public long mo239toSpkPz2Gy4(float f) {
        return this.$$delegate_0.mo239toSpkPz2Gy4(f);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public float mo204toDpu2uoSUM(int i) {
        return this.$$delegate_0.mo204toDpu2uoSUM(i);
    }

    @Override // com.android.egg.landroid.ZoomedDrawScope, androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public long mo240toSpkPz2Gy4(int i) {
        return this.$$delegate_0.mo240toSpkPz2Gy4(i);
    }
}
