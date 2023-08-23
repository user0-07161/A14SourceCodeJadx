package androidx.compose.ui.text.platform.style;

import android.graphics.Paint;
import android.graphics.PathEffect;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import androidx.compose.ui.graphics.AndroidPathEffect;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DrawStyleSpan extends CharacterStyle implements UpdateAppearance {
    private final DrawStyle drawStyle;

    public DrawStyleSpan(DrawStyle drawStyle) {
        this.drawStyle = drawStyle;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        boolean z;
        boolean z2;
        boolean z3;
        Paint.Join join;
        boolean z4;
        boolean z5;
        Paint.Cap cap;
        PathEffect pathEffect;
        if (textPaint != null) {
            DrawStyle drawStyle = this.drawStyle;
            if (Intrinsics.areEqual(drawStyle, Fill.INSTANCE)) {
                textPaint.setStyle(Paint.Style.FILL);
            } else if (drawStyle instanceof Stroke) {
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setStrokeWidth(((Stroke) this.drawStyle).getWidth());
                textPaint.setStrokeMiter(((Stroke) this.drawStyle).getMiter());
                int m169getJoinLxFBmk8 = ((Stroke) this.drawStyle).m169getJoinLxFBmk8();
                boolean z6 = true;
                if (m169getJoinLxFBmk8 == 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    join = Paint.Join.MITER;
                } else {
                    if (m169getJoinLxFBmk8 == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (z2) {
                        join = Paint.Join.ROUND;
                    } else {
                        if (m169getJoinLxFBmk8 == 2) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        if (z3) {
                            join = Paint.Join.BEVEL;
                        } else {
                            join = Paint.Join.MITER;
                        }
                    }
                }
                textPaint.setStrokeJoin(join);
                int m168getCapKaPHkGw = ((Stroke) this.drawStyle).m168getCapKaPHkGw();
                if (m168getCapKaPHkGw == 0) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (z4) {
                    cap = Paint.Cap.BUTT;
                } else {
                    if (m168getCapKaPHkGw == 1) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (z5) {
                        cap = Paint.Cap.ROUND;
                    } else {
                        if (m168getCapKaPHkGw != 2) {
                            z6 = false;
                        }
                        if (z6) {
                            cap = Paint.Cap.SQUARE;
                        } else {
                            cap = Paint.Cap.BUTT;
                        }
                    }
                }
                textPaint.setStrokeCap(cap);
                androidx.compose.ui.graphics.PathEffect pathEffect2 = ((Stroke) this.drawStyle).getPathEffect();
                if (pathEffect2 != null) {
                    pathEffect = ((AndroidPathEffect) pathEffect2).getNativePathEffect();
                } else {
                    pathEffect = null;
                }
                textPaint.setPathEffect(pathEffect);
            }
        }
    }
}
