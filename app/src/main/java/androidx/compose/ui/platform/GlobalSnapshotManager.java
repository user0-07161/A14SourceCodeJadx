package androidx.compose.ui.platform;

import androidx.compose.runtime.snapshots.Snapshot;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.AbstractChannel;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class GlobalSnapshotManager {
    private static final AtomicBoolean started = new AtomicBoolean(false);

    public static void ensureStarted() {
        Lazy lazy;
        if (started.compareAndSet(false, true)) {
            final AbstractChannel Channel$default = ChannelKt.Channel$default(-1, null, 6);
            lazy = AndroidUiDispatcher.Main$delegate;
            BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope((CoroutineContext) lazy.getValue()), null, null, new GlobalSnapshotManager$ensureStarted$1(Channel$default, null), 3);
            Snapshot.Companion.registerGlobalWriteObserver(new Function1() { // from class: androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Channel channel = Channel$default;
                    Unit unit = Unit.INSTANCE;
                    channel.mo484trySendJP2dKIU(unit);
                    return unit;
                }
            });
        }
    }
}
