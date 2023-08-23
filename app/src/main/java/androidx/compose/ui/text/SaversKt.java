package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverKt$Saver$1;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.AndroidLocale;
import androidx.compose.ui.text.intl.Locale;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.intl.PlatformLocaleKt;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.unit.TextUnitType;
import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ULong;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SaversKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final SaverKt$Saver$1 AnnotatedStringSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$AnnotatedStringSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverKt$Saver$1 saverKt$Saver$1;
            SaverKt$Saver$1 saverKt$Saver$12;
            SaverKt$Saver$1 saverKt$Saver$13;
            SaverScope Saver = (SaverScope) obj;
            AnnotatedString it = (AnnotatedString) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            String text = it.getText();
            int i = SaversKt.$r8$clinit;
            List spanStyles = it.getSpanStyles();
            saverKt$Saver$1 = SaversKt.AnnotationRangeListSaver;
            Object save = SaversKt.save(spanStyles, saverKt$Saver$1, Saver);
            List paragraphStyles = it.getParagraphStyles();
            saverKt$Saver$12 = SaversKt.AnnotationRangeListSaver;
            Object save2 = SaversKt.save(paragraphStyles, saverKt$Saver$12, Saver);
            List annotations$ui_text_release = it.getAnnotations$ui_text_release();
            saverKt$Saver$13 = SaversKt.AnnotationRangeListSaver;
            return CollectionsKt.arrayListOf(text, save, save2, SaversKt.save(annotations$ui_text_release, saverKt$Saver$13, Saver));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$AnnotatedStringSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            SaverKt$Saver$1 saverKt$Saver$1;
            List list;
            SaverKt$Saver$1 saverKt$Saver$12;
            List list2;
            String str;
            SaverKt$Saver$1 saverKt$Saver$13;
            Intrinsics.checkNotNullParameter(it, "it");
            List list3 = (List) it;
            Object obj = list3.get(1);
            saverKt$Saver$1 = SaversKt.AnnotationRangeListSaver;
            Boolean bool = Boolean.FALSE;
            List list4 = null;
            if (!Intrinsics.areEqual(obj, bool) && obj != null) {
                list = (List) saverKt$Saver$1.restore(obj);
            } else {
                list = null;
            }
            Object obj2 = list3.get(2);
            saverKt$Saver$12 = SaversKt.AnnotationRangeListSaver;
            if (!Intrinsics.areEqual(obj2, bool) && obj2 != null) {
                list2 = (List) saverKt$Saver$12.restore(obj2);
            } else {
                list2 = null;
            }
            Object obj3 = list3.get(0);
            if (obj3 != null) {
                str = (String) obj3;
            } else {
                str = null;
            }
            Intrinsics.checkNotNull(str);
            if (list == null || list.isEmpty()) {
                list = null;
            }
            if (list2 == null || list2.isEmpty()) {
                list2 = null;
            }
            Object obj4 = list3.get(3);
            saverKt$Saver$13 = SaversKt.AnnotationRangeListSaver;
            if (!Intrinsics.areEqual(obj4, bool) && obj4 != null) {
                list4 = (List) saverKt$Saver$13.restore(obj4);
            }
            return new AnnotatedString(str, list, list2, list4);
        }
    });
    private static final SaverKt$Saver$1 AnnotationRangeListSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeListSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverKt$Saver$1 saverKt$Saver$1;
            SaverScope Saver = (SaverScope) obj;
            List it = (List) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            ArrayList arrayList = new ArrayList(it.size());
            int size = it.size();
            for (int i = 0; i < size; i++) {
                saverKt$Saver$1 = SaversKt.AnnotationRangeSaver;
                arrayList.add(SaversKt.save((AnnotatedString.Range) it.get(i), saverKt$Saver$1, Saver));
            }
            return arrayList;
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeListSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            SaverKt$Saver$1 saverKt$Saver$1;
            AnnotatedString.Range range;
            Intrinsics.checkNotNullParameter(it, "it");
            List list = (List) it;
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj = list.get(i);
                saverKt$Saver$1 = SaversKt.AnnotationRangeSaver;
                if (!Intrinsics.areEqual(obj, Boolean.FALSE) && obj != null) {
                    range = (AnnotatedString.Range) saverKt$Saver$1.restore(obj);
                } else {
                    range = null;
                }
                Intrinsics.checkNotNull(range);
                arrayList.add(range);
            }
            return arrayList;
        }
    });
    private static final SaverKt$Saver$1 AnnotationRangeSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnnotationType annotationType;
            Object save;
            SaverKt$Saver$1 saverKt$Saver$1;
            SaverKt$Saver$1 saverKt$Saver$12;
            SaverScope Saver = (SaverScope) obj;
            AnnotatedString.Range it = (AnnotatedString.Range) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            Object item = it.getItem();
            if (item instanceof ParagraphStyle) {
                annotationType = AnnotationType.Paragraph;
            } else if (item instanceof SpanStyle) {
                annotationType = AnnotationType.Span;
            } else if (item instanceof VerbatimTtsAnnotation) {
                annotationType = AnnotationType.VerbatimTts;
            } else if (item instanceof UrlAnnotation) {
                annotationType = AnnotationType.Url;
            } else {
                annotationType = AnnotationType.String;
            }
            int ordinal = annotationType.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal != 3) {
                            if (ordinal == 4) {
                                save = it.getItem();
                                int i = SaversKt.$r8$clinit;
                            } else {
                                throw new NoWhenBranchMatchedException();
                            }
                        } else {
                            Object item2 = it.getItem();
                            Intrinsics.checkNotNull(item2, "null cannot be cast to non-null type androidx.compose.ui.text.UrlAnnotation");
                            saverKt$Saver$12 = SaversKt.UrlAnnotationSaver;
                            save = SaversKt.save((UrlAnnotation) item2, saverKt$Saver$12, Saver);
                        }
                    } else {
                        Object item3 = it.getItem();
                        Intrinsics.checkNotNull(item3, "null cannot be cast to non-null type androidx.compose.ui.text.VerbatimTtsAnnotation");
                        saverKt$Saver$1 = SaversKt.VerbatimTtsAnnotationSaver;
                        save = SaversKt.save((VerbatimTtsAnnotation) item3, saverKt$Saver$1, Saver);
                    }
                } else {
                    Object item4 = it.getItem();
                    Intrinsics.checkNotNull(item4, "null cannot be cast to non-null type androidx.compose.ui.text.SpanStyle");
                    save = SaversKt.save((SpanStyle) item4, SaversKt.getSpanStyleSaver(), Saver);
                }
            } else {
                Object item5 = it.getItem();
                Intrinsics.checkNotNull(item5, "null cannot be cast to non-null type androidx.compose.ui.text.ParagraphStyle");
                save = SaversKt.save((ParagraphStyle) item5, SaversKt.getParagraphStyleSaver(), Saver);
            }
            return CollectionsKt.arrayListOf(annotationType, save, Integer.valueOf(it.getStart()), Integer.valueOf(it.getEnd()), it.getTag());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$AnnotationRangeSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            AnnotationType annotationType;
            Integer num;
            Integer num2;
            String str;
            SaverKt$Saver$1 saverKt$Saver$1;
            SaverKt$Saver$1 saverKt$Saver$12;
            Intrinsics.checkNotNullParameter(it, "it");
            List list = (List) it;
            Object obj = list.get(0);
            Object obj2 = null;
            if (obj != null) {
                annotationType = (AnnotationType) obj;
            } else {
                annotationType = null;
            }
            Intrinsics.checkNotNull(annotationType);
            Object obj3 = list.get(2);
            if (obj3 != null) {
                num = (Integer) obj3;
            } else {
                num = null;
            }
            Intrinsics.checkNotNull(num);
            int intValue = num.intValue();
            Object obj4 = list.get(3);
            if (obj4 != null) {
                num2 = (Integer) obj4;
            } else {
                num2 = null;
            }
            Intrinsics.checkNotNull(num2);
            int intValue2 = num2.intValue();
            Object obj5 = list.get(4);
            if (obj5 != null) {
                str = (String) obj5;
            } else {
                str = null;
            }
            Intrinsics.checkNotNull(str);
            int ordinal = annotationType.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal != 3) {
                            if (ordinal == 4) {
                                Object obj6 = list.get(1);
                                if (obj6 != null) {
                                    obj2 = (String) obj6;
                                }
                                Intrinsics.checkNotNull(obj2);
                                return new AnnotatedString.Range(obj2, intValue, intValue2, str);
                            }
                            throw new NoWhenBranchMatchedException();
                        }
                        Object obj7 = list.get(1);
                        saverKt$Saver$12 = SaversKt.UrlAnnotationSaver;
                        if (!Intrinsics.areEqual(obj7, Boolean.FALSE) && obj7 != null) {
                            obj2 = (UrlAnnotation) saverKt$Saver$12.restore(obj7);
                        }
                        Intrinsics.checkNotNull(obj2);
                        return new AnnotatedString.Range(obj2, intValue, intValue2, str);
                    }
                    Object obj8 = list.get(1);
                    saverKt$Saver$1 = SaversKt.VerbatimTtsAnnotationSaver;
                    if (!Intrinsics.areEqual(obj8, Boolean.FALSE) && obj8 != null) {
                        obj2 = (VerbatimTtsAnnotation) saverKt$Saver$1.restore(obj8);
                    }
                    Intrinsics.checkNotNull(obj2);
                    return new AnnotatedString.Range(obj2, intValue, intValue2, str);
                }
                Object obj9 = list.get(1);
                SaverKt$Saver$1 spanStyleSaver = SaversKt.getSpanStyleSaver();
                if (!Intrinsics.areEqual(obj9, Boolean.FALSE) && obj9 != null) {
                    obj2 = (SpanStyle) spanStyleSaver.restore(obj9);
                }
                Intrinsics.checkNotNull(obj2);
                return new AnnotatedString.Range(obj2, intValue, intValue2, str);
            }
            Object obj10 = list.get(1);
            SaverKt$Saver$1 paragraphStyleSaver = SaversKt.getParagraphStyleSaver();
            if (!Intrinsics.areEqual(obj10, Boolean.FALSE) && obj10 != null) {
                obj2 = (ParagraphStyle) paragraphStyleSaver.restore(obj10);
            }
            Intrinsics.checkNotNull(obj2);
            return new AnnotatedString.Range(obj2, intValue, intValue2, str);
        }
    });
    private static final SaverKt$Saver$1 VerbatimTtsAnnotationSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$VerbatimTtsAnnotationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            VerbatimTtsAnnotation it = (VerbatimTtsAnnotation) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            String verbatim = it.getVerbatim();
            int i = SaversKt.$r8$clinit;
            return verbatim;
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$VerbatimTtsAnnotationSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return new VerbatimTtsAnnotation((String) it);
        }
    });
    private static final SaverKt$Saver$1 UrlAnnotationSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$UrlAnnotationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            UrlAnnotation it = (UrlAnnotation) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            String url = it.getUrl();
            int i = SaversKt.$r8$clinit;
            return url;
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$UrlAnnotationSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return new UrlAnnotation((String) it);
        }
    });
    private static final SaverKt$Saver$1 ParagraphStyleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$ParagraphStyleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            ParagraphStyle it = (ParagraphStyle) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            TextAlign m300getTextAlignbuA522U = it.m300getTextAlignbuA522U();
            int i = SaversKt.$r8$clinit;
            TextDirection m301getTextDirectionmmuk1to = it.m301getTextDirectionmmuk1to();
            Object save = SaversKt.save(TextUnit.m409boximpl(it.m299getLineHeightXSAIIZE()), SaversKt.getSaver$8(), Saver);
            TextIndent textIndent = it.getTextIndent();
            TextIndent.Companion companion = TextIndent.Companion;
            return CollectionsKt.arrayListOf(m300getTextAlignbuA522U, m301getTextDirectionmmuk1to, save, SaversKt.save(textIndent, SaversKt.getSaver$2(), Saver));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$ParagraphStyleSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            TextAlign textAlign;
            TextDirection textDirection;
            TextUnit textUnit;
            TextIndent textIndent;
            Intrinsics.checkNotNullParameter(it, "it");
            List list = (List) it;
            Object obj = list.get(0);
            if (obj != null) {
                textAlign = (TextAlign) obj;
            } else {
                textAlign = null;
            }
            Object obj2 = list.get(1);
            if (obj2 != null) {
                textDirection = (TextDirection) obj2;
            } else {
                textDirection = null;
            }
            Object obj3 = list.get(2);
            TextUnit.Companion companion = TextUnit.Companion;
            SaverKt$Saver$1 saver$8 = SaversKt.getSaver$8();
            Boolean bool = Boolean.FALSE;
            if (!Intrinsics.areEqual(obj3, bool) && obj3 != null) {
                textUnit = (TextUnit) saver$8.restore(obj3);
            } else {
                textUnit = null;
            }
            Intrinsics.checkNotNull(textUnit);
            long m414unboximpl = textUnit.m414unboximpl();
            Object obj4 = list.get(3);
            TextIndent.Companion companion2 = TextIndent.Companion;
            SaverKt$Saver$1 saver$2 = SaversKt.getSaver$2();
            if (!Intrinsics.areEqual(obj4, bool) && obj4 != null) {
                textIndent = (TextIndent) saver$2.restore(obj4);
            } else {
                textIndent = null;
            }
            return new ParagraphStyle(textAlign, textDirection, m414unboximpl, textIndent, null, null, null);
        }
    });
    private static final SaverKt$Saver$1 SpanStyleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$SpanStyleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverKt$Saver$1 saverKt$Saver$1;
            SaverKt$Saver$1 saverKt$Saver$12;
            SaverKt$Saver$1 saverKt$Saver$13;
            SaverScope Saver = (SaverScope) obj;
            SpanStyle it = (SpanStyle) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            Object save = SaversKt.save(Color.m91boximpl(it.m305getColor0d7_KjU()), SaversKt.getSaver$7(), Saver);
            Object save2 = SaversKt.save(TextUnit.m409boximpl(it.m306getFontSizeXSAIIZE()), SaversKt.getSaver$8(), Saver);
            FontWeight fontWeight = it.getFontWeight();
            FontWeight.Companion companion = FontWeight.Companion;
            Object save3 = SaversKt.save(fontWeight, SaversKt.getSaver$3(), Saver);
            FontStyle m307getFontStyle4Lr2A7w = it.m307getFontStyle4Lr2A7w();
            FontSynthesis m308getFontSynthesisZQGJjVo = it.m308getFontSynthesisZQGJjVo();
            String fontFeatureSettings = it.getFontFeatureSettings();
            Object save4 = SaversKt.save(TextUnit.m409boximpl(it.m309getLetterSpacingXSAIIZE()), SaversKt.getSaver$8(), Saver);
            BaselineShift m304getBaselineShift5SSeXJ0 = it.m304getBaselineShift5SSeXJ0();
            saverKt$Saver$1 = SaversKt.BaselineShiftSaver;
            Object save5 = SaversKt.save(m304getBaselineShift5SSeXJ0, saverKt$Saver$1, Saver);
            TextGeometricTransform textGeometricTransform = it.getTextGeometricTransform();
            saverKt$Saver$12 = SaversKt.TextGeometricTransformSaver;
            Object save6 = SaversKt.save(textGeometricTransform, saverKt$Saver$12, Saver);
            LocaleList localeList = it.getLocaleList();
            saverKt$Saver$13 = SaversKt.LocaleListSaver;
            Object save7 = SaversKt.save(localeList, saverKt$Saver$13, Saver);
            Object save8 = SaversKt.save(Color.m91boximpl(it.m303getBackground0d7_KjU()), SaversKt.getSaver$7(), Saver);
            Object save9 = SaversKt.save(it.getTextDecoration(), SaversKt.getSaver(), Saver);
            Shadow shadow = it.getShadow();
            Shadow.Companion companion2 = Shadow.Companion;
            return CollectionsKt.arrayListOf(save, save2, save3, m307getFontStyle4Lr2A7w, m308getFontSynthesisZQGJjVo, -1, fontFeatureSettings, save4, save5, save6, save7, save8, save9, SaversKt.save(shadow, SaversKt.getSaver$6(), Saver));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$SpanStyleSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Color color;
            TextUnit textUnit;
            FontWeight fontWeight;
            FontStyle fontStyle;
            FontSynthesis fontSynthesis;
            String str;
            TextUnit textUnit2;
            SaverKt$Saver$1 saverKt$Saver$1;
            BaselineShift baselineShift;
            SaverKt$Saver$1 saverKt$Saver$12;
            TextGeometricTransform textGeometricTransform;
            SaverKt$Saver$1 saverKt$Saver$13;
            LocaleList localeList;
            Color color2;
            TextDecoration textDecoration;
            Shadow shadow;
            Intrinsics.checkNotNullParameter(it, "it");
            List list = (List) it;
            Object obj = list.get(0);
            Color.Companion companion = Color.Companion;
            SaverKt$Saver$1 saver$7 = SaversKt.getSaver$7();
            Boolean bool = Boolean.FALSE;
            if (!Intrinsics.areEqual(obj, bool) && obj != null) {
                color = (Color) saver$7.restore(obj);
            } else {
                color = null;
            }
            Intrinsics.checkNotNull(color);
            long m99unboximpl = color.m99unboximpl();
            Object obj2 = list.get(1);
            TextUnit.Companion companion2 = TextUnit.Companion;
            SaverKt$Saver$1 saver$8 = SaversKt.getSaver$8();
            if (!Intrinsics.areEqual(obj2, bool) && obj2 != null) {
                textUnit = (TextUnit) saver$8.restore(obj2);
            } else {
                textUnit = null;
            }
            Intrinsics.checkNotNull(textUnit);
            long m414unboximpl = textUnit.m414unboximpl();
            Object obj3 = list.get(2);
            FontWeight.Companion companion3 = FontWeight.Companion;
            SaverKt$Saver$1 saver$3 = SaversKt.getSaver$3();
            if (!Intrinsics.areEqual(obj3, bool) && obj3 != null) {
                fontWeight = (FontWeight) saver$3.restore(obj3);
            } else {
                fontWeight = null;
            }
            Object obj4 = list.get(3);
            if (obj4 != null) {
                fontStyle = (FontStyle) obj4;
            } else {
                fontStyle = null;
            }
            Object obj5 = list.get(4);
            if (obj5 != null) {
                fontSynthesis = (FontSynthesis) obj5;
            } else {
                fontSynthesis = null;
            }
            Object obj6 = list.get(6);
            if (obj6 != null) {
                str = (String) obj6;
            } else {
                str = null;
            }
            Object obj7 = list.get(7);
            SaverKt$Saver$1 saver$82 = SaversKt.getSaver$8();
            if (!Intrinsics.areEqual(obj7, bool) && obj7 != null) {
                textUnit2 = (TextUnit) saver$82.restore(obj7);
            } else {
                textUnit2 = null;
            }
            Intrinsics.checkNotNull(textUnit2);
            long m414unboximpl2 = textUnit2.m414unboximpl();
            Object obj8 = list.get(8);
            saverKt$Saver$1 = SaversKt.BaselineShiftSaver;
            if (!Intrinsics.areEqual(obj8, bool) && obj8 != null) {
                baselineShift = (BaselineShift) saverKt$Saver$1.restore(obj8);
            } else {
                baselineShift = null;
            }
            Object obj9 = list.get(9);
            saverKt$Saver$12 = SaversKt.TextGeometricTransformSaver;
            if (!Intrinsics.areEqual(obj9, bool) && obj9 != null) {
                textGeometricTransform = (TextGeometricTransform) saverKt$Saver$12.restore(obj9);
            } else {
                textGeometricTransform = null;
            }
            Object obj10 = list.get(10);
            saverKt$Saver$13 = SaversKt.LocaleListSaver;
            if (!Intrinsics.areEqual(obj10, bool) && obj10 != null) {
                localeList = (LocaleList) saverKt$Saver$13.restore(obj10);
            } else {
                localeList = null;
            }
            Object obj11 = list.get(11);
            SaverKt$Saver$1 saver$72 = SaversKt.getSaver$7();
            if (!Intrinsics.areEqual(obj11, bool) && obj11 != null) {
                color2 = (Color) saver$72.restore(obj11);
            } else {
                color2 = null;
            }
            Intrinsics.checkNotNull(color2);
            long m99unboximpl2 = color2.m99unboximpl();
            Object obj12 = list.get(12);
            SaverKt$Saver$1 saver = SaversKt.getSaver();
            if (!Intrinsics.areEqual(obj12, bool) && obj12 != null) {
                textDecoration = (TextDecoration) saver.restore(obj12);
            } else {
                textDecoration = null;
            }
            Object obj13 = list.get(13);
            Shadow.Companion companion4 = Shadow.Companion;
            SaverKt$Saver$1 saver$6 = SaversKt.getSaver$6();
            if (!Intrinsics.areEqual(obj13, bool) && obj13 != null) {
                shadow = (Shadow) saver$6.restore(obj13);
            } else {
                shadow = null;
            }
            return new SpanStyle(m99unboximpl, m414unboximpl, fontWeight, fontStyle, fontSynthesis, (FontFamily) null, str, m414unboximpl2, baselineShift, textGeometricTransform, localeList, m99unboximpl2, textDecoration, shadow, 32);
        }
    });
    private static final SaverKt$Saver$1 TextDecorationSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextDecorationSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            TextDecoration it = (TextDecoration) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            return Integer.valueOf(it.getMask());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextDecorationSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return new TextDecoration(((Integer) it).intValue());
        }
    });
    private static final SaverKt$Saver$1 TextGeometricTransformSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextGeometricTransformSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            TextGeometricTransform it = (TextGeometricTransform) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            return CollectionsKt.arrayListOf(Float.valueOf(it.getScaleX()), Float.valueOf(it.getSkewX()));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextGeometricTransformSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Intrinsics.checkNotNullParameter(it, "it");
            List list = (List) it;
            return new TextGeometricTransform(((Number) list.get(0)).floatValue(), ((Number) list.get(1)).floatValue());
        }
    });
    private static final SaverKt$Saver$1 TextIndentSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextIndentSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            TextIndent it = (TextIndent) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            return CollectionsKt.arrayListOf(SaversKt.save(TextUnit.m409boximpl(it.m373getFirstLineXSAIIZE()), SaversKt.getSaver$8(), Saver), SaversKt.save(TextUnit.m409boximpl(it.m374getRestLineXSAIIZE()), SaversKt.getSaver$8(), Saver));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextIndentSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            TextUnit textUnit;
            Intrinsics.checkNotNullParameter(it, "it");
            List list = (List) it;
            Object obj = list.get(0);
            TextUnit.Companion companion = TextUnit.Companion;
            SaverKt$Saver$1 saver$8 = SaversKt.getSaver$8();
            Boolean bool = Boolean.FALSE;
            TextUnit textUnit2 = null;
            if (!Intrinsics.areEqual(obj, bool) && obj != null) {
                textUnit = (TextUnit) saver$8.restore(obj);
            } else {
                textUnit = null;
            }
            Intrinsics.checkNotNull(textUnit);
            long m414unboximpl = textUnit.m414unboximpl();
            Object obj2 = list.get(1);
            SaverKt$Saver$1 saver$82 = SaversKt.getSaver$8();
            if (!Intrinsics.areEqual(obj2, bool) && obj2 != null) {
                textUnit2 = (TextUnit) saver$82.restore(obj2);
            }
            Intrinsics.checkNotNull(textUnit2);
            return new TextIndent(m414unboximpl, textUnit2.m414unboximpl());
        }
    });
    private static final SaverKt$Saver$1 FontWeightSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$FontWeightSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            FontWeight it = (FontWeight) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            return Integer.valueOf(it.getWeight());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$FontWeightSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return new FontWeight(((Integer) it).intValue());
        }
    });
    private static final SaverKt$Saver$1 BaselineShiftSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$BaselineShiftSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            float m351unboximpl = ((BaselineShift) obj2).m351unboximpl();
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            return Float.valueOf(m351unboximpl);
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$BaselineShiftSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return BaselineShift.m350boximpl(((Float) it).floatValue());
        }
    });
    private static final SaverKt$Saver$1 TextRangeSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextRangeSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            long m317unboximpl = ((TextRange) obj2).m317unboximpl();
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            TextRange.Companion companion = TextRange.Companion;
            Integer valueOf = Integer.valueOf((int) (m317unboximpl >> 32));
            int i = SaversKt.$r8$clinit;
            return CollectionsKt.arrayListOf(valueOf, Integer.valueOf(TextRange.m315getEndimpl(m317unboximpl)));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextRangeSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Integer num;
            Intrinsics.checkNotNullParameter(it, "it");
            List list = (List) it;
            Object obj = list.get(0);
            Integer num2 = null;
            if (obj != null) {
                num = (Integer) obj;
            } else {
                num = null;
            }
            Intrinsics.checkNotNull(num);
            int intValue = num.intValue();
            Object obj2 = list.get(1);
            if (obj2 != null) {
                num2 = (Integer) obj2;
            }
            Intrinsics.checkNotNull(num2);
            return TextRange.m314boximpl(TextRangeKt.TextRange(intValue, num2.intValue()));
        }
    });
    private static final SaverKt$Saver$1 ShadowSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$ShadowSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            Shadow it = (Shadow) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            return CollectionsKt.arrayListOf(SaversKt.save(Color.m91boximpl(it.m115getColor0d7_KjU()), SaversKt.getSaver$7(), Saver), SaversKt.save(Offset.m42boximpl(it.m116getOffsetF1C5BW0()), SaversKt.getSaver$9(), Saver), Float.valueOf(it.getBlurRadius()));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$ShadowSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Color color;
            Offset offset;
            Intrinsics.checkNotNullParameter(it, "it");
            List list = (List) it;
            Object obj = list.get(0);
            Color.Companion companion = Color.Companion;
            SaverKt$Saver$1 saver$7 = SaversKt.getSaver$7();
            Boolean bool = Boolean.FALSE;
            Float f = null;
            if (!Intrinsics.areEqual(obj, bool) && obj != null) {
                color = (Color) saver$7.restore(obj);
            } else {
                color = null;
            }
            Intrinsics.checkNotNull(color);
            long m99unboximpl = color.m99unboximpl();
            Object obj2 = list.get(1);
            Offset.Companion companion2 = Offset.Companion;
            SaverKt$Saver$1 saver$9 = SaversKt.getSaver$9();
            if (!Intrinsics.areEqual(obj2, bool) && obj2 != null) {
                offset = (Offset) saver$9.restore(obj2);
            } else {
                offset = null;
            }
            Intrinsics.checkNotNull(offset);
            long m51unboximpl = offset.m51unboximpl();
            Object obj3 = list.get(2);
            if (obj3 != null) {
                f = (Float) obj3;
            }
            Intrinsics.checkNotNull(f);
            return new Shadow(m99unboximpl, m51unboximpl, f.floatValue());
        }
    });
    private static final SaverKt$Saver$1 ColorSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            long m99unboximpl = ((Color) obj2).m99unboximpl();
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            return ULong.m479boximpl(m99unboximpl);
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$ColorSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Color.m91boximpl(((ULong) it).m480unboximpl());
        }
    });
    private static final SaverKt$Saver$1 TextUnitSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$TextUnitSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            long m414unboximpl = ((TextUnit) obj2).m414unboximpl();
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Float valueOf = Float.valueOf(TextUnit.m412getValueimpl(m414unboximpl));
            int i = SaversKt.$r8$clinit;
            return CollectionsKt.arrayListOf(valueOf, TextUnitType.m416boximpl(TextUnit.m411getTypeUIouoOA(m414unboximpl)));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$TextUnitSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Float f;
            Intrinsics.checkNotNullParameter(it, "it");
            List list = (List) it;
            Object obj = list.get(0);
            TextUnitType textUnitType = null;
            if (obj != null) {
                f = (Float) obj;
            } else {
                f = null;
            }
            Intrinsics.checkNotNull(f);
            float floatValue = f.floatValue();
            Object obj2 = list.get(1);
            if (obj2 != null) {
                textUnitType = (TextUnitType) obj2;
            }
            Intrinsics.checkNotNull(textUnitType);
            return TextUnit.m409boximpl(TextUnitKt.pack(textUnitType.m418unboximpl(), floatValue));
        }
    });
    private static final SaverKt$Saver$1 OffsetSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$OffsetSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            long j;
            SaverScope Saver = (SaverScope) obj;
            long m51unboximpl = ((Offset) obj2).m51unboximpl();
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            j = Offset.Unspecified;
            if (Offset.m43equalsimpl0(m51unboximpl, j)) {
                return Boolean.FALSE;
            }
            Float valueOf = Float.valueOf(Offset.m45getXimpl(m51unboximpl));
            int i = SaversKt.$r8$clinit;
            return CollectionsKt.arrayListOf(valueOf, Float.valueOf(Offset.m46getYimpl(m51unboximpl)));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$OffsetSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Float f;
            long j;
            Intrinsics.checkNotNullParameter(it, "it");
            if (Intrinsics.areEqual(it, Boolean.FALSE)) {
                j = Offset.Unspecified;
                return Offset.m42boximpl(j);
            }
            List list = (List) it;
            Object obj = list.get(0);
            Float f2 = null;
            if (obj != null) {
                f = (Float) obj;
            } else {
                f = null;
            }
            Intrinsics.checkNotNull(f);
            float floatValue = f.floatValue();
            Object obj2 = list.get(1);
            if (obj2 != null) {
                f2 = (Float) obj2;
            }
            Intrinsics.checkNotNull(f2);
            return Offset.m42boximpl(OffsetKt.Offset(floatValue, f2.floatValue()));
        }
    });
    private static final SaverKt$Saver$1 LocaleListSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$LocaleListSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverKt$Saver$1 saverKt$Saver$1;
            SaverScope Saver = (SaverScope) obj;
            LocaleList it = (LocaleList) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            List localeList = it.getLocaleList();
            ArrayList arrayList = new ArrayList(localeList.size());
            int size = localeList.size();
            for (int i = 0; i < size; i++) {
                saverKt$Saver$1 = SaversKt.LocaleSaver;
                arrayList.add(SaversKt.save((Locale) localeList.get(i), saverKt$Saver$1, Saver));
            }
            return arrayList;
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$LocaleListSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            SaverKt$Saver$1 saverKt$Saver$1;
            Locale locale;
            Intrinsics.checkNotNullParameter(it, "it");
            List list = (List) it;
            ArrayList arrayList = new ArrayList(list.size());
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj = list.get(i);
                saverKt$Saver$1 = SaversKt.LocaleSaver;
                if (!Intrinsics.areEqual(obj, Boolean.FALSE) && obj != null) {
                    locale = (Locale) saverKt$Saver$1.restore(obj);
                } else {
                    locale = null;
                }
                Intrinsics.checkNotNull(locale);
                arrayList.add(locale);
            }
            return new LocaleList(arrayList);
        }
    });
    private static final SaverKt$Saver$1 LocaleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.SaversKt$LocaleSaver$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            SaverScope Saver = (SaverScope) obj;
            Locale it = (Locale) obj2;
            Intrinsics.checkNotNullParameter(Saver, "$this$Saver");
            Intrinsics.checkNotNullParameter(it, "it");
            return it.toLanguageTag();
        }
    }, new Function1() { // from class: androidx.compose.ui.text.SaversKt$LocaleSaver$2
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object it) {
            Intrinsics.checkNotNullParameter(it, "it");
            PlatformLocaleKt.getPlatformLocaleDelegate().getClass();
            java.util.Locale forLanguageTag = java.util.Locale.forLanguageTag((String) it);
            Intrinsics.checkNotNullExpressionValue(forLanguageTag, "forLanguageTag(languageTag)");
            return new Locale(new AndroidLocale(forLanguageTag));
        }
    });

    public static final SaverKt$Saver$1 getAnnotatedStringSaver() {
        return AnnotatedStringSaver;
    }

    public static final SaverKt$Saver$1 getParagraphStyleSaver() {
        return ParagraphStyleSaver;
    }

    public static final SaverKt$Saver$1 getSaver() {
        return TextDecorationSaver;
    }

    public static final SaverKt$Saver$1 getSaver$2() {
        TextIndent.Companion companion = TextIndent.Companion;
        return TextIndentSaver;
    }

    public static final SaverKt$Saver$1 getSaver$3() {
        FontWeight.Companion companion = FontWeight.Companion;
        return FontWeightSaver;
    }

    public static final SaverKt$Saver$1 getSaver$5() {
        TextRange.Companion companion = TextRange.Companion;
        return TextRangeSaver;
    }

    public static final SaverKt$Saver$1 getSaver$6() {
        Shadow.Companion companion = Shadow.Companion;
        return ShadowSaver;
    }

    public static final SaverKt$Saver$1 getSaver$7() {
        Color.Companion companion = Color.Companion;
        return ColorSaver;
    }

    public static final SaverKt$Saver$1 getSaver$8() {
        TextUnit.Companion companion = TextUnit.Companion;
        return TextUnitSaver;
    }

    public static final SaverKt$Saver$1 getSaver$9() {
        Offset.Companion companion = Offset.Companion;
        return OffsetSaver;
    }

    public static final SaverKt$Saver$1 getSpanStyleSaver() {
        return SpanStyleSaver;
    }

    public static final Object save(Object obj, SaverKt$Saver$1 saver, SaverScope scope) {
        Object save;
        Intrinsics.checkNotNullParameter(saver, "saver");
        Intrinsics.checkNotNullParameter(scope, "scope");
        if (obj == null || (save = saver.save(scope, obj)) == null) {
            return Boolean.FALSE;
        }
        return save;
    }
}
