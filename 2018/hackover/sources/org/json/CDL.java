package org.json;

import kotlin.text.Typography;

public class CDL {
    private static String getValue(JSONTokener jSONTokener) throws JSONException {
        char next;
        while (true) {
            next = jSONTokener.next();
            if (next != ' ' && next != 9) {
                break;
            }
        }
        if (next == 0) {
            return null;
        }
        if (next == Typography.quote || next == '\'') {
            StringBuilder stringBuilder;
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                char next2 = jSONTokener.next();
                if (next2 == next) {
                    char next3 = jSONTokener.next();
                    if (next3 != Typography.quote) {
                        if (next3 > 0) {
                            jSONTokener.back();
                        }
                        return stringBuffer.toString();
                    }
                }
                if (next2 == 0 || next2 == 10 || next2 == 13) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Missing close quote '");
                    stringBuilder.append(next);
                    stringBuilder.append("'.");
                } else {
                    stringBuffer.append(next2);
                }
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Missing close quote '");
            stringBuilder.append(next);
            stringBuilder.append("'.");
            throw jSONTokener.syntaxError(stringBuilder.toString());
        } else if (next != ',') {
            jSONTokener.back();
            return jSONTokener.nextTo(',');
        } else {
            jSONTokener.back();
            return "";
        }
    }

    public static JSONArray rowToJSONArray(JSONTokener jSONTokener) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        while (true) {
            Object value = getValue(jSONTokener);
            char next = jSONTokener.next();
            if (value != null && (jSONArray.length() != 0 || value.length() != 0 || next == ',')) {
                jSONArray.put(value);
                while (next != ',') {
                    if (next == ' ') {
                        next = jSONTokener.next();
                    } else if (next == 10 || next == 13 || next == 0) {
                        return jSONArray;
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Bad character '");
                        stringBuilder.append(next);
                        stringBuilder.append("' (");
                        stringBuilder.append(next);
                        stringBuilder.append(").");
                        throw jSONTokener.syntaxError(stringBuilder.toString());
                    }
                }
            }
        }
        return null;
    }

    public static JSONObject rowToJSONObject(JSONArray jSONArray, JSONTokener jSONTokener) throws JSONException {
        JSONArray rowToJSONArray = rowToJSONArray(jSONTokener);
        return rowToJSONArray != null ? rowToJSONArray.toJSONObject(jSONArray) : null;
    }

    public static String rowToString(JSONArray jSONArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            if (i > 0) {
                stringBuilder.append(',');
            }
            Object opt = jSONArray.opt(i);
            if (opt != null) {
                String obj = opt.toString();
                if (obj.length() <= 0 || (obj.indexOf(44) < 0 && obj.indexOf(10) < 0 && obj.indexOf(13) < 0 && obj.indexOf(0) < 0 && obj.charAt(0) != Typography.quote)) {
                    stringBuilder.append(obj);
                } else {
                    stringBuilder.append(Typography.quote);
                    int length = obj.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        char charAt = obj.charAt(i2);
                        if (charAt >= ' ' && charAt != Typography.quote) {
                            stringBuilder.append(charAt);
                        }
                    }
                    stringBuilder.append(Typography.quote);
                }
            }
        }
        stringBuilder.append(10);
        return stringBuilder.toString();
    }

    public static JSONArray toJSONArray(String str) throws JSONException {
        return toJSONArray(new JSONTokener(str));
    }

    public static JSONArray toJSONArray(JSONTokener jSONTokener) throws JSONException {
        return toJSONArray(rowToJSONArray(jSONTokener), jSONTokener);
    }

    public static JSONArray toJSONArray(JSONArray jSONArray, String str) throws JSONException {
        return toJSONArray(jSONArray, new JSONTokener(str));
    }

    public static JSONArray toJSONArray(JSONArray jSONArray, JSONTokener jSONTokener) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        while (true) {
            Object rowToJSONObject = rowToJSONObject(jSONArray, jSONTokener);
            if (rowToJSONObject == null) {
                break;
            }
            jSONArray2.put(rowToJSONObject);
        }
        if (jSONArray2.length() == 0) {
            return null;
        }
        return jSONArray2;
    }

    public static String toString(JSONArray jSONArray) throws JSONException {
        JSONObject optJSONObject = jSONArray.optJSONObject(0);
        if (optJSONObject != null) {
            JSONArray names = optJSONObject.names();
            if (names != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(rowToString(names));
                stringBuilder.append(toString(names, jSONArray));
                return stringBuilder.toString();
            }
        }
        return null;
    }

    public static String toString(JSONArray jSONArray, JSONArray jSONArray2) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < jSONArray2.length(); i++) {
            JSONObject optJSONObject = jSONArray2.optJSONObject(i);
            if (optJSONObject != null) {
                stringBuffer.append(rowToString(optJSONObject.toJSONArray(jSONArray)));
            }
        }
        return stringBuffer.toString();
    }
}
