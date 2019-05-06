package com.sean.demo.ui.a.activity.intentservice;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.sean.demo.ui.a.activity.intentservice.IntentServiceActivity.UPLOAD_RESULT;

/**
 * @author sean
 */
public class UploadImageService extends IntentService {

    private static final String TAG = "UploadImageService";

    public static String IMG_PATH_KEY = "imgPath";

    private int i = 0;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public UploadImageService() {
        super("UploadImageService");
    }

    public static void startUpload(Context context, String imgPath) {
        Intent intent = new Intent(context, UploadImageService.class);
        intent.putExtra(IMG_PATH_KEY, imgPath);
        context.startService(intent);
    }

    public void close(){
        this.stopSelf();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: "+(++i));
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: flags:  "+flags+"     startId:    "+startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d("seanseansean","getId : "+Thread.currentThread().getId());
        Log.d("seanseansean","getName : "+Thread.currentThread().getName());
        if (intent != null && intent.hasExtra(IMG_PATH_KEY)) {
            String path = intent.getStringExtra(IMG_PATH_KEY);
            upload(path);
        }
    }

    private void upload(String path) {
        try {
            // 模拟上传 3 秒结束
            Thread.sleep(3000);
            sendBroadcastToCaller(path);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendBroadcastToCaller(String path) {
        Intent intent = new Intent(UPLOAD_RESULT);
        intent.putExtra(IMG_PATH_KEY, path);
        sendBroadcast(intent);
    }
}
