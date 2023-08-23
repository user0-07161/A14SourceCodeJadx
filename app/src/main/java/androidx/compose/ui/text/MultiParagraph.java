package androidx.compose.ui.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MultiParagraph {
    private final boolean didExceedMaxLines;
    private final float height;
    private final MultiParagraphIntrinsics intrinsics;
    private final int lineCount;
    private final int maxLines;
    private final List paragraphInfoList;
    private final List placeholderRects;
    private final float width;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.List] */
    public MultiParagraph(MultiParagraphIntrinsics multiParagraphIntrinsics, long j, int i, boolean z) {
        boolean z2;
        boolean z3;
        Rect rect;
        int m380getMaxHeightimpl;
        this.intrinsics = multiParagraphIntrinsics;
        this.maxLines = i;
        if (Constraints.m383getMinWidthimpl(j) == 0 && Constraints.m382getMinHeightimpl(j) == 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = (ArrayList) multiParagraphIntrinsics.getInfoList$ui_text_release();
            int size = arrayList2.size();
            float f = 0.0f;
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                ParagraphIntrinsicInfo paragraphIntrinsicInfo = (ParagraphIntrinsicInfo) arrayList2.get(i2);
                ParagraphIntrinsics paragraphIntrinsics = paragraphIntrinsicInfo.getIntrinsics();
                int m381getMaxWidthimpl = Constraints.m381getMaxWidthimpl(j);
                if (Constraints.m378getHasBoundedHeightimpl(j)) {
                    m380getMaxHeightimpl = Constraints.m380getMaxHeightimpl(j) - ((int) Math.ceil(f));
                    if (m380getMaxHeightimpl < 0) {
                        m380getMaxHeightimpl = 0;
                    }
                } else {
                    m380getMaxHeightimpl = Constraints.m380getMaxHeightimpl(j);
                }
                long Constraints$default = ConstraintsKt.Constraints$default(m381getMaxWidthimpl, m380getMaxHeightimpl, 5);
                Intrinsics.checkNotNullParameter(paragraphIntrinsics, "paragraphIntrinsics");
                AndroidParagraph androidParagraph = new AndroidParagraph((AndroidParagraphIntrinsics) paragraphIntrinsics, this.maxLines - i3, z, Constraints$default);
                float height = androidParagraph.getHeight() + f;
                int lineCount = androidParagraph.getLineCount() + i3;
                arrayList.add(new ParagraphInfo(androidParagraph, paragraphIntrinsicInfo.getStartIndex(), paragraphIntrinsicInfo.getEndIndex(), i3, lineCount, f, height));
                if (!androidParagraph.getDidExceedMaxLines() && (lineCount != this.maxLines || i2 == CollectionsKt.getLastIndex(this.intrinsics.getInfoList$ui_text_release()))) {
                    i2++;
                    i3 = lineCount;
                    f = height;
                } else {
                    i3 = lineCount;
                    f = height;
                    z3 = true;
                    break;
                }
            }
            z3 = false;
            this.height = f;
            this.lineCount = i3;
            this.didExceedMaxLines = z3;
            this.paragraphInfoList = arrayList;
            this.width = Constraints.m381getMaxWidthimpl(j);
            ArrayList arrayList3 = new ArrayList(arrayList.size());
            int size2 = arrayList.size();
            for (int i4 = 0; i4 < size2; i4++) {
                ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i4);
                List placeholderRects = paragraphInfo.getParagraph().getPlaceholderRects();
                ArrayList arrayList4 = new ArrayList(placeholderRects.size());
                int size3 = placeholderRects.size();
                for (int i5 = 0; i5 < size3; i5++) {
                    Rect rect2 = (Rect) placeholderRects.get(i5);
                    if (rect2 != null) {
                        rect = paragraphInfo.toGlobal(rect2);
                    } else {
                        rect = null;
                    }
                    arrayList4.add(rect);
                }
                CollectionsKt.addAll(arrayList4, arrayList3);
            }
            int size4 = arrayList3.size();
            ArrayList arrayList5 = arrayList3;
            if (size4 < this.intrinsics.getPlaceholders().size()) {
                int size5 = this.intrinsics.getPlaceholders().size() - arrayList3.size();
                ArrayList arrayList6 = new ArrayList(size5);
                for (int i6 = 0; i6 < size5; i6++) {
                    arrayList6.add(null);
                }
                arrayList5 = CollectionsKt.plus(arrayList6, arrayList3);
            }
            this.placeholderRects = arrayList5;
            return;
        }
        throw new IllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.".toString());
    }

    private final AnnotatedString getAnnotatedString() {
        return this.intrinsics.getAnnotatedString();
    }

    private final void requireLineIndexInRange(int i) {
        int i2 = this.lineCount;
        boolean z = false;
        if (i >= 0 && i < i2) {
            z = true;
        }
        if (z) {
            return;
        }
        throw new IllegalArgumentException(("lineIndex(" + i + ") is out of bounds [0, " + i2 + ')').toString());
    }

    public final Rect getBoundingBox(int i) {
        boolean z;
        if (i >= 0 && i < getAnnotatedString().getText().length()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            List list = this.paragraphInfoList;
            ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) list).get(MultiParagraphKt.findParagraphByIndex(list, i));
            return paragraphInfo.toGlobal(paragraphInfo.getParagraph().getBoundingBox(paragraphInfo.toLocalIndex(i)));
        }
        throw new IllegalArgumentException(("offset(" + i + ") is out of bounds [0, " + getAnnotatedString().length() + ')').toString());
    }

    public final boolean getDidExceedMaxLines() {
        return this.didExceedMaxLines;
    }

    public final float getFirstBaseline() {
        List list = this.paragraphInfoList;
        if (((ArrayList) list).isEmpty()) {
            return 0.0f;
        }
        return ((ParagraphInfo) ((ArrayList) list).get(0)).getParagraph().getLineBaseline$ui_text_release(0);
    }

    public final float getHeight() {
        return this.height;
    }

    public final MultiParagraphIntrinsics getIntrinsics() {
        return this.intrinsics;
    }

    public final float getLastBaseline() {
        List list = this.paragraphInfoList;
        if (((ArrayList) list).isEmpty()) {
            return 0.0f;
        }
        Intrinsics.checkNotNullParameter(list, "<this>");
        ArrayList arrayList = (ArrayList) list;
        if (!arrayList.isEmpty()) {
            ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(CollectionsKt.getLastIndex(list));
            AndroidParagraph paragraph = paragraphInfo.getParagraph();
            return paragraphInfo.toGlobalYPosition(paragraph.getLineBaseline$ui_text_release(paragraph.getLineCount() - 1));
        }
        throw new NoSuchElementException("List is empty.");
    }

    public final int getLineCount() {
        return this.lineCount;
    }

    public final int getLineEnd(int i, boolean z) {
        requireLineIndexInRange(i);
        List list = this.paragraphInfoList;
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) list).get(MultiParagraphKt.findParagraphByLineIndex(list, i));
        return paragraphInfo.toGlobalIndex(paragraphInfo.getParagraph().getLineEnd(paragraphInfo.toLocalLineIndex(i), z));
    }

    public final int getLineForOffset(int i) {
        int findParagraphByIndex;
        int length = getAnnotatedString().length();
        List list = this.paragraphInfoList;
        if (i >= length) {
            findParagraphByIndex = CollectionsKt.getLastIndex(list);
        } else if (i < 0) {
            findParagraphByIndex = 0;
        } else {
            findParagraphByIndex = MultiParagraphKt.findParagraphByIndex(list, i);
        }
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) list).get(findParagraphByIndex);
        return paragraphInfo.toGlobalLineIndex(paragraphInfo.getParagraph().getLineForOffset(paragraphInfo.toLocalIndex(i)));
    }

    public final int getLineForVerticalPosition(float f) {
        int findParagraphByY;
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        List list = this.paragraphInfoList;
        if (i <= 0) {
            findParagraphByY = 0;
        } else if (f >= this.height) {
            findParagraphByY = CollectionsKt.getLastIndex(list);
        } else {
            findParagraphByY = MultiParagraphKt.findParagraphByY(list, f);
        }
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) list).get(findParagraphByY);
        if (paragraphInfo.getLength() == 0) {
            return Math.max(0, paragraphInfo.getStartIndex() - 1);
        }
        return paragraphInfo.toGlobalLineIndex(paragraphInfo.getParagraph().getLineForVerticalPosition(paragraphInfo.toLocalYPosition(f)));
    }

    public final int getLineStart(int i) {
        requireLineIndexInRange(i);
        List list = this.paragraphInfoList;
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) list).get(MultiParagraphKt.findParagraphByLineIndex(list, i));
        return paragraphInfo.toGlobalIndex(paragraphInfo.getParagraph().getLineStart(paragraphInfo.toLocalLineIndex(i)));
    }

    public final float getLineTop(int i) {
        requireLineIndexInRange(i);
        List list = this.paragraphInfoList;
        ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) list).get(MultiParagraphKt.findParagraphByLineIndex(list, i));
        return paragraphInfo.toGlobalYPosition(paragraphInfo.getParagraph().getLineTop(paragraphInfo.toLocalLineIndex(i)));
    }

    public final ResolvedTextDirection getParagraphDirection(int i) {
        boolean z;
        int findParagraphByIndex;
        if (i >= 0 && i <= getAnnotatedString().getText().length()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            int length = getAnnotatedString().length();
            List list = this.paragraphInfoList;
            if (i == length) {
                findParagraphByIndex = CollectionsKt.getLastIndex(list);
            } else {
                findParagraphByIndex = MultiParagraphKt.findParagraphByIndex(list, i);
            }
            ParagraphInfo paragraphInfo = (ParagraphInfo) ((ArrayList) list).get(findParagraphByIndex);
            return paragraphInfo.getParagraph().getParagraphDirection(paragraphInfo.toLocalIndex(i));
        }
        throw new IllegalArgumentException(("offset(" + i + ") is out of bounds [0, " + getAnnotatedString().length() + ']').toString());
    }

    public final List getParagraphInfoList$ui_text_release() {
        return this.paragraphInfoList;
    }

    public final List getPlaceholderRects() {
        return this.placeholderRects;
    }

    public final float getWidth() {
        return this.width;
    }

    /* renamed from: paint-iJQMabo  reason: not valid java name */
    public final void m295paintiJQMabo(Canvas canvas, long j, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle) {
        canvas.save();
        ArrayList arrayList = (ArrayList) this.paragraphInfoList;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i);
            paragraphInfo.getParagraph().m294paintiJQMabo(canvas, j, shadow, textDecoration, drawStyle);
            canvas.translate(0.0f, paragraphInfo.getParagraph().getHeight());
        }
        canvas.restore();
    }
}
