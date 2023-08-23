package androidx.compose.ui.text.font;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TypefaceRequest {
    private final FontFamily fontFamily;
    private final int fontStyle;
    private final int fontSynthesis;
    private final FontWeight fontWeight;
    private final Object resourceLoaderCacheKey;

    public TypefaceRequest(FontFamily fontFamily, FontWeight fontWeight, int i, int i2, Object obj) {
        this.fontFamily = fontFamily;
        this.fontWeight = fontWeight;
        this.fontStyle = i;
        this.fontSynthesis = i2;
        this.resourceLoaderCacheKey = obj;
    }

    /* renamed from: copy-e1PVR60$default  reason: not valid java name */
    public static TypefaceRequest m337copye1PVR60$default(TypefaceRequest typefaceRequest) {
        FontWeight fontWeight = typefaceRequest.fontWeight;
        int i = typefaceRequest.fontStyle;
        int i2 = typefaceRequest.fontSynthesis;
        Object obj = typefaceRequest.resourceLoaderCacheKey;
        typefaceRequest.getClass();
        Intrinsics.checkNotNullParameter(fontWeight, "fontWeight");
        return new TypefaceRequest(null, fontWeight, i, i2, obj);
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TypefaceRequest)) {
            return false;
        }
        TypefaceRequest typefaceRequest = (TypefaceRequest) obj;
        if (!Intrinsics.areEqual(this.fontFamily, typefaceRequest.fontFamily) || !Intrinsics.areEqual(this.fontWeight, typefaceRequest.fontWeight)) {
            return false;
        }
        if (this.fontStyle == typefaceRequest.fontStyle) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        if (this.fontSynthesis == typefaceRequest.fontSynthesis) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 && Intrinsics.areEqual(this.resourceLoaderCacheKey, typefaceRequest.resourceLoaderCacheKey)) {
            return true;
        }
        return false;
    }

    public final FontFamily getFontFamily() {
        return this.fontFamily;
    }

    /* renamed from: getFontStyle-_-LCdwA  reason: not valid java name */
    public final int m338getFontStyle_LCdwA() {
        return this.fontStyle;
    }

    public final FontWeight getFontWeight() {
        return this.fontWeight;
    }

    public final int hashCode() {
        int hashCode;
        int i = 0;
        FontFamily fontFamily = this.fontFamily;
        if (fontFamily == null) {
            hashCode = 0;
        } else {
            hashCode = fontFamily.hashCode();
        }
        int hashCode2 = this.fontWeight.hashCode();
        int hashCode3 = (Integer.hashCode(this.fontSynthesis) + ((Integer.hashCode(this.fontStyle) + ((hashCode2 + (hashCode * 31)) * 31)) * 31)) * 31;
        Object obj = this.resourceLoaderCacheKey;
        if (obj != null) {
            i = obj.hashCode();
        }
        return hashCode3 + i;
    }

    public final String toString() {
        boolean z;
        String str;
        StringBuilder sb = new StringBuilder("TypefaceRequest(fontFamily=");
        sb.append(this.fontFamily);
        sb.append(", fontWeight=");
        sb.append(this.fontWeight);
        sb.append(", fontStyle=");
        int i = this.fontStyle;
        boolean z2 = false;
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            str = "Normal";
        } else {
            if (i == 1) {
                z2 = true;
            }
            if (z2) {
                str = "Italic";
            } else {
                str = "Invalid";
            }
        }
        sb.append((Object) str);
        sb.append(", fontSynthesis=");
        sb.append((Object) FontSynthesis.m332toStringimpl(this.fontSynthesis));
        sb.append(", resourceLoaderCacheKey=");
        sb.append(this.resourceLoaderCacheKey);
        sb.append(')');
        return sb.toString();
    }
}
