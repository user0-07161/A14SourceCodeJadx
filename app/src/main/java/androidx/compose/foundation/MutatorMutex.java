package androidx.compose.foundation;

import androidx.compose.foundation.gestures.DefaultTransformableState$transformScope$1;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MutatorMutex {
    private final AtomicReference currentMutator = new AtomicReference(null);
    private final MutexImpl mutex;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Mutator {
        private final Job job;
        private final MutatePriority priority;

        public Mutator(MutatePriority priority, Job job) {
            Intrinsics.checkNotNullParameter(priority, "priority");
            this.priority = priority;
            this.job = job;
        }

        public final boolean canInterrupt(Mutator mutator) {
            if (this.priority.compareTo(mutator.priority) >= 0) {
                return true;
            }
            return false;
        }

        public final void cancel() {
            this.job.cancel(null);
        }
    }

    public MutatorMutex() {
        int i = MutexKt.$r8$clinit;
        this.mutex = new MutexImpl(false);
    }

    public static final void access$tryMutateOrCancel(MutatorMutex mutatorMutex, Mutator mutator) {
        AtomicReference atomicReference;
        Mutator mutator2;
        do {
            atomicReference = mutatorMutex.currentMutator;
            mutator2 = (Mutator) atomicReference.get();
            if (mutator2 != null && !mutator.canInterrupt(mutator2)) {
                throw new CancellationException("Current mutation had a higher priority");
            }
        } while (!atomicReference.compareAndSet(mutator2, mutator));
        if (mutator2 != null) {
            mutator2.cancel();
        }
    }

    public final Object mutateWith(DefaultTransformableState$transformScope$1 defaultTransformableState$transformScope$1, MutatePriority mutatePriority, Function2 function2, Continuation continuation) {
        return CoroutineScopeKt.coroutineScope(new MutatorMutex$mutateWith$2(mutatePriority, this, function2, defaultTransformableState$transformScope$1, null), continuation);
    }
}
