package androidx.compose.ui.platform;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class InvertMatrixKt {
    /* renamed from: invertTo-JiSxe2E  reason: not valid java name */
    public static final boolean m283invertToJiSxe2E(float[] invertTo, float[] other) {
        boolean z;
        Intrinsics.checkNotNullParameter(invertTo, "$this$invertTo");
        Intrinsics.checkNotNullParameter(other, "other");
        float f = invertTo[0];
        float f2 = invertTo[1];
        float f3 = invertTo[2];
        float f4 = invertTo[3];
        float f5 = invertTo[4];
        float f6 = invertTo[5];
        float f7 = invertTo[6];
        float f8 = invertTo[7];
        float f9 = invertTo[8];
        float f10 = invertTo[9];
        float f11 = invertTo[10];
        float f12 = invertTo[11];
        float f13 = invertTo[12];
        float f14 = invertTo[13];
        float f15 = invertTo[14];
        float f16 = invertTo[15];
        float f17 = (f * f6) - (f2 * f5);
        float f18 = (f * f7) - (f3 * f5);
        float f19 = (f * f8) - (f4 * f5);
        float f20 = (f2 * f7) - (f3 * f6);
        float f21 = (f2 * f8) - (f4 * f6);
        float f22 = (f3 * f8) - (f4 * f7);
        float f23 = (f9 * f14) - (f10 * f13);
        float f24 = (f9 * f15) - (f11 * f13);
        float f25 = (f9 * f16) - (f12 * f13);
        float f26 = (f10 * f15) - (f11 * f14);
        float f27 = (f10 * f16) - (f12 * f14);
        float f28 = (f11 * f16) - (f12 * f15);
        float f29 = (f22 * f23) + (((f20 * f25) + ((f19 * f26) + ((f17 * f28) - (f18 * f27)))) - (f21 * f24));
        if (f29 == 0.0f) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return false;
        }
        float f30 = 1.0f / f29;
        other[0] = ((f8 * f26) + ((f6 * f28) - (f7 * f27))) * f30;
        other[1] = (((f3 * f27) + ((-f2) * f28)) - (f4 * f26)) * f30;
        other[2] = ((f16 * f20) + ((f14 * f22) - (f15 * f21))) * f30;
        other[3] = (((f11 * f21) + ((-f10) * f22)) - (f12 * f20)) * f30;
        float f31 = -f5;
        other[4] = (((f7 * f25) + (f31 * f28)) - (f8 * f24)) * f30;
        other[5] = ((f4 * f24) + ((f28 * f) - (f3 * f25))) * f30;
        float f32 = -f13;
        other[6] = (((f15 * f19) + (f32 * f22)) - (f16 * f18)) * f30;
        other[7] = ((f12 * f18) + ((f22 * f9) - (f11 * f19))) * f30;
        other[8] = ((f8 * f23) + ((f5 * f27) - (f6 * f25))) * f30;
        other[9] = (((f25 * f2) + ((-f) * f27)) - (f4 * f23)) * f30;
        other[10] = ((f16 * f17) + ((f13 * f21) - (f14 * f19))) * f30;
        other[11] = (((f19 * f10) + ((-f9) * f21)) - (f12 * f17)) * f30;
        other[12] = (((f6 * f24) + (f31 * f26)) - (f7 * f23)) * f30;
        other[13] = ((f3 * f23) + ((f * f26) - (f2 * f24))) * f30;
        other[14] = (((f14 * f18) + (f32 * f20)) - (f15 * f17)) * f30;
        other[15] = ((f11 * f17) + ((f9 * f20) - (f10 * f18))) * f30;
        return true;
    }
}
