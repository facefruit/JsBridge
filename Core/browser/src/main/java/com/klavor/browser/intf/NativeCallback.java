package com.klavor.browser.intf;

import com.klavor.browser.jsbridge.JsContext;

public interface NativeCallback {
    public void success(JsContext jsContext);
    public void cancel(JsContext jsContext);
    public void error(JsContext jsContext);
}
