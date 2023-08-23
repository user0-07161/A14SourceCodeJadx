package androidx.compose.ui.layout;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AlignmentLineKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final HorizontalAlignmentLine FirstBaseline = new HorizontalAlignmentLine(AlignmentLineKt$FirstBaseline$1.INSTANCE);
    private static final HorizontalAlignmentLine LastBaseline = new HorizontalAlignmentLine(AlignmentLineKt$LastBaseline$1.INSTANCE);

    public static final HorizontalAlignmentLine getFirstBaseline() {
        return FirstBaseline;
    }

    public static final HorizontalAlignmentLine getLastBaseline() {
        return LastBaseline;
    }
}
