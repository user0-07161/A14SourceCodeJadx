package androidx.compose.ui.unit;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Dp implements Comparable {
    public static final Companion Companion = new Companion();
    private final float value;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    private /* synthetic */ Dp(float f) {
        this.value = f;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Dp m389boximpl(float f) {
        return new Dp(f);
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m390equalsimpl0(float f, float f2) {
        if (Float.compare(f, f2) == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m391toStringimpl(float f) {
        if (Float.isNaN(f)) {
            return "Dp.Unspecified";
        }
        return f + ".dp";
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Float.compare(this.value, ((Dp) obj).value);
    }

    public final boolean equals(Object obj) {
        float f = this.value;
        if (!(obj instanceof Dp) || Float.compare(f, ((Dp) obj).value) != 0) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Float.hashCode(this.value);
    }

    public final String toString() {
        return m391toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ float m392unboximpl() {
        return this.value;
    }
}
