package androidx.compose.foundation.layout;

import androidx.compose.animation.core.AnimationVector4D$$ExternalSyntheticOutline0;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class SizeModifier extends InspectorValueInfo implements LayoutModifier {
    private final boolean enforceIncoming;
    private final float maxHeight;
    private final float maxWidth;
    private final float minHeight;
    private final float minWidth;

    public SizeModifier(float f, float f2, Function1 function1) {
        super(function1);
        this.minWidth = Float.NaN;
        this.minHeight = f;
        this.maxWidth = Float.NaN;
        this.maxHeight = f2;
        this.enforceIncoming = true;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SizeModifier)) {
            return false;
        }
        SizeModifier sizeModifier = (SizeModifier) obj;
        if (!Dp.m390equalsimpl0(this.minWidth, sizeModifier.minWidth) || !Dp.m390equalsimpl0(this.minHeight, sizeModifier.minHeight) || !Dp.m390equalsimpl0(this.maxWidth, sizeModifier.maxWidth) || !Dp.m390equalsimpl0(this.maxHeight, sizeModifier.maxHeight) || this.enforceIncoming != sizeModifier.enforceIncoming) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return AnimationVector4D$$ExternalSyntheticOutline0.m(this.maxHeight, AnimationVector4D$$ExternalSyntheticOutline0.m(this.maxWidth, AnimationVector4D$$ExternalSyntheticOutline0.m(this.minHeight, Float.hashCode(this.minWidth) * 31, 31), 31), 31);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0061, code lost:
        if (r8 != Integer.MAX_VALUE) goto L21;
     */
    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.ui.layout.MeasureResult mo2measure3p2s80s(androidx.compose.ui.layout.MeasureScope r12, androidx.compose.ui.layout.Measurable r13, long r14) {
        /*
            Method dump skipped, instructions count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.SizeModifier.mo2measure3p2s80s(androidx.compose.ui.layout.MeasureScope, androidx.compose.ui.layout.Measurable, long):androidx.compose.ui.layout.MeasureResult");
    }
}
