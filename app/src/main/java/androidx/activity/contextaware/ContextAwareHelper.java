package androidx.activity.contextaware;

import android.content.Context;
import androidx.activity.ComponentActivity;
import androidx.activity.ComponentActivity$$ExternalSyntheticLambda3;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ContextAwareHelper {
    private volatile Context context;
    private final Set listeners = new CopyOnWriteArraySet();

    public final void addOnContextAvailableListener(OnContextAvailableListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.context != null) {
            ComponentActivity.$r8$lambda$h2i_RK2mddCIbAsGubaI4eL8_cU(((ComponentActivity$$ExternalSyntheticLambda3) listener).f$0);
        }
        ((CopyOnWriteArraySet) this.listeners).add(listener);
    }

    public final void clearAvailableContext() {
        this.context = null;
    }

    public final void dispatchOnContextAvailable(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        Iterator it = ((CopyOnWriteArraySet) this.listeners).iterator();
        while (it.hasNext()) {
            ComponentActivity.$r8$lambda$h2i_RK2mddCIbAsGubaI4eL8_cU(((ComponentActivity$$ExternalSyntheticLambda3) ((OnContextAvailableListener) it.next())).f$0);
        }
    }

    public final Context peekAvailableContext() {
        return this.context;
    }

    public final void removeOnContextAvailableListener(OnContextAvailableListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        ((CopyOnWriteArraySet) this.listeners).remove(listener);
    }
}
