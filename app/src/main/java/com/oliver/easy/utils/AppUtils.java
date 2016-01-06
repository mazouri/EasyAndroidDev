package com.oliver.easy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;

import com.oliver.easy.application.EasyApp;
import com.oliver.easy.utils.log.EasyLogHelper;

/**
 * Created by wangdong on 16-1-6.
 */
public class AppUtils {
    private static final EasyLogHelper sLogger = EasyLogHelper.getLogger(AppUtils.class.getSimpleName());

    // 客户端版本版本号
    public static String getVersionName(Activity activity) {
        String version = null;
        try {
            version = String.valueOf(activity.getPackageManager()
                    .getPackageInfo(activity.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }

    // 获取包名
    public static String getPackageName(Activity activity) {

        String packageName = null;
        try {
            packageName = String.valueOf(activity.getPackageManager()
                    .getPackageInfo(activity.getPackageName(), 0).packageName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageName;
    }

    public static int getAppVersionCodeFromPackage(Context ctx) {

        try {
            PackageManager manager = ctx.getPackageManager();
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            if (info != null) {
                return info.versionCode;
            }
        } catch (Exception e) {
            sLogger.v("getAppVersionCodeFromPackage Exception ->: " + e.getMessage());

        }
        return -1;
    }

    // 获取Android_id
    public static String getAndroidId() {

        return Settings.Secure.getString(EasyApp.getInstance()
                .getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
