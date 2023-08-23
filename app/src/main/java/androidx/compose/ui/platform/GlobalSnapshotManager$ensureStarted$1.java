package androidx.compose.ui.platform;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
@DebugMetadata(c = "androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1", f = "GlobalSnapshotManager.android.kt", l = {63}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class GlobalSnapshotManager$ensureStarted$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Channel $channel;
    Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlobalSnapshotManager$ensureStarted$1(Channel channel, Continuation continuation) {
        super(2, continuation);
        this.$channel = channel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new GlobalSnapshotManager$ensureStarted$1(this.$channel, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((GlobalSnapshotManager$ensureStarted$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0033 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003c A[Catch: all -> 0x0072, TryCatch #3 {all -> 0x0072, blocks: (B:6:0x0011, B:15:0x0034, B:17:0x003c, B:18:0x0046, B:26:0x0061, B:12:0x0027, B:28:0x0064, B:11:0x0022, B:19:0x0047, B:21:0x0057), top: B:41:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0031 -> B:15:0x0034). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r5.label
            r2 = 1
            if (r1 == 0) goto L1d
            if (r1 != r2) goto L15
            java.lang.Object r1 = r5.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r3 = r5.L$0
            kotlinx.coroutines.channels.ReceiveChannel r3 = (kotlinx.coroutines.channels.ReceiveChannel) r3
            kotlin.ResultKt.throwOnFailure(r6)     // Catch: java.lang.Throwable -> L72
            goto L34
        L15:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L1d:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.channels.Channel r3 = r5.$channel
            kotlinx.coroutines.channels.ChannelIterator r6 = r3.iterator()     // Catch: java.lang.Throwable -> L72
            r1 = r6
        L27:
            r5.L$0 = r3     // Catch: java.lang.Throwable -> L72
            r5.L$1 = r1     // Catch: java.lang.Throwable -> L72
            r5.label = r2     // Catch: java.lang.Throwable -> L72
            java.lang.Object r6 = r1.hasNext(r5)     // Catch: java.lang.Throwable -> L72
            if (r6 != r0) goto L34
            return r0
        L34:
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch: java.lang.Throwable -> L72
            boolean r6 = r6.booleanValue()     // Catch: java.lang.Throwable -> L72
            if (r6 == 0) goto L6b
            java.lang.Object r6 = r1.next()     // Catch: java.lang.Throwable -> L72
            kotlin.Unit r6 = (kotlin.Unit) r6     // Catch: java.lang.Throwable -> L72
            java.lang.Object r6 = androidx.compose.runtime.snapshots.SnapshotKt.getLock()     // Catch: java.lang.Throwable -> L72
            monitor-enter(r6)     // Catch: java.lang.Throwable -> L72
            java.util.concurrent.atomic.AtomicReference r4 = androidx.compose.runtime.snapshots.SnapshotKt.access$getCurrentGlobalSnapshot$p()     // Catch: java.lang.Throwable -> L68
            java.lang.Object r4 = r4.get()     // Catch: java.lang.Throwable -> L68
            androidx.compose.runtime.snapshots.GlobalSnapshot r4 = (androidx.compose.runtime.snapshots.GlobalSnapshot) r4     // Catch: java.lang.Throwable -> L68
            java.util.Set r4 = r4.getModified$runtime_release()     // Catch: java.lang.Throwable -> L68
            if (r4 == 0) goto L60
            boolean r4 = r4.isEmpty()     // Catch: java.lang.Throwable -> L68
            r4 = r4 ^ r2
            if (r4 != r2) goto L60
            r4 = r2
            goto L61
        L60:
            r4 = 0
        L61:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L72
            if (r4 == 0) goto L27
            androidx.compose.runtime.snapshots.SnapshotKt.access$advanceGlobalSnapshot$1()     // Catch: java.lang.Throwable -> L72
            goto L27
        L68:
            r5 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L72
            throw r5     // Catch: java.lang.Throwable -> L72
        L6b:
            r5 = 0
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r3, r5)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L72:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L74
        L74:
            r6 = move-exception
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r3, r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
