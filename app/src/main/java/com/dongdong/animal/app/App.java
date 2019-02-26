package com.dongdong.animal.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        String thisProcess = getThisProcessName();
        String mainProcess = getApplicationContext().getPackageName();
        if (!TextUtils.isEmpty(thisProcess) && thisProcess.equals(mainProcess)) {
            Log.d("App ", "//////////////////////");
            Log.d("App ", "这里是主进程，我们在这里进行一些主进程资源的初始化");
            Log.d("App ", "进程名称是：" + thisProcess);
            Log.d("App ", "//////////////////////");
        } else {
            Log.d("App ", "//////////////////////");
            Log.d("App ", "这里是其他进程，我们在这里进行一些其他进程资源的初始化");
            Log.d("App ", "进程名称是：" + thisProcess);
            Log.d("App ", "//////////////////////");
        }
    }


    private String getThisProcessName() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService
                (Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return processName;
    }

}
