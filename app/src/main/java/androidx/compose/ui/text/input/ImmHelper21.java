package androidx.compose.ui.text.input;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ImmHelper21 {
    private final View view;

    /* renamed from: $r8$lambda$wQR5FNZ5Gpg1HM_nqB8XbX-blcs  reason: not valid java name */
    public static void m339$r8$lambda$wQR5FNZ5Gpg1HM_nqB8XbXblcs(InputMethodManager imm, ImmHelper21 this$0) {
        Intrinsics.checkNotNullParameter(imm, "$imm");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        imm.showSoftInput(this$0.view, 0);
    }

    public ImmHelper21(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
    }

    public final void hideSoftInput(InputMethodManager imm) {
        Intrinsics.checkNotNullParameter(imm, "imm");
        imm.hideSoftInputFromWindow(this.view.getWindowToken(), 0);
    }

    public final void showSoftInput(final InputMethodManager imm) {
        Intrinsics.checkNotNullParameter(imm, "imm");
        this.view.post(new Runnable() { // from class: androidx.compose.ui.text.input.ImmHelper21$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ImmHelper21.m339$r8$lambda$wQR5FNZ5Gpg1HM_nqB8XbXblcs(imm, this);
            }
        });
    }
}
