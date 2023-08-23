package androidx.compose.ui.text;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class UrlAnnotation {
    private final String url;

    public UrlAnnotation(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.url = url;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UrlAnnotation)) {
            return false;
        }
        if (Intrinsics.areEqual(this.url, ((UrlAnnotation) obj).url)) {
            return true;
        }
        return false;
    }

    public final String getUrl() {
        return this.url;
    }

    public final int hashCode() {
        return this.url.hashCode();
    }

    public final String toString() {
        return "UrlAnnotation(url=" + this.url + ')';
    }
}
