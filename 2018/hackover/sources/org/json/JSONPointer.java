package org.json;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONPointer {
    private static final String ENCODING = "utf-8";
    private final List<String> refTokens;

    public static class Builder {
        private final List<String> refTokens = new ArrayList();

        public JSONPointer build() {
            return new JSONPointer(this.refTokens);
        }

        public Builder append(String str) {
            if (str != null) {
                this.refTokens.add(str);
                return this;
            }
            throw new NullPointerException("token cannot be null");
        }

        public Builder append(int i) {
            this.refTokens.add(String.valueOf(i));
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public JSONPointer(String str) {
        if (str == null) {
            throw new NullPointerException("pointer cannot be null");
        } else if (str.isEmpty() || str.equals("#")) {
            this.refTokens = Collections.emptyList();
        } else {
            if (str.startsWith("#/")) {
                try {
                    str = URLDecoder.decode(str.substring(2), ENCODING);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            } else if (str.startsWith("/")) {
                str = str.substring(1);
            } else {
                throw new IllegalArgumentException("a JSON pointer should start with '/' or '#/'");
            }
            this.refTokens = new ArrayList();
            int i = -1;
            while (true) {
                i++;
                int indexOf = str.indexOf(47, i);
                if (i == indexOf || i == str.length()) {
                    this.refTokens.add("");
                } else if (indexOf >= 0) {
                    this.refTokens.add(unescape(str.substring(i, indexOf)));
                } else {
                    this.refTokens.add(unescape(str.substring(i)));
                }
                if (indexOf >= 0) {
                    i = indexOf;
                } else {
                    return;
                }
            }
        }
    }

    public JSONPointer(List<String> list) {
        this.refTokens = new ArrayList(list);
    }

    private String unescape(String str) {
        return str.replace("~1", "/").replace("~0", "~").replace("\\\"", "\"").replace("\\\\", "\\");
    }

    public Object queryFrom(Object obj) throws JSONPointerException {
        if (this.refTokens.isEmpty()) {
            return obj;
        }
        for (String str : this.refTokens) {
            if (obj instanceof JSONObject) {
                obj = ((JSONObject) obj).opt(unescape(str));
            } else if (obj instanceof JSONArray) {
                obj = readByIndexToken(obj, str);
            } else {
                throw new JSONPointerException(String.format("value [%s] is not an array or object therefore its key %s cannot be resolved", new Object[]{obj, str}));
            }
        }
        return obj;
    }

    private Object readByIndexToken(Object obj, String str) throws JSONPointerException {
        int parseInt;
        try {
            parseInt = Integer.parseInt(str);
            JSONArray jSONArray = (JSONArray) obj;
            if (parseInt < jSONArray.length()) {
                return jSONArray.get(parseInt);
            }
            throw new JSONPointerException(String.format("index %d is out of bounds - the array has %d elements", new Object[]{Integer.valueOf(parseInt), Integer.valueOf(jSONArray.length())}));
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error reading value at index position ");
            stringBuilder.append(parseInt);
            throw new JSONPointerException(stringBuilder.toString(), e);
        } catch (Throwable e2) {
            throw new JSONPointerException(String.format("%s is not an array index", new Object[]{str}), e2);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (String str : this.refTokens) {
            stringBuilder.append('/');
            stringBuilder.append(escape(str));
        }
        return stringBuilder.toString();
    }

    private String escape(String str) {
        return str.replace("~", "~0").replace("/", "~1").replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public String toURIFragment() {
        try {
            StringBuilder stringBuilder = new StringBuilder("#");
            for (String str : this.refTokens) {
                stringBuilder.append('/');
                stringBuilder.append(URLEncoder.encode(str, ENCODING));
            }
            return stringBuilder.toString();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
