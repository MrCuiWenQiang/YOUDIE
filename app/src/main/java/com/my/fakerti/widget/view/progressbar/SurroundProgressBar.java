package com.my.fakerti.widget.view.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.my.fakerti.R;
import com.my.fakerti.widget.view.progressbar.base.BaseProgressBar;

/**
 * 自定义环形加载控件
 * created by Mr.C at 2017 04 13:46
 **/
public class SurroundProgressBar extends BaseProgressBar{


    public SurroundProgressBar(Context context) {
       this(context,null);
    }

    public SurroundProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec),measure(heightMeasureSpec));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}
