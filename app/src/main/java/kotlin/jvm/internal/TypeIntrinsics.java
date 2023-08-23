package kotlin.jvm.internal;

import androidx.compose.runtime.internal.ComposableLambda;
import java.util.Collection;
import java.util.Map;
import kotlin.Function;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableMap;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class TypeIntrinsics {
    public static Collection asMutableCollection(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableCollection)) {
            throwCce(obj, "kotlin.collections.MutableCollection");
            throw null;
        }
        try {
            return (Collection) obj;
        } catch (ClassCastException e) {
            Intrinsics.sanitizeStackTrace(TypeIntrinsics.class.getName(), e);
            throw e;
        }
    }

    public static Map asMutableMap(Object obj) {
        if ((obj instanceof KMappedMarker) && !(obj instanceof KMutableMap)) {
            throwCce(obj, "kotlin.collections.MutableMap");
            throw null;
        }
        try {
            return (Map) obj;
        } catch (ClassCastException e) {
            Intrinsics.sanitizeStackTrace(TypeIntrinsics.class.getName(), e);
            throw e;
        }
    }

    public static void beforeCheckcastToFunctionOfArity(int i, Object obj) {
        if (obj != null && !isFunctionOfArity(i, obj)) {
            throwCce(obj, "kotlin.jvm.functions.Function" + i);
            throw null;
        }
    }

    public static boolean isFunctionOfArity(int i, Object obj) {
        int i2;
        if (!(obj instanceof Function)) {
            return false;
        }
        if (obj instanceof FunctionBase) {
            i2 = ((FunctionBase) obj).getArity();
        } else if (obj instanceof Function0) {
            i2 = 0;
        } else if (obj instanceof Function1) {
            i2 = 1;
        } else if (obj instanceof Function2) {
            i2 = 2;
        } else if (obj instanceof Function3) {
            i2 = 3;
        } else if (obj instanceof Function4) {
            i2 = 4;
        } else if (obj instanceof Function5) {
            i2 = 5;
        } else {
            boolean z = obj instanceof ComposableLambda;
            if (z) {
                i2 = 6;
            } else if (z) {
                i2 = 7;
            } else if (z) {
                i2 = 8;
            } else if (z) {
                i2 = 9;
            } else if (z) {
                i2 = 10;
            } else if (z) {
                i2 = 11;
            } else if (z) {
                i2 = 13;
            } else if (z) {
                i2 = 14;
            } else if (z) {
                i2 = 15;
            } else if (z) {
                i2 = 16;
            } else if (z) {
                i2 = 17;
            } else if (z) {
                i2 = 18;
            } else if (z) {
                i2 = 19;
            } else if (z) {
                i2 = 20;
            } else if (z) {
                i2 = 21;
            } else {
                i2 = -1;
            }
        }
        if (i2 != i) {
            return false;
        }
        return true;
    }

    public static void throwCce(Object obj, String str) {
        String name;
        if (obj == null) {
            name = "null";
        } else {
            name = obj.getClass().getName();
        }
        ClassCastException classCastException = new ClassCastException(name + " cannot be cast to " + str);
        Intrinsics.sanitizeStackTrace(TypeIntrinsics.class.getName(), classCastException);
        throw classCastException;
    }
}
