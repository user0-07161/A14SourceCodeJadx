package androidx.compose.runtime;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SkippableUpdater {
    private final Composer composer;

    private /* synthetic */ SkippableUpdater(Composer composer) {
        this.composer = composer;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ SkippableUpdater m21boximpl(Composer composer) {
        return new SkippableUpdater(composer);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SkippableUpdater)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.composer, ((SkippableUpdater) obj).composer)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.composer.hashCode();
    }

    public final String toString() {
        return "SkippableUpdater(composer=" + this.composer + ')';
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Composer m22unboximpl() {
        return this.composer;
    }
}
