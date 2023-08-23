package androidx.activity;

import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class OnBackPressedDispatcher {
    private final Runnable fallbackOnBackPressed;
    private OnBackInvokedDispatcher invokedDispatcher;
    private OnBackPressedDispatcher$Api33Impl$$ExternalSyntheticLambda0 onBackInvokedCallback;
    private final ArrayDeque onBackPressedCallbacks = new ArrayDeque();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* renamed from: androidx.activity.OnBackPressedDispatcher$1  reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass1 extends Lambda implements Function0 {
        final /* synthetic */ OnBackPressedDispatcher this$0;

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            this.this$0.updateBackInvokedCallbackState$activity_release();
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class LifecycleOnBackPressedCancellable implements LifecycleEventObserver {
        @Override // androidx.lifecycle.LifecycleEventObserver
        public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            if (event != Lifecycle.Event.ON_START) {
                if (event == Lifecycle.Event.ON_STOP || event != Lifecycle.Event.ON_DESTROY) {
                    return;
                }
                throw null;
            }
            throw null;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.activity.OnBackPressedDispatcher$Api33Impl$$ExternalSyntheticLambda0] */
    public OnBackPressedDispatcher(Runnable runnable) {
        this.fallbackOnBackPressed = runnable;
        final Function0 function0 = new Function0() { // from class: androidx.activity.OnBackPressedDispatcher.2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                OnBackPressedDispatcher.this.onBackPressed();
                return Unit.INSTANCE;
            }
        };
        this.onBackInvokedCallback = new OnBackInvokedCallback() { // from class: androidx.activity.OnBackPressedDispatcher$Api33Impl$$ExternalSyntheticLambda0
            public final void onBackInvoked() {
                Function0 onBackInvoked = Function0.this;
                Intrinsics.checkNotNullParameter(onBackInvoked, "$onBackInvoked");
                onBackInvoked.invoke();
            }
        };
    }

    public final void onBackPressed() {
        ArrayDeque arrayDeque = this.onBackPressedCallbacks;
        ListIterator listIterator = arrayDeque.listIterator(arrayDeque.getSize());
        if (!listIterator.hasPrevious()) {
            Runnable runnable = this.fallbackOnBackPressed;
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(listIterator.previous());
        throw null;
    }

    public final void setOnBackInvokedDispatcher(OnBackInvokedDispatcher invoker) {
        Intrinsics.checkNotNullParameter(invoker, "invoker");
        this.invokedDispatcher = invoker;
        updateBackInvokedCallbackState$activity_release();
    }

    public final void updateBackInvokedCallbackState$activity_release() {
        ArrayDeque arrayDeque = this.onBackPressedCallbacks;
        if (!(arrayDeque instanceof Collection) || !arrayDeque.isEmpty()) {
            Iterator it = arrayDeque.iterator();
            if (!it.hasNext()) {
                return;
            }
            OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(it.next());
            throw null;
        }
    }
}
