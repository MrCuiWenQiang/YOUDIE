package com.my.fakerti.widget.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.my.fakerti.R;

/**
 * 加载带有进度条的对话框
 * created by Mr.C at 2017 04 14:51
 **/
public class MProgressDiolog extends Dialog{

    private TextView tv_message;

    public MProgressDiolog(Context context,boolean iscan) {
        this(context,null,iscan);
    }

    public MProgressDiolog(Context context, String message ,boolean iscan) {
        super(context,R.style.mp_dialog);
        initview(context,message);
        setCancelable(iscan);
    }
    private void initview(Context context, String message){
        View view = LayoutInflater.from(context).inflate(R.layout.dg_mprogress,null);
    }
}
