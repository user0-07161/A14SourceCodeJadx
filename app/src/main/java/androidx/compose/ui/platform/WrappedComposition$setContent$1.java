package androidx.compose.ui.platform;

import android.view.View;
import android.view.ViewParent;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.lifecycle.Lifecycle;
import com.android.egg.R;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableSet;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class WrappedComposition$setContent$1 extends Lambda implements Function1 {
    final /* synthetic */ Function2 $content;
    final /* synthetic */ WrappedComposition this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WrappedComposition$setContent$1(WrappedComposition wrappedComposition, Function2 function2) {
        super(1);
        this.this$0 = wrappedComposition;
        this.$content = function2;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.compose.ui.platform.WrappedComposition$setContent$1$1, kotlin.jvm.internal.Lambda] */
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        boolean z;
        Lifecycle lifecycle;
        boolean z2;
        AndroidComposeView.ViewTreeOwners it = (AndroidComposeView.ViewTreeOwners) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        z = this.this$0.disposed;
        if (!z) {
            Lifecycle lifecycle2 = it.getLifecycleOwner().getLifecycle();
            this.this$0.lastContent = this.$content;
            lifecycle = this.this$0.addedToLifecycle;
            if (lifecycle == null) {
                this.this$0.addedToLifecycle = lifecycle2;
                lifecycle2.addObserver(this.this$0);
            } else {
                if (lifecycle2.getCurrentState().compareTo(Lifecycle.State.CREATED) >= 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (z2) {
                    Composition original = this.this$0.getOriginal();
                    final WrappedComposition wrappedComposition = this.this$0;
                    final Function2 function2 = this.$content;
                    original.setContent(ComposableLambdaKt.composableLambdaInstance(-2000640158, new Function2() { // from class: androidx.compose.ui.platform.WrappedComposition$setContent$1.1

                        /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
                        @DebugMetadata(c = "androidx.compose.ui.platform.WrappedComposition$setContent$1$1$1", f = "Wrapper.android.kt", l = {153}, m = "invokeSuspend")
                        /* renamed from: androidx.compose.ui.platform.WrappedComposition$setContent$1$1$1  reason: invalid class name and collision with other inner class name */
                        /* loaded from: classes.dex */
                        final class C00021 extends SuspendLambda implements Function2 {
                            int label;
                            final /* synthetic */ WrappedComposition this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            C00021(WrappedComposition wrappedComposition, Continuation continuation) {
                                super(2, continuation);
                                this.this$0 = wrappedComposition;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation create(Object obj, Continuation continuation) {
                                return new C00021(this.this$0, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return ((C00021) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                                int i = this.label;
                                if (i != 0) {
                                    if (i == 1) {
                                        ResultKt.throwOnFailure(obj);
                                    } else {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                } else {
                                    ResultKt.throwOnFailure(obj);
                                    AndroidComposeView owner = this.this$0.getOwner();
                                    this.label = 1;
                                    if (owner.keyboardVisibilityEventLoop(this) == coroutineSingletons) {
                                        return coroutineSingletons;
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        }

                        /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
                        @DebugMetadata(c = "androidx.compose.ui.platform.WrappedComposition$setContent$1$1$2", f = "Wrapper.android.kt", l = {154}, m = "invokeSuspend")
                        /* renamed from: androidx.compose.ui.platform.WrappedComposition$setContent$1$1$2  reason: invalid class name */
                        /* loaded from: classes.dex */
                        final class AnonymousClass2 extends SuspendLambda implements Function2 {
                            int label;
                            final /* synthetic */ WrappedComposition this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass2(WrappedComposition wrappedComposition, Continuation continuation) {
                                super(2, continuation);
                                this.this$0 = wrappedComposition;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation create(Object obj, Continuation continuation) {
                                return new AnonymousClass2(this.this$0, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) {
                                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                                int i = this.label;
                                if (i != 0) {
                                    if (i == 1) {
                                        ResultKt.throwOnFailure(obj);
                                    } else {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                } else {
                                    ResultKt.throwOnFailure(obj);
                                    AndroidComposeView owner = this.this$0.getOwner();
                                    this.label = 1;
                                    if (owner.boundsUpdatesEventLoop(this) == coroutineSingletons) {
                                        return coroutineSingletons;
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        }

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        /* JADX WARN: Type inference failed for: r0v7, types: [androidx.compose.ui.platform.WrappedComposition$setContent$1$1$3, kotlin.jvm.internal.Lambda] */
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            boolean z3;
                            Set set;
                            View view;
                            Object obj4;
                            Composer composer = (Composer) obj2;
                            if ((((Number) obj3).intValue() & 11) == 2) {
                                ComposerImpl composerImpl = (ComposerImpl) composer;
                                if (composerImpl.getSkipping()) {
                                    composerImpl.skipToGroupEnd();
                                    return Unit.INSTANCE;
                                }
                            }
                            int i = ComposerKt.$r8$clinit;
                            Object tag = WrappedComposition.this.getOwner().getTag(R.id.inspection_slot_table_set);
                            boolean z4 = true;
                            if ((tag instanceof Set) && (!(tag instanceof KMappedMarker) || (tag instanceof KMutableSet))) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            if (z3) {
                                set = (Set) tag;
                            } else {
                                set = null;
                            }
                            if (set == null) {
                                ViewParent parent = WrappedComposition.this.getOwner().getParent();
                                if (parent instanceof View) {
                                    view = (View) parent;
                                } else {
                                    view = null;
                                }
                                if (view != null) {
                                    obj4 = view.getTag(R.id.inspection_slot_table_set);
                                } else {
                                    obj4 = null;
                                }
                                if (!(obj4 instanceof Set) || ((obj4 instanceof KMappedMarker) && !(obj4 instanceof KMutableSet))) {
                                    z4 = false;
                                }
                                if (z4) {
                                    set = (Set) obj4;
                                } else {
                                    set = null;
                                }
                            }
                            if (set != null) {
                                ComposerImpl composerImpl2 = (ComposerImpl) composer;
                                set.add(composerImpl2.getCompositionData());
                                composerImpl2.collectParameterInformation();
                            }
                            EffectsKt.LaunchedEffect(WrappedComposition.this.getOwner(), new C00021(WrappedComposition.this, null), composer);
                            EffectsKt.LaunchedEffect(WrappedComposition.this.getOwner(), new AnonymousClass2(WrappedComposition.this, null), composer);
                            final WrappedComposition wrappedComposition2 = WrappedComposition.this;
                            final Function2 function22 = function2;
                            CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{InspectionTablesKt.getLocalInspectionTables().provides(set)}, ComposableLambdaKt.composableLambda(composer, -1193460702, new Function2() { // from class: androidx.compose.ui.platform.WrappedComposition.setContent.1.1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(Object obj5, Object obj6) {
                                    Composer composer2 = (Composer) obj5;
                                    if ((((Number) obj6).intValue() & 11) == 2) {
                                        ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                                        if (composerImpl3.getSkipping()) {
                                            composerImpl3.skipToGroupEnd();
                                            return Unit.INSTANCE;
                                        }
                                    }
                                    int i2 = ComposerKt.$r8$clinit;
                                    AndroidCompositionLocals_androidKt.ProvideAndroidCompositionLocals(WrappedComposition.this.getOwner(), function22, composer2, 8);
                                    return Unit.INSTANCE;
                                }
                            }), composer, 56);
                            return Unit.INSTANCE;
                        }
                    }, true));
                }
            }
        }
        return Unit.INSTANCE;
    }
}
