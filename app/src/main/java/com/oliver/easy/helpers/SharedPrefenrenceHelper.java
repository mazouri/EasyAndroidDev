package com.oliver.easy.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.oliver.easy.application.EasyApp;

/**
 * Created by wangdong on 16-1-5.
 */
public class SharedPrefenrenceHelper {

    private static final String SP = "EasyAndroidDevSP";

    public static boolean getShareBoolean(String name) {
        SharedPreferences share = EasyApp.getInstance().getSharedPreferences(SP, Context.MODE_PRIVATE);
        return share.getBoolean(name, false);
    }

    public static void saveShareBoolean(String name, boolean flag) {
        SharedPreferences share = EasyApp.getInstance().getSharedPreferences(SP, Context.MODE_PRIVATE);
        Editor editor = share.edit();
        editor.putBoolean(name, flag);
        editor.commit();
    }

    public static int getShareInt(String name) {
        SharedPreferences share = EasyApp.getInstance().getSharedPreferences(SP, Context.MODE_PRIVATE);
        return share.getInt(name, 0);
    }

    /**
     * @param name
     * @param value 默认值 default value
     * @return
     */
    public static int getShareInt(String name, int value) {
        SharedPreferences share = EasyApp.getInstance().getSharedPreferences(SP, Context.MODE_PRIVATE);
        return share.getInt(name, value);
    }

    public static void saveShareInt(String name, int value) {
        SharedPreferences share = EasyApp.getInstance().getSharedPreferences(SP, Context.MODE_PRIVATE);
        Editor editor = share.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    public static String getShareString(String name, String value) {
        SharedPreferences share = EasyApp.getInstance().getSharedPreferences(SP, Context.MODE_PRIVATE);
        return share.getString(name, value);
    }

    public static void saveShareString(String name, String value) {
        SharedPreferences share = EasyApp.getInstance().getSharedPreferences(SP, Context.MODE_PRIVATE);
        Editor editor = share.edit();
        editor.putString(name, value);
        editor.commit();
    }

    private static long getShareLong(String name) {
        SharedPreferences share = EasyApp.getInstance().getSharedPreferences(SP, Context.MODE_PRIVATE);
        return share.getLong(name, 0L);
    }

    private static void saveShareLong(String name, Long value) {
        SharedPreferences share = EasyApp.getInstance().getSharedPreferences(SP, Context.MODE_PRIVATE);
        Editor editor = share.edit();
        editor.putLong(name, value);
        editor.commit();
    }

    /*
    为了统一SharedPreference管理，保存的所有数据，都用如下方式调用
     */
    public static final String SAVED_DATA_1 = "saved_data_1";
    public static final String SAVED_FIRST_TIME_LAUNCH = "saved_first_time_launch";

    public static void setSavedData1(int savedId) {
        SharedPrefenrenceHelper.saveShareInt(SAVED_DATA_1, savedId);
    }

    public static int getSavedData1() {
        return SharedPrefenrenceHelper.getShareInt(SAVED_DATA_1);
    }

    public static void setSavedFirstTimeLaunch(boolean savedId) {
        SharedPrefenrenceHelper.saveShareBoolean(SAVED_FIRST_TIME_LAUNCH, savedId);
    }

    public static boolean getSavedFirstTimeLaunch() {
        return SharedPrefenrenceHelper.getShareBoolean(SAVED_FIRST_TIME_LAUNCH);
    }

    public static final String KEY_THEME = "key_theme";
    public static final String DEFAULT_THEME = "default_theme";

    public static void setSavedTheme(String theme) {
        SharedPrefenrenceHelper.saveShareString(KEY_THEME, theme);
    }

    public static String getSavedTheme() {
        return SharedPrefenrenceHelper.getShareString(KEY_THEME, DEFAULT_THEME);
    }

    public static final String KEY_WALLPAPER_DIM = "key_wallpaper_dim";
    public static final int DEFAULT_WALLPAPER_DIM = 1;
    public static final int MAX_WALLPAPER_DIM = 1;

    public static void setSavedWallpaperDim(int wallpaperDim) {
        SharedPrefenrenceHelper.saveShareInt(KEY_WALLPAPER_DIM, wallpaperDim);
    }

    public static int getSavedWallpaperDim() {
        return SharedPrefenrenceHelper.getShareInt(KEY_WALLPAPER_DIM, DEFAULT_WALLPAPER_DIM);
    }


}
