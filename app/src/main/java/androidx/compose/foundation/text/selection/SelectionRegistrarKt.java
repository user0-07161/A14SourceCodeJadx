package androidx.compose.foundation.text.selection;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class SelectionRegistrarKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final DynamicProvidableCompositionLocal LocalSelectionRegistrar = CompositionLocalKt.compositionLocalOf$default(new Function0() { // from class: androidx.compose.foundation.text.selection.SelectionRegistrarKt$LocalSelectionRegistrar$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return null;
        }
    });

    public static final DynamicProvidableCompositionLocal getLocalSelectionRegistrar() {
        return LocalSelectionRegistrar;
    }
}
