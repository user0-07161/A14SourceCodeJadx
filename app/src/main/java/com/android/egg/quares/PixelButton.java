package com.android.egg.quares;

import android.content.Context;
import android.widget.CompoundButton;
import com.android.egg.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PixelButton extends CompoundButton {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PixelButton(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setBackgroundResource(R.drawable.pixel_bg);
        setClickable(true);
        setEnabled(true);
    }
}
