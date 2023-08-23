package androidx.compose.ui.modifier;

import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ModifierLocal {
    private final Function0 defaultFactory;

    public ModifierLocal(Function0 function0) {
        this.defaultFactory = function0;
    }

    public final Function0 getDefaultFactory$ui_release() {
        return this.defaultFactory;
    }
}
