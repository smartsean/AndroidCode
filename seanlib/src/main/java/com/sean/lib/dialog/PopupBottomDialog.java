package com.sean.lib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.sean.lib.R;

/**
 * Created by Sean on 17/4/20.
 */

public class PopupBottomDialog extends Dialog {

    private Context context;

    TextView text_cancel;
    TextView text_photo;
    TextView text_camera;

    public PopupBottomDialog(Context context) {
        super(context, R.style.PopupDialog);
        this.context = context;
        setMsgDialog();
    }




    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PopupBottomDialog.this.dismiss();
        }
    };

    private void setMsgDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.bottom_my_dialog, null);
        text_cancel = (TextView) mView.findViewById(R.id.text_pop_identy_cancel);
        text_photo = (TextView) mView.findViewById(R.id.text_pop_identy_photo);
        text_camera = (TextView) mView.findViewById(R.id.text_pop_identy_camera);
        text_cancel.setOnClickListener(listener);
        text_photo.setOnClickListener(listener);
        text_camera.setOnClickListener(listener);


        this.setCanceledOnTouchOutside(true);                 //点击外部关闭窗口

        Window win = this.getWindow();
        win.setGravity(Gravity.BOTTOM);                       //从下方弹出
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //宽度填满
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;  //高度自适应
        win.setAttributes(lp);

        super.setContentView(mView);
    }

    @Override
    public void show() {
        super.show();
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        /////////获取屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);;
        wm.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        /////////设置高宽
        lp.width = (int) (screenWidth * 0.9); // 宽度
//        lp.height = (int) (lp.width*1);     // 高度
        dialogWindow.setAttributes(lp);
    }
}
