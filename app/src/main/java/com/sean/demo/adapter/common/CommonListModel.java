package com.sean.demo.adapter.common;

/**
 * Created by sean on 2017/3/9.
 */

public class CommonListModel {
    private String name;

    private Class activity;

    public CommonListModel(String name, Class activity) {
        this.name = name;
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }
}
