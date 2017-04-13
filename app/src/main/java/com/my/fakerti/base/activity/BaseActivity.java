package com.my.fakerti.base.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.my.fakerti.base.activity.manager.ActivityManager;
import com.my.fakerti.widget.view.dialog.MessageDialog;

/**
 * Activity的基类
 * 应该有的功能：对话框  等待框  错误信息保存收集
 * Created by Mr.C on 2017/3/31 0031.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private int resId = -1;

    public BaseActivity(@LayoutRes int resId){
        this.resId = resId;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(resId);
        ActivityManager.getActivityManager().add(this);

        initViews();
        initData();
    }

    /**
     * 初始化界面，对界面进行赋值等操作
     */
    public abstract void initViews();

    /**
     * 初始化界面，对界面进行赋值等操作
     */
    public abstract void initData();

    /**
     * 弹出对话框 ，只有确定选项的对话框
     * @param message  提示信息
     * @param deputy_message 消息的详细描述 可为空
     * @param iscancelable 是否点击返回键可以取消对话框
     */
    public void showDialog(@NonNull String message , @Nullable String deputy_message,@NonNull boolean iscancelable){
        final MessageDialog dialog =new MessageDialog(this,iscancelable);
        dialog.setMain_message(message);
        dialog.gonebt_cancel();
        if (TextUtils.isEmpty(deputy_message)){
            dialog.gonet_deputy_message();
        }else{
            dialog.setDeputy_message(deputy_message);
        }
        dialog.setOnclick(new MessageDialog.OnDialogClick() {
            @Override
            public void confirm(MessageDialog dialog) {
                dialog.dismiss();

            }

            @Override
            public void cancel(MessageDialog dialog) {
                dialog.dismiss();

            }


        });
        dialog.show();
    }

    /**
     *弹出对话框  带有确定和取消按钮
     * @param message  提示消息
     * @param deputy_message  消息的详细描述 可为空
     * @param iscancelable
     * @param onDialogClick 按钮点击回调类
     */
    public void showDialog(@NonNull String message ,@Nullable String deputy_message,@NonNull boolean iscancelable,@NonNull MessageDialog.OnDialogClick onDialogClick){
        final MessageDialog dialog =new MessageDialog(this,iscancelable);
        dialog.setMain_message(message);
        if (TextUtils.isEmpty(deputy_message)){
            dialog.gonet_deputy_message();
        }else{
            dialog.setDeputy_message(deputy_message);
        }
        dialog.setOnclick(onDialogClick);
        dialog.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getActivityManager().finish(this);
    }

    //退出APP
    public void exit(){
        ActivityManager.getActivityManager().exit();
    }
}
