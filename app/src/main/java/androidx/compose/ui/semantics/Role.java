package androidx.compose.ui.semantics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Role {
    private final int value;

    private /* synthetic */ Role(int i) {
        this.value = i;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Role m290boximpl(int i) {
        return new Role(i);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Role)) {
            return false;
        }
        if (this.value != ((Role) obj).value) {
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
        boolean z6;
        int i = this.value;
        boolean z7 = false;
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return "Button";
        }
        if (i == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            return "Checkbox";
        }
        if (i == 2) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            return "Switch";
        }
        if (i == 3) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4) {
            return "RadioButton";
        }
        if (i == 4) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5) {
            return "Tab";
        }
        if (i == 5) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (z6) {
            return "Image";
        }
        if (i == 6) {
            z7 = true;
        }
        if (z7) {
            return "DropdownList";
        }
        return "Unknown";
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m291unboximpl() {
        return this.value;
    }
}
