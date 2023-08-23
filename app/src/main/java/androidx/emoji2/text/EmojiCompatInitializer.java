package androidx.emoji2.text;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.EmojiCompatInitializer;
import androidx.emoji2.text.FontRequestEmojiCompatConfig;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleInitializer;
import androidx.startup.AppInitializer;
import androidx.startup.Initializer;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class EmojiCompatInitializer implements Initializer {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class BackgroundDefaultConfig extends EmojiCompat.Config {
        protected BackgroundDefaultConfig(Context context) {
            super(new BackgroundDefaultLoader(context));
            this.mMetadataLoadStrategy = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class BackgroundDefaultLoader implements EmojiCompat.MetadataRepoLoader {
        private final Context mContext;

        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.emoji2.text.DefaultEmojiCompatConfig$DefaultEmojiCompatConfigFactory] */
        public static void $r8$lambda$2V1iWTiAwNxOBlVvz73bbuEdzIw(BackgroundDefaultLoader backgroundDefaultLoader, final EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback, final ThreadPoolExecutor threadPoolExecutor) {
            backgroundDefaultLoader.getClass();
            try {
                FontRequestEmojiCompatConfig create = new Object() { // from class: androidx.emoji2.text.DefaultEmojiCompatConfig$DefaultEmojiCompatConfigFactory
                    private final DefaultEmojiCompatConfig$DefaultEmojiCompatConfigHelper mHelper = new DefaultEmojiCompatConfig$DefaultEmojiCompatConfigHelper_API28();

                    /* JADX WARN: Removed duplicated region for block: B:28:0x0073  */
                    /* JADX WARN: Removed duplicated region for block: B:33:0x003e A[EDGE_INSN: B:33:0x003e->B:16:0x003e ?: BREAK  , SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final androidx.emoji2.text.FontRequestEmojiCompatConfig create(android.content.Context r8) {
                        /*
                            r7 = this;
                            android.content.pm.PackageManager r0 = r8.getPackageManager()
                            java.lang.String r1 = "Package manager required to locate emoji font provider"
                            androidx.core.util.Preconditions.checkNotNull(r0, r1)
                            android.content.Intent r1 = new android.content.Intent
                            java.lang.String r2 = "androidx.content.action.LOAD_EMOJI_FONT"
                            r1.<init>(r2)
                            androidx.emoji2.text.DefaultEmojiCompatConfig$DefaultEmojiCompatConfigHelper r7 = r7.mHelper
                            java.util.List r1 = r7.queryIntentContentProviders(r0, r1)
                            java.util.Iterator r1 = r1.iterator()
                        L1a:
                            boolean r2 = r1.hasNext()
                            r3 = 0
                            r4 = 0
                            if (r2 == 0) goto L3d
                            java.lang.Object r2 = r1.next()
                            android.content.pm.ResolveInfo r2 = (android.content.pm.ResolveInfo) r2
                            android.content.pm.ProviderInfo r2 = r7.getProviderInfo(r2)
                            if (r2 == 0) goto L39
                            android.content.pm.ApplicationInfo r5 = r2.applicationInfo
                            if (r5 == 0) goto L39
                            int r5 = r5.flags
                            r6 = 1
                            r5 = r5 & r6
                            if (r5 != r6) goto L39
                            goto L3a
                        L39:
                            r6 = r3
                        L3a:
                            if (r6 == 0) goto L1a
                            goto L3e
                        L3d:
                            r2 = r4
                        L3e:
                            if (r2 != 0) goto L41
                            goto L6f
                        L41:
                            java.lang.String r1 = r2.authority     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                            java.lang.String r2 = r2.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                            android.content.pm.Signature[] r7 = r7.getSigningSignatures(r0, r2)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                            java.util.ArrayList r0 = new java.util.ArrayList     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                            r0.<init>()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                            int r5 = r7.length     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                        L4f:
                            if (r3 >= r5) goto L5d
                            r6 = r7[r3]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                            byte[] r6 = r6.toByteArray()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                            r0.add(r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                            int r3 = r3 + 1
                            goto L4f
                        L5d:
                            java.util.List r7 = java.util.Collections.singletonList(r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                            androidx.core.provider.FontRequest r0 = new androidx.core.provider.FontRequest     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                            java.lang.String r3 = "emojicompat-emoji-font"
                            r0.<init>(r1, r2, r3, r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L69
                            goto L70
                        L69:
                            r7 = move-exception
                            java.lang.String r0 = "emoji2.text.DefaultEmojiConfig"
                            android.util.Log.wtf(r0, r7)
                        L6f:
                            r0 = r4
                        L70:
                            if (r0 != 0) goto L73
                            goto L78
                        L73:
                            androidx.emoji2.text.FontRequestEmojiCompatConfig r4 = new androidx.emoji2.text.FontRequestEmojiCompatConfig
                            r4.<init>(r8, r0)
                        L78:
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.DefaultEmojiCompatConfig$DefaultEmojiCompatConfigFactory.create(android.content.Context):androidx.emoji2.text.FontRequestEmojiCompatConfig");
                    }
                }.create(backgroundDefaultLoader.mContext);
                if (create != null) {
                    EmojiCompat.MetadataRepoLoader metadataRepoLoader = create.mMetadataLoader;
                    ((FontRequestEmojiCompatConfig.FontRequestMetadataLoader) metadataRepoLoader).setExecutor(threadPoolExecutor);
                    metadataRepoLoader.load(new EmojiCompat.MetadataRepoLoaderCallback() { // from class: androidx.emoji2.text.EmojiCompatInitializer.BackgroundDefaultLoader.1
                        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
                        public final void onFailed(Throwable th) {
                            ThreadPoolExecutor threadPoolExecutor2 = threadPoolExecutor;
                            try {
                                EmojiCompat.MetadataRepoLoaderCallback.this.onFailed(th);
                            } finally {
                                threadPoolExecutor2.shutdown();
                            }
                        }

                        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
                        public final void onLoaded(MetadataRepo metadataRepo) {
                            ThreadPoolExecutor threadPoolExecutor2 = threadPoolExecutor;
                            try {
                                EmojiCompat.MetadataRepoLoaderCallback.this.onLoaded(metadataRepo);
                            } finally {
                                threadPoolExecutor2.shutdown();
                            }
                        }
                    });
                    return;
                }
                throw new RuntimeException("EmojiCompat font provider not available on this device.");
            } catch (Throwable th) {
                metadataRepoLoaderCallback.onFailed(th);
                threadPoolExecutor.shutdown();
            }
        }

        BackgroundDefaultLoader(Context context) {
            this.mContext = context.getApplicationContext();
        }

        @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoader
        public final void load(final EmojiCompat.MetadataRepoLoaderCallback metadataRepoLoaderCallback) {
            final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new ConcurrencyHelpers$$ExternalSyntheticLambda0("EmojiCompatInitializer"));
            threadPoolExecutor.allowCoreThreadTimeOut(true);
            threadPoolExecutor.execute(new Runnable() { // from class: androidx.emoji2.text.EmojiCompatInitializer$BackgroundDefaultLoader$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    EmojiCompatInitializer.BackgroundDefaultLoader.$r8$lambda$2V1iWTiAwNxOBlVvz73bbuEdzIw(EmojiCompatInitializer.BackgroundDefaultLoader.this, metadataRepoLoaderCallback, threadPoolExecutor);
                }
            });
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class LoadEmojiCompatRunnable implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
            try {
                Trace.beginSection("EmojiCompat.EmojiCompatInitializer.run");
                if (EmojiCompat.isConfigured()) {
                    EmojiCompat.get().load();
                }
            } finally {
                Trace.endSection();
            }
        }
    }

    @Override // androidx.startup.Initializer
    public final List dependencies() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    @Override // androidx.startup.Initializer
    public final Boolean create(Context context) {
        EmojiCompat.init(new BackgroundDefaultConfig(context));
        final Lifecycle lifecycle = ((LifecycleOwner) AppInitializer.getInstance(context).initializeComponent()).getLifecycle();
        lifecycle.addObserver(new DefaultLifecycleObserver() { // from class: androidx.emoji2.text.EmojiCompatInitializer.1
            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public final void onResume(LifecycleOwner lifecycleOwner) {
                EmojiCompatInitializer.this.getClass();
                Handler.createAsync(Looper.getMainLooper()).postDelayed(new LoadEmojiCompatRunnable(), 500L);
                lifecycle.removeObserver(this);
            }
        });
        return Boolean.TRUE;
    }
}
