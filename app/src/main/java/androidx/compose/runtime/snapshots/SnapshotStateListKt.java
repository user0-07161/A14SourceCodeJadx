package androidx.compose.runtime.snapshots;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SnapshotStateListKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Object sync = new Object();

    public static final void access$validateRange(int i, int i2) {
        boolean z;
        if (i >= 0 && i < i2) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        throw new IndexOutOfBoundsException("index (" + i + ") is out of bound of [0, " + i2 + ')');
    }
}
