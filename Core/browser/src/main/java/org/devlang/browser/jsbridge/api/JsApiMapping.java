package org.devlang.browser.jsbridge.api;

import java.util.HashMap;

public class JsApiMapping {
    private static HashMap<String, Class<? extends JsApi>> mApiMapping;

    private JsApiMapping() {

    }

    public static HashMap<String, Class<? extends JsApi>> getApiMapping() {
        if (mApiMapping == null) {
            synchronized (JsApiMapping.class) {
                if (mApiMapping == null) {
                    mApiMapping = new HashMap<>();
                    buildMapping();
                }
            }
        }
        return mApiMapping;
    }

    private static void buildMapping() {
        mApiMapping.put("log", LogJsApi.class);
        mApiMapping.put("dialog", DialogJsApi.class);
        mApiMapping.put("nativeJs", NativeJsApi.class);
    }
}
