package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class HistoricalChange {
    private final long position;
    private final long uptimeMillis;

    public HistoricalChange(long j, long j2) {
        this.uptimeMillis = j;
        this.position = j2;
    }

    /* renamed from: getPosition-F1C5BW0  reason: not valid java name */
    public final long m178getPositionF1C5BW0() {
        return this.position;
    }

    public final long getUptimeMillis() {
        return this.uptimeMillis;
    }

    public final String toString() {
        return "HistoricalChange(uptimeMillis=" + this.uptimeMillis + ", position=" + ((Object) Offset.m50toStringimpl(this.position)) + ')';
    }
}
