package androidx.compose.ui.text.style;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextAlign {
    private final int value;

    private /* synthetic */ TextAlign(int i) {
        this.value = i;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ TextAlign m368boximpl(int i) {
        return new TextAlign(i);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TextAlign)) {
            return false;
        }
        if (this.value != ((TextAlign) obj).value) {
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
        boolean z5;
        int i = this.value;
        boolean z6 = false;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return "Left";
        }
        if (i == 2) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            return "Right";
        }
        if (i == 3) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            return "Center";
        }
        if (i == 4) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4) {
            return "Justify";
        }
        if (i == 5) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5) {
            return "Start";
        }
        if (i == 6) {
            z6 = true;
        }
        if (z6) {
            return "End";
        }
        return "Invalid";
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m369unboximpl() {
        return this.value;
    }
}
