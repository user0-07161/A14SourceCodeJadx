package androidx.compose.ui.node;

import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class OwnerSnapshotObserver {
    private final SnapshotStateObserver observer;
    private final Function1 onCommitAffectingLookaheadMeasure = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLookaheadMeasure$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
            if (layoutNode.isAttached()) {
                layoutNode.requestLookaheadRemeasure$ui_release(false);
            }
            return Unit.INSTANCE;
        }
    };
    private final Function1 onCommitAffectingMeasure = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingMeasure$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
            if (layoutNode.isAttached()) {
                layoutNode.requestRemeasure$ui_release(false);
            }
            return Unit.INSTANCE;
        }
    };
    private final Function1 onCommitAffectingLayout = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLayout$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
            if (layoutNode.isAttached()) {
                layoutNode.requestRelayout$ui_release(false);
            }
            return Unit.INSTANCE;
        }
    };
    private final Function1 onCommitAffectingLayoutModifier = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLayoutModifier$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
            if (layoutNode.isAttached()) {
                layoutNode.requestRelayout$ui_release(false);
            }
            return Unit.INSTANCE;
        }
    };
    private final Function1 onCommitAffectingLayoutModifierInLookahead = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLayoutModifierInLookahead$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
            if (layoutNode.isAttached()) {
                layoutNode.requestLookaheadRelayout$ui_release(false);
            }
            return Unit.INSTANCE;
        }
    };
    private final Function1 onCommitAffectingLookaheadLayout = new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$onCommitAffectingLookaheadLayout$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            LayoutNode layoutNode = (LayoutNode) obj;
            Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
            if (layoutNode.isAttached()) {
                layoutNode.requestLookaheadRelayout$ui_release(false);
            }
            return Unit.INSTANCE;
        }
    };

    public OwnerSnapshotObserver(Function1 function1) {
        this.observer = new SnapshotStateObserver(function1);
    }

    public final void clearInvalidObservations$ui_release() {
        this.observer.clearIf(new Function1() { // from class: androidx.compose.ui.node.OwnerSnapshotObserver$clearInvalidObservations$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.valueOf(!((OwnerScope) it).isValidOwnerScope());
            }
        });
    }

    public final void observeLayoutModifierSnapshotReads$ui_release(LayoutNode node, boolean z, Function0 function0) {
        Intrinsics.checkNotNullParameter(node, "node");
        observeReads$ui_release(node, this.onCommitAffectingLayoutModifier, function0);
    }

    public final void observeLayoutSnapshotReads$ui_release(LayoutNode node, boolean z, Function0 function0) {
        Intrinsics.checkNotNullParameter(node, "node");
        observeReads$ui_release(node, this.onCommitAffectingLayout, function0);
    }

    public final void observeMeasureSnapshotReads$ui_release(LayoutNode node, boolean z, Function0 function0) {
        Intrinsics.checkNotNullParameter(node, "node");
        observeReads$ui_release(node, this.onCommitAffectingMeasure, function0);
    }

    public final void observeReads$ui_release(OwnerScope target, Function1 onChanged, Function0 function0) {
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(onChanged, "onChanged");
        this.observer.observeReads(target, onChanged, function0);
    }

    public final void startObserving$ui_release() {
        this.observer.start();
    }

    public final void stopObserving$ui_release() {
        SnapshotStateObserver snapshotStateObserver = this.observer;
        snapshotStateObserver.stop();
        snapshotStateObserver.clear();
    }
}
