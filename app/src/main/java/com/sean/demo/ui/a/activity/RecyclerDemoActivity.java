package com.sean.demo.ui.a.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;
import com.sean.demo.ui.a.adapter.RecyclerDemoAdapter;
import com.sean.demo.ui.a.adapter.RecyclerDemoModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerDemoActivity extends BaseActivity {

    @BindView(R.id.recycler_demo)
    RecyclerView recyclerDemo;

    private List<RecyclerDemoModel> recyclerDemoModels;

    private RecyclerDemoAdapter recyclerDemoAdapter;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_recycler_demo);
        context = RecyclerDemoActivity.this;
        ButterKnife.bind(this);
        setBackArrow();
        initData();
        LinearLayoutManager lm = new LinearLayoutManager(context);
        recyclerDemo.setLayoutManager(lm);
        recyclerDemoAdapter = new RecyclerDemoAdapter(context, recyclerDemoModels);
        recyclerDemo.setAdapter(recyclerDemoAdapter);
    }

    private void initData() {
        recyclerDemoModels = new ArrayList<>();
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.a, "这是第一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第二个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.e, "这是第三个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.eee, "这是第四个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.f, "这是第五个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.g, "这是第六个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.h, "这是第七个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.i, "这是第八个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.a, "这是第九个"));
    }


}
