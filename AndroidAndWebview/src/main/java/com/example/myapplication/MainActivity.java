package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;
    private Button mBtn;
    @Override
    @JavascriptInterface
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView)findViewById(R.id.webview);
        mBtn = (Button) findViewById(R.id.btn);
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        //开启javascript支持
        webSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置出现缩放工具
        webSettings.setBuiltInZoomControls(true);
        mWebView.addJavascriptInterface(MainActivity.this, "android");
        mWebView.loadUrl("file:///android_asset/web.html");


    }
    public void toastMessage(String message) {
        Toast.makeText(getApplicationContext(), "通过Natvie传递的Toast:"+message, Toast.LENGTH_LONG).show();
    }
}
