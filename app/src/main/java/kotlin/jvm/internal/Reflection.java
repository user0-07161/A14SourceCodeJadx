package kotlin.jvm.internal;

/* compiled from: go/retraceme 56a29ddcb1e7781e96a7f0eae9764fd95150a8d849b28fe37c764d7d19c0db72 */
/* loaded from: classes.dex */
public abstract class Reflection {
    private static final ReflectionFactory factory;

    static {
        ReflectionFactory reflectionFactory = null;
        try {
            reflectionFactory = (ReflectionFactory) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (reflectionFactory == null) {
            reflectionFactory = new ReflectionFactory();
        }
        factory = reflectionFactory;
    }

    public static void function(FunctionReference functionReference) {
        factory.getClass();
    }

    public static ClassReference getOrCreateKotlinClass(Class cls) {
        factory.getClass();
        return new ClassReference(cls);
    }

    public static PackageReference getOrCreateKotlinPackage(Class cls) {
        factory.getClass();
        return new PackageReference(cls);
    }

    public static void mutableProperty1(MutablePropertyReference1 mutablePropertyReference1) {
        factory.getClass();
    }

    public static void property0(PropertyReference0 propertyReference0) {
        factory.getClass();
    }

    public static String renderLambdaToString(Lambda lambda) {
        factory.getClass();
        return ReflectionFactory.renderLambdaToString(lambda);
    }

    public static String renderLambdaToString(FunctionBase functionBase) {
        factory.getClass();
        return ReflectionFactory.renderLambdaToString(functionBase);
    }
}
