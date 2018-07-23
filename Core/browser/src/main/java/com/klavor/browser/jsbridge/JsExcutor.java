package com.klavor.browser.jsbridge;

import android.os.Build;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.klavor.browser.intf.NativeCallback;

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
        Log.d("cmf", js);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.loadUrl(js);
        } else {
            webView.evaluateJavascript(js, mCallBack);
        }
    }

    public static void nativeJs(WebView webView, String funName, JsContext jsContext, NativeCallback nativeCallback) {
        int id = NativeCallbackMap.put(nativeCallback);
        /*test({}, {
                    id : 1,
                    success : function(rtn) {
                nativeJs.callback(this.id, rtn, 1);
            },
            cancel : function(rtn) {
                nativeJs.callback(this.id, rtn, 0);
            },
            error : function(rtn) {
                nativeJs.callback(this.id, rtn, -1);
            }
        })*/
        StringBuffer sb = new StringBuffer();
        sb.append(funName);
        sb.append("(");
        sb.append(jsContext.string());
        sb.append(", ");
        sb.append("{ id : ").append(id).append(",");
        sb.append("success : function(rtn) { window.nativeJs.callback(this.id, rtn, 1); },");
        sb.append("cancel : function(rtn) { window.nativeJs.callback(this.id, rtn, 0); },");
        sb.append("error : function(rtn) { window.nativeJs.callback(this.id, rtn, -1); }");
        sb.append("})");
        String jsCode = sb.toString();
        excuteJs(webView, jsCode);
    }
}
