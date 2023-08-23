package com.android.egg.quares;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Quare implements Parcelable {
    private final int[] data;
    private final int depth;
    private final int height;
    private final int[] user;
    private final int width;
    public static final CREATOR CREATOR = new CREATOR(null);
    public static final int $stable = 8;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class CREATOR implements Parcelable.Creator {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override // android.os.Parcelable.Creator
        public Quare createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNull(parcel);
            Quare quare = new Quare(parcel.readInt(), parcel.readInt(), parcel.readInt());
            parcel.readIntArray(quare.data);
            parcel.readIntArray(quare.user);
            return quare;
        }

        @Override // android.os.Parcelable.Creator
        public Quare[] newArray(int i) {
            return new Quare[i];
        }
    }

    public Quare(int i, int i2, int i3) {
        this.width = i;
        this.height = i2;
        this.depth = i3;
        int[] iArr = new int[i * i2];
        this.data = iArr;
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        this.user = copyOf;
    }

    private final void loadAndQuantize(Bitmap bitmap) {
        int[] iArr = this.data;
        int i = this.width;
        bitmap.getPixels(iArr, 0, i, 0, 0, i, this.height);
        int i2 = this.depth;
        if (i2 == 8) {
            return;
        }
        float f = 255.0f / i2;
        int length = this.data.length;
        for (int i3 = 0; i3 < length; i3++) {
            int[] iArr2 = this.data;
            iArr2[i3] = ((int) (((float) Math.rint(((iArr2[i3] >>> 24) / f) * 1.25f)) * f)) << 24;
        }
    }

    public final Bitmap bitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(this.data, this.width, this.height, Bitmap.Config.ALPHA_8);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(data, width…t, Bitmap.Config.ALPHA_8)");
        return createBitmap;
    }

    public final boolean check() {
        return Arrays.equals(this.data, this.user);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final int[] errors() {
        int i = this.width * this.height;
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = Math.abs(this.data[i2] - this.user[i2]);
        }
        return iArr;
    }

    public final int[] getClue(int i, int i2) {
        int i3;
        int i4;
        ArrayList arrayList = new ArrayList();
        if (i < 0) {
            i3 = 0;
        } else {
            i3 = i;
        }
        if (i < 0) {
            i = this.width - 1;
        }
        if (i2 < 0) {
            i4 = 0;
        } else {
            i4 = i2;
        }
        if (i2 < 0) {
            i2 = this.height - 1;
        }
        int i5 = 0;
        if (i4 <= i2) {
            while (true) {
                if (i3 <= i) {
                    int i6 = i3;
                    while (true) {
                        if (getDataAt(i6, i4) != 0) {
                            i5++;
                        } else if (i5 > 0) {
                            arrayList.add(Integer.valueOf(i5));
                            i5 = 0;
                        }
                        if (i6 == i) {
                            break;
                        }
                        i6++;
                    }
                }
                if (i4 == i2) {
                    break;
                }
                i4++;
            }
        }
        if (i5 > 0) {
            arrayList.add(Integer.valueOf(i5));
        } else if (arrayList.size() == 0) {
            arrayList.add(0);
        }
        return CollectionsKt.toIntArray(arrayList);
    }

    public final int[] getColumnClue(int i) {
        return getClue(i, -1);
    }

    public final int getDataAt(int i, int i2) {
        return this.data[(i2 * this.width) + i] >>> 24;
    }

    public final int getDepth() {
        return this.depth;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int[] getRowClue(int i) {
        return getClue(-1, i);
    }

    public final int getUserMark(int i, int i2) {
        return this.user[(i2 * this.width) + i] >>> 24;
    }

    public final int getWidth() {
        return this.width;
    }

    public final boolean isBlank() {
        int[] iArr = this.data;
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        if (i != 0) {
            return false;
        }
        return true;
    }

    public final void load(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        Bitmap resized = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(resized);
        drawable.setBounds(0, 0, this.width, this.height);
        drawable.setTint(-16777216);
        drawable.draw(canvas);
        Intrinsics.checkNotNullExpressionValue(resized, "resized");
        loadAndQuantize(resized);
        resized.recycle();
    }

    public final void resetUserMarks() {
        int[] iArr = this.user;
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = iArr[i];
            this.user[i2] = 0;
            i++;
            i2++;
        }
    }

    public final void setUserMark(int i, int i2, int i3) {
        this.user[(i2 * this.width) + i] = i3 << 24;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeInt(this.width);
            parcel.writeInt(this.height);
            parcel.writeInt(this.depth);
            parcel.writeIntArray(this.data);
            parcel.writeIntArray(this.user);
        }
    }

    public final boolean check(int i, int i2) {
        int i3 = i < 0 ? 0 : i;
        if (i < 0) {
            i = this.width - 1;
        }
        int i4 = i2 < 0 ? 0 : i2;
        if (i2 < 0) {
            i2 = this.height - 1;
        }
        if (i4 <= i2) {
            while (true) {
                if (i3 <= i) {
                    for (int i5 = i3; getDataAt(i5, i4) == getUserMark(i5, i4); i5++) {
                        if (i5 != i) {
                        }
                    }
                    return false;
                }
                if (i4 == i2) {
                    break;
                }
                i4++;
            }
        }
        return true;
    }

    public final void load(Context context, Icon icon) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Drawable loadDrawable = icon.loadDrawable(context);
        if (loadDrawable != null) {
            load(loadDrawable);
        }
    }
}
