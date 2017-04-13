package com.my.fakerti.net;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.my.fakerti.net.callback.FileHttpResponseHanlder;
import com.my.fakerti.net.callback.HttpResponseCallback;
import com.my.fakerti.net.cookie.Cookie;
import com.my.fakerti.util.json.JsonUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Okhttp3网络访问类
 * 注意：init方法应当在应用启动的时候调用，只调用一次即可
 * 缺少功能:多文件上传
 * Created by Mr.C on 2017/3/31 0031.
 */

public  class HttpHelper {
    private static  HttpHelper httpHelper;
    private  OkHttpClient okHttpClient;

    private static String contentType = "application/json; charset=utf-8";
    //默认请求时间
    protected static int httpConnectTimeOut = 60 * 1000;

    private HttpHelper(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.cookieJar(new Cookie());
        builder.connectTimeout(httpConnectTimeOut, TimeUnit.MILLISECONDS);
        okHttpClient = builder.build();
    }

    public static void init(){
        if (httpHelper == null){
            synchronized (HttpHelper.class){
                if (httpHelper == null) {
                    httpHelper = new HttpHelper();
                }
            }
        }
    }

    public static HttpHelper getHttpHelper(){
        if (httpHelper == null){
            throw new RuntimeException("请先调用init()方法初始化okhttp3");
        }
            return httpHelper;
    }

    /**
     * 通用POST访问方法
     * @param url
     * @param o
     * @param headers
     * @param callback
     */
    public void post(@NonNull String url,@NonNull Object o, @Nullable HashMap<String,String> headers, @NonNull HttpResponseCallback callback){
        Request request=null;
        String data = JsonUtil.convertObjectToJson(o);
        RequestBody body =RequestBody.create(MediaType.parse(contentType), data);
        if (headers != null){
            final Headers hd = Headers.of(headers);
            request = new Request.Builder().url(url).headers(hd).post(body).build();
        }else {
            request = new Request.Builder().url(url).post(body).build();
        }
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 通用GET方法
     * @param url
     * @param headers
     * @param response
     */
    public  void newget(@NonNull String url,@Nullable HashMap<String, String> headers, @NonNull HttpResponseCallback response) {
        Request request = null;
        if (headers != null){
            Headers hd = Headers.of(headers);
            request = new Request.Builder().url(url).headers(hd).build();
        }else {
            request = new Request.Builder().url(url).build();
        }
        Call call = okHttpClient.newCall(request);
        call.enqueue(response);
    }

    /**
     * get方式下载文件
     *
     */
    public void downloadFile(@NonNull String url ,@Nullable HashMap<String,String> headers ,@NonNull FileHttpResponseHanlder fileHttpResponseHanlder){
        Request request = null;
        if (headers != null){
            Headers hd = Headers.of(headers);
            request = new Request.Builder().url(url).headers(hd).get().build();
        }else {
            request = new Request.Builder().url(url).get().build();
        }

        Call call = okHttpClient.newCall(request);
        call.enqueue(fileHttpResponseHanlder);
    }


    /**
     * post 表单方式上传文件-----支持多参数---只支持单文件
     * @param url
     * @param headers
     * @param filekey 递时的文件参数名
     * @param filepath 文件的路径+文件名
     * @param filename 文件名
     * @param bodynv 多参数集合
     * @param responseHandler
     */
    public void updateFile(@NonNull String url,@Nullable HashMap<String,String> headers,@NonNull String filekey,@NonNull String filepath,@NonNull String filename,@Nullable HashMap<String,String> bodynv,@NonNull HttpResponseCallback  responseHandler){
        Request request;
        MultipartBody.Builder requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(filekey,filename,RequestBody.create(null,new File(filepath)));
        if (bodynv != null && bodynv.size() >0){
            requestBody=addParameter(requestBody,bodynv);
        }
        if (headers != null)
        {
            Headers hd = Headers.of(headers);
             request =new Request.Builder().url(url).headers(hd).post(requestBody.build()).build();
        }else {
             request =new Request.Builder().url(url).post(requestBody.build()).build();
        }

        Call call = okHttpClient.newCall(request);
        call.enqueue(responseHandler);
    }

    /**
     * 将HashMap key-value加入请求体中
     * @param builder
     * @param bodynv
     */
    private   MultipartBody.Builder addParameter(MultipartBody.Builder builder,HashMap<String,String> bodynv){
        Iterator iter  = bodynv.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String name = (String) entry.getValue();
            builder = builder.addFormDataPart(key,name);
        }
        return builder;
    }
}
