package com.android.egg.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import com.android.egg.paint.SpotFilter;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Painting extends View implements SpotFilter.Plotter {
    private static final long FADE_MINS;
    private static final ColorMatrixColorFilter FADE_TO_BLACK_CF;
    private static final ColorMatrixColorFilter FADE_TO_WHITE_CF;
    private static final ColorMatrixColorFilter INVERT_CF;
    private static final String TOUCH_STATS;
    private static final float ZEN_FADE;
    private static final long ZEN_RATE;
    private final Object _bitmapLock;
    private float _brushWidth;
    private Paint _drawPaint;
    private SpotFilter _filter;
    private WindowInsets _insets;
    private float _lastR;
    private float _lastX;
    private float _lastY;
    private Canvas _paintCanvas;
    private Bitmap bitmap;
    private float devicePressureMax;
    private float devicePressureMin;
    private final Painting$fadeRunnable$1 fadeRunnable;
    private int paperColor;
    private boolean zenMode;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final long getFADE_MINS() {
            return Painting.FADE_MINS;
        }

        public final ColorMatrixColorFilter getFADE_TO_BLACK_CF() {
            return Painting.FADE_TO_BLACK_CF;
        }

        public final ColorMatrixColorFilter getFADE_TO_WHITE_CF() {
            return Painting.FADE_TO_WHITE_CF;
        }

        public final ColorMatrixColorFilter getINVERT_CF() {
            return Painting.INVERT_CF;
        }

        public final String getTOUCH_STATS() {
            return Painting.TOUCH_STATS;
        }

        public final float getZEN_FADE() {
            return Painting.ZEN_FADE;
        }

        public final long getZEN_RATE() {
            return Painting.ZEN_RATE;
        }
    }

    static {
        long millis = TimeUnit.MINUTES.toMillis(3L);
        FADE_MINS = millis;
        long millis2 = TimeUnit.SECONDS.toMillis(2L);
        ZEN_RATE = millis2;
        float max = Math.max(1.0f, ((float) (millis2 / millis)) * 255.0f);
        ZEN_FADE = max;
        FADE_TO_WHITE_CF = new ColorMatrixColorFilter(new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, max, 0.0f, 1.0f, 0.0f, 0.0f, max, 0.0f, 0.0f, 1.0f, 0.0f, max, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        FADE_TO_BLACK_CF = new ColorMatrixColorFilter(new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, -max, 0.0f, 1.0f, 0.0f, 0.0f, -max, 0.0f, 0.0f, 1.0f, 0.0f, -max, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        INVERT_CF = new ColorMatrixColorFilter(new ColorMatrix(new float[]{-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f}));
        TOUCH_STATS = "touch.stats";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.android.egg.paint.Painting$fadeRunnable$1] */
    public Painting(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.devicePressureMax = 1.0f;
        this.zenMode = true;
        this.paperColor = -1;
        this._bitmapLock = new Object();
        this._drawPaint = new Paint(1);
        this._brushWidth = 100.0f;
        this._filter = new SpotFilter(10, 0.5f, 0.9f, this);
        this.fadeRunnable = new Runnable() { // from class: com.android.egg.paint.Painting$fadeRunnable$1
            private final Paint pt = new Paint();

            @Override // java.lang.Runnable
            public void run() {
                Canvas canvas;
                ColorMatrixColorFilter fade_to_black_cf;
                Object obj;
                canvas = Painting.this._paintCanvas;
                if (canvas != null) {
                    Paint paint = this.pt;
                    if ((Painting.this.getPaperColor() & 255) > 128) {
                        fade_to_black_cf = Painting.Companion.getFADE_TO_WHITE_CF();
                    } else {
                        fade_to_black_cf = Painting.Companion.getFADE_TO_BLACK_CF();
                    }
                    paint.setColorFilter(fade_to_black_cf);
                    obj = Painting.this._bitmapLock;
                    Painting painting = Painting.this;
                    synchronized (obj) {
                        if (painting.getBitmap() != null) {
                            Bitmap bitmap = painting.getBitmap();
                            Intrinsics.checkNotNull(bitmap);
                            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.pt);
                        }
                    }
                    Painting.this.invalidate();
                }
                Painting.this.postDelayed(this, Painting.Companion.getZEN_RATE());
            }
        };
        init();
    }

    private final float adjustPressure(float f) {
        if (f > this.devicePressureMax) {
            this.devicePressureMax = f;
        }
        if (f < this.devicePressureMin) {
            this.devicePressureMin = f;
        }
        return PaintingKt.invlerp(f, this.devicePressureMin, this.devicePressureMax);
    }

    private final void init() {
        loadDevicePressureData();
    }

    private final void loadDevicePressureData() {
        try {
            String string = Settings.System.getString(getContext().getContentResolver(), TOUCH_STATS);
            if (string == null) {
                string = "{}";
            }
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("min")) {
                this.devicePressureMin = (float) jSONObject.getDouble("min");
            }
            if (jSONObject.has("max")) {
                this.devicePressureMax = (float) jSONObject.getDouble("max");
            }
            if (this.devicePressureMin < 0.0f) {
                this.devicePressureMin = 0.0f;
            }
            float f = this.devicePressureMax;
            float f2 = this.devicePressureMin;
            if (f < f2) {
                this.devicePressureMax = f2 + 1.0f;
            }
        } catch (Exception unused) {
        }
    }

    private final float powf(float f, float f2) {
        return (float) Math.pow(f, f2);
    }

    private final void setupBitmaps() {
        Bitmap createBitmap;
        boolean z;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getDisplay().getRealMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        Bitmap bitmap = this.bitmap;
        if (bitmap != null && bitmap.getWidth() == i && bitmap.getHeight() == i2) {
            createBitmap = bitmap;
        } else {
            createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        }
        if (createBitmap == null) {
            return;
        }
        Canvas canvas = new Canvas(createBitmap);
        if (bitmap != null) {
            boolean z2 = true;
            if (bitmap.getWidth() < bitmap.getHeight()) {
                z = true;
            } else {
                z = false;
            }
            if (createBitmap.getWidth() >= createBitmap.getHeight()) {
                z2 = false;
            }
            if (z != z2) {
                Matrix matrix = new Matrix();
                if (createBitmap.getWidth() > createBitmap.getHeight()) {
                    matrix.postRotate(-90.0f);
                    matrix.postTranslate(0.0f, createBitmap.getHeight());
                } else {
                    matrix.postRotate(90.0f);
                    matrix.postTranslate(createBitmap.getWidth(), 0.0f);
                }
                if (createBitmap.getWidth() != bitmap.getHeight() || createBitmap.getHeight() != bitmap.getWidth()) {
                    matrix.postScale(createBitmap.getWidth() / bitmap.getHeight(), createBitmap.getHeight() / bitmap.getWidth());
                }
                canvas.setMatrix(matrix);
            }
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this._drawPaint);
            canvas.setMatrix(new Matrix());
        } else {
            canvas.drawColor(this.paperColor);
        }
        this.bitmap = createBitmap;
        this._paintCanvas = canvas;
    }

    public final void clear() {
        this.bitmap = null;
        setupBitmaps();
        invalidate();
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final float getBrushWidth() {
        return this._brushWidth;
    }

    public final float getDevicePressureMax() {
        return this.devicePressureMax;
    }

    public final float getDevicePressureMin() {
        return this.devicePressureMin;
    }

    public final int getPaintColor() {
        return this._drawPaint.getColor();
    }

    public final int getPaperColor() {
        return this.paperColor;
    }

    public final boolean getZenMode() {
        return this.zenMode;
    }

    public final void invertContents() {
        Canvas canvas;
        Paint paint = new Paint();
        paint.setColorFilter(INVERT_CF);
        synchronized (this._bitmapLock) {
            Bitmap bitmap = this.bitmap;
            if (bitmap != null && (canvas = this._paintCanvas) != null) {
                Intrinsics.checkNotNull(bitmap);
                canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
            }
        }
        invalidate();
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        this._insets = windowInsets;
        if (windowInsets != null && this._paintCanvas == null) {
            setupBitmaps();
        }
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        Intrinsics.checkNotNullExpressionValue(onApplyWindowInsets, "super.onApplyWindowInsets(insets)");
        return onApplyWindowInsets;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setupBitmaps();
        if (this.zenMode) {
            getHandler().postDelayed(this.fadeRunnable, ZEN_RATE);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        if (this.zenMode) {
            removeCallbacks(this.fadeRunnable);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            Intrinsics.checkNotNull(bitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this._drawPaint);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        setupBitmaps();
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0014, code lost:
        if (r0 != 3) goto L11;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            android.graphics.Canvas r0 = r3._paintCanvas
            if (r4 == 0) goto L3b
            if (r0 != 0) goto L7
            goto L3b
        L7:
            int r0 = r4.getActionMasked()
            r1 = 1
            if (r0 == 0) goto L2e
            if (r0 == r1) goto L20
            r2 = 2
            if (r0 == r2) goto L17
            r2 = 3
            if (r0 == r2) goto L20
            goto L3a
        L17:
            com.android.egg.paint.SpotFilter r0 = r3._filter
            r0.add(r4)
            r3.invalidate()
            goto L3a
        L20:
            com.android.egg.paint.SpotFilter r0 = r3._filter
            r0.add(r4)
            com.android.egg.paint.SpotFilter r4 = r3._filter
            r4.finish()
            r3.invalidate()
            goto L3a
        L2e:
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            r3._lastR = r0
            com.android.egg.paint.SpotFilter r0 = r3._filter
            r0.add(r4)
            r3.invalidate()
        L3a:
            return r1
        L3b:
            boolean r3 = super.onTouchEvent(r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.egg.paint.Painting.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // com.android.egg.paint.SpotFilter.Plotter
    public void plot(MotionEvent.PointerCoords s) {
        Intrinsics.checkNotNullParameter(s, "s");
        Canvas canvas = this._paintCanvas;
        if (canvas == null) {
            return;
        }
        synchronized (this._bitmapLock) {
            float f = this._lastX;
            float f2 = this._lastY;
            float f3 = this._lastR;
            float max = Math.max(1.0f, powf(adjustPressure(s.pressure), 2.0f) * this._brushWidth);
            if (f3 >= 0.0f) {
                float hypot = PaintingKt.hypot(s.x - f, s.y - f2);
                if (hypot > 1.0f) {
                    float f4 = f3 + max;
                    if (f4 > 1.0f) {
                        int min = (int) ((2 * hypot) / Math.min(4.0f, f4));
                        float f5 = min;
                        float f6 = (s.x - f) / f5;
                        float f7 = (s.y - f2) / f5;
                        float f8 = (max - f3) / f5;
                        int i = min - 1;
                        for (int i2 = 0; i2 < i; i2++) {
                            f += f6;
                            f2 += f7;
                            f3 += f8;
                            canvas.drawCircle(f, f2, f3, this._drawPaint);
                        }
                    }
                }
            }
            canvas.drawCircle(s.x, s.y, max, this._drawPaint);
            this._lastX = s.x;
            this._lastY = s.y;
            this._lastR = max;
        }
    }

    public final int sampleAt(float f, float f2) {
        int left = (int) (f - getLeft());
        int top = (int) (f2 - getTop());
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            return bitmap.getPixel(left, top);
        }
        return -16777216;
    }

    public final void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public final void setBrushWidth(float f) {
        this._brushWidth = f;
    }

    public final void setDevicePressureMax(float f) {
        this.devicePressureMax = f;
    }

    public final void setDevicePressureMin(float f) {
        this.devicePressureMin = f;
    }

    public final void setPaintColor(int i) {
        this._drawPaint.setColor(i);
    }

    public final void setPaperColor(int i) {
        this.paperColor = i;
    }

    public final void setZenMode(boolean z) {
        if (this.zenMode != z) {
            this.zenMode = z;
            removeCallbacks(this.fadeRunnable);
            if (z && isAttachedToWindow()) {
                getHandler().postDelayed(this.fadeRunnable, ZEN_RATE);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r3v5, types: [com.android.egg.paint.Painting$fadeRunnable$1] */
    public Painting(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.devicePressureMax = 1.0f;
        this.zenMode = true;
        this.paperColor = -1;
        this._bitmapLock = new Object();
        this._drawPaint = new Paint(1);
        this._brushWidth = 100.0f;
        this._filter = new SpotFilter(10, 0.5f, 0.9f, this);
        this.fadeRunnable = new Runnable() { // from class: com.android.egg.paint.Painting$fadeRunnable$1
            private final Paint pt = new Paint();

            @Override // java.lang.Runnable
            public void run() {
                Canvas canvas;
                ColorMatrixColorFilter fade_to_black_cf;
                Object obj;
                canvas = Painting.this._paintCanvas;
                if (canvas != null) {
                    Paint paint = this.pt;
                    if ((Painting.this.getPaperColor() & 255) > 128) {
                        fade_to_black_cf = Painting.Companion.getFADE_TO_WHITE_CF();
                    } else {
                        fade_to_black_cf = Painting.Companion.getFADE_TO_BLACK_CF();
                    }
                    paint.setColorFilter(fade_to_black_cf);
                    obj = Painting.this._bitmapLock;
                    Painting painting = Painting.this;
                    synchronized (obj) {
                        if (painting.getBitmap() != null) {
                            Bitmap bitmap = painting.getBitmap();
                            Intrinsics.checkNotNull(bitmap);
                            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.pt);
                        }
                    }
                    Painting.this.invalidate();
                }
                Painting.this.postDelayed(this, Painting.Companion.getZEN_RATE());
            }
        };
        init();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.android.egg.paint.Painting$fadeRunnable$1] */
    public Painting(Context context, AttributeSet attrs, int i) {
        super(context, attrs, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
        this.devicePressureMax = 1.0f;
        this.zenMode = true;
        this.paperColor = -1;
        this._bitmapLock = new Object();
        this._drawPaint = new Paint(1);
        this._brushWidth = 100.0f;
        this._filter = new SpotFilter(10, 0.5f, 0.9f, this);
        this.fadeRunnable = new Runnable() { // from class: com.android.egg.paint.Painting$fadeRunnable$1
            private final Paint pt = new Paint();

            @Override // java.lang.Runnable
            public void run() {
                Canvas canvas;
                ColorMatrixColorFilter fade_to_black_cf;
                Object obj;
                canvas = Painting.this._paintCanvas;
                if (canvas != null) {
                    Paint paint = this.pt;
                    if ((Painting.this.getPaperColor() & 255) > 128) {
                        fade_to_black_cf = Painting.Companion.getFADE_TO_WHITE_CF();
                    } else {
                        fade_to_black_cf = Painting.Companion.getFADE_TO_BLACK_CF();
                    }
                    paint.setColorFilter(fade_to_black_cf);
                    obj = Painting.this._bitmapLock;
                    Painting painting = Painting.this;
                    synchronized (obj) {
                        if (painting.getBitmap() != null) {
                            Bitmap bitmap = painting.getBitmap();
                            Intrinsics.checkNotNull(bitmap);
                            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.pt);
                        }
                    }
                    Painting.this.invalidate();
                }
                Painting.this.postDelayed(this, Painting.Companion.getZEN_RATE());
            }
        };
        init();
    }

    public final void onTrimMemory() {
    }
}
