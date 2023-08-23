package androidx.compose.ui.layout;

import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AlignmentLine {
    private final Function2 merger;

    public AlignmentLine(Function2 function2) {
        this.merger = function2;
    }

    public final Function2 getMerger$ui_release() {
        return this.merger;
    }
}
