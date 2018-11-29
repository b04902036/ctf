package org.json;

import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import kotlin.text.Typography;

public class XML {
    public static final Character AMP = Character.valueOf(Typography.amp);
    public static final Character APOS = Character.valueOf('\'');
    public static final Character BANG = Character.valueOf('!');
    public static final Character EQ = Character.valueOf('=');
    public static final Character GT = Character.valueOf(Typography.greater);
    public static final Character LT = Character.valueOf(Typography.less);
    public static final Character QUEST = Character.valueOf('?');
    public static final Character QUOT = Character.valueOf(Typography.quote);
    public static final Character SLASH = Character.valueOf('/');

    private static Iterable<Integer> codePointIterator(final String str) {
        return new Iterable<Integer>() {
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    private int length = str.length();
                    private int nextIndex = 0;

                    public boolean hasNext() {
                        return this.nextIndex < this.length;
                    }

                    public Integer next() {
                        int codePointAt = str.codePointAt(this.nextIndex);
                        this.nextIndex += Character.charCount(codePointAt);
                        return Integer.valueOf(codePointAt);
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public static String escape(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        for (Integer intValue : codePointIterator(str)) {
            int intValue2 = intValue.intValue();
            if (intValue2 == 34) {
                stringBuilder.append("&quot;");
            } else if (intValue2 == 60) {
                stringBuilder.append("&lt;");
            } else if (intValue2 != 62) {
                switch (intValue2) {
                    case 38:
                        stringBuilder.append("&amp;");
                        break;
                    case 39:
                        stringBuilder.append("&apos;");
                        break;
                    default:
                        if (!mustEscape(intValue2)) {
                            stringBuilder.appendCodePoint(intValue2);
                            break;
                        }
                        stringBuilder.append("&#x");
                        stringBuilder.append(Integer.toHexString(intValue2));
                        stringBuilder.append(';');
                        break;
                }
            } else {
                stringBuilder.append("&gt;");
            }
        }
        return stringBuilder.toString();
    }

    private static boolean mustEscape(int i) {
        return !(!Character.isISOControl(i) || i == 9 || i == 10 || i == 13) || ((i < 32 || i > 55295) && ((i < 57344 || i > 65533) && (i < 65536 || i > 1114111)));
    }

    public static String unescape(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == Typography.amp) {
                int indexOf = str.indexOf(59, i);
                if (indexOf > i) {
                    String substring = str.substring(i + 1, indexOf);
                    stringBuilder.append(XMLTokener.unescapeEntity(substring));
                    i += substring.length() + 1;
                } else {
                    stringBuilder.append(charAt);
                }
            } else {
                stringBuilder.append(charAt);
            }
            i++;
        }
        return stringBuilder.toString();
    }

    public static void noSpace(String str) throws JSONException {
        int length = str.length();
        if (length != 0) {
            for (int i = 0; i < length; i++) {
                if (Character.isWhitespace(str.charAt(i))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("'");
                    stringBuilder.append(str);
                    stringBuilder.append("' contains a space character.");
                    throw new JSONException(stringBuilder.toString());
                }
            }
            return;
        }
        throw new JSONException("Empty string.");
    }

    /* JADX WARNING: Missing block: B:62:0x00eb, code:
            r5 = r7.nextToken();
     */
    /* JADX WARNING: Missing block: B:63:0x00f1, code:
            if ((r5 instanceof java.lang.String) == false) goto L_0x0102;
     */
    /* JADX WARNING: Missing block: B:64:0x00f3, code:
            if (r10 == false) goto L_0x00f8;
     */
    /* JADX WARNING: Missing block: B:65:0x00f5, code:
            r5 = (java.lang.String) r5;
     */
    /* JADX WARNING: Missing block: B:66:0x00f8, code:
            r5 = stringToValue((java.lang.String) r5);
     */
    /* JADX WARNING: Missing block: B:69:0x0108, code:
            throw r7.syntaxError("Missing value");
     */
    private static boolean parse(org.json.XMLTokener r7, org.json.JSONObject r8, java.lang.String r9, boolean r10) throws org.json.JSONException {
        /*
        r0 = r7.nextToken();
        r1 = BANG;
        r2 = 1;
        r3 = 0;
        if (r0 != r1) goto L_0x006c;
    L_0x000a:
        r9 = r7.next();
        r10 = 45;
        if (r9 != r10) goto L_0x0022;
    L_0x0012:
        r8 = r7.next();
        if (r8 != r10) goto L_0x001e;
    L_0x0018:
        r8 = "-->";
        r7.skipPast(r8);
        return r3;
    L_0x001e:
        r7.back();
        goto L_0x004f;
    L_0x0022:
        r10 = 91;
        if (r9 != r10) goto L_0x004f;
    L_0x0026:
        r9 = r7.nextToken();
        r0 = "CDATA";
        r9 = r0.equals(r9);
        if (r9 == 0) goto L_0x0048;
    L_0x0032:
        r9 = r7.next();
        if (r9 != r10) goto L_0x0048;
    L_0x0038:
        r7 = r7.nextCDATA();
        r9 = r7.length();
        if (r9 <= 0) goto L_0x0047;
    L_0x0042:
        r9 = "content";
        r8.accumulate(r9, r7);
    L_0x0047:
        return r3;
    L_0x0048:
        r8 = "Expected 'CDATA['";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x004f:
        r8 = r7.nextMeta();
        if (r8 == 0) goto L_0x0065;
    L_0x0055:
        r9 = LT;
        if (r8 != r9) goto L_0x005c;
    L_0x0059:
        r2 = r2 + 1;
        goto L_0x0062;
    L_0x005c:
        r9 = GT;
        if (r8 != r9) goto L_0x0062;
    L_0x0060:
        r2 = r2 + -1;
    L_0x0062:
        if (r2 > 0) goto L_0x004f;
    L_0x0064:
        return r3;
    L_0x0065:
        r8 = "Missing '>' after '<!'.";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x006c:
        r1 = QUEST;
        if (r0 != r1) goto L_0x0076;
    L_0x0070:
        r8 = "?>";
        r7.skipPast(r8);
        return r3;
    L_0x0076:
        r1 = SLASH;
        if (r0 != r1) goto L_0x00ca;
    L_0x007a:
        r8 = r7.nextToken();
        if (r9 == 0) goto L_0x00b4;
    L_0x0080:
        r10 = r8.equals(r9);
        if (r10 == 0) goto L_0x0096;
    L_0x0086:
        r8 = r7.nextToken();
        r9 = GT;
        if (r8 != r9) goto L_0x008f;
    L_0x008e:
        return r2;
    L_0x008f:
        r8 = "Misshaped close tag";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0096:
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r0 = "Mismatched ";
        r10.append(r0);
        r10.append(r9);
        r9 = " and ";
        r10.append(r9);
        r10.append(r8);
        r8 = r10.toString();
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x00b4:
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = "Mismatched close tag ";
        r9.append(r10);
        r9.append(r8);
        r8 = r9.toString();
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x00ca:
        r9 = r0 instanceof java.lang.Character;
        if (r9 != 0) goto L_0x01a8;
    L_0x00ce:
        r0 = (java.lang.String) r0;
        r9 = new org.json.JSONObject;
        r9.<init>();
        r1 = 0;
    L_0x00d6:
        r4 = r1;
    L_0x00d7:
        if (r4 != 0) goto L_0x00dd;
    L_0x00d9:
        r4 = r7.nextToken();
    L_0x00dd:
        r5 = r4 instanceof java.lang.String;
        if (r5 == 0) goto L_0x0110;
    L_0x00e1:
        r4 = (java.lang.String) r4;
        r5 = r7.nextToken();
        r6 = EQ;
        if (r5 != r6) goto L_0x0109;
    L_0x00eb:
        r5 = r7.nextToken();
        r6 = r5 instanceof java.lang.String;
        if (r6 == 0) goto L_0x0102;
    L_0x00f3:
        if (r10 == 0) goto L_0x00f8;
    L_0x00f5:
        r5 = (java.lang.String) r5;
        goto L_0x00fe;
    L_0x00f8:
        r5 = (java.lang.String) r5;
        r5 = stringToValue(r5);
    L_0x00fe:
        r9.accumulate(r4, r5);
        goto L_0x00d6;
    L_0x0102:
        r8 = "Missing value";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0109:
        r6 = "";
        r9.accumulate(r4, r6);
        r4 = r5;
        goto L_0x00d7;
    L_0x0110:
        r1 = SLASH;
        if (r4 != r1) goto L_0x0133;
    L_0x0114:
        r10 = r7.nextToken();
        r1 = GT;
        if (r10 != r1) goto L_0x012c;
    L_0x011c:
        r7 = r9.length();
        if (r7 <= 0) goto L_0x0126;
    L_0x0122:
        r8.accumulate(r0, r9);
        goto L_0x012b;
    L_0x0126:
        r7 = "";
        r8.accumulate(r0, r7);
    L_0x012b:
        return r3;
    L_0x012c:
        r8 = "Misshaped tag";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0133:
        r1 = GT;
        if (r4 != r1) goto L_0x01a1;
    L_0x0137:
        r1 = r7.nextContent();
        if (r1 != 0) goto L_0x0156;
    L_0x013d:
        if (r0 != 0) goto L_0x0140;
    L_0x013f:
        return r3;
    L_0x0140:
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "Unclosed tag ";
        r8.append(r9);
        r8.append(r0);
        r8 = r8.toString();
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x0156:
        r4 = r1 instanceof java.lang.String;
        if (r4 == 0) goto L_0x016f;
    L_0x015a:
        r1 = (java.lang.String) r1;
        r4 = r1.length();
        if (r4 <= 0) goto L_0x0137;
    L_0x0162:
        r4 = "content";
        if (r10 == 0) goto L_0x0167;
    L_0x0166:
        goto L_0x016b;
    L_0x0167:
        r1 = stringToValue(r1);
    L_0x016b:
        r9.accumulate(r4, r1);
        goto L_0x0137;
    L_0x016f:
        r4 = LT;
        if (r1 != r4) goto L_0x0137;
    L_0x0173:
        r1 = parse(r7, r9, r0, r10);
        if (r1 == 0) goto L_0x0137;
    L_0x0179:
        r7 = r9.length();
        if (r7 != 0) goto L_0x0185;
    L_0x017f:
        r7 = "";
        r8.accumulate(r0, r7);
        goto L_0x01a0;
    L_0x0185:
        r7 = r9.length();
        if (r7 != r2) goto L_0x019d;
    L_0x018b:
        r7 = "content";
        r7 = r9.opt(r7);
        if (r7 == 0) goto L_0x019d;
    L_0x0193:
        r7 = "content";
        r7 = r9.opt(r7);
        r8.accumulate(r0, r7);
        goto L_0x01a0;
    L_0x019d:
        r8.accumulate(r0, r9);
    L_0x01a0:
        return r3;
    L_0x01a1:
        r8 = "Misshaped tag";
        r7 = r7.syntaxError(r8);
        throw r7;
    L_0x01a8:
        r8 = "Misshaped tag";
        r7 = r7.syntaxError(r8);
        throw r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.XML.parse(org.json.XMLTokener, org.json.JSONObject, java.lang.String, boolean):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0093 A:{RETURN, Splitter: B:22:0x003d, ExcHandler: java.lang.Exception (unused java.lang.Exception)} */
    public static java.lang.Object stringToValue(java.lang.String r5) {
        /*
        r0 = "";
        r0 = r5.equals(r0);
        if (r0 == 0) goto L_0x0009;
    L_0x0008:
        return r5;
    L_0x0009:
        r0 = "true";
        r0 = r5.equalsIgnoreCase(r0);
        if (r0 == 0) goto L_0x0014;
    L_0x0011:
        r5 = java.lang.Boolean.TRUE;
        return r5;
    L_0x0014:
        r0 = "false";
        r0 = r5.equalsIgnoreCase(r0);
        if (r0 == 0) goto L_0x001f;
    L_0x001c:
        r5 = java.lang.Boolean.FALSE;
        return r5;
    L_0x001f:
        r0 = "null";
        r0 = r5.equalsIgnoreCase(r0);
        if (r0 == 0) goto L_0x002a;
    L_0x0027:
        r5 = org.json.JSONObject.NULL;
        return r5;
    L_0x002a:
        r0 = 0;
        r0 = r5.charAt(r0);
        r1 = 48;
        if (r0 < r1) goto L_0x0037;
    L_0x0033:
        r1 = 57;
        if (r0 <= r1) goto L_0x003b;
    L_0x0037:
        r1 = 45;
        if (r0 != r1) goto L_0x0093;
    L_0x003b:
        r0 = 46;
        r0 = r5.indexOf(r0);	 Catch:{ Exception -> 0x0093 }
        r1 = -1;
        if (r0 > r1) goto L_0x0082;
    L_0x0044:
        r0 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r0 = r5.indexOf(r0);	 Catch:{ Exception -> 0x0093 }
        if (r0 > r1) goto L_0x0082;
    L_0x004c:
        r0 = 69;
        r0 = r5.indexOf(r0);	 Catch:{ Exception -> 0x0093 }
        if (r0 > r1) goto L_0x0082;
    L_0x0054:
        r0 = "-0";
        r0 = r0.equals(r5);	 Catch:{ Exception -> 0x0093 }
        if (r0 == 0) goto L_0x005d;
    L_0x005c:
        goto L_0x0082;
    L_0x005d:
        r0 = java.lang.Long.valueOf(r5);	 Catch:{ Exception -> 0x0093 }
        r1 = r0.toString();	 Catch:{ Exception -> 0x0093 }
        r1 = r5.equals(r1);	 Catch:{ Exception -> 0x0093 }
        if (r1 == 0) goto L_0x0093;
    L_0x006b:
        r1 = r0.longValue();	 Catch:{ Exception -> 0x0093 }
        r3 = r0.intValue();	 Catch:{ Exception -> 0x0093 }
        r3 = (long) r3;	 Catch:{ Exception -> 0x0093 }
        r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r1 != 0) goto L_0x0081;
    L_0x0078:
        r0 = r0.intValue();	 Catch:{ Exception -> 0x0093 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x0093 }
        return r0;
    L_0x0081:
        return r0;
    L_0x0082:
        r0 = java.lang.Double.valueOf(r5);	 Catch:{ Exception -> 0x0093 }
        r1 = r0.isInfinite();	 Catch:{ Exception -> 0x0093 }
        if (r1 != 0) goto L_0x0093;
    L_0x008c:
        r1 = r0.isNaN();	 Catch:{ Exception -> 0x0093 }
        if (r1 != 0) goto L_0x0093;
    L_0x0092:
        return r0;
    L_0x0093:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.XML.stringToValue(java.lang.String):java.lang.Object");
    }

    public static JSONObject toJSONObject(String str) throws JSONException {
        return toJSONObject(str, false);
    }

    public static JSONObject toJSONObject(Reader reader) throws JSONException {
        return toJSONObject(reader, false);
    }

    public static JSONObject toJSONObject(Reader reader, boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        XMLTokener xMLTokener = new XMLTokener(reader);
        while (xMLTokener.more()) {
            xMLTokener.skipPast("<");
            if (xMLTokener.more()) {
                parse(xMLTokener, jSONObject, null, z);
            }
        }
        return jSONObject;
    }

    public static JSONObject toJSONObject(String str, boolean z) throws JSONException {
        return toJSONObject(new StringReader(str), z);
    }

    public static String toString(Object obj) throws JSONException {
        return toString(obj, null);
    }

    public static String toString(Object obj, String str) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        if (obj instanceof JSONObject) {
            if (str != null) {
                stringBuilder.append(Typography.less);
                stringBuilder.append(str);
                stringBuilder.append(Typography.greater);
            }
            JSONObject jSONObject = (JSONObject) obj;
            for (String str2 : jSONObject.keySet()) {
                Object opt = jSONObject.opt(str2);
                if (opt == null) {
                    opt = "";
                } else if (opt.getClass().isArray()) {
                    opt = new JSONArray(opt);
                }
                JSONArray jSONArray;
                int i2;
                if ("content".equals(str2)) {
                    if (opt instanceof JSONArray) {
                        jSONArray = (JSONArray) opt;
                        int length = jSONArray.length();
                        for (i2 = 0; i2 < length; i2++) {
                            if (i2 > 0) {
                                stringBuilder.append(10);
                            }
                            stringBuilder.append(escape(jSONArray.opt(i2).toString()));
                        }
                    } else {
                        stringBuilder.append(escape(opt.toString()));
                    }
                } else if (opt instanceof JSONArray) {
                    jSONArray = (JSONArray) opt;
                    i2 = jSONArray.length();
                    for (int i3 = 0; i3 < i2; i3++) {
                        Object opt2 = jSONArray.opt(i3);
                        if (opt2 instanceof JSONArray) {
                            stringBuilder.append(Typography.less);
                            stringBuilder.append(str2);
                            stringBuilder.append(Typography.greater);
                            stringBuilder.append(toString(opt2));
                            stringBuilder.append("</");
                            stringBuilder.append(str2);
                            stringBuilder.append(Typography.greater);
                        } else {
                            stringBuilder.append(toString(opt2, str2));
                        }
                    }
                } else if ("".equals(opt)) {
                    stringBuilder.append(Typography.less);
                    stringBuilder.append(str2);
                    stringBuilder.append("/>");
                } else {
                    stringBuilder.append(toString(opt, str2));
                }
            }
            if (str != null) {
                stringBuilder.append("</");
                stringBuilder.append(str);
                stringBuilder.append(Typography.greater);
            }
            return stringBuilder.toString();
        } else if (obj == null || !((obj instanceof JSONArray) || obj.getClass().isArray())) {
            String str3;
            if (obj == null) {
                str3 = "null";
            } else {
                str3 = escape(obj.toString());
            }
            if (str == null) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("\"");
                stringBuilder2.append(str3);
                stringBuilder2.append("\"");
                str3 = stringBuilder2.toString();
            } else if (str3.length() == 0) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("<");
                stringBuilder3.append(str);
                stringBuilder3.append("/>");
                str3 = stringBuilder3.toString();
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append("<");
                stringBuilder.append(str);
                stringBuilder.append(">");
                stringBuilder.append(str3);
                stringBuilder.append("</");
                stringBuilder.append(str);
                stringBuilder.append(">");
                str3 = stringBuilder.toString();
            }
            return str3;
        } else {
            JSONArray jSONArray2;
            if (obj.getClass().isArray()) {
                jSONArray2 = new JSONArray(obj);
            } else {
                jSONArray2 = (JSONArray) obj;
            }
            int length2 = jSONArray2.length();
            while (i < length2) {
                stringBuilder.append(toString(jSONArray2.opt(i), str == null ? "array" : str));
                i++;
            }
            return stringBuilder.toString();
        }
    }
}
