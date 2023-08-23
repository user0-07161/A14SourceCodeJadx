package androidx.compose.runtime;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Updater {
    /* renamed from: set-impl  reason: not valid java name */
    public static final void m23setimpl(Composer composer, Object obj, Function2 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        if (composerImpl.getInserting() || !Intrinsics.areEqual(composerImpl.nextSlot(), obj)) {
            composerImpl.updateValue(obj);
            composerImpl.apply(obj, block);
        }
    }
}
