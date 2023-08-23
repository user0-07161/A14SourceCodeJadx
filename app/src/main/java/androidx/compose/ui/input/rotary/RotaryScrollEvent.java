package androidx.compose.ui.input.rotary;

import androidx.compose.animation.core.AnimationVector4D$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RotaryScrollEvent {
    private final float horizontalScrollPixels;
    private final long uptimeMillis;
    private final float verticalScrollPixels;

    public RotaryScrollEvent(float f, long j, float f2) {
        this.verticalScrollPixels = f;
        this.horizontalScrollPixels = f2;
        this.uptimeMillis = j;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (!(obj instanceof RotaryScrollEvent)) {
            return false;
        }
        RotaryScrollEvent rotaryScrollEvent = (RotaryScrollEvent) obj;
        if (rotaryScrollEvent.verticalScrollPixels == this.verticalScrollPixels) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        if (rotaryScrollEvent.horizontalScrollPixels == this.horizontalScrollPixels) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2 || rotaryScrollEvent.uptimeMillis != this.uptimeMillis) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Long.hashCode(this.uptimeMillis) + AnimationVector4D$$ExternalSyntheticOutline0.m(this.horizontalScrollPixels, Float.hashCode(this.verticalScrollPixels) * 31, 31);
    }

    public final String toString() {
        return "RotaryScrollEvent(verticalScrollPixels=" + this.verticalScrollPixels + ",horizontalScrollPixels=" + this.horizontalScrollPixels + ",uptimeMillis=" + this.uptimeMillis + ')';
    }
}
