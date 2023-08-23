package androidx.compose.ui.text;

import android.graphics.RectF;
import android.text.TextUtils;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.android.LayoutIntrinsics;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidParagraph {
    private final CharSequence charSequence;
    private final long constraints;
    private final TextLayout layout;
    private final int maxLines;
    private final AndroidParagraphIntrinsics paragraphIntrinsics;
    private final List placeholderRects;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02d8 A[LOOP:1: B:234:0x02d6->B:235:0x02d8, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x011a  */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r0v75, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r0v77, types: [android.text.Spannable] */
    /* JADX WARN: Type inference failed for: r0v79 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AndroidParagraph(androidx.compose.ui.text.platform.AndroidParagraphIntrinsics r25, int r26, boolean r27, long r28) {
        /*
            Method dump skipped, instructions count: 972
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.AndroidParagraph.<init>(androidx.compose.ui.text.platform.AndroidParagraphIntrinsics, int, boolean, long):void");
    }

    private final TextLayout constructTextLayout(int i, int i2, TextUtils.TruncateAt truncateAt, int i3, int i4, int i5, int i6, int i7) {
        CharSequence charSequence = this.charSequence;
        float width = getWidth();
        AndroidParagraphIntrinsics androidParagraphIntrinsics = this.paragraphIntrinsics;
        AndroidTextPaint textPaint$ui_text_release = androidParagraphIntrinsics.getTextPaint$ui_text_release();
        int textDirectionHeuristic$ui_text_release = androidParagraphIntrinsics.getTextDirectionHeuristic$ui_text_release();
        LayoutIntrinsics layoutIntrinsics$ui_text_release = androidParagraphIntrinsics.getLayoutIntrinsics$ui_text_release();
        TextStyle style = androidParagraphIntrinsics.getStyle();
        int i8 = AndroidParagraphHelper_androidKt.$r8$clinit;
        Intrinsics.checkNotNullParameter(style, "<this>");
        return new TextLayout(charSequence, width, textPaint$ui_text_release, i, truncateAt, textDirectionHeuristic$ui_text_release, i3, i5, i6, i7, i4, i2, layoutIntrinsics$ui_text_release);
    }

    public final Rect getBoundingBox(int i) {
        RectF boundingBox = this.layout.getBoundingBox(i);
        return new Rect(boundingBox.left, boundingBox.top, boundingBox.right, boundingBox.bottom);
    }

    public final boolean getDidExceedMaxLines() {
        return this.layout.getDidExceedMaxLines();
    }

    public final float getHeight() {
        return this.layout.getHeight();
    }

    public final float getLineBaseline$ui_text_release(int i) {
        return this.layout.getLineBaseline(i);
    }

    public final int getLineCount() {
        return this.layout.getLineCount();
    }

    public final int getLineEnd(int i, boolean z) {
        TextLayout textLayout = this.layout;
        if (z) {
            return textLayout.getLineVisibleEnd(i);
        }
        return textLayout.getLineEnd(i);
    }

    public final int getLineForOffset(int i) {
        return this.layout.getLineForOffset(i);
    }

    public final int getLineForVerticalPosition(float f) {
        return this.layout.getLineForVertical((int) f);
    }

    public final int getLineStart(int i) {
        return this.layout.getLineStart(i);
    }

    public final float getLineTop(int i) {
        return this.layout.getLineTop(i);
    }

    public final float getMinIntrinsicWidth() {
        return this.paragraphIntrinsics.getMinIntrinsicWidth();
    }

    public final ResolvedTextDirection getParagraphDirection(int i) {
        TextLayout textLayout = this.layout;
        if (textLayout.getParagraphDirection(textLayout.getLineForOffset(i)) == 1) {
            return ResolvedTextDirection.Ltr;
        }
        return ResolvedTextDirection.Rtl;
    }

    public final List getPlaceholderRects() {
        return this.placeholderRects;
    }

    public final Locale getTextLocale$ui_text_release() {
        Locale textLocale = this.paragraphIntrinsics.getTextPaint$ui_text_release().getTextLocale();
        Intrinsics.checkNotNullExpressionValue(textLocale, "paragraphIntrinsics.textPaint.textLocale");
        return textLocale;
    }

    public final float getWidth() {
        return Constraints.m381getMaxWidthimpl(this.constraints);
    }

    public final void paint(Canvas canvas, Brush brush, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle) {
        AndroidTextPaint textPaint$ui_text_release = this.paragraphIntrinsics.getTextPaint$ui_text_release();
        textPaint$ui_text_release.m341setBrush12SF9DM(brush, SizeKt.Size(getWidth(), getHeight()), f);
        textPaint$ui_text_release.setShadow(shadow);
        textPaint$ui_text_release.setTextDecoration(textDecoration);
        textPaint$ui_text_release.setDrawStyle(drawStyle);
        paint(canvas);
    }

    /* renamed from: paint-iJQMabo  reason: not valid java name */
    public final void m294paintiJQMabo(Canvas canvas, long j, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle) {
        AndroidTextPaint textPaint$ui_text_release = this.paragraphIntrinsics.getTextPaint$ui_text_release();
        textPaint$ui_text_release.m342setColor8_81llA(j);
        textPaint$ui_text_release.setShadow(shadow);
        textPaint$ui_text_release.setTextDecoration(textDecoration);
        textPaint$ui_text_release.setDrawStyle(drawStyle);
        paint(canvas);
    }

    private final void paint(Canvas canvas) {
        int i = AndroidCanvas_androidKt.$r8$clinit;
        android.graphics.Canvas internalCanvas = ((AndroidCanvas) canvas).getInternalCanvas();
        if (getDidExceedMaxLines()) {
            internalCanvas.save();
            internalCanvas.clipRect(0.0f, 0.0f, getWidth(), getHeight());
        }
        this.layout.paint(internalCanvas);
        if (getDidExceedMaxLines()) {
            internalCanvas.restore();
        }
    }
}
