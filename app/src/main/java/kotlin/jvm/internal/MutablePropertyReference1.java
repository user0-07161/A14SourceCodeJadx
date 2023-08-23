package kotlin.jvm.internal;

import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KCallable;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class MutablePropertyReference1 extends MutablePropertyReference implements KMutableProperty1 {
    @Override // kotlin.jvm.internal.CallableReference
    protected final KCallable computeReflected() {
        Reflection.mutableProperty1(this);
        return this;
    }

    public final void getGetter() {
        KCallable compute = compute();
        if (compute != this) {
            ((MutablePropertyReference1) ((KMutableProperty1) ((KProperty) compute))).getGetter();
            return;
        }
        throw new KotlinReflectionNotSupportedError();
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ((MutablePropertyReference1Impl) this).getGetter();
        throw null;
    }
}
