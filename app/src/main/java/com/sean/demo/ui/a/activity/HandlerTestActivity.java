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
/**
 * @author  SmartSean
 */
public class HandlerTestActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    TextView show;
    @BindView(R.id.handler_tb)
    Toolbar handlerTb;
    private String message = "Sean";
    private Button button4;
    private Button button5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.a_activity_handler_test);
        initView1();
        setToolBar(R.id.handler_tb);
        setToolBarMenuOnclick(new HandlerMenuClickListener());
        setBackArrow();
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
            }
            return false;
        }
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

    private void initView1() {
        show = (TextView) findViewById(R.id.show);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button4.performClick();// 写在OnResume之前执行的话，可以在子线程更新UI线程


        /**
         *使用post方法直接更新ui线程
         */
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        message = "mySean1";
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                show.setText(message);
                            }
                        });
                    }
                }).start();
            }
        });

        /**
         *使用runOnUiThread更新ui线程
         */
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        message = "mySean2";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                show.setText(message);
                            }
                        });
                    }
                }).start();
            }
        });

        /**
         * 会崩溃的
         */
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        show.setText("测试非ui线程更新ui");
                    }
                }).start();
            }
        });


        /**
         * 此种方式能在onResume中或者其之前调用，因为在onResume中会初始化控制ui线程更新ui的一些控制
         */
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beforeOnResumeClick();
            }
        });


        /**
         * 子线程中使用主线程中创建的Handler发送消息，在主线程中的Handler中处理
         */
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: "+Thread.currentThread().getName());

                        button5.post(new Runnable() {
                            @Override
                            public void run() {
                                show.setText("我是通过View的post方法更新ui的");
                            }
                        });
                    }
                }).start();
            }
        });

        /**
         * 子线程中创建Handler（handler1）发送消息，在子线程中的Handler（handler1）中处理,然后发送给主线程（mHandler）去更新ui
         */
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Handler handler1;
                        handler1 = new Handler() {
                            @Override
                            public void handleMessage(Message msg) {
                                Log.d(TAG, "handleMessage: " + "\nwhat = " + msg.what + "\nobj = " + msg.obj);
                                if (msg.what == 1) {
                                    Message message = new Message();
                                    message.what = 4;
                                    message.obj = "测试子线程发送消息，在主线程更新ui======>>>4";
                                    mHandler.sendMessage(message);
                                }
                            }
                        };
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = "测试子线程message";
                        handler1.sendMessage(msg);
                        Looper.loop();
                    }
                }).start();

            }
        });


        /**
         * 使用主线程中的handler发送消息，然后在主线程中更新ui
         */
        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 1;
                        message.obj = "测试子线程发送消息，在主线程更新ui======>>>1";
                        mHandler.sendMessage(message);
                    }
                }).start();
            }
        });

        /**
         * 使用主线程中的handler发送消息，然后在主线程中更新ui
         */
        findViewById(R.id.button7).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 2;
                        message.obj = "测试子线程发送消息，在主线程更新ui======>>>2";
                        mHandler.sendMessage(message);
                    }
                }).start();
                return true;
            }
        });

        /**
         * 消除handler的内存泄漏
         */
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HandlerTestActivity.this,SafeHandlerDemoActivity.class));
            }
        });
    }


    private Handler mHandler1 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    /**
     * 主线程中的Handler
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            show.setText("msg.what = " + msg.what + " 的时候 ： \n" + "msg.obj = " + msg.obj.toString());
        }
    };

    /**
     * 测试在onResume之前调用Thread更新ui
     */
    private void beforeOnResumeClick() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                show.setText("测试在onResume之前调用Thread更新ui");
            }
        }.start();
    }
}
