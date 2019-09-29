package org.devlang.browser.jsbridge.api;

import android.util.Log;

import org.devlang.browser.annotation.JsAnnotation;
import org.devlang.browser.jsbridge.JsContext;

public class LogJsApi implements JsApi {

    @JsAnnotation
    public void d(JsContext jsContext) {
        Log.d(jsContext.get("tag", "undefine"), jsContext.get("msg", "undefine"));
    }

    @JsAnnotation
    public void i(JsContext jsContext) {
        Log.i(jsContext.get("tag", "undefine"), jsContext.get("msg", "undefine"));
    }
}
