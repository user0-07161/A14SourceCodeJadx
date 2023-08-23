package androidx.window.core;

import java.lang.reflect.Method;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ConsumerAdapter$createSubscription$1 {
    final /* synthetic */ Object $javaConsumer;
    final /* synthetic */ Object $obj;
    final /* synthetic */ Method $removeMethod;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConsumerAdapter$createSubscription$1(Method method, Object obj, Object obj2) {
        this.$removeMethod = method;
        this.$obj = obj;
        this.$javaConsumer = obj2;
    }

    public final void dispose() {
        this.$removeMethod.invoke(this.$obj, this.$javaConsumer);
    }
}
