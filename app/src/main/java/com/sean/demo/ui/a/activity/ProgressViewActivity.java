package com.sean.demo.ui.a.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;

import com.sean.demo.R;
import com.sean.demo.ui.BaseActivity;
import com.sean.demo.widget.progress.LineProgressBar;

public class ProgressViewActivity extends BaseActivity {

    private static final String TAG = "ProgressViewActivity";

    LineProgressBar mProgressLpb;
    EditText mValueEt;
    Button mSubmitBtn;
    Switch mToggleS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_progress_view);
        mProgressLpb = findViewById(R.id.progress_lpb);
        mValueEt = findViewById(R.id.value_et);
        mSubmitBtn = findViewById(R.id.submit_btn);
        mToggleS = findViewById(R.id.toggle_s);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressLpb.setSmoothProgress(mToggleS.isChecked());
                float value = Float.parseFloat(mValueEt.getText().toString().trim());
                mProgressLpb.setProgress(value);
            }
        });
    }
}
