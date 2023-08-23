package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.intrinsics.CancellableKt;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class BuildersKt {
    public static final Job launch(CoroutineScope coroutineScope, CoroutineContext context, CoroutineStart start, Function2 block) {
        boolean z;
        AbstractCoroutine standaloneCoroutine;
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(block, "block");
        CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(coroutineScope, context);
        if (start == CoroutineStart.LAZY) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            standaloneCoroutine = new LazyStandaloneCoroutine(newCoroutineContext, block);
        } else {
            standaloneCoroutine = new StandaloneCoroutine(newCoroutineContext, true);
        }
        standaloneCoroutine.start(start, standaloneCoroutine, block);
        return standaloneCoroutine;
    }

    public static /* synthetic */ Job launch$default(CoroutineScope coroutineScope, MainCoroutineDispatcher mainCoroutineDispatcher, CoroutineStart coroutineStart, Function2 function2, int i) {
        CoroutineContext coroutineContext = mainCoroutineDispatcher;
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i & 2) != 0) {
            coroutineStart = CoroutineStart.DEFAULT;
        }
        return launch(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static final Object runBlocking(MainCoroutineDispatcher context, Function2 function2) {
        EventLoopImplPlatform currentOrNull$external__kotlinx_coroutines__android_common__kotlinx_coroutines;
        CoroutineContext newCoroutineContext;
        Intrinsics.checkNotNullParameter(context, "context");
        Thread currentThread = Thread.currentThread();
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) ContinuationInterceptor.DefaultImpls.get(context, ContinuationInterceptor.Key);
        GlobalScope globalScope = GlobalScope.INSTANCE;
        if (continuationInterceptor == null) {
            currentOrNull$external__kotlinx_coroutines__android_common__kotlinx_coroutines = ThreadLocalEventLoop.getEventLoop$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            newCoroutineContext = CoroutineContextKt.newCoroutineContext(globalScope, CoroutineContext.DefaultImpls.plus(context, currentOrNull$external__kotlinx_coroutines__android_common__kotlinx_coroutines));
        } else {
            if (continuationInterceptor instanceof EventLoopImplPlatform) {
                EventLoopImplPlatform eventLoopImplPlatform = (EventLoopImplPlatform) continuationInterceptor;
            }
            currentOrNull$external__kotlinx_coroutines__android_common__kotlinx_coroutines = ThreadLocalEventLoop.currentOrNull$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            newCoroutineContext = CoroutineContextKt.newCoroutineContext(globalScope, context);
        }
        Intrinsics.checkNotNullExpressionValue(currentThread, "currentThread");
        BlockingCoroutine blockingCoroutine = new BlockingCoroutine(newCoroutineContext, currentThread, currentOrNull$external__kotlinx_coroutines__android_common__kotlinx_coroutines);
        blockingCoroutine.start(CoroutineStart.DEFAULT, blockingCoroutine, function2);
        return blockingCoroutine.joinBlocking();
    }

    public static final Object withContext(CoroutineContext.Element element, Function2 function2, Continuation continuation) {
        CoroutineContext context = continuation.getContext();
        CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(context, element);
        JobKt.ensureActive(newCoroutineContext);
        if (newCoroutineContext == context) {
            ScopeCoroutine scopeCoroutine = new ScopeCoroutine(continuation, newCoroutineContext);
            return UndispatchedKt.startUndispatchedOrReturn(scopeCoroutine, scopeCoroutine, function2);
        }
        ContinuationInterceptor.Key key = ContinuationInterceptor.Key;
        if (Intrinsics.areEqual(newCoroutineContext.get(key), context.get(key))) {
            UndispatchedCoroutine undispatchedCoroutine = new UndispatchedCoroutine(continuation, newCoroutineContext);
            Object updateThreadContext = ThreadContextKt.updateThreadContext(newCoroutineContext, null);
            try {
                return UndispatchedKt.startUndispatchedOrReturn(undispatchedCoroutine, undispatchedCoroutine, function2);
            } finally {
                ThreadContextKt.restoreThreadContext(newCoroutineContext, updateThreadContext);
            }
        }
        DispatchedCoroutine dispatchedCoroutine = new DispatchedCoroutine(continuation, newCoroutineContext);
        CancellableKt.startCoroutineCancellable$default(function2, dispatchedCoroutine, dispatchedCoroutine);
        return dispatchedCoroutine.getResult();
    }
}
