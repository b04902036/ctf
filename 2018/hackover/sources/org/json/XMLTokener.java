package org.json;

import java.io.Reader;
import java.util.HashMap;
import kotlin.text.Typography;

public class XMLTokener extends JSONTokener {
    public static final HashMap<String, Character> entity = new HashMap(8);

    static {
        entity.put("amp", XML.AMP);
        entity.put("apos", XML.APOS);
        entity.put("gt", XML.GT);
        entity.put("lt", XML.LT);
        entity.put("quot", XML.QUOT);
    }

    public XMLTokener(Reader reader) {
        super(reader);
    }

    public XMLTokener(String str) {
        super(str);
    }

    public String nextCDATA() throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        while (more()) {
            stringBuilder.append(next());
            int length = stringBuilder.length() - 3;
            if (length >= 0 && stringBuilder.charAt(length) == ']' && stringBuilder.charAt(length + 1) == ']' && stringBuilder.charAt(length + 2) == Typography.greater) {
                stringBuilder.setLength(length);
                return stringBuilder.toString();
            }
        }
        throw syntaxError("Unclosed CDATA");
    }

    public Object nextContent() throws JSONException {
        char next;
        do {
            next = next();
        } while (Character.isWhitespace(next));
        if (next == 0) {
            return null;
        }
        if (next == Typography.less) {
            return XML.LT;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (next != 0) {
            if (next == Typography.less) {
                back();
                return stringBuilder.toString().trim();
            }
            if (next == Typography.amp) {
                stringBuilder.append(nextEntity(next));
            } else {
                stringBuilder.append(next);
            }
            next = next();
        }
        return stringBuilder.toString().trim();
    }

    public Object nextEntity(char c) throws JSONException {
        char next;
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            next = next();
            if (!Character.isLetterOrDigit(next) && next != '#') {
                break;
            }
            stringBuilder.append(Character.toLowerCase(next));
        }
        if (next == ';') {
            return unescapeEntity(stringBuilder.toString());
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Missing ';' in XML entity: &");
        stringBuilder2.append(stringBuilder);
        throw syntaxError(stringBuilder2.toString());
    }

    static String unescapeEntity(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        if (str.charAt(0) == '#') {
            int parseInt;
            if (str.charAt(1) == 'x') {
                parseInt = Integer.parseInt(str.substring(2), 16);
            } else {
                parseInt = Integer.parseInt(str.substring(1));
            }
            return new String(new int[]{parseInt}, 0, 1);
        }
        Character ch = (Character) entity.get(str);
        if (ch != null) {
            return ch.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Typography.amp);
        stringBuilder.append(str);
        stringBuilder.append(';');
        return stringBuilder.toString();
    }

    public Object nextMeta() throws JSONException {
        char next;
        do {
            next = next();
        } while (Character.isWhitespace(next));
        if (next != 0) {
            if (next != '\'') {
                if (next == '/') {
                    return XML.SLASH;
                }
                switch (next) {
                    case '!':
                        return XML.BANG;
                    case '\"':
                        break;
                    default:
                        switch (next) {
                            case '<':
                                return XML.LT;
                            case '=':
                                return XML.EQ;
                            case '>':
                                return XML.GT;
                            case '?':
                                return XML.QUEST;
                        }
                        while (true) {
                            next = next();
                            if (Character.isWhitespace(next)) {
                                return Boolean.TRUE;
                            }
                            if (!(next == 0 || next == '\'' || next == '/')) {
                                switch (next) {
                                    case '!':
                                    case '\"':
                                        break;
                                    default:
                                        switch (next) {
                                            case '<':
                                            case '=':
                                            case '>':
                                            case '?':
                                                break;
                                            default:
                                        }
                                }
                            }
                        }
                        back();
                        return Boolean.TRUE;
                }
            }
            char next2;
            do {
                next2 = next();
                if (next2 == 0) {
                    throw syntaxError("Unterminated string");
                }
            } while (next2 != next);
            return Boolean.TRUE;
        }
        throw syntaxError("Misshaped meta tag");
    }

    /* JADX WARNING: Missing block: B:24:0x0046, code:
            back();
     */
    /* JADX WARNING: Missing block: B:25:0x004d, code:
            return r3.toString();
     */
    public java.lang.Object nextToken() throws org.json.JSONException {
        /*
        r5 = this;
    L_0x0000:
        r0 = r5.next();
        r1 = java.lang.Character.isWhitespace(r0);
        if (r1 != 0) goto L_0x0000;
    L_0x000a:
        if (r0 == 0) goto L_0x0099;
    L_0x000c:
        r1 = 39;
        if (r0 == r1) goto L_0x0070;
    L_0x0010:
        r2 = 47;
        if (r0 == r2) goto L_0x006d;
    L_0x0014:
        switch(r0) {
            case 33: goto L_0x006a;
            case 34: goto L_0x0070;
            default: goto L_0x0017;
        };
    L_0x0017:
        switch(r0) {
            case 60: goto L_0x0063;
            case 61: goto L_0x0060;
            case 62: goto L_0x005d;
            case 63: goto L_0x005a;
            default: goto L_0x001a;
        };
    L_0x001a:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
    L_0x001f:
        r3.append(r0);
        r0 = r5.next();
        r4 = java.lang.Character.isWhitespace(r0);
        if (r4 == 0) goto L_0x0031;
    L_0x002c:
        r5 = r3.toString();
        return r5;
    L_0x0031:
        if (r0 == 0) goto L_0x0055;
    L_0x0033:
        if (r0 == r1) goto L_0x004e;
    L_0x0035:
        if (r0 == r2) goto L_0x0046;
    L_0x0037:
        r4 = 91;
        if (r0 == r4) goto L_0x0046;
    L_0x003b:
        r4 = 93;
        if (r0 == r4) goto L_0x0046;
    L_0x003f:
        switch(r0) {
            case 33: goto L_0x0046;
            case 34: goto L_0x004e;
            default: goto L_0x0042;
        };
    L_0x0042:
        switch(r0) {
            case 60: goto L_0x004e;
            case 61: goto L_0x0046;
            case 62: goto L_0x0046;
            case 63: goto L_0x0046;
            default: goto L_0x0045;
        };
    L_0x0045:
        goto L_0x001f;
    L_0x0046:
        r5.back();
        r5 = r3.toString();
        return r5;
    L_0x004e:
        r0 = "Bad character in a name";
        r5 = r5.syntaxError(r0);
        throw r5;
    L_0x0055:
        r5 = r3.toString();
        return r5;
    L_0x005a:
        r5 = org.json.XML.QUEST;
        return r5;
    L_0x005d:
        r5 = org.json.XML.GT;
        return r5;
    L_0x0060:
        r5 = org.json.XML.EQ;
        return r5;
    L_0x0063:
        r0 = "Misplaced '<'";
        r5 = r5.syntaxError(r0);
        throw r5;
    L_0x006a:
        r5 = org.json.XML.BANG;
        return r5;
    L_0x006d:
        r5 = org.json.XML.SLASH;
        return r5;
    L_0x0070:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
    L_0x0075:
        r2 = r5.next();
        if (r2 == 0) goto L_0x0092;
    L_0x007b:
        if (r2 != r0) goto L_0x0082;
    L_0x007d:
        r5 = r1.toString();
        return r5;
    L_0x0082:
        r3 = 38;
        if (r2 != r3) goto L_0x008e;
    L_0x0086:
        r2 = r5.nextEntity(r2);
        r1.append(r2);
        goto L_0x0075;
    L_0x008e:
        r1.append(r2);
        goto L_0x0075;
    L_0x0092:
        r0 = "Unterminated string";
        r5 = r5.syntaxError(r0);
        throw r5;
    L_0x0099:
        r0 = "Misshaped element";
        r5 = r5.syntaxError(r0);
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.XMLTokener.nextToken():java.lang.Object");
    }

    public void skipPast(String str) {
        char next;
        int length = str.length();
        char[] cArr = new char[length];
        int i = 0;
        while (i < length) {
            next = next();
            if (next != 0) {
                cArr[i] = next;
                i++;
            } else {
                return;
            }
        }
        i = 0;
        while (true) {
            Object obj;
            int i2 = i;
            for (int i3 = 0; i3 < length; i3++) {
                if (cArr[i2] != str.charAt(i3)) {
                    obj = null;
                    break;
                }
                i2++;
                if (i2 >= length) {
                    i2 -= length;
                }
            }
            obj = 1;
            if (obj == null) {
                next = next();
                if (next != 0) {
                    cArr[i] = next;
                    i++;
                    if (i >= length) {
                        i -= length;
                    }
                } else {
                    return;
                }
            }
            return;
        }
    }
}
