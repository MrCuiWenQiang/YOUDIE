package com.my.fakerti.util.permission.collocation;

import android.Manifest;

/**
 * Android 6.0 权限表
 * 因为6.0的动态权限机制是申请同一组的某个权限成功，则这个组内的其它权限无需申请就能访问
 * Created by Mr.C on 2017/4/1 0001.
 */

public interface Api23 {
    //日历日程权限
    String CALENDAR = Manifest.permission.READ_CALENDAR;

    //照相机权限
    String CAMERA = Manifest.permission.CAMERA;

    //允许应用访问联系人通讯录信息
    String CONTACTS = Manifest.permission.READ_CONTACTS;

    //定位权限
    String LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;

    //录音权限
    String MICROPHONE =Manifest.permission.RECORD_AUDIO;

    //电话权限
    String PHONE = Manifest.permission.WRITE_CALL_LOG;

    //传感器权限
    String SENSORS = Manifest.permission.BODY_SENSORS;

    //短信权限
    String SMS = Manifest.permission.SEND_SMS;

    //存储权限
    String STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
}
