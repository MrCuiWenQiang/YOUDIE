package com.my.fakerti.base.activity.manager;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.Stack;

/**
 * Activity管理类
 * 利用stack 先进后出 后进先出的特性进行activity管理
 * created by Mr.C at 2017 04 14:08
 **/
public class ActivityManager {

    private static ActivityManager manager;
    private  static Stack<AppCompatActivity> activityStack;

    private ActivityManager(){
    }

    public static ActivityManager getActivityManager(){
        if (manager == null){
            manager = new ActivityManager();
        }
        return  manager;
    }

    /**
     * 添加AppCompatActivity到堆栈
     */
    public void add(AppCompatActivity activity){
        if(activityStack == null){
            activityStack = new Stack<AppCompatActivity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前AppCompatActivity（堆栈中最后一个压入的）
     */
    public AppCompatActivity current(){
        AppCompatActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束AppCompatActivity
     */
    public void finish(AppCompatActivity activity) {
        if (activity != null){
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束所有的AppCompatActivity
     */
    public void exit(){
        finishAllActivity();
    }



    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }





}
