package androidx.compose.ui.text.android;

import android.graphics.Rect;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import androidx.compose.ui.text.android.style.LineHeightStyleSpan;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TextLayoutKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Pair EmptyPair = new Pair(0, 0);

    public static final Pair access$getLineHeightPaddings(LineHeightStyleSpan[] lineHeightStyleSpanArr) {
        int i = 0;
        int i2 = 0;
        for (LineHeightStyleSpan lineHeightStyleSpan : lineHeightStyleSpanArr) {
            if (lineHeightStyleSpan.getFirstAscentDiff() < 0) {
                i = Math.max(i, Math.abs(lineHeightStyleSpan.getFirstAscentDiff()));
            }
            if (lineHeightStyleSpan.getLastDescentDiff() < 0) {
                i2 = Math.max(i, Math.abs(lineHeightStyleSpan.getLastDescentDiff()));
            }
        }
        if (i == 0 && i2 == 0) {
            return EmptyPair;
        }
        return new Pair(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static final Pair access$getVerticalPaddings(TextLayout textLayout) {
        int topPadding;
        int bottomPadding;
        if (!textLayout.getIncludePadding() && !textLayout.isFallbackLinespacingApplied$ui_text_release()) {
            TextPaint paint = textLayout.getLayout().getPaint();
            CharSequence text = textLayout.getLayout().getText();
            Intrinsics.checkNotNullExpressionValue(paint, "paint");
            Intrinsics.checkNotNullExpressionValue(text, "text");
            Rect charSequenceBounds = PaintExtensionsKt.getCharSequenceBounds(paint, text, textLayout.getLayout().getLineStart(0), textLayout.getLayout().getLineEnd(0));
            int lineAscent = textLayout.getLayout().getLineAscent(0);
            int i = charSequenceBounds.top;
            if (i < lineAscent) {
                topPadding = lineAscent - i;
            } else {
                topPadding = textLayout.getLayout().getTopPadding();
            }
            if (textLayout.getLineCount() != 1) {
                int lineCount = textLayout.getLineCount() - 1;
                charSequenceBounds = PaintExtensionsKt.getCharSequenceBounds(paint, text, textLayout.getLayout().getLineStart(lineCount), textLayout.getLayout().getLineEnd(lineCount));
            }
            int lineDescent = textLayout.getLayout().getLineDescent(textLayout.getLineCount() - 1);
            int i2 = charSequenceBounds.bottom;
            if (i2 > lineDescent) {
                bottomPadding = i2 - lineDescent;
            } else {
                bottomPadding = textLayout.getLayout().getBottomPadding();
            }
            if (topPadding == 0 && bottomPadding == 0) {
                return EmptyPair;
            }
            return new Pair(Integer.valueOf(topPadding), Integer.valueOf(bottomPadding));
        }
        return new Pair(0, 0);
    }

    public static final TextDirectionHeuristic getTextDirectionHeuristic(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                TextDirectionHeuristic FIRSTSTRONG_LTR = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                                Intrinsics.checkNotNullExpressionValue(FIRSTSTRONG_LTR, "FIRSTSTRONG_LTR");
                                return FIRSTSTRONG_LTR;
                            }
                            TextDirectionHeuristic LOCALE = TextDirectionHeuristics.LOCALE;
                            Intrinsics.checkNotNullExpressionValue(LOCALE, "LOCALE");
                            return LOCALE;
                        }
                        TextDirectionHeuristic ANYRTL_LTR = TextDirectionHeuristics.ANYRTL_LTR;
                        Intrinsics.checkNotNullExpressionValue(ANYRTL_LTR, "ANYRTL_LTR");
                        return ANYRTL_LTR;
                    }
                    TextDirectionHeuristic FIRSTSTRONG_RTL = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    Intrinsics.checkNotNullExpressionValue(FIRSTSTRONG_RTL, "FIRSTSTRONG_RTL");
                    return FIRSTSTRONG_RTL;
                }
                TextDirectionHeuristic FIRSTSTRONG_LTR2 = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                Intrinsics.checkNotNullExpressionValue(FIRSTSTRONG_LTR2, "FIRSTSTRONG_LTR");
                return FIRSTSTRONG_LTR2;
            }
            TextDirectionHeuristic RTL = TextDirectionHeuristics.RTL;
            Intrinsics.checkNotNullExpressionValue(RTL, "RTL");
            return RTL;
        }
        TextDirectionHeuristic LTR = TextDirectionHeuristics.LTR;
        Intrinsics.checkNotNullExpressionValue(LTR, "LTR");
        return LTR;
    }
}
