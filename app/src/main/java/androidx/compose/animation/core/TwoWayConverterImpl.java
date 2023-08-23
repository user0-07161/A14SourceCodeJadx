package androidx.compose.animation.core;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class TwoWayConverterImpl implements TwoWayConverter {
    private final Function1 convertFromVector;
    private final Function1 convertToVector;

    public TwoWayConverterImpl(Function1 convertToVector, Function1 convertFromVector) {
        Intrinsics.checkNotNullParameter(convertToVector, "convertToVector");
        Intrinsics.checkNotNullParameter(convertFromVector, "convertFromVector");
        this.convertToVector = convertToVector;
        this.convertFromVector = convertFromVector;
    }

    public final Function1 getConvertFromVector() {
        return this.convertFromVector;
    }

    public final Function1 getConvertToVector() {
        return this.convertToVector;
    }
}
