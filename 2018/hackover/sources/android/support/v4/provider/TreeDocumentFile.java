package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
class TreeDocumentFile extends DocumentFile {
    private Context mContext;
    private Uri mUri;

    TreeDocumentFile(@Nullable DocumentFile documentFile, Context context, Uri uri) {
        super(documentFile);
        this.mContext = context;
        this.mUri = uri;
    }

    @Nullable
    public DocumentFile createFile(String str, String str2) {
        Uri createFile = createFile(this.mContext, this.mUri, str, str2);
        return createFile != null ? new TreeDocumentFile(this, this.mContext, createFile) : null;
    }

    @Nullable
    private static Uri createFile(Context context, Uri uri, String str, String str2) {
        try {
            return DocumentsContract.createDocument(context.getContentResolver(), uri, str, str2);
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public DocumentFile createDirectory(String str) {
        Uri createFile = createFile(this.mContext, this.mUri, "vnd.android.document/directory", str);
        return createFile != null ? new TreeDocumentFile(this, this.mContext, createFile) : null;
    }

    public Uri getUri() {
        return this.mUri;
    }

    @Nullable
    public String getName() {
        return DocumentsContractApi19.getName(this.mContext, this.mUri);
    }

    @Nullable
    public String getType() {
        return DocumentsContractApi19.getType(this.mContext, this.mUri);
    }

    public boolean isDirectory() {
        return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
    }

    public boolean isFile() {
        return DocumentsContractApi19.isFile(this.mContext, this.mUri);
    }

    public boolean isVirtual() {
        return DocumentsContractApi19.isVirtual(this.mContext, this.mUri);
    }

    public long lastModified() {
        return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
    }

    public long length() {
        return DocumentsContractApi19.length(this.mContext, this.mUri);
    }

    public boolean canRead() {
        return DocumentsContractApi19.canRead(this.mContext, this.mUri);
    }

    public boolean canWrite() {
        return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
    }

    public boolean delete() {
        try {
            return DocumentsContract.deleteDocument(this.mContext.getContentResolver(), this.mUri);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean exists() {
        return DocumentsContractApi19.exists(this.mContext, this.mUri);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0072 A:{LOOP_END, LOOP:1: B:19:0x006f->B:21:0x0072} */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0072 A:{LOOP_END, LOOP:1: B:19:0x006f->B:21:0x0072} */
    public android.support.v4.provider.DocumentFile[] listFiles() {
        /*
        r9 = this;
        r0 = r9.mContext;
        r1 = r0.getContentResolver();
        r0 = r9.mUri;
        r2 = r9.mUri;
        r2 = android.provider.DocumentsContract.getDocumentId(r2);
        r2 = android.provider.DocumentsContract.buildChildDocumentsUriUsingTree(r0, r2);
        r0 = new java.util.ArrayList;
        r0.<init>();
        r7 = 0;
        r8 = 0;
        r3 = "document_id";
        r3 = new java.lang.String[]{r3};	 Catch:{ Exception -> 0x0046 }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r1 = r1.query(r2, r3, r4, r5, r6);	 Catch:{ Exception -> 0x0046 }
    L_0x0026:
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x0041, all -> 0x003e }
        if (r2 == 0) goto L_0x003a;
    L_0x002c:
        r2 = r1.getString(r7);	 Catch:{ Exception -> 0x0041, all -> 0x003e }
        r3 = r9.mUri;	 Catch:{ Exception -> 0x0041, all -> 0x003e }
        r2 = android.provider.DocumentsContract.buildDocumentUriUsingTree(r3, r2);	 Catch:{ Exception -> 0x0041, all -> 0x003e }
        r0.add(r2);	 Catch:{ Exception -> 0x0041, all -> 0x003e }
        goto L_0x0026;
    L_0x003a:
        closeQuietly(r1);
        goto L_0x0060;
    L_0x003e:
        r9 = move-exception;
        r8 = r1;
        goto L_0x0081;
    L_0x0041:
        r2 = move-exception;
        r8 = r1;
        goto L_0x0047;
    L_0x0044:
        r9 = move-exception;
        goto L_0x0081;
    L_0x0046:
        r2 = move-exception;
    L_0x0047:
        r1 = "DocumentFile";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0044 }
        r3.<init>();	 Catch:{ all -> 0x0044 }
        r4 = "Failed query: ";
        r3.append(r4);	 Catch:{ all -> 0x0044 }
        r3.append(r2);	 Catch:{ all -> 0x0044 }
        r2 = r3.toString();	 Catch:{ all -> 0x0044 }
        android.util.Log.w(r1, r2);	 Catch:{ all -> 0x0044 }
        closeQuietly(r8);
    L_0x0060:
        r1 = r0.size();
        r1 = new android.net.Uri[r1];
        r0 = r0.toArray(r1);
        r0 = (android.net.Uri[]) r0;
        r1 = r0.length;
        r1 = new android.support.v4.provider.DocumentFile[r1];
    L_0x006f:
        r2 = r0.length;
        if (r7 >= r2) goto L_0x0080;
    L_0x0072:
        r2 = new android.support.v4.provider.TreeDocumentFile;
        r3 = r9.mContext;
        r4 = r0[r7];
        r2.<init>(r9, r3, r4);
        r1[r7] = r2;
        r7 = r7 + 1;
        goto L_0x006f;
    L_0x0080:
        return r1;
    L_0x0081:
        closeQuietly(r8);
        throw r9;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.provider.TreeDocumentFile.listFiles():android.support.v4.provider.DocumentFile[]");
    }

    private static void closeQuietly(@Nullable AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    public boolean renameTo(String str) {
        try {
            Uri renameDocument = DocumentsContract.renameDocument(this.mContext.getContentResolver(), this.mUri, str);
            if (renameDocument == null) {
                return false;
            }
            this.mUri = renameDocument;
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
