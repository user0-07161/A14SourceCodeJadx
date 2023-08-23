package androidx.compose.ui.platform;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class InspectorValueInfo {
    public InspectorValueInfo(Function1 info) {
        Intrinsics.checkNotNullParameter(info, "info");
    }
}
