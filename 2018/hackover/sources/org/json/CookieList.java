package org.json;

public class CookieList {
    public static JSONObject toJSONObject(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONTokener jSONTokener = new JSONTokener(str);
        while (jSONTokener.more()) {
            String unescape = Cookie.unescape(jSONTokener.nextTo('='));
            jSONTokener.next('=');
            jSONObject.put(unescape, Cookie.unescape(jSONTokener.nextTo(';')));
            jSONTokener.next();
        }
        return jSONObject;
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = null;
        for (String str : jSONObject.keySet()) {
            Object opt = jSONObject.opt(str);
            if (!JSONObject.NULL.equals(opt)) {
                if (obj != null) {
                    stringBuilder.append(';');
                }
                stringBuilder.append(Cookie.escape(str));
                stringBuilder.append("=");
                stringBuilder.append(Cookie.escape(opt.toString()));
                obj = 1;
            }
        }
        return stringBuilder.toString();
    }
}
