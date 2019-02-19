package com.sean.demo.ui.b

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.sean.demo.R
import com.sean.demo.ui.BaseActivity
import com.sean.demo.ui.MainActivity
import com.sean.demo.ui.b.android_call_js.AndroidCallJsOneActivity
import com.sean.demo.ui.b.android_call_js.AndroidCallJsTwoActivity
import com.sean.demo.ui.b.js_call_android.JsCallAndroidOneActivity
import com.sean.demo.ui.b.js_call_android.JsCallAndroidThreeActivity
import com.sean.demo.ui.b.js_call_android.JsCallAndroidTwoActivity

class JsAndJavaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentLayout(R.layout.activity_js_and_java)
        setBackArrow()
        findViewById<View>(R.id.js_call_android_one_btn).setOnClickListener { startActivity(Intent(this@JsAndJavaActivity, JsCallAndroidOneActivity::class.java)) }
        findViewById<View>(R.id.js_call_android_two_btn).setOnClickListener { startActivity(Intent(this@JsAndJavaActivity, JsCallAndroidTwoActivity::class.java)) }
        findViewById<View>(R.id.js_call_android_three_btn).setOnClickListener { startActivity(Intent(this@JsAndJavaActivity, JsCallAndroidThreeActivity::class.java)) }
        findViewById<View>(R.id.android_call_js_one_btn).setOnClickListener { startActivity(Intent(this@JsAndJavaActivity, AndroidCallJsOneActivity::class.java)) }
        findViewById<View>(R.id.android_call_js_two_btn).setOnClickListener { startActivity(Intent(this@JsAndJavaActivity, AndroidCallJsTwoActivity::class.java)) }
        findViewById<View>(R.id.load_server_page_btn).setOnClickListener { startActivity(Intent(this@JsAndJavaActivity, LoadServerPageActivity::class.java)) }
    }
}
