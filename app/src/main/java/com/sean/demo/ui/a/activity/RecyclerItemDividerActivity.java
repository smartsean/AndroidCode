package com.sean.demo.ui.a.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sean.demo.R;
import com.sean.demo.ui.a.adapter.RecyclerItemDividerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.smartsean.lib.widget.recyclerview.RecyclerItemDivider;

/**
 * @author  SmartSean
 */
public class RecyclerItemDividerActivity extends AppCompatActivity {

    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_item_divider);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("我是第" + i + "个");
        }
        RecyclerItemDividerAdapter adapter = new RecyclerItemDividerAdapter(list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecyclerItemDivider(this, RecyclerItemDivider.VERTICAL));
    }
}
