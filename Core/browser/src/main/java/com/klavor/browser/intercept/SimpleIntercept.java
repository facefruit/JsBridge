package com.klavor.browser.intercept;

import android.net.Uri;

import com.klavor.browser.WebViewProxy;
import com.klavor.browser.intf.Intercept;

public abstract class SimpleIntercept implements Intercept {
    @Override
    public final boolean isIntercept(WebViewProxy webViewProxy, String url) {
        String scheme = Uri.parse(url).getScheme();
        if (scheme != null && scheme.equalsIgnoreCase(getScheme())) {
            return true;
        }
        return false;
    }

    @Override
    public abstract void intercept(WebViewProxy webViewProxy, String url);

    @Override
    public abstract String getScheme();
}
