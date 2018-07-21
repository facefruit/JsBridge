package com.klavor.browser.jsbridge.api;

import android.util.Log;

import com.klavor.browser.JsMehod;
import com.klavor.browser.jsbridge.JsObject;

public class LogJsApi implements JsApi {

    @JsMehod
    public void d(JsObject jsObject) {
        Log.d(jsObject.getParameter("tag"), jsObject.getParameter("msg"));
    }

    @JsMehod
    public void i(JsObject jsObject) {
        Log.i(jsObject.getParameter("tag"), jsObject.getParameter("msg"));
    }
}
