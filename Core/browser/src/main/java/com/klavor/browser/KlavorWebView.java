package com.klavor.browser;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.klavor.browser.intercept.JsIntercept;
import com.klavor.browser.jsbridge.JsExcutor;

public class KlavorWebView extends WebView {
    public KlavorWebView(Context context) {
        this(context, null);
    }

    public KlavorWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KlavorWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        initSettings();
        initWebChromeClient();
        initWebViewClient();
        initWebView();
    }

    private void initWebView() {

    }

    private void initWebViewClient() {
        KlavorWebViewClient klavorWebViewClient = new KlavorWebViewClient();
        klavorWebViewClient.addIntercept(new JsIntercept());
        setWebViewClient(klavorWebViewClient);
    }

    private void initWebChromeClient() {
        KlavorWebChromeClient klavorWebChromeClient = new KlavorWebChromeClient();
        setWebChromeClient(klavorWebChromeClient);
    }

    private void initSettings() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
    }

    public void excuteJs(String js) {
        JsExcutor.excuteJs(this, js);
    }
}
