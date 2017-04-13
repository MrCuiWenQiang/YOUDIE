package com.my.fakerti.permission.collocation;

/**
 * Android7.0权限表
 * created by Mr.C at 2017 04 14:16
 * Android 6.0之后的版本增加了运行时权限应用程序在执行每个需要系统权限的功能时，需要添加权限请求代码（默认权限禁止），否则应用程序无法响应；Android 7.0在Android 6.0的基础上，对系统权限进一步更改，这次的权限更改包括三个方面：
 *1.APP应用程序的私有文件不再向使用者放宽
 *2. Intent组件传递file://URI的方式可能给接收器留下无法访问的路径，触发FileUriExposedException异常，推荐使用FileProvider
 *3.DownloadManager不再按文件名分享私人存储的文件。旧版应用在访问COLUMN_LOCAL_FILENAME时可能出现无法访问的路径。面向 Android 7.0 或更高版本的应用在尝试访问 COLUMN_LOCAL_FILENAME 时会触发 SecurityException
 **/
public interface Api24 {
}
