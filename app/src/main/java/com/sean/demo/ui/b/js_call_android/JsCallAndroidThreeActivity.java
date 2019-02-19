package com.sean.demo.ui.b.js_call_android;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;

public class JsCallAndroidThreeActivity extends BaseActivity {

    private WebView mWebView;
    private TextView mMessageShowTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_js_call_android_three);
        mWebView = findViewById(R.id.web_view);
        mMessageShowTv = findViewById(R.id.message_show_tv);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.loadUrl("file:///android_asset/jsCallAndroidThree.html");
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                System.out.println("url   =   "+url  +"\n"+"message   =   "+message  +"\n"+"defaultValue   =   "+defaultValue  +"\n"+"result   =   "+result.toString()  +"\n");
                Uri uri = Uri.parse(message);
                if (uri.getScheme().equals("js")){
                    if (uri.getAuthority().equals("jsCallAndroidThree")){
                        mMessageShowTv.setText("js使用第三种方式调用了Android的方法");
                        result.confirm("js使用第三种方式调用了Android的方法");
                    }
                    return true;
                }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                System.out.println("onJsAlert  ==="+"url   =   "+url  +"\n"+"messager   =   "+message  +"\n");


                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {

                System.out.println("onJsConfirm  ==="+"url   =   "+url  +"\n"+"messager   =   "+message  +"\n");
                Uri uri = Uri.parse(message);
                if (uri.getScheme().equals("js")){
                    if (uri.getAuthority().equals("jsCallAndroidThree")){
                        mMessageShowTv.setText("js使用第三种方式调用了Android的方法");
                        result.confirm();
                    }
                    return true;
                }

                return super.onJsConfirm(view, url, message, result);
            }
        });
    }
}
