package com.klavor.browser.jsbridge;

import android.content.Context;
import android.util.Log;

import com.klavor.browser.WebViewProxy;
import com.klavor.browser.intf.JsCallbackStatus;

import org.json.JSONException;
import org.json.JSONObject;

public class JsContext implements JsCallbackStatus {
    private static final String STATUS = "status";

    private WebViewProxy mWebViewProxy;
    private String mArg;
    private int id;
    private JSONObject mJsonObject;

    public JsContext(WebViewProxy proxy, String arg, int id) {
        this.mWebViewProxy = proxy;
        this.mArg = arg;
        this.id = id;
        mJsonObject = getJsonObject();
    }

    public Context getContext() {
        return mWebViewProxy.getContext();
    }

    public String get(String key, String defValue) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return null;
        }
        String parameter;
        try {
            parameter = jsonObject.getString(key);
        } catch (JSONException e) {
            parameter = defValue;
            e.printStackTrace();
        }
        return parameter;
    }

    public void put(String key, String value) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return;
        }
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public double get(String key, double defValue) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return defValue;
        }
        double parameter;
        try {
            parameter = jsonObject.getDouble(key);
        } catch (JSONException e) {
            parameter = defValue;
            e.printStackTrace();
        }
        return parameter;
    }

    public void put(String key, double value) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return;
        }
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public long get(String key, long defValue) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return defValue;
        }
        long parameter;
        try {
            parameter = jsonObject.getLong(key);
        } catch (JSONException e) {
            parameter = defValue;
            e.printStackTrace();
        }
        return parameter;
    }

    public void put(String key, long value) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return;
        }
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int get(String key, int defValue) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return defValue;
        }
        int parameter;
        try {
            parameter = jsonObject.getInt(key);
        } catch (JSONException e) {
            parameter = defValue;
            e.printStackTrace();
        }
        return parameter;
    }

    public void put(String key, int value) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return;
        }
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean get(String key, boolean defValue) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return defValue;
        }
        boolean parameter;
        try {
            parameter = jsonObject.getBoolean(key);
        } catch (JSONException e) {
            parameter = defValue;
            e.printStackTrace();
        }
        return parameter;
    }

    public void put(String key, boolean value) {
        JSONObject jsonObject = getJsonObject();
        if (jsonObject == null) {
            return;
        }
        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJsonObject() {
        if (mJsonObject == null) {
            synchronized (this) {
                if (mJsonObject == null) {
                    try {
                        mJsonObject = mArg == null ? new JSONObject() : new JSONObject(mArg);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mJsonObject;
    }

    public void success(JsContext jsContext) {
        jsContext.put(STATUS, SUCCESS);
        callback(jsContext, null);
    }

    public void cancel(JsContext jsContext) {
        jsContext.put(STATUS, CANCEL);
        callback(jsContext, null);
    }

    public void error(JsContext jsContext, String errMsg) {
        jsContext.put(STATUS, ERROR);
        callback(jsContext, errMsg);
    }

    private void callback(JsContext jsContext, String errMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("window.callback.cb(");
        sb.append(id);
        sb.append(",");
        sb.append("JSON.parse('").append(jsContext.string()).append("')");
        sb.append(",");
        sb.append(errMsg == null ? "undefined" : ("'" + errMsg + "'"));
        sb.append(",");
        sb.append("true");  //暂时默认只能回调一次
        sb.append(")");
        String jsCode = sb.toString();
        Log.d("wsy", jsCode);
        mWebViewProxy.excute(jsCode);
    }

    public String string() {
        return mJsonObject.toString();
    }
}
