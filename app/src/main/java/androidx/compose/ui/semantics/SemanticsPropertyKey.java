package androidx.compose.ui.semantics;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SemanticsPropertyKey {
    private final Function2 mergePolicy;
    private final String name;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* renamed from: androidx.compose.ui.semantics.SemanticsPropertyKey$1  reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass1 extends Lambda implements Function2 {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            if (obj == null) {
                return obj2;
            }
            return obj;
        }
    }

    public SemanticsPropertyKey(String str, Function2 mergePolicy) {
        Intrinsics.checkNotNullParameter(mergePolicy, "mergePolicy");
        this.name = str;
        this.mergePolicy = mergePolicy;
    }

    public final String getName() {
        return this.name;
    }

    public final Object merge(Object obj, Object obj2) {
        return this.mergePolicy.invoke(obj, obj2);
    }

    public final String toString() {
        return "SemanticsPropertyKey: " + this.name;
    }
}
