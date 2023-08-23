package androidx.profileinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import androidx.profileinstaller.ProfileInstaller;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class ProfileInstallReceiver extends BroadcastReceiver {

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class ResultDiagnostics implements ProfileInstaller.DiagnosticsCallback {
        ResultDiagnostics() {
        }

        @Override // androidx.profileinstaller.ProfileInstaller.DiagnosticsCallback
        public final void onResultReceived(int i, Object obj) {
            ((ProfileInstaller.AnonymousClass1) ProfileInstaller.LOG_DIAGNOSTICS).onResultReceived(i, obj);
            ProfileInstallReceiver.this.setResultCode(i);
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Bundle extras;
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if ("androidx.profileinstaller.action.INSTALL_PROFILE".equals(action)) {
            ProfileInstaller.writeProfile(context, new Executor() { // from class: androidx.profileinstaller.ProfileInstallReceiver$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable) {
                    switch (r1) {
                        case 0:
                            runnable.run();
                            return;
                        case 1:
                            runnable.run();
                            return;
                        default:
                            runnable.run();
                            return;
                    }
                }
            }, new ResultDiagnostics(), true);
            return;
        }
        boolean equals = "androidx.profileinstaller.action.SKIP_FILE".equals(action);
        ProfileInstaller.DiagnosticsCallback diagnosticsCallback = ProfileInstaller.LOG_DIAGNOSTICS;
        if (equals) {
            Bundle extras2 = intent.getExtras();
            if (extras2 != null) {
                String string = extras2.getString("EXTRA_SKIP_FILE_OPERATION");
                if ("WRITE_SKIP_FILE".equals(string)) {
                    try {
                        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0);
                        try {
                            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(context.getFilesDir(), "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
                            dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                            dataOutputStream.close();
                        } catch (IOException unused) {
                        }
                        ((ProfileInstaller.AnonymousClass1) diagnosticsCallback).onResultReceived(10, null);
                        setResultCode(10);
                    } catch (PackageManager.NameNotFoundException e) {
                        ((ProfileInstaller.AnonymousClass1) diagnosticsCallback).onResultReceived(7, e);
                        setResultCode(7);
                    }
                } else if ("DELETE_SKIP_FILE".equals(string)) {
                    new File(context.getFilesDir(), "profileinstaller_profileWrittenFor_lastUpdateTime.dat").delete();
                    ((ProfileInstaller.AnonymousClass1) diagnosticsCallback).onResultReceived(11, null);
                    setResultCode(11);
                }
            }
        } else if ("androidx.profileinstaller.action.SAVE_PROFILE".equals(action)) {
            Process.sendSignal(Process.myPid(), 10);
            ((ProfileInstaller.AnonymousClass1) diagnosticsCallback).onResultReceived(12, null);
            setResultCode(12);
        } else if ("androidx.profileinstaller.action.BENCHMARK_OPERATION".equals(action) && (extras = intent.getExtras()) != null) {
            if ("DROP_SHADER_CACHE".equals(extras.getString("EXTRA_BENCHMARK_OPERATION"))) {
                if (BenchmarkOperation.deleteFilesRecursively(context.createDeviceProtectedStorageContext().getCodeCacheDir())) {
                    ((ProfileInstaller.AnonymousClass1) diagnosticsCallback).onResultReceived(14, null);
                    setResultCode(14);
                    return;
                }
                ((ProfileInstaller.AnonymousClass1) diagnosticsCallback).onResultReceived(15, null);
                setResultCode(15);
                return;
            }
            ((ProfileInstaller.AnonymousClass1) diagnosticsCallback).onResultReceived(16, null);
            setResultCode(16);
        }
    }
}
