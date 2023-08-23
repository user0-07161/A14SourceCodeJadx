package com.android.egg.quares;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;
import com.android.egg.R;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ClueView extends View {
    public static final int $stable = 8;
    private int column;
    private final int correctColor;
    private final int incorrectColor;
    private final TextPaint paint;
    private int row;
    private boolean showText;
    private CharSequence text;
    private float textRotation;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClueView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.row = -1;
        this.column = -1;
        this.text = "";
        this.showText = true;
        setBackgroundColor(0);
        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(context.getResources().getDisplayMetrics().density * 14.0f);
        textPaint.setColor(context.getColor(R.color.q_clue_text));
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setTextAlign(Paint.Align.CENTER);
        this.paint = textPaint;
        this.incorrectColor = context.getColor(R.color.q_clue_bg);
        this.correctColor = context.getColor(R.color.q_clue_bg_correct);
    }

    public final boolean check(Quare q) {
        int i;
        Intrinsics.checkNotNullParameter(q, "q");
        boolean check = q.check(this.column, this.row);
        if (check) {
            i = this.correctColor;
        } else {
            i = this.incorrectColor;
        }
        setBackgroundColor(i);
        return check;
    }

    public final int getColumn() {
        return this.column;
    }

    public final int getCorrectColor() {
        return this.correctColor;
    }

    public final int getIncorrectColor() {
        return this.incorrectColor;
    }

    public final TextPaint getPaint() {
        return this.paint;
    }

    public final int getRow() {
        return this.row;
    }

    public final boolean getShowText() {
        return this.showText;
    }

    public final CharSequence getText() {
        return this.text;
    }

    public final float getTextRotation() {
        return this.textRotation;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        boolean z;
        super.onDraw(canvas);
        if (this.showText && canvas != null) {
            float width = canvas.getWidth() / 2.0f;
            float height = canvas.getHeight() / 2.0f;
            int width2 = canvas.getWidth();
            float f = this.textRotation;
            if (f == 0.0f) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                canvas.rotate(f, width, height);
                width2 = canvas.getHeight();
            }
            CharSequence charSequence = this.text;
            StaticLayout build = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.paint, width2).build();
            canvas.translate(width, height - (build.getHeight() / 2));
            build.draw(canvas);
        }
    }

    public final void setColumn(int i) {
        this.column = i;
    }

    public final void setRow(int i) {
        this.row = i;
    }

    public final void setShowText(boolean z) {
        this.showText = z;
    }

    public final void setText(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<set-?>");
        this.text = charSequence;
    }

    public final void setTextRotation(float f) {
        this.textRotation = f;
    }

    public final boolean setColumn(Quare q, int i) {
        Intrinsics.checkNotNullParameter(q, "q");
        this.column = i;
        this.row = -1;
        this.textRotation = 90.0f;
        this.text = ArraysKt.joinToString$default(q.getColumnClue(i));
        return check(q);
    }

    public final boolean setRow(Quare q, int i) {
        Intrinsics.checkNotNullParameter(q, "q");
        this.row = i;
        this.column = -1;
        this.textRotation = 0.0f;
        this.text = ArraysKt.joinToString$default(q.getRowClue(i));
        return check(q);
    }
}
