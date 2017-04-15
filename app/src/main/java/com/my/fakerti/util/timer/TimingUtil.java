package com.my.fakerti.util.timer;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务工具类
 * Created by Mr.c on 2017/4/13 0013.
 */

public class TimingUtil {

    public long starttime = 1000;

    private Timer timer;
    private Task in_task;
    private TimerTask timerTask;

    /**
     * 开始任务
     * @param in_task
     * @param delay
     */
    public synchronized void goTask(@NonNull Task in_task, @Nullable long delay){
        this.in_task = in_task;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        if (delay <=0 ){
            delay = starttime;
        }
        timer.schedule(timerTask,delay,delay);
    }

    /**
     * 停止任务
     */
    public synchronized void stopTask(){
        timer.cancel();
    }

    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (in_task!=null && msg.what == 1){
                in_task.startStask();
            }
            super.handleMessage(msg);
        }
    };

    //要启动的任务
    public interface Task{
        void startStask();
    }
}
