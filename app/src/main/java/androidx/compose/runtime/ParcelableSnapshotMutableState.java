package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class ParcelableSnapshotMutableState extends SnapshotMutableStateImpl implements Parcelable {
    public static final Parcelable.Creator CREATOR = new ParcelableSnapshotMutableState$Companion$CREATOR$1();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ParcelableSnapshotMutableState(Object obj, SnapshotMutationPolicy policy) {
        super(obj, policy);
        Intrinsics.checkNotNullParameter(policy, "policy");
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeValue(getValue());
        SnapshotMutationPolicy policy = getPolicy();
        if (Intrinsics.areEqual(policy, NeverEqualPolicy.INSTANCE)) {
            i2 = 0;
        } else if (Intrinsics.areEqual(policy, StructuralEqualityPolicy.INSTANCE)) {
            i2 = 1;
        } else if (Intrinsics.areEqual(policy, ReferentialEqualityPolicy.INSTANCE)) {
            i2 = 2;
        } else {
            throw new IllegalStateException("Only known types of MutableState's SnapshotMutationPolicy are supported");
        }
        parcel.writeInt(i2);
    }
}
