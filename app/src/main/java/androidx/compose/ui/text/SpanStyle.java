package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SpanStyle {
    private final long background;
    private final BaselineShift baselineShift;
    private final DrawStyle drawStyle;
    private final FontFamily fontFamily;
    private final String fontFeatureSettings;
    private final long fontSize;
    private final FontStyle fontStyle;
    private final FontSynthesis fontSynthesis;
    private final FontWeight fontWeight;
    private final long letterSpacing;
    private final LocaleList localeList;
    private final Shadow shadow;
    private final TextDecoration textDecoration;
    private final TextForegroundStyle textForegroundStyle;
    private final TextGeometricTransform textGeometricTransform;

    public SpanStyle(TextForegroundStyle textForegroundStyle, long j, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j2, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j3, TextDecoration textDecoration, Shadow shadow, DrawStyle drawStyle) {
        this.textForegroundStyle = textForegroundStyle;
        this.fontSize = j;
        this.fontWeight = fontWeight;
        this.fontStyle = fontStyle;
        this.fontSynthesis = fontSynthesis;
        this.fontFamily = fontFamily;
        this.fontFeatureSettings = str;
        this.letterSpacing = j2;
        this.baselineShift = baselineShift;
        this.textGeometricTransform = textGeometricTransform;
        this.localeList = localeList;
        this.background = j3;
        this.textDecoration = textDecoration;
        this.shadow = shadow;
        this.drawStyle = drawStyle;
    }

    /* renamed from: copy-IuqyXdg$default  reason: not valid java name */
    public static SpanStyle m302copyIuqyXdg$default(SpanStyle spanStyle) {
        TextForegroundStyle m372from8_81llA;
        long m305getColor0d7_KjU = spanStyle.m305getColor0d7_KjU();
        long j = spanStyle.fontSize;
        FontWeight fontWeight = spanStyle.fontWeight;
        FontStyle fontStyle = spanStyle.fontStyle;
        FontSynthesis fontSynthesis = spanStyle.fontSynthesis;
        String str = spanStyle.fontFeatureSettings;
        long j2 = spanStyle.letterSpacing;
        BaselineShift baselineShift = spanStyle.baselineShift;
        TextGeometricTransform textGeometricTransform = spanStyle.textGeometricTransform;
        LocaleList localeList = spanStyle.localeList;
        long j3 = spanStyle.background;
        TextDecoration textDecoration = spanStyle.textDecoration;
        Shadow shadow = spanStyle.shadow;
        if (Color.m93equalsimpl0(m305getColor0d7_KjU, spanStyle.m305getColor0d7_KjU())) {
            m372from8_81llA = spanStyle.textForegroundStyle;
        } else {
            m372from8_81llA = TextForegroundStyle.Companion.m372from8_81llA(m305getColor0d7_KjU);
        }
        return new SpanStyle(m372from8_81llA, j, fontWeight, fontStyle, fontSynthesis, (FontFamily) null, str, j2, baselineShift, textGeometricTransform, localeList, j3, textDecoration, shadow, spanStyle.drawStyle);
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SpanStyle)) {
            return false;
        }
        SpanStyle spanStyle = (SpanStyle) obj;
        if (hasSameLayoutAffectingAttributes$ui_text_release(spanStyle)) {
            if (!Intrinsics.areEqual(this.textForegroundStyle, spanStyle.textForegroundStyle) || !Intrinsics.areEqual(this.textDecoration, spanStyle.textDecoration) || !Intrinsics.areEqual(this.shadow, spanStyle.shadow) || !Intrinsics.areEqual(this.drawStyle, spanStyle.drawStyle)) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final float getAlpha() {
        return this.textForegroundStyle.getAlpha();
    }

    /* renamed from: getBackground-0d7_KjU  reason: not valid java name */
    public final long m303getBackground0d7_KjU() {
        return this.background;
    }

    /* renamed from: getBaselineShift-5SSeXJ0  reason: not valid java name */
    public final BaselineShift m304getBaselineShift5SSeXJ0() {
        return this.baselineShift;
    }

    public final Brush getBrush() {
        return this.textForegroundStyle.getBrush();
    }

    /* renamed from: getColor-0d7_KjU  reason: not valid java name */
    public final long m305getColor0d7_KjU() {
        return this.textForegroundStyle.mo352getColor0d7_KjU();
    }

    public final DrawStyle getDrawStyle() {
        return this.drawStyle;
    }

    public final FontFamily getFontFamily() {
        return this.fontFamily;
    }

    public final String getFontFeatureSettings() {
        return this.fontFeatureSettings;
    }

    /* renamed from: getFontSize-XSAIIZE  reason: not valid java name */
    public final long m306getFontSizeXSAIIZE() {
        return this.fontSize;
    }

    /* renamed from: getFontStyle-4Lr2A7w  reason: not valid java name */
    public final FontStyle m307getFontStyle4Lr2A7w() {
        return this.fontStyle;
    }

    /* renamed from: getFontSynthesis-ZQGJjVo  reason: not valid java name */
    public final FontSynthesis m308getFontSynthesisZQGJjVo() {
        return this.fontSynthesis;
    }

    public final FontWeight getFontWeight() {
        return this.fontWeight;
    }

    /* renamed from: getLetterSpacing-XSAIIZE  reason: not valid java name */
    public final long m309getLetterSpacingXSAIIZE() {
        return this.letterSpacing;
    }

    public final LocaleList getLocaleList() {
        return this.localeList;
    }

    public final Shadow getShadow() {
        return this.shadow;
    }

    public final TextDecoration getTextDecoration() {
        return this.textDecoration;
    }

    public final TextForegroundStyle getTextForegroundStyle$ui_text_release() {
        return this.textForegroundStyle;
    }

    public final TextGeometricTransform getTextGeometricTransform() {
        return this.textGeometricTransform;
    }

    public final boolean hasSameLayoutAffectingAttributes$ui_text_release(SpanStyle other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (this == other) {
            return true;
        }
        if (TextUnit.m410equalsimpl0(this.fontSize, other.fontSize) && Intrinsics.areEqual(this.fontWeight, other.fontWeight) && Intrinsics.areEqual(this.fontStyle, other.fontStyle) && Intrinsics.areEqual(this.fontSynthesis, other.fontSynthesis) && Intrinsics.areEqual(this.fontFamily, other.fontFamily) && Intrinsics.areEqual(this.fontFeatureSettings, other.fontFeatureSettings) && TextUnit.m410equalsimpl0(this.letterSpacing, other.letterSpacing) && Intrinsics.areEqual(this.baselineShift, other.baselineShift) && Intrinsics.areEqual(this.textGeometricTransform, other.textGeometricTransform) && Intrinsics.areEqual(this.localeList, other.localeList) && Color.m93equalsimpl0(this.background, other.background) && Intrinsics.areEqual(null, null)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        long m305getColor0d7_KjU = m305getColor0d7_KjU();
        Color.Companion companion = Color.Companion;
        int hashCode = Long.hashCode(m305getColor0d7_KjU) * 31;
        Brush brush = getBrush();
        int i12 = 0;
        if (brush != null) {
            i = brush.hashCode();
        } else {
            i = 0;
        }
        int hashCode2 = Float.hashCode(getAlpha());
        TextUnit.Companion companion2 = TextUnit.Companion;
        int hashCode3 = (Long.hashCode(this.fontSize) + ((hashCode2 + ((hashCode + i) * 31)) * 31)) * 31;
        FontWeight fontWeight = this.fontWeight;
        if (fontWeight != null) {
            i2 = fontWeight.hashCode();
        } else {
            i2 = 0;
        }
        int i13 = (hashCode3 + i2) * 31;
        FontStyle fontStyle = this.fontStyle;
        if (fontStyle != null) {
            i3 = Integer.hashCode(fontStyle.m330unboximpl());
        } else {
            i3 = 0;
        }
        int i14 = (i13 + i3) * 31;
        FontSynthesis fontSynthesis = this.fontSynthesis;
        if (fontSynthesis != null) {
            i4 = Integer.hashCode(fontSynthesis.m333unboximpl());
        } else {
            i4 = 0;
        }
        int i15 = (i14 + i4) * 31;
        FontFamily fontFamily = this.fontFamily;
        if (fontFamily != null) {
            i5 = fontFamily.hashCode();
        } else {
            i5 = 0;
        }
        int i16 = (i15 + i5) * 31;
        String str = this.fontFeatureSettings;
        if (str != null) {
            i6 = str.hashCode();
        } else {
            i6 = 0;
        }
        int hashCode4 = (Long.hashCode(this.letterSpacing) + ((i16 + i6) * 31)) * 31;
        BaselineShift baselineShift = this.baselineShift;
        if (baselineShift != null) {
            i7 = Float.hashCode(baselineShift.m351unboximpl());
        } else {
            i7 = 0;
        }
        int i17 = (hashCode4 + i7) * 31;
        TextGeometricTransform textGeometricTransform = this.textGeometricTransform;
        if (textGeometricTransform != null) {
            i8 = textGeometricTransform.hashCode();
        } else {
            i8 = 0;
        }
        int i18 = (i17 + i8) * 31;
        LocaleList localeList = this.localeList;
        if (localeList != null) {
            i9 = localeList.hashCode();
        } else {
            i9 = 0;
        }
        int hashCode5 = (Long.hashCode(this.background) + ((i18 + i9) * 31)) * 31;
        TextDecoration textDecoration = this.textDecoration;
        if (textDecoration != null) {
            i10 = textDecoration.hashCode();
        } else {
            i10 = 0;
        }
        int i19 = (hashCode5 + i10) * 31;
        Shadow shadow = this.shadow;
        if (shadow != null) {
            i11 = shadow.hashCode();
        } else {
            i11 = 0;
        }
        int i20 = (((i19 + i11) * 31) + 0) * 31;
        DrawStyle drawStyle = this.drawStyle;
        if (drawStyle != null) {
            i12 = drawStyle.hashCode();
        }
        return i20 + i12;
    }

    public final SpanStyle merge(SpanStyle spanStyle) {
        long j;
        boolean z;
        long j2;
        DrawStyle drawStyle;
        if (spanStyle == null) {
            return this;
        }
        TextForegroundStyle merge = this.textForegroundStyle.merge(spanStyle.textForegroundStyle);
        FontFamily fontFamily = spanStyle.fontFamily;
        if (fontFamily == null) {
            fontFamily = this.fontFamily;
        }
        FontFamily fontFamily2 = fontFamily;
        long j3 = spanStyle.fontSize;
        if (TextUnitKt.m415isUnspecifiedR2X_6o(j3)) {
            j3 = this.fontSize;
        }
        long j4 = j3;
        FontWeight fontWeight = spanStyle.fontWeight;
        if (fontWeight == null) {
            fontWeight = this.fontWeight;
        }
        FontWeight fontWeight2 = fontWeight;
        FontStyle fontStyle = spanStyle.fontStyle;
        if (fontStyle == null) {
            fontStyle = this.fontStyle;
        }
        FontStyle fontStyle2 = fontStyle;
        FontSynthesis fontSynthesis = spanStyle.fontSynthesis;
        if (fontSynthesis == null) {
            fontSynthesis = this.fontSynthesis;
        }
        FontSynthesis fontSynthesis2 = fontSynthesis;
        String str = spanStyle.fontFeatureSettings;
        if (str == null) {
            str = this.fontFeatureSettings;
        }
        String str2 = str;
        long j5 = spanStyle.letterSpacing;
        if (TextUnitKt.m415isUnspecifiedR2X_6o(j5)) {
            j5 = this.letterSpacing;
        }
        long j6 = j5;
        BaselineShift baselineShift = spanStyle.baselineShift;
        if (baselineShift == null) {
            baselineShift = this.baselineShift;
        }
        BaselineShift baselineShift2 = baselineShift;
        TextGeometricTransform textGeometricTransform = spanStyle.textGeometricTransform;
        if (textGeometricTransform == null) {
            textGeometricTransform = this.textGeometricTransform;
        }
        TextGeometricTransform textGeometricTransform2 = textGeometricTransform;
        LocaleList localeList = spanStyle.localeList;
        if (localeList == null) {
            localeList = this.localeList;
        }
        LocaleList localeList2 = localeList;
        j = Color.Unspecified;
        long j7 = spanStyle.background;
        if (j7 != j) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            j2 = j7;
        } else {
            j2 = this.background;
        }
        TextDecoration textDecoration = spanStyle.textDecoration;
        if (textDecoration == null) {
            textDecoration = this.textDecoration;
        }
        TextDecoration textDecoration2 = textDecoration;
        Shadow shadow = spanStyle.shadow;
        if (shadow == null) {
            shadow = this.shadow;
        }
        Shadow shadow2 = shadow;
        DrawStyle drawStyle2 = spanStyle.drawStyle;
        if (drawStyle2 == null) {
            drawStyle = this.drawStyle;
        } else {
            drawStyle = drawStyle2;
        }
        return new SpanStyle(merge, j4, fontWeight2, fontStyle2, fontSynthesis2, fontFamily2, str2, j6, baselineShift2, textGeometricTransform2, localeList2, j2, textDecoration2, shadow2, drawStyle);
    }

    public final String toString() {
        return "SpanStyle(color=" + ((Object) Color.m98toStringimpl(m305getColor0d7_KjU())) + ", brush=" + getBrush() + ", alpha=" + getAlpha() + ", fontSize=" + ((Object) TextUnit.m413toStringimpl(this.fontSize)) + ", fontWeight=" + this.fontWeight + ", fontStyle=" + this.fontStyle + ", fontSynthesis=" + this.fontSynthesis + ", fontFamily=" + this.fontFamily + ", fontFeatureSettings=" + this.fontFeatureSettings + ", letterSpacing=" + ((Object) TextUnit.m413toStringimpl(this.letterSpacing)) + ", baselineShift=" + this.baselineShift + ", textGeometricTransform=" + this.textGeometricTransform + ", localeList=" + this.localeList + ", background=" + ((Object) Color.m98toStringimpl(this.background)) + ", textDecoration=" + this.textDecoration + ", shadow=" + this.shadow + ", platformStyle=null, drawStyle=" + this.drawStyle + ')';
    }

    public SpanStyle(TextForegroundStyle textForegroundStyle, long j, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j2, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j3, TextDecoration textDecoration, Shadow shadow) {
        this(textForegroundStyle, j, fontWeight, fontStyle, fontSynthesis, fontFamily, str, j2, baselineShift, textGeometricTransform, localeList, j3, textDecoration, shadow, (DrawStyle) null);
    }

    public SpanStyle(long j, long j2, FontWeight fontWeight, FontStyle fontStyle, FontSynthesis fontSynthesis, FontFamily fontFamily, String str, long j3, BaselineShift baselineShift, TextGeometricTransform textGeometricTransform, LocaleList localeList, long j4, TextDecoration textDecoration, Shadow shadow, int i) {
        this(TextForegroundStyle.Companion.m372from8_81llA((i & 1) != 0 ? Color.Unspecified : j), (i & 2) != 0 ? TextUnit.Unspecified : j2, (i & 4) != 0 ? null : fontWeight, (i & 8) != 0 ? null : fontStyle, (i & 16) != 0 ? null : fontSynthesis, (i & 32) != 0 ? null : fontFamily, (i & 64) != 0 ? null : str, (i & 128) != 0 ? TextUnit.Unspecified : j3, (i & 256) != 0 ? null : baselineShift, (i & 512) != 0 ? null : textGeometricTransform, (i & 1024) != 0 ? null : localeList, (i & 2048) != 0 ? Color.Unspecified : j4, (i & 4096) != 0 ? null : textDecoration, (i & 8192) != 0 ? null : shadow);
    }
}
