package androidx.compose.ui.text;

import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ParagraphStyleKt {
    private static final long DefaultLineHeight;

    static {
        long j;
        TextUnit.Companion companion = TextUnit.Companion;
        j = TextUnit.Unspecified;
        DefaultLineHeight = j;
    }

    public static final ParagraphStyle resolveParagraphStyleDefaults(ParagraphStyle style, LayoutDirection direction) {
        int i;
        boolean z;
        long m299getLineHeightXSAIIZE;
        int i2;
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(direction, "direction");
        TextAlign m300getTextAlignbuA522U = style.m300getTextAlignbuA522U();
        int i3 = 5;
        if (m300getTextAlignbuA522U != null) {
            i = m300getTextAlignbuA522U.m369unboximpl();
        } else {
            i = 5;
        }
        TextAlign m368boximpl = TextAlign.m368boximpl(i);
        TextDirection m301getTextDirectionmmuk1to = style.m301getTextDirectionmmuk1to();
        int i4 = 1;
        if (m301getTextDirectionmmuk1to != null && m301getTextDirectionmmuk1to.m371unboximpl() == 3) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            int ordinal = direction.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    throw new NoWhenBranchMatchedException();
                }
            } else {
                i3 = 4;
            }
        } else if (m301getTextDirectionmmuk1to == null) {
            int ordinal2 = direction.ordinal();
            if (ordinal2 != 0) {
                if (ordinal2 == 1) {
                    i3 = 2;
                } else {
                    throw new NoWhenBranchMatchedException();
                }
            } else {
                i3 = 1;
            }
        } else {
            i3 = m301getTextDirectionmmuk1to.m371unboximpl();
        }
        TextDirection m370boximpl = TextDirection.m370boximpl(i3);
        if (TextUnitKt.m415isUnspecifiedR2X_6o(style.m299getLineHeightXSAIIZE())) {
            m299getLineHeightXSAIIZE = DefaultLineHeight;
        } else {
            m299getLineHeightXSAIIZE = style.m299getLineHeightXSAIIZE();
        }
        long j = m299getLineHeightXSAIIZE;
        TextIndent textIndent = style.getTextIndent();
        if (textIndent == null) {
            textIndent = TextIndent.None;
        }
        TextIndent textIndent2 = textIndent;
        LineHeightStyle lineHeightStyle = style.getLineHeightStyle();
        LineBreak m298getLineBreakLgCVezo = style.m298getLineBreakLgCVezo();
        if (m298getLineBreakLgCVezo == null) {
            i2 = LineBreak.Simple;
        } else {
            i2 = m298getLineBreakLgCVezo.m356unboximpl();
        }
        LineBreak m355boximpl = LineBreak.m355boximpl(i2);
        Hyphens m297getHyphensEaSxIns = style.m297getHyphensEaSxIns();
        if (m297getHyphensEaSxIns != null) {
            i4 = m297getHyphensEaSxIns.m354unboximpl();
        }
        Hyphens m353boximpl = Hyphens.m353boximpl(i4);
        TextMotion textMotion = style.getTextMotion();
        if (textMotion == null) {
            textMotion = TextMotion.Static;
        }
        return new ParagraphStyle(m368boximpl, m370boximpl, j, textIndent2, lineHeightStyle, m355boximpl, m353boximpl, textMotion);
    }
}
