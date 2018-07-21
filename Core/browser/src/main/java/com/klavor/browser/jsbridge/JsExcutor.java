package com.klavor.browser.jsbridge;

import android.os.Build;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

public class JsExcutor {
    private static final String JAVASCRIPT = "javascript";

    private static final ValueCallback mCallBack = new ValueCallback<String>() {
        @Override
        public void onReceiveValue(String s) {
            Log.d("cmf", s);
        }
    };

    private JsExcutor() {

    }

    public static void excuteJs(WebView webView, String js) {
        if (!js.startsWith(JAVASCRIPT)) {
            js = JAVASCRIPT + " : " + js;
        }
        excute(webView, js);
    }

    private static void excute(WebView webView, String js) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.loadUrl(js);
        } else {
            webView.evaluateJavascript(js, mCallBack);
        }
    }

}
