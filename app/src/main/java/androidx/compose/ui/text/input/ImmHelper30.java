package androidx.compose.ui.text.input;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import androidx.core.view.WindowInsetsControllerCompat;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
final class ImmHelper30 {
    private ImmHelper21 _immHelper21;
    private final View view;

    public ImmHelper30(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
    }

    private final WindowInsetsControllerCompat getInsetsControllerCompat() {
        Window window;
        View view = this.view;
        view.getParent();
        Context baseContext = view.getContext();
        Intrinsics.checkNotNullExpressionValue(baseContext, "context");
        while (true) {
            if (baseContext instanceof Activity) {
                window = ((Activity) baseContext).getWindow();
                break;
            } else if (baseContext instanceof ContextWrapper) {
                baseContext = ((ContextWrapper) baseContext).getBaseContext();
                Intrinsics.checkNotNullExpressionValue(baseContext, "baseContext");
            } else {
                window = null;
                break;
            }
        }
        if (window == null) {
            return null;
        }
        return new WindowInsetsControllerCompat(window);
    }

    public final void hideSoftInput(InputMethodManager imm) {
        Intrinsics.checkNotNullParameter(imm, "imm");
        WindowInsetsControllerCompat insetsControllerCompat = getInsetsControllerCompat();
        if (insetsControllerCompat != null) {
            insetsControllerCompat.hide();
            return;
        }
        ImmHelper21 immHelper21 = this._immHelper21;
        if (immHelper21 == null) {
            immHelper21 = new ImmHelper21(this.view);
            this._immHelper21 = immHelper21;
        }
        immHelper21.hideSoftInput(imm);
    }

    public final void showSoftInput(InputMethodManager imm) {
        Intrinsics.checkNotNullParameter(imm, "imm");
        WindowInsetsControllerCompat insetsControllerCompat = getInsetsControllerCompat();
        if (insetsControllerCompat != null) {
            insetsControllerCompat.show();
            return;
        }
        ImmHelper21 immHelper21 = this._immHelper21;
        if (immHelper21 == null) {
            immHelper21 = new ImmHelper21(this.view);
            this._immHelper21 = immHelper21;
        }
        immHelper21.showSoftInput(imm);
    }
}
