package androidx.emoji2.text.flatbuffer;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Utf8Safe {
    private static Utf8Safe DEFAULT;

    public static void getDefault() {
        if (DEFAULT == null) {
            DEFAULT = new Utf8Safe();
        }
    }
}
