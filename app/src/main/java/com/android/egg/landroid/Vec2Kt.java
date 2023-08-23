package com.android.egg.landroid;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Vec2Kt {
    public static final float PI2f = 6.2831855f;
    public static final float PIf = 3.1415927f;

    public static final long Vec2(float f, float f2) {
        return OffsetKt.Offset(f, f2);
    }

    /* renamed from: angle-k-4lQ0M  reason: not valid java name */
    public static final float m443anglek4lQ0M(long j) {
        return (float) Math.atan2(Offset.m46getYimpl(j), Offset.m45getXimpl(j));
    }

    /* renamed from: distance-0a9Yr6o  reason: not valid java name */
    public static final float m444distance0a9Yr6o(long j, long j2) {
        return m446magk4lQ0M(Offset.m47minusMKHz9U(j, j2));
    }

    /* renamed from: dot-0a9Yr6o  reason: not valid java name */
    public static final float m445dot0a9Yr6o(long j, long j2) {
        float m45getXimpl = Offset.m45getXimpl(j);
        return (Offset.m46getYimpl(j2) * Offset.m46getYimpl(j)) + (Offset.m45getXimpl(j2) * m45getXimpl);
    }

    /* renamed from: mag-k-4lQ0M  reason: not valid java name */
    public static final float m446magk4lQ0M(long j) {
        return Offset.m44getDistanceimpl(j);
    }

    public static final long makeWithAngleMag(Offset.Companion companion, float f, float f2) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        double d = f;
        return Vec2(((float) Math.cos(d)) * f2, f2 * ((float) Math.sin(d)));
    }

    /* renamed from: product-3MmeM6k  reason: not valid java name */
    public static final long m447product3MmeM6k(long j, float f) {
        return Vec2(Offset.m45getXimpl(j) * f, Offset.m46getYimpl(j) * f);
    }

    /* renamed from: rotate-YOhFQsI  reason: not valid java name */
    public static final long m448rotateYOhFQsI(long j, float f, long j2) {
        long m47minusMKHz9U = Offset.m47minusMKHz9U(j, j2);
        double d = f;
        return Offset.m48plusMKHz9U(j2, OffsetKt.Offset((Offset.m45getXimpl(m47minusMKHz9U) * ((float) Math.cos(d))) - (Offset.m46getYimpl(m47minusMKHz9U) * ((float) Math.sin(d))), (Offset.m46getYimpl(m47minusMKHz9U) * ((float) Math.cos(d))) + (Offset.m45getXimpl(m47minusMKHz9U) * ((float) Math.sin(d)))));
    }

    /* renamed from: rotate-YOhFQsI$default  reason: not valid java name */
    public static long m449rotateYOhFQsI$default(long j, float f, long j2, int i, Object obj) {
        if ((i & 2) != 0) {
            Offset.Companion companion = Offset.Companion;
            j2 = Offset.Zero;
        }
        return m448rotateYOhFQsI(j, f, j2);
    }

    /* renamed from: str-3MmeM6k  reason: not valid java name */
    public static final String m450str3MmeM6k(long j, String fmt) {
        Intrinsics.checkNotNullParameter(fmt, "fmt");
        String format = String.format("<" + fmt + "," + fmt + ">", Arrays.copyOf(new Object[]{Float.valueOf(Offset.m45getXimpl(j)), Float.valueOf(Offset.m46getYimpl(j))}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
        return format;
    }

    /* renamed from: str-3MmeM6k$default  reason: not valid java name */
    public static /* synthetic */ String m451str3MmeM6k$default(long j, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "%+.2f";
        }
        return m450str3MmeM6k(j, str);
    }
}
