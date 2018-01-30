package com.sean.demo.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sean.demo.R;

import cn.smartsean.lib.widget.dialog.BaseDialogFragment;


/**
 * @author SmartSean Created on 2018/1/29 16:58.
 */

public class InputDialog extends ConfirmDialog {

    private static final String PARAM_HINT = "hint";

    public static Builder newInputBuilder() {
        return new Builder();
    }

    private static InputDialog newInstance(Builder builder) {
        InputDialog dialog = new InputDialog();
        Bundle bundle = getArgumentBundle(builder);
        bundle.putString(PARAM_HINT, builder.mHint);
        bundle.putString(LEFT_TEXT, builder.leftText);
        bundle.putString(RIGHT_TEXT, builder.rightText);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static class Builder extends BaseDialogFragment.Builder<Builder, InputDialog> {

        private String mHint;
        private String leftText;
        private String rightText;

        public Builder setHint(String hint) {
            mHint = hint;
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
        public InputDialog build() {
            return InputDialog.newInstance(this);
        }
    }

    @Override
    protected View setView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_input, container, false);
        final EditText inputEt = view.findViewById(R.id.input_et);
        if (!TextUtils.isEmpty(getArguments().getString(PARAM_HINT))) {
            inputEt.setHint(getArguments().getString(PARAM_HINT));
        }
        Button cancelBtn = view.findViewById(R.id.cancel_btn);
        Button confirmBtn = view.findViewById(R.id.confirm_btn);
        if (getArguments() != null) {
            cancelBtn.setText(getArguments().getString(LEFT_TEXT));
            confirmBtn.setText(getArguments().getString(RIGHT_TEXT));
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mDialogResultListener != null) {
                        mDialogResultListener.result("");
                        dismiss();
                    }
                }
            });
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mDialogResultListener != null) {
                        mDialogResultListener.result(inputEt.getText().toString().trim());
                        dismiss();
                    }
                }
            });
        }
        return view;
    }
}
