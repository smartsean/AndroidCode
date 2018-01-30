package com.sean.demo.ui.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sean.demo.R;
import com.sean.demo.ui.BaseSupportFragment;

/**
 * Created by sean on 2017/3/9.
 */

public class DFragment extends BaseSupportFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d, container, false);
        view.setOnTouchListener(new DontSpillOnTouchListener());
        return view;
    }
}
