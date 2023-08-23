package kotlin.text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.TransformingSequence;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class StringsKt extends StringsKt___StringsKt {
    public static String capitalize(String str) {
        boolean z;
        Intrinsics.checkNotNullParameter(str, "<this>");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        if (str.length() > 0) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            char charAt = str.charAt(0);
            if (Character.isLowerCase(charAt)) {
                StringBuilder sb = new StringBuilder();
                char titleCase = Character.toTitleCase(charAt);
                if (titleCase != Character.toUpperCase(charAt)) {
                    sb.append(titleCase);
                } else {
                    String substring = str.substring(0, 1);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    String upperCase = substring.toUpperCase(locale);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
                    sb.append(upperCase);
                }
                String substring2 = str.substring(1);
                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
                sb.append(substring2);
                String sb2 = sb.toString();
                Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
                return sb2;
            }
            return str;
        }
        return str;
    }

    public static boolean contains$default(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (indexOf$default(charSequence, ":", 0, false, 2) < 0) {
            return false;
        }
        return true;
    }

    public static int indexOf$default(CharSequence charSequence, String string, int i, boolean z, int i2) {
        int indexOf$StringsKt__StringsKt;
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(string, "string");
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(string, i);
        }
        indexOf$StringsKt__StringsKt = StringsKt__StringsKt.indexOf$StringsKt__StringsKt(charSequence, string, i, charSequence.length(), z, false);
        return indexOf$StringsKt__StringsKt;
    }

    public static boolean isBlank(CharSequence charSequence) {
        boolean z;
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        if (charSequence.length() == 0) {
            return true;
        }
        IntRange intRange = new IntRange(0, charSequence.length() - 1);
        if (!(intRange instanceof Collection) || !((Collection) intRange).isEmpty()) {
            Iterator it = intRange.iterator();
            while (((IntProgressionIterator) it).hasNext()) {
                if (!CharsKt__CharKt.isWhitespace(charSequence.charAt(((IntProgressionIterator) it).nextInt()))) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        if (z) {
            return true;
        }
        return false;
    }

    public static char last(CharSequence charSequence) {
        boolean z;
        if (charSequence.length() == 0) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return charSequence.charAt(StringsKt__StringsKt.getLastIndex(charSequence));
        }
        throw new NoSuchElementException("Char sequence is empty.");
    }

    public static String substringAfter$default(String str, String delimiter) {
        Intrinsics.checkNotNullParameter(delimiter, "delimiter");
        int indexOf$default = indexOf$default((CharSequence) str, delimiter, 0, false, 6);
        if (indexOf$default != -1) {
            String substring = str.substring(delimiter.length() + indexOf$default, str.length());
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        return str;
    }

    public static String substringAfterLast$default(String missingDelimiterValue) {
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "<this>");
        Intrinsics.checkNotNullParameter(missingDelimiterValue, "missingDelimiterValue");
        int lastIndexOf = missingDelimiterValue.lastIndexOf(46, StringsKt__StringsKt.getLastIndex(missingDelimiterValue));
        if (lastIndexOf != -1) {
            String substring = missingDelimiterValue.substring(lastIndexOf + 1, missingDelimiterValue.length());
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return substring;
        }
        return missingDelimiterValue;
    }

    public static CharSequence trim(CharSequence charSequence) {
        int i;
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        int length = charSequence.length() - 1;
        int i2 = 0;
        boolean z = false;
        while (i2 <= length) {
            if (!z) {
                i = i2;
            } else {
                i = length;
            }
            boolean isWhitespace = CharsKt__CharKt.isWhitespace(charSequence.charAt(i));
            if (!z) {
                if (!isWhitespace) {
                    z = true;
                } else {
                    i2++;
                }
            } else if (!isWhitespace) {
                break;
            } else {
                length--;
            }
        }
        return charSequence.subSequence(i2, length + 1);
    }

    public static String trimIndent(final String str) {
        Comparable comparable;
        int i;
        String str2;
        boolean z;
        Intrinsics.checkNotNullParameter(str, "<this>");
        StringsKt__StringsKt.requireNonNegativeLimit(0);
        final List asList = Arrays.asList("\r\n", "\n", "\r");
        Intrinsics.checkNotNullExpressionValue(asList, "asList(this)");
        List list = SequencesKt.toList(new TransformingSequence(new DelimitedRangesSequence(str, 0, 0, new Function2() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                Object obj3;
                Pair pair;
                Object obj4;
                CharSequence $receiver = (CharSequence) obj;
                int intValue = ((Number) obj2).intValue();
                Intrinsics.checkNotNullParameter($receiver, "$this$$receiver");
                List list2 = asList;
                boolean z2 = r2;
                if (!z2 && list2.size() == 1) {
                    int size = list2.size();
                    if (size != 0) {
                        if (size == 1) {
                            String str3 = (String) list2.get(0);
                            int indexOf$default = StringsKt.indexOf$default($receiver, str3, intValue, false, 4);
                            if (indexOf$default >= 0) {
                                pair = new Pair(Integer.valueOf(indexOf$default), str3);
                            }
                            pair = null;
                        } else {
                            throw new IllegalArgumentException("List has more than one element.");
                        }
                    } else {
                        throw new NoSuchElementException("List is empty.");
                    }
                } else {
                    if (intValue < 0) {
                        intValue = 0;
                    }
                    IntRange intRange = new IntRange(intValue, $receiver.length());
                    if ($receiver instanceof String) {
                        int first = intRange.getFirst();
                        int last = intRange.getLast();
                        int step = intRange.getStep();
                        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
                            while (true) {
                                Iterator it = list2.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        obj4 = it.next();
                                        String str4 = (String) obj4;
                                        if (StringsKt__StringsKt.regionMatches(first, str4.length(), str4, (String) $receiver, z2)) {
                                            break;
                                        }
                                    } else {
                                        obj4 = null;
                                        break;
                                    }
                                }
                                String str5 = (String) obj4;
                                if (str5 != null) {
                                    pair = new Pair(Integer.valueOf(first), str5);
                                    break;
                                } else if (first == last) {
                                    break;
                                } else {
                                    first += step;
                                }
                            }
                        }
                        pair = null;
                    } else {
                        int first2 = intRange.getFirst();
                        int last2 = intRange.getLast();
                        int step2 = intRange.getStep();
                        if ((step2 > 0 && first2 <= last2) || (step2 < 0 && last2 <= first2)) {
                            while (true) {
                                Iterator it2 = list2.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        obj3 = it2.next();
                                        String str6 = (String) obj3;
                                        if (StringsKt__StringsKt.regionMatchesImpl(str6, $receiver, first2, str6.length(), z2)) {
                                            break;
                                        }
                                    } else {
                                        obj3 = null;
                                        break;
                                    }
                                }
                                String str7 = (String) obj3;
                                if (str7 != null) {
                                    pair = new Pair(Integer.valueOf(first2), str7);
                                    break;
                                } else if (first2 == last2) {
                                    break;
                                } else {
                                    first2 += step2;
                                }
                            }
                        }
                        pair = null;
                    }
                }
                if (pair == null) {
                    return null;
                }
                return new Pair(pair.getFirst(), Integer.valueOf(((String) pair.getSecond()).length()));
            }
        }), new Function1() { // from class: kotlin.text.StringsKt__StringsKt$splitToSequence$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                IntRange it = (IntRange) obj;
                Intrinsics.checkNotNullParameter(it, "it");
                CharSequence charSequence = str;
                Intrinsics.checkNotNullParameter(charSequence, "<this>");
                return charSequence.subSequence(Integer.valueOf(it.getFirst()).intValue(), Integer.valueOf(it.getLast()).intValue() + 1).toString();
            }
        }));
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (true ^ isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            int length = str3.length();
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    if (!CharsKt__CharKt.isWhitespace(str3.charAt(i2))) {
                        break;
                    }
                    i2++;
                } else {
                    i2 = -1;
                    break;
                }
            }
            if (i2 == -1) {
                i2 = str3.length();
            }
            arrayList2.add(Integer.valueOf(i2));
        }
        Iterator it2 = arrayList2.iterator();
        if (!it2.hasNext()) {
            comparable = null;
        } else {
            comparable = (Comparable) it2.next();
            while (it2.hasNext()) {
                Comparable comparable2 = (Comparable) it2.next();
                if (comparable.compareTo(comparable2) > 0) {
                    comparable = comparable2;
                }
            }
        }
        Integer num = (Integer) comparable;
        if (num != null) {
            i = num.intValue();
        } else {
            i = 0;
        }
        int size = (list.size() * 0) + str.length();
        StringsKt__IndentKt$getIndentFunction$1 stringsKt__IndentKt$getIndentFunction$1 = StringsKt__IndentKt$getIndentFunction$1.INSTANCE;
        int lastIndex = CollectionsKt.getLastIndex(list);
        ArrayList arrayList3 = new ArrayList();
        int i3 = 0;
        for (Object obj2 : list) {
            int i4 = i3 + 1;
            if (i3 >= 0) {
                String str4 = (String) obj2;
                if ((i3 == 0 || i3 == lastIndex) && isBlank(str4)) {
                    str2 = null;
                } else {
                    Intrinsics.checkNotNullParameter(str4, "<this>");
                    if (i >= 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        int length2 = str4.length();
                        if (i <= length2) {
                            length2 = i;
                        }
                        String substring = str4.substring(length2);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                        str2 = (String) stringsKt__IndentKt$getIndentFunction$1.invoke(substring);
                    } else {
                        throw new IllegalArgumentException(("Requested character count " + i + " is less than zero.").toString());
                    }
                }
                if (str2 != null) {
                    arrayList3.add(str2);
                }
                i3 = i4;
            } else {
                CollectionsKt.throwIndexOverflow();
                throw null;
            }
        }
        StringBuilder sb = new StringBuilder(size);
        CollectionsKt___CollectionsKt.joinTo(arrayList3, sb, "\n", "", "", -1, "...", null);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb2;
    }

    public static int indexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(c, i);
        }
        char[] cArr = {c};
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(c, i);
        }
        if (i < 0) {
            i = 0;
        }
        IntProgressionIterator it = new IntRange(i, StringsKt__StringsKt.getLastIndex(charSequence)).iterator();
        while (it.hasNext()) {
            int nextInt = it.nextInt();
            if (CharsKt__CharKt.equals(cArr[0], charSequence.charAt(nextInt), z)) {
                return nextInt;
            }
        }
        return -1;
    }
}
