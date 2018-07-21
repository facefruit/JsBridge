package com.klavor.browser;

import android.util.Log;
import android.webkit.WebView;

import com.klavor.browser.jsbridge.JsExcutor;
import com.klavor.browser.jsbridge.api.JsApi;
import com.klavor.browser.jsbridge.api.JsApiMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

public class JsBridge {
    private static String javascript;

    public static void inject(WebView view) {
        if (javascript == null) {
            synchronized (JsBridge.class) {
                if (javascript == null) {
                    javascript = buildJavascript();
                }
            }
        }
        JsExcutor.excuteJs(view, javascript);
    }

    private static String buildJavascript() {
        StringBuffer sb = new StringBuffer();
        sb.append("window.jsBridge = {\n" +
                "    run : function(host, path, arg) {\n" +
                "        location.href = \"jsBridge://\" + host + \"/\" + path + \"?params=\" + JSON.stringify(arg)\n" +
                "    }\n" +
                "}\n");

        HashMap<String, Class<? extends JsApi>> apiMapping = JsApiMapping.getApiMapping();
        Set<String> keySet = apiMapping.keySet();
        sb.append("window.jsApi = {");
        for (String key : keySet) {
            sb.append(key).append(":{");
            Class<? extends JsApi> clazz = apiMapping.get(key);
            Method[] declaredMethods = clazz.getDeclaredMethods();
            if (declaredMethods != null) {
                for (Method method : declaredMethods) {
                    boolean annotationPresent = method.isAnnotationPresent(JsMehod.class);
                    if (annotationPresent) {
                        sb.append(method.getName()).append(":function(arg){");
                        sb.append("jsBridge.run('" + key + "', '" + method.getName() + "', arg)");
                        sb.append("},");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("},");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        Log.d("cmf", sb.toString());
        return sb.toString();
    }
}
