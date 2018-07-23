package com.klavor.browser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.klavor.browser.jsbridge.JsContext;
import com.klavor.browser.intf.NativeCallback;

public class MainActivity extends AppCompatActivity {

    private KWebView kwv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kwv = findViewById(R.id.webview);
        kwv.loadUrl("file:///android_asset/html/demo.html");

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsContext jsContext = new JsContext(kwv.getWebViewProxy());
                kwv.nativeJs("test", jsContext, new NativeCallback() {
                    @Override
                    public void success(JsContext jsContext) {
                        Log.d("cmf", "success...");
                    }

                    @Override
                    public void cancel(JsContext jsContext) {
                        Log.d("cmf", "cancel...");
                    }

                    @Override
                    public void error(JsContext jsContext) {
                        Log.d("cmf", "error...");
                    }
                });
            }
        });
    }
}
