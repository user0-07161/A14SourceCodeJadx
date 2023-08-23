package androidx.compose.runtime;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class StaticValueHolder implements State {
    private final Object value;

    public StaticValueHolder(Object obj) {
        this.value = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StaticValueHolder)) {
            return false;
        }
        if (Intrinsics.areEqual(this.value, ((StaticValueHolder) obj).value)) {
            return true;
        }
        return false;
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return this.value;
    }

    public final int hashCode() {
        Object obj = this.value;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        return "StaticValueHolder(value=" + this.value + ')';
    }
}
