package kotlinx.coroutines.internal;

import kotlin.jvm.internal.PropertyReference0Impl;
import kotlinx.coroutines.DebugStringsKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final /* synthetic */ class LockFreeLinkedListNode$toString$1 extends PropertyReference0Impl {
    @Override // kotlin.reflect.KProperty0
    public final Object get() {
        return DebugStringsKt.getClassSimpleName(this.receiver);
    }
}
