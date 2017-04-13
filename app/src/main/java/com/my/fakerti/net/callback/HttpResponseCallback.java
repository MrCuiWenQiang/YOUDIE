package com.my.fakerti.net.callback;/**
 * Created by Administrator on 2017/4/1 0001.
 */

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.my.fakerti.net.base.ApiResult;
import com.my.fakerti.util.json.JsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 普通响应回调
 * created by Mr.C at 2017年4月1日 09:45:28
 **/
public abstract class HttpResponseCallback implements Callback {
    public static Handler handler;

    public HttpResponseCallback(){
        if (handler == null){
            handler = new Handler(Looper.getMainLooper());
        }
    }

    @Override
    public void onFailure(Call arg0, IOException arg1) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                // 连接失败
                onFailed(0);
            }
        });
    }

    @Override
    public void onResponse(Call arg0, Response response) throws IOException {

        System.out.print("11111");

        if (response != null) {

            String result = response.body().string();

            if (!TextUtils.isEmpty(result)) {
                try {
                    final ApiResult apiResult = JsonUtil.convertJsonToObject(result, ApiResult.class);
                    if (null != apiResult) {
                        if (apiResult.getStatusCode() == ApiResult.TOKEN_INVAILL){
                            // Token 过期

                        } else {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    onSuccess(apiResult);
                                }
                            });
                        }
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                // 对象为null
                                onFailed(4);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // 异常
                            onFailed(3);
                        }
                    });
                }
            } else {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // 返回的结果字符串是null或空字符
                        onFailed(2);
                    }
                });
            }
        } else {
            handler.post(new Runnable() {

                @Override
                public void run() {
                    // Response 为null
                    onFailed(1);
                }
            });
        }
    }
    /**
     * 成功返回结果
     *
     * @param result
     */
    public abstract void onSuccess(ApiResult result);

    /**
     * 失败
     *
     * @param status
     */
    public abstract void onFailed(int status);
}
