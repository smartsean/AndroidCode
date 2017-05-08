package com.sean.demo.ui.a.activity;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Sean on 2017/4/28.
 */

public class WebHost {
    public Context context;

    public WebHost(Context context) {
        this.context = context;
    }

    public void callJs() {
        Toast.makeText(context, "点击了js", Toast.LENGTH_LONG).show();
    }
}
