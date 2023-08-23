package com.android.egg.landroid;

import android.util.Log;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class PathToolsKt {
    public static final Path createPolygon(float f, int i) {
        AndroidPath Path = AndroidPath_androidKt.Path();
        Path.moveTo(f, 0.0f);
        float f2 = 6.2831855f / i;
        for (int i2 = 1; i2 < i; i2++) {
            double d = i2 * f2;
            Path.lineTo(((float) Math.cos(d)) * f, ((float) Math.sin(d)) * f);
        }
        Path.close();
        return Path;
    }

    public static final Path createStar(float f, float f2, int i) {
        AndroidPath Path = AndroidPath_androidKt.Path();
        float f3 = 6.2831855f / i;
        Path.moveTo(f, 0.0f);
        double d = f3 * 0.5f;
        Path.lineTo(((float) Math.cos(d)) * f2, ((float) Math.sin(d)) * f2);
        for (int i2 = 1; i2 < i; i2++) {
            float f4 = i2;
            double d2 = f3 * f4;
            Path.lineTo(((float) Math.cos(d2)) * f, ((float) Math.sin(d2)) * f);
            double d3 = (f4 + 0.5f) * f3;
            Path.lineTo(((float) Math.cos(d3)) * f2, ((float) Math.sin(d3)) * f2);
        }
        Path.close();
        return Path;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [kotlin.collections.EmptyList] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.util.ArrayList] */
    public static final void parseSvgPathData(Path path, String d) {
        ?? r4;
        String value;
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(d, "d");
        for (MatchResult matchResult : Regex.findAll$default(new Regex("([A-Z])([-.,0-9e ]+)"), StringsKt.trim(d).toString())) {
            MatchGroup matchGroup = matchResult.getGroups().get(1);
            Intrinsics.checkNotNull(matchGroup);
            String value2 = matchGroup.getValue();
            MatchGroup matchGroup2 = matchResult.getGroups().get(2);
            if (matchGroup2 != null && (value = matchGroup2.getValue()) != null) {
                List<String> split = new Regex("\\s+").split(value);
                r4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(split));
                for (String str : split) {
                    r4.add(Float.valueOf(Float.parseFloat(str)));
                }
            } else {
                r4 = EmptyList.INSTANCE;
            }
            String joinToString$default = CollectionsKt.joinToString$default(r4, ",", null, null, null, 62);
            Log.d("Landroid", "cmd = " + value2 + ", args = " + joinToString$default);
            int hashCode = value2.hashCode();
            if (hashCode != 67) {
                if (hashCode != 90) {
                    if (hashCode != 76) {
                        if (hashCode == 77 && value2.equals("M")) {
                            ((AndroidPath) path).moveTo(((Number) r4.get(0)).floatValue(), ((Number) r4.get(1)).floatValue());
                        }
                        Log.v("Landroid", "unsupported SVG command: ".concat(value2));
                    } else if (value2.equals("L")) {
                        ((AndroidPath) path).lineTo(((Number) r4.get(0)).floatValue(), ((Number) r4.get(1)).floatValue());
                    } else {
                        Log.v("Landroid", "unsupported SVG command: ".concat(value2));
                    }
                } else if (value2.equals("Z")) {
                    ((AndroidPath) path).close();
                } else {
                    Log.v("Landroid", "unsupported SVG command: ".concat(value2));
                }
            } else if (!value2.equals("C")) {
                Log.v("Landroid", "unsupported SVG command: ".concat(value2));
            } else {
                ((AndroidPath) path).cubicTo(((Number) r4.get(0)).floatValue(), ((Number) r4.get(1)).floatValue(), ((Number) r4.get(2)).floatValue(), ((Number) r4.get(3)).floatValue(), ((Number) r4.get(4)).floatValue(), ((Number) r4.get(5)).floatValue());
            }
        }
    }
}
