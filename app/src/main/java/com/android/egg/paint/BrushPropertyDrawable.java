package com.android.egg.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class BrushPropertyDrawable extends Drawable {
    public static final int $stable = 8;
    private float _scale;
    private int _size;
    private final Paint framePaint;
    private final Paint wellPaint;

    public BrushPropertyDrawable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Paint paint = new Paint(1);
        paint.setColor(-16777216);
        paint.setStyle(Paint.Style.FILL);
        this.framePaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setColor(-65536);
        paint2.setStyle(Paint.Style.FILL);
        this.wellPaint = paint2;
        this._size = 24;
        this._scale = 1.0f;
        this._size = (int) (24 * context.getResources().getDisplayMetrics().density);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas c) {
        Intrinsics.checkNotNullParameter(c, "c");
        float width = getBounds().width();
        float height = getBounds().height();
        float f = 2;
        float min = Math.min(width, height) / f;
        float f2 = width / f;
        float f3 = height / f;
        float f4 = min - (this._size / 12);
        c.drawCircle(f2, f3, (this._scale * f4) + 1, this.wellPaint);
        Path path = new Path();
        path.addCircle(f2, f3, min, Path.Direction.CCW);
        path.addCircle(f2, f3, f4, Path.Direction.CW);
        c.drawPath(path, this.framePaint);
    }

    public final Paint getFramePaint() {
        return this.framePaint;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this._size;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this._size;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public final Paint getWellPaint() {
        return this.wellPaint;
    }

    public final void setFrameColor(int i) {
        this.framePaint.setColor(i);
        invalidateSelf();
    }

    public final void setWellColor(int i) {
        this.wellPaint.setColor(i);
        invalidateSelf();
    }

    public final void setWellScale(float f) {
        this._scale = f;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
