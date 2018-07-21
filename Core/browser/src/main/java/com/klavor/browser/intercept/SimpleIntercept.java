package com.klavor.browser.intercept;

import android.net.Uri;

import com.klavor.browser.intercept.Intercept;

public abstract class SimpleIntercept implements Intercept {
    @Override
    public final boolean isIntercept(String url) {
        String scheme = Uri.parse(url).getScheme();
        if (scheme != null && scheme.equalsIgnoreCase(getScheme())) {
            return true;
        }
        return false;
    }

    @Override
    public abstract void intercept(String url);

    @Override
    public abstract String getScheme();
}
