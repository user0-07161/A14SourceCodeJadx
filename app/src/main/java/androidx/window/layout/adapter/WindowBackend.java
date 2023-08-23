package androidx.window.layout.adapter;

import android.content.Context;
import androidx.core.util.Consumer;
import androidx.profileinstaller.ProfileInstaller$$ExternalSyntheticLambda0;
import androidx.window.layout.WindowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public interface WindowBackend {
    void registerLayoutChangeCallback(Context context, ProfileInstaller$$ExternalSyntheticLambda0 profileInstaller$$ExternalSyntheticLambda0, WindowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0 windowInfoTrackerImpl$windowLayoutInfo$2$$ExternalSyntheticLambda0);

    void unregisterLayoutChangeCallback(Consumer consumer);
}
