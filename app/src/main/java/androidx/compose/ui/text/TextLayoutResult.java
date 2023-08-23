package androidx.compose.ui.text;

import androidx.compose.animation.core.AnimationVector4D$$ExternalSyntheticOutline0;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextLayoutResult {
    private final float firstBaseline;
    private final float lastBaseline;
    private final TextLayoutInput layoutInput;
    private final MultiParagraph multiParagraph;
    private final List placeholderRects;
    private final long size;

    public TextLayoutResult(TextLayoutInput textLayoutInput, MultiParagraph multiParagraph, long j) {
        this.layoutInput = textLayoutInput;
        this.multiParagraph = multiParagraph;
        this.size = j;
        this.firstBaseline = multiParagraph.getFirstBaseline();
        this.lastBaseline = multiParagraph.getLastBaseline();
        this.placeholderRects = multiParagraph.getPlaceholderRects();
    }

    /* renamed from: copy-O0kMr_c  reason: not valid java name */
    public final TextLayoutResult m312copyO0kMr_c(TextLayoutInput textLayoutInput, long j) {
        return new TextLayoutResult(textLayoutInput, this.multiParagraph, j);
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        boolean z3;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextLayoutResult)) {
            return false;
        }
        TextLayoutResult textLayoutResult = (TextLayoutResult) obj;
        if (!Intrinsics.areEqual(this.layoutInput, textLayoutResult.layoutInput) || !Intrinsics.areEqual(this.multiParagraph, textLayoutResult.multiParagraph)) {
            return false;
        }
        if (this.size == textLayoutResult.size) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        if (this.firstBaseline == textLayoutResult.firstBaseline) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            return false;
        }
        if (this.lastBaseline == textLayoutResult.lastBaseline) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 && Intrinsics.areEqual(this.placeholderRects, textLayoutResult.placeholderRects)) {
            return true;
        }
        return false;
    }

    public final Rect getBoundingBox(int i) {
        return this.multiParagraph.getBoundingBox(i);
    }

    public final float getFirstBaseline() {
        return this.firstBaseline;
    }

    public final boolean getHasVisualOverflow() {
        boolean z;
        boolean z2;
        long j = this.size;
        MultiParagraph multiParagraph = this.multiParagraph;
        if (((int) (j >> 32)) < multiParagraph.getWidth()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return true;
        }
        if (!multiParagraph.getDidExceedMaxLines() && IntSize.m405getHeightimpl(j) >= multiParagraph.getHeight()) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z2) {
            return true;
        }
        return false;
    }

    public final float getLastBaseline() {
        return this.lastBaseline;
    }

    public final TextLayoutInput getLayoutInput() {
        return this.layoutInput;
    }

    public final int getLineCount() {
        return this.multiParagraph.getLineCount();
    }

    public final int getLineForOffset(int i) {
        return this.multiParagraph.getLineForOffset(i);
    }

    public final int getLineForVerticalPosition(float f) {
        return this.multiParagraph.getLineForVerticalPosition(f);
    }

    public final int getLineStart(int i) {
        return this.multiParagraph.getLineStart(i);
    }

    public final float getLineTop(int i) {
        return this.multiParagraph.getLineTop(i);
    }

    public final MultiParagraph getMultiParagraph() {
        return this.multiParagraph;
    }

    public final ResolvedTextDirection getParagraphDirection(int i) {
        return this.multiParagraph.getParagraphDirection(i);
    }

    public final List getPlaceholderRects() {
        return this.placeholderRects;
    }

    /* renamed from: getSize-YbymL2g  reason: not valid java name */
    public final long m313getSizeYbymL2g() {
        return this.size;
    }

    public final int hashCode() {
        int hashCode = this.multiParagraph.hashCode();
        int hashCode2 = Long.hashCode(this.size);
        return this.placeholderRects.hashCode() + AnimationVector4D$$ExternalSyntheticOutline0.m(this.lastBaseline, AnimationVector4D$$ExternalSyntheticOutline0.m(this.firstBaseline, (hashCode2 + ((hashCode + (this.layoutInput.hashCode() * 31)) * 31)) * 31, 31), 31);
    }

    public final String toString() {
        return "TextLayoutResult(layoutInput=" + this.layoutInput + ", multiParagraph=" + this.multiParagraph + ", size=" + ((Object) IntSize.m406toStringimpl(this.size)) + ", firstBaseline=" + this.firstBaseline + ", lastBaseline=" + this.lastBaseline + ", placeholderRects=" + this.placeholderRects + ')';
    }
}
