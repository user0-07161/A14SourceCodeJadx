package androidx.compose.ui.graphics;

import java.util.List;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface Canvas {
    /* renamed from: clipPath-mtrdD-E */
    void mo67clipPathmtrdDE(Path path, int i);

    /* renamed from: clipRect-N_I0leg */
    void mo68clipRectN_I0leg(float f, float f2, float f3, float f4, int i);

    /* renamed from: concat-58bKbWc */
    void mo69concat58bKbWc(float[] fArr);

    void disableZ();

    void drawArc(float f, float f2, float f3, float f4, float f5, float f6, boolean z, AndroidPaint androidPaint);

    /* renamed from: drawCircle-9KIMszo */
    void mo70drawCircle9KIMszo(float f, long j, AndroidPaint androidPaint);

    /* renamed from: drawImage-d-4ec7I */
    void mo71drawImaged4ec7I(AndroidPaint androidPaint);

    /* renamed from: drawImageRect-HPBpro0 */
    void mo72drawImageRectHPBpro0(AndroidPaint androidPaint);

    /* renamed from: drawLine-Wko1d7g */
    void mo73drawLineWko1d7g(long j, long j2, AndroidPaint androidPaint);

    void drawOval(float f, float f2, float f3, float f4, AndroidPaint androidPaint);

    void drawPath(Path path, AndroidPaint androidPaint);

    /* renamed from: drawPoints-O7TthRY */
    void mo74drawPointsO7TthRY(int i, AndroidPaint androidPaint, List list);

    void drawRect(float f, float f2, float f3, float f4, AndroidPaint androidPaint);

    void drawRoundRect(float f, float f2, float f3, float f4, float f5, float f6, AndroidPaint androidPaint);

    void enableZ();

    void restore();

    void rotate(float f);

    void save();

    void scale(float f, float f2);

    void translate(float f, float f2);
}
