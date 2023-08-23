package androidx.compose.ui.text.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.StaticLayout;
import androidx.core.os.BuildCompat;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TextLayout {
    private final int bottomPadding;
    private final boolean didExceedMaxLines;
    private final boolean fallbackLineSpacing;
    private final boolean includePadding;
    private final boolean isBoringLayout;
    private final int lastLineExtra;
    private final Paint.FontMetricsInt lastLineFontMetrics;
    private final Layout layout;
    private final Lazy layoutHelper$delegate;
    private final float leftPadding;
    private final int lineCount;
    private final Rect rect;
    private final float rightPadding;
    private final TextAndroidCanvas textCanvas;
    private final int topPadding;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0171  */
    /* JADX WARN: Type inference failed for: r13v4, types: [java.lang.CharSequence, android.text.SpannableString] */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [int] */
    /* JADX WARN: Type inference failed for: r5v11, types: [android.text.StaticLayout] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TextLayout(java.lang.CharSequence r37, float r38, androidx.compose.ui.text.platform.AndroidTextPaint r39, int r40, android.text.TextUtils.TruncateAt r41, int r42, int r43, int r44, int r45, int r46, int r47, int r48, androidx.compose.ui.text.android.LayoutIntrinsics r49) {
        /*
            Method dump skipped, instructions count: 612
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.TextLayout.<init>(java.lang.CharSequence, float, androidx.compose.ui.text.platform.AndroidTextPaint, int, android.text.TextUtils$TruncateAt, int, int, int, int, int, int, int, androidx.compose.ui.text.android.LayoutIntrinsics):void");
    }

    public final RectF getBoundingBox(int i) {
        boolean z;
        float secondaryHorizontal;
        float secondaryHorizontal2;
        float primaryHorizontal;
        float primaryHorizontal2;
        int lineForOffset = getLineForOffset(i);
        float lineTop = getLineTop(lineForOffset);
        float lineBottom = getLineBottom(lineForOffset);
        if (getParagraphDirection(lineForOffset) == 1) {
            z = true;
        } else {
            z = false;
        }
        boolean isRtlCharAt = this.layout.isRtlCharAt(i);
        if (z && !isRtlCharAt) {
            secondaryHorizontal = getPrimaryHorizontal(i, false);
            secondaryHorizontal2 = getPrimaryHorizontal(i + 1, true);
        } else {
            if (z && isRtlCharAt) {
                primaryHorizontal = getSecondaryHorizontal(i, false);
                primaryHorizontal2 = getSecondaryHorizontal(i + 1, true);
            } else if (isRtlCharAt) {
                primaryHorizontal = getPrimaryHorizontal(i, false);
                primaryHorizontal2 = getPrimaryHorizontal(i + 1, true);
            } else {
                secondaryHorizontal = getSecondaryHorizontal(i, false);
                secondaryHorizontal2 = getSecondaryHorizontal(i + 1, true);
            }
            float f = primaryHorizontal;
            secondaryHorizontal = primaryHorizontal2;
            secondaryHorizontal2 = f;
        }
        return new RectF(secondaryHorizontal, lineTop, secondaryHorizontal2, lineBottom);
    }

    public final boolean getDidExceedMaxLines() {
        return this.didExceedMaxLines;
    }

    public final int getHeight() {
        int height;
        boolean z = this.didExceedMaxLines;
        Layout layout = this.layout;
        if (z) {
            height = layout.getLineBottom(this.lineCount - 1);
        } else {
            height = layout.getHeight();
        }
        return height + this.topPadding + this.bottomPadding + this.lastLineExtra;
    }

    public final boolean getIncludePadding() {
        return this.includePadding;
    }

    public final Layout getLayout() {
        return this.layout;
    }

    public final float getLineBaseline(int i) {
        float lineBaseline;
        Paint.FontMetricsInt fontMetricsInt;
        float f = this.topPadding;
        if (i == this.lineCount - 1 && (fontMetricsInt = this.lastLineFontMetrics) != null) {
            lineBaseline = getLineTop(i) - fontMetricsInt.ascent;
        } else {
            lineBaseline = this.layout.getLineBaseline(i);
        }
        return f + lineBaseline;
    }

    public final float getLineBottom(int i) {
        int i2;
        Paint.FontMetricsInt fontMetricsInt;
        int i3 = this.lineCount;
        int i4 = i3 - 1;
        Layout layout = this.layout;
        if (i == i4 && (fontMetricsInt = this.lastLineFontMetrics) != null) {
            return layout.getLineBottom(i - 1) + fontMetricsInt.bottom;
        }
        float lineBottom = this.topPadding + layout.getLineBottom(i);
        if (i == i3 - 1) {
            i2 = this.bottomPadding;
        } else {
            i2 = 0;
        }
        return lineBottom + i2;
    }

    public final int getLineCount() {
        return this.lineCount;
    }

    public final int getLineEllipsisCount(int i) {
        return this.layout.getEllipsisCount(i);
    }

    public final int getLineEllipsisOffset(int i) {
        return this.layout.getEllipsisStart(i);
    }

    public final int getLineEnd(int i) {
        Layout layout = this.layout;
        if (layout.getEllipsisStart(i) == 0) {
            return layout.getLineEnd(i);
        }
        return layout.getText().length();
    }

    public final int getLineForOffset(int i) {
        return this.layout.getLineForOffset(i);
    }

    public final int getLineForVertical(int i) {
        return this.layout.getLineForVertical(this.topPadding + i);
    }

    public final int getLineStart(int i) {
        return this.layout.getLineStart(i);
    }

    public final float getLineTop(int i) {
        int i2;
        float lineTop = this.layout.getLineTop(i);
        if (i == 0) {
            i2 = 0;
        } else {
            i2 = this.topPadding;
        }
        return lineTop + i2;
    }

    public final int getLineVisibleEnd(int i) {
        Layout layout = this.layout;
        if (layout.getEllipsisStart(i) == 0) {
            return layout.getLineVisibleEnd(i);
        }
        return layout.getEllipsisStart(i) + layout.getLineStart(i);
    }

    public final int getParagraphDirection(int i) {
        return this.layout.getParagraphDirection(i);
    }

    public final float getPrimaryHorizontal(int i, boolean z) {
        float f;
        float horizontalPosition = ((LayoutHelper) this.layoutHelper$delegate.getValue()).getHorizontalPosition(i, true, z);
        if (getLineForOffset(i) == this.lineCount - 1) {
            f = this.leftPadding + this.rightPadding;
        } else {
            f = 0.0f;
        }
        return horizontalPosition + f;
    }

    public final float getSecondaryHorizontal(int i, boolean z) {
        float f;
        float horizontalPosition = ((LayoutHelper) this.layoutHelper$delegate.getValue()).getHorizontalPosition(i, false, z);
        if (getLineForOffset(i) == this.lineCount - 1) {
            f = this.leftPadding + this.rightPadding;
        } else {
            f = 0.0f;
        }
        return horizontalPosition + f;
    }

    public final CharSequence getText() {
        CharSequence text = this.layout.getText();
        Intrinsics.checkNotNullExpressionValue(text, "layout.text");
        return text;
    }

    public final boolean isFallbackLinespacingApplied$ui_text_release() {
        boolean z = this.isBoringLayout;
        Layout layout = this.layout;
        if (z) {
            Intrinsics.checkNotNull(layout, "null cannot be cast to non-null type android.text.BoringLayout");
            int i = BuildCompat.$r8$clinit;
            return ((BoringLayout) layout).isFallbackLineSpacingEnabled();
        }
        Intrinsics.checkNotNull(layout, "null cannot be cast to non-null type android.text.StaticLayout");
        int i2 = BuildCompat.$r8$clinit;
        return ((StaticLayout) layout).isFallbackLineSpacingEnabled();
    }

    public final boolean isRtlCharAt(int i) {
        return this.layout.isRtlCharAt(i);
    }

    public final void paint(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (!canvas.getClipBounds(this.rect)) {
            return;
        }
        int i = this.topPadding;
        if (i != 0) {
            canvas.translate(0.0f, i);
        }
        TextAndroidCanvas textAndroidCanvas = this.textCanvas;
        textAndroidCanvas.setCanvas(canvas);
        this.layout.draw(textAndroidCanvas);
        if (i != 0) {
            canvas.translate(0.0f, (-1) * i);
        }
    }
}
