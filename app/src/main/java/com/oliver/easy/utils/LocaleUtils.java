package com.oliver.easy.utils;

import android.content.Context;

import java.util.Locale;

/**
 * Created by wangdong on 16-1-6.
 */
public class LocaleUtils {

    /**
     * 系统语言是否为中文
     * @param context
     * @return
     */
    public static boolean isZh(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }
}
