package androidx.compose.ui.platform;

import androidx.compose.ui.input.InputMode;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidComposeView$_inputModeManager$1 extends Lambda implements Function1 {
    final /* synthetic */ AndroidComposeView this$0;

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        boolean z;
        boolean z2;
        int m171unboximpl = ((InputMode) obj).m171unboximpl();
        boolean z3 = false;
        if (m171unboximpl == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            z3 = this.this$0.isInTouchMode();
        } else {
            if (m171unboximpl == 2) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                if (this.this$0.isInTouchMode()) {
                    z3 = this.this$0.requestFocusFromTouch();
                } else {
                    z3 = true;
                }
            }
        }
        return Boolean.valueOf(z3);
    }
}
