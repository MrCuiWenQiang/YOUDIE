package com.my.fakerti.net.callback;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 文件下载专用调度
 * Created by Mr.C on 2017/3/25 0025.
 */

public abstract class FileHttpResponseHanlder implements Callback {

    private  final String TAG = "com.my.fakerti.net.callback.FileHttpResponseHanlder.class";

    //文件存储路径
    private String file_Path;
    //文件名
    private String file_name;

    private long tmps = 0;

    private File filen;

    private  Handler handler;
    //下载连接错误
    public static int download_code_error = 0;
    //连接超时
    public static int download_code_overtime = 1;
    //下载过程中失败错误
    public static int download_code_dlrror = 2;

    public FileHttpResponseHanlder(String file_Path, String file_name){
        if (handler == null){
            handler = new Handler(Looper.getMainLooper());
        }
       this.file_Path=file_Path;
       this.file_name=file_name;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onDownloadFail(download_code_error);
            }
        });
    }

   @Override
    public void onResponse(Call call, Response response) throws IOException {
        if ( response.code() == 200) {
            InputStream fileInstream = null;
            OutputStream file_out = null;
            byte[] tmp = new byte[1024];
            int length = 0;
            try {
                fileInstream = response.body().byteStream();
                long fileLength = response.body().contentLength();
                int read = 0;
                filen = getfile();
                file_out = new FileOutputStream(filen);
                while ((length = fileInstream.read(tmp)) != -1) {
                    file_out.write(tmp, 0, length);
                    read +=  length;
                    if (length > 0) {
                        tmps = read * 100 / fileLength;
                        if (tmps >= 100) {
                            tmps = 100;
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                onDwnload(tmps);
                            }
                        });
                    }
                }

                file_out.flush();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                         onComplete(filen);
                    }
                });

            }catch(Exception e){
                Log.e(TAG,"--------------"+e.toString());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onDownloadFail(download_code_dlrror);
                    }
                });
            }finally {
                if (fileInstream != null){
                    fileInstream.close();
                }
                if (file_out != null){
                    file_out.close();
                }


            }
            }else{

            handler.post(new Runnable() {
                @Override
                public void run() {
                    onDownloadFail(download_code_overtime);
                }
            });
            }

    }



    /**
 *  下载中
 * @param file_downloadlength  文件总长度
 * @return
 */
    public abstract void onDwnload(long file_downloadlength);
    //下载完成
    public   abstract void onComplete(File file);
    //下载失败
    public abstract void onDownloadFail(int code);

    /**
     * 创建文件
     * 可能引起的BUG:遇到同名文件造成数据增加问题
     * @return
     * @throws IOException
     */
    private File getfile() throws IOException {
        File file = new File(file_Path+file_name);
        if (!file.exists()){
            File pathf = new File(file_Path);
            if (!pathf.exists()){
                pathf.mkdirs();
            }
            file.createNewFile();
        }
        return file;
    }

}


