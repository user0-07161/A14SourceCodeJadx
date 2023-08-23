package androidx.compose.ui.text.font;

import androidx.compose.ui.text.font.TypefaceResult;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class FontFamilyResolverImpl$resolve$result$1 extends Lambda implements Function1 {
    final /* synthetic */ TypefaceRequest $typefaceRequest;
    final /* synthetic */ FontFamilyResolverImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FontFamilyResolverImpl$resolve$result$1(FontFamilyResolverImpl fontFamilyResolverImpl, TypefaceRequest typefaceRequest) {
        super(1);
        this.this$0 = fontFamilyResolverImpl;
        this.$typefaceRequest = typefaceRequest;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Function1 onAsyncCompletion = (Function1) obj;
        Intrinsics.checkNotNullParameter(onAsyncCompletion, "onAsyncCompletion");
        FontListFontFamilyTypefaceAdapter access$getFontListFontFamilyTypefaceAdapter$p = FontFamilyResolverImpl.access$getFontListFontFamilyTypefaceAdapter$p(this.this$0);
        TypefaceRequest typefaceRequest = this.$typefaceRequest;
        AndroidFontLoader platformFontLoader = this.this$0.getPlatformFontLoader$ui_text_release();
        Function1 createDefaultTypeface = FontFamilyResolverImpl.access$getCreateDefaultTypeface$p(this.this$0);
        access$getFontListFontFamilyTypefaceAdapter$p.getClass();
        Intrinsics.checkNotNullParameter(typefaceRequest, "typefaceRequest");
        Intrinsics.checkNotNullParameter(platformFontLoader, "platformFontLoader");
        Intrinsics.checkNotNullParameter(createDefaultTypeface, "createDefaultTypeface");
        TypefaceResult.Immutable resolve = FontFamilyResolverImpl.access$getPlatformFamilyTypefaceAdapter$p(this.this$0).resolve(this.$typefaceRequest, this.this$0.getPlatformFontLoader$ui_text_release(), onAsyncCompletion, FontFamilyResolverImpl.access$getCreateDefaultTypeface$p(this.this$0));
        if (resolve != null) {
            return resolve;
        }
        throw new IllegalStateException("Could not load font");
    }
}
