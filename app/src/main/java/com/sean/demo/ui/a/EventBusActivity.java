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
        setContentLayout(R.layout.activity_event_bus);
        setTitle("EventBus使用");
        setBackArrow();
        setToolBarOnclick(onMenuItemClick);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_eventbus, menu);
        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_message:
                    msg += "Click edit";
                    break;
                case R.id.action_message1:
                    msg += "Click share";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(EventBusActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };

}
