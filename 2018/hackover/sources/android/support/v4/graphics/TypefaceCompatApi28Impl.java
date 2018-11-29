package android.support.v4.graphics;

import android.graphics.Typeface;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

@RequiresApi(28)
@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompatApi28Impl extends TypefaceCompatApi26Impl {
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String DEFAULT_FAMILY = "sans-serif";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi28Impl";

    /* JADX WARNING: Removed duplicated region for block: B:3:0x002d A:{Splitter: B:0:0x0000, ExcHandler: java.lang.IllegalAccessException (r4_4 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x002d, code:
            r4 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0033, code:
            throw new java.lang.RuntimeException(r4);
     */
    protected android.graphics.Typeface createFromFamiliesWithDefault(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = r4.mFontFamily;	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r1 = 1;
        r0 = java.lang.reflect.Array.newInstance(r0, r1);	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r2 = 0;
        java.lang.reflect.Array.set(r0, r2, r5);	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r4 = r4.mCreateFromFamiliesWithDefault;	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r5 = 0;
        r3 = 4;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r3[r2] = r0;	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r0 = "sans-serif";
        r3[r1] = r0;	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r0 = 2;
        r1 = -1;
        r2 = java.lang.Integer.valueOf(r1);	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r3[r0] = r2;	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r0 = 3;
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r3[r0] = r1;	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r4 = r4.invoke(r5, r3);	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        r4 = (android.graphics.Typeface) r4;	 Catch:{ IllegalAccessException -> 0x002d, IllegalAccessException -> 0x002d }
        return r4;
    L_0x002d:
        r4 = move-exception;
        r5 = new java.lang.RuntimeException;
        r5.<init>(r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi28Impl.createFromFamiliesWithDefault(java.lang.Object):android.graphics.Typeface");
    }

    protected Method obtainCreateFromFamiliesWithDefaultMethod(Class cls) throws NoSuchMethodException {
        Object newInstance = Array.newInstance(cls, 1);
        Method declaredMethod = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, new Class[]{newInstance.getClass(), String.class, Integer.TYPE, Integer.TYPE});
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
