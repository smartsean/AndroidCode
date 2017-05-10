package com.sean.demo.ui.a.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;

public class HandlerThreadActivity extends BaseActivity {


    private TextView show;

    private int MSG_UPDATE = 0X00;
    private boolean isUpdate = false;
    private HandlerThread handlerThread;
    private Handler handler;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_handler_thread);
        setBackArrow();
        initView1();
        initBackThread();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isUpdate = true;
        handler.sendEmptyMessage(MSG_UPDATE);
    }

    private void initBackThread() {
        handlerThread = new HandlerThread("子线程刷新大盘指数");
        handlerThread.start();

        handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                checkUpdate();
                if (isUpdate) {
                    handler.sendEmptyMessage(MSG_UPDATE);
                }
            }
        };
    }

    private void checkUpdate() {
        SystemClock.sleep(2000);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                show.setTextColor(getResources().getColor(R.color.red));
                show.setText("实时更新中，当前大盘指数：" + (int) (Math.random() * 3000 + 1000));
            }
        });
    }

    private void initView1() {
        isUpdate = true;
        show = (TextView) findViewById(R.id.show);
    }


    @Override
    protected void onPause() {
        super.onPause();
        isUpdate = false;
        handler.removeMessages(MSG_UPDATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }
}
