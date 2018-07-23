package com.klavor.browser.jsbridge.api;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.klavor.browser.annotation.JsAnnotation;
import com.klavor.browser.jsbridge.JsContext;

public class DialogJsApi implements JsApi {

    @JsAnnotation
    public void show(final JsContext jsContext) {
        AlertDialog alertDialog = new AlertDialog.Builder(jsContext.getContext())
                .setTitle(jsContext.get("title", "undefine"))
                .setMessage(jsContext.get("message", "undefine"))
                .setPositiveButton(jsContext.get("positive", "确定"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jsContext.put("msg", "这很JS！");
                        jsContext.success(jsContext);
                    }
                })
                .setNegativeButton(jsContext.get("negative", "取消"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jsContext.put("msg", "不够MAN！");
                        jsContext.cancel(jsContext);
                    }
                }).create();
        /*alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                jsContext.error(jsContext, "都说了不要了！");
            }
        });*/
        alertDialog.show();
    }
}
