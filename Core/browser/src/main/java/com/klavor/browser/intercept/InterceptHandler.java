package com.klavor.browser.intercept;

import java.util.ArrayList;
import java.util.List;

public class InterceptHandler {
    private List<Intercept> mInterceptList = new ArrayList<>();

    public void addIntercept(Intercept intercept) {
        mInterceptList.add(intercept);
    }

    public void clear() {
        mInterceptList.clear();
    }

    public boolean intercept(String url) {
        for (Intercept intercept : mInterceptList) {
            if (intercept.isIntercept(url)) {
                intercept.intercept(url);
                return true;
            }
        }
        return false;
    }
}
