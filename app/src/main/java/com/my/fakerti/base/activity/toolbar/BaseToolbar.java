package com.my.fakerti.base.activity.toolbar;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * 标题栏的基类
 * Created by Mr.c on 2017/5/3 0003.
 */

public abstract class BaseToolbar extends AppCompatActivity{
    private int layoutResID =-1;

    //实现引用子类的xml布局文件
    protected abstract int getContentChildView();


    //按钮点击事件
    public interface OnClickListener{
        void onclick();
    }

}
