package androidx.compose.ui.layout;

import androidx.compose.ui.modifier.ProvidableModifierLocal;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class BeyondBoundsLayoutKt {
    private static final ProvidableModifierLocal ModifierLocalBeyondBoundsLayout;

    static {
        BeyondBoundsLayoutKt$ModifierLocalBeyondBoundsLayout$1 defaultFactory = new Function0() { // from class: androidx.compose.ui.layout.BeyondBoundsLayoutKt$ModifierLocalBeyondBoundsLayout$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return null;
            }
        };
        Intrinsics.checkNotNullParameter(defaultFactory, "defaultFactory");
        ModifierLocalBeyondBoundsLayout = new ProvidableModifierLocal(defaultFactory);
    }

    public static final ProvidableModifierLocal getModifierLocalBeyondBoundsLayout() {
        return ModifierLocalBeyondBoundsLayout;
    }
}
