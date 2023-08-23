package androidx.compose.ui.semantics;

import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ProgressBarRangeInfo {
    private static final ProgressBarRangeInfo Indeterminate = new ProgressBarRangeInfo(RangesKt.rangeTo(0.0f, 0.0f));
    private final ClosedFloatingPointRange range;
    private final float current = 0.0f;
    private final int steps = 0;

    public ProgressBarRangeInfo(ClosedFloatingPointRange closedFloatingPointRange) {
        this.range = closedFloatingPointRange;
        if (!Float.isNaN(0.0f)) {
            return;
        }
        throw new IllegalArgumentException("current must not be NaN".toString());
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProgressBarRangeInfo)) {
            return false;
        }
        ProgressBarRangeInfo progressBarRangeInfo = (ProgressBarRangeInfo) obj;
        if (this.current == progressBarRangeInfo.current) {
            z = true;
        } else {
            z = false;
        }
        if (z && Intrinsics.areEqual(this.range, progressBarRangeInfo.range) && this.steps == progressBarRangeInfo.steps) {
            return true;
        }
        return false;
    }

    public final float getCurrent() {
        return this.current;
    }

    public final ClosedFloatingPointRange getRange() {
        return this.range;
    }

    public final int getSteps() {
        return this.steps;
    }

    public final int hashCode() {
        return ((this.range.hashCode() + (Float.hashCode(this.current) * 31)) * 31) + this.steps;
    }

    public final String toString() {
        return "ProgressBarRangeInfo(current=" + this.current + ", range=" + this.range + ", steps=" + this.steps + ')';
    }
}
