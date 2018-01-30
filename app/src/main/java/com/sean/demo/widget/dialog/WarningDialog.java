package com.sean.demo.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sean.demo.R;

import cn.smartsean.lib.widget.dialog.BaseDialogFragment;


/**
 * @author SmartSean Created on 2018/1/29 15:18.
 */

public class WarningDialog extends BaseDialogFragment {

    private static final String PARAM_TITLE = "title";
    private static final String PARAM_MESSAGE = "message";

    private static WarningDialog newInstance(Builder builder) {
        WarningDialog dialog = new WarningDialog();
        Bundle bundle = getArgumentBundle(builder);
        bundle.putString(PARAM_TITLE, builder.mTitle);
        bundle.putString(PARAM_MESSAGE, builder.mMessage);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder extends BaseDialogFragment.Builder<Builder, WarningDialog> {

        private String mTitle;
        private String mMessage;

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            mMessage = message;
            return this;
        }

        @Override
        public WarningDialog build() {
            return WarningDialog.newInstance(this);
        }
    }

    @Override
    protected View setView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_warning, container, false);

        TextView titleTv = view.findViewById(R.id.title);
        TextView messageTv = view.findViewById(R.id.message);

        if (!TextUtils.isEmpty(getArguments().getString(PARAM_TITLE))) {
            titleTv.setText(getArguments().getString(PARAM_TITLE));
        }
        if (!TextUtils.isEmpty(getArguments().getString(PARAM_MESSAGE))) {
            messageTv.setText(getArguments().getString(PARAM_MESSAGE));
        }
        return view;
    }
}
