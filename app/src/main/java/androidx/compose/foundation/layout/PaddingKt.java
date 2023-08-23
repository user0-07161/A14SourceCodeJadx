package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class PaddingKt {
    /* renamed from: padding-3ABfNKs  reason: not valid java name */
    public static final Modifier m10padding3ABfNKs(Modifier padding, float f) {
        Intrinsics.checkNotNullParameter(padding, "$this$padding");
        return padding.then(new PaddingModifier(f, f, f, f, InspectableValueKt.getNoInspectorInfo()));
    }
}
