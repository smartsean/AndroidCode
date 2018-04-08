package com.sean.demo.ui.a.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sean.demo.R;

import java.lang.ref.WeakReference;

public class SafeHandlerDemoActivity extends AppCompatActivity {

    private MyHandler mMyHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_handler_demo);
        mMyHandler = new MyHandler(this);
        mMyHandler.sendEmptyMessageDelayed(1, 2000);
        finish();
    }

    private static class MyHandler extends Handler {
        WeakReference<SafeHandlerDemoActivity> mWeakReference;

        public MyHandler(SafeHandlerDemoActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SafeHandlerDemoActivity activity = mWeakReference.get();
            if (activity != null) {
                Toast.makeText(activity, "执行了", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 移除所有消息
        mMyHandler.removeCallbacksAndMessages(null);
        // 移除指定的单条消息 what就是message中的what
//        mMyHandler.removeMessages(what);
    }
}
