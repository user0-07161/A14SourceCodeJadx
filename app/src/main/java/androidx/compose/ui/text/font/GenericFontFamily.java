package androidx.compose.ui.text.font;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class GenericFontFamily extends SystemFontFamily {
    private final String fontFamilyName;
    private final String name;

    public GenericFontFamily(String str, String str2) {
        this.name = str;
        this.fontFamilyName = str2;
    }

    public final String getName() {
        return this.name;
    }

    public final String toString() {
        return this.fontFamilyName;
    }
}
