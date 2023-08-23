package androidx.compose.runtime;

import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.UiApplier;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AbstractApplier implements Applier {
    private Object current;
    private final Object root;
    private final List stack = new ArrayList();

    public AbstractApplier(Object obj) {
        this.root = obj;
        this.current = obj;
    }

    @Override // androidx.compose.runtime.Applier
    public final void clear() {
        ((ArrayList) this.stack).clear();
        this.current = this.root;
        ((LayoutNode) ((UiApplier) this).root).removeAll$ui_release();
    }

    @Override // androidx.compose.runtime.Applier
    public final void down(Object obj) {
        ((ArrayList) this.stack).add(this.current);
        this.current = obj;
    }

    @Override // androidx.compose.runtime.Applier
    public final Object getCurrent() {
        return this.current;
    }

    public final Object getRoot() {
        return this.root;
    }

    @Override // androidx.compose.runtime.Applier
    public final void up() {
        List list = this.stack;
        if (!list.isEmpty()) {
            this.current = ((ArrayList) list).remove(((ArrayList) list).size() - 1);
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }
}
