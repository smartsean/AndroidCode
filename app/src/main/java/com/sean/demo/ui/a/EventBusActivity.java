package com.sean.demo.ui.a;

import android.os.Bundle;
import android.view.Menu;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;

public class EventBusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_event_bus);
        setTitle("EventBus使用");
        setBackArrow();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_eventbus, menu);
        return true;
    }


}
