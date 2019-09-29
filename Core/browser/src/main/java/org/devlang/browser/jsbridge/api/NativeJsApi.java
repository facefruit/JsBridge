package org.devlang.browser.jsbridge.api;

import org.devlang.browser.annotation.JsAnnotation;
import org.devlang.browser.intf.NativeCallback;
import org.devlang.browser.jsbridge.JsContext;
import org.devlang.browser.jsbridge.NativeCallbackMap;

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
