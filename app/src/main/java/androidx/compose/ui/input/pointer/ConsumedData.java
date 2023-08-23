package androidx.compose.ui.input.pointer;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ConsumedData {
    private boolean downChange;
    private boolean positionChange;

    public ConsumedData(boolean z, boolean z2) {
        this.positionChange = z;
        this.downChange = z2;
    }

    public final boolean getDownChange() {
        return this.downChange;
    }

    public final boolean getPositionChange() {
        return this.positionChange;
    }

    public final void setDownChange() {
        this.downChange = true;
    }

    public final void setPositionChange() {
        this.positionChange = true;
    }
}
