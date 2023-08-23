package androidx.core.view;

import android.view.Window;
import android.view.WindowInsetsController;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class WindowInsetsControllerCompat {
    private final Impl30 mImpl;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    abstract class Impl {
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class Impl30 extends Impl {
        final WindowInsetsController mInsetsController;
        protected Window mWindow;

        Impl30(Window window) {
            this.mInsetsController = window.getInsetsController();
            this.mWindow = window;
        }
    }

    public WindowInsetsControllerCompat(Window window) {
        this.mImpl = new Impl30(window);
    }

    public final void hide() {
        Impl30 impl30 = this.mImpl;
        Window window = impl30.mWindow;
        impl30.mInsetsController.hide(8);
    }

    public final void show() {
        Impl30 impl30 = this.mImpl;
        Window window = impl30.mWindow;
        impl30.mInsetsController.show(8);
    }
}
