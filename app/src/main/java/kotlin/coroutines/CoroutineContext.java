package kotlin.coroutines;

import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface CoroutineContext {

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class DefaultImpls {
        public static Element get(Element element, Key key) {
            Intrinsics.checkNotNullParameter(key, "key");
            if (!Intrinsics.areEqual(element.getKey(), key)) {
                return null;
            }
            return element;
        }

        public static CoroutineContext minusKey(Element element, Key key) {
            Intrinsics.checkNotNullParameter(key, "key");
            if (Intrinsics.areEqual(element.getKey(), key)) {
                return EmptyCoroutineContext.INSTANCE;
            }
            return element;
        }

        public static CoroutineContext plus(CoroutineContext coroutineContext, CoroutineContext context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (context != EmptyCoroutineContext.INSTANCE) {
                return (CoroutineContext) context.fold(coroutineContext, new Function2() { // from class: kotlin.coroutines.CoroutineContext$plus$1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        CombinedContext combinedContext;
                        CoroutineContext acc = (CoroutineContext) obj;
                        CoroutineContext.Element element = (CoroutineContext.Element) obj2;
                        Intrinsics.checkNotNullParameter(acc, "acc");
                        Intrinsics.checkNotNullParameter(element, "element");
                        CoroutineContext minusKey = acc.minusKey(element.getKey());
                        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
                        if (minusKey != emptyCoroutineContext) {
                            ContinuationInterceptor.Key key = ContinuationInterceptor.Key;
                            ContinuationInterceptor.Key key2 = ContinuationInterceptor.Key.$$INSTANCE;
                            ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) minusKey.get(key2);
                            if (continuationInterceptor == null) {
                                combinedContext = new CombinedContext(element, minusKey);
                            } else {
                                CoroutineContext minusKey2 = minusKey.minusKey(key2);
                                if (minusKey2 == emptyCoroutineContext) {
                                    return new CombinedContext(continuationInterceptor, element);
                                }
                                combinedContext = new CombinedContext(continuationInterceptor, new CombinedContext(element, minusKey2));
                            }
                            return combinedContext;
                        }
                        return element;
                    }
                });
            }
            return coroutineContext;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface Element extends CoroutineContext {
        Key getKey();
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface Key {
    }

    Object fold(Object obj, Function2 function2);

    Element get(Key key);

    CoroutineContext minusKey(Key key);

    CoroutineContext plus(CoroutineContext coroutineContext);
}
