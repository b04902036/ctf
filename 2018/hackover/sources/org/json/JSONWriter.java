package org.json;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public class JSONWriter {
    private static final int maxdepth = 200;
    private boolean comma = false;
    protected char mode = 'i';
    private final JSONObject[] stack = new JSONObject[maxdepth];
    private int top = 0;
    protected Appendable writer;

    public JSONWriter(Appendable appendable) {
        this.writer = appendable;
    }

    private JSONWriter append(String str) throws JSONException {
        if (str == null) {
            throw new JSONException("Null pointer");
        } else if (this.mode == 'o' || this.mode == 'a') {
            try {
                if (this.comma && this.mode == 'a') {
                    this.writer.append(',');
                }
                this.writer.append(str);
                if (this.mode == 'o') {
                    this.mode = 'k';
                }
                this.comma = true;
                return this;
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        } else {
            throw new JSONException("Value out of sequence.");
        }
    }

    public JSONWriter array() throws JSONException {
        if (this.mode == 'i' || this.mode == 'o' || this.mode == 'a') {
            push(null);
            append("[");
            this.comma = false;
            return this;
        }
        throw new JSONException("Misplaced array.");
    }

    private JSONWriter end(char c, char c2) throws JSONException {
        if (this.mode != c) {
            throw new JSONException(c == 'a' ? "Misplaced endArray." : "Misplaced endObject.");
        }
        pop(c);
        try {
            this.writer.append(c2);
            this.comma = true;
            return this;
        } catch (Throwable e) {
            throw new JSONException(e);
        }
    }

    public JSONWriter endArray() throws JSONException {
        return end('a', ']');
    }

    public JSONWriter endObject() throws JSONException {
        return end('k', '}');
    }

    public JSONWriter key(String str) throws JSONException {
        if (str == null) {
            throw new JSONException("Null key.");
        } else if (this.mode == 'k') {
            try {
                JSONObject jSONObject = this.stack[this.top - 1];
                if (jSONObject.has(str)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Duplicate key \"");
                    stringBuilder.append(str);
                    stringBuilder.append("\"");
                    throw new JSONException(stringBuilder.toString());
                }
                jSONObject.put(str, true);
                if (this.comma) {
                    this.writer.append(',');
                }
                this.writer.append(JSONObject.quote(str));
                this.writer.append(':');
                this.comma = false;
                this.mode = 'o';
                return this;
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        } else {
            throw new JSONException("Misplaced key.");
        }
    }

    public JSONWriter object() throws JSONException {
        if (this.mode == 'i') {
            this.mode = 'o';
        }
        if (this.mode == 'o' || this.mode == 'a') {
            append("{");
            push(new JSONObject());
            this.comma = false;
            return this;
        }
        throw new JSONException("Misplaced object.");
    }

    private void pop(char c) throws JSONException {
        if (this.top > 0) {
            char c2 = 'k';
            if ((this.stack[this.top + -1] == null ? 'a' : 'k') == c) {
                this.top--;
                if (this.top == 0) {
                    c2 = 'd';
                } else if (this.stack[this.top - 1] == null) {
                    c2 = 'a';
                }
                this.mode = c2;
                return;
            }
            throw new JSONException("Nesting error.");
        }
        throw new JSONException("Nesting error.");
    }

    private void push(JSONObject jSONObject) throws JSONException {
        if (this.top < maxdepth) {
            this.stack[this.top] = jSONObject;
            this.mode = jSONObject == null ? 'a' : 'k';
            this.top++;
            return;
        }
        throw new JSONException("Nesting too deep.");
    }

    public static String valueToString(Object obj) throws JSONException {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        String toJSONString;
        if (obj instanceof JSONString) {
            try {
                toJSONString = ((JSONString) obj).toJSONString();
                if (toJSONString instanceof String) {
                    return toJSONString;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Bad value from toJSONString: ");
                stringBuilder.append(toJSONString);
                throw new JSONException(stringBuilder.toString());
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        } else if (obj instanceof Number) {
            toJSONString = JSONObject.numberToString((Number) obj);
            try {
                BigDecimal bigDecimal = new BigDecimal(toJSONString);
                return toJSONString;
            } catch (NumberFormatException unused) {
                return JSONObject.quote(toJSONString);
            }
        } else if ((obj instanceof Boolean) || (obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj.toString();
        } else {
            if (obj instanceof Map) {
                return new JSONObject((Map) obj).toString();
            }
            if (obj instanceof Collection) {
                return new JSONArray((Collection) obj).toString();
            }
            if (obj.getClass().isArray()) {
                return new JSONArray(obj).toString();
            }
            if (obj instanceof Enum) {
                return JSONObject.quote(((Enum) obj).name());
            }
            return JSONObject.quote(obj.toString());
        }
    }

    public JSONWriter value(boolean z) throws JSONException {
        return append(z ? "true" : "false");
    }

    public JSONWriter value(double d) throws JSONException {
        return value(Double.valueOf(d));
    }

    public JSONWriter value(long j) throws JSONException {
        return append(Long.toString(j));
    }

    public JSONWriter value(Object obj) throws JSONException {
        return append(valueToString(obj));
    }
}
