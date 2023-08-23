package androidx.startup;

import android.content.Context;
import java.util.List;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface Initializer {
    Object create(Context context);

    List dependencies();
}
