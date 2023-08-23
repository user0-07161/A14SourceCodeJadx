package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Density;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Arrangement {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Arrangement$Top$1 Top = new Arrangement$Top$1();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface Vertical {
        void arrange(Density density, int i, int[] iArr, int[] iArr2);
    }

    static {
        new Vertical(0) { // from class: androidx.compose.foundation.layout.Arrangement$Center$1
            public final /* synthetic */ int $r8$classId;
            private final float spacing;

            {
                this.$r8$classId = r3;
                if (r3 != 1) {
                    if (r3 != 2) {
                        if (r3 != 3) {
                            this.spacing = 0;
                            return;
                        } else {
                            this.spacing = 0;
                            return;
                        }
                    }
                    this.spacing = 0;
                    return;
                }
                this.spacing = 0;
            }

            @Override // androidx.compose.foundation.layout.Arrangement.Vertical
            public final void arrange(Density density, int i, int[] sizes, int[] outPositions) {
                boolean z;
                float f;
                float f2 = 0.0f;
                int i2 = 0;
                switch (this.$r8$classId) {
                    case 0:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i3 = Arrangement.$r8$clinit;
                        int i4 = 0;
                        for (int i5 : sizes) {
                            i4 += i5;
                        }
                        float f3 = (i - i4) / 2;
                        int length = sizes.length;
                        int i6 = 0;
                        while (i2 < length) {
                            int i7 = sizes[i2];
                            outPositions[i6] = MathKt.roundToInt(f3);
                            f3 += i7;
                            i2++;
                            i6++;
                        }
                        return;
                    case 1:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i8 = Arrangement.$r8$clinit;
                        int i9 = 0;
                        for (int i10 : sizes) {
                            i9 += i10;
                        }
                        if (sizes.length == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z) {
                            f2 = (i - i9) / sizes.length;
                        }
                        float f4 = f2 / 2;
                        int length2 = sizes.length;
                        int i11 = 0;
                        while (i2 < length2) {
                            int i12 = sizes[i2];
                            outPositions[i11] = MathKt.roundToInt(f4);
                            f4 += i12 + f2;
                            i2++;
                            i11++;
                        }
                        return;
                    case 2:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i13 = Arrangement.$r8$clinit;
                        int i14 = 0;
                        for (int i15 : sizes) {
                            i14 += i15;
                        }
                        if (sizes.length > 1) {
                            f = (i - i14) / (sizes.length - 1);
                        } else {
                            f = 0.0f;
                        }
                        int length3 = sizes.length;
                        int i16 = 0;
                        while (i2 < length3) {
                            int i17 = sizes[i2];
                            outPositions[i16] = MathKt.roundToInt(f2);
                            f2 += i17 + f;
                            i2++;
                            i16++;
                        }
                        return;
                    default:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i18 = Arrangement.$r8$clinit;
                        int i19 = 0;
                        for (int i20 : sizes) {
                            i19 += i20;
                        }
                        float length4 = (i - i19) / (sizes.length + 1);
                        int length5 = sizes.length;
                        float f5 = length4;
                        int i21 = 0;
                        while (i2 < length5) {
                            int i22 = sizes[i2];
                            outPositions[i21] = MathKt.roundToInt(f5);
                            f5 += i22 + length4;
                            i2++;
                            i21++;
                        }
                        return;
                }
            }

            public final String toString() {
                switch (this.$r8$classId) {
                    case 0:
                        return "Arrangement#Center";
                    case 1:
                        return "Arrangement#SpaceAround";
                    case 2:
                        return "Arrangement#SpaceBetween";
                    default:
                        return "Arrangement#SpaceEvenly";
                }
            }
        };
        new Vertical(3) { // from class: androidx.compose.foundation.layout.Arrangement$Center$1
            public final /* synthetic */ int $r8$classId;
            private final float spacing;

            {
                this.$r8$classId = r3;
                if (r3 != 1) {
                    if (r3 != 2) {
                        if (r3 != 3) {
                            this.spacing = 0;
                            return;
                        } else {
                            this.spacing = 0;
                            return;
                        }
                    }
                    this.spacing = 0;
                    return;
                }
                this.spacing = 0;
            }

            @Override // androidx.compose.foundation.layout.Arrangement.Vertical
            public final void arrange(Density density, int i, int[] sizes, int[] outPositions) {
                boolean z;
                float f;
                float f2 = 0.0f;
                int i2 = 0;
                switch (this.$r8$classId) {
                    case 0:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i3 = Arrangement.$r8$clinit;
                        int i4 = 0;
                        for (int i5 : sizes) {
                            i4 += i5;
                        }
                        float f3 = (i - i4) / 2;
                        int length = sizes.length;
                        int i6 = 0;
                        while (i2 < length) {
                            int i7 = sizes[i2];
                            outPositions[i6] = MathKt.roundToInt(f3);
                            f3 += i7;
                            i2++;
                            i6++;
                        }
                        return;
                    case 1:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i8 = Arrangement.$r8$clinit;
                        int i9 = 0;
                        for (int i10 : sizes) {
                            i9 += i10;
                        }
                        if (sizes.length == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z) {
                            f2 = (i - i9) / sizes.length;
                        }
                        float f4 = f2 / 2;
                        int length2 = sizes.length;
                        int i11 = 0;
                        while (i2 < length2) {
                            int i12 = sizes[i2];
                            outPositions[i11] = MathKt.roundToInt(f4);
                            f4 += i12 + f2;
                            i2++;
                            i11++;
                        }
                        return;
                    case 2:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i13 = Arrangement.$r8$clinit;
                        int i14 = 0;
                        for (int i15 : sizes) {
                            i14 += i15;
                        }
                        if (sizes.length > 1) {
                            f = (i - i14) / (sizes.length - 1);
                        } else {
                            f = 0.0f;
                        }
                        int length3 = sizes.length;
                        int i16 = 0;
                        while (i2 < length3) {
                            int i17 = sizes[i2];
                            outPositions[i16] = MathKt.roundToInt(f2);
                            f2 += i17 + f;
                            i2++;
                            i16++;
                        }
                        return;
                    default:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i18 = Arrangement.$r8$clinit;
                        int i19 = 0;
                        for (int i20 : sizes) {
                            i19 += i20;
                        }
                        float length4 = (i - i19) / (sizes.length + 1);
                        int length5 = sizes.length;
                        float f5 = length4;
                        int i21 = 0;
                        while (i2 < length5) {
                            int i22 = sizes[i2];
                            outPositions[i21] = MathKt.roundToInt(f5);
                            f5 += i22 + length4;
                            i2++;
                            i21++;
                        }
                        return;
                }
            }

            public final String toString() {
                switch (this.$r8$classId) {
                    case 0:
                        return "Arrangement#Center";
                    case 1:
                        return "Arrangement#SpaceAround";
                    case 2:
                        return "Arrangement#SpaceBetween";
                    default:
                        return "Arrangement#SpaceEvenly";
                }
            }
        };
        new Vertical(2) { // from class: androidx.compose.foundation.layout.Arrangement$Center$1
            public final /* synthetic */ int $r8$classId;
            private final float spacing;

            {
                this.$r8$classId = r3;
                if (r3 != 1) {
                    if (r3 != 2) {
                        if (r3 != 3) {
                            this.spacing = 0;
                            return;
                        } else {
                            this.spacing = 0;
                            return;
                        }
                    }
                    this.spacing = 0;
                    return;
                }
                this.spacing = 0;
            }

            @Override // androidx.compose.foundation.layout.Arrangement.Vertical
            public final void arrange(Density density, int i, int[] sizes, int[] outPositions) {
                boolean z;
                float f;
                float f2 = 0.0f;
                int i2 = 0;
                switch (this.$r8$classId) {
                    case 0:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i3 = Arrangement.$r8$clinit;
                        int i4 = 0;
                        for (int i5 : sizes) {
                            i4 += i5;
                        }
                        float f3 = (i - i4) / 2;
                        int length = sizes.length;
                        int i6 = 0;
                        while (i2 < length) {
                            int i7 = sizes[i2];
                            outPositions[i6] = MathKt.roundToInt(f3);
                            f3 += i7;
                            i2++;
                            i6++;
                        }
                        return;
                    case 1:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i8 = Arrangement.$r8$clinit;
                        int i9 = 0;
                        for (int i10 : sizes) {
                            i9 += i10;
                        }
                        if (sizes.length == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z) {
                            f2 = (i - i9) / sizes.length;
                        }
                        float f4 = f2 / 2;
                        int length2 = sizes.length;
                        int i11 = 0;
                        while (i2 < length2) {
                            int i12 = sizes[i2];
                            outPositions[i11] = MathKt.roundToInt(f4);
                            f4 += i12 + f2;
                            i2++;
                            i11++;
                        }
                        return;
                    case 2:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i13 = Arrangement.$r8$clinit;
                        int i14 = 0;
                        for (int i15 : sizes) {
                            i14 += i15;
                        }
                        if (sizes.length > 1) {
                            f = (i - i14) / (sizes.length - 1);
                        } else {
                            f = 0.0f;
                        }
                        int length3 = sizes.length;
                        int i16 = 0;
                        while (i2 < length3) {
                            int i17 = sizes[i2];
                            outPositions[i16] = MathKt.roundToInt(f2);
                            f2 += i17 + f;
                            i2++;
                            i16++;
                        }
                        return;
                    default:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i18 = Arrangement.$r8$clinit;
                        int i19 = 0;
                        for (int i20 : sizes) {
                            i19 += i20;
                        }
                        float length4 = (i - i19) / (sizes.length + 1);
                        int length5 = sizes.length;
                        float f5 = length4;
                        int i21 = 0;
                        while (i2 < length5) {
                            int i22 = sizes[i2];
                            outPositions[i21] = MathKt.roundToInt(f5);
                            f5 += i22 + length4;
                            i2++;
                            i21++;
                        }
                        return;
                }
            }

            public final String toString() {
                switch (this.$r8$classId) {
                    case 0:
                        return "Arrangement#Center";
                    case 1:
                        return "Arrangement#SpaceAround";
                    case 2:
                        return "Arrangement#SpaceBetween";
                    default:
                        return "Arrangement#SpaceEvenly";
                }
            }
        };
        new Vertical(1) { // from class: androidx.compose.foundation.layout.Arrangement$Center$1
            public final /* synthetic */ int $r8$classId;
            private final float spacing;

            {
                this.$r8$classId = r3;
                if (r3 != 1) {
                    if (r3 != 2) {
                        if (r3 != 3) {
                            this.spacing = 0;
                            return;
                        } else {
                            this.spacing = 0;
                            return;
                        }
                    }
                    this.spacing = 0;
                    return;
                }
                this.spacing = 0;
            }

            @Override // androidx.compose.foundation.layout.Arrangement.Vertical
            public final void arrange(Density density, int i, int[] sizes, int[] outPositions) {
                boolean z;
                float f;
                float f2 = 0.0f;
                int i2 = 0;
                switch (this.$r8$classId) {
                    case 0:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i3 = Arrangement.$r8$clinit;
                        int i4 = 0;
                        for (int i5 : sizes) {
                            i4 += i5;
                        }
                        float f3 = (i - i4) / 2;
                        int length = sizes.length;
                        int i6 = 0;
                        while (i2 < length) {
                            int i7 = sizes[i2];
                            outPositions[i6] = MathKt.roundToInt(f3);
                            f3 += i7;
                            i2++;
                            i6++;
                        }
                        return;
                    case 1:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i8 = Arrangement.$r8$clinit;
                        int i9 = 0;
                        for (int i10 : sizes) {
                            i9 += i10;
                        }
                        if (sizes.length == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z) {
                            f2 = (i - i9) / sizes.length;
                        }
                        float f4 = f2 / 2;
                        int length2 = sizes.length;
                        int i11 = 0;
                        while (i2 < length2) {
                            int i12 = sizes[i2];
                            outPositions[i11] = MathKt.roundToInt(f4);
                            f4 += i12 + f2;
                            i2++;
                            i11++;
                        }
                        return;
                    case 2:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i13 = Arrangement.$r8$clinit;
                        int i14 = 0;
                        for (int i15 : sizes) {
                            i14 += i15;
                        }
                        if (sizes.length > 1) {
                            f = (i - i14) / (sizes.length - 1);
                        } else {
                            f = 0.0f;
                        }
                        int length3 = sizes.length;
                        int i16 = 0;
                        while (i2 < length3) {
                            int i17 = sizes[i2];
                            outPositions[i16] = MathKt.roundToInt(f2);
                            f2 += i17 + f;
                            i2++;
                            i16++;
                        }
                        return;
                    default:
                        Intrinsics.checkNotNullParameter(density, "<this>");
                        Intrinsics.checkNotNullParameter(sizes, "sizes");
                        Intrinsics.checkNotNullParameter(outPositions, "outPositions");
                        int i18 = Arrangement.$r8$clinit;
                        int i19 = 0;
                        for (int i20 : sizes) {
                            i19 += i20;
                        }
                        float length4 = (i - i19) / (sizes.length + 1);
                        int length5 = sizes.length;
                        float f5 = length4;
                        int i21 = 0;
                        while (i2 < length5) {
                            int i22 = sizes[i2];
                            outPositions[i21] = MathKt.roundToInt(f5);
                            f5 += i22 + length4;
                            i2++;
                            i21++;
                        }
                        return;
                }
            }

            public final String toString() {
                switch (this.$r8$classId) {
                    case 0:
                        return "Arrangement#Center";
                    case 1:
                        return "Arrangement#SpaceAround";
                    case 2:
                        return "Arrangement#SpaceBetween";
                    default:
                        return "Arrangement#SpaceEvenly";
                }
            }
        };
    }

    public static Arrangement$Top$1 getTop() {
        return Top;
    }
}
