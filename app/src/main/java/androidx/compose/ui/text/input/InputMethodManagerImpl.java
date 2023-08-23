package androidx.compose.ui.text.input;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class InputMethodManagerImpl {
    private final ImmHelper30 helper;
    private final Lazy imm$delegate;
    private final View view;

    public InputMethodManagerImpl(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
        this.imm$delegate = LazyKt.lazy(new Function0() { // from class: androidx.compose.ui.text.input.InputMethodManagerImpl$imm$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                View view2;
                view2 = InputMethodManagerImpl.this.view;
                Object systemService = view2.getContext().getSystemService("input_method");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                return (InputMethodManager) systemService;
            }
        });
        this.helper = new ImmHelper30(view);
    }

    public final void hideSoftInput() {
        this.helper.hideSoftInput((InputMethodManager) this.imm$delegate.getValue());
    }

    public final void restartInput() {
        ((InputMethodManager) this.imm$delegate.getValue()).restartInput(this.view);
    }

    public final void showSoftInput() {
        this.helper.showSoftInput((InputMethodManager) this.imm$delegate.getValue());
    }
}
