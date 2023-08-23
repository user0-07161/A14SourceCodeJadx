package androidx.compose.ui.text.platform;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TtsSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TtsAnnotation;
import androidx.compose.ui.text.UrlAnnotation;
import androidx.compose.ui.text.VerbatimTtsAnnotation;
import androidx.compose.ui.text.font.AndroidFontUtils_androidKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.ui.text.font.TypefaceResult;
import androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Density;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AndroidAccessibilitySpannableString_androidKt {
    public static final SpannableString toAccessibilitySpannableString(AnnotatedString annotatedString, Density density, FontFamilyResolverImpl fontFamilyResolver) {
        int i;
        int i2;
        TextDecoration textDecoration;
        TextDecoration textDecoration2;
        int i3;
        FontWeight fontWeight;
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(fontFamilyResolver, "fontFamilyResolver");
        SpannableString spannableString = new SpannableString(annotatedString.getText());
        List spanStylesOrNull$ui_text_release = annotatedString.getSpanStylesOrNull$ui_text_release();
        if (spanStylesOrNull$ui_text_release != null) {
            int size = spanStylesOrNull$ui_text_release.size();
            for (int i4 = 0; i4 < size; i4++) {
                AnnotatedString.Range range = (AnnotatedString.Range) spanStylesOrNull$ui_text_release.get(i4);
                int component2 = range.component2();
                int component3 = range.component3();
                SpanStyle m302copyIuqyXdg$default = SpanStyle.m302copyIuqyXdg$default((SpanStyle) range.component1());
                SpannableExtensions_androidKt.m345setColorRPmYEkk(spannableString, m302copyIuqyXdg$default.m305getColor0d7_KjU(), component2, component3);
                SpannableExtensions_androidKt.m346setFontSizeKmRG4DE(spannableString, m302copyIuqyXdg$default.m306getFontSizeXSAIIZE(), density, component2, component3);
                if (m302copyIuqyXdg$default.getFontWeight() == null && m302copyIuqyXdg$default.m307getFontStyle4Lr2A7w() == null) {
                    i2 = component3;
                } else {
                    FontWeight fontWeight2 = m302copyIuqyXdg$default.getFontWeight();
                    if (fontWeight2 == null) {
                        fontWeight2 = FontWeight.Normal;
                    }
                    FontStyle m307getFontStyle4Lr2A7w = m302copyIuqyXdg$default.m307getFontStyle4Lr2A7w();
                    if (m307getFontStyle4Lr2A7w != null) {
                        i = m307getFontStyle4Lr2A7w.m330unboximpl();
                    } else {
                        i = 0;
                    }
                    StyleSpan styleSpan = new StyleSpan(AndroidFontUtils_androidKt.m327getAndroidTypefaceStyleFO1MlWM(fontWeight2, i));
                    i2 = component3;
                    spannableString.setSpan(styleSpan, component2, i2, 33);
                }
                if (m302copyIuqyXdg$default.getFontFamily() != null) {
                    if (m302copyIuqyXdg$default.getFontFamily() instanceof GenericFontFamily) {
                        spannableString.setSpan(new TypefaceSpan(((GenericFontFamily) m302copyIuqyXdg$default.getFontFamily()).getName()), component2, i2, 33);
                    } else {
                        FontFamily fontFamily = m302copyIuqyXdg$default.getFontFamily();
                        FontSynthesis m308getFontSynthesisZQGJjVo = m302copyIuqyXdg$default.m308getFontSynthesisZQGJjVo();
                        if (m308getFontSynthesisZQGJjVo != null) {
                            i3 = m308getFontSynthesisZQGJjVo.m333unboximpl();
                        } else {
                            i3 = 1;
                        }
                        fontWeight = FontWeight.Normal;
                        Object value = ((TypefaceResult.Immutable) fontFamilyResolver.m328resolveDPcqOEQ(fontFamily, fontWeight, 0, i3)).getValue();
                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type android.graphics.Typeface");
                        spannableString.setSpan(new TypefaceSpan((Typeface) value), component2, i2, 33);
                    }
                }
                if (m302copyIuqyXdg$default.getTextDecoration() != null) {
                    TextDecoration textDecoration3 = m302copyIuqyXdg$default.getTextDecoration();
                    textDecoration = TextDecoration.Underline;
                    if (textDecoration3.contains(textDecoration)) {
                        spannableString.setSpan(new UnderlineSpan(), component2, i2, 33);
                    }
                    TextDecoration textDecoration4 = m302copyIuqyXdg$default.getTextDecoration();
                    textDecoration2 = TextDecoration.LineThrough;
                    if (textDecoration4.contains(textDecoration2)) {
                        spannableString.setSpan(new StrikethroughSpan(), component2, i2, 33);
                    }
                }
                if (m302copyIuqyXdg$default.getTextGeometricTransform() != null) {
                    spannableString.setSpan(new ScaleXSpan(m302copyIuqyXdg$default.getTextGeometricTransform().getScaleX()), component2, i2, 33);
                }
                SpannableExtensions_androidKt.setLocaleList(spannableString, m302copyIuqyXdg$default.getLocaleList(), component2, i2);
                SpannableExtensions_androidKt.m344setBackgroundRPmYEkk(spannableString, m302copyIuqyXdg$default.m303getBackground0d7_KjU(), component2, i2);
            }
        }
        List ttsAnnotations = annotatedString.getTtsAnnotations(annotatedString.length());
        int size2 = ttsAnnotations.size();
        for (int i5 = 0; i5 < size2; i5++) {
            AnnotatedString.Range range2 = (AnnotatedString.Range) ttsAnnotations.get(i5);
            TtsAnnotation ttsAnnotation = (TtsAnnotation) range2.component1();
            int component22 = range2.component2();
            int component32 = range2.component3();
            Intrinsics.checkNotNullParameter(ttsAnnotation, "<this>");
            if (ttsAnnotation instanceof VerbatimTtsAnnotation) {
                TtsSpan build = new TtsSpan.VerbatimBuilder(((VerbatimTtsAnnotation) ttsAnnotation).getVerbatim()).build();
                Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
                spannableString.setSpan(build, component22, component32, 33);
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        List urlAnnotations = annotatedString.getUrlAnnotations(annotatedString.length());
        int size3 = urlAnnotations.size();
        for (int i6 = 0; i6 < size3; i6++) {
            AnnotatedString.Range range3 = (AnnotatedString.Range) urlAnnotations.get(i6);
            UrlAnnotation urlAnnotation = (UrlAnnotation) range3.component1();
            int component23 = range3.component2();
            int component33 = range3.component3();
            Intrinsics.checkNotNullParameter(urlAnnotation, "<this>");
            spannableString.setSpan(new URLSpan(urlAnnotation.getUrl()), component23, component33, 33);
        }
        return spannableString;
    }
}
