package com.sean.demo.ui.a.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sean.demo.R;

import java.util.List;

/**
 * Created by Sean on 2017/3/22.
 */

public class RecyclerDemoAdapter extends RecyclerView.Adapter<RecyclerDemoAdapter.ViewHolder> {

    // 首先定义一个ViewHolder，用于复用View
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageId;
        TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            imageId = (ImageView) itemView.findViewById(R.id.item_image);
            desc = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    private List<RecyclerDemoModel> recyclerDemoModels;
    private Context context;

    // 构造方法，问Activity或者Fragment索要需要的数据
    public RecyclerDemoAdapter(Context context, List<RecyclerDemoModel> recyclerDemoModels) {
        this.recyclerDemoModels = recyclerDemoModels;
        this.context = context;
    }

    // onCreateViewHolder只要用于加载布局，初始化View操作，返回ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_demo, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // onBindViewHolder 主要用于操作数据和控件的
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerDemoModel bean = recyclerDemoModels.get(position);
        holder.imageId.setImageResource(bean.getImageId());
        holder.desc.setText(bean.getDesc());
    }

    // 此处是千万不能忘记写的，不然会没数据
    @Override
    public int getItemCount() {
        return recyclerDemoModels.size();
    }


}
