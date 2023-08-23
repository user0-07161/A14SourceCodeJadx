package androidx.compose.ui.platform;

import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AccessibilityIterators$LineTextSegmentIterator extends AccessibilityIterators$AbstractTextSegmentIterator {
    private static AccessibilityIterators$LineTextSegmentIterator lineInstance;
    private TextLayoutResult layoutResult;

    private final int getLineEdgeIndex(int i, ResolvedTextDirection resolvedTextDirection) {
        int lineEnd;
        TextLayoutResult textLayoutResult = this.layoutResult;
        if (textLayoutResult != null) {
            int lineStart = textLayoutResult.getLineStart(i);
            TextLayoutResult textLayoutResult2 = this.layoutResult;
            if (textLayoutResult2 != null) {
                if (resolvedTextDirection != textLayoutResult2.getParagraphDirection(lineStart)) {
                    TextLayoutResult textLayoutResult3 = this.layoutResult;
                    if (textLayoutResult3 != null) {
                        return textLayoutResult3.getLineStart(i);
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("layoutResult");
                    throw null;
                }
                TextLayoutResult textLayoutResult4 = this.layoutResult;
                if (textLayoutResult4 != null) {
                    lineEnd = textLayoutResult4.multiParagraph.getLineEnd(i, false);
                    return lineEnd - 1;
                }
                Intrinsics.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
            Intrinsics.throwUninitializedPropertyAccessException("layoutResult");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("layoutResult");
        throw null;
    }

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] following(int i) {
        int i2;
        if (getText().length() <= 0 || i >= getText().length()) {
            return null;
        }
        ResolvedTextDirection resolvedTextDirection = ResolvedTextDirection.Rtl;
        if (i < 0) {
            TextLayoutResult textLayoutResult = this.layoutResult;
            if (textLayoutResult != null) {
                i2 = textLayoutResult.getLineForOffset(0);
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
        } else {
            TextLayoutResult textLayoutResult2 = this.layoutResult;
            if (textLayoutResult2 != null) {
                int lineForOffset = textLayoutResult2.getLineForOffset(i);
                if (getLineEdgeIndex(lineForOffset, resolvedTextDirection) == i) {
                    i2 = lineForOffset;
                } else {
                    i2 = lineForOffset + 1;
                }
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
        }
        TextLayoutResult textLayoutResult3 = this.layoutResult;
        if (textLayoutResult3 != null) {
            if (i2 >= textLayoutResult3.getLineCount()) {
                return null;
            }
            return getRange(getLineEdgeIndex(i2, resolvedTextDirection), getLineEdgeIndex(i2, ResolvedTextDirection.Ltr) + 1);
        }
        Intrinsics.throwUninitializedPropertyAccessException("layoutResult");
        throw null;
    }

    public final void initialize(String text, TextLayoutResult layoutResult) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(layoutResult, "layoutResult");
        this.text = text;
        this.layoutResult = layoutResult;
    }

    @Override // androidx.compose.ui.platform.AccessibilityIterators$TextSegmentIterator
    public final int[] preceding(int i) {
        int i2;
        if (getText().length() <= 0 || i <= 0) {
            return null;
        }
        int length = getText().length();
        ResolvedTextDirection resolvedTextDirection = ResolvedTextDirection.Ltr;
        if (i > length) {
            TextLayoutResult textLayoutResult = this.layoutResult;
            if (textLayoutResult != null) {
                i2 = textLayoutResult.getLineForOffset(getText().length());
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
        } else {
            TextLayoutResult textLayoutResult2 = this.layoutResult;
            if (textLayoutResult2 != null) {
                int lineForOffset = textLayoutResult2.getLineForOffset(i);
                if (getLineEdgeIndex(lineForOffset, resolvedTextDirection) + 1 == i) {
                    i2 = lineForOffset;
                } else {
                    i2 = lineForOffset - 1;
                }
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("layoutResult");
                throw null;
            }
        }
        if (i2 < 0) {
            return null;
        }
        return getRange(getLineEdgeIndex(i2, ResolvedTextDirection.Rtl), getLineEdgeIndex(i2, resolvedTextDirection) + 1);
    }
}
