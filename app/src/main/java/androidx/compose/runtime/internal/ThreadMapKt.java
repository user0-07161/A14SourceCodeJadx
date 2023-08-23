package androidx.compose.runtime.internal;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ThreadMapKt {
    private static final ThreadMap emptyThreadMap = new ThreadMap(0, new long[0], new Object[0]);

    public static final ThreadMap getEmptyThreadMap() {
        return emptyThreadMap;
    }
}
