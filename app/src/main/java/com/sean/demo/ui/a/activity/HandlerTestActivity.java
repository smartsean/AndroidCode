package com.sean.demo.ui.a.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author SmartSean
 */
public class HandlerTestActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.handler_tb)
    Toolbar handlerTb;
    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.button5)
    Button mButton5;
    @BindView(R.id.button6)
    Button mButton6;
    @BindView(R.id.button7)
    Button mButton7;
    @BindView(R.id.button8)
    Button mButton8;
    @BindView(R.id.button9)
    Button mButton9;

    TextView mShow;
    Button mButton4;

    /**
     * 主线程中的Handler
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mShow.setText("msg.what = " + msg.what + " 的时候 ： \n\n" + "msg.obj = " + msg.obj.toString());
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.a_activity_handler_test);
        ButterKnife.bind(this);
        setToolBar(R.id.handler_tb);
        setToolBarMenuOnclick(new HandlerMenuClickListener());
        setBackArrow();
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 单独把抽出来是为了测试：可以在子线程中更新 UI（在onResume之前调用点击事件则可以）
     * 如果使用 ButterKnife 则无效
     */
    private void init() {
        mShow = findViewById(R.id.show);
        mButton4 = findViewById(R.id.button4);
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beforeOnResumeClick();
            }
        });
        // 写在OnResume之前执行点击事件的话，可以在子线程更新UI线程
        mButton4.performClick();
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                //使用post方法直接更新ui线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d(TAG, "run: ");
                                mShow.setText("使用post方法直接更新ui线程");
                            }
                        });
                    }
                }).start();
                break;
            case R.id.button2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mShow.setText("使用runOnUiThread更新ui线程");
                            }
                        });
                    }
                }).start();
                break;
            case R.id.button3:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mShow.setText("点我会蹦，我是在子线程更新ui的");
                    }
                }).start();
                break;
            case R.id.button5:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mButton5.post(new Runnable() {
                            @Override
                            public void run() {
                                mShow.setText("通过View的post方法更新ui");
                            }
                        });
                    }
                }).start();
                break;
            case R.id.button6:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Handler threadHandler;
                        threadHandler = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                                if (msg.what == 1) {
                                    mHandler.sendMessage(mHandler.obtainMessage(1, "子线程中创建Handler（handler1）发送消息，在子线程中的Handler（handler1）中处理,然后发送给主线程（mHandler）去更新ui"));
                                }
                            }

                        };
                        Message message = Message.obtain();
                        message.what = 1;
                        threadHandler.sendMessage(message);
                        Looper.loop();
                    }
                }).start();
                break;
            case R.id.button7:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = mHandler.obtainMessage(7, "子线程中发布消息，更新主线程");
                        mHandler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.button8:
                startActivity(new Intent(HandlerTestActivity.this, SafeHandlerDemoActivity.class));
                break;
            case R.id.button9:
//                startActivity(new Intent(HandlerTestActivity.this, MemoryLeakActivity.class));
                break;
            default:
                return;
        }
    }


    /**
     * 此种方式能在onResume中或者其之前调用，因为在onResume中会初始化控制ui线程更新ui的一些控制
     */


    /**
     * 测试在onResume之前调用Thread更新ui
     */
    private void beforeOnResumeClick() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                mShow.setText("测试在onResume之前调用Thread更新ui");
            }
        }.start();
    }

    /**
     * 创建菜单栏
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_handler, menu);
        return true;
    }

    /**
     * 用于调用Toolbar菜单栏的点击事件
     */
    class HandlerMenuClickListener implements Toolbar.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_message:
                    Toast.makeText(HandlerTestActivity.this, "clicked me", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            return false;
        }
    }
}
