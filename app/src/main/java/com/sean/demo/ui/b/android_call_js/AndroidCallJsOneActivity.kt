package com.sean.demo.ui.b.android_call_js

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Button

import com.sean.demo.R
import com.sean.demo.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_android_call_js_one.*

class AndroidCallJsOneActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.activity_android_call_js_one)
        web_view.settings.javaScriptEnabled = true
        web_view.settings.javaScriptCanOpenWindowsAutomatically = true
        web_view.loadUrl("file:///android_asset/androidCallJsOne.html")
        val string = "AndroidCallJs"
        android_call_js_btn.setOnClickListener {
            web_view.post {
                // 注意传值的时候，一定要加 单引号 '' ，不然不会识别
                web_view.loadUrl("javaScript:callJsFromAndroid('$string')")
            }
        }
        web_view.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                println("url  = "+url +"   message  = "+message)
                message_show_tv.text = message.toString()
                return super.onJsAlert(view, url, message, result)
            }
        }

    }
}
