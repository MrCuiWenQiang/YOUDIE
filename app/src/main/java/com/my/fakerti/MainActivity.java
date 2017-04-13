package com.my.fakerti;



import android.os.Handler;
import android.os.Message;

import com.my.fakerti.base.activity.BaseActivity;
import com.my.fakerti.widget.view.progressbar.RoundProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
   private RoundProgressBar roundProgressBar2;
    int i = 1;
    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    public void initViews() {
          roundProgressBar2= (RoundProgressBar) findViewById(R.id.roundProgressBar2);
        timer.schedule(task, 1000, 1000); // 1s后执行task,经过1s再次执行
    }
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                i++;
                roundProgressBar2.setProgress(i);
            }
            super.handleMessage(msg);
        };
    };
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            // 需要做的事:发送消息
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };
    @Override
    public void initData() {

    }
}
