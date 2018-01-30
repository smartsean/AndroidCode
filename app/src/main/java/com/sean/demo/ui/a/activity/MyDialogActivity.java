package com.sean.demo.ui.a.activity;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;
import com.sean.demo.utils.ScreenUtils;
import com.sean.demo.widget.dialog.ConfirmDialog;
import com.sean.demo.widget.dialog.InputDialog;
import com.sean.demo.widget.dialog.WarningDialog;
import com.sean.demo.widget.dialog.list.ListDialog;
import com.sean.demo.widget.dialog.list.ListDialogModel;

import java.util.ArrayList;

import cn.smartsean.lib.utils.DensityUtils;
import cn.smartsean.lib.widget.dialog.DialogDismissListener;
import cn.smartsean.lib.widget.dialog.DialogResultListener;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * @author SmartSean
 */
public class MyDialogActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_my_dialog);
        mContext = this;

        RxTextView rxTextView;
        Observable.just("1")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                    }
                });


        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WarningDialog.newBuilder()
                        .setTitle("我是测试标题")
                        .setMessage("我是测试message")
                        .setAnimation(R.style.DialogAnimFromCenter)
                        .setSize((int) (ScreenUtils.getScreenWidth(mContext) * 0.5), DensityUtils.dip2px(mContext, 200))
                        .build()
                        .setDialogDismissListener(new DialogDismissListener() {
                            @Override
                            public void dismiss(DialogFragment dialog) {
                                Toast.makeText(mContext, "我的tag：" + dialog.getTag(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(getFragmentManager(), "warningDialog");
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDialog.newConfirmBuilder()
                        .setTitle("这是一个带有确认、取消的dialog")
                        .setMessage("这是一个带有确认、取消的dialog的message")
                        .setLeftText("我点错了")
                        .setRightText("我确定")
                        .setAnimation(R.style.DialogAnimFromCenter)
                        .build()
                        .setDialogResultListener(new DialogResultListener<Boolean>() {
                            @Override
                            public void result(Boolean result) {
                                Toast.makeText(mContext, "你点击了：" + (result ? "确定" : "取消"), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setDialogDismissListener(new DialogDismissListener() {
                            @Override
                            public void dismiss(DialogFragment dialog) {
                                Toast.makeText(mContext, "我的tag：" + dialog.getTag(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(getFragmentManager(), "confirmDialog");

            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputDialog.newInputBuilder()
                        .setHint("请输入姓名")
                        .setLeftText("取消")
                        .setRightText("确认输入")
                        .setAnimation(R.style.DialogAnimFromCenter)
                        .setSize((int) (ScreenUtils.getScreenWidth(mContext) * 0.68), DensityUtils.dip2px(mContext, 200))
                        .build()
                        .setDialogResultListener(new DialogResultListener<String>() {
                            @Override
                            public void result(String result) {
                                if (!TextUtils.isEmpty(result)) {
                                    Toast.makeText(mContext, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .show(getFragmentManager(), "inputDialog");
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ListDialogModel> listDialogModels = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    listDialogModels.add(new ListDialogModel(i, "我是" + i + "个"));
                }
                ListDialog.newBuilder()
                        .setTitle("请选择你的操作")
                        .setListDialogModels(listDialogModels)
                        .setGravity(Gravity.BOTTOM)
                        .setOffsetY(8)
                        .setSize((int) (ScreenUtils.getScreenWidth(mContext) * 0.8), (int) (ScreenUtils.getScreenWidth(mContext) * 0.8))
                        .build()
                        .setDialogResultListener(new DialogResultListener<ListDialogModel>() {
                            @Override
                            public void result(ListDialogModel result) {
                                Toast.makeText(mContext, "你选择的是第" + result.getKey() + "个，内容==" + result.getValue(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(getFragmentManager(), "listDialog");
            }
        });
    }
}
