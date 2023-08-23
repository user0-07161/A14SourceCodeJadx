package com.android.egg.neko;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.service.controls.Control;
import android.service.controls.ControlsProviderService;
import android.service.controls.actions.ControlAction;
import android.service.controls.actions.FloatAction;
import android.service.controls.templates.ControlButton;
import android.service.controls.templates.RangeTemplate;
import android.service.controls.templates.StatelessTemplate;
import android.service.controls.templates.ToggleTemplate;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import com.android.egg.R;
import com.android.egg.neko.NekoControlsService;
import com.android.egg.neko.PrefState;
import com.android.internal.logging.MetricsLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Flow;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongProgressionIterator;
import kotlin.ranges.LongRange;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class NekoControlsService extends ControlsProviderService implements PrefState.PrefsListener {
    public static final int $stable = 8;
    private Icon lastToyIcon;
    private PrefState prefs;
    private final String TAG = "NekoControls";
    private final HashMap controls = new HashMap();
    private final ArrayList publishers = new ArrayList();
    private final Random rng = new Random();
    private final MetricsLogger metricsLogger = new MetricsLogger();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class UglyPublisher implements Flow.Publisher {
        private final Iterable controlKeys;
        private final boolean indefinite;
        private final ArrayList subscriptions;
        final /* synthetic */ NekoControlsService this$0;

        /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
        /* loaded from: classes.dex */
        final class UglySubscription implements Flow.Subscription {
            private final Iterator initialControls;
            private Flow.Subscriber subscriber;
            final /* synthetic */ UglyPublisher this$0;

            public UglySubscription(UglyPublisher uglyPublisher, Iterator initialControls, Flow.Subscriber subscriber) {
                Intrinsics.checkNotNullParameter(initialControls, "initialControls");
                this.this$0 = uglyPublisher;
                this.initialControls = initialControls;
                this.subscriber = subscriber;
            }

            @Override // java.util.concurrent.Flow.Subscription
            public void cancel() {
                String str = this.this$0.this$0.TAG;
                Flow.Subscriber subscriber = this.subscriber;
                Log.v(str, "cancel subscription: " + this + " for subscriber: " + subscriber + " to publisher: " + this + "@UglyPublisher");
                this.subscriber = null;
                this.this$0.unsubscribe(this);
            }

            public final Iterator getInitialControls() {
                return this.initialControls;
            }

            public final Flow.Subscriber getSubscriber() {
                return this.subscriber;
            }

            @Override // java.util.concurrent.Flow.Subscription
            public void request(long j) {
                LongRange longRange;
                Flow.Subscriber subscriber;
                if (j <= Long.MIN_VALUE) {
                    longRange = LongRange.EMPTY;
                } else {
                    longRange = new LongRange(0, j - 1);
                }
                UglyPublisher uglyPublisher = this.this$0;
                Iterator it = longRange.iterator();
                while (it.hasNext()) {
                    ((LongProgressionIterator) it).nextLong();
                    if (this.initialControls.hasNext()) {
                        send((Control) this.initialControls.next());
                    } else if (!uglyPublisher.getIndefinite() && (subscriber = this.subscriber) != null) {
                        subscriber.onComplete();
                    }
                }
            }

            public final void send(Control c) {
                Intrinsics.checkNotNullParameter(c, "c");
                String str = this.this$0.this$0.TAG;
                String Control_toString = NekoControlsServiceKt.Control_toString(c);
                Flow.Subscriber subscriber = this.subscriber;
                Log.v(str, "sending update: " + Control_toString + " => " + subscriber);
                Flow.Subscriber subscriber2 = this.subscriber;
                if (subscriber2 != null) {
                    subscriber2.onNext(c);
                }
            }

            public final void setSubscriber(Flow.Subscriber subscriber) {
                this.subscriber = subscriber;
            }
        }

        public UglyPublisher(NekoControlsService nekoControlsService, Iterable controlKeys, boolean z) {
            Intrinsics.checkNotNullParameter(controlKeys, "controlKeys");
            this.this$0 = nekoControlsService;
            this.controlKeys = controlKeys;
            this.indefinite = z;
            this.subscriptions = new ArrayList();
        }

        public final Iterable getControlKeys() {
            return this.controlKeys;
        }

        public final boolean getIndefinite() {
            return this.indefinite;
        }

        public final ArrayList getSubscriptions() {
            return this.subscriptions;
        }

        public final void refresh() {
            Iterable<String> iterable = this.controlKeys;
            NekoControlsService nekoControlsService = this.this$0;
            ArrayList<Control> arrayList = new ArrayList();
            for (String str : iterable) {
                Control control = (Control) nekoControlsService.controls.get(str);
                if (control != null) {
                    arrayList.add(control);
                }
            }
            for (Control control2 : arrayList) {
                for (UglySubscription uglySubscription : this.subscriptions) {
                    Intrinsics.checkNotNullExpressionValue(control2, "control");
                    uglySubscription.send(control2);
                }
            }
        }

        @Override // java.util.concurrent.Flow.Publisher
        public void subscribe(Flow.Subscriber subscriber) {
            Intrinsics.checkNotNullParameter(subscriber, "subscriber");
            String str = this.this$0.TAG;
            Log.v(str, "subscribe to publisher: " + this + " by subscriber: " + subscriber);
            Iterable<String> iterable = this.controlKeys;
            NekoControlsService nekoControlsService = this.this$0;
            ArrayList arrayList = new ArrayList();
            for (String str2 : iterable) {
                Control control = (Control) nekoControlsService.controls.get(str2);
                if (control != null) {
                    arrayList.add(control);
                }
            }
            UglySubscription uglySubscription = new UglySubscription(this, arrayList.iterator(), subscriber);
            this.subscriptions.add(uglySubscription);
            subscriber.onSubscribe(uglySubscription);
        }

        public final void unsubscribe(UglySubscription sub) {
            Intrinsics.checkNotNullParameter(sub, "sub");
            String str = this.this$0.TAG;
            Log.v(str, "no more subscriptions, removing subscriber: " + sub);
            this.subscriptions.remove(sub);
            if (this.subscriptions.size() == 0) {
                String str2 = this.this$0.TAG;
                Log.v(str2, "no more subscribers, removing publisher: " + this);
                this.this$0.publishers.remove(this);
            }
        }
    }

    private final CharSequence colorize(CharSequence charSequence, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(i), 0, charSequence.length(), 0);
        return spannableStringBuilder;
    }

    private final void createDefaultControls() {
        boolean z;
        PrefState prefState = this.prefs;
        if (prefState != null) {
            int foodState = prefState.getFoodState();
            if (foodState != 0) {
                NekoService.registerJobIfNeeded(this, 5L);
            }
            PrefState prefState2 = this.prefs;
            if (prefState2 != null) {
                this.controls.put(NekoControlsServiceKt.CONTROL_ID_WATER, makeWaterBowlControl(prefState2.getWaterState()));
                HashMap hashMap = this.controls;
                if (foodState != 0) {
                    z = true;
                } else {
                    z = false;
                }
                hashMap.put(NekoControlsServiceKt.CONTROL_ID_FOOD, makeFoodBowlControl(z));
                this.controls.put(NekoControlsServiceKt.CONTROL_ID_TOY, makeToyControl(currentToyIcon(), false));
                return;
            }
            Intrinsics.throwUninitializedPropertyAccessException("prefs");
            throw null;
        }
        Intrinsics.throwUninitializedPropertyAccessException("prefs");
        throw null;
    }

    private final Icon currentToyIcon() {
        Icon icon = this.lastToyIcon;
        if (icon == null) {
            icon = randomToyIcon();
        }
        this.lastToyIcon = icon;
        return icon;
    }

    private final PendingIntent getAppIntent() {
        return getPendingIntent();
    }

    private final PendingIntent getPendingIntent() {
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent("android.intent.action.MAIN").setClass(this, NekoLand.class).addFlags(268435456), 201326592);
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(this, 0, int…ingIntent.FLAG_IMMUTABLE)");
        return activity;
    }

    private final Control makeFoodBowlControl(boolean z) {
        int i;
        CharSequence colorize;
        String string;
        Control.StatefulBuilder customColor = new Control.StatefulBuilder(NekoControlsServiceKt.CONTROL_ID_FOOD, getPendingIntent()).setDeviceType(0).setCustomColor(ColorStateList.valueOf(NekoControlsServiceKt.COLOR_FOOD_BG));
        String string2 = getString(R.string.control_food_title);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.control_food_title)");
        Control.StatefulBuilder title = customColor.setTitle(colorize(string2, NekoControlsServiceKt.COLOR_FOOD_FG));
        Resources resources = getResources();
        if (z) {
            i = R.drawable.ic_foodbowl_filled;
        } else {
            i = R.drawable.ic_bowl;
        }
        Control.StatefulBuilder customIcon = title.setCustomIcon(Icon.createWithResource(resources, i));
        if (z) {
            String string3 = getString(R.string.control_food_status_full);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.control_food_status_full)");
            colorize = colorize(string3, -855638017);
        } else {
            String string4 = getString(R.string.control_food_status_empty);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.control_food_status_empty)");
            colorize = colorize(string4, -2130706433);
        }
        Control.StatefulBuilder status = customIcon.setStatusText(colorize).setControlTemplate(new ToggleTemplate("foodbowl", new ControlButton(z, "Refill"))).setStatus(1);
        if (z) {
            string = "";
        } else {
            string = getString(R.string.control_food_subtitle);
        }
        Control build = status.setSubtitle(string).build();
        Intrinsics.checkNotNullExpressionValue(build, "StatefulBuilder(CONTROL_…\n                .build()");
        return build;
    }

    private final Control makeStateless(Control control) {
        if (control == null) {
            return null;
        }
        return new Control.StatelessBuilder(control.getControlId(), control.getAppIntent()).setTitle(control.getTitle()).setSubtitle(control.getSubtitle()).setStructure(control.getStructure()).setDeviceType(control.getDeviceType()).setCustomIcon(control.getCustomIcon()).setCustomColor(control.getCustomColor()).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Control makeToyControl(Icon icon, boolean z) {
        String str;
        Control.StatefulBuilder customColor = new Control.StatefulBuilder(NekoControlsServiceKt.CONTROL_ID_TOY, getPendingIntent()).setDeviceType(0).setCustomIcon(icon).setCustomColor(ColorStateList.valueOf(NekoControlsServiceKt.COLOR_TOY_BG));
        String string = getString(R.string.control_toy_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.control_toy_title)");
        Control.StatefulBuilder title = customColor.setTitle(colorize(string, NekoControlsServiceKt.COLOR_TOY_FG));
        String str2 = "";
        if (!z) {
            str = "";
        } else {
            str = getString(R.string.control_toy_status);
        }
        Intrinsics.checkNotNullExpressionValue(str, "if (thrown) getString(R.…ntrol_toy_status) else \"\"");
        Control.StatefulBuilder status = title.setStatusText(colorize(str, NekoControlsServiceKt.COLOR_TOY_FG)).setControlTemplate(new StatelessTemplate(NekoControlsServiceKt.CONTROL_ID_TOY)).setStatus(1);
        if (!z) {
            str2 = getString(R.string.control_toy_subtitle);
        }
        Control build = status.setSubtitle(str2).setAppIntent(getAppIntent()).build();
        Intrinsics.checkNotNullExpressionValue(build, "StatefulBuilder(CONTROL_…\n                .build()");
        return build;
    }

    private final Control makeWaterBowlControl(float f) {
        int i;
        String str;
        Control.StatefulBuilder deviceType = new Control.StatefulBuilder(NekoControlsServiceKt.CONTROL_ID_WATER, getPendingIntent()).setDeviceType(12);
        String string = getString(R.string.control_water_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.control_water_title)");
        Control.StatefulBuilder customColor = deviceType.setTitle(colorize(string, NekoControlsServiceKt.COLOR_WATER_FG)).setCustomColor(ColorStateList.valueOf(NekoControlsServiceKt.COLOR_WATER_BG));
        Resources resources = getResources();
        if (f >= 100.0f) {
            i = R.drawable.ic_water_filled;
        } else {
            i = R.drawable.ic_water;
        }
        boolean z = true;
        Control.StatefulBuilder status = customColor.setCustomIcon(Icon.createWithResource(resources, i)).setControlTemplate(new RangeTemplate("waterlevel", 0.0f, 200.0f, f, 10.0f, "%.0f mL")).setStatus(1);
        if (f != 0.0f) {
            z = false;
        }
        if (z) {
            str = getString(R.string.control_water_subtitle);
        } else {
            str = "";
        }
        Control build = status.setSubtitle(str).build();
        Intrinsics.checkNotNullExpressionValue(build, "StatefulBuilder(CONTROL_…\n                .build()");
        return build;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void pushControlChanges() {
        new Thread(new Runnable() { // from class: com.android.egg.neko.NekoControlsService$pushControlChanges$1
            @Override // java.lang.Runnable
            public final void run() {
                for (NekoControlsService.UglyPublisher uglyPublisher : NekoControlsService.this.publishers) {
                    uglyPublisher.refresh();
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Icon randomToyIcon() {
        Icon createWithResource = Icon.createWithResource(getResources(), Cat.chooseP(this.rng, NekoControlsServiceKt.getP_TOY_ICONS(), 4));
        Intrinsics.checkNotNullExpressionValue(createWithResource, "createWithResource(resou…seP(rng, P_TOY_ICONS, 4))");
        return createWithResource;
    }

    @Override // android.service.controls.ControlsProviderService
    public Flow.Publisher createPublisherFor(List list) {
        Intrinsics.checkNotNullParameter(list, "list");
        createDefaultControls();
        UglyPublisher uglyPublisher = new UglyPublisher(this, list, true);
        this.publishers.add(uglyPublisher);
        return uglyPublisher;
    }

    @Override // android.service.controls.ControlsProviderService
    public Flow.Publisher createPublisherForAllAvailable() {
        createDefaultControls();
        Set keySet = this.controls.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "controls.keys");
        UglyPublisher uglyPublisher = new UglyPublisher(this, keySet, false);
        this.publishers.add(uglyPublisher);
        return uglyPublisher;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        PrefState prefState = new PrefState(this);
        this.prefs = prefState;
        prefState.setListener(this);
        createDefaultControls();
    }

    @Override // com.android.egg.neko.PrefState.PrefsListener
    public void onPrefsChanged() {
        createDefaultControls();
    }

    @Override // android.service.controls.ControlsProviderService
    public void performControlAction(String controlId, ControlAction action, Consumer consumer) {
        Intrinsics.checkNotNullParameter(controlId, "controlId");
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        int hashCode = controlId.hashCode();
        if (hashCode != 115038) {
            if (hashCode != 3148894) {
                if (hashCode == 112903447 && controlId.equals(NekoControlsServiceKt.CONTROL_ID_WATER)) {
                    if (action instanceof FloatAction) {
                        FloatAction floatAction = (FloatAction) action;
                        this.controls.put(NekoControlsServiceKt.CONTROL_ID_WATER, makeWaterBowlControl(floatAction.getNewValue()));
                        String str = this.TAG;
                        float newValue = floatAction.getNewValue();
                        Log.v(str, "Water level set to " + newValue);
                        PrefState prefState = this.prefs;
                        if (prefState != null) {
                            prefState.setWaterState(floatAction.getNewValue());
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("prefs");
                            throw null;
                        }
                    }
                } else {
                    return;
                }
            } else if (controlId.equals(NekoControlsServiceKt.CONTROL_ID_FOOD)) {
                this.controls.put(NekoControlsServiceKt.CONTROL_ID_FOOD, makeFoodBowlControl(true));
                Log.v(this.TAG, "Bowl refilled. (Registering job.)");
                NekoService.registerJob(this, 5L);
                this.metricsLogger.histogram("egg_neko_offered_food", 11);
                PrefState prefState2 = this.prefs;
                if (prefState2 != null) {
                    prefState2.setFoodState(11);
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("prefs");
                    throw null;
                }
            } else {
                return;
            }
        } else if (!controlId.equals(NekoControlsServiceKt.CONTROL_ID_TOY)) {
            return;
        } else {
            Log.v(this.TAG, "Toy tossed.");
            this.controls.put(NekoControlsServiceKt.CONTROL_ID_TOY, makeToyControl(currentToyIcon(), true));
            new Thread(new Runnable() { // from class: com.android.egg.neko.NekoControlsService$performControlAction$1
                @Override // java.lang.Runnable
                public final void run() {
                    PrefState prefState3;
                    Icon randomToyIcon;
                    Control makeToyControl;
                    Thread.sleep((new Random().nextInt(4) + 1) * 1000);
                    prefState3 = NekoControlsService.this.prefs;
                    if (prefState3 != null) {
                        Cat existingCat = NekoService.getExistingCat(prefState3);
                        if (existingCat != null) {
                            NekoService.notifyCat(NekoControlsService.this, existingCat);
                        }
                        HashMap hashMap = NekoControlsService.this.controls;
                        NekoControlsService nekoControlsService = NekoControlsService.this;
                        randomToyIcon = nekoControlsService.randomToyIcon();
                        makeToyControl = nekoControlsService.makeToyControl(randomToyIcon, false);
                        hashMap.put(NekoControlsServiceKt.CONTROL_ID_TOY, makeToyControl);
                        NekoControlsService.this.pushControlChanges();
                        return;
                    }
                    Intrinsics.throwUninitializedPropertyAccessException("prefs");
                    throw null;
                }
            }).start();
        }
        consumer.accept(1);
        pushControlChanges();
    }
}
