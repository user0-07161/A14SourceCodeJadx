package androidx.compose.ui.text.style;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LineHeightStyle {
    private static final LineHeightStyle Default = new LineHeightStyle(Alignment.Proportional);
    private final float alignment;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class Alignment {
        public static final /* synthetic */ int $r8$clinit = 0;
        private static final float Bottom;
        private static final float Center;
        private static final float Proportional;

        static {
            m366constructorimpl(0.0f);
            m366constructorimpl(0.5f);
            Center = 0.5f;
            m366constructorimpl(-1.0f);
            Proportional = -1.0f;
            m366constructorimpl(1.0f);
            Bottom = 1.0f;
        }

        /* renamed from: constructor-impl  reason: not valid java name */
        public static void m366constructorimpl(float f) {
            boolean z;
            boolean z2;
            boolean z3 = true;
            if (0.0f <= f && f <= 1.0f) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                if (f == -1.0f) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (!z2) {
                    z3 = false;
                }
            }
            if (z3) {
                return;
            }
            throw new IllegalStateException("topRatio should be in [0..1] range or -1".toString());
        }

        /* renamed from: toString-impl  reason: not valid java name */
        public static String m367toStringimpl(float f) {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4 = true;
            if (f == 0.0f) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return "LineHeightStyle.Alignment.Top";
            }
            if (f == Center) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                return "LineHeightStyle.Alignment.Center";
            }
            if (f == Proportional) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                return "LineHeightStyle.Alignment.Proportional";
            }
            if (f != Bottom) {
                z4 = false;
            }
            if (z4) {
                return "LineHeightStyle.Alignment.Bottom";
            }
            return "LineHeightStyle.Alignment(topPercentage = " + f + ')';
        }
    }

    public LineHeightStyle(float f) {
        this.alignment = f;
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LineHeightStyle)) {
            return false;
        }
        float f = ((LineHeightStyle) obj).alignment;
        int i = Alignment.$r8$clinit;
        if (Float.compare(this.alignment, f) == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        return false;
    }

    /* renamed from: getAlignment-PIaL0Z0  reason: not valid java name */
    public final float m365getAlignmentPIaL0Z0() {
        return this.alignment;
    }

    public final int hashCode() {
        int i = Alignment.$r8$clinit;
        return Integer.hashCode(17) + (Float.hashCode(this.alignment) * 31);
    }

    public final String toString() {
        return "LineHeightStyle(alignment=" + ((Object) Alignment.m367toStringimpl(this.alignment)) + ", trim=" + ((Object) "LineHeightStyle.Trim.Both") + ')';
    }
}
