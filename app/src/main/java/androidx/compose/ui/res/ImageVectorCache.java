package androidx.compose.ui.res;

import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ImageVectorCache {
    private final HashMap map = new HashMap();

    public final void clear() {
        this.map.clear();
    }

    public final void prune() {
        Iterator it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "it.next()");
            OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(((WeakReference) ((Map.Entry) next).getValue()).get());
            it.remove();
        }
    }
}
