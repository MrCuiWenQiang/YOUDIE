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

    //View默认最小宽度
    private static final int DEFAULT_MIN_WIDTH =100;

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
     * 测量模式 兼容wrap_content模式
     * @param origin
     * @return
     */
    public int measure(int origin){
        int result = DEFAULT_MIN_WIDTH;
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        switch (specMode){
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result,specSize);
        }
        return  result;
    }

    /**
     *
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue){
        float scale  = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale + 0.5f);
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
