package com.android.egg.neko;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class PrefState implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String CAT_KEY_PREFIX = "cat:";
    private static final String FILE_NAME = "mPrefs";
    private static final String FOOD_STATE = "food";
    private static final String WATER_STATE = "water";
    private final Context mContext;
    private PrefsListener mListener;
    private final SharedPreferences mPrefs;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface PrefsListener {
        void onPrefsChanged();
    }

    public PrefState(Context context) {
        this.mContext = context;
        this.mPrefs = context.getSharedPreferences(FILE_NAME, 0);
    }

    public void addCat(Cat cat) {
        SharedPreferences.Editor edit = this.mPrefs.edit();
        edit.putString(CAT_KEY_PREFIX + String.valueOf(cat.getSeed()), cat.getName()).apply();
    }

    public List getCats() {
        ArrayList arrayList = new ArrayList();
        Map<String, ?> all = this.mPrefs.getAll();
        for (String str : all.keySet()) {
            if (str.startsWith(CAT_KEY_PREFIX)) {
                Cat cat = new Cat(this.mContext, Long.parseLong(str.substring(4)));
                cat.setName(String.valueOf(all.get(str)));
                arrayList.add(cat);
            }
        }
        return arrayList;
    }

    public int getFoodState() {
        return this.mPrefs.getInt("food", 0);
    }

    public float getWaterState() {
        return this.mPrefs.getFloat("water", 0.0f);
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        this.mListener.onPrefsChanged();
    }

    public void removeCat(Cat cat) {
        SharedPreferences.Editor edit = this.mPrefs.edit();
        edit.remove(CAT_KEY_PREFIX + String.valueOf(cat.getSeed())).apply();
    }

    public void setFoodState(int i) {
        this.mPrefs.edit().putInt("food", i).apply();
    }

    public void setListener(PrefsListener prefsListener) {
        this.mListener = prefsListener;
        if (prefsListener != null) {
            this.mPrefs.registerOnSharedPreferenceChangeListener(this);
        } else {
            this.mPrefs.unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    public void setWaterState(float f) {
        this.mPrefs.edit().putFloat("water", f).apply();
    }
}
