package com.android.egg.neko;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import com.android.egg.R;
import com.android.internal.logging.MetricsLogger;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class Cat extends Drawable {
    public static final boolean ALL_CATS_IN_ONE_CONVERSATION = true;
    public static final String GLOBAL_SHORTCUT_ID = "com.android.egg.neko:allcats";
    public static final String SHORTCUT_ID_PREFIX = "com.android.egg.neko:cat:";
    private CatParts D;
    private Bitmap mBitmap;
    private int mBodyColor;
    private boolean mBowTie;
    private String mFirstMessage;
    private int mFootType;
    private String mName;
    private Random mNotSoRandom;
    private long mSeed;
    public static final long[] PURR = {0, 40, 20, 40, 20, 40, 20, 40, 20, 40, 20, 40};
    public static final int[] P_BODY_COLORS = {180, -14606047, 180, -1, 140, -10395295, 140, -8825528, 100, -7297874, 100, -1596, 100, -28928, 5, -14043402, 5, -12846, 5, -3238952, 4, -12345273, 1, 0};
    public static final int[] P_COLLAR_COLORS = {250, -1, 250, -16777216, 250, -769226, 50, -15108398, 50, -141259, 50, -291840, 50, -749647, 50, -11751600};
    public static final int[] P_BELLY_COLORS = {750, 0, 250, -1};
    public static final int[] P_DARK_SPOT_COLORS = {700, 0, 250, -14606047, 50, -9614271};
    public static final int[] P_LIGHT_SPOT_COLORS = {700, 0, 300, -1};

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public class CatParts {
        public Drawable back;
        public Drawable belly;
        public Drawable body;
        public Drawable bowtie;
        public Drawable cap;
        public Drawable collar;
        public Drawable[] drawingOrder = getDrawingOrder();
        public Drawable faceSpot;
        public Drawable foot1;
        public Drawable foot2;
        public Drawable foot3;
        public Drawable foot4;
        public Drawable head;
        public Drawable leftEar;
        public Drawable leftEarInside;
        public Drawable leftEye;
        public Drawable leg1;
        public Drawable leg2;
        public Drawable leg2Shadow;
        public Drawable leg3;
        public Drawable leg4;
        public Drawable mouth;
        public Drawable nose;
        public Drawable rightEar;
        public Drawable rightEarInside;
        public Drawable rightEye;
        public Drawable tail;
        public Drawable tailCap;
        public Drawable tailShadow;

        public CatParts(Context context) {
            this.body = context.getDrawable(R.drawable.body);
            this.head = context.getDrawable(R.drawable.head);
            this.leg1 = context.getDrawable(R.drawable.leg1);
            this.leg2 = context.getDrawable(R.drawable.leg2);
            this.leg3 = context.getDrawable(R.drawable.leg3);
            this.leg4 = context.getDrawable(R.drawable.leg4);
            this.tail = context.getDrawable(R.drawable.tail);
            this.leftEar = context.getDrawable(R.drawable.left_ear);
            this.rightEar = context.getDrawable(R.drawable.right_ear);
            this.rightEarInside = context.getDrawable(R.drawable.right_ear_inside);
            this.leftEarInside = context.getDrawable(R.drawable.left_ear_inside);
            this.faceSpot = context.getDrawable(R.drawable.face_spot);
            this.cap = context.getDrawable(R.drawable.cap);
            this.mouth = context.getDrawable(R.drawable.mouth);
            this.foot4 = context.getDrawable(R.drawable.foot4);
            this.foot3 = context.getDrawable(R.drawable.foot3);
            this.foot1 = context.getDrawable(R.drawable.foot1);
            this.foot2 = context.getDrawable(R.drawable.foot2);
            this.leg2Shadow = context.getDrawable(R.drawable.leg2_shadow);
            this.tailShadow = context.getDrawable(R.drawable.tail_shadow);
            this.tailCap = context.getDrawable(R.drawable.tail_cap);
            this.belly = context.getDrawable(R.drawable.belly);
            this.back = context.getDrawable(R.drawable.back);
            this.rightEye = context.getDrawable(R.drawable.right_eye);
            this.leftEye = context.getDrawable(R.drawable.left_eye);
            this.nose = context.getDrawable(R.drawable.nose);
            this.collar = context.getDrawable(R.drawable.collar);
            this.bowtie = context.getDrawable(R.drawable.bowtie);
        }

        private Drawable[] getDrawingOrder() {
            return new Drawable[]{this.collar, this.leftEar, this.leftEarInside, this.rightEar, this.rightEarInside, this.head, this.faceSpot, this.cap, this.leftEye, this.rightEye, this.nose, this.mouth, this.tail, this.tailCap, this.tailShadow, this.foot1, this.leg1, this.foot2, this.leg2, this.foot3, this.leg3, this.foot4, this.leg4, this.leg2Shadow, this.body, this.belly, this.bowtie};
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Cat(android.content.Context r26, long r27) {
        /*
            Method dump skipped, instructions count: 579
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.egg.neko.Cat.<init>(android.content.Context, long):void");
    }

    public static final Object choose(Random random, Object... objArr) {
        return objArr[random.nextInt(objArr.length)];
    }

    public static final int chooseP(Random random, int[] iArr) {
        return chooseP(random, iArr, 1000);
    }

    public static Cat create(Context context) {
        return new Cat(context, Math.abs(ThreadLocalRandom.current().nextInt()));
    }

    private Person createPerson() {
        return new Person.Builder().setName(getName()).setBot(true).setKey(getShortcutId()).build();
    }

    public static final float frandrange(Random random, float f, float f2) {
        return (random.nextFloat() * (f2 - f)) + f;
    }

    public static Cat fromShortcutId(Context context, String str) {
        if (str.startsWith(SHORTCUT_ID_PREFIX)) {
            return new Cat(context, Long.parseLong(str.replace(SHORTCUT_ID_PREFIX, "")));
        }
        return null;
    }

    public static final int getColorIndex(int i, int[] iArr) {
        for (int i2 = 1; i2 < iArr.length; i2 += 2) {
            if (iArr[i2] == i) {
                return i2 / 2;
            }
        }
        return -1;
    }

    public static boolean isDark(int i) {
        if (((16711680 & i) >> 16) + ((65280 & i) >> 8) + (i & 255) < 128) {
            return true;
        }
        return false;
    }

    private void logCatAction(Context context, String str) {
        MetricsLogger.count(context, str, 1);
        MetricsLogger.histogram(context, str + "_color", getColorIndex(this.mBodyColor, P_BODY_COLORS));
        MetricsLogger.histogram(context, str + "_bowtie", this.mBowTie ? 1 : 0);
        MetricsLogger.histogram(context, str + "_feet", this.mFootType);
    }

    private synchronized Random notSoRandom(long j) {
        if (this.mNotSoRandom == null) {
            Random random = new Random();
            this.mNotSoRandom = random;
            random.setSeed(j);
        }
        return this.mNotSoRandom;
    }

    public static Icon recompressIcon(Icon icon) {
        if (icon.getType() != 1) {
            return icon;
        }
        try {
            Bitmap bitmap = (Bitmap) Icon.class.getDeclaredMethod("getBitmap", new Class[0]).invoke(icon, new Object[0]);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight() * 2);
            if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)) {
                return null;
            }
            return Icon.createWithData(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return icon;
        }
    }

    private void slowDraw(Canvas canvas, int i, int i2, int i3, int i4) {
        int i5 = 0;
        while (true) {
            Drawable[] drawableArr = this.D.drawingOrder;
            if (i5 < drawableArr.length) {
                Drawable drawable = drawableArr[i5];
                if (drawable != null) {
                    drawable.setBounds(i, i2, i + i3, i2 + i4);
                    drawable.draw(canvas);
                }
                i5++;
            } else {
                return;
            }
        }
    }

    public static void tint(int i, Drawable... drawableArr) {
        for (Drawable drawable : drawableArr) {
            if (drawable != null) {
                drawable.mutate().setTint(i);
            }
        }
    }

    public Notification.Builder buildNotification(Context context) {
        Bundle bundle = new Bundle();
        bundle.putString("android.substName", context.getString(R.string.notification_name));
        Icon createNotificationLargeIcon = createNotificationLargeIcon(context);
        Intent addFlags = new Intent("android.intent.action.MAIN").setClass(context, NekoLand.class).addFlags(268435456);
        ((ShortcutManager) context.getSystemService(ShortcutManager.class)).addDynamicShortcuts(List.of(new ShortcutInfo.Builder(context, getShortcutId()).setActivity(addFlags.getComponent()).setIntent(addFlags).setShortLabel(getName()).setIcon(createShortcutIcon(context)).setLongLived(true).build()));
        return new Notification.Builder(context, NekoLand.CHAN_ID).setSmallIcon(Icon.createWithResource(context, (int) R.drawable.stat_icon)).setLargeIcon(createNotificationLargeIcon).setColor(getBodyColor()).setContentTitle(context.getString(R.string.notification_title)).setShowWhen(true).setCategory("status").setContentText(getName()).setContentIntent(PendingIntent.getActivity(context, 0, addFlags, 67108864)).setAutoCancel(true).setStyle(new Notification.MessagingStyle(createPerson()).addMessage(this.mFirstMessage, System.currentTimeMillis(), createPerson()).setConversationTitle(getName())).setBubbleMetadata(new Notification.BubbleMetadata.Builder().setIntent(PendingIntent.getActivity(context, 0, addFlags, 33554432)).setIcon(createNotificationLargeIcon).setSuppressNotification(false).setDesiredHeight(context.getResources().getDisplayMetrics().heightPixels).build()).setShortcutId(getShortcutId()).addExtras(bundle);
    }

    public Bitmap createBitmap(int i, int i2) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null && bitmap.getWidth() == i && this.mBitmap.getHeight() == i2) {
            Bitmap bitmap2 = this.mBitmap;
            return bitmap2.copy(bitmap2.getConfig(), true);
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        slowDraw(new Canvas(createBitmap), 0, 0, i, i2);
        return createBitmap;
    }

    public Icon createIcon(Context context, int i, int i2) {
        float f;
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float[] fArr = new float[3];
        Color.colorToHSV(this.mBodyColor, fArr);
        float f2 = fArr[2];
        if (f2 > 0.5f) {
            f = f2 - 0.25f;
        } else {
            f = f2 + 0.25f;
        }
        fArr[2] = f;
        canvas.drawColor(Color.HSVToColor(fArr));
        int i3 = i / 4;
        slowDraw(canvas, i3, i3, (i - i3) - i3, (i2 - i3) - i3);
        return Icon.createWithAdaptiveBitmap(createBitmap);
    }

    public Icon createNotificationLargeIcon(Context context) {
        Resources resources = context.getResources();
        return recompressIcon(createIcon(context, resources.getDimensionPixelSize(17104901), resources.getDimensionPixelSize(17104902)));
    }

    public Icon createShortcutIcon(Context context) {
        Resources resources = context.getResources();
        return createIcon(context, resources.getDimensionPixelSize(17104901), resources.getDimensionPixelSize(17104902));
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int min = Math.min(canvas.getWidth(), canvas.getHeight());
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmap.getWidth() != min || this.mBitmap.getHeight() != min) {
            this.mBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
            slowDraw(new Canvas(this.mBitmap), 0, 0, min, min);
        }
        canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, (Paint) null);
    }

    public int getBodyColor() {
        return this.mBodyColor;
    }

    public String getName() {
        return this.mName;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public long getSeed() {
        return this.mSeed;
    }

    public String getShortcutId() {
        return GLOBAL_SHORTCUT_ID;
    }

    public void logAdd(Context context) {
        logCatAction(context, "egg_neko_add");
    }

    public void logRemove(Context context) {
        logCatAction(context, "egg_neko_remove");
    }

    public void logRename(Context context) {
        logCatAction(context, "egg_neko_rename");
    }

    public void logShare(Context context) {
        logCatAction(context, "egg_neko_share");
    }

    public void setName(String str) {
        this.mName = str;
    }

    public static final int chooseP(Random random, int[] iArr, int i) {
        int nextInt = random.nextInt(i);
        int length = iArr.length - 2;
        int i2 = 0;
        while (i2 < length) {
            nextInt -= iArr[i2];
            if (nextInt < 0) {
                break;
            }
            i2 += 2;
        }
        return iArr[i2 + 1];
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }
}
