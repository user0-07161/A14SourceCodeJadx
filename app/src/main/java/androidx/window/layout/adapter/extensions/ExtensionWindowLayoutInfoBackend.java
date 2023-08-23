package androidx.window.layout.adapter.extensions;

import android.app.Activity;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.profileinstaller.ProfileInstaller$$ExternalSyntheticLambda0;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ConsumerAdapter$createSubscription$1;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.layout.WindowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.WindowBackend;
import androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ExtensionWindowLayoutInfoBackend implements WindowBackend {
    private final WindowLayoutComponent component;
    private final ConsumerAdapter consumerAdapter;
    private final ReentrantLock extensionWindowBackendLock = new ReentrantLock();
    private final Map contextToListeners = new LinkedHashMap();
    private final Map listenerToContext = new LinkedHashMap();
    private final Map consumerToToken = new LinkedHashMap();
    private final Map consumerToOemConsumer = new LinkedHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class MulticastConsumer implements Consumer {
        private final Context context;
        private WindowLayoutInfo lastKnownValue;
        private final ReentrantLock multicastConsumerLock;
        private final Set registeredListeners;

        public MulticastConsumer(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            this.multicastConsumerLock = new ReentrantLock();
            this.registeredListeners = new LinkedHashSet();
        }

        public final void addListener(WindowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0 windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0) {
            ReentrantLock reentrantLock = this.multicastConsumerLock;
            reentrantLock.lock();
            try {
                WindowLayoutInfo windowLayoutInfo = this.lastKnownValue;
                if (windowLayoutInfo != null) {
                    windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0.accept(windowLayoutInfo);
                }
                this.registeredListeners.add(windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0);
            } finally {
                reentrantLock.unlock();
            }
        }

        public final boolean isEmpty() {
            return this.registeredListeners.isEmpty();
        }

        public final void removeListener(Consumer listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            ReentrantLock reentrantLock = this.multicastConsumerLock;
            reentrantLock.lock();
            try {
                this.registeredListeners.remove(listener);
            } finally {
                reentrantLock.unlock();
            }
        }

        @Override // androidx.core.util.Consumer
        public final void accept(androidx.window.extensions.layout.WindowLayoutInfo value) {
            Intrinsics.checkNotNullParameter(value, "value");
            ReentrantLock reentrantLock = this.multicastConsumerLock;
            reentrantLock.lock();
            try {
                this.lastKnownValue = ExtensionsWindowLayoutInfoAdapter.translate$window_release(this.context, value);
                for (Consumer consumer : this.registeredListeners) {
                    consumer.accept(this.lastKnownValue);
                }
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public ExtensionWindowLayoutInfoBackend(WindowLayoutComponent windowLayoutComponent, ConsumerAdapter consumerAdapter) {
        this.component = windowLayoutComponent;
        this.consumerAdapter = consumerAdapter;
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public final void registerLayoutChangeCallback(Context context, ProfileInstaller$$ExternalSyntheticLambda0 profileInstaller$$ExternalSyntheticLambda0, WindowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0 windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0) {
        Unit unit;
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        ReentrantLock reentrantLock = this.extensionWindowBackendLock;
        reentrantLock.lock();
        Map map = this.contextToListeners;
        try {
            MulticastConsumer multicastConsumer = (MulticastConsumer) ((LinkedHashMap) map).get(context);
            Map map2 = this.listenerToContext;
            if (multicastConsumer != null) {
                multicastConsumer.addListener(windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0);
                map2.put(windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0, context);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                final MulticastConsumer multicastConsumer2 = new MulticastConsumer(context);
                map.put(context, multicastConsumer2);
                map2.put(windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0, context);
                multicastConsumer2.addListener(windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0);
                int i2 = ExtensionsUtil.$r8$clinit;
                try {
                    i = WindowExtensionsProvider.getWindowExtensions().getVendorApiLevel();
                } catch (NoClassDefFoundError | UnsupportedOperationException unused) {
                    i = 0;
                }
                WindowLayoutComponent windowLayoutComponent = this.component;
                if (i < 2) {
                    Function1 function1 = new Function1() { // from class: androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend$registerLayoutChangeCallback$1$2$consumeWindowLayoutInfo$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            androidx.window.extensions.layout.WindowLayoutInfo value = (androidx.window.extensions.layout.WindowLayoutInfo) obj;
                            Intrinsics.checkNotNullParameter(value, "value");
                            ExtensionWindowLayoutInfoBackend.MulticastConsumer.this.accept(value);
                            return Unit.INSTANCE;
                        }
                    };
                    if (context instanceof Activity) {
                        this.consumerToToken.put(multicastConsumer2, this.consumerAdapter.createSubscription(windowLayoutComponent, Reflection.getOrCreateKotlinClass(androidx.window.extensions.layout.WindowLayoutInfo.class), (Activity) context, function1));
                    } else {
                        multicastConsumer2.accept(new androidx.window.extensions.layout.WindowLayoutInfo(EmptyList.INSTANCE));
                    }
                } else {
                    androidx.window.extensions.core.util.function.Consumer consumer = new androidx.window.extensions.core.util.function.Consumer() { // from class: androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend$$ExternalSyntheticLambda0
                        @Override // androidx.window.extensions.core.util.function.Consumer
                        public final void accept(Object obj) {
                            androidx.window.extensions.layout.WindowLayoutInfo info = (androidx.window.extensions.layout.WindowLayoutInfo) obj;
                            ExtensionWindowLayoutInfoBackend.MulticastConsumer consumer2 = ExtensionWindowLayoutInfoBackend.MulticastConsumer.this;
                            Intrinsics.checkNotNullParameter(consumer2, "$consumer");
                            Intrinsics.checkNotNullExpressionValue(info, "info");
                            consumer2.accept(info);
                        }
                    };
                    this.consumerToOemConsumer.put(multicastConsumer2, consumer);
                    windowLayoutComponent.addWindowLayoutInfoListener(context, consumer);
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // androidx.window.layout.adapter.WindowBackend
    public final void unregisterLayoutChangeCallback(Consumer callback) {
        int i;
        Intrinsics.checkNotNullParameter(callback, "callback");
        ReentrantLock reentrantLock = this.extensionWindowBackendLock;
        reentrantLock.lock();
        Map map = this.listenerToContext;
        try {
            Context context = (Context) ((LinkedHashMap) map).get(callback);
            if (context == null) {
                return;
            }
            Map map2 = this.contextToListeners;
            MulticastConsumer multicastConsumer = (MulticastConsumer) ((LinkedHashMap) map2).get(context);
            if (multicastConsumer == null) {
                return;
            }
            multicastConsumer.removeListener(callback);
            map.remove(callback);
            if (multicastConsumer.isEmpty()) {
                map2.remove(context);
                int i2 = ExtensionsUtil.$r8$clinit;
                try {
                    i = WindowExtensionsProvider.getWindowExtensions().getVendorApiLevel();
                } catch (NoClassDefFoundError | UnsupportedOperationException unused) {
                    i = 0;
                }
                if (i < 2) {
                    ConsumerAdapter$createSubscription$1 consumerAdapter$createSubscription$1 = (ConsumerAdapter$createSubscription$1) this.consumerToToken.remove(multicastConsumer);
                    if (consumerAdapter$createSubscription$1 != null) {
                        consumerAdapter$createSubscription$1.dispose();
                    }
                } else {
                    androidx.window.extensions.core.util.function.Consumer consumer = (androidx.window.extensions.core.util.function.Consumer) this.consumerToOemConsumer.remove(multicastConsumer);
                    if (consumer != null) {
                        this.component.removeWindowLayoutInfoListener(consumer);
                    }
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
