package androidx.compose.ui.input.pointer;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilter;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SuspendingPointerInputFilter extends PointerInputFilter implements PointerInputModifier, PointerInputScope, Density {
    private final /* synthetic */ Density $$delegate_0;
    private long boundsSize;
    private CoroutineScope coroutineScope;
    private PointerEvent currentEvent;
    private final MutableVector dispatchingPointerHandlers;
    private PointerEvent lastPointerEvent;
    private final MutableVector pointerHandlers;
    private final ViewConfiguration viewConfiguration;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class PointerEventHandlerCoroutine implements AwaitPointerEventScope, Density, Continuation {
        private final /* synthetic */ SuspendingPointerInputFilter $$delegate_0;
        private final Continuation completion;
        private CancellableContinuation pointerAwaiter;
        private PointerEventPass awaitPass = PointerEventPass.Main;
        private final EmptyCoroutineContext context = EmptyCoroutineContext.INSTANCE;

        public PointerEventHandlerCoroutine(CancellableContinuationImpl cancellableContinuationImpl) {
            this.completion = cancellableContinuationImpl;
            this.$$delegate_0 = SuspendingPointerInputFilter.this;
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        public final Object awaitPointerEvent(PointerEventPass pointerEventPass, BaseContinuationImpl baseContinuationImpl) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt.intercepted(baseContinuationImpl));
            cancellableContinuationImpl.initCancellability();
            this.awaitPass = pointerEventPass;
            this.pointerAwaiter = cancellableContinuationImpl;
            return cancellableContinuationImpl.getResult();
        }

        public final void cancel(Throwable th) {
            CancellableContinuation cancellableContinuation = this.pointerAwaiter;
            if (cancellableContinuation != null) {
                ((CancellableContinuationImpl) cancellableContinuation).cancel(th);
            }
            this.pointerAwaiter = null;
        }

        @Override // kotlin.coroutines.Continuation
        public final CoroutineContext getContext() {
            return this.context;
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        public final PointerEvent getCurrentEvent() {
            return SuspendingPointerInputFilter.this.currentEvent;
        }

        @Override // androidx.compose.ui.unit.Density
        public final float getDensity() {
            return this.$$delegate_0.getDensity();
        }

        @Override // androidx.compose.ui.unit.Density
        public final float getFontScale() {
            return this.$$delegate_0.getFontScale();
        }

        @Override // androidx.compose.ui.input.pointer.AwaitPointerEventScope
        public final ViewConfiguration getViewConfiguration() {
            return SuspendingPointerInputFilter.this.getViewConfiguration();
        }

        public final void offerPointerEvent(PointerEvent pointerEvent, PointerEventPass pointerEventPass) {
            CancellableContinuation cancellableContinuation;
            if (pointerEventPass == this.awaitPass && (cancellableContinuation = this.pointerAwaiter) != null) {
                this.pointerAwaiter = null;
                ((CancellableContinuationImpl) cancellableContinuation).resumeWith(pointerEvent);
            }
        }

        @Override // kotlin.coroutines.Continuation
        public final void resumeWith(Object obj) {
            MutableVector mutableVector = SuspendingPointerInputFilter.this.pointerHandlers;
            SuspendingPointerInputFilter suspendingPointerInputFilter = SuspendingPointerInputFilter.this;
            synchronized (mutableVector) {
                suspendingPointerInputFilter.pointerHandlers.remove(this);
            }
            this.completion.resumeWith(obj);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: roundToPx-0680j_4 */
        public final int mo202roundToPx0680j_4(float f) {
            return this.$$delegate_0.mo202roundToPx0680j_4(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toDp-u2uoSUM */
        public final float mo203toDpu2uoSUM(float f) {
            return this.$$delegate_0.mo203toDpu2uoSUM(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toPx--R2X_6o */
        public final float mo205toPxR2X_6o(long j) {
            return this.$$delegate_0.mo205toPxR2X_6o(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toPx-0680j_4 */
        public final float mo206toPx0680j_4(float f) {
            return this.$$delegate_0.mo206toPx0680j_4(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toSize-XkaWNTQ */
        public final long mo207toSizeXkaWNTQ(long j) {
            return this.$$delegate_0.mo207toSizeXkaWNTQ(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toDp-u2uoSUM */
        public final float mo204toDpu2uoSUM(int i) {
            return this.$$delegate_0.mo204toDpu2uoSUM(i);
        }
    }

    public SuspendingPointerInputFilter(ViewConfiguration viewConfiguration, Density density) {
        Intrinsics.checkNotNullParameter(viewConfiguration, "viewConfiguration");
        Intrinsics.checkNotNullParameter(density, "density");
        this.viewConfiguration = viewConfiguration;
        this.$$delegate_0 = density;
        this.currentEvent = SuspendingPointerInputFilterKt.access$getEmptyPointerEvent$p();
        this.pointerHandlers = new MutableVector(new PointerEventHandlerCoroutine[16]);
        this.dispatchingPointerHandlers = new MutableVector(new PointerEventHandlerCoroutine[16]);
        this.boundsSize = 0L;
        this.coroutineScope = GlobalScope.INSTANCE;
    }

    private final void dispatchPointerEvent(PointerEvent pointerEvent, PointerEventPass pointerEventPass) {
        synchronized (this.pointerHandlers) {
            MutableVector mutableVector = this.dispatchingPointerHandlers;
            mutableVector.addAll(mutableVector.getSize(), this.pointerHandlers);
        }
        try {
            int ordinal = pointerEventPass.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                    }
                } else {
                    MutableVector mutableVector2 = this.dispatchingPointerHandlers;
                    int size = mutableVector2.getSize();
                    if (size > 0) {
                        int i = size - 1;
                        Object[] content = mutableVector2.getContent();
                        do {
                            ((PointerEventHandlerCoroutine) content[i]).offerPointerEvent(pointerEvent, pointerEventPass);
                            i--;
                        } while (i >= 0);
                    }
                }
            }
            MutableVector mutableVector3 = this.dispatchingPointerHandlers;
            int size2 = mutableVector3.getSize();
            if (size2 > 0) {
                Object[] content2 = mutableVector3.getContent();
                int i2 = 0;
                do {
                    ((PointerEventHandlerCoroutine) content2[i2]).offerPointerEvent(pointerEvent, pointerEventPass);
                    i2++;
                } while (i2 < size2);
            }
        } finally {
            this.dispatchingPointerHandlers.clear();
        }
    }

    public final Object awaitPointerEventScope(Function2 function2, Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        final PointerEventHandlerCoroutine pointerEventHandlerCoroutine = new PointerEventHandlerCoroutine(cancellableContinuationImpl);
        synchronized (this.pointerHandlers) {
            this.pointerHandlers.add(pointerEventHandlerCoroutine);
            new SafeContinuation(IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(pointerEventHandlerCoroutine, pointerEventHandlerCoroutine, function2))).resumeWith(Unit.INSTANCE);
        }
        cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: androidx.compose.ui.input.pointer.SuspendingPointerInputFilter$awaitPointerEventScope$2$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SuspendingPointerInputFilter.PointerEventHandlerCoroutine.this.cancel((Throwable) obj);
                return Unit.INSTANCE;
            }
        });
        return cancellableContinuationImpl.getResult();
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.$$delegate_0.getDensity();
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getFontScale() {
        return this.$$delegate_0.getFontScale();
    }

    public final ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    public final void onCancel() {
        boolean z;
        long j;
        PointerEvent pointerEvent = this.lastPointerEvent;
        if (pointerEvent == null) {
            return;
        }
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        int i = 0;
        while (true) {
            z = true;
            if (i >= size) {
                break;
            } else if (!(true ^ ((PointerInputChange) changes.get(i)).getPressed())) {
                z = false;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            return;
        }
        List changes2 = pointerEvent.getChanges();
        ArrayList arrayList = new ArrayList(changes2.size());
        int size2 = changes2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            PointerInputChange pointerInputChange = (PointerInputChange) changes2.get(i2);
            long m189getIdJ3iCeTQ = pointerInputChange.m189getIdJ3iCeTQ();
            long m190getPositionF1C5BW0 = pointerInputChange.m190getPositionF1C5BW0();
            long uptimeMillis = pointerInputChange.getUptimeMillis();
            float pressure = pointerInputChange.getPressure();
            long m190getPositionF1C5BW02 = pointerInputChange.m190getPositionF1C5BW0();
            long uptimeMillis2 = pointerInputChange.getUptimeMillis();
            boolean pressed = pointerInputChange.getPressed();
            boolean pressed2 = pointerInputChange.getPressed();
            j = Offset.Zero;
            arrayList.add(new PointerInputChange(m189getIdJ3iCeTQ, uptimeMillis, m190getPositionF1C5BW0, false, pressure, uptimeMillis2, m190getPositionF1C5BW02, pressed, pressed2, 1, j));
        }
        PointerEvent pointerEvent2 = new PointerEvent(arrayList);
        this.currentEvent = pointerEvent2;
        dispatchPointerEvent(pointerEvent2, PointerEventPass.Initial);
        dispatchPointerEvent(pointerEvent2, PointerEventPass.Main);
        dispatchPointerEvent(pointerEvent2, PointerEventPass.Final);
        this.lastPointerEvent = null;
    }

    /* renamed from: onPointerEvent-H0pRuoY  reason: not valid java name */
    public final void m201onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        this.boundsSize = j;
        if (pointerEventPass == PointerEventPass.Initial) {
            this.currentEvent = pointerEvent;
        }
        dispatchPointerEvent(pointerEvent, pointerEventPass);
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i < size) {
                if (!PointerEventKt.changedToUpIgnoreConsumed((PointerInputChange) changes.get(i))) {
                    break;
                }
                i++;
            } else {
                z = true;
                break;
            }
        }
        if (!(!z)) {
            pointerEvent = null;
        }
        this.lastPointerEvent = pointerEvent;
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx-0680j_4  reason: not valid java name */
    public final int mo202roundToPx0680j_4(float f) {
        return this.$$delegate_0.mo202roundToPx0680j_4(f);
    }

    public final void setCoroutineScope(CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<set-?>");
        this.coroutineScope = coroutineScope;
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM  reason: not valid java name */
    public final float mo203toDpu2uoSUM(float f) {
        return this.$$delegate_0.mo203toDpu2uoSUM(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx--R2X_6o  reason: not valid java name */
    public final float mo205toPxR2X_6o(long j) {
        return this.$$delegate_0.mo205toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx-0680j_4  reason: not valid java name */
    public final float mo206toPx0680j_4(float f) {
        return this.$$delegate_0.mo206toPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSize-XkaWNTQ  reason: not valid java name */
    public final long mo207toSizeXkaWNTQ(long j) {
        return this.$$delegate_0.mo207toSizeXkaWNTQ(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM  reason: not valid java name */
    public final float mo204toDpu2uoSUM(int i) {
        return this.$$delegate_0.mo204toDpu2uoSUM(i);
    }
}
