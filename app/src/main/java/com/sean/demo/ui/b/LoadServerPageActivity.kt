package com.sean.demo.ui.b

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.webkit.WebResourceResponse
import android.widget.ProgressBar
import com.sean.demo.R
import com.sean.demo.ui.BaseActivity
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_async_task.view.*
import kotlinx.android.synthetic.main.activity_load_server_page.*

class LoadServerPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_server_page)
        web_view.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //设置缓存
        web_view.settings.javaScriptEnabled = true
        web_view.settings.domStorageEnabled = true //重点是这个设置 设置适应Html5
        web_view.settings.javaScriptCanOpenWindowsAutomatically = true
        web_view.loadUrl("https://adaptive.i5sesol.com/#/")
        web_view.webViewClient =object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress.visibility = View.VISIBLE
                web_view.visibility = View.GONE
            }


            override fun shouldOverrideUrlLoading(p0: WebView?, p1: String?): Boolean {
                return super.shouldOverrideUrlLoading(p0, p1)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progress.visibility = View.GONE
                web_view.visibility = View.VISIBLE
                super.onPageFinished(view, url)
            }



        }
    }

    override fun onBackPressed() {

        if (null != web_view && web_view.canGoBack())
            web_view.goBack()
        else
            super.onBackPressed()
    }
}
