package com.sean.demo.widget.dialog.list;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author SmartSean Created on 2018/1/29 17:24.
 */

public class ListDialogModel implements Parcelable {
    private int key;
    private String value;

    public ListDialogModel(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.key);
        dest.writeString(this.value);
    }

    protected ListDialogModel(Parcel in) {
        this.key = in.readInt();
        this.value = in.readString();
    }

    public static final Creator<ListDialogModel> CREATOR = new Creator<ListDialogModel>() {
        @Override
        public ListDialogModel createFromParcel(Parcel source) {
            return new ListDialogModel(source);
        }

        @Override
        public ListDialogModel[] newArray(int size) {
            return new ListDialogModel[size];
        }
    };
}
