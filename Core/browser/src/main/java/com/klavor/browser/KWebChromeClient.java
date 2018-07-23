package com.klavor.browser;

import android.webkit.WebChromeClient;

public class KWebChromeClient extends WebChromeClient {

    private final WebViewProxy mWebViewProxy;

    public KWebChromeClient(WebViewProxy webViewProxy) {
        this.mWebViewProxy = webViewProxy;
    }
}
