package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Density;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LayoutWeightImpl extends InspectorValueInfo implements ParentDataModifier {
    private final boolean fill;
    private final float weight;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LayoutWeightImpl(boolean z, Function1 inspectorInfo) {
        super(inspectorInfo);
        Intrinsics.checkNotNullParameter(inspectorInfo, "inspectorInfo");
        this.weight = 1.0f;
        this.fill = z;
    }

    public final boolean equals(Object obj) {
        LayoutWeightImpl layoutWeightImpl;
        boolean z;
        if (this == obj) {
            return true;
        }
        if (obj instanceof LayoutWeightImpl) {
            layoutWeightImpl = (LayoutWeightImpl) obj;
        } else {
            layoutWeightImpl = null;
        }
        if (layoutWeightImpl == null) {
            return false;
        }
        if (this.weight == layoutWeightImpl.weight) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.fill == layoutWeightImpl.fill) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.fill) + (Float.hashCode(this.weight) * 31);
    }

    @Override // androidx.compose.ui.layout.ParentDataModifier
    public final Object modifyParentData(Density density, Object obj) {
        RowColumnParentData rowColumnParentData;
        Intrinsics.checkNotNullParameter(density, "<this>");
        if (obj instanceof RowColumnParentData) {
            rowColumnParentData = (RowColumnParentData) obj;
        } else {
            rowColumnParentData = null;
        }
        if (rowColumnParentData == null) {
            rowColumnParentData = new RowColumnParentData();
        }
        rowColumnParentData.setWeight(this.weight);
        rowColumnParentData.setFill(this.fill);
        return rowColumnParentData;
    }

    public final String toString() {
        return "LayoutWeightImpl(weight=" + this.weight + ", fill=" + this.fill + ')';
    }
}
