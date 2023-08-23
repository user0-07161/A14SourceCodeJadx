package androidx.compose.ui.text.font;

import androidx.compose.ui.text.caches.LruCache;
import androidx.compose.ui.text.caches.SimpleArrayMap;
import androidx.compose.ui.text.platform.SynchronizedObject;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AsyncTypefaceCache {
    private final LruCache resultCache = new LruCache();
    private final SimpleArrayMap permanentCache = new SimpleArrayMap();
    private final SynchronizedObject cacheLock = new SynchronizedObject();
}
