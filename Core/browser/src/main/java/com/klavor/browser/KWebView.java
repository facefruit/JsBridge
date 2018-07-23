package com.klavor.browser;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.klavor.browser.intercept.JsIntercept;

public class KWebView extends WebView {
    private WebViewProxy mWebViewProxy;

    public KWebView(Context context) {
        this(context, null);
    }

    public KWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        initWebView();
        initSettings();
        initWebChromeClient();
        initWebViewClient();
    }

    private void initWebView() {
        mWebViewProxy = new WebViewProxy(this);
    }

    private void initWebViewClient() {
        KWebViewClient kWebViewClient = new KWebViewClient(mWebViewProxy);
        kWebViewClient.addIntercept(new JsIntercept());
        setWebViewClient(kWebViewClient);
    }

    private void initWebChromeClient() {
        KWebChromeClient kWebChromeClient = new KWebChromeClient(mWebViewProxy);
        setWebChromeClient(kWebChromeClient);
    }

    private void initSettings() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
    }

    public void excuteJs(String js) {
        mWebViewProxy.excute(js);
    }
}
