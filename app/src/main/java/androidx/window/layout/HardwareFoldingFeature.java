package androidx.window.layout;

import androidx.window.core.Bounds;
import androidx.window.layout.FoldingFeature;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class HardwareFoldingFeature implements FoldingFeature {
    private final Bounds featureBounds;
    private final FoldingFeature.State state;
    private final Type type;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Type {
        private static final Type FOLD = new Type("FOLD");
        private static final Type HINGE = new Type("HINGE");
        private final String description;

        private Type(String str) {
            this.description = str;
        }

        public final String toString() {
            return this.description;
        }
    }

    public HardwareFoldingFeature(Bounds bounds, Type type, FoldingFeature.State state) {
        boolean z;
        this.featureBounds = bounds;
        this.type = type;
        this.state = state;
        boolean z2 = false;
        if (bounds.getWidth() == 0 && bounds.getHeight() == 0) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            if ((bounds.getLeft() == 0 || bounds.getTop() == 0) ? true : z2) {
                return;
            }
            throw new IllegalArgumentException("Bounding rectangle must start at the top or left window edge for folding features".toString());
        }
        throw new IllegalArgumentException("Bounds must be non zero".toString());
    }

    public final boolean equals(Object obj) {
        Class<?> cls;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            cls = obj.getClass();
        } else {
            cls = null;
        }
        if (!Intrinsics.areEqual(HardwareFoldingFeature.class, cls)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.window.layout.HardwareFoldingFeature");
        HardwareFoldingFeature hardwareFoldingFeature = (HardwareFoldingFeature) obj;
        if (Intrinsics.areEqual(this.featureBounds, hardwareFoldingFeature.featureBounds) && Intrinsics.areEqual(this.type, hardwareFoldingFeature.type) && Intrinsics.areEqual(this.state, hardwareFoldingFeature.state)) {
            return true;
        }
        return false;
    }

    public final FoldingFeature.Orientation getOrientation() {
        Bounds bounds = this.featureBounds;
        if (bounds.getWidth() > bounds.getHeight()) {
            return FoldingFeature.Orientation.HORIZONTAL;
        }
        return FoldingFeature.Orientation.VERTICAL;
    }

    public final FoldingFeature.State getState() {
        return this.state;
    }

    public final int hashCode() {
        int hashCode = this.type.hashCode();
        return this.state.hashCode() + ((hashCode + (this.featureBounds.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "HardwareFoldingFeature { " + this.featureBounds + ", type=" + this.type + ", state=" + this.state + " }";
    }
}
