package com.my.fakerti.widget.view.dialog.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
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

    public View inflate(@LayoutRes int id){
        return LayoutInflater.from(context).inflate(id,null);
    }
}
