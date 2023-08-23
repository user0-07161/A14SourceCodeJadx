package com.android.egg.landroid;

import android.content.res.Resources;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.HorizontalAlignModifier;
import androidx.compose.foundation.layout.LayoutWeightImpl;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.AbsoluteAlignment;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAbsoluteAlignment$Horizontal;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.GenericFontFamily;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnitKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MainActivityKt {
    public static final float DEFAULT_CAMERA_ZOOM = 0.25f;
    public static final boolean DYNAMIC_ZOOM = false;
    public static final long FIXED_RANDOM_SEED = 5038;
    public static final float MAX_CAMERA_ZOOM = 5.0f;
    public static final float MIN_CAMERA_ZOOM = 0.00125f;
    public static final boolean SHOW_DEBUG_TEXT = false;
    public static final boolean TEST_UNIVERSE = false;
    public static final boolean TOUCH_CAMERA_PAN = false;
    public static final boolean TOUCH_CAMERA_ZOOM = true;
    private static final RandomSeedType RANDOM_SEED_TYPE = RandomSeedType.Daily;
    private static final MutableState DEBUG_TEXT = SnapshotStateKt.mutableStateOf$default("Hello Universe");

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RandomSeedType.values().length];
            try {
                iArr[RandomSeedType.Fixed.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[RandomSeedType.Daily.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.android.egg.landroid.MainActivityKt$ConsoleText$1, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void ConsoleText(final androidx.compose.foundation.layout.ColumnScope r17, androidx.compose.ui.Modifier r18, boolean r19, kotlin.random.Random r20, final java.lang.String r21, androidx.compose.runtime.Composer r22, final int r23, final int r24) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.egg.landroid.MainActivityKt.ConsoleText(androidx.compose.foundation.layout.ColumnScope, androidx.compose.ui.Modifier, boolean, kotlin.random.Random, java.lang.String, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void DebugText(final MutableState text, Composer composer, final int i) {
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(text, "text");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(372106673);
        if ((i & 14) == 0) {
            if (composerImpl.changed(text)) {
                i3 = 4;
            } else {
                i3 = 2;
            }
            i2 = i3 | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            int i4 = ComposerKt.$r8$clinit;
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.android.egg.landroid.MainActivityKt$DebugText$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i5) {
                    MainActivityKt.DebugText(MutableState.this, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0160 A[LOOP:0: B:82:0x015d->B:84:0x0160, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:99:? A[RETURN, SYNTHETIC] */
    /* renamed from: FlightStick-uDo3WH8  reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m435FlightStickuDo3WH8(final androidx.compose.ui.Modifier r17, float r18, float r19, long r20, final kotlin.jvm.functions.Function1 r22, androidx.compose.runtime.Composer r23, final int r24, final int r25) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.egg.landroid.MainActivityKt.m435FlightStickuDo3WH8(androidx.compose.ui.Modifier, float, float, long, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void MainActivityPreview(Composer composer, final int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(668846172);
        if (i == 0 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            int i2 = ComposerKt.$r8$clinit;
            Resources system = Resources.getSystem();
            Intrinsics.checkNotNullExpressionValue(system, "getSystem()");
            VisibleUniverse visibleUniverse = new VisibleUniverse(new Namer(system), randomSeed());
            visibleUniverse.initTest();
            Spaaaace(SizeKt.fillMaxSize$default(Modifier.Companion), visibleUniverse, null, composerImpl, 6, 4);
            DebugText(DEBUG_TEXT, composerImpl, 6);
            Telemetry(visibleUniverse, composerImpl, 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.android.egg.landroid.MainActivityKt$MainActivityPreview$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    MainActivityKt.MainActivityPreview(composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:89:0x0193, code lost:
        if (r2 == androidx.compose.runtime.Composer.Companion.getEmpty()) goto L70;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [com.android.egg.landroid.MainActivityKt$Spaaaace$2$1] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void Spaaaace(final androidx.compose.ui.Modifier r10, final com.android.egg.landroid.VisibleUniverse r11, androidx.compose.runtime.MutableState r12, androidx.compose.runtime.Composer r13, final int r14, final int r15) {
        /*
            Method dump skipped, instructions count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.egg.landroid.MainActivityKt.Spaaaace(androidx.compose.ui.Modifier, com.android.egg.landroid.VisibleUniverse, androidx.compose.runtime.MutableState, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Spaaaace$lambda$15(MutableState mutableState) {
        return ((Number) mutableState.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Spaaaace$lambda$16(MutableState mutableState, float f) {
        ((SnapshotMutableStateImpl) mutableState).setValue(Float.valueOf(f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long Spaaaace$lambda$18(MutableState mutableState) {
        return ((Offset) mutableState.getValue()).m51unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Spaaaace$lambda$19(MutableState mutableState, long j) {
        ((SnapshotMutableStateImpl) mutableState).setValue(Offset.m42boximpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Spaaaace$lambda$23(State state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Spaaaace$lambda$24(State state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX WARN: Type inference failed for: r2v23, types: [com.android.egg.landroid.MainActivityKt$Telemetry$2$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v29, types: [kotlin.jvm.internal.Lambda, com.android.egg.landroid.MainActivityKt$Telemetry$2$2] */
    public static final void Telemetry(final VisibleUniverse universe, Composer composer, final int i) {
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(universe, "universe");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-79644008);
        if ((i & 14) == 0) {
            if (composerImpl.changed(universe)) {
                i3 = 4;
            } else {
                i3 = 2;
            }
            i2 = i3 | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) == 2 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            int i4 = ComposerKt.$r8$clinit;
            composerImpl.startReplaceableGroup(-492369756);
            Object nextSlot = composerImpl.nextSlot();
            if (nextSlot == Composer.Companion.getEmpty()) {
                nextSlot = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
                composerImpl.updateValue(nextSlot);
            }
            composerImpl.endReplaceableGroup();
            MutableState mutableState = (MutableState) nextSlot;
            composerImpl.startReplaceableGroup(-492369756);
            Object nextSlot2 = composerImpl.nextSlot();
            if (nextSlot2 == Composer.Companion.getEmpty()) {
                nextSlot2 = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
                composerImpl.updateValue(nextSlot2);
            }
            composerImpl.endReplaceableGroup();
            MutableState mutableState2 = (MutableState) nextSlot2;
            composerImpl.startReplaceableGroup(511388516);
            boolean changed = composerImpl.changed(mutableState2) | composerImpl.changed(mutableState);
            Object nextSlot3 = composerImpl.nextSlot();
            if (changed || nextSlot3 == Composer.Companion.getEmpty()) {
                nextSlot3 = new MainActivityKt$Telemetry$1$1(mutableState2, mutableState, null);
                composerImpl.updateValue(nextSlot3);
            }
            composerImpl.endReplaceableGroup();
            EffectsKt.LaunchedEffect("blah", (Function2) nextSlot3, composerImpl);
            Modifier m10padding3ABfNKs = PaddingKt.m10padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.Companion), 6);
            composerImpl.startReplaceableGroup(-483455358);
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.getTop(), Alignment.Companion.getStart(), composerImpl);
            composerImpl.startReplaceableGroup(-1323940314);
            Density density = (Density) composerImpl.consume(CompositionLocalsKt.getLocalDensity());
            LayoutDirection layoutDirection = (LayoutDirection) composerImpl.consume(CompositionLocalsKt.getLocalLayoutDirection());
            ViewConfiguration viewConfiguration = (ViewConfiguration) composerImpl.consume(CompositionLocalsKt.getLocalViewConfiguration());
            ComposeUiNode.Companion.getClass();
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            ComposableLambdaImpl materializerOf = LayoutKt.materializerOf(m10padding3ABfNKs);
            if (composerImpl.getApplier() instanceof Applier) {
                composerImpl.startReusableNode();
                if (composerImpl.getInserting()) {
                    composerImpl.createNode(constructor);
                } else {
                    composerImpl.useNode();
                }
                composerImpl.disableReusing();
                Updater.m23setimpl(composerImpl, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.m23setimpl(composerImpl, density, ComposeUiNode.Companion.getSetDensity());
                Updater.m23setimpl(composerImpl, layoutDirection, ComposeUiNode.Companion.getSetLayoutDirection());
                Updater.m23setimpl(composerImpl, viewConfiguration, ComposeUiNode.Companion.getSetViewConfiguration());
                composerImpl.enableReusing();
                boolean z = false;
                materializerOf.invoke(SkippableUpdater.m21boximpl(composerImpl), (Object) composerImpl, (Object) 0);
                composerImpl.startReplaceableGroup(2058660585);
                final ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                ((SnapshotMutableStateImpl) universe.getTriggerDraw()).getValue();
                List planets = universe.getPlanets();
                final ArrayList arrayList = new ArrayList();
                for (Object obj : planets) {
                    if (((Planet) obj).getExplored()) {
                        arrayList.add(obj);
                    }
                }
                Modifier.Companion companion = Modifier.Companion;
                AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, Telemetry$lambda$1(mutableState), companion, ComposeToolsKt.getFlickerFadeIn(), null, null, ComposableLambdaKt.composableLambda(composerImpl, 788846474, new Function3() { // from class: com.android.egg.landroid.MainActivityKt$Telemetry$2$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer composer2, int i5) {
                        GenericFontFamily genericFontFamily;
                        FontWeight fontWeight;
                        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
                        int i6 = ComposerKt.$r8$clinit;
                        genericFontFamily = FontFamily.Monospace;
                        fontWeight = FontWeight.Medium;
                        long sp = TextUnitKt.getSp(12);
                        long m428getConsole0d7_KjU = Colors.INSTANCE.m428getConsole0d7_KjU();
                        ColumnScope columnScope = ColumnScope.this;
                        Modifier.Companion companion2 = Modifier.Companion;
                        BiasAbsoluteAlignment$Horizontal left = AbsoluteAlignment.getLeft();
                        ((ColumnScopeInstance) columnScope).getClass();
                        HorizontalAlignModifier horizontalAlignModifier = new HorizontalAlignModifier(left, InspectableValueKt.getNoInspectorInfo());
                        Star star = universe.getStar();
                        VisibleUniverse visibleUniverse = universe;
                        List list = arrayList;
                        String name = star.getName();
                        long randomSeed = visibleUniverse.getRandomSeed() % 100000;
                        String name2 = star.getCls().name();
                        String format = String.format("  MASS: %.3g\n", Arrays.copyOf(new Object[]{Float.valueOf(star.getMass())}, 1));
                        Intrinsics.checkNotNullExpressionValue(format, "format(this, *args)");
                        String str = "  STAR: " + name + " (UDC-" + randomSeed + ")\n CLASS: " + name2 + "\nRADIUS: " + ((int) star.getRadius()) + "\n" + format + "BODIES: " + list.size() + " / " + visibleUniverse.getPlanets().size() + "\n\n";
                        List<Planet> list2 = arrayList;
                        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2));
                        for (Planet planet : list2) {
                            arrayList2.add("  BODY: " + planet.getName() + "\n  TYPE: " + StringsKt.capitalize(planet.getDescription()) + "\n  ATMO: " + StringsKt.capitalize(planet.getAtmosphere()) + "\n FAUNA: " + StringsKt.capitalize(planet.getFauna()) + "\n FLORA: " + StringsKt.capitalize(planet.getFlora()) + "\n");
                        }
                        TextKt.m19Text4IGK_g(((Object) str) + CollectionsKt.joinToString$default(arrayList2, "\n", null, null, null, 62), horizontalAlignModifier, m428getConsole0d7_KjU, sp, null, fontWeight, genericFontFamily, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 200064, 0, 130960);
                        int i7 = ComposerKt.$r8$clinit;
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3, Object obj4) {
                        invoke((AnimatedVisibilityScope) obj2, (Composer) obj3, ((Number) obj4).intValue());
                        return Unit.INSTANCE;
                    }
                }), composerImpl, 1576326, 24);
                if (1.0f > 0.0d) {
                    z = true;
                }
                if (z) {
                    SpacerKt.Spacer(new LayoutWeightImpl(true, InspectableValueKt.getNoInspectorInfo()), composerImpl);
                    AnimatedVisibilityKt.AnimatedVisibility(columnScopeInstance, Telemetry$lambda$4(mutableState2), companion, ComposeToolsKt.getFlickerFadeIn(), null, null, ComposableLambdaKt.composableLambda(composerImpl, 205165555, new Function3() { // from class: com.android.egg.landroid.MainActivityKt$Telemetry$2$2
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        /* JADX WARN: Code restructure failed: missing block: B:5:0x0072, code lost:
                            if (r11 == null) goto L20;
                         */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public final void invoke(androidx.compose.animation.AnimatedVisibilityScope r28, androidx.compose.runtime.Composer r29, int r30) {
                            /*
                                Method dump skipped, instructions count: 323
                                To view this dump change 'Code comments level' option to 'DEBUG'
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.egg.landroid.MainActivityKt$Telemetry$2$2.invoke(androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):void");
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3, Object obj4) {
                            invoke((AnimatedVisibilityScope) obj2, (Composer) obj3, ((Number) obj4).intValue());
                            return Unit.INSTANCE;
                        }
                    }), composerImpl, 1576326, 24);
                    composerImpl.endReplaceableGroup();
                    composerImpl.endNode();
                    composerImpl.endReplaceableGroup();
                    composerImpl.endReplaceableGroup();
                    int i5 = ComposerKt.$r8$clinit;
                } else {
                    throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero".toString());
                }
            } else {
                ComposablesKt.invalidApplier();
                throw null;
            }
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.android.egg.landroid.MainActivityKt$Telemetry$3
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    invoke((Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i6) {
                    MainActivityKt.Telemetry(VisibleUniverse.this, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    private static final boolean Telemetry$lambda$1(MutableState mutableState) {
        return ((Boolean) mutableState.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Telemetry$lambda$2(MutableState mutableState, boolean z) {
        ((SnapshotMutableStateImpl) mutableState).setValue(Boolean.valueOf(z));
    }

    private static final boolean Telemetry$lambda$4(MutableState mutableState) {
        return ((Boolean) mutableState.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Telemetry$lambda$5(MutableState mutableState, boolean z) {
        ((SnapshotMutableStateImpl) mutableState).setValue(Boolean.valueOf(z));
    }

    public static final long dailySeed() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        return (gregorianCalendar.get(2) * 100) + (gregorianCalendar.get(1) * 10000) + gregorianCalendar.get(5);
    }

    public static final MutableState getDEBUG_TEXT() {
        return DEBUG_TEXT;
    }

    public static final RandomSeedType getRANDOM_SEED_TYPE() {
        return RANDOM_SEED_TYPE;
    }

    public static final long randomSeed() {
        long j;
        int i = WhenMappings.$EnumSwitchMapping$0[RANDOM_SEED_TYPE.ordinal()];
        if (i != 1) {
            if (i != 2) {
                long j2 = 10000000;
                long nextLong = Random.Default.nextLong() % j2;
                j = (int) (nextLong + (j2 & (((nextLong ^ j2) & ((-nextLong) | nextLong)) >> 63)));
            } else {
                j = dailySeed();
            }
        } else {
            j = FIXED_RANDOM_SEED;
        }
        return Math.abs(j);
    }
}
