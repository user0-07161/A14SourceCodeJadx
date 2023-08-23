package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Shadow {
    public static final Companion Companion = new Companion();
    private static final Shadow None;
    private final float blurRadius;
    private final long color;
    private final long offset;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
    }

    static {
        long j;
        long Color = ColorKt.Color(4278190080L);
        j = Offset.Zero;
        None = new Shadow(Color, j, 0.0f);
    }

    public Shadow(long j, long j2, float f) {
        this.color = j;
        this.offset = j2;
        this.blurRadius = f;
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Shadow)) {
            return false;
        }
        Shadow shadow = (Shadow) obj;
        if (!Color.m93equalsimpl0(this.color, shadow.color) || !Offset.m43equalsimpl0(this.offset, shadow.offset)) {
            return false;
        }
        if (this.blurRadius == shadow.blurRadius) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        return false;
    }

    public final float getBlurRadius() {
        return this.blurRadius;
    }

    /* renamed from: getColor-0d7_KjU  reason: not valid java name */
    public final long m115getColor0d7_KjU() {
        return this.color;
    }

    /* renamed from: getOffset-F1C5BW0  reason: not valid java name */
    public final long m116getOffsetF1C5BW0() {
        return this.offset;
    }

    public final int hashCode() {
        Color.Companion companion = Color.Companion;
        int hashCode = Long.hashCode(this.offset);
        return Float.hashCode(this.blurRadius) + ((hashCode + (Long.hashCode(this.color) * 31)) * 31);
    }

    public final String toString() {
        return "Shadow(color=" + ((Object) Color.m98toStringimpl(this.color)) + ", offset=" + ((Object) Offset.m50toStringimpl(this.offset)) + ", blurRadius=" + this.blurRadius + ')';
    }
}
