package androidx.compose.ui;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class BiasAbsoluteAlignment$Horizontal implements Alignment.Horizontal {
    @Override // androidx.compose.ui.Alignment.Horizontal
    public final int align(int i, LayoutDirection layoutDirection) {
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        return MathKt.roundToInt((1 - 1.0f) * ((i + 0) / 2.0f));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BiasAbsoluteAlignment$Horizontal)) {
            return false;
        }
        ((BiasAbsoluteAlignment$Horizontal) obj).getClass();
        if (Float.compare(-1.0f, -1.0f) == 0) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(-1.0f);
    }

    public final String toString() {
        return "Horizontal(bias=-1.0)";
    }
}
