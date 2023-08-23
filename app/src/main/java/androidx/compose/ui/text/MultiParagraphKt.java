package androidx.compose.ui.text;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class MultiParagraphKt {
    public static final int findParagraphByIndex(List paragraphInfoList, int i) {
        char c;
        Intrinsics.checkNotNullParameter(paragraphInfoList, "paragraphInfoList");
        ArrayList arrayList = (ArrayList) paragraphInfoList;
        int size = arrayList.size() - 1;
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i3);
            if (paragraphInfo.getStartIndex() > i) {
                c = 1;
            } else if (paragraphInfo.getEndIndex() <= i) {
                c = 65535;
            } else {
                c = 0;
            }
            if (c < 0) {
                i2 = i3 + 1;
            } else if (c > 0) {
                size = i3 - 1;
            } else {
                return i3;
            }
        }
        return -(i2 + 1);
    }

    public static final int findParagraphByLineIndex(List paragraphInfoList, int i) {
        char c;
        Intrinsics.checkNotNullParameter(paragraphInfoList, "paragraphInfoList");
        ArrayList arrayList = (ArrayList) paragraphInfoList;
        int size = arrayList.size() - 1;
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i3);
            if (paragraphInfo.getStartLineIndex() > i) {
                c = 1;
            } else if (paragraphInfo.getEndLineIndex() <= i) {
                c = 65535;
            } else {
                c = 0;
            }
            if (c < 0) {
                i2 = i3 + 1;
            } else if (c > 0) {
                size = i3 - 1;
            } else {
                return i3;
            }
        }
        return -(i2 + 1);
    }

    public static final int findParagraphByY(List paragraphInfoList, float f) {
        char c;
        Intrinsics.checkNotNullParameter(paragraphInfoList, "paragraphInfoList");
        ArrayList arrayList = (ArrayList) paragraphInfoList;
        int size = arrayList.size() - 1;
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) >>> 1;
            ParagraphInfo paragraphInfo = (ParagraphInfo) arrayList.get(i2);
            if (paragraphInfo.getTop() > f) {
                c = 1;
            } else if (paragraphInfo.getBottom() <= f) {
                c = 65535;
            } else {
                c = 0;
            }
            if (c < 0) {
                i = i2 + 1;
            } else if (c > 0) {
                size = i2 - 1;
            } else {
                return i2;
            }
        }
        return -(i + 1);
    }
}
