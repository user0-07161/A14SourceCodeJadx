package androidx.compose.ui.platform;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.compose.runtime.MonotonicFrameClock;
import androidx.compose.runtime.PausableMonotonicFrameClock;
import androidx.compose.runtime.Recomposer;
import androidx.compose.ui.MotionDurationScale;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import com.android.egg.R;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Lazy;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.android.HandlerDispatcherKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class WindowRecomposerPolicy {
    private static final AtomicReference factory;

    static {
        WindowRecomposerFactory.Companion.getClass();
        factory = new AtomicReference(WindowRecomposerFactory$Companion$LifecycleAware$1.INSTANCE);
    }

    public static Recomposer createAndInstallWindowRecomposer$ui_release(final View view) {
        boolean z;
        AndroidUiDispatcher$Companion$currentThread$1 androidUiDispatcher$Companion$currentThread$1;
        CoroutineContext coroutineContext;
        final PausableMonotonicFrameClock pausableMonotonicFrameClock;
        Lifecycle lifecycle;
        Lazy lazy;
        ((WindowRecomposerFactory$Companion$LifecycleAware$1) ((WindowRecomposerFactory) factory.get())).getClass();
        int i = WindowRecomposer_androidKt.$r8$clinit;
        EmptyCoroutineContext coroutineContext2 = EmptyCoroutineContext.INSTANCE;
        Intrinsics.checkNotNullParameter(coroutineContext2, "coroutineContext");
        ContinuationInterceptor.Key key = ContinuationInterceptor.Key;
        int i2 = AndroidUiDispatcher.$r8$clinit;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            lazy = AndroidUiDispatcher.Main$delegate;
            coroutineContext = (CoroutineContext) lazy.getValue();
        } else {
            androidUiDispatcher$Companion$currentThread$1 = AndroidUiDispatcher.currentThread;
            coroutineContext = (CoroutineContext) androidUiDispatcher$Companion$currentThread$1.get();
            if (coroutineContext == null) {
                throw new IllegalStateException("no AndroidUiDispatcher for this thread".toString());
            }
        }
        CoroutineContext plus = coroutineContext.plus(coroutineContext2);
        MonotonicFrameClock monotonicFrameClock = (MonotonicFrameClock) plus.get(MonotonicFrameClock.Key);
        if (monotonicFrameClock != null) {
            PausableMonotonicFrameClock pausableMonotonicFrameClock2 = new PausableMonotonicFrameClock(monotonicFrameClock);
            pausableMonotonicFrameClock2.pause();
            pausableMonotonicFrameClock = pausableMonotonicFrameClock2;
        } else {
            pausableMonotonicFrameClock = null;
        }
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        CoroutineContext coroutineContext3 = (MotionDurationScale) plus.get(MotionDurationScale.Key);
        if (coroutineContext3 == null) {
            coroutineContext3 = new MotionDurationScaleImpl();
            ref$ObjectRef.element = coroutineContext3;
        }
        if (pausableMonotonicFrameClock != null) {
            coroutineContext2 = pausableMonotonicFrameClock;
        }
        CoroutineContext plus2 = plus.plus(coroutineContext2).plus(coroutineContext3);
        final Recomposer recomposer = new Recomposer(plus2);
        final ContextScope CoroutineScope = CoroutineScopeKt.CoroutineScope(plus2);
        LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(view);
        if (lifecycleOwner != null) {
            lifecycle = lifecycleOwner.getLifecycle();
        } else {
            lifecycle = null;
        }
        if (lifecycle != null) {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$1
                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewAttachedToWindow(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewDetachedFromWindow(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    view.removeOnAttachStateChangeListener(this);
                    recomposer.cancel();
                }
            });
            lifecycle.addObserver(new LifecycleEventObserver() { // from class: androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2

                /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
                /* loaded from: classes.dex */
                public abstract /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] iArr = new int[Lifecycle.Event.values().length];
                        try {
                            iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
                        } catch (NoSuchFieldError unused) {
                        }
                        try {
                            iArr[Lifecycle.Event.ON_START.ordinal()] = 2;
                        } catch (NoSuchFieldError unused2) {
                        }
                        try {
                            iArr[Lifecycle.Event.ON_STOP.ordinal()] = 3;
                        } catch (NoSuchFieldError unused3) {
                        }
                        try {
                            iArr[Lifecycle.Event.ON_DESTROY.ordinal()] = 4;
                        } catch (NoSuchFieldError unused4) {
                        }
                        try {
                            iArr[Lifecycle.Event.ON_PAUSE.ordinal()] = 5;
                        } catch (NoSuchFieldError unused5) {
                        }
                        try {
                            iArr[Lifecycle.Event.ON_RESUME.ordinal()] = 6;
                        } catch (NoSuchFieldError unused6) {
                        }
                        try {
                            iArr[Lifecycle.Event.ON_ANY.ordinal()] = 7;
                        } catch (NoSuchFieldError unused7) {
                        }
                        $EnumSwitchMapping$0 = iArr;
                    }
                }

                @Override // androidx.lifecycle.LifecycleEventObserver
                public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                    int i3 = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
                    if (i3 != 1) {
                        PausableMonotonicFrameClock pausableMonotonicFrameClock3 = pausableMonotonicFrameClock;
                        if (i3 != 2) {
                            if (i3 != 3) {
                                if (i3 == 4) {
                                    recomposer.cancel();
                                    return;
                                }
                                return;
                            } else if (pausableMonotonicFrameClock3 != null) {
                                pausableMonotonicFrameClock3.pause();
                                return;
                            } else {
                                return;
                            }
                        } else if (pausableMonotonicFrameClock3 != null) {
                            pausableMonotonicFrameClock3.resume();
                            return;
                        } else {
                            return;
                        }
                    }
                    BuildersKt.launch$default(CoroutineScope, null, CoroutineStart.UNDISPATCHED, new WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1(ref$ObjectRef, recomposer, lifecycleOwner2, this, view, null), 1);
                }
            });
            view.setTag(R.id.androidx_compose_ui_view_composition_context, recomposer);
            GlobalScope globalScope = GlobalScope.INSTANCE;
            Handler handler = view.getHandler();
            Intrinsics.checkNotNullExpressionValue(handler, "rootView.handler");
            int i3 = HandlerDispatcherKt.$r8$clinit;
            final Job launch$default = BuildersKt.launch$default(globalScope, new HandlerContext(handler, "windowRecomposer cleanup").getImmediate$1(), null, new WindowRecomposerPolicy$createAndInstallWindowRecomposer$unsetJob$1(recomposer, view, null), 2);
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.WindowRecomposerPolicy$createAndInstallWindowRecomposer$1
                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewAttachedToWindow(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewDetachedFromWindow(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    v.removeOnAttachStateChangeListener(this);
                    Job.this.cancel(null);
                }
            });
            return recomposer;
        }
        throw new IllegalStateException(("ViewTreeLifecycleOwner not found from " + view).toString());
    }
}
