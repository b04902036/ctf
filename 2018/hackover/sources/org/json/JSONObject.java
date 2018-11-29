package org.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import kotlin.text.Typography;

public class JSONObject {
    public static final Object NULL = new Null();
    private final Map<String, Object> map;

    private static final class Null {
        protected final Object clone() {
            return this;
        }

        public boolean equals(Object obj) {
            return obj == null || obj == this;
        }

        public int hashCode() {
            return 0;
        }

        public String toString() {
            return "null";
        }

        private Null() {
        }
    }

    public JSONObject() {
        this.map = new HashMap();
    }

    public JSONObject(JSONObject jSONObject, String[] strArr) {
        this(strArr.length);
        int i = 0;
        while (i < strArr.length) {
            try {
                putOnce(strArr[i], jSONObject.opt(strArr[i]));
            } catch (Exception unused) {
                i++;
            }
        }
    }

    public JSONObject(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.nextClean() == '{') {
            while (true) {
                char nextClean = jSONTokener.nextClean();
                if (nextClean == 0) {
                    throw jSONTokener.syntaxError("A JSONObject text must end with '}'");
                } else if (nextClean != '}') {
                    jSONTokener.back();
                    String obj = jSONTokener.nextValue().toString();
                    if (jSONTokener.nextClean() == ':') {
                        if (obj != null) {
                            if (opt(obj) == null) {
                                Object nextValue = jSONTokener.nextValue();
                                if (nextValue != null) {
                                    put(obj, nextValue);
                                }
                            } else {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("Duplicate key \"");
                                stringBuilder.append(obj);
                                stringBuilder.append("\"");
                                throw jSONTokener.syntaxError(stringBuilder.toString());
                            }
                        }
                        nextClean = jSONTokener.nextClean();
                        if (nextClean == ',' || nextClean == ';') {
                            if (jSONTokener.nextClean() != '}') {
                                jSONTokener.back();
                            } else {
                                return;
                            }
                        } else if (nextClean != '}') {
                            throw jSONTokener.syntaxError("Expected a ',' or '}'");
                        } else {
                            return;
                        }
                    }
                    throw jSONTokener.syntaxError("Expected a ':' after a key");
                } else {
                    return;
                }
            }
        }
        throw jSONTokener.syntaxError("A JSONObject text must begin with '{'");
    }

    public JSONObject(Map<?, ?> map) {
        if (map == null) {
            this.map = new HashMap();
            return;
        }
        this.map = new HashMap(map.size());
        for (Entry entry : map.entrySet()) {
            if (entry.getKey() != null) {
                Object value = entry.getValue();
                if (value != null) {
                    this.map.put(String.valueOf(entry.getKey()), wrap(value));
                }
            } else {
                throw new NullPointerException("Null key.");
            }
        }
    }

    public JSONObject(Object obj) {
        this();
        populateMap(obj);
    }

    public JSONObject(Object obj, String[] strArr) {
        this(strArr.length);
        Class cls = obj.getClass();
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            try {
                putOpt(str, cls.getField(str).get(obj));
            } catch (Exception unused) {
                i++;
            }
        }
    }

    public JSONObject(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONObject(String str, Locale locale) throws JSONException {
        this();
        ResourceBundle bundle = ResourceBundle.getBundle(str, locale, Thread.currentThread().getContextClassLoader());
        Enumeration keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            Object nextElement = keys.nextElement();
            if (nextElement != null) {
                String str2 = (String) nextElement;
                String[] split = str2.split("\\.");
                int length = split.length - 1;
                JSONObject jSONObject = this;
                for (int i = 0; i < length; i++) {
                    String str3 = split[i];
                    JSONObject optJSONObject = jSONObject.optJSONObject(str3);
                    if (optJSONObject == null) {
                        optJSONObject = new JSONObject();
                        jSONObject.put(str3, (Object) optJSONObject);
                    }
                    jSONObject = optJSONObject;
                }
                jSONObject.put(split[length], bundle.getString(str2));
            }
        }
    }

    protected JSONObject(int i) {
        this.map = new HashMap(i);
    }

    public JSONObject accumulate(String str, Object obj) throws JSONException {
        testValidity(obj);
        Object opt = opt(str);
        if (opt == null) {
            if (obj instanceof JSONArray) {
                obj = new JSONArray().put(obj);
            }
            put(str, obj);
        } else if (opt instanceof JSONArray) {
            ((JSONArray) opt).put(obj);
        } else {
            put(str, new JSONArray().put(opt).put(obj));
        }
        return this;
    }

    public JSONObject append(String str, Object obj) throws JSONException {
        testValidity(obj);
        Object opt = opt(str);
        if (opt == null) {
            put(str, new JSONArray().put(obj));
        } else if (opt instanceof JSONArray) {
            put(str, ((JSONArray) opt).put(obj));
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSONObject[");
            stringBuilder.append(str);
            stringBuilder.append("] is not a JSONArray.");
            throw new JSONException(stringBuilder.toString());
        }
        return this;
    }

    public static String doubleToString(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            return "null";
        }
        String d2 = Double.toString(d);
        if (d2.indexOf(46) > 0 && d2.indexOf(101) < 0 && d2.indexOf(69) < 0) {
            while (d2.endsWith("0")) {
                d2 = d2.substring(0, d2.length() - 1);
            }
            if (d2.endsWith(".")) {
                d2 = d2.substring(0, d2.length() - 1);
            }
        }
        return d2;
    }

    public Object get(String str) throws JSONException {
        if (str != null) {
            Object opt = opt(str);
            if (opt != null) {
                return opt;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSONObject[");
            stringBuilder.append(quote(str));
            stringBuilder.append("] not found.");
            throw new JSONException(stringBuilder.toString());
        }
        throw new JSONException("Null key.");
    }

    public <E extends Enum<E>> E getEnum(Class<E> cls, String str) throws JSONException {
        E optEnum = optEnum(cls, str);
        if (optEnum != null) {
            return optEnum;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONObject[");
        stringBuilder.append(quote(str));
        stringBuilder.append("] is not an enum of type ");
        stringBuilder.append(quote(cls.getSimpleName()));
        stringBuilder.append(".");
        throw new JSONException(stringBuilder.toString());
    }

    public boolean getBoolean(String str) throws JSONException {
        Object obj = get(str);
        if (!obj.equals(Boolean.FALSE)) {
            boolean z = obj instanceof String;
            if (!(z && ((String) obj).equalsIgnoreCase("false"))) {
                if (obj.equals(Boolean.TRUE) || (z && ((String) obj).equalsIgnoreCase("true"))) {
                    return true;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("JSONObject[");
                stringBuilder.append(quote(str));
                stringBuilder.append("] is not a Boolean.");
                throw new JSONException(stringBuilder.toString());
            }
        }
        return false;
    }

    public BigInteger getBigInteger(String str) throws JSONException {
        try {
            return new BigInteger(get(str).toString());
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSONObject[");
            stringBuilder.append(quote(str));
            stringBuilder.append("] could not be converted to BigInteger.");
            throw new JSONException(stringBuilder.toString(), e);
        }
    }

    public BigDecimal getBigDecimal(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        try {
            return new BigDecimal(obj.toString());
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSONObject[");
            stringBuilder.append(quote(str));
            stringBuilder.append("] could not be converted to BigDecimal.");
            throw new JSONException(stringBuilder.toString(), e);
        }
    }

    public double getDouble(String str) throws JSONException {
        Object obj = get(str);
        try {
            if (obj instanceof Number) {
                return ((Number) obj).doubleValue();
            }
            return Double.parseDouble(obj.toString());
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSONObject[");
            stringBuilder.append(quote(str));
            stringBuilder.append("] is not a number.");
            throw new JSONException(stringBuilder.toString(), e);
        }
    }

    public float getFloat(String str) throws JSONException {
        Object obj = get(str);
        try {
            if (obj instanceof Number) {
                return ((Number) obj).floatValue();
            }
            return Float.parseFloat(obj.toString());
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSONObject[");
            stringBuilder.append(quote(str));
            stringBuilder.append("] is not a number.");
            throw new JSONException(stringBuilder.toString(), e);
        }
    }

    public Number getNumber(String str) throws JSONException {
        Object obj = get(str);
        try {
            if (obj instanceof Number) {
                return (Number) obj;
            }
            return stringToNumber(obj.toString());
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSONObject[");
            stringBuilder.append(quote(str));
            stringBuilder.append("] is not a number.");
            throw new JSONException(stringBuilder.toString(), e);
        }
    }

    public int getInt(String str) throws JSONException {
        Object obj = get(str);
        try {
            if (obj instanceof Number) {
                return ((Number) obj).intValue();
            }
            return Integer.parseInt((String) obj);
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSONObject[");
            stringBuilder.append(quote(str));
            stringBuilder.append("] is not an int.");
            throw new JSONException(stringBuilder.toString(), e);
        }
    }

    public JSONArray getJSONArray(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONObject[");
        stringBuilder.append(quote(str));
        stringBuilder.append("] is not a JSONArray.");
        throw new JSONException(stringBuilder.toString());
    }

    public JSONObject getJSONObject(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONObject[");
        stringBuilder.append(quote(str));
        stringBuilder.append("] is not a JSONObject.");
        throw new JSONException(stringBuilder.toString());
    }

    public long getLong(String str) throws JSONException {
        Object obj = get(str);
        try {
            if (obj instanceof Number) {
                return ((Number) obj).longValue();
            }
            return Long.parseLong((String) obj);
        } catch (Throwable e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("JSONObject[");
            stringBuilder.append(quote(str));
            stringBuilder.append("] is not a long.");
            throw new JSONException(stringBuilder.toString(), e);
        }
    }

    public static String[] getNames(JSONObject jSONObject) {
        if (jSONObject.isEmpty()) {
            return null;
        }
        return (String[]) jSONObject.keySet().toArray(new String[jSONObject.length()]);
    }

    public static String[] getNames(Object obj) {
        if (obj == null) {
            return null;
        }
        Field[] fields = obj.getClass().getFields();
        int length = fields.length;
        if (length == 0) {
            return null;
        }
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = fields[i].getName();
        }
        return strArr;
    }

    public String getString(String str) throws JSONException {
        Object obj = get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("JSONObject[");
        stringBuilder.append(quote(str));
        stringBuilder.append("] not a string.");
        throw new JSONException(stringBuilder.toString());
    }

    public boolean has(String str) {
        return this.map.containsKey(str);
    }

    public JSONObject increment(String str) throws JSONException {
        Object opt = opt(str);
        if (opt == null) {
            put(str, 1);
        } else if (opt instanceof BigInteger) {
            put(str, ((BigInteger) opt).add(BigInteger.ONE));
        } else if (opt instanceof BigDecimal) {
            put(str, ((BigDecimal) opt).add(BigDecimal.ONE));
        } else if (opt instanceof Integer) {
            put(str, ((Integer) opt).intValue() + 1);
        } else if (opt instanceof Long) {
            put(str, ((Long) opt).longValue() + 1);
        } else if (opt instanceof Double) {
            put(str, ((Double) opt).doubleValue() + 1.0d);
        } else if (opt instanceof Float) {
            put(str, ((Float) opt).floatValue() + 1.0f);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to increment [");
            stringBuilder.append(quote(str));
            stringBuilder.append("].");
            throw new JSONException(stringBuilder.toString());
        }
        return this;
    }

    public boolean isNull(String str) {
        return NULL.equals(opt(str));
    }

    public Iterator<String> keys() {
        return keySet().iterator();
    }

    public Set<String> keySet() {
        return this.map.keySet();
    }

    protected Set<Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    public int length() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public JSONArray names() {
        if (this.map.isEmpty()) {
            return null;
        }
        return new JSONArray(this.map.keySet());
    }

    public static String numberToString(Number number) throws JSONException {
        if (number != null) {
            testValidity(number);
            String obj = number.toString();
            if (obj.indexOf(46) <= 0 || obj.indexOf(101) >= 0 || obj.indexOf(69) >= 0) {
                return obj;
            }
            while (obj.endsWith("0")) {
                obj = obj.substring(0, obj.length() - 1);
            }
            return obj.endsWith(".") ? obj.substring(0, obj.length() - 1) : obj;
        } else {
            throw new JSONException("Null pointer");
        }
    }

    public Object opt(String str) {
        return str == null ? null : this.map.get(str);
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, String str) {
        return optEnum(cls, str, null);
    }

    public <E extends Enum<E>> E optEnum(Class<E> cls, String str, E e) {
        try {
            Object opt = opt(str);
            if (NULL.equals(opt)) {
                return e;
            }
            if (cls.isAssignableFrom(opt.getClass())) {
                return (Enum) opt;
            }
            return Enum.valueOf(cls, opt.toString());
        } catch (IllegalArgumentException unused) {
            return e;
        } catch (NullPointerException unused2) {
            return e;
        }
    }

    public boolean optBoolean(String str) {
        return optBoolean(str, false);
    }

    public boolean optBoolean(String str, boolean z) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return z;
        }
        if (opt instanceof Boolean) {
            return ((Boolean) opt).booleanValue();
        }
        try {
            return getBoolean(str);
        } catch (Exception unused) {
            return z;
        }
    }

    public BigDecimal optBigDecimal(String str, BigDecimal bigDecimal) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return bigDecimal;
        }
        if (opt instanceof BigDecimal) {
            return (BigDecimal) opt;
        }
        if (opt instanceof BigInteger) {
            return new BigDecimal((BigInteger) opt);
        }
        if ((opt instanceof Double) || (opt instanceof Float)) {
            return new BigDecimal(((Number) opt).doubleValue());
        }
        if ((opt instanceof Long) || (opt instanceof Integer) || (opt instanceof Short) || (opt instanceof Byte)) {
            return new BigDecimal(((Number) opt).longValue());
        }
        try {
            return new BigDecimal(opt.toString());
        } catch (Exception unused) {
            return bigDecimal;
        }
    }

    public BigInteger optBigInteger(String str, BigInteger bigInteger) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return bigInteger;
        }
        if (opt instanceof BigInteger) {
            return (BigInteger) opt;
        }
        if (opt instanceof BigDecimal) {
            return ((BigDecimal) opt).toBigInteger();
        }
        if ((opt instanceof Double) || (opt instanceof Float)) {
            return new BigDecimal(((Number) opt).doubleValue()).toBigInteger();
        }
        if ((opt instanceof Long) || (opt instanceof Integer) || (opt instanceof Short) || (opt instanceof Byte)) {
            return BigInteger.valueOf(((Number) opt).longValue());
        }
        try {
            String obj = opt.toString();
            if (isDecimalNotation(obj)) {
                return new BigDecimal(obj).toBigInteger();
            }
            return new BigInteger(obj);
        } catch (Exception unused) {
            return bigInteger;
        }
    }

    public double optDouble(String str) {
        return optDouble(str, Double.NaN);
    }

    public double optDouble(String str, double d) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return d;
        }
        if (opt instanceof Number) {
            return ((Number) opt).doubleValue();
        }
        if (!(opt instanceof String)) {
            return d;
        }
        try {
            return Double.parseDouble((String) opt);
        } catch (Exception unused) {
            return d;
        }
    }

    public float optFloat(String str) {
        return optFloat(str, Float.NaN);
    }

    public float optFloat(String str, float f) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return f;
        }
        if (opt instanceof Number) {
            return ((Number) opt).floatValue();
        }
        if (!(opt instanceof String)) {
            return f;
        }
        try {
            return Float.parseFloat((String) opt);
        } catch (Exception unused) {
            return f;
        }
    }

    public int optInt(String str) {
        return optInt(str, 0);
    }

    public int optInt(String str, int i) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return i;
        }
        if (opt instanceof Number) {
            return ((Number) opt).intValue();
        }
        if (!(opt instanceof String)) {
            return i;
        }
        try {
            return new BigDecimal((String) opt).intValue();
        } catch (Exception unused) {
            return i;
        }
    }

    public JSONArray optJSONArray(String str) {
        Object opt = opt(str);
        return opt instanceof JSONArray ? (JSONArray) opt : null;
    }

    public JSONObject optJSONObject(String str) {
        Object opt = opt(str);
        return opt instanceof JSONObject ? (JSONObject) opt : null;
    }

    public long optLong(String str) {
        return optLong(str, 0);
    }

    public long optLong(String str, long j) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return j;
        }
        if (opt instanceof Number) {
            return ((Number) opt).longValue();
        }
        if (!(opt instanceof String)) {
            return j;
        }
        try {
            return new BigDecimal((String) opt).longValue();
        } catch (Exception unused) {
            return j;
        }
    }

    public Number optNumber(String str) {
        return optNumber(str, null);
    }

    public Number optNumber(String str, Number number) {
        Object opt = opt(str);
        if (NULL.equals(opt)) {
            return number;
        }
        if (opt instanceof Number) {
            return (Number) opt;
        }
        if (!(opt instanceof String)) {
            return number;
        }
        try {
            return stringToNumber((String) opt);
        } catch (Exception unused) {
            return number;
        }
    }

    public String optString(String str) {
        return optString(str, "");
    }

    public String optString(String str, String str2) {
        Object opt = opt(str);
        return NULL.equals(opt) ? str2 : opt.toString();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    private void populateMap(java.lang.Object r9) {
        /*
        r8 = this;
        r0 = r9.getClass();
        r1 = r0.getClassLoader();
        r2 = 0;
        if (r1 == 0) goto L_0x000d;
    L_0x000b:
        r1 = 1;
        goto L_0x000e;
    L_0x000d:
        r1 = r2;
    L_0x000e:
        if (r1 == 0) goto L_0x0015;
    L_0x0010:
        r0 = r0.getMethods();
        goto L_0x0019;
    L_0x0015:
        r0 = r0.getDeclaredMethods();
    L_0x0019:
        r1 = r0.length;
        r3 = r2;
    L_0x001b:
        if (r3 >= r1) goto L_0x0077;
    L_0x001d:
        r4 = r0[r3];
        r5 = r4.getModifiers();
        r6 = java.lang.reflect.Modifier.isPublic(r5);
        if (r6 == 0) goto L_0x0074;
    L_0x0029:
        r5 = java.lang.reflect.Modifier.isStatic(r5);
        if (r5 != 0) goto L_0x0074;
    L_0x002f:
        r5 = r4.getParameterTypes();
        r5 = r5.length;
        if (r5 != 0) goto L_0x0074;
    L_0x0036:
        r5 = r4.isBridge();
        if (r5 != 0) goto L_0x0074;
    L_0x003c:
        r5 = r4.getReturnType();
        r6 = java.lang.Void.TYPE;
        if (r5 == r6) goto L_0x0074;
    L_0x0044:
        r5 = r4.getName();
        r5 = r8.isValidMethodName(r5);
        if (r5 == 0) goto L_0x0074;
    L_0x004e:
        r5 = r8.getKeyNameFromMethod(r4);
        if (r5 == 0) goto L_0x0074;
    L_0x0054:
        r6 = r5.isEmpty();
        if (r6 != 0) goto L_0x0074;
    L_0x005a:
        r6 = new java.lang.Object[r2];	 Catch:{ IllegalAccessException -> 0x0074, IllegalAccessException -> 0x0074 }
        r4 = r4.invoke(r9, r6);	 Catch:{ IllegalAccessException -> 0x0074, IllegalAccessException -> 0x0074 }
        if (r4 == 0) goto L_0x0074;
    L_0x0062:
        r6 = r8.map;	 Catch:{ IllegalAccessException -> 0x0074, IllegalAccessException -> 0x0074 }
        r7 = wrap(r4);	 Catch:{ IllegalAccessException -> 0x0074, IllegalAccessException -> 0x0074 }
        r6.put(r5, r7);	 Catch:{ IllegalAccessException -> 0x0074, IllegalAccessException -> 0x0074 }
        r5 = r4 instanceof java.io.Closeable;	 Catch:{ IllegalAccessException -> 0x0074, IllegalAccessException -> 0x0074 }
        if (r5 == 0) goto L_0x0074;
    L_0x006f:
        r4 = (java.io.Closeable) r4;	 Catch:{ IllegalAccessException -> 0x0074 }
        r4.close();	 Catch:{ IllegalAccessException -> 0x0074 }
    L_0x0074:
        r3 = r3 + 1;
        goto L_0x001b;
    L_0x0077:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.populateMap(java.lang.Object):void");
    }

    private boolean isValidMethodName(String str) {
        return ("getClass".equals(str) || "getDeclaringClass".equals(str)) ? false : true;
    }

    private String getKeyNameFromMethod(Method method) {
        int annotationDepth = getAnnotationDepth(method, JSONPropertyIgnore.class);
        if (annotationDepth > 0) {
            int annotationDepth2 = getAnnotationDepth(method, JSONPropertyName.class);
            if (annotationDepth2 < 0 || annotationDepth <= annotationDepth2) {
                return null;
            }
        }
        JSONPropertyName jSONPropertyName = (JSONPropertyName) getAnnotation(method, JSONPropertyName.class);
        if (jSONPropertyName != null && jSONPropertyName.value() != null && !jSONPropertyName.value().isEmpty()) {
            return jSONPropertyName.value();
        }
        String name = method.getName();
        if (name.startsWith("get") && name.length() > 3) {
            name = name.substring(3);
        } else if (!name.startsWith("is") || name.length() <= 2) {
            return null;
        } else {
            name = name.substring(2);
        }
        if (Character.isLowerCase(name.charAt(0))) {
            return null;
        }
        if (name.length() == 1) {
            name = name.toLowerCase(Locale.ROOT);
        } else if (!Character.isUpperCase(name.charAt(1))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(name.substring(0, 1).toLowerCase(Locale.ROOT));
            stringBuilder.append(name.substring(1));
            name = stringBuilder.toString();
        }
        return name;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0037 A:{LOOP_END, LOOP:0: B:11:0x0022->B:16:0x0037, Splitter: B:13:0x0026, ExcHandler: java.lang.SecurityException (unused java.lang.SecurityException)} */
    /* JADX WARNING: Missing block: B:16:0x0037, code:
            r4 = r4 + 1;
     */
    private static <A extends java.lang.annotation.Annotation> A getAnnotation(java.lang.reflect.Method r8, java.lang.Class<A> r9) {
        /*
        r0 = 0;
        if (r8 == 0) goto L_0x0051;
    L_0x0003:
        if (r9 != 0) goto L_0x0006;
    L_0x0005:
        goto L_0x0051;
    L_0x0006:
        r1 = r8.isAnnotationPresent(r9);
        if (r1 == 0) goto L_0x0011;
    L_0x000c:
        r8 = r8.getAnnotation(r9);
        return r8;
    L_0x0011:
        r1 = r8.getDeclaringClass();
        r2 = r1.getSuperclass();
        if (r2 != 0) goto L_0x001c;
    L_0x001b:
        return r0;
    L_0x001c:
        r2 = r1.getInterfaces();
        r3 = r2.length;
        r4 = 0;
    L_0x0022:
        if (r4 >= r3) goto L_0x003a;
    L_0x0024:
        r5 = r2[r4];
        r6 = r8.getName();	 Catch:{ SecurityException -> 0x0037, SecurityException -> 0x0037 }
        r7 = r8.getParameterTypes();	 Catch:{ SecurityException -> 0x0037, SecurityException -> 0x0037 }
        r5 = r5.getMethod(r6, r7);	 Catch:{ SecurityException -> 0x0037, SecurityException -> 0x0037 }
        r5 = getAnnotation(r5, r9);	 Catch:{ SecurityException -> 0x0037, SecurityException -> 0x0037 }
        return r5;
    L_0x0037:
        r4 = r4 + 1;
        goto L_0x0022;
    L_0x003a:
        r1 = r1.getSuperclass();	 Catch:{ SecurityException -> 0x0050, NoSuchMethodException -> 0x004f }
        r2 = r8.getName();	 Catch:{ SecurityException -> 0x0050, NoSuchMethodException -> 0x004f }
        r8 = r8.getParameterTypes();	 Catch:{ SecurityException -> 0x0050, NoSuchMethodException -> 0x004f }
        r8 = r1.getMethod(r2, r8);	 Catch:{ SecurityException -> 0x0050, NoSuchMethodException -> 0x004f }
        r8 = getAnnotation(r8, r9);	 Catch:{ SecurityException -> 0x0050, NoSuchMethodException -> 0x004f }
        return r8;
    L_0x004f:
        return r0;
    L_0x0050:
        return r0;
    L_0x0051:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.getAnnotation(java.lang.reflect.Method, java.lang.Class):A");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0037 A:{LOOP_END, LOOP:0: B:10:0x001f->B:17:0x0037, Splitter: B:12:0x0023, ExcHandler: java.lang.SecurityException (unused java.lang.SecurityException)} */
    /* JADX WARNING: Missing block: B:17:0x0037, code:
            r5 = r5 + 1;
     */
    /* JADX WARNING: Missing block: B:28:0x0037, code:
            continue;
     */
    private static int getAnnotationDepth(java.lang.reflect.Method r9, java.lang.Class<? extends java.lang.annotation.Annotation> r10) {
        /*
        r0 = -1;
        if (r9 == 0) goto L_0x0055;
    L_0x0003:
        if (r10 != 0) goto L_0x0006;
    L_0x0005:
        goto L_0x0055;
    L_0x0006:
        r1 = r9.isAnnotationPresent(r10);
        r2 = 1;
        if (r1 == 0) goto L_0x000e;
    L_0x000d:
        return r2;
    L_0x000e:
        r1 = r9.getDeclaringClass();
        r3 = r1.getSuperclass();
        if (r3 != 0) goto L_0x0019;
    L_0x0018:
        return r0;
    L_0x0019:
        r3 = r1.getInterfaces();
        r4 = r3.length;
        r5 = 0;
    L_0x001f:
        if (r5 >= r4) goto L_0x003a;
    L_0x0021:
        r6 = r3[r5];
        r7 = r9.getName();	 Catch:{ SecurityException -> 0x0037, SecurityException -> 0x0037 }
        r8 = r9.getParameterTypes();	 Catch:{ SecurityException -> 0x0037, SecurityException -> 0x0037 }
        r6 = r6.getMethod(r7, r8);	 Catch:{ SecurityException -> 0x0037, SecurityException -> 0x0037 }
        r6 = getAnnotationDepth(r6, r10);	 Catch:{ SecurityException -> 0x0037, SecurityException -> 0x0037 }
        if (r6 <= 0) goto L_0x0037;
    L_0x0035:
        r6 = r6 + r2;
        return r6;
    L_0x0037:
        r5 = r5 + 1;
        goto L_0x001f;
    L_0x003a:
        r1 = r1.getSuperclass();	 Catch:{ SecurityException -> 0x0054, NoSuchMethodException -> 0x0053 }
        r3 = r9.getName();	 Catch:{ SecurityException -> 0x0054, NoSuchMethodException -> 0x0053 }
        r9 = r9.getParameterTypes();	 Catch:{ SecurityException -> 0x0054, NoSuchMethodException -> 0x0053 }
        r9 = r1.getMethod(r3, r9);	 Catch:{ SecurityException -> 0x0054, NoSuchMethodException -> 0x0053 }
        r9 = getAnnotationDepth(r9, r10);	 Catch:{ SecurityException -> 0x0054, NoSuchMethodException -> 0x0053 }
        if (r9 <= 0) goto L_0x0052;
    L_0x0050:
        r9 = r9 + r2;
        return r9;
    L_0x0052:
        return r0;
    L_0x0053:
        return r0;
    L_0x0054:
        return r0;
    L_0x0055:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.getAnnotationDepth(java.lang.reflect.Method, java.lang.Class):int");
    }

    public JSONObject put(String str, boolean z) throws JSONException {
        return put(str, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public JSONObject put(String str, Collection<?> collection) throws JSONException {
        return put(str, new JSONArray((Collection) collection));
    }

    public JSONObject put(String str, double d) throws JSONException {
        return put(str, Double.valueOf(d));
    }

    public JSONObject put(String str, float f) throws JSONException {
        return put(str, Float.valueOf(f));
    }

    public JSONObject put(String str, int i) throws JSONException {
        return put(str, Integer.valueOf(i));
    }

    public JSONObject put(String str, long j) throws JSONException {
        return put(str, Long.valueOf(j));
    }

    public JSONObject put(String str, Map<?, ?> map) throws JSONException {
        return put(str, new JSONObject((Map) map));
    }

    public JSONObject put(String str, Object obj) throws JSONException {
        if (str != null) {
            if (obj != null) {
                testValidity(obj);
                this.map.put(str, obj);
            } else {
                remove(str);
            }
            return this;
        }
        throw new NullPointerException("Null key.");
    }

    public JSONObject putOnce(String str, Object obj) throws JSONException {
        if (str == null || obj == null) {
            return this;
        }
        if (opt(str) == null) {
            return put(str, obj);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Duplicate key \"");
        stringBuilder.append(str);
        stringBuilder.append("\"");
        throw new JSONException(stringBuilder.toString());
    }

    public JSONObject putOpt(String str, Object obj) throws JSONException {
        return (str == null || obj == null) ? this : put(str, obj);
    }

    public Object query(String str) {
        return query(new JSONPointer(str));
    }

    public Object query(JSONPointer jSONPointer) {
        return jSONPointer.queryFrom(this);
    }

    public Object optQuery(String str) {
        return optQuery(new JSONPointer(str));
    }

    public Object optQuery(JSONPointer jSONPointer) {
        try {
            return jSONPointer.queryFrom(this);
        } catch (JSONPointerException unused) {
            return null;
        }
    }

    public static String quote(String str) {
        Writer stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            try {
                str = quote(str, stringWriter).toString();
            } catch (IOException unused) {
                return "";
            }
        }
        return str;
    }

    public static Writer quote(String str, Writer writer) throws IOException {
        if (str == null || str.isEmpty()) {
            writer.write("\"\"");
            return writer;
        }
        int length = str.length();
        writer.write(34);
        int i = 0;
        int i2 = i;
        while (i < length) {
            char i22;
            char charAt = str.charAt(i);
            if (charAt != Typography.quote) {
                if (charAt != '/') {
                    if (charAt != '\\') {
                        switch (charAt) {
                            case 8:
                                writer.write("\\b");
                                break;
                            case 9:
                                writer.write("\\t");
                                break;
                            case 10:
                                writer.write("\\n");
                                break;
                            default:
                                switch (charAt) {
                                    case 12:
                                        writer.write("\\f");
                                        break;
                                    case 13:
                                        writer.write("\\r");
                                        break;
                                    default:
                                        if (charAt >= ' ' && ((charAt < 128 || charAt >= Typography.nbsp) && (charAt < 8192 || charAt >= 8448))) {
                                            writer.write(charAt);
                                            break;
                                        }
                                        writer.write("\\u");
                                        String toHexString = Integer.toHexString(charAt);
                                        writer.write("0000", 0, 4 - toHexString.length());
                                        writer.write(toHexString);
                                        break;
                                }
                        }
                    }
                }
                if (i22 == 60) {
                    writer.write(92);
                }
                writer.write(charAt);
                i++;
                i22 = charAt;
            }
            writer.write(92);
            writer.write(charAt);
            i++;
            i22 = charAt;
        }
        writer.write(34);
        return writer;
    }

    public Object remove(String str) {
        return this.map.remove(str);
    }

    public boolean similar(Object obj) {
        try {
            if (!(obj instanceof JSONObject) || !keySet().equals(((JSONObject) obj).keySet())) {
                return false;
            }
            for (Entry entry : entrySet()) {
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                Object obj2 = ((JSONObject) obj).get(str);
                if (value != obj2) {
                    if (value == null) {
                        return false;
                    }
                    if (value instanceof JSONObject) {
                        if (!((JSONObject) value).similar(obj2)) {
                            return false;
                        }
                    } else if (value instanceof JSONArray) {
                        if (!((JSONArray) value).similar(obj2)) {
                            return false;
                        }
                    } else if (!value.equals(obj2)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    protected static boolean isDecimalNotation(String str) {
        return str.indexOf(46) > -1 || str.indexOf(101) > -1 || str.indexOf(69) > -1 || "-0".equals(str);
    }

    protected static Number stringToNumber(String str) throws NumberFormatException {
        char charAt = str.charAt(0);
        Number bigInteger;
        if ((charAt < '0' || charAt > '9') && charAt != '-') {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("val [");
            stringBuilder.append(str);
            stringBuilder.append("] is not a valid number.");
            throw new NumberFormatException(stringBuilder.toString());
        } else if (!isDecimalNotation(str)) {
            bigInteger = new BigInteger(str);
            if (bigInteger.bitLength() <= 31) {
                return Integer.valueOf(bigInteger.intValue());
            }
            return bigInteger.bitLength() <= 63 ? Long.valueOf(bigInteger.longValue()) : bigInteger;
        } else if (str.length() > 14) {
            return new BigDecimal(str);
        } else {
            bigInteger = Double.valueOf(str);
            if (bigInteger.isInfinite() || bigInteger.isNaN()) {
                return new BigDecimal(str);
            }
            return bigInteger;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0077 A:{RETURN, Splitter: B:21:0x003b, ExcHandler: java.lang.Exception (unused java.lang.Exception)} */
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
        r5 = NULL;
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
        if (r0 != r1) goto L_0x0077;
    L_0x003b:
        r0 = isDecimalNotation(r5);	 Catch:{ Exception -> 0x0077 }
        if (r0 == 0) goto L_0x0052;
    L_0x0041:
        r0 = java.lang.Double.valueOf(r5);	 Catch:{ Exception -> 0x0077 }
        r1 = r0.isInfinite();	 Catch:{ Exception -> 0x0077 }
        if (r1 != 0) goto L_0x0077;
    L_0x004b:
        r1 = r0.isNaN();	 Catch:{ Exception -> 0x0077 }
        if (r1 != 0) goto L_0x0077;
    L_0x0051:
        return r0;
    L_0x0052:
        r0 = java.lang.Long.valueOf(r5);	 Catch:{ Exception -> 0x0077 }
        r1 = r0.toString();	 Catch:{ Exception -> 0x0077 }
        r1 = r5.equals(r1);	 Catch:{ Exception -> 0x0077 }
        if (r1 == 0) goto L_0x0077;
    L_0x0060:
        r1 = r0.longValue();	 Catch:{ Exception -> 0x0077 }
        r3 = r0.intValue();	 Catch:{ Exception -> 0x0077 }
        r3 = (long) r3;	 Catch:{ Exception -> 0x0077 }
        r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r1 != 0) goto L_0x0076;
    L_0x006d:
        r0 = r0.intValue();	 Catch:{ Exception -> 0x0077 }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x0077 }
        return r0;
    L_0x0076:
        return r0;
    L_0x0077:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONObject.stringToValue(java.lang.String):java.lang.Object");
    }

    public static void testValidity(Object obj) throws JSONException {
        if (obj == null) {
            return;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.isInfinite() || d.isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        } else if (obj instanceof Float) {
            Float f = (Float) obj;
            if (f.isInfinite() || f.isNaN()) {
                throw new JSONException("JSON does not allow non-finite numbers.");
            }
        }
    }

    public JSONArray toJSONArray(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null || jSONArray.isEmpty()) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            jSONArray2.put(opt(jSONArray.getString(i)));
        }
        return jSONArray2;
    }

    public String toString() {
        try {
            return toString(0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String toString(int i) throws JSONException {
        String obj;
        Writer stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            obj = write(stringWriter, i, 0).toString();
        }
        return obj;
    }

    public static String valueToString(Object obj) throws JSONException {
        return JSONWriter.valueToString(obj);
    }

    public static Object wrap(Object obj) {
        if (obj == null) {
            try {
                return NULL;
            } catch (Exception unused) {
                return null;
            }
        } else if ((obj instanceof JSONObject) || (obj instanceof JSONArray) || NULL.equals(obj) || (obj instanceof JSONString) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Boolean) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof BigInteger) || (obj instanceof BigDecimal) || (obj instanceof Enum)) {
            return obj;
        } else {
            if (obj instanceof Collection) {
                return new JSONArray((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                return new JSONArray(obj);
            }
            if (obj instanceof Map) {
                return new JSONObject((Map) obj);
            }
            Package packageR = obj.getClass().getPackage();
            String name = packageR != null ? packageR.getName() : "";
            if (name.startsWith("java.") || name.startsWith("javax.") || obj.getClass().getClassLoader() == null) {
                return obj.toString();
            }
            return new JSONObject(obj);
        }
    }

    public Writer write(Writer writer) throws JSONException {
        return write(writer, 0, 0);
    }

    static final Writer writeValue(Writer writer, Object obj, int i, int i2) throws JSONException, IOException {
        if (obj == null || obj.equals(null)) {
            writer.write("null");
        } else if (obj instanceof JSONString) {
            try {
                String toJSONString = ((JSONString) obj).toJSONString();
                writer.write(toJSONString != null ? toJSONString.toString() : quote(obj.toString()));
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        } else if (obj instanceof Number) {
            String numberToString = numberToString((Number) obj);
            try {
                BigDecimal bigDecimal = new BigDecimal(numberToString);
                writer.write(numberToString);
            } catch (NumberFormatException unused) {
                quote(numberToString, writer);
            }
        } else if (obj instanceof Boolean) {
            writer.write(obj.toString());
        } else if (obj instanceof Enum) {
            writer.write(quote(((Enum) obj).name()));
        } else if (obj instanceof JSONObject) {
            ((JSONObject) obj).write(writer, i, i2);
        } else if (obj instanceof JSONArray) {
            ((JSONArray) obj).write(writer, i, i2);
        } else if (obj instanceof Map) {
            new JSONObject((Map) obj).write(writer, i, i2);
        } else if (obj instanceof Collection) {
            new JSONArray((Collection) obj).write(writer, i, i2);
        } else if (obj.getClass().isArray()) {
            new JSONArray(obj).write(writer, i, i2);
        } else {
            quote(obj.toString(), writer);
        }
        return writer;
    }

    static final void indent(Writer writer, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            writer.write(32);
        }
    }

    public Writer write(Writer writer, int i, int i2) throws JSONException {
        StringBuilder stringBuilder;
        Object obj = null;
        String str;
        try {
            int length = length();
            writer.write(123);
            if (length == 1) {
                Entry entry = (Entry) entrySet().iterator().next();
                str = (String) entry.getKey();
                writer.write(quote(str));
                writer.write(58);
                if (i > 0) {
                    writer.write(32);
                }
                writeValue(writer, entry.getValue(), i, i2);
            } else if (length != 0) {
                length = i2 + i;
                for (Entry entry2 : entrySet()) {
                    if (obj != null) {
                        writer.write(44);
                    }
                    if (i > 0) {
                        writer.write(10);
                    }
                    indent(writer, length);
                    str = (String) entry2.getKey();
                    writer.write(quote(str));
                    writer.write(58);
                    if (i > 0) {
                        writer.write(32);
                    }
                    writeValue(writer, entry2.getValue(), i, length);
                    int obj2 = 1;
                }
                if (i > 0) {
                    writer.write(10);
                }
                indent(writer, i2);
            }
            writer.write(125);
            return writer;
        } catch (Throwable e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to write JSONObject value for key: ");
            stringBuilder.append(str);
            throw new JSONException(stringBuilder.toString(), e);
        } catch (Throwable e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to write JSONObject value for key: ");
            stringBuilder.append(str);
            throw new JSONException(stringBuilder.toString(), e2);
        } catch (Throwable e22) {
            throw new JSONException(e22);
        }
    }

    public Map<String, Object> toMap() {
        Map<String, Object> hashMap = new HashMap();
        for (Entry entry : entrySet()) {
            Object toMap = (entry.getValue() == null || NULL.equals(entry.getValue())) ? null : entry.getValue() instanceof JSONObject ? ((JSONObject) entry.getValue()).toMap() : entry.getValue() instanceof JSONArray ? ((JSONArray) entry.getValue()).toList() : entry.getValue();
            hashMap.put(entry.getKey(), toMap);
        }
        return hashMap;
    }
}
