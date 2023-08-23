package androidx.compose.ui.text.font;

import android.graphics.Typeface;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class PlatformTypefacesApi28 implements PlatformTypefaces {
    /* renamed from: createAndroidTypefaceApi28-RetOiIg  reason: not valid java name */
    private static Typeface m334createAndroidTypefaceApi28RetOiIg(String str, FontWeight fontWeight, int i) {
        boolean z;
        Typeface create;
        FontWeight fontWeight2;
        boolean z2;
        boolean z3 = true;
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            fontWeight2 = FontWeight.Normal;
            if (Intrinsics.areEqual(fontWeight, fontWeight2)) {
                if (str != null && str.length() != 0) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (z2) {
                    Typeface DEFAULT = Typeface.DEFAULT;
                    Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
                    return DEFAULT;
                }
            }
        }
        if (str == null) {
            create = Typeface.DEFAULT;
        } else {
            create = Typeface.create(str, 0);
        }
        int weight = fontWeight.getWeight();
        if (i != 1) {
            z3 = false;
        }
        Typeface create2 = Typeface.create(create, weight, z3);
        Intrinsics.checkNotNullExpressionValue(create2, "create(\n            fami…ontStyle.Italic\n        )");
        return create2;
    }

    /* renamed from: createDefault-FO1MlWM  reason: not valid java name */
    public final Typeface m335createDefaultFO1MlWM(FontWeight fontWeight, int i) {
        Intrinsics.checkNotNullParameter(fontWeight, "fontWeight");
        return m334createAndroidTypefaceApi28RetOiIg(null, fontWeight, i);
    }

    /* renamed from: createNamed-RetOiIg  reason: not valid java name */
    public final Typeface m336createNamedRetOiIg(GenericFontFamily name, FontWeight fontWeight, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(fontWeight, "fontWeight");
        return m334createAndroidTypefaceApi28RetOiIg(name.getName(), fontWeight, i);
    }
}
