package com.klavor.browser;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.klavor.browser.intercept.Intercept;
import com.klavor.browser.intercept.InterceptHandler;

public class KlavorWebViewClient extends WebViewClient {

    private InterceptHandler mInterceptHandler = new InterceptHandler();

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.d("cmf", "onPageFinished() : " + url);
        JsBridge.inject(view);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("cmf", "shouldOverrideUrlLoading() : " + url);
        return mInterceptHandler.intercept(url);
    }

    public void addIntercept(Intercept intercept) {
        mInterceptHandler.addIntercept(intercept);
    }
}
