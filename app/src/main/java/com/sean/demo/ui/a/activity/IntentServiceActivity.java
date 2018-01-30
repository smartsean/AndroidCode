package com.sean.demo.ui.a.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/**
 * @author  SmartSean
 */
public class IntentServiceActivity extends BaseActivity {

    public static final String UPLOAD_RESULT = "com.sean.demo.ui.a.activity.UPLOAD_RESULT";

    private LinearLayout mLyTaskContainer;
    private ProgressBar pb;

    private BroadcastReceiver uploadImgReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == UPLOAD_RESULT) {
                String path = intent.getStringExtra(UploadImgService.EXTRA_IMG_PATH);

                handleResult(path);

            }

        }
    };

    private void handleResult(String path) {
        TextView tv = (TextView) mLyTaskContainer.findViewWithTag(path);
        pb.setVisibility(View.GONE);
        tv.setText(path + " upload success ~~~ ");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(TestMsg testMsg) {

        if (testMsg != null&& !testMsg.getName().equals("cancel")) {
            handleResult(testMsg.getName());
            Log.d("MainActivity", "你收到的名字为： "+testMsg.getName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_intent_service);
        setBackArrow();
        mLyTaskContainer = (LinearLayout) findViewById(R.id.id_ll_taskcontainer);
        pb = (ProgressBar) findViewById(R.id.pb);
        registerReceiver();
        EventBus.getDefault().register(this);
        final Button button = (Button) findViewById(R.id.add_task);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask(button);
            }
        });
        findViewById(R.id.stop_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new TestMsg1("cancel"));
            }
        });
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPLOAD_RESULT);
//        registerReceiver(uploadImgReceiver, filter);
    }

    int i = 0;

    public void addTask(View view) {
        //模拟路径
        String path = "/sdcard/imgs/" + (++i) + ".png";
        UploadImgService.startUploadImg(this, path);
        pb.setVisibility(View.VISIBLE);
        TextView tv = new TextView(this);
        mLyTaskContainer.addView(tv);
        tv.setText(path + " is uploading ...");
        tv.setTag(path);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(uploadImgReceiver);
        EventBus.getDefault().unregister(this);
    }

}
