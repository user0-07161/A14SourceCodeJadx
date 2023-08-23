package com.android.egg.neko;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Icon;
import com.android.egg.R;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class Food {
    private static int[] sIcons;
    private static String[] sNames;
    private final int mType;

    public Food(int i) {
        this.mType = i;
    }

    public Icon getIcon(Context context) {
        if (sIcons == null) {
            TypedArray obtainTypedArray = context.getResources().obtainTypedArray(R.array.food_icons);
            sIcons = new int[obtainTypedArray.length()];
            int i = 0;
            while (true) {
                int[] iArr = sIcons;
                if (i >= iArr.length) {
                    break;
                }
                iArr[i] = obtainTypedArray.getResourceId(i, 0);
                i++;
            }
            obtainTypedArray.recycle();
        }
        return Icon.createWithResource(context, sIcons[this.mType]);
    }

    public long getInterval(Context context) {
        return context.getResources().getIntArray(R.array.food_intervals)[this.mType];
    }

    public String getName(Context context) {
        if (sNames == null) {
            sNames = context.getResources().getStringArray(R.array.food_names);
        }
        return sNames[this.mType];
    }

    public int getType() {
        return this.mType;
    }
}
