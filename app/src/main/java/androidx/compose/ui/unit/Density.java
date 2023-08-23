package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface Density {
    float getDensity();

    float getFontScale();

    /* renamed from: roundToPx--R2X_6o */
    default int mo235roundToPxR2X_6o(long j) {
        return MathKt.roundToInt(mo205toPxR2X_6o(j));
    }

    /* renamed from: roundToPx-0680j_4 */
    default int mo202roundToPx0680j_4(float f) {
        float mo206toPx0680j_4 = mo206toPx0680j_4(f);
        if (Float.isInfinite(mo206toPx0680j_4)) {
            return Integer.MAX_VALUE;
        }
        return MathKt.roundToInt(mo206toPx0680j_4);
    }

    /* renamed from: toDp-GaN1DYA */
    default float mo236toDpGaN1DYA(long j) {
        if (TextUnitType.m417equalsimpl0(TextUnit.m411getTypeUIouoOA(j), 4294967296L)) {
            return getFontScale() * TextUnit.m412getValueimpl(j);
        }
        throw new IllegalStateException("Only Sp can convert to Px".toString());
    }

    /* renamed from: toDp-u2uoSUM */
    default float mo204toDpu2uoSUM(int i) {
        return i / getDensity();
    }

    /* renamed from: toDpSize-k-rfVVM */
    default long mo237toDpSizekrfVVM(long j) {
        long j2;
        boolean z;
        long j3;
        j2 = Size.Unspecified;
        if (j != j2) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            j3 = DpSize.Unspecified;
            return j3;
        }
        return DpKt.m394DpSizeYgX7TsA(mo203toDpu2uoSUM(Size.m64getWidthimpl(j)), mo203toDpu2uoSUM(Size.m63getHeightimpl(j)));
    }

    /* renamed from: toPx--R2X_6o */
    default float mo205toPxR2X_6o(long j) {
        if (TextUnitType.m417equalsimpl0(TextUnit.m411getTypeUIouoOA(j), 4294967296L)) {
            float m412getValueimpl = TextUnit.m412getValueimpl(j);
            return getDensity() * getFontScale() * m412getValueimpl;
        }
        throw new IllegalStateException("Only Sp can convert to Px".toString());
    }

    /* renamed from: toPx-0680j_4 */
    default float mo206toPx0680j_4(float f) {
        return getDensity() * f;
    }

    /* renamed from: toSize-XkaWNTQ */
    default long mo207toSizeXkaWNTQ(long j) {
        long j2;
        boolean z;
        long j3;
        j2 = DpSize.Unspecified;
        if (j != j2) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            j3 = Size.Unspecified;
            return j3;
        }
        return SizeKt.Size(mo206toPx0680j_4(DpSize.m400getWidthD9Ej5fM(j)), mo206toPx0680j_4(DpSize.m399getHeightD9Ej5fM(j)));
    }

    /* renamed from: toSp-0xMU5do */
    default long mo238toSp0xMU5do(float f) {
        return TextUnitKt.pack(4294967296L, f / getFontScale());
    }

    /* renamed from: toSp-kPz2Gy4 */
    default long mo240toSpkPz2Gy4(int i) {
        return TextUnitKt.pack(4294967296L, i / (getDensity() * getFontScale()));
    }

    /* renamed from: toDp-u2uoSUM */
    default float mo203toDpu2uoSUM(float f) {
        return f / getDensity();
    }

    /* renamed from: toSp-kPz2Gy4 */
    default long mo239toSpkPz2Gy4(float f) {
        return TextUnitKt.pack(4294967296L, f / (getDensity() * getFontScale()));
    }
}
