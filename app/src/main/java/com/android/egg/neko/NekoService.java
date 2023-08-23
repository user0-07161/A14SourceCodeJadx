package com.android.egg.neko;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.android.egg.R;
import java.util.List;
import java.util.Random;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class NekoService extends JobService {
    public static float CAT_CAPTURE_PROB = 1.0f;
    public static int CAT_NOTIFICATION = 1;
    public static int DEBUG_NOTIFICATION = 1234;
    public static long INTERVAL_FLEX = 0;
    public static float INTERVAL_JITTER_FRAC = 0.25f;
    public static int JOB_ID = 42;
    public static long MINUTES = 0;
    public static long SECONDS = 1000;
    private static final String TAG = "NekoService";

    static {
        long j = 1000 * 60;
        MINUTES = j;
        INTERVAL_FLEX = j * 5;
    }

    public static void cancelJob(Context context) {
        Log.v(TAG, "Canceling job");
        ((JobScheduler) context.getSystemService(JobScheduler.class)).cancel(JOB_ID);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Cat getExistingCat(PrefState prefState) {
        List cats = prefState.getCats();
        if (cats.size() == 0) {
            return null;
        }
        return (Cat) cats.get(new Random().nextInt(cats.size()));
    }

    static Cat newRandomCat(Context context, PrefState prefState) {
        Cat create = Cat.create(context);
        prefState.addCat(create);
        create.logAdd(context);
        return create;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void notifyCat(Context context, Cat cat) {
        ((NotificationManager) context.getSystemService(NotificationManager.class)).notify(cat.getShortcutId(), CAT_NOTIFICATION, cat.buildNotification(context).build());
    }

    public static void registerJob(Context context, long j) {
        long j2;
        setupNotificationChannels(context);
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JobScheduler.class);
        jobScheduler.cancel(JOB_ID);
        long j3 = j * MINUTES;
        long random = (((long) (Math.random() * (2 * j2))) - (INTERVAL_JITTER_FRAC * ((float) j3))) + j3;
        JobInfo build = new JobInfo.Builder(JOB_ID, new ComponentName(context, NekoService.class)).setPeriodic(random, INTERVAL_FLEX).build();
        Log.v(TAG, "A cat will visit in " + random + "ms: " + String.valueOf(build));
        jobScheduler.schedule(build);
        if (NekoLand.DEBUG_NOTIFICATIONS) {
            ((NotificationManager) context.getSystemService(NotificationManager.class)).notify(DEBUG_NOTIFICATION, new Notification.Builder(context).setSmallIcon(R.drawable.stat_icon).setContentTitle(String.format("Job scheduled in %d min", Long.valueOf(random / MINUTES))).setContentText(String.valueOf(build)).setPriority(-2).setCategory("service").setChannelId(NekoLand.CHAN_ID).setShowWhen(true).build());
        }
    }

    public static void registerJobIfNeeded(Context context, long j) {
        if (((JobScheduler) context.getSystemService(JobScheduler.class)).getPendingJob(JOB_ID) == null) {
            registerJob(context, j);
        }
    }

    private static void setupNotificationChannels(Context context) {
        NotificationChannel notificationChannel = new NotificationChannel(NekoLand.CHAN_ID, context.getString(R.string.notification_channel_name), 3);
        notificationChannel.setSound(Uri.EMPTY, Notification.AUDIO_ATTRIBUTES_DEFAULT);
        notificationChannel.setVibrationPattern(Cat.PURR);
        notificationChannel.setLockscreenVisibility(1);
        ((NotificationManager) context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
    }

    private static void triggerFoodResponse(Context context) {
        Cat newRandomCat;
        PrefState prefState = new PrefState(context);
        int foodState = prefState.getFoodState();
        if (foodState != 0) {
            prefState.setFoodState(0);
            Random random = new Random();
            if (random.nextFloat() <= CAT_CAPTURE_PROB) {
                List cats = prefState.getCats();
                int[] intArray = context.getResources().getIntArray(R.array.food_new_cat_prob);
                float waterState = prefState.getWaterState() / 2.0f;
                if (foodState < intArray.length) {
                    waterState = intArray[foodState];
                }
                float f = waterState / 100.0f;
                Log.v(TAG, "Food type: " + foodState);
                Log.v(TAG, "New cat probability: " + f);
                if (cats.size() != 0 && random.nextFloat() > f) {
                    newRandomCat = getExistingCat(prefState);
                    Log.v(TAG, "A cat has returned: " + newRandomCat.getName());
                } else {
                    newRandomCat = newRandomCat(context, prefState);
                    Log.v(TAG, "A new cat is here: " + newRandomCat.getName());
                }
                notifyCat(context, newRandomCat);
            }
        }
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        Log.v(TAG, "Starting job: ".concat(String.valueOf(jobParameters)));
        if (NekoLand.DEBUG_NOTIFICATIONS) {
            new Bundle().putString("android.substName", getString(R.string.notification_name));
            getResources().getDimensionPixelSize(17104901);
            Notification.Builder channelId = Cat.create(this).buildNotification(this).setContentTitle("DEBUG").setChannelId(NekoLand.CHAN_ID);
            ((NotificationManager) getSystemService(NotificationManager.class)).notify(DEBUG_NOTIFICATION, channelId.setContentText("Ran job: " + jobParameters).build());
        }
        triggerFoodResponse(this);
        cancelJob(this);
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
