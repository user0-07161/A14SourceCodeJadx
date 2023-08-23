package androidx.compose.runtime;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Anchor {
    private int location;

    public Anchor(int i) {
        this.location = i;
    }

    public final int getLocation$runtime_release() {
        return this.location;
    }

    public final boolean getValid() {
        if (this.location != Integer.MIN_VALUE) {
            return true;
        }
        return false;
    }

    public final void setLocation$runtime_release(int i) {
        this.location = i;
    }
}
