package org.json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import kotlin.text.Typography;

public class JSONTokener {
    private long character;
    private long characterPreviousLine;
    private boolean eof;
    private long index;
    private long line;
    private char previous;
    private final Reader reader;
    private boolean usePrevious;

    public static int dehexchar(char c) {
        return (c < '0' || c > '9') ? (c < 'A' || c > 'F') ? (c < 'a' || c > 'f') ? -1 : c - 87 : c - 55 : c - 48;
    }

    public JSONTokener(Reader reader) {
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader);
        }
        this.reader = reader;
        this.eof = false;
        this.usePrevious = false;
        this.previous = 0;
        this.index = 0;
        this.character = 1;
        this.characterPreviousLine = 0;
        this.line = 1;
    }

    public JSONTokener(InputStream inputStream) {
        this(new InputStreamReader(inputStream));
    }

    public JSONTokener(String str) {
        this(new StringReader(str));
    }

    public void back() throws JSONException {
        if (this.usePrevious || this.index <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        decrementIndexes();
        this.usePrevious = true;
        this.eof = false;
    }

    private void decrementIndexes() {
        this.index--;
        if (this.previous == 13 || this.previous == 10) {
            this.line--;
            this.character = this.characterPreviousLine;
        } else if (this.character > 0) {
            this.character--;
        }
    }

    public boolean end() {
        return this.eof && !this.usePrevious;
    }

    public boolean more() throws JSONException {
        if (this.usePrevious) {
            return true;
        }
        try {
            this.reader.mark(1);
            try {
                if (this.reader.read() <= 0) {
                    this.eof = true;
                    return false;
                }
                this.reader.reset();
                return true;
            } catch (Throwable e) {
                throw new JSONException("Unable to read the next character from the stream", e);
            }
        } catch (Throwable e2) {
            throw new JSONException("Unable to preserve stream position", e2);
        }
    }

    public char next() throws JSONException {
        int i;
        if (this.usePrevious) {
            this.usePrevious = false;
            i = this.previous;
        } else {
            try {
                i = this.reader.read();
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        }
        if (i <= 0) {
            this.eof = true;
            return 0;
        }
        incrementIndexes(i);
        this.previous = (char) i;
        return this.previous;
    }

    private void incrementIndexes(int i) {
        if (i > 0) {
            this.index++;
            if (i == 13) {
                this.line++;
                this.characterPreviousLine = this.character;
                this.character = 0;
            } else if (i == 10) {
                if (this.previous != 13) {
                    this.line++;
                    this.characterPreviousLine = this.character;
                }
                this.character = 0;
            } else {
                this.character++;
            }
        }
    }

    public char next(char c) throws JSONException {
        char next = next();
        if (next == c) {
            return next;
        }
        if (next > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Expected '");
            stringBuilder.append(c);
            stringBuilder.append("' and instead saw '");
            stringBuilder.append(next);
            stringBuilder.append("'");
            throw syntaxError(stringBuilder.toString());
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Expected '");
        stringBuilder2.append(c);
        stringBuilder2.append("' and instead saw ''");
        throw syntaxError(stringBuilder2.toString());
    }

    public String next(int i) throws JSONException {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = next();
            if (end()) {
                throw syntaxError("Substring bounds error");
            }
        }
        return new String(cArr);
    }

    public char nextClean() throws JSONException {
        char next;
        do {
            next = next();
            if (next == 0) {
                break;
            }
        } while (next <= ' ');
        return next;
    }

    public String nextString(char c) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            char next = next();
            if (next != 0 && next != 10 && next != 13) {
                if (next == '\\') {
                    next = next();
                    if (next == Typography.quote || next == '\'' || next == '/' || next == '\\') {
                        stringBuilder.append(next);
                    } else if (next == 'b') {
                        stringBuilder.append(8);
                    } else if (next == 'f') {
                        stringBuilder.append(12);
                    } else if (next == 'n') {
                        stringBuilder.append(10);
                    } else if (next != 'r') {
                        switch (next) {
                            case 't':
                                stringBuilder.append(9);
                                break;
                            case 'u':
                                try {
                                    stringBuilder.append((char) Integer.parseInt(next(4), 16));
                                    break;
                                } catch (Throwable e) {
                                    throw syntaxError("Illegal escape.", e);
                                }
                            default:
                                throw syntaxError("Illegal escape.");
                        }
                    } else {
                        stringBuilder.append(13);
                    }
                } else if (next == c) {
                    return stringBuilder.toString();
                } else {
                    stringBuilder.append(next);
                }
            }
        }
        throw syntaxError("Unterminated string");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001c  */
    public java.lang.String nextTo(char r4) throws org.json.JSONException {
        /*
        r3 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
    L_0x0005:
        r1 = r3.next();
        if (r1 == r4) goto L_0x001a;
    L_0x000b:
        if (r1 == 0) goto L_0x001a;
    L_0x000d:
        r2 = 10;
        if (r1 == r2) goto L_0x001a;
    L_0x0011:
        r2 = 13;
        if (r1 != r2) goto L_0x0016;
    L_0x0015:
        goto L_0x001a;
    L_0x0016:
        r0.append(r1);
        goto L_0x0005;
    L_0x001a:
        if (r1 == 0) goto L_0x001f;
    L_0x001c:
        r3.back();
    L_0x001f:
        r3 = r0.toString();
        r3 = r3.trim();
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONTokener.nextTo(char):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020  */
    public java.lang.String nextTo(java.lang.String r4) throws org.json.JSONException {
        /*
        r3 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
    L_0x0005:
        r1 = r3.next();
        r2 = r4.indexOf(r1);
        if (r2 >= 0) goto L_0x001e;
    L_0x000f:
        if (r1 == 0) goto L_0x001e;
    L_0x0011:
        r2 = 10;
        if (r1 == r2) goto L_0x001e;
    L_0x0015:
        r2 = 13;
        if (r1 != r2) goto L_0x001a;
    L_0x0019:
        goto L_0x001e;
    L_0x001a:
        r0.append(r1);
        goto L_0x0005;
    L_0x001e:
        if (r1 == 0) goto L_0x0023;
    L_0x0020:
        r3.back();
    L_0x0023:
        r3 = r0.toString();
        r3 = r3.trim();
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONTokener.nextTo(java.lang.String):java.lang.String");
    }

    public Object nextValue() throws JSONException {
        char nextClean = nextClean();
        if (nextClean == Typography.quote || nextClean == '\'') {
            return nextString(nextClean);
        }
        if (nextClean == '[') {
            back();
            return new JSONArray(this);
        } else if (nextClean != '{') {
            StringBuilder stringBuilder = new StringBuilder();
            while (nextClean >= ' ' && ",:]}/\\\"[{;=#".indexOf(nextClean) < 0) {
                stringBuilder.append(nextClean);
                nextClean = next();
            }
            back();
            String trim = stringBuilder.toString().trim();
            if (!"".equals(trim)) {
                return JSONObject.stringToValue(trim);
            }
            throw syntaxError("Missing value");
        } else {
            back();
            return new JSONObject(this);
        }
    }

    public char skipTo(char c) throws JSONException {
        try {
            char next;
            long j = this.index;
            long j2 = this.character;
            long j3 = this.line;
            this.reader.mark(1000000);
            do {
                next = next();
                if (next == 0) {
                    this.reader.reset();
                    this.index = j;
                    this.character = j2;
                    this.line = j3;
                    return 0;
                }
            } while (next != c);
            this.reader.mark(1);
            back();
            return next;
        } catch (Throwable e) {
            throw new JSONException(e);
        }
    }

    public JSONException syntaxError(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(toString());
        return new JSONException(stringBuilder.toString());
    }

    public JSONException syntaxError(String str, Throwable th) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(toString());
        return new JSONException(stringBuilder.toString(), th);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" at ");
        stringBuilder.append(this.index);
        stringBuilder.append(" [character ");
        stringBuilder.append(this.character);
        stringBuilder.append(" line ");
        stringBuilder.append(this.line);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
