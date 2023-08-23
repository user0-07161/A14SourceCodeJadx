package androidx.compose.ui.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MultiParagraphIntrinsics implements ParagraphIntrinsics {
    private final AnnotatedString annotatedString;
    private final List infoList;
    private final Lazy maxIntrinsicWidth$delegate;
    private final Lazy minIntrinsicWidth$delegate;
    private final List placeholders;

    /* JADX WARN: Type inference failed for: r9v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r9v9 */
    public MultiParagraphIntrinsics(AnnotatedString annotatedString, TextStyle textStyle, List placeholders, Density density, FontFamilyResolverImpl fontFamilyResolver) {
        String str;
        int i;
        List list;
        List list2;
        boolean z;
        ?? spanStylesOrNull$ui_text_release;
        Intrinsics.checkNotNullParameter(annotatedString, "annotatedString");
        Intrinsics.checkNotNullParameter(placeholders, "placeholders");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(fontFamilyResolver, "fontFamilyResolver");
        this.annotatedString = annotatedString;
        this.placeholders = placeholders;
        this.minIntrinsicWidth$delegate = LazyKt.lazy(new Function0() { // from class: androidx.compose.ui.text.MultiParagraphIntrinsics$minIntrinsicWidth$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Object obj;
                float f;
                ParagraphIntrinsics intrinsics;
                ArrayList arrayList = (ArrayList) MultiParagraphIntrinsics.this.getInfoList$ui_text_release();
                if (arrayList.isEmpty()) {
                    obj = null;
                } else {
                    Object obj2 = arrayList.get(0);
                    float minIntrinsicWidth = ((ParagraphIntrinsicInfo) obj2).getIntrinsics().getMinIntrinsicWidth();
                    int lastIndex = CollectionsKt.getLastIndex(arrayList);
                    int i2 = 1;
                    if (1 <= lastIndex) {
                        while (true) {
                            Object obj3 = arrayList.get(i2);
                            float minIntrinsicWidth2 = ((ParagraphIntrinsicInfo) obj3).getIntrinsics().getMinIntrinsicWidth();
                            if (Float.compare(minIntrinsicWidth, minIntrinsicWidth2) < 0) {
                                obj2 = obj3;
                                minIntrinsicWidth = minIntrinsicWidth2;
                            }
                            if (i2 == lastIndex) {
                                break;
                            }
                            i2++;
                        }
                    }
                    obj = obj2;
                }
                ParagraphIntrinsicInfo paragraphIntrinsicInfo = (ParagraphIntrinsicInfo) obj;
                if (paragraphIntrinsicInfo != null && (intrinsics = paragraphIntrinsicInfo.getIntrinsics()) != null) {
                    f = intrinsics.getMinIntrinsicWidth();
                } else {
                    f = 0.0f;
                }
                return Float.valueOf(f);
            }
        });
        this.maxIntrinsicWidth$delegate = LazyKt.lazy(new Function0() { // from class: androidx.compose.ui.text.MultiParagraphIntrinsics$maxIntrinsicWidth$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Object obj;
                float f;
                ParagraphIntrinsics intrinsics;
                ArrayList arrayList = (ArrayList) MultiParagraphIntrinsics.this.getInfoList$ui_text_release();
                if (arrayList.isEmpty()) {
                    obj = null;
                } else {
                    Object obj2 = arrayList.get(0);
                    float maxIntrinsicWidth = ((ParagraphIntrinsicInfo) obj2).getIntrinsics().getMaxIntrinsicWidth();
                    int lastIndex = CollectionsKt.getLastIndex(arrayList);
                    int i2 = 1;
                    if (1 <= lastIndex) {
                        while (true) {
                            Object obj3 = arrayList.get(i2);
                            float maxIntrinsicWidth2 = ((ParagraphIntrinsicInfo) obj3).getIntrinsics().getMaxIntrinsicWidth();
                            if (Float.compare(maxIntrinsicWidth, maxIntrinsicWidth2) < 0) {
                                obj2 = obj3;
                                maxIntrinsicWidth = maxIntrinsicWidth2;
                            }
                            if (i2 == lastIndex) {
                                break;
                            }
                            i2++;
                        }
                    }
                    obj = obj2;
                }
                ParagraphIntrinsicInfo paragraphIntrinsicInfo = (ParagraphIntrinsicInfo) obj;
                if (paragraphIntrinsicInfo != null && (intrinsics = paragraphIntrinsicInfo.getIntrinsics()) != null) {
                    f = intrinsics.getMaxIntrinsicWidth();
                } else {
                    f = 0.0f;
                }
                return Float.valueOf(f);
            }
        });
        ParagraphStyle defaultParagraphStyle = textStyle.toParagraphStyle();
        int i2 = AnnotatedStringKt.$r8$clinit;
        Intrinsics.checkNotNullParameter(defaultParagraphStyle, "defaultParagraphStyle");
        int length = annotatedString.getText().length();
        List paragraphStylesOrNull$ui_text_release = annotatedString.getParagraphStylesOrNull$ui_text_release();
        paragraphStylesOrNull$ui_text_release = paragraphStylesOrNull$ui_text_release == null ? EmptyList.INSTANCE : paragraphStylesOrNull$ui_text_release;
        ArrayList arrayList = new ArrayList();
        int size = paragraphStylesOrNull$ui_text_release.size();
        int i3 = 0;
        int i4 = 0;
        while (i3 < size) {
            AnnotatedString.Range range = (AnnotatedString.Range) paragraphStylesOrNull$ui_text_release.get(i3);
            ParagraphStyle paragraphStyle = (ParagraphStyle) range.component1();
            int component2 = range.component2();
            int component3 = range.component3();
            if (component2 != i4) {
                arrayList.add(new AnnotatedString.Range(i4, component2, defaultParagraphStyle));
            }
            arrayList.add(new AnnotatedString.Range(component2, component3, defaultParagraphStyle.merge(paragraphStyle)));
            i3++;
            i4 = component3;
        }
        if (i4 != length) {
            arrayList.add(new AnnotatedString.Range(i4, length, defaultParagraphStyle));
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new AnnotatedString.Range(0, 0, defaultParagraphStyle));
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        int i5 = 0;
        while (i5 < size2) {
            AnnotatedString.Range range2 = (AnnotatedString.Range) arrayList.get(i5);
            int start = range2.getStart();
            int end = range2.getEnd();
            if (start != end) {
                str = annotatedString.getText().substring(start, end);
                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
            } else {
                str = "";
            }
            if (start == end || (spanStylesOrNull$ui_text_release = annotatedString.getSpanStylesOrNull$ui_text_release()) == 0) {
                i = i5;
                list = null;
                list2 = null;
            } else {
                if (start == 0 && end >= annotatedString.getText().length()) {
                    i = i5;
                } else {
                    ArrayList arrayList3 = new ArrayList(spanStylesOrNull$ui_text_release.size());
                    int size3 = spanStylesOrNull$ui_text_release.size();
                    int i6 = 0;
                    List list3 = spanStylesOrNull$ui_text_release;
                    while (i6 < size3) {
                        int i7 = size3;
                        Object obj = list3.get(i6);
                        AnnotatedString.Range range3 = (AnnotatedString.Range) obj;
                        List list4 = list3;
                        int i8 = i5;
                        if (AnnotatedStringKt.intersect(start, end, range3.getStart(), range3.getEnd())) {
                            arrayList3.add(obj);
                        }
                        i6++;
                        size3 = i7;
                        list3 = list4;
                        i5 = i8;
                    }
                    i = i5;
                    spanStylesOrNull$ui_text_release = new ArrayList(arrayList3.size());
                    int i9 = 0;
                    for (int size4 = arrayList3.size(); i9 < size4; size4 = size4) {
                        AnnotatedString.Range range4 = (AnnotatedString.Range) arrayList3.get(i9);
                        spanStylesOrNull$ui_text_release.add(new AnnotatedString.Range(RangesKt.coerceIn(range4.getStart(), start, end) - start, RangesKt.coerceIn(range4.getEnd(), start, end) - start, range4.getItem()));
                        i9++;
                    }
                }
                list = null;
                list2 = spanStylesOrNull$ui_text_release;
            }
            AnnotatedString annotatedString2 = new AnnotatedString(str, list2, list, list);
            ParagraphStyle paragraphStyle2 = (ParagraphStyle) range2.getItem();
            paragraphStyle2 = paragraphStyle2.m301getTextDirectionmmuk1to() == null ? ParagraphStyle.m296copyciSxzs0$default(paragraphStyle2, defaultParagraphStyle.m301getTextDirectionmmuk1to()) : paragraphStyle2;
            String text = annotatedString2.getText();
            TextStyle merge = textStyle.merge(paragraphStyle2);
            List spanStyles = annotatedString2.getSpanStyles();
            List list5 = this.placeholders;
            int start2 = range2.getStart();
            int end2 = range2.getEnd();
            ArrayList arrayList4 = new ArrayList(list5.size());
            int size5 = list5.size();
            int i10 = 0;
            while (i10 < size5) {
                ParagraphStyle paragraphStyle3 = defaultParagraphStyle;
                Object obj2 = list5.get(i10);
                AnnotatedString.Range range5 = (AnnotatedString.Range) obj2;
                List list6 = list5;
                int i11 = size5;
                if (AnnotatedStringKt.intersect(start2, end2, range5.getStart(), range5.getEnd())) {
                    arrayList4.add(obj2);
                }
                i10++;
                defaultParagraphStyle = paragraphStyle3;
                list5 = list6;
                size5 = i11;
            }
            ParagraphStyle paragraphStyle4 = defaultParagraphStyle;
            ArrayList arrayList5 = new ArrayList(arrayList4.size());
            int size6 = arrayList4.size();
            int i12 = 0;
            while (i12 < size6) {
                AnnotatedString.Range range6 = (AnnotatedString.Range) arrayList4.get(i12);
                int i13 = size6;
                if (start2 <= range6.getStart() && range6.getEnd() <= end2) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    arrayList5.add(new AnnotatedString.Range(range6.getStart() - start2, range6.getEnd() - start2, range6.getItem()));
                    i12++;
                    size6 = i13;
                    end2 = end2;
                    arrayList4 = arrayList4;
                } else {
                    throw new IllegalArgumentException("placeholder can not overlap with paragraph.".toString());
                }
            }
            Intrinsics.checkNotNullParameter(text, "text");
            Intrinsics.checkNotNullParameter(spanStyles, "spanStyles");
            arrayList2.add(new ParagraphIntrinsicInfo(new AndroidParagraphIntrinsics(merge, fontFamilyResolver, density, text, spanStyles, arrayList5), range2.getStart(), range2.getEnd()));
            i5 = i + 1;
            defaultParagraphStyle = paragraphStyle4;
        }
        this.infoList = arrayList2;
    }

    public final AnnotatedString getAnnotatedString() {
        return this.annotatedString;
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final boolean getHasStaleResolvedFonts() {
        ArrayList arrayList = (ArrayList) this.infoList;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (((ParagraphIntrinsicInfo) arrayList.get(i)).getIntrinsics().getHasStaleResolvedFonts()) {
                return true;
            }
        }
        return false;
    }

    public final List getInfoList$ui_text_release() {
        return this.infoList;
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMaxIntrinsicWidth() {
        return ((Number) this.maxIntrinsicWidth$delegate.getValue()).floatValue();
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMinIntrinsicWidth() {
        return ((Number) this.minIntrinsicWidth$delegate.getValue()).floatValue();
    }

    public final List getPlaceholders() {
        return this.placeholders;
    }
}
