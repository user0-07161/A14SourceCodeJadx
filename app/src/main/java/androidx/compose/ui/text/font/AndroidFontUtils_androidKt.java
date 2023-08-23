package androidx.compose.ui.text.font;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AndroidFontUtils_androidKt {
    /* renamed from: getAndroidTypefaceStyle-FO1MlWM  reason: not valid java name */
    public static final int m327getAndroidTypefaceStyleFO1MlWM(FontWeight fontWeight, int i) {
        FontWeight fontWeight2;
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(fontWeight, "fontWeight");
        fontWeight2 = FontWeight.W600;
        if (fontWeight.compareTo(fontWeight2) >= 0) {
            z = true;
        } else {
            z = false;
        }
        if (i == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 && z) {
            return 3;
        }
        if (z) {
            return 1;
        }
        if (!z2) {
            return 0;
        }
        return 2;
    }
}
