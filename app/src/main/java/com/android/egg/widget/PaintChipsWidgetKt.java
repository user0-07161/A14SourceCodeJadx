package com.android.egg.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.SizeF;
import android.widget.RemoteViews;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PaintChipsWidgetKt {
    private static int[][] COLORS = null;
    private static int[] COLORS_ACCENT1 = null;
    private static int[] COLORS_ACCENT2 = null;
    private static int[] COLORS_ACCENT3 = null;
    private static final int[] COLORS_NEUTRAL1;
    private static final int[] COLORS_NEUTRAL2;
    public static final String TAG = "PaintChips";
    private static final int[] SHADE_NUMBERS = {0, 10, 50, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};
    private static String[] COLOR_NAMES = {"N1", "N2", "A1", "A2", "A3"};

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ClickBehavior.values().length];
            try {
                iArr[ClickBehavior.SHARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ClickBehavior.LAUNCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ClickBehavior.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        int[] iArr = {17170461, 17170462, 17170463, 17170464, 17170465, 17170466, 17170467, 17170468, 17170469, 17170470, 17170471, 17170472, 17170473};
        COLORS_NEUTRAL1 = iArr;
        int[] iArr2 = {17170474, 17170475, 17170476, 17170477, 17170478, 17170479, 17170480, 17170481, 17170482, 17170483, 17170484, 17170485, 17170486};
        COLORS_NEUTRAL2 = iArr2;
        int[] iArr3 = {17170487, 17170488, 17170489, 17170490, 17170491, 17170492, 17170493, 17170494, 17170495, 17170496, 17170497, 17170498, 17170499};
        COLORS_ACCENT1 = iArr3;
        int[] iArr4 = {17170500, 17170501, 17170502, 17170503, 17170504, 17170505, 17170506, 17170507, 17170508, 17170509, 17170510, 17170511, 17170512};
        COLORS_ACCENT2 = iArr4;
        int[] iArr5 = {17170513, 17170514, 17170515, 17170516, 17170517, 17170518, 17170519, 17170520, 17170521, 17170522, 17170523, 17170524, 17170525};
        COLORS_ACCENT3 = iArr5;
        COLORS = new int[][]{iArr, iArr2, iArr3, iArr4, iArr5};
    }

    public static final RemoteViews buildFullWidget(Context context, ClickBehavior clickable) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(clickable, "clickable");
        return buildWidget(context, SHADE_NUMBERS.length, COLORS.length, clickable);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00af, code lost:
        if (r1 >= r14.length) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b2, code lost:
        r16 = r6;
        r19 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c0, code lost:
        if (r1 < 3) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c5, code lost:
        if (r1 < 5) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final android.widget.RemoteViews buildWidget(android.content.Context r20, int r21, int r22, com.android.egg.widget.ClickBehavior r23) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.egg.widget.PaintChipsWidgetKt.buildWidget(android.content.Context, int, int, com.android.egg.widget.ClickBehavior):android.widget.RemoteViews");
    }

    public static final int[][] getCOLORS() {
        return COLORS;
    }

    public static final int[] getCOLORS_ACCENT1() {
        return COLORS_ACCENT1;
    }

    public static final int[] getCOLORS_ACCENT2() {
        return COLORS_ACCENT2;
    }

    public static final int[] getCOLORS_ACCENT3() {
        return COLORS_ACCENT3;
    }

    public static final int[] getCOLORS_NEUTRAL1() {
        return COLORS_NEUTRAL1;
    }

    public static final int[] getCOLORS_NEUTRAL2() {
        return COLORS_NEUTRAL2;
    }

    public static final String[] getCOLOR_NAMES() {
        return COLOR_NAMES;
    }

    public static final int[] getSHADE_NUMBERS() {
        return SHADE_NUMBERS;
    }

    public static final PendingIntent makeActivityLaunchPendingIntent(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, PaintChipsActivity.class));
        intent.setAction("android.intent.action.MAIN");
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 201326592);
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(context, 0,\n…tent.FLAG_UPDATE_CURRENT)");
        return activity;
    }

    public static final PendingIntent makeTextSharePendingIntent(Context context, String text) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(text, "text");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", text);
        intent.setType("text/plain");
        Intent createChooser = Intent.createChooser(intent, null);
        createChooser.setIdentifier(text);
        PendingIntent activity = PendingIntent.getActivity(context, 0, createChooser, 201326592);
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(context, 0, …tent.FLAG_UPDATE_CURRENT)");
        return activity;
    }

    public static final void setCOLORS(int[][] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        COLORS = iArr;
    }

    public static final void setCOLORS_ACCENT1(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        COLORS_ACCENT1 = iArr;
    }

    public static final void setCOLORS_ACCENT2(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        COLORS_ACCENT2 = iArr;
    }

    public static final void setCOLORS_ACCENT3(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        COLORS_ACCENT3 = iArr;
    }

    public static final void setCOLOR_NAMES(String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        COLOR_NAMES = strArr;
    }

    public static final void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appWidgetManager, "appWidgetManager");
        SizeF sizeF = new SizeF(50.0f, 50.0f);
        ClickBehavior clickBehavior = ClickBehavior.LAUNCH;
        appWidgetManager.updateAppWidget(i, new RemoteViews(MapsKt.mapOf(new Pair(sizeF, buildWidget(context, 1, 1, clickBehavior)), new Pair(new SizeF(100.0f, 50.0f), buildWidget(context, 1, 2, clickBehavior)), new Pair(new SizeF(150.0f, 50.0f), buildWidget(context, 1, 3, clickBehavior)), new Pair(new SizeF(200.0f, 50.0f), buildWidget(context, 1, 4, clickBehavior)), new Pair(new SizeF(250.0f, 50.0f), buildWidget(context, 1, 5, clickBehavior)), new Pair(new SizeF(50.0f, 120.0f), buildWidget(context, 3, 1, clickBehavior)), new Pair(new SizeF(100.0f, 120.0f), buildWidget(context, 3, 2, clickBehavior)), new Pair(new SizeF(150.0f, 120.0f), buildWidget(context, 3, 3, clickBehavior)), new Pair(new SizeF(200.0f, 120.0f), buildWidget(context, 3, 4, clickBehavior)), new Pair(new SizeF(250.0f, 120.0f), buildWidget(context, 3, 5, clickBehavior)), new Pair(new SizeF(50.0f, 250.0f), buildWidget(context, 5, 1, clickBehavior)), new Pair(new SizeF(100.0f, 250.0f), buildWidget(context, 5, 2, clickBehavior)), new Pair(new SizeF(150.0f, 250.0f), buildWidget(context, 5, 3, clickBehavior)), new Pair(new SizeF(200.0f, 250.0f), buildWidget(context, 5, 4, clickBehavior)), new Pair(new SizeF(250.0f, 250.0f), buildWidget(context, 5, 5, clickBehavior)), new Pair(new SizeF(300.0f, 300.0f), buildWidget(context, SHADE_NUMBERS.length, COLORS.length, clickBehavior)))));
    }
}
