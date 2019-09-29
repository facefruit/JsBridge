package org.devlang.browser;

import android.content.Context;

import org.devlang.browser.jsbridge.JsContext;
import org.devlang.browser.jsbridge.JsExcutor;
import org.devlang.browser.intf.NativeCallback;

public class WebViewProxy {
    private final KWebView mKWebView;

    public WebViewProxy(KWebView webView) {
        this.mKWebView = webView;
    }

    public Context getContext() {
        Context context = mKWebView.getContext();
        return context;
    }

    public KWebView getWebView() {
        KWebView kWebView = mKWebView;
        return kWebView;
    }

    public void excute(String jsCode) {
        JsExcutor.excuteJs(mKWebView, jsCode);
    }

    public void nativeJs(String funName, JsContext jsContext, NativeCallback nativeCallback) {
        JsExcutor.nativeJs(mKWebView, funName, jsContext, nativeCallback);
    }
}
