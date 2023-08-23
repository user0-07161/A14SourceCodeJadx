package androidx.compose.ui.text;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.intl.PlatformLocaleKt;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SpanStyleKt {
    private static final long DefaultBackgroundColor;
    private static final long DefaultColor;
    private static final long DefaultFontSize = TextUnitKt.getSp(14);
    private static final long DefaultLetterSpacing = TextUnitKt.getSp(0);

    static {
        long j;
        long j2;
        Color.Companion companion = Color.Companion;
        j = Color.Transparent;
        DefaultBackgroundColor = j;
        j2 = Color.Black;
        DefaultColor = j2;
    }

    public static final SpanStyle resolveSpanStyleDefaults(SpanStyle style) {
        long m306getFontSizeXSAIIZE;
        int i;
        int i2;
        long m309getLetterSpacingXSAIIZE;
        float f;
        long j;
        Intrinsics.checkNotNullParameter(style, "style");
        TextForegroundStyle textForegroundStyle$ui_text_release = style.getTextForegroundStyle$ui_text_release();
        SpanStyleKt$resolveSpanStyleDefaults$1 other = SpanStyleKt$resolveSpanStyleDefaults$1.INSTANCE;
        textForegroundStyle$ui_text_release.getClass();
        Intrinsics.checkNotNullParameter(other, "other");
        if (Intrinsics.areEqual(textForegroundStyle$ui_text_release, TextForegroundStyle.Unspecified.INSTANCE)) {
            textForegroundStyle$ui_text_release = (TextForegroundStyle) other.invoke();
        }
        TextForegroundStyle textForegroundStyle = textForegroundStyle$ui_text_release;
        if (TextUnitKt.m415isUnspecifiedR2X_6o(style.m306getFontSizeXSAIIZE())) {
            m306getFontSizeXSAIIZE = DefaultFontSize;
        } else {
            m306getFontSizeXSAIIZE = style.m306getFontSizeXSAIIZE();
        }
        FontWeight fontWeight = style.getFontWeight();
        if (fontWeight == null) {
            fontWeight = FontWeight.Normal;
        }
        FontWeight fontWeight2 = fontWeight;
        FontStyle m307getFontStyle4Lr2A7w = style.m307getFontStyle4Lr2A7w();
        boolean z = false;
        if (m307getFontStyle4Lr2A7w != null) {
            i = m307getFontStyle4Lr2A7w.m330unboximpl();
        } else {
            i = 0;
        }
        FontStyle m329boximpl = FontStyle.m329boximpl(i);
        FontSynthesis m308getFontSynthesisZQGJjVo = style.m308getFontSynthesisZQGJjVo();
        if (m308getFontSynthesisZQGJjVo != null) {
            i2 = m308getFontSynthesisZQGJjVo.m333unboximpl();
        } else {
            i2 = 1;
        }
        FontSynthesis m331boximpl = FontSynthesis.m331boximpl(i2);
        FontFamily fontFamily = style.getFontFamily();
        if (fontFamily == null) {
            fontFamily = FontFamily.Default;
        }
        String fontFeatureSettings = style.getFontFeatureSettings();
        if (fontFeatureSettings == null) {
            fontFeatureSettings = "";
        }
        if (TextUnitKt.m415isUnspecifiedR2X_6o(style.m309getLetterSpacingXSAIIZE())) {
            m309getLetterSpacingXSAIIZE = DefaultLetterSpacing;
        } else {
            m309getLetterSpacingXSAIIZE = style.m309getLetterSpacingXSAIIZE();
        }
        BaselineShift m304getBaselineShift5SSeXJ0 = style.m304getBaselineShift5SSeXJ0();
        if (m304getBaselineShift5SSeXJ0 != null) {
            f = m304getBaselineShift5SSeXJ0.m351unboximpl();
        } else {
            f = 0.0f;
        }
        BaselineShift m350boximpl = BaselineShift.m350boximpl(f);
        TextGeometricTransform textGeometricTransform = style.getTextGeometricTransform();
        if (textGeometricTransform == null) {
            textGeometricTransform = TextGeometricTransform.None;
        }
        LocaleList localeList = style.getLocaleList();
        if (localeList == null) {
            localeList = PlatformLocaleKt.getPlatformLocaleDelegate().getCurrent();
        }
        long m303getBackground0d7_KjU = style.m303getBackground0d7_KjU();
        j = Color.Unspecified;
        if (m303getBackground0d7_KjU != j) {
            z = true;
        }
        if (!z) {
            m303getBackground0d7_KjU = DefaultBackgroundColor;
        }
        TextDecoration textDecoration = style.getTextDecoration();
        if (textDecoration == null) {
            textDecoration = TextDecoration.None;
        }
        TextDecoration textDecoration2 = textDecoration;
        Shadow shadow = style.getShadow();
        if (shadow == null) {
            shadow = Shadow.None;
        }
        Shadow shadow2 = shadow;
        DrawStyle drawStyle = style.getDrawStyle();
        if (drawStyle == null) {
            drawStyle = Fill.INSTANCE;
        }
        return new SpanStyle(textForegroundStyle, m306getFontSizeXSAIIZE, fontWeight2, m329boximpl, m331boximpl, fontFamily, fontFeatureSettings, m309getLetterSpacingXSAIIZE, m350boximpl, textGeometricTransform, localeList, m303getBackground0d7_KjU, textDecoration2, shadow2, drawStyle);
    }
}
