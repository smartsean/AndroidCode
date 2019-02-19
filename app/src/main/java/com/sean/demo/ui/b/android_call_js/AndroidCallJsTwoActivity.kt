package com.sean.demo.ui.b.android_call_js

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.webkit.JsResult
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView

import com.sean.demo.R
import com.sean.demo.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_android_call_js_one.*

class AndroidCallJsTwoActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.activity_android_call_js_two)
        web_view.settings.javaScriptEnabled = true
        web_view.settings.javaScriptCanOpenWindowsAutomatically = true
        web_view.loadUrl("file:///android_asset/androidCallJsTwo.html")
//        web_view.loadUrl("file:///android_asset/index.html")
        var string = "ddddddd"
        android_call_js_btn.setOnClickListener {

            web_view.evaluateJavascript("javascript:callJsFromAndroid('css')", ValueCallback {
                println(it)
                message_show_tv.text = it.toString()
            })
        }
        web_view.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                message_show_tv.text = result.toString()
                return super.onJsAlert(view, url, message, result)
            }
        }

    }
}
