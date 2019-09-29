package org.devlang.browser;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.devlang.browser.intf.Intercept;
import org.devlang.browser.intercept.InterceptHandler;
import org.devlang.browser.jsbridge.JsInject;

public class KWebViewClient extends WebViewClient {

    private final WebViewProxy mWebViewProxy;

    public KWebViewClient(WebViewProxy webViewProxy) {
        this.mWebViewProxy = webViewProxy;
    }

    private InterceptHandler mInterceptHandler = new InterceptHandler();

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.d("cmf", "onPageFinished() : " + url);
        WebViewProxy webViewProxy = this.mWebViewProxy;
        JsInject.inject(webViewProxy);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("cmf", "shouldOverrideUrlLoading() : " + url);
        WebViewProxy webViewProxy = this.mWebViewProxy;
        return mInterceptHandler.intercept(webViewProxy, url);
    }

    public void addIntercept(Intercept intercept) {
        mInterceptHandler.addIntercept(intercept);
    }
}
