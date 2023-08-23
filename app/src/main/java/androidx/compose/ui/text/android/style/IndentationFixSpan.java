package androidx.compose.ui.text.android.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;
import androidx.compose.ui.text.android.TextLayoutKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class IndentationFixSpan implements LeadingMarginSpan {
    @Override // android.text.style.LeadingMarginSpan
    public final void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
        if (layout == null || paint == null) {
            return;
        }
        int lineForOffset = layout.getLineForOffset(i6);
        if (lineForOffset == layout.getLineCount() - 1) {
            int i8 = TextLayoutKt.$r8$clinit;
            if (layout.getEllipsisCount(lineForOffset) > 0) {
                float ellipsizedRightPadding = IndentationFixSpanKt.getEllipsizedRightPadding(layout, lineForOffset, paint) + IndentationFixSpanKt.getEllipsizedLeftPadding(layout, lineForOffset, paint);
                if (ellipsizedRightPadding == 0.0f) {
                    return;
                }
                Intrinsics.checkNotNull(canvas);
                canvas.translate(ellipsizedRightPadding, 0.0f);
            }
        }
    }

    @Override // android.text.style.LeadingMarginSpan
    public final int getLeadingMargin(boolean z) {
        return 0;
    }
}
