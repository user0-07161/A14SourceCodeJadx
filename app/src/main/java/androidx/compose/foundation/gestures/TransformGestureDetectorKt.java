package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputChange;
import java.util.List;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TransformGestureDetectorKt {
    /* renamed from: angle-k-4lQ0M  reason: not valid java name */
    private static final float m8anglek4lQ0M(long j) {
        boolean z;
        boolean z2 = true;
        if (Offset.m45getXimpl(j) == 0.0f) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (Offset.m46getYimpl(j) != 0.0f) {
                z2 = false;
            }
            if (z2) {
                return 0.0f;
            }
        }
        return ((-((float) Math.atan2(Offset.m45getXimpl(j), Offset.m46getYimpl(j)))) * 180.0f) / 3.1415927f;
    }

    public static final long calculateCentroid(PointerEvent pointerEvent, boolean z) {
        long j;
        long j2;
        long m191getPreviousPositionF1C5BW0;
        j = Offset.Zero;
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            PointerInputChange pointerInputChange = (PointerInputChange) changes.get(i2);
            if (pointerInputChange.getPressed() && pointerInputChange.getPreviousPressed()) {
                if (z) {
                    m191getPreviousPositionF1C5BW0 = pointerInputChange.m190getPositionF1C5BW0();
                } else {
                    m191getPreviousPositionF1C5BW0 = pointerInputChange.m191getPreviousPositionF1C5BW0();
                }
                j = Offset.m48plusMKHz9U(j, m191getPreviousPositionF1C5BW0);
                i++;
            }
        }
        if (i == 0) {
            j2 = Offset.Unspecified;
            return j2;
        }
        float f = i;
        return OffsetKt.Offset(Offset.m45getXimpl(j) / f, Offset.m46getYimpl(j) / f);
    }

    public static final float calculateCentroidSize(PointerEvent pointerEvent, boolean z) {
        long j;
        long m191getPreviousPositionF1C5BW0;
        long calculateCentroid = calculateCentroid(pointerEvent, z);
        j = Offset.Unspecified;
        float f = 0.0f;
        if (Offset.m43equalsimpl0(calculateCentroid, j)) {
            return 0.0f;
        }
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            PointerInputChange pointerInputChange = (PointerInputChange) changes.get(i2);
            if (pointerInputChange.getPressed() && pointerInputChange.getPreviousPressed()) {
                if (z) {
                    m191getPreviousPositionF1C5BW0 = pointerInputChange.m190getPositionF1C5BW0();
                } else {
                    m191getPreviousPositionF1C5BW0 = pointerInputChange.m191getPreviousPositionF1C5BW0();
                }
                i++;
                f = Offset.m44getDistanceimpl(Offset.m47minusMKHz9U(m191getPreviousPositionF1C5BW0, calculateCentroid)) + f;
            }
        }
        return f / i;
    }

    public static final float calculateRotation(PointerEvent pointerEvent) {
        boolean z;
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = 1;
            if (i >= size) {
                break;
            }
            PointerInputChange pointerInputChange = (PointerInputChange) changes.get(i);
            if (!pointerInputChange.getPreviousPressed() || !pointerInputChange.getPressed()) {
                i3 = 0;
            }
            i2 += i3;
            i++;
        }
        float f = 0.0f;
        if (i2 < 2) {
            return 0.0f;
        }
        long calculateCentroid = calculateCentroid(pointerEvent, true);
        long calculateCentroid2 = calculateCentroid(pointerEvent, false);
        List changes2 = pointerEvent.getChanges();
        int size2 = changes2.size();
        float f2 = 0.0f;
        float f3 = 0.0f;
        int i4 = 0;
        while (i4 < size2) {
            PointerInputChange pointerInputChange2 = (PointerInputChange) changes2.get(i4);
            if (pointerInputChange2.getPressed() && pointerInputChange2.getPreviousPressed()) {
                long m190getPositionF1C5BW0 = pointerInputChange2.m190getPositionF1C5BW0();
                long m47minusMKHz9U = Offset.m47minusMKHz9U(pointerInputChange2.m191getPreviousPositionF1C5BW0(), calculateCentroid2);
                long m47minusMKHz9U2 = Offset.m47minusMKHz9U(m190getPositionF1C5BW0, calculateCentroid);
                float m8anglek4lQ0M = m8anglek4lQ0M(m47minusMKHz9U2) - m8anglek4lQ0M(m47minusMKHz9U);
                float m44getDistanceimpl = Offset.m44getDistanceimpl(Offset.m48plusMKHz9U(m47minusMKHz9U2, m47minusMKHz9U)) / 2.0f;
                if (m8anglek4lQ0M > 180.0f) {
                    m8anglek4lQ0M -= 360.0f;
                } else if (m8anglek4lQ0M < -180.0f) {
                    m8anglek4lQ0M += 360.0f;
                }
                f3 += m8anglek4lQ0M * m44getDistanceimpl;
                f2 += m44getDistanceimpl;
            }
            i4++;
            f = 0.0f;
        }
        if (f2 == f) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return f3 / f2;
        }
        return f;
    }
}
