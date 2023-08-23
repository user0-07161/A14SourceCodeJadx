package androidx.compose.ui.text.platform.style;

import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BrushKt$ShaderBrush$1;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ShaderBrushSpan extends CharacterStyle implements UpdateAppearance {
    private final float alpha;
    private Pair cachedShader;
    private final BrushKt$ShaderBrush$1 shaderBrush;
    private long size;

    public ShaderBrushSpan(BrushKt$ShaderBrush$1 brushKt$ShaderBrush$1, float f) {
        long j;
        this.shaderBrush = brushKt$ShaderBrush$1;
        this.alpha = f;
        j = Size.Unspecified;
        this.size = j;
    }

    /* renamed from: setSize-uvyYCjk  reason: not valid java name */
    public final void m349setSizeuvyYCjk(long j) {
        this.size = j;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        long j;
        boolean z;
        Shader m90createShaderuvyYCjk;
        Intrinsics.checkNotNullParameter(textPaint, "textPaint");
        float f = this.alpha;
        if (!Float.isNaN(f)) {
            textPaint.setAlpha(MathKt.roundToInt(RangesKt.coerceIn(f, 0.0f, 1.0f) * 255));
        }
        long j2 = this.size;
        j = Size.Unspecified;
        boolean z2 = true;
        if (j2 == j) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        Pair pair = this.cachedShader;
        if (pair != null) {
            if (((Size) pair.getFirst()).m66unboximpl() != this.size) {
                z2 = false;
            }
            if (z2) {
                m90createShaderuvyYCjk = (Shader) pair.getSecond();
                textPaint.setShader(m90createShaderuvyYCjk);
                this.cachedShader = new Pair(Size.m62boximpl(this.size), m90createShaderuvyYCjk);
            }
        }
        m90createShaderuvyYCjk = this.shaderBrush.m90createShaderuvyYCjk();
        textPaint.setShader(m90createShaderuvyYCjk);
        this.cachedShader = new Pair(Size.m62boximpl(this.size), m90createShaderuvyYCjk);
    }
}
