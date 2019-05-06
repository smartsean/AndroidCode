package com.sean.demo.ui.a.activity.intentservice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import static com.sean.demo.ui.a.activity.intentservice.UploadImageService.IMG_PATH_KEY;

/**
 * @author SmartSean
 */
public class IntentServiceActivity extends BaseActivity {

    public static final String UPLOAD_RESULT = "com.sean.demo.ui.a.activity.UPLOAD_RESULT";
    private int i = 0;
    private LinearLayout mLyTaskContainer;
    private BroadcastReceiver uploadImgReceiver;
    private List<String> mUploadPaths;
    private AlertDialog mDialog;

    private void handleResult(String path) {
        TextView tv = mLyTaskContainer.findViewWithTag(path);
        mUploadPaths.remove(path);
        if (mUploadPaths.size() < 1) {
            mDialog.dismiss();
        }
        tv.setText(path + " upload success ~~~ ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_intent_service);
        setBackArrow();
        uploadImgReceiver = new UploadBroadcastReceiver();
        mDialog = new AlertDialog.Builder(this).setView(new ProgressBar(this)).create();
        mLyTaskContainer = findViewById(R.id.id_ll_taskcontainer);
        registerReceiver();
        findViewById(R.id.add_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPLOAD_RESULT);
        registerReceiver(uploadImgReceiver, filter);
    }

    public void addTask() {
        //模拟路径
        String path = "/sdcard/imgs/" + (++i) + ".png";
        if (null == mUploadPaths) {
            mUploadPaths = new ArrayList<>();
        }
        mUploadPaths.add(path);
        UploadImageService.startUpload(this, path);
        mDialog.show();
        TextView tv = new TextView(this);
        mLyTaskContainer.addView(tv);
        tv.setText(path + " is uploading ...");
        tv.setTag(path);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(uploadImgReceiver);
    }

    class UploadBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.getAction().equals(UPLOAD_RESULT)) {
                String successPath = intent.getStringExtra(IMG_PATH_KEY);
                handleResult(successPath);
            }
        }
    }

}
