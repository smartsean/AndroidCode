package com.sean.demo.adapter.common;

import android.content.Context;
import android.content.res.Resources;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.sean.demo.R;

import java.util.List;

/**
 * Created by sean on 2017/3/9.
 */

public class CommonListAdapter extends BaseQuickAdapter<CommonListModel, BaseViewHolder> {

    private Context context;
    private Resources resources;

    public CommonListAdapter(Context context,int layoutResId, List<CommonListModel> data) {
        super(layoutResId, data);
        this.context = context;
        this.resources = context.getResources();
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonListModel item) {
        helper.setText(R.id.name,item.getName());
    }
}
