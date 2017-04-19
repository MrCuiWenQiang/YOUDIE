package com.my.fakerti.widget.view.dialog;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.fakerti.R;
import com.my.fakerti.compatible.C_Resources;
import com.my.fakerti.widget.view.dialog.base.BaseDialog;

/**
 * 加载带有进度条的对话框
 * created by Mr.C at 2017 04 14:51
 **/
public class MProgressDiolog extends BaseDialog {

    private TextView main_message;
    private ImageView pro_im;

    public MProgressDiolog(@NonNull Context context, boolean iscan) {
        super(context, iscan);
    }

    @Override
    public void initview(Context context) {
        View view = inflate(R.layout.dg_mprogress);
        main_message = (TextView) view.findViewById(R.id.main_message);
        pro_im = (ImageView) view.findViewById(R.id.pro_im);
        Animation manimation = AnimationUtils.loadAnimation(context,R.anim.progress_surround);
        manimation.setInterpolator(new LinearInterpolator());
        pro_im.startAnimation(manimation);
        setContentView(view);
    }

    //替换加载中的图片
    public void setProgressImage(@DrawableRes int id){
        pro_im.setImageDrawable(C_Resources.getDrawable(getContext(),id));
    }

    //要显示的提示
    public void setText(String text){
        main_message.setText(text);
    }

}
