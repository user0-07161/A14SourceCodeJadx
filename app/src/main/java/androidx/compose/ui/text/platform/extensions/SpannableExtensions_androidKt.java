package androidx.compose.ui.text.platform.extensions;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LocaleSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt$ShaderBrush$1;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.AnnotatedStringKt;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.android.style.BaselineShiftSpan;
import androidx.compose.ui.text.android.style.FontFeatureSpan;
import androidx.compose.ui.text.android.style.LetterSpacingSpanEm;
import androidx.compose.ui.text.android.style.LetterSpacingSpanPx;
import androidx.compose.ui.text.android.style.LineHeightSpan;
import androidx.compose.ui.text.android.style.LineHeightStyleSpan;
import androidx.compose.ui.text.android.style.ShadowSpan;
import androidx.compose.ui.text.android.style.SkewXSpan;
import androidx.compose.ui.text.android.style.TextDecorationSpan;
import androidx.compose.ui.text.android.style.TypefaceSpan;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.AndroidLocale;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.platform.style.DrawStyleSpan;
import androidx.compose.ui.text.platform.style.ShaderBrushSpan;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SpannableExtensions_androidKt {
    /* renamed from: resolveLineHeightInPx-o2QH7mI  reason: not valid java name */
    private static final float m343resolveLineHeightInPxo2QH7mI(long j, float f, Density density) {
        long m411getTypeUIouoOA = TextUnit.m411getTypeUIouoOA(j);
        if (TextUnitType.m417equalsimpl0(m411getTypeUIouoOA, 4294967296L)) {
            return density.mo205toPxR2X_6o(j);
        }
        if (TextUnitType.m417equalsimpl0(m411getTypeUIouoOA, 8589934592L)) {
            return TextUnit.m412getValueimpl(j) * f;
        }
        return Float.NaN;
    }

    /* renamed from: setBackground-RPmYEkk  reason: not valid java name */
    public static final void m344setBackgroundRPmYEkk(Spannable spannable, long j, int i, int i2) {
        long j2;
        boolean z;
        j2 = Color.Unspecified;
        if (j != j2) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            setSpan(spannable, new BackgroundColorSpan(ColorKt.m100toArgb8_81llA(j)), i, i2);
        }
    }

    /* renamed from: setColor-RPmYEkk  reason: not valid java name */
    public static final void m345setColorRPmYEkk(Spannable spannable, long j, int i, int i2) {
        long j2;
        boolean z;
        j2 = Color.Unspecified;
        if (j != j2) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            setSpan(spannable, new ForegroundColorSpan(ColorKt.m100toArgb8_81llA(j)), i, i2);
        }
    }

    /* renamed from: setFontSize-KmRG4DE  reason: not valid java name */
    public static final void m346setFontSizeKmRG4DE(Spannable spannable, long j, Density density, int i, int i2) {
        Intrinsics.checkNotNullParameter(density, "density");
        long m411getTypeUIouoOA = TextUnit.m411getTypeUIouoOA(j);
        if (TextUnitType.m417equalsimpl0(m411getTypeUIouoOA, 4294967296L)) {
            setSpan(spannable, new AbsoluteSizeSpan(MathKt.roundToInt(density.mo205toPxR2X_6o(j)), false), i, i2);
        } else if (TextUnitType.m417equalsimpl0(m411getTypeUIouoOA, 8589934592L)) {
            setSpan(spannable, new RelativeSizeSpan(TextUnit.m412getValueimpl(j)), i, i2);
        }
    }

    /* renamed from: setLineHeight-KmRG4DE  reason: not valid java name */
    public static final void m347setLineHeightKmRG4DE(Spannable spannable, long j, float f, Density density, LineHeightStyle lineHeightStyle) {
        boolean z;
        int length;
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(lineHeightStyle, "lineHeightStyle");
        float m343resolveLineHeightInPxo2QH7mI = m343resolveLineHeightInPxo2QH7mI(j, f, density);
        if (!Float.isNaN(m343resolveLineHeightInPxo2QH7mI)) {
            if (spannable.length() == 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z && StringsKt.last(spannable) != '\n') {
                length = spannable.length();
            } else {
                length = spannable.length() + 1;
            }
            setSpan(spannable, new LineHeightStyleSpan(m343resolveLineHeightInPxo2QH7mI, length, true, true, lineHeightStyle.m365getAlignmentPIaL0Z0()), 0, spannable.length());
        }
    }

    /* renamed from: setLineHeight-r9BaKPg  reason: not valid java name */
    public static final void m348setLineHeightr9BaKPg(Spannable spannable, long j, float f, Density density) {
        Intrinsics.checkNotNullParameter(density, "density");
        float m343resolveLineHeightInPxo2QH7mI = m343resolveLineHeightInPxo2QH7mI(j, f, density);
        if (!Float.isNaN(m343resolveLineHeightInPxo2QH7mI)) {
            setSpan(spannable, new LineHeightSpan(m343resolveLineHeightInPxo2QH7mI), 0, spannable.length());
        }
    }

    public static final void setLocaleList(Spannable spannable, LocaleList localeList, int i, int i2) {
        if (localeList != null) {
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(localeList));
            Iterator it = localeList.iterator();
            while (it.hasNext()) {
                Locale locale = (Locale) it.next();
                Intrinsics.checkNotNullParameter(locale, "<this>");
                AndroidLocale platformLocale$ui_text_release = locale.getPlatformLocale$ui_text_release();
                Intrinsics.checkNotNull(platformLocale$ui_text_release, "null cannot be cast to non-null type androidx.compose.ui.text.intl.AndroidLocale");
                arrayList.add(platformLocale$ui_text_release.getJavaLocale());
            }
            java.util.Locale[] localeArr = (java.util.Locale[]) arrayList.toArray(new java.util.Locale[0]);
            setSpan(spannable, new LocaleSpan(new android.os.LocaleList((java.util.Locale[]) Arrays.copyOf(localeArr, localeArr.length))), i, i2);
        }
    }

    public static final void setSpan(Spannable spannable, Object span, int i, int i2) {
        Intrinsics.checkNotNullParameter(spannable, "<this>");
        Intrinsics.checkNotNullParameter(span, "span");
        spannable.setSpan(span, i, i2, 33);
    }

    public static final void setSpanStyles(final Spannable spannable, TextStyle contextTextStyle, List spanStyles, Density density, final Function4 function4) {
        boolean z;
        SpanStyle spanStyle;
        boolean z2;
        Object obj;
        int i;
        boolean z3;
        boolean z4;
        TextDecoration textDecoration;
        TextDecoration textDecoration2;
        Intrinsics.checkNotNullParameter(contextTextStyle, "contextTextStyle");
        Intrinsics.checkNotNullParameter(spanStyles, "spanStyles");
        Intrinsics.checkNotNullParameter(density, "density");
        ArrayList arrayList = new ArrayList(spanStyles.size());
        int size = spanStyles.size();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            boolean z5 = true;
            if (i3 >= size) {
                break;
            }
            Object obj2 = spanStyles.get(i3);
            AnnotatedString.Range range = (AnnotatedString.Range) obj2;
            if (!TextPaintExtensions_androidKt.hasFontAttributes((SpanStyle) range.getItem()) && ((SpanStyle) range.getItem()).m308getFontSynthesisZQGJjVo() == null) {
                z5 = false;
            }
            if (z5) {
                arrayList.add(obj2);
            }
            i3++;
        }
        if (!TextPaintExtensions_androidKt.hasFontAttributes(contextTextStyle.toSpanStyle()) && contextTextStyle.m321getFontSynthesisZQGJjVo() == null) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            spanStyle = new SpanStyle(0L, 0L, contextTextStyle.getFontWeight(), contextTextStyle.m320getFontStyle4Lr2A7w(), contextTextStyle.m321getFontSynthesisZQGJjVo(), contextTextStyle.getFontFamily(), (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, 16323);
        } else {
            spanStyle = null;
        }
        Function3 function3 = new Function3() { // from class: androidx.compose.ui.text.platform.extensions.SpannableExtensions_androidKt$setFontAttributes$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj3, Object obj4, Object obj5) {
                int i4;
                int i5;
                SpanStyle spanStyle2 = (SpanStyle) obj3;
                int intValue = ((Number) obj4).intValue();
                int intValue2 = ((Number) obj5).intValue();
                Intrinsics.checkNotNullParameter(spanStyle2, "spanStyle");
                Spannable spannable2 = spannable;
                Function4 function42 = function4;
                FontFamily fontFamily = spanStyle2.getFontFamily();
                FontWeight fontWeight = spanStyle2.getFontWeight();
                if (fontWeight == null) {
                    fontWeight = FontWeight.Normal;
                }
                FontStyle m307getFontStyle4Lr2A7w = spanStyle2.m307getFontStyle4Lr2A7w();
                if (m307getFontStyle4Lr2A7w != null) {
                    i4 = m307getFontStyle4Lr2A7w.m330unboximpl();
                } else {
                    i4 = 0;
                }
                FontStyle m329boximpl = FontStyle.m329boximpl(i4);
                FontSynthesis m308getFontSynthesisZQGJjVo = spanStyle2.m308getFontSynthesisZQGJjVo();
                if (m308getFontSynthesisZQGJjVo != null) {
                    i5 = m308getFontSynthesisZQGJjVo.m333unboximpl();
                } else {
                    i5 = 1;
                }
                spannable2.setSpan(new TypefaceSpan((Typeface) function42.invoke(fontFamily, fontWeight, m329boximpl, FontSynthesis.m331boximpl(i5))), intValue, intValue2, 33);
                return Unit.INSTANCE;
            }
        };
        if (arrayList.size() <= 1) {
            if (!arrayList.isEmpty()) {
                SpanStyle spanStyle2 = (SpanStyle) ((AnnotatedString.Range) arrayList.get(0)).getItem();
                if (spanStyle != null) {
                    spanStyle2 = spanStyle.merge(spanStyle2);
                }
                function3.invoke(spanStyle2, Integer.valueOf(((AnnotatedString.Range) arrayList.get(0)).getStart()), Integer.valueOf(((AnnotatedString.Range) arrayList.get(0)).getEnd()));
            }
        } else {
            int size2 = arrayList.size();
            int i4 = size2 * 2;
            Integer[] numArr = new Integer[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                numArr[i5] = 0;
            }
            int size3 = arrayList.size();
            for (int i6 = 0; i6 < size3; i6++) {
                AnnotatedString.Range range2 = (AnnotatedString.Range) arrayList.get(i6);
                numArr[i6] = Integer.valueOf(range2.getStart());
                numArr[i6 + size2] = Integer.valueOf(range2.getEnd());
            }
            Integer[] numArr2 = numArr;
            if (numArr2.length > 1) {
                Arrays.sort(numArr2);
            }
            if (i4 == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                int intValue = numArr[0].intValue();
                int i7 = 0;
                while (i7 < i4) {
                    int intValue2 = numArr[i7].intValue();
                    if (intValue2 != intValue) {
                        int size4 = arrayList.size();
                        SpanStyle spanStyle3 = spanStyle;
                        for (int i8 = i2; i8 < size4; i8++) {
                            AnnotatedString.Range range3 = (AnnotatedString.Range) arrayList.get(i8);
                            if (range3.getStart() != range3.getEnd() && AnnotatedStringKt.intersect(intValue, intValue2, range3.getStart(), range3.getEnd())) {
                                SpanStyle spanStyle4 = (SpanStyle) range3.getItem();
                                if (spanStyle3 == null) {
                                    spanStyle3 = spanStyle4;
                                } else {
                                    spanStyle3 = spanStyle3.merge(spanStyle4);
                                }
                            }
                        }
                        if (spanStyle3 != null) {
                            function3.invoke(spanStyle3, Integer.valueOf(intValue), Integer.valueOf(intValue2));
                        }
                        intValue = intValue2;
                    }
                    i7++;
                    i2 = 0;
                }
            } else {
                throw new NoSuchElementException("Array is empty.");
            }
        }
        int size5 = spanStyles.size();
        boolean z6 = false;
        for (int i9 = 0; i9 < size5; i9++) {
            AnnotatedString.Range range4 = (AnnotatedString.Range) spanStyles.get(i9);
            int start = range4.getStart();
            int end = range4.getEnd();
            if (start >= 0 && start < spannable.length() && end > start && end <= spannable.length()) {
                int start2 = range4.getStart();
                int end2 = range4.getEnd();
                SpanStyle spanStyle5 = (SpanStyle) range4.getItem();
                BaselineShift m304getBaselineShift5SSeXJ0 = spanStyle5.m304getBaselineShift5SSeXJ0();
                if (m304getBaselineShift5SSeXJ0 != null) {
                    setSpan(spannable, new BaselineShiftSpan(m304getBaselineShift5SSeXJ0.m351unboximpl()), start2, end2);
                }
                m345setColorRPmYEkk(spannable, spanStyle5.m305getColor0d7_KjU(), start2, end2);
                Brush brush = spanStyle5.getBrush();
                float alpha = spanStyle5.getAlpha();
                if (brush != null && (brush instanceof BrushKt$ShaderBrush$1)) {
                    setSpan(spannable, new ShaderBrushSpan((BrushKt$ShaderBrush$1) brush, alpha), start2, end2);
                }
                TextDecoration textDecoration3 = spanStyle5.getTextDecoration();
                if (textDecoration3 != null) {
                    textDecoration = TextDecoration.Underline;
                    boolean contains = textDecoration3.contains(textDecoration);
                    textDecoration2 = TextDecoration.LineThrough;
                    setSpan(spannable, new TextDecorationSpan(contains, textDecoration3.contains(textDecoration2)), start2, end2);
                }
                m346setFontSizeKmRG4DE(spannable, spanStyle5.m306getFontSizeXSAIIZE(), density, start2, end2);
                String fontFeatureSettings = spanStyle5.getFontFeatureSettings();
                if (fontFeatureSettings != null) {
                    FontFeatureSpan fontFeatureSpan = new FontFeatureSpan(fontFeatureSettings);
                    i = end2;
                    setSpan(spannable, fontFeatureSpan, start2, i);
                } else {
                    i = end2;
                }
                TextGeometricTransform textGeometricTransform = spanStyle5.getTextGeometricTransform();
                if (textGeometricTransform != null) {
                    setSpan(spannable, new ScaleXSpan(textGeometricTransform.getScaleX()), start2, i);
                    setSpan(spannable, new SkewXSpan(textGeometricTransform.getSkewX()), start2, i);
                }
                setLocaleList(spannable, spanStyle5.getLocaleList(), start2, i);
                m344setBackgroundRPmYEkk(spannable, spanStyle5.m303getBackground0d7_KjU(), start2, i);
                Shadow shadow = spanStyle5.getShadow();
                if (shadow != null) {
                    int m100toArgb8_81llA = ColorKt.m100toArgb8_81llA(shadow.m115getColor0d7_KjU());
                    float m45getXimpl = Offset.m45getXimpl(shadow.m116getOffsetF1C5BW0());
                    float m46getYimpl = Offset.m46getYimpl(shadow.m116getOffsetF1C5BW0());
                    float blurRadius = shadow.getBlurRadius();
                    if (blurRadius == 0.0f) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (z4) {
                        blurRadius = Float.MIN_VALUE;
                    }
                    setSpan(spannable, new ShadowSpan(m100toArgb8_81llA, m45getXimpl, m46getYimpl, blurRadius), start2, i);
                }
                DrawStyle drawStyle = spanStyle5.getDrawStyle();
                if (drawStyle != null) {
                    setSpan(spannable, new DrawStyleSpan(drawStyle), start2, i);
                }
                SpanStyle spanStyle6 = (SpanStyle) range4.getItem();
                if (!TextUnitType.m417equalsimpl0(TextUnit.m411getTypeUIouoOA(spanStyle6.m309getLetterSpacingXSAIIZE()), 4294967296L) && !TextUnitType.m417equalsimpl0(TextUnit.m411getTypeUIouoOA(spanStyle6.m309getLetterSpacingXSAIIZE()), 8589934592L)) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (z3) {
                    z6 = true;
                }
            }
        }
        if (z6) {
            int size6 = spanStyles.size();
            for (int i10 = 0; i10 < size6; i10++) {
                AnnotatedString.Range range5 = (AnnotatedString.Range) spanStyles.get(i10);
                int start3 = range5.getStart();
                int end3 = range5.getEnd();
                SpanStyle spanStyle7 = (SpanStyle) range5.getItem();
                if (start3 >= 0 && start3 < spannable.length() && end3 > start3 && end3 <= spannable.length()) {
                    long m309getLetterSpacingXSAIIZE = spanStyle7.m309getLetterSpacingXSAIIZE();
                    long m411getTypeUIouoOA = TextUnit.m411getTypeUIouoOA(m309getLetterSpacingXSAIIZE);
                    if (TextUnitType.m417equalsimpl0(m411getTypeUIouoOA, 4294967296L)) {
                        obj = new LetterSpacingSpanPx(density.mo205toPxR2X_6o(m309getLetterSpacingXSAIIZE));
                    } else if (TextUnitType.m417equalsimpl0(m411getTypeUIouoOA, 8589934592L)) {
                        obj = new LetterSpacingSpanEm(TextUnit.m412getValueimpl(m309getLetterSpacingXSAIIZE));
                    } else {
                        obj = null;
                    }
                    if (obj != null) {
                        setSpan(spannable, obj, start3, end3);
                    }
                }
            }
        }
    }
}
