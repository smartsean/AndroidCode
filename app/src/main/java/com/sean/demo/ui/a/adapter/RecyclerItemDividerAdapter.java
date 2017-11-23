package com.sean.demo.ui.a.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sean.demo.R;

import java.util.List;

/**
 * Created by smartsean on 17/11/23 15:45.
 */

public class RecyclerItemDividerAdapter extends RecyclerView.Adapter<RecyclerItemDividerAdapter.ViewHolder> {

    private List<String> list;
    private Context mContext;

    public RecyclerItemDividerAdapter(List<String> list, Context context) {
        this.list = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTV.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemTV;

        public ViewHolder(View itemView) {
            super(itemView);
            itemTV = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }
}
