package com.sean.demo.ui.a;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;

public class EventBusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_event_bus);//设置到BaseActivity中的content中
        setTitle("EventBus使用");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        setToolBarMenuOnclick(new EventBusMenuItemClick());//设置menu菜单的显示和点击事件
    }

    /**
     * 显示菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_eventbus, menu);
        return true;
    }

    /**
     * 创建菜单点击事件
     */
    class EventBusMenuItemClick implements Toolbar.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_message:
                    Toast.makeText(EventBusActivity.this, "Click action_message", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_message1:
                    Toast.makeText(EventBusActivity.this, "Click action_message1", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    }

}
