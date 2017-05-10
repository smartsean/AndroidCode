package com.sean.demo.ui.a.activity;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Sean on 2017/5/10 14:51.
 */

public class UploadImgService extends IntentService {

    private static final String ACTION_UPLOAD_IMG = "com.sean.demo.ui.a.activity.action.UPLOAD_IMAGE";
    public static final String EXTRA_IMG_PATH = "com.sean.demo.ui.a.activity.extra.IMG_PATH";

    private int startId;


    public static void startUploadImg(Context context, String path) {
        Intent intent = new Intent(context, UploadImgService.class);
        intent.setAction(ACTION_UPLOAD_IMG);
        intent.putExtra(EXTRA_IMG_PATH, path);
        context.startService(intent);
    }

    public UploadImgService() {
        super("UploadImgService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPLOAD_IMG.equals(action)) {
                Log.i("seanUploadImgService", 111 + "<<<===end");
                final String path = intent.getStringExtra(EXTRA_IMG_PATH);
                handleUploadImg(path);
            }
        }
    }


    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        this.startId = startId;
    }

    private void handleUploadImg(String path) {
        try {
            //模拟上传耗时
            Thread.sleep(3000);
            EventBus.getDefault().post(new TestMsg(path));
            Log.i("seanUploadImgService", Thread.currentThread().getName() + "<<<===end");

            Intent intent = new Intent(IntentServiceActivity.UPLOAD_RESULT);
            intent.putExtra(EXTRA_IMG_PATH, path);
            sendBroadcast(intent);
            Log.i("seanUploadImgService", Thread.currentThread().getName() + "<<<===end");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND )
    public void onEventMainThread(TestMsg1 testMsg) {

        if (testMsg != null) {
            if (testMsg.getName().equals("cancel")) ;
            Log.d("MainActivity", "你收到的名字为： " + testMsg.getName());

//            stopSelf();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);

        Log.e("TAG", "onCreateIntentService");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.e("TAG", "onDestroyIntentService");
    }


}