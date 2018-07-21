package com.klavor.browser.jsbridge;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

public class JsObject {
    private Context mContext;
    private String mArg;
    private JSONObject mJsonObject;

    public JsObject(Context context, String arg) {
        this.mContext = context;
        this.mArg = arg;
    }

    public Context getContext() {
        return mContext;
    }

    public String getArg() {
        return mArg;
    }

    public String getParameter(String key) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return null;
        }
        String parameter = null;
        try {
            parameter = jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parameter;
    }

    private JSONObject getJsonObject() {
        if (mJsonObject == null) {
            synchronized (this) {
                if (mJsonObject == null) {
                    try {
                        mJsonObject = new JSONObject(mArg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mJsonObject;
    }
}
