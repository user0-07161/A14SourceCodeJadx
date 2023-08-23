package kotlinx.coroutines.sync;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Symbol;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Empty {
    public final Object locked;

    public Empty(Symbol locked) {
        Intrinsics.checkNotNullParameter(locked, "locked");
        this.locked = locked;
    }

    public final String toString() {
        return "Empty[" + this.locked + "]";
    }
}
