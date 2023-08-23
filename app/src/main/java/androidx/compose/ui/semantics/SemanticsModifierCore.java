package androidx.compose.ui.semantics;

import androidx.compose.ui.platform.InspectorValueInfo;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SemanticsModifierCore extends InspectorValueInfo implements SemanticsModifier {
    private static AtomicInteger lastIdentifier = new AtomicInteger(0);
    private final SemanticsConfiguration semanticsConfiguration;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SemanticsModifierCore(boolean z, Function1 properties, Function1 inspectorInfo) {
        super(inspectorInfo);
        Intrinsics.checkNotNullParameter(properties, "properties");
        Intrinsics.checkNotNullParameter(inspectorInfo, "inspectorInfo");
        SemanticsConfiguration semanticsConfiguration = new SemanticsConfiguration();
        semanticsConfiguration.setMergingSemanticsOfDescendants(z);
        semanticsConfiguration.setClearingSemantics();
        properties.invoke(semanticsConfiguration);
        this.semanticsConfiguration = semanticsConfiguration;
    }

    public static final /* synthetic */ AtomicInteger access$getLastIdentifier$cp() {
        return lastIdentifier;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SemanticsModifierCore)) {
            return false;
        }
        if (Intrinsics.areEqual(this.semanticsConfiguration, ((SemanticsModifierCore) obj).semanticsConfiguration)) {
            return true;
        }
        return false;
    }

    public final SemanticsConfiguration getSemanticsConfiguration() {
        return this.semanticsConfiguration;
    }

    public final int hashCode() {
        return this.semanticsConfiguration.hashCode();
    }
}
