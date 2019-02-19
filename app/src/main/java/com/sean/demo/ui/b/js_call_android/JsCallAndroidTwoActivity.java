package com.sean.demo.ui.b.js_call_android;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;

import java.net.URI;
import java.util.Map;


/**
 * 通过 WebViewClient 的shouldOverrideUrlLoading ()方法回调拦截 url
 */
public class JsCallAndroidTwoActivity extends BaseActivity {

    private static final String TAG = "JsCallAndroidTwoActivit";
    private WebView mWebView;

    private TextView mMessageShowTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_js_call_android_two);
        mWebView = findViewById(R.id.web_view);
        mMessageShowTv = findViewById(R.id.message_show_tv);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.loadUrl("file:///android_asset/jsCallAndroidTwo.html");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Uri uri = Uri.parse(url);
                if (uri.getScheme().equals("js")){
                    if (uri.getAuthority().equals("webviewSecond")){
                        StringBuffer sb =  new StringBuffer("js使用第二种方式调用了Android的方法");
                        sb.append("总共拿到了"+uri.getQueryParameterNames().size()+"个参数,分别为");
                        for (String s : uri.getQueryParameterNames()) {
                            System.out.println(s);
                            System.out.println(s+"  =  "+uri.getQueryParameter(s));
                            sb.append("\n"+s+" : "+uri.getQueryParameter(s));
                        }
                        mMessageShowTv.setText(sb.toString());
                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}
