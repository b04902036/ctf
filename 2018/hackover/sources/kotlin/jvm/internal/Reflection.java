package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;

public class Reflection {
    private static final KClass[] EMPTY_K_CLASS_ARRAY = new KClass[0];
    static final String REFLECTION_NOT_AVAILABLE = " (Kotlin reflection is not available)";
    private static final ReflectionFactory factory;

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000e A:{PHI: r0 , Splitter: B:1:0x0001, ExcHandler: java.lang.ClassCastException (unused java.lang.ClassCastException)} */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x000e A:{PHI: r0 , Splitter: B:1:0x0001, ExcHandler: java.lang.ClassCastException (unused java.lang.ClassCastException)} */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x000e A:{PHI: r0 , Splitter: B:1:0x0001, ExcHandler: java.lang.ClassCastException (unused java.lang.ClassCastException)} */
    /* JADX WARNING: Missing block: B:4:0x000e, code:
            if (r0 == null) goto L_0x0011;
     */
    /* JADX WARNING: Missing block: B:5:0x0011, code:
            r0 = new kotlin.jvm.internal.ReflectionFactory();
     */
    /* JADX WARNING: Missing block: B:6:0x0016, code:
            factory = r0;
            EMPTY_K_CLASS_ARRAY = new kotlin.reflect.KClass[0];
     */
    /* JADX WARNING: Missing block: B:7:0x001d, code:
            return;
     */
    static {
        /*
        r0 = 0;
        r1 = "kotlin.reflect.jvm.internal.ReflectionFactoryImpl";
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassCastException -> 0x000e, ClassCastException -> 0x000e, ClassCastException -> 0x000e, ClassCastException -> 0x000e }
        r1 = r1.newInstance();	 Catch:{ ClassCastException -> 0x000e, ClassCastException -> 0x000e, ClassCastException -> 0x000e, ClassCastException -> 0x000e }
        r1 = (kotlin.jvm.internal.ReflectionFactory) r1;	 Catch:{ ClassCastException -> 0x000e, ClassCastException -> 0x000e, ClassCastException -> 0x000e, ClassCastException -> 0x000e }
        r0 = r1;
    L_0x000e:
        if (r0 == 0) goto L_0x0011;
    L_0x0010:
        goto L_0x0016;
    L_0x0011:
        r0 = new kotlin.jvm.internal.ReflectionFactory;
        r0.<init>();
    L_0x0016:
        factory = r0;
        r0 = 0;
        r0 = new kotlin.reflect.KClass[r0];
        EMPTY_K_CLASS_ARRAY = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.internal.Reflection.<clinit>():void");
    }

    public static KClass createKotlinClass(Class cls) {
        return factory.createKotlinClass(cls);
    }

    public static KClass createKotlinClass(Class cls, String str) {
        return factory.createKotlinClass(cls, str);
    }

    public static KDeclarationContainer getOrCreateKotlinPackage(Class cls, String str) {
        return factory.getOrCreateKotlinPackage(cls, str);
    }

    public static KClass getOrCreateKotlinClass(Class cls) {
        return factory.getOrCreateKotlinClass(cls);
    }

    public static KClass getOrCreateKotlinClass(Class cls, String str) {
        return factory.getOrCreateKotlinClass(cls, str);
    }

    public static KClass[] getOrCreateKotlinClasses(Class[] clsArr) {
        int length = clsArr.length;
        if (length == 0) {
            return EMPTY_K_CLASS_ARRAY;
        }
        KClass[] kClassArr = new KClass[length];
        for (int i = 0; i < length; i++) {
            kClassArr[i] = getOrCreateKotlinClass(clsArr[i]);
        }
        return kClassArr;
    }

    @SinceKotlin(version = "1.1")
    public static String renderLambdaToString(Lambda lambda) {
        return factory.renderLambdaToString(lambda);
    }

    public static KFunction function(FunctionReference functionReference) {
        return factory.function(functionReference);
    }

    public static KProperty0 property0(PropertyReference0 propertyReference0) {
        return factory.property0(propertyReference0);
    }

    public static KMutableProperty0 mutableProperty0(MutablePropertyReference0 mutablePropertyReference0) {
        return factory.mutableProperty0(mutablePropertyReference0);
    }

    public static KProperty1 property1(PropertyReference1 propertyReference1) {
        return factory.property1(propertyReference1);
    }

    public static KMutableProperty1 mutableProperty1(MutablePropertyReference1 mutablePropertyReference1) {
        return factory.mutableProperty1(mutablePropertyReference1);
    }

    public static KProperty2 property2(PropertyReference2 propertyReference2) {
        return factory.property2(propertyReference2);
    }

    public static KMutableProperty2 mutableProperty2(MutablePropertyReference2 mutablePropertyReference2) {
        return factory.mutableProperty2(mutablePropertyReference2);
    }
}
