package androidx.compose.ui.graphics;

import android.graphics.Path;
import android.graphics.RectF;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.RoundRect;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidPath implements Path {
    private final android.graphics.Path internalPath;
    private final android.graphics.Matrix mMatrix;
    private final float[] radii;
    private final RectF rectF;

    public AndroidPath(android.graphics.Path internalPath) {
        Intrinsics.checkNotNullParameter(internalPath, "internalPath");
        this.internalPath = internalPath;
        this.rectF = new RectF();
        this.radii = new float[8];
        this.mMatrix = new android.graphics.Matrix();
    }

    public final void addRoundRect(RoundRect roundRect) {
        RectF rectF = this.rectF;
        rectF.set(roundRect.getLeft(), roundRect.getTop(), roundRect.getRight(), roundRect.getBottom());
        float m39getXimpl = CornerRadius.m39getXimpl(roundRect.m59getTopLeftCornerRadiuskKHJgLs());
        float[] fArr = this.radii;
        fArr[0] = m39getXimpl;
        fArr[1] = CornerRadius.m40getYimpl(roundRect.m59getTopLeftCornerRadiuskKHJgLs());
        fArr[2] = CornerRadius.m39getXimpl(roundRect.m60getTopRightCornerRadiuskKHJgLs());
        fArr[3] = CornerRadius.m40getYimpl(roundRect.m60getTopRightCornerRadiuskKHJgLs());
        fArr[4] = CornerRadius.m39getXimpl(roundRect.m58getBottomRightCornerRadiuskKHJgLs());
        fArr[5] = CornerRadius.m40getYimpl(roundRect.m58getBottomRightCornerRadiuskKHJgLs());
        fArr[6] = CornerRadius.m39getXimpl(roundRect.m57getBottomLeftCornerRadiuskKHJgLs());
        fArr[7] = CornerRadius.m40getYimpl(roundRect.m57getBottomLeftCornerRadiuskKHJgLs());
        this.internalPath.addRoundRect(rectF, fArr, Path.Direction.CCW);
    }

    public final void close() {
        this.internalPath.close();
    }

    public final void cubicTo(float f, float f2, float f3, float f4, float f5, float f6) {
        this.internalPath.cubicTo(f, f2, f3, f4, f5, f6);
    }

    public final android.graphics.Path getInternalPath() {
        return this.internalPath;
    }

    public final void lineTo(float f, float f2) {
        this.internalPath.lineTo(f, f2);
    }

    public final void moveTo(float f, float f2) {
        this.internalPath.moveTo(f, f2);
    }

    public final void reset() {
        this.internalPath.reset();
    }

    /* renamed from: translate-k-4lQ0M  reason: not valid java name */
    public final void m87translatek4lQ0M(long j) {
        android.graphics.Matrix matrix = this.mMatrix;
        matrix.reset();
        matrix.setTranslate(Offset.m45getXimpl(j), Offset.m46getYimpl(j));
        this.internalPath.transform(matrix);
    }
}
