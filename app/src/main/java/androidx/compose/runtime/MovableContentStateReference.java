package androidx.compose.runtime;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import java.util.List;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class MovableContentStateReference {
    public abstract Anchor getAnchor$runtime_release();

    public abstract ControlledComposition getComposition$runtime_release();

    public abstract List getInvalidations$runtime_release();

    public abstract PersistentMap getLocals$runtime_release();

    public abstract Object getParameter$runtime_release();

    public abstract SlotTable getSlotTable$runtime_release();
}
