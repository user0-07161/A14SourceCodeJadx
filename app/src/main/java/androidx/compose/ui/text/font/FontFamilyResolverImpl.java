package androidx.compose.ui.text.font;

import androidx.compose.ui.text.font.TypefaceResult;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FontFamilyResolverImpl {
    private final Function1 createDefaultTypeface;
    private final FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter;
    private final PlatformFontFamilyTypefaceAdapter platformFamilyTypefaceAdapter;
    private final AndroidFontLoader platformFontLoader;
    private final PlatformResolveInterceptor platformResolveInterceptor;
    private final TypefaceRequestCache typefaceRequestCache;

    public FontFamilyResolverImpl(AndroidFontLoader androidFontLoader, AndroidFontResolveInterceptor androidFontResolveInterceptor) {
        TypefaceRequestCache typefaceRequestCache = FontFamilyResolverKt.getGlobalTypefaceRequestCache();
        FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter = new FontListFontFamilyTypefaceAdapter(FontFamilyResolverKt.getGlobalAsyncTypefaceCache());
        PlatformFontFamilyTypefaceAdapter platformFontFamilyTypefaceAdapter = new PlatformFontFamilyTypefaceAdapter();
        Intrinsics.checkNotNullParameter(typefaceRequestCache, "typefaceRequestCache");
        this.platformFontLoader = androidFontLoader;
        this.platformResolveInterceptor = androidFontResolveInterceptor;
        this.typefaceRequestCache = typefaceRequestCache;
        this.fontListFontFamilyTypefaceAdapter = fontListFontFamilyTypefaceAdapter;
        this.platformFamilyTypefaceAdapter = platformFontFamilyTypefaceAdapter;
        this.createDefaultTypeface = new Function1() { // from class: androidx.compose.ui.text.font.FontFamilyResolverImpl$createDefaultTypeface$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                TypefaceRequest it = (TypefaceRequest) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                return ((TypefaceResult.Immutable) FontFamilyResolverImpl.access$resolve(FontFamilyResolverImpl.this, TypefaceRequest.m337copye1PVR60$default(it))).getValue();
            }
        };
    }

    public static final /* synthetic */ Function1 access$getCreateDefaultTypeface$p(FontFamilyResolverImpl fontFamilyResolverImpl) {
        return fontFamilyResolverImpl.createDefaultTypeface;
    }

    public static final /* synthetic */ FontListFontFamilyTypefaceAdapter access$getFontListFontFamilyTypefaceAdapter$p(FontFamilyResolverImpl fontFamilyResolverImpl) {
        return fontFamilyResolverImpl.fontListFontFamilyTypefaceAdapter;
    }

    public static final /* synthetic */ PlatformFontFamilyTypefaceAdapter access$getPlatformFamilyTypefaceAdapter$p(FontFamilyResolverImpl fontFamilyResolverImpl) {
        return fontFamilyResolverImpl.platformFamilyTypefaceAdapter;
    }

    public static final TypefaceResult access$resolve(FontFamilyResolverImpl fontFamilyResolverImpl, TypefaceRequest typefaceRequest) {
        fontFamilyResolverImpl.getClass();
        return fontFamilyResolverImpl.typefaceRequestCache.runCached(typefaceRequest, new FontFamilyResolverImpl$resolve$result$1(fontFamilyResolverImpl, typefaceRequest));
    }

    public final AndroidFontLoader getPlatformFontLoader$ui_text_release() {
        return this.platformFontLoader;
    }

    /* renamed from: resolve-DPcqOEQ */
    public final TypefaceResult m328resolveDPcqOEQ(FontFamily fontFamily, FontWeight fontWeight, int i, int i2) {
        Intrinsics.checkNotNullParameter(fontWeight, "fontWeight");
        PlatformResolveInterceptor platformResolveInterceptor = this.platformResolveInterceptor;
        platformResolveInterceptor.getClass();
        FontWeight interceptFontWeight = platformResolveInterceptor.interceptFontWeight(fontWeight);
        this.platformFontLoader.getClass();
        TypefaceRequest typefaceRequest = new TypefaceRequest(fontFamily, interceptFontWeight, i, i2, null);
        return this.typefaceRequestCache.runCached(typefaceRequest, new FontFamilyResolverImpl$resolve$result$1(this, typefaceRequest));
    }
}
