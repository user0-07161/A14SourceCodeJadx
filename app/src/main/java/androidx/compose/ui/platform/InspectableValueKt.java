package androidx.compose.ui.platform;

import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class InspectableValueKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Function1 NoInspectorInfo = new Function1() { // from class: androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
            Intrinsics.checkNotNullParameter(null, "$this$null");
            return Unit.INSTANCE;
        }
    };
    private static boolean isDebugInspectorInfoEnabled;

    public static final Function1 getNoInspectorInfo() {
        return NoInspectorInfo;
    }
}
