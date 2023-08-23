package androidx.compose.runtime.internal;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.RecomposeScopeImpl;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ComposableLambdaImpl implements ComposableLambda {
    private Object _block;
    private final int key;
    private RecomposeScopeImpl scope;
    private List scopes;
    private final boolean tracked;

    public ComposableLambdaImpl(int i, boolean z) {
        this.key = i;
        this.tracked = z;
    }

    private final void trackRead(Composer composer) {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        if (this.tracked && (currentRecomposeScope$runtime_release = ((ComposerImpl) composer).getCurrentRecomposeScope$runtime_release()) != null) {
            currentRecomposeScope$runtime_release.setUsed();
            if (ComposableLambdaKt.replacableWith(this.scope, currentRecomposeScope$runtime_release)) {
                this.scope = currentRecomposeScope$runtime_release;
                return;
            }
            List list = this.scopes;
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                this.scopes = arrayList;
                arrayList.add(currentRecomposeScope$runtime_release);
                return;
            }
            ArrayList arrayList2 = (ArrayList) list;
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                if (ComposableLambdaKt.replacableWith((RecomposeScopeImpl) arrayList2.get(i), currentRecomposeScope$runtime_release)) {
                    arrayList2.set(i, currentRecomposeScope$runtime_release);
                    return;
                }
            }
            arrayList2.add(currentRecomposeScope$runtime_release);
        }
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        int bitsForSlot;
        Composer c = (Composer) obj;
        int intValue = ((Number) obj2).intValue();
        Intrinsics.checkNotNullParameter(c, "c");
        ComposerImpl composerImpl = (ComposerImpl) c;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(2, 0);
        } else {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(1, 0);
        }
        int i = intValue | bitsForSlot;
        Object obj3 = this._block;
        Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Function2<@[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, obj3);
        Object invoke = ((Function2) obj3).invoke(composerImpl, Integer.valueOf(i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(2, this);
            endRestartGroup.updateScope(this);
        }
        return invoke;
    }

    public final void update(Lambda block) {
        boolean z;
        Intrinsics.checkNotNullParameter(block, "block");
        if (!Intrinsics.areEqual(this._block, block)) {
            if (this._block == null) {
                z = true;
            } else {
                z = false;
            }
            this._block = block;
            if (!z && this.tracked) {
                RecomposeScopeImpl recomposeScopeImpl = this.scope;
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.invalidate();
                    this.scope = null;
                }
                List list = this.scopes;
                if (list != null) {
                    ArrayList arrayList = (ArrayList) list;
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        ((RecomposeScopeImpl) arrayList.get(i)).invalidate();
                    }
                    arrayList.clear();
                }
            }
        }
    }

    @Override // kotlin.jvm.functions.Function3
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke(obj, (Composer) obj2, ((Number) obj3).intValue());
    }

    @Override // kotlin.jvm.functions.Function4
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke(obj, obj2, (Composer) obj3, ((Number) obj4).intValue());
    }

    @Override // kotlin.jvm.functions.Function5
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return invoke(obj, obj2, obj3, (Composer) obj4, ((Number) obj5).intValue());
    }

    public final Object invoke(final Object obj, Composer c, final int i) {
        int bitsForSlot;
        Intrinsics.checkNotNullParameter(c, "c");
        ComposerImpl composerImpl = (ComposerImpl) c;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(2, 1);
        } else {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(1, 1);
        }
        Object obj2 = this._block;
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = 'p1')] kotlin.Any?, @[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(3, obj2);
        Object invoke = ((Function3) obj2).invoke(obj, composerImpl, Integer.valueOf(bitsForSlot | i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.runtime.internal.ComposableLambdaImpl$invoke$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    Composer nc = (Composer) obj3;
                    ((Number) obj4).intValue();
                    Intrinsics.checkNotNullParameter(nc, "nc");
                    ComposableLambdaImpl.this.invoke(obj, nc, i | 1);
                    return Unit.INSTANCE;
                }
            });
        }
        return invoke;
    }

    public final Object invoke(final Object obj, final Object obj2, Composer c, final int i) {
        int bitsForSlot;
        Intrinsics.checkNotNullParameter(c, "c");
        ComposerImpl composerImpl = (ComposerImpl) c;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(2, 2);
        } else {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(1, 2);
        }
        Object obj3 = this._block;
        Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Function4<@[ParameterName(name = 'p1')] kotlin.Any?, @[ParameterName(name = 'p2')] kotlin.Any?, @[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(4, obj3);
        Object invoke = ((Function4) obj3).invoke(obj, obj2, composerImpl, Integer.valueOf(bitsForSlot | i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.runtime.internal.ComposableLambdaImpl$invoke$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    Composer nc = (Composer) obj4;
                    ((Number) obj5).intValue();
                    Intrinsics.checkNotNullParameter(nc, "nc");
                    ComposableLambdaImpl.this.invoke(obj, obj2, nc, i | 1);
                    return Unit.INSTANCE;
                }
            });
        }
        return invoke;
    }

    public final Object invoke(final Object obj, final Object obj2, final Object obj3, Composer c, final int i) {
        int bitsForSlot;
        Intrinsics.checkNotNullParameter(c, "c");
        ComposerImpl composerImpl = (ComposerImpl) c;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(2, 3);
        } else {
            bitsForSlot = ComposableLambdaKt.bitsForSlot(1, 3);
        }
        Object obj4 = this._block;
        Intrinsics.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.Function5<@[ParameterName(name = 'p1')] kotlin.Any?, @[ParameterName(name = 'p2')] kotlin.Any?, @[ParameterName(name = 'p3')] kotlin.Any?, @[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(5, obj4);
        Object invoke = ((Function5) obj4).invoke(obj, obj2, obj3, composerImpl, Integer.valueOf(bitsForSlot | i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: androidx.compose.runtime.internal.ComposableLambdaImpl$invoke$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj5, Object obj6) {
                    Composer nc = (Composer) obj5;
                    ((Number) obj6).intValue();
                    Intrinsics.checkNotNullParameter(nc, "nc");
                    ComposableLambdaImpl.this.invoke(obj, obj2, obj3, nc, i | 1);
                    return Unit.INSTANCE;
                }
            });
        }
        return invoke;
    }
}
