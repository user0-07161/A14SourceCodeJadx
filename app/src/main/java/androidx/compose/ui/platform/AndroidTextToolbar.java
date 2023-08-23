package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.geometry.Rect;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidTextToolbar {
    public AndroidTextToolbar(View view) {
        Rect rect;
        Intrinsics.checkNotNullParameter(view, "view");
        if (true & true) {
            rect = Rect.Zero;
        } else {
            rect = null;
        }
        Intrinsics.checkNotNullParameter(rect, "rect");
    }
}
