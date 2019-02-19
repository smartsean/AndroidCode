package com.sean.demo.ui.a.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;
import com.sean.demo.ui.a.adapter.RecyclerDemoAdapter;
import com.sean.demo.ui.a.adapter.RecyclerDemoModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author SmartSean
 */
public class RecyclerDemoActivity extends BaseActivity {

    @BindView(R.id.recycler_demo)
    RecyclerView recyclerDemo;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    private List<RecyclerDemoModel> recyclerDemoModels;
    private List<RecyclerDemoModel> recyclerDemoModels1;

    private RecyclerDemoAdapter recyclerDemoAdapter;

    private Context context;
    private LinearLayoutManager lm;
    private boolean sIsScrolling;
    private RecyclerView.OnScrollListener mOnScrollListener;
    LruCache<String,Bitmap> mStringBitmapLruCache = new LruCache<>(20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_recycler_demo);
        context = RecyclerDemoActivity.this;
        ButterKnife.bind(this);
        setBackArrow();
        initData();
        lm = new LinearLayoutManager(context);
//        recyclerDemo.setLayoutManager(lm);
//        recyclerDemoAdapter = new RecyclerDemoAdapter(context, recyclerDemoModels);
//        recyclerDemo.setAdapter(recyclerDemoAdapter);
        initDataForStaggeredGridLayout();
        StaggeredGridLayoutManager sgl = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerDemo.setLayoutManager(sgl);
        recyclerDemoAdapter = new RecyclerDemoAdapter(context, recyclerDemoModels);
        recyclerDemoAdapter.getRandomHeight(recyclerDemoModels);

        recyclerDemo.setAdapter(recyclerDemoAdapter);
        /**
         * SCROLL_STATE_DRAGGING      正在滚动
         * SCROLL_STATE_SETTLING      手指做了抛的动作（手指离开屏幕前，用力滑了一下）
         * SCROLL_STATE_IDLE          停止滚动
         */
        mOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                    sIsScrolling = true;
                    Glide.with(RecyclerDemoActivity.this).pauseRequests();
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (sIsScrolling) {
                        Glide.with(RecyclerDemoActivity.this).resumeRequests();
                    }
                    sIsScrolling = false;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        };
        recyclerDemo.addOnScrollListener(mOnScrollListener);
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
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.a, "这是第十个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.d, "这是第十一个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.e, "这是第十二个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.eee, "这是第十三个"));
        recyclerDemoModels.add(new RecyclerDemoModel(R.drawable.f, "这是第十四个"));
    }

    private void initDataForStaggeredGridLayout() {
        recyclerDemoModels1 = new ArrayList<>();
        recyclerDemoModels1.add(new RecyclerDemoModel(R.drawable.a, "这是第一个"));
        recyclerDemoModels1.add(new RecyclerDemoModel(R.drawable.d, "这是第二个"));
        recyclerDemoModels1.add(new RecyclerDemoModel(R.drawable.e, "这是第四个"));
        recyclerDemoModels1.add(new RecyclerDemoModel(R.drawable.eee, "这是第四个"));
        recyclerDemoModels1.add(new RecyclerDemoModel(R.drawable.f, "这是第五个"));
        recyclerDemoModels1.add(new RecyclerDemoModel(R.drawable.g, "这是第六个"));
        recyclerDemoModels1.add(new RecyclerDemoModel(R.drawable.h, "这是第七个"));
        recyclerDemoModels1.add(new RecyclerDemoModel(R.drawable.i, "这是第八个"));
        recyclerDemoModels1.add(new RecyclerDemoModel(R.drawable.a, "这是第九个"));
    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                lm = new LinearLayoutManager(context);
                recyclerDemo.setLayoutManager(lm);
                break;
            case R.id.btn2:
                GridLayoutManager gm = new GridLayoutManager(context, 4);
                recyclerDemo.setLayoutManager(gm);
                break;
            case R.id.btn3:
//                lm.setOrientation(LinearLayoutManager.HORIZONTAL);
//                recyclerDemo.setLayoutManager(lm);
                GridLayoutManager gm1 = new GridLayoutManager(context, 2);
                gm1.setOrientation(GridLayoutManager.HORIZONTAL);
                recyclerDemo.setLayoutManager(gm1);
                break;
            case R.id.btn4:
                initDataForStaggeredGridLayout();
                StaggeredGridLayoutManager sgl = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                recyclerDemo.setLayoutManager(sgl);
                recyclerDemo.setAdapter(new RecyclerDemoAdapter(context, recyclerDemoModels1));
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        recyclerDemo.removeOnScrollListener(mOnScrollListener);
    }
}
