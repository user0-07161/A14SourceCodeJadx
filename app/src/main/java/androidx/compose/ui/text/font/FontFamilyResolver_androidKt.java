package androidx.compose.ui.text.font;

import android.content.Context;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class FontFamilyResolver_androidKt {
    public static final FontFamilyResolverImpl createFontFamilyResolver(Context context) {
        return new FontFamilyResolverImpl(new AndroidFontLoader(context), new AndroidFontResolveInterceptor(context.getResources().getConfiguration().fontWeightAdjustment));
    }
}
