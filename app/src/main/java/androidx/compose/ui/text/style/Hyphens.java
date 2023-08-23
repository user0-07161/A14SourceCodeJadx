package androidx.compose.ui.text.style;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Hyphens {
    private final int value;

    private /* synthetic */ Hyphens(int i) {
        this.value = i;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Hyphens m353boximpl(int i) {
        return new Hyphens(i);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Hyphens)) {
            return false;
        }
        if (this.value != ((Hyphens) obj).value) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        boolean z;
        int i = this.value;
        boolean z2 = false;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return "Hyphens.None";
        }
        if (i == 2) {
            z2 = true;
        }
        if (z2) {
            return "Hyphens.Auto";
        }
        return "Invalid";
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m354unboximpl() {
        return this.value;
    }
}
