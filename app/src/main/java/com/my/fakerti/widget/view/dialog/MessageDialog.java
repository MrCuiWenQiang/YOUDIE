package com.my.fakerti.widget.view.dialog;
/**
 * Created by Administrator on 2017/4/10 0010.
 */

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.my.fakerti.R;

/**
 * 一般的消息提示
 * created by Mr.C at 2017 04 11:16
 **/
public class MessageDialog extends Dialog{

    private TextView t_main_message;
    private TextView t_deputy_message;
    private Button bt_confirm;
    private Button bt_cancel;

    /**
     *
     * @param context

     * @param cancelable true:点击返回键取消dialge
     */
    public MessageDialog(@NonNull Context context, @NonNull boolean cancelable) {
        super(context, R.style.ma_dialog);
        initview(context);
        initwindows(context);
        setCancelable(cancelable);
    }

    private void initwindows(Context context) {
    }

    private void initview(Context context){
        View layout = LayoutInflater.from(context).inflate(R.layout.dg_mprogress,null);
         t_main_message = (TextView) layout.findViewById(R.id.main_message);
         t_deputy_message = (TextView) layout.findViewById(R.id.deputy_message);
         bt_confirm = (Button) layout.findViewById(R.id.bt_confirm);
         bt_cancel = (Button) layout.findViewById(R.id.bt_cancel);
        setContentView(layout);
    }



    public void setMain_message(String main_message) {
        t_main_message.setText(main_message);

    }


    public void setDeputy_message(String deputy_message) {
        t_deputy_message.setText(deputy_message);

    }
    public void setOnclick(final OnDialogClick onclick) {
        if (onclick != null){
            bt_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.confirm(MessageDialog.this);
                }
            });

            bt_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclick.cancel(MessageDialog.this);
                }
            });
        }
    }

    /**
     * 隐藏取消按钮
     */
    public void gonebt_cancel(){
        bt_cancel.setVisibility(View.GONE);
    }

    /**
     * 隐藏详细描述 文本控件
     */
    public void gonet_deputy_message(){
        t_deputy_message.setVisibility(View.GONE);
    }
    /**
     * 按钮回调方法
     */
    public interface OnDialogClick{
        //确定
        void confirm(MessageDialog dialog);
        //取消
        void  cancel(MessageDialog dialog);
    }
}
