package androidx.compose.ui.modifier;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.node.BackwardsCompatNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ModifierLocalManager {
    private final MutableVector inserted;
    private final MutableVector insertedLocal;
    private final Owner owner;
    private final MutableVector removed;
    private final MutableVector removedLocal;

    public ModifierLocalManager(Owner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.owner = owner;
        this.inserted = new MutableVector(new BackwardsCompatNode[16]);
        this.insertedLocal = new MutableVector(new ModifierLocal[16]);
        this.removed = new MutableVector(new LayoutNode[16]);
        this.removedLocal = new MutableVector(new ModifierLocal[16]);
    }
}
