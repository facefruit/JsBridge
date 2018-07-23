package com.klavor.browser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KWebView kwv = findViewById(R.id.webview);
        kwv.loadUrl("file:///android_asset/html/demo.html");
    }
}
