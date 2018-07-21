package com.klavor.browser.intercept;

public interface Intercept {
    public boolean isIntercept(String url);
    public void intercept(String url);
    public String getScheme();
}
