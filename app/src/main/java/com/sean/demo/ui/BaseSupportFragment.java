package com.sean.demo.ui;

import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;

public class BaseSupportFragment extends Fragment {
    public class DontSpillOnTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

}
