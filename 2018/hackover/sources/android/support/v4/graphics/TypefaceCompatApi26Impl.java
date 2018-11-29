package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.Typeface.Builder;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.v4.content.res.FontResourcesParserCompat.FontFileResourceEntry;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.provider.FontsContractCompat.FontInfo;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

@RequiresApi(26)
@RestrictTo({Scope.LIBRARY_GROUP})
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String DEFAULT_FAMILY = "sans-serif";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    protected final Method mAbortCreation;
    protected final Method mAddFontFromAssetManager;
    protected final Method mAddFontFromBuffer;
    protected final Method mCreateFromFamiliesWithDefault;
    protected final Class mFontFamily;
    protected final Constructor mFontFamilyCtor;
    protected final Method mFreeze;

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0022 A:{Splitter: B:1:0x0004, ExcHandler: java.lang.ClassNotFoundException (r1_1 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:4:0x0022, code:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0023, code:
            r2 = TAG;
            r3 = new java.lang.StringBuilder();
            r3.append("Unable to collect necessary methods for class ");
            r3.append(r1.getClass().getName());
            android.util.Log.e(r2, r3.toString(), r1);
            r2 = null;
            r3 = r2;
            r4 = r3;
            r5 = r4;
            r6 = r5;
            r7 = r6;
     */
    public TypefaceCompatApi26Impl() {
        /*
        r8 = this;
        r8.<init>();
        r0 = 0;
        r1 = r8.obtainFontFamily();	 Catch:{ ClassNotFoundException -> 0x0022, ClassNotFoundException -> 0x0022 }
        r2 = r8.obtainFontFamilyCtor(r1);	 Catch:{ ClassNotFoundException -> 0x0022, ClassNotFoundException -> 0x0022 }
        r3 = r8.obtainAddFontFromAssetManagerMethod(r1);	 Catch:{ ClassNotFoundException -> 0x0022, ClassNotFoundException -> 0x0022 }
        r4 = r8.obtainAddFontFromBufferMethod(r1);	 Catch:{ ClassNotFoundException -> 0x0022, ClassNotFoundException -> 0x0022 }
        r5 = r8.obtainFreezeMethod(r1);	 Catch:{ ClassNotFoundException -> 0x0022, ClassNotFoundException -> 0x0022 }
        r6 = r8.obtainAbortCreationMethod(r1);	 Catch:{ ClassNotFoundException -> 0x0022, ClassNotFoundException -> 0x0022 }
        r7 = r8.obtainCreateFromFamiliesWithDefaultMethod(r1);	 Catch:{ ClassNotFoundException -> 0x0022, ClassNotFoundException -> 0x0022 }
        r0 = r1;
        goto L_0x0047;
    L_0x0022:
        r1 = move-exception;
        r2 = "TypefaceCompatApi26Impl";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "Unable to collect necessary methods for class ";
        r3.append(r4);
        r4 = r1.getClass();
        r4 = r4.getName();
        r3.append(r4);
        r3 = r3.toString();
        android.util.Log.e(r2, r3, r1);
        r2 = r0;
        r3 = r2;
        r4 = r3;
        r5 = r4;
        r6 = r5;
        r7 = r6;
    L_0x0047:
        r8.mFontFamily = r0;
        r8.mFontFamilyCtor = r2;
        r8.mAddFontFromAssetManager = r3;
        r8.mAddFontFromBuffer = r4;
        r8.mFreeze = r5;
        r8.mAbortCreation = r6;
        r8.mCreateFromFamiliesWithDefault = r7;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.<init>():void");
    }

    private boolean isFontFamilyPrivateAPIAvailable() {
        if (this.mAddFontFromAssetManager == null) {
            Log.w(TAG, "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.mAddFontFromAssetManager != null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000a A:{Splitter: B:0:0x0000, ExcHandler: java.lang.IllegalAccessException (r1_3 'e' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000a A:{Splitter: B:0:0x0000, ExcHandler: java.lang.IllegalAccessException (r1_3 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x000a, code:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0010, code:
            throw new java.lang.RuntimeException(r1);
     */
    private java.lang.Object newFamily() {
        /*
        r1 = this;
        r1 = r1.mFontFamilyCtor;	 Catch:{ IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a }
        r0 = 0;
        r0 = new java.lang.Object[r0];	 Catch:{ IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a }
        r1 = r1.newInstance(r0);	 Catch:{ IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a, IllegalAccessException -> 0x000a }
        return r1;
    L_0x000a:
        r1 = move-exception;
        r0 = new java.lang.RuntimeException;
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.newFamily():java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0041 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.IllegalAccessException (r2_5 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x0041, code:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0047, code:
            throw new java.lang.RuntimeException(r2);
     */
    private boolean addFontFromAssetManager(android.content.Context r3, java.lang.Object r4, java.lang.String r5, int r6, int r7, int r8, @android.support.annotation.Nullable android.graphics.fonts.FontVariationAxis[] r9) {
        /*
        r2 = this;
        r2 = r2.mAddFontFromAssetManager;	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r0 = 8;
        r0 = new java.lang.Object[r0];	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r3 = r3.getAssets();	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r1 = 0;
        r0[r1] = r3;	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r3 = 1;
        r0[r3] = r5;	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r3 = 2;
        r5 = java.lang.Integer.valueOf(r1);	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r0[r3] = r5;	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r3 = 3;
        r5 = java.lang.Boolean.valueOf(r1);	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r0[r3] = r5;	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r3 = 4;
        r5 = java.lang.Integer.valueOf(r6);	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r0[r3] = r5;	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r3 = 5;
        r5 = java.lang.Integer.valueOf(r7);	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r0[r3] = r5;	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r3 = 6;
        r5 = java.lang.Integer.valueOf(r8);	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r0[r3] = r5;	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r3 = 7;
        r0[r3] = r9;	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r2 = r2.invoke(r4, r0);	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r2 = (java.lang.Boolean) r2;	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        r2 = r2.booleanValue();	 Catch:{ IllegalAccessException -> 0x0041, IllegalAccessException -> 0x0041 }
        return r2;
    L_0x0041:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r3.<init>(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.addFontFromAssetManager(android.content.Context, java.lang.Object, java.lang.String, int, int, int, android.graphics.fonts.FontVariationAxis[]):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x002c A:{Splitter: B:0:0x0000, ExcHandler: java.lang.IllegalAccessException (r2_5 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x002c, code:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0032, code:
            throw new java.lang.RuntimeException(r2);
     */
    private boolean addFontFromBuffer(java.lang.Object r3, java.nio.ByteBuffer r4, int r5, int r6, int r7) {
        /*
        r2 = this;
        r2 = r2.mAddFontFromBuffer;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r0 = 5;
        r0 = new java.lang.Object[r0];	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r1 = 0;
        r0[r1] = r4;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r4 = 1;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r0[r4] = r5;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r4 = 2;
        r5 = 0;
        r0[r4] = r5;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r4 = 3;
        r5 = java.lang.Integer.valueOf(r6);	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r0[r4] = r5;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r4 = 4;
        r5 = java.lang.Integer.valueOf(r7);	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r0[r4] = r5;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r2 = r2.invoke(r3, r0);	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r2 = (java.lang.Boolean) r2;	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        r2 = r2.booleanValue();	 Catch:{ IllegalAccessException -> 0x002c, IllegalAccessException -> 0x002c }
        return r2;
    L_0x002c:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r3.<init>(r2);
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.addFontFromBuffer(java.lang.Object, java.nio.ByteBuffer, int, int, int):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0028 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.IllegalAccessException (r4_4 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x0028, code:
            r4 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x002e, code:
            throw new java.lang.RuntimeException(r4);
     */
    protected android.graphics.Typeface createFromFamiliesWithDefault(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = r4.mFontFamily;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r1 = 1;
        r0 = java.lang.reflect.Array.newInstance(r0, r1);	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r2 = 0;
        java.lang.reflect.Array.set(r0, r2, r5);	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r4 = r4.mCreateFromFamiliesWithDefault;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r5 = 0;
        r3 = 3;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r3[r2] = r0;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r0 = -1;
        r2 = java.lang.Integer.valueOf(r0);	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r3[r1] = r2;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r1 = 2;
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r3[r1] = r0;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r4 = r4.invoke(r5, r3);	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        r4 = (android.graphics.Typeface) r4;	 Catch:{ IllegalAccessException -> 0x0028, IllegalAccessException -> 0x0028 }
        return r4;
    L_0x0028:
        r4 = move-exception;
        r5 = new java.lang.RuntimeException;
        r5.<init>(r4);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.createFromFamiliesWithDefault(java.lang.Object):android.graphics.Typeface");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0010 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.IllegalAccessException (r1_5 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x0010, code:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x0016, code:
            throw new java.lang.RuntimeException(r1);
     */
    private boolean freeze(java.lang.Object r2) {
        /*
        r1 = this;
        r1 = r1.mFreeze;	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r0 = 0;
        r0 = new java.lang.Object[r0];	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r1 = r1.invoke(r2, r0);	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r1 = (java.lang.Boolean) r1;	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        r1 = r1.booleanValue();	 Catch:{ IllegalAccessException -> 0x0010, IllegalAccessException -> 0x0010 }
        return r1;
    L_0x0010:
        r1 = move-exception;
        r2 = new java.lang.RuntimeException;
        r2.<init>(r1);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.freeze(java.lang.Object):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0009 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.IllegalAccessException (r1_2 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x0009, code:
            r1 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x000f, code:
            throw new java.lang.RuntimeException(r1);
     */
    private void abortCreation(java.lang.Object r2) {
        /*
        r1 = this;
        r1 = r1.mAbortCreation;	 Catch:{ IllegalAccessException -> 0x0009, IllegalAccessException -> 0x0009 }
        r0 = 0;
        r0 = new java.lang.Object[r0];	 Catch:{ IllegalAccessException -> 0x0009, IllegalAccessException -> 0x0009 }
        r1.invoke(r2, r0);	 Catch:{ IllegalAccessException -> 0x0009, IllegalAccessException -> 0x0009 }
        return;
    L_0x0009:
        r1 = move-exception;
        r2 = new java.lang.RuntimeException;
        r2.<init>(r1);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.abortCreation(java.lang.Object):void");
    }

    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, fontFamilyFilesResourceEntry, resources, i);
        }
        Object newFamily = newFamily();
        FontFileResourceEntry[] entries = fontFamilyFilesResourceEntry.getEntries();
        i = entries.length;
        int i2 = 0;
        while (i2 < i) {
            FontFileResourceEntry fontFileResourceEntry = entries[i2];
            if (addFontFromAssetManager(context, newFamily, fontFileResourceEntry.getFileName(), fontFileResourceEntry.getTtcIndex(), fontFileResourceEntry.getWeight(), fontFileResourceEntry.isItalic(), FontVariationAxis.fromFontVariationSettings(fontFileResourceEntry.getVariationSettings()))) {
                i2++;
            } else {
                abortCreation(newFamily);
                return null;
            }
        }
        if (freeze(newFamily)) {
            return createFromFamiliesWithDefault(newFamily);
        }
        return null;
    }

    public Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr, int i) {
        ParcelFileDescriptor openFileDescriptor;
        Throwable th;
        Throwable th2;
        if (fontInfoArr.length < 1) {
            return null;
        }
        if (isFontFamilyPrivateAPIAvailable()) {
            Map prepareFontData = FontsContractCompat.prepareFontData(context, fontInfoArr, cancellationSignal);
            Object newFamily = newFamily();
            Object obj = null;
            for (FontInfo fontInfo : fontInfoArr) {
                ByteBuffer byteBuffer = (ByteBuffer) prepareFontData.get(fontInfo.getUri());
                if (byteBuffer != null) {
                    if (addFontFromBuffer(newFamily, byteBuffer, fontInfo.getTtcIndex(), fontInfo.getWeight(), fontInfo.isItalic())) {
                        obj = 1;
                    } else {
                        abortCreation(newFamily);
                        return null;
                    }
                }
            }
            if (obj == null) {
                abortCreation(newFamily);
                return null;
            } else if (freeze(newFamily)) {
                return Typeface.create(createFromFamiliesWithDefault(newFamily), i);
            } else {
                return null;
            }
        }
        FontInfo findBestInfo = findBestInfo(fontInfoArr, i);
        try {
            openFileDescriptor = context.getContentResolver().openFileDescriptor(findBestInfo.getUri(), "r", cancellationSignal);
            if (openFileDescriptor == null) {
                if (openFileDescriptor != null) {
                    openFileDescriptor.close();
                }
                return null;
            }
            try {
                Typeface build = new Builder(openFileDescriptor.getFileDescriptor()).setWeight(findBestInfo.getWeight()).setItalic(findBestInfo.isItalic()).build();
                if (openFileDescriptor != null) {
                    openFileDescriptor.close();
                }
                return build;
            } catch (Throwable th22) {
                Throwable th3 = th22;
                th22 = th;
                th = th3;
            }
        } catch (IOException unused) {
            return null;
        }
        if (openFileDescriptor != null) {
            if (th22 != null) {
                try {
                    openFileDescriptor.close();
                } catch (Throwable th4) {
                    th22.addSuppressed(th4);
                }
            } else {
                openFileDescriptor.close();
            }
        }
        throw th;
        throw th;
    }

    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, i, str, i2);
        }
        Object newFamily = newFamily();
        if (!addFontFromAssetManager(context, newFamily, str, 0, -1, -1, null)) {
            abortCreation(newFamily);
            return null;
        } else if (freeze(newFamily)) {
            return createFromFamiliesWithDefault(newFamily);
        } else {
            return null;
        }
    }

    protected Class obtainFontFamily() throws ClassNotFoundException {
        return Class.forName(FONT_FAMILY_CLASS);
    }

    protected Constructor obtainFontFamilyCtor(Class cls) throws NoSuchMethodException {
        return cls.getConstructor(new Class[0]);
    }

    protected Method obtainAddFontFromAssetManagerMethod(Class cls) throws NoSuchMethodException {
        return cls.getMethod(ADD_FONT_FROM_ASSET_MANAGER_METHOD, new Class[]{AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class});
    }

    protected Method obtainAddFontFromBufferMethod(Class cls) throws NoSuchMethodException {
        return cls.getMethod(ADD_FONT_FROM_BUFFER_METHOD, new Class[]{ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE});
    }

    protected Method obtainFreezeMethod(Class cls) throws NoSuchMethodException {
        return cls.getMethod(FREEZE_METHOD, new Class[0]);
    }

    protected Method obtainAbortCreationMethod(Class cls) throws NoSuchMethodException {
        return cls.getMethod(ABORT_CREATION_METHOD, new Class[0]);
    }

    protected Method obtainCreateFromFamiliesWithDefaultMethod(Class cls) throws NoSuchMethodException {
        Object newInstance = Array.newInstance(cls, 1);
        Method declaredMethod = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, new Class[]{newInstance.getClass(), Integer.TYPE, Integer.TYPE});
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }
}
