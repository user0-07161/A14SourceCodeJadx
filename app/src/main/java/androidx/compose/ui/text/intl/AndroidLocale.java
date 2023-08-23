package androidx.compose.ui.text.intl;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidLocale {
    private final java.util.Locale javaLocale;

    public AndroidLocale(java.util.Locale locale) {
        this.javaLocale = locale;
    }

    public final java.util.Locale getJavaLocale() {
        return this.javaLocale;
    }

    public final String toLanguageTag() {
        String languageTag = this.javaLocale.toLanguageTag();
        Intrinsics.checkNotNullExpressionValue(languageTag, "javaLocale.toLanguageTag()");
        return languageTag;
    }
}
