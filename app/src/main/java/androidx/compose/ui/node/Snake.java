package androidx.compose.ui.node;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
abstract class Snake {
    /* renamed from: getDiagonalSize-impl  reason: not valid java name */
    public static final int m274getDiagonalSizeimpl(int[] iArr) {
        return Math.min(iArr[2] - iArr[0], iArr[3] - iArr[1]);
    }
}
