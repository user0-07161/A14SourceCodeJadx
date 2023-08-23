package androidx.compose.runtime;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ComposerImpl$doCompose$lambda$37$$inlined$sortBy$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt.compareValues(Integer.valueOf(((Invalidation) obj).getLocation()), Integer.valueOf(((Invalidation) obj2).getLocation()));
    }
}
