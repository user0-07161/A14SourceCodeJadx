package androidx.core.view;

import androidx.activity.ComponentActivity$$ExternalSyntheticLambda0;
import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MenuHostHelper {
    private final Runnable mOnInvalidateMenuCallback;
    private final CopyOnWriteArrayList mMenuProviders = new CopyOnWriteArrayList();
    private final Map mProviderToLifecycleContainers = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class LifecycleContainer {
        final Lifecycle mLifecycle;
        private LifecycleEventObserver mObserver;

        LifecycleContainer(Lifecycle lifecycle, LifecycleEventObserver lifecycleEventObserver) {
            this.mLifecycle = lifecycle;
            this.mObserver = lifecycleEventObserver;
            lifecycle.addObserver(lifecycleEventObserver);
        }

        final void clearObservers() {
            this.mLifecycle.removeObserver(this.mObserver);
            this.mObserver = null;
        }
    }

    public static /* synthetic */ void $r8$lambda$VnUJXnvHt4N_mEoh2slqiieT0pg(MenuHostHelper menuHostHelper, Lifecycle.State state, Lifecycle.Event event) {
        menuHostHelper.getClass();
        if (event == Lifecycle.Event.upTo(state)) {
            menuHostHelper.addMenuProvider();
        } else if (event == Lifecycle.Event.ON_DESTROY) {
            menuHostHelper.removeMenuProvider();
        } else if (event == Lifecycle.Event.downFrom(state)) {
            menuHostHelper.mMenuProviders.remove((Object) null);
            menuHostHelper.mOnInvalidateMenuCallback.run();
        }
    }

    public MenuHostHelper(ComponentActivity$$ExternalSyntheticLambda0 componentActivity$$ExternalSyntheticLambda0) {
        this.mOnInvalidateMenuCallback = componentActivity$$ExternalSyntheticLambda0;
    }

    public final void addMenuProvider() {
        this.mMenuProviders.add(null);
        this.mOnInvalidateMenuCallback.run();
    }

    public final void onCreateMenu() {
        Iterator it = this.mMenuProviders.iterator();
        if (!it.hasNext()) {
            return;
        }
        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
        throw null;
    }

    public final void onMenuClosed() {
        Iterator it = this.mMenuProviders.iterator();
        if (!it.hasNext()) {
            return;
        }
        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
        throw null;
    }

    public final void onMenuItemSelected() {
        Iterator it = this.mMenuProviders.iterator();
        if (!it.hasNext()) {
            return;
        }
        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
        throw null;
    }

    public final void onPrepareMenu() {
        Iterator it = this.mMenuProviders.iterator();
        if (!it.hasNext()) {
            return;
        }
        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
        throw null;
    }

    public final void removeMenuProvider() {
        this.mMenuProviders.remove((Object) null);
        LifecycleContainer lifecycleContainer = (LifecycleContainer) ((HashMap) this.mProviderToLifecycleContainers).remove(null);
        if (lifecycleContainer != null) {
            lifecycleContainer.clearObservers();
        }
        this.mOnInvalidateMenuCallback.run();
    }

    public final void addMenuProvider(LifecycleOwner lifecycleOwner) {
        addMenuProvider();
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        HashMap hashMap = (HashMap) this.mProviderToLifecycleContainers;
        LifecycleContainer lifecycleContainer = (LifecycleContainer) hashMap.remove(null);
        if (lifecycleContainer != null) {
            lifecycleContainer.clearObservers();
        }
        hashMap.put(null, new LifecycleContainer(lifecycle, new LifecycleEventObserver() { // from class: androidx.core.view.MenuHostHelper$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                Lifecycle.Event event2 = Lifecycle.Event.ON_DESTROY;
                MenuHostHelper menuHostHelper = MenuHostHelper.this;
                if (event == event2) {
                    menuHostHelper.removeMenuProvider();
                } else {
                    menuHostHelper.getClass();
                }
            }
        }));
    }

    public final void addMenuProvider(LifecycleOwner lifecycleOwner, final Lifecycle.State state) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        HashMap hashMap = (HashMap) this.mProviderToLifecycleContainers;
        LifecycleContainer lifecycleContainer = (LifecycleContainer) hashMap.remove(null);
        if (lifecycleContainer != null) {
            lifecycleContainer.clearObservers();
        }
        hashMap.put(null, new LifecycleContainer(lifecycle, new LifecycleEventObserver() { // from class: androidx.core.view.MenuHostHelper$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                MenuHostHelper.$r8$lambda$VnUJXnvHt4N_mEoh2slqiieT0pg(MenuHostHelper.this, state, event);
            }
        }));
    }
}
