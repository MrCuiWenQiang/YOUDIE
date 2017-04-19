package com.my.fakerti.widget.view.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;

import com.my.fakerti.R;

/**
 * 对话框基类
 * Created by Mr.C on 2017/4/19 0019.
 */

public abstract class BaseDialog extends Dialog {

    private Context context;

    protected BaseDialog(@NonNull Context context, boolean iscan){
        super(context, R.style.mp_dialog);
        this.context = context;
        setCancelable(iscan);
        initview(context);
    }

    public abstract void initview(Context context);

    /**
     * 获取布局
     * @param id
     * @return
     */
    public View inflate(@LayoutRes int id){
        return LayoutInflater.from(context).inflate(id,null);
    }


    /**
     * 获取图片
     * @param id
     * @return
     */
    //因为getResources().getDrawable（int）方法过时，而新方法只能在5.0上使用所以用此方法替代
    public Drawable getDrawable(@DrawableRes int id){
        return ContextCompat.getDrawable(context,id);
    }
}
