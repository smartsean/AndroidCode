package com.sean.demo.ui.a.activity.webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;
import com.sean.demo.ui.a.activity.WebHost;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * @author  SmartSean
 */
public class WebViewActivity extends BaseActivity {


    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.web_view_url)
    TextView webViewUrl;
    @BindView(R.id.refrese_tv)
    TextView refreseTv;
    @BindView(R.id.error)
    TextView errorText;


    private String loadUrl = "http://www.baidu.com";

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_web_view);
        ButterKnife.bind(this);
        setToolBar(R.id.web_view_tb);
        setTitle("WebView");
        setBackArrow();
//        webView.loadUrl("http://www.qq.com");
        webView.loadUrl(loadUrl);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebHost(this),"js");

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                webViewUrl.setText(title);
                super.onReceivedTitle(view, title);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                Toast.makeText(WebViewActivity.this, "complete", Toast.LENGTH_SHORT).show();
                super.onPageFinished(view, url);
            }

            /**
             * webView出现错误的时候会回调该方法,两种方式，一种是本地html，一种是本地TextView等布局
             * @param view
             * @param request
             * @param error
             */
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                view.loadUrl("file:///android_asset/error.html");
//                errorText.setVisibility(View.VISIBLE);
//                webView.setVisibility(View.GONE);
                super.onReceivedError(view, request, error);
            }
        });


        refreseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(loadUrl);
            }
        });

        //下载功能
        webView.setDownloadListener(new MyDownLoad());
    }

    class MyDownLoad implements DownloadListener {

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Log.i("SmartSean-Debug===>>>", "" + "<<<===end");
            if (url.endsWith(".apk")) {
                Log.i("SmartSean-Debug===>>>", "<<<===en1d");
                new HttpThread(url).start();
                // 调用系统浏览器下载
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        }
    }

}
