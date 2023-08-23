package kotlinx.coroutines;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public class JobImpl extends JobSupport {
    private final boolean handlesException;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JobImpl(Job job) {
        super(true);
        ChildHandleNode childHandleNode;
        ChildHandleNode childHandleNode2;
        boolean z = true;
        initParentJob(job);
        ChildHandle parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines = getParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
        if (parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines instanceof ChildHandleNode) {
            childHandleNode = (ChildHandleNode) parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines;
        } else {
            childHandleNode = null;
        }
        if (childHandleNode != null) {
            JobSupport job2 = childHandleNode.getJob();
            while (!job2.getHandlesException$external__kotlinx_coroutines__android_common__kotlinx_coroutines()) {
                ChildHandle parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines2 = job2.getParentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines();
                if (parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines2 instanceof ChildHandleNode) {
                    childHandleNode2 = (ChildHandleNode) parentHandle$external__kotlinx_coroutines__android_common__kotlinx_coroutines2;
                } else {
                    childHandleNode2 = null;
                }
                if (childHandleNode2 != null) {
                    job2 = childHandleNode2.getJob();
                }
            }
            this.handlesException = z;
        }
        z = false;
        this.handlesException = z;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final boolean getHandlesException$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return this.handlesException;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final boolean getOnCancelComplete$external__kotlinx_coroutines__android_common__kotlinx_coroutines() {
        return true;
    }
}
