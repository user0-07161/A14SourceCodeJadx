package androidx.compose.ui.text;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AnnotatedString implements CharSequence {
    private final List annotations;
    private final List paragraphStylesOrNull;
    private final List spanStylesOrNull;
    private final String text;

    public AnnotatedString(String text, List list, List list2, List list3) {
        List sortedWith;
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.spanStylesOrNull = list;
        this.paragraphStylesOrNull = list2;
        this.annotations = list3;
        if (list2 == null || (sortedWith = CollectionsKt.sortedWith(list2, new AnnotatedString$special$$inlined$sortedBy$1())) == null) {
            return;
        }
        int size = sortedWith.size();
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            Range range = (Range) sortedWith.get(i2);
            if (range.getStart() >= i) {
                if (range.getEnd() <= this.text.length()) {
                    i = range.getEnd();
                } else {
                    throw new IllegalArgumentException(("ParagraphStyle range [" + range.getStart() + ", " + range.getEnd() + ") is out of boundary").toString());
                }
            } else {
                throw new IllegalArgumentException("ParagraphStyle should not overlap".toString());
            }
        }
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        return this.text.charAt(i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnnotatedString)) {
            return false;
        }
        AnnotatedString annotatedString = (AnnotatedString) obj;
        if (Intrinsics.areEqual(this.text, annotatedString.text) && Intrinsics.areEqual(this.spanStylesOrNull, annotatedString.spanStylesOrNull) && Intrinsics.areEqual(this.paragraphStylesOrNull, annotatedString.paragraphStylesOrNull) && Intrinsics.areEqual(this.annotations, annotatedString.annotations)) {
            return true;
        }
        return false;
    }

    public final List getAnnotations$ui_text_release() {
        return this.annotations;
    }

    public final List getParagraphStyles() {
        List list = this.paragraphStylesOrNull;
        if (list == null) {
            return EmptyList.INSTANCE;
        }
        return list;
    }

    public final List getParagraphStylesOrNull$ui_text_release() {
        return this.paragraphStylesOrNull;
    }

    public final List getSpanStyles() {
        List list = this.spanStylesOrNull;
        if (list == null) {
            return EmptyList.INSTANCE;
        }
        return list;
    }

    public final List getSpanStylesOrNull$ui_text_release() {
        return this.spanStylesOrNull;
    }

    public final String getText() {
        return this.text;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.collections.EmptyList] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList] */
    public final List getTtsAnnotations(int i) {
        ?? r0;
        boolean z;
        List list = this.annotations;
        if (list != null) {
            r0 = new ArrayList(list.size());
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                Object obj = list.get(i2);
                Range range = (Range) obj;
                if ((range.getItem() instanceof TtsAnnotation) && AnnotatedStringKt.intersect(0, i, range.getStart(), range.getEnd())) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    r0.add(obj);
                }
            }
        } else {
            r0 = EmptyList.INSTANCE;
        }
        Intrinsics.checkNotNull(r0, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.TtsAnnotation>>");
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.collections.EmptyList] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList] */
    public final List getUrlAnnotations(int i) {
        ?? r0;
        boolean z;
        List list = this.annotations;
        if (list != null) {
            r0 = new ArrayList(list.size());
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                Object obj = list.get(i2);
                Range range = (Range) obj;
                if ((range.getItem() instanceof UrlAnnotation) && AnnotatedStringKt.intersect(0, i, range.getStart(), range.getEnd())) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    r0.add(obj);
                }
            }
        } else {
            r0 = EmptyList.INSTANCE;
        }
        Intrinsics.checkNotNull(r0, "null cannot be cast to non-null type kotlin.collections.List<androidx.compose.ui.text.AnnotatedString.Range<androidx.compose.ui.text.UrlAnnotation>>");
        return r0;
    }

    public final int hashCode() {
        int i;
        int i2;
        int hashCode = this.text.hashCode() * 31;
        List list = this.spanStylesOrNull;
        int i3 = 0;
        if (list != null) {
            i = list.hashCode();
        } else {
            i = 0;
        }
        int i4 = (hashCode + i) * 31;
        List list2 = this.paragraphStylesOrNull;
        if (list2 != null) {
            i2 = list2.hashCode();
        } else {
            i2 = 0;
        }
        int i5 = (i4 + i2) * 31;
        List list3 = this.annotations;
        if (list3 != null) {
            i3 = list3.hashCode();
        }
        return i5 + i3;
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.text.length();
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.text;
    }

    @Override // java.lang.CharSequence
    public final AnnotatedString subSequence(int i, int i2) {
        if (i <= i2) {
            if (i == 0 && i2 == this.text.length()) {
                return this;
            }
            String substring = this.text.substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return new AnnotatedString(substring, AnnotatedStringKt.access$filterRanges(this.spanStylesOrNull, i, i2), AnnotatedStringKt.access$filterRanges(this.paragraphStylesOrNull, i, i2), AnnotatedStringKt.access$filterRanges(this.annotations, i, i2));
        }
        throw new IllegalArgumentException(("start (" + i + ") should be less or equal to end (" + i2 + ')').toString());
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Range {
        private final int end;
        private final Object item;
        private final int start;
        private final String tag;

        public Range(Object obj, int i, int i2, String tag) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            this.item = obj;
            this.start = i;
            this.end = i2;
            this.tag = tag;
            if (!(i <= i2)) {
                throw new IllegalArgumentException("Reversed range is not supported".toString());
            }
        }

        public final Object component1() {
            return this.item;
        }

        public final int component2() {
            return this.start;
        }

        public final int component3() {
            return this.end;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Range)) {
                return false;
            }
            Range range = (Range) obj;
            if (Intrinsics.areEqual(this.item, range.item) && this.start == range.start && this.end == range.end && Intrinsics.areEqual(this.tag, range.tag)) {
                return true;
            }
            return false;
        }

        public final int getEnd() {
            return this.end;
        }

        public final Object getItem() {
            return this.item;
        }

        public final int getStart() {
            return this.start;
        }

        public final String getTag() {
            return this.tag;
        }

        public final int hashCode() {
            int hashCode;
            Object obj = this.item;
            if (obj == null) {
                hashCode = 0;
            } else {
                hashCode = obj.hashCode();
            }
            int hashCode2 = Integer.hashCode(this.start);
            int hashCode3 = Integer.hashCode(this.end);
            return this.tag.hashCode() + ((hashCode3 + ((hashCode2 + (hashCode * 31)) * 31)) * 31);
        }

        public final String toString() {
            return "Range(item=" + this.item + ", start=" + this.start + ", end=" + this.end + ", tag=" + this.tag + ')';
        }

        public Range(int i, int i2, Object obj) {
            this(obj, i, i2, "");
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AnnotatedString(String text) {
        this(text, null, null, null);
        EmptyList paragraphStyles = EmptyList.INSTANCE;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(paragraphStyles, "spanStyles");
        Intrinsics.checkNotNullParameter(paragraphStyles, "paragraphStyles");
    }
}
