package androidx.compose.ui.text.platform;

import android.text.TextPaint;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt$ShaderBrush$1;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.text.style.TextDecoration;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidTextPaint extends TextPaint {
    private final AndroidPaint composePaint;
    private DrawStyle drawStyle;
    private Shadow shadow;
    private TextDecoration textDecoration;

    public AndroidTextPaint(float f) {
        super(1);
        TextDecoration textDecoration;
        Shadow shadow;
        ((TextPaint) this).density = f;
        this.composePaint = new AndroidPaint(this);
        textDecoration = TextDecoration.None;
        this.textDecoration = textDecoration;
        shadow = Shadow.None;
        this.shadow = shadow;
    }

    /* renamed from: setBrush-12SF9DM  reason: not valid java name */
    public final void m341setBrush12SF9DM(Brush brush, long j, float f) {
        long j2;
        boolean z;
        float coerceIn;
        if (brush instanceof BrushKt$ShaderBrush$1) {
            Size.Companion companion = Size.Companion;
            j2 = Size.Unspecified;
            if (j != j2) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                AndroidPaint androidPaint = this.composePaint;
                if (Float.isNaN(f)) {
                    coerceIn = this.composePaint.getAlpha();
                } else {
                    coerceIn = RangesKt.coerceIn(f, 0.0f, 1.0f);
                }
                brush.mo89applyToPq9zytI(coerceIn, j, androidPaint);
                return;
            }
        }
        if (brush == null) {
            this.composePaint.setShader(null);
        }
    }

    /* renamed from: setColor-8_81llA  reason: not valid java name */
    public final void m342setColor8_81llA(long j) {
        long j2;
        boolean z;
        ColorKt.m100toArgb8_81llA(j);
        Color.Companion companion = Color.Companion;
        j2 = Color.Unspecified;
        if (j != j2) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.composePaint.m82setColor8_81llA(j);
            this.composePaint.setShader(null);
        }
    }

    public final void setDrawStyle(DrawStyle drawStyle) {
        if (drawStyle != null && !Intrinsics.areEqual(this.drawStyle, drawStyle)) {
            this.drawStyle = drawStyle;
            if (Intrinsics.areEqual(drawStyle, Fill.INSTANCE)) {
                this.composePaint.m86setStylek9PVt8s(0);
            } else if (drawStyle instanceof Stroke) {
                this.composePaint.m86setStylek9PVt8s(1);
                Stroke stroke = (Stroke) drawStyle;
                this.composePaint.setStrokeWidth(stroke.getWidth());
                this.composePaint.setStrokeMiterLimit(stroke.getMiter());
                this.composePaint.m85setStrokeJoinWw9F2mQ(stroke.m169getJoinLxFBmk8());
                this.composePaint.m84setStrokeCapBeK7IIE(stroke.m168getCapKaPHkGw());
                this.composePaint.setPathEffect(stroke.getPathEffect());
            }
        }
    }

    public final void setShadow(Shadow shadow) {
        Shadow shadow2;
        boolean z;
        if (shadow != null && !Intrinsics.areEqual(this.shadow, shadow)) {
            this.shadow = shadow;
            shadow2 = Shadow.None;
            if (Intrinsics.areEqual(shadow, shadow2)) {
                clearShadowLayer();
                return;
            }
            float blurRadius = this.shadow.getBlurRadius();
            if (blurRadius == 0.0f) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                blurRadius = Float.MIN_VALUE;
            }
            setShadowLayer(blurRadius, Offset.m45getXimpl(this.shadow.m116getOffsetF1C5BW0()), Offset.m46getYimpl(this.shadow.m116getOffsetF1C5BW0()), ColorKt.m100toArgb8_81llA(this.shadow.m115getColor0d7_KjU()));
        }
    }

    public final void setTextDecoration(TextDecoration textDecoration) {
        TextDecoration textDecoration2;
        TextDecoration textDecoration3;
        if (textDecoration != null && !Intrinsics.areEqual(this.textDecoration, textDecoration)) {
            this.textDecoration = textDecoration;
            textDecoration2 = TextDecoration.Underline;
            setUnderlineText(textDecoration.contains(textDecoration2));
            TextDecoration textDecoration4 = this.textDecoration;
            textDecoration3 = TextDecoration.LineThrough;
            setStrikeThruText(textDecoration4.contains(textDecoration3));
        }
    }
}
