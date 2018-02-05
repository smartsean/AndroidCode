package com.sean.demo.ui.a.activity.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sean.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewDispatchActivity extends AppCompatActivity {

    @BindView(R.id.button_1)
    Button mButton1;
    @BindView(R.id.linearlayout_1)
    LinearLayout mLinearlayout1;
    @BindView(R.id.button_2)
    Button mButton2;
    @BindView(R.id.linearlayout_2)
    LinearLayout mLinearlayout2;
    @BindView(R.id.button_3)
    Button mButton3;
    @BindView(R.id.linearlayout_3)
    LinearLayout mLinearlayout3;
    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.linearlayout)
    LinearLayout mLinearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dispatch);
        ButterKnife.bind(this);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewDispatchActivity.this, "显示dialog", Toast.LENGTH_SHORT).show();
            }
        });

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewDispatchActivity.this, "LinearLayout拦截", Toast.LENGTH_SHORT).show();
            }
        });


        mLinearlayout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
