package androidx.compose.ui.platform;

import android.os.Binder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import java.io.Serializable;
import kotlin.Function;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class DisposableSaveableStateRegistry_androidKt {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Class[] AcceptableClasses = {Serializable.class, Parcelable.class, String.class, SparseArray.class, Binder.class, Size.class, SizeF.class};

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean canBeSavedToBundle(Object obj) {
        if (obj instanceof SnapshotMutableStateImpl) {
            SnapshotMutableStateImpl snapshotMutableStateImpl = (SnapshotMutableStateImpl) obj;
            if (snapshotMutableStateImpl.getPolicy() != SnapshotStateKt.neverEqualPolicy() && snapshotMutableStateImpl.getPolicy() != SnapshotStateKt.structuralEqualityPolicy() && snapshotMutableStateImpl.getPolicy() != SnapshotStateKt.referentialEqualityPolicy()) {
                return false;
            }
            Object value = snapshotMutableStateImpl.getValue();
            if (value == null) {
                return true;
            }
            return canBeSavedToBundle(value);
        } else if ((obj instanceof Function) && (obj instanceof Serializable)) {
            return false;
        } else {
            for (Class cls : AcceptableClasses) {
                if (cls.isInstance(obj)) {
                    return true;
                }
            }
            return false;
        }
    }
}
