package androidx.compose.ui;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectorValueInfo;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class ComposedModifier extends InspectorValueInfo implements Modifier.Element {
    private final Function3 factory;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposedModifier(Function1 inspectorInfo, Function3 function3) {
        super(inspectorInfo);
        Intrinsics.checkNotNullParameter(inspectorInfo, "inspectorInfo");
        this.factory = function3;
    }

    public final Function3 getFactory() {
        return this.factory;
    }
}
