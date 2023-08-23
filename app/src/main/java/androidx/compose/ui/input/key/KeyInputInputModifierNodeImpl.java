package androidx.compose.ui.input.key;

import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class KeyInputInputModifierNodeImpl extends Modifier.Node implements KeyInputModifierNode {
    private Function1 onEvent;

    public KeyInputInputModifierNodeImpl(Function1 function1) {
        this.onEvent = function1;
    }

    /* renamed from: onKeyEvent-ZmokQxo  reason: not valid java name */
    public final boolean m176onKeyEventZmokQxo(android.view.KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Function1 function1 = this.onEvent;
        if (function1 != null) {
            return ((Boolean) function1.invoke(KeyEvent.m174boximpl(event))).booleanValue();
        }
        return false;
    }

    /* renamed from: onPreKeyEvent-ZmokQxo  reason: not valid java name */
    public final boolean m177onPreKeyEventZmokQxo(android.view.KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return false;
    }

    public final void setOnEvent(Function1 function1) {
        this.onEvent = function1;
    }
}
