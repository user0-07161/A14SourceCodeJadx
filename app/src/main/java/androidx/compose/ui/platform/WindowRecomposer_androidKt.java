package androidx.compose.ui.platform;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import androidx.compose.runtime.CompositionContext;
import com.android.egg.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.AbstractChannel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.MainDispatcherLoader;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class WindowRecomposer_androidKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Map animationScale = new LinkedHashMap();

    /* JADX WARN: Type inference failed for: r6v1, types: [androidx.compose.ui.platform.WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$contentObserver$1] */
    public static final StateFlow access$getAnimationScaleFlowFor(Context context) {
        StateFlow stateFlow;
        Map map = animationScale;
        synchronized (map) {
            LinkedHashMap linkedHashMap = (LinkedHashMap) map;
            Object obj = linkedHashMap.get(context);
            if (obj == null) {
                ContentResolver contentResolver = context.getContentResolver();
                Uri uriFor = Settings.Global.getUriFor("animator_duration_scale");
                final AbstractChannel Channel$default = ChannelKt.Channel$default(-1, null, 6);
                final Handler createAsync = Handler.createAsync(Looper.getMainLooper());
                Flow flow = FlowKt.flow(new WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$1(contentResolver, uriFor, new ContentObserver(createAsync) { // from class: androidx.compose.ui.platform.WindowRecomposer_androidKt$getAnimationScaleFlowFor$1$1$contentObserver$1
                    @Override // android.database.ContentObserver
                    public final void onChange(boolean z, Uri uri) {
                        Channel$default.mo484trySendJP2dKIU(Unit.INSTANCE);
                    }
                }, Channel$default, context, null));
                JobImpl SupervisorJob$default = SupervisorKt.SupervisorJob$default();
                int i = Dispatchers.$r8$clinit;
                ContextScope contextScope = new ContextScope(SupervisorJob$default.plus(MainDispatcherLoader.dispatcher));
                SharingStarted.Companion companion = SharingStarted.Companion;
                obj = FlowKt.stateIn(flow, contextScope, SharingStarted.Companion.WhileSubscribed$default(), Float.valueOf(Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f)));
                linkedHashMap.put(context, obj);
            }
            stateFlow = (StateFlow) obj;
        }
        return stateFlow;
    }

    public static final CompositionContext getCompositionContext(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Object tag = view.getTag(R.id.androidx_compose_ui_view_composition_context);
        if (tag instanceof CompositionContext) {
            return (CompositionContext) tag;
        }
        return null;
    }
}
