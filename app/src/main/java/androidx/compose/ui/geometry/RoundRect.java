package androidx.compose.ui.geometry;

import androidx.compose.animation.core.AnimationVector4D$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RoundRect {
    private final float bottom;
    private final long bottomLeftCornerRadius;
    private final long bottomRightCornerRadius;
    private final float left;
    private final float right;
    private final float top;
    private final long topLeftCornerRadius;
    private final long topRightCornerRadius;

    static {
        long j;
        int i = CornerRadius.$r8$clinit;
        j = CornerRadius.Zero;
        RoundRectKt.m61RoundRectgG7oq9Y(0.0f, 0.0f, 0.0f, 0.0f, j);
    }

    public RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
        this.topLeftCornerRadius = j;
        this.topRightCornerRadius = j2;
        this.bottomRightCornerRadius = j3;
        this.bottomLeftCornerRadius = j4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoundRect)) {
            return false;
        }
        RoundRect roundRect = (RoundRect) obj;
        if (Float.compare(this.left, roundRect.left) == 0 && Float.compare(this.top, roundRect.top) == 0 && Float.compare(this.right, roundRect.right) == 0 && Float.compare(this.bottom, roundRect.bottom) == 0 && CornerRadius.m38equalsimpl0(this.topLeftCornerRadius, roundRect.topLeftCornerRadius) && CornerRadius.m38equalsimpl0(this.topRightCornerRadius, roundRect.topRightCornerRadius) && CornerRadius.m38equalsimpl0(this.bottomRightCornerRadius, roundRect.bottomRightCornerRadius) && CornerRadius.m38equalsimpl0(this.bottomLeftCornerRadius, roundRect.bottomLeftCornerRadius)) {
            return true;
        }
        return false;
    }

    public final float getBottom() {
        return this.bottom;
    }

    /* renamed from: getBottomLeftCornerRadius-kKHJgLs  reason: not valid java name */
    public final long m57getBottomLeftCornerRadiuskKHJgLs() {
        return this.bottomLeftCornerRadius;
    }

    /* renamed from: getBottomRightCornerRadius-kKHJgLs  reason: not valid java name */
    public final long m58getBottomRightCornerRadiuskKHJgLs() {
        return this.bottomRightCornerRadius;
    }

    public final float getLeft() {
        return this.left;
    }

    public final float getRight() {
        return this.right;
    }

    public final float getTop() {
        return this.top;
    }

    /* renamed from: getTopLeftCornerRadius-kKHJgLs  reason: not valid java name */
    public final long m59getTopLeftCornerRadiuskKHJgLs() {
        return this.topLeftCornerRadius;
    }

    /* renamed from: getTopRightCornerRadius-kKHJgLs  reason: not valid java name */
    public final long m60getTopRightCornerRadiuskKHJgLs() {
        return this.topRightCornerRadius;
    }

    public final int hashCode() {
        int m = AnimationVector4D$$ExternalSyntheticOutline0.m(this.bottom, AnimationVector4D$$ExternalSyntheticOutline0.m(this.right, AnimationVector4D$$ExternalSyntheticOutline0.m(this.top, Float.hashCode(this.left) * 31, 31), 31), 31);
        int i = CornerRadius.$r8$clinit;
        int hashCode = Long.hashCode(this.topRightCornerRadius);
        int hashCode2 = Long.hashCode(this.bottomRightCornerRadius);
        return Long.hashCode(this.bottomLeftCornerRadius) + ((hashCode2 + ((hashCode + ((Long.hashCode(this.topLeftCornerRadius) + m) * 31)) * 31)) * 31);
    }

    public final String toString() {
        boolean z;
        String str = GeometryUtilsKt.toStringAsFixed(this.left) + ", " + GeometryUtilsKt.toStringAsFixed(this.top) + ", " + GeometryUtilsKt.toStringAsFixed(this.right) + ", " + GeometryUtilsKt.toStringAsFixed(this.bottom);
        long j = this.topLeftCornerRadius;
        long j2 = this.topRightCornerRadius;
        boolean m38equalsimpl0 = CornerRadius.m38equalsimpl0(j, j2);
        long j3 = this.bottomRightCornerRadius;
        long j4 = this.bottomLeftCornerRadius;
        if (m38equalsimpl0 && CornerRadius.m38equalsimpl0(j2, j3) && CornerRadius.m38equalsimpl0(j3, j4)) {
            if (CornerRadius.m39getXimpl(j) == CornerRadius.m40getYimpl(j)) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return "RoundRect(rect=" + str + ", radius=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m39getXimpl(j)) + ')';
            }
            return "RoundRect(rect=" + str + ", x=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m39getXimpl(j)) + ", y=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m40getYimpl(j)) + ')';
        }
        return "RoundRect(rect=" + str + ", topLeft=" + ((Object) CornerRadius.m41toStringimpl(j)) + ", topRight=" + ((Object) CornerRadius.m41toStringimpl(j2)) + ", bottomRight=" + ((Object) CornerRadius.m41toStringimpl(j3)) + ", bottomLeft=" + ((Object) CornerRadius.m41toStringimpl(j4)) + ')';
    }
}
