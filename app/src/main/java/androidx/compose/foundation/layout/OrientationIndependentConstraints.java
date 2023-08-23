package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class OrientationIndependentConstraints {
    private final int crossAxisMax;
    private final int crossAxisMin;
    private final int mainAxisMax;
    private final int mainAxisMin;

    public OrientationIndependentConstraints(int i, int i2, int i3, int i4) {
        this.mainAxisMin = i;
        this.mainAxisMax = i2;
        this.crossAxisMin = i3;
        this.crossAxisMax = i4;
    }

    public static OrientationIndependentConstraints copy$default(OrientationIndependentConstraints orientationIndependentConstraints, int i) {
        return new OrientationIndependentConstraints(0, i, 0, orientationIndependentConstraints.crossAxisMax);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OrientationIndependentConstraints)) {
            return false;
        }
        OrientationIndependentConstraints orientationIndependentConstraints = (OrientationIndependentConstraints) obj;
        if (this.mainAxisMin == orientationIndependentConstraints.mainAxisMin && this.mainAxisMax == orientationIndependentConstraints.mainAxisMax && this.crossAxisMin == orientationIndependentConstraints.crossAxisMin && this.crossAxisMax == orientationIndependentConstraints.crossAxisMax) {
            return true;
        }
        return false;
    }

    public final int getCrossAxisMax() {
        return this.crossAxisMax;
    }

    public final int getCrossAxisMin() {
        return this.crossAxisMin;
    }

    public final int getMainAxisMax() {
        return this.mainAxisMax;
    }

    public final int getMainAxisMin() {
        return this.mainAxisMin;
    }

    public final int hashCode() {
        int hashCode = Integer.hashCode(this.mainAxisMax);
        int hashCode2 = Integer.hashCode(this.crossAxisMin);
        return Integer.hashCode(this.crossAxisMax) + ((hashCode2 + ((hashCode + (Integer.hashCode(this.mainAxisMin) * 31)) * 31)) * 31);
    }

    /* renamed from: toBoxConstraints-OenEA2s  reason: not valid java name */
    public final long m9toBoxConstraintsOenEA2s(LayoutOrientation orientation) {
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        LayoutOrientation layoutOrientation = LayoutOrientation.Horizontal;
        int i = this.mainAxisMax;
        int i2 = this.mainAxisMin;
        int i3 = this.crossAxisMax;
        int i4 = this.crossAxisMin;
        if (orientation == layoutOrientation) {
            return ConstraintsKt.Constraints(i2, i, i4, i3);
        }
        return ConstraintsKt.Constraints(i4, i3, i2, i);
    }

    public final String toString() {
        return "OrientationIndependentConstraints(mainAxisMin=" + this.mainAxisMin + ", mainAxisMax=" + this.mainAxisMax + ", crossAxisMin=" + this.crossAxisMin + ", crossAxisMax=" + this.crossAxisMax + ')';
    }
}
