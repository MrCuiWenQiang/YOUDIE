package com.my.fakerti;


import android.util.Log;

import com.my.fakerti.base.activity.BaseActivity;
import com.my.fakerti.widget.view.dialog.MessageDialog;

public class MainActivity extends BaseActivity {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    public void initViews() {
        throw new RuntimeException("error测试");
    }

    @Override
    public void initData() {

    }
}
