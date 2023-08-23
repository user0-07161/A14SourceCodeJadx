package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Matrix {
    /* renamed from: constructor-impl$default  reason: not valid java name */
    public static float[] m104constructorimpl$default() {
        return new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    }

    /* renamed from: map-MK-Hz9U  reason: not valid java name */
    public static final long m105mapMKHz9U(float[] fArr, long j) {
        boolean z;
        float m45getXimpl = Offset.m45getXimpl(j);
        float m46getYimpl = Offset.m46getYimpl(j);
        float f = 1 / (((fArr[7] * m46getYimpl) + (fArr[3] * m45getXimpl)) + fArr[15]);
        if (!Float.isInfinite(f) && !Float.isNaN(f)) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            f = 0.0f;
        }
        return OffsetKt.Offset(((fArr[4] * m46getYimpl) + (fArr[0] * m45getXimpl) + fArr[12]) * f, ((fArr[5] * m46getYimpl) + (fArr[1] * m45getXimpl) + fArr[13]) * f);
    }

    /* renamed from: map-impl  reason: not valid java name */
    public static final void m106mapimpl(float[] fArr, MutableRect mutableRect) {
        long m105mapMKHz9U = m105mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getLeft(), mutableRect.getTop()));
        long m105mapMKHz9U2 = m105mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getLeft(), mutableRect.getBottom()));
        long m105mapMKHz9U3 = m105mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getRight(), mutableRect.getTop()));
        long m105mapMKHz9U4 = m105mapMKHz9U(fArr, OffsetKt.Offset(mutableRect.getRight(), mutableRect.getBottom()));
        mutableRect.setLeft(Math.min(Math.min(Offset.m45getXimpl(m105mapMKHz9U), Offset.m45getXimpl(m105mapMKHz9U2)), Math.min(Offset.m45getXimpl(m105mapMKHz9U3), Offset.m45getXimpl(m105mapMKHz9U4))));
        mutableRect.setTop(Math.min(Math.min(Offset.m46getYimpl(m105mapMKHz9U), Offset.m46getYimpl(m105mapMKHz9U2)), Math.min(Offset.m46getYimpl(m105mapMKHz9U3), Offset.m46getYimpl(m105mapMKHz9U4))));
        mutableRect.setRight(Math.max(Math.max(Offset.m45getXimpl(m105mapMKHz9U), Offset.m45getXimpl(m105mapMKHz9U2)), Math.max(Offset.m45getXimpl(m105mapMKHz9U3), Offset.m45getXimpl(m105mapMKHz9U4))));
        mutableRect.setBottom(Math.max(Math.max(Offset.m46getYimpl(m105mapMKHz9U), Offset.m46getYimpl(m105mapMKHz9U2)), Math.max(Offset.m46getYimpl(m105mapMKHz9U3), Offset.m46getYimpl(m105mapMKHz9U4))));
    }
}
