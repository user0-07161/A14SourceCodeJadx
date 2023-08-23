package androidx.compose.ui.text.android;

import android.text.Layout;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LayoutHelper {
    private final boolean[] bidiProcessedParagraphs;
    private final Layout layout;
    private final List paragraphBidi;
    private final List paragraphEnds;
    private char[] tmpBuffer;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    final class BidiRun {
        private final int end;
        private final boolean isRtl;
        private final int start;

        public BidiRun(int i, int i2, boolean z) {
            this.start = i;
            this.end = i2;
            this.isRtl = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BidiRun)) {
                return false;
            }
            BidiRun bidiRun = (BidiRun) obj;
            if (this.start == bidiRun.start && this.end == bidiRun.end && this.isRtl == bidiRun.isRtl) {
                return true;
            }
            return false;
        }

        public final int getEnd() {
            return this.end;
        }

        public final int getStart() {
            return this.start;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final int hashCode() {
            int hashCode = (Integer.hashCode(this.end) + (Integer.hashCode(this.start) * 31)) * 31;
            boolean z = this.isRtl;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode + i;
        }

        public final boolean isRtl() {
            return this.isRtl;
        }

        public final String toString() {
            return "BidiRun(start=" + this.start + ", end=" + this.end + ", isRtl=" + this.isRtl + ')';
        }
    }

    public LayoutHelper(Layout layout) {
        Intrinsics.checkNotNullParameter(layout, "layout");
        this.layout = layout;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        do {
            CharSequence text = this.layout.getText();
            Intrinsics.checkNotNullExpressionValue(text, "layout.text");
            int indexOf$default = StringsKt.indexOf$default(text, '\n', i, false, 4);
            if (indexOf$default < 0) {
                i = this.layout.getText().length();
            } else {
                i = indexOf$default + 1;
            }
            arrayList.add(Integer.valueOf(i));
        } while (i < this.layout.getText().length());
        this.paragraphEnds = arrayList;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList2.add(null);
        }
        this.paragraphBidi = arrayList2;
        this.bidiProcessedParagraphs = new boolean[((ArrayList) this.paragraphEnds).size()];
        ((ArrayList) this.paragraphEnds).size();
    }

    private final int lineEndToVisibleEnd(int i) {
        boolean z;
        while (i > 0) {
            char charAt = this.layout.getText().charAt(i - 1);
            boolean z2 = true;
            if (charAt != ' ' && charAt != '\n' && charAt != 5760) {
                if (8192 <= charAt && charAt < 8203) {
                    z = true;
                } else {
                    z = false;
                }
                if ((!z || charAt == 8199) && charAt != 8287 && charAt != 12288) {
                    z2 = false;
                }
            }
            if (!z2) {
                break;
            }
            i--;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:130:0x021e, code lost:
        if (r13 == r1.isRtl()) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x018c, code lost:
        if (r3.getRunCount() == 1) goto L186;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float getHorizontalPosition(int r28, boolean r29, boolean r30) {
        /*
            Method dump skipped, instructions count: 809
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.LayoutHelper.getHorizontalPosition(int, boolean, boolean):float");
    }
}
