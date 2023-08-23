package androidx.window.layout;

import androidx.window.layout.adapter.WindowBackend;
import com.android.egg.landroid.MainActivity;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class WindowInfoTrackerImpl implements WindowInfoTracker {
    private final WindowBackend windowBackend;

    public WindowInfoTrackerImpl(WindowBackend windowBackend) {
        WindowMetricsCalculatorCompat windowMetricsCalculatorCompat = WindowMetricsCalculatorCompat.INSTANCE;
        this.windowBackend = windowBackend;
    }

    public final Flow windowLayoutInfo(MainActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return FlowKt.callbackFlow(new WindowInfoTrackerImpl$windowLayoutInfo$2(this, activity, null));
    }
}
