package androidx.compose.ui.autofill;

import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AutofillTree {
    private final Map children = new LinkedHashMap();

    public final Map getChildren() {
        return this.children;
    }

    public final void performAutofill(String value, int i) {
        Intrinsics.checkNotNullParameter(value, "value");
        OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(((LinkedHashMap) this.children).get(Integer.valueOf(i)));
    }
}
