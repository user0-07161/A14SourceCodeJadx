package androidx.compose.ui.geometry;

import androidx.compose.animation.core.AnimationVector4D$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Rect {
    public static final Companion Companion = new Companion();
    private static final Rect Zero = new Rect(0.0f, 0.0f, 0.0f, 0.0f);
    private final float bottom;
    private final float left;
    private final float right;
    private final float top;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    public Rect(float f, float f2, float f3, float f4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
    }

    public static final /* synthetic */ Rect access$getZero$cp() {
        return Zero;
    }

    /* renamed from: contains-k-4lQ0M  reason: not valid java name */
    public final boolean m52containsk4lQ0M(long j) {
        if (Offset.m45getXimpl(j) >= this.left && Offset.m45getXimpl(j) < this.right && Offset.m46getYimpl(j) >= this.top && Offset.m46getYimpl(j) < this.bottom) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Rect)) {
            return false;
        }
        Rect rect = (Rect) obj;
        if (Float.compare(this.left, rect.left) == 0 && Float.compare(this.top, rect.top) == 0 && Float.compare(this.right, rect.right) == 0 && Float.compare(this.bottom, rect.bottom) == 0) {
            return true;
        }
        return false;
    }

    public final float getBottom() {
        return this.bottom;
    }

    /* renamed from: getCenter-F1C5BW0  reason: not valid java name */
    public final long m53getCenterF1C5BW0() {
        float f = this.right;
        float f2 = this.left;
        float f3 = ((f - f2) / 2.0f) + f2;
        float f4 = this.bottom;
        float f5 = this.top;
        return OffsetKt.Offset(f3, ((f4 - f5) / 2.0f) + f5);
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    public final float getLeft() {
        return this.left;
    }

    public final float getRight() {
        return this.right;
    }

    /* renamed from: getSize-NH-jbRc  reason: not valid java name */
    public final void m54getSizeNHjbRc() {
        SizeKt.Size(this.right - this.left, this.bottom - this.top);
    }

    public final float getTop() {
        return this.top;
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public final int hashCode() {
        return Float.hashCode(this.bottom) + AnimationVector4D$$ExternalSyntheticOutline0.m(this.right, AnimationVector4D$$ExternalSyntheticOutline0.m(this.top, Float.hashCode(this.left) * 31, 31), 31);
    }

    public final Rect intersect(Rect rect) {
        return new Rect(Math.max(this.left, rect.left), Math.max(this.top, rect.top), Math.min(this.right, rect.right), Math.min(this.bottom, rect.bottom));
    }

    public final boolean overlaps(Rect other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (this.right <= other.left || other.right <= this.left || this.bottom <= other.top || other.bottom <= this.top) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return "Rect.fromLTRB(" + GeometryUtilsKt.toStringAsFixed(this.left) + ", " + GeometryUtilsKt.toStringAsFixed(this.top) + ", " + GeometryUtilsKt.toStringAsFixed(this.right) + ", " + GeometryUtilsKt.toStringAsFixed(this.bottom) + ')';
    }

    public final Rect translate(float f, float f2) {
        return new Rect(this.left + f, this.top + f2, this.right + f, this.bottom + f2);
    }

    /* renamed from: translate-k-4lQ0M  reason: not valid java name */
    public final Rect m55translatek4lQ0M(long j) {
        return new Rect(Offset.m45getXimpl(j) + this.left, Offset.m46getYimpl(j) + this.top, Offset.m45getXimpl(j) + this.right, Offset.m46getYimpl(j) + this.bottom);
    }
}
