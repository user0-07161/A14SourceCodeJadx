package androidx.compose.ui.platform;

import android.content.Context;
import android.os.IBinder;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.core.view.ViewKt;
import androidx.customview.poolingcontainer.PoolingContainer;
import androidx.customview.poolingcontainer.PoolingContainerListener;
import com.android.egg.R;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class AbstractComposeView extends ViewGroup {
    private WeakReference cachedViewTreeCompositionContext;
    private Composition composition;
    private boolean creatingComposition;
    private boolean isTransitionGroupSet;
    private CompositionContext parentContext;
    private IBinder previousAttachedWindowToken;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractComposeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        setClipChildren(false);
        setClipToPadding(false);
        int i2 = ViewCompositionStrategy.$r8$clinit;
        addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$listener$1
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View v) {
                boolean z;
                Boolean bool;
                Intrinsics.checkNotNullParameter(v, "v");
                AbstractComposeView abstractComposeView = AbstractComposeView.this;
                Intrinsics.checkNotNullParameter(abstractComposeView, "<this>");
                Iterator it = ViewKt.getAncestors(abstractComposeView).iterator();
                while (true) {
                    z = false;
                    if (!it.hasNext()) {
                        break;
                    }
                    ViewParent viewParent = (ViewParent) it.next();
                    if (viewParent instanceof View) {
                        View view = (View) viewParent;
                        Intrinsics.checkNotNullParameter(view, "<this>");
                        Object tag = view.getTag(R.id.is_pooling_container_tag);
                        if (tag instanceof Boolean) {
                            bool = (Boolean) tag;
                        } else {
                            bool = null;
                        }
                        if (bool != null) {
                            z = bool.booleanValue();
                        }
                        if (z) {
                            z = true;
                            break;
                        }
                    }
                }
                if (!z) {
                    AbstractComposeView.this.disposeComposition();
                }
            }
        });
        PoolingContainer.addPoolingContainerListener(this, new PoolingContainerListener() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$poolingContainerListener$1
            @Override // androidx.customview.poolingcontainer.PoolingContainerListener
            public final void onRelease() {
                AbstractComposeView.this.disposeComposition();
            }
        });
    }

    private final void checkAddView() {
        if (this.creatingComposition) {
            return;
        }
        throw new UnsupportedOperationException("Cannot add views to " + getClass().getSimpleName() + "; only Compose content is supported");
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [kotlin.jvm.internal.Lambda, androidx.compose.ui.platform.AbstractComposeView$ensureCompositionCreated$1] */
    private final void ensureCompositionCreated() {
        if (this.composition == null) {
            try {
                this.creatingComposition = true;
                this.composition = Wrapper_androidKt.setContent(this, resolveParentCompositionContext(), ComposableLambdaKt.composableLambdaInstance(-656146368, new Function2() { // from class: androidx.compose.ui.platform.AbstractComposeView$ensureCompositionCreated$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        Composer composer = (Composer) obj;
                        if ((((Number) obj2).intValue() & 11) == 2) {
                            ComposerImpl composerImpl = (ComposerImpl) composer;
                            if (composerImpl.getSkipping()) {
                                composerImpl.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        int i = ComposerKt.$r8$clinit;
                        AbstractComposeView.this.Content(composer, 8);
                        return Unit.INSTANCE;
                    }
                }, true));
            } finally {
                this.creatingComposition = false;
            }
        }
    }

    private static boolean isAlive(CompositionContext compositionContext) {
        if ((compositionContext instanceof Recomposer) && ((Recomposer.State) ((Recomposer) compositionContext).getCurrentState().getValue()).compareTo(Recomposer.State.ShuttingDown) <= 0) {
            return false;
        }
        return true;
    }

    private final CompositionContext resolveParentCompositionContext() {
        CompositionContext compositionContext;
        CompositionContext compositionContext2 = this.parentContext;
        if (compositionContext2 == null) {
            compositionContext2 = WindowRecomposer_androidKt.getCompositionContext(this);
            if (compositionContext2 == null) {
                for (ViewParent parent = getParent(); compositionContext2 == null && (parent instanceof View); parent = parent.getParent()) {
                    compositionContext2 = WindowRecomposer_androidKt.getCompositionContext((View) parent);
                }
            }
            CompositionContext compositionContext3 = null;
            if (compositionContext2 != null) {
                if (isAlive(compositionContext2)) {
                    compositionContext = compositionContext2;
                } else {
                    compositionContext = null;
                }
                if (compositionContext != null) {
                    this.cachedViewTreeCompositionContext = new WeakReference(compositionContext);
                }
            } else {
                compositionContext2 = null;
            }
            if (compositionContext2 == null) {
                WeakReference weakReference = this.cachedViewTreeCompositionContext;
                compositionContext2 = (weakReference == null || (compositionContext2 = (CompositionContext) weakReference.get()) == null || !isAlive(compositionContext2)) ? null : null;
                if (compositionContext2 == null) {
                    if (isAttachedToWindow()) {
                        ViewParent parent2 = getParent();
                        View view = this;
                        while (parent2 instanceof View) {
                            View view2 = (View) parent2;
                            if (view2.getId() == 16908290) {
                                break;
                            }
                            view = view2;
                            parent2 = view2.getParent();
                        }
                        CompositionContext compositionContext4 = WindowRecomposer_androidKt.getCompositionContext(view);
                        if (compositionContext4 == null) {
                            compositionContext2 = WindowRecomposerPolicy.createAndInstallWindowRecomposer$ui_release(view);
                        } else if (compositionContext4 instanceof Recomposer) {
                            compositionContext2 = (Recomposer) compositionContext4;
                        } else {
                            throw new IllegalStateException("root viewTreeParentCompositionContext is not a Recomposer".toString());
                        }
                        if (isAlive(compositionContext2)) {
                            compositionContext3 = compositionContext2;
                        }
                        if (compositionContext3 != null) {
                            this.cachedViewTreeCompositionContext = new WeakReference(compositionContext3);
                        }
                    } else {
                        throw new IllegalStateException(("Cannot locate windowRecomposer; View " + this + " is not attached to a window").toString());
                    }
                }
            }
        }
        return compositionContext2;
    }

    public abstract void Content(Composer composer, int i);

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        checkAddView();
        super.addView(view);
    }

    @Override // android.view.ViewGroup
    protected final boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams) {
        checkAddView();
        return super.addViewInLayout(view, i, layoutParams);
    }

    public final void createComposition() {
        boolean z;
        if (this.parentContext == null && !isAttachedToWindow()) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            ensureCompositionCreated();
            return;
        }
        throw new IllegalStateException("createComposition requires either a parent reference or the View to be attachedto a window. Attach the View or call setParentCompositionReference.".toString());
    }

    public final void disposeComposition() {
        Composition composition = this.composition;
        if (composition != null) {
            composition.dispose();
        }
        this.composition = null;
        requestLayout();
    }

    protected abstract boolean getShouldCreateCompositionOnAttachedToWindow();

    @Override // android.view.ViewGroup
    public final boolean isTransitionGroup() {
        if (this.isTransitionGroupSet && !super.isTransitionGroup()) {
            return false;
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        IBinder windowToken = getWindowToken();
        if (this.previousAttachedWindowToken != windowToken) {
            this.previousAttachedWindowToken = windowToken;
            this.cachedViewTreeCompositionContext = null;
        }
        if (getShouldCreateCompositionOnAttachedToWindow()) {
            ensureCompositionCreated();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.layout(getPaddingLeft(), getPaddingTop(), (i3 - i) - getPaddingRight(), (i4 - i2) - getPaddingBottom());
        }
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        ensureCompositionCreated();
        View childAt = getChildAt(0);
        if (childAt == null) {
            super.onMeasure(i, i2);
            return;
        }
        childAt.measure(View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight()), View.MeasureSpec.getMode(i)), View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom()), View.MeasureSpec.getMode(i2)));
        setMeasuredDimension(getPaddingRight() + getPaddingLeft() + childAt.getMeasuredWidth(), getPaddingBottom() + getPaddingTop() + childAt.getMeasuredHeight());
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.setLayoutDirection(i);
        }
    }

    public final void setParentCompositionContext(CompositionContext compositionContext) {
        if (this.parentContext != compositionContext) {
            this.parentContext = compositionContext;
            if (compositionContext != null) {
                this.cachedViewTreeCompositionContext = null;
            }
            Composition composition = this.composition;
            if (composition != null) {
                ((WrappedComposition) composition).dispose();
                this.composition = null;
                if (isAttachedToWindow()) {
                    ensureCompositionCreated();
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    public final void setTransitionGroup(boolean z) {
        super.setTransitionGroup(z);
        this.isTransitionGroupSet = true;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i) {
        checkAddView();
        super.addView(view, i);
    }

    @Override // android.view.ViewGroup
    protected final boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        checkAddView();
        return super.addViewInLayout(view, i, layoutParams, z);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, int i2) {
        checkAddView();
        super.addView(view, i, i2);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        checkAddView();
        super.addView(view, layoutParams);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        checkAddView();
        super.addView(view, i, layoutParams);
    }
}
