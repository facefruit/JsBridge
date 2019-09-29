package org.devlang.browser.intf;

import org.devlang.browser.jsbridge.JsContext;

public interface NativeCallback {
    public void success(JsContext jsContext);
    public void cancel(JsContext jsContext);
    public void error(JsContext jsContext);
}
