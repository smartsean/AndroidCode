package com.sean.demo.ui.b.js_call_android;

import android.os.Bundle;
import android.util.SparseArray;
import android.webkit.WebView;
import android.widget.TextView;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;
import com.sean.demo.ui.b.js_call_android.one.IUpdateAndroid;
import com.sean.demo.ui.b.js_call_android.one.JsCallAndroidInteface;

/**
 *  通过WebView的addJavascriptInterface（）进行对象映射
 */
public class JsCallAndroidOneActivity extends BaseActivity implements IUpdateAndroid {


   private WebView mWebView;

   private TextView mMessageShowTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_js_call_android_one);
        mWebView = findViewById(R.id.web_view);
        mMessageShowTv = findViewById(R.id.message_show_tv);
        mWebView.getSettings().setJavaScriptEnabled(true);
        SparseArray<String > array = new SparseArray<>();
        mWebView.addJavascriptInterface(new JsCallAndroidInteface(JsCallAndroidOneActivity.this),"jsCallAndroid");
        mWebView.loadUrl("file:///android_asset/jsCallAndroidOne.html");
    }

    @Override
    public void updateAndroid(String string) {
        mMessageShowTv.setText(string);
    }
}
