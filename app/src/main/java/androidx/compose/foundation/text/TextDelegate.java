package androidx.compose.foundation.text;

import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextDelegate {
    private final Density density;
    private final FontFamilyResolverImpl fontFamilyResolver;
    private LayoutDirection intrinsicsLayoutDirection;
    private final int maxLines;
    private final int minLines;
    private final int overflow;
    private MultiParagraphIntrinsics paragraphIntrinsics;
    private final List placeholders;
    private final boolean softWrap;
    private final TextStyle style;
    private final AnnotatedString text;

    public TextDelegate(AnnotatedString annotatedString, TextStyle textStyle, int i, int i2, boolean z, int i3, Density density, FontFamilyResolverImpl fontFamilyResolverImpl) {
        boolean z2;
        boolean z3;
        EmptyList emptyList = EmptyList.INSTANCE;
        this.text = annotatedString;
        this.style = textStyle;
        this.maxLines = i;
        this.minLines = i2;
        this.softWrap = z;
        this.overflow = i3;
        this.density = density;
        this.fontFamilyResolver = fontFamilyResolverImpl;
        this.placeholders = emptyList;
        if (i > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            if (i2 > 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                if (i2 <= i) {
                    return;
                }
                throw new IllegalStateException("Check failed.".toString());
            }
            throw new IllegalStateException("Check failed.".toString());
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final Density getDensity() {
        return this.density;
    }

    public final FontFamilyResolverImpl getFontFamilyResolver() {
        return this.fontFamilyResolver;
    }

    public final int getMaxLines() {
        return this.maxLines;
    }

    public final int getMinLines() {
        return this.minLines;
    }

    /* renamed from: getOverflow-gIe3tQ8  reason: not valid java name */
    public final int m17getOverflowgIe3tQ8() {
        return this.overflow;
    }

    public final boolean getSoftWrap() {
        return this.softWrap;
    }

    public final TextStyle getStyle() {
        return this.style;
    }

    public final AnnotatedString getText() {
        return this.text;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b3, code lost:
        if (r2 == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d0, code lost:
        if (androidx.compose.ui.unit.Constraints.m380getMaxHeightimpl(r27) == androidx.compose.ui.unit.Constraints.m380getMaxHeightimpl(r1.m310getConstraintsmsEJaDk())) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00d2, code lost:
        r1 = true;
     */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x021b  */
    /* renamed from: layout-NN6Ew-U  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.ui.text.TextLayoutResult m18layoutNN6EwU(long r27, androidx.compose.ui.unit.LayoutDirection r29, androidx.compose.ui.text.TextLayoutResult r30) {
        /*
            Method dump skipped, instructions count: 551
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.TextDelegate.m18layoutNN6EwU(long, androidx.compose.ui.unit.LayoutDirection, androidx.compose.ui.text.TextLayoutResult):androidx.compose.ui.text.TextLayoutResult");
    }
}
