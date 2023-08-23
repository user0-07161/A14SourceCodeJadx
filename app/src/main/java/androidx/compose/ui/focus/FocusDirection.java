package androidx.compose.ui.focus;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FocusDirection {
    private final int value;

    private /* synthetic */ FocusDirection(int i) {
        this.value = i;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ FocusDirection m26boximpl(int i) {
        return new FocusDirection(i);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m27toStringimpl(int i) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8 = false;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return "Next";
        }
        if (i == 2) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            return "Previous";
        }
        if (i == 3) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            return "Left";
        }
        if (i == 4) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4) {
            return "Right";
        }
        if (i == 5) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5) {
            return "Up";
        }
        if (i == 6) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (z6) {
            return "Down";
        }
        if (i == 7) {
            z7 = true;
        } else {
            z7 = false;
        }
        if (z7) {
            return "Enter";
        }
        if (i == 8) {
            z8 = true;
        }
        if (z8) {
            return "Exit";
        }
        return "Invalid FocusDirection";
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FocusDirection)) {
            return false;
        }
        if (this.value != ((FocusDirection) obj).value) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m27toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m28unboximpl() {
        return this.value;
    }
}
