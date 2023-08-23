package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class DefaultItemAnimator extends SimpleItemAnimator {
    private static TimeInterpolator sDefaultInterpolator;
    private ArrayList mPendingRemovals = new ArrayList();
    private ArrayList mPendingAdditions = new ArrayList();
    private ArrayList mPendingMoves = new ArrayList();
    private ArrayList mPendingChanges = new ArrayList();
    ArrayList mAdditionsList = new ArrayList();
    ArrayList mMovesList = new ArrayList();
    ArrayList mChangesList = new ArrayList();
    ArrayList mAddAnimations = new ArrayList();
    ArrayList mMoveAnimations = new ArrayList();
    ArrayList mRemoveAnimations = new ArrayList();
    ArrayList mChangeAnimations = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* renamed from: androidx.recyclerview.widget.DefaultItemAnimator$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass4 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId = 1;
        final /* synthetic */ ViewPropertyAnimator val$animation;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;
        final /* synthetic */ View val$view;

        public AnonymousClass4(RecyclerView.ViewHolder viewHolder, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.val$holder = viewHolder;
            this.val$animation = viewPropertyAnimator;
            this.val$view = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            switch (this.$r8$classId) {
                case 1:
                    this.val$view.setAlpha(1.0f);
                    return;
                default:
                    super.onAnimationCancel(animator);
                    return;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    this.val$animation.setListener(null);
                    this.val$view.setAlpha(1.0f);
                    DefaultItemAnimator.this.dispatchAnimationFinished(this.val$holder);
                    DefaultItemAnimator.this.mRemoveAnimations.remove(this.val$holder);
                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                    return;
                default:
                    this.val$animation.setListener(null);
                    DefaultItemAnimator.this.dispatchAnimationFinished(this.val$holder);
                    DefaultItemAnimator.this.mAddAnimations.remove(this.val$holder);
                    DefaultItemAnimator.this.dispatchFinishedWhenDone();
                    return;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    DefaultItemAnimator.this.getClass();
                    return;
                default:
                    DefaultItemAnimator.this.getClass();
                    return;
            }
        }

        public AnonymousClass4(RecyclerView.ViewHolder viewHolder, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.val$holder = viewHolder;
            this.val$view = view;
            this.val$animation = viewPropertyAnimator;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class ChangeInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder newHolder;
        public RecyclerView.ViewHolder oldHolder;
        public int toX;
        public int toY;

        ChangeInfo(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
            this.oldHolder = viewHolder;
            this.newHolder = viewHolder2;
            this.fromX = i;
            this.fromY = i2;
            this.toX = i3;
            this.toY = i4;
        }

        public final String toString() {
            return "ChangeInfo{oldHolder=" + this.oldHolder + ", newHolder=" + this.newHolder + ", fromX=" + this.fromX + ", fromY=" + this.fromY + ", toX=" + this.toX + ", toY=" + this.toY + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class MoveInfo {
        public int fromX;
        public int fromY;
        public RecyclerView.ViewHolder holder;
        public int toX;
        public int toY;

        MoveInfo(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            this.holder = viewHolder;
            this.fromX = i;
            this.fromY = i2;
            this.toX = i3;
            this.toY = i4;
        }
    }

    static void cancelAll(List list) {
        ArrayList arrayList = (ArrayList) list;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size >= 0) {
                ((RecyclerView.ViewHolder) arrayList.get(size)).itemView.animate().cancel();
            } else {
                return;
            }
        }
    }

    private void endChangeAnimation(RecyclerView.ViewHolder viewHolder, List list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            ChangeInfo changeInfo = (ChangeInfo) list.get(size);
            if (endChangeAnimationIfNecessary(changeInfo, viewHolder) && changeInfo.oldHolder == null && changeInfo.newHolder == null) {
                list.remove(changeInfo);
            }
        }
    }

    private boolean endChangeAnimationIfNecessary(ChangeInfo changeInfo, RecyclerView.ViewHolder viewHolder) {
        if (changeInfo.newHolder == viewHolder) {
            changeInfo.newHolder = null;
        } else if (changeInfo.oldHolder == viewHolder) {
            changeInfo.oldHolder = null;
        } else {
            return false;
        }
        viewHolder.itemView.setAlpha(1.0f);
        viewHolder.itemView.setTranslationX(0.0f);
        viewHolder.itemView.setTranslationY(0.0f);
        dispatchAnimationFinished(viewHolder);
        return true;
    }

    private void resetAnimation(RecyclerView.ViewHolder viewHolder) {
        if (sDefaultInterpolator == null) {
            sDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(viewHolder);
    }

    public final void animateAdd(RecyclerView.ViewHolder viewHolder) {
        resetAnimation(viewHolder);
        viewHolder.itemView.setAlpha(0.0f);
        this.mPendingAdditions.add(viewHolder);
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator
    public final boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        if (viewHolder == viewHolder2) {
            return animateMove(viewHolder, i, i2, i3, i4);
        }
        float translationX = viewHolder.itemView.getTranslationX();
        float translationY = viewHolder.itemView.getTranslationY();
        float alpha = viewHolder.itemView.getAlpha();
        resetAnimation(viewHolder);
        viewHolder.itemView.setTranslationX(translationX);
        viewHolder.itemView.setTranslationY(translationY);
        viewHolder.itemView.setAlpha(alpha);
        resetAnimation(viewHolder2);
        viewHolder2.itemView.setTranslationX(-((int) ((i3 - i) - translationX)));
        viewHolder2.itemView.setTranslationY(-((int) ((i4 - i2) - translationY)));
        viewHolder2.itemView.setAlpha(0.0f);
        this.mPendingChanges.add(new ChangeInfo(viewHolder, viewHolder2, i, i2, i3, i4));
        return true;
    }

    public final boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        View view = viewHolder.itemView;
        int translationX = i + ((int) view.getTranslationX());
        int translationY = i2 + ((int) viewHolder.itemView.getTranslationY());
        resetAnimation(viewHolder);
        int i5 = i3 - translationX;
        int i6 = i4 - translationY;
        if (i5 == 0 && i6 == 0) {
            dispatchAnimationFinished(viewHolder);
            return false;
        }
        if (i5 != 0) {
            view.setTranslationX(-i5);
        }
        if (i6 != 0) {
            view.setTranslationY(-i6);
        }
        this.mPendingMoves.add(new MoveInfo(viewHolder, translationX, translationY, i3, i4));
        return true;
    }

    public final void animateRemove(RecyclerView.ViewHolder viewHolder) {
        resetAnimation(viewHolder);
        this.mPendingRemovals.add(viewHolder);
    }

    final void dispatchFinishedWhenDone() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    public final void endAnimation(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        view.animate().cancel();
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else if (((MoveInfo) this.mPendingMoves.get(size)).holder == viewHolder) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                dispatchAnimationFinished(viewHolder);
                this.mPendingMoves.remove(size);
            }
        }
        endChangeAnimation(viewHolder, this.mPendingChanges);
        if (this.mPendingRemovals.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        if (this.mPendingAdditions.remove(viewHolder)) {
            view.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
        }
        for (int size2 = this.mChangesList.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList = (ArrayList) this.mChangesList.get(size2);
            endChangeAnimation(viewHolder, arrayList);
            if (arrayList.isEmpty()) {
                this.mChangesList.remove(size2);
            }
        }
        for (int size3 = this.mMovesList.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList2 = (ArrayList) this.mMovesList.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                } else if (((MoveInfo) arrayList2.get(size4)).holder == viewHolder) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    dispatchAnimationFinished(viewHolder);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.mMovesList.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.mAdditionsList.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList3 = (ArrayList) this.mAdditionsList.get(size5);
            if (arrayList3.remove(viewHolder)) {
                view.setAlpha(1.0f);
                dispatchAnimationFinished(viewHolder);
                if (arrayList3.isEmpty()) {
                    this.mAdditionsList.remove(size5);
                }
            }
        }
        this.mRemoveAnimations.remove(viewHolder);
        this.mAddAnimations.remove(viewHolder);
        this.mChangeAnimations.remove(viewHolder);
        this.mMoveAnimations.remove(viewHolder);
        dispatchFinishedWhenDone();
    }

    public final void endAnimations() {
        int size = this.mPendingMoves.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            MoveInfo moveInfo = (MoveInfo) this.mPendingMoves.get(size);
            View view = moveInfo.holder.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            dispatchAnimationFinished(moveInfo.holder);
            this.mPendingMoves.remove(size);
        }
        int size2 = this.mPendingRemovals.size();
        while (true) {
            size2--;
            if (size2 < 0) {
                break;
            }
            dispatchAnimationFinished((RecyclerView.ViewHolder) this.mPendingRemovals.get(size2));
            this.mPendingRemovals.remove(size2);
        }
        int size3 = this.mPendingAdditions.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) this.mPendingAdditions.get(size3);
            viewHolder.itemView.setAlpha(1.0f);
            dispatchAnimationFinished(viewHolder);
            this.mPendingAdditions.remove(size3);
        }
        int size4 = this.mPendingChanges.size();
        while (true) {
            size4--;
            if (size4 < 0) {
                break;
            }
            ChangeInfo changeInfo = (ChangeInfo) this.mPendingChanges.get(size4);
            RecyclerView.ViewHolder viewHolder2 = changeInfo.oldHolder;
            if (viewHolder2 != null) {
                endChangeAnimationIfNecessary(changeInfo, viewHolder2);
            }
            RecyclerView.ViewHolder viewHolder3 = changeInfo.newHolder;
            if (viewHolder3 != null) {
                endChangeAnimationIfNecessary(changeInfo, viewHolder3);
            }
        }
        this.mPendingChanges.clear();
        if (!isRunning()) {
            return;
        }
        int size5 = this.mMovesList.size();
        while (true) {
            size5--;
            if (size5 < 0) {
                break;
            }
            ArrayList arrayList = (ArrayList) this.mMovesList.get(size5);
            int size6 = arrayList.size();
            while (true) {
                size6--;
                if (size6 >= 0) {
                    MoveInfo moveInfo2 = (MoveInfo) arrayList.get(size6);
                    View view2 = moveInfo2.holder.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    dispatchAnimationFinished(moveInfo2.holder);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.mMovesList.remove(arrayList);
                    }
                }
            }
        }
        int size7 = this.mAdditionsList.size();
        while (true) {
            size7--;
            if (size7 < 0) {
                break;
            }
            ArrayList arrayList2 = (ArrayList) this.mAdditionsList.get(size7);
            int size8 = arrayList2.size();
            while (true) {
                size8--;
                if (size8 >= 0) {
                    RecyclerView.ViewHolder viewHolder4 = (RecyclerView.ViewHolder) arrayList2.get(size8);
                    viewHolder4.itemView.setAlpha(1.0f);
                    dispatchAnimationFinished(viewHolder4);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.mAdditionsList.remove(arrayList2);
                    }
                }
            }
        }
        int size9 = this.mChangesList.size();
        while (true) {
            size9--;
            if (size9 >= 0) {
                ArrayList arrayList3 = (ArrayList) this.mChangesList.get(size9);
                int size10 = arrayList3.size();
                while (true) {
                    size10--;
                    if (size10 >= 0) {
                        ChangeInfo changeInfo2 = (ChangeInfo) arrayList3.get(size10);
                        RecyclerView.ViewHolder viewHolder5 = changeInfo2.oldHolder;
                        if (viewHolder5 != null) {
                            endChangeAnimationIfNecessary(changeInfo2, viewHolder5);
                        }
                        RecyclerView.ViewHolder viewHolder6 = changeInfo2.newHolder;
                        if (viewHolder6 != null) {
                            endChangeAnimationIfNecessary(changeInfo2, viewHolder6);
                        }
                        if (arrayList3.isEmpty()) {
                            this.mChangesList.remove(arrayList3);
                        }
                    }
                }
            } else {
                cancelAll(this.mRemoveAnimations);
                cancelAll(this.mMoveAnimations);
                cancelAll(this.mAddAnimations);
                cancelAll(this.mChangeAnimations);
                dispatchAnimationsFinished();
                return;
            }
        }
    }

    public final boolean isRunning() {
        if (this.mPendingAdditions.isEmpty() && this.mPendingChanges.isEmpty() && this.mPendingMoves.isEmpty() && this.mPendingRemovals.isEmpty() && this.mMoveAnimations.isEmpty() && this.mRemoveAnimations.isEmpty() && this.mAddAnimations.isEmpty() && this.mChangeAnimations.isEmpty() && this.mMovesList.isEmpty() && this.mAdditionsList.isEmpty() && this.mChangesList.isEmpty()) {
            return false;
        }
        return true;
    }

    public final void runPendingAnimations() {
        long j;
        long j2;
        boolean z = !this.mPendingRemovals.isEmpty();
        boolean z2 = !this.mPendingMoves.isEmpty();
        boolean z3 = !this.mPendingChanges.isEmpty();
        boolean z4 = !this.mPendingAdditions.isEmpty();
        if (!z && !z2 && !z4 && !z3) {
            return;
        }
        Iterator it = this.mPendingRemovals.iterator();
        while (it.hasNext()) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) it.next();
            View view = viewHolder.itemView;
            ViewPropertyAnimator animate = view.animate();
            this.mRemoveAnimations.add(viewHolder);
            animate.setDuration(getRemoveDuration()).alpha(0.0f).setListener(new AnonymousClass4(viewHolder, animate, view)).start();
        }
        this.mPendingRemovals.clear();
        if (z2) {
            final ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.mPendingMoves);
            this.mMovesList.add(arrayList);
            this.mPendingMoves.clear();
            Runnable runnable = new Runnable(this) { // from class: androidx.recyclerview.widget.DefaultItemAnimator.1
                final /* synthetic */ DefaultItemAnimator this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    final View view2;
                    View view3;
                    switch (r3) {
                        case 0:
                            Iterator it2 = arrayList.iterator();
                            while (it2.hasNext()) {
                                MoveInfo moveInfo = (MoveInfo) it2.next();
                                final DefaultItemAnimator defaultItemAnimator = this.this$0;
                                final RecyclerView.ViewHolder viewHolder2 = moveInfo.holder;
                                int i = moveInfo.fromX;
                                int i2 = moveInfo.fromY;
                                int i3 = moveInfo.toX;
                                int i4 = moveInfo.toY;
                                defaultItemAnimator.getClass();
                                final View view4 = viewHolder2.itemView;
                                final int i5 = i3 - i;
                                final int i6 = i4 - i2;
                                if (i5 != 0) {
                                    view4.animate().translationX(0.0f);
                                }
                                if (i6 != 0) {
                                    view4.animate().translationY(0.0f);
                                }
                                final ViewPropertyAnimator animate2 = view4.animate();
                                defaultItemAnimator.mMoveAnimations.add(viewHolder2);
                                animate2.setDuration(defaultItemAnimator.getMoveDuration()).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.6
                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationCancel(Animator animator) {
                                        if (i5 != 0) {
                                            view4.setTranslationX(0.0f);
                                        }
                                        if (i6 != 0) {
                                            view4.setTranslationY(0.0f);
                                        }
                                    }

                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationEnd(Animator animator) {
                                        animate2.setListener(null);
                                        DefaultItemAnimator.this.dispatchAnimationFinished(viewHolder2);
                                        DefaultItemAnimator.this.mMoveAnimations.remove(viewHolder2);
                                        DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                    }

                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationStart(Animator animator) {
                                        DefaultItemAnimator.this.getClass();
                                    }
                                }).start();
                            }
                            arrayList.clear();
                            this.this$0.mMovesList.remove(arrayList);
                            return;
                        case 1:
                            Iterator it3 = arrayList.iterator();
                            while (it3.hasNext()) {
                                final ChangeInfo changeInfo = (ChangeInfo) it3.next();
                                final DefaultItemAnimator defaultItemAnimator2 = this.this$0;
                                defaultItemAnimator2.getClass();
                                RecyclerView.ViewHolder viewHolder3 = changeInfo.oldHolder;
                                if (viewHolder3 == null) {
                                    view2 = null;
                                } else {
                                    view2 = viewHolder3.itemView;
                                }
                                RecyclerView.ViewHolder viewHolder4 = changeInfo.newHolder;
                                if (viewHolder4 != null) {
                                    view3 = viewHolder4.itemView;
                                } else {
                                    view3 = null;
                                }
                                if (view2 != null) {
                                    final ViewPropertyAnimator duration = view2.animate().setDuration(defaultItemAnimator2.getChangeDuration());
                                    defaultItemAnimator2.mChangeAnimations.add(changeInfo.oldHolder);
                                    duration.translationX(changeInfo.toX - changeInfo.fromX);
                                    duration.translationY(changeInfo.toY - changeInfo.fromY);
                                    duration.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationEnd(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    duration.setListener(null);
                                                    view2.setAlpha(1.0f);
                                                    view2.setTranslationX(0.0f);
                                                    view2.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.oldHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.oldHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                                default:
                                                    duration.setListener(null);
                                                    view2.setAlpha(1.0f);
                                                    view2.setTranslationX(0.0f);
                                                    view2.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.newHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.newHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                            }
                                        }

                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationStart(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    DefaultItemAnimator defaultItemAnimator3 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder5 = changeInfo.oldHolder;
                                                    defaultItemAnimator3.getClass();
                                                    return;
                                                default:
                                                    DefaultItemAnimator defaultItemAnimator4 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder6 = changeInfo.newHolder;
                                                    defaultItemAnimator4.getClass();
                                                    return;
                                            }
                                        }
                                    }).start();
                                }
                                if (view3 != null) {
                                    final ViewPropertyAnimator animate3 = view3.animate();
                                    defaultItemAnimator2.mChangeAnimations.add(changeInfo.newHolder);
                                    final View view5 = view3;
                                    animate3.translationX(0.0f).translationY(0.0f).setDuration(defaultItemAnimator2.getChangeDuration()).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationEnd(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    animate3.setListener(null);
                                                    view5.setAlpha(1.0f);
                                                    view5.setTranslationX(0.0f);
                                                    view5.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.oldHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.oldHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                                default:
                                                    animate3.setListener(null);
                                                    view5.setAlpha(1.0f);
                                                    view5.setTranslationX(0.0f);
                                                    view5.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.newHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.newHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                            }
                                        }

                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationStart(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    DefaultItemAnimator defaultItemAnimator3 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder5 = changeInfo.oldHolder;
                                                    defaultItemAnimator3.getClass();
                                                    return;
                                                default:
                                                    DefaultItemAnimator defaultItemAnimator4 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder6 = changeInfo.newHolder;
                                                    defaultItemAnimator4.getClass();
                                                    return;
                                            }
                                        }
                                    }).start();
                                }
                            }
                            arrayList.clear();
                            this.this$0.mChangesList.remove(arrayList);
                            return;
                        default:
                            Iterator it4 = arrayList.iterator();
                            while (it4.hasNext()) {
                                RecyclerView.ViewHolder viewHolder5 = (RecyclerView.ViewHolder) it4.next();
                                DefaultItemAnimator defaultItemAnimator3 = this.this$0;
                                defaultItemAnimator3.getClass();
                                View view6 = viewHolder5.itemView;
                                ViewPropertyAnimator animate4 = view6.animate();
                                defaultItemAnimator3.mAddAnimations.add(viewHolder5);
                                animate4.alpha(1.0f).setDuration(defaultItemAnimator3.getAddDuration()).setListener(new AnonymousClass4(viewHolder5, view6, animate4)).start();
                            }
                            arrayList.clear();
                            this.this$0.mAdditionsList.remove(arrayList);
                            return;
                    }
                }
            };
            if (z) {
                ViewCompat.postOnAnimationDelayed(((MoveInfo) arrayList.get(0)).holder.itemView, runnable, getRemoveDuration());
            } else {
                runnable.run();
            }
        }
        if (z3) {
            final ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(this.mPendingChanges);
            this.mChangesList.add(arrayList2);
            this.mPendingChanges.clear();
            Runnable runnable2 = new Runnable(this) { // from class: androidx.recyclerview.widget.DefaultItemAnimator.1
                final /* synthetic */ DefaultItemAnimator this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    final View view2;
                    View view3;
                    switch (r3) {
                        case 0:
                            Iterator it2 = arrayList2.iterator();
                            while (it2.hasNext()) {
                                MoveInfo moveInfo = (MoveInfo) it2.next();
                                final DefaultItemAnimator defaultItemAnimator = this.this$0;
                                final RecyclerView.ViewHolder viewHolder2 = moveInfo.holder;
                                int i = moveInfo.fromX;
                                int i2 = moveInfo.fromY;
                                int i3 = moveInfo.toX;
                                int i4 = moveInfo.toY;
                                defaultItemAnimator.getClass();
                                final View view4 = viewHolder2.itemView;
                                final int i5 = i3 - i;
                                final int i6 = i4 - i2;
                                if (i5 != 0) {
                                    view4.animate().translationX(0.0f);
                                }
                                if (i6 != 0) {
                                    view4.animate().translationY(0.0f);
                                }
                                final ViewPropertyAnimator animate2 = view4.animate();
                                defaultItemAnimator.mMoveAnimations.add(viewHolder2);
                                animate2.setDuration(defaultItemAnimator.getMoveDuration()).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.6
                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationCancel(Animator animator) {
                                        if (i5 != 0) {
                                            view4.setTranslationX(0.0f);
                                        }
                                        if (i6 != 0) {
                                            view4.setTranslationY(0.0f);
                                        }
                                    }

                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationEnd(Animator animator) {
                                        animate2.setListener(null);
                                        DefaultItemAnimator.this.dispatchAnimationFinished(viewHolder2);
                                        DefaultItemAnimator.this.mMoveAnimations.remove(viewHolder2);
                                        DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                    }

                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationStart(Animator animator) {
                                        DefaultItemAnimator.this.getClass();
                                    }
                                }).start();
                            }
                            arrayList2.clear();
                            this.this$0.mMovesList.remove(arrayList2);
                            return;
                        case 1:
                            Iterator it3 = arrayList2.iterator();
                            while (it3.hasNext()) {
                                final ChangeInfo changeInfo = (ChangeInfo) it3.next();
                                final DefaultItemAnimator defaultItemAnimator2 = this.this$0;
                                defaultItemAnimator2.getClass();
                                RecyclerView.ViewHolder viewHolder3 = changeInfo.oldHolder;
                                if (viewHolder3 == null) {
                                    view2 = null;
                                } else {
                                    view2 = viewHolder3.itemView;
                                }
                                RecyclerView.ViewHolder viewHolder4 = changeInfo.newHolder;
                                if (viewHolder4 != null) {
                                    view3 = viewHolder4.itemView;
                                } else {
                                    view3 = null;
                                }
                                if (view2 != null) {
                                    final ViewPropertyAnimator duration = view2.animate().setDuration(defaultItemAnimator2.getChangeDuration());
                                    defaultItemAnimator2.mChangeAnimations.add(changeInfo.oldHolder);
                                    duration.translationX(changeInfo.toX - changeInfo.fromX);
                                    duration.translationY(changeInfo.toY - changeInfo.fromY);
                                    duration.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationEnd(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    duration.setListener(null);
                                                    view2.setAlpha(1.0f);
                                                    view2.setTranslationX(0.0f);
                                                    view2.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.oldHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.oldHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                                default:
                                                    duration.setListener(null);
                                                    view2.setAlpha(1.0f);
                                                    view2.setTranslationX(0.0f);
                                                    view2.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.newHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.newHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                            }
                                        }

                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationStart(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    DefaultItemAnimator defaultItemAnimator3 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder5 = changeInfo.oldHolder;
                                                    defaultItemAnimator3.getClass();
                                                    return;
                                                default:
                                                    DefaultItemAnimator defaultItemAnimator4 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder6 = changeInfo.newHolder;
                                                    defaultItemAnimator4.getClass();
                                                    return;
                                            }
                                        }
                                    }).start();
                                }
                                if (view3 != null) {
                                    final ViewPropertyAnimator animate3 = view3.animate();
                                    defaultItemAnimator2.mChangeAnimations.add(changeInfo.newHolder);
                                    final View view5 = view3;
                                    animate3.translationX(0.0f).translationY(0.0f).setDuration(defaultItemAnimator2.getChangeDuration()).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationEnd(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    animate3.setListener(null);
                                                    view5.setAlpha(1.0f);
                                                    view5.setTranslationX(0.0f);
                                                    view5.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.oldHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.oldHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                                default:
                                                    animate3.setListener(null);
                                                    view5.setAlpha(1.0f);
                                                    view5.setTranslationX(0.0f);
                                                    view5.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.newHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.newHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                            }
                                        }

                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationStart(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    DefaultItemAnimator defaultItemAnimator3 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder5 = changeInfo.oldHolder;
                                                    defaultItemAnimator3.getClass();
                                                    return;
                                                default:
                                                    DefaultItemAnimator defaultItemAnimator4 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder6 = changeInfo.newHolder;
                                                    defaultItemAnimator4.getClass();
                                                    return;
                                            }
                                        }
                                    }).start();
                                }
                            }
                            arrayList2.clear();
                            this.this$0.mChangesList.remove(arrayList2);
                            return;
                        default:
                            Iterator it4 = arrayList2.iterator();
                            while (it4.hasNext()) {
                                RecyclerView.ViewHolder viewHolder5 = (RecyclerView.ViewHolder) it4.next();
                                DefaultItemAnimator defaultItemAnimator3 = this.this$0;
                                defaultItemAnimator3.getClass();
                                View view6 = viewHolder5.itemView;
                                ViewPropertyAnimator animate4 = view6.animate();
                                defaultItemAnimator3.mAddAnimations.add(viewHolder5);
                                animate4.alpha(1.0f).setDuration(defaultItemAnimator3.getAddDuration()).setListener(new AnonymousClass4(viewHolder5, view6, animate4)).start();
                            }
                            arrayList2.clear();
                            this.this$0.mAdditionsList.remove(arrayList2);
                            return;
                    }
                }
            };
            if (z) {
                ViewCompat.postOnAnimationDelayed(((ChangeInfo) arrayList2.get(0)).oldHolder.itemView, runnable2, getRemoveDuration());
            } else {
                runnable2.run();
            }
        }
        if (z4) {
            final ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(this.mPendingAdditions);
            this.mAdditionsList.add(arrayList3);
            this.mPendingAdditions.clear();
            Runnable runnable3 = new Runnable(this) { // from class: androidx.recyclerview.widget.DefaultItemAnimator.1
                final /* synthetic */ DefaultItemAnimator this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    final View view2;
                    View view3;
                    switch (r3) {
                        case 0:
                            Iterator it2 = arrayList3.iterator();
                            while (it2.hasNext()) {
                                MoveInfo moveInfo = (MoveInfo) it2.next();
                                final DefaultItemAnimator defaultItemAnimator = this.this$0;
                                final RecyclerView.ViewHolder viewHolder2 = moveInfo.holder;
                                int i = moveInfo.fromX;
                                int i2 = moveInfo.fromY;
                                int i3 = moveInfo.toX;
                                int i4 = moveInfo.toY;
                                defaultItemAnimator.getClass();
                                final View view4 = viewHolder2.itemView;
                                final int i5 = i3 - i;
                                final int i6 = i4 - i2;
                                if (i5 != 0) {
                                    view4.animate().translationX(0.0f);
                                }
                                if (i6 != 0) {
                                    view4.animate().translationY(0.0f);
                                }
                                final ViewPropertyAnimator animate2 = view4.animate();
                                defaultItemAnimator.mMoveAnimations.add(viewHolder2);
                                animate2.setDuration(defaultItemAnimator.getMoveDuration()).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.6
                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationCancel(Animator animator) {
                                        if (i5 != 0) {
                                            view4.setTranslationX(0.0f);
                                        }
                                        if (i6 != 0) {
                                            view4.setTranslationY(0.0f);
                                        }
                                    }

                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationEnd(Animator animator) {
                                        animate2.setListener(null);
                                        DefaultItemAnimator.this.dispatchAnimationFinished(viewHolder2);
                                        DefaultItemAnimator.this.mMoveAnimations.remove(viewHolder2);
                                        DefaultItemAnimator.this.dispatchFinishedWhenDone();
                                    }

                                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                    public final void onAnimationStart(Animator animator) {
                                        DefaultItemAnimator.this.getClass();
                                    }
                                }).start();
                            }
                            arrayList3.clear();
                            this.this$0.mMovesList.remove(arrayList3);
                            return;
                        case 1:
                            Iterator it3 = arrayList3.iterator();
                            while (it3.hasNext()) {
                                final ChangeInfo changeInfo = (ChangeInfo) it3.next();
                                final DefaultItemAnimator defaultItemAnimator2 = this.this$0;
                                defaultItemAnimator2.getClass();
                                RecyclerView.ViewHolder viewHolder3 = changeInfo.oldHolder;
                                if (viewHolder3 == null) {
                                    view2 = null;
                                } else {
                                    view2 = viewHolder3.itemView;
                                }
                                RecyclerView.ViewHolder viewHolder4 = changeInfo.newHolder;
                                if (viewHolder4 != null) {
                                    view3 = viewHolder4.itemView;
                                } else {
                                    view3 = null;
                                }
                                if (view2 != null) {
                                    final ViewPropertyAnimator duration = view2.animate().setDuration(defaultItemAnimator2.getChangeDuration());
                                    defaultItemAnimator2.mChangeAnimations.add(changeInfo.oldHolder);
                                    duration.translationX(changeInfo.toX - changeInfo.fromX);
                                    duration.translationY(changeInfo.toY - changeInfo.fromY);
                                    duration.alpha(0.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationEnd(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    duration.setListener(null);
                                                    view2.setAlpha(1.0f);
                                                    view2.setTranslationX(0.0f);
                                                    view2.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.oldHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.oldHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                                default:
                                                    duration.setListener(null);
                                                    view2.setAlpha(1.0f);
                                                    view2.setTranslationX(0.0f);
                                                    view2.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.newHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.newHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                            }
                                        }

                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationStart(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    DefaultItemAnimator defaultItemAnimator3 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder5 = changeInfo.oldHolder;
                                                    defaultItemAnimator3.getClass();
                                                    return;
                                                default:
                                                    DefaultItemAnimator defaultItemAnimator4 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder6 = changeInfo.newHolder;
                                                    defaultItemAnimator4.getClass();
                                                    return;
                                            }
                                        }
                                    }).start();
                                }
                                if (view3 != null) {
                                    final ViewPropertyAnimator animate3 = view3.animate();
                                    defaultItemAnimator2.mChangeAnimations.add(changeInfo.newHolder);
                                    final View view5 = view3;
                                    animate3.translationX(0.0f).translationY(0.0f).setDuration(defaultItemAnimator2.getChangeDuration()).alpha(1.0f).setListener(new AnimatorListenerAdapter() { // from class: androidx.recyclerview.widget.DefaultItemAnimator.7
                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationEnd(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    animate3.setListener(null);
                                                    view5.setAlpha(1.0f);
                                                    view5.setTranslationX(0.0f);
                                                    view5.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.oldHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.oldHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                                default:
                                                    animate3.setListener(null);
                                                    view5.setAlpha(1.0f);
                                                    view5.setTranslationX(0.0f);
                                                    view5.setTranslationY(0.0f);
                                                    defaultItemAnimator2.dispatchAnimationFinished(changeInfo.newHolder);
                                                    defaultItemAnimator2.mChangeAnimations.remove(changeInfo.newHolder);
                                                    defaultItemAnimator2.dispatchFinishedWhenDone();
                                                    return;
                                            }
                                        }

                                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                        public final void onAnimationStart(Animator animator) {
                                            switch (r5) {
                                                case 0:
                                                    DefaultItemAnimator defaultItemAnimator3 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder5 = changeInfo.oldHolder;
                                                    defaultItemAnimator3.getClass();
                                                    return;
                                                default:
                                                    DefaultItemAnimator defaultItemAnimator4 = defaultItemAnimator2;
                                                    RecyclerView.ViewHolder viewHolder6 = changeInfo.newHolder;
                                                    defaultItemAnimator4.getClass();
                                                    return;
                                            }
                                        }
                                    }).start();
                                }
                            }
                            arrayList3.clear();
                            this.this$0.mChangesList.remove(arrayList3);
                            return;
                        default:
                            Iterator it4 = arrayList3.iterator();
                            while (it4.hasNext()) {
                                RecyclerView.ViewHolder viewHolder5 = (RecyclerView.ViewHolder) it4.next();
                                DefaultItemAnimator defaultItemAnimator3 = this.this$0;
                                defaultItemAnimator3.getClass();
                                View view6 = viewHolder5.itemView;
                                ViewPropertyAnimator animate4 = view6.animate();
                                defaultItemAnimator3.mAddAnimations.add(viewHolder5);
                                animate4.alpha(1.0f).setDuration(defaultItemAnimator3.getAddDuration()).setListener(new AnonymousClass4(viewHolder5, view6, animate4)).start();
                            }
                            arrayList3.clear();
                            this.this$0.mAdditionsList.remove(arrayList3);
                            return;
                    }
                }
            };
            if (!z && !z2 && !z3) {
                runnable3.run();
                return;
            }
            long j3 = 0;
            if (z) {
                j = getRemoveDuration();
            } else {
                j = 0;
            }
            if (z2) {
                j2 = getMoveDuration();
            } else {
                j2 = 0;
            }
            if (z3) {
                j3 = getChangeDuration();
            }
            ViewCompat.postOnAnimationDelayed(((RecyclerView.ViewHolder) arrayList3.get(0)).itemView, runnable3, Math.max(j2, j3) + j);
        }
    }
}
