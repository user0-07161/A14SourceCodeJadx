package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class ActivityResultRegistry {
    private Random mRandom = new Random();
    private final Map mRcToKey = new HashMap();
    final Map mKeyToRc = new HashMap();
    private final Map mKeyToLifecycleContainers = new HashMap();
    ArrayList mLaunchedKeys = new ArrayList();
    final transient Map mKeyToCallback = new HashMap();
    final Map mParsedPendingResults = new HashMap();
    final Bundle mPendingResults = new Bundle();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* renamed from: androidx.activity.result.ActivityResultRegistry$2  reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass2 extends ActivityResultLauncher {
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class CallbackAndContract {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class LifecycleContainer {
        final Lifecycle mLifecycle;
        private final ArrayList mObservers = new ArrayList();

        LifecycleContainer(Lifecycle lifecycle) {
            this.mLifecycle = lifecycle;
        }

        final void addObserver(LifecycleEventObserver lifecycleEventObserver) {
            this.mLifecycle.addObserver(lifecycleEventObserver);
            this.mObservers.add(lifecycleEventObserver);
        }

        final void clearObservers() {
            ArrayList arrayList = this.mObservers;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.mLifecycle.removeObserver((LifecycleEventObserver) it.next());
            }
            arrayList.clear();
        }
    }

    public final boolean dispatchResult(int i, int i2, Intent intent) {
        String str = (String) ((HashMap) this.mRcToKey).get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        CallbackAndContract callbackAndContract = (CallbackAndContract) ((HashMap) this.mKeyToCallback).get(str);
        ((HashMap) this.mParsedPendingResults).remove(str);
        this.mPendingResults.putParcelable(str, new ActivityResult(intent, i2));
        return true;
    }

    public final void onRestoreInstanceState(Bundle bundle) {
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS");
        ArrayList<String> stringArrayList = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS");
        if (stringArrayList != null && integerArrayList != null) {
            this.mLaunchedKeys = bundle.getStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS");
            this.mRandom = (Random) bundle.getSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT");
            Bundle bundle2 = bundle.getBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT");
            Bundle bundle3 = this.mPendingResults;
            bundle3.putAll(bundle2);
            for (int i = 0; i < stringArrayList.size(); i++) {
                String str = stringArrayList.get(i);
                Map map = this.mKeyToRc;
                HashMap hashMap = (HashMap) map;
                boolean containsKey = hashMap.containsKey(str);
                Map map2 = this.mRcToKey;
                if (containsKey) {
                    Integer num = (Integer) hashMap.remove(str);
                    if (!bundle3.containsKey(str)) {
                        ((HashMap) map2).remove(num);
                    }
                }
                int intValue = integerArrayList.get(i).intValue();
                String str2 = stringArrayList.get(i);
                ((HashMap) map2).put(Integer.valueOf(intValue), str2);
                ((HashMap) map).put(str2, Integer.valueOf(intValue));
            }
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        HashMap hashMap = (HashMap) this.mKeyToRc;
        bundle.putIntegerArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", new ArrayList<>(hashMap.values()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", new ArrayList<>(hashMap.keySet()));
        bundle.putStringArrayList("KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", new ArrayList<>(this.mLaunchedKeys));
        bundle.putBundle("KEY_COMPONENT_ACTIVITY_PENDING_RESULT", (Bundle) this.mPendingResults.clone());
        bundle.putSerializable("KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT", this.mRandom);
    }

    public final ActivityResultLauncher register(final String str, LifecycleOwner lifecycleOwner) {
        boolean z;
        int i;
        Map map;
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState().compareTo(Lifecycle.State.STARTED) >= 0) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            Map map2 = this.mKeyToRc;
            if (((Integer) ((HashMap) map2).get(str)) == null) {
                int nextInt = this.mRandom.nextInt(2147418112);
                while (true) {
                    i = nextInt + 65536;
                    map = this.mRcToKey;
                    if (!((HashMap) map).containsKey(Integer.valueOf(i))) {
                        break;
                    }
                    nextInt = this.mRandom.nextInt(2147418112);
                }
                ((HashMap) map).put(Integer.valueOf(i), str);
                ((HashMap) map2).put(str, Integer.valueOf(i));
            }
            HashMap hashMap = (HashMap) this.mKeyToLifecycleContainers;
            LifecycleContainer lifecycleContainer = (LifecycleContainer) hashMap.get(str);
            if (lifecycleContainer == null) {
                lifecycleContainer = new LifecycleContainer(lifecycle);
            }
            lifecycleContainer.addObserver(new LifecycleEventObserver() { // from class: androidx.activity.result.ActivityResultRegistry.1
                @Override // androidx.lifecycle.LifecycleEventObserver
                public final void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                    boolean equals = Lifecycle.Event.ON_START.equals(event);
                    String str2 = str;
                    ActivityResultRegistry activityResultRegistry = ActivityResultRegistry.this;
                    if (equals) {
                        ((HashMap) activityResultRegistry.mKeyToCallback).put(str2, new CallbackAndContract());
                        Map map3 = activityResultRegistry.mParsedPendingResults;
                        if (!((HashMap) map3).containsKey(str2)) {
                            Bundle bundle = activityResultRegistry.mPendingResults;
                            if (((ActivityResult) bundle.getParcelable(str2)) != null) {
                                bundle.remove(str2);
                                throw null;
                            }
                            return;
                        }
                        ((HashMap) map3).get(str2);
                        ((HashMap) map3).remove(str2);
                        throw null;
                    } else if (Lifecycle.Event.ON_STOP.equals(event)) {
                        ((HashMap) activityResultRegistry.mKeyToCallback).remove(str2);
                    } else if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                        activityResultRegistry.unregister(str2);
                    }
                }
            });
            hashMap.put(str, lifecycleContainer);
            return new AnonymousClass2();
        }
        throw new IllegalStateException("LifecycleOwner " + lifecycleOwner + " is attempting to register while current state is " + lifecycle.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.");
    }

    final void unregister(String str) {
        Integer num;
        if (!this.mLaunchedKeys.contains(str) && (num = (Integer) ((HashMap) this.mKeyToRc).remove(str)) != null) {
            ((HashMap) this.mRcToKey).remove(num);
        }
        ((HashMap) this.mKeyToCallback).remove(str);
        Map map = this.mParsedPendingResults;
        if (((HashMap) map).containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + ((HashMap) map).get(str));
            ((HashMap) map).remove(str);
        }
        Bundle bundle = this.mPendingResults;
        if (bundle.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + bundle.getParcelable(str));
            bundle.remove(str);
        }
        Map map2 = this.mKeyToLifecycleContainers;
        LifecycleContainer lifecycleContainer = (LifecycleContainer) ((HashMap) map2).get(str);
        if (lifecycleContainer != null) {
            lifecycleContainer.clearObservers();
            ((HashMap) map2).remove(str);
        }
    }
}
