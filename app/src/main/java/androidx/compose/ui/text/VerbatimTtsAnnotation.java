package androidx.compose.ui.text;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class VerbatimTtsAnnotation extends TtsAnnotation {
    private final String verbatim;

    public VerbatimTtsAnnotation(String verbatim) {
        Intrinsics.checkNotNullParameter(verbatim, "verbatim");
        this.verbatim = verbatim;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VerbatimTtsAnnotation)) {
            return false;
        }
        if (Intrinsics.areEqual(this.verbatim, ((VerbatimTtsAnnotation) obj).verbatim)) {
            return true;
        }
        return false;
    }

    public final String getVerbatim() {
        return this.verbatim;
    }

    public final int hashCode() {
        return this.verbatim.hashCode();
    }

    public final String toString() {
        return "VerbatimTtsAnnotation(verbatim=" + this.verbatim + ')';
    }
}
