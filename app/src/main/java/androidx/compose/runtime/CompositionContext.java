package androidx.compose.runtime;

import androidx.compose.runtime.internal.ComposableLambdaImpl;
import kotlin.coroutines.CoroutineContext;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class CompositionContext {
    public abstract void composeInitial$runtime_release(ControlledComposition controlledComposition, ComposableLambdaImpl composableLambdaImpl);

    public abstract CoroutineContext getEffectCoroutineContext$runtime_release();

    public abstract void invalidate$runtime_release(ControlledComposition controlledComposition);

    public abstract MovableContentState movableContentStateResolve$runtime_release(MovableContentStateReference movableContentStateReference);

    public abstract void unregisterComposition$runtime_release(ControlledComposition controlledComposition);
}
