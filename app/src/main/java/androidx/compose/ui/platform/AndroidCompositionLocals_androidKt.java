package androidx.compose.ui.platform;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewParent;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.res.ImageVectorCache;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import com.android.egg.R;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AndroidCompositionLocals_androidKt {
    private static final DynamicProvidableCompositionLocal LocalConfiguration;
    private static final StaticProvidableCompositionLocal LocalContext;
    private static final StaticProvidableCompositionLocal LocalImageVectorCache;
    private static final StaticProvidableCompositionLocal LocalLifecycleOwner;
    private static final StaticProvidableCompositionLocal LocalSavedStateRegistryOwner;
    private static final StaticProvidableCompositionLocal LocalView;

    static {
        SnapshotMutationPolicy neverEqualPolicy = SnapshotStateKt.neverEqualPolicy();
        AndroidCompositionLocals_androidKt$LocalConfiguration$1 defaultFactory = new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalConfiguration$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalConfiguration");
                throw null;
            }
        };
        Intrinsics.checkNotNullParameter(defaultFactory, "defaultFactory");
        LocalConfiguration = new DynamicProvidableCompositionLocal(neverEqualPolicy, defaultFactory);
        LocalContext = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalContext$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalContext");
                throw null;
            }
        });
        LocalImageVectorCache = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalImageVectorCache$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalImageVectorCache");
                throw null;
            }
        });
        LocalLifecycleOwner = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalLifecycleOwner$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalLifecycleOwner");
                throw null;
            }
        });
        LocalSavedStateRegistryOwner = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalSavedStateRegistryOwner$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalSavedStateRegistryOwner");
                throw null;
            }
        });
        LocalView = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$LocalView$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidCompositionLocals_androidKt.access$noLocalProvidedFor("LocalView");
                throw null;
            }
        });
    }

    /* JADX WARN: Type inference failed for: r5v5, types: [androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$3, kotlin.jvm.internal.Lambda] */
    public static final void ProvideAndroidCompositionLocals(final AndroidComposeView owner, final Function2 content, Composer composer, final int i) {
        String id;
        final boolean z;
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(content, "content");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1396852028);
        int i2 = ComposerKt.$r8$clinit;
        final Context context = owner.getContext();
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot = composerImpl.nextSlot();
        if (nextSlot == Composer.Companion.getEmpty()) {
            nextSlot = SnapshotStateKt.mutableStateOf(context.getResources().getConfiguration(), SnapshotStateKt.neverEqualPolicy());
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.endReplaceableGroup();
        final MutableState mutableState = (MutableState) nextSlot;
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed = composerImpl.changed(mutableState);
        Object nextSlot2 = composerImpl.nextSlot();
        if (changed || nextSlot2 == Composer.Companion.getEmpty()) {
            nextSlot2 = new Function1() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$1$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Configuration it = (Configuration) obj;
                    Intrinsics.checkNotNullParameter(it, "it");
                    ((SnapshotMutableStateImpl) MutableState.this).setValue(it);
                    return Unit.INSTANCE;
                }
            };
            composerImpl.updateValue(nextSlot2);
        }
        composerImpl.endReplaceableGroup();
        owner.setConfigurationChangeObserver((Function1) nextSlot2);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot3 = composerImpl.nextSlot();
        if (nextSlot3 == Composer.Companion.getEmpty()) {
            Intrinsics.checkNotNullExpressionValue(context, "context");
            nextSlot3 = new AndroidUriHandler(context);
            composerImpl.updateValue(nextSlot3);
        }
        composerImpl.endReplaceableGroup();
        final AndroidUriHandler androidUriHandler = (AndroidUriHandler) nextSlot3;
        AndroidComposeView.ViewTreeOwners viewTreeOwners = owner.getViewTreeOwners();
        if (viewTreeOwners != null) {
            composerImpl.startReplaceableGroup(-492369756);
            Object nextSlot4 = composerImpl.nextSlot();
            if (nextSlot4 == Composer.Companion.getEmpty()) {
                SavedStateRegistryOwner owner2 = viewTreeOwners.getSavedStateRegistryOwner();
                int i3 = DisposableSaveableStateRegistry_androidKt.$r8$clinit;
                Intrinsics.checkNotNullParameter(owner2, "owner");
                ViewParent parent = owner.getParent();
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
                View view = (View) parent;
                Object tag = view.getTag(R.id.compose_view_saveable_id_tag);
                LinkedHashMap linkedHashMap = null;
                if (tag instanceof String) {
                    id = (String) tag;
                } else {
                    id = null;
                }
                if (id == null) {
                    id = String.valueOf(view.getId());
                }
                Intrinsics.checkNotNullParameter(id, "id");
                final String concat = "SaveableStateRegistry:".concat(id);
                final SavedStateRegistry savedStateRegistry = owner2.getSavedStateRegistry();
                Bundle consumeRestoredStateForKey = savedStateRegistry.consumeRestoredStateForKey(concat);
                if (consumeRestoredStateForKey != null) {
                    linkedHashMap = new LinkedHashMap();
                    Set<String> keySet = consumeRestoredStateForKey.keySet();
                    Intrinsics.checkNotNullExpressionValue(keySet, "this.keySet()");
                    for (String key : keySet) {
                        ArrayList parcelableArrayList = consumeRestoredStateForKey.getParcelableArrayList(key);
                        Intrinsics.checkNotNull(parcelableArrayList, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Any?>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.Any?> }");
                        Intrinsics.checkNotNullExpressionValue(key, "key");
                        linkedHashMap.put(key, parcelableArrayList);
                        consumeRestoredStateForKey = consumeRestoredStateForKey;
                    }
                }
                final SaveableStateRegistry SaveableStateRegistry = SaveableStateRegistryKt.SaveableStateRegistry(linkedHashMap, new Function1() { // from class: androidx.compose.ui.platform.DisposableSaveableStateRegistry_androidKt$DisposableSaveableStateRegistry$saveableStateRegistry$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object it) {
                        boolean canBeSavedToBundle;
                        Intrinsics.checkNotNullParameter(it, "it");
                        canBeSavedToBundle = DisposableSaveableStateRegistry_androidKt.canBeSavedToBundle(it);
                        return Boolean.valueOf(canBeSavedToBundle);
                    }
                });
                try {
                    savedStateRegistry.registerSavedStateProvider(concat, new SavedStateRegistry.SavedStateProvider() { // from class: androidx.compose.ui.platform.DisposableSaveableStateRegistry_androidKt$DisposableSaveableStateRegistry$registered$1
                        @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
                        public final Bundle saveState() {
                            ArrayList<? extends Parcelable> arrayList;
                            Map performSave = SaveableStateRegistry.this.performSave();
                            Bundle bundle = new Bundle();
                            for (Map.Entry entry : performSave.entrySet()) {
                                String str = (String) entry.getKey();
                                List list = (List) entry.getValue();
                                if (list instanceof ArrayList) {
                                    arrayList = (ArrayList) list;
                                } else {
                                    arrayList = new ArrayList<>(list);
                                }
                                bundle.putParcelableArrayList(str, arrayList);
                            }
                            return bundle;
                        }
                    });
                    z = true;
                } catch (IllegalArgumentException unused) {
                    z = false;
                }
                DisposableSaveableStateRegistry disposableSaveableStateRegistry = new DisposableSaveableStateRegistry(SaveableStateRegistry, new Function0() { // from class: androidx.compose.ui.platform.DisposableSaveableStateRegistry_androidKt$DisposableSaveableStateRegistry$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        if (z) {
                            savedStateRegistry.unregisterSavedStateProvider(concat);
                        }
                        return Unit.INSTANCE;
                    }
                });
                composerImpl.updateValue(disposableSaveableStateRegistry);
                nextSlot4 = disposableSaveableStateRegistry;
            }
            composerImpl.endReplaceableGroup();
            final DisposableSaveableStateRegistry disposableSaveableStateRegistry2 = (DisposableSaveableStateRegistry) nextSlot4;
            EffectsKt.DisposableEffect(Unit.INSTANCE, new Function1() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    DisposableEffectScope DisposableEffect = (DisposableEffectScope) obj;
                    Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                    final DisposableSaveableStateRegistry disposableSaveableStateRegistry3 = DisposableSaveableStateRegistry.this;
                    return new DisposableEffectResult() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$2$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public final void dispose() {
                            DisposableSaveableStateRegistry.this.dispose();
                        }
                    };
                }
            }, composerImpl);
            Intrinsics.checkNotNullExpressionValue(context, "context");
            SnapshotMutableStateImpl snapshotMutableStateImpl = (SnapshotMutableStateImpl) mutableState;
            Configuration configuration = (Configuration) snapshotMutableStateImpl.getValue();
            composerImpl.startReplaceableGroup(-485908294);
            int i4 = ComposerKt.$r8$clinit;
            composerImpl.startReplaceableGroup(-492369756);
            Object nextSlot5 = composerImpl.nextSlot();
            if (nextSlot5 == Composer.Companion.getEmpty()) {
                nextSlot5 = new ImageVectorCache();
                composerImpl.updateValue(nextSlot5);
            }
            composerImpl.endReplaceableGroup();
            final ImageVectorCache imageVectorCache = (ImageVectorCache) nextSlot5;
            composerImpl.startReplaceableGroup(-492369756);
            Object nextSlot6 = composerImpl.nextSlot();
            Configuration configuration2 = nextSlot6;
            if (nextSlot6 == Composer.Companion.getEmpty()) {
                Configuration configuration3 = new Configuration();
                if (configuration != null) {
                    configuration3.setTo(configuration);
                }
                composerImpl.updateValue(configuration3);
                configuration2 = configuration3;
            }
            composerImpl.endReplaceableGroup();
            final Configuration configuration4 = (Configuration) configuration2;
            composerImpl.startReplaceableGroup(-492369756);
            Object nextSlot7 = composerImpl.nextSlot();
            if (nextSlot7 == Composer.Companion.getEmpty()) {
                nextSlot7 = new ComponentCallbacks2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1
                    @Override // android.content.ComponentCallbacks
                    public final void onConfigurationChanged(Configuration configuration5) {
                        Intrinsics.checkNotNullParameter(configuration5, "configuration");
                        configuration4.updateFrom(configuration5);
                        imageVectorCache.prune();
                        configuration4.setTo(configuration5);
                    }

                    @Override // android.content.ComponentCallbacks
                    public final void onLowMemory() {
                        imageVectorCache.clear();
                    }

                    @Override // android.content.ComponentCallbacks2
                    public final void onTrimMemory(int i5) {
                        imageVectorCache.clear();
                    }
                };
                composerImpl.updateValue(nextSlot7);
            }
            composerImpl.endReplaceableGroup();
            final AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1 androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1 = (AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1) nextSlot7;
            EffectsKt.DisposableEffect(imageVectorCache, new Function1() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    DisposableEffectScope DisposableEffect = (DisposableEffectScope) obj;
                    Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                    context.getApplicationContext().registerComponentCallbacks(androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1);
                    final Context context2 = context;
                    final AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1 androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$12 = androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1;
                    return new DisposableEffectResult() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$1$invoke$$inlined$onDispose$1
                        @Override // androidx.compose.runtime.DisposableEffectResult
                        public final void dispose() {
                            context2.getApplicationContext().unregisterComponentCallbacks(androidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$12);
                        }
                    };
                }
            }, composerImpl);
            composerImpl.endReplaceableGroup();
            Configuration configuration5 = (Configuration) snapshotMutableStateImpl.getValue();
            Intrinsics.checkNotNullExpressionValue(configuration5, "configuration");
            CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{LocalConfiguration.provides(configuration5), LocalContext.provides(context), LocalLifecycleOwner.provides(viewTreeOwners.getLifecycleOwner()), LocalSavedStateRegistryOwner.provides(viewTreeOwners.getSavedStateRegistryOwner()), SaveableStateRegistryKt.getLocalSaveableStateRegistry().provides(disposableSaveableStateRegistry2), LocalView.provides(owner), LocalImageVectorCache.provides(imageVectorCache)}, ComposableLambdaKt.composableLambda(composerImpl, 1471621628, new Function2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    Composer composer2 = (Composer) obj;
                    if ((((Number) obj2).intValue() & 11) == 2) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                        if (composerImpl2.getSkipping()) {
                            composerImpl2.skipToGroupEnd();
                            return Unit.INSTANCE;
                        }
                    }
                    int i5 = ComposerKt.$r8$clinit;
                    CompositionLocalsKt.ProvideCommonCompositionLocals(AndroidComposeView.this, androidUriHandler, content, composer2, ((i << 3) & 896) | 72);
                    return Unit.INSTANCE;
                }
            }), composerImpl, 56);
            RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
            if (endRestartGroup != null) {
                endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$4
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        ((Number) obj2).intValue();
                        AndroidCompositionLocals_androidKt.ProvideAndroidCompositionLocals(AndroidComposeView.this, content, (Composer) obj, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                        return Unit.INSTANCE;
                    }
                });
                return;
            }
            return;
        }
        throw new IllegalStateException("Called when the ViewTreeOwnersAvailability is not yet in Available state");
    }

    public static final void access$noLocalProvidedFor(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }
}
