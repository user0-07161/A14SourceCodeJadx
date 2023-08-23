package androidx.compose.ui.text.android;

import android.text.Spanned;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SpannedExtensionsKt {
    public static final boolean hasSpan(Spanned spanned, Class cls) {
        Intrinsics.checkNotNullParameter(spanned, "<this>");
        if (spanned.nextSpanTransition(-1, spanned.length(), cls) != spanned.length()) {
            return true;
        }
        return false;
    }
}
