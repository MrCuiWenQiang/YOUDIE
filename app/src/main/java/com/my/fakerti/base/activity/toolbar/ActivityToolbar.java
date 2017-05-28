package com.my.fakerti.base.activity.toolbar;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.my.fakerti.R;

/**
 * 常用的标题 带有左部按钮点击返回，右置菜单功能
 * Created by Mr.c on 2017/5/3 0003.
 */

public abstract class ActivityToolbar extends BaseToolbar{
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private TextView title;
    private BaseToolbar.OnClickListener onClickListener;
    private int menuid = -1;



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_basetoolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        title = (TextView) findViewById(R.id.title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater.from(ActivityToolbar.this).inflate(getContentChildView(),frameLayout,true);
    }

    /**
     * 设置标题
     * @param text
     */
    protected  void setTitle(String text){
        if (!TextUtils.isEmpty(text)){
            title.setText(text);
        }
    }


    /**
     * 设置左上角按钮
     * @param iconResId 左上角图标ID
     * @param onClickListener 点击事件
     */
    protected void setLeftButton(int iconResId,BaseToolbar.OnClickListener onClickListener){
        toolbar.setNavigationIcon(iconResId);
        this.onClickListener=onClickListener;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            if (onClickListener != null){
                onClickListener.onclick();
            }
        }
        return true;
    }

    /**
     * 设置菜单
     * @param menuid menu文件ID
     * @param itemClickListener 菜单子项点击事件
     */
    protected  void setMenu(@MenuRes int menuid,Toolbar.OnMenuItemClickListener itemClickListener){
        this.menuid = menuid;
        toolbar.setOnMenuItemClickListener(itemClickListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuid != -1){
            getMenuInflater().inflate(menuid,menu);
        }
        return true;
    }


}
