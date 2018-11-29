package android.support.v4.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import android.support.annotation.RequiresApi;

@RequiresApi(28)
public class AppComponentFactory extends android.app.AppComponentFactory {
    public final Activity instantiateActivity(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Activity) CoreComponentFactory.checkCompatWrapper(instantiateActivityCompat(classLoader, str, intent));
    }

    public final Application instantiateApplication(ClassLoader classLoader, String str) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Application) CoreComponentFactory.checkCompatWrapper(instantiateApplicationCompat(classLoader, str));
    }

    public final BroadcastReceiver instantiateReceiver(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (BroadcastReceiver) CoreComponentFactory.checkCompatWrapper(instantiateReceiverCompat(classLoader, str, intent));
    }

    public final ContentProvider instantiateProvider(ClassLoader classLoader, String str) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ContentProvider) CoreComponentFactory.checkCompatWrapper(instantiateProviderCompat(classLoader, str));
    }

    public final Service instantiateService(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Service) CoreComponentFactory.checkCompatWrapper(instantiateServiceCompat(classLoader, str, intent));
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0014 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.reflect.InvocationTargetException (r0_5 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x0014, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x001c, code:
            throw new java.lang.RuntimeException("Couldn't call constructor", r0);
     */
    @android.support.annotation.NonNull
    public android.app.Application instantiateApplicationCompat(@android.support.annotation.NonNull java.lang.ClassLoader r1, @android.support.annotation.NonNull java.lang.String r2) throws java.lang.InstantiationException, java.lang.IllegalAccessException, java.lang.ClassNotFoundException {
        /*
        r0 = this;
        r0 = r1.loadClass(r2);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r1 = 0;
        r2 = new java.lang.Class[r1];	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = r0.getDeclaredConstructor(r2);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r1 = new java.lang.Object[r1];	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = r0.newInstance(r1);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = (android.app.Application) r0;	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        return r0;
    L_0x0014:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r2 = "Couldn't call constructor";
        r1.<init>(r2, r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.AppComponentFactory.instantiateApplicationCompat(java.lang.ClassLoader, java.lang.String):android.app.Application");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0014 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.reflect.InvocationTargetException (r0_5 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x0014, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x001c, code:
            throw new java.lang.RuntimeException("Couldn't call constructor", r0);
     */
    @android.support.annotation.NonNull
    public android.app.Activity instantiateActivityCompat(@android.support.annotation.NonNull java.lang.ClassLoader r1, @android.support.annotation.NonNull java.lang.String r2, @android.support.annotation.Nullable android.content.Intent r3) throws java.lang.InstantiationException, java.lang.IllegalAccessException, java.lang.ClassNotFoundException {
        /*
        r0 = this;
        r0 = r1.loadClass(r2);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r1 = 0;
        r2 = new java.lang.Class[r1];	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = r0.getDeclaredConstructor(r2);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r1 = new java.lang.Object[r1];	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = r0.newInstance(r1);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = (android.app.Activity) r0;	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        return r0;
    L_0x0014:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r2 = "Couldn't call constructor";
        r1.<init>(r2, r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.AppComponentFactory.instantiateActivityCompat(java.lang.ClassLoader, java.lang.String, android.content.Intent):android.app.Activity");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0014 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.reflect.InvocationTargetException (r0_5 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x0014, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x001c, code:
            throw new java.lang.RuntimeException("Couldn't call constructor", r0);
     */
    @android.support.annotation.NonNull
    public android.content.BroadcastReceiver instantiateReceiverCompat(@android.support.annotation.NonNull java.lang.ClassLoader r1, @android.support.annotation.NonNull java.lang.String r2, @android.support.annotation.Nullable android.content.Intent r3) throws java.lang.InstantiationException, java.lang.IllegalAccessException, java.lang.ClassNotFoundException {
        /*
        r0 = this;
        r0 = r1.loadClass(r2);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r1 = 0;
        r2 = new java.lang.Class[r1];	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = r0.getDeclaredConstructor(r2);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r1 = new java.lang.Object[r1];	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = r0.newInstance(r1);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = (android.content.BroadcastReceiver) r0;	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        return r0;
    L_0x0014:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r2 = "Couldn't call constructor";
        r1.<init>(r2, r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.AppComponentFactory.instantiateReceiverCompat(java.lang.ClassLoader, java.lang.String, android.content.Intent):android.content.BroadcastReceiver");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0014 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.reflect.InvocationTargetException (r0_5 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x0014, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x001c, code:
            throw new java.lang.RuntimeException("Couldn't call constructor", r0);
     */
    @android.support.annotation.NonNull
    public android.app.Service instantiateServiceCompat(@android.support.annotation.NonNull java.lang.ClassLoader r1, @android.support.annotation.NonNull java.lang.String r2, @android.support.annotation.Nullable android.content.Intent r3) throws java.lang.InstantiationException, java.lang.IllegalAccessException, java.lang.ClassNotFoundException {
        /*
        r0 = this;
        r0 = r1.loadClass(r2);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r1 = 0;
        r2 = new java.lang.Class[r1];	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = r0.getDeclaredConstructor(r2);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r1 = new java.lang.Object[r1];	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = r0.newInstance(r1);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = (android.app.Service) r0;	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        return r0;
    L_0x0014:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r2 = "Couldn't call constructor";
        r1.<init>(r2, r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.AppComponentFactory.instantiateServiceCompat(java.lang.ClassLoader, java.lang.String, android.content.Intent):android.app.Service");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0014 A:{Splitter: B:0:0x0000, ExcHandler: java.lang.reflect.InvocationTargetException (r0_5 'e' java.lang.Throwable)} */
    /* JADX WARNING: Missing block: B:3:0x0014, code:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:5:0x001c, code:
            throw new java.lang.RuntimeException("Couldn't call constructor", r0);
     */
    @android.support.annotation.NonNull
    public android.content.ContentProvider instantiateProviderCompat(@android.support.annotation.NonNull java.lang.ClassLoader r1, @android.support.annotation.NonNull java.lang.String r2) throws java.lang.InstantiationException, java.lang.IllegalAccessException, java.lang.ClassNotFoundException {
        /*
        r0 = this;
        r0 = r1.loadClass(r2);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r1 = 0;
        r2 = new java.lang.Class[r1];	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = r0.getDeclaredConstructor(r2);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r1 = new java.lang.Object[r1];	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = r0.newInstance(r1);	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        r0 = (android.content.ContentProvider) r0;	 Catch:{ InvocationTargetException -> 0x0014, InvocationTargetException -> 0x0014 }
        return r0;
    L_0x0014:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r2 = "Couldn't call constructor";
        r1.<init>(r2, r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.AppComponentFactory.instantiateProviderCompat(java.lang.ClassLoader, java.lang.String):android.content.ContentProvider");
    }
}
