package androidx.compose.ui.text.style;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextDirection {
    private final int value;

    private /* synthetic */ TextDirection(int i) {
        this.value = i;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ TextDirection m370boximpl(int i) {
        return new TextDirection(i);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TextDirection)) {
            return false;
        }
        if (this.value != ((TextDirection) obj).value) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i = this.value;
        boolean z5 = false;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return "Ltr";
        }
        if (i == 2) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            return "Rtl";
        }
        if (i == 3) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            return "Content";
        }
        if (i == 4) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4) {
            return "ContentOrLtr";
        }
        if (i == 5) {
            z5 = true;
        }
        if (z5) {
            return "ContentOrRtl";
        }
        return "Invalid";
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m371unboximpl() {
        return this.value;
    }
}
