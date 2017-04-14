package com.my.fakerti.widget.view.progressbar.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.my.fakerti.util.timer.TimingUtil;

/**
 * 进度条的基类
 * Created by Mr.C on 2017/4/13 0013.
 */

public class BaseProgressBar extends View implements TimingUtil.Task{
    private TimingUtil timingUtil;
    public BaseProgressBar(Context context) {
        this(context,null);
    }

    public BaseProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (istask()){
            timingUtil = new TimingUtil();
            timingUtil.goTask(this,timingUtil.starttime);
        }
    }

    //是否开启循环
    protected Boolean istask() {
        return false;
    }

    /**
     * 需要做循环处理的代码逻辑写在此处
     */
    @Override
    public void startStask() {

    }

    /**
     * 停止循环
     */
    public void stopStask(){
        if (timingUtil != null){
            timingUtil.stopTask();
        }
    }
}
