package com.my.fakerti.compatible;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

/**
 * 此类主要面向图片等资源获取功能：解决版本中过时的方法,提供各个API通用方法
 * Created by M on 2017/4/19 0019.
 */

public class C_Resources {

    /**
     * 获取图片
     * @param id
     * @return
     */
    //因为getResources().getDrawable（int）方法过时，而新方法只能在5.0上使用所以用此方法替代
    public static Drawable getDrawable(Context context, @DrawableRes int id){
        return ContextCompat.getDrawable(context,id);
    }
}
