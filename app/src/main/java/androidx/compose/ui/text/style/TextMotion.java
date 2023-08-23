package androidx.compose.ui.text.style;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextMotion {
    private final int linearity;
    private final boolean subpixelTextPositioning;
    private static final TextMotion Static = new TextMotion(2, false);
    private static final TextMotion Animated = new TextMotion(1, true);

    public TextMotion(int i, boolean z) {
        this.linearity = i;
        this.subpixelTextPositioning = z;
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextMotion)) {
            return false;
        }
        TextMotion textMotion = (TextMotion) obj;
        if (this.linearity == textMotion.linearity) {
            z = true;
        } else {
            z = false;
        }
        if (z && this.subpixelTextPositioning == textMotion.subpixelTextPositioning) {
            return true;
        }
        return false;
    }

    /* renamed from: getLinearity-4e0Vf04$ui_text_release  reason: not valid java name */
    public final int m375getLinearity4e0Vf04$ui_text_release() {
        return this.linearity;
    }

    public final boolean getSubpixelTextPositioning$ui_text_release() {
        return this.subpixelTextPositioning;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.subpixelTextPositioning) + (Integer.hashCode(this.linearity) * 31);
    }

    public final String toString() {
        if (Intrinsics.areEqual(this, Static)) {
            return "TextMotion.Static";
        }
        if (Intrinsics.areEqual(this, Animated)) {
            return "TextMotion.Animated";
        }
        return "Invalid";
    }
}
