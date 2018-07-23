package com.klavor.browser.intf;

import com.klavor.browser.KWebView;
import com.klavor.browser.WebViewProxy;

public interface Intercept {
    public boolean isIntercept(WebViewProxy webViewProxy, String url);
    public void intercept(WebViewProxy webViewProxy, String url);
    public String getScheme();
}
