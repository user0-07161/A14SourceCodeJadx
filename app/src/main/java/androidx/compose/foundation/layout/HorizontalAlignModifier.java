package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.CrossAxisAlignment;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAbsoluteAlignment$Horizontal;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Density;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class HorizontalAlignModifier extends InspectorValueInfo implements ParentDataModifier {
    private final Alignment.Horizontal horizontal;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HorizontalAlignModifier(BiasAbsoluteAlignment$Horizontal biasAbsoluteAlignment$Horizontal, Function1 inspectorInfo) {
        super(inspectorInfo);
        Intrinsics.checkNotNullParameter(inspectorInfo, "inspectorInfo");
        this.horizontal = biasAbsoluteAlignment$Horizontal;
    }

    public final boolean equals(Object obj) {
        HorizontalAlignModifier horizontalAlignModifier;
        if (this == obj) {
            return true;
        }
        if (obj instanceof HorizontalAlignModifier) {
            horizontalAlignModifier = (HorizontalAlignModifier) obj;
        } else {
            horizontalAlignModifier = null;
        }
        if (horizontalAlignModifier == null) {
            return false;
        }
        return Intrinsics.areEqual(this.horizontal, horizontalAlignModifier.horizontal);
    }

    public final int hashCode() {
        return this.horizontal.hashCode();
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
        Alignment.Horizontal horizontal = this.horizontal;
        Intrinsics.checkNotNullParameter(horizontal, "horizontal");
        rowColumnParentData.setCrossAxisAlignment(new CrossAxisAlignment.HorizontalCrossAxisAlignment(horizontal));
        return rowColumnParentData;
    }

    public final String toString() {
        return "HorizontalAlignModifier(horizontal=" + this.horizontal + ')';
    }
}
