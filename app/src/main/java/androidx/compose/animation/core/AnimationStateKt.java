package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AnimationStateKt {
    public static final AnimationVector createZeroVectorFrom(TwoWayConverter twoWayConverter, Object obj) {
        Intrinsics.checkNotNullParameter(twoWayConverter, "<this>");
        return AnimationVectorsKt.newInstance((AnimationVector) ((TwoWayConverterImpl) twoWayConverter).getConvertToVector().invoke(obj));
    }
}
