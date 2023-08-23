package androidx.compose.animation;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class EnterTransition {
    private static final EnterTransition None = new EnterTransitionImpl(new TransitionData(null, null, 15));

    public final boolean equals(Object obj) {
        if ((obj instanceof EnterTransition) && Intrinsics.areEqual(((EnterTransition) obj).getData$animation_release(), getData$animation_release())) {
            return true;
        }
        return false;
    }

    public abstract TransitionData getData$animation_release();

    public final int hashCode() {
        return getData$animation_release().hashCode();
    }

    public final String toString() {
        String str;
        String str2;
        if (Intrinsics.areEqual(this, None)) {
            return "EnterTransition.None";
        }
        TransitionData data$animation_release = getData$animation_release();
        StringBuilder sb = new StringBuilder("EnterTransition: \nFade - ");
        Fade fade = data$animation_release.getFade();
        if (fade != null) {
            str = fade.toString();
        } else {
            str = null;
        }
        sb.append(str);
        sb.append(",\nSlide - ");
        sb.append((String) null);
        sb.append(",\nShrink - ");
        ChangeSize changeSize = data$animation_release.getChangeSize();
        if (changeSize != null) {
            str2 = changeSize.toString();
        } else {
            str2 = null;
        }
        sb.append(str2);
        sb.append(",\nScale - ");
        sb.append((String) null);
        return sb.toString();
    }
}
