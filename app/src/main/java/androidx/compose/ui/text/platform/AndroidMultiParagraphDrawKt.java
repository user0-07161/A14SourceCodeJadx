package androidx.compose.ui.text.platform;

import android.graphics.Matrix;
import android.graphics.Shader;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt;
import androidx.compose.ui.graphics.BrushKt$ShaderBrush$1;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.ParagraphInfo;
import androidx.compose.ui.text.style.TextDecoration;
import java.util.ArrayList;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AndroidMultiParagraphDrawKt {
    public static final void drawMultiParagraph(MultiParagraph multiParagraph, Canvas canvas, Brush brush, float f, Shadow shadow, TextDecoration textDecoration, DrawStyle drawStyle) {
        canvas.save();
        if (((ArrayList) multiParagraph.getParagraphInfoList$ui_text_release()).size() <= 1) {
            ArrayList arrayList = (ArrayList) multiParagraph.getParagraphInfoList$ui_text_release();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i);
                paragraphInfo.getParagraph().paint(canvas, brush, f, shadow, textDecoration, drawStyle);
                canvas.translate(0.0f, paragraphInfo.getParagraph().getHeight());
            }
        } else if (brush instanceof BrushKt$ShaderBrush$1) {
            ArrayList arrayList2 = (ArrayList) multiParagraph.getParagraphInfoList$ui_text_release();
            int size2 = arrayList2.size();
            float f2 = 0.0f;
            float f3 = 0.0f;
            for (int i2 = 0; i2 < size2; i2++) {
                ParagraphInfo paragraphInfo2 = (ParagraphInfo) arrayList2.get(i2);
                f3 += paragraphInfo2.getParagraph().getHeight();
                f2 = Math.max(f2, paragraphInfo2.getParagraph().getWidth());
            }
            SizeKt.Size(f2, f3);
            Shader m90createShaderuvyYCjk = ((BrushKt$ShaderBrush$1) brush).m90createShaderuvyYCjk();
            Matrix matrix = new Matrix();
            m90createShaderuvyYCjk.getLocalMatrix(matrix);
            ArrayList arrayList3 = (ArrayList) multiParagraph.getParagraphInfoList$ui_text_release();
            int size3 = arrayList3.size();
            for (int i3 = 0; i3 < size3; i3++) {
                ParagraphInfo paragraphInfo3 = (ParagraphInfo) arrayList3.get(i3);
                paragraphInfo3.getParagraph().paint(canvas, BrushKt.ShaderBrush(m90createShaderuvyYCjk), f, shadow, textDecoration, drawStyle);
                canvas.translate(0.0f, paragraphInfo3.getParagraph().getHeight());
                matrix.setTranslate(0.0f, -paragraphInfo3.getParagraph().getHeight());
                m90createShaderuvyYCjk.setLocalMatrix(matrix);
            }
        }
        canvas.restore();
    }
}
