package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class SlotTable implements Iterable, KMappedMarker {
    private int groupsSize;
    private int readers;
    private int slotsSize;
    private int version;
    private boolean writer;
    private int[] groups = new int[0];
    private Object[] slots = new Object[0];
    private ArrayList anchors = new ArrayList();

    public final Anchor anchor() {
        boolean z;
        int search;
        if (!this.writer) {
            int i = this.groupsSize;
            if (i > 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                ArrayList arrayList = this.anchors;
                search = SlotTableKt.search(arrayList, 0, i);
                if (search < 0) {
                    Anchor anchor = new Anchor(0);
                    arrayList.add(-(search + 1), anchor);
                    return anchor;
                }
                Object obj = arrayList.get(search);
                Intrinsics.checkNotNullExpressionValue(obj, "get(location)");
                return (Anchor) obj;
            }
            throw new IllegalArgumentException("Parameter index is out of range".toString());
        }
        ComposerKt.composeRuntimeError("use active SlotWriter to create an anchor location instead ".toString());
        throw null;
    }

    public final int anchorIndex(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        if (!this.writer) {
            if (anchor.getValid()) {
                return anchor.getLocation$runtime_release();
            }
            throw new IllegalArgumentException("Anchor refers to a group that was removed".toString());
        }
        ComposerKt.composeRuntimeError("Use active SlotWriter to determine anchor location instead".toString());
        throw null;
    }

    public final void close$runtime_release(SlotReader reader) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        if (reader.getTable$runtime_release() == this && this.readers > 0) {
            this.readers--;
        } else {
            ComposerKt.composeRuntimeError("Unexpected reader close()".toString());
            throw null;
        }
    }

    public final ArrayList getAnchors$runtime_release() {
        return this.anchors;
    }

    public final int[] getGroups() {
        return this.groups;
    }

    public final int getGroupsSize() {
        return this.groupsSize;
    }

    public final Object[] getSlots() {
        return this.slots;
    }

    public final int getSlotsSize() {
        return this.slotsSize;
    }

    public final int getVersion$runtime_release() {
        return this.version;
    }

    public final boolean getWriter$runtime_release() {
        return this.writer;
    }

    public final boolean groupContainsAnchor(int i, Anchor anchor) {
        boolean z;
        boolean z2;
        if (!this.writer) {
            if (i >= 0 && i < this.groupsSize) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                if (ownsAnchor(anchor)) {
                    int access$groupSize = SlotTableKt.access$groupSize(i, this.groups) + i;
                    int location$runtime_release = anchor.getLocation$runtime_release();
                    if (i <= location$runtime_release && location$runtime_release < access$groupSize) {
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
            ComposerKt.composeRuntimeError("Invalid group index".toString());
            throw null;
        }
        ComposerKt.composeRuntimeError("Writer is active".toString());
        throw null;
    }

    public final boolean isEmpty() {
        if (this.groupsSize == 0) {
            return true;
        }
        return false;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new GroupIterator(0, this.groupsSize, this);
    }

    public final SlotReader openReader() {
        if (!this.writer) {
            this.readers++;
            return new SlotReader(this);
        }
        throw new IllegalStateException("Cannot read while a writer is pending".toString());
    }

    public final SlotWriter openWriter() {
        boolean z;
        if (!this.writer) {
            if (this.readers <= 0) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                this.writer = true;
                this.version++;
                return new SlotWriter(this);
            }
            ComposerKt.composeRuntimeError("Cannot start a writer when a reader is pending".toString());
            throw null;
        }
        ComposerKt.composeRuntimeError("Cannot start a writer when another writer is pending".toString());
        throw null;
    }

    public final boolean ownsAnchor(Anchor anchor) {
        int search;
        boolean z;
        if (anchor.getValid()) {
            search = SlotTableKt.search(this.anchors, anchor.getLocation$runtime_release(), this.groupsSize);
            if (search >= 0 && Intrinsics.areEqual(this.anchors.get(search), anchor)) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final void setTo$runtime_release(int[] groups, int i, Object[] slots, int i2, ArrayList anchors) {
        Intrinsics.checkNotNullParameter(groups, "groups");
        Intrinsics.checkNotNullParameter(slots, "slots");
        Intrinsics.checkNotNullParameter(anchors, "anchors");
        this.groups = groups;
        this.groupsSize = i;
        this.slots = slots;
        this.slotsSize = i2;
        this.anchors = anchors;
    }

    public final void close$runtime_release(SlotWriter writer, int[] groups, int i, Object[] slots, int i2, ArrayList anchors) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(groups, "groups");
        Intrinsics.checkNotNullParameter(slots, "slots");
        Intrinsics.checkNotNullParameter(anchors, "anchors");
        if (writer.getTable$runtime_release() == this && this.writer) {
            this.writer = false;
            setTo$runtime_release(groups, i, slots, i2, anchors);
            return;
        }
        throw new IllegalArgumentException("Unexpected writer close()".toString());
    }
}
