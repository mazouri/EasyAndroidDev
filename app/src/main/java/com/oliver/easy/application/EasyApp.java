package com.oliver.easy.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.oliver.easy.helpers.SharedPrefenrenceHelper;
import com.oliver.easy.utils.log.EasyLog;
import com.oliver.easy.utils.log.EasyLogHelper;

/**
 * Created by wangdong on 16-1-5.
 */
public class EasyApp extends Application{

    private static final String TAG = EasyLogHelper.class.getSimpleName();
    private static EasyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static EasyApp get(Context context) {
        return (EasyApp) context.getApplicationContext();
    }

    public static EasyApp getInstance() {
        return mInstance;
    }

    @Override
    public void onTerminate() {
        if (EasyLog.DEBUG) {
            EasyLog.d(TAG, "onTerminate called");
        }

        super.onTerminate();

        System.exit(0);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }

    //记录第一次登陆，处理第一次登陆的操作
    public boolean isFirstTimeLaunched() {
        return SharedPrefenrenceHelper.getSavedFirstTimeLaunch();
    }

    public void recordAppFirstTimeLaunched() {
        SharedPrefenrenceHelper.setSavedFirstTimeLaunch(true);
    }
}
