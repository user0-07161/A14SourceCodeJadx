package com.android.egg.neko;

import android.service.controls.Control;
import android.service.controls.templates.ControlTemplate;
import com.android.egg.R;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class NekoControlsServiceKt {
    public static final int COLOR_FOOD_BG = 1090486272;
    public static final int COLOR_FOOD_FG = -32768;
    public static final int COLOR_TOY_BG = 1090470016;
    public static final int COLOR_TOY_FG = -49024;
    public static final int COLOR_WATER_BG = 1073774847;
    public static final int COLOR_WATER_FG = -16744193;
    public static final String CONTROL_ID_FOOD = "food";
    public static final String CONTROL_ID_TOY = "toy";
    public static final String CONTROL_ID_WATER = "water";
    public static final long FOOD_SPAWN_CAT_DELAY_MINS = 5;
    private static final int[] P_TOY_ICONS = {1, R.drawable.ic_toy_mouse, 1, R.drawable.ic_toy_fish, 1, R.drawable.ic_toy_ball, 1, R.drawable.ic_toy_laser};

    public static final String Control_toString(Control control) {
        Intrinsics.checkNotNullParameter(control, "control");
        String format = String.format("0x%08x", Arrays.copyOf(new Object[]{Integer.valueOf(control.hashCode())}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        String controlId = control.getControlId();
        int deviceType = control.getDeviceType();
        CharSequence title = control.getTitle();
        ControlTemplate controlTemplate = control.getControlTemplate();
        return "Control(" + format + " id=" + controlId + ", type=" + deviceType + ", title=" + ((Object) title) + ", template=" + controlTemplate + ")";
    }

    public static final int[] getP_TOY_ICONS() {
        return P_TOY_ICONS;
    }
}
