package com.my.fakerti.base.context.Exception;

import android.content.Context;
import android.util.Log;

/**
 * 捕捉APP全局错误
 * Created by Mr.c on 2017/4/11 0011.
 */

public class AppExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static AppExceptionHandler handler = new AppExceptionHandler();

    private Thread.UncaughtExceptionHandler mDeHandler;

    private Context context;

    public void AppExceptionHandler(){
    }

    public static AppExceptionHandler getAppExceptionHandler(){
        return handler;
    }

    public void init(Context context){
        this.context=context;
        mDeHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String threadName = t.getName();
        String error = e.toString();
        Log.e("d",threadName+"+"+error);
    }
}
