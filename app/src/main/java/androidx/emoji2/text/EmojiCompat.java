package androidx.emoji2.text;

import android.os.Handler;
import android.os.Looper;
import androidx.collection.ArraySet;
import androidx.compose.ui.text.platform.DefaultImpl$getFontLoadState$initCallback$1;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class EmojiCompat {
    private static final Object INSTANCE_LOCK = new Object();
    private static volatile EmojiCompat sInstance;
    private final int mEmojiSpanIndicatorColor;
    private final GlyphChecker mGlyphChecker;
    private final CompatInternal19 mHelper;
    private final ArraySet mInitCallbacks;
    private final ReadWriteLock mInitLock;
    private volatile int mLoadState;
    private final Handler mMainHandler;
    private final int mMetadataLoadStrategy;
    final MetadataRepoLoader mMetadataLoader;
    private final DefaultSpanFactory mSpanFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class CompatInternal {
        final EmojiCompat mEmojiCompat;

        CompatInternal(EmojiCompat emojiCompat) {
            this.mEmojiCompat = emojiCompat;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class CompatInternal19 extends CompatInternal {
        private volatile MetadataRepo mMetadataRepo;
        private volatile EmojiProcessor mProcessor;

        /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
        /* renamed from: androidx.emoji2.text.EmojiCompat$CompatInternal19$1  reason: invalid class name */
        /* loaded from: classes.dex */
        final class AnonymousClass1 extends MetadataRepoLoaderCallback {
            AnonymousClass1() {
            }

            @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
            public final void onFailed(Throwable th) {
                CompatInternal19.this.mEmojiCompat.onMetadataLoadFailed(th);
            }

            @Override // androidx.emoji2.text.EmojiCompat.MetadataRepoLoaderCallback
            public final void onLoaded(MetadataRepo metadataRepo) {
                CompatInternal19.this.onMetadataLoadSuccess(metadataRepo);
            }
        }

        final void onMetadataLoadSuccess(MetadataRepo metadataRepo) {
            Set<Object> emptySet;
            this.mMetadataRepo = metadataRepo;
            MetadataRepo metadataRepo2 = this.mMetadataRepo;
            DefaultSpanFactory defaultSpanFactory = this.mEmojiCompat.mSpanFactory;
            GlyphChecker glyphChecker = this.mEmojiCompat.mGlyphChecker;
            this.mEmojiCompat.getClass();
            this.mEmojiCompat.getClass();
            try {
                Object invoke = Class.forName("android.text.EmojiConsistency").getMethod("getEmojiConsistencySet", new Class[0]).invoke(null, new Object[0]);
                if (invoke == null) {
                    emptySet = Collections.emptySet();
                } else {
                    emptySet = (Set) invoke;
                    for (Object obj : emptySet) {
                        if (!(obj instanceof int[])) {
                            emptySet = Collections.emptySet();
                            break;
                        }
                    }
                }
            } catch (Throwable unused) {
                emptySet = Collections.emptySet();
            }
            this.mProcessor = new EmojiProcessor(metadataRepo2, defaultSpanFactory, glyphChecker, emptySet);
            this.mEmojiCompat.onMetadataLoadSuccess();
        }

        final CharSequence process(CharSequence charSequence, int i, boolean z) {
            return this.mProcessor.process(charSequence, i, z);
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class Config {
        final MetadataRepoLoader mMetadataLoader;
        int mMetadataLoadStrategy = 0;
        GlyphChecker mGlyphChecker = new DefaultGlyphChecker();

        /* JADX INFO: Access modifiers changed from: protected */
        public Config(MetadataRepoLoader metadataRepoLoader) {
            this.mMetadataLoader = metadataRepoLoader;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class DefaultSpanFactory {
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface GlyphChecker {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class ListenerDispatcher implements Runnable {
        private final List mInitCallbacks;
        private final int mLoadState;
        private final Throwable mThrowable;

        ListenerDispatcher(Collection collection, int i, Throwable th) {
            Preconditions.checkNotNull(collection, "initCallbacks cannot be null");
            this.mInitCallbacks = new ArrayList(collection);
            this.mLoadState = i;
            this.mThrowable = th;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int size = ((ArrayList) this.mInitCallbacks).size();
            int i = 0;
            if (this.mLoadState != 1) {
                while (i < size) {
                    ((DefaultImpl$getFontLoadState$initCallback$1) ((ArrayList) this.mInitCallbacks).get(i)).onFailed();
                    i++;
                }
                return;
            }
            while (i < size) {
                ((DefaultImpl$getFontLoadState$initCallback$1) ((ArrayList) this.mInitCallbacks).get(i)).onInitialized();
                i++;
            }
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public interface MetadataRepoLoader {
        void load(MetadataRepoLoaderCallback metadataRepoLoaderCallback);
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public abstract class MetadataRepoLoaderCallback {
        public abstract void onFailed(Throwable th);

        public abstract void onLoaded(MetadataRepo metadataRepo);
    }

    private EmojiCompat(Config config) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.mInitLock = reentrantReadWriteLock;
        this.mLoadState = 3;
        this.mEmojiSpanIndicatorColor = -16711936;
        MetadataRepoLoader metadataRepoLoader = config.mMetadataLoader;
        this.mMetadataLoader = metadataRepoLoader;
        int i = config.mMetadataLoadStrategy;
        this.mMetadataLoadStrategy = i;
        this.mGlyphChecker = config.mGlyphChecker;
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mInitCallbacks = new ArraySet();
        this.mSpanFactory = new DefaultSpanFactory();
        CompatInternal19 compatInternal19 = new CompatInternal19(this);
        this.mHelper = compatInternal19;
        reentrantReadWriteLock.writeLock().lock();
        if (i == 0) {
            try {
                this.mLoadState = 0;
            } catch (Throwable th) {
                ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
                throw th;
            }
        }
        reentrantReadWriteLock.writeLock().unlock();
        if (getLoadState() == 0) {
            try {
                metadataRepoLoader.load(new CompatInternal19.AnonymousClass1());
            } catch (Throwable th2) {
                onMetadataLoadFailed(th2);
            }
        }
    }

    public static EmojiCompat get() {
        EmojiCompat emojiCompat;
        boolean z;
        synchronized (INSTANCE_LOCK) {
            emojiCompat = sInstance;
            if (emojiCompat != null) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                throw new IllegalStateException("EmojiCompat is not initialized.\n\nYou must initialize EmojiCompat prior to referencing the EmojiCompat instance.\n\nThe most likely cause of this error is disabling the EmojiCompatInitializer\neither explicitly in AndroidManifest.xml, or by including\nandroidx.emoji2:emoji2-bundled.\n\nAutomatic initialization is typically performed by EmojiCompatInitializer. If\nyou are not expecting to initialize EmojiCompat manually in your application,\nplease check to ensure it has not been removed from your APK's manifest. You can\ndo this in Android Studio using Build > Analyze APK.\n\nIn the APK Analyzer, ensure that the startup entry for\nEmojiCompatInitializer and InitializationProvider is present in\n AndroidManifest.xml. If it is missing or contains tools:node=\"remove\", and you\nintend to use automatic configuration, verify:\n\n  1. Your application does not include emoji2-bundled\n  2. All modules do not contain an exclusion manifest rule for\n     EmojiCompatInitializer or InitializationProvider. For more information\n     about manifest exclusions see the documentation for the androidx startup\n     library.\n\nIf you intend to use emoji2-bundled, please call EmojiCompat.init. You can\nlearn more in the documentation for BundledEmojiCompatConfig.\n\nIf you intended to perform manual configuration, it is recommended that you call\nEmojiCompat.init immediately on application startup.\n\nIf you still cannot resolve this issue, please open a bug with your specific\nconfiguration to help improve error message.");
            }
        }
        return emojiCompat;
    }

    public static void init(Config config) {
        if (sInstance == null) {
            synchronized (INSTANCE_LOCK) {
                if (sInstance == null) {
                    sInstance = new EmojiCompat(config);
                }
            }
        }
    }

    public static boolean isConfigured() {
        if (sInstance != null) {
            return true;
        }
        return false;
    }

    public final int getLoadState() {
        this.mInitLock.readLock().lock();
        try {
            return this.mLoadState;
        } finally {
            this.mInitLock.readLock().unlock();
        }
    }

    public final void load() {
        boolean z;
        boolean z2 = true;
        if (this.mMetadataLoadStrategy == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (getLoadState() != 1) {
                z2 = false;
            }
            if (z2) {
                return;
            }
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().lock();
            try {
                if (this.mLoadState == 0) {
                    return;
                }
                this.mLoadState = 0;
                ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
                CompatInternal19 compatInternal19 = this.mHelper;
                EmojiCompat emojiCompat = compatInternal19.mEmojiCompat;
                try {
                    emojiCompat.mMetadataLoader.load(new CompatInternal19.AnonymousClass1());
                    return;
                } catch (Throwable th) {
                    emojiCompat.onMetadataLoadFailed(th);
                    return;
                }
            } finally {
                ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
            }
        }
        throw new IllegalStateException("Set metadataLoadStrategy to LOAD_STRATEGY_MANUAL to execute manual loading");
    }

    final void onMetadataLoadFailed(Throwable th) {
        ArrayList arrayList = new ArrayList();
        ((ReentrantReadWriteLock) this.mInitLock).writeLock().lock();
        try {
            this.mLoadState = 2;
            arrayList.addAll(this.mInitCallbacks);
            this.mInitCallbacks.clear();
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
            this.mMainHandler.post(new ListenerDispatcher(arrayList, this.mLoadState, th));
        } catch (Throwable th2) {
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
            throw th2;
        }
    }

    final void onMetadataLoadSuccess() {
        ArrayList arrayList = new ArrayList();
        ((ReentrantReadWriteLock) this.mInitLock).writeLock().lock();
        try {
            this.mLoadState = 1;
            arrayList.addAll(this.mInitCallbacks);
            this.mInitCallbacks.clear();
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
            this.mMainHandler.post(new ListenerDispatcher(arrayList, this.mLoadState, null));
        } catch (Throwable th) {
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
            throw th;
        }
    }

    public final CharSequence process(CharSequence charSequence) {
        int length;
        boolean z;
        boolean z2;
        boolean z3;
        if (charSequence == null) {
            length = 0;
        } else {
            length = charSequence.length();
        }
        boolean z4 = true;
        if (getLoadState() == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (length >= 0) {
                if (length >= 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                Preconditions.checkArgument("start should be <= than end", z2);
                if (charSequence == null) {
                    return null;
                }
                if (charSequence.length() >= 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                Preconditions.checkArgument("start should be < than charSequence length", z3);
                if (length > charSequence.length()) {
                    z4 = false;
                }
                Preconditions.checkArgument("end should be < than charSequence length", z4);
                if (charSequence.length() != 0 && length != 0) {
                    return this.mHelper.process(charSequence, length, false);
                }
                return charSequence;
            }
            throw new IllegalArgumentException("end cannot be negative");
        }
        throw new IllegalStateException("Not initialized yet");
    }

    public final void registerInitCallback(DefaultImpl$getFontLoadState$initCallback$1 defaultImpl$getFontLoadState$initCallback$1) {
        ((ReentrantReadWriteLock) this.mInitLock).writeLock().lock();
        try {
            if (this.mLoadState != 1 && this.mLoadState != 2) {
                this.mInitCallbacks.add(defaultImpl$getFontLoadState$initCallback$1);
            }
            this.mMainHandler.post(new ListenerDispatcher(Arrays.asList(defaultImpl$getFontLoadState$initCallback$1), this.mLoadState, null));
        } finally {
            ((ReentrantReadWriteLock) this.mInitLock).writeLock().unlock();
        }
    }
}
