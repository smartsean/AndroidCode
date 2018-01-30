package com.sean.demo.ui.a.adapter;

/**
 * @author  SmartSean
 */
public class RecyclerDemoModel {
    private int imageId;
    private String desc;

    public RecyclerDemoModel(int imageId, String desc) {
        this.imageId = imageId;
        this.desc = desc;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
