package androidx.compose.ui.graphics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidPathEffect implements PathEffect {
    private final android.graphics.PathEffect nativePathEffect;

    public AndroidPathEffect(android.graphics.PathEffect pathEffect) {
        this.nativePathEffect = pathEffect;
    }

    public final android.graphics.PathEffect getNativePathEffect() {
        return this.nativePathEffect;
    }
}
