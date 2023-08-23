package androidx.compose.ui.text.font;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FontStyle {
    private final int value;

    private /* synthetic */ FontStyle(int i) {
        this.value = i;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ FontStyle m329boximpl(int i) {
        return new FontStyle(i);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof FontStyle)) {
            return false;
        }
        if (this.value != ((FontStyle) obj).value) {
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
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return "Normal";
        }
        if (i == 1) {
            z2 = true;
        }
        if (z2) {
            return "Italic";
        }
        return "Invalid";
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m330unboximpl() {
        return this.value;
    }
}
