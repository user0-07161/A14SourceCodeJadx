package androidx.compose.ui.input.pointer;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PointerKeyboardModifiers {
    private final int packedValue;

    private /* synthetic */ PointerKeyboardModifiers(int i) {
        this.packedValue = i;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ PointerKeyboardModifiers m200boximpl(int i) {
        return new PointerKeyboardModifiers(i);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof PointerKeyboardModifiers)) {
            return false;
        }
        if (this.packedValue != ((PointerKeyboardModifiers) obj).packedValue) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Integer.hashCode(this.packedValue);
    }

    public final String toString() {
        return "PointerKeyboardModifiers(packedValue=" + this.packedValue + ')';
    }
}
