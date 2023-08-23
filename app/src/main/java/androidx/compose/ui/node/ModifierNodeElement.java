package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectorValueInfo;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ModifierNodeElement extends InspectorValueInfo implements Modifier.Element {
    private final boolean autoInvalidate;
    private final Object params;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ModifierNodeElement(Object obj, boolean z, Function1 inspectorInfo) {
        super(inspectorInfo);
        Intrinsics.checkNotNullParameter(inspectorInfo, "inspectorInfo");
        this.params = obj;
        this.autoInvalidate = z;
    }

    public abstract Modifier.Node create();

    public boolean equals(Object b) {
        boolean z = true;
        if (this == b) {
            return true;
        }
        if (!(b instanceof ModifierNodeElement)) {
            return false;
        }
        Intrinsics.checkNotNullParameter(b, "b");
        if (getClass() != b.getClass()) {
            z = false;
        }
        if (!z) {
            return false;
        }
        return Intrinsics.areEqual(this.params, ((ModifierNodeElement) b).params);
    }

    public final boolean getAutoInvalidate$ui_release() {
        return this.autoInvalidate;
    }

    public int hashCode() {
        Object obj = this.params;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public abstract Modifier.Node update(Modifier.Node node);
}
