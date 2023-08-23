package androidx.compose.ui.text.style;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class LineBreak {
    private static final int Simple = 66305;
    private final int mask;

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Strategy {
        private final int value;

        private /* synthetic */ Strategy(int i) {
            this.value = i;
        }

        /* renamed from: box-impl  reason: not valid java name */
        public static final /* synthetic */ Strategy m357boximpl(int i) {
            return new Strategy(i);
        }

        /* renamed from: toString-impl  reason: not valid java name */
        public static String m358toStringimpl(int i) {
            boolean z;
            boolean z2;
            boolean z3 = false;
            if (i == 1) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return "Strategy.Simple";
            }
            if (i == 2) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                return "Strategy.HighQuality";
            }
            if (i == 3) {
                z3 = true;
            }
            if (z3) {
                return "Strategy.Balanced";
            }
            return "Invalid";
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof Strategy)) {
                return false;
            }
            if (this.value != ((Strategy) obj).value) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return Integer.hashCode(this.value);
        }

        public final String toString() {
            return m358toStringimpl(this.value);
        }

        /* renamed from: unbox-impl  reason: not valid java name */
        public final /* synthetic */ int m359unboximpl() {
            return this.value;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Strictness {
        private final int value;

        private /* synthetic */ Strictness(int i) {
            this.value = i;
        }

        /* renamed from: box-impl  reason: not valid java name */
        public static final /* synthetic */ Strictness m360boximpl(int i) {
            return new Strictness(i);
        }

        /* renamed from: toString-impl  reason: not valid java name */
        public static String m361toStringimpl(int i) {
            boolean z;
            boolean z2;
            boolean z3;
            boolean z4 = false;
            if (i == 1) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return "Strictness.None";
            }
            if (i == 2) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                return "Strictness.Loose";
            }
            if (i == 3) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z3) {
                return "Strictness.Normal";
            }
            if (i == 4) {
                z4 = true;
            }
            if (z4) {
                return "Strictness.Strict";
            }
            return "Invalid";
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof Strictness)) {
                return false;
            }
            if (this.value != ((Strictness) obj).value) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return Integer.hashCode(this.value);
        }

        public final String toString() {
            return m361toStringimpl(this.value);
        }

        /* renamed from: unbox-impl  reason: not valid java name */
        public final /* synthetic */ int m362unboximpl() {
            return this.value;
        }
    }

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class WordBreak {
        private final int value;

        private /* synthetic */ WordBreak(int i) {
            this.value = i;
        }

        /* renamed from: box-impl  reason: not valid java name */
        public static final /* synthetic */ WordBreak m363boximpl(int i) {
            return new WordBreak(i);
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof WordBreak)) {
                return false;
            }
            if (this.value != ((WordBreak) obj).value) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return Integer.hashCode(this.value);
        }

        public final String toString() {
            boolean z;
            int i = this.value;
            boolean z2 = false;
            if (i == 1) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return "WordBreak.None";
            }
            if (i == 2) {
                z2 = true;
            }
            if (z2) {
                return "WordBreak.Phrase";
            }
            return "Invalid";
        }

        /* renamed from: unbox-impl  reason: not valid java name */
        public final /* synthetic */ int m364unboximpl() {
            return this.value;
        }
    }

    private /* synthetic */ LineBreak(int i) {
        this.mask = i;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ LineBreak m355boximpl(int i) {
        return new LineBreak(i);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof LineBreak)) {
            return false;
        }
        if (this.mask != ((LineBreak) obj).mask) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Integer.hashCode(this.mask);
    }

    public final String toString() {
        boolean z;
        String str;
        StringBuilder sb = new StringBuilder("LineBreak(strategy=");
        int i = this.mask;
        sb.append((Object) Strategy.m358toStringimpl(i & 255));
        sb.append(", strictness=");
        sb.append((Object) Strictness.m361toStringimpl((i >> 8) & 255));
        sb.append(", wordBreak=");
        int i2 = (i >> 16) & 255;
        boolean z2 = false;
        if (i2 == 1) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            str = "WordBreak.None";
        } else {
            if (i2 == 2) {
                z2 = true;
            }
            if (z2) {
                str = "WordBreak.Phrase";
            } else {
                str = "Invalid";
            }
        }
        sb.append((Object) str);
        sb.append(')');
        return sb.toString();
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m356unboximpl() {
        return this.mask;
    }
}
