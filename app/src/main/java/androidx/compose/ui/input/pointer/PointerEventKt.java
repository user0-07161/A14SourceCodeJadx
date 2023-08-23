package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.IntSize;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class PointerEventKt {
    public static final boolean changedToDownIgnoreConsumed(PointerInputChange pointerInputChange) {
        Intrinsics.checkNotNullParameter(pointerInputChange, "<this>");
        if (!pointerInputChange.getPreviousPressed() && pointerInputChange.getPressed()) {
            return true;
        }
        return false;
    }

    public static final boolean changedToUpIgnoreConsumed(PointerInputChange pointerInputChange) {
        Intrinsics.checkNotNullParameter(pointerInputChange, "<this>");
        if (pointerInputChange.getPreviousPressed() && !pointerInputChange.getPressed()) {
            return true;
        }
        return false;
    }

    /* renamed from: isOutOfBounds-O0kMr_c  reason: not valid java name */
    public static final boolean m183isOutOfBoundsO0kMr_c(PointerInputChange isOutOfBounds, long j) {
        Intrinsics.checkNotNullParameter(isOutOfBounds, "$this$isOutOfBounds");
        long m190getPositionF1C5BW0 = isOutOfBounds.m190getPositionF1C5BW0();
        float m45getXimpl = Offset.m45getXimpl(m190getPositionF1C5BW0);
        float m46getYimpl = Offset.m46getYimpl(m190getPositionF1C5BW0);
        int i = (int) (j >> 32);
        int m405getHeightimpl = IntSize.m405getHeightimpl(j);
        if (m45getXimpl >= 0.0f && m45getXimpl <= i && m46getYimpl >= 0.0f && m46getYimpl <= m405getHeightimpl) {
            return false;
        }
        return true;
    }

    private static final long positionChangeInternal(PointerInputChange pointerInputChange, boolean z) {
        long j;
        long m47minusMKHz9U = Offset.m47minusMKHz9U(pointerInputChange.m190getPositionF1C5BW0(), pointerInputChange.m191getPreviousPositionF1C5BW0());
        if (!z && pointerInputChange.isConsumed()) {
            Offset.Companion companion = Offset.Companion;
            j = Offset.Zero;
            return j;
        }
        return m47minusMKHz9U;
    }

    public static final boolean positionChanged(PointerInputChange pointerInputChange) {
        long j;
        Intrinsics.checkNotNullParameter(pointerInputChange, "<this>");
        long positionChangeInternal = positionChangeInternal(pointerInputChange, false);
        Offset.Companion companion = Offset.Companion;
        j = Offset.Zero;
        return !Offset.m43equalsimpl0(positionChangeInternal, j);
    }

    public static final boolean positionChangedIgnoreConsumed(PointerInputChange pointerInputChange) {
        long j;
        Intrinsics.checkNotNullParameter(pointerInputChange, "<this>");
        long positionChangeInternal = positionChangeInternal(pointerInputChange, true);
        Offset.Companion companion = Offset.Companion;
        j = Offset.Zero;
        return !Offset.m43equalsimpl0(positionChangeInternal, j);
    }
}
