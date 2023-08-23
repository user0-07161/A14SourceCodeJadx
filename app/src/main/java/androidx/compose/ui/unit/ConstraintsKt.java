package androidx.compose.ui.unit;

import androidx.compose.ui.unit.Constraints;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ConstraintsKt {
    public static final long Constraints(int i, int i2, int i3, int i4) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        if (i2 >= i) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (i4 >= i3) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if (i < 0 || i3 < 0) {
                    z3 = false;
                }
                if (z3) {
                    return Constraints.Companion.m386createConstraintsZbe2FdA$ui_unit_release(i, i2, i3, i4);
                }
                throw new IllegalArgumentException(("minWidth(" + i + ") and minHeight(" + i3 + ") must be >= 0").toString());
            }
            throw new IllegalArgumentException(("maxHeight(" + i4 + ") must be >= than minHeight(" + i3 + ')').toString());
        }
        throw new IllegalArgumentException(("maxWidth(" + i2 + ") must be >= than minWidth(" + i + ')').toString());
    }

    public static /* synthetic */ long Constraints$default(int i, int i2, int i3) {
        if ((i3 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i3 & 8) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return Constraints(0, i, 0, i2);
    }

    /* renamed from: constrain-4WqzIAM  reason: not valid java name */
    public static final long m387constrain4WqzIAM(long j, long j2) {
        return IntSizeKt.IntSize(RangesKt.coerceIn((int) (j2 >> 32), Constraints.m383getMinWidthimpl(j), Constraints.m381getMaxWidthimpl(j)), RangesKt.coerceIn(IntSize.m405getHeightimpl(j2), Constraints.m382getMinHeightimpl(j), Constraints.m380getMaxHeightimpl(j)));
    }

    /* renamed from: offset-NN6Ew-U  reason: not valid java name */
    public static final long m388offsetNN6EwU(long j, int i, int i2) {
        int m383getMinWidthimpl = Constraints.m383getMinWidthimpl(j) + i;
        int i3 = 0;
        if (m383getMinWidthimpl < 0) {
            m383getMinWidthimpl = 0;
        }
        int m381getMaxWidthimpl = Constraints.m381getMaxWidthimpl(j);
        if (m381getMaxWidthimpl != Integer.MAX_VALUE && (m381getMaxWidthimpl = m381getMaxWidthimpl + i) < 0) {
            m381getMaxWidthimpl = 0;
        }
        int m382getMinHeightimpl = Constraints.m382getMinHeightimpl(j) + i2;
        if (m382getMinHeightimpl < 0) {
            m382getMinHeightimpl = 0;
        }
        int m380getMaxHeightimpl = Constraints.m380getMaxHeightimpl(j);
        if (m380getMaxHeightimpl == Integer.MAX_VALUE || (m380getMaxHeightimpl = m380getMaxHeightimpl + i2) >= 0) {
            i3 = m380getMaxHeightimpl;
        }
        return Constraints(m383getMinWidthimpl, m381getMaxWidthimpl, m382getMinHeightimpl, i3);
    }
}
