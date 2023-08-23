package androidx.compose.animation;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TransitionData {
    private final ChangeSize changeSize;
    private final Fade fade;

    public TransitionData(Fade fade, ChangeSize changeSize) {
        this.fade = fade;
        this.changeSize = changeSize;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransitionData)) {
            return false;
        }
        TransitionData transitionData = (TransitionData) obj;
        if (Intrinsics.areEqual(this.fade, transitionData.fade) && Intrinsics.areEqual(null, null) && Intrinsics.areEqual(this.changeSize, transitionData.changeSize) && Intrinsics.areEqual(null, null)) {
            return true;
        }
        return false;
    }

    public final ChangeSize getChangeSize() {
        return this.changeSize;
    }

    public final Fade getFade() {
        return this.fade;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2;
        Fade fade = this.fade;
        if (fade == null) {
            hashCode = 0;
        } else {
            hashCode = fade.hashCode();
        }
        int i = ((hashCode * 31) + 0) * 31;
        ChangeSize changeSize = this.changeSize;
        if (changeSize == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = changeSize.hashCode();
        }
        return ((i + hashCode2) * 31) + 0;
    }

    public final String toString() {
        return "TransitionData(fade=" + this.fade + ", slide=null, changeSize=" + this.changeSize + ", scale=null)";
    }

    public /* synthetic */ TransitionData(Fade fade, ChangeSize changeSize, int i) {
        this((i & 1) != 0 ? null : fade, (i & 4) != 0 ? null : changeSize);
    }
}
