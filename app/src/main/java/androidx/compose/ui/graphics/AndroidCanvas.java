package androidx.compose.ui.graphics;

import android.graphics.Rect;
import android.graphics.Region;
import androidx.compose.ui.geometry.Offset;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidCanvas implements Canvas {
    private android.graphics.Canvas internalCanvas;

    public AndroidCanvas() {
        android.graphics.Canvas canvas;
        canvas = AndroidCanvas_androidKt.EmptyCanvas;
        this.internalCanvas = canvas;
        new Rect();
        new Rect();
    }

    private final void drawLines(int i, AndroidPaint androidPaint, List list) {
        if (list.size() >= 2) {
            IntProgression step = RangesKt.step(RangesKt.until(0, list.size() - 1), i);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if ((step2 <= 0 || first > last) && (step2 >= 0 || last > first)) {
                return;
            }
            while (true) {
                long m51unboximpl = ((Offset) list.get(first)).m51unboximpl();
                long m51unboximpl2 = ((Offset) list.get(first + 1)).m51unboximpl();
                this.internalCanvas.drawLine(Offset.m45getXimpl(m51unboximpl), Offset.m46getYimpl(m51unboximpl), Offset.m45getXimpl(m51unboximpl2), Offset.m46getYimpl(m51unboximpl2), androidPaint.asFrameworkPaint());
                if (first != last) {
                    first += step2;
                } else {
                    return;
                }
            }
        }
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: clipPath-mtrdD-E  reason: not valid java name */
    public final void mo67clipPathmtrdDE(Path path, int i) {
        boolean z;
        Region.Op op;
        Intrinsics.checkNotNullParameter(path, "path");
        android.graphics.Canvas canvas = this.internalCanvas;
        if (path instanceof AndroidPath) {
            android.graphics.Path internalPath = ((AndroidPath) path).getInternalPath();
            if (i == 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                op = Region.Op.DIFFERENCE;
            } else {
                op = Region.Op.INTERSECT;
            }
            canvas.clipPath(internalPath, op);
            return;
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: clipRect-N_I0leg  reason: not valid java name */
    public final void mo68clipRectN_I0leg(float f, float f2, float f3, float f4, int i) {
        boolean z;
        Region.Op op;
        android.graphics.Canvas canvas = this.internalCanvas;
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            op = Region.Op.DIFFERENCE;
        } else {
            op = Region.Op.INTERSECT;
        }
        canvas.clipRect(f, f2, f3, f4, op);
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00de  */
    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: concat-58bKbWc  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void mo69concat58bKbWc(float[] r24) {
        /*
            Method dump skipped, instructions count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.AndroidCanvas.mo69concat58bKbWc(float[]):void");
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void disableZ() {
        android.graphics.Canvas canvas = this.internalCanvas;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.disableZ();
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void drawArc(float f, float f2, float f3, float f4, float f5, float f6, boolean z, AndroidPaint androidPaint) {
        this.internalCanvas.drawArc(f, f2, f3, f4, f5, f6, z, androidPaint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: drawCircle-9KIMszo  reason: not valid java name */
    public final void mo70drawCircle9KIMszo(float f, long j, AndroidPaint androidPaint) {
        this.internalCanvas.drawCircle(Offset.m45getXimpl(j), Offset.m46getYimpl(j), f, androidPaint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: drawImage-d-4ec7I  reason: not valid java name */
    public final void mo71drawImaged4ec7I(AndroidPaint androidPaint) {
        Intrinsics.checkNotNullParameter(null, "image");
        Intrinsics.checkNotNullParameter(null, "<this>");
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Bitmap");
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: drawImageRect-HPBpro0  reason: not valid java name */
    public final void mo72drawImageRectHPBpro0(AndroidPaint androidPaint) {
        Intrinsics.checkNotNullParameter(null, "image");
        Intrinsics.checkNotNullParameter(null, "<this>");
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Bitmap");
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: drawLine-Wko1d7g  reason: not valid java name */
    public final void mo73drawLineWko1d7g(long j, long j2, AndroidPaint androidPaint) {
        this.internalCanvas.drawLine(Offset.m45getXimpl(j), Offset.m46getYimpl(j), Offset.m45getXimpl(j2), Offset.m46getYimpl(j2), androidPaint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void drawOval(float f, float f2, float f3, float f4, AndroidPaint androidPaint) {
        this.internalCanvas.drawOval(f, f2, f3, f4, androidPaint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void drawPath(Path path, AndroidPaint androidPaint) {
        Intrinsics.checkNotNullParameter(path, "path");
        android.graphics.Canvas canvas = this.internalCanvas;
        if (path instanceof AndroidPath) {
            canvas.drawPath(((AndroidPath) path).getInternalPath(), androidPaint.asFrameworkPaint());
            return;
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }

    @Override // androidx.compose.ui.graphics.Canvas
    /* renamed from: drawPoints-O7TthRY  reason: not valid java name */
    public final void mo74drawPointsO7TthRY(int i, AndroidPaint androidPaint, List points) {
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(points, "points");
        boolean z3 = true;
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            drawLines(2, androidPaint, points);
            return;
        }
        if (i == 2) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            drawLines(1, androidPaint, points);
            return;
        }
        if (i != 0) {
            z3 = false;
        }
        if (z3) {
            int size = points.size();
            for (int i2 = 0; i2 < size; i2++) {
                long m51unboximpl = ((Offset) points.get(i2)).m51unboximpl();
                this.internalCanvas.drawPoint(Offset.m45getXimpl(m51unboximpl), Offset.m46getYimpl(m51unboximpl), androidPaint.asFrameworkPaint());
            }
        }
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void drawRect(float f, float f2, float f3, float f4, AndroidPaint paint) {
        Intrinsics.checkNotNullParameter(paint, "paint");
        this.internalCanvas.drawRect(f, f2, f3, f4, paint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void drawRoundRect(float f, float f2, float f3, float f4, float f5, float f6, AndroidPaint androidPaint) {
        this.internalCanvas.drawRoundRect(f, f2, f3, f4, f5, f6, androidPaint.asFrameworkPaint());
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void enableZ() {
        android.graphics.Canvas canvas = this.internalCanvas;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.enableZ();
    }

    public final android.graphics.Canvas getInternalCanvas() {
        return this.internalCanvas;
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void restore() {
        this.internalCanvas.restore();
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void rotate(float f) {
        this.internalCanvas.rotate(f);
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void save() {
        this.internalCanvas.save();
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void scale(float f, float f2) {
        this.internalCanvas.scale(f, f2);
    }

    public final void setInternalCanvas(android.graphics.Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "<set-?>");
        this.internalCanvas = canvas;
    }

    @Override // androidx.compose.ui.graphics.Canvas
    public final void translate(float f, float f2) {
        this.internalCanvas.translate(f, f2);
    }
}
