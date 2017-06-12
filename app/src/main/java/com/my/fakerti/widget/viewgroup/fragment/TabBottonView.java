package com.my.fakerti.widget.viewgroup.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.fakerti.R;

import java.util.List;

/**
 * 模仿主流分页标签
 * Created by Mr.c on 2017/5/29 0029.
 */

public class TabBottonView extends LinearLayout {

    private String TAG =this.getClass().getPackage().getName();
    //View默认最小高度
    private static final int DEFAULT_MIN_WIDTH =100;
    /**
     * 记录最新的选择位置
     */
    private int lastPosition = -1;

    private OnTabItemSelectListener onTabItemSelectListener;
    private OnSecondSelectListener onSecondSelectListener;
    private List<TabItemView> tabItemViews;

    private  TabTitleView tagTabTitleView;

    public TabBottonView(Context context) {
        super(context);
    }

    public TabBottonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TabBottonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //设置常用属性
    private void TabBottonViewValue(){
        FrameLayout.LayoutParams params =new FrameLayout.LayoutParams(this.getLayoutParams());
        params.gravity= Gravity.CENTER;
        this.setLayoutParams(params);
    }

    //设置数据
    public void setTabItemViews(List<TabItemView> tabItemViews){
        setTabItemViews(tabItemViews,null);
    }

    /**
     * 连接TabTitleView
     * @param tagTabTitleView
     */
    public void setTAGTabTitleView(TabTitleView tagTabTitleView){
        this.tagTabTitleView = tagTabTitleView;
    }

    /**
     * 连接 Viewpager
     * @param viewPager　
     */
    public void setUpWithViewPager(final ViewPager viewPager){

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updatePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setOnTabItemSelectListener(new OnTabItemSelectListener() {
            @Override
            public void onTabItemSelect(int position) {
                viewPager.setCurrentItem(position);
            }
        });
    }

    public void setTabItemViews(List<TabItemView> tabItemViews,View centerView){
        if (this.tabItemViews != null){
            throw new RuntimeException("Duplicate settings are not allowed!");
        }

        if (tabItemViews == null || tabItemViews.size()<2){
            throw new RuntimeException(TAG+": tabItemViews is Null or tabItemViews Length less than 2 !");
        }
        this.tabItemViews = tabItemViews;
        for (int i=0; i<tabItemViews.size(); i++) {

            if (centerView != null && i == tabItemViews.size() / 2){
                this.addView(centerView);
            }

            final TabItemView tabItemView = tabItemViews.get(i);

            this.addView(tabItemView);

            final int finalI = i;

            tabItemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (finalI == lastPosition){
                        // 第二次点击
                        if (onSecondSelectListener != null){
                            onSecondSelectListener.onSecondSelect(finalI);
                        }
                        return ;
                    }

                    updatePosition(finalI);

                    if (onTabItemSelectListener != null){
                        onTabItemSelectListener.onTabItemSelect(finalI);
                    }
                }
            });
        }

        /**
         * 将所有的 TabItem 设置为 初始化状态
         */
        for (TabItemView tab : tabItemViews) {
            tab.setStatus(TabItemView.DEFAULT);
        }

        /**
         * 默认状态选择第一个
         */
        updatePosition(0);
    }


    /**
     * 更新被选中 Tab Item 的状态
     * 恢复上一个 Tab Item 的状态
     */
    public void updatePosition(int position){
        Log.e("ddd",position+"");
        if (lastPosition != position){
            tabItemViews.get(position).setStatus(TabItemView.PRESS);
            if (lastPosition != -1) {
                tabItemViews.get(lastPosition).setStatus(TabItemView.DEFAULT);
            }
            if (tagTabTitleView != null){
                tagTabTitleView.addlayout(position);
            }
            lastPosition = position;
        }
    }

    //设置事件监听(单次点击按钮)
    public void setOnTabItemSelectListener(OnTabItemSelectListener onTabItemSelectListener){
        this.onTabItemSelectListener = onTabItemSelectListener;
    }

    //设置事件监听(多次点击同一按钮)
    public void setOnSecondSelectListener(OnSecondSelectListener onSecondSelectListener){
        this.onSecondSelectListener = onSecondSelectListener;
    }

    /**
     * 第二次被选择的监听器
     */
    public interface OnSecondSelectListener{
        void onSecondSelect(int position);
    }

    /**
     * 第一次被选择的监听器
     */
    public interface OnTabItemSelectListener{
        void onTabItemSelect(int position);
    }

    public static class TabItemView extends LinearLayout{
        /**
         * 两个状态 选中、未选中
         */
        public final static int PRESS = 1;
        public final static int DEFAULT = 2;

        private TextView item_title;
        private ImageView item_icon;

        //选中状态下图标变化
        private int iconResPress;
        private int iconResDef;

        //选中状态下文字颜色变化 默认黑色
        private int textResPress = R.color.black;
        private int textResDef = R.color.black;


        /**
         *
         * @param context
         * @param title
         * @param iconResDef 未选中状态下的图标
         * @param iconResPress 选中状态下的图标
         */
        public TabItemView(Context context,String title,int iconResDef, int iconResPress) {
            super(context);
            this.iconResDef = iconResDef;
            this.iconResPress = iconResPress;

            init(title);
        }
        /**
         *
         * @param context
         * @param title
         * @param iconResDef 未选中状态下的图标
         * @param iconResPress 选中状态下的图标
         * @param textResDef 未选中状态下的标题颜色
         * @param textResPress 选中状态下的标题颜色
         */
        public TabItemView(Context context,String title,int iconResDef, int iconResPress,int textResDef, int textResPress) {
            super(context);
            this.iconResDef = iconResDef;
            this.iconResPress = iconResPress;
            this.textResDef = textResDef;
            this.textResPress = textResPress;
            init(title);
        }

        private void init(String title){
            View view = LayoutInflater.from(getContext()).inflate(R.layout.view_tabitem,this);
            item_title = (TextView) view.findViewById(R.id.item_text);
            item_icon= (ImageView) view.findViewById(R.id.item_icon);

            LayoutParams layoutParams =new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            view.setLayoutParams(layoutParams);

            item_title.setText(title);
        }
        //设置选中状态
        public void setStatus(int status){
            item_title.setTextColor(ContextCompat.getColor(getContext(),status == PRESS? textResPress : textResDef));
            item_icon.setImageResource(status == PRESS? iconResPress : iconResDef);        }
    }
}
