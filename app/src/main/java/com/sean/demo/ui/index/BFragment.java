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
import com.sean.demo.ui.a.activity.HandlerTestActivity;
import com.sean.demo.ui.a.activity.webview.GlideActivity;
import com.sean.demo.ui.b.JsAndJavaActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sean on 2017/3/9.
 */

public class BFragment extends BaseSupportFragment {

    @BindView(R.id.fragment_b_rv)
    RecyclerView fragmentBRv;
    private Context context;
    private List<CommonListModel> commonListModelList = new ArrayList<>();
    private CommonListAdapter commonListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        view.setOnTouchListener(new DontSpillOnTouchListener());
        context = getActivity();
        ButterKnife.bind(this, view);
        initData();
        initRecycler();
        return view;
    }

    private void initRecycler() {
        commonListAdapter = new CommonListAdapter(context, R.layout.item_common_fragment_a, commonListModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        fragmentBRv.setLayoutManager(linearLayoutManager);
        fragmentBRv.setAdapter(commonListAdapter);
        fragmentBRv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(context, commonListModelList.get(position).getActivity()));
            }
        });

    }

    private void initData() {
        commonListModelList.add(new CommonListModel("Js和Java交互", JsAndJavaActivity.class));
    }
}
