package com.my.fakerti.base.activity.toolbar;

import android.support.annotation.LayoutRes;
import com.my.fakerti.base.activity.BaseActivity;


/**
 * 标题栏的基类
 * Created by Mr.c on 2017/5/3 0003.
 */

public abstract class BaseToolbar extends BaseActivity{
    private int layoutResID =-1;



    //子类必须实现该方法。该方法实现引用子类的xml布局文件，无须调用setContentView（）
    protected abstract int getContentChildView();


    //按钮点击事件
    public interface OnClickListener{
        void onclick();
    }

}
