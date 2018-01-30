package com.sean.demo.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sean.demo.R;

import cn.smartsean.lib.widget.dialog.BaseDialogFragment;


/**
 * @author SmartSean Created on 2018/1/29 16:38.
 */
public class ConfirmDialog extends BaseDialogFragment {

    protected static final String LEFT_TEXT = "left_text";
    protected static final String RIGHT_TEXT = "right_text";
    private static final String PARAM_TITLE = "title";
    private static final String PARAM_MESSAGE = "message";

    public static Builder newConfirmBuilder() {
        return new Builder();
    }

    public static class Builder extends BaseDialogFragment.Builder<Builder, ConfirmDialog> {

        private String mTitle;
        private String mMessage;
        private String leftText;
        private String rightText;

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            mMessage = message;
            return this;
        }

        public Builder setLeftText(String leftText) {
            this.leftText = leftText;
            return this;
        }

        public Builder setRightText(String rightText) {
            this.rightText = rightText;
            return this;
        }

        @Override
        public ConfirmDialog build() {
            return ConfirmDialog.newInstance(this);
        }
    }

    private static ConfirmDialog newInstance(Builder builder) {
        ConfirmDialog dialog = new ConfirmDialog();
        Bundle bundle = getArgumentBundle(builder);
        bundle.putString(LEFT_TEXT, builder.leftText);
        bundle.putString(RIGHT_TEXT, builder.rightText);
        bundle.putString(PARAM_TITLE, builder.mTitle);
        bundle.putString(PARAM_MESSAGE, builder.mMessage);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    protected View setView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm, container, false);
        TextView titleTv = view.findViewById(R.id.title);
        TextView messageTv = view.findViewById(R.id.message);

        if (!TextUtils.isEmpty(getArguments().getString(PARAM_TITLE))) {
            titleTv.setText(getArguments().getString(PARAM_TITLE));
        }
        if (!TextUtils.isEmpty(getArguments().getString(PARAM_MESSAGE))) {
            messageTv.setText(getArguments().getString(PARAM_MESSAGE));
        }
        setBottomButton(view);
        return view;
    }

    protected void setBottomButton(View view) {
        Button cancelBtn = view.findViewById(R.id.cancel_btn);
        Button confirmBtn = view.findViewById(R.id.confirm_btn);
        if (getArguments() != null) {
            cancelBtn.setText(getArguments().getString(LEFT_TEXT));
            confirmBtn.setText(getArguments().getString(RIGHT_TEXT));
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mDialogResultListener != null) {
                        mDialogResultListener.result(false);
                        dismiss();
                    }
                }
            });
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mDialogResultListener != null) {
                        mDialogResultListener.result(true);
                        dismiss();
                    }
                }
            });
        }
    }
}
