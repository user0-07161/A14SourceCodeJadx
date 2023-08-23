package com.android.egg.paint;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Magnifier;
import com.android.egg.R;
import java.util.Arrays;
import java.util.stream.IntStream;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class PaintActivity extends Activity {
    private static final float MAX_BRUSH_WIDTH_DP = 100.0f;
    private static final float MIN_BRUSH_WIDTH_DP = 1.0f;
    private static final int NUM_BRUSHES = 6;
    private static final int NUM_COLORS = 6;
    private BrushPropertyDrawable colorButtonDrawable;
    private float maxBrushWidth;
    private float minBrushWidth;
    private BrushPropertyDrawable widthButtonDrawable;
    private Painting painting = null;
    private CutoutAvoidingToolbar toolbar = null;
    private LinearLayout brushes = null;
    private LinearLayout colors = null;
    private Magnifier magnifier = null;
    private boolean sampling = false;
    private View.OnClickListener buttonHandler = new View.OnClickListener() { // from class: com.android.egg.paint.PaintActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            float f;
            switch (view.getId()) {
                case R.id.btnBrush /* 2131099700 */:
                    view.setSelected(true);
                    PaintActivity paintActivity = PaintActivity.this;
                    paintActivity.hideToolbar(paintActivity.colors);
                    PaintActivity paintActivity2 = PaintActivity.this;
                    paintActivity2.toggleToolbar(paintActivity2.brushes);
                    return;
                case R.id.btnClear /* 2131099701 */:
                    PaintActivity.this.painting.clear();
                    return;
                case R.id.btnColor /* 2131099702 */:
                    view.setSelected(true);
                    PaintActivity paintActivity3 = PaintActivity.this;
                    paintActivity3.hideToolbar(paintActivity3.brushes);
                    PaintActivity paintActivity4 = PaintActivity.this;
                    paintActivity4.toggleToolbar(paintActivity4.colors);
                    return;
                case R.id.btnSample /* 2131099703 */:
                    PaintActivity.this.sampling = true;
                    view.setSelected(true);
                    return;
                case R.id.btnZen /* 2131099704 */:
                    PaintActivity.this.painting.setZenMode(true ^ PaintActivity.this.painting.getZenMode());
                    ViewPropertyAnimator interpolator = view.animate().setStartDelay(200L).setInterpolator(new OvershootInterpolator());
                    if (PaintActivity.this.painting.getZenMode()) {
                        f = 0.0f;
                    } else {
                        f = 90.0f;
                    }
                    interpolator.rotation(f);
                    return;
                default:
                    return;
            }
        }
    };
    private int nightMode = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public void hideToolbar(final View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        view.animate().translationY(this.toolbar.getHeight() / 2).alpha(0.0f).setDuration(150L).withEndAction(new Runnable() { // from class: com.android.egg.paint.PaintActivity.2
            @Override // java.lang.Runnable
            public void run() {
                view.setVisibility(8);
            }
        }).start();
    }

    static final float lerp(float f, float f2, float f3) {
        return ((f3 - f2) * f) + f2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBrushAndColor() {
        int[] array;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        if (this.brushes.getChildCount() == 0) {
            for (int i = 0; i < 6; i++) {
                BrushPropertyDrawable brushPropertyDrawable = new BrushPropertyDrawable(this);
                brushPropertyDrawable.setFrameColor(getColor(R.color.toolbar_icon_color));
                final float lerp = lerp((float) Math.pow(i / 6.0f, 2.0d), this.minBrushWidth, this.maxBrushWidth);
                brushPropertyDrawable.setWellScale(lerp / this.maxBrushWidth);
                brushPropertyDrawable.setWellColor(getColor(R.color.toolbar_icon_color));
                ImageButton imageButton = new ImageButton(this);
                imageButton.setImageDrawable(brushPropertyDrawable);
                imageButton.setBackground(getDrawable(R.drawable.toolbar_button_bg));
                imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.egg.paint.PaintActivity.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        PaintActivity.this.brushes.setSelected(false);
                        PaintActivity paintActivity = PaintActivity.this;
                        paintActivity.hideToolbar(paintActivity.brushes);
                        PaintActivity.this.painting.setBrushWidth(lerp);
                        PaintActivity.this.refreshBrushAndColor();
                    }
                });
                this.brushes.addView(imageButton, layoutParams);
            }
        }
        if (this.colors.getChildCount() == 0) {
            for (final int i2 : IntStream.concat(IntStream.of(-16777216, -1), Arrays.stream(new Palette(6).getColors())).toArray()) {
                BrushPropertyDrawable brushPropertyDrawable2 = new BrushPropertyDrawable(this);
                brushPropertyDrawable2.setFrameColor(getColor(R.color.toolbar_icon_color));
                brushPropertyDrawable2.setWellColor(i2);
                ImageButton imageButton2 = new ImageButton(this);
                imageButton2.setImageDrawable(brushPropertyDrawable2);
                imageButton2.setBackground(getDrawable(R.drawable.toolbar_button_bg));
                imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.android.egg.paint.PaintActivity.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        PaintActivity.this.colors.setSelected(false);
                        PaintActivity paintActivity = PaintActivity.this;
                        paintActivity.hideToolbar(paintActivity.colors);
                        PaintActivity.this.painting.setPaintColor(i2);
                        PaintActivity.this.refreshBrushAndColor();
                    }
                });
                this.colors.addView(imageButton2, layoutParams);
            }
        }
        this.widthButtonDrawable.setWellScale(this.painting.getBrushWidth() / this.maxBrushWidth);
        this.widthButtonDrawable.setWellColor(this.painting.getPaintColor());
        this.colorButtonDrawable.setWellColor(this.painting.getPaintColor());
    }

    private void refreshNightMode(Configuration configuration) {
        int i = configuration.uiMode & 48;
        int i2 = this.nightMode;
        if (i2 != i) {
            if (i2 != 0) {
                this.painting.invertContents();
                ((ViewGroup) this.painting.getParent()).removeView(this.painting);
                setupViews(this.painting);
                View decorView = getWindow().getDecorView();
                int systemUiVisibility = decorView.getSystemUiVisibility();
                if (i == 32) {
                    decorView.setSystemUiVisibility(systemUiVisibility & (-17));
                } else {
                    decorView.setSystemUiVisibility(systemUiVisibility | 16);
                }
            }
            this.nightMode = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToolbar(View view) {
        if (view.getVisibility() != 8) {
            return;
        }
        view.setVisibility(0);
        view.setTranslationY(this.toolbar.getHeight() / 2);
        view.animate().translationY(this.toolbar.getHeight()).alpha(1.0f).setDuration(220L).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleToolbar(View view) {
        if (view.getVisibility() == 0) {
            hideToolbar(view);
        } else {
            showToolbar(view);
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        refreshNightMode(configuration);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.flags = attributes.flags | 256 | 512;
        getWindow().setAttributes(attributes);
        this.maxBrushWidth = getResources().getDisplayMetrics().density * MAX_BRUSH_WIDTH_DP;
        this.minBrushWidth = getResources().getDisplayMetrics().density * 1.0f;
        setupViews(null);
        refreshNightMode(getResources().getConfiguration());
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        this.painting.onTrimMemory();
    }

    void setupViews(Painting painting) {
        setContentView(R.layout.activity_paint);
        if (painting == null) {
            painting = new Painting(this);
        }
        this.painting = painting;
        ((FrameLayout) findViewById(R.id.contentView)).addView(this.painting, new FrameLayout.LayoutParams(-1, -1));
        this.painting.setPaperColor(getColor(R.color.paper_color));
        this.painting.setPaintColor(getColor(R.color.paint_color));
        this.toolbar = (CutoutAvoidingToolbar) findViewById(R.id.toolbar);
        this.brushes = (LinearLayout) findViewById(R.id.brushes);
        this.colors = (LinearLayout) findViewById(R.id.colors);
        this.magnifier = new Magnifier(this.painting);
        this.painting.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.egg.paint.PaintActivity.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked != 0) {
                    if (actionMasked != 1) {
                        if (actionMasked != 2) {
                            if (actionMasked == 3 && PaintActivity.this.sampling) {
                                PaintActivity.this.findViewById(R.id.btnSample).setSelected(false);
                                PaintActivity.this.sampling = false;
                                PaintActivity.this.magnifier.dismiss();
                            }
                        }
                    } else if (PaintActivity.this.sampling) {
                        PaintActivity.this.findViewById(R.id.btnSample).setSelected(false);
                        PaintActivity.this.sampling = false;
                        PaintActivity.this.magnifier.dismiss();
                        PaintActivity.this.painting.setPaintColor(PaintActivity.this.painting.sampleAt(motionEvent.getX(), motionEvent.getY()));
                        PaintActivity.this.refreshBrushAndColor();
                    }
                    return false;
                }
                if (PaintActivity.this.sampling) {
                    PaintActivity.this.magnifier.show(motionEvent.getX(), motionEvent.getY());
                    PaintActivity.this.colorButtonDrawable.setWellColor(PaintActivity.this.painting.sampleAt(motionEvent.getX(), motionEvent.getY()));
                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.btnBrush).setOnClickListener(this.buttonHandler);
        findViewById(R.id.btnColor).setOnClickListener(this.buttonHandler);
        findViewById(R.id.btnClear).setOnClickListener(this.buttonHandler);
        findViewById(R.id.btnSample).setOnClickListener(this.buttonHandler);
        findViewById(R.id.btnZen).setOnClickListener(this.buttonHandler);
        findViewById(R.id.btnColor).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.egg.paint.PaintActivity.4
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                PaintActivity.this.colors.removeAllViews();
                PaintActivity paintActivity = PaintActivity.this;
                paintActivity.showToolbar(paintActivity.colors);
                PaintActivity.this.refreshBrushAndColor();
                return true;
            }
        });
        findViewById(R.id.btnClear).setOnLongClickListener(new View.OnLongClickListener() { // from class: com.android.egg.paint.PaintActivity.5
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                PaintActivity.this.painting.invertContents();
                return true;
            }
        });
        BrushPropertyDrawable brushPropertyDrawable = new BrushPropertyDrawable(this);
        this.widthButtonDrawable = brushPropertyDrawable;
        brushPropertyDrawable.setFrameColor(getColor(R.color.toolbar_icon_color));
        BrushPropertyDrawable brushPropertyDrawable2 = new BrushPropertyDrawable(this);
        this.colorButtonDrawable = brushPropertyDrawable2;
        brushPropertyDrawable2.setFrameColor(getColor(R.color.toolbar_icon_color));
        ((ImageButton) findViewById(R.id.btnBrush)).setImageDrawable(this.widthButtonDrawable);
        ((ImageButton) findViewById(R.id.btnColor)).setImageDrawable(this.colorButtonDrawable);
        refreshBrushAndColor();
    }
}
