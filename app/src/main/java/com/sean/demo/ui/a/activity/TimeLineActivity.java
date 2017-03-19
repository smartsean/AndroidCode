package com.sean.demo.ui.a.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeLineActivity extends BaseActivity {

    @BindView(R.id.timeline_rv)
    RecyclerView timelineRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_time_line);
        ButterKnife.bind(this);
        setTitle("时间线");
        setBackArrow();
    }
}
