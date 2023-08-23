package androidx.window.core;

import android.graphics.Rect;
import androidx.compose.foundation.text.HeightInLinesModifierKt$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Bounds {
    private final int bottom;
    private final int left;
    private final int right;
    private final int top;

    public Bounds(Rect rect) {
        boolean z;
        int i = rect.left;
        int i2 = rect.top;
        int i3 = rect.right;
        int i4 = rect.bottom;
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
        if (i <= i3) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (i2 <= i4) {
                return;
            }
            throw new IllegalArgumentException(HeightInLinesModifierKt$$ExternalSyntheticOutline0.m("top must be less than or equal to bottom, top: ", i2, ", bottom: ", i4).toString());
        }
        throw new IllegalArgumentException(HeightInLinesModifierKt$$ExternalSyntheticOutline0.m("Left must be less than or equal to right, left: ", i, ", right: ", i3).toString());
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
        if (!Intrinsics.areEqual(Bounds.class, cls)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.window.core.Bounds");
        Bounds bounds = (Bounds) obj;
        if (this.left == bounds.left && this.top == bounds.top && this.right == bounds.right && this.bottom == bounds.bottom) {
            return true;
        }
        return false;
    }

    public final int getHeight() {
        return this.bottom - this.top;
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getTop() {
        return this.top;
    }

    public final int getWidth() {
        return this.right - this.left;
    }

    public final int hashCode() {
        return (((((this.left * 31) + this.top) * 31) + this.right) * 31) + this.bottom;
    }

    public final boolean isZero() {
        if (this.bottom - this.top == 0 && this.right - this.left == 0) {
            return true;
        }
        return false;
    }

    public final Rect toRect() {
        return new Rect(this.left, this.top, this.right, this.bottom);
    }

    public final String toString() {
        return "Bounds { [" + this.left + ',' + this.top + ',' + this.right + ',' + this.bottom + "] }";
    }
}
