package com.klavor.browser.intercept;

import android.net.Uri;
import android.util.Log;

import com.klavor.browser.annotation.JsMehod;
import com.klavor.browser.WebViewProxy;
import com.klavor.browser.jsbridge.JsContext;
import com.klavor.browser.jsbridge.api.JsApi;
import com.klavor.browser.jsbridge.api.JsApiMapping;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class JsIntercept extends SimpleIntercept {
    private static final String JS_BRIDGE = "jsBridge";

    @Override
    public void intercept(WebViewProxy webViewProxy, String url) {
        Log.d("cmf", "intercept()");
        Uri uri = Uri.parse(url);
        HashMap<String, Class<? extends JsApi>> apiMapping = JsApiMapping.getApiMapping();
        Set<String> keySet = apiMapping.keySet();
        String host = uri.getHost();
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments != null && pathSegments.size() > 0) {
            for (String key : keySet) {
                if (key.equalsIgnoreCase(host)) {
                    Class<? extends JsApi> clazz = apiMapping.get(key);
                    try {
                        Constructor<? extends JsApi> constructor = clazz.getConstructor();
                        JsApi jsApi = constructor.newInstance();
                        Method method = clazz.getDeclaredMethod(pathSegments.get(0), JsContext.class);
                        if (method.isAnnotationPresent(JsMehod.class)) {
                            Integer id = null;
                            try {
                                id = Integer.valueOf(uri.getQueryParameter("id"));
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                            JsContext jsContext = new JsContext(webViewProxy, uri.getQueryParameter("params"), id);
                            method.invoke(jsApi, jsContext);
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }
    }

    @Override
    public String getScheme() {
        return JS_BRIDGE;
    }
}
