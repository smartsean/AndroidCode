package com.sean.demo.ui.b.js_call_android.one;

import android.webkit.JavascriptInterface;

public class JsCallAndroidInteface {

    private IUpdateAndroid mUpdateAndroid;

    public JsCallAndroidInteface(IUpdateAndroid iUpdateAndroidParam) {
        mUpdateAndroid = iUpdateAndroidParam;
    }

    @JavascriptInterface
    public void call(String string) {
        System.out.println("Android 收到了来自js的消息，内容为： " + string);
        mUpdateAndroid.updateAndroid("Android 收到了来自js的消息，内容为：" + string);
    }
}
