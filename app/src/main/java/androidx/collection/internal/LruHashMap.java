package androidx.collection.internal;

import java.util.LinkedHashMap;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LruHashMap {
    private final LinkedHashMap map = new LinkedHashMap(0, 0.75f, true);
}
