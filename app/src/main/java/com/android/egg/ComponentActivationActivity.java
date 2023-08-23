package com.android.egg;

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import com.android.egg.neko.NekoControlsService;
import com.android.egg.widget.PaintChipsActivity;
import com.android.egg.widget.PaintChipsWidget;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class ComponentActivationActivity extends Activity {
    private static final String S_EGG_UNLOCK_SETTING = "egg_mode_s";
    private static final String TAG = "EasterEgg";

    private void toastUp(String str) {
        Toast.makeText(this, str, 0).show();
    }

    @Override // android.app.Activity
    public void onStart() {
        boolean z;
        super.onStart();
        PackageManager packageManager = getPackageManager();
        ComponentName[] componentNameArr = {new ComponentName(this, NekoControlsService.class), new ComponentName(this, PaintChipsActivity.class), new ComponentName(this, PaintChipsWidget.class)};
        long j = Settings.System.getLong(getContentResolver(), S_EGG_UNLOCK_SETTING, 0L);
        for (int i = 0; i < 3; i++) {
            ComponentName componentName = componentNameArr[i];
            if (packageManager.getComponentEnabledSetting(componentName) == 1) {
                z = true;
            } else {
                z = false;
            }
            if (j == 0) {
                if (z) {
                    Log.v(TAG, "Disabling component: " + componentName);
                    packageManager.setComponentEnabledSetting(componentName, 2, 1);
                } else {
                    Log.v(TAG, "Already disabled: " + componentName);
                }
            } else if (!z) {
                Log.v(TAG, "Enabling component: " + componentName);
                packageManager.setComponentEnabledSetting(componentName, 1, 1);
            } else {
                Log.v(TAG, "Already enabled: " + componentName);
            }
        }
        finish();
    }
}
