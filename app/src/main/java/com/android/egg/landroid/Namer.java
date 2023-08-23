package com.android.egg.landroid;

import android.content.res.Resources;
import com.android.egg.R;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public final class Namer {
    public static final int $stable = 8;
    private final Bag anyDescriptors;
    private final Bag atmoDescriptors;
    private RandomTable atmoTable;
    private final Bag constellations;
    private final Bag constellationsRare;
    private RandomTable constellationsTable;
    private RandomTable delimiterTable;
    private final Bag lifeDescriptors;
    private RandomTable lifeTable;
    private final Bag planetDescriptors;
    private final RandomTable planetTable;
    private final Bag planetTypes;
    private final Bag suffixes;
    private final Bag suffixesRare;
    private RandomTable suffixesTable;

    public Namer(Resources resources) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        String[] stringArray = resources.getStringArray(R.array.planet_descriptors);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray…array.planet_descriptors)");
        Bag bag = new Bag(stringArray);
        this.planetDescriptors = bag;
        String[] stringArray2 = resources.getStringArray(R.array.life_descriptors);
        Intrinsics.checkNotNullExpressionValue(stringArray2, "resources.getStringArray(R.array.life_descriptors)");
        Bag bag2 = new Bag(stringArray2);
        this.lifeDescriptors = bag2;
        String[] stringArray3 = resources.getStringArray(R.array.any_descriptors);
        Intrinsics.checkNotNullExpressionValue(stringArray3, "resources.getStringArray(R.array.any_descriptors)");
        Bag bag3 = new Bag(stringArray3);
        this.anyDescriptors = bag3;
        String[] stringArray4 = resources.getStringArray(R.array.atmo_descriptors);
        Intrinsics.checkNotNullExpressionValue(stringArray4, "resources.getStringArray(R.array.atmo_descriptors)");
        Bag bag4 = new Bag(stringArray4);
        this.atmoDescriptors = bag4;
        String[] stringArray5 = resources.getStringArray(R.array.planet_types);
        Intrinsics.checkNotNullExpressionValue(stringArray5, "resources.getStringArray(R.array.planet_types)");
        this.planetTypes = new Bag(stringArray5);
        String[] stringArray6 = resources.getStringArray(R.array.constellations);
        Intrinsics.checkNotNullExpressionValue(stringArray6, "resources.getStringArray(R.array.constellations)");
        Bag bag5 = new Bag(stringArray6);
        this.constellations = bag5;
        String[] stringArray7 = resources.getStringArray(R.array.constellations_rare);
        Intrinsics.checkNotNullExpressionValue(stringArray7, "resources.getStringArray…rray.constellations_rare)");
        Bag bag6 = new Bag(stringArray7);
        this.constellationsRare = bag6;
        String[] stringArray8 = resources.getStringArray(R.array.star_suffixes);
        Intrinsics.checkNotNullExpressionValue(stringArray8, "resources.getStringArray(R.array.star_suffixes)");
        Bag bag7 = new Bag(stringArray8);
        this.suffixes = bag7;
        String[] stringArray9 = resources.getStringArray(R.array.star_suffixes_rare);
        Intrinsics.checkNotNullExpressionValue(stringArray9, "resources.getStringArray…array.star_suffixes_rare)");
        Bag bag8 = new Bag(stringArray9);
        this.suffixesRare = bag8;
        Float valueOf = Float.valueOf(0.75f);
        this.planetTable = new RandomTable(new Pair(valueOf, bag), new Pair(Float.valueOf(0.25f), bag3));
        this.lifeTable = new RandomTable(new Pair(valueOf, bag2), new Pair(Float.valueOf(0.25f), bag3));
        this.constellationsTable = new RandomTable(new Pair(Float.valueOf(0.05f), bag6), new Pair(Float.valueOf(0.95f), bag5));
        this.suffixesTable = new RandomTable(new Pair(Float.valueOf(0.05f), bag8), new Pair(Float.valueOf(0.95f), bag7));
        this.atmoTable = new RandomTable(new Pair(valueOf, bag4), new Pair(Float.valueOf(0.25f), bag3));
        this.delimiterTable = new RandomTable(new Pair(Float.valueOf(15.0f), " "), new Pair(Float.valueOf(3.0f), "-"), new Pair(Float.valueOf(1.0f), "_"), new Pair(Float.valueOf(1.0f), "/"), new Pair(Float.valueOf(1.0f), "."), new Pair(Float.valueOf(1.0f), "*"), new Pair(Float.valueOf(1.0f), "^"), new Pair(Float.valueOf(1.0f), "#"), new Pair(Float.valueOf(0.1f), "(^*!%@##!!"));
    }

    public final String describeAtmo(Random rng) {
        Intrinsics.checkNotNullParameter(rng, "rng");
        Object pull = ((Bag) this.atmoTable.roll(rng)).pull(rng);
        Intrinsics.checkNotNullExpressionValue(pull, "atmoTable.roll(rng).pull(rng)");
        return (String) pull;
    }

    public final String describeLife(Random rng) {
        Intrinsics.checkNotNullParameter(rng, "rng");
        Object pull = ((Bag) this.lifeTable.roll(rng)).pull(rng);
        Intrinsics.checkNotNullExpressionValue(pull, "lifeTable.roll(rng).pull(rng)");
        return (String) pull;
    }

    public final String describePlanet(Random rng) {
        Intrinsics.checkNotNullParameter(rng, "rng");
        Object pull = ((Bag) this.planetTable.roll(rng)).pull(rng);
        Object pull2 = this.planetTypes.pull(rng);
        return pull + " " + pull2;
    }

    public final String nameSystem(Random rng) {
        Intrinsics.checkNotNullParameter(rng, "rng");
        StringBuilder sb = new StringBuilder();
        sb.append((String) ((Bag) this.constellationsTable.roll(rng)).pull(rng));
        if (rng.nextFloat() <= 0.75f) {
            sb.append((String) this.delimiterTable.roll(rng));
            sb.append((String) ((Bag) this.suffixesTable.roll(rng)).pull(rng));
            if (rng.nextFloat() <= 0.05f) {
                sb.append(' ');
                sb.append((String) this.suffixesRare.pull(rng));
            }
        }
        if (rng.nextFloat() <= 0.3f) {
            sb.append((String) this.delimiterTable.roll(rng));
            sb.append((char) (rng.nextInt(0, 26) + 65));
            if (rng.nextFloat() <= 0.05f) {
                sb.append((String) this.delimiterTable.roll(rng));
            }
        }
        if (rng.nextFloat() <= 0.3f) {
            sb.append((String) this.delimiterTable.roll(rng));
            sb.append(rng.nextInt(2, 5039));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "parts.toString()");
        return sb2;
    }
}
