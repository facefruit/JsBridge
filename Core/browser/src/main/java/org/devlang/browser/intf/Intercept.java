package org.devlang.browser.intf;

import org.devlang.browser.WebViewProxy;

public interface Intercept {
    public boolean isIntercept(WebViewProxy webViewProxy, String url);
    public void intercept(WebViewProxy webViewProxy, String url);
    public String getScheme();
}
