package com.klavor.browser.jsbridge.api;

import com.klavor.browser.annotation.JsAnnotation;
import com.klavor.browser.intf.NativeCallback;
import com.klavor.browser.jsbridge.JsContext;
import com.klavor.browser.jsbridge.NativeCallbackMap;

/**
 *
 */
public class NativeJsApi implements JsApi {

    @JsAnnotation
    public void success(JsContext jsContext) {
        NativeCallback callback = NativeCallbackMap.pop(jsContext.getId());
        if (callback != null) {
            callback.success(jsContext);
        }
    }

    @JsAnnotation
    public void cancel(JsContext jsContext) {
        NativeCallback callback = NativeCallbackMap.pop(jsContext.getId());
        if (callback != null) {
            callback.cancel(jsContext);
        }
    }

    @JsAnnotation
    public void error(JsContext jsContext) {
        NativeCallback callback = NativeCallbackMap.pop(jsContext.getId());
        if (callback != null) {
            callback.error(jsContext);
        }
    }
}
