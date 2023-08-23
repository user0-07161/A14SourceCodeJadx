package kotlin.random;

import java.io.Serializable;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Random {
    public static final Default Default = new Default(0);
    private static final Random defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();

    /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
    /* loaded from: classes.dex */
    public final class Default extends Random implements Serializable {

        /* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
        /* loaded from: classes.dex */
        final class Serialized implements Serializable {
            public static final Serialized INSTANCE = new Serialized();
            private static final long serialVersionUID = 0;

            private Serialized() {
            }

            private final Object readResolve() {
                return Random.Default;
            }
        }

        public /* synthetic */ Default(int i) {
            this();
        }

        private final Object writeReplace() {
            return Serialized.INSTANCE;
        }

        @Override // kotlin.random.Random
        public final int nextBits(int i) {
            return Random.defaultRandom.nextBits(i);
        }

        @Override // kotlin.random.Random
        public final float nextFloat() {
            return Random.defaultRandom.nextFloat();
        }

        @Override // kotlin.random.Random
        public final int nextInt() {
            return Random.defaultRandom.nextInt();
        }

        @Override // kotlin.random.Random
        public final long nextLong() {
            return Random.defaultRandom.nextLong();
        }

        private Default() {
        }

        @Override // kotlin.random.Random
        public final int nextInt(int i) {
            return Random.defaultRandom.nextInt(i);
        }

        @Override // kotlin.random.Random
        public final int nextInt(int i, int i2) {
            return Random.defaultRandom.nextInt(i, i2);
        }
    }

    public abstract int nextBits(int i);

    public float nextFloat() {
        return nextBits(24) / 1.6777216E7f;
    }

    public abstract int nextInt();

    public int nextInt(int i) {
        return nextInt(0, i);
    }

    public long nextLong() {
        return (nextInt() << 32) + nextInt();
    }

    public int nextInt(int i, int i2) {
        int nextInt;
        int i3;
        int i4;
        int nextInt2;
        boolean z;
        if (!(i2 > i)) {
            Integer from = Integer.valueOf(i);
            Integer until = Integer.valueOf(i2);
            Intrinsics.checkNotNullParameter(from, "from");
            Intrinsics.checkNotNullParameter(until, "until");
            throw new IllegalArgumentException(("Random range is empty: [" + from + ", " + until + ").").toString());
        }
        int i5 = i2 - i;
        if (i5 > 0 || i5 == Integer.MIN_VALUE) {
            if (((-i5) & i5) == i5) {
                i4 = nextBits(31 - Integer.numberOfLeadingZeros(i5));
            } else {
                do {
                    nextInt = nextInt() >>> 1;
                    i3 = nextInt % i5;
                } while ((i5 - 1) + (nextInt - i3) < 0);
                i4 = i3;
            }
            return i + i4;
        }
        do {
            nextInt2 = nextInt();
            if (i > nextInt2 || nextInt2 >= i2) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
        } while (!z);
        return nextInt2;
    }
}
