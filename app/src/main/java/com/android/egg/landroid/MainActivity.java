package com.android.egg.landroid;

import android.content.res.Resources;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowInfoTrackerImpl;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class MainActivity extends ComponentActivity {
    public static final int $stable = 8;
    private MutableState foldState = SnapshotStateKt.mutableStateOf$default(null);

    private final void onWindowLayoutInfoChange() {
        WindowInfoTracker.Companion.getClass();
        WindowInfoTrackerImpl orCreate = WindowInfoTracker.Companion.getOrCreate(this);
        LifecycleCoroutineScopeImpl lifecycleScope = LifecycleOwnerKt.getLifecycleScope(this);
        int i = Dispatchers.$r8$clinit;
        BuildersKt.launch$default(lifecycleScope, MainDispatcherLoader.dispatcher, null, new MainActivity$onWindowLayoutInfoChange$1(this, orCreate, null), 2);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.egg.landroid.MainActivity$onCreate$1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        onWindowLayoutInfoChange();
        Resources resources = getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "resources");
        final VisibleUniverse visibleUniverse = new VisibleUniverse(new Namer(resources), MainActivityKt.randomSeed());
        visibleUniverse.initRandom();
        ComponentActivityKt.setContent$default(this, ComposableLambdaKt.composableLambdaInstance(1000079658, new Function2() { // from class: com.android.egg.landroid.MainActivity$onCreate$1
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

            public final void invoke(Composer composer, int i) {
                MutableState mutableState;
                long j;
                if ((i & 11) == 2) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return;
                    }
                }
                int i2 = ComposerKt.$r8$clinit;
                Modifier.Companion companion = Modifier.Companion;
                Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(companion);
                VisibleUniverse visibleUniverse2 = VisibleUniverse.this;
                mutableState = this.foldState;
                MainActivityKt.Spaaaace(fillMaxSize$default, visibleUniverse2, mutableState, composer, 6, 0);
                MainActivityKt.DebugText(MainActivityKt.getDEBUG_TEXT(), composer, 6);
                final float m433toLocalPx8Feqmps = ComposeToolsKt.m433toLocalPx8Feqmps(50, composer, 6);
                final float m433toLocalPx8Feqmps2 = ComposeToolsKt.m433toLocalPx8Feqmps(100, composer, 6);
                Modifier fillMaxSize$default2 = SizeKt.fillMaxSize$default(companion);
                j = Color.Green;
                VisibleUniverse visibleUniverse3 = VisibleUniverse.this;
                Float valueOf = Float.valueOf(m433toLocalPx8Feqmps);
                Float valueOf2 = Float.valueOf(m433toLocalPx8Feqmps2);
                final VisibleUniverse visibleUniverse4 = VisibleUniverse.this;
                ComposerImpl composerImpl2 = (ComposerImpl) composer;
                composerImpl2.startReplaceableGroup(1618982084);
                boolean changed = composerImpl2.changed(visibleUniverse3) | composerImpl2.changed(valueOf) | composerImpl2.changed(valueOf2);
                Object nextSlot = composerImpl2.nextSlot();
                if (changed || nextSlot == Composer.Companion.getEmpty()) {
                    nextSlot = new Function1() { // from class: com.android.egg.landroid.MainActivity$onCreate$1$1$1
                        /* JADX INFO: Access modifiers changed from: package-private */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            m434invokek4lQ0M(((Offset) obj).m51unboximpl());
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke-k-4lQ0M  reason: not valid java name */
                        public final void m434invokek4lQ0M(long j2) {
                            Spacecraft spacecraft;
                            long j3;
                            long j4;
                            long j5;
                            Body follow = VisibleUniverse.this.getFollow();
                            if (follow instanceof Spacecraft) {
                                spacecraft = (Spacecraft) follow;
                            } else {
                                spacecraft = null;
                            }
                            if (spacecraft != null) {
                                float f = m433toLocalPx8Feqmps;
                                float f2 = m433toLocalPx8Feqmps2;
                                Offset.Companion companion2 = Offset.Companion;
                                j3 = Offset.Zero;
                                if (Offset.m43equalsimpl0(j2, j3)) {
                                    j5 = Offset.Zero;
                                    spacecraft.m441setThrustk4lQ0M(j5);
                                    return;
                                }
                                float m443anglek4lQ0M = Vec2Kt.m443anglek4lQ0M(j2);
                                spacecraft.setAngle(m443anglek4lQ0M);
                                float m446magk4lQ0M = Vec2Kt.m446magk4lQ0M(j2);
                                if (m446magk4lQ0M < f) {
                                    j4 = Offset.Zero;
                                    spacecraft.m441setThrustk4lQ0M(j4);
                                    return;
                                }
                                spacecraft.m441setThrustk4lQ0M(Vec2Kt.makeWithAngleMag(companion2, m443anglek4lQ0M, RangesKt.coerceIn(MathsKt.lexp(f, f2, m446magk4lQ0M), 0.0f, 1.0f)));
                            }
                        }
                    };
                    composerImpl2.updateValue(nextSlot);
                }
                composerImpl2.endReplaceableGroup();
                MainActivityKt.m435FlightStickuDo3WH8(fillMaxSize$default2, m433toLocalPx8Feqmps, m433toLocalPx8Feqmps2, j, (Function1) nextSlot, composerImpl2, 3078, 0);
                MainActivityKt.Telemetry(VisibleUniverse.this, composerImpl2, 0);
            }
        }, true));
    }
}
