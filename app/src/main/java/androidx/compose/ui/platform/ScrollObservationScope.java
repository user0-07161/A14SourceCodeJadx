package androidx.compose.ui.platform;

import androidx.compose.ui.node.OwnerScope;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ScrollObservationScope implements OwnerScope {
    private final List allScopes;
    private Float oldXValue;
    private Float oldYValue;
    private final int semanticsNodeId;

    public ScrollObservationScope(int i, List allScopes) {
        Intrinsics.checkNotNullParameter(allScopes, "allScopes");
        this.semanticsNodeId = i;
        this.allScopes = allScopes;
        this.oldXValue = null;
        this.oldYValue = null;
    }

    public final int getSemanticsNodeId() {
        return this.semanticsNodeId;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        return this.allScopes.contains(this);
    }
}
