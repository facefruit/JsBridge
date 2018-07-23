package com.klavor.browser.jsbridge.api;

import android.util.Log;

import com.klavor.browser.annotation.JsMehod;
import com.klavor.browser.jsbridge.JsContext;

public class LogJsApi implements JsApi {

    @JsMehod
    public void d(JsContext jsContext) {
        Log.d(jsContext.get("tag", "undefine"), jsContext.get("msg", "undefine"));
    }

    @JsMehod
    public void i(JsContext jsContext) {
        Log.i(jsContext.get("tag", "undefine"), jsContext.get("msg", "undefine"));
    }
}
