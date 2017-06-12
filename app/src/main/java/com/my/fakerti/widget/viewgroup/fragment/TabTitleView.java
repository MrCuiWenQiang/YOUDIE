package com.my.fakerti.widget.viewgroup.fragment;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by Mr.c on 2017/6/11 0011.
 */

public class TabTitleView extends FrameLayout{

    private LayoutInflater inflater;
    private List<Integer> xmlid;
    private TitleLayout titleLayout;


    public TabTitleView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public TabTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TabTitleView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        inflater = LayoutInflater.from(context);
    }


    public void setTitlexml(List<Integer> xmlid){
        this.xmlid = xmlid;
        addlayout(0);
    }

    public void setTitleLayout( TitleLayout titleLayout){
        this.titleLayout = titleLayout;
    }

    public void addlayout(int index){
        View layout = inflater.inflate(xmlid.get(index),null);
        if (titleLayout != null){
            titleLayout.viewsetting(index, layout);
        }
        this.addView(layout);
    }

    public interface TitleLayout{
        //设置布局控件属性 注册事件等
        public void viewsetting(int index, View v);
    }


    @Override
    public void addView(View child) {
        super.addView(child);
    }

    @Override
    public void removeView(View view) {
        super.removeView(view);
    }
}
