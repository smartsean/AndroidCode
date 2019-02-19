package com.sean.demo.ui.index;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.sean.demo.R;
import com.sean.demo.adapter.common.CommonListAdapter;
import com.sean.demo.adapter.common.CommonListModel;
import com.sean.demo.ui.BaseSupportFragment;
import com.sean.demo.ui.a.activity.ArcProgressActivity;
import com.sean.demo.ui.a.activity.AsyncTaskActivity;
import com.sean.demo.ui.a.activity.EventBusActivity;
import com.sean.demo.ui.a.activity.HandlerTestActivity;
import com.sean.demo.ui.a.activity.HandlerThreadActivity;
import com.sean.demo.ui.a.activity.HandlerThreadTestActivity;
import com.sean.demo.ui.a.activity.IntentServiceActivity;
import com.sean.demo.ui.a.activity.MyDialogActivity;
import com.sean.demo.ui.a.activity.ProgressViewActivity;
import com.sean.demo.ui.a.activity.RecyclerDemoActivity;
import com.sean.demo.ui.a.activity.RecyclerItemDividerActivity;
import com.sean.demo.ui.a.activity.ThreadPoolActivity;
import com.sean.demo.ui.a.activity.webview.GlideActivity;
import com.sean.demo.ui.a.activity.webview.ViewDispatchActivity;
import com.sean.demo.ui.a.activity.webview.WebViewActivity;
import com.sean.demo.ui.b.JsAndJavaActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AFragment extends BaseSupportFragment {


    @BindView(R.id.fragment_a_rv)
    RecyclerView fragmentARv;
    private List<CommonListModel> commonListModelList = new ArrayList<>();
    private CommonListAdapter commonListAdapter;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        view.setOnTouchListener(new DontSpillOnTouchListener());
        context = getActivity();
        ButterKnife.bind(this, view);
        initData();
        initRecycler();
        return view;
    }

    /**
     * 初始化主页显示数据
     */
    private void initData() {
        commonListModelList.add(new CommonListModel("Glide", GlideActivity.class));
        commonListModelList.add(new CommonListModel("Handler的使用", HandlerTestActivity.class));
        commonListModelList.add(new CommonListModel("EventBus", EventBusActivity.class));
        commonListModelList.add(new CommonListModel("RecyclerDemo", RecyclerDemoActivity.class));
        commonListModelList.add(new CommonListModel("RecyclerView的分割线", RecyclerItemDividerActivity.class));
        commonListModelList.add(new CommonListModel("自定义Dialog", MyDialogActivity.class));
        commonListModelList.add(new CommonListModel("WebView", JsAndJavaActivity.class));
        commonListModelList.add(new CommonListModel("AsyncTask", AsyncTaskActivity.class));
        commonListModelList.add(new CommonListModel("IntentService", IntentServiceActivity.class));
        commonListModelList.add(new CommonListModel("HandlerThread", HandlerThreadActivity.class));
        commonListModelList.add(new CommonListModel("ThreadPool", ThreadPoolActivity.class));
        commonListModelList.add(new CommonListModel("自定义View无值进度条", ProgressViewActivity.class));
        commonListModelList.add(new CommonListModel("自定义View弧形进度条", ArcProgressActivity.class));
        commonListModelList.add(new CommonListModel("事件分发", ViewDispatchActivity.class));
        commonListModelList.add(new CommonListModel("HandlerThread", HandlerThreadTestActivity.class));
    }

    private void initRecycler() {
        commonListAdapter = new CommonListAdapter(context, R.layout.item_common_fragment_a, commonListModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        fragmentARv.setLayoutManager(linearLayoutManager);
        fragmentARv.setAdapter(commonListAdapter);
        fragmentARv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(context, commonListModelList.get(position).getActivity()));
            }
        });
    }
}
