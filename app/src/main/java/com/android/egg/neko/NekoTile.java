package com.android.egg.neko;

import android.content.Intent;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.util.Log;
import com.android.egg.neko.PrefState;
import com.android.internal.logging.MetricsLogger;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class NekoTile extends TileService implements PrefState.PrefsListener {
    private static final String TAG = "NekoTile";
    private PrefState mPrefs;

    /* JADX INFO: Access modifiers changed from: private */
    public void showNekoDialog() {
        Log.d(TAG, "showNekoDialog");
        MetricsLogger.count(this, "egg_neko_select_food", 1);
        showDialog(new NekoDialog(this));
    }

    private void updateState() {
        int i;
        Tile qsTile = getQsTile();
        int foodState = this.mPrefs.getFoodState();
        Food food = new Food(foodState);
        if (foodState != 0) {
            NekoService.registerJobIfNeeded(this, food.getInterval(this));
        }
        qsTile.setIcon(food.getIcon(this));
        qsTile.setLabel(food.getName(this));
        if (foodState != 0) {
            i = 2;
        } else {
            i = 1;
        }
        qsTile.setState(i);
        qsTile.updateTile();
    }

    @Override // android.service.quicksettings.TileService
    public void onClick() {
        if (this.mPrefs.getFoodState() != 0) {
            MetricsLogger.count(this, "egg_neko_empty_food", 1);
            this.mPrefs.setFoodState(0);
            NekoService.cancelJob(this);
        } else if (isLocked()) {
            if (isSecure()) {
                Log.d(TAG, "startActivityAndCollapse");
                Intent intent = new Intent(this, NekoLockedActivity.class);
                intent.addFlags(268435456);
                startActivityAndCollapse(intent);
                return;
            }
            unlockAndRun(new Runnable() { // from class: com.android.egg.neko.NekoTile.1
                @Override // java.lang.Runnable
                public void run() {
                    NekoTile.this.showNekoDialog();
                }
            });
        } else {
            showNekoDialog();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mPrefs = new PrefState(this);
    }

    @Override // com.android.egg.neko.PrefState.PrefsListener
    public void onPrefsChanged() {
        updateState();
    }

    @Override // android.service.quicksettings.TileService
    public void onStartListening() {
        super.onStartListening();
        this.mPrefs.setListener(this);
        updateState();
    }

    @Override // android.service.quicksettings.TileService
    public void onStopListening() {
        super.onStopListening();
        this.mPrefs.setListener(null);
    }

    @Override // android.service.quicksettings.TileService
    public void onTileAdded() {
        super.onTileAdded();
        MetricsLogger.count(this, "egg_neko_tile_added", 1);
    }

    @Override // android.service.quicksettings.TileService
    public void onTileRemoved() {
        super.onTileRemoved();
        MetricsLogger.count(this, "egg_neko_tile_removed", 1);
    }
}
