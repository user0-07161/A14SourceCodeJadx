package androidx.compose.ui.platform;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.activity.OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.autofill.AndroidAutofill;
import androidx.compose.ui.autofill.AutofillCallback;
import androidx.compose.ui.autofill.AutofillTree;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.hapticfeedback.PlatformHapticFeedback;
import androidx.compose.ui.input.InputModeManagerImpl;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyInputModifierKt$onKeyEvent$$inlined$modifierElementOf$2;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.input.pointer.MotionEventAdapter;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerInputEvent;
import androidx.compose.ui.input.pointer.PointerInputEventData;
import androidx.compose.ui.input.pointer.PointerInputEventProcessor;
import androidx.compose.ui.input.pointer.PositionCalculator;
import androidx.compose.ui.input.rotary.RotaryInputModifierKt$onRotaryScrollEvent$$inlined$modifierElementOf$2;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.layout.RootMeasurePolicy;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.MeasureAndLayoutDelegate;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerSnapshotObserver;
import androidx.compose.ui.platform.ViewLayer;
import androidx.compose.ui.semantics.SemanticsModifierCore;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import androidx.compose.ui.text.font.FontFamilyResolver_androidKt;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.text.input.TextInputServiceAndroid;
import androidx.compose.ui.unit.AndroidDensity_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class AndroidComposeView extends ViewGroup implements Owner, PositionCalculator, DefaultLifecycleObserver {
    public static final Companion Companion = new Companion();
    private static Method getBooleanMethod;
    private static Class systemPropertiesClass;
    private AndroidViewsHandler _androidViewsHandler;
    private final AndroidAutofill _autofill;
    private final InputModeManagerImpl _inputModeManager;
    private final WindowInfoImpl _windowInfo;
    private final AndroidComposeViewAccessibilityDelegateCompat accessibilityDelegate;
    private final AndroidAccessibilityManager accessibilityManager;
    private final AutofillTree autofillTree;
    private final CanvasHolder canvasHolder;
    private final AndroidClipboardManager clipboardManager;
    private Function1 configurationChangeObserver;
    private int currentFontWeightAdjustment;
    private Density density;
    private PointerIcon desiredPointerIcon;
    private final List dirtyLayers;
    private final MutableVector endApplyChangesListeners;
    private final FocusOwnerImpl focusOwner;
    private final ParcelableSnapshotMutableState fontFamilyResolver$delegate;
    private final AndroidFontResourceLoader fontLoader;
    private boolean forceUseMatrixCache;
    private final AndroidComposeView$$ExternalSyntheticLambda0 globalLayoutListener;
    private long globalPosition;
    private final PlatformHapticFeedback hapticFeedBack;
    private boolean hoverExitReceived;
    private boolean isDrawingContent;
    private boolean isRenderNodeCompatible;
    private final KeyInputModifierKt$onKeyEvent$$inlined$modifierElementOf$2 keyInputModifier;
    private boolean keyboardModifiersRequireUpdate;
    private long lastDownPointerPosition;
    private long lastMatrixRecalculationAnimationTime;
    private final WeakCache layerCache;
    private final ParcelableSnapshotMutableState layoutDirection$delegate;
    private final CalculateMatrixToWindowApi29 matrixToWindow;
    private final MeasureAndLayoutDelegate measureAndLayoutDelegate;
    private final ModifierLocalManager modifierLocalManager;
    private final MotionEventAdapter motionEventAdapter;
    private boolean observationClearRequested;
    private Constraints onMeasureConstraints;
    private Function1 onViewTreeOwnersAvailable;
    private final AndroidComposeView$pointerIconService$1 pointerIconService;
    private final PointerInputEventProcessor pointerInputEventProcessor;
    private List postponedDirtyLayers;
    private MotionEvent previousMotionEvent;
    private long relayoutTime;
    private final Function0 resendMotionEventOnLayout;
    private final AndroidComposeView$resendMotionEventRunnable$1 resendMotionEventRunnable;
    private final LayoutNode root;
    private final RotaryInputModifierKt$onRotaryScrollEvent$$inlined$modifierElementOf$2 rotaryInputModifier;
    private final AndroidComposeView$$ExternalSyntheticLambda1 scrollChangedListener;
    private final SemanticsOwner semanticsOwner;
    private final AndroidComposeView$$ExternalSyntheticLambda3 sendHoverExitEvent;
    private final LayoutNodeDrawScope sharedDrawScope;
    private boolean showLayoutBounds;
    private final OwnerSnapshotObserver snapshotObserver;
    private boolean superclassInitComplete;
    private final TextInputService textInputService;
    private final TextInputServiceAndroid textInputServiceAndroid;
    private final AndroidTextToolbar textToolbar;
    private final int[] tmpPositionArray;
    private final AndroidComposeView$$ExternalSyntheticLambda2 touchModeChangeListener;
    private final AndroidViewConfiguration viewConfiguration;
    private DrawChildContainer viewLayersContainer;
    private final float[] viewToWindowMatrix;
    private final ParcelableSnapshotMutableState viewTreeOwners$delegate;
    private boolean wasMeasuredWithMultipleConstraints;
    private long windowPosition;
    private final float[] windowToViewMatrix;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Companion {
        public static final boolean access$getIsShowingLayoutBounds() {
            Object obj;
            Method method;
            Companion companion = AndroidComposeView.Companion;
            try {
                Boolean bool = null;
                if (AndroidComposeView.systemPropertiesClass == null) {
                    AndroidComposeView.systemPropertiesClass = Class.forName("android.os.SystemProperties");
                    Class cls = AndroidComposeView.systemPropertiesClass;
                    if (cls != null) {
                        method = cls.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
                    } else {
                        method = null;
                    }
                    AndroidComposeView.getBooleanMethod = method;
                }
                Method method2 = AndroidComposeView.getBooleanMethod;
                if (method2 != null) {
                    obj = method2.invoke(null, "debug.layout", Boolean.FALSE);
                } else {
                    obj = null;
                }
                if (obj instanceof Boolean) {
                    bool = obj;
                }
                if (bool == null) {
                    return false;
                }
                return bool.booleanValue();
            } catch (Exception unused) {
                return false;
            }
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class ViewTreeOwners {
        private final LifecycleOwner lifecycleOwner;
        private final SavedStateRegistryOwner savedStateRegistryOwner;

        public ViewTreeOwners(LifecycleOwner lifecycleOwner, SavedStateRegistryOwner savedStateRegistryOwner) {
            this.lifecycleOwner = lifecycleOwner;
            this.savedStateRegistryOwner = savedStateRegistryOwner;
        }

        public final LifecycleOwner getLifecycleOwner() {
            return this.lifecycleOwner;
        }

        public final SavedStateRegistryOwner getSavedStateRegistryOwner() {
            return this.savedStateRegistryOwner;
        }
    }

    public static void $r8$lambda$6rnsioIDxAVR319ScBkOteeoeiE(AndroidComposeView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updatePositionCacheAndDispatch();
    }

    public static void $r8$lambda$HVKfDYrbF2azN0QgGmEndJ5P5to(AndroidComposeView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = false;
        this$0.hoverExitReceived = false;
        MotionEvent motionEvent = this$0.previousMotionEvent;
        Intrinsics.checkNotNull(motionEvent);
        if (motionEvent.getActionMasked() == 10) {
            z = true;
        }
        if (z) {
            this$0.m276sendMotionEvent8iAsVTc(motionEvent);
            return;
        }
        throw new IllegalStateException("The ACTION_HOVER_EXIT event was not cleared.".toString());
    }

    public static void $r8$lambda$L87CYcEnqBX85FFtAxws3_2BpkM(AndroidComposeView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updatePositionCacheAndDispatch();
    }

    public static void $r8$lambda$TvhWqMihl4JwF42Odovn0ewO6fk(AndroidComposeView this$0, boolean z) {
        int i;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        InputModeManagerImpl inputModeManagerImpl = this$0._inputModeManager;
        if (z) {
            i = 1;
        } else {
            i = 2;
        }
        inputModeManagerImpl.m172setInputModeiuPiT84(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v15, types: [androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r2v16, types: [androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r2v17, types: [androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda2] */
    /* JADX WARN: Type inference failed for: r6v0, types: [androidx.compose.ui.input.key.KeyInputModifierKt$onKeyEvent$$inlined$modifierElementOf$2, androidx.compose.ui.Modifier] */
    /* JADX WARN: Type inference failed for: r7v0, types: [androidx.compose.ui.input.rotary.RotaryInputModifierKt$onRotaryScrollEvent$$inlined$modifierElementOf$2, androidx.compose.ui.Modifier] */
    /* JADX WARN: Type inference failed for: r9v11, types: [androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1] */
    /* JADX WARN: Type inference failed for: r9v15, types: [androidx.compose.ui.platform.AndroidComposeView$pointerIconService$1] */
    public AndroidComposeView(Context context) {
        super(context);
        long j;
        long j2;
        int i;
        j = Offset.Unspecified;
        this.lastDownPointerPosition = j;
        this.superclassInitComplete = true;
        this.sharedDrawScope = new LayoutNodeDrawScope();
        this.density = AndroidDensity_androidKt.Density(context);
        SemanticsModifierCore semanticsModifierCore = new SemanticsModifierCore(false, new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$semanticsModifier$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SemanticsPropertyReceiver $receiver = (SemanticsPropertyReceiver) obj;
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                return Unit.INSTANCE;
            }
        }, InspectableValueKt.getNoInspectorInfo());
        FocusOwnerImpl focusOwnerImpl = new FocusOwnerImpl(new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$focusOwner$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Function0 it = (Function0) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                AndroidComposeView.this.registerOnEndApplyChangesListener(it);
                return Unit.INSTANCE;
            }
        });
        this.focusOwner = focusOwnerImpl;
        this._windowInfo = new WindowInfoImpl();
        Modifier.Companion companion = Modifier.Companion;
        final Function1 function1 = new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$keyInputModifier$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                long j3;
                long j4;
                long j5;
                long j6;
                long j7;
                long j8;
                long j9;
                boolean m173equalsimpl0;
                long j10;
                boolean m173equalsimpl02;
                long j11;
                long j12;
                boolean m173equalsimpl03;
                FocusDirection focusDirection;
                boolean z;
                int i2;
                KeyEvent it = ((androidx.compose.ui.input.key.KeyEvent) obj).m175unboximpl();
                Intrinsics.checkNotNullParameter(it, "it");
                AndroidComposeView.this.getClass();
                long Key = Key_androidKt.Key(it.getKeyCode());
                j3 = Key.Tab;
                boolean z2 = true;
                if (!Key.m173equalsimpl0(Key, j3)) {
                    j4 = Key.DirectionRight;
                    if (!Key.m173equalsimpl0(Key, j4)) {
                        j5 = Key.DirectionLeft;
                        if (!Key.m173equalsimpl0(Key, j5)) {
                            j6 = Key.DirectionUp;
                            if (!Key.m173equalsimpl0(Key, j6)) {
                                j7 = Key.DirectionDown;
                                if (!Key.m173equalsimpl0(Key, j7)) {
                                    j8 = Key.DirectionCenter;
                                    if (!Key.m173equalsimpl0(Key, j8)) {
                                        j9 = Key.Enter;
                                        m173equalsimpl0 = Key.m173equalsimpl0(Key, j9);
                                    } else {
                                        m173equalsimpl0 = true;
                                    }
                                    if (!m173equalsimpl0) {
                                        j10 = Key.NumPadEnter;
                                        m173equalsimpl02 = Key.m173equalsimpl0(Key, j10);
                                    } else {
                                        m173equalsimpl02 = true;
                                    }
                                    if (!m173equalsimpl02) {
                                        j11 = Key.Back;
                                        if (!Key.m173equalsimpl0(Key, j11)) {
                                            j12 = Key.Escape;
                                            m173equalsimpl03 = Key.m173equalsimpl0(Key, j12);
                                        } else {
                                            m173equalsimpl03 = true;
                                        }
                                        if (m173equalsimpl03) {
                                            focusDirection = FocusDirection.m26boximpl(8);
                                        } else {
                                            focusDirection = null;
                                        }
                                    } else {
                                        focusDirection = FocusDirection.m26boximpl(7);
                                    }
                                } else {
                                    focusDirection = FocusDirection.m26boximpl(6);
                                }
                            } else {
                                focusDirection = FocusDirection.m26boximpl(5);
                            }
                        } else {
                            focusDirection = FocusDirection.m26boximpl(3);
                        }
                    } else {
                        focusDirection = FocusDirection.m26boximpl(4);
                    }
                } else {
                    if (it.isShiftPressed()) {
                        i2 = 2;
                    } else {
                        i2 = 1;
                    }
                    focusDirection = FocusDirection.m26boximpl(i2);
                }
                if (focusDirection != null) {
                    int action = it.getAction();
                    if (action != 0) {
                        if (action != 1) {
                            z = false;
                        } else {
                            z = true;
                        }
                    } else {
                        z = true;
                    }
                    if (!z) {
                        z2 = false;
                    }
                    if (z2) {
                        return Boolean.valueOf(((FocusOwnerImpl) AndroidComposeView.this.getFocusOwner()).m30moveFocus3ESFkO8(focusDirection.m28unboximpl()));
                    }
                }
                return Boolean.FALSE;
            }
        };
        final Function1 noInspectorInfo = InspectableValueKt.getNoInspectorInfo();
        ?? r6 = new ModifierNodeElement(function1, noInspectorInfo) { // from class: androidx.compose.ui.input.key.KeyInputModifierKt$onKeyEvent$$inlined$modifierElementOf$2
            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final Modifier.Node create() {
                return new KeyInputInputModifierNodeImpl(function1);
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final Modifier.Node update(Modifier.Node node) {
                Intrinsics.checkNotNullParameter(node, "node");
                ((KeyInputInputModifierNodeImpl) node).setOnEvent(function1);
                return node;
            }
        };
        this.keyInputModifier = r6;
        final AndroidComposeView$rotaryInputModifier$1 onRotaryScrollEvent = new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$rotaryInputModifier$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                RotaryScrollEvent it = (RotaryScrollEvent) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                return Boolean.FALSE;
            }
        };
        Intrinsics.checkNotNullParameter(onRotaryScrollEvent, "onRotaryScrollEvent");
        final Function1 noInspectorInfo2 = InspectableValueKt.getNoInspectorInfo();
        ?? r7 = new ModifierNodeElement(onRotaryScrollEvent, noInspectorInfo2) { // from class: androidx.compose.ui.input.rotary.RotaryInputModifierKt$onRotaryScrollEvent$$inlined$modifierElementOf$2
            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final Modifier.Node create() {
                return new RotaryInputModifierNodeImpl(onRotaryScrollEvent);
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final Modifier.Node update(Modifier.Node node) {
                Intrinsics.checkNotNullParameter(node, "node");
                ((RotaryInputModifierNodeImpl) node).setOnEvent(onRotaryScrollEvent);
                return node;
            }
        };
        this.rotaryInputModifier = r7;
        this.canvasHolder = new CanvasHolder();
        LayoutNode layoutNode = new LayoutNode(false, 3);
        layoutNode.setMeasurePolicy(RootMeasurePolicy.INSTANCE);
        layoutNode.setDensity(this.density);
        layoutNode.setModifier(semanticsModifierCore.then(r7).then(focusOwnerImpl.getModifier()).then(r6));
        this.root = layoutNode;
        this.semanticsOwner = new SemanticsOwner(layoutNode);
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = new AndroidComposeViewAccessibilityDelegateCompat(this);
        this.accessibilityDelegate = androidComposeViewAccessibilityDelegateCompat;
        AutofillTree autofillTree = new AutofillTree();
        this.autofillTree = autofillTree;
        this.dirtyLayers = new ArrayList();
        this.motionEventAdapter = new MotionEventAdapter();
        this.pointerInputEventProcessor = new PointerInputEventProcessor(layoutNode);
        this.configurationChangeObserver = new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$configurationChangeObserver$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Configuration it = (Configuration) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                return Unit.INSTANCE;
            }
        };
        this._autofill = new AndroidAutofill(this, autofillTree);
        this.clipboardManager = new AndroidClipboardManager(context);
        this.accessibilityManager = new AndroidAccessibilityManager(context);
        this.snapshotObserver = new OwnerSnapshotObserver(new Function1() { // from class: androidx.compose.ui.platform.AndroidComposeView$snapshotObserver$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Looper looper;
                Function0 command = (Function0) obj;
                Intrinsics.checkNotNullParameter(command, "command");
                Handler handler = AndroidComposeView.this.getHandler();
                if (handler != null) {
                    looper = handler.getLooper();
                } else {
                    looper = null;
                }
                if (looper == Looper.myLooper()) {
                    command.invoke();
                } else {
                    Handler handler2 = AndroidComposeView.this.getHandler();
                    if (handler2 != null) {
                        handler2.post(new AndroidComposeView$$ExternalSyntheticLambda3(1, command));
                    }
                }
                return Unit.INSTANCE;
            }
        });
        this.measureAndLayoutDelegate = new MeasureAndLayoutDelegate(layoutNode);
        android.view.ViewConfiguration viewConfiguration = android.view.ViewConfiguration.get(context);
        Intrinsics.checkNotNullExpressionValue(viewConfiguration, "get(context)");
        this.viewConfiguration = new AndroidViewConfiguration(viewConfiguration);
        this.globalPosition = IntOffsetKt.IntOffset(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.tmpPositionArray = new int[]{0, 0};
        this.viewToWindowMatrix = Matrix.m104constructorimpl$default();
        this.windowToViewMatrix = Matrix.m104constructorimpl$default();
        this.lastMatrixRecalculationAnimationTime = -1L;
        j2 = Offset.Infinite;
        this.windowPosition = j2;
        this.isRenderNodeCompatible = true;
        this.viewTreeOwners$delegate = SnapshotStateKt.mutableStateOf$default(null);
        this.globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                AndroidComposeView.$r8$lambda$6rnsioIDxAVR319ScBkOteeoeiE(AndroidComposeView.this);
            }
        };
        this.scrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda1
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                AndroidComposeView.$r8$lambda$L87CYcEnqBX85FFtAxws3_2BpkM(AndroidComposeView.this);
            }
        };
        this.touchModeChangeListener = new ViewTreeObserver.OnTouchModeChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda2
            @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
            public final void onTouchModeChanged(boolean z) {
                AndroidComposeView.$r8$lambda$TvhWqMihl4JwF42Odovn0ewO6fk(AndroidComposeView.this, z);
            }
        };
        TextInputServiceAndroid textInputServiceAndroid = new TextInputServiceAndroid(this);
        this.textInputServiceAndroid = textInputServiceAndroid;
        this.textInputService = (TextInputService) ((AndroidComposeView_androidKt$textInputServiceFactory$1) AndroidComposeView_androidKt.getTextInputServiceFactory()).invoke(textInputServiceAndroid);
        this.fontLoader = new AndroidFontResourceLoader(context);
        this.fontFamilyResolver$delegate = SnapshotStateKt.mutableStateOf(FontFamilyResolver_androidKt.createFontFamilyResolver(context), SnapshotStateKt.referentialEqualityPolicy());
        Configuration configuration = context.getResources().getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "context.resources.configuration");
        this.currentFontWeightAdjustment = configuration.fontWeightAdjustment;
        Configuration configuration2 = context.getResources().getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration2, "context.resources.configuration");
        this.layoutDirection$delegate = SnapshotStateKt.mutableStateOf$default(AndroidComposeView_androidKt.getLocaleLayoutDirection(configuration2));
        this.hapticFeedBack = new PlatformHapticFeedback(this);
        if (isInTouchMode()) {
            i = 1;
        } else {
            i = 2;
        }
        this._inputModeManager = new InputModeManagerImpl(i);
        this.modifierLocalManager = new ModifierLocalManager(this);
        this.textToolbar = new AndroidTextToolbar(this);
        this.layerCache = new WeakCache();
        this.endApplyChangesListeners = new MutableVector(new Function0[16]);
        this.resendMotionEventRunnable = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                MotionEvent motionEvent;
                boolean z;
                long j3;
                AndroidComposeView.this.removeCallbacks(this);
                motionEvent = AndroidComposeView.this.previousMotionEvent;
                if (motionEvent != null) {
                    boolean z2 = false;
                    if (motionEvent.getToolType(0) == 3) {
                        z = true;
                    } else {
                        z = false;
                    }
                    int actionMasked = motionEvent.getActionMasked();
                    if (!z ? actionMasked != 1 : !(actionMasked == 10 || actionMasked == 1)) {
                        z2 = true;
                    }
                    if (z2) {
                        int i2 = 7;
                        if (actionMasked != 7 && actionMasked != 9) {
                            i2 = 2;
                        }
                        AndroidComposeView androidComposeView = AndroidComposeView.this;
                        j3 = androidComposeView.relayoutTime;
                        androidComposeView.sendSimulatedEvent(motionEvent, i2, j3, false);
                    }
                }
            }
        };
        this.sendHoverExitEvent = new AndroidComposeView$$ExternalSyntheticLambda3(0, this);
        this.resendMotionEventOnLayout = new Function0() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventOnLayout$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                MotionEvent motionEvent;
                int actionMasked;
                AndroidComposeView$resendMotionEventRunnable$1 androidComposeView$resendMotionEventRunnable$1;
                motionEvent = AndroidComposeView.this.previousMotionEvent;
                if (motionEvent != null && ((actionMasked = motionEvent.getActionMasked()) == 7 || actionMasked == 9)) {
                    AndroidComposeView.this.relayoutTime = SystemClock.uptimeMillis();
                    AndroidComposeView androidComposeView = AndroidComposeView.this;
                    androidComposeView$resendMotionEventRunnable$1 = androidComposeView.resendMotionEventRunnable;
                    androidComposeView.post(androidComposeView$resendMotionEventRunnable$1);
                }
                return Unit.INSTANCE;
            }
        };
        this.matrixToWindow = new CalculateMatrixToWindowApi29();
        setWillNotDraw(false);
        setFocusable(true);
        setFocusable(1);
        setDefaultFocusHighlightEnabled(false);
        setFocusableInTouchMode(true);
        setClipChildren(false);
        ViewCompat.setAccessibilityDelegate(this, androidComposeViewAccessibilityDelegateCompat);
        layoutNode.attach$ui_release(this);
        setForceDarkAllowed(false);
        this.pointerIconService = new Object() { // from class: androidx.compose.ui.platform.AndroidComposeView$pointerIconService$1
        };
    }

    private static void clearChildInvalidObservations(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof AndroidComposeView) {
                ((AndroidComposeView) childAt).onEndApplyChanges();
            } else if (childAt instanceof ViewGroup) {
                clearChildInvalidObservations((ViewGroup) childAt);
            }
        }
    }

    private static Pair convertMeasureSpec(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode != Integer.MIN_VALUE) {
            if (mode != 0) {
                if (mode == 1073741824) {
                    return new Pair(Integer.valueOf(size), Integer.valueOf(size));
                }
                throw new IllegalStateException();
            }
            return new Pair(0, Integer.MAX_VALUE);
        }
        return new Pair(0, Integer.valueOf(size));
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0083 A[Catch: all -> 0x007d, TryCatch #2 {all -> 0x012a, blocks: (B:3:0x0006, B:53:0x00ea, B:55:0x00f3, B:57:0x0116, B:59:0x0120, B:56:0x0107, B:4:0x0052, B:6:0x005b, B:11:0x0066, B:13:0x0070, B:21:0x0083, B:33:0x009b, B:34:0x00a1, B:37:0x00ab, B:24:0x008a, B:38:0x00b7, B:47:0x00c9, B:49:0x00cf, B:51:0x00dd, B:52:0x00e0), top: B:67:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009b A[Catch: all -> 0x007d, TryCatch #2 {all -> 0x012a, blocks: (B:3:0x0006, B:53:0x00ea, B:55:0x00f3, B:57:0x0116, B:59:0x0120, B:56:0x0107, B:4:0x0052, B:6:0x005b, B:11:0x0066, B:13:0x0070, B:21:0x0083, B:33:0x009b, B:34:0x00a1, B:37:0x00ab, B:24:0x008a, B:38:0x00b7, B:47:0x00c9, B:49:0x00cf, B:51:0x00dd, B:52:0x00e0), top: B:67:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a1 A[Catch: all -> 0x007d, TryCatch #2 {all -> 0x012a, blocks: (B:3:0x0006, B:53:0x00ea, B:55:0x00f3, B:57:0x0116, B:59:0x0120, B:56:0x0107, B:4:0x0052, B:6:0x005b, B:11:0x0066, B:13:0x0070, B:21:0x0083, B:33:0x009b, B:34:0x00a1, B:37:0x00ab, B:24:0x008a, B:38:0x00b7, B:47:0x00c9, B:49:0x00cf, B:51:0x00dd, B:52:0x00e0), top: B:67:0x0006 }] */
    /* renamed from: handleMotionEvent-8iAsVTc  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int m275handleMotionEvent8iAsVTc(android.view.MotionEvent r13) {
        /*
            Method dump skipped, instructions count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.m275handleMotionEvent8iAsVTc(android.view.MotionEvent):int");
    }

    private static void invalidateLayers(LayoutNode layoutNode) {
        layoutNode.invalidateLayers$ui_release();
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                invalidateLayers((LayoutNode) content[i]);
                i++;
            } while (i < size);
        }
    }

    private final void invalidateLayoutNodeMeasurement(LayoutNode layoutNode) {
        int i = 0;
        this.measureAndLayoutDelegate.requestRemeasure(layoutNode, false);
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            do {
                invalidateLayoutNodeMeasurement((LayoutNode) content[i]);
                i++;
            } while (i < size);
        }
    }

    private static boolean isBadMotionEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        float x = motionEvent.getX();
        if (!Float.isInfinite(x) && !Float.isNaN(x)) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return true;
        }
        float y = motionEvent.getY();
        if (!Float.isInfinite(y) && !Float.isNaN(y)) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            return true;
        }
        float rawX = motionEvent.getRawX();
        if (!Float.isInfinite(rawX) && !Float.isNaN(rawX)) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (!z3) {
            return true;
        }
        float rawY = motionEvent.getRawY();
        if (!Float.isInfinite(rawY) && !Float.isNaN(rawY)) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (!z4) {
            return true;
        }
        return false;
    }

    private final boolean isInBounds(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (0.0f <= x && x <= getWidth()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (0.0f <= y && y <= getHeight()) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                return true;
            }
        }
        return false;
    }

    private final boolean isPositionChanged(MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        boolean z;
        boolean z2;
        if (motionEvent.getPointerCount() != 1 || (motionEvent2 = this.previousMotionEvent) == null) {
            return true;
        }
        if (motionEvent.getRawX() == motionEvent2.getRawX()) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return true;
        }
        if (motionEvent.getRawY() == motionEvent2.getRawY()) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (!z2) {
            return true;
        }
        return false;
    }

    private final void recalculateWindowPosition() {
        if (!this.forceUseMatrixCache) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            if (currentAnimationTimeMillis != this.lastMatrixRecalculationAnimationTime) {
                this.lastMatrixRecalculationAnimationTime = currentAnimationTimeMillis;
                this.matrixToWindow.m282calculateMatrixToWindowEL8BTi8(this, this.viewToWindowMatrix);
                InvertMatrixKt.m283invertToJiSxe2E(this.viewToWindowMatrix, this.windowToViewMatrix);
                ViewParent parent = getParent();
                View view = this;
                while (parent instanceof ViewGroup) {
                    view = (View) parent;
                    parent = ((ViewGroup) view).getParent();
                }
                view.getLocationOnScreen(this.tmpPositionArray);
                int[] iArr = this.tmpPositionArray;
                view.getLocationInWindow(iArr);
                int[] iArr2 = this.tmpPositionArray;
                this.windowPosition = OffsetKt.Offset(iArr[0] - iArr2[0], iArr[1] - iArr2[1]);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void scheduleMeasureAndLayout(androidx.compose.ui.node.LayoutNode r7) {
        /*
            r6 = this;
            boolean r0 = r6.isLayoutRequested()
            if (r0 != 0) goto L79
            boolean r0 = r6.isAttachedToWindow()
            if (r0 == 0) goto L79
            if (r7 == 0) goto L65
        Le:
            if (r7 == 0) goto L5d
            androidx.compose.ui.node.LayoutNode$UsageByParent r0 = r7.getMeasuredByParent$ui_release()
            androidx.compose.ui.node.LayoutNode$UsageByParent r1 = androidx.compose.ui.node.LayoutNode.UsageByParent.InMeasureBlock
            if (r0 != r1) goto L5d
            boolean r0 = r6.wasMeasuredWithMultipleConstraints
            r1 = 1
            if (r0 != 0) goto L56
            androidx.compose.ui.node.LayoutNode r0 = r7.getParent$ui_release()
            r2 = 0
            if (r0 == 0) goto L51
            androidx.compose.ui.node.InnerNodeCoordinator r0 = r0.getInnerCoordinator$ui_release()
            long r3 = r0.m212getLastMeasurementConstraintsmsEJaDk$ui_release()
            int r0 = androidx.compose.ui.unit.Constraints.m381getMaxWidthimpl(r3)
            int r5 = androidx.compose.ui.unit.Constraints.m383getMinWidthimpl(r3)
            if (r0 != r5) goto L38
            r0 = r1
            goto L39
        L38:
            r0 = r2
        L39:
            if (r0 == 0) goto L4c
            int r0 = androidx.compose.ui.unit.Constraints.m380getMaxHeightimpl(r3)
            int r3 = androidx.compose.ui.unit.Constraints.m382getMinHeightimpl(r3)
            if (r0 != r3) goto L47
            r0 = r1
            goto L48
        L47:
            r0 = r2
        L48:
            if (r0 == 0) goto L4c
            r0 = r1
            goto L4d
        L4c:
            r0 = r2
        L4d:
            if (r0 != 0) goto L51
            r0 = r1
            goto L52
        L51:
            r0 = r2
        L52:
            if (r0 == 0) goto L55
            goto L56
        L55:
            r1 = r2
        L56:
            if (r1 == 0) goto L5d
            androidx.compose.ui.node.LayoutNode r7 = r7.getParent$ui_release()
            goto Le
        L5d:
            androidx.compose.ui.node.LayoutNode r0 = r6.root
            if (r7 != r0) goto L65
            r6.requestLayout()
            return
        L65:
            int r7 = r6.getWidth()
            if (r7 == 0) goto L76
            int r7 = r6.getHeight()
            if (r7 != 0) goto L72
            goto L76
        L72:
            r6.invalidate()
            goto L79
        L76:
            r6.requestLayout()
        L79:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.scheduleMeasureAndLayout(androidx.compose.ui.node.LayoutNode):void");
    }

    /* renamed from: sendMotionEvent-8iAsVTc  reason: not valid java name */
    private final int m276sendMotionEvent8iAsVTc(MotionEvent motionEvent) {
        Object obj;
        boolean z = false;
        if (this.keyboardModifiersRequireUpdate) {
            this.keyboardModifiersRequireUpdate = false;
            WindowInfoImpl windowInfoImpl = this._windowInfo;
            int metaState = motionEvent.getMetaState();
            windowInfoImpl.getClass();
            WindowInfoImpl.m289setKeyboardModifiers5xRPYO0(metaState);
        }
        PointerInputEvent convertToPointerInputEvent$ui_release = this.motionEventAdapter.convertToPointerInputEvent$ui_release(motionEvent, this);
        if (convertToPointerInputEvent$ui_release != null) {
            List pointers = convertToPointerInputEvent$ui_release.getPointers();
            ListIterator listIterator = pointers.listIterator(pointers.size());
            while (true) {
                if (listIterator.hasPrevious()) {
                    obj = listIterator.previous();
                    if (((PointerInputEventData) obj).getDown()) {
                        break;
                    }
                } else {
                    obj = null;
                    break;
                }
            }
            PointerInputEventData pointerInputEventData = (PointerInputEventData) obj;
            if (pointerInputEventData != null) {
                this.lastDownPointerPosition = pointerInputEventData.m195getPositionF1C5BW0();
            }
            int m199processBIzXfog = this.pointerInputEventProcessor.m199processBIzXfog(convertToPointerInputEvent$ui_release, this, isInBounds(motionEvent));
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0 || actionMasked == 5) {
                if ((m199processBIzXfog & 1) != 0) {
                    z = true;
                }
                if (!z) {
                    this.motionEventAdapter.endStream(motionEvent.getPointerId(motionEvent.getActionIndex()));
                }
            }
            return m199processBIzXfog;
        }
        this.pointerInputEventProcessor.processCancel();
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSimulatedEvent(MotionEvent motionEvent, int i, long j, boolean z) {
        int i2;
        int i3;
        int buttonState;
        long downTime;
        int i4;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 1) {
            if (actionMasked == 6) {
                i2 = motionEvent.getActionIndex();
            }
            i2 = -1;
        } else {
            if (i != 9 && i != 10) {
                i2 = 0;
            }
            i2 = -1;
        }
        int pointerCount = motionEvent.getPointerCount();
        if (i2 >= 0) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i5 = pointerCount - i3;
        if (i5 == 0) {
            return;
        }
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            pointerPropertiesArr[i6] = new MotionEvent.PointerProperties();
        }
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[i5];
        for (int i7 = 0; i7 < i5; i7++) {
            pointerCoordsArr[i7] = new MotionEvent.PointerCoords();
        }
        for (int i8 = 0; i8 < i5; i8++) {
            if (i2 >= 0 && i8 >= i2) {
                i4 = 1;
            } else {
                i4 = 0;
            }
            int i9 = i4 + i8;
            motionEvent.getPointerProperties(i9, pointerPropertiesArr[i8]);
            MotionEvent.PointerCoords pointerCoords = pointerCoordsArr[i8];
            motionEvent.getPointerCoords(i9, pointerCoords);
            long m278localToScreenMKHz9U = m278localToScreenMKHz9U(OffsetKt.Offset(pointerCoords.x, pointerCoords.y));
            pointerCoords.x = Offset.m45getXimpl(m278localToScreenMKHz9U);
            pointerCoords.y = Offset.m46getYimpl(m278localToScreenMKHz9U);
        }
        if (z) {
            buttonState = 0;
        } else {
            buttonState = motionEvent.getButtonState();
        }
        if (motionEvent.getDownTime() == motionEvent.getEventTime()) {
            downTime = j;
        } else {
            downTime = motionEvent.getDownTime();
        }
        MotionEvent event = MotionEvent.obtain(downTime, j, i, i5, pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), buttonState, motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
        MotionEventAdapter motionEventAdapter = this.motionEventAdapter;
        Intrinsics.checkNotNullExpressionValue(event, "event");
        PointerInputEvent convertToPointerInputEvent$ui_release = motionEventAdapter.convertToPointerInputEvent$ui_release(event, this);
        Intrinsics.checkNotNull(convertToPointerInputEvent$ui_release);
        this.pointerInputEventProcessor.m199processBIzXfog(convertToPointerInputEvent$ui_release, this, true);
        event.recycle();
    }

    private final void updatePositionCacheAndDispatch() {
        getLocationOnScreen(this.tmpPositionArray);
        long j = this.globalPosition;
        int i = (int) (j >> 32);
        int m402getYimpl = IntOffset.m402getYimpl(j);
        int[] iArr = this.tmpPositionArray;
        boolean z = false;
        int i2 = iArr[0];
        if (i != i2 || m402getYimpl != iArr[1]) {
            this.globalPosition = IntOffsetKt.IntOffset(i2, iArr[1]);
            if (i != Integer.MAX_VALUE && m402getYimpl != Integer.MAX_VALUE) {
                this.root.getLayoutDelegate$ui_release().getMeasurePassDelegate$ui_release().notifyChildrenUsingCoordinatesWhilePlacing();
                z = true;
            }
        }
        this.measureAndLayoutDelegate.dispatchOnPositionedCallbacks(z);
    }

    @Override // android.view.View
    public final void autofill(SparseArray values) {
        Intrinsics.checkNotNullParameter(values, "values");
        AndroidAutofill androidAutofill = this._autofill;
        if (androidAutofill != null) {
            int size = values.size();
            for (int i = 0; i < size; i++) {
                int keyAt = values.keyAt(i);
                AutofillValue value = (AutofillValue) values.get(keyAt);
                Intrinsics.checkNotNullExpressionValue(value, "value");
                if (value.isText()) {
                    AutofillTree autofillTree = androidAutofill.getAutofillTree();
                    CharSequence textValue = value.getTextValue();
                    Intrinsics.checkNotNullExpressionValue(textValue, "value.textValue");
                    autofillTree.performAutofill(textValue.toString(), keyAt);
                } else if (!value.isDate()) {
                    if (!value.isList()) {
                        if (value.isToggle()) {
                            throw new NotImplementedError("An operation is not implemented: b/138604541:  Add onFill() callback for toggle");
                        }
                    } else {
                        throw new NotImplementedError("An operation is not implemented: b/138604541: Add onFill() callback for list");
                    }
                } else {
                    throw new NotImplementedError("An operation is not implemented: b/138604541: Add onFill() callback for date");
                }
            }
        }
    }

    public final Object boundsUpdatesEventLoop(Continuation continuation) {
        Object boundsUpdatesEventLoop = this.accessibilityDelegate.boundsUpdatesEventLoop(continuation);
        if (boundsUpdatesEventLoop == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return boundsUpdatesEventLoop;
        }
        return Unit.INSTANCE;
    }

    /* renamed from: calculatePositionInWindow-MK-Hz9U  reason: not valid java name */
    public final long m277calculatePositionInWindowMKHz9U(long j) {
        recalculateWindowPosition();
        return Matrix.m105mapMKHz9U(this.viewToWindowMatrix, j);
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i) {
        this.accessibilityDelegate.m281canScroll0AR0LA0$ui_release(this.lastDownPointerPosition, false);
        return false;
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i) {
        this.accessibilityDelegate.m281canScroll0AR0LA0$ui_release(this.lastDownPointerPosition, true);
        return false;
    }

    public final OwnedLayer createLayer(Function0 invalidateParentLayer, Function1 drawBlock) {
        DrawChildContainer viewLayerContainer;
        Intrinsics.checkNotNullParameter(drawBlock, "drawBlock");
        Intrinsics.checkNotNullParameter(invalidateParentLayer, "invalidateParentLayer");
        OwnedLayer ownedLayer = (OwnedLayer) this.layerCache.pop();
        if (ownedLayer != null) {
            ownedLayer.reuseLayer(invalidateParentLayer, drawBlock);
            return ownedLayer;
        }
        if (isHardwareAccelerated() && this.isRenderNodeCompatible) {
            try {
                return new RenderNodeLayer(this, drawBlock, invalidateParentLayer);
            } catch (Throwable unused) {
                this.isRenderNodeCompatible = false;
            }
        }
        if (this.viewLayersContainer == null) {
            if (!ViewLayer.access$getHasRetrievedMethod$cp()) {
                ViewLayer.Companion.updateDisplayList(new View(getContext()));
            }
            if (ViewLayer.access$getShouldUseDispatchDraw$cp()) {
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                viewLayerContainer = new DrawChildContainer(context);
            } else {
                Context context2 = getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "context");
                viewLayerContainer = new ViewLayerContainer(context2);
            }
            this.viewLayersContainer = viewLayerContainer;
            addView(viewLayerContainer);
        }
        DrawChildContainer drawChildContainer = this.viewLayersContainer;
        Intrinsics.checkNotNull(drawChildContainer);
        return new ViewLayer(this, drawChildContainer, drawBlock, invalidateParentLayer);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void dispatchDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (!isAttachedToWindow()) {
            invalidateLayers(this.root);
        }
        measureAndLayout(true);
        this.isDrawingContent = true;
        CanvasHolder canvasHolder = this.canvasHolder;
        Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
        canvasHolder.getAndroidCanvas().setInternalCanvas(canvas);
        this.root.draw$ui_release(canvasHolder.getAndroidCanvas());
        canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
        if (true ^ ((ArrayList) this.dirtyLayers).isEmpty()) {
            int size = ((ArrayList) this.dirtyLayers).size();
            for (int i = 0; i < size; i++) {
                ((OwnedLayer) ((ArrayList) this.dirtyLayers).get(i)).updateDisplayList();
            }
        }
        if (ViewLayer.access$getShouldUseDispatchDraw$cp()) {
            int save = canvas.save();
            canvas.clipRect(0.0f, 0.0f, 0.0f, 0.0f);
            super.dispatchDraw(canvas);
            canvas.restoreToCount(save);
        }
        ((ArrayList) this.dirtyLayers).clear();
        this.isDrawingContent = false;
        List list = this.postponedDirtyLayers;
        if (list != null) {
            ((ArrayList) this.dirtyLayers).addAll(list);
            ((ArrayList) list).clear();
        }
    }

    @Override // android.view.View
    public final boolean dispatchGenericMotionEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getActionMasked() == 8) {
            if (event.isFromSource(4194304)) {
                android.view.ViewConfiguration viewConfiguration = android.view.ViewConfiguration.get(getContext());
                float f = -event.getAxisValue(26);
                getContext();
                getContext();
                return this.focusOwner.dispatchRotaryEvent(new RotaryScrollEvent(viewConfiguration.getScaledVerticalScrollFactor() * f, event.getEventTime(), viewConfiguration.getScaledHorizontalScrollFactor() * f));
            } else if (!isBadMotionEvent(event) && isAttachedToWindow()) {
                if ((m275handleMotionEvent8iAsVTc(event) & 1) != 0) {
                    return true;
                }
                return false;
            } else {
                return super.dispatchGenericMotionEvent(event);
            }
        }
        return super.dispatchGenericMotionEvent(event);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchHoverEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            this.sendHoverExitEvent.run();
        }
        if (isBadMotionEvent(event) || !isAttachedToWindow()) {
            return false;
        }
        if (event.isFromSource(4098) && event.getToolType(0) == 1) {
            return this.accessibilityDelegate.dispatchHoverEvent(event);
        }
        int actionMasked = event.getActionMasked();
        if (actionMasked != 7) {
            if (actionMasked == 10 && isInBounds(event)) {
                if (event.getToolType(0) != 3) {
                    MotionEvent motionEvent = this.previousMotionEvent;
                    if (motionEvent != null) {
                        motionEvent.recycle();
                    }
                    this.previousMotionEvent = MotionEvent.obtainNoHistory(event);
                    this.hoverExitReceived = true;
                    post(this.sendHoverExitEvent);
                    return false;
                } else if (event.getButtonState() != 0) {
                    return false;
                }
            }
        } else if (!isPositionChanged(event)) {
            return false;
        }
        if ((m275handleMotionEvent8iAsVTc(event) & 1) == 0) {
            return false;
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (isFocused()) {
            WindowInfoImpl windowInfoImpl = this._windowInfo;
            int metaState = event.getMetaState();
            windowInfoImpl.getClass();
            WindowInfoImpl.m289setKeyboardModifiers5xRPYO0(metaState);
            return this.focusOwner.m29dispatchKeyEventZmokQxo(event);
        }
        return super.dispatchKeyEvent(event);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        if (this.hoverExitReceived) {
            removeCallbacks(this.sendHoverExitEvent);
            MotionEvent motionEvent2 = this.previousMotionEvent;
            Intrinsics.checkNotNull(motionEvent2);
            if (motionEvent.getActionMasked() == 0) {
                if (motionEvent2.getSource() == motionEvent.getSource() && motionEvent2.getToolType(0) == motionEvent.getToolType(0)) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                if (!z2) {
                    this.hoverExitReceived = false;
                }
            }
            this.sendHoverExitEvent.run();
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return false;
        }
        if (motionEvent.getActionMasked() == 2 && !isPositionChanged(motionEvent)) {
            return false;
        }
        int m275handleMotionEvent8iAsVTc = m275handleMotionEvent8iAsVTc(motionEvent);
        if ((m275handleMotionEvent8iAsVTc & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        if ((m275handleMotionEvent8iAsVTc & 1) != 0) {
            return true;
        }
        return false;
    }

    public final View findViewByAccessibilityIdTraversal(int i) {
        try {
            Method declaredMethod = View.class.getDeclaredMethod("findViewByAccessibilityIdTraversal", Integer.TYPE);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(this, Integer.valueOf(i));
            if (!(invoke instanceof View)) {
                return null;
            }
            return (View) invoke;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public final void forceMeasureTheSubtree(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.measureAndLayoutDelegate.forceMeasureTheSubtree(layoutNode);
    }

    public final AndroidAccessibilityManager getAccessibilityManager() {
        return this.accessibilityManager;
    }

    public final AndroidViewsHandler getAndroidViewsHandler$ui_release() {
        if (this._androidViewsHandler == null) {
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            AndroidViewsHandler androidViewsHandler = new AndroidViewsHandler(context);
            this._androidViewsHandler = androidViewsHandler;
            addView(androidViewsHandler);
        }
        AndroidViewsHandler androidViewsHandler2 = this._androidViewsHandler;
        Intrinsics.checkNotNull(androidViewsHandler2);
        return androidViewsHandler2;
    }

    public final AndroidAutofill getAutofill() {
        return this._autofill;
    }

    public final AutofillTree getAutofillTree() {
        return this.autofillTree;
    }

    public final AndroidClipboardManager getClipboardManager() {
        return this.clipboardManager;
    }

    public final AndroidClipboardManager getClipboardManager$1() {
        return this.clipboardManager;
    }

    public final Density getDensity() {
        return this.density;
    }

    public final FocusOwner getFocusOwner() {
        return this.focusOwner;
    }

    @Override // android.view.View
    public final void getFocusedRect(Rect rect) {
        Unit unit;
        Intrinsics.checkNotNullParameter(rect, "rect");
        androidx.compose.ui.geometry.Rect focusRect = this.focusOwner.getFocusRect();
        if (focusRect != null) {
            rect.left = MathKt.roundToInt(focusRect.getLeft());
            rect.top = MathKt.roundToInt(focusRect.getTop());
            rect.right = MathKt.roundToInt(focusRect.getRight());
            rect.bottom = MathKt.roundToInt(focusRect.getBottom());
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            super.getFocusedRect(rect);
        }
    }

    public final FontFamilyResolverImpl getFontFamilyResolver() {
        return (FontFamilyResolverImpl) this.fontFamilyResolver$delegate.getValue();
    }

    public final AndroidFontResourceLoader getFontLoader() {
        return this.fontLoader;
    }

    public final PlatformHapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public final InputModeManagerImpl getInputModeManager() {
        return this._inputModeManager;
    }

    @Override // android.view.View, android.view.ViewParent
    public final LayoutDirection getLayoutDirection() {
        return (LayoutDirection) this.layoutDirection$delegate.getValue();
    }

    public final AndroidComposeView$pointerIconService$1 getPointerIconService() {
        return this.pointerIconService;
    }

    public final LayoutNode getRoot() {
        return this.root;
    }

    public final SemanticsOwner getSemanticsOwner() {
        return this.semanticsOwner;
    }

    public final LayoutNodeDrawScope getSharedDrawScope() {
        return this.sharedDrawScope;
    }

    public final boolean getShowLayoutBounds() {
        return this.showLayoutBounds;
    }

    public final OwnerSnapshotObserver getSnapshotObserver() {
        return this.snapshotObserver;
    }

    public final TextInputService getTextInputService() {
        return this.textInputService;
    }

    public final AndroidTextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    public final ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    public final ViewTreeOwners getViewTreeOwners() {
        return (ViewTreeOwners) this.viewTreeOwners$delegate.getValue();
    }

    public final WindowInfoImpl getWindowInfo() {
        return this._windowInfo;
    }

    public final Object keyboardVisibilityEventLoop(Continuation continuation) {
        Object textInputCommandEventLoop = this.textInputServiceAndroid.textInputCommandEventLoop(continuation);
        if (textInputCommandEventLoop == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return textInputCommandEventLoop;
        }
        return Unit.INSTANCE;
    }

    /* renamed from: localToScreen-MK-Hz9U  reason: not valid java name */
    public final long m278localToScreenMKHz9U(long j) {
        recalculateWindowPosition();
        long m105mapMKHz9U = Matrix.m105mapMKHz9U(this.viewToWindowMatrix, j);
        return OffsetKt.Offset(Offset.m45getXimpl(this.windowPosition) + Offset.m45getXimpl(m105mapMKHz9U), Offset.m46getYimpl(this.windowPosition) + Offset.m46getYimpl(m105mapMKHz9U));
    }

    public final void measureAndLayout(boolean z) {
        Function0 function0;
        Trace.beginSection("AndroidOwner:measureAndLayout");
        if (z) {
            try {
                function0 = this.resendMotionEventOnLayout;
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        } else {
            function0 = null;
        }
        if (this.measureAndLayoutDelegate.measureAndLayout(function0)) {
            requestLayout();
        }
        this.measureAndLayoutDelegate.dispatchOnPositionedCallbacks(false);
        Trace.endSection();
    }

    public final void notifyLayerIsDirty$ui_release(OwnedLayer layer, boolean z) {
        Intrinsics.checkNotNullParameter(layer, "layer");
        if (!z) {
            if (!this.isDrawingContent) {
                this.dirtyLayers.remove(layer);
                List list = this.postponedDirtyLayers;
                if (list != null) {
                    list.remove(layer);
                }
            }
        } else if (!this.isDrawingContent) {
            this.dirtyLayers.add(layer);
        } else {
            List list2 = this.postponedDirtyLayers;
            if (list2 == null) {
                list2 = new ArrayList();
                this.postponedDirtyLayers = list2;
            }
            list2.add(layer);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        boolean z;
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycle;
        super.onAttachedToWindow();
        invalidateLayoutNodeMeasurement(this.root);
        invalidateLayers(this.root);
        this.snapshotObserver.startObserving$ui_release();
        AndroidAutofill androidAutofill = this._autofill;
        if (androidAutofill != null) {
            AutofillCallback autofillCallback = AutofillCallback.INSTANCE;
            autofillCallback.getClass();
            androidAutofill.getAutofillManager().registerCallback(autofillCallback);
        }
        LifecycleOwner lifecycleOwner2 = ViewTreeLifecycleOwner.get(this);
        SavedStateRegistryOwner savedStateRegistryOwner = ViewTreeSavedStateRegistryOwner.get(this);
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null && (lifecycleOwner2 == null || savedStateRegistryOwner == null || (lifecycleOwner2 == viewTreeOwners.getLifecycleOwner() && savedStateRegistryOwner == viewTreeOwners.getLifecycleOwner()))) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            if (lifecycleOwner2 != null) {
                if (savedStateRegistryOwner != null) {
                    if (viewTreeOwners != null && (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
                        lifecycle.removeObserver(this);
                    }
                    lifecycleOwner2.getLifecycle().addObserver(this);
                    ViewTreeOwners viewTreeOwners2 = new ViewTreeOwners(lifecycleOwner2, savedStateRegistryOwner);
                    this.viewTreeOwners$delegate.setValue(viewTreeOwners2);
                    Function1 function1 = this.onViewTreeOwnersAvailable;
                    if (function1 != null) {
                        function1.invoke(viewTreeOwners2);
                    }
                    this.onViewTreeOwnersAvailable = null;
                } else {
                    throw new IllegalStateException("Composed into the View which doesn't propagateViewTreeSavedStateRegistryOwner!");
                }
            } else {
                throw new IllegalStateException("Composed into the View which doesn't propagate ViewTreeLifecycleOwner!");
            }
        }
        ViewTreeOwners viewTreeOwners3 = getViewTreeOwners();
        Intrinsics.checkNotNull(viewTreeOwners3);
        viewTreeOwners3.getLifecycleOwner().getLifecycle().addObserver(this);
        getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().addOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().addOnTouchModeChangeListener(this.touchModeChangeListener);
    }

    @Override // android.view.View
    public final boolean onCheckIsTextEditor() {
        this.textInputServiceAndroid.getClass();
        return false;
    }

    @Override // android.view.View
    protected final void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        this.density = AndroidDensity_androidKt.Density(context);
        int i = newConfig.fontWeightAdjustment;
        if (i != this.currentFontWeightAdjustment) {
            this.currentFontWeightAdjustment = i;
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            this.fontFamilyResolver$delegate.setValue(FontFamilyResolver_androidKt.createFontFamilyResolver(context2));
        }
        this.configurationChangeObserver.invoke(newConfig);
    }

    @Override // android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        Intrinsics.checkNotNullParameter(outAttrs, "outAttrs");
        this.textInputServiceAndroid.getClass();
        return null;
    }

    public final void onDetach(LayoutNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this.measureAndLayoutDelegate.onNodeDetached(node);
        this.observationClearRequested = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycle;
        super.onDetachedFromWindow();
        this.snapshotObserver.stopObserving$ui_release();
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null && (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
            lifecycle.removeObserver(this);
        }
        AndroidAutofill androidAutofill = this._autofill;
        if (androidAutofill != null) {
            AutofillCallback autofillCallback = AutofillCallback.INSTANCE;
            autofillCallback.getClass();
            androidAutofill.getAutofillManager().unregisterCallback(autofillCallback);
        }
        getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().removeOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().removeOnTouchModeChangeListener(this.touchModeChangeListener);
    }

    @Override // android.view.View
    protected final void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
    }

    public final void onEndApplyChanges() {
        if (this.observationClearRequested) {
            this.snapshotObserver.clearInvalidObservations$ui_release();
            this.observationClearRequested = false;
        }
        AndroidViewsHandler androidViewsHandler = this._androidViewsHandler;
        if (androidViewsHandler != null) {
            clearChildInvalidObservations(androidViewsHandler);
        }
        while (this.endApplyChangesListeners.isNotEmpty()) {
            int size = this.endApplyChangesListeners.getSize();
            for (int i = 0; i < size; i++) {
                Function0 function0 = (Function0) this.endApplyChangesListeners.getContent()[i];
                this.endApplyChangesListeners.set(i, null);
                if (function0 != null) {
                    function0.invoke();
                }
            }
            this.endApplyChangesListeners.removeRange(0, size);
        }
    }

    @Override // android.view.View
    protected final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        Log.d("Compose Focus", "Owner FocusChanged(" + z + ')');
        if (z) {
            this.focusOwner.takeFocus();
        } else {
            this.focusOwner.releaseFocus();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.measureAndLayoutDelegate.measureAndLayout(this.resendMotionEventOnLayout);
        this.onMeasureConstraints = null;
        updatePositionCacheAndDispatch();
        if (this._androidViewsHandler != null) {
            getAndroidViewsHandler$ui_release().layout(0, 0, i3 - i, i4 - i2);
        }
    }

    public final void onLayoutChange(LayoutNode layoutNode) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        this.accessibilityDelegate.onLayoutChange$ui_release(layoutNode);
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        Trace.beginSection("AndroidOwner:onMeasure");
        try {
            if (!isAttachedToWindow()) {
                invalidateLayoutNodeMeasurement(this.root);
            }
            Pair convertMeasureSpec = convertMeasureSpec(i);
            int intValue = ((Number) convertMeasureSpec.component1()).intValue();
            int intValue2 = ((Number) convertMeasureSpec.component2()).intValue();
            Pair convertMeasureSpec2 = convertMeasureSpec(i2);
            long Constraints = ConstraintsKt.Constraints(intValue, intValue2, ((Number) convertMeasureSpec2.component1()).intValue(), ((Number) convertMeasureSpec2.component2()).intValue());
            Constraints constraints = this.onMeasureConstraints;
            if (constraints == null) {
                this.onMeasureConstraints = Constraints.m376boximpl(Constraints);
                this.wasMeasuredWithMultipleConstraints = false;
            } else if (!Constraints.m377equalsimpl0(constraints.m385unboximpl(), Constraints)) {
                this.wasMeasuredWithMultipleConstraints = true;
            }
            this.measureAndLayoutDelegate.m249updateRootConstraintsBRTryo0(Constraints);
            this.measureAndLayoutDelegate.measureOnly();
            setMeasuredDimension(this.root.getWidth(), this.root.getHeight());
            if (this._androidViewsHandler != null) {
                getAndroidViewsHandler$ui_release().measure(View.MeasureSpec.makeMeasureSpec(this.root.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this.root.getHeight(), 1073741824));
            }
        } finally {
            Trace.endSection();
        }
    }

    @Override // android.view.View
    public final void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i) {
        AndroidAutofill androidAutofill;
        if (viewStructure != null && (androidAutofill = this._autofill) != null) {
            int addChildCount = viewStructure.addChildCount(androidAutofill.getAutofillTree().getChildren().size());
            for (Map.Entry entry : ((LinkedHashMap) androidAutofill.getAutofillTree().getChildren()).entrySet()) {
                int intValue = ((Number) entry.getKey()).intValue();
                OnBackPressedDispatcher$$ExternalSyntheticThrowCCEIfNotNull0.m(entry.getValue());
                ViewStructure newChild = viewStructure.newChild(addChildCount);
                if (newChild == null) {
                    addChildCount++;
                } else {
                    AutofillId autofillId = viewStructure.getAutofillId();
                    Intrinsics.checkNotNull(autofillId);
                    newChild.setAutofillId(autofillId, intValue);
                    newChild.setId(intValue, androidAutofill.getView().getContext().getPackageName(), null, null);
                    newChild.setAutofillType(1);
                    throw null;
                }
            }
        }
    }

    public final void onRequestMeasure(LayoutNode layoutNode, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        if (z) {
            if (this.measureAndLayoutDelegate.requestLookaheadRemeasure(layoutNode, z2)) {
                scheduleMeasureAndLayout(layoutNode);
            }
        } else if (this.measureAndLayoutDelegate.requestRemeasure(layoutNode, z2)) {
            scheduleMeasureAndLayout(layoutNode);
        }
    }

    public final void onRequestRelayout(LayoutNode layoutNode, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        if (z) {
            if (this.measureAndLayoutDelegate.requestLookaheadRelayout(layoutNode, z2)) {
                scheduleMeasureAndLayout(null);
            }
        } else if (this.measureAndLayoutDelegate.requestRelayout(layoutNode, z2)) {
            scheduleMeasureAndLayout(null);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onResume(LifecycleOwner lifecycleOwner) {
        this.showLayoutBounds = Companion.access$getIsShowingLayoutBounds();
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        if (this.superclassInitComplete) {
            LayoutDirection access$layoutDirectionFromInt = AndroidComposeView_androidKt.access$layoutDirectionFromInt(i);
            this.layoutDirection$delegate.setValue(access$layoutDirectionFromInt);
            FocusOwnerImpl focusOwnerImpl = this.focusOwner;
            focusOwnerImpl.getClass();
            focusOwnerImpl.layoutDirection = access$layoutDirectionFromInt;
        }
    }

    public final void onSemanticsChange() {
        this.accessibilityDelegate.onSemanticsChange$ui_release();
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        boolean access$getIsShowingLayoutBounds;
        this._windowInfo.setWindowFocused(z);
        this.keyboardModifiersRequireUpdate = true;
        super.onWindowFocusChanged(z);
        if (z && this.showLayoutBounds != (access$getIsShowingLayoutBounds = Companion.access$getIsShowingLayoutBounds())) {
            this.showLayoutBounds = access$getIsShowingLayoutBounds;
            invalidateLayers(this.root);
        }
    }

    public final void recycle$ui_release(OwnedLayer layer) {
        Intrinsics.checkNotNullParameter(layer, "layer");
        if (this.viewLayersContainer != null) {
            int i = ViewLayer.$r8$clinit;
        }
        this.layerCache.push(layer);
    }

    public final void registerOnEndApplyChangesListener(Function0 listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (!this.endApplyChangesListeners.contains(listener)) {
            this.endApplyChangesListeners.add(listener);
        }
    }

    public final void requestClearInvalidObservations() {
        this.observationClearRequested = true;
    }

    public final void requestOnPositionedCallback(LayoutNode layoutNode) {
        this.measureAndLayoutDelegate.requestOnPositionedCallback(layoutNode);
        scheduleMeasureAndLayout(null);
    }

    /* renamed from: screenToLocal-MK-Hz9U  reason: not valid java name */
    public final long m279screenToLocalMKHz9U(long j) {
        recalculateWindowPosition();
        return Matrix.m105mapMKHz9U(this.windowToViewMatrix, OffsetKt.Offset(Offset.m45getXimpl(j) - Offset.m45getXimpl(this.windowPosition), Offset.m46getYimpl(j) - Offset.m46getYimpl(this.windowPosition)));
    }

    public final void setConfigurationChangeObserver(Function1 function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.configurationChangeObserver = function1;
    }

    public final void setOnViewTreeOwnersAvailable(Function1 function1) {
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null) {
            ((WrappedComposition$setContent$1) function1).invoke(viewTreeOwners);
        }
        if (!isAttachedToWindow()) {
            this.onViewTreeOwnersAvailable = function1;
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
