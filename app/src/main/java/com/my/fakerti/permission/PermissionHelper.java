package com.my.fakerti.permission;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * android 6.0 动态权限帮助类
 * created by Mr.C at 2017年4月1日 13:53:15
 **/
public class PermissionHelper {

    public static int PERMISSION_CODE = 110;

    /**
     * 动态权限处理
     * @param context
     * @param permissiontext 需要的权限字段 可参考collocation包下的API23类
     */
    @TargetApi(23)
    public void isHavePM(Context context,String permissiontext){
        if (isversion()){
            if ((ContextCompat.checkSelfPermission(context, permissiontext))!=
                    PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions((AppCompatActivity)context,new String[]{permissiontext}, PERMISSION_CODE);
            }
        }
    }

    //判断版本是否为android6.0
    public boolean isversion(){
        return Build.VERSION.SDK_INT>= Build.VERSION_CODES.M;
    }
}
