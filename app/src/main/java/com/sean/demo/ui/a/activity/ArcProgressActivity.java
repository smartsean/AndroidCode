package com.sean.demo.ui.a.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sean.demo.R;
import com.sean.demo.widget.progress.ArcProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArcProgressActivity extends AppCompatActivity {

    @BindView(R.id.progress_apb)
    ArcProgressBar mProgressApb;
    @BindView(R.id.btn1)
    Button mBtn1;
    @BindView(R.id.btn2)
    Button mBtn2;
    @BindView(R.id.btn3)
    Button mBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_progress);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                mProgressApb.setDatas(new float[]{0.6f, 0.3f, 0.1f});
                break;
            case R.id.btn2:
                mProgressApb.setDatas(new float[]{0.3f, 0.6f, 0.1f});
                break;
            case R.id.btn3:
                mProgressApb.setDatas(new float[]{0.1f, 0.3f, 0.6f});
                break;
        }
    }
}
