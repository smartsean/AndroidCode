package com.sean.demo.widget.dialog.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sean.demo.R;

import java.util.ArrayList;

import cn.smartsean.lib.widget.dialog.BaseDialogFragment;


/**
 * @author SmartSean Created on 2018/1/29 17:23.
 */

public class ListDialog extends BaseDialogFragment {

    private static final String PARAM_TITLE = "title";
    private static final String PARAM_LIST = "list";

    public static Builder newBuilder() {
        return new Builder();
    }

    public static ListDialog newInstance(Builder builder) {
        ListDialog dialog = new ListDialog();
        Bundle bundle = getArgumentBundle(builder);
        bundle.putString(PARAM_TITLE, builder.mTitle);
        bundle.putParcelableArrayList(PARAM_LIST, builder.mListDialogModels);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static class Builder extends BaseDialogFragment.Builder<Builder, ListDialog> {

        private String mTitle;
        private ArrayList<ListDialogModel> mListDialogModels;

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setListDialogModels(ArrayList<ListDialogModel> listDialogModels) {
            mListDialogModels = listDialogModels;
            return this;
        }

        @Override
        public ListDialog build() {
            return ListDialog.newInstance(this);
        }
    }

    @Override
    protected View setView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.dialog_list_rv);
        TextView mTitleTv = view.findViewById(R.id.dialog_list_title_tv);
        if (!TextUtils.isEmpty(getArguments().getString(PARAM_TITLE))) {
            mTitleTv.setText(getArguments().getString(PARAM_TITLE));
        }
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        if (getArguments().getParcelableArrayList(PARAM_LIST) != null && getArguments().getParcelableArrayList(PARAM_LIST).size() > 0) {
            ArrayList<ListDialogModel> list = getArguments().getParcelableArrayList(PARAM_LIST);
            ListDialogAdapter adapter = new ListDialogAdapter(list, new DialogClick() {
                @Override
                public void click(ListDialogModel bean) {
                    if (mDialogResultListener != null) {
                        mDialogResultListener.result(bean);
                    }
                    dismiss();
                }
            });
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

    public class ListDialogAdapter extends RecyclerView.Adapter<ListDialogAdapter.Holder> {

        private ArrayList<ListDialogModel> mListDialogModels;
        private DialogClick mDialogClick;


        public ListDialogAdapter(ArrayList<ListDialogModel> listDialogModels, DialogClick dialogClick) {
            mListDialogModels = listDialogModels;
            mDialogClick = dialogClick;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout, parent, false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            final ListDialogModel bean = mListDialogModels.get(position);
            holder.mTextView.setText(bean.getValue());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialogClick.click(bean);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mListDialogModels.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public Holder(View itemView) {
                super(itemView);
                mTextView = itemView.findViewById(R.id.string);
            }
        }
    }

    interface DialogClick {
        void click(ListDialogModel bean);
    }
}
