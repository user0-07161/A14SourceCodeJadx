package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class JobSupport implements Job {
    private final AtomicRef _parentHandle;
    private final AtomicRef _state;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class ChildCompletion extends JobNode {
        private final ChildHandleNode child;
        private final JobSupport parent;
        private final Object proposedUpdate;
        private final Finishing state;

        public ChildCompletion(JobSupport parent, Finishing state, ChildHandleNode childHandleNode, Object obj) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            this.parent = parent;
            this.state = state;
            this.child = childHandleNode;
            this.proposedUpdate = obj;
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.JobNode
        public final void invoke(Throwable th) {
            JobSupport.access$continueCompleting(this.parent, this.state, this.child, this.proposedUpdate);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Finishing implements Incomplete {
        private final AtomicRef _rootCause;
        private final NodeList list;
        private final AtomicBoolean _isCompleting = new AtomicBoolean(false);
        private final AtomicRef _exceptionsHolder = new AtomicRef(null);

        public Finishing(NodeList nodeList, Throwable th) {
            this.list = nodeList;
            this._rootCause = new AtomicRef(th);
        }

        public final void addExceptionLocked(Throwable exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            Throwable rootCause = getRootCause();
            if (rootCause == null) {
                this._rootCause.setValue(exception);
            } else if (exception == rootCause) {
            } else {
                AtomicRef atomicRef = this._exceptionsHolder;
                Object value = atomicRef.getValue();
                if (value == null) {
                    atomicRef.setValue(exception);
                } else if (value instanceof Throwable) {
                    if (exception == value) {
                        return;
                    }
                    ArrayList arrayList = new ArrayList(4);
                    arrayList.add(value);
                    arrayList.add(exception);
                    atomicRef.setValue(arrayList);
                } else if (value instanceof ArrayList) {
                    ((ArrayList) value).add(exception);
                } else {
                    throw new IllegalStateException(("State is " + value).toString());
                }
            }
        }

        @Override // kotlinx.coroutines.Incomplete
        public final NodeList getList() {
            return this.list;
        }

        public final Throwable getRootCause() {
            return (Throwable) this._rootCause.getValue();
        }

        @Override // kotlinx.coroutines.Incomplete
        public final boolean isActive() {
            if (getRootCause() == null) {
                return true;
            }
            return false;
        }

        public final boolean isCancelling() {
            if (getRootCause() != null) {
                return true;
            }
            return false;
        }

        public final boolean isCompleting() {
            return this._isCompleting.getValue();
        }

        public final boolean isSealed() {
            if (this._exceptionsHolder.getValue() == JobSupportKt.access$getSEALED$p()) {
                return true;
            }
            return false;
        }

        public final List sealLocked(Throwable th) {
            ArrayList arrayList;
            AtomicRef atomicRef = this._exceptionsHolder;
            Object value = atomicRef.getValue();
            if (value == null) {
                arrayList = new ArrayList(4);
            } else if (value instanceof Throwable) {
                ArrayList arrayList2 = new ArrayList(4);
                arrayList2.add(value);
                arrayList = arrayList2;
            } else if (value instanceof ArrayList) {
                arrayList = (ArrayList) value;
            } else {
                throw new IllegalStateException(("State is " + value).toString());
            }
            Throwable rootCause = getRootCause();
            if (rootCause != null) {
                arrayList.add(0, rootCause);
            }
            if (th != null && !Intrinsics.areEqual(th, rootCause)) {
                arrayList.add(th);
            }
            atomicRef.setValue(JobSupportKt.access$getSEALED$p());
            return arrayList;
        }

        public final void setCompleting() {
            this._isCompleting.setValue();
        }

        public final String toString() {
            boolean isCancelling = isCancelling();
            boolean isCompleting = isCompleting();
            Throwable rootCause = getRootCause();
            Object value = this._exceptionsHolder.getValue();
            return "Finishing[cancelling=" + isCancelling + ", completing=" + isCompleting + ", rootCause=" + rootCause + ", exceptions=" + value + ", list=" + this.list + "]";
        }
    }

    public JobSupport(boolean z) {
        Empty access$getEMPTY_NEW$p;
        if (z) {
            access$getEMPTY_NEW$p = JobSupportKt.access$getEMPTY_ACTIVE$p();
        } else {
            access$getEMPTY_NEW$p = JobSupportKt.access$getEMPTY_NEW$p();
        }
        this._state = AtomicFU.atomic(access$getEMPTY_NEW$p);
        this._parentHandle = AtomicFU.atomic((Object) null);
    }

    public static final void access$continueCompleting(JobSupport jobSupport, Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        jobSupport.getClass();
        ChildHandleNode nextChild = nextChild(childHandleNode);
        if (nextChild == null || !jobSupport.tryWaitForChild(finishing, nextChild, obj)) {
            jobSupport.afterCompletion(jobSupport.finalizeFinishingState(finishing, obj));
        }
    }

    private final boolean cancelParent(Throwable th) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        ChildHandle parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines != null && parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines != NonDisposableHandle.INSTANCE) {
            if (parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines.childCancelled(th) || z) {
                return true;
            }
            return false;
        }
        return z;
    }

    private final void completeStateFinalization(Incomplete incomplete, Object obj) {
        CompletedExceptionally completedExceptionally;
        Throwable th;
        ChildHandle parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines != null) {
            parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines.dispose();
            this._parentHandle.setValue(NonDisposableHandle.INSTANCE);
        }
        CompletionHandlerException completionHandlerException = null;
        if (obj instanceof CompletedExceptionally) {
            completedExceptionally = (CompletedExceptionally) obj;
        } else {
            completedExceptionally = null;
        }
        if (completedExceptionally != null) {
            th = completedExceptionally.cause;
        } else {
            th = null;
        }
        if (incomplete instanceof JobNode) {
            try {
                ((JobNode) incomplete).invoke(th);
                return;
            } catch (Throwable th2) {
                handleOnCompletionException$external__kotlinx_coroutines__android_common__kotlinx_coroutines(new CompletionHandlerException("Exception in completion handler " + incomplete + " for " + this, th2));
                return;
            }
        }
        NodeList list = incomplete.getList();
        if (list != null) {
            Object next = list.getNext();
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(lockFreeLinkedListNode, list); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                if (lockFreeLinkedListNode instanceof JobNode) {
                    JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                    try {
                        jobNode.invoke(th);
                    } catch (Throwable th3) {
                        if (completionHandlerException != null) {
                            ExceptionsKt.addSuppressed(completionHandlerException, th3);
                        } else {
                            completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th3);
                        }
                    }
                }
            }
            if (completionHandlerException != null) {
                handleOnCompletionException$external__kotlinx_coroutines__android_common__kotlinx_coroutines(completionHandlerException);
            }
        }
    }

    private final Throwable createCauseException(Object obj) {
        Throwable th;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        JobSupport jobSupport = (JobSupport) obj;
        Object state$external__kotlinx_coroutines__android_common__kotlinx_coroutines = jobSupport.getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        CancellationException cancellationException = null;
        if (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Finishing) {
            th = ((Finishing) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).getRootCause();
        } else if (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof CompletedExceptionally) {
            th = ((CompletedExceptionally) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).cause;
        } else if (!(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Incomplete)) {
            th = null;
        } else {
            throw new IllegalStateException(("Cannot be cancelling child in this state: " + state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).toString());
        }
        if (th instanceof CancellationException) {
            cancellationException = th;
        }
        if (cancellationException == null) {
            cancellationException = new JobCancellationException("Parent job is ".concat(stateString(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines)), th, jobSupport);
        }
        return cancellationException;
    }

    private final Object finalizeFinishingState(Finishing finishing, Object obj) {
        CompletedExceptionally completedExceptionally;
        Throwable finalRootCause;
        boolean z;
        Object obj2;
        Throwable th = null;
        if (obj instanceof CompletedExceptionally) {
            completedExceptionally = (CompletedExceptionally) obj;
        } else {
            completedExceptionally = null;
        }
        if (completedExceptionally != null) {
            th = completedExceptionally.cause;
        }
        synchronized (finishing) {
            finishing.isCancelling();
            List<Throwable> sealLocked = finishing.sealLocked(th);
            finalRootCause = getFinalRootCause(finishing, sealLocked);
            z = true;
            if (finalRootCause != null && sealLocked.size() > 1) {
                Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap(sealLocked.size()));
                Intrinsics.checkNotNullExpressionValue(newSetFromMap, "newSetFromMap(IdentityHashMap(expectedSize))");
                for (Throwable th2 : sealLocked) {
                    if (th2 != finalRootCause && th2 != finalRootCause && !(th2 instanceof CancellationException) && newSetFromMap.add(th2)) {
                        ExceptionsKt.addSuppressed(finalRootCause, th2);
                    }
                }
            }
        }
        if (finalRootCause != null && finalRootCause != th) {
            obj = new CompletedExceptionally(finalRootCause, false);
        }
        if (finalRootCause != null) {
            if (!cancelParent(finalRootCause) && !handleJobException(finalRootCause)) {
                z = false;
            }
            if (z) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                ((CompletedExceptionally) obj).makeHandled();
            }
        }
        onCompletionInternal(obj);
        AtomicRef atomicRef = this._state;
        if (obj instanceof Incomplete) {
            obj2 = new IncompleteStateBox((Incomplete) obj);
        } else {
            obj2 = obj;
        }
        atomicRef.compareAndSet(finishing, obj2);
        completeStateFinalization(finishing, obj);
        return obj;
    }

    private final Throwable getFinalRootCause(Finishing finishing, List list) {
        Object obj;
        boolean z;
        Object obj2 = null;
        if (list.isEmpty()) {
            if (!finishing.isCancelling()) {
                return null;
            }
            return new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        Iterator it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (!(((Throwable) obj) instanceof CancellationException)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        Throwable th = (Throwable) obj;
        if (th != null) {
            return th;
        }
        Throwable th2 = (Throwable) list.get(0);
        if (th2 instanceof TimeoutCancellationException) {
            Iterator it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                Throwable th3 = (Throwable) next;
                if (th3 != th2 && (th3 instanceof TimeoutCancellationException)) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    obj2 = next;
                    break;
                }
            }
            Throwable th4 = (Throwable) obj2;
            if (th4 != null) {
                return th4;
            }
        }
        return th2;
    }

    private final NodeList getOrPromoteCancellingList(Incomplete incomplete) {
        NodeList list = incomplete.getList();
        if (list == null) {
            if (incomplete instanceof Empty) {
                return new NodeList();
            }
            if (incomplete instanceof JobNode) {
                JobNode jobNode = (JobNode) incomplete;
                jobNode.addOneIfEmpty(new NodeList());
                this._state.compareAndSet(jobNode, jobNode.getNextNode());
                return null;
            }
            throw new IllegalStateException(("State should have list: " + incomplete).toString());
        }
        return list;
    }

    private static ChildHandleNode nextChild(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getPrevNode();
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if (!lockFreeLinkedListNode.isRemoved()) {
                if (lockFreeLinkedListNode instanceof ChildHandleNode) {
                    return (ChildHandleNode) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    private final void notifyCancelling(NodeList nodeList, Throwable th) {
        Object next = nodeList.getNext();
        Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
        CompletionHandlerException completionHandlerException = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(lockFreeLinkedListNode, nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof JobCancellingNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.invoke(th);
                } catch (Throwable th2) {
                    if (completionHandlerException != null) {
                        ExceptionsKt.addSuppressed(completionHandlerException, th2);
                    } else {
                        completionHandlerException = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th2);
                    }
                }
            }
        }
        if (completionHandlerException != null) {
            handleOnCompletionException$external__kotlinx_coroutines__android_common__kotlinx_coroutines(completionHandlerException);
        }
        cancelParent(th);
    }

    private final int startInternal(Object obj) {
        boolean z = obj instanceof Empty;
        AtomicRef atomicRef = this._state;
        if (z) {
            if (((Empty) obj).isActive()) {
                return 0;
            }
            if (!atomicRef.compareAndSet(obj, JobSupportKt.access$getEMPTY_ACTIVE$p())) {
                return -1;
            }
            onStart$1();
            return 1;
        } else if (!(obj instanceof InactiveNodeList)) {
            return 0;
        } else {
            if (!atomicRef.compareAndSet(obj, ((InactiveNodeList) obj).getList())) {
                return -1;
            }
            onStart$1();
            return 1;
        }
    }

    private static String stateString(Object obj) {
        if (obj instanceof Finishing) {
            Finishing finishing = (Finishing) obj;
            if (finishing.isCancelling()) {
                return "Cancelling";
            }
            if (finishing.isCompleting()) {
                return "Completing";
            }
        } else if (obj instanceof Incomplete) {
            if (!((Incomplete) obj).isActive()) {
                return "New";
            }
        } else if (obj instanceof CompletedExceptionally) {
            return "Cancelled";
        } else {
            return "Completed";
        }
        return "Active";
    }

    public static CancellationException toCancellationException$default(JobSupport jobSupport, Throwable th) {
        CancellationException cancellationException;
        jobSupport.getClass();
        Intrinsics.checkNotNullParameter(th, "<this>");
        if (th instanceof CancellationException) {
            cancellationException = (CancellationException) th;
        } else {
            cancellationException = null;
        }
        if (cancellationException == null) {
            return new JobCancellationException(jobSupport.cancellationExceptionMessage(), th, jobSupport);
        }
        return cancellationException;
    }

    private final Object tryMakeCompleting(Object obj, Object obj2) {
        Object obj3;
        Finishing finishing;
        CompletedExceptionally completedExceptionally;
        ChildHandleNode childHandleNode;
        if (!(obj instanceof Incomplete)) {
            return JobSupportKt.access$getCOMPLETING_ALREADY$p();
        }
        boolean z = true;
        if (((obj instanceof Empty) || (obj instanceof JobNode)) && !(obj instanceof ChildHandleNode) && !(obj2 instanceof CompletedExceptionally)) {
            Incomplete incomplete = (Incomplete) obj;
            AtomicRef atomicRef = this._state;
            Symbol symbol = JobSupportKt.COMPLETING_WAITING_CHILDREN;
            if (obj2 instanceof Incomplete) {
                obj3 = new IncompleteStateBox((Incomplete) obj2);
            } else {
                obj3 = obj2;
            }
            if (!atomicRef.compareAndSet(incomplete, obj3)) {
                z = false;
            } else {
                onCompletionInternal(obj2);
                completeStateFinalization(incomplete, obj2);
            }
            if (z) {
                return obj2;
            }
            return JobSupportKt.access$getCOMPLETING_RETRY$p();
        }
        Incomplete incomplete2 = (Incomplete) obj;
        NodeList orPromoteCancellingList = getOrPromoteCancellingList(incomplete2);
        if (orPromoteCancellingList == null) {
            return JobSupportKt.access$getCOMPLETING_RETRY$p();
        }
        ChildHandleNode childHandleNode2 = null;
        if (incomplete2 instanceof Finishing) {
            finishing = (Finishing) incomplete2;
        } else {
            finishing = null;
        }
        if (finishing == null) {
            finishing = new Finishing(orPromoteCancellingList, null);
        }
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        synchronized (finishing) {
            if (finishing.isCompleting()) {
                return JobSupportKt.access$getCOMPLETING_ALREADY$p();
            }
            finishing.setCompleting();
            if (finishing != incomplete2 && !this._state.compareAndSet(incomplete2, finishing)) {
                return JobSupportKt.access$getCOMPLETING_RETRY$p();
            }
            boolean isCancelling = finishing.isCancelling();
            if (obj2 instanceof CompletedExceptionally) {
                completedExceptionally = (CompletedExceptionally) obj2;
            } else {
                completedExceptionally = null;
            }
            if (completedExceptionally != null) {
                finishing.addExceptionLocked(completedExceptionally.cause);
            }
            Throwable rootCause = finishing.getRootCause();
            if (!Boolean.valueOf(true ^ isCancelling).booleanValue()) {
                rootCause = null;
            }
            ref$ObjectRef.element = rootCause;
            if (rootCause != null) {
                notifyCancelling(orPromoteCancellingList, rootCause);
            }
            if (incomplete2 instanceof ChildHandleNode) {
                childHandleNode = (ChildHandleNode) incomplete2;
            } else {
                childHandleNode = null;
            }
            if (childHandleNode == null) {
                NodeList list = incomplete2.getList();
                if (list != null) {
                    childHandleNode2 = nextChild(list);
                }
            } else {
                childHandleNode2 = childHandleNode;
            }
            if (childHandleNode2 != null && tryWaitForChild(finishing, childHandleNode2, obj2)) {
                return JobSupportKt.COMPLETING_WAITING_CHILDREN;
            }
            return finalizeFinishingState(finishing, obj2);
        }
    }

    private final boolean tryWaitForChild(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        while (Job.DefaultImpls.invokeOnCompletion$default(childHandleNode.childJob, false, new ChildCompletion(this, finishing, childHandleNode, obj), 1) == NonDisposableHandle.INSTANCE) {
            childHandleNode = nextChild(childHandleNode);
            if (childHandleNode == null) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void afterResume(Object obj) {
        afterCompletion(obj);
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException);
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x00fb, code lost:
        r0 = r9;
     */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00c3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0047 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean cancelImpl$external__kotlinx_coroutines__android_common__kotlinx_coroutines(java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.cancelImpl$external__kotlinx_coroutines__android_common__kotlinx_coroutines(java.lang.Object):boolean");
    }

    public void cancelInternal(Throwable th) {
        cancelImpl$external__kotlinx_coroutines__android_common__kotlinx_coroutines(th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    public boolean childCancelled(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        if (cancelImpl$external__kotlinx_coroutines__android_common__kotlinx_coroutines(th) && getHandlesException$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
            return true;
        }
        return false;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 operation) {
        Intrinsics.checkNotNullParameter(operation, "operation");
        return operation.invoke(obj, this);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return CoroutineContext.DefaultImpls.get(this, key);
    }

    public final CancellationException getCancellationException() {
        Object state$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        CancellationException cancellationException = null;
        if (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Finishing) {
            Throwable rootCause = ((Finishing) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).getRootCause();
            if (rootCause != null) {
                String concat = DebugStringsKt.getClassSimpleName(this).concat(" is cancelling");
                if (rootCause instanceof CancellationException) {
                    cancellationException = (CancellationException) rootCause;
                }
                if (cancellationException == null) {
                    if (concat == null) {
                        concat = cancellationExceptionMessage();
                    }
                    return new JobCancellationException(concat, rootCause, this);
                }
                return cancellationException;
            }
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        } else if (!(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Incomplete)) {
            if (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof CompletedExceptionally) {
                return toCancellationException$default(this, ((CompletedExceptionally) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).cause);
            }
            return new JobCancellationException(DebugStringsKt.getClassSimpleName(this).concat(" has completed normally"), null, this);
        } else {
            throw new IllegalStateException(("Job is still new or active: " + this).toString());
        }
    }

    public boolean getHandlesException$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return true;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key getKey() {
        return Job.Key.$$INSTANCE;
    }

    public boolean getOnCancelComplete$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return false;
    }

    public final ChildHandle getParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return (ChildHandle) this._parentHandle.getValue();
    }

    public final Object getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        while (true) {
            Object value = this._state.getValue();
            if (!(value instanceof OpDescriptor)) {
                return value;
            }
            ((OpDescriptor) value).perform(this);
        }
    }

    protected boolean handleJobException(Throwable th) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void initParentJob(Job job) {
        int startInternal;
        NonDisposableHandle nonDisposableHandle = NonDisposableHandle.INSTANCE;
        AtomicRef atomicRef = this._parentHandle;
        if (job == null) {
            atomicRef.setValue(nonDisposableHandle);
            return;
        }
        JobSupport jobSupport = (JobSupport) job;
        do {
            startInternal = jobSupport.startInternal(jobSupport.getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines());
            if (startInternal == 0) {
                break;
            }
        } while (startInternal != 1);
        ChildHandle childHandle = (ChildHandle) Job.DefaultImpls.invokeOnCompletion$default(jobSupport, true, new ChildHandleNode(this), 2);
        atomicRef.setValue(childHandle);
        if (!(getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() instanceof Incomplete)) {
            childHandle.dispose();
            atomicRef.setValue(nonDisposableHandle);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [kotlinx.coroutines.InactiveNodeList] */
    public final DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1 function1) {
        final JobNode jobNode;
        CompletedExceptionally completedExceptionally;
        Throwable th;
        boolean z3;
        Throwable th2 = null;
        if (z) {
            if (function1 instanceof JobCancellingNode) {
                jobNode = (JobCancellingNode) function1;
            } else {
                jobNode = null;
            }
            if (jobNode == null) {
                jobNode = new InvokeOnCancelling(function1);
            }
        } else {
            if (function1 instanceof JobNode) {
                jobNode = (JobNode) function1;
            } else {
                jobNode = null;
            }
            if (jobNode == null) {
                jobNode = new InvokeOnCompletion(function1);
            }
        }
        jobNode.job = this;
        while (true) {
            final Object state$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            if (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Empty) {
                Empty empty = (Empty) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines;
                if (empty.isActive()) {
                    if (this._state.compareAndSet(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines, jobNode)) {
                        return jobNode;
                    }
                } else {
                    NodeList nodeList = new NodeList();
                    if (!empty.isActive()) {
                        nodeList = new InactiveNodeList(nodeList);
                    }
                    this._state.compareAndSet(empty, nodeList);
                }
            } else if (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Incomplete) {
                NodeList list = ((Incomplete) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).getList();
                if (list == null) {
                    Intrinsics.checkNotNull(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    JobNode jobNode2 = (JobNode) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines;
                    jobNode2.addOneIfEmpty(new NodeList());
                    this._state.compareAndSet(jobNode2, jobNode2.getNextNode());
                } else {
                    DisposableHandle disposableHandle = NonDisposableHandle.INSTANCE;
                    boolean z4 = false;
                    if (z && (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Finishing)) {
                        synchronized (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines) {
                            th = ((Finishing) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).getRootCause();
                            if (th == null || ((function1 instanceof ChildHandleNode) && !((Finishing) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).isCompleting())) {
                                LockFreeLinkedListNode.CondAddOp condAddOp = new LockFreeLinkedListNode.CondAddOp(jobNode) { // from class: kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1
                                    @Override // kotlinx.coroutines.internal.AtomicOp
                                    public final Symbol prepare(Object obj) {
                                        boolean z5;
                                        LockFreeLinkedListNode affected = (LockFreeLinkedListNode) obj;
                                        Intrinsics.checkNotNullParameter(affected, "affected");
                                        if (this.getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == state$external__kotlinx_coroutines__android_common__kotlinx_coroutines) {
                                            z5 = true;
                                        } else {
                                            z5 = false;
                                        }
                                        if (z5) {
                                            return null;
                                        }
                                        return LockFreeLinkedListKt.getCONDITION_FALSE();
                                    }
                                };
                                while (true) {
                                    int tryCondAddNext = list.getPrevNode().tryCondAddNext(jobNode, list, condAddOp);
                                    if (tryCondAddNext != 1) {
                                        if (tryCondAddNext == 2) {
                                            z3 = false;
                                            break;
                                        }
                                    } else {
                                        z3 = true;
                                        break;
                                    }
                                }
                                if (z3) {
                                    if (th == null) {
                                        return jobNode;
                                    }
                                    disposableHandle = jobNode;
                                }
                            }
                        }
                    } else {
                        th = null;
                    }
                    if (th != null) {
                        if (z2) {
                            function1.invoke(th);
                        }
                        return disposableHandle;
                    }
                    LockFreeLinkedListNode.CondAddOp condAddOp2 = new LockFreeLinkedListNode.CondAddOp(jobNode) { // from class: kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1
                        @Override // kotlinx.coroutines.internal.AtomicOp
                        public final Symbol prepare(Object obj) {
                            boolean z5;
                            LockFreeLinkedListNode affected = (LockFreeLinkedListNode) obj;
                            Intrinsics.checkNotNullParameter(affected, "affected");
                            if (this.getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines() == state$external__kotlinx_coroutines__android_common__kotlinx_coroutines) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            if (z5) {
                                return null;
                            }
                            return LockFreeLinkedListKt.getCONDITION_FALSE();
                        }
                    };
                    while (true) {
                        int tryCondAddNext2 = list.getPrevNode().tryCondAddNext(jobNode, list, condAddOp2);
                        if (tryCondAddNext2 != 1) {
                            if (tryCondAddNext2 == 2) {
                                break;
                            }
                        } else {
                            z4 = true;
                            break;
                        }
                    }
                    if (z4) {
                        return jobNode;
                    }
                }
            } else {
                if (z2) {
                    if (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof CompletedExceptionally) {
                        completedExceptionally = (CompletedExceptionally) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines;
                    } else {
                        completedExceptionally = null;
                    }
                    if (completedExceptionally != null) {
                        th2 = completedExceptionally.cause;
                    }
                    function1.invoke(th2);
                }
                return NonDisposableHandle.INSTANCE;
            }
        }
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object state$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if ((state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Incomplete) && ((Incomplete) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).isActive()) {
            return true;
        }
        return false;
    }

    public final boolean isCancelled() {
        Object state$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (!(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof CompletedExceptionally) && (!(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Finishing) || !((Finishing) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).isCancelling())) {
            return false;
        }
        return true;
    }

    protected boolean isScopedCoroutine() {
        return this instanceof BlockingCoroutine;
    }

    public final Object join(Continuation continuation) {
        boolean z;
        while (true) {
            Object state$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            if (!(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Incomplete)) {
                z = false;
                break;
            } else if (startInternal(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines) >= 0) {
                z = true;
                break;
            }
        }
        Unit unit = Unit.INSTANCE;
        if (!z) {
            JobKt.ensureActive(continuation.getContext());
            return unit;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        CancellableContinuationKt.disposeOnCancellation(cancellableContinuationImpl, invokeOnCompletion(false, true, new ResumeOnCompletion(cancellableContinuationImpl)));
        Object result = cancellableContinuationImpl.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (result != coroutineSingletons) {
            result = unit;
        }
        if (result == coroutineSingletons) {
            return result;
        }
        return unit;
    }

    public final Object makeCompletingOnce$external__kotlinx_coroutines__android_common__kotlinx_coroutines(Object obj) {
        Object tryMakeCompleting;
        CompletedExceptionally completedExceptionally;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines(), obj);
            if (tryMakeCompleting == JobSupportKt.access$getCOMPLETING_ALREADY$p()) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                Throwable th = null;
                if (obj instanceof CompletedExceptionally) {
                    completedExceptionally = (CompletedExceptionally) obj;
                } else {
                    completedExceptionally = null;
                }
                if (completedExceptionally != null) {
                    th = completedExceptionally.cause;
                }
                throw new IllegalStateException(str, th);
            }
        } while (tryMakeCompleting == JobSupportKt.access$getCOMPLETING_RETRY$p());
        return tryMakeCompleting;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return CoroutineContext.DefaultImpls.minusKey(this, key);
    }

    public String nameString$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return DebugStringsKt.getClassSimpleName(this);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CoroutineContext.DefaultImpls.plus(this, context);
    }

    public final void removeNode$external__kotlinx_coroutines__android_common__kotlinx_coroutines(JobNode node) {
        Object state$external__kotlinx_coroutines__android_common__kotlinx_coroutines;
        Intrinsics.checkNotNullParameter(node, "node");
        do {
            state$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
            if (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof JobNode) {
                if (state$external__kotlinx_coroutines__android_common__kotlinx_coroutines != node) {
                    return;
                }
            } else if ((state$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof Incomplete) && ((Incomplete) state$external__kotlinx_coroutines__android_common__kotlinx_coroutines).getList() != null) {
                node.remove$1();
                return;
            } else {
                return;
            }
        } while (!this._state.compareAndSet(state$external__kotlinx_coroutines__android_common__kotlinx_coroutines, JobSupportKt.access$getEMPTY_ACTIVE$p()));
    }

    public final String toString() {
        return (nameString$external__kotlinx_coroutines__android_common__kotlinx_coroutines() + "{" + stateString(getState$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) + "}") + "@" + DebugStringsKt.getHexAddress(this);
    }

    protected void onStart$1() {
    }

    protected void afterCompletion(Object obj) {
    }

    public void handleOnCompletionException$external__kotlinx_coroutines__android_common__kotlinx_coroutines(CompletionHandlerException completionHandlerException) {
        throw completionHandlerException;
    }

    protected void onCompletionInternal(Object obj) {
    }
}
