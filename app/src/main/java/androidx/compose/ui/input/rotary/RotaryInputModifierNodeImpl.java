package androidx.compose.ui.input.rotary;

import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class RotaryInputModifierNodeImpl extends Modifier.Node implements RotaryInputModifierNode {
    private Function1 onEvent;

    public RotaryInputModifierNodeImpl(Function1 function1) {
        this.onEvent = function1;
    }

    public final boolean onRotaryScrollEvent(RotaryScrollEvent rotaryScrollEvent) {
        Function1 function1 = this.onEvent;
        if (function1 != null) {
            return ((Boolean) function1.invoke(rotaryScrollEvent)).booleanValue();
        }
        return false;
    }

    public final void setOnEvent(Function1 function1) {
        this.onEvent = function1;
    }
}
