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

/**
 * 自定义环形加载控件
 * created by Mr.C at 2017 04 13:46
 **/
public class SurroundProgressBar extends View{

    private Context context;
    private double r;
    //圆环的宽度
    private int widthing = 5;
    //圆环的边距
    private int pandding = 10;

    //圆环颜色 默认为蓝色
    private int outsideColor = R.color.blue;
    /**
     * 圆环渐变颜色
     * */
    private int[] doughnutColors = new int[]{0xFFDAF6FE,0xFF45C3E5,0xFF45C3E5,0xFF45C3E5,0xFF45C3E5};
    //初始角度
    private float progress = 0;
    //最终进度360
    private float MAXProgress = 360;
    //圆点照片
    private Drawable drawable;

    public SurroundProgressBar(Context context) {
       this(context,null);
    }

    public SurroundProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        drawable = getResources().getDrawable(R.drawable.circle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint circlePaint = getOutsideCircle();
        RectF rectF = new RectF(dip2px(context,pandding),dip2px(context,pandding),getWidth()-dip2px(context,pandding),getHeight()-dip2px(context,pandding));
        canvas.drawArc(rectF,progress,MAXProgress,false,circlePaint);

        drawProgress(canvas,circlePaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //绘制等待框外部的圆
    private Paint getOutsideCircle(){
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(getOutsideColor());
        mPaint.setStrokeWidth(dip2px(context,widthing));
        return mPaint;
    }
    /**
     * 绘制圆弧
     * */
    private void drawProgress(Canvas canvas,Paint mPaint){
        if(progress > 90){
            mPaint.setColor(getResources().getColor(R.color.RoundFillColor));
            mPaint.setStrokeWidth(dip2px(context, widthing));
            RectF oval = new RectF( dip2px(context, pandding)
                    , dip2px(context, pandding)
                    , getWidth()-dip2px(context, pandding)
                    , getHeight()-dip2px(context, pandding));
            canvas.drawArc(oval, 0, progress-90, false, mPaint);    //绘制圆弧
            r = getHeight()/2f-dip2px(context,pandding);
        }
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.rotate(-90, getWidth() / 2, getHeight() / 2);
        mPaint.setStrokeWidth(dip2px(context, widthing));
        mPaint.setShader(new SweepGradient(getWidth() / 2, getHeight() / 2, doughnutColors, null));
        RectF oval = new RectF( dip2px(context, pandding)
                , dip2px(context, pandding)
                , getWidth()-dip2px(context, pandding)
                , getHeight()-dip2px(context, pandding));
        canvas.drawArc(oval, 0, progress<90?progress:90, false, mPaint);    //绘制圆弧
        canvas.rotate(90, getWidth() / 2, getHeight() / 2);
        mPaint.reset();
        drawImageDot(canvas,mPaint);

    }
    //绘制圆点
    private void drawImageDot(Canvas canvas,Paint paint){
        r = getHeight()/2f - dip2px(context,pandding);
        double hu = Math.PI*Double.parseDouble(String.valueOf(progress))/180.0;
        double p = Math.sin(hu)*r;
        double q = Math.cos(hu)*r;
        float x = (float)((getWidth() - drawable.getIntrinsicWidth())/2f+p);
        float y = (float)((getHeight() - drawable.getIntrinsicHeight())/2f+q);
        canvas.drawBitmap(((BitmapDrawable)drawable).getBitmap(),x,y,paint);
    }

    /**
     *
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    private int dip2px(Context context, float dpValue){
        float scale  = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale + 0.5f);
    }


    public int getOutsideColor() {
        return outsideColor;
    }

    /**
     * 设置外部圆框的颜色
     * @param outsideColor
     */
    public void setOutsideColor(int outsideColor) {
        this.outsideColor = outsideColor;
    }
}
