package com.sean.demo.ui.a.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sean.demo.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author  SmartSean
 */
public class TImeLineAdapter extends RecyclerView.Adapter<TImeLineAdapter.Viewholder> {
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, Object>> listItem;

    //构造函数，传入数据
    public TImeLineAdapter(Context context, ArrayList<HashMap<String, Object>> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }


    //定义Viewholder
    class Viewholder extends RecyclerView.ViewHolder {
        private TextView Title, Text;

        public Viewholder(View root) {
            super(root);
            Title = (TextView) root.findViewById(R.id.Itemtitle);
            Text = (TextView) root.findViewById(R.id.Itemtext);

        }

        public TextView getTitle() {
            return Title;
        }

        public TextView getText() {
            return Text;
        }


    }

    @Override
    public TImeLineAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(inflater.inflate(R.layout.item_time_line, null));
    }//在这里把ViewHolder绑定Item的布局

    @Override
    public void onBindViewHolder(TImeLineAdapter.Viewholder holder, final int position) {
        // 绑定数据到ViewHolder里面
        holder.Title.setText((String) listItem.get(position).get("ItemTitle"));
        holder.Text.setText((String) listItem.get(position).get("ItemText"));
    }

    //返回Item数目
    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
