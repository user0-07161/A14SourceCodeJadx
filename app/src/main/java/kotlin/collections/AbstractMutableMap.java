package kotlin.collections;

import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilderEntries;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilderKeys;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilderValues;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.markers.KMutableMap;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AbstractMutableMap extends java.util.AbstractMap implements Map, KMutableMap {
    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return new PersistentHashMapBuilderEntries((PersistentHashMapBuilder) this);
    }

    public abstract /* bridge */ int getSize();

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return new PersistentHashMapBuilderKeys((PersistentHashMapBuilder) this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        return new PersistentHashMapBuilderValues((PersistentHashMapBuilder) this);
    }
}
