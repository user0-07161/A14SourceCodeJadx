package androidx.compose.ui.unit;

import android.content.Context;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AndroidDensity_androidKt {
    public static final Density Density(Context context) {
        return new DensityImpl(context.getResources().getDisplayMetrics().density, context.getResources().getConfiguration().fontScale);
    }
}
