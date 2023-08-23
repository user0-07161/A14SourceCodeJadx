package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Outline {

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Rectangle extends Outline {
        private final Rect rect;

        public Rectangle(Rect rect) {
            this.rect = rect;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Rectangle)) {
                return false;
            }
            if (Intrinsics.areEqual(this.rect, ((Rectangle) obj).rect)) {
                return true;
            }
            return false;
        }

        public final Rect getRect() {
            return this.rect;
        }

        public final int hashCode() {
            return this.rect.hashCode();
        }
    }
}
