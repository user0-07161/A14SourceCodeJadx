package androidx.compose.ui.semantics;

import androidx.compose.ui.platform.JvmActuals_jvmKt;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SemanticsConfiguration implements SemanticsPropertyReceiver, Iterable, KMappedMarker {
    private boolean isClearingSemantics;
    private boolean isMergingSemanticsOfDescendants;
    private final Map props = new LinkedHashMap();

    public final void collapsePeer$ui_release(SemanticsConfiguration peer) {
        Intrinsics.checkNotNullParameter(peer, "peer");
        if (peer.isMergingSemanticsOfDescendants) {
            this.isMergingSemanticsOfDescendants = true;
        }
        if (peer.isClearingSemantics) {
            this.isClearingSemantics = true;
        }
        for (Map.Entry entry : ((LinkedHashMap) peer.props).entrySet()) {
            SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) entry.getKey();
            Object value = entry.getValue();
            if (!this.props.containsKey(semanticsPropertyKey)) {
                this.props.put(semanticsPropertyKey, value);
            } else if (value instanceof AccessibilityAction) {
                Object obj = ((LinkedHashMap) this.props).get(semanticsPropertyKey);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>");
                AccessibilityAction accessibilityAction = (AccessibilityAction) obj;
                Map map = this.props;
                String label = accessibilityAction.getLabel();
                if (label == null) {
                    label = ((AccessibilityAction) value).getLabel();
                }
                Function action = accessibilityAction.getAction();
                if (action == null) {
                    action = ((AccessibilityAction) value).getAction();
                }
                map.put(semanticsPropertyKey, new AccessibilityAction(label, action));
            }
        }
    }

    public final boolean contains(SemanticsPropertyKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.props.containsKey(key);
    }

    public final SemanticsConfiguration copy() {
        SemanticsConfiguration semanticsConfiguration = new SemanticsConfiguration();
        semanticsConfiguration.isMergingSemanticsOfDescendants = this.isMergingSemanticsOfDescendants;
        semanticsConfiguration.isClearingSemantics = this.isClearingSemantics;
        semanticsConfiguration.props.putAll(this.props);
        return semanticsConfiguration;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SemanticsConfiguration)) {
            return false;
        }
        SemanticsConfiguration semanticsConfiguration = (SemanticsConfiguration) obj;
        if (Intrinsics.areEqual(this.props, semanticsConfiguration.props) && this.isMergingSemanticsOfDescendants == semanticsConfiguration.isMergingSemanticsOfDescendants && this.isClearingSemantics == semanticsConfiguration.isClearingSemantics) {
            return true;
        }
        return false;
    }

    public final Object get(SemanticsPropertyKey key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Object obj = ((LinkedHashMap) this.props).get(key);
        if (obj != null) {
            return obj;
        }
        throw new IllegalStateException("Key not present: " + key + " - consider getOrElse or getOrNull");
    }

    public final Object getOrElse(SemanticsPropertyKey key, Function0 defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        Object obj = ((LinkedHashMap) this.props).get(key);
        if (obj == null) {
            return defaultValue.invoke();
        }
        return obj;
    }

    public final Object getOrElseNullable(SemanticsPropertyKey key, Function0 defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        Object obj = ((LinkedHashMap) this.props).get(key);
        if (obj == null) {
            return null;
        }
        return obj;
    }

    public final int hashCode() {
        int hashCode = Boolean.hashCode(this.isMergingSemanticsOfDescendants);
        return Boolean.hashCode(this.isClearingSemantics) + ((hashCode + (this.props.hashCode() * 31)) * 31);
    }

    public final boolean isClearingSemantics() {
        return this.isClearingSemantics;
    }

    public final boolean isMergingSemanticsOfDescendants() {
        return this.isMergingSemanticsOfDescendants;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return ((LinkedHashMap) this.props).entrySet().iterator();
    }

    public final void mergeChild$ui_release(SemanticsConfiguration child) {
        Intrinsics.checkNotNullParameter(child, "child");
        for (Map.Entry entry : ((LinkedHashMap) child.props).entrySet()) {
            SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) entry.getKey();
            Object value = entry.getValue();
            Object obj = ((LinkedHashMap) this.props).get(semanticsPropertyKey);
            Intrinsics.checkNotNull(semanticsPropertyKey, "null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsPropertyKey<kotlin.Any?>");
            Object merge = semanticsPropertyKey.merge(obj, value);
            if (merge != null) {
                this.props.put(semanticsPropertyKey, merge);
            }
        }
    }

    public final void set(SemanticsPropertyKey key, Object obj) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.props.put(key, obj);
    }

    public final void setClearingSemantics() {
        this.isClearingSemantics = false;
    }

    public final void setMergingSemanticsOfDescendants(boolean z) {
        this.isMergingSemanticsOfDescendants = z;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.isMergingSemanticsOfDescendants) {
            sb.append("mergeDescendants=true");
            str = ", ";
        } else {
            str = "";
        }
        if (this.isClearingSemantics) {
            sb.append(str);
            sb.append("isClearingSemantics=true");
            str = ", ";
        }
        for (Map.Entry entry : ((LinkedHashMap) this.props).entrySet()) {
            Object value = entry.getValue();
            sb.append(str);
            sb.append(((SemanticsPropertyKey) entry.getKey()).getName());
            sb.append(" : ");
            sb.append(value);
            str = ", ";
        }
        return JvmActuals_jvmKt.simpleIdentityToString(this) + "{ " + ((Object) sb) + " }";
    }
}
