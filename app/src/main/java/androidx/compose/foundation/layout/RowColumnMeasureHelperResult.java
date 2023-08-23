package androidx.compose.foundation.layout;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RowColumnMeasureHelperResult {
    private final int crossAxisSize;
    private final int endIndex;
    private final int[] mainAxisPositions;
    private final int mainAxisSize;
    private final int startIndex = 0;
    private final int beforeCrossAxisAlignmentLine = 0;

    public RowColumnMeasureHelperResult(int i, int i2, int i3, int[] iArr) {
        this.crossAxisSize = i;
        this.mainAxisSize = i2;
        this.endIndex = i3;
        this.mainAxisPositions = iArr;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    public final int getEndIndex() {
        return this.endIndex;
    }

    public final int[] getMainAxisPositions() {
        return this.mainAxisPositions;
    }

    public final int getMainAxisSize() {
        return this.mainAxisSize;
    }

    public final int getStartIndex() {
        return this.startIndex;
    }
}
