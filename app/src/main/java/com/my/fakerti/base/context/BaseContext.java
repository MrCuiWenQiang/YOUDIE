package com.my.fakerti.base.context;

import android.app.Application;

import com.my.fakerti.base.context.Exception.AppExceptionHandler;
import com.my.fakerti.net.HttpHelper;

/**
 * 基类全局上下文类
 * Created by Mr.C on 2017/3/31 0031.
 */

public class BaseContext extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initnet();
        initException();
    }

    private void initException() {
        AppExceptionHandler.getAppExceptionHandler().init(getApplicationContext());
    }

    //初始化Okhttp3
    private final void initnet() {
        HttpHelper.init();
    }
}
