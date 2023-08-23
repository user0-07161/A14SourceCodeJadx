package androidx.compose.ui.text.style;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class BaselineShift {
    public static final Companion Companion = new Companion();
    private final float multiplier;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    private /* synthetic */ BaselineShift(float f) {
        this.multiplier = f;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ BaselineShift m350boximpl(float f) {
        return new BaselineShift(f);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof BaselineShift)) {
            return false;
        }
        if (Float.compare(this.multiplier, ((BaselineShift) obj).multiplier) != 0) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Float.hashCode(this.multiplier);
    }

    public final String toString() {
        return "BaselineShift(multiplier=" + this.multiplier + ')';
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ float m351unboximpl() {
        return this.multiplier;
    }
}
