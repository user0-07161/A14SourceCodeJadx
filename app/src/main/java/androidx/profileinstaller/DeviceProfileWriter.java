package androidx.profileinstaller;

import android.content.res.AssetManager;
import android.os.Build;
import androidx.profileinstaller.ProfileInstaller;
import java.io.File;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DeviceProfileWriter {
    private final ProfileInstaller.DiagnosticsCallback mDiagnostics;
    private final Executor mExecutor;

    public DeviceProfileWriter(AssetManager assetManager, Executor executor, ProfileInstaller.DiagnosticsCallback diagnosticsCallback, String str, File file) {
        this.mExecutor = executor;
        this.mDiagnostics = diagnosticsCallback;
    }

    public final void deviceAllowsProfileInstallerAotWrites() {
        final Integer valueOf = Integer.valueOf(Build.VERSION.SDK_INT);
        this.mExecutor.execute(new Runnable() { // from class: androidx.profileinstaller.DeviceProfileWriter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DeviceProfileWriter.this.mDiagnostics.onResultReceived(r2, valueOf);
            }
        });
    }
}
