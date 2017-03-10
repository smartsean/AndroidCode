package com.sean.demo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sean.demo.R;

public class BaseActivity extends AppCompatActivity {

    private TextView commonTitleTv;
    private Toolbar commonTitleTb;
    private RelativeLayout content;

    public void setToolBarOnclick(Toolbar.OnMenuItemClickListener onclick) {
        commonTitleTb.setOnMenuItemClickListener(onclick);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
        setSupportActionBar(commonTitleTb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        setBackArrow();
    }

    private void initView() {
        commonTitleTv = (TextView) findViewById(R.id.common_title_tv);
        commonTitleTb = (Toolbar) findViewById(R.id.common_title_tb);
        content = (RelativeLayout) findViewById(R.id.content);
    }


    public void setToolBar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        this.getMenuInflater().inflate(R.menu.menu_main, menu);
//        Logger.d("menu");
//        return true;
//    }

    /**
     * 设置左上角back按钮
     */
    public void setBackArrow() {

        final Drawable upArrow = getResources().getDrawable(R.drawable.common_back_ic);
        //给ToolBar设置左侧的图标
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置actionBar的标题是否显示，对应ActionBar.DISPLAY_SHOW_TITLE。
        commonTitleTb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 隐藏掉右边的图标
     */
    public void hideRightIcon() {
        getSupportActionBar().setDisplayShowCustomEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

//    /**
//     * 点击左上角的返回按钮，结束本Activity
//     * home就是左上角的小箭头，在toolbar上
//     *
//     * @param item
//     * @return
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void setContentLayout(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        content.addView(contentView, params);
    }

    public void setTitle(String title) {
        if (null != title) {
            commonTitleTv.setText(title);
        }
    }

    public void setTitle(int resId) {
        commonTitleTv.setText(resId);
    }
}
