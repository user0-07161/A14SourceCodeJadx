package androidx.compose.runtime;

import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface Composition {
    void dispose();

    void setContent(Function2 function2);
}
