package org.devlang.browser.jsbridge;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.devlang.browser.WebViewProxy;
import org.devlang.browser.annotation.JsAnnotation;
import org.devlang.browser.jsbridge.api.JsApi;
import org.devlang.browser.jsbridge.api.JsApiMapping;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

public class JsInject {
    private static String javascript;

    public static void inject(WebViewProxy webViewProxy) {
        if (javascript == null) {
            synchronized (JsInject.class) {
                if (javascript == null) {
                    javascript = buildJavascript(webViewProxy);
                }
            }
        }
        webViewProxy.excute(javascript);
    }

    private static String buildJavascript(WebViewProxy webViewProxy) {
        Context context = webViewProxy.getContext();
        StringBuffer sb = new StringBuffer();

        InputStream is = null;  //获得AssetManger 对象, 调用其open 方法取得  对应的inputStream对象
        try {
            AssetManager assets = context.getAssets();
            is = assets.open("js/jsBridge.js");
            int size = is.available();  //取得数据流的数据大小
            byte[] bytes = new byte[size];
            is.read(bytes);
            is.close();
            sb.append(new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, Class<? extends JsApi>> apiMapping = JsApiMapping.getApiMapping();
        Set<String> keySet = apiMapping.keySet();
        sb.append("window.jsApi = {");
        for (String key : keySet) {
            sb.append(key).append(":{");
            Class<? extends JsApi> clazz = apiMapping.get(key);
            Method[] declaredMethods = clazz.getDeclaredMethods();
            if (declaredMethods != null) {
                for (Method method : declaredMethods) {
                    boolean annotationPresent = method.isAnnotationPresent(JsAnnotation.class);
                    if (annotationPresent) {
                        sb.append(method.getName()).append(":function(arg){");
                        sb.append("jsBridge.doJs('" + key + "', '" + method.getName() + "', arg)");
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
