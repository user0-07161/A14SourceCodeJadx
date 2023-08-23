package androidx.compose.ui.text.font;

import android.graphics.Typeface;
import androidx.compose.ui.text.font.TypefaceResult;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PlatformFontFamilyTypefaceAdapter {
    private final PlatformTypefaces platformTypefaceResolver = new PlatformTypefacesApi28();

    public final TypefaceResult.Immutable resolve(TypefaceRequest typefaceRequest, AndroidFontLoader platformFontLoader, Function1 onAsyncCompletion, Function1 createDefaultTypeface) {
        boolean z;
        Typeface m336createNamedRetOiIg;
        Intrinsics.checkNotNullParameter(typefaceRequest, "typefaceRequest");
        Intrinsics.checkNotNullParameter(platformFontLoader, "platformFontLoader");
        Intrinsics.checkNotNullParameter(onAsyncCompletion, "onAsyncCompletion");
        Intrinsics.checkNotNullParameter(createDefaultTypeface, "createDefaultTypeface");
        FontFamily fontFamily = typefaceRequest.getFontFamily();
        if (fontFamily == null) {
            z = true;
        } else {
            z = fontFamily instanceof DefaultFontFamily;
        }
        PlatformTypefaces platformTypefaces = this.platformTypefaceResolver;
        if (z) {
            m336createNamedRetOiIg = ((PlatformTypefacesApi28) platformTypefaces).m335createDefaultFO1MlWM(typefaceRequest.getFontWeight(), typefaceRequest.m338getFontStyle_LCdwA());
        } else if (fontFamily instanceof GenericFontFamily) {
            m336createNamedRetOiIg = ((PlatformTypefacesApi28) platformTypefaces).m336createNamedRetOiIg((GenericFontFamily) typefaceRequest.getFontFamily(), typefaceRequest.getFontWeight(), typefaceRequest.m338getFontStyle_LCdwA());
        } else {
            return null;
        }
        return new TypefaceResult.Immutable(m336createNamedRetOiIg, true);
    }
}
