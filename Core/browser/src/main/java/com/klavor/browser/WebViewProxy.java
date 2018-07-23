package com.klavor.browser;

import android.content.Context;

import com.klavor.browser.jsbridge.JsExcutor;

public class WebViewProxy {
    private final KWebView mKWebView;

    public WebViewProxy(KWebView webView) {
        this.mKWebView = webView;
    }

    public Context getContext() {
        return mKWebView.getContext();
    }

    public KWebView getWebView() {
        return mKWebView;
    }

    public void excute(String jsCode) {
        JsExcutor.excuteJs(mKWebView, jsCode);
    }
}
