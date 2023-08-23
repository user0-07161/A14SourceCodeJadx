package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineId;
import kotlinx.coroutines.ThreadContextElement;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ThreadContextKt {
    public static final Symbol NO_THREAD_ELEMENTS = new Symbol("NO_THREAD_ELEMENTS");
    private static final Function2 countAll = new Function2() { // from class: kotlinx.coroutines.internal.ThreadContextKt$countAll$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Integer num;
            int i;
            CoroutineContext.Element element = (CoroutineContext.Element) obj2;
            Intrinsics.checkNotNullParameter(element, "element");
            if (element instanceof ThreadContextElement) {
                if (obj instanceof Integer) {
                    num = (Integer) obj;
                } else {
                    num = null;
                }
                if (num != null) {
                    i = num.intValue();
                } else {
                    i = 1;
                }
                if (i == 0) {
                    return element;
                }
                return Integer.valueOf(i + 1);
            }
            return obj;
        }
    };
    private static final Function2 findOne = new Function2() { // from class: kotlinx.coroutines.internal.ThreadContextKt$findOne$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ThreadContextElement threadContextElement = (ThreadContextElement) obj;
            CoroutineContext.Element element = (CoroutineContext.Element) obj2;
            Intrinsics.checkNotNullParameter(element, "element");
            if (threadContextElement == null) {
                if (element instanceof ThreadContextElement) {
                    return (ThreadContextElement) element;
                }
                return null;
            }
            return threadContextElement;
        }
    };
    private static final Function2 updateState = new Function2() { // from class: kotlinx.coroutines.internal.ThreadContextKt$updateState$1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ThreadState state = (ThreadState) obj;
            CoroutineContext.Element element = (CoroutineContext.Element) obj2;
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(element, "element");
            return state;
        }
    };

    public static final void restoreThreadContext(CoroutineContext context, Object obj) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (obj == NO_THREAD_ELEMENTS) {
            return;
        }
        if (obj instanceof ThreadState) {
            ((ThreadState) obj).restore(context);
            return;
        }
        Object fold = context.fold(null, findOne);
        Intrinsics.checkNotNull(fold, "null cannot be cast to non-null type kotlinx.coroutines.ThreadContextElement<kotlin.Any?>");
        ((CoroutineId) ((ThreadContextElement) fold)).restoreThreadContext(context, obj);
    }

    public static final Object threadContextElements(CoroutineContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object fold = context.fold(0, countAll);
        Intrinsics.checkNotNull(fold);
        return fold;
    }

    public static final Object updateThreadContext(CoroutineContext context, Object obj) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (obj == null) {
            obj = threadContextElements(context);
        }
        if (obj == 0) {
            return NO_THREAD_ELEMENTS;
        }
        if (obj instanceof Integer) {
            return context.fold(new ThreadState(context, ((Number) obj).intValue()), updateState);
        }
        return ((CoroutineId) ((ThreadContextElement) obj)).updateThreadContext(context);
    }
}
