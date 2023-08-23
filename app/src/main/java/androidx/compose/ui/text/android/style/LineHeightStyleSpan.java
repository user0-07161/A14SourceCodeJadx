package androidx.compose.ui.text.android.style;

import android.graphics.Paint;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LineHeightStyleSpan implements android.text.style.LineHeightSpan {
    private int ascent;
    private int descent;
    private final int endIndex;
    private int firstAscent;
    private int firstAscentDiff;
    private int lastDescent;
    private int lastDescentDiff;
    private final float lineHeight;
    private final int startIndex = 0;
    private final float topRatio;
    private final boolean trimFirstLineTop;
    private final boolean trimLastLineBottom;

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        if (r0 != false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public LineHeightStyleSpan(float r1, int r2, boolean r3, boolean r4, float r5) {
        /*
            r0 = this;
            r0.<init>()
            r0.lineHeight = r1
            r1 = 0
            r0.startIndex = r1
            r0.endIndex = r2
            r0.trimFirstLineTop = r3
            r0.trimLastLineBottom = r4
            r0.topRatio = r5
            r0 = 0
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            r2 = 1
            if (r0 > 0) goto L1e
            r0 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r0 > 0) goto L1e
            r0 = r2
            goto L1f
        L1e:
            r0 = r1
        L1f:
            if (r0 != 0) goto L2c
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r0 != 0) goto L29
            r0 = r2
            goto L2a
        L29:
            r0 = r1
        L2a:
            if (r0 == 0) goto L2d
        L2c:
            r1 = r2
        L2d:
            if (r1 == 0) goto L30
            return
        L30:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "topRatio should be in [0..1] range or -1"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.style.LineHeightStyleSpan.<init>(float, int, boolean, boolean, float):void");
    }

    @Override // android.text.style.LineHeightSpan
    public final void chooseHeight(CharSequence text, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
        boolean z;
        boolean z2;
        int i5;
        int i6;
        double ceil;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(fontMetricsInt, "fontMetricsInt");
        if (fontMetricsInt.descent - fontMetricsInt.ascent <= 0) {
            return;
        }
        boolean z3 = true;
        if (i == this.startIndex) {
            z = true;
        } else {
            z = false;
        }
        if (i2 == this.endIndex) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && z2 && this.trimFirstLineTop && this.trimLastLineBottom) {
            return;
        }
        if (z) {
            int ceil2 = (int) Math.ceil(this.lineHeight);
            int i7 = ceil2 - (fontMetricsInt.descent - fontMetricsInt.ascent);
            float f = this.topRatio;
            if (f != -1.0f) {
                z3 = false;
            }
            if (z3) {
                f = Math.abs(fontMetricsInt.ascent) / (fontMetricsInt.descent - fontMetricsInt.ascent);
            }
            if (i7 <= 0) {
                ceil = Math.ceil(i7 * f);
            } else {
                ceil = Math.ceil((1.0f - f) * i7);
            }
            int i8 = fontMetricsInt.descent;
            int i9 = ((int) ceil) + i8;
            this.descent = i9;
            int i10 = i9 - ceil2;
            this.ascent = i10;
            if (this.trimFirstLineTop) {
                i10 = fontMetricsInt.ascent;
            }
            this.firstAscent = i10;
            if (this.trimLastLineBottom) {
                i9 = i8;
            }
            this.lastDescent = i9;
            this.firstAscentDiff = fontMetricsInt.ascent - i10;
            this.lastDescentDiff = i9 - i8;
        }
        if (z) {
            i5 = this.firstAscent;
        } else {
            i5 = this.ascent;
        }
        fontMetricsInt.ascent = i5;
        if (z2) {
            i6 = this.lastDescent;
        } else {
            i6 = this.descent;
        }
        fontMetricsInt.descent = i6;
    }

    public final LineHeightStyleSpan copy$ui_text_release(int i, boolean z) {
        return new LineHeightStyleSpan(this.lineHeight, i, z, this.trimLastLineBottom, this.topRatio);
    }

    public final int getFirstAscentDiff() {
        return this.firstAscentDiff;
    }

    public final int getLastDescentDiff() {
        return this.lastDescentDiff;
    }

    public final boolean getTrimLastLineBottom() {
        return this.trimLastLineBottom;
    }
}
