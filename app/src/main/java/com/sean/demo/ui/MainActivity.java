package com.sean.demo.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.sean.demo.R;
import com.sean.demo.ui.index.AFragment;
import com.sean.demo.ui.index.BFragment;
import com.sean.demo.ui.index.CFragment;
import com.sean.demo.ui.index.DFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final int FRAGMENT_FLAG_A = 0X01;
    private static final int FRAGMENT_FLAG_B = 0X02;
    private static final int FRAGMENT_FLAG_C = 0X03;
    private static final int FRAGMENT_FLAG_D = 0X04;


    @BindView(R.id.index_tab_a)
    RadioButton indexTabA;
    @BindView(R.id.index_tab_b)
    RadioButton indexTabB;
    @BindView(R.id.index_tab_c)
    RadioButton indexTabC;
    @BindView(R.id.index_tab_d)
    RadioButton indexTabD;

    private AFragment fragmentA;
    private BFragment fragmentB;
    private CFragment fragmentC;
    private DFragment fragmentD;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);
        ButterKnife.bind(this);
        context = MainActivity.this;
        initView();
        Logger.d("MainActivity+====onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("MainActivity+====onResume");

    }

    private void initView() {
        indexTabA.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_A));
        indexTabB.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_B));
        indexTabC.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_C));
        indexTabD.setOnCheckedChangeListener(new OnNaviCheckChangeListener(FRAGMENT_FLAG_D));
        indexTabA.setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Logger.d("main");

        return true;
    }

    /**
     * 定义RadioButton的选中改变监听
     */
    class OnNaviCheckChangeListener implements CompoundButton.OnCheckedChangeListener {

        private int posttion;

        public OnNaviCheckChangeListener(int posttion) {
            this.posttion = posttion;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                changeTab(posttion);
            }
        }
    }

    /**
     * 切换tab
     *
     * @param posttion
     */
    private void changeTab(int posttion) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (posttion) {
            case FRAGMENT_FLAG_A:
                changeTabToFragmentA(transaction);
                break;
            case FRAGMENT_FLAG_B:
                changeTabToFragmentB(transaction);
                break;

            case FRAGMENT_FLAG_C:
                changeTabToFragmentC(transaction);
                break;

            case FRAGMENT_FLAG_D:
                changeTabToFragmentD(transaction);
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 跳转到fragmentA
     */
    private void changeTabToFragmentA(FragmentTransaction transaction) {
        setTitle(R.string.fragment_a_title);
        if (fragmentA == null) {
            fragmentA = new AFragment();
            transaction.add(R.id.index_content_fl, fragmentA);
        } else {
            transaction.show(fragmentA);
        }
    }

    /**
     * 跳转到fragmentB
     */
    private void changeTabToFragmentB(FragmentTransaction transaction) {
        setTitle(R.string.fragment_b_title);
        if (fragmentB == null) {
            fragmentB = new BFragment();
            transaction.add(R.id.index_content_fl, fragmentB);
        } else {
            transaction.show(fragmentB);
        }

    }

    /**
     * 跳转到fragmentC
     */
    private void changeTabToFragmentC(FragmentTransaction transaction) {
        setTitle(R.string.fragment_c_title);
        if (fragmentC == null) {
            fragmentC = new CFragment();
            transaction.add(R.id.index_content_fl, fragmentC);
        } else {
            transaction.show(fragmentC);
        }
    }

    /**
     * 跳转到fragmentD
     */
    private void changeTabToFragmentD(FragmentTransaction transaction) {
        setTitle(R.string.fragment_d_title);
        if (fragmentD == null) {
            fragmentD = new DFragment();
            transaction.add(R.id.index_content_fl, fragmentD);
        } else {
            transaction.show(fragmentD);
        }
    }


    /**
     * 先隐藏掉所有的fragment
     */
    private void hideAllFragment(FragmentTransaction transaction) {
        if (fragmentA != null && !fragmentA.isHidden()) {
            transaction.hide(fragmentA);
        }
        if (fragmentB != null && !fragmentB.isHidden()) {
            transaction.hide(fragmentB);
        }
        if (fragmentC != null && !fragmentC.isHidden()) {
            transaction.hide(fragmentC);
        }
        if (fragmentD != null && !fragmentD.isHidden()) {
            transaction.hide(fragmentD);
        }
    }
}
