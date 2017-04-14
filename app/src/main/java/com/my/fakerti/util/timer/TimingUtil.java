package com.my.fakerti.util.timer;

import android.os.Handler;
import android.os.Message;

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
    public synchronized void goTask(Task in_task,Long delay){
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
        timer.schedule(timerTask,delay);
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
            if (in_task!=null){
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
