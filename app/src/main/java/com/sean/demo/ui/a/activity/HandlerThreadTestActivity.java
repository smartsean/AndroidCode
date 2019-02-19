package com.sean.demo.ui.a.activity;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sean.demo.R;

/**
 * @author smartsean
 */
public class HandlerThreadTestActivity extends AppCompatActivity {

    private static final String TAG = "HandlerThreadTest";

    private TextView mShow;

    private HandlerThread mHandlerThread = new HandlerThread("MyHandlerThread");

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread_test);
        mShow = findViewById(R.id.show);
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(final Message message) {
                Log.d(TAG, "handleMessage: "+message.what);
                super.handleMessage(message);
                Log.d(TAG, "handleMessage: "+message.what);
                final int what = message.what;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "里面的  hashCode: "+message.hashCode()+" what 的值为"+message.what);
                        mShow.setText("得到的 what 为： "+what+"====");
                    }
                });
                Log.d(TAG, "外面的  hashCode: "+message.hashCode()+" what 的值为"+message.what);
                Log.d(TAG, "handleMessage: " + message.what);
            }
        };
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendMessage(mHandler.obtainMessage(1));
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendMessage(mHandler.obtainMessage(2));
            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendMessage(mHandler.obtainMessage(3));
            }
        });
    }
}
