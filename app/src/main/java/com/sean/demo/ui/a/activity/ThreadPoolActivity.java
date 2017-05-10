package com.sean.demo.ui.a.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ThreadPoolActivity extends BaseActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    private LinearLayout linearlayoutBuild;
    private MyThread thread1;
    private MyThread thread2;
    private MyThread thread3;
    private MyThread thread4;

    private Context context;
    private String threadName = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_thread_pool);
        ButterKnife.bind(this);
        setBackArrow();
        context = this;
        linearlayoutBuild = (LinearLayout) findViewById(R.id.linearlayout_build);

        if (fixedThreadPool == null) {
            fixedThreadPool = Executors.newFixedThreadPool(4);
        }
        if (cacheThreadPool == null) {
            cacheThreadPool = Executors.newCachedThreadPool();
        }
        if (scheduledThreadPool == null) {
            scheduledThreadPool = Executors.newScheduledThreadPool(2);
        }
        if (singleThreadPool == null) {
            singleThreadPool = Executors.newSingleThreadExecutor();
        }
    }

    private ExecutorService fixedThreadPool;
    private ExecutorService cacheThreadPool;
    private ExecutorService scheduledThreadPool;
    private ExecutorService singleThreadPool;

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                fixedThreadPool.execute(thread1 = new MyThread());
                fixedThreadPool.execute(thread2 = new MyThread());
                fixedThreadPool.execute(thread3 = new MyThread());
                break;
            case R.id.btn2:
                cacheThreadPool.execute(thread1 = new MyThread());
                cacheThreadPool.execute(thread2 = new MyThread());
                cacheThreadPool.execute(thread3 = new MyThread());
                cacheThreadPool.execute(thread4 = new MyThread());
                break;
            case R.id.btn3:
                scheduledThreadPool.execute(thread1 = new MyThread());
                scheduledThreadPool.execute(thread2 = new MyThread());
                scheduledThreadPool.execute(thread3 = new MyThread());
                scheduledThreadPool.execute(thread4 = new MyThread());
                scheduledThreadPool.execute(new MyThread());
                break;
            case R.id.btn4:
                singleThreadPool.execute(thread1 = new MyThread());
                singleThreadPool.execute(thread2 = new MyThread());
                singleThreadPool.execute(thread3 = new MyThread());
                singleThreadPool.execute(thread4 = new MyThread());
                break;
        }
    }

    class MyThread implements Runnable {

        @Override
        public void run() {
            Log.i("seanMyThread", Thread.currentThread().getName() + "<<<===end");
            SystemClock.sleep(3000);
            threadName = Thread.currentThread().getName();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView tv = new TextView(context);
                    tv.setText(threadName);
                    linearlayoutBuild.addView(tv);
                }
            });

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (fixedThreadPool != null && !fixedThreadPool.isShutdown()) {
            fixedThreadPool.shutdown();
        }
        if (cacheThreadPool != null && !cacheThreadPool.isShutdown()) {
            cacheThreadPool.shutdown();
        }
        if (scheduledThreadPool != null && !scheduledThreadPool.isShutdown()) {
            scheduledThreadPool.shutdown();
        }
        if (singleThreadPool != null && !singleThreadPool.isShutdown()) {
            singleThreadPool.shutdown();
        }
    }
}
